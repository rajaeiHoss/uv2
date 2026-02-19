package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.DriveId;
import java.util.List;

public final class zzbtm extends zzbgl {
    public static final Parcelable.Creator<zzbtm> CREATOR = new zzbtn();
    private DriveId zzgxz;
    private List<DriveId> zzgya;

    public zzbtm(DriveId driveId, List<DriveId> list) {
        this.zzgxz = driveId;
        this.zzgya = list;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzgxz, i, false);
        zzbgo.zzc(parcel, 3, this.zzgya, false);
        zzbgo.zzai(parcel, zze);
    }
}
