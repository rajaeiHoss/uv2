package com.streamax.client;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;

public class DeviceSelect extends ListView {
    public Context mContext;
    List<DevInfoBean> mDevInfoList;
    List<String> mGroupNameList;
    public DbHelper mdbHelper;

    public DeviceSelect(Context context) {
        super(context);
        this.mContext = context;
        setAdapter(new DeviceAdapter());
    }

    public DeviceSelect(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        setAdapter(new DeviceAdapter());
    }

    public DeviceSelect(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
        setAdapter(new DeviceAdapter());
    }

    public void SetData(List<DevInfoBean> list, List<String> list2) {
        this.mDevInfoList = list;
        this.mGroupNameList = list2;
    }

    class DeviceAdapter extends BaseAdapter {
        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        DeviceAdapter() {
        }

        public int getCount() {
            if (DeviceSelect.this.mDevInfoList == null) {
                return 0;
            }
            int size = DeviceSelect.this.mDevInfoList.size();
            if (DeviceSelect.this.mGroupNameList == null) {
                return size;
            }
            return size + DeviceSelect.this.mGroupNameList.size();
        }

        public int getViewTypeCount() {
            return (DeviceSelect.this.mGroupNameList == null || DeviceSelect.this.mGroupNameList.size() == 0) ? 1 : 2;
        }

        public int getItemViewType(int i) {
            return (DeviceSelect.this.mGroupNameList == null || i < DeviceSelect.this.mGroupNameList.size()) ? 0 : 1;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView textView;
            TextView textView2;
            if (view == null) {
                textView2 = new TextView(DeviceSelect.this.mContext);
                textView = textView2;
            } else {
                textView = (TextView) view;
                textView2 = (TextView) view;
            }
            textView2.setLayoutParams(new AbsListView.LayoutParams(-1, -1));
            textView2.setGravity(17);
            textView2.setTextSize(24.0f);
            textView2.setMinHeight(64);
            if (i < DeviceSelect.this.mDevInfoList.size()) {
                textView2.setText(DeviceSelect.this.mDevInfoList.get(i).mDevName);
                textView2.setTag(Integer.valueOf(DeviceSelect.this.mDevInfoList.get(i).mDevId));
            } else if (DeviceSelect.this.mGroupNameList != null && DeviceSelect.this.mGroupNameList.size() > 0) {
                textView2.setText(DeviceSelect.this.mGroupNameList.get(i - DeviceSelect.this.mDevInfoList.size()));
            }
            return textView;
        }
    }
}
