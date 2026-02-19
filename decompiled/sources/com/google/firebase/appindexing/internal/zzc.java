package com.google.firebase.appindexing.internal;

import android.os.Parcelable;

public final class zzc implements Parcelable.Creator<zza> {
    public final zza createFromParcel(android.os.Parcel parcel) {
        int end = com.google.android.gms.internal.zzbgm.zzd(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        zzb zzb = null;
        String str5 = null;
        while (parcel.dataPosition() < end) {
            int readInt = parcel.readInt();
            int fieldId = 65535 & readInt;
            if (fieldId == 1) {
                str = com.google.android.gms.internal.zzbgm.zzq(parcel, readInt);
            } else if (fieldId == 2) {
                str2 = com.google.android.gms.internal.zzbgm.zzq(parcel, readInt);
            } else if (fieldId == 3) {
                str3 = com.google.android.gms.internal.zzbgm.zzq(parcel, readInt);
            } else if (fieldId == 4) {
                str4 = com.google.android.gms.internal.zzbgm.zzq(parcel, readInt);
            } else if (fieldId == 5) {
                zzb = (zzb) com.google.android.gms.internal.zzbgm.zza(parcel, readInt, zzb.CREATOR);
            } else if (fieldId != 6) {
                com.google.android.gms.internal.zzbgm.zzb(parcel, readInt);
            } else {
                str5 = com.google.android.gms.internal.zzbgm.zzq(parcel, readInt);
            }
        }
        com.google.android.gms.internal.zzbgm.zzaf(parcel, end);
        return new zza(str, str2, str3, str4, zzb, str5);
    }

    public final zza[] newArray(int i) {
        return new zza[i];
    }
}
