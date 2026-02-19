package com.google.android.gms.internal;

import android.content.SharedPreferences;
import android.util.Pair;
import com.google.android.gms.common.internal.zzbq;
import java.util.UUID;
import kotlin.jvm.internal.LongCompanionObject;

public final class zzatj {
    private final String mName;
    private final long zzeeg;
    private /* synthetic */ zzath zzeeh;

    zzatj(zzath zzath, String str, long j) {
        this.zzeeh = zzath;
        zzbq.zzgv(str);
        zzbq.checkArgument(j > 0);
        this.mName = str;
        this.zzeeg = j;
    }

    private final void zzabg() {
        long currentTimeMillis = this.zzeeh.zzxx().currentTimeMillis();
        SharedPreferences.Editor edit = this.zzeeh.zzeec.edit();
        edit.remove(zzabk());
        edit.remove(zzabl());
        edit.putLong(zzabj(), currentTimeMillis);
        edit.commit();
    }

    private final long zzabi() {
        return this.zzeeh.zzeec.getLong(zzabj(), 0);
    }

    private final String zzabj() {
        return String.valueOf(this.mName).concat(":start");
    }

    private final String zzabk() {
        return String.valueOf(this.mName).concat(":count");
    }

    private final String zzabl() {
        return String.valueOf(this.mName).concat(":value");
    }

    public final Pair<String, Long> zzabh() {
        long zzabi = zzabi();
        long abs = zzabi == 0 ? 0 : Math.abs(zzabi - this.zzeeh.zzxx().currentTimeMillis());
        long j = this.zzeeg;
        if (abs < j) {
            return null;
        }
        if (abs > (j << 1)) {
            zzabg();
            return null;
        }
        String string = this.zzeeh.zzeec.getString(zzabl(), (String) null);
        long j2 = this.zzeeh.zzeec.getLong(zzabk(), 0);
        zzabg();
        if (string == null || j2 <= 0) {
            return null;
        }
        return new Pair<>(string, Long.valueOf(j2));
    }

    public final void zzem(String str) {
        if (zzabi() == 0) {
            zzabg();
        }
        if (str == null) {
            str = "";
        }
        synchronized (this) {
            long j = this.zzeeh.zzeec.getLong(zzabk(), 0);
            if (j <= 0) {
                SharedPreferences.Editor edit = this.zzeeh.zzeec.edit();
                edit.putString(zzabl(), str);
                edit.putLong(zzabk(), 1);
                edit.apply();
                return;
            }
            long j2 = j + 1;
            boolean z = (UUID.randomUUID().getLeastSignificantBits() & LongCompanionObject.MAX_VALUE) < LongCompanionObject.MAX_VALUE / j2;
            SharedPreferences.Editor edit2 = this.zzeeh.zzeec.edit();
            if (z) {
                edit2.putString(zzabl(), str);
            }
            edit2.putLong(zzabk(), j2);
            edit2.apply();
        }
    }
}
