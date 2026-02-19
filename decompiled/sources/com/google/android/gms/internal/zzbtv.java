package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public final class zzbtv extends zzbgl {
    public static final Parcelable.Creator<zzbtv> CREATOR = new zzbtw();
    private DriveId zzgss;
    private MetadataBundle zzgst;

    public zzbtv(DriveId driveId, MetadataBundle metadataBundle) {
        this.zzgss = driveId;
        this.zzgst = metadataBundle;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzgss, i, false);
        zzbgo.zza(parcel, 3, (Parcelable) this.zzgst, i, false);
        zzbgo.zzai(parcel, zze);
    }
}
