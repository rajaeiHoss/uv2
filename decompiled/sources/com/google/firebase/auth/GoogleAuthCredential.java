package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzeci;

public class GoogleAuthCredential extends AuthCredential {
    public static final Parcelable.Creator<GoogleAuthCredential> CREATOR = new zzr();
    private final String zzelh;
    private final String zzmow;

    GoogleAuthCredential(String str, String str2) {
        if (str == null && str2 == null) {
            throw new IllegalArgumentException("Must specify an idToken or an accessToken.");
        }
        this.zzelh = zzba(str, "idToken");
        this.zzmow = zzba(str2, "accessToken");
    }

    public static zzeci zza(GoogleAuthCredential googleAuthCredential) {
        zzbq.checkNotNull(googleAuthCredential);
        return new zzeci(googleAuthCredential.zzelh, googleAuthCredential.zzmow, googleAuthCredential.getProvider(), (String) null, (String) null);
    }

    private static String zzba(String str, String str2) {
        if (str == null || !TextUtils.isEmpty(str)) {
            return str;
        }
        throw new IllegalArgumentException(String.valueOf(str2).concat(" must not be empty"));
    }

    public String getProvider() {
        return "google.com";
    }

    public String getSignInMethod() {
        return "google.com";
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzelh, false);
        zzbgo.zza(parcel, 2, this.zzmow, false);
        zzbgo.zzai(parcel, zze);
    }
}
