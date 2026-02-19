package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import com.google.firebase.auth.PhoneAuthProvider;

public final class zzc implements Parcelable.Creator<PhoneAuthProvider.ForceResendingToken> {
    public final PhoneAuthProvider.ForceResendingToken createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        while (parcel.dataPosition() < zzd) {
            zzbgm.zzb(parcel, parcel.readInt());
        }
        zzbgm.zzaf(parcel, zzd);
        return new PhoneAuthProvider.ForceResendingToken();
    }

    public final PhoneAuthProvider.ForceResendingToken[] newArray(int i) {
        return new PhoneAuthProvider.ForceResendingToken[i];
    }
}
