package com.google.android.gms.ads.internal.js;

import android.content.Context;
import com.google.android.gms.ads.internal.gmsg.zzt;
import com.google.android.gms.ads.internal.zzbm;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzaij;
import com.google.android.gms.internal.zzako;
import com.google.android.gms.internal.zzala;
import com.google.android.gms.internal.zzaof;
import com.google.android.gms.internal.zzaop;
import com.google.android.gms.internal.zzapv;
import com.google.android.gms.internal.zzaqa;
import com.google.android.gms.internal.zzcv;
import com.google.android.gms.internal.zziu;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzov;
import java.util.Map;
import org.json.JSONObject;

@zzabh
public final class zze implements zzc {
    private final Context mContext;
    /* access modifiers changed from: private */
    public final zzaof zzcct;

    public zze(Context context, zzala zzala, zzcv zzcv, zzv zzv) throws zzaop {
        Context context2 = context;
        this.mContext = context2;
        zzaof zza = zzbt.zzem().zza(context2, zzaqa.zzvj(), "", false, false, zzcv, zzala, (zzov) null, (zzbm) null, (zzv) null, zziu.zzhp());
        this.zzcct = zza;
        zza.getView().setWillNotDraw(true);
    }

    private static void runOnUiThread(Runnable runnable) {
        zzlc.zzij();
        if (zzako.zzsa()) {
            runnable.run();
        } else {
            zzaij.zzdfn.post(runnable);
        }
    }

    public final void destroy() {
        this.zzcct.destroy();
    }

    public final void zza(zzd zzd) {
        this.zzcct.zzua().zza((zzapv) new zzk(this, zzd));
    }

    public final void zza(String str, zzt<? super zzaj> zzt) {
        this.zzcct.zzua().zza(str, (zzt<? super zzaof>) new zzl(this, zzt));
    }

    public final void zza(String str, Map<String, ?> map) {
        this.zzcct.zza(str, map);
    }

    public final void zza(String str, JSONObject jSONObject) {
        this.zzcct.zza(str, jSONObject);
    }

    public final void zzb(String str, zzt<? super zzaj> zzt) {
        this.zzcct.zzua().zza(str, (com.google.android.gms.common.util.zzt<zzt<? super zzaof>>) new zzf(zzt));
    }

    public final void zzb(String str, JSONObject jSONObject) {
        runOnUiThread(new zzg(this, str, jSONObject));
    }

    public final void zzbb(String str) {
        runOnUiThread(new zzh(this, String.format("<!DOCTYPE html><html><head><script src=\"%s\"></script></head><body></body></html>", new Object[]{str})));
    }

    public final void zzbc(String str) {
        runOnUiThread(new zzj(this, str));
    }

    public final void zzbd(String str) {
        runOnUiThread(new zzi(this, str));
    }

    public final zzak zzly() {
        return new zzal(this);
    }
}
