package com.streamax.config.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.List;

public abstract class CustomBaseAdapter<ITEMBEANTYPE> extends BaseAdapter {
    public List<ITEMBEANTYPE> mDataSource = new ArrayList();

    public long getItemId(int i) {
        return (long) i;
    }

    /* access modifiers changed from: protected */
    public abstract View initConvertView(int i, View view, ViewGroup viewGroup);

    public CustomBaseAdapter(List<ITEMBEANTYPE> list) {
        this.mDataSource = list;
    }

    public int getCount() {
        List<ITEMBEANTYPE> list = this.mDataSource;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public Object getItem(int i) {
        List<ITEMBEANTYPE> list = this.mDataSource;
        if (list == null) {
            return null;
        }
        return list.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return initConvertView(i, view, viewGroup);
    }
}
