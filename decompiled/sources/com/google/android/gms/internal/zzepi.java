package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public final class zzepi extends zzbgl {
    public static final Parcelable.Creator<zzepi> CREATOR = new zzepj();
    private String zznsb;
    private String zznsc;
    private int zznsd;
    private long zznse = 0;
    private Bundle zznsf = null;
    private Uri zznsg;

    public zzepi(String str, String str2, int i, long j, Bundle bundle, Uri uri) {
        this.zznsb = str;
        this.zznsc = str2;
        this.zznsd = i;
        this.zznse = j;
        this.zznsf = bundle;
        this.zznsg = uri;
    }

    public final long getClickTimestamp() {
        return this.zznse;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zznsb, false);
        zzbgo.zza(parcel, 2, this.zznsc, false);
        zzbgo.zzc(parcel, 3, this.zznsd);
        zzbgo.zza(parcel, 4, this.zznse);
        zzbgo.zza(parcel, 5, zzcdw(), false);
        zzbgo.zza(parcel, 6, (Parcelable) this.zznsg, i, false);
        zzbgo.zzai(parcel, zze);
    }

    public final void zzcb(long j) {
        this.zznse = j;
    }

    public final Uri zzcdt() {
        return this.zznsg;
    }

    public final String zzcdu() {
        return this.zznsc;
    }

    public final int zzcdv() {
        return this.zznsd;
    }

    public final Bundle zzcdw() {
        Bundle bundle = this.zznsf;
        return bundle == null ? new Bundle() : bundle;
    }
}
