package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fitness.data.DataSource;

public final class zzcca extends zzbgl {
    public static final Parcelable.Creator<zzcca> CREATOR = new zzccb();
    private final DataSource zzhhk;

    public zzcca(DataSource dataSource) {
        this.zzhhk = dataSource;
    }

    public final DataSource getDataSource() {
        return this.zzhhk;
    }

    public final String toString() {
        return String.format("ApplicationUnregistrationRequest{%s}", new Object[]{this.zzhhk});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) this.zzhhk, i, false);
        zzbgo.zzai(parcel, zze);
    }
}
