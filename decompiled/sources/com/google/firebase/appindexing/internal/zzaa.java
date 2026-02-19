package com.google.firebase.appindexing.internal;

import android.os.Parcelable;

public final class zzaa implements Parcelable.Creator<Thing> {
    public final Thing createFromParcel(android.os.Parcel parcel) {
        int end = com.google.android.gms.internal.zzbgm.zzd(parcel);
        android.os.Bundle bundle = null;
        Thing.zza zza = null;
        String str = null;
        String str2 = null;
        int i = 0;
        while (parcel.dataPosition() < end) {
            int readInt = parcel.readInt();
            int fieldId = 65535 & readInt;
            if (fieldId == 1) {
                bundle = com.google.android.gms.internal.zzbgm.zzs(parcel, readInt);
            } else if (fieldId == 2) {
                zza = (Thing.zza) com.google.android.gms.internal.zzbgm.zza(parcel, readInt, Thing.zza.CREATOR);
            } else if (fieldId == 3) {
                str = com.google.android.gms.internal.zzbgm.zzq(parcel, readInt);
            } else if (fieldId == 4) {
                str2 = com.google.android.gms.internal.zzbgm.zzq(parcel, readInt);
            } else if (fieldId != 1000) {
                com.google.android.gms.internal.zzbgm.zzb(parcel, readInt);
            } else {
                i = com.google.android.gms.internal.zzbgm.zzg(parcel, readInt);
            }
        }
        com.google.android.gms.internal.zzbgm.zzaf(parcel, end);
        return new Thing(i, bundle, zza, str, str2);
    }

    public final Thing[] newArray(int i) {
        return new Thing[i];
    }
}
