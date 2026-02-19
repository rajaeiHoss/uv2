package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;

public final class zzaq implements Parcelable.Creator<zzan> {
    public final /* synthetic */ zzan createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        ArrayList<zzao> arrayList = null;
        ArrayList<zzap> arrayList2 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                arrayList = zzbgm.zzc(parcel, readInt, zzao.CREATOR);
            } else if (i != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                arrayList2 = zzbgm.zzc(parcel, readInt, zzap.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzan(arrayList, arrayList2);
    }

    public final /* synthetic */ zzan[] newArray(int i) {
        return new zzan[i];
    }
}
