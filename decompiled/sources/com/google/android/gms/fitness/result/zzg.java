package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.Subscription;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;

public final class zzg implements Parcelable.Creator<ListSubscriptionsResult> {
    public final /* synthetic */ ListSubscriptionsResult createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        ArrayList<Subscription> arrayList = null;
        Status status = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                arrayList = zzbgm.zzc(parcel, readInt, Subscription.CREATOR);
            } else if (i != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                status = (Status) zzbgm.zza(parcel, readInt, Status.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new ListSubscriptionsResult(arrayList, status);
    }

    public final /* synthetic */ ListSubscriptionsResult[] newArray(int i) {
        return new ListSubscriptionsResult[i];
    }
}
