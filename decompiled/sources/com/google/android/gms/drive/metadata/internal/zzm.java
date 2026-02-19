package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.drive.metadata.zza;
import java.util.Collection;

public abstract class zzm<T extends ReflectedParcelable> extends zza<T> {
    public zzm(String str, Collection<String> collection, Collection<String> collection2, int i) {
        super(str, collection, collection2, i);
    }

    /* access modifiers changed from: protected */
    public final void zza(Bundle bundle, T t) {
        bundle.putParcelable(getName(), t);
    }

    /* access modifiers changed from: protected */
    public final T zzo(Bundle bundle) {
        return (T) bundle.getParcelable(getName());
    }
}
