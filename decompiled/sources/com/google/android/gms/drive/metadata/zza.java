package com.google.android.gms.drive.metadata;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class zza<T> implements MetadataField<T> {
    private final String zzgyi;
    private final Set<String> zzgyj;
    private final Set<String> zzgyk;
    private final int zzgyl;

    protected zza(String str, int i) {
        this.zzgyi = (String) zzbq.checkNotNull(str, "fieldName");
        this.zzgyj = Collections.singleton(str);
        this.zzgyk = Collections.emptySet();
        this.zzgyl = i;
    }

    protected zza(String str, Collection<String> collection, Collection<String> collection2, int i) {
        this.zzgyi = (String) zzbq.checkNotNull(str, "fieldName");
        this.zzgyj = Collections.unmodifiableSet(new HashSet(collection));
        this.zzgyk = Collections.unmodifiableSet(new HashSet(collection2));
        this.zzgyl = i;
    }

    public final String getName() {
        return this.zzgyi;
    }

    public String toString() {
        return this.zzgyi;
    }

    public final T zza(DataHolder dataHolder, int i, int i2) {
        if (zzb(dataHolder, i, i2)) {
            return zzc(dataHolder, i, i2);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract void zza(Bundle bundle, T t);

    public final void zza(DataHolder dataHolder, MetadataBundle metadataBundle, int i, int i2) {
        zzbq.checkNotNull(dataHolder, "dataHolder");
        zzbq.checkNotNull(metadataBundle, "bundle");
        if (zzb(dataHolder, i, i2)) {
            metadataBundle.zzc(this, zzc(dataHolder, i, i2));
        }
    }

    public final void zza(T t, Bundle bundle) {
        zzbq.checkNotNull(bundle, "bundle");
        if (t == null) {
            bundle.putString(this.zzgyi, (String) null);
        } else {
            zza(bundle, t);
        }
    }

    public final Collection<String> zzaqu() {
        return this.zzgyj;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:3:0x000c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean zzb(com.google.android.gms.common.data.DataHolder r4, int r5, int r6) {
        /*
            r3 = this;
            java.util.Set<java.lang.String> r0 = r3.zzgyj
            java.util.Iterator r0 = r0.iterator()
        L_0x0006:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0020
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            boolean r2 = r4.zzgj(r1)
            if (r2 == 0) goto L_0x001e
            boolean r1 = r4.zzh(r1, r5, r6)
            if (r1 == 0) goto L_0x0006
        L_0x001e:
            r4 = 0
            return r4
        L_0x0020:
            r4 = 1
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.drive.metadata.zza.zzb(com.google.android.gms.common.data.DataHolder, int, int):boolean");
    }

    /* access modifiers changed from: protected */
    public abstract T zzc(DataHolder dataHolder, int i, int i2);

    public final T zzn(Bundle bundle) {
        zzbq.checkNotNull(bundle, "bundle");
        if (bundle.get(this.zzgyi) != null) {
            return zzo(bundle);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract T zzo(Bundle bundle);
}
