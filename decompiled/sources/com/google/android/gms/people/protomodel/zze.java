package com.google.android.gms.people.protomodel;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;

public final class zze implements Parcelable.Creator<FetchBackUpDeviceContactInfoResponseEntity> {
    public final /* synthetic */ FetchBackUpDeviceContactInfoResponseEntity createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        ArrayList<zzc> arrayList = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                arrayList = zzbgm.zzc(parcel, readInt, zzc.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new FetchBackUpDeviceContactInfoResponseEntity(arrayList);
    }

    public final /* synthetic */ FetchBackUpDeviceContactInfoResponseEntity[] newArray(int i) {
        return new FetchBackUpDeviceContactInfoResponseEntity[i];
    }
}
