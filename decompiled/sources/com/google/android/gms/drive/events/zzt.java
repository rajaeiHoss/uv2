package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;
import java.util.Locale;

public final class zzt extends zzbgl {
    public static final Parcelable.Creator<zzt> CREATOR = new zzu();
    private int zzgsj;

    public zzt(int i) {
        this.zzgsj = i;
    }

    public final boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        return zzbg.equal(Integer.valueOf(this.zzgsj), Integer.valueOf(((zzt) obj).zzgsj));
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zzgsj)});
    }

    public final String toString() {
        return String.format(Locale.US, "TransferProgressOptions[type=%d]", new Object[]{Integer.valueOf(this.zzgsj)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, this.zzgsj);
        zzbgo.zzai(parcel, zze);
    }
}
