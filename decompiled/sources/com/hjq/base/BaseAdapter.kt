package com.hjq.base

import android.content.Context
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hjq.base.action.ResourcesAction

abstract class BaseAdapter<VH : RecyclerView.ViewHolder>(context: Context?) :
    RecyclerView.Adapter<VH>(),
    ResourcesAction {
    private var mChildClickListeners: SparseArray<OnChildClickListener>? = null
    private var mChildLongClickListeners: SparseArray<OnChildLongClickListener>? = null
    private var mItemClickListener: OnItemClickListener? = null
    private var mItemLongClickListener: OnItemLongClickListener? = null
    private var mPositionOffset: Int = 0
    private var mRecyclerView: RecyclerView? = null
    private val mContext: Context = context ?: throw IllegalArgumentException("are you ok?")

    interface OnChildClickListener {
        fun onChildClick(recyclerView: RecyclerView?, view: View, position: Int)
    }

    interface OnChildLongClickListener {
        fun onChildLongClick(recyclerView: RecyclerView?, view: View, position: Int): Boolean
    }

    interface OnItemClickListener {
        fun onItemClick(recyclerView: RecyclerView?, view: View, position: Int)
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(recyclerView: RecyclerView?, view: View, position: Int): Boolean
    }

    final override fun onBindViewHolder(holder: VH, position: Int) {
        mPositionOffset = position - holder.adapterPosition
        (holder as BaseAdapter<*>.ViewHolder).onBindView(position)
    }

    fun getRecyclerView(): RecyclerView? {
        return mRecyclerView
    }

    override fun getContext(): Context {
        return mContext
    }

    abstract inner class ViewHolder : RecyclerView.ViewHolder, View.OnClickListener, View.OnLongClickListener {
        constructor(layoutId: Int) : this(
            LayoutInflater.from(getContext()).inflate(layoutId, mRecyclerView, false)
        )

        constructor(view: View) : super(view) {
            if (mItemClickListener != null) {
                view.setOnClickListener(this)
            }
            if (mItemLongClickListener != null) {
                view.setOnLongClickListener(this)
            }

            val childClickListeners = mChildClickListeners
            if (childClickListeners != null) {
                for (i in 0 until childClickListeners.size()) {
                    val childView = findViewById<View>(childClickListeners.keyAt(i))
                    if (childView != null) {
                        childView.setOnClickListener(this)
                    }
                }
            }

            val childLongClickListeners = mChildLongClickListeners
            if (childLongClickListeners != null) {
                for (i in 0 until childLongClickListeners.size()) {
                    val childView = findViewById<View>(childLongClickListeners.keyAt(i))
                    if (childView != null) {
                        childView.setOnLongClickListener(this)
                    }
                }
            }
        }

        protected fun getViewHolderPosition(): Int {
            return layoutPosition + mPositionOffset
        }

        override fun onClick(view: View) {
            val viewHolderPosition = getViewHolderPosition()
            if (viewHolderPosition >= 0 && viewHolderPosition < itemCount) {
                if (view === getItemView()) {
                    mItemClickListener?.onItemClick(mRecyclerView, view, viewHolderPosition)
                } else {
                    val listener = mChildClickListeners?.get(view.id)
                    if (listener != null) {
                        listener.onChildClick(mRecyclerView, view, viewHolderPosition)
                    }
                }
            }
        }

        override fun onLongClick(view: View): Boolean {
            val viewHolderPosition = getViewHolderPosition()
            if (viewHolderPosition >= 0 && viewHolderPosition < itemCount) {
                if (view === getItemView()) {
                    val listener = mItemLongClickListener
                    return listener?.onItemLongClick(mRecyclerView, view, viewHolderPosition) ?: false
                }

                val childListener = mChildLongClickListeners?.get(view.id)
                if (childListener != null) {
                    return childListener.onChildLongClick(mRecyclerView, view, viewHolderPosition)
                }
            }
            return false
        }

        abstract fun onBindView(position: Int)

        fun getItemView(): View {
            return itemView
        }

        fun <V : View> findViewById(id: Int): V? {
            return getItemView().findViewById(id)
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        mRecyclerView = recyclerView
        if (recyclerView.layoutManager == null) {
            val layoutManager = generateDefaultLayoutManager(mContext)
            if (layoutManager != null) {
                mRecyclerView!!.layoutManager = layoutManager
            }
        }
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        mRecyclerView = null
    }

    protected open fun generateDefaultLayoutManager(context: Context): RecyclerView.LayoutManager? {
        return LinearLayoutManager(context)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        checkRecyclerViewState()
        mItemClickListener = listener
    }

    fun setOnChildClickListener(id: Int, listener: OnChildClickListener?) {
        checkRecyclerViewState()
        if (mChildClickListeners == null) {
            mChildClickListeners = SparseArray()
        }
        mChildClickListeners!!.put(id, listener)
    }

    fun setOnItemLongClickListener(listener: OnItemLongClickListener?) {
        checkRecyclerViewState()
        mItemLongClickListener = listener
    }

    fun setOnChildLongClickListener(id: Int, listener: OnChildLongClickListener?) {
        checkRecyclerViewState()
        if (mChildLongClickListeners == null) {
            mChildLongClickListeners = SparseArray()
        }
        mChildLongClickListeners!!.put(id, listener)
    }

    private fun checkRecyclerViewState() {
        if (mRecyclerView != null) {
            throw IllegalStateException("are you ok?")
        }
    }
}
