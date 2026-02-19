package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import com.google.android.gms.internal.zzblw;
import java.util.ArrayList;

public final class zzw implements Parcelable.Creator<zzv> {
    public final /* synthetic */ zzv createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        ArrayList<zzblw> arrayList = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                arrayList = zzbgm.zzc(parcel, readInt, zzblw.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzv(str, arrayList);
    }

    public final /* synthetic */ zzv[] newArray(int i) {
        return new zzv[i];
    }
}
