package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import com.google.android.gms.internal.zzcfs;
import java.util.ArrayList;

public final class zzf implements Parcelable.Creator<ActivityTransitionRequest> {
    public final /* synthetic */ ActivityTransitionRequest createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        ArrayList<ActivityTransition> arrayList = null;
        String str = null;
        ArrayList<zzcfs> arrayList2 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                arrayList = zzbgm.zzc(parcel, readInt, ActivityTransition.CREATOR);
            } else if (i == 2) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                arrayList2 = zzbgm.zzc(parcel, readInt, zzcfs.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new ActivityTransitionRequest(arrayList, str, arrayList2);
    }

    public final /* synthetic */ ActivityTransitionRequest[] newArray(int i) {
        return new ActivityTransitionRequest[i];
    }
}
