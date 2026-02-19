package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.drive.DriveSpace;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public final class zzx extends zzbgl {
    public static final Parcelable.Creator<zzx> CREATOR = new zzy();
    private List<DriveSpace> zzgrq;
    private final Set<DriveSpace> zzgrr;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    zzx(List<DriveSpace> list) {
        this(list, list == null ? Collections.emptySet() : new HashSet(list));
    }

    private zzx(List<DriveSpace> list, Set<DriveSpace> set) {
        this.zzgrq = list;
        this.zzgrr = set;
    }

    public final boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        return zzbg.equal(this.zzgrr, ((zzx) obj).zzgrr);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzgrr});
    }

    public final String toString() {
        return String.format(Locale.US, "TransferStateOptions[Spaces=%s]", new Object[]{this.zzgrq});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, this.zzgrq, false);
        zzbgo.zzai(parcel, zze);
    }
}
