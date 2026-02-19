package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.SystemClock;
import com.google.android.gms.ads.internal.zzbt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

@zzabh
public final class zzahf {
    private final Object mLock;
    private final zzahq zzatv;
    private boolean zzcwy;
    private final LinkedList<zzahg> zzdcx;
    private final String zzdcy;
    private final String zzdcz;
    private long zzdda;
    private long zzddb;
    private long zzddc;
    private long zzddd;
    private long zzdde;
    private long zzddf;

    private zzahf(zzahq zzahq, String str, String str2) {
        this.mLock = new Object();
        this.zzdda = -1;
        this.zzddb = -1;
        this.zzcwy = false;
        this.zzddc = -1;
        this.zzddd = 0;
        this.zzdde = -1;
        this.zzddf = -1;
        this.zzatv = zzahq;
        this.zzdcy = str;
        this.zzdcz = str2;
        this.zzdcx = new LinkedList<>();
    }

    public zzahf(String str, String str2) {
        this(zzbt.zzeq(), str, str2);
    }

    public final Bundle toBundle() {
        Bundle bundle;
        synchronized (this.mLock) {
            bundle = new Bundle();
            bundle.putString("seq_num", this.zzdcy);
            bundle.putString("slotid", this.zzdcz);
            bundle.putBoolean("ismediation", this.zzcwy);
            bundle.putLong("treq", this.zzdde);
            bundle.putLong("tresponse", this.zzddf);
            bundle.putLong("timp", this.zzddb);
            bundle.putLong("tload", this.zzddc);
            bundle.putLong("pcc", this.zzddd);
            bundle.putLong("tfetch", this.zzdda);
            ArrayList arrayList = new ArrayList();
            Iterator it = this.zzdcx.iterator();
            while (it.hasNext()) {
                arrayList.add(((zzahg) it.next()).toBundle());
            }
            bundle.putParcelableArrayList("tclick", arrayList);
        }
        return bundle;
    }

    public final void zzh(long j) {
        synchronized (this.mLock) {
            this.zzddf = j;
            if (j != -1) {
                this.zzatv.zza(this);
            }
        }
    }

    public final void zzi(long j) {
        synchronized (this.mLock) {
            if (this.zzddf != -1) {
                this.zzdda = j;
                this.zzatv.zza(this);
            }
        }
    }

    public final void zzn(zzkk zzkk) {
        synchronized (this.mLock) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.zzdde = elapsedRealtime;
            this.zzatv.zzb(zzkk, elapsedRealtime);
        }
    }

    public final void zzpk() {
        synchronized (this.mLock) {
            if (this.zzddf != -1 && this.zzddb == -1) {
                this.zzddb = SystemClock.elapsedRealtime();
                this.zzatv.zza(this);
            }
            this.zzatv.zzpk();
        }
    }

    public final void zzpl() {
        synchronized (this.mLock) {
            if (this.zzddf != -1) {
                zzahg zzahg = new zzahg();
                zzahg.zzpp();
                this.zzdcx.add(zzahg);
                this.zzddd++;
                this.zzatv.zzpl();
                this.zzatv.zza(this);
            }
        }
    }

    public final void zzpm() {
        synchronized (this.mLock) {
            if (this.zzddf != -1 && !this.zzdcx.isEmpty()) {
                zzahg last = this.zzdcx.getLast();
                if (last.zzpn() == -1) {
                    last.zzpo();
                    this.zzatv.zza(this);
                }
            }
        }
    }

    public final void zzx(boolean z) {
        synchronized (this.mLock) {
            if (this.zzddf != -1) {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                this.zzddc = elapsedRealtime;
                if (!z) {
                    this.zzddb = elapsedRealtime;
                    this.zzatv.zza(this);
                }
            }
        }
    }

    public final void zzy(boolean z) {
        synchronized (this.mLock) {
            if (this.zzddf != -1) {
                this.zzcwy = z;
                this.zzatv.zza(this);
            }
        }
    }
}
