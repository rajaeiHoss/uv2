package com.hjq.base;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hjq.base.BaseAdapter.ViewHolder;
import com.hjq.base.action.ResourcesAction;

public abstract class BaseAdapter<VH extends BaseAdapter<?>.ViewHolder> extends RecyclerView.Adapter<VH> implements ResourcesAction {
    /* access modifiers changed from: private */
    public SparseArray<OnChildClickListener> mChildClickListeners;
    /* access modifiers changed from: private */
    public SparseArray<OnChildLongClickListener> mChildLongClickListeners;
    private final Context mContext;
    /* access modifiers changed from: private */
    public OnItemClickListener mItemClickListener;
    /* access modifiers changed from: private */
    public OnItemLongClickListener mItemLongClickListener;
    /* access modifiers changed from: private */
    public int mPositionOffset = 0;
    /* access modifiers changed from: private */
    public RecyclerView mRecyclerView;

    public interface OnChildClickListener {
        void onChildClick(RecyclerView recyclerView, View view, int i);
    }

    public interface OnChildLongClickListener {
        boolean onChildLongClick(RecyclerView recyclerView, View view, int i);
    }

    public interface OnItemClickListener {
        void onItemClick(RecyclerView recyclerView, View view, int i);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(RecyclerView recyclerView, View view, int i);
    }

    public /* synthetic */ int getColor(int i) {
        return ResourcesAction.CC.$default$getColor(this, i);
    }

    public /* synthetic */ Drawable getDrawable(int i) {
        return ResourcesAction.CC.$default$getDrawable(this, i);
    }

    public /* synthetic */ Resources getResources() {
        return ResourcesAction.CC.$default$getResources(this);
    }

    public /* synthetic */ String getString(int i) {
        return ResourcesAction.CC.$default$getString(this, i);
    }

    public /* synthetic */ String getString(int i, Object... objArr) {
        return ResourcesAction.CC.$default$getString(this, i, objArr);
    }

    public /* synthetic */ Object getSystemService(Class cls) {
        return ResourcesAction.CC.$default$getSystemService(this, cls);
    }

    public BaseAdapter(Context context) {
        this.mContext = context;
        if (context == null) {
            throw new IllegalArgumentException("are you ok?");
        }
    }

    public final void onBindViewHolder(VH vh, int i) {
        this.mPositionOffset = i - vh.getAdapterPosition();
        vh.onBindView(i);
    }

    public RecyclerView getRecyclerView() {
        return this.mRecyclerView;
    }

    public Context getContext() {
        return this.mContext;
    }

    public abstract class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public abstract void onBindView(int i);

        public ViewHolder(BaseAdapter baseAdapter, int i) {
            this(LayoutInflater.from(baseAdapter.getContext()).inflate(i, baseAdapter.mRecyclerView, false));
        }

        public ViewHolder(View view) {
            super(view);
            if (BaseAdapter.this.mItemClickListener != null) {
                view.setOnClickListener(this);
            }
            if (BaseAdapter.this.mItemLongClickListener != null) {
                view.setOnLongClickListener(this);
            }
            if (BaseAdapter.this.mChildClickListeners != null) {
                for (int i = 0; i < BaseAdapter.this.mChildClickListeners.size(); i++) {
                    View findViewById = findViewById(BaseAdapter.this.mChildClickListeners.keyAt(i));
                    if (findViewById != null) {
                        findViewById.setOnClickListener(this);
                    }
                }
            }
            if (BaseAdapter.this.mChildLongClickListeners != null) {
                for (int i2 = 0; i2 < BaseAdapter.this.mChildLongClickListeners.size(); i2++) {
                    View findViewById2 = findViewById(BaseAdapter.this.mChildLongClickListeners.keyAt(i2));
                    if (findViewById2 != null) {
                        findViewById2.setOnLongClickListener(this);
                    }
                }
            }
        }

        /* access modifiers changed from: protected */
        public final int getViewHolderPosition() {
            return getLayoutPosition() + BaseAdapter.this.mPositionOffset;
        }

        public void onClick(View view) {
            OnChildClickListener onChildClickListener;
            int viewHolderPosition = getViewHolderPosition();
            if (viewHolderPosition >= 0 && viewHolderPosition < BaseAdapter.this.getItemCount()) {
                if (view == getItemView()) {
                    if (BaseAdapter.this.mItemClickListener != null) {
                        BaseAdapter.this.mItemClickListener.onItemClick(BaseAdapter.this.mRecyclerView, view, viewHolderPosition);
                    }
                } else if (BaseAdapter.this.mChildClickListeners != null && (onChildClickListener = (OnChildClickListener) BaseAdapter.this.mChildClickListeners.get(view.getId())) != null) {
                    onChildClickListener.onChildClick(BaseAdapter.this.mRecyclerView, view, viewHolderPosition);
                }
            }
        }

        public boolean onLongClick(View view) {
            OnChildLongClickListener onChildLongClickListener;
            int viewHolderPosition = getViewHolderPosition();
            if (viewHolderPosition >= 0 && viewHolderPosition < BaseAdapter.this.getItemCount()) {
                if (view == getItemView()) {
                    if (BaseAdapter.this.mItemLongClickListener != null) {
                        return BaseAdapter.this.mItemLongClickListener.onItemLongClick(BaseAdapter.this.mRecyclerView, view, viewHolderPosition);
                    }
                    return false;
                } else if (!(BaseAdapter.this.mChildLongClickListeners == null || (onChildLongClickListener = (OnChildLongClickListener) BaseAdapter.this.mChildLongClickListeners.get(view.getId())) == null)) {
                    return onChildLongClickListener.onChildLongClick(BaseAdapter.this.mRecyclerView, view, viewHolderPosition);
                }
            }
            return false;
        }

        public final View getItemView() {
            return this.itemView;
        }

        public final <V extends View> V findViewById(int i) {
            return getItemView().findViewById(i);
        }
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        RecyclerView.LayoutManager generateDefaultLayoutManager;
        this.mRecyclerView = recyclerView;
        if (recyclerView.getLayoutManager() == null && (generateDefaultLayoutManager = generateDefaultLayoutManager(this.mContext)) != null) {
            this.mRecyclerView.setLayoutManager(generateDefaultLayoutManager);
        }
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        this.mRecyclerView = null;
    }

    /* access modifiers changed from: protected */
    public RecyclerView.LayoutManager generateDefaultLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        checkRecyclerViewState();
        this.mItemClickListener = onItemClickListener;
    }

    public void setOnChildClickListener(int i, OnChildClickListener onChildClickListener) {
        checkRecyclerViewState();
        if (this.mChildClickListeners == null) {
            this.mChildClickListeners = new SparseArray<>();
        }
        this.mChildClickListeners.put(i, onChildClickListener);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        checkRecyclerViewState();
        this.mItemLongClickListener = onItemLongClickListener;
    }

    public void setOnChildLongClickListener(int i, OnChildLongClickListener onChildLongClickListener) {
        checkRecyclerViewState();
        if (this.mChildLongClickListeners == null) {
            this.mChildLongClickListeners = new SparseArray<>();
        }
        this.mChildLongClickListeners.put(i, onChildLongClickListener);
    }

    private void checkRecyclerViewState() {
        if (this.mRecyclerView != null) {
            throw new IllegalStateException("are you ok?");
        }
    }
}
