package com.orangicetech.smartglasslauncher.model;

import android.graphics.drawable.Drawable;

/**
 * Created by wild0 on 2014/12/13.
 */
public class ApplicationDetailModel {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    int type = 0;
    int mode = 0;
    int status = 0;

    String label =  "";
    String name = "";
    Drawable icon = null;
}
