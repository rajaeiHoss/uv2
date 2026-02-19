package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;

public final class zzag implements Parcelable.Creator<LocationSettingsRequest> {
    public final /* synthetic */ LocationSettingsRequest createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        boolean z = false;
        ArrayList<LocationRequest> arrayList = null;
        zzae zzae = null;
        boolean z2 = false;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                arrayList = zzbgm.zzc(parcel, readInt, LocationRequest.CREATOR);
            } else if (i == 2) {
                z = zzbgm.zzc(parcel, readInt);
            } else if (i == 3) {
                z2 = zzbgm.zzc(parcel, readInt);
            } else if (i != 5) {
                zzbgm.zzb(parcel, readInt);
            } else {
                zzae = (zzae) zzbgm.zza(parcel, readInt, zzae.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new LocationSettingsRequest(arrayList, z, z2, zzae);
    }

    public final /* synthetic */ LocationSettingsRequest[] newArray(int i) {
        return new LocationSettingsRequest[i];
    }
}
