package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.query.Filter;
import com.google.android.gms.internal.zzbgo;

public final class zzv extends zza {
    public static final Parcelable.Creator<zzv> CREATOR = new zzw();
    private FilterHolder zzhca;

    public zzv(Filter filter) {
        this(new FilterHolder(filter));
    }

    zzv(FilterHolder filterHolder) {
        this.zzhca = filterHolder;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) this.zzhca, i, false);
        zzbgo.zzai(parcel, zze);
    }

    public final <T> T zza(zzj<T> zzj) {
        return zzj.zzy(this.zzhca.getFilter().zza(zzj));
    }
}
