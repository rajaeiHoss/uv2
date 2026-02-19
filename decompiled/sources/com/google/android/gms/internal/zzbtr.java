package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.DriveId;

public final class zzbtr extends zzbgl {
    public static final Parcelable.Creator<zzbtr> CREATOR = new zzbts();
    private DriveId zzgss;

    public zzbtr(DriveId driveId) {
        this.zzgss = driveId;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzgss, i, false);
        zzbgo.zzai(parcel, zze);
    }
}
