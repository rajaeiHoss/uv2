package com.google.android.gms.wallet.fragment;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzg implements Parcelable.Creator<WalletFragmentStyle> {
    public final /* synthetic */ WalletFragmentStyle createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        Bundle bundle = null;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 2) {
                bundle = zzbgm.zzs(parcel, readInt);
            } else if (i2 != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                i = zzbgm.zzg(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new WalletFragmentStyle(bundle, i);
    }

    public final /* synthetic */ WalletFragmentStyle[] newArray(int i) {
        return new WalletFragmentStyle[i];
    }
}
