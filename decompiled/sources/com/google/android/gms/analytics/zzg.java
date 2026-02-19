package com.google.android.gms.analytics;

import android.os.Build;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zze;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class zzg {
    private final zze zzata;
    private final zzj zzdux;
    private boolean zzduy;
    private long zzduz;
    private long zzdva;
    private long zzdvb;
    private long zzdvc;
    private long zzdvd;
    private boolean zzdve;
    private final Map<Class<? extends zzi>, zzi> zzdvf;
    private final List<zzo> zzdvg;

    private zzg(zzg zzg) {
        this.zzdux = zzg.zzdux;
        this.zzata = zzg.zzata;
        this.zzduz = zzg.zzduz;
        this.zzdva = zzg.zzdva;
        this.zzdvb = zzg.zzdvb;
        this.zzdvc = zzg.zzdvc;
        this.zzdvd = zzg.zzdvd;
        this.zzdvg = new ArrayList(zzg.zzdvg);
        this.zzdvf = new HashMap(zzg.zzdvf.size());
        for (Map.Entry next : zzg.zzdvf.entrySet()) {
            zzi zzc = zzc((Class) next.getKey());
            ((zzi) next.getValue()).zzb(zzc);
            this.zzdvf.put((Class) next.getKey(), zzc);
        }
    }

    zzg(zzj zzj, zze zze) {
        zzbq.checkNotNull(zzj);
        zzbq.checkNotNull(zze);
        this.zzdux = zzj;
        this.zzata = zze;
        this.zzdvc = 1800000;
        this.zzdvd = 3024000000L;
        this.zzdvf = new HashMap();
        this.zzdvg = new ArrayList();
    }

    private static <T extends zzi> T zzc(Class<T> cls) {
        try {
            return cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            if (e instanceof InstantiationException) {
                throw new IllegalArgumentException("dataType doesn't have default constructor", e);
            } else if (e instanceof IllegalAccessException) {
                throw new IllegalArgumentException("dataType default constructor is not accessible", e);
            } else if (Build.VERSION.SDK_INT < 19 || !(e instanceof ReflectiveOperationException)) {
                throw new RuntimeException(e);
            } else {
                throw new IllegalArgumentException("Linkage exception", e);
            }
        }
    }

    public final List<zzo> getTransports() {
        return this.zzdvg;
    }

    public final <T extends zzi> T zza(Class<T> cls) {
        return cls.cast(this.zzdvf.get(cls));
    }

    public final void zza(zzi zzi) {
        zzbq.checkNotNull(zzi);
        Class cls = zzi.getClass();
        if (cls.getSuperclass() == zzi.class) {
            zzi.zzb(zzb(cls));
            return;
        }
        throw new IllegalArgumentException();
    }

    public final <T extends zzi> T zzb(Class<T> cls) {
        T t = cls.cast(this.zzdvf.get(cls));
        if (t != null) {
            return t;
        }
        T zzc = zzc(cls);
        this.zzdvf.put(cls, zzc);
        return zzc;
    }

    public final void zzl(long j) {
        this.zzdva = j;
    }

    public final zzg zzvx() {
        return new zzg(this);
    }

    public final Collection<zzi> zzvy() {
        return this.zzdvf.values();
    }

    public final long zzvz() {
        return this.zzduz;
    }

    public final void zzwa() {
        this.zzdux.zzwg().zze(this);
    }

    public final boolean zzwb() {
        return this.zzduy;
    }

    /* access modifiers changed from: package-private */
    public final void zzwc() {
        this.zzdvb = this.zzata.elapsedRealtime();
        long j = this.zzdva;
        if (j == 0) {
            j = this.zzata.currentTimeMillis();
        }
        this.zzduz = j;
        this.zzduy = true;
    }

    /* access modifiers changed from: package-private */
    public final zzj zzwd() {
        return this.zzdux;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzwe() {
        return this.zzdve;
    }

    /* access modifiers changed from: package-private */
    public final void zzwf() {
        this.zzdve = true;
    }
}
