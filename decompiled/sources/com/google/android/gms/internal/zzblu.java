package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.events.zzm;
import java.util.Arrays;

public final class zzblu implements zzm {
    private final int zzcfl;
    private final DriveId zzgpe;
    private final int zzgsj;

    public zzblu(zzblw zzblw) {
        this.zzgpe = zzblw.zzgpe;
        this.zzgsj = zzblw.zzgsj;
        this.zzcfl = zzblw.zzcfl;
    }

    public final boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            if (obj == this) {
                return true;
            }
            zzblu zzblu = (zzblu) obj;
            return zzbg.equal(this.zzgpe, zzblu.zzgpe) && this.zzgsj == zzblu.zzgsj && this.zzcfl == zzblu.zzcfl;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzgpe, Integer.valueOf(this.zzgsj), Integer.valueOf(this.zzcfl)});
    }

    public final String toString() {
        return String.format("FileTransferState[TransferType: %d, DriveId: %s, status: %d]", new Object[]{Integer.valueOf(this.zzgsj), this.zzgpe, Integer.valueOf(this.zzcfl)});
    }
}
