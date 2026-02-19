package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.DriveId;

public final class zzbsz extends zzbgl {
    public static final Parcelable.Creator<zzbsz> CREATOR = new zzbta();
    private int zzgpd;
    private DriveId zzgss;
    private int zzgxv;

    public zzbsz(DriveId driveId, int i, int i2) {
        this.zzgss = driveId;
        this.zzgpd = i;
        this.zzgxv = i2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzgss, i, false);
        zzbgo.zzc(parcel, 3, this.zzgpd);
        zzbgo.zzc(parcel, 4, this.zzgxv);
        zzbgo.zzai(parcel, zze);
    }
}
