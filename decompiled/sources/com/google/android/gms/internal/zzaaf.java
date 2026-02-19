package com.google.android.gms.internal;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.gmsg.zzd;
import com.google.android.gms.ads.internal.gmsg.zzt;
import com.google.android.gms.ads.internal.zzbb;
import com.google.android.gms.ads.internal.zzbm;
import com.google.android.gms.ads.internal.zzbt;
import java.lang.ref.WeakReference;
import org.json.JSONObject;

@zzabh
public final class zzaaf {
    private final Context mContext;
    private final Object mLock = new Object();
    private final zzov zzanh;
    private int zzavd = -1;
    private int zzave = -1;
    private zzake zzavf;
    private final DisplayMetrics zzaxo;
    private final zzcv zzbyz;
    private final zzahe zzcob;
    /* access modifiers changed from: private */
    public final zzbb zzcpw;
    private ViewTreeObserver.OnGlobalLayoutListener zzcpx;
    private ViewTreeObserver.OnScrollChangedListener zzcpy;

    public zzaaf(Context context, zzcv zzcv, zzahe zzahe, zzov zzov, zzbb zzbb) {
        this.mContext = context;
        this.zzbyz = zzcv;
        this.zzcob = zzahe;
        this.zzanh = zzov;
        this.zzcpw = zzbb;
        this.zzavf = new zzake(200);
        zzbt.zzel();
        this.zzaxo = zzaij.zza((WindowManager) context.getSystemService("window"));
    }

    /* access modifiers changed from: private */
    public final void zza(WeakReference<zzaof> weakReference, boolean z) {
        zzaof zzaof;
        if (weakReference != null && (zzaof = (zzaof) weakReference.get()) != null && zzaof.getView() != null) {
            if (!z || this.zzavf.tryAcquire()) {
                int[] iArr = new int[2];
                zzaof.getView().getLocationOnScreen(iArr);
                zzlc.zzij();
                boolean z2 = false;
                int zzb = zzako.zzb(this.zzaxo, iArr[0]);
                zzlc.zzij();
                int zzb2 = zzako.zzb(this.zzaxo, iArr[1]);
                synchronized (this.mLock) {
                    if (!(this.zzavd == zzb && this.zzave == zzb2)) {
                        this.zzavd = zzb;
                        this.zzave = zzb2;
                        zzapu zzua = zzaof.zzua();
                        int i = this.zzavd;
                        int i2 = this.zzave;
                        if (!z) {
                            z2 = true;
                        }
                        zzua.zza(i, i2, z2);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(zzamd zzamd, zzaof zzaof, boolean z) {
        this.zzcpw.zzdx();
        zzamd.set(zzaof);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(JSONObject jSONObject, zzamd zzamd) {
        try {
            zzaof zza = zzbt.zzem().zza(this.mContext, zzaqa.zzvj(), "native-video", false, false, this.zzbyz, this.zzcob.zzcvm.zzatz, this.zzanh, (zzbm) null, this.zzcpw.zzbo(), this.zzcob.zzdcu);
            zza.zza(zzaqa.zzvk());
            this.zzcpw.zze(zza);
            WeakReference weakReference = new WeakReference(zza);
            zzapu zzua = zza.zzua();
            if (this.zzcpx == null) {
                this.zzcpx = new zzaal(this, weakReference);
            }
            ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = this.zzcpx;
            if (this.zzcpy == null) {
                this.zzcpy = new zzaam(this, weakReference);
            }
            zzua.zza(onGlobalLayoutListener, this.zzcpy);
            zzapu zzua2 = zza.zzua();
            zzua2.zza("/video", (zzt<? super zzaof>) zzd.zzcbp);
            zzua2.zza("/videoMeta", (zzt<? super zzaof>) zzd.zzcbq);
            zzua2.zza("/precache", (zzt<? super zzaof>) new zzaoc());
            zzua2.zza("/delayPageLoaded", (zzt<? super zzaof>) zzd.zzcbt);
            zzua2.zza("/instrument", (zzt<? super zzaof>) zzd.zzcbr);
            zzua2.zza("/log", (zzt<? super zzaof>) zzd.zzcbk);
            zzua2.zza("/videoClicked", (zzt<? super zzaof>) zzd.zzcbl);
            zzua2.zza("/trackActiveViewUnit", (zzt<? super zzaof>) new zzaaj(this));
            zzua2.zza("/untrackActiveViewUnit", (zzt<? super zzaof>) new zzaak(this));
            zza.zzua().zza((zzapw) new zzaah(zza, jSONObject));
            zza.zzua().zza((zzapv) new zzaai(this, zzamd));
            zza.loadUrl((String) zzlc.zzio().zzd(zzoi.zzbsl));
        } catch (Exception e) {
            zzahw.zzc("Exception occurred while getting video view", e);
            zzamd.set(null);
        }
    }
}
