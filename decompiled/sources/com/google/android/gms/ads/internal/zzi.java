package com.google.android.gms.ads.internal;

import android.content.Context;
import android.view.View;
import com.google.android.gms.ads.internal.gmsg.zzt;
import com.google.android.gms.ads.internal.gmsg.zzx;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzagq;
import com.google.android.gms.internal.zzahd;
import com.google.android.gms.internal.zzahe;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzaij;
import com.google.android.gms.internal.zzala;
import com.google.android.gms.internal.zzaof;
import com.google.android.gms.internal.zzaop;
import com.google.android.gms.internal.zzaqa;
import com.google.android.gms.internal.zzko;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzoi;
import com.google.android.gms.internal.zzov;
import com.google.android.gms.internal.zzpb;
import com.google.android.gms.internal.zzpx;
import com.google.android.gms.internal.zzvp;
import com.google.android.gms.internal.zzvs;
import com.google.android.gms.internal.zzwf;
import com.google.android.gms.internal.zzwi;
import com.google.android.gms.internal.zzyo;
import com.google.android.gms.internal.zzzm;

@zzabh
public abstract class zzi extends zzd implements zzae, zzyo {
    private boolean zzany;

    public zzi(Context context, zzko zzko, String str, zzwf zzwf, zzala zzala, zzv zzv) {
        super(context, zzko, str, zzwf, zzala, zzv);
    }

    /* access modifiers changed from: protected */
    public zzaof zza(zzahe zzahe, zzw zzw, zzagq zzagq) throws zzaop {
        zzahe zzahe2 = zzahe;
        View nextView = this.zzanm.zzaua.getNextView();
        if (nextView instanceof zzaof) {
            ((zzaof) nextView).destroy();
        }
        if (nextView != null) {
            this.zzanm.zzaua.removeView(nextView);
        }
        zzaof zza = zzbt.zzem().zza(this.zzanm.zzaiq, zzaqa.zzc(this.zzanm.zzaud), this.zzanm.zzaud.zzbia, false, false, this.zzanm.zzaty, this.zzanm.zzatz, this.zzanh, this, this.zzanp, zzahe2.zzdcu);
        if (this.zzanm.zzaud.zzbic == null) {
            zzg(zza.getView());
        }
        zza.zzua().zza(this, this, this, this, false, (zzx) null, zzw, this, zzagq);
        zza(zza);
        zza.zzde(zzahe2.zzcvm.zzcsi);
        return zza;
    }

    public final void zza(int i, int i2, int i3, int i4) {
        zzbv();
    }

    /* access modifiers changed from: protected */
    public void zza(zzahe zzahe, zzov zzov) {
        if (zzahe.errorCode != -2) {
            zzaij.zzdfn.post(new zzk(this, zzahe));
            return;
        }
        if (zzahe.zzaud != null) {
            this.zzanm.zzaud = zzahe.zzaud;
        }
        if (!zzahe.zzdcw.zzcto || zzahe.zzdcw.zzbif) {
            zzaij.zzdfn.post(new zzl(this, zzahe, this.zzanp.zzaom.zza(this.zzanm.zzaiq, this.zzanm.zzatz, zzahe.zzdcw), zzov));
            return;
        }
        this.zzanm.zzavb = 0;
        zzbu zzbu = this.zzanm;
        zzbt.zzek();
        zzbu.zzauc = zzzm.zza(this.zzanm.zzaiq, this, zzahe, this.zzanm.zzaty, (zzaof) null, this.zzanu, this, zzov);
    }

    /* access modifiers changed from: protected */
    public final void zza(zzaof zzaof) {
        zzaof.zza("/trackActiveViewUnit", (zzt<? super zzaof>) new zzj(this));
    }

    public final void zza(zzpb zzpb) {
        zzbq.zzgn("setOnCustomRenderedAdLoadedListener must be called on the main UI thread.");
        this.zzanm.zzauv = zzpb;
    }

    /* access modifiers changed from: protected */
    public boolean zza(zzahd zzahd, zzahd zzahd2) {
        boolean z;
        if (this.zzanm.zzfo() && this.zzanm.zzaua != null) {
            this.zzanm.zzaua.zzfr().zzcr(zzahd2.zzctt);
        }
        try {
            if (zzahd2.zzcnm != null && !zzahd2.zzcto && zzahd2.zzdcv) {
                if (((Boolean) zzlc.zzio().zzd(zzoi.zzbvv)).booleanValue()) {
                    String str = zzahd2.zzcrv.zzbhj;
                    if (str != null) {
                        if (!"com.google.ads.mediation.AbstractAdViewAdapter".equals(str)) {
                            z = false;
                            if (z && !zzahd2.zzcrv.extras.containsKey("sdk_less_server_data")) {
                                zzahd2.zzcnm.zzum();
                            }
                        }
                    }
                    z = true;
                    try {
                        zzahd2.zzcnm.zzum();
                    } catch (Throwable unused) {
                        zzahw.v("Could not render test Ad label.");
                    }
                }
            }
        } catch (RuntimeException unused2) {
            zzahw.v("Could not render test AdLabel.");
        }
        return super.zza(zzahd, zzahd2);
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzaof zzaof) {
        if (this.zzanm.zzaue != null) {
            this.zzano.zza(this.zzanm.zzaud, this.zzanm.zzaue, zzaof.getView(), zzaof);
            this.zzany = false;
            return;
        }
        this.zzany = true;
        zzahw.zzcz("Request to enable ActiveView before adState is available.");
    }

    /* access modifiers changed from: protected */
    public void zzbw() {
        super.zzbw();
        if (this.zzany) {
            if (((Boolean) zzlc.zzio().zzd(zzoi.zzbst)).booleanValue()) {
                zzb(this.zzanm.zzaue.zzcnm);
            }
        }
    }

    public final void zzcr() {
        onAdClicked();
    }

    public final void zzcs() {
        recordImpression();
        zzbs();
    }

    public final void zzct() {
        zzbt();
    }

    public final void zzh(View view) {
        this.zzanm.zzava = view;
        zzb(new zzahd(this.zzanm.zzauf, (zzaof) null, (zzvp) null, (zzwi) null, (String) null, (zzvs) null, (zzpx) null, (String) null));
    }
}
