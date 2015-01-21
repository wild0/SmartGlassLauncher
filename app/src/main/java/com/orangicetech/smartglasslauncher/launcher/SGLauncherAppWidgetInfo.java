package com.orangicetech.smartglasslauncher.launcher;

import android.content.ContentValues;

/**
 * Created by roy on 2015/1/21.
 */
class SGLauncherAppWidgetInfo extends ItemInfo {

    /**
     * Identifier for this widget when talking with {@link AppWidgetManager} for updates.
     */
    int appWidgetId;

    /**
     * View that holds this widget after it's been created.  This view isn't created
     * until Launcher knows it's needed.
     */
    AppWidgetHostView hostView = null;

    SGLauncherAppWidgetInfo(int appWidgetId) {
        itemType = SGLauncherSetting.Favorites.ITEM_TYPE_APPWIDGET;
        this.appWidgetId = appWidgetId;
    }

    @Override
    void onAddToDatabase(ContentValues values) {
        super.onAddToDatabase(values);
        values.put(SGLauncherSetting.Favorites.APPWIDGET_ID, appWidgetId);
    }

    @Override
    public String toString() {
        return Integer.toString(appWidgetId);
    }


    @Override
    void unbind() {
        super.unbind();
        hostView = null;
    }
}