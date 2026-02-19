package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzc implements Parcelable.Creator<zzb> {
    public final /* synthetic */ zzb createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        zze zze = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                zze = (zze) zzbgm.zza(parcel, readInt, zze.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzb(str, zze);
    }

    public final /* synthetic */ zzb[] newArray(int i) {
        return new zzb[i];
    }
}
