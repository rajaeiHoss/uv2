package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public final class zzbmo implements Parcelable.Creator<zzbmn> {
    public final zzbmn createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        DriveId driveId = null;
        MetadataBundle metadataBundle = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                driveId = (DriveId) zzbgm.zza(parcel, readInt, DriveId.CREATOR);
            } else if (i != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                metadataBundle = (MetadataBundle) zzbgm.zza(parcel, readInt, MetadataBundle.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbmn(driveId, metadataBundle);
    }

    public final zzbmn[] newArray(int i) {
        return new zzbmn[i];
    }
}
