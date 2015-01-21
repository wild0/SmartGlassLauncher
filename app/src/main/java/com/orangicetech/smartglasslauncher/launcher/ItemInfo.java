package com.orangicetech.smartglasslauncher.launcher;

import android.content.ContentValues;
import android.graphics.Bitmap;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by roy on 2015/1/21.
 */

class ItemInfo {

    static final int NO_ID = -1;

    /**
     * The id in the settings database for this item
     */
    long id = NO_ID;

    /**
     * One of {@link SGLauncherSetting.Favorites#ITEM_TYPE_APPLICATION},
     * {@link SGLauncherSetting.Favorites#ITEM_TYPE_SHORTCUT},
     * {@link SGLauncherSetting.Favorites#ITEM_TYPE_USER_FOLDER}, or
     * {@link SGLauncherSetting.Favorites#ITEM_TYPE_APPWIDGET}.
     */
    int itemType;

    /**
     * The id of the container that holds this item. For the desktop, this will be
     * {@link SGLauncherSetting.Favorites#CONTAINER_DESKTOP}. For the all applications folder it
     * will be {@link #NO_ID} (since it is not stored in the settings DB). For user folders
     * it will be the id of the folder.
     */
    long container = NO_ID;

    /**
     * Iindicates the screen in which the shortcut appears.
     */
    int screen = -1;

    /**
     * Indicates the X position of the associated cell.
     */
    int cellX = -1;

    /**
     * Indicates the Y position of the associated cell.
     */
    int cellY = -1;

    /**
     * Indicates the X cell span.
     */
    int spanX = 1;

    /**
     * Indicates the Y cell span.
     */
    int spanY = 1;

    /**
     * Indicates whether the item is a gesture.
     */
    boolean isGesture = false;

    ItemInfo() {
    }

    ItemInfo(ItemInfo info) {
        id = info.id;
        cellX = info.cellX;
        cellY = info.cellY;
        spanX = info.spanX;
        spanY = info.spanY;
        screen = info.screen;
        itemType = info.itemType;
        container = info.container;
    }

    /**
     * Write the fields of this item to the DB
     *
     * @param values
     */
    void onAddToDatabase(ContentValues values) {
        values.put(SGLauncherSetting.BaseLauncherColumns.ITEM_TYPE, itemType);
        if (!isGesture) {
            values.put(SGLauncherSetting.Favorites.CONTAINER, container);
            values.put(SGLauncherSetting.Favorites.SCREEN, screen);
            values.put(SGLauncherSetting.Favorites.CELLX, cellX);
            values.put(SGLauncherSetting.Favorites.CELLY, cellY);
            values.put(SGLauncherSetting.Favorites.SPANX, spanX);
            values.put(SGLauncherSetting.Favorites.SPANY, spanY);
        }
    }

    static void writeBitmap(ContentValues values, Bitmap bitmap) {
        if (bitmap != null) {
            // Try go guesstimate how much space the icon will take when serialized
            // to avoid unnecessary allocations/copies during the write.
            int size = bitmap.getWidth() * bitmap.getHeight() * 4;
            ByteArrayOutputStream out = new ByteArrayOutputStream(size);
            try {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.flush();
                out.close();

                values.put(SGLauncherSetting.Favorites.ICON, out.toByteArray());
            } catch (IOException e) {
                Log.w("Favorite", "Could not write icon");
            }
        }
    }

    void unbind() {
    }
}
