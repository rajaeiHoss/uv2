package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzn implements Parcelable.Creator<PublicKeyCredentialParameters> {
    public final /* synthetic */ PublicKeyCredentialParameters createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        String str2 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                str2 = zzbgm.zzq(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new PublicKeyCredentialParameters(str, str2);
    }

    public final /* synthetic */ PublicKeyCredentialParameters[] newArray(int i) {
        return new PublicKeyCredentialParameters[i];
    }
}
