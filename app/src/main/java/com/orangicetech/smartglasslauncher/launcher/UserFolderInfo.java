package com.orangicetech.smartglasslauncher.launcher;

import android.content.ContentValues;

import java.util.ArrayList;

/**
 * Created by roy on 2015/1/21.
 */
class UserFolderInfo extends FolderInfo {
    /**
     * The apps and shortcuts
     */
    ArrayList<ApplicationInfo> contents = new ArrayList<ApplicationInfo>();

    UserFolderInfo() {
        itemType = SGLauncherSetting.Favorites.ITEM_TYPE_USER_FOLDER;
    }

    /**
     * Add an app or shortcut
     *
     * @param item
     */
    public void add(ApplicationInfo item) {
        contents.add(item);
    }

    /**
     * Remove an app or shortcut. Does not change the DB.
     *
     * @param item
     */
    public void remove(ApplicationInfo item) {
        contents.remove(item);
    }

    @Override
    void onAddToDatabase(ContentValues values) {
        super.onAddToDatabase(values);
        values.put(SGLauncherSetting.Favorites.TITLE, title.toString());
    }
}
