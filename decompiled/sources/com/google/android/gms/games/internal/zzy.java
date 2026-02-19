package com.google.android.gms.games.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgo;

public final class zzy extends zzc {
    public static final Parcelable.Creator<zzy> CREATOR = new zzz();
    private final Bundle zziaa;
    private final IBinder zziab;

    zzy(Bundle bundle, IBinder iBinder) {
        this.zziaa = bundle;
        this.zziab = iBinder;
    }

    public zzy(zzac zzac) {
        this.zziaa = zzac.zzauy();
        this.zziab = zzac.zziae;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zziaa, false);
        zzbgo.zza(parcel, 2, this.zziab, false);
        zzbgo.zzai(parcel, zze);
    }
}
