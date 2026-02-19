package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.fido2.api.common.ErrorCode;

final class zzj implements Parcelable.Creator<ErrorCode> {
    zzj() {
    }

    private static ErrorCode zzi(Parcel parcel) {
        try {
            return ErrorCode.toErrorCode(parcel.readInt());
        } catch (ErrorCode.UnsupportedErrorCodeException e) {
            throw new RuntimeException(e);
        }
    }

    public final /* synthetic */ ErrorCode createFromParcel(Parcel parcel) {
        return zzi(parcel);
    }

    public final /* synthetic */ ErrorCode[] newArray(int i) {
        return new ErrorCode[i];
    }
}
