package com.google.android.gms.people.protomodel;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;

public final class zzb implements Parcelable.Creator<zzc> {
    public final /* synthetic */ zzc createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        ArrayList<zzh> arrayList = null;
        String str2 = null;
        Long l = null;
        Long l2 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i == 3) {
                arrayList = zzbgm.zzc(parcel, readInt, zzh.CREATOR);
            } else if (i == 4) {
                str2 = zzbgm.zzq(parcel, readInt);
            } else if (i == 5) {
                l = zzbgm.zzj(parcel, readInt);
            } else if (i != 6) {
                zzbgm.zzb(parcel, readInt);
            } else {
                l2 = zzbgm.zzj(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzc(str, arrayList, str2, l, l2);
    }

    public final /* synthetic */ zzc[] newArray(int i) {
        return new zzc[i];
    }
}
