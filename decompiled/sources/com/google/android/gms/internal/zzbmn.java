package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public final class zzbmn extends zzbgl {
    public static final Parcelable.Creator<zzbmn> CREATOR = new zzbmo();
    private MetadataBundle zzgtf;
    private DriveId zzgth;

    public zzbmn(DriveId driveId, MetadataBundle metadataBundle) {
        this.zzgth = (DriveId) zzbq.checkNotNull(driveId);
        this.zzgtf = (MetadataBundle) zzbq.checkNotNull(metadataBundle);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzgth, i, false);
        zzbgo.zza(parcel, 3, (Parcelable) this.zzgtf, i, false);
        zzbgo.zzai(parcel, zze);
    }
}
