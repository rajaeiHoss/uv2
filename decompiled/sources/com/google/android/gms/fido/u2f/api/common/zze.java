package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable;

final class zze implements Parcelable.Creator<ErrorCode> {
    zze() {
    }

    public final /* synthetic */ ErrorCode createFromParcel(Parcel parcel) {
        return ErrorCode.toErrorCode(parcel.readInt());
    }

    public final /* synthetic */ ErrorCode[] newArray(int i) {
        return new ErrorCode[i];
    }
}
