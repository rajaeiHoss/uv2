package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbb;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@zzabh
public final class zzzu extends zzahs {
    private final Object mLock;
    /* access modifiers changed from: private */
    public final zzzn zzcoa;
    private final zzahe zzcob;
    private final zzacj zzcoc;
    private final zzzy zzcor;
    private Future<zzahd> zzcos;

    public zzzu(Context context, zzbb zzbb, zzahe zzahe, zzcv zzcv, zzzn zzzn, zzov zzov) {
        this(zzahe, zzzn, new zzzy(context, zzbb, new zzajr(context), zzcv, zzahe, zzov));
    }

    private zzzu(zzahe zzahe, zzzn zzzn, zzzy zzzy) {
        this.mLock = new Object();
        this.zzcob = zzahe;
        this.zzcoc = zzahe.zzdcw;
        this.zzcoa = zzzn;
        this.zzcor = zzzy;
    }

    public final void onStop() {
        synchronized (this.mLock) {
            Future<zzahd> future = this.zzcos;
            if (future != null) {
                future.cancel(true);
            }
        }
    }

    public final void zzdo() {
        int i;
        zzalt zza;
        zzahd zzahd = null;
        try {
            synchronized (this.mLock) {
                zza = zzaid.zza((ExecutorService) zzaid.zzdfi, this.zzcor);
                this.zzcos = zza;
            }
            zzahd = (zzahd) zza.get(60000, TimeUnit.MILLISECONDS);
            i = -2;
        } catch (TimeoutException unused) {
            zzahw.zzcz("Timed out waiting for native ad.");
            this.zzcos.cancel(true);
            i = 2;
        } catch (InterruptedException | CancellationException | ExecutionException unused2) {
            i = 0;
        }
        if (zzahd == null) {
            zzahd = new zzahd(this.zzcob.zzcvm.zzcrv, (zzaof) null, (List<String>) null, i, (List<String>) null, (List<String>) null, this.zzcoc.orientation, this.zzcoc.zzcic, this.zzcob.zzcvm.zzcry, false, (zzvp) null, (zzwi) null, (String) null, (zzvq) null, (zzvs) null, this.zzcoc.zzctp, this.zzcob.zzaud, this.zzcoc.zzctn, this.zzcob.zzdcn, this.zzcoc.zzcts, this.zzcoc.zzctt, this.zzcob.zzdch, (zzpx) null, (zzagd) null, (List<String>) null, (List<String>) null, this.zzcob.zzdcw.zzcuf, this.zzcob.zzdcw.zzcug, (String) null, (List<String>) null, this.zzcoc.zzcuj, this.zzcob.zzdcu, this.zzcob.zzdcw.zzaqw, false);
        }
        zzaij.zzdfn.post(new zzzv(this, zzahd));
    }
}
