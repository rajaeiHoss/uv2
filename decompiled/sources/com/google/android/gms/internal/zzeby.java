package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

public final class zzeby extends zzbgl {
    public static final Parcelable.Creator<zzeby> CREATOR = new zzebz();
    private String zzemh;
    private String zzemi;
    private String zziva;
    private String zzmov;
    private String zzmqb;
    private String zzmst;
    private String zzmsu;

    public zzeby() {
    }

    zzeby(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.zzmst = str;
        this.zzemi = str2;
        this.zzmqb = str3;
        this.zzmov = str4;
        this.zzmsu = str5;
        this.zziva = str6;
        this.zzemh = str7;
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

    public final Uri getPhotoUri() {
        if (!TextUtils.isEmpty(this.zzmqb)) {
            return Uri.parse(this.zzmqb);
        }
        return null;
    }

    public final String getProviderId() {
        return this.zzmov;
    }

    public final String getRawUserInfo() {
        return this.zzmsu;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzmst, false);
        zzbgo.zza(parcel, 3, this.zzemi, false);
        zzbgo.zza(parcel, 4, this.zzmqb, false);
        zzbgo.zza(parcel, 5, this.zzmov, false);
        zzbgo.zza(parcel, 6, this.zzmsu, false);
        zzbgo.zza(parcel, 7, this.zziva, false);
        zzbgo.zza(parcel, 8, this.zzemh, false);
        zzbgo.zzai(parcel, zze);
    }

    public final String zzbuh() {
        return this.zzmst;
    }
}
