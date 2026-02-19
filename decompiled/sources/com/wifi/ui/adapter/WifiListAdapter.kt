package com.wifi.ui.adapter

import android.content.Context
import android.net.wifi.ScanResult
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zycs.UView.R
import java.util.ArrayList

class WifiListAdapter(context: Context) : RecyclerView.Adapter<WifiListAdapter.WifiViewHolder>() {
    private val mContext: Context = context.applicationContext
    private var mOnItemClickListener: onItemClickListener? = null
    private val scanResults: MutableList<ScanResult> = ArrayList()

    interface onItemClickListener {
        fun onItemClick(view: View, i: Int, obj: Any)
    }

    fun setOnItemClickListener(onitemclicklistener: onItemClickListener?) {
        mOnItemClickListener = onitemclicklistener
    }

    fun refreshData(list: List<ScanResult>?) {
        if (list != null) {
            scanResults.clear()
            scanResults.addAll(list)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WifiViewHolder {
        val itemView = LayoutInflater.from(mContext).inflate(R.layout.item_wifi_list, parent, false)
        return WifiViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WifiViewHolder, position: Int) {
        val scanResult = scanResults[position]
        holder.tvItemWifiName.text = scanResult.SSID
        holder.itemview.setOnClickListener { view ->
            mOnItemClickListener?.onItemClick(view, position, scanResult)
        }
    }

    override fun getItemCount(): Int {
        return scanResults.size
    }

    class WifiViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemview: View = view
        val ivItemWifiLevel: ImageView = view.findViewById(R.id.tv_item_wifi_level)
        val tvItemWifiName: TextView = view.findViewById(R.id.tv_item_wifi_name)
        val tvItemWifiStatus: TextView = view.findViewById(R.id.tv_item_wifi_status)
    }
}
