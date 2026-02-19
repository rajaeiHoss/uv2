package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;

public enum AlgorithmIdentifier implements Parcelable {
    ECDSA("ECDSA using P-256 curve and SHA-256");
    
    public static final Parcelable.Creator<AlgorithmIdentifier> CREATOR = new zza();
    private final String mValue;

    public static class UnsupportedAlgorithmIdentifierException extends Exception {
        public UnsupportedAlgorithmIdentifierException(String str) {
            super(String.format("AlgorithmIdentifier %s not supported", new Object[]{str}));
        }
    }

    private AlgorithmIdentifier(String str) {
        this.mValue = str;
    }

    public static AlgorithmIdentifier fromString(String str) throws UnsupportedAlgorithmIdentifierException {
        for (AlgorithmIdentifier algorithmIdentifier : values()) {
            if (str.equals(algorithmIdentifier.mValue)) {
                return algorithmIdentifier;
            }
        }
        throw new UnsupportedAlgorithmIdentifierException(str);
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
