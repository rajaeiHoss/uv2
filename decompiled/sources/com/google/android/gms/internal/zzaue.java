package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import java.util.Arrays;

public final class zzaue extends zzbgl {
    public static final Parcelable.Creator<zzaue> CREATOR = new zzauf();
    private int id;
    private Bundle zzefr;

    zzaue(int i, Bundle bundle) {
        this.id = i;
        this.zzefr = bundle;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzaue) {
            zzaue zzaue = (zzaue) obj;
            return zzbg.equal(Integer.valueOf(zzaue.id), Integer.valueOf(this.id)) && zzbg.equal(zzaue.zzefr, this.zzefr);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.id), this.zzefr});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.id);
        zzbgo.zza(parcel, 2, this.zzefr, false);
        zzbgo.zzai(parcel, zze);
    }
}
