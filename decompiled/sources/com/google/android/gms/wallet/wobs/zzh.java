package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzh implements Parcelable.Creator<LoyaltyPointsBalance> {
    public final /* synthetic */ LoyaltyPointsBalance createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        String str2 = null;
        double d = 0.0d;
        long j = 0;
        int i = 0;
        int i2 = -1;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    i = zzbgm.zzg(parcel2, readInt);
                    break;
                case 3:
                    str = zzbgm.zzq(parcel2, readInt);
                    break;
                case 4:
                    d = zzbgm.zzn(parcel2, readInt);
                    break;
                case 5:
                    str2 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 6:
                    j = zzbgm.zzi(parcel2, readInt);
                    break;
                case 7:
                    i2 = zzbgm.zzg(parcel2, readInt);
                    break;
                default:
                    zzbgm.zzb(parcel2, readInt);
                    break;
            }
        }
        zzbgm.zzaf(parcel2, zzd);
        return new LoyaltyPointsBalance(i, str, d, str2, j, i2);
    }

    public final /* synthetic */ LoyaltyPointsBalance[] newArray(int i) {
        return new LoyaltyPointsBalance[i];
    }
}
