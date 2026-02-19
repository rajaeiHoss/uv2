package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzak implements Parcelable.Creator<ProxyCard> {
    public final /* synthetic */ ProxyCard createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i3 = 65535 & readInt;
            if (i3 == 2) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i3 == 3) {
                str2 = zzbgm.zzq(parcel, readInt);
            } else if (i3 == 4) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i3 != 5) {
                zzbgm.zzb(parcel, readInt);
            } else {
                i2 = zzbgm.zzg(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new ProxyCard(str, str2, i, i2);
    }

    public final /* synthetic */ ProxyCard[] newArray(int i) {
        return new ProxyCard[i];
    }
}
