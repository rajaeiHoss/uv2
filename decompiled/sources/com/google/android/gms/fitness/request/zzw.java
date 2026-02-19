package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbzt;
import com.google.android.gms.internal.zzbzu;
import java.util.Arrays;

public final class zzw extends zzbgl {
    public static final Parcelable.Creator<zzw> CREATOR = new zzx();
    private final PendingIntent zzekk;
    private final zzbzt zzhnu;

    public zzw(PendingIntent pendingIntent, IBinder iBinder) {
        this.zzekk = pendingIntent;
        this.zzhnu = zzbzu.zzba(iBinder);
    }

    public final boolean equals(Object obj) {
        if (obj != this) {
            return (obj instanceof zzw) && zzbg.equal(this.zzekk, ((zzw) obj).zzekk);
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzekk});
    }

    public final String toString() {
        return "DataUpdateListenerUnregistrationRequest";
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) this.zzekk, i, false);
        zzbzt zzbzt = this.zzhnu;
        zzbgo.zza(parcel, 2, zzbzt == null ? null : zzbzt.asBinder(), false);
        zzbgo.zzai(parcel, zze);
    }
}
