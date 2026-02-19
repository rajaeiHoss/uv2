package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbt;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@zzabh
public final class zzov {
    private final Object mLock;
    private boolean zzbwc;
    private final List<zzot> zzbwt = new LinkedList();
    private final Map<String, String> zzbwu;
    private String zzbwv;
    private zzov zzbww;

    public zzov(boolean z, String str, String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.zzbwu = linkedHashMap;
        this.mLock = new Object();
        this.zzbwc = z;
        linkedHashMap.put("action", str);
        linkedHashMap.put("ad_format", str2);
    }

    public final boolean zza(zzot zzot, long j, String... strArr) {
        synchronized (this.mLock) {
            for (String zzot2 : strArr) {
                this.zzbwt.add(new zzot(j, zzot2, zzot));
            }
        }
        return true;
    }

    public final boolean zza(zzot zzot, String... strArr) {
        if (!this.zzbwc || zzot == null) {
            return false;
        }
        return zza(zzot, zzbt.zzes().elapsedRealtime(), strArr);
    }

    public final void zzao(String str) {
        if (this.zzbwc) {
            synchronized (this.mLock) {
                this.zzbwv = str;
            }
        }
    }

    public final void zzc(zzov zzov) {
        synchronized (this.mLock) {
            this.zzbww = zzov;
        }
    }

    public final zzot zzd(long j) {
        if (!this.zzbwc) {
            return null;
        }
        return new zzot(j, (String) null, (zzot) null);
    }

    public final void zzf(String str, String str2) {
        zzol zzpv;
        if (this.zzbwc && !TextUtils.isEmpty(str2) && (zzpv = zzbt.zzep().zzpv()) != null) {
            synchronized (this.mLock) {
                zzop zzam = zzpv.zzam(str);
                Map<String, String> map = this.zzbwu;
                map.put(str, zzam.zze(map.get(str), str2));
            }
        }
    }

    public final zzot zzjo() {
        return zzd(zzbt.zzes().elapsedRealtime());
    }

    public final String zzjp() {
        String sb;
        StringBuilder sb2 = new StringBuilder();
        synchronized (this.mLock) {
            for (zzot next : this.zzbwt) {
                long time = next.getTime();
                String zzjl = next.zzjl();
                zzot zzjm = next.zzjm();
                if (zzjm != null && time > 0) {
                    sb2.append(zzjl);
                    sb2.append('.');
                    sb2.append(time - zzjm.getTime());
                    sb2.append(',');
                }
            }
            this.zzbwt.clear();
            if (!TextUtils.isEmpty(this.zzbwv)) {
                sb2.append(this.zzbwv);
            } else if (sb2.length() > 0) {
                sb2.setLength(sb2.length() - 1);
            }
            sb = sb2.toString();
        }
        return sb;
    }

    /* access modifiers changed from: package-private */
    public final Map<String, String> zzjq() {
        synchronized (this.mLock) {
            zzol zzpv = zzbt.zzep().zzpv();
            if (zzpv != null) {
                zzov zzov = this.zzbww;
                if (zzov != null) {
                    Map<String, String> zza = zzpv.zza(this.zzbwu, zzov.zzjq());
                    return zza;
                }
            }
            Map<String, String> map = this.zzbwu;
            return map;
        }
    }

    public final zzot zzjr() {
        synchronized (this.mLock) {
        }
        return null;
    }
}
