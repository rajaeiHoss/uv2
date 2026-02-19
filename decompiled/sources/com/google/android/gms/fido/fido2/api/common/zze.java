package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zze implements Parcelable.Creator<AuthenticatorAttestationResponse> {
    public final /* synthetic */ AuthenticatorAttestationResponse createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        byte[] bArr = null;
        byte[] bArr2 = null;
        byte[] bArr3 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                bArr = zzbgm.zzt(parcel, readInt);
            } else if (i == 3) {
                bArr2 = zzbgm.zzt(parcel, readInt);
            } else if (i != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                bArr3 = zzbgm.zzt(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new AuthenticatorAttestationResponse(bArr, bArr2, bArr3);
    }

    public final /* synthetic */ AuthenticatorAttestationResponse[] newArray(int i) {
        return new AuthenticatorAttestationResponse[i];
    }
}
