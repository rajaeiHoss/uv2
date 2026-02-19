package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.metadata.zza;

public class zzi extends zza<Long> {
    public zzi(String str, int i) {
        super(str, 4300000);
    }

    /* access modifiers changed from: protected */
    public final void zza(Bundle bundle, Long l) {
        bundle.putLong(getName(), l.longValue());
    }

    /* access modifiers changed from: protected */
    public final Long zzc(DataHolder dataHolder, int i, int i2) {
        return Long.valueOf(dataHolder.zzb(getName(), i, i2));
    }

    /* access modifiers changed from: protected */
    public final Long zzo(Bundle bundle) {
        return Long.valueOf(bundle.getLong(getName()));
    }
}
