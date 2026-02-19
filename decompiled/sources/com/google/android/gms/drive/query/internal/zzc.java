package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.internal.zzbgm;

public final class zzc implements Parcelable.Creator<zzb> {
    public final /* synthetic */ zzb createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        zzx zzx = null;
        MetadataBundle metadataBundle = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                zzx = (zzx) zzbgm.zza(parcel, readInt, zzx.CREATOR);
            } else if (i != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                metadataBundle = (MetadataBundle) zzbgm.zza(parcel, readInt, MetadataBundle.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzb(zzx, metadataBundle);
    }

    public final /* synthetic */ zzb[] newArray(int i) {
        return new zzb[i];
    }
}
