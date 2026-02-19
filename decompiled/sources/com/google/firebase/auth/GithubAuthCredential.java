package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzeci;

public class GithubAuthCredential extends AuthCredential {
    public static final Parcelable.Creator<GithubAuthCredential> CREATOR = new zzq();
    private String zzeia;

    GithubAuthCredential(String str) {
        this.zzeia = zzbq.zzgv(str);
    }

    public static zzeci zza(GithubAuthCredential githubAuthCredential) {
        zzbq.checkNotNull(githubAuthCredential);
        return new zzeci((String) null, githubAuthCredential.zzeia, githubAuthCredential.getProvider(), (String) null, (String) null);
    }

    public String getProvider() {
        return "github.com";
    }

    public String getSignInMethod() {
        return "github.com";
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzeia, false);
        zzbgo.zzai(parcel, zze);
    }
}
