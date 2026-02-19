package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzi implements Parcelable.Creator<LoyaltyPoints> {
    public final /* synthetic */ LoyaltyPoints createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        LoyaltyPointsBalance loyaltyPointsBalance = null;
        TimeInterval timeInterval = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i == 3) {
                loyaltyPointsBalance = (LoyaltyPointsBalance) zzbgm.zza(parcel, readInt, LoyaltyPointsBalance.CREATOR);
            } else if (i != 5) {
                zzbgm.zzb(parcel, readInt);
            } else {
                timeInterval = (TimeInterval) zzbgm.zza(parcel, readInt, TimeInterval.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new LoyaltyPoints(str, loyaltyPointsBalance, timeInterval);
    }

    public final /* synthetic */ LoyaltyPoints[] newArray(int i) {
        return new LoyaltyPoints[i];
    }
}
