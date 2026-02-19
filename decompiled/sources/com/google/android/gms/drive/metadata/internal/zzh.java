package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.metadata.zza;

public final class zzh extends zza<Integer> {
    public zzh(String str, int i) {
        super(str, 4300000);
    }

    /* access modifiers changed from: protected */
    public final void zza(Bundle bundle, Integer num) {
        bundle.putInt(getName(), num.intValue());
    }

    /* access modifiers changed from: protected */
    public final Integer zzc(DataHolder dataHolder, int i, int i2) {
        return Integer.valueOf(dataHolder.zzc(getName(), i, i2));
    }

    /* access modifiers changed from: protected */
    public final Integer zzo(Bundle bundle) {
        return Integer.valueOf(bundle.getInt(getName()));
    }
}
