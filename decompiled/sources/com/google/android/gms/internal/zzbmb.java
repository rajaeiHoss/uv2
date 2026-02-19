package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.zzc;
import com.google.android.gms.drive.zzr;

public final class zzbmb extends zzbgl {
    public static final Parcelable.Creator<zzbmb> CREATOR = new zzbmc();
    private String zzgqg;
    private boolean zzgqh;
    private boolean zzgql;
    private DriveId zzgss;
    private MetadataBundle zzgst;
    private zzc zzgsu;
    private int zzgsv;
    private int zzgsw;
    private boolean zzgsx;

    public zzbmb(DriveId driveId, MetadataBundle metadataBundle, int i, boolean z, zzr zzr) {
        this(driveId, metadataBundle, (zzc) null, zzr.zzapp(), zzr.zzapo(), zzr.zzapq(), i, z, zzr.zzapu());
    }

    zzbmb(DriveId driveId, MetadataBundle metadataBundle, zzc zzc, boolean z, String str, int i, int i2, boolean z2, boolean z3) {
        this.zzgss = driveId;
        this.zzgst = metadataBundle;
        this.zzgsu = zzc;
        this.zzgqh = z;
        this.zzgqg = str;
        this.zzgsv = i;
        this.zzgsw = i2;
        this.zzgsx = z2;
        this.zzgql = z3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzgss, i, false);
        zzbgo.zza(parcel, 3, (Parcelable) this.zzgst, i, false);
        zzbgo.zza(parcel, 4, (Parcelable) this.zzgsu, i, false);
        zzbgo.zza(parcel, 5, this.zzgqh);
        zzbgo.zza(parcel, 6, this.zzgqg, false);
        zzbgo.zzc(parcel, 7, this.zzgsv);
        zzbgo.zzc(parcel, 8, this.zzgsw);
        zzbgo.zza(parcel, 9, this.zzgsx);
        zzbgo.zza(parcel, 10, this.zzgql);
        zzbgo.zzai(parcel, zze);
    }
}
