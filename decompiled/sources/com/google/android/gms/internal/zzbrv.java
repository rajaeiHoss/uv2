package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.zza;
import com.google.android.gms.drive.zzy;
import java.util.List;

public final class zzbrv extends zzy {
    public static final Parcelable.Creator<zzbrv> CREATOR = new zzbrw();
    private DataHolder zzgwy;
    private List<DriveId> zzgwz;
    private zza zzgxa;
    private boolean zzgxb;

    public zzbrv(DataHolder dataHolder, List<DriveId> list, zza zza, boolean z) {
        this.zzgwy = dataHolder;
        this.zzgwz = list;
        this.zzgxa = zza;
        this.zzgxb = z;
    }

    /* access modifiers changed from: protected */
    public final void zzaj(Parcel parcel, int i) {
        int i2 = i | 1;
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzgwy, i2, false);
        zzbgo.zzc(parcel, 3, this.zzgwz, false);
        zzbgo.zza(parcel, 4, (Parcelable) this.zzgxa, i2, false);
        zzbgo.zza(parcel, 5, this.zzgxb);
        zzbgo.zzai(parcel, zze);
    }
}
