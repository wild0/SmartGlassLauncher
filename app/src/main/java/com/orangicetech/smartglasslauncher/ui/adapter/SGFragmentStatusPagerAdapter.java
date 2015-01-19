package com.orangicetech.smartglasslauncher.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.orangicetech.smartglasslauncher.fragment.SGCameraFragment;
import com.orangicetech.smartglasslauncher.fragment.SGMusicFragment;
import com.orangicetech.smartglasslauncher.fragment.SGStatusFragment;
import com.orangicetech.smartglasslauncher.fragment.SGVideoFragment;

/**
 * Created by wild0 on 2014/12/30.
 */
public class SGFragmentStatusPagerAdapter extends FragmentStatePagerAdapter {
    public SGFragmentStatusPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        if(i==0){
            SGStatusFragment fragment = SGStatusFragment.getInstance();
            return fragment;
        }
        else if(i==1) {
            SGCameraFragment fragment = SGCameraFragment.getInstance();
            return fragment;
        }
        else if(i==2) {
            SGMusicFragment fragment = SGMusicFragment.getInstance();
            return fragment;
        }
        else if(i==3) {
            SGVideoFragment fragment = SGVideoFragment.getInstance();
            return fragment;
        }


        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
