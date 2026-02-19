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

public class ChannelSelect extends ListView {
    public List<Integer> mChannelList;
    public Context mContext;
    public int mMenuHeight;

    public ChannelSelect(Context context) {
        super(context);
        this.mContext = context;
        setAdapter(new ChannelAdapter());
    }

    public ChannelSelect(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        setAdapter(new ChannelAdapter());
    }

    public ChannelSelect(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
        setAdapter(new ChannelAdapter());
    }

    public void setMenuHeight(int i) {
        this.mMenuHeight = i;
    }

    public void SetData(List<Integer> list) {
        this.mChannelList = list;
    }

    class ChannelAdapter extends BaseAdapter {
        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        ChannelAdapter() {
        }

        public int getCount() {
            if (ChannelSelect.this.mChannelList == null) {
                return 0;
            }
            return ChannelSelect.this.mChannelList.size();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView textView;
            TextView textView2;
            if (view == null) {
                textView2 = new TextView(ChannelSelect.this.mContext);
                textView = textView2;
            } else {
                textView = (TextView) view;
                textView2 = (TextView) view;
            }
            textView2.setLayoutParams(new AbsListView.LayoutParams(-1, -1));
            textView2.setGravity(17);
            textView2.setTextSize(13.0f);
            textView2.setMinHeight(ChannelSelect.this.mMenuHeight);
            if (i < ChannelSelect.this.mChannelList.size()) {
                Integer num = ChannelSelect.this.mChannelList.get(i);
                textView2.setText("CH" + (num.intValue() + 1));
                textView2.setTag(num);
            }
            return textView;
        }
    }
}
