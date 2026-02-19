package com.streamax.config.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class SuperAdapter extends BaseAdapter {
    public ViewHolder mHolder;

    public int getCount() {
        return 0;
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return view;
    }

    public SuperAdapter(ViewHolder viewHolder) {
        this.mHolder = viewHolder;
    }

    public abstract class ViewHolder {
        public abstract void initHolder(View view);

        public ViewHolder(View view) {
            initHolder(view);
            view.setTag(this);
        }
    }
}
