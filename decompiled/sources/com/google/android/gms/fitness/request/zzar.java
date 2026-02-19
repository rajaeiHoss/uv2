package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fitness.data.zzt;
import com.google.android.gms.fitness.data.zzu;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbzt;
import com.google.android.gms.internal.zzbzu;

public final class zzar extends zzbgl {
    public static final Parcelable.Creator<zzar> CREATOR = new zzas();
    private final PendingIntent zzekk;
    private final zzbzt zzhnu;
    private final zzt zzhoz;

    zzar(IBinder iBinder, PendingIntent pendingIntent, IBinder iBinder2) {
        this.zzhoz = iBinder == null ? null : zzu.zzar(iBinder);
        this.zzekk = pendingIntent;
        this.zzhnu = zzbzu.zzba(iBinder2);
    }

    public zzar(zzt zzt, PendingIntent pendingIntent, zzbzt zzbzt) {
        this.zzhoz = zzt;
        this.zzekk = pendingIntent;
        this.zzhnu = zzbzt;
    }

    public final String toString() {
        return String.format("SensorUnregistrationRequest{%s}", new Object[]{this.zzhoz});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzt zzt = this.zzhoz;
        IBinder iBinder = null;
        zzbgo.zza(parcel, 1, zzt == null ? null : zzt.asBinder(), false);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzekk, i, false);
        zzbzt zzbzt = this.zzhnu;
        if (zzbzt != null) {
            iBinder = zzbzt.asBinder();
        }
        zzbgo.zza(parcel, 3, iBinder, false);
        zzbgo.zzai(parcel, zze);
    }
}
