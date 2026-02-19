package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;

public enum PublicKeyCredentialType implements Parcelable {
    PUBLIC_KEY("public-key");
    
    public static final Parcelable.Creator<PublicKeyCredentialType> CREATOR = new zzp();
    private final String mValue;

    public static class UnsupportedPublicKeyCredTypeException extends Exception {
        public UnsupportedPublicKeyCredTypeException(String str) {
            super(String.format("PublicKeyCredentialType %s not supported", new Object[]{str}));
        }
    }

    private PublicKeyCredentialType(String str) {
        this.mValue = str;
    }

    public static PublicKeyCredentialType fromString(String str) throws UnsupportedPublicKeyCredTypeException {
        for (PublicKeyCredentialType publicKeyCredentialType : values()) {
            if (str.equals(publicKeyCredentialType.mValue)) {
                return publicKeyCredentialType;
            }
        }
        throw new UnsupportedPublicKeyCredTypeException(str);
    }

    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return this.mValue;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mValue);
    }
}
