package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.fido2.api.common.AlgorithmIdentifier;

final class zza implements Parcelable.Creator<AlgorithmIdentifier> {
    zza() {
    }

    private static AlgorithmIdentifier zzg(Parcel parcel) {
        try {
            return AlgorithmIdentifier.fromString(parcel.readString());
        } catch (AlgorithmIdentifier.UnsupportedAlgorithmIdentifierException e) {
            throw new RuntimeException(e);
        }
    }

    public final /* synthetic */ AlgorithmIdentifier createFromParcel(Parcel parcel) {
        return zzg(parcel);
    }

    public final /* synthetic */ AlgorithmIdentifier[] newArray(int i) {
        return new AlgorithmIdentifier[i];
    }
}
