package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzq implements Parcelable.Creator<PublicKeyCredentialUserEntity> {
    public final /* synthetic */ PublicKeyCredentialUserEntity createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i == 3) {
                str2 = zzbgm.zzq(parcel, readInt);
            } else if (i == 4) {
                str3 = zzbgm.zzq(parcel, readInt);
            } else if (i != 5) {
                zzbgm.zzb(parcel, readInt);
            } else {
                str4 = zzbgm.zzq(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new PublicKeyCredentialUserEntity(str, str2, str3, str4);
    }

    public final /* synthetic */ PublicKeyCredentialUserEntity[] newArray(int i) {
        return new PublicKeyCredentialUserEntity[i];
    }
}
