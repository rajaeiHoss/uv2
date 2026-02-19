package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialType;

final class zzp implements Parcelable.Creator<PublicKeyCredentialType> {
    zzp() {
    }

    private static PublicKeyCredentialType zzj(Parcel parcel) {
        try {
            return PublicKeyCredentialType.fromString(parcel.readString());
        } catch (PublicKeyCredentialType.UnsupportedPublicKeyCredTypeException e) {
            throw new RuntimeException(e);
        }
    }

    public final /* synthetic */ PublicKeyCredentialType createFromParcel(Parcel parcel) {
        return zzj(parcel);
    }

    public final /* synthetic */ PublicKeyCredentialType[] newArray(int i) {
        return new PublicKeyCredentialType[i];
    }
}
