package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public final class zzb extends zzbgl {
    public static final Parcelable.Creator<zzb> CREATOR = new zzc();
    public static final zzb zzhhw = new zzb("com.google.android.gms", (String) null);
    private final String packageName;
    private final String zzhhx;

    public zzb(String str, String str2) {
        this.packageName = (String) zzbq.checkNotNull(str);
        this.zzhhx = str2;
    }

    public static zzb zzhl(String str) {
        return "com.google.android.gms".equals(str) ? zzhhw : new zzb(str, (String) null);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzb)) {
            return false;
        }
        zzb zzb = (zzb) obj;
        return this.packageName.equals(zzb.packageName) && zzbg.equal(this.zzhhx, zzb.zzhhx);
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.packageName, this.zzhhx});
    }

    public final String toString() {
        return String.format("Application{%s:%s}", new Object[]{this.packageName, this.zzhhx});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.packageName, false);
        zzbgo.zza(parcel, 3, this.zzhhx, false);
        zzbgo.zzai(parcel, zze);
    }
}
