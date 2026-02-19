package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public final class zzbsp implements Parcelable.Creator<zzbso> {
    public final zzbso createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        MetadataBundle metadataBundle = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                metadataBundle = (MetadataBundle) zzbgm.zza(parcel, readInt, MetadataBundle.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbso(metadataBundle);
    }

    public final zzbso[] newArray(int i) {
        return new zzbso[i];
    }
}
