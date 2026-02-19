package com.google.android.gms.fido.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.common.Transport;

final class zza implements Parcelable.Creator<Transport> {
    zza() {
    }

    private static Transport zzf(Parcel parcel) {
        try {
            return Transport.fromString(parcel.readString());
        } catch (Transport.UnsupportedTransportException e) {
            throw new RuntimeException(e);
        }
    }

    public final /* synthetic */ Transport createFromParcel(Parcel parcel) {
        return zzf(parcel);
    }

    public final /* synthetic */ Transport[] newArray(int i) {
        return new Transport[i];
    }
}
