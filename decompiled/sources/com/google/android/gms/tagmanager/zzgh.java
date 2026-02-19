package com.google.android.gms.tagmanager;

import com.google.android.gms.analytics.Logger;

final class zzgh implements Logger {
    zzgh() {
    }

    public final void error(Exception exc) {
        zzdj.zzb("", exc);
    }

    public final void error(String str) {
        zzdj.e(str);
    }

    public final int getLogLevel() {
        int i = zzdj.zzkqw;
        if (i == 2) {
            return 0;
        }
        if (i == 3 || i == 4) {
            return 1;
        }
        return i != 5 ? 3 : 2;
    }

    public final void info(String str) {
        zzdj.zzcy(str);
    }

    public final void setLogLevel(int i) {
        zzdj.zzcz("GA uses GTM logger. Please use TagManager.setLogLevel(int) instead.");
    }

    public final void verbose(String str) {
        zzdj.v(str);
    }

    public final void warn(String str) {
        zzdj.zzcz(str);
    }
}
