package com.google.android.gms.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;

public final class zzcey extends zzbgl {
    public static final Parcelable.Creator<zzcey> CREATOR = new zzcez();
    private final String zzgog;
    private final int zzipw;
    @Deprecated
    private final Account[] zzipx;
    private final boolean zzipy;

    zzcey(int i, String str, Account[] accountArr, boolean z) {
        this.zzipw = i;
        this.zzgog = str;
        this.zzipx = accountArr;
        this.zzipy = z;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, this.zzipw);
        zzbgo.zza(parcel, 3, this.zzgog, false);
        zzbgo.zza(parcel, 4, this.zzipx, i, false);
        zzbgo.zza(parcel, 5, this.zzipy);
        zzbgo.zzai(parcel, zze);
    }
}
