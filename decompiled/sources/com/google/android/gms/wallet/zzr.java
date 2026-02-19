package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;

public final class zzr implements Parcelable.Creator<IsReadyToPayRequest> {
    public final /* synthetic */ IsReadyToPayRequest createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        ArrayList<Integer> arrayList = null;
        String str = null;
        String str2 = null;
        ArrayList<Integer> arrayList2 = null;
        boolean z = false;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                arrayList = zzbgm.zzab(parcel, readInt);
            } else if (i == 4) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i == 5) {
                str2 = zzbgm.zzq(parcel, readInt);
            } else if (i == 6) {
                arrayList2 = zzbgm.zzab(parcel, readInt);
            } else if (i != 7) {
                zzbgm.zzb(parcel, readInt);
            } else {
                z = zzbgm.zzc(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new IsReadyToPayRequest(arrayList, str, str2, arrayList2, z);
    }

    public final /* synthetic */ IsReadyToPayRequest[] newArray(int i) {
        return new IsReadyToPayRequest[i];
    }
}
