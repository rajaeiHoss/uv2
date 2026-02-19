package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.DriveId;

public final class zzbtt extends zzbgl {
    public static final Parcelable.Creator<zzbtt> CREATOR = new zzbtu();
    private DriveId zzgss;

    public zzbtt(DriveId driveId) {
        this.zzgss = driveId;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzgss, i, false);
        zzbgo.zzai(parcel, zze);
    }
}
