package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.internal.zzbgm;

public final class zze implements Parcelable.Creator<zzd> {
    public final /* synthetic */ zzd createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        MetadataBundle metadataBundle = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 1) {
                zzbgm.zzb(parcel, readInt);
            } else {
                metadataBundle = (MetadataBundle) zzbgm.zza(parcel, readInt, MetadataBundle.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzd(metadataBundle);
    }

    public final /* synthetic */ zzd[] newArray(int i) {
        return new zzd[i];
    }
}
