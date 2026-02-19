package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.DriveId;

public final class zzbsd extends zzbgl {
    public static final Parcelable.Creator<zzbsd> CREATOR = new zzbse();
    DriveId zzgss;

    public zzbsd(DriveId driveId) {
        this.zzgss = driveId;
    }

    public final DriveId getDriveId() {
        return this.zzgss;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzgss, i, false);
        zzbgo.zzai(parcel, zze);
    }
}
