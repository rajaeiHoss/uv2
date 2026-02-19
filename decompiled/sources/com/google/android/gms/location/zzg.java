package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;

public final class zzg implements Parcelable.Creator<ActivityTransitionResult> {
    public final /* synthetic */ ActivityTransitionResult createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        ArrayList<ActivityTransitionEvent> arrayList = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 1) {
                zzbgm.zzb(parcel, readInt);
            } else {
                arrayList = zzbgm.zzc(parcel, readInt, ActivityTransitionEvent.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new ActivityTransitionResult(arrayList);
    }

    public final /* synthetic */ ActivityTransitionResult[] newArray(int i) {
        return new ActivityTransitionResult[i];
    }
}
