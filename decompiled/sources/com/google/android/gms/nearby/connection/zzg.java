package com.google.android.gms.nearby.connection;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzg implements Parcelable.Creator<DiscoveryOptions> {
    public final /* synthetic */ DiscoveryOptions createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        Strategy strategy = null;
        boolean z = false;
        boolean z2 = true;
        boolean z3 = true;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                strategy = (Strategy) zzbgm.zza(parcel, readInt, Strategy.CREATOR);
            } else if (i == 2) {
                z = zzbgm.zzc(parcel, readInt);
            } else if (i == 3) {
                z2 = zzbgm.zzc(parcel, readInt);
            } else if (i != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                z3 = zzbgm.zzc(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new DiscoveryOptions(strategy, z, z2, z3);
    }

    public final /* synthetic */ DiscoveryOptions[] newArray(int i) {
        return new DiscoveryOptions[i];
    }
}
