package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.nearby.connection.DiscoveryOptions;
import java.util.Arrays;

public final class zzcum extends zzbgl {
    public static final Parcelable.Creator<zzcum> CREATOR = new zzcun();
    private long durationMillis;
    private String zzjwh;
    private zzcst zzjxd;
    private zzcsj zzkaf;
    private DiscoveryOptions zzkag;
    private zzcsl zzkah;

    private zzcum() {
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzcum(android.os.IBinder r14, android.os.IBinder r15, java.lang.String r16, long r17, com.google.android.gms.nearby.connection.DiscoveryOptions r19, android.os.IBinder r20) {
        /*
            r13 = this;
            r0 = r14
            r1 = r15
            r2 = r20
            r3 = 0
            if (r0 != 0) goto L_0x0009
            r6 = r3
            goto L_0x001c
        L_0x0009:
            java.lang.String r4 = "com.google.android.gms.nearby.internal.connection.IResultListener"
            android.os.IInterface r4 = r14.queryLocalInterface(r4)
            boolean r5 = r4 instanceof com.google.android.gms.internal.zzcst
            if (r5 == 0) goto L_0x0016
            com.google.android.gms.internal.zzcst r4 = (com.google.android.gms.internal.zzcst) r4
            goto L_0x001b
        L_0x0016:
            com.google.android.gms.internal.zzcsv r4 = new com.google.android.gms.internal.zzcsv
            r4.<init>(r14)
        L_0x001b:
            r6 = r4
        L_0x001c:
            if (r1 != 0) goto L_0x0020
            r7 = r3
            goto L_0x0033
        L_0x0020:
            java.lang.String r0 = "com.google.android.gms.nearby.internal.connection.IDiscoveryCallback"
            android.os.IInterface r0 = r15.queryLocalInterface(r0)
            boolean r4 = r0 instanceof com.google.android.gms.internal.zzcsj
            if (r4 == 0) goto L_0x002d
            com.google.android.gms.internal.zzcsj r0 = (com.google.android.gms.internal.zzcsj) r0
            goto L_0x0032
        L_0x002d:
            com.google.android.gms.internal.zzcsk r0 = new com.google.android.gms.internal.zzcsk
            r0.<init>(r15)
        L_0x0032:
            r7 = r0
        L_0x0033:
            if (r2 != 0) goto L_0x0037
        L_0x0035:
            r12 = r3
            goto L_0x004b
        L_0x0037:
            java.lang.String r0 = "com.google.android.gms.nearby.internal.connection.IDiscoveryListener"
            android.os.IInterface r0 = r2.queryLocalInterface(r0)
            boolean r1 = r0 instanceof com.google.android.gms.internal.zzcsl
            if (r1 == 0) goto L_0x0045
            r3 = r0
            com.google.android.gms.internal.zzcsl r3 = (com.google.android.gms.internal.zzcsl) r3
            goto L_0x0035
        L_0x0045:
            com.google.android.gms.internal.zzcsn r3 = new com.google.android.gms.internal.zzcsn
            r3.<init>(r2)
            goto L_0x0035
        L_0x004b:
            r5 = r13
            r8 = r16
            r9 = r17
            r11 = r19
            r5.<init>((com.google.android.gms.internal.zzcst) r6, (com.google.android.gms.internal.zzcsj) r7, (java.lang.String) r8, (long) r9, (com.google.android.gms.nearby.connection.DiscoveryOptions) r11, (com.google.android.gms.internal.zzcsl) r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcum.<init>(android.os.IBinder, android.os.IBinder, java.lang.String, long, com.google.android.gms.nearby.connection.DiscoveryOptions, android.os.IBinder):void");
    }

    private zzcum(zzcst zzcst, zzcsj zzcsj, String str, long j, DiscoveryOptions discoveryOptions, zzcsl zzcsl) {
        this.zzjxd = zzcst;
        this.zzkaf = zzcsj;
        this.zzjwh = str;
        this.durationMillis = j;
        this.zzkag = discoveryOptions;
        this.zzkah = zzcsl;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzcum) {
            zzcum zzcum = (zzcum) obj;
            return zzbg.equal(this.zzjxd, zzcum.zzjxd) && zzbg.equal(this.zzkaf, zzcum.zzkaf) && zzbg.equal(this.zzjwh, zzcum.zzjwh) && zzbg.equal(Long.valueOf(this.durationMillis), Long.valueOf(zzcum.durationMillis)) && zzbg.equal(this.zzkag, zzcum.zzkag) && zzbg.equal(this.zzkah, zzcum.zzkah);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzjxd, this.zzkaf, this.zzjwh, Long.valueOf(this.durationMillis), this.zzkag, this.zzkah});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzcst zzcst = this.zzjxd;
        IBinder iBinder = null;
        zzbgo.zza(parcel, 1, zzcst == null ? null : zzcst.asBinder(), false);
        zzcsj zzcsj = this.zzkaf;
        zzbgo.zza(parcel, 2, zzcsj == null ? null : zzcsj.asBinder(), false);
        zzbgo.zza(parcel, 3, this.zzjwh, false);
        zzbgo.zza(parcel, 4, this.durationMillis);
        zzbgo.zza(parcel, 5, (Parcelable) this.zzkag, i, false);
        zzcsl zzcsl = this.zzkah;
        if (zzcsl != null) {
            iBinder = zzcsl.asBinder();
        }
        zzbgo.zza(parcel, 6, iBinder, false);
        zzbgo.zzai(parcel, zze);
    }
}
