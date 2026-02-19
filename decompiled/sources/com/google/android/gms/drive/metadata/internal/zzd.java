package com.google.android.gms.drive.metadata.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import com.google.android.gms.internal.zzbgm;

public final class zzd implements Parcelable.Creator<zzc> {
    public final /* synthetic */ zzc createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        CustomPropertyKey customPropertyKey = null;
        String str = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                customPropertyKey = (CustomPropertyKey) zzbgm.zza(parcel, readInt, CustomPropertyKey.CREATOR);
            } else if (i != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                str = zzbgm.zzq(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzc(customPropertyKey, str);
    }

    public final /* synthetic */ zzc[] newArray(int i) {
        return new zzc[i];
    }
}
