package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.query.Query;

public final class zzbtg extends zzbgl {
    public static final Parcelable.Creator<zzbtg> CREATOR = new zzbth();
    private Query zzgxy;

    public zzbtg(Query query) {
        this.zzgxy = query;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzgxy, i, false);
        zzbgo.zzai(parcel, zze);
    }
}
