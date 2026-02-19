package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.fido2.api.common.UserVerificationRequirement;

final class zzu implements Parcelable.Creator<UserVerificationRequirement> {
    zzu() {
    }

    private static UserVerificationRequirement zzl(Parcel parcel) {
        try {
            return UserVerificationRequirement.fromString(parcel.readString());
        } catch (UserVerificationRequirement.UnsupportedUserVerificationRequirementException e) {
            throw new RuntimeException(e);
        }
    }

    public final /* synthetic */ UserVerificationRequirement createFromParcel(Parcel parcel) {
        return zzl(parcel);
    }

    public final /* synthetic */ UserVerificationRequirement[] newArray(int i) {
        return new UserVerificationRequirement[i];
    }
}
