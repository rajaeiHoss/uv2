package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public enum ErrorCode implements Parcelable {
    OK(0),
    OTHER_ERROR(1),
    BAD_REQUEST(2),
    CONFIGURATION_UNSUPPORTED(3),
    DEVICE_INELIGIBLE(4),
    TIMEOUT(5);
    
    public static final Parcelable.Creator<ErrorCode> CREATOR = new zze();
    private static final String TAG = ErrorCode.class.getSimpleName();
    private final int zzhey;

    private ErrorCode(int i) {
        this.zzhey = i;
    }

    public static ErrorCode toErrorCode(int i) {
        for (ErrorCode errorCode : values()) {
            if (i == errorCode.zzhey) {
                return errorCode;
            }
        }
        Log.d(TAG, String.format("Unsupported error code: %d", new Object[]{Integer.valueOf(i)}));
        return OTHER_ERROR;
    }

    public final int describeContents() {
        return 0;
    }

    public final int getCode() {
        return this.zzhey;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.zzhey);
    }
}
