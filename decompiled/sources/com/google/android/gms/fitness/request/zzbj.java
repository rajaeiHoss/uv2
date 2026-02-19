package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.fitness.data.Subscription;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbzt;
import com.google.android.gms.internal.zzbzu;

public final class zzbj extends zzbgl {
    public static final Parcelable.Creator<zzbj> CREATOR = new zzbk();
    private final zzbzt zzhnu;
    private Subscription zzhps;
    private final boolean zzhpt;

    zzbj(Subscription subscription, boolean z, IBinder iBinder) {
        this.zzhps = subscription;
        this.zzhpt = z;
        this.zzhnu = zzbzu.zzba(iBinder);
    }

    public zzbj(Subscription subscription, boolean z, zzbzt zzbzt) {
        this.zzhps = subscription;
        this.zzhpt = false;
        this.zzhnu = zzbzt;
    }

    public final String toString() {
        return zzbg.zzx(this).zzg("subscription", this.zzhps).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) this.zzhps, i, false);
        zzbgo.zza(parcel, 2, this.zzhpt);
        zzbzt zzbzt = this.zzhnu;
        zzbgo.zza(parcel, 3, zzbzt == null ? null : zzbzt.asBinder(), false);
        zzbgo.zzai(parcel, zze);
    }
}
