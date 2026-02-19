package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.ads.internal.zzbt;
import org.json.JSONException;
import org.json.JSONObject;

@zzabh
public final class zzaef extends zzaeh {
    private final Context mApplicationContext;
    private final Object mLock = new Object();
    private SharedPreferences zzcze;
    private final zzux<JSONObject, JSONObject> zzczf;

    public zzaef(Context context, zzux<JSONObject, JSONObject> zzux) {
        this.mApplicationContext = context.getApplicationContext();
        this.zzczf = zzux;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Void zzm(JSONObject jSONObject) {
        zzoi.zza(this.mApplicationContext, 1, jSONObject);
        this.zzcze.edit().putLong("js_last_update", zzbt.zzes().currentTimeMillis()).apply();
        return null;
    }

    public final zzalt<Void> zzon() {
        synchronized (this.mLock) {
            if (this.zzcze == null) {
                this.zzcze = this.mApplicationContext.getSharedPreferences("google_ads_flags_meta", 0);
            }
        }
        if (zzbt.zzes().currentTimeMillis() - this.zzcze.getLong("js_last_update", 0) < ((Long) zzlc.zzio().zzd(zzoi.zzbse)).longValue()) {
            return zzali.zzh(null);
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("js", zzala.zzse().zzcu);
            jSONObject.put("mf", zzlc.zzio().zzd(zzoi.zzbsf));
            jSONObject.put("cl", "190237664");
            jSONObject.put("rapid_rc", "dev");
            jSONObject.put("rapid_rollup", "HEAD");
            return zzali.zza(this.zzczf.zzf(jSONObject), new zzaeg(this), zzaly.zzdju);
        } catch (JSONException e) {
            zzahw.zzb("Unable to populate SDK Core Constants parameters.", e);
            return zzali.zzh(null);
        }
    }
}
