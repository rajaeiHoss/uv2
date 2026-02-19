package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.internal.zzb;

public final class zzbut extends zzb implements SearchableMetadataField<Boolean> {
    public zzbut(String str, int i) {
        super(str, 4100000);
    }

    /* access modifiers changed from: protected */
    public final Boolean zzc(DataHolder dataHolder, int i, int i2) {
        return zze(dataHolder, i, i2);
    }

    /* access modifiers changed from: protected */
    public final Boolean zze(DataHolder dataHolder, int i, int i2) {
        return Boolean.valueOf(dataHolder.zzc(getName(), i, i2) != 0);
    }
}
