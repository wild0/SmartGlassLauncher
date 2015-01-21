package com.orangicetech.smartglasslauncher.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.orangicetech.smartglasslauncher.model.media.MusicModel;

import java.util.ArrayList;

/**
 * Created by roy on 2015/1/20.
 */
public class MusicAdapter extends BaseAdapter {

    ArrayList<MusicModel> nodes = null;
    Context ctx = null;

    public MusicAdapter(Context ctx){
        this.ctx = ctx;
        nodes = new ArrayList<MusicModel>();
    }


    @Override
    public int getCount() {
        return nodes.size();
    }

    @Override
    public Object getItem(int position) {
        return nodes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
