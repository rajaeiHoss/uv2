package com.google.android.gms.nearby.messages.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import com.google.android.gms.nearby.messages.Message;

public final class zzag implements Parcelable.Creator<zzaf> {
    public final /* synthetic */ zzaf createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 0;
        Message message = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 1) {
                message = (Message) zzbgm.zza(parcel, readInt, Message.CREATOR);
            } else if (i2 != 1000) {
                zzbgm.zzb(parcel, readInt);
            } else {
                i = zzbgm.zzg(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzaf(i, message);
    }

    public final /* synthetic */ zzaf[] newArray(int i) {
        return new zzaf[i];
    }
}
