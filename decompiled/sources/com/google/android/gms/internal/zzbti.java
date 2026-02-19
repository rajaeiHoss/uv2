package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.events.zzt;

public final class zzbti extends zzbgl {
    public static final Parcelable.Creator<zzbti> CREATOR = new zzbtj();
    private int zzgjw;
    private DriveId zzgpe;
    private zzt zzgsr;

    public zzbti(DriveId driveId, int i) {
        this(driveId, i, (zzt) null);
    }

    zzbti(DriveId driveId, int i, zzt zzt) {
        this.zzgpe = driveId;
        this.zzgjw = i;
        this.zzgsr = zzt;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzgpe, i, false);
        zzbgo.zzc(parcel, 3, this.zzgjw);
        zzbgo.zza(parcel, 4, (Parcelable) this.zzgsr, i, false);
        zzbgo.zzai(parcel, zze);
    }
}
