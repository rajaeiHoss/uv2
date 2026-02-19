package com.google.android.gms.awareness.fence;

import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbke;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public abstract class AwarenessFence extends zzbgl {
    protected AwarenessFence() {
    }

    public static AwarenessFence and(Collection<AwarenessFence> collection) {
        zzbq.checkArgument(collection != null && !collection.isEmpty());
        return zzbke.zzf(zzd(collection));
    }

    public static AwarenessFence and(AwarenessFence... awarenessFenceArr) {
        zzbq.checkArgument(awarenessFenceArr != null && awarenessFenceArr.length > 0);
        return zzbke.zzf(zza(awarenessFenceArr));
    }

    public static AwarenessFence not(AwarenessFence awarenessFence) {
        zzbq.checkNotNull(awarenessFence);
        return zzbke.zza((zzbke) awarenessFence);
    }

    public static AwarenessFence or(Collection<AwarenessFence> collection) {
        zzbq.checkArgument(collection != null && !collection.isEmpty());
        return zzbke.zzg(zzd(collection));
    }

    public static AwarenessFence or(AwarenessFence... awarenessFenceArr) {
        zzbq.checkArgument(awarenessFenceArr != null && awarenessFenceArr.length > 0);
        return zzbke.zzg(zza(awarenessFenceArr));
    }

    private static ArrayList<zzbke> zza(AwarenessFence[] awarenessFenceArr) {
        ArrayList<zzbke> arrayList = new ArrayList<>(awarenessFenceArr.length);
        for (AwarenessFence awarenessFence : awarenessFenceArr) {
            arrayList.add((zzbke) awarenessFence);
        }
        return arrayList;
    }

    private static ArrayList<zzbke> zzd(Collection<AwarenessFence> collection) {
        ArrayList<zzbke> arrayList = new ArrayList<>(collection.size());
        Iterator<AwarenessFence> it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add((zzbke) it.next());
        }
        return arrayList;
    }
}
