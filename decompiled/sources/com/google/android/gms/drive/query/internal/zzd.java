package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.internal.zzbgo;

public final class zzd extends zza {
    public static final Parcelable.Creator<zzd> CREATOR = new zze();
    private MetadataBundle zzhbl;
    private final MetadataField<?> zzhbm;

    public zzd(SearchableMetadataField<?> searchableMetadataField) {
        this(MetadataBundle.zzb(searchableMetadataField, null));
    }

    zzd(MetadataBundle metadataBundle) {
        this.zzhbl = metadataBundle;
        this.zzhbm = zzi.zza(metadataBundle);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) this.zzhbl, i, false);
        zzbgo.zzai(parcel, zze);
    }

    public final <T> T zza(zzj<T> zzj) {
        return zzj.zze(this.zzhbm);
    }
}
