package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import com.google.android.gms.wallet.wobs.CommonWalletObject;

public final class zzab implements Parcelable.Creator<OfferWalletObject> {
    public final /* synthetic */ OfferWalletObject createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        String str2 = null;
        CommonWalletObject commonWalletObject = null;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 1) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i2 == 2) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i2 == 3) {
                str2 = zzbgm.zzq(parcel, readInt);
            } else if (i2 != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                commonWalletObject = (CommonWalletObject) zzbgm.zza(parcel, readInt, CommonWalletObject.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new OfferWalletObject(i, str, str2, commonWalletObject);
    }

    public final /* synthetic */ OfferWalletObject[] newArray(int i) {
        return new OfferWalletObject[i];
    }
}
