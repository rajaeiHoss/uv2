package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzd implements Parcelable.Creator<AuthenticatorAssertionResponse> {
    public final /* synthetic */ AuthenticatorAssertionResponse createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        byte[] bArr = null;
        byte[] bArr2 = null;
        byte[] bArr3 = null;
        byte[] bArr4 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                bArr = zzbgm.zzt(parcel, readInt);
            } else if (i == 3) {
                bArr2 = zzbgm.zzt(parcel, readInt);
            } else if (i == 4) {
                bArr3 = zzbgm.zzt(parcel, readInt);
            } else if (i != 5) {
                zzbgm.zzb(parcel, readInt);
            } else {
                bArr4 = zzbgm.zzt(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new AuthenticatorAssertionResponse(bArr, bArr2, bArr3, bArr4);
    }

    public final /* synthetic */ AuthenticatorAssertionResponse[] newArray(int i) {
        return new AuthenticatorAssertionResponse[i];
    }
}
