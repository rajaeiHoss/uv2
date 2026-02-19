package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

public final class zzdck {
    private Context mContext;
    private Tracker zzdug;
    private GoogleAnalytics zzdui;

    public zzdck(Context context) {
        this.mContext = context;
    }

    private final synchronized void zzmg(String str) {
        if (this.zzdui == null) {
            GoogleAnalytics instance = GoogleAnalytics.getInstance(this.mContext);
            this.zzdui = instance;
            instance.setLogger(new zzdcl());
            this.zzdug = this.zzdui.newTracker(str);
        }
    }

    public final Tracker zzmf(String str) {
        zzmg(str);
        return this.zzdug;
    }
}
