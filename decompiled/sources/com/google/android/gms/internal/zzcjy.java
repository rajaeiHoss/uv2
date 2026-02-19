package com.google.android.gms.internal;

import android.content.SharedPreferences;
import android.util.Pair;
import com.google.android.gms.common.internal.zzbq;
import kotlin.jvm.internal.LongCompanionObject;

public final class zzcjy {
    private /* synthetic */ zzcju zzjmi;
    private String zzjmk;
    private final String zzjml;
    private final String zzjmm;
    private final long zzjmn;

    zzcjy(zzcju zzcju, String str, long j) {
        this.zzjmi = zzcju;
        zzbq.zzgv(str);
        zzbq.checkArgument(j > 0);
        this.zzjmk = String.valueOf(str).concat(":start");
        this.zzjml = String.valueOf(str).concat(":count");
        this.zzjmm = String.valueOf(str).concat(":value");
        this.zzjmn = j;
    }

    private final void zzabg() {
        this.zzjmi.zzwj();
        long currentTimeMillis = this.zzjmi.zzxx().currentTimeMillis();
        SharedPreferences.Editor edit = this.zzjmi.zzbbd().edit();
        edit.remove(this.zzjml);
        edit.remove(this.zzjmm);
        edit.putLong(this.zzjmk, currentTimeMillis);
        edit.apply();
    }

    private final long zzabi() {
        return this.zzjmi.zzbbd().getLong(this.zzjmk, 0);
    }

    public final Pair<String, Long> zzabh() {
        long j;
        this.zzjmi.zzwj();
        this.zzjmi.zzwj();
        long zzabi = zzabi();
        if (zzabi == 0) {
            zzabg();
            j = 0;
        } else {
            j = Math.abs(zzabi - this.zzjmi.zzxx().currentTimeMillis());
        }
        long j2 = this.zzjmn;
        if (j < j2) {
            return null;
        }
        if (j > (j2 << 1)) {
            zzabg();
            return null;
        }
        String string = this.zzjmi.zzbbd().getString(this.zzjmm, (String) null);
        long j3 = this.zzjmi.zzbbd().getLong(this.zzjml, 0);
        zzabg();
        return (string == null || j3 <= 0) ? zzcju.zzjlk : new Pair<>(string, Long.valueOf(j3));
    }

    public final void zzf(String str, long j) {
        this.zzjmi.zzwj();
        if (zzabi() == 0) {
            zzabg();
        }
        if (str == null) {
            str = "";
        }
        long j2 = this.zzjmi.zzbbd().getLong(this.zzjml, 0);
        if (j2 <= 0) {
            SharedPreferences.Editor edit = this.zzjmi.zzbbd().edit();
            edit.putString(this.zzjmm, str);
            edit.putLong(this.zzjml, 1);
            edit.apply();
            return;
        }
        long j3 = j2 + 1;
        boolean z = (this.zzjmi.zzayl().zzbcr().nextLong() & LongCompanionObject.MAX_VALUE) < LongCompanionObject.MAX_VALUE / j3;
        SharedPreferences.Editor edit2 = this.zzjmi.zzbbd().edit();
        if (z) {
            edit2.putString(this.zzjmm, str);
        }
        edit2.putLong(this.zzjml, j3);
        edit2.apply();
    }
}
