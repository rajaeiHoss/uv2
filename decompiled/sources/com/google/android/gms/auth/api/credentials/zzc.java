package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzc implements Parcelable.Creator<CredentialPickerConfig> {
    public final CredentialPickerConfig createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        int i2 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i3 = 65535 & readInt;
            if (i3 == 1) {
                z = zzbgm.zzc(parcel, readInt);
            } else if (i3 == 2) {
                z2 = zzbgm.zzc(parcel, readInt);
            } else if (i3 == 3) {
                z3 = zzbgm.zzc(parcel, readInt);
            } else if (i3 == 4) {
                i2 = zzbgm.zzg(parcel, readInt);
            } else if (i3 != 1000) {
                zzbgm.zzb(parcel, readInt);
            } else {
                i = zzbgm.zzg(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new CredentialPickerConfig(i, z, z2, z3, i2);
    }

    public final CredentialPickerConfig[] newArray(int i) {
        return new CredentialPickerConfig[i];
    }
}
