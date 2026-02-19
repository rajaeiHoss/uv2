package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.drive.query.Filter;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public class FilterHolder extends zzbgl implements ReflectedParcelable {
    public static final Parcelable.Creator<FilterHolder> CREATOR = new zzh();
    private final Filter zzgqs;
    private zzb<?> zzhbo;
    private zzd zzhbp;
    private zzr zzhbq;
    private zzv zzhbr;
    private zzp<?> zzhbs;
    private zzt zzhbt;
    private zzn zzhbu;
    private zzl zzhbv;
    private zzz zzhbw;

    public FilterHolder(Filter filter) {
        zzbq.checkNotNull(filter, "Null filter.");
        zzz zzz = null;
        zzb<?> zzb = filter instanceof zzb ? (zzb) filter : null;
        this.zzhbo = zzb;
        zzd zzd = filter instanceof zzd ? (zzd) filter : null;
        this.zzhbp = zzd;
        zzr zzr = filter instanceof zzr ? (zzr) filter : null;
        this.zzhbq = zzr;
        zzv zzv = filter instanceof zzv ? (zzv) filter : null;
        this.zzhbr = zzv;
        zzp<?> zzp = filter instanceof zzp ? (zzp) filter : null;
        this.zzhbs = zzp;
        zzt zzt = filter instanceof zzt ? (zzt) filter : null;
        this.zzhbt = zzt;
        zzn zzn = filter instanceof zzn ? (zzn) filter : null;
        this.zzhbu = zzn;
        zzl zzl = filter instanceof zzl ? (zzl) filter : null;
        this.zzhbv = zzl;
        zzz = filter instanceof zzz ? (zzz) filter : zzz;
        this.zzhbw = zzz;
        if (zzb == null && zzd == null && zzr == null && zzv == null && zzp == null && zzt == null && zzn == null && zzl == null && zzz == null) {
            throw new IllegalArgumentException("Invalid filter type.");
        }
        this.zzgqs = filter;
    }

    FilterHolder(zzb<?> zzb, zzd zzd, zzr zzr, zzv zzv, zzp<?> zzp, zzt zzt, zzn<?> zzn, zzl zzl, zzz zzz) {
        this.zzhbo = zzb;
        this.zzhbp = zzd;
        this.zzhbq = zzr;
        this.zzhbr = zzv;
        this.zzhbs = zzp;
        this.zzhbt = zzt;
        this.zzhbu = zzn;
        this.zzhbv = zzl;
        this.zzhbw = zzz;
        if (zzb != null) {
            this.zzgqs = zzb;
        } else if (zzd != null) {
            this.zzgqs = zzd;
        } else if (zzr != null) {
            this.zzgqs = zzr;
        } else if (zzv != null) {
            this.zzgqs = zzv;
        } else if (zzp != null) {
            this.zzgqs = zzp;
        } else if (zzt != null) {
            this.zzgqs = zzt;
        } else if (zzn != null) {
            this.zzgqs = zzn;
        } else if (zzl != null) {
            this.zzgqs = zzl;
        } else if (zzz != null) {
            this.zzgqs = zzz;
        } else {
            throw new IllegalArgumentException("At least one filter must be set.");
        }
    }

    public final Filter getFilter() {
        return this.zzgqs;
    }

    public String toString() {
        return String.format("FilterHolder[%s]", new Object[]{this.zzgqs});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) this.zzhbo, i, false);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzhbp, i, false);
        zzbgo.zza(parcel, 3, (Parcelable) this.zzhbq, i, false);
        zzbgo.zza(parcel, 4, (Parcelable) this.zzhbr, i, false);
        zzbgo.zza(parcel, 5, (Parcelable) this.zzhbs, i, false);
        zzbgo.zza(parcel, 6, (Parcelable) this.zzhbt, i, false);
        zzbgo.zza(parcel, 7, (Parcelable) this.zzhbu, i, false);
        zzbgo.zza(parcel, 8, (Parcelable) this.zzhbv, i, false);
        zzbgo.zza(parcel, 9, (Parcelable) this.zzhbw, i, false);
        zzbgo.zzai(parcel, zze);
    }
}
