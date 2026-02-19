package com.google.android.gms.internal;

import android.os.Handler;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import java.util.concurrent.atomic.AtomicReference;

final class zzbdr extends zzbee {
    private final Handler mHandler;
    private final AtomicReference<zzbdp> zzfmy;

    public zzbdr(zzbdp zzbdp) {
        this.zzfmy = new AtomicReference<>(zzbdp);
        this.mHandler = new Handler(zzbdp.getLooper());
    }

    private static void zza(zzbdp zzbdp, long j, int i) {
        zzn zzn;
        synchronized (zzbdp.zzfmq) {
            zzn = (zzn) zzbdp.zzfmq.remove(Long.valueOf(j));
        }
        if (zzn != null) {
            zzn.setResult(new Status(i));
        }
    }

    private static boolean zza(zzbdp zzbdp, int i) {
        synchronized (zzbdp.zzfmu) {
            if (zzbdp.zzfms == null) {
                return false;
            }
            zzbdp.zzfms.setResult(new Status(i));
            zzn unused = zzbdp.zzfms = null;
            return true;
        }
    }

    public final boolean isDisposed() {
        return this.zzfmy.get() == null;
    }

    public final void onApplicationDisconnected(int i) {
        zzbdp zzbdp = this.zzfmy.get();
        if (zzbdp != null) {
            String unused = zzbdp.zzfmn = null;
            String unused2 = zzbdp.zzfmo = null;
            zza(zzbdp, i);
            if (zzbdp.zzetn != null) {
                this.mHandler.post(new zzbds(this, zzbdp, i));
            }
        }
    }

    public final void zza(ApplicationMetadata applicationMetadata, String str, String str2, boolean z) {
        zzbdp zzbdp = this.zzfmy.get();
        if (zzbdp != null) {
            ApplicationMetadata unused = zzbdp.zzfmc = applicationMetadata;
            String unused2 = zzbdp.zzfmn = applicationMetadata.getApplicationId();
            String unused3 = zzbdp.zzfmo = str2;
            String unused4 = zzbdp.zzfmg = str;
            synchronized (zzbdp.zzfmt) {
                if (zzbdp.zzfmr != null) {
                    zzbdp.zzfmr.setResult(new zzbdq(new Status(0), applicationMetadata, str, str2, z));
                    zzn unused5 = zzbdp.zzfmr = null;
                }
            }
        }
    }

    public final void zza(String str, double d, boolean z) {
        zzbdp.zzeus.zzb("Deprecated callback: \"onStatusreceived\"", new Object[0]);
    }

    public final void zza(String str, long j, int i) {
        zzbdp zzbdp = this.zzfmy.get();
        if (zzbdp != null) {
            zza(zzbdp, j, i);
        }
    }

    public final void zza(String str, byte[] bArr) {
        if (this.zzfmy.get() != null) {
            zzbdp.zzeus.zzb("IGNORING: Receive (type=binary, ns=%s) <%d bytes>", str, Integer.valueOf(bArr.length));
        }
    }

    public final zzbdp zzagv() {
        zzbdp andSet = this.zzfmy.getAndSet(null);
        if (andSet == null) {
            return null;
        }
        andSet.zzago();
        return andSet;
    }

    public final void zzb(zzbdd zzbdd) {
        zzbdp zzbdp = this.zzfmy.get();
        if (zzbdp != null) {
            zzbdp.zzeus.zzb("onApplicationStatusChanged", new Object[0]);
            this.mHandler.post(new zzbdu(this, zzbdp, zzbdd));
        }
    }

    public final void zzb(zzbdx zzbdx) {
        zzbdp zzbdp = this.zzfmy.get();
        if (zzbdp != null) {
            zzbdp.zzeus.zzb("onDeviceStatusChanged", new Object[0]);
            this.mHandler.post(new zzbdt(this, zzbdp, zzbdx));
        }
    }

    public final void zzb(String str, long j) {
        zzbdp zzbdp = this.zzfmy.get();
        if (zzbdp != null) {
            zza(zzbdp, j, 0);
        }
    }

    public final void zzbf(int i) {
        zzbdp zzbdp = this.zzfmy.get();
        if (zzbdp != null) {
            synchronized (zzbdp.zzfmt) {
                if (zzbdp.zzfmr != null) {
                    zzbdp.zzfmr.setResult(new zzbdq(new Status(i)));
                    zzn unused = zzbdp.zzfmr = null;
                }
            }
        }
    }

    public final void zzbk(int i) {
        zzbdp zzagv = zzagv();
        if (zzagv != null) {
            zzbdp.zzeus.zzb("ICastDeviceControllerListener.onDisconnected: %d", Integer.valueOf(i));
            if (i != 0) {
                zzagv.zzcd(2);
            }
        }
    }

    public final void zzbl(int i) {
        zzbdp zzbdp = this.zzfmy.get();
        if (zzbdp != null) {
            zza(zzbdp, i);
        }
    }

    public final void zzbm(int i) {
        zzbdp zzbdp = this.zzfmy.get();
        if (zzbdp != null) {
            zza(zzbdp, i);
        }
    }

    public final void zzr(String str, String str2) {
        zzbdp zzbdp = this.zzfmy.get();
        if (zzbdp != null) {
            zzbdp.zzeus.zzb("Receive (type=text, ns=%s) %s", str, str2);
            this.mHandler.post(new zzbdv(this, zzbdp, str, str2));
        }
    }
}
