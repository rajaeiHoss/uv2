package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.gmsg.zzt;
import java.util.Map;

final class zzadu implements zzt<Object> {
    private /* synthetic */ zzadt zzcwp;

    zzadu(zzadt zzadt) {
        this.zzcwp = zzadt;
    }

    public final void zza(Object obj, Map<String, String> map) {
        synchronized (this.zzcwp.mLock) {
            if (!this.zzcwp.zzcwl.isDone()) {
                if (this.zzcwp.zzcwj.equals(map.get("request_id"))) {
                    zzadz zzadz = new zzadz(1, map);
                    String type = zzadz.getType();
                    String valueOf = String.valueOf(zzadz.zzog());
                    StringBuilder sb = new StringBuilder(String.valueOf(type).length() + 24 + String.valueOf(valueOf).length());
                    sb.append("Invalid ");
                    sb.append(type);
                    sb.append(" request error: ");
                    sb.append(valueOf);
                    zzahw.zzcz(sb.toString());
                    this.zzcwp.zzcwl.set(zzadz);
                }
            }
        }
    }
}
