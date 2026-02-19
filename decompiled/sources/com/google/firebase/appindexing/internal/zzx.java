package com.google.firebase.appindexing.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import com.google.firebase.appindexing.internal.Thing;

public final class zzx implements Parcelable.Creator<Thing.zza> {
    public final Thing.zza createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        Bundle bundle = null;
        boolean z = false;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 1) {
                z = zzbgm.zzc(parcel, readInt);
            } else if (i2 == 2) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i2 == 3) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i2 != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                bundle = zzbgm.zzs(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new Thing.zza(z, i, str, bundle);
    }

    public final Thing.zza[] newArray(int i) {
        return new Thing.zza[i];
    }
}
