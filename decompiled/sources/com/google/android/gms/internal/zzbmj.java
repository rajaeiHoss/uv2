package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public final class zzbmj extends zzbgl {
    public static final Parcelable.Creator<zzbmj> CREATOR = new zzbmk();
    private String zzesj;
    private int zzgpc;
    private DriveId zzgqt;
    private MetadataBundle zzgtf;
    private Integer zzgtg;

    public zzbmj(MetadataBundle metadataBundle, int i, String str, DriveId driveId, Integer num) {
        this.zzgtf = metadataBundle;
        this.zzgpc = i;
        this.zzesj = str;
        this.zzgqt = driveId;
        this.zzgtg = num;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzgtf, i, false);
        zzbgo.zzc(parcel, 3, this.zzgpc);
        zzbgo.zza(parcel, 4, this.zzesj, false);
        zzbgo.zza(parcel, 5, (Parcelable) this.zzgqt, i, false);
        zzbgo.zza(parcel, 6, this.zzgtg, false);
        zzbgo.zzai(parcel, zze);
    }
}
