package com.google.android.gms.location;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import java.util.List;

public final class zzac implements Parcelable.Creator<LocationResult> {
    public final /* synthetic */ LocationResult createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        List<Location> list = LocationResult.zzisl;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 1) {
                zzbgm.zzb(parcel, readInt);
            } else {
                list = zzbgm.zzc(parcel, readInt, Location.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new LocationResult(list);
    }

    public final /* synthetic */ LocationResult[] newArray(int i) {
        return new LocationResult[i];
    }
}
