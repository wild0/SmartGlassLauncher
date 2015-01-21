package com.orangicetech.smartglasslauncher.launcher;

import com.orangicetech.smartglasslauncher.R;

/**
 * Created by roy on 2015/1/21.
 */
class Widget extends ItemInfo {
    int layoutResource;

    static Widget makeSearch() {
        Widget w = new Widget();
        w.itemType = SGLauncherSetting.Favorites.ITEM_TYPE_WIDGET_SEARCH;
        w.spanX = 4;
        w.spanY = 1;
        w.layoutResource = R.layout.widget_search;
        return w;
    }
}
