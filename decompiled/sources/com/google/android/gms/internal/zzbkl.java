package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.awareness.fence.FenceState;
import com.google.android.gms.awareness.fence.FenceStateMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class zzbkl extends zzbgl implements FenceStateMap {
    public static final Parcelable.Creator<zzbkl> CREATOR = new zzbkm();
    private Map<String, zzbkj> zzgnr = new HashMap();

    zzbkl(Bundle bundle) {
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                this.zzgnr.put(str, (zzbkj) zzbgq.zza(bundle.getByteArray(str), zzbkj.CREATOR));
            }
        }
    }

    public final Set<String> getFenceKeys() {
        return this.zzgnr.keySet();
    }

    public final /* synthetic */ FenceState getFenceState(String str) {
        if (this.zzgnr.containsKey(str)) {
            return this.zzgnr.get(str);
        }
        return null;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        Bundle bundle;
        int zze = zzbgo.zze(parcel);
        if (this.zzgnr == null) {
            bundle = null;
        } else {
            bundle = new Bundle();
            for (Map.Entry next : this.zzgnr.entrySet()) {
                bundle.putByteArray((String) next.getKey(), zzbgq.zza((zzbkj) next.getValue()));
            }
        }
        zzbgo.zza(parcel, 2, bundle, false);
        zzbgo.zzai(parcel, zze);
    }
}
