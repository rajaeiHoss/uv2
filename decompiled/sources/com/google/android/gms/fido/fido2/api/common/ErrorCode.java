package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;

public enum ErrorCode implements Parcelable {
    CTAP2_ERR_CREDENTIAL_EXCLUDED(1),
    CTAP2_ERR_UNSUPPORTED_ALGORITHM(2),
    CTAP2_ERR_OPERATION_DENIED(3),
    CTAP2_ERR_OPTION_NOT_SUPPORTED(4),
    CTAP2_ERR_DEVICE_NO_SCREEN_LOCK(5);
    
    public static final Parcelable.Creator<ErrorCode> CREATOR = new zzj();
    private final int zzhey;

    public static class UnsupportedErrorCodeException extends Exception {
        public UnsupportedErrorCodeException(int i) {
            super(String.format("Error code %d is not supported", new Object[]{Integer.valueOf(i)}));
        }
    }

    private ErrorCode(int i) {
        this.zzhey = i;
    }

    public static ErrorCode toErrorCode(int i) throws UnsupportedErrorCodeException {
        for (ErrorCode errorCode : values()) {
            if (i == errorCode.zzhey) {
                return errorCode;
            }
        }
        throw new UnsupportedErrorCodeException(i);
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
