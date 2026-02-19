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

public final class zzbd extends zzbgl {
    public static final Parcelable.Creator<zzbd> CREATOR = new zzbe();
    private final PendingIntent zzekk;
    private final zzbzt zzhnu;

    zzbd(PendingIntent pendingIntent, IBinder iBinder) {
        this.zzekk = pendingIntent;
        this.zzhnu = zzbzu.zzba(iBinder);
    }

    public zzbd(PendingIntent pendingIntent, zzbzt zzbzt) {
        this.zzekk = pendingIntent;
        this.zzhnu = zzbzt;
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            return (obj instanceof zzbd) && zzbg.equal(this.zzekk, ((zzbd) obj).zzekk);
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzekk});
    }

    public final String toString() {
        return zzbg.zzx(this).zzg("pendingIntent", this.zzekk).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) this.zzekk, i, false);
        zzbzt zzbzt = this.zzhnu;
        zzbgo.zza(parcel, 2, zzbzt == null ? null : zzbzt.asBinder(), false);
        zzbgo.zzai(parcel, zze);
    }
}
