package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.common.internal.zzbq;
import java.util.concurrent.atomic.AtomicBoolean;

@zzabh
public abstract class zzzg implements zzajb<Void>, zzapv {
    protected final Context mContext;
    protected final zzaof zzcct;
    private zzzn zzcoa;
    private zzahe zzcob;
    protected zzacj zzcoc;
    private Runnable zzcod;
    private Object zzcoe = new Object();
    /* access modifiers changed from: private */
    public AtomicBoolean zzcof = new AtomicBoolean(true);

    protected zzzg(Context context, zzahe zzahe, zzaof zzaof, zzzn zzzn) {
        this.mContext = context;
        this.zzcob = zzahe;
        this.zzcoc = zzahe.zzdcw;
        this.zzcct = zzaof;
        this.zzcoa = zzzn;
    }

    public void cancel() {
        if (this.zzcof.getAndSet(false)) {
            this.zzcct.stopLoading();
            zzbt.zzen();
            zzaip.zzh(this.zzcct);
            zzx(-1);
            zzaij.zzdfn.removeCallbacks(this.zzcod);
        }
    }

    public final void zza(zzaof zzaof, boolean z) {
        zzahw.zzby("WebView finished loading.");
        int i = 0;
        if (this.zzcof.getAndSet(false)) {
            if (z) {
                i = -2;
            }
            zzx(i);
            zzaij.zzdfn.removeCallbacks(this.zzcod);
        }
    }

    /* access modifiers changed from: protected */
    public abstract void zznr();

    public final Void zzns() {
        zzbq.zzgn("Webview render task needs to be called on UI thread.");
        this.zzcod = new zzzh(this);
        zzaij.zzdfn.postDelayed(this.zzcod, ((Long) zzlc.zzio().zzd(zzoi.zzbrg)).longValue());
        zznr();
        return null;
    }

    /* access modifiers changed from: protected */
    public void zzx(int i) {
        int i2 = i;
        if (i2 != -2) {
            this.zzcoc = new zzacj(i2, this.zzcoc.zzcic);
        }
        this.zzcct.zztu();
        zzzn zzzn = this.zzcoa;
        zzacf zzacf = this.zzcob.zzcvm;
        zzahd zzahd = new zzahd(zzacf.zzcrv, this.zzcct, this.zzcoc.zzchw, i, this.zzcoc.zzchx, this.zzcoc.zzctq, this.zzcoc.orientation, this.zzcoc.zzcic, zzacf.zzcry, this.zzcoc.zzcto, (zzvp) null, (zzwi) null, (String) null, (zzvq) null, (zzvs) null, this.zzcoc.zzctp, this.zzcob.zzaud, this.zzcoc.zzctn, this.zzcob.zzdcn, this.zzcoc.zzcts, this.zzcoc.zzctt, this.zzcob.zzdch, (zzpx) null, this.zzcoc.zzcuc, this.zzcoc.zzcud, this.zzcoc.zzcue, this.zzcoc.zzcuf, this.zzcoc.zzcug, (String) null, this.zzcoc.zzchz, this.zzcoc.zzcuj, this.zzcob.zzdcu, this.zzcob.zzdcw.zzaqw, this.zzcob.zzdcv);
        zzzn.zzb(zzahd);
    }
}
