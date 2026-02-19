package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzc implements Parcelable.Creator<SnapshotEntity> {
    public final /* synthetic */ SnapshotEntity createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        SnapshotMetadataEntity snapshotMetadataEntity = null;
        zza zza = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                snapshotMetadataEntity = (SnapshotMetadataEntity) zzbgm.zza(parcel, readInt, SnapshotMetadataEntity.CREATOR);
            } else if (i != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                zza = (zza) zzbgm.zza(parcel, readInt, zza.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new SnapshotEntity(snapshotMetadataEntity, zza);
    }

    public final /* synthetic */ SnapshotEntity[] newArray(int i) {
        return new SnapshotEntity[i];
    }
}
