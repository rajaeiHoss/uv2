package com.google.android.gms.search;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public class GoogleNowAuthState extends zzbgl {
    public static final Parcelable.Creator<GoogleNowAuthState> CREATOR = new zza();
    private String zzkla;
    private String zzklb;
    private long zzklc;

    GoogleNowAuthState(String str, String str2, long j) {
        this.zzkla = str;
        this.zzklb = str2;
        this.zzklc = j;
    }

    public String getAccessToken() {
        return this.zzklb;
    }

    public String getAuthCode() {
        return this.zzkla;
    }

    public long getNextAllowedTimeMillis() {
        return this.zzklc;
    }

    public String toString() {
        String str = this.zzkla;
        String str2 = this.zzklb;
        long j = this.zzklc;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 74 + String.valueOf(str2).length());
        sb.append("mAuthCode = ");
        sb.append(str);
        sb.append("\nmAccessToken = ");
        sb.append(str2);
        sb.append("\nmNextAllowedTimeMillis = ");
        sb.append(j);
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, getAuthCode(), false);
        zzbgo.zza(parcel, 2, getAccessToken(), false);
        zzbgo.zza(parcel, 3, getNextAllowedTimeMillis());
        zzbgo.zzai(parcel, zze);
    }
}
