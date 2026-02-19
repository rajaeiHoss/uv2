package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.nearby.connection.PayloadTransferUpdate;

public final class zzcts implements Parcelable.Creator<zzctr> {
    public final zzctr createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        PayloadTransferUpdate payloadTransferUpdate = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                payloadTransferUpdate = (PayloadTransferUpdate) zzbgm.zza(parcel, readInt, PayloadTransferUpdate.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzctr(str, payloadTransferUpdate);
    }

    public final zzctr[] newArray(int i) {
        return new zzctr[i];
    }
}
