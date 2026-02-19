package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzeci;

public class PlayGamesAuthCredential extends AuthCredential {
    public static final Parcelable.Creator<PlayGamesAuthCredential> CREATOR = new zzu();
    private final String zzemk;

    PlayGamesAuthCredential(String str) {
        this.zzemk = zzbq.zzgv(str);
    }

    public static zzeci zza(PlayGamesAuthCredential playGamesAuthCredential) {
        zzbq.checkNotNull(playGamesAuthCredential);
        return new zzeci((String) null, (String) null, playGamesAuthCredential.getProvider(), (String) null, (String) null, playGamesAuthCredential.zzemk);
    }

    public String getProvider() {
        return "playgames.google.com";
    }

    public String getSignInMethod() {
        return "playgames.google.com";
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzemk, false);
        zzbgo.zzai(parcel, zze);
    }
}
