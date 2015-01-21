package com.orangicetech.smartglasslauncher.launcher;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.orangicetech.smartglasslauncher.R;
import com.orangicetech.smartglasslauncher.fragment.SGCameraFragment;
import com.orangicetech.smartglasslauncher.fragment.SGMusicFragment;
import com.orangicetech.smartglasslauncher.fragment.SGStatusFragment;
import com.orangicetech.smartglasslauncher.fragment.SGVideoFragment;
import com.orangicetech.smartglasslauncher.ui.adapter.SGFragmentStatusPagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;


public class SGLauncherActivity extends FragmentActivity implements View.OnClickListener, View.OnLongClickListener, SGLauncherModel.Callbacks {

    static final boolean PROFILE_ROTATE = false;

    static SGLauncherActivity instance = null;
    SGFragmentStatusPagerAdapter pagerAdapter = null;
    ViewPager mViewPager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SGLauncherApplication app = ((SGLauncherApplication) getApplication());


        setContentView(R.layout.sg_pager_activity_layout);

        Log.d("smart glass", "SGLauncherActivity: onCreate");

        instance = this;
        pagerAdapter =
                new SGFragmentStatusPagerAdapter(
                        getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(pagerAdapter);

    }

    public void init() {
        SGCameraFragment cameraFragment = SGCameraFragment.newInstance("", "");
        SGStatusFragment statusFragment = SGStatusFragment.newInstance("", "");

        SGMusicFragment musicFragment = SGMusicFragment.newInstance("", "");

        SGVideoFragment videoFragment = SGVideoFragment.newInstance("", "");

    }


    @Override
    public int getCurrentWorkspaceScreen() {
        return 0;
    }

    @Override
    public void startBinding() {

    }

    @Override
    public void bindItems(ArrayList<ItemInfo> shortcuts, int start, int end) {

    }

    @Override
    public void bindFolders(HashMap<Long, FolderInfo> folders) {

    }

    @Override
    public void finishBindingItems() {

    }

    @Override
    public void bindAppWidget(SGLauncherAppWidgetInfo info) {

    }

    @Override
    public void bindAllApplications(ArrayList<ApplicationInfo> apps) {

    }

    @Override
    public void bindPackageAdded(ArrayList<ApplicationInfo> apps) {

    }

    @Override
    public void bindPackageUpdated(String packageName, ArrayList<ApplicationInfo> apps) {

    }

    @Override
    public void bindPackageRemoved(String packageName, ArrayList<ApplicationInfo> apps) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }
}
