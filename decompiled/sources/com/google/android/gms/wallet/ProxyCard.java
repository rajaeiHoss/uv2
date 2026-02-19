package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

@Deprecated
public final class ProxyCard extends zzbgl {
    public static final Parcelable.Creator<ProxyCard> CREATOR = new zzak();
    private String zzlnq;
    private String zzlnr;
    private int zzlns;
    private int zzlnt;

    public ProxyCard(String str, String str2, int i, int i2) {
        this.zzlnq = str;
        this.zzlnr = str2;
        this.zzlns = i;
        this.zzlnt = i2;
    }

    public final String getCvn() {
        return this.zzlnr;
    }

    public final int getExpirationMonth() {
        return this.zzlns;
    }

    public final int getExpirationYear() {
        return this.zzlnt;
    }

    public final String getPan() {
        return this.zzlnq;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzlnq, false);
        zzbgo.zza(parcel, 3, this.zzlnr, false);
        zzbgo.zzc(parcel, 4, this.zzlns);
        zzbgo.zzc(parcel, 5, this.zzlnt);
        zzbgo.zzai(parcel, zze);
    }
}
