package com.hjq.base;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

public final class RecyclerPagerAdapter extends PagerAdapter {
    private final RecyclerView.Adapter mAdapter;

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public RecyclerPagerAdapter(RecyclerView.Adapter adapter) {
        if (adapter != null) {
            this.mAdapter = adapter;
            adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                public void onChanged() {
                    RecyclerPagerAdapter.this.notifyDataSetChanged();
                }

                public void onItemRangeChanged(int i, int i2) {
                    RecyclerPagerAdapter.this.notifyDataSetChanged();
                }

                public void onItemRangeChanged(int i, int i2, Object obj) {
                    RecyclerPagerAdapter.this.notifyDataSetChanged();
                }

                public void onItemRangeInserted(int i, int i2) {
                    RecyclerPagerAdapter.this.notifyDataSetChanged();
                }

                public void onItemRangeRemoved(int i, int i2) {
                    RecyclerPagerAdapter.this.notifyDataSetChanged();
                }

                public void onItemRangeMoved(int i, int i2, int i3) {
                    RecyclerPagerAdapter.this.notifyDataSetChanged();
                }
            });
            return;
        }
        throw new IllegalArgumentException("are you ok?");
    }

    public int getCount() {
        return this.mAdapter.getItemCount();
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder createViewHolder = this.mAdapter.createViewHolder(viewGroup, 0);
        viewGroup.addView(createViewHolder.itemView);
        this.mAdapter.onBindViewHolder(createViewHolder, i);
        return createViewHolder.itemView;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }
}
