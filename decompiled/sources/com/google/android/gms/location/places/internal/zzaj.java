package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzaj implements Parcelable.Creator<zzai> {
    public final /* synthetic */ zzai createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        PlaceEntity placeEntity = null;
        float f = 0.0f;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                placeEntity = (PlaceEntity) zzbgm.zza(parcel, readInt, PlaceEntity.CREATOR);
            } else if (i != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                f = zzbgm.zzl(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzai(placeEntity, f);
    }

    public final /* synthetic */ zzai[] newArray(int i) {
        return new zzai[i];
    }
}
