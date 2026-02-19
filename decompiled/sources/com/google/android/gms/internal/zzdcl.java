package com.google.android.gms.internal;

import com.google.android.gms.analytics.Logger;

final class zzdcl implements Logger {
    zzdcl() {
    }

    public final void error(Exception exc) {
        zzdal.zzb("", exc);
    }

    public final void error(String str) {
        zzdal.e(str);
    }

    public final int getLogLevel() {
        zzdal.getLogLevel();
        return 3;
    }

    public final void info(String str) {
        zzdal.zzcy(str);
    }

    public final void setLogLevel(int i) {
        zzdal.zzcz("GA uses GTM logger. Please use TagManager.setLogLevel(int) instead.");
    }

    public final void verbose(String str) {
        zzdal.v(str);
    }

    public final void warn(String str) {
        zzdal.zzcz(str);
    }
}
