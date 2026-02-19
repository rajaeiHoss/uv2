package com.google.android.gms.internal;

import android.content.Context;
import android.view.View;
import com.google.android.gms.ads.internal.js.zzn;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

@zzabh
public final class zzfu implements zzgc {
    private final Context mApplicationContext;
    private final Object mLock = new Object();
    private final zzala zzapq;
    private final WeakHashMap<zzahd, zzfv> zzavs = new WeakHashMap<>();
    private final ArrayList<zzfv> zzavt = new ArrayList<>();
    private final zzn zzavu;

    public zzfu(Context context, zzala zzala) {
        this.mApplicationContext = context.getApplicationContext();
        this.zzapq = zzala;
        this.zzavu = new zzn(context.getApplicationContext(), zzala, (String) zzlc.zzio().zzd(zzoi.zzblc));
    }

    private final boolean zzg(zzahd zzahd) {
        boolean z;
        synchronized (this.mLock) {
            zzfv zzfv = this.zzavs.get(zzahd);
            z = zzfv != null && zzfv.zzge();
        }
        return z;
    }

    public final void zza(zzfv zzfv) {
        synchronized (this.mLock) {
            if (!zzfv.zzge()) {
                this.zzavt.remove(zzfv);
                Iterator<Map.Entry<zzahd, zzfv>> it = this.zzavs.entrySet().iterator();
                while (it.hasNext()) {
                    if (it.next().getValue() == zzfv) {
                        it.remove();
                    }
                }
            }
        }
    }

    public final void zza(zzko zzko, zzahd zzahd) {
        zza(zzko, zzahd, zzahd.zzcnm.getView());
    }

    public final void zza(zzko zzko, zzahd zzahd, View view) {
        zza(zzko, zzahd, (zzhf) new zzgb(view, zzahd), (zzaof) null);
    }

    public final void zza(zzko zzko, zzahd zzahd, View view, zzaof zzaof) {
        zza(zzko, zzahd, (zzhf) new zzgb(view, zzahd), zzaof);
    }

    public final void zza(zzko zzko, zzahd zzahd, zzhf zzhf, zzaof zzaof) {
        zzfv zzfv;
        synchronized (this.mLock) {
            if (zzg(zzahd)) {
                zzfv = this.zzavs.get(zzahd);
            } else {
                zzfv zzfv2 = new zzfv(this.mApplicationContext, zzko, zzahd, this.zzapq, zzhf);
                zzfv2.zza((zzgc) this);
                this.zzavs.put(zzahd, zzfv2);
                this.zzavt.add(zzfv2);
                zzfv = zzfv2;
            }
            zzfv.zza(zzaof != null ? new zzgd(zzfv, zzaof) : new zzgh(zzfv, this.zzavu, this.mApplicationContext));
        }
    }

    public final void zzh(zzahd zzahd) {
        synchronized (this.mLock) {
            zzfv zzfv = this.zzavs.get(zzahd);
            if (zzfv != null) {
                zzfv.zzgc();
            }
        }
    }

    public final void zzi(zzahd zzahd) {
        synchronized (this.mLock) {
            zzfv zzfv = this.zzavs.get(zzahd);
            if (zzfv != null) {
                zzfv.stop();
            }
        }
    }

    public final void zzj(zzahd zzahd) {
        synchronized (this.mLock) {
            zzfv zzfv = this.zzavs.get(zzahd);
            if (zzfv != null) {
                zzfv.pause();
            }
        }
    }

    public final void zzk(zzahd zzahd) {
        synchronized (this.mLock) {
            zzfv zzfv = this.zzavs.get(zzahd);
            if (zzfv != null) {
                zzfv.resume();
            }
        }
    }
}
