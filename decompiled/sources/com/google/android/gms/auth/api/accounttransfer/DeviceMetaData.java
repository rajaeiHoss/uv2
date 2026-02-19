package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public class DeviceMetaData extends zzbgl {
    public static final Parcelable.Creator<DeviceMetaData> CREATOR = new zzv();
    private int zzehz;
    private boolean zzekf;
    private long zzekg;
    private final boolean zzekh;

    DeviceMetaData(int i, boolean z, long j, boolean z2) {
        this.zzehz = i;
        this.zzekf = z;
        this.zzekg = j;
        this.zzekh = z2;
    }

    public long getMinAgeOfLockScreen() {
        return this.zzekg;
    }

    public boolean isChallengeAllowed() {
        return this.zzekh;
    }

    public boolean isLockScreenSolved() {
        return this.zzekf;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.zzehz);
        zzbgo.zza(parcel, 2, isLockScreenSolved());
        zzbgo.zza(parcel, 3, getMinAgeOfLockScreen());
        zzbgo.zza(parcel, 4, isChallengeAllowed());
        zzbgo.zzai(parcel, zze);
    }
}
