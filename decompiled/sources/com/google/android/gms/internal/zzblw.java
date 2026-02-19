package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.drive.DriveId;
import java.util.Arrays;

public final class zzblw extends zzbgl {
    public static final Parcelable.Creator<zzblw> CREATOR = new zzblx();
    final int zzcfl;
    final DriveId zzgpe;
    final int zzgsj;
    final long zzgsm;
    final long zzgsn;

    public zzblw(int i, DriveId driveId, int i2, long j, long j2) {
        this.zzgsj = i;
        this.zzgpe = driveId;
        this.zzcfl = i2;
        this.zzgsm = j;
        this.zzgsn = j2;
    }

    public final boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            if (obj == this) {
                return true;
            }
            zzblw zzblw = (zzblw) obj;
            return this.zzgsj == zzblw.zzgsj && zzbg.equal(this.zzgpe, zzblw.zzgpe) && this.zzcfl == zzblw.zzcfl && this.zzgsm == zzblw.zzgsm && this.zzgsn == zzblw.zzgsn;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zzgsj), this.zzgpe, Integer.valueOf(this.zzcfl), Long.valueOf(this.zzgsm), Long.valueOf(this.zzgsn)});
    }

    public final String toString() {
        return String.format("TransferProgressData[TransferType: %d, DriveId: %s, status: %d, bytes transferred: %d, total bytes: %d]", new Object[]{Integer.valueOf(this.zzgsj), this.zzgpe, Integer.valueOf(this.zzcfl), Long.valueOf(this.zzgsm), Long.valueOf(this.zzgsn)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, this.zzgsj);
        zzbgo.zza(parcel, 3, (Parcelable) this.zzgpe, i, false);
        zzbgo.zzc(parcel, 4, this.zzcfl);
        zzbgo.zza(parcel, 5, this.zzgsm);
        zzbgo.zza(parcel, 6, this.zzgsn);
        zzbgo.zzai(parcel, zze);
    }
}
