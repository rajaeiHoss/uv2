package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzeci;

public class zzd extends zzs {
    public static final Parcelable.Creator<zzd> CREATOR = new zze();
    private final String zzelh;
    private final String zzmov;
    private final String zzmow;
    private final zzeci zzmox;

    zzd(String str, String str2, String str3, zzeci zzeci) {
        this.zzmov = str;
        this.zzelh = str2;
        this.zzmow = str3;
        this.zzmox = zzeci;
    }

    public static zzeci zza(zzd zzd) {
        zzbq.checkNotNull(zzd);
        zzeci zzeci = zzd.zzmox;
        return zzeci != null ? zzeci : new zzeci(zzd.zzelh, zzd.zzmow, zzd.getProvider(), (String) null, (String) null);
    }

    public static zzd zzo(String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str3)) {
            return new zzd(zzbq.zzh(str, "Must specify a non-empty providerId"), str2, str3, (zzeci) null);
        }
        throw new IllegalArgumentException("Must specify an idToken or an accessToken.");
    }

    public String getProvider() {
        return this.zzmov;
    }

    public String getSignInMethod() {
        return this.zzmov;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, getProvider(), false);
        zzbgo.zza(parcel, 2, this.zzelh, false);
        zzbgo.zza(parcel, 3, this.zzmow, false);
        zzbgo.zza(parcel, 4, (Parcelable) this.zzmox, i, false);
        zzbgo.zzai(parcel, zze);
    }
}
