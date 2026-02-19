package com.google.android.gms.identity.intents;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.identity.intents.model.CountrySpecification;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;

public final class zzd implements Parcelable.Creator<UserAddressRequest> {
    public final UserAddressRequest createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        ArrayList<CountrySpecification> arrayList = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                arrayList = zzbgm.zzc(parcel, readInt, CountrySpecification.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new UserAddressRequest(arrayList);
    }

    public final UserAddressRequest[] newArray(int i) {
        return new UserAddressRequest[i];
    }
}
