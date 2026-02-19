package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.DriveId;

public final class zzbrt extends zzbgl {
    public static final Parcelable.Creator<zzbrt> CREATOR = new zzbru();
    private DriveId zzgwx;

    public zzbrt(DriveId driveId) {
        this.zzgwx = driveId;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzgwx, i, false);
        zzbgo.zzai(parcel, zze);
    }
}
