package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.Iterator;
import java.util.LinkedList;

@zzabh
final class zzuq {
    /* access modifiers changed from: private */
    public final String zzapp;
    private final LinkedList<zzur> zzcec = new LinkedList<>();
    /* access modifiers changed from: private */
    public zzkk zzced;
    private final int zzcee;
    private boolean zzcef;

    zzuq(zzkk zzkk, String str, int i) {
        zzbq.checkNotNull(zzkk);
        zzbq.checkNotNull(str);
        this.zzced = zzkk;
        this.zzapp = str;
        this.zzcee = i;
    }

    /* access modifiers changed from: package-private */
    public final String getAdUnitId() {
        return this.zzapp;
    }

    /* access modifiers changed from: package-private */
    public final int getNetworkType() {
        return this.zzcee;
    }

    /* access modifiers changed from: package-private */
    public final int size() {
        return this.zzcec.size();
    }

    /* access modifiers changed from: package-private */
    public final void zza(zztk zztk, zzkk zzkk) {
        this.zzcec.add(new zzur(this, zztk, zzkk));
    }

    /* access modifiers changed from: package-private */
    public final boolean zzb(zztk zztk) {
        zzur zzur = new zzur(this, zztk);
        this.zzcec.add(zzur);
        return zzur.load();
    }

    /* access modifiers changed from: package-private */
    public final zzur zzl(zzkk zzkk) {
        if (zzkk != null) {
            this.zzced = zzkk;
        }
        return this.zzcec.remove();
    }

    /* access modifiers changed from: package-private */
    public final zzkk zzli() {
        return this.zzced;
    }

    /* access modifiers changed from: package-private */
    public final int zzlj() {
        Iterator it = this.zzcec.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (((zzur) it.next()).zzcek) {
                i++;
            }
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public final int zzlk() {
        Iterator it = this.zzcec.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (((zzur) it.next()).load()) {
                i++;
            }
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public final void zzll() {
        this.zzcef = true;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzlm() {
        return this.zzcef;
    }
}
