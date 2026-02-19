package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzeci;

public class TwitterAuthCredential extends AuthCredential {
    public static final Parcelable.Creator<TwitterAuthCredential> CREATOR = new zzv();
    private String zzeia;
    private String zzmqa;

    TwitterAuthCredential(String str, String str2) {
        this.zzeia = zzbq.zzgv(str);
        this.zzmqa = zzbq.zzgv(str2);
    }

    public static zzeci zza(TwitterAuthCredential twitterAuthCredential) {
        zzbq.checkNotNull(twitterAuthCredential);
        return new zzeci((String) null, twitterAuthCredential.zzeia, twitterAuthCredential.getProvider(), (String) null, twitterAuthCredential.zzmqa);
    }

    public String getProvider() {
        return "twitter.com";
    }

    public String getSignInMethod() {
        return "twitter.com";
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzeia, false);
        zzbgo.zza(parcel, 2, this.zzmqa, false);
        zzbgo.zzai(parcel, zze);
    }
}
