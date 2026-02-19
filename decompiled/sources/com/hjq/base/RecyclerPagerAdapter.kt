package com.hjq.base

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter

class RecyclerPagerAdapter(adapter: RecyclerView.Adapter<*>?) : PagerAdapter() {
    private val mAdapter: RecyclerView.Adapter<*>

    init {
        if (adapter != null) {
            mAdapter = adapter
            adapter.registerAdapterDataObserver(
                object : RecyclerView.AdapterDataObserver() {
                    override fun onChanged() {
                        notifyDataSetChanged()
                    }

                    override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                        notifyDataSetChanged()
                    }

                    override fun onItemRangeChanged(
                        positionStart: Int,
                        itemCount: Int,
                        payload: Any?
                    ) {
                        notifyDataSetChanged()
                    }

                    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                        notifyDataSetChanged()
                    }

                    override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                        notifyDataSetChanged()
                    }

                    override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
                        notifyDataSetChanged()
                    }
                }
            )
        } else {
            throw IllegalArgumentException("are you ok?")
        }
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }

    override fun getCount(): Int {
        return mAdapter.itemCount
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val adapter = mAdapter as RecyclerView.Adapter<RecyclerView.ViewHolder>
        val viewHolder = adapter.createViewHolder(container, 0)
        container.addView(viewHolder.itemView)
        adapter.onBindViewHolder(viewHolder, position)
        return viewHolder.itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }
}
