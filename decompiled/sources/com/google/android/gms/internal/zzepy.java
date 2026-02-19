package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.firebase.dynamiclinks.ShortDynamicLink;

public final class zzepy extends zzbgl implements ShortDynamicLink.Warning {
    public static final Parcelable.Creator<zzepy> CREATOR = new zzeqa();
    private final String message;

    public zzepy(String str) {
        this.message = str;
    }

    public final String getCode() {
        return null;
    }

    public final String getMessage() {
        return this.message;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, getMessage(), false);
        zzbgo.zzai(parcel, zze);
    }
}
