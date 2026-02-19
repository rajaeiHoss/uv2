package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

final class zzahz extends zzaib {
    private /* synthetic */ Context zzdff;
    private /* synthetic */ zzahy zzdfg;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzahz(zzahy zzahy, Context context) {
        super((zzahz) null);
        this.zzdfg = zzahy;
        this.zzdff = context;
    }

    public final void zzdo() {
        SharedPreferences sharedPreferences = this.zzdff.getSharedPreferences("admob", 0);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        synchronized (this.zzdfg.mLock) {
            SharedPreferences unused = this.zzdfg.zzbkx = sharedPreferences;
            this.zzdfg.zzdev = edit;
            zzahy zzahy = this.zzdfg;
            boolean unused2 = zzahy.zzdew = zzahy.zzql();
            zzahy zzahy2 = this.zzdfg;
            boolean unused3 = zzahy2.zzcxd = zzahy2.zzbkx.getBoolean("use_https", this.zzdfg.zzcxd);
            zzahy zzahy3 = this.zzdfg;
            boolean unused4 = zzahy3.zzcxe = zzahy3.zzbkx.getBoolean("content_url_opted_out", this.zzdfg.zzcxe);
            zzahy zzahy4 = this.zzdfg;
            String unused5 = zzahy4.zzdex = zzahy4.zzbkx.getString("content_url_hashes", this.zzdfg.zzdex);
            zzahy zzahy5 = this.zzdfg;
            boolean unused6 = zzahy5.zzcxm = zzahy5.zzbkx.getBoolean("auto_collect_location", this.zzdfg.zzcxm);
            zzahy zzahy6 = this.zzdfg;
            boolean unused7 = zzahy6.zzcxf = zzahy6.zzbkx.getBoolean("content_vertical_opted_out", this.zzdfg.zzcxf);
            zzahy zzahy7 = this.zzdfg;
            String unused8 = zzahy7.zzdey = zzahy7.zzbkx.getString("content_vertical_hashes", this.zzdfg.zzdey);
            zzahy zzahy8 = this.zzdfg;
            int unused9 = zzahy8.zzdfc = zzahy8.zzbkx.getInt("version_code", this.zzdfg.zzdfc);
            zzahy zzahy9 = this.zzdfg;
            String unused10 = zzahy9.zzddm = zzahy9.zzbkx.getString("app_settings_json", this.zzdfg.zzddm);
            zzahy zzahy10 = this.zzdfg;
            long unused11 = zzahy10.zzdez = zzahy10.zzbkx.getLong("app_settings_last_update_ms", this.zzdfg.zzdez);
            zzahy zzahy11 = this.zzdfg;
            long unused12 = zzahy11.zzdfa = zzahy11.zzbkx.getLong("app_last_background_time_ms", this.zzdfg.zzdfa);
            zzahy zzahy12 = this.zzdfg;
            int unused13 = zzahy12.zzdef = zzahy12.zzbkx.getInt("request_in_session_count", this.zzdfg.zzdef);
            zzahy zzahy13 = this.zzdfg;
            long unused14 = zzahy13.zzdfb = zzahy13.zzbkx.getLong("first_ad_req_time_ms", this.zzdfg.zzdfb);
            zzahy zzahy14 = this.zzdfg;
            Set unused15 = zzahy14.zzdfd = zzahy14.zzbkx.getStringSet("never_pool_slots", this.zzdfg.zzdfd);
            try {
                JSONObject unused16 = this.zzdfg.zzdfe = new JSONObject(this.zzdfg.zzbkx.getString("native_advanced_settings", "{}"));
            } catch (JSONException e) {
                zzahw.zzc("Could not convert native advanced settings to json object", e);
            }
            zzahy zzahy15 = this.zzdfg;
            zzahy15.zzc(zzahy15.zzqn());
        }
    }
}
