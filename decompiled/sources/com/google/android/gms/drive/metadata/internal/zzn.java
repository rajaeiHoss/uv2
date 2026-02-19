package com.google.android.gms.drive.metadata.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;

public final class zzn implements Parcelable.Creator<ParentDriveIdSet> {
    public final /* synthetic */ ParentDriveIdSet createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        ArrayList<zzq> arrayList = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                arrayList = zzbgm.zzc(parcel, readInt, zzq.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new ParentDriveIdSet(arrayList);
    }

    public final /* synthetic */ ParentDriveIdSet[] newArray(int i) {
        return new ParentDriveIdSet[i];
    }
}
