package com.google.android.gms.internal;

final class zzclx implements Runnable {
    private /* synthetic */ zzclk zzjpy;

    zzclx(zzclk zzclk) {
        this.zzjpy = zzclk;
    }

    public final void run() {
        zzclk zzclk = this.zzjpy;
        zzclk.zzwj();
        zzclk.zzyk();
        zzclk.zzayp().zzbaz().log("Resetting analytics data (FE)");
        zzclk.zzayg().resetAnalyticsData();
    }
}
