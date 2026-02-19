package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.gmsg.zzt;
import com.google.android.gms.ads.internal.gmsg.zzx;
import com.google.android.gms.ads.internal.gmsg.zzz;
import com.google.android.gms.ads.internal.js.JavascriptEngineFactory;
import com.google.android.gms.ads.internal.js.zzn;
import com.google.android.gms.ads.internal.zzbb;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.ads.internal.zzw;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

@zzabh
public final class zzaar extends zzzw<zzaar> {
    private static final Object sLock = new Object();
    private static final long zzcqj = TimeUnit.SECONDS.toMillis(60);
    private static boolean zzcqk = false;
    private static zzn zzcql = null;
    private final Context mContext;
    private final Object mLock = new Object();
    private final zzala zzarg;
    private String zzarn;
    private final zzcv zzbyz;
    private final zzbb zzcpw;
    private JavascriptEngineFactory zzcqh;
    private zzalt<zzaof> zzcqi;
    private final zzz zzcqm;
    private final zzqa zzcqn;

    public zzaar(Context context, zzbb zzbb, String str, zzcv zzcv, zzala zzala) {
        zzahw.zzcy("Webview loading for native ads.");
        this.mContext = context;
        this.zzcpw = zzbb;
        this.zzbyz = zzcv;
        this.zzarg = zzala;
        this.zzarn = str;
        this.zzcqh = new JavascriptEngineFactory();
        zzbt.zzem();
        zzalt<zzaof> zza = zzaol.zza(context, zzala, (String) zzlc.zzio().zzd(zzoi.zzbsi), zzcv, zzbb.zzbo());
        this.zzcqm = new zzz(context);
        this.zzcqn = new zzqa(zzbb, str);
        zzalt<zzaof> zza2 = zzali.zza(zza, new zzaas(this), zzaly.zzdju);
        this.zzcqi = zza2;
        zzalg.zza(zza2, "WebViewNativeAdsUtil.constructor");
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzalt zza(JSONObject jSONObject, zzaof zzaof) throws Exception {
        jSONObject.put("ads_id", this.zzarn);
        zzaof.zzb("google.afma.nativeAds.handleImpressionPing", jSONObject);
        return zzali.zzh(new JSONObject());
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(zzaof zzaof, zzzx zzzx, zzamd zzamd, zzaof zzaof2, Map map) {
        boolean z;
        JSONObject jSONObject;
        try {
            String str = (String) map.get(FirebaseAnalytics.Param.SUCCESS);
            String str2 = (String) map.get("failure");
            if (!TextUtils.isEmpty(str2)) {
                jSONObject = new JSONObject(str2);
                z = false;
            } else {
                jSONObject = new JSONObject(str);
                z = true;
            }
            if (this.zzarn.equals(jSONObject.optString("ads_id", ""))) {
                zzaof.zzb("/nativeAdPreProcess", (zzt<? super zzaof>) zzzx.zzcov);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(FirebaseAnalytics.Param.SUCCESS, z);
                jSONObject2.put("json", jSONObject);
                zzamd.set(jSONObject2);
            }
        } catch (Throwable th) {
            zzahw.zzb("Error while preprocessing json.", th);
            zzamd.setException(th);
        }
    }

    public final void zza(String str, zzt zzt) {
        zzali.zza(this.zzcqi, new zzaax(this, str, zzt), zzaly.zzdjt);
    }

    public final void zza(String str, JSONObject jSONObject) {
        zzali.zza(this.zzcqi, new zzaaz(this, str, jSONObject), zzaly.zzdjt);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzalt zzb(JSONObject jSONObject, zzaof zzaof) throws Exception {
        jSONObject.put("ads_id", this.zzarn);
        zzaof.zzb("google.afma.nativeAds.handleClickGmsg", jSONObject);
        return zzali.zzh(new JSONObject());
    }

    public final void zzb(String str, zzt zzt) {
        zzali.zza(this.zzcqi, new zzaay(this, str, zzt), zzaly.zzdjt);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzalt zzc(JSONObject jSONObject, zzaof zzaof) throws Exception {
        jSONObject.put("ads_id", this.zzarn);
        zzamd zzamd = new zzamd();
        zzzx zzzx = new zzzx();
        zzaaw zzaaw = new zzaaw(this, zzaof, zzzx, zzamd);
        zzzx.zzcov = zzaaw;
        zzaof.zza("/nativeAdPreProcess", (zzt<? super zzaof>) zzaaw);
        zzaof.zzb("google.afma.nativeAds.preProcessJsonGmsg", jSONObject);
        return zzamd;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzalt zzg(zzaof zzaof) throws Exception {
        zzahw.zzcy("Javascript has loaded for native ads.");
        zzapu zzua = zzaof.zzua();
        zzbb zzbb = this.zzcpw;
        zzua.zza(zzbb, zzbb, zzbb, zzbb, false, (zzx) null, new zzw(this.mContext, (zzagq) null, (zzacl) null), (zzyo) null, (zzagq) null);
        zzaof.zzua().zza("/logScionEvent", (zzt<? super zzaof>) this.zzcqm);
        zzaof.zzua().zza("/logScionEvent", (zzt<? super zzaof>) this.zzcqn);
        return zzali.zzh(zzaof);
    }

    public final zzalt<JSONObject> zzh(JSONObject jSONObject) {
        return zzali.zza(this.zzcqi, new zzaat(this, jSONObject), zzaly.zzdjt);
    }

    public final zzalt<JSONObject> zzi(JSONObject jSONObject) {
        return zzali.zza(this.zzcqi, new zzaau(this, jSONObject), zzaly.zzdjt);
    }

    public final zzalt<JSONObject> zzj(JSONObject jSONObject) {
        return zzali.zza(this.zzcqi, new zzaav(this, jSONObject), zzaly.zzdjt);
    }

    public final void zzmd() {
        zzali.zza(this.zzcqi, new zzaba(this), zzaly.zzdjt);
    }
}
