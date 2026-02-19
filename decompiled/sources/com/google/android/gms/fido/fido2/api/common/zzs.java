package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.fido2.api.common.TokenBindingIdValue;

final class zzs implements Parcelable.Creator<TokenBindingIdValue.TokenBindingIdValueType> {
    zzs() {
    }

    private static TokenBindingIdValue.TokenBindingIdValueType zzk(Parcel parcel) {
        try {
            return TokenBindingIdValue.toTokenBindingIdValueType(parcel.readInt());
        } catch (TokenBindingIdValue.UnsupportedTokenBindingIdValueTypeException e) {
            throw new RuntimeException(e);
        }
    }

    public final /* synthetic */ TokenBindingIdValue.TokenBindingIdValueType createFromParcel(Parcel parcel) {
        return zzk(parcel);
    }

    public final /* synthetic */ TokenBindingIdValue.TokenBindingIdValueType[] newArray(int i) {
        return new TokenBindingIdValue.TokenBindingIdValueType[i];
    }
}
