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

public final class zzax extends zzbgl {
    public static final Parcelable.Creator<zzax> CREATOR = new zzay();
    private final PendingIntent zzekk;
    private final zzbzt zzhnu;
    private final int zzhpo;

    zzax(PendingIntent pendingIntent, IBinder iBinder, int i) {
        this.zzekk = pendingIntent;
        this.zzhnu = iBinder == null ? null : zzbzu.zzba(iBinder);
        this.zzhpo = i;
    }

    public zzax(PendingIntent pendingIntent, zzbzt zzbzt, int i) {
        this.zzekk = pendingIntent;
        this.zzhnu = zzbzt;
        this.zzhpo = i;
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof zzax) {
                zzax zzax = (zzax) obj;
                if (this.zzhpo == zzax.zzhpo && zzbg.equal(this.zzekk, zzax.zzekk)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzekk, Integer.valueOf(this.zzhpo)});
    }

    public final String toString() {
        return zzbg.zzx(this).zzg("pendingIntent", this.zzekk).zzg("sessionRegistrationOption", Integer.valueOf(this.zzhpo)).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) this.zzekk, i, false);
        zzbzt zzbzt = this.zzhnu;
        zzbgo.zza(parcel, 2, zzbzt == null ? null : zzbzt.asBinder(), false);
        zzbgo.zzc(parcel, 4, this.zzhpo);
        zzbgo.zzai(parcel, zze);
    }
}
