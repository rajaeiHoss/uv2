package com.hjq.widget.layout

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class WrapRecyclerView : RecyclerView {
    private var mRealAdapter: Adapter<*>? = null
    private val mWrapAdapter = WrapRecyclerAdapter()

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun setAdapter(adapter: Adapter<*>?) {
        mRealAdapter = adapter
        mWrapAdapter.setRealAdapter(adapter)
        itemAnimator = null
        super.setAdapter(mWrapAdapter)
    }

    override fun getAdapter(): Adapter<*>? {
        return mRealAdapter
    }

    fun addHeaderView(view: View) {
        mWrapAdapter.addHeaderView(view)
    }

    fun <V : View> addHeaderView(layoutId: Int): V {
        val inflated = LayoutInflater.from(context).inflate(layoutId, this, false)
        addHeaderView(inflated)
        return inflated as V
    }

    fun removeHeaderView(view: View) {
        mWrapAdapter.removeHeaderView(view)
    }

    fun addFooterView(view: View) {
        mWrapAdapter.addFooterView(view)
    }

    fun <V : View> addFooterView(layoutId: Int): V {
        val inflated = LayoutInflater.from(context).inflate(layoutId, this, false)
        addFooterView(inflated)
        return inflated as V
    }

    fun removeFooterView(view: View) {
        mWrapAdapter.removeFooterView(view)
    }

    fun getHeaderViewsCount(): Int {
        return mWrapAdapter.getHeaderViewsCount()
    }

    fun getFooterViewsCount(): Int {
        return mWrapAdapter.getFooterViewsCount()
    }

    fun getHeaderViews(): List<View> {
        return mWrapAdapter.getHeaderViews()
    }

    fun getFooterViews(): List<View> {
        return mWrapAdapter.getFooterViews()
    }

    fun refreshHeaderFooterViews() {
        mWrapAdapter.notifyDataSetChanged()
    }

    fun adjustSpanSize() {
        val layoutManager = layoutManager
        if (layoutManager is GridLayoutManager) {
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    if (position >= mWrapAdapter.getHeaderViewsCount()) {
                        val itemCount = mRealAdapter?.itemCount ?: 0
                        if (position < mWrapAdapter.getHeaderViewsCount() + itemCount) {
                            return 1
                        }
                    }
                    return layoutManager.spanCount
                }
            }
        }
    }

    private class WrapRecyclerAdapter : Adapter<ViewHolder>() {
        private var mCurrentPosition = 0
        private val mHeaderViews = ArrayList<View>()
        private val mFooterViews = ArrayList<View>()
        private var mObserver: WrapAdapterDataObserver? = null
        private var mRealAdapter: Adapter<*>? = null
        private var mRecyclerView: RecyclerView? = null

        fun setRealAdapter(adapter: Adapter<*>?) {
            val oldAdapter = mRealAdapter
            if (oldAdapter != adapter) {
                val observer = mObserver
                if (oldAdapter != null && observer != null) {
                    oldAdapter.unregisterAdapterDataObserver(observer)
                }
                mRealAdapter = adapter
                if (adapter != null) {
                    if (mObserver == null) {
                        mObserver = WrapAdapterDataObserver(this)
                    }
                    adapter.registerAdapterDataObserver(mObserver!!)
                    if (mRecyclerView != null) {
                        notifyDataSetChanged()
                    }
                }
            }
        }

        override fun getItemCount(): Int {
            return getHeaderViewsCount() + (mRealAdapter?.itemCount ?: 0) + getFooterViewsCount()
        }

        override fun getItemViewType(position: Int): Int {
            mCurrentPosition = position
            val headerCount = getHeaderViewsCount()
            val itemCount = mRealAdapter?.itemCount ?: 0
            val realPosition = position - headerCount
            if (position < headerCount) {
                return HEADER_VIEW_TYPE
            }
            return if (realPosition < itemCount) {
                mRealAdapter?.getItemViewType(realPosition) ?: FOOTER_VIEW_TYPE
            } else {
                FOOTER_VIEW_TYPE
            }
        }

        fun getPosition(): Int {
            return mCurrentPosition
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            if (viewType == HEADER_VIEW_TYPE) {
                return newWrapViewHolder(mHeaderViews[getPosition()])
            }
            if (viewType != FOOTER_VIEW_TYPE) {
                val adapter = mRealAdapter
                    ?: throw IllegalStateException("Real adapter is null")
                val itemViewType = adapter.getItemViewType(getPosition() - getHeaderViewsCount())
                if (itemViewType == HEADER_VIEW_TYPE || itemViewType == FOOTER_VIEW_TYPE) {
                    throw IllegalStateException("Please do not use this type as itemType")
                }
                return (adapter as Adapter<ViewHolder>).onCreateViewHolder(parent, itemViewType)
            }
            val position = getPosition() - getHeaderViewsCount()
            val realCount = mRealAdapter?.itemCount ?: 0
            return newWrapViewHolder(mFooterViews[position - realCount])
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val adapter = mRealAdapter
            val itemViewType = getItemViewType(position)
            if (adapter != null && itemViewType != HEADER_VIEW_TYPE && itemViewType != FOOTER_VIEW_TYPE) {
                (adapter as Adapter<ViewHolder>).onBindViewHolder(holder, getPosition() - getHeaderViewsCount())
            }
        }

        private fun newWrapViewHolder(view: View): WrapViewHolder {
            val parent = view.parent
            if (parent is ViewGroup) {
                parent.removeView(view)
            }
            return WrapViewHolder(view)
        }

        override fun getItemId(position: Int): Long {
            val adapter = mRealAdapter
            if (adapter == null ||
                position <= getHeaderViewsCount() - 1 ||
                position >= getHeaderViewsCount() + adapter.itemCount
            ) {
                return super.getItemId(position)
            }
            return adapter.getItemId(position - getHeaderViewsCount())
        }

        override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
            mRecyclerView = recyclerView
            mRealAdapter?.onAttachedToRecyclerView(recyclerView)
        }

        override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
            mRecyclerView = null
            mRealAdapter?.onDetachedFromRecyclerView(recyclerView)
        }

        override fun onViewRecycled(holder: ViewHolder) {
            if (holder is WrapViewHolder) {
                holder.setIsRecyclable(false)
                return
            }
            (mRealAdapter as? Adapter<ViewHolder>)?.onViewRecycled(holder)
        }

        override fun onFailedToRecycleView(holder: ViewHolder): Boolean {
            val adapter = mRealAdapter as? Adapter<ViewHolder>
            return adapter?.onFailedToRecycleView(holder) ?: super.onFailedToRecycleView(holder)
        }

        override fun onViewAttachedToWindow(holder: ViewHolder) {
            (mRealAdapter as? Adapter<ViewHolder>)?.onViewAttachedToWindow(holder)
        }

        override fun onViewDetachedFromWindow(holder: ViewHolder) {
            (mRealAdapter as? Adapter<ViewHolder>)?.onViewDetachedFromWindow(holder)
        }

        fun addHeaderView(view: View) {
            if (!mHeaderViews.contains(view) && !mFooterViews.contains(view)) {
                mHeaderViews.add(view)
                notifyDataSetChanged()
            }
        }

        fun removeHeaderView(view: View) {
            if (mHeaderViews.remove(view)) {
                notifyDataSetChanged()
            }
        }

        fun addFooterView(view: View) {
            if (!mFooterViews.contains(view) && !mHeaderViews.contains(view)) {
                mFooterViews.add(view)
                notifyDataSetChanged()
            }
        }

        fun removeFooterView(view: View) {
            if (mFooterViews.remove(view)) {
                notifyDataSetChanged()
            }
        }

        fun getHeaderViewsCount(): Int {
            return mHeaderViews.size
        }

        fun getFooterViewsCount(): Int {
            return mFooterViews.size
        }

        fun getHeaderViews(): List<View> {
            return mHeaderViews
        }

        fun getFooterViews(): List<View> {
            return mFooterViews
        }

        companion object {
            private const val HEADER_VIEW_TYPE = -1073741824
            private const val FOOTER_VIEW_TYPE = 1073741823
        }
    }

    private class WrapViewHolder(view: View) : ViewHolder(view)

    private class WrapAdapterDataObserver(
        private val mWrapAdapter: WrapRecyclerAdapter
    ) : AdapterDataObserver() {

        override fun onChanged() {
            mWrapAdapter.notifyDataSetChanged()
        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
            mWrapAdapter.notifyItemRangeChanged(
                mWrapAdapter.getHeaderViewsCount() + positionStart,
                itemCount,
                payload
            )
        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
            mWrapAdapter.notifyItemRangeChanged(
                mWrapAdapter.getHeaderViewsCount() + positionStart,
                itemCount
            )
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            mWrapAdapter.notifyItemRangeInserted(
                mWrapAdapter.getHeaderViewsCount() + positionStart,
                itemCount
            )
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            mWrapAdapter.notifyItemRangeRemoved(
                mWrapAdapter.getHeaderViewsCount() + positionStart,
                itemCount
            )
        }

        override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
            mWrapAdapter.notifyItemMoved(
                mWrapAdapter.getHeaderViewsCount() + fromPosition,
                mWrapAdapter.getHeaderViewsCount() + toPosition
            )
        }
    }
}
