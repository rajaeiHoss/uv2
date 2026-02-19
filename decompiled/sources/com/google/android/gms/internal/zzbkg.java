package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.awareness.fence.FenceQueryRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public final class zzbkg extends FenceQueryRequest {
    public static final Parcelable.Creator<zzbkg> CREATOR = new zzbki();
    private zzbkh zzgnk;

    public zzbkg() {
        this(zzbkh.zza(1, (List<String>) null));
    }

    zzbkg(zzbkh zzbkh) {
        this.zzgnk = zzbkh;
    }

    public zzbkg(Collection<String> collection) {
        this(zzbkh.zza(2, new ArrayList(collection)));
    }

    public zzbkg(String... strArr) {
        this(zzbkh.zza(2, Arrays.asList(strArr)));
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzgnk, i, false);
        zzbgo.zzai(parcel, zze);
    }
}
