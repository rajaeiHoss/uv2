package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.zzc;
import com.google.android.gms.drive.zzo;

public final class zzbml extends zzbgl {
    public static final Parcelable.Creator<zzbml> CREATOR = new zzbmm();
    private String zzgqg;
    private String zzgqj;
    private zzc zzgsu;
    private MetadataBundle zzgtf;
    private Integer zzgtg;
    private DriveId zzgth;
    private boolean zzgti;
    private int zzgtj;
    private int zzgtk;

    public zzbml(DriveId driveId, MetadataBundle metadataBundle, int i, int i2, zzo zzo) {
        this(driveId, metadataBundle, (zzc) null, Integer.valueOf(i2), zzo.zzapp(), zzo.zzapo(), zzo.zzapq(), i, zzo.zzaps());
    }

    zzbml(DriveId driveId, MetadataBundle metadataBundle, zzc zzc, Integer num, boolean z, String str, int i, int i2, String str2) {
        if (!(zzc == null || i2 == 0)) {
            zzbq.checkArgument(zzc.getRequestId() == i2, "inconsistent contents reference");
        }
        if ((num == null || num.intValue() == 0) && zzc == null && i2 == 0) {
            throw new IllegalArgumentException("Need a valid contents");
        }
        this.zzgth = (DriveId) zzbq.checkNotNull(driveId);
        this.zzgtf = (MetadataBundle) zzbq.checkNotNull(metadataBundle);
        this.zzgsu = zzc;
        this.zzgtg = num;
        this.zzgqg = str;
        this.zzgtj = i;
        this.zzgti = z;
        this.zzgtk = i2;
        this.zzgqj = str2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzgth, i, false);
        zzbgo.zza(parcel, 3, (Parcelable) this.zzgtf, i, false);
        zzbgo.zza(parcel, 4, (Parcelable) this.zzgsu, i, false);
        zzbgo.zza(parcel, 5, this.zzgtg, false);
        zzbgo.zza(parcel, 6, this.zzgti);
        zzbgo.zza(parcel, 7, this.zzgqg, false);
        zzbgo.zzc(parcel, 8, this.zzgtj);
        zzbgo.zzc(parcel, 9, this.zzgtk);
        zzbgo.zza(parcel, 10, this.zzgqj, false);
        zzbgo.zzai(parcel, zze);
    }
}
