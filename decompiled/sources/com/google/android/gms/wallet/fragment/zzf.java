package com.google.android.gms.wallet.fragment;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzf implements Parcelable.Creator<WalletFragmentOptions> {
    public final /* synthetic */ WalletFragmentOptions createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 1;
        WalletFragmentStyle walletFragmentStyle = null;
        int i2 = 1;
        int i3 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i4 = 65535 & readInt;
            if (i4 == 2) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i4 == 3) {
                i3 = zzbgm.zzg(parcel, readInt);
            } else if (i4 == 4) {
                walletFragmentStyle = (WalletFragmentStyle) zzbgm.zza(parcel, readInt, WalletFragmentStyle.CREATOR);
            } else if (i4 != 5) {
                zzbgm.zzb(parcel, readInt);
            } else {
                i2 = zzbgm.zzg(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new WalletFragmentOptions(i, i3, walletFragmentStyle, i2);
    }

    public final /* synthetic */ WalletFragmentOptions[] newArray(int i) {
        return new WalletFragmentOptions[i];
    }
}
