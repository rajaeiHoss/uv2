package com.google.android.gms.games.video;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zza implements Parcelable.Creator<VideoCapabilities> {
    public final /* synthetic */ VideoCapabilities createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        boolean[] zArr = null;
        boolean[] zArr2 = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                z = zzbgm.zzc(parcel, readInt);
            } else if (i == 2) {
                z2 = zzbgm.zzc(parcel, readInt);
            } else if (i == 3) {
                z3 = zzbgm.zzc(parcel, readInt);
            } else if (i == 4) {
                zArr = zzbgm.zzv(parcel, readInt);
            } else if (i != 5) {
                zzbgm.zzb(parcel, readInt);
            } else {
                zArr2 = zzbgm.zzv(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new VideoCapabilities(z, z2, z3, zArr, zArr2);
    }

    public final /* synthetic */ VideoCapabilities[] newArray(int i) {
        return new VideoCapabilities[i];
    }
}
