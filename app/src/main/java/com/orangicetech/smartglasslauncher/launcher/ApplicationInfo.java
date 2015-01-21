package com.orangicetech.smartglasslauncher.launcher;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by roy on 2015/1/21.
 */
public class ApplicationInfo extends ItemInfo {

    /**
     * The application name.
     */
    CharSequence title;

    /**
     * The intent used to start the application.
     */
    Intent intent;

    /**
     * The application icon.
     */
    Drawable icon;

    /**
     * What we show in all apps, including the text.
     */
    Bitmap iconBitmap;

    /**
     * When set to true, indicates that the icon has been resized.
     */
    boolean filtered;

    /**
     * Indicates whether the icon comes from an application's resource (if false)
     * or from a custom Bitmap (if true.)
     */
    boolean customIcon;

    /**
     * If isShortcut=true and customIcon=false, this contains a reference to the
     * shortcut icon as an application's resource.
     */
    Intent.ShortcutIconResource iconResource;

    ApplicationInfo() {
        itemType = SGLauncherSetting.BaseLauncherColumns.ITEM_TYPE_SHORTCUT;
    }

    public ApplicationInfo(ApplicationInfo info) {
        super(info);
        title = info.title.toString();
        intent = new Intent(info.intent);
        if (info.iconResource != null) {
            iconResource = new Intent.ShortcutIconResource();
            iconResource.packageName = info.iconResource.packageName;
            iconResource.resourceName = info.iconResource.resourceName;
        }
        icon = info.icon;
        filtered = info.filtered;
        customIcon = info.customIcon;
    }

    /**
     * Creates the application intent based on a component name and various launch flags.
     * Sets {@link #itemType} to {@link SGLauncherSetting.BaseLauncherColumns#ITEM_TYPE_APPLICATION}.
     *
     * @param className   the class name of the component representing the intent
     * @param launchFlags the launch flags
     */
    final void setActivity(ComponentName className, int launchFlags) {
        intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setComponent(className);
        intent.setFlags(launchFlags);
        itemType = SGLauncherSetting.BaseLauncherColumns.ITEM_TYPE_APPLICATION;
    }

    @Override
    void onAddToDatabase(ContentValues values) {
        super.onAddToDatabase(values);

        String titleStr = title != null ? title.toString() : null;
        values.put(SGLauncherSetting.BaseLauncherColumns.TITLE, titleStr);

        String uri = intent != null ? intent.toUri(0) : null;
        values.put(SGLauncherSetting.BaseLauncherColumns.INTENT, uri);

        if (customIcon) {
            values.put(SGLauncherSetting.BaseLauncherColumns.ICON_TYPE,
                    SGLauncherSetting.BaseLauncherColumns.ICON_TYPE_BITMAP);
            Bitmap bitmap = ((FastBitmapDrawable) icon).getBitmap();
            writeBitmap(values, bitmap);
        } else {
            values.put(SGLauncherSetting.BaseLauncherColumns.ICON_TYPE,
                    SGLauncherSetting.BaseLauncherColumns.ICON_TYPE_RESOURCE);
            if (iconResource != null) {
                values.put(SGLauncherSetting.BaseLauncherColumns.ICON_PACKAGE,
                        iconResource.packageName);
                values.put(SGLauncherSetting.BaseLauncherColumns.ICON_RESOURCE,
                        iconResource.resourceName);
            }
        }
    }

    @Override
    public String toString() {
        return title.toString();
    }

    @Override
    void unbind() {
        super.unbind();
        icon.setCallback(null);
    }

    public Intent getIntent() {
        return intent;
    }

    public static void dumpApplicationInfoList(String tag, String label,
                                               ArrayList<ApplicationInfo> list) {
        Log.d(tag, label + " size=" + list.size());
        for (ApplicationInfo info : list) {
            Log.d(tag, "   title=\"" + info.title + "\" icon=" + info.icon
                    + " iconBitmap=" + info.iconBitmap + " filtered=" + info.filtered
                    + " customIcon=" + info.customIcon);
        }
    }
}