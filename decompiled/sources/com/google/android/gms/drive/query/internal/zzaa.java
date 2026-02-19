package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzaa implements Parcelable.Creator<zzz> {
    public final /* synthetic */ zzz createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        while (parcel.dataPosition() < zzd) {
            zzbgm.zzb(parcel, parcel.readInt());
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzz();
    }

    public final /* synthetic */ zzz[] newArray(int i) {
        return new zzz[i];
    }
}
