package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public class AuthenticationExtensions extends zzbgl {
    public static final Parcelable.Creator<AuthenticationExtensions> CREATOR = new zzc();

    public boolean equals(Object obj) {
        return this == obj || (obj instanceof AuthenticationExtensions);
    }

    public int hashCode() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzbgo.zzai(parcel, zzbgo.zze(parcel));
    }
}
