package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.awareness.fence.FenceUpdateRequest;
import java.util.ArrayList;

public final class zzbkp extends zzbgl implements FenceUpdateRequest {
    public static final Parcelable.Creator<zzbkp> CREATOR = new zzbkq();
    public final ArrayList<zzbkz> zzgnt;

    @Deprecated
    public zzbkp() {
        this(new ArrayList());
    }

    public zzbkp(ArrayList<zzbkz> arrayList) {
        this.zzgnt = arrayList;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, this.zzgnt, false);
        zzbgo.zzai(parcel, zze);
    }
}
