package com.google.android.gms.wearable;

import android.os.Parcelable;

public final class zzh implements Parcelable.Creator<PutDataRequest> {
    public final PutDataRequest createFromParcel(android.os.Parcel parcel) {
        int zzd = com.google.android.gms.internal.zzbgm.zzd(parcel);
        android.net.Uri uri = null;
        android.os.Bundle bundle = null;
        byte[] bArr = null;
        long j = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                uri = (android.net.Uri) com.google.android.gms.internal.zzbgm.zza(parcel, readInt, android.net.Uri.CREATOR);
            } else if (i == 4) {
                bundle = com.google.android.gms.internal.zzbgm.zzs(parcel, readInt);
            } else if (i == 5) {
                bArr = com.google.android.gms.internal.zzbgm.zzt(parcel, readInt);
            } else if (i != 6) {
                com.google.android.gms.internal.zzbgm.zzb(parcel, readInt);
            } else {
                j = com.google.android.gms.internal.zzbgm.zzi(parcel, readInt);
            }
        }
        com.google.android.gms.internal.zzbgm.zzaf(parcel, zzd);
        return new PutDataRequest(uri, bundle, bArr, j);
    }

    public final PutDataRequest[] newArray(int i) {
        return new PutDataRequest[i];
    }
}
