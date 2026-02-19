package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@zzabh
public final class zzwc implements zzvo {
    private final Context mContext;
    private final Object mLock = new Object();
    private final long mStartTime;
    private final zzov zzanh;
    private final zzwf zzanu;
    private final boolean zzavr;
    private final zzvq zzcir;
    private final boolean zzciv;
    private final boolean zzciw;
    private final zzacf zzcjk;
    private final long zzcjl;
    private boolean zzcjn = false;
    private final String zzcjp;
    private List<zzvw> zzcjq = new ArrayList();
    private zzvt zzcju;

    public zzwc(Context context, zzacf zzacf, zzwf zzwf, zzvq zzvq, boolean z, boolean z2, String str, long j, long j2, zzov zzov, boolean z3) {
        this.mContext = context;
        this.zzcjk = zzacf;
        this.zzanu = zzwf;
        this.zzcir = zzvq;
        this.zzavr = z;
        this.zzciv = z2;
        this.zzcjp = str;
        this.mStartTime = j;
        this.zzcjl = j2;
        this.zzanh = zzov;
        this.zzciw = z3;
    }

    public final void cancel() {
        synchronized (this.mLock) {
            this.zzcjn = true;
            zzvt zzvt = this.zzcju;
            if (zzvt != null) {
                zzvt.cancel();
            }
        }
    }

    public final zzvw zzh(List<zzvp> list) {
        Object obj;
        zzvw zzvw = null;
        zzahw.zzby("Starting mediation.");
        ArrayList arrayList = new ArrayList();
        zzot zzjo = this.zzanh.zzjo();
        zzko zzko = this.zzcjk.zzaud;
        int[] iArr = new int[2];
        if (zzko.zzbic != null) {
            zzbt.zzfd();
            if (zzvy.zza(this.zzcjp, iArr)) {
                int i = 0;
                int i2 = iArr[0];
                int i3 = iArr[1];
                zzko[] zzkoArr = zzko.zzbic;
                int length = zzkoArr.length;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    zzko zzko2 = zzkoArr[i];
                    if (i2 == zzko2.width && i3 == zzko2.height) {
                        zzko = zzko2;
                        break;
                    }
                    i++;
                }
            }
        }
        Iterator<zzvp> it = list.iterator();
        while (it.hasNext()) {
            zzvp next = it.next();
            String valueOf = String.valueOf(next.zzchc);
            zzahw.zzcy(valueOf.length() != 0 ? "Trying mediation network: ".concat(valueOf) : new String("Trying mediation network: "));
            Iterator<String> it2 = next.zzchd.iterator();
            while (it2.hasNext()) {
                String next2 = it2.next();
                zzot zzjo2 = this.zzanh.zzjo();
                Object obj2 = this.mLock;
                synchronized (obj2) {
                    try {
                        if (this.zzcjn) {
                            zzvw = new zzvw(-1);
                        } else {
                            Context context = this.mContext;
                            zzwf zzwf = this.zzanu;
                            zzvq zzvq = this.zzcir;
                            zzkk zzkk = this.zzcjk.zzcrv;
                            zzala zzala = this.zzcjk.zzatz;
                            Iterator<zzvp> it3 = it;
                            boolean z = this.zzavr;
                            Iterator<String> it4 = it2;
                            boolean z2 = this.zzciv;
                            zzkk zzkk2 = zzkk;
                            zzqh zzqh = this.zzcjk.zzauq;
                            zzot zzot = zzjo;
                            zzot zzot2 = zzjo2;
                            obj = obj2;
                            zzvt zzvt = new zzvt(context, next2, zzwf, zzvq, next, zzkk2, zzko, zzala, z, z2, zzqh, this.zzcjk.zzauy, this.zzcjk.zzcsk, this.zzcjk.zzctf, this.zzciw);
                            this.zzcju = zzvt;
                            zzvw zza = zzvt.zza(this.mStartTime, this.zzcjl);
                            this.zzcjq.add(zza);
                            if (zza.zzcjd == 0) {
                                zzahw.zzby("Adapter succeeded.");
                                this.zzanh.zzf("mediation_network_succeed", next2);
                                if (!arrayList.isEmpty()) {
                                    this.zzanh.zzf("mediation_networks_fail", TextUtils.join(",", arrayList));
                                }
                                this.zzanh.zza(zzot2, "mls");
                                this.zzanh.zza(zzot, "ttm");
                                return zza;
                            }
                            zzot zzot3 = zzot;
                            arrayList.add(next2);
                            this.zzanh.zza(zzot2, "mlf");
                            if (zza.zzcjf != null) {
                                zzaij.zzdfn.post(new zzwd(this, zza));
                            }
                            it = it3;
                            zzjo = zzot3;
                            it2 = it4;
                        }
                    } catch (Throwable th2) {
                        obj = obj2;
                        throw th2;
                    }
                }
                if (zzvw != null) {
                    return zzvw;
                }
            }
        }
        if (!arrayList.isEmpty()) {
            this.zzanh.zzf("mediation_networks_fail", TextUtils.join(",", arrayList));
        }
        return new zzvw(1);
    }

    public final List<zzvw> zzmf() {
        return this.zzcjq;
    }
}
