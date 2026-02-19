package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.credentials.Credential;

public final class zzaxq implements Parcelable.Creator<zzaxp> {
    public final zzaxp createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        Credential credential = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 1) {
                zzbgm.zzb(parcel, readInt);
            } else {
                credential = (Credential) zzbgm.zza(parcel, readInt, Credential.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzaxp(credential);
    }

    public final zzaxp[] newArray(int i) {
        return new zzaxp[i];
    }
}
