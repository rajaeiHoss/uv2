package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.internal.zzbgm;

public final class zzo implements Parcelable.Creator<zzn> {
    public final /* synthetic */ zzn createFromParcel(Parcel parcel) {
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
        return new zzn(metadataBundle);
    }

    public final /* synthetic */ zzn[] newArray(int i) {
        return new zzn[i];
    }
}
