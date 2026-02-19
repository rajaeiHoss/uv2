package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

public final class zzgg {
    private Context mContext;
    private Tracker zzdug;
    private GoogleAnalytics zzdui;

    public zzgg(Context context) {
        this.mContext = context;
    }

    private final synchronized void zzmg(String str) {
        if (this.zzdui == null) {
            GoogleAnalytics instance = GoogleAnalytics.getInstance(this.mContext);
            this.zzdui = instance;
            instance.setLogger(new zzgh());
            this.zzdug = this.zzdui.newTracker(str);
        }
    }

    public final Tracker zzmf(String str) {
        zzmg(str);
        return this.zzdug;
    }
}
