package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.metadata.internal.zzb;
import java.util.Collection;

final class zzbuk extends zzb {
    zzbuk(String str, Collection collection, Collection collection2, int i) {
        super(str, collection, collection2, 7000000);
    }

    /* access modifiers changed from: protected */
    public final Boolean zzc(DataHolder dataHolder, int i, int i2) {
        return zze(dataHolder, i, i2);
    }

    /* access modifiers changed from: protected */
    public final Boolean zze(DataHolder dataHolder, int i, int i2) {
        return Boolean.valueOf(dataHolder.zzc("trashed", i, i2) == 2);
    }
}
