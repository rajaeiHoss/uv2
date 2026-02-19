package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzi;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzebw extends zzbgl {
    public static final Parcelable.Creator<zzebw> CREATOR = new zzebx();
    private String zzmow;
    private String zzmqq;
    private Long zzmsq;
    private String zzmsr;
    private Long zzmss;

    public zzebw() {
        this.zzmss = Long.valueOf(System.currentTimeMillis());
    }

    zzebw(String str, String str2, Long l, String str3, Long l2) {
        this.zzmqq = str;
        this.zzmow = str2;
        this.zzmsq = l;
        this.zzmsr = str3;
        this.zzmss = l2;
    }

    public static zzebw zzpg(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            zzebw zzebw = new zzebw();
            zzebw.zzmqq = jSONObject.optString("refresh_token", (String) null);
            zzebw.zzmow = jSONObject.optString("access_token", (String) null);
            zzebw.zzmsq = Long.valueOf(jSONObject.optLong("expires_in"));
            zzebw.zzmsr = jSONObject.optString("token_type", (String) null);
            zzebw.zzmss = Long.valueOf(jSONObject.optLong("issued_at"));
            return zzebw;
        } catch (JSONException e) {
            Log.d("GetTokenResponse", "Failed to read GetTokenResponse from JSONObject");
            throw new zzdyz(e);
        }
    }

    public final String getAccessToken() {
        return this.zzmow;
    }

    public final boolean isValid() {
        return zzi.zzanq().currentTimeMillis() + 300000 < this.zzmss.longValue() + (this.zzmsq.longValue() * 1000);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzmqq, false);
        zzbgo.zza(parcel, 3, this.zzmow, false);
        zzbgo.zza(parcel, 4, Long.valueOf(zzbuf()), false);
        zzbgo.zza(parcel, 5, this.zzmsr, false);
        zzbgo.zza(parcel, 6, Long.valueOf(this.zzmss.longValue()), false);
        zzbgo.zzai(parcel, zze);
    }

    public final String zzack() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("refresh_token", this.zzmqq);
            jSONObject.put("access_token", this.zzmow);
            jSONObject.put("expires_in", this.zzmsq);
            jSONObject.put("token_type", this.zzmsr);
            jSONObject.put("issued_at", this.zzmss);
            return jSONObject.toString();
        } catch (JSONException e) {
            Log.d("GetTokenResponse", "Failed to convert GetTokenResponse to JSON");
            throw new zzdyz(e);
        }
    }

    public final String zzbue() {
        return this.zzmqq;
    }

    public final long zzbuf() {
        Long l = this.zzmsq;
        if (l == null) {
            return 0;
        }
        return l.longValue();
    }

    public final long zzbug() {
        return this.zzmss.longValue();
    }

    public final void zzpf(String str) {
        this.zzmqq = zzbq.zzgv(str);
    }
}
