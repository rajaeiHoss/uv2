package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.zzc;
import com.google.android.gms.internal.zzbgm;

public final class zzb implements Parcelable.Creator<zza> {
    public final /* synthetic */ zza createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        zzc zzc = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 1) {
                zzbgm.zzb(parcel, readInt);
            } else {
                zzc = (zzc) zzbgm.zza(parcel, readInt, zzc.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zza(zzc);
    }

    public final /* synthetic */ zza[] newArray(int i) {
        return new zza[i];
    }
}
