package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzl implements Parcelable.Creator<RegisteredKey> {
    public final /* synthetic */ RegisteredKey createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        KeyHandle keyHandle = null;
        String str = null;
        String str2 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                keyHandle = (KeyHandle) zzbgm.zza(parcel, readInt, KeyHandle.CREATOR);
            } else if (i == 3) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                str2 = zzbgm.zzq(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new RegisteredKey(keyHandle, str, str2);
    }

    public final /* synthetic */ RegisteredKey[] newArray(int i) {
        return new RegisteredKey[i];
    }
}
