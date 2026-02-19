package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.internal.zzbgm;

public final class zzq implements Parcelable.Creator<zzp> {
    public final /* synthetic */ zzp createFromParcel(Parcel parcel) {
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
        return new zzp(metadataBundle);
    }

    public final /* synthetic */ zzp[] newArray(int i) {
        return new zzp[i];
    }
}
