package com.google.firebase.auth.internal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzdyz;
import com.google.android.gms.internal.zzebu;
import com.google.android.gms.internal.zzeby;
import com.google.firebase.auth.UserInfo;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzh extends zzbgl implements UserInfo {
    public static final Parcelable.Creator<zzh> CREATOR = new zzi();
    private String zzaux;
    private String zzemh;
    private String zzemi;
    private String zziva;
    private String zzmov;
    private String zzmqb;
    private Uri zzmqe;
    private boolean zzmsl;
    private String zzmsu;

    public zzh(zzebu zzebu, String str) {
        zzbq.checkNotNull(zzebu);
        zzbq.zzgv(str);
        this.zzaux = zzbq.zzgv(zzebu.getLocalId());
        this.zzmov = str;
        this.zzemh = zzebu.getEmail();
        this.zzemi = zzebu.getDisplayName();
        Uri photoUri = zzebu.getPhotoUri();
        if (photoUri != null) {
            this.zzmqb = photoUri.toString();
            this.zzmqe = photoUri;
        }
        this.zzmsl = zzebu.isEmailVerified();
        this.zzmsu = null;
        this.zziva = zzebu.getPhoneNumber();
    }

    public zzh(zzeby zzeby) {
        zzbq.checkNotNull(zzeby);
        this.zzaux = zzeby.zzbuh();
        this.zzmov = zzbq.zzgv(zzeby.getProviderId());
        this.zzemi = zzeby.getDisplayName();
        Uri photoUri = zzeby.getPhotoUri();
        if (photoUri != null) {
            this.zzmqb = photoUri.toString();
            this.zzmqe = photoUri;
        }
        this.zzemh = zzeby.getEmail();
        this.zziva = zzeby.getPhoneNumber();
        this.zzmsl = false;
        this.zzmsu = zzeby.getRawUserInfo();
    }

    public zzh(String str, String str2, String str3, String str4, String str5, String str6, boolean z, String str7) {
        this.zzaux = str;
        this.zzmov = str2;
        this.zzemh = str3;
        this.zziva = str4;
        this.zzemi = str5;
        this.zzmqb = str6;
        if (!TextUtils.isEmpty(str6)) {
            this.zzmqe = Uri.parse(this.zzmqb);
        }
        this.zzmsl = z;
        this.zzmsu = str7;
    }

    public static zzh zzph(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            return new zzh(jSONObject.optString("userId"), jSONObject.optString("providerId"), jSONObject.optString("email"), jSONObject.optString("phoneNumber"), jSONObject.optString("displayName"), jSONObject.optString("photoUrl"), jSONObject.optBoolean("isEmailVerified"), jSONObject.optString("rawUserInfo"));
        } catch (JSONException e) {
            Log.d("DefaultAuthUserInfo", "Failed to unpack UserInfo from JSON");
            throw new zzdyz(e);
        }
    }

    public final String getDisplayName() {
        return this.zzemi;
    }

    public final String getEmail() {
        return this.zzemh;
    }

    public final String getPhoneNumber() {
        return this.zziva;
    }

    public final Uri getPhotoUrl() {
        if (!TextUtils.isEmpty(this.zzmqb) && this.zzmqe == null) {
            this.zzmqe = Uri.parse(this.zzmqb);
        }
        return this.zzmqe;
    }

    public final String getProviderId() {
        return this.zzmov;
    }

    public final String getRawUserInfo() {
        return this.zzmsu;
    }

    public final String getUid() {
        return this.zzaux;
    }

    public final boolean isEmailVerified() {
        return this.zzmsl;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, getUid(), false);
        zzbgo.zza(parcel, 2, getProviderId(), false);
        zzbgo.zza(parcel, 3, getDisplayName(), false);
        zzbgo.zza(parcel, 4, this.zzmqb, false);
        zzbgo.zza(parcel, 5, getEmail(), false);
        zzbgo.zza(parcel, 6, getPhoneNumber(), false);
        zzbgo.zza(parcel, 7, isEmailVerified());
        zzbgo.zza(parcel, 8, this.zzmsu, false);
        zzbgo.zzai(parcel, zze);
    }

    public final String zzack() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("userId", this.zzaux);
            jSONObject.putOpt("providerId", this.zzmov);
            jSONObject.putOpt("displayName", this.zzemi);
            jSONObject.putOpt("photoUrl", this.zzmqb);
            jSONObject.putOpt("email", this.zzemh);
            jSONObject.putOpt("phoneNumber", this.zziva);
            jSONObject.putOpt("isEmailVerified", Boolean.valueOf(this.zzmsl));
            jSONObject.putOpt("rawUserInfo", this.zzmsu);
            return jSONObject.toString();
        } catch (JSONException e) {
            Log.d("DefaultAuthUserInfo", "Failed to jsonify this object");
            throw new zzdyz(e);
        }
    }
}
