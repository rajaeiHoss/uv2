package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import java.util.Arrays;

public final class zzcor extends zzbgl {
    public static final Parcelable.Creator<zzcor> CREATOR = new zzcos();
    private long zzjwr;
    private zzcst zzjxd;

    private zzcor() {
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [android.os.IInterface] */
    /* JADX WARNING: Illegal instructions before constructor call */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzcor(android.os.IBinder r3, long r4) {
        /*
            r2 = this;
            if (r3 != 0) goto L_0x0004
            r3 = 0
            goto L_0x0018
        L_0x0004:
            java.lang.String r0 = "com.google.android.gms.nearby.internal.connection.IResultListener"
            android.os.IInterface r0 = r3.queryLocalInterface(r0)
            boolean r1 = r0 instanceof com.google.android.gms.internal.zzcst
            if (r1 == 0) goto L_0x0012
            r3 = r0
            com.google.android.gms.internal.zzcst r3 = (com.google.android.gms.internal.zzcst) r3
            goto L_0x0018
        L_0x0012:
            com.google.android.gms.internal.zzcsv r0 = new com.google.android.gms.internal.zzcsv
            r0.<init>(r3)
            r3 = r0
        L_0x0018:
            r2.<init>((com.google.android.gms.internal.zzcst) r3, (long) r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcor.<init>(android.os.IBinder, long):void");
    }

    private zzcor(zzcst zzcst, long j) {
        this.zzjxd = zzcst;
        this.zzjwr = j;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzcor) {
            zzcor zzcor = (zzcor) obj;
            return zzbg.equal(this.zzjxd, zzcor.zzjxd) && zzbg.equal(Long.valueOf(this.zzjwr), Long.valueOf(zzcor.zzjwr));
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzjxd, Long.valueOf(this.zzjwr)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzcst zzcst = this.zzjxd;
        zzbgo.zza(parcel, 1, zzcst == null ? null : zzcst.asBinder(), false);
        zzbgo.zza(parcel, 2, this.zzjwr);
        zzbgo.zzai(parcel, zze);
    }
}
