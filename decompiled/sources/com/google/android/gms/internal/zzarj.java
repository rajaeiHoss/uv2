package com.google.android.gms.internal;

import com.google.android.gms.common.zzf;

public final class zzarj {
    public static final String VERSION;
    public static final String zzdyr;

    static {
        String replaceAll = String.valueOf(zzf.GOOGLE_PLAY_SERVICES_VERSION_CODE / 1000).replaceAll("(\\d+)(\\d)(\\d\\d)", "$1.$2.$3");
        VERSION = replaceAll;
        String valueOf = String.valueOf(replaceAll);
        zzdyr = valueOf.length() != 0 ? "ma".concat(valueOf) : new String("ma");
    }
}
