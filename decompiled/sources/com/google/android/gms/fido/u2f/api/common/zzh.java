package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.u2f.api.common.ProtocolVersion;

final class zzh implements Parcelable.Creator<ProtocolVersion> {
    zzh() {
    }

    private static ProtocolVersion zzn(Parcel parcel) {
        try {
            return ProtocolVersion.fromString(parcel.readString());
        } catch (ProtocolVersion.UnsupportedProtocolException e) {
            throw new RuntimeException(e);
        }
    }

    public final /* synthetic */ ProtocolVersion createFromParcel(Parcel parcel) {
        return zzn(parcel);
    }

    public final /* synthetic */ ProtocolVersion[] newArray(int i) {
        return new ProtocolVersion[i];
    }
}
