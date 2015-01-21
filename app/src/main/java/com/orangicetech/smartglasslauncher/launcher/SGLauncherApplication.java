package com.orangicetech.smartglasslauncher.launcher;

import android.app.Application;
import android.app.SearchManager;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.os.Handler;

/**
 * Created by roy on 2015/1/20.
 */
public class SGLauncherApplication extends Application {

    public final SGLauncherModel mModel;

    public SGLauncherApplication() {
        mModel = new SGLauncherModel(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        final int screenSize = getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;
        //SCREEN SIZE
        //sIsScreenLarge = screenSize == Configuration.SCREENLAYOUT_SIZE_LARGE ||
        //        screenSize == Configuration.SCREENLAYOUT_SIZE_XLARGE;

        //DENSITY
        //sScreenDensity = getResources().getDisplayMetrics().density;


        //mIconCache = new IconCache(this);

        //manage the data
        //mModel = new LauncherModel(this, mIconCache);

        //REG LISTENER
        IntentFilter filter = new IntentFilter(Intent.ACTION_PACKAGE_ADDED);
        filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        filter.addAction(Intent.ACTION_PACKAGE_CHANGED);
        filter.addDataScheme("package");
        registerReceiver(mModel, filter);
        filter = new IntentFilter();
        filter.addAction(Intent.ACTION_EXTERNAL_APPLICATIONS_AVAILABLE);
        filter.addAction(Intent.ACTION_EXTERNAL_APPLICATIONS_UNAVAILABLE);
        filter.addAction(Intent.ACTION_LOCALE_CHANGED);
        filter.addAction(Intent.ACTION_CONFIGURATION_CHANGED);
        registerReceiver(mModel, filter);
        filter = new IntentFilter();
        filter.addAction(SearchManager.INTENT_GLOBAL_SEARCH_ACTIVITY_CHANGED);
        registerReceiver(mModel, filter);
        filter = new IntentFilter();
        filter.addAction(SearchManager.INTENT_ACTION_SEARCHABLES_CHANGED);
        registerReceiver(mModel, filter);

        ContentResolver resolver = getContentResolver();

        resolver.registerContentObserver(SGLauncherSetting.Favorites.CONTENT_URI, true, mFavoritesObserver);
    }

    /**
     * There's no guarantee that this function is ever called.
     */
    @Override
    public void onTerminate() {
        super.onTerminate();

        unregisterReceiver(mModel);

        ContentResolver resolver = getContentResolver();
        resolver.unregisterContentObserver(mFavoritesObserver);
    }

    /**
     * Receives notifications whenever the user favorites have changed.
     */
    private final ContentObserver mFavoritesObserver = new ContentObserver(new Handler()) {
        @Override
        public void onChange(boolean selfChange) {
            // TODO: lockAllApps();
            mModel.setWorkspaceDirty();
            mModel.startLoader(SGLauncherApplication.this, false);
        }
    };

    SGLauncherModel setLauncher(SGLauncherActivity launcher) {
        mModel.initialize(launcher);
        return mModel;
    }

}
