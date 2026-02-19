package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbt;
import java.util.Map;

abstract class zzdz extends zzeg {
    public zzdz(String str) {
        super(str);
    }

    /* access modifiers changed from: protected */
    public final boolean zza(zzbt zzbt, zzbt zzbt2, Map<String, zzbt> map) {
        zzgj zze = zzgk.zze(zzbt);
        zzgj zze2 = zzgk.zze(zzbt2);
        if (zze == zzgk.zzbij() || zze2 == zzgk.zzbij()) {
            return false;
        }
        return zza(zze, zze2, map);
    }

    /* access modifiers changed from: protected */
    public abstract boolean zza(zzgj zzgj, zzgj zzgj2, Map<String, zzbt> map);
}
