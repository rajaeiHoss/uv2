package com.streamax.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.streamax.view.IvView;
import com.streamax.view.VsTextView;
import com.zycs.UView.R;
import java.util.List;

public class SingleAdapter extends BaseAdapter {
    public Context mContext;
    public List<String> mDatas;
    public int mPosition;

    public long getItemId(int i) {
        return (long) i;
    }

    public SingleAdapter(Context context, List<String> list, int i) {
        this.mContext = context;
        this.mDatas = list;
        this.mPosition = i;
    }

    public int getCount() {
        List<String> list = this.mDatas;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public Object getItem(int i) {
        List<String> list = this.mDatas;
        if (list == null) {
            return null;
        }
        return list.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = View.inflate(this.mContext, R.layout.view_lv_item, (ViewGroup) null);
            new ViewHolder(view);
        }
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.mTv.SetText(this.mDatas.get(i));
        if (this.mPosition == i) {
            viewHolder.mIv.setVisibility(0);
        } else {
            viewHolder.mIv.setVisibility(8);
        }
        return view;
    }

    public class ViewHolder {
        public IvView mIv;
        public VsTextView mTv;

        public ViewHolder(View view) {
            this.mTv = (VsTextView) view.findViewById(R.id.view_lv_item_tv);
            this.mIv = (IvView) view.findViewById(R.id.view_lv_item_iv);
            view.setTag(this);
        }
    }
}
