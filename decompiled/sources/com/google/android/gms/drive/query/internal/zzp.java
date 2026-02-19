package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.metadata.SearchableCollectionMetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.metadata.zzb;
import com.google.android.gms.internal.zzbgo;
import java.util.Collection;
import java.util.Collections;

public final class zzp<T> extends zza {
    public static final zzq CREATOR = new zzq();
    private MetadataBundle zzhbl;
    private final zzb<T> zzhby;

    public zzp(SearchableCollectionMetadataField<T> searchableCollectionMetadataField, T t) {
        this(MetadataBundle.zzb(searchableCollectionMetadataField, Collections.singleton(t)));
    }

    zzp(MetadataBundle metadataBundle) {
        this.zzhbl = metadataBundle;
        this.zzhby = (zzb) zzi.zza(metadataBundle);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) this.zzhbl, i, false);
        zzbgo.zzai(parcel, zze);
    }

    public final <F> F zza(zzj<F> zzj) {
        zzb<T> zzb = this.zzhby;
        return zzj.zza(zzb, (T) ((Collection) this.zzhbl.zza(zzb)).iterator().next());
    }
}
