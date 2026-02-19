package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;

public final class zzcfd extends zzbgl {
    public static final Parcelable.Creator<zzcfd> CREATOR = new zzcfe();
    private final String path;
    private final int port;
    private final String zzios;
    private final String zziqf;
    private final String zziqg;
    private final String zziqh;
    private final String zziqi;
    private final String zziqj;

    public zzcfd(String str, int i, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.zziqf = zzbq.zzgv(str);
        this.port = i;
        this.path = str2;
        this.zziqg = str3;
        this.zziqh = str4;
        this.zziqi = str5;
        this.zzios = str6;
        this.zziqj = str7;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zziqf, false);
        zzbgo.zzc(parcel, 3, this.port);
        zzbgo.zza(parcel, 4, this.path, false);
        zzbgo.zza(parcel, 5, this.zziqg, false);
        zzbgo.zza(parcel, 6, this.zziqh, false);
        zzbgo.zza(parcel, 7, this.zzios, false);
        zzbgo.zza(parcel, 8, this.zziqj, false);
        zzbgo.zza(parcel, 9, this.zziqh, false);
        zzbgo.zzai(parcel, zze);
    }
}
