package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbzt;

public final class zzbh extends zzbgl {
    public static final Parcelable.Creator<zzbh> CREATOR = new zzbi();
    private final zzbzt zzhnu;
    private final zzae zzhpq;

    /* JADX WARNING: type inference failed for: r0v1, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    zzbh(android.os.IBinder r3, android.os.IBinder r4) {
        /*
            r2 = this;
            r2.<init>()
            if (r3 != 0) goto L_0x0007
            r3 = 0
            goto L_0x001b
        L_0x0007:
            java.lang.String r0 = "com.google.android.gms.fitness.request.IBleScanCallback"
            android.os.IInterface r0 = r3.queryLocalInterface(r0)
            boolean r1 = r0 instanceof com.google.android.gms.fitness.request.zzae
            if (r1 == 0) goto L_0x0015
            r3 = r0
            com.google.android.gms.fitness.request.zzae r3 = (com.google.android.gms.fitness.request.zzae) r3
            goto L_0x001b
        L_0x0015:
            com.google.android.gms.fitness.request.zzag r0 = new com.google.android.gms.fitness.request.zzag
            r0.<init>(r3)
            r3 = r0
        L_0x001b:
            r2.zzhpq = r3
            com.google.android.gms.internal.zzbzt r3 = com.google.android.gms.internal.zzbzu.zzba(r4)
            r2.zzhnu = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fitness.request.zzbh.<init>(android.os.IBinder, android.os.IBinder):void");
    }

    public zzbh(BleScanCallback bleScanCallback, zzbzt zzbzt) {
        this((zzae) zzd.zzasf().zzb(bleScanCallback), zzbzt);
    }

    public zzbh(zzae zzae, zzbzt zzbzt) {
        this.zzhpq = zzae;
        this.zzhnu = zzbzt;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzhpq.asBinder(), false);
        zzbzt zzbzt = this.zzhnu;
        zzbgo.zza(parcel, 2, zzbzt == null ? null : zzbzt.asBinder(), false);
        zzbgo.zzai(parcel, zze);
    }
}
