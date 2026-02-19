package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzc implements Parcelable.Creator<AuthenticationExtensions> {
    public final /* synthetic */ AuthenticationExtensions createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        while (parcel.dataPosition() < zzd) {
            zzbgm.zzb(parcel, parcel.readInt());
        }
        zzbgm.zzaf(parcel, zzd);
        return new AuthenticationExtensions();
    }

    public final /* synthetic */ AuthenticationExtensions[] newArray(int i) {
        return new AuthenticationExtensions[i];
    }
}
