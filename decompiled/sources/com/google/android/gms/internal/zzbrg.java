package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.DriveId;

public final class zzbrg extends zzbgl {
    public static final Parcelable.Creator<zzbrg> CREATOR = new zzbrh();
    private DriveId zzgss;
    private boolean zzgwv;

    public zzbrg(DriveId driveId, boolean z) {
        this.zzgss = driveId;
        this.zzgwv = z;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzgss, i, false);
        zzbgo.zza(parcel, 3, this.zzgwv);
        zzbgo.zzai(parcel, zze);
    }
}
