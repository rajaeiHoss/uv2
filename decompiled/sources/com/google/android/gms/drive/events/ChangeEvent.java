package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Locale;

public final class ChangeEvent extends zzbgl implements ResourceEvent {
    public static final Parcelable.Creator<ChangeEvent> CREATOR = new zza();
    private DriveId zzgpe;
    private int zzgrm;

    public ChangeEvent(DriveId driveId, int i) {
        this.zzgpe = driveId;
        this.zzgrm = i;
    }

    public final DriveId getDriveId() {
        return this.zzgpe;
    }

    public final int getType() {
        return 1;
    }

    public final boolean hasBeenDeleted() {
        return (this.zzgrm & 4) != 0;
    }

    public final boolean hasContentChanged() {
        return (this.zzgrm & 2) != 0;
    }

    public final boolean hasMetadataChanged() {
        return (this.zzgrm & 1) != 0;
    }

    public final String toString() {
        return String.format(Locale.US, "ChangeEvent [id=%s,changeFlags=%x]", new Object[]{this.zzgpe, Integer.valueOf(this.zzgrm)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzgpe, i, false);
        zzbgo.zzc(parcel, 3, this.zzgrm);
        zzbgo.zzai(parcel, zze);
    }
}
