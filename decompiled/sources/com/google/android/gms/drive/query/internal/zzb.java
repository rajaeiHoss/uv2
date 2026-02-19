package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.internal.zzbgo;

public final class zzb<T> extends zza {
    public static final zzc CREATOR = new zzc();
    private zzx zzhbk;
    private MetadataBundle zzhbl;
    private MetadataField<T> zzhbm;

    public zzb(zzx zzx, SearchableMetadataField<T> searchableMetadataField, T t) {
        this(zzx, MetadataBundle.zzb(searchableMetadataField, t));
    }

    zzb(zzx zzx, MetadataBundle metadataBundle) {
        this.zzhbk = zzx;
        this.zzhbl = metadataBundle;
        this.zzhbm = (MetadataField<T>) zzi.zza(metadataBundle);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) this.zzhbk, i, false);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzhbl, i, false);
        zzbgo.zzai(parcel, zze);
    }

    public final <F> F zza(zzj<F> zzj) {
        zzx zzx = this.zzhbk;
        MetadataField<T> metadataField = this.zzhbm;
        return zzj.zza(zzx, metadataField, this.zzhbl.zza(metadataField));
    }
}
