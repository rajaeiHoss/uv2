package com.google.android.gms.awareness.fence;

import com.google.android.gms.awareness.state.BeaconState;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbjy;
import com.google.android.gms.internal.zzbke;
import java.util.Collection;

public final class BeaconFence {
    private BeaconFence() {
    }

    public static AwarenessFence found(Collection<BeaconState.TypeFilter> collection) {
        zzbq.checkArgument(collection != null && !collection.isEmpty());
        return found((BeaconState.TypeFilter[]) collection.toArray(new BeaconState.TypeFilter[collection.size()]));
    }

    public static AwarenessFence found(BeaconState.TypeFilter... typeFilterArr) {
        zzbq.checkArgument(typeFilterArr != null && typeFilterArr.length > 0);
        return zzbke.zza(zzbjy.zza(typeFilterArr));
    }

    public static AwarenessFence lost(Collection<BeaconState.TypeFilter> collection) {
        zzbq.checkArgument(collection != null && !collection.isEmpty());
        return lost((BeaconState.TypeFilter[]) collection.toArray(new BeaconState.TypeFilter[collection.size()]));
    }

    public static AwarenessFence lost(BeaconState.TypeFilter... typeFilterArr) {
        zzbq.checkArgument(typeFilterArr != null && typeFilterArr.length > 0);
        return zzbke.zza(zzbjy.zzb(typeFilterArr));
    }

    public static AwarenessFence near(Collection<BeaconState.TypeFilter> collection) {
        zzbq.checkArgument(collection != null && !collection.isEmpty());
        return near((BeaconState.TypeFilter[]) collection.toArray(new BeaconState.TypeFilter[collection.size()]));
    }

    public static AwarenessFence near(BeaconState.TypeFilter... typeFilterArr) {
        zzbq.checkArgument(typeFilterArr != null && typeFilterArr.length > 0);
        return zzbke.zza(zzbjy.zzc(typeFilterArr));
    }
}
