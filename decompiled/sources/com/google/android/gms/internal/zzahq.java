package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.internal.zzbt;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

@zzabh
public final class zzahq implements zzhl {
    private final Object lock;
    private zzahm zzdem;
    private HashSet<zzahf> zzden;
    private HashSet<zzahp> zzdeo;

    public zzahq() {
        this(zzlc.zzil());
    }

    private zzahq(String str) {
        this.lock = new Object();
        this.zzden = new HashSet<>();
        this.zzdeo = new HashSet<>();
        this.zzdem = new zzahm(str);
    }

    public final Bundle zza(Context context, zzahn zzahn, String str) {
        Bundle bundle;
        synchronized (this.lock) {
            bundle = new Bundle();
            bundle.putBundle("app", this.zzdem.zzk(context, str));
            Bundle bundle2 = new Bundle();
            Iterator<zzahp> it = this.zzdeo.iterator();
            while (it.hasNext()) {
                zzahp next = it.next();
                bundle2.putBundle(next.zzqh(), next.toBundle());
            }
            bundle.putBundle("slots", bundle2);
            ArrayList arrayList = new ArrayList();
            Iterator<zzahf> it2 = this.zzden.iterator();
            while (it2.hasNext()) {
                arrayList.add(it2.next().toBundle());
            }
            bundle.putParcelableArrayList("ads", arrayList);
            zzahn.zza(this.zzden);
            this.zzden.clear();
        }
        return bundle;
    }

    public final void zza(zzahf zzahf) {
        synchronized (this.lock) {
            this.zzden.add(zzahf);
        }
    }

    public final void zza(zzahp zzahp) {
        synchronized (this.lock) {
            this.zzdeo.add(zzahp);
        }
    }

    public final void zzb(zzkk zzkk, long j) {
        synchronized (this.lock) {
            this.zzdem.zzb(zzkk, j);
        }
    }

    public final void zzb(HashSet<zzahf> hashSet) {
        synchronized (this.lock) {
            this.zzden.addAll(hashSet);
        }
    }

    public final void zzh(boolean z) {
        long currentTimeMillis = zzbt.zzes().currentTimeMillis();
        if (z) {
            if (currentTimeMillis - zzbt.zzep().zzqe().zzqw() > ((Long) zzlc.zzio().zzd(zzoi.zzbpc)).longValue()) {
                this.zzdem.zzdef = -1;
                return;
            }
            this.zzdem.zzdef = zzbt.zzep().zzqe().zzqx();
            return;
        }
        zzbt.zzep().zzqe().zzj(currentTimeMillis);
        zzbt.zzep().zzqe().zzad(this.zzdem.zzdef);
    }

    public final void zzpk() {
        synchronized (this.lock) {
            this.zzdem.zzpk();
        }
    }

    public final void zzpl() {
        synchronized (this.lock) {
            this.zzdem.zzpl();
        }
    }
}
