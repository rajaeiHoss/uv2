package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzm implements Parcelable.Creator<FullWalletRequest> {
    public final /* synthetic */ FullWalletRequest createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        String str2 = null;
        Cart cart = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i == 3) {
                str2 = zzbgm.zzq(parcel, readInt);
            } else if (i != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                cart = (Cart) zzbgm.zza(parcel, readInt, Cart.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new FullWalletRequest(str, str2, cart);
    }

    public final /* synthetic */ FullWalletRequest[] newArray(int i) {
        return new FullWalletRequest[i];
    }
}
