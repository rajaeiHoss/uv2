package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.RemoteViews;

public final class zzdmb extends zzbgl {
    public static final Parcelable.Creator<zzdmb> CREATOR = new zzdmc();
    private String[] zzlok;
    private int[] zzlol;
    private RemoteViews zzlom;
    private byte[] zzlon;

    private zzdmb() {
    }

    public zzdmb(String[] strArr, int[] iArr, RemoteViews remoteViews, byte[] bArr) {
        this.zzlok = strArr;
        this.zzlol = iArr;
        this.zzlom = remoteViews;
        this.zzlon = bArr;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzlok, false);
        zzbgo.zza(parcel, 2, this.zzlol, false);
        zzbgo.zza(parcel, 3, (Parcelable) this.zzlom, i, false);
        zzbgo.zza(parcel, 4, this.zzlon, false);
        zzbgo.zzai(parcel, zze);
    }
}
