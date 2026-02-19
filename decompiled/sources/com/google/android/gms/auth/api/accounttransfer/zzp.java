package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;

public final class zzp implements Parcelable.Creator<zzo> {
    public final zzo createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        ArrayList<String> arrayList = null;
        ArrayList<String> arrayList2 = null;
        ArrayList<String> arrayList3 = null;
        ArrayList<String> arrayList4 = null;
        ArrayList<String> arrayList5 = null;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = zzbgm.zzg(parcel, readInt);
                    break;
                case 2:
                    arrayList = zzbgm.zzac(parcel, readInt);
                    break;
                case 3:
                    arrayList2 = zzbgm.zzac(parcel, readInt);
                    break;
                case 4:
                    arrayList3 = zzbgm.zzac(parcel, readInt);
                    break;
                case 5:
                    arrayList4 = zzbgm.zzac(parcel, readInt);
                    break;
                case 6:
                    arrayList5 = zzbgm.zzac(parcel, readInt);
                    break;
                default:
                    zzbgm.zzb(parcel, readInt);
                    break;
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzo(i, arrayList, arrayList2, arrayList3, arrayList4, arrayList5);
    }

    public final zzo[] newArray(int i) {
        return new zzo[i];
    }
}
