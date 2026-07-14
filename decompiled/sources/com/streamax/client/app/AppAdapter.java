package com.streamax.client.app;

import android.content.Context;
import android.view.View;
import com.hjq.base.BaseAdapter;
import java.util.ArrayList;
import java.util.List;

public abstract class AppAdapter<T> extends BaseAdapter<BaseAdapter<?>.ViewHolder> {
    private List<T> mDataSet;
    private boolean mLastPage;
    private int mPageNumber = 1;
    private Object mTag;

    public AppAdapter(Context context) {
        super(context);
    }

    public int getItemCount() {
        return getCount();
    }

    public int getCount() {
        List<T> list = this.mDataSet;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void setData(List<T> dataSet) {
        this.mDataSet = dataSet;
        notifyDataSetChanged();
    }

    public List<T> getData() {
        return this.mDataSet;
    }

    public void addData(List<T> dataSet) {
        if (dataSet != null && dataSet.size() != 0) {
            List<T> currentDataSet = this.mDataSet;
            if (currentDataSet == null || currentDataSet.size() == 0) {
                setData(dataSet);
                return;
            }
            this.mDataSet.addAll(dataSet);
            notifyItemRangeInserted(this.mDataSet.size() - dataSet.size(), dataSet.size());
        }
    }

    public void clearData() {
        List<T> list = this.mDataSet;
        if (list != null && list.size() != 0) {
            this.mDataSet.clear();
            notifyDataSetChanged();
        }
    }

    public boolean containsItem(int position) {
        return containsItem(getItem(position));
    }

    public boolean containsItem(T item) {
        List<T> list = this.mDataSet;
        if (list == null || item == null) {
            return false;
        }
        return list.contains(item);
    }

    public T getItem(int position) {
        List<T> list = this.mDataSet;
        if (list == null) {
            return null;
        }
        return list.get(position);
    }

    public void setItem(int position, T item) {
        if (this.mDataSet == null) {
            this.mDataSet = new ArrayList();
        }
        this.mDataSet.set(position, item);
        notifyItemChanged(position);
    }

    public void addItem(T item) {
        if (this.mDataSet == null) {
            this.mDataSet = new ArrayList();
        }
        addItem(this.mDataSet.size(), item);
    }

    public void addItem(int position, T item) {
        if (this.mDataSet == null) {
            this.mDataSet = new ArrayList();
        }
        if (position < this.mDataSet.size()) {
            this.mDataSet.add(position, item);
        } else {
            this.mDataSet.add(item);
            position = this.mDataSet.size() - 1;
        }
        notifyItemInserted(position);
    }

    public void removeItem(T item) {
        int indexOf = this.mDataSet.indexOf(item);
        if (indexOf != -1) {
            removeItem(indexOf);
        }
    }

    public void removeItem(int position) {
        this.mDataSet.remove(position);
        notifyItemRemoved(position);
    }

    public int getPageNumber() {
        return this.mPageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.mPageNumber = pageNumber;
    }

    public boolean isLastPage() {
        return this.mLastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.mLastPage = lastPage;
    }

    public Object getTag() {
        return this.mTag;
    }

    public void setTag(Object tag) {
        this.mTag = tag;
    }

    public final class SimpleHolder extends BaseAdapter<BaseAdapter<?>.ViewHolder>.ViewHolder {
        public void onBindView(int position) {
        }

        public SimpleHolder(int layoutResId) {
            super((BaseAdapter) AppAdapter.this, layoutResId);
        }

        public SimpleHolder(View view) {
            super(view);
        }
    }
}
