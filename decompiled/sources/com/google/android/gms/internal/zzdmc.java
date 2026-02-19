package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.RemoteViews;

public final class zzdmc implements Parcelable.Creator<zzdmb> {
    public final zzdmb createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String[] strArr = null;
        int[] iArr = null;
        RemoteViews remoteViews = null;
        byte[] bArr = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                strArr = zzbgm.zzaa(parcel, readInt);
            } else if (i == 2) {
                iArr = zzbgm.zzw(parcel, readInt);
            } else if (i == 3) {
                remoteViews = (RemoteViews) zzbgm.zza(parcel, readInt, RemoteViews.CREATOR);
            } else if (i != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                bArr = zzbgm.zzt(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzdmb(strArr, iArr, remoteViews, bArr);
    }

    public final zzdmb[] newArray(int i) {
        return new zzdmb[i];
    }
}
