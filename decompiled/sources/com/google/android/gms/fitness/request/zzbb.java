package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbzq;
import com.google.android.gms.internal.zzbzr;
import java.util.Arrays;

public final class zzbb extends zzbgl {
    public static final Parcelable.Creator<zzbb> CREATOR = new zzbc();
    private final String name;
    private final String zzhlo;
    private final zzbzq zzhpp;

    zzbb(String str, String str2, IBinder iBinder) {
        this.name = str;
        this.zzhlo = str2;
        this.zzhpp = zzbzr.zzaz(iBinder);
    }

    public zzbb(String str, String str2, zzbzq zzbzq) {
        this.name = str;
        this.zzhlo = str2;
        this.zzhpp = zzbzq;
    }

    public final boolean equals(Object obj) {
        if (obj != this) {
            if (obj instanceof zzbb) {
                zzbb zzbb = (zzbb) obj;
                if (zzbg.equal(this.name, zzbb.name) && zzbg.equal(this.zzhlo, zzbb.zzhlo)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.name, this.zzhlo});
    }

    public final String toString() {
        return zzbg.zzx(this).zzg("name", this.name).zzg("identifier", this.zzhlo).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.name, false);
        zzbgo.zza(parcel, 2, this.zzhlo, false);
        zzbzq zzbzq = this.zzhpp;
        zzbgo.zza(parcel, 3, zzbzq == null ? null : zzbzq.asBinder(), false);
        zzbgo.zzai(parcel, zze);
    }
}
