package com.orangicetech.smartglasslauncher;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.orangicetech.smartglasslauncher.fragment.SGCameraFragment;
import com.orangicetech.smartglasslauncher.fragment.SGMusicFragment;
import com.orangicetech.smartglasslauncher.fragment.SGStatusFragment;
import com.orangicetech.smartglasslauncher.fragment.SGVideoFragment;
import com.orangicetech.smartglasslauncher.ui.adapter.SGFragmentStatusPagerAdapter;


public class SGLauncherActivity extends FragmentActivity {
    static SGLauncherActivity instance = null;
    SGFragmentStatusPagerAdapter pagerAdapter = null;
    ViewPager mViewPager = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sg_pager_activity_layout);

        Log.d("smart glass", "SGLauncherActivity: onCreate");

        instance = this;
        pagerAdapter =
                new SGFragmentStatusPagerAdapter(
                        getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(pagerAdapter);

    }
    public void init(){
        SGCameraFragment cameraFragment = SGCameraFragment.newInstance("","");
        SGStatusFragment statusFragment =  SGStatusFragment.newInstance("","");;
        SGMusicFragment musicFragment =  SGMusicFragment.newInstance("","");;
        SGVideoFragment videoFragment =  SGVideoFragment.newInstance("","");;
    }



}
