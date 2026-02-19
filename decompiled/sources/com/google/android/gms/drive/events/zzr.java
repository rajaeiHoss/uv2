package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzblw;
import java.util.Arrays;

public final class zzr extends zzbgl implements DriveEvent {
    public static final Parcelable.Creator<zzr> CREATOR = new zzs();
    private zzblw zzgsi;

    public zzr(zzblw zzblw) {
        this.zzgsi = zzblw;
    }

    public final boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        return zzbg.equal(this.zzgsi, ((zzr) obj).zzgsi);
    }

    public final int getType() {
        return 8;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzgsi});
    }

    public final String toString() {
        return String.format("TransferProgressEvent[%s]", new Object[]{this.zzgsi.toString()});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzgsi, i, false);
        zzbgo.zzai(parcel, zze);
    }

    public final zzblw zzaqh() {
        return this.zzgsi;
    }
}
