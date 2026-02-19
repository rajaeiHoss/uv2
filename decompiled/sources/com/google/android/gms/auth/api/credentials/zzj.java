package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;

public final class zzj implements Parcelable.Creator<PasswordSpecification> {
    public final PasswordSpecification createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        ArrayList<String> arrayList = null;
        ArrayList<Integer> arrayList2 = null;
        int i = 0;
        int i2 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i3 = 65535 & readInt;
            if (i3 == 1) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i3 == 2) {
                arrayList = zzbgm.zzac(parcel, readInt);
            } else if (i3 == 3) {
                arrayList2 = zzbgm.zzab(parcel, readInt);
            } else if (i3 == 4) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i3 != 5) {
                zzbgm.zzb(parcel, readInt);
            } else {
                i2 = zzbgm.zzg(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new PasswordSpecification(str, arrayList, arrayList2, i, i2);
    }

    public final PasswordSpecification[] newArray(int i) {
        return new PasswordSpecification[i];
    }
}
