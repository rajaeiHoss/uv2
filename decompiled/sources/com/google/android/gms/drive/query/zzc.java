package com.google.android.gms.drive.query;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.query.internal.zzf;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;

public final class zzc implements Parcelable.Creator<SortOrder> {
    public final /* synthetic */ SortOrder createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        ArrayList<zzf> arrayList = null;
        boolean z = false;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                arrayList = zzbgm.zzc(parcel, readInt, zzf.CREATOR);
            } else if (i != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                z = zzbgm.zzc(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new SortOrder(arrayList, z);
    }

    public final /* synthetic */ SortOrder[] newArray(int i) {
        return new SortOrder[i];
    }
}
