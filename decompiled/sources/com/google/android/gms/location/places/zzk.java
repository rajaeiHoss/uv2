package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.internal.zzbgm;

public final class zzk implements Parcelable.Creator<PlacePhotoResult> {
    public final /* synthetic */ PlacePhotoResult createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        Status status = null;
        BitmapTeleporter bitmapTeleporter = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                status = (Status) zzbgm.zza(parcel, readInt, Status.CREATOR);
            } else if (i != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                bitmapTeleporter = (BitmapTeleporter) zzbgm.zza(parcel, readInt, BitmapTeleporter.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new PlacePhotoResult(status, bitmapTeleporter);
    }

    public final /* synthetic */ PlacePhotoResult[] newArray(int i) {
        return new PlacePhotoResult[i];
    }
}
