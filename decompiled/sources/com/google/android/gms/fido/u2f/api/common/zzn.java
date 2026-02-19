package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzn implements Parcelable.Creator<SignResponseData> {
    public final /* synthetic */ SignResponseData createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        byte[] bArr = null;
        String str = null;
        byte[] bArr2 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                bArr = zzbgm.zzt(parcel, readInt);
            } else if (i == 3) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                bArr2 = zzbgm.zzt(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new SignResponseData(bArr, str, bArr2);
    }

    public final /* synthetic */ SignResponseData[] newArray(int i) {
        return new SignResponseData[i];
    }
}
