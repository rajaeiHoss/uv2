package com.hjq.widget.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public final class WrapRecyclerView extends RecyclerView {
    /* access modifiers changed from: private */
    public RecyclerView.Adapter mRealAdapter;
    /* access modifiers changed from: private */
    public final WrapRecyclerAdapter mWrapAdapter = new WrapRecyclerAdapter();

    public WrapRecyclerView(Context context) {
        super(context);
    }

    public WrapRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public WrapRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        this.mRealAdapter = adapter;
        this.mWrapAdapter.setRealAdapter(adapter);
        setItemAnimator((RecyclerView.ItemAnimator) null);
        super.setAdapter(this.mWrapAdapter);
    }

    public RecyclerView.Adapter getAdapter() {
        return this.mRealAdapter;
    }

    public void addHeaderView(View view) {
        this.mWrapAdapter.addHeaderView(view);
    }

    public <V extends View> V addHeaderView(int i) {
        View inflate = LayoutInflater.from(getContext()).inflate(i, this, false);
        addHeaderView(inflate);
        @SuppressWarnings("unchecked")
        V view = (V) inflate;
        return view;
    }

    public void removeHeaderView(View view) {
        this.mWrapAdapter.removeHeaderView(view);
    }

    public void addFooterView(View view) {
        this.mWrapAdapter.addFooterView(view);
    }

    public <V extends View> V addFooterView(int i) {
        View inflate = LayoutInflater.from(getContext()).inflate(i, this, false);
        addFooterView(inflate);
        @SuppressWarnings("unchecked")
        V view = (V) inflate;
        return view;
    }

    public void removeFooterView(View view) {
        this.mWrapAdapter.removeFooterView(view);
    }

    public int getHeaderViewsCount() {
        return this.mWrapAdapter.getHeaderViewsCount();
    }

    public int getFooterViewsCount() {
        return this.mWrapAdapter.getFooterViewsCount();
    }

    public List<View> getHeaderViews() {
        return this.mWrapAdapter.getHeaderViews();
    }

    public List<View> getFooterViews() {
        return this.mWrapAdapter.getFooterViews();
    }

    public void refreshHeaderFooterViews() {
        this.mWrapAdapter.notifyDataSetChanged();
    }

    public void adjustSpanSize() {
        final RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                public int getSpanSize(int i) {
                    if (i >= WrapRecyclerView.this.mWrapAdapter.getHeaderViewsCount()) {
                        if (i < WrapRecyclerView.this.mWrapAdapter.getHeaderViewsCount() + (WrapRecyclerView.this.mRealAdapter == null ? 0 : WrapRecyclerView.this.mRealAdapter.getItemCount())) {
                            return 1;
                        }
                    }
                    return ((GridLayoutManager) layoutManager).getSpanCount();
                }
            });
        }
    }

    private static final class WrapRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int FOOTER_VIEW_TYPE = 1073741823;
        private static final int HEADER_VIEW_TYPE = -1073741824;
        private int mCurrentPosition;
        private final List<View> mFooterViews;
        private final List<View> mHeaderViews;
        private WrapAdapterDataObserver mObserver;
        private RecyclerView.Adapter mRealAdapter;
        private RecyclerView mRecyclerView;

        private WrapRecyclerAdapter() {
            this.mHeaderViews = new ArrayList();
            this.mFooterViews = new ArrayList();
        }

        /* access modifiers changed from: private */
        public void setRealAdapter(RecyclerView.Adapter adapter) {
            WrapAdapterDataObserver wrapAdapterDataObserver;
            RecyclerView.Adapter adapter2 = this.mRealAdapter;
            if (adapter2 != adapter) {
                if (!(adapter2 == null || (wrapAdapterDataObserver = this.mObserver) == null)) {
                    adapter2.unregisterAdapterDataObserver(wrapAdapterDataObserver);
                }
                this.mRealAdapter = adapter;
                if (adapter != null) {
                    if (this.mObserver == null) {
                        this.mObserver = new WrapAdapterDataObserver(this);
                    }
                    this.mRealAdapter.registerAdapterDataObserver(this.mObserver);
                    if (this.mRecyclerView != null) {
                        notifyDataSetChanged();
                    }
                }
            }
        }

        public int getItemCount() {
            RecyclerView.Adapter adapter = this.mRealAdapter;
            return getHeaderViewsCount() + (adapter != null ? adapter.getItemCount() : 0) + getFooterViewsCount();
        }

        public int getItemViewType(int i) {
            this.mCurrentPosition = i;
            int headerViewsCount = getHeaderViewsCount();
            RecyclerView.Adapter adapter = this.mRealAdapter;
            int itemCount = adapter != null ? adapter.getItemCount() : 0;
            int i2 = i - headerViewsCount;
            if (i < headerViewsCount) {
                return HEADER_VIEW_TYPE;
            }
            return i2 < itemCount ? this.mRealAdapter.getItemViewType(i2) : FOOTER_VIEW_TYPE;
        }

        public int getPosition() {
            return this.mCurrentPosition;
        }

        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            if (i == HEADER_VIEW_TYPE) {
                return newWrapViewHolder(this.mHeaderViews.get(getPosition()));
            }
            if (i != FOOTER_VIEW_TYPE) {
                int itemViewType = this.mRealAdapter.getItemViewType(getPosition() - getHeaderViewsCount());
                if (itemViewType == HEADER_VIEW_TYPE || itemViewType == FOOTER_VIEW_TYPE) {
                    throw new IllegalStateException("Please do not use this type as itemType");
                }
                RecyclerView.Adapter adapter = this.mRealAdapter;
                if (adapter == null) {
                    return null;
                }
                return adapter.onCreateViewHolder(viewGroup, itemViewType);
            }
            List<View> list = this.mFooterViews;
            int position = getPosition() - getHeaderViewsCount();
            RecyclerView.Adapter adapter2 = this.mRealAdapter;
            return newWrapViewHolder(list.get(position - (adapter2 != null ? adapter2.getItemCount() : 0)));
        }

        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            int itemViewType;
            if (this.mRealAdapter != null && (itemViewType = getItemViewType(i)) != HEADER_VIEW_TYPE && itemViewType != FOOTER_VIEW_TYPE) {
                this.mRealAdapter.onBindViewHolder(viewHolder, getPosition() - getHeaderViewsCount());
            }
        }

        private WrapViewHolder newWrapViewHolder(View view) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(view);
            }
            return new WrapViewHolder(view);
        }

        public long getItemId(int i) {
            if (this.mRealAdapter == null || i <= getHeaderViewsCount() - 1 || i >= getHeaderViewsCount() + this.mRealAdapter.getItemCount()) {
                return super.getItemId(i);
            }
            return this.mRealAdapter.getItemId(i - getHeaderViewsCount());
        }

        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            this.mRecyclerView = recyclerView;
            RecyclerView.Adapter adapter = this.mRealAdapter;
            if (adapter != null) {
                adapter.onAttachedToRecyclerView(recyclerView);
            }
        }

        public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
            this.mRecyclerView = null;
            RecyclerView.Adapter adapter = this.mRealAdapter;
            if (adapter != null) {
                adapter.onDetachedFromRecyclerView(recyclerView);
            }
        }

        public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
            if (viewHolder instanceof WrapViewHolder) {
                viewHolder.setIsRecyclable(false);
                return;
            }
            RecyclerView.Adapter adapter = this.mRealAdapter;
            if (adapter != null) {
                adapter.onViewRecycled(viewHolder);
            }
        }

        public boolean onFailedToRecycleView(RecyclerView.ViewHolder viewHolder) {
            RecyclerView.Adapter adapter = this.mRealAdapter;
            if (adapter == null) {
                return super.onFailedToRecycleView(viewHolder);
            }
            return adapter.onFailedToRecycleView(viewHolder);
        }

        public void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
            RecyclerView.Adapter adapter = this.mRealAdapter;
            if (adapter != null) {
                adapter.onViewAttachedToWindow(viewHolder);
            }
        }

        public void onViewDetachedFromWindow(RecyclerView.ViewHolder viewHolder) {
            RecyclerView.Adapter adapter = this.mRealAdapter;
            if (adapter != null) {
                adapter.onViewDetachedFromWindow(viewHolder);
            }
        }

        /* access modifiers changed from: private */
        public void addHeaderView(View view) {
            if (!this.mHeaderViews.contains(view) && !this.mFooterViews.contains(view)) {
                this.mHeaderViews.add(view);
                notifyDataSetChanged();
            }
        }

        /* access modifiers changed from: private */
        public void removeHeaderView(View view) {
            if (this.mHeaderViews.remove(view)) {
                notifyDataSetChanged();
            }
        }

        /* access modifiers changed from: private */
        public void addFooterView(View view) {
            if (!this.mFooterViews.contains(view) && !this.mHeaderViews.contains(view)) {
                this.mFooterViews.add(view);
                notifyDataSetChanged();
            }
        }

        /* access modifiers changed from: private */
        public void removeFooterView(View view) {
            if (this.mFooterViews.remove(view)) {
                notifyDataSetChanged();
            }
        }

        /* access modifiers changed from: private */
        public int getHeaderViewsCount() {
            return this.mHeaderViews.size();
        }

        /* access modifiers changed from: private */
        public int getFooterViewsCount() {
            return this.mFooterViews.size();
        }

        /* access modifiers changed from: private */
        public List<View> getHeaderViews() {
            return this.mHeaderViews;
        }

        /* access modifiers changed from: private */
        public List<View> getFooterViews() {
            return this.mFooterViews;
        }
    }

    private static final class WrapViewHolder extends RecyclerView.ViewHolder {
        private WrapViewHolder(View view) {
            super(view);
        }
    }

    private static final class WrapAdapterDataObserver extends RecyclerView.AdapterDataObserver {
        private final WrapRecyclerAdapter mWrapAdapter;

        private WrapAdapterDataObserver(WrapRecyclerAdapter wrapRecyclerAdapter) {
            this.mWrapAdapter = wrapRecyclerAdapter;
        }

        public void onChanged() {
            this.mWrapAdapter.notifyDataSetChanged();
        }

        public void onItemRangeChanged(int i, int i2, Object obj) {
            WrapRecyclerAdapter wrapRecyclerAdapter = this.mWrapAdapter;
            wrapRecyclerAdapter.notifyItemRangeChanged(wrapRecyclerAdapter.getHeaderViewsCount() + i, i2, obj);
        }

        public void onItemRangeChanged(int i, int i2) {
            WrapRecyclerAdapter wrapRecyclerAdapter = this.mWrapAdapter;
            wrapRecyclerAdapter.notifyItemRangeChanged(wrapRecyclerAdapter.getHeaderViewsCount() + i, i2);
        }

        public void onItemRangeInserted(int i, int i2) {
            WrapRecyclerAdapter wrapRecyclerAdapter = this.mWrapAdapter;
            wrapRecyclerAdapter.notifyItemRangeInserted(wrapRecyclerAdapter.getHeaderViewsCount() + i, i2);
        }

        public void onItemRangeRemoved(int i, int i2) {
            WrapRecyclerAdapter wrapRecyclerAdapter = this.mWrapAdapter;
            wrapRecyclerAdapter.notifyItemRangeRemoved(wrapRecyclerAdapter.getHeaderViewsCount() + i, i2);
        }

        public void onItemRangeMoved(int i, int i2, int i3) {
            WrapRecyclerAdapter wrapRecyclerAdapter = this.mWrapAdapter;
            wrapRecyclerAdapter.notifyItemMoved(wrapRecyclerAdapter.getHeaderViewsCount() + i, this.mWrapAdapter.getHeaderViewsCount() + i2);
        }
    }
}
