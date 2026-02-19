package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.internal.zzbgo;

public final class zzn<T> extends zza {
    public static final zzo CREATOR = new zzo();
    private MetadataBundle zzhbl;
    private MetadataField<T> zzhbm;

    public zzn(SearchableMetadataField<T> searchableMetadataField, T t) {
        this(MetadataBundle.zzb(searchableMetadataField, t));
    }

    zzn(MetadataBundle metadataBundle) {
        this.zzhbl = metadataBundle;
        this.zzhbm = (MetadataField<T>) zzi.zza(metadataBundle);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) this.zzhbl, i, false);
        zzbgo.zzai(parcel, zze);
    }

    public final <F> F zza(zzj<F> zzj) {
        MetadataField<T> metadataField = this.zzhbm;
        return zzj.zzd(metadataField, this.zzhbl.zza(metadataField));
    }
}
