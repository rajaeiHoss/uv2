package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.query.internal.FilterHolder;

public final class zzbtc extends zzbgl {
    public static final Parcelable.Creator<zzbtc> CREATOR = new zzbtd();
    private String zzesj;
    private String[] zzgqr;
    private DriveId zzgqt;
    private FilterHolder zzgxx;

    public zzbtc(String str, String[] strArr, DriveId driveId, FilterHolder filterHolder) {
        this.zzesj = str;
        this.zzgqr = strArr;
        this.zzgqt = driveId;
        this.zzgxx = filterHolder;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzesj, false);
        zzbgo.zza(parcel, 3, this.zzgqr, false);
        zzbgo.zza(parcel, 4, (Parcelable) this.zzgqt, i, false);
        zzbgo.zza(parcel, 5, (Parcelable) this.zzgxx, i, false);
        zzbgo.zzai(parcel, zze);
    }
}
