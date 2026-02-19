package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Collections;
import java.util.List;

public final class zzan extends zzbgl {
    public static final Parcelable.Creator<zzan> CREATOR = new zzaq();
    private List<zzao> zziyk;
    private List<zzap> zziyl;

    zzan(List<zzao> list, List<zzap> list2) {
        this.zziyk = Collections.unmodifiableList(list);
        this.zziyl = Collections.unmodifiableList(list2);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.zziyk, false);
        zzbgo.zzc(parcel, 2, this.zziyl, false);
        zzbgo.zzai(parcel, zze);
    }
}
