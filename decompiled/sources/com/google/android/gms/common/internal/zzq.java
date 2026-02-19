package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;

final class zzq implements Parcelable.Creator<BinderWrapper> {
    zzq() {
    }

    public final /* synthetic */ BinderWrapper createFromParcel(Parcel parcel) {
        return new BinderWrapper(parcel, (zzq) null);
    }

    public final /* synthetic */ BinderWrapper[] newArray(int i) {
        return new BinderWrapper[i];
    }
}
