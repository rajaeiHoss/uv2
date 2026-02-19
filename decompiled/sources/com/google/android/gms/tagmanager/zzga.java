package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbt;
import java.util.Map;

abstract class zzga extends zzeg {
    public zzga(String str) {
        super(str);
    }

    /* access modifiers changed from: protected */
    public final boolean zza(zzbt zzbt, zzbt zzbt2, Map<String, zzbt> map) {
        String zzd = zzgk.zzd(zzbt);
        String zzd2 = zzgk.zzd(zzbt2);
        if (zzd == zzgk.zzbik() || zzd2 == zzgk.zzbik()) {
            return false;
        }
        return zza(zzd, zzd2, map);
    }

    /* access modifiers changed from: protected */
    public abstract boolean zza(String str, String str2, Map<String, zzbt> map);
}
