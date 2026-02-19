package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class zzeca extends zzbgl {
    public static final Parcelable.Creator<zzeca> CREATOR = new zzecb();
    private List<zzeby> zzmsv;

    public zzeca() {
        this.zzmsv = new ArrayList();
    }

    zzeca(List<zzeby> list) {
        this.zzmsv = (list == null || list.isEmpty()) ? Collections.emptyList() : Collections.unmodifiableList(list);
    }

    public static zzeca zza(zzeca zzeca) {
        List<zzeby> list = zzeca.zzmsv;
        zzeca zzeca2 = new zzeca();
        if (list != null) {
            zzeca2.zzmsv.addAll(list);
        }
        return zzeca2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, this.zzmsv, false);
        zzbgo.zzai(parcel, zze);
    }

    public final List<zzeby> zzbuc() {
        return this.zzmsv;
    }
}
