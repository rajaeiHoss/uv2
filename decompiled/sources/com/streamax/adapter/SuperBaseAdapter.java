package com.streamax.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.List;

public abstract class SuperBaseAdapter<T> extends BaseAdapter {
    protected List<T> mDataSource;

    public long getItemId(int i) {
        return (long) i;
    }

    /* access modifiers changed from: protected */
    public abstract View initConvertView(int i, View view, ViewGroup viewGroup);

    public SuperBaseAdapter(List<T> list) {
        this.mDataSource = list;
    }

    public int getCount() {
        List<T> list = this.mDataSource;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public Object getItem(int i) {
        List<T> list = this.mDataSource;
        if (list == null) {
            return null;
        }
        return list.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return initConvertView(i, view, viewGroup);
    }
}
