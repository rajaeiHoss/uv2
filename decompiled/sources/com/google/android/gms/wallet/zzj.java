package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzj implements Parcelable.Creator<CreateWalletObjectsRequest> {
    public final /* synthetic */ CreateWalletObjectsRequest createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        LoyaltyWalletObject loyaltyWalletObject = null;
        OfferWalletObject offerWalletObject = null;
        GiftCardWalletObject giftCardWalletObject = null;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 2) {
                loyaltyWalletObject = (LoyaltyWalletObject) zzbgm.zza(parcel, readInt, LoyaltyWalletObject.CREATOR);
            } else if (i2 == 3) {
                offerWalletObject = (OfferWalletObject) zzbgm.zza(parcel, readInt, OfferWalletObject.CREATOR);
            } else if (i2 == 4) {
                giftCardWalletObject = (GiftCardWalletObject) zzbgm.zza(parcel, readInt, GiftCardWalletObject.CREATOR);
            } else if (i2 != 5) {
                zzbgm.zzb(parcel, readInt);
            } else {
                i = zzbgm.zzg(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new CreateWalletObjectsRequest(loyaltyWalletObject, offerWalletObject, giftCardWalletObject, i);
    }

    public final /* synthetic */ CreateWalletObjectsRequest[] newArray(int i) {
        return new CreateWalletObjectsRequest[i];
    }
}
