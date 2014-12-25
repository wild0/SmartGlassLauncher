package com.orangicetech.smartglasslauncher.manager;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import com.orangicetech.smartglasslauncher.model.ApplicationDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wild0 on 2014/12/13.
 */
public class ApplicationDetailManager {

    PackageManager manager;
    static ArrayList<ApplicationDetail> apps = null;

    public ApplicationDetailManager(Context ctx) {
        manager = ctx.getPackageManager();
        apps = new ArrayList<ApplicationDetail>();

        Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> availableActivities = manager.queryIntentActivities(i, 0);
        for(ResolveInfo ri:availableActivities){
            ApplicationDetail app = new ApplicationDetail();

        }

    }
}
