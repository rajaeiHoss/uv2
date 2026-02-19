package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzeci;

public class FacebookAuthCredential extends AuthCredential {
    public static final Parcelable.Creator<FacebookAuthCredential> CREATOR = new zzg();
    private final String zzmow;

    FacebookAuthCredential(String str) {
        this.zzmow = zzbq.zzgv(str);
    }

    public static zzeci zza(FacebookAuthCredential facebookAuthCredential) {
        zzbq.checkNotNull(facebookAuthCredential);
        return new zzeci((String) null, facebookAuthCredential.zzmow, facebookAuthCredential.getProvider(), (String) null, (String) null);
    }

    public String getProvider() {
        return "facebook.com";
    }

    public String getSignInMethod() {
        return "facebook.com";
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzmow, false);
        zzbgo.zzai(parcel, zze);
    }
}
