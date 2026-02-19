package com.google.android.gms.auth.api.proxy;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zza implements Parcelable.Creator<ProxyRequest> {
    public final ProxyRequest createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        byte[] bArr = null;
        Bundle bundle = null;
        long j = 0;
        int i = 0;
        int i2 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i3 = 65535 & readInt;
            if (i3 == 1) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i3 == 2) {
                i2 = zzbgm.zzg(parcel, readInt);
            } else if (i3 == 3) {
                j = zzbgm.zzi(parcel, readInt);
            } else if (i3 == 4) {
                bArr = zzbgm.zzt(parcel, readInt);
            } else if (i3 == 5) {
                bundle = zzbgm.zzs(parcel, readInt);
            } else if (i3 != 1000) {
                zzbgm.zzb(parcel, readInt);
            } else {
                i = zzbgm.zzg(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new ProxyRequest(i, str, i2, j, bArr, bundle);
    }

    public final ProxyRequest[] newArray(int i) {
        return new ProxyRequest[i];
    }
}
