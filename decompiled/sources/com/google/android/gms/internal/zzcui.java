package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import java.util.Arrays;

public final class zzcui extends zzbgl {
    public static final Parcelable.Creator<zzcui> CREATOR = new zzcuj();
    private zzcst zzjxd;
    private zzcub zzjzj;
    private String[] zzkaa;
    private boolean zzkab;

    private zzcui() {
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [android.os.IInterface] */
    /* JADX WARNING: Illegal instructions before constructor call */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzcui(android.os.IBinder r3, java.lang.String[] r4, com.google.android.gms.internal.zzcub r5, boolean r6) {
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
            r2.<init>((com.google.android.gms.internal.zzcst) r3, (java.lang.String[]) r4, (com.google.android.gms.internal.zzcub) r5, (boolean) r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcui.<init>(android.os.IBinder, java.lang.String[], com.google.android.gms.internal.zzcub, boolean):void");
    }

    private zzcui(zzcst zzcst, String[] strArr, zzcub zzcub, boolean z) {
        this.zzjxd = zzcst;
        this.zzkaa = strArr;
        this.zzjzj = zzcub;
        this.zzkab = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzcui) {
            zzcui zzcui = (zzcui) obj;
            return zzbg.equal(this.zzjxd, zzcui.zzjxd) && Arrays.equals(this.zzkaa, zzcui.zzkaa) && zzbg.equal(this.zzjzj, zzcui.zzjzj) && zzbg.equal(Boolean.valueOf(this.zzkab), Boolean.valueOf(zzcui.zzkab));
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzjxd, Integer.valueOf(Arrays.hashCode(this.zzkaa)), this.zzjzj, Boolean.valueOf(this.zzkab)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzcst zzcst = this.zzjxd;
        zzbgo.zza(parcel, 1, zzcst == null ? null : zzcst.asBinder(), false);
        zzbgo.zza(parcel, 2, this.zzkaa, false);
        zzbgo.zza(parcel, 3, (Parcelable) this.zzjzj, i, false);
        zzbgo.zza(parcel, 4, this.zzkab);
        zzbgo.zzai(parcel, zze);
    }
}
