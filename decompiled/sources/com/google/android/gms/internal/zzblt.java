package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.drive.events.zzk;
import com.google.android.gms.drive.events.zzm;
import java.util.Arrays;

public final class zzblt implements zzk {
    private final zzm zzgsl;
    private final long zzgsm;
    private final long zzgsn;

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.android.gms.drive.events.zzm, com.google.android.gms.internal.zzblu] */
    public zzblt(zzblw zzblw) {
        this.zzgsl = new zzblu(zzblw);
        this.zzgsm = zzblw.zzgsm;
        this.zzgsn = zzblw.zzgsn;
    }

    public final boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            if (obj == this) {
                return true;
            }
            zzblt zzblt = (zzblt) obj;
            return zzbg.equal(this.zzgsl, zzblt.zzgsl) && this.zzgsm == zzblt.zzgsm && this.zzgsn == zzblt.zzgsn;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.zzgsn), Long.valueOf(this.zzgsm), Long.valueOf(this.zzgsn)});
    }

    public final String toString() {
        return String.format("FileTransferProgress[FileTransferState: %s, BytesTransferred: %d, TotalBytes: %d]", new Object[]{this.zzgsl.toString(), Long.valueOf(this.zzgsm), Long.valueOf(this.zzgsn)});
    }
}
