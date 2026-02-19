package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.query.Query;

public final class zzbth implements Parcelable.Creator<zzbtg> {
    public final zzbtg createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        Query query = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                query = (Query) zzbgm.zza(parcel, readInt, Query.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbtg(query);
    }

    public final zzbtg[] newArray(int i) {
        return new zzbtg[i];
    }
}
