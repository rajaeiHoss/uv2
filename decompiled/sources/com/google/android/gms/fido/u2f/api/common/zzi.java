package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzi implements Parcelable.Creator<RegisterRequest> {
    public final /* synthetic */ RegisterRequest createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        byte[] bArr = null;
        String str2 = null;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 1) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i2 == 2) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i2 == 3) {
                bArr = zzbgm.zzt(parcel, readInt);
            } else if (i2 != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                str2 = zzbgm.zzq(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new RegisterRequest(i, str, bArr, str2);
    }

    public final /* synthetic */ RegisterRequest[] newArray(int i) {
        return new RegisterRequest[i];
    }
}
