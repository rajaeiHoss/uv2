package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.u2f.api.common.ChannelIdValue;

final class zzb implements Parcelable.Creator<ChannelIdValue.ChannelIdValueType> {
    zzb() {
    }

    private static ChannelIdValue.ChannelIdValueType zzm(Parcel parcel) {
        try {
            return ChannelIdValue.toChannelIdValueType(parcel.readInt());
        } catch (ChannelIdValue.UnsupportedChannelIdValueTypeException e) {
            throw new RuntimeException(e);
        }
    }

    public final /* synthetic */ ChannelIdValue.ChannelIdValueType createFromParcel(Parcel parcel) {
        return zzm(parcel);
    }

    public final /* synthetic */ ChannelIdValue.ChannelIdValueType[] newArray(int i) {
        return new ChannelIdValue.ChannelIdValueType[i];
    }
}
