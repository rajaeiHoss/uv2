package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public final class zzab extends zzbgl {
    public static final Parcelable.Creator<zzab> CREATOR = new zzac();
    private int zzevv;

    public zzab() {
        this(0);
    }

    zzab(int i) {
        this.zzevv = i;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof zzab) && this.zzevv == ((zzab) obj).zzevv;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zzevv)});
    }

    public final String toString() {
        int i = this.zzevv;
        return String.format("joinOptions(connectionType=%s)", new Object[]{i != 0 ? i != 2 ? "UNKNOWN" : "INVISIBLE" : "STRONG"});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, this.zzevv);
        zzbgo.zzai(parcel, zze);
    }
}
