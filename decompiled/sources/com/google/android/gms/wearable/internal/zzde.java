package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzde implements Parcelable.Creator<zzdd> {
    public final zzdd createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        Uri uri = null;
        Bundle bundle = null;
        byte[] bArr = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                uri = (Uri) zzbgm.zza(parcel, readInt, Uri.CREATOR);
            } else if (i == 4) {
                bundle = zzbgm.zzs(parcel, readInt);
            } else if (i != 5) {
                zzbgm.zzb(parcel, readInt);
            } else {
                bArr = zzbgm.zzt(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzdd(uri, bundle, bArr);
    }

    public final zzdd[] newArray(int i) {
        return new zzdd[i];
    }
}
