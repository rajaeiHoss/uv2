package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;

public enum UserVerificationRequirement implements Parcelable {
    USER_VERIFICATION_REQUIRED("user-verification-required"),
    USER_VERIFICATION_NOT_REQUIRED("user-verification-not-required");
    
    public static final Parcelable.Creator<UserVerificationRequirement> CREATOR = new zzu();
    private final String mValue;

    public static class UnsupportedUserVerificationRequirementException extends Exception {
        public UnsupportedUserVerificationRequirementException(String str) {
            super(String.format("User verification requirement %s not supported", new Object[]{str}));
        }
    }

    private UserVerificationRequirement(String str) {
        this.mValue = str;
    }

    public static UserVerificationRequirement fromString(String str) throws UnsupportedUserVerificationRequirementException {
        for (UserVerificationRequirement userVerificationRequirement : values()) {
            if (str.equals(userVerificationRequirement.mValue)) {
                return userVerificationRequirement;
            }
        }
        throw new UnsupportedUserVerificationRequirementException(str);
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
