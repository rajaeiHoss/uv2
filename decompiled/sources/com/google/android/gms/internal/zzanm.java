package com.google.android.gms.internal;

import org.json.JSONException;
import org.json.JSONObject;

@zzabh
public final class zzanm {
    public final String zzdnm;
    private boolean zzdnn;
    private int zzdno;
    private int zzdnp;
    private int zzdnq;
    private int zzdnr;
    private boolean zzdns;

    public zzanm(String str) {
        JSONObject jSONObject = null;
        if (str != null) {
            try {
                jSONObject = new JSONObject(str);
            } catch (JSONException unused) {
            }
        }
        this.zzdnn = zza(jSONObject, "aggressive_media_codec_release", zzoi.zzbmi);
        this.zzdnm = zzc(jSONObject, "exo_player_version", zzoi.zzblp);
        this.zzdno = zzb(jSONObject, "exo_cache_buffer_size", zzoi.zzblw);
        this.zzdnp = zzb(jSONObject, "exo_connect_timeout_millis", zzoi.zzblq);
        this.zzdnq = zzb(jSONObject, "exo_read_timeout_millis", zzoi.zzblr);
        this.zzdnr = zzb(jSONObject, "load_check_interval_bytes", zzoi.zzbls);
        this.zzdns = zza(jSONObject, "use_cache_data_source", zzoi.zzbuh);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.android.gms.internal.zzny<java.lang.Boolean>, com.google.android.gms.internal.zzny] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean zza(org.json.JSONObject r0, java.lang.String r1, com.google.android.gms.internal.zzny<java.lang.Boolean> r2) {
        /*
            if (r0 == 0) goto L_0x0007
            boolean r0 = r0.getBoolean(r1)     // Catch:{ JSONException -> 0x0007 }
            return r0
        L_0x0007:
            com.google.android.gms.internal.zzog r0 = com.google.android.gms.internal.zzlc.zzio()
            java.lang.Object r0 = r0.zzd(r2)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzanm.zza(org.json.JSONObject, java.lang.String, com.google.android.gms.internal.zzny):boolean");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.android.gms.internal.zzny<java.lang.Integer>, com.google.android.gms.internal.zzny] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int zzb(org.json.JSONObject r0, java.lang.String r1, com.google.android.gms.internal.zzny<java.lang.Integer> r2) {
        /*
            if (r0 == 0) goto L_0x0007
            int r0 = r0.getInt(r1)     // Catch:{ JSONException -> 0x0007 }
            return r0
        L_0x0007:
            com.google.android.gms.internal.zzog r0 = com.google.android.gms.internal.zzlc.zzio()
            java.lang.Object r0 = r0.zzd(r2)
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzanm.zzb(org.json.JSONObject, java.lang.String, com.google.android.gms.internal.zzny):int");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.android.gms.internal.zzny<java.lang.String>, com.google.android.gms.internal.zzny] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String zzc(org.json.JSONObject r0, java.lang.String r1, com.google.android.gms.internal.zzny<java.lang.String> r2) {
        /*
            if (r0 == 0) goto L_0x0007
            java.lang.String r0 = r0.getString(r1)     // Catch:{ JSONException -> 0x0007 }
            return r0
        L_0x0007:
            com.google.android.gms.internal.zzog r0 = com.google.android.gms.internal.zzlc.zzio()
            java.lang.Object r0 = r0.zzd(r2)
            java.lang.String r0 = (java.lang.String) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzanm.zzc(org.json.JSONObject, java.lang.String, com.google.android.gms.internal.zzny):java.lang.String");
    }
}
