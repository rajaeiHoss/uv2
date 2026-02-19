package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzg implements Parcelable.Creator<AuthenticatorSelectionCriteria> {
    public final /* synthetic */ AuthenticatorSelectionCriteria createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        Boolean bool = null;
        String str2 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i == 3) {
                bool = zzbgm.zzd(parcel, readInt);
            } else if (i != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                str2 = zzbgm.zzq(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new AuthenticatorSelectionCriteria(str, bool, str2);
    }

    public final /* synthetic */ AuthenticatorSelectionCriteria[] newArray(int i) {
        return new AuthenticatorSelectionCriteria[i];
    }
}
