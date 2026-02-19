package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public final class zzceh extends zzbgl {
    public static final Parcelable.Creator<zzceh> CREATOR = new zzcei();
    private final String packageName;
    private final String splitName;
    private final int versionCode;
    private final String zzgog;
    private final int zzipb;
    private final boolean zzipc;
    private final Intent zzipd;
    private final Intent zzipe;
    private final zzcdv zzipf;
    private final zzcfd zzipg;
    private final boolean zziph;
    private final byte[] zzipi;
    private final int zzipj;
    private final byte[] zzipk;
    private final Bundle zzipl;

    zzceh(int i, String str, boolean z, Intent intent, Intent intent2, zzcdv zzcdv, zzcfd zzcfd, boolean z2, byte[] bArr, String str2, int i2, int i3, String str3, byte[] bArr2, Bundle bundle) {
        this.zzipb = i;
        this.zzgog = str;
        this.zzipc = z;
        this.zzipd = intent;
        this.zzipe = intent2;
        this.zzipf = zzcdv;
        this.zzipg = zzcfd;
        this.zziph = z2;
        this.zzipi = bArr;
        this.packageName = str2;
        this.versionCode = i2;
        this.splitName = str3;
        this.zzipj = i3;
        this.zzipk = bArr2;
        this.zzipl = bundle;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, this.zzipb);
        zzbgo.zza(parcel, 3, this.zzgog, false);
        zzbgo.zza(parcel, 4, this.zzipc);
        zzbgo.zza(parcel, 5, (Parcelable) this.zzipd, i, false);
        zzbgo.zza(parcel, 6, (Parcelable) this.zzipe, i, false);
        zzbgo.zza(parcel, 8, (Parcelable) this.zzipf, i, false);
        zzbgo.zza(parcel, 9, (Parcelable) this.zzipg, i, false);
        zzbgo.zza(parcel, 10, this.zziph);
        zzbgo.zza(parcel, 11, this.zzipi, false);
        zzbgo.zza(parcel, 12, this.packageName, false);
        zzbgo.zzc(parcel, 13, this.versionCode);
        zzbgo.zza(parcel, 14, this.splitName, false);
        zzbgo.zza(parcel, 15, this.zzipl, false);
        zzbgo.zzc(parcel, 16, this.zzipj);
        zzbgo.zza(parcel, 17, this.zzipk, false);
        zzbgo.zzai(parcel, zze);
    }
}
