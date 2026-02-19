package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.zzbgm;

public final class zzj implements Parcelable.Creator<PlacePhotoMetadataResult> {
    public final /* synthetic */ PlacePhotoMetadataResult createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        Status status = null;
        DataHolder dataHolder = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                status = (Status) zzbgm.zza(parcel, readInt, Status.CREATOR);
            } else if (i != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                dataHolder = (DataHolder) zzbgm.zza(parcel, readInt, DataHolder.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new PlacePhotoMetadataResult(status, dataHolder);
    }

    public final /* synthetic */ PlacePhotoMetadataResult[] newArray(int i) {
        return new PlacePhotoMetadataResult[i];
    }
}
