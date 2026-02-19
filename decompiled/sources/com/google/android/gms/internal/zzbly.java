package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.events.zze;
import com.google.android.gms.drive.events.zzt;
import com.google.android.gms.drive.events.zzx;

public final class zzbly extends zzbgl {
    public static final Parcelable.Creator<zzbly> CREATOR = new zzblz();
    final int zzgjw;
    final DriveId zzgpe;
    private zze zzgrn;
    private zzx zzgsq;
    private zzt zzgsr;

    public zzbly(int i, DriveId driveId) {
        this((DriveId) zzbq.checkNotNull(driveId), 1, (zze) null, (zzx) null, (zzt) null);
    }

    zzbly(DriveId driveId, int i, zze zze, zzx zzx, zzt zzt) {
        this.zzgpe = driveId;
        this.zzgjw = i;
        this.zzgrn = zze;
        this.zzgsq = zzx;
        this.zzgsr = zzt;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzgpe, i, false);
        zzbgo.zzc(parcel, 3, this.zzgjw);
        zzbgo.zza(parcel, 4, (Parcelable) this.zzgrn, i, false);
        zzbgo.zza(parcel, 5, (Parcelable) this.zzgsq, i, false);
        zzbgo.zza(parcel, 6, (Parcelable) this.zzgsr, i, false);
        zzbgo.zzai(parcel, zze);
    }
}
