package com.google.android.gms.drive.metadata.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class AppVisibleCustomProperties extends zzbgl implements ReflectedParcelable, Iterable<zzc> {
    public static final Parcelable.Creator<AppVisibleCustomProperties> CREATOR = new com.google.android.gms.drive.metadata.internal.zza();
    public static final AppVisibleCustomProperties zzgyn = new zza().zzaqw();
    private List<zzc> zzgyo;

    public static class zza {
        private final Map<CustomPropertyKey, zzc> zzgyp = new HashMap();

        public final zza zza(CustomPropertyKey customPropertyKey, String str) {
            zzbq.checkNotNull(customPropertyKey, "key");
            this.zzgyp.put(customPropertyKey, new zzc(customPropertyKey, str));
            return this;
        }

        public final zza zza(zzc zzc) {
            zzbq.checkNotNull(zzc, "property");
            this.zzgyp.put(zzc.zzgyq, zzc);
            return this;
        }

        public final AppVisibleCustomProperties zzaqw() {
            return new AppVisibleCustomProperties(this.zzgyp.values());
        }
    }

    AppVisibleCustomProperties(Collection<zzc> collection) {
        zzbq.checkNotNull(collection);
        this.zzgyo = new ArrayList(collection);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return zzaqv().equals(((AppVisibleCustomProperties) obj).zzaqv());
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzgyo});
    }

    public final Iterator<zzc> iterator() {
        return this.zzgyo.iterator();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, this.zzgyo, false);
        zzbgo.zzai(parcel, zze);
    }

    public final Map<CustomPropertyKey, String> zzaqv() {
        HashMap hashMap = new HashMap(this.zzgyo.size());
        for (zzc next : this.zzgyo) {
            hashMap.put(next.zzgyq, next.mValue);
        }
        return Collections.unmodifiableMap(hashMap);
    }
}
