package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.common.Transport;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;

public final class zzg implements Parcelable.Creator<KeyHandle> {
    public final /* synthetic */ KeyHandle createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        byte[] bArr = null;
        String str = null;
        ArrayList<Transport> arrayList = null;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 1) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i2 == 2) {
                bArr = zzbgm.zzt(parcel, readInt);
            } else if (i2 == 3) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i2 != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                arrayList = zzbgm.zzc(parcel, readInt, Transport.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new KeyHandle(i, bArr, str, arrayList);
    }

    public final /* synthetic */ KeyHandle[] newArray(int i) {
        return new KeyHandle[i];
    }
}
