package com.wifi.ui.adapter;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.zycs.UView.R;
import java.util.ArrayList;
import java.util.List;

public class WifiListAdapter extends RecyclerView.Adapter<WifiListAdapter.WifiViewHolder> {
    private Context mContext;
    /* access modifiers changed from: private */
    public onItemClickListener onItemClickListener;
    private List<ScanResult> scanResults = new ArrayList();

    public interface onItemClickListener {
        void onItemClick(View view, int i, Object obj);
    }

    public WifiListAdapter(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public void setOnItemClickListener(onItemClickListener onitemclicklistener) {
        this.onItemClickListener = onitemclicklistener;
    }

    public void refreshData(List<ScanResult> list) {
        if (list != null) {
            this.scanResults.clear();
            this.scanResults.addAll(list);
        }
        notifyDataSetChanged();
    }

    public WifiViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new WifiViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.item_wifi_list, viewGroup, false));
    }

    public void onBindViewHolder(WifiViewHolder wifiViewHolder, final int i) {
        final ScanResult scanResult = this.scanResults.get(i);
        wifiViewHolder.tvItemWifiName.setText(scanResult.SSID);
        wifiViewHolder.itemview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WifiListAdapter.this.onItemClickListener.onItemClick(view, i, scanResult);
            }
        });
    }

    public int getItemCount() {
        return this.scanResults.size();
    }

    static class WifiViewHolder extends RecyclerView.ViewHolder {
        View itemview;
        ImageView ivItemWifiLevel;
        TextView tvItemWifiName;
        TextView tvItemWifiStatus;

        public WifiViewHolder(View view) {
            super(view);
            this.itemview = view;
            this.tvItemWifiName = (TextView) view.findViewById(R.id.tv_item_wifi_name);
            this.tvItemWifiStatus = (TextView) view.findViewById(R.id.tv_item_wifi_status);
            this.ivItemWifiLevel = (ImageView) view.findViewById(R.id.tv_item_wifi_level);
        }
    }
}
