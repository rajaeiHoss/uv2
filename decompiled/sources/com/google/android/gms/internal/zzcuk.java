package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.nearby.connection.AdvertisingOptions;
import java.util.Arrays;

public final class zzcuk extends zzbgl {
    public static final Parcelable.Creator<zzcuk> CREATOR = new zzcul();
    private long durationMillis;
    private String name;
    private String zzjwh;
    private zzcsd zzjzz;
    private zzcsw zzkac;
    private zzcrx zzkad;
    private AdvertisingOptions zzkae;

    private zzcuk() {
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzcuk(android.os.IBinder r15, android.os.IBinder r16, java.lang.String r17, java.lang.String r18, long r19, com.google.android.gms.nearby.connection.AdvertisingOptions r21, android.os.IBinder r22) {
        /*
            r14 = this;
            r0 = r15
            r1 = r16
            r2 = r22
            r3 = 0
            if (r0 != 0) goto L_0x000a
            r6 = r3
            goto L_0x001d
        L_0x000a:
            java.lang.String r4 = "com.google.android.gms.nearby.internal.connection.IStartAdvertisingResultListener"
            android.os.IInterface r4 = r15.queryLocalInterface(r4)
            boolean r5 = r4 instanceof com.google.android.gms.internal.zzcsw
            if (r5 == 0) goto L_0x0017
            com.google.android.gms.internal.zzcsw r4 = (com.google.android.gms.internal.zzcsw) r4
            goto L_0x001c
        L_0x0017:
            com.google.android.gms.internal.zzcsy r4 = new com.google.android.gms.internal.zzcsy
            r4.<init>(r15)
        L_0x001c:
            r6 = r4
        L_0x001d:
            if (r1 != 0) goto L_0x0021
            r7 = r3
            goto L_0x0034
        L_0x0021:
            java.lang.String r0 = "com.google.android.gms.nearby.internal.connection.IAdvertisingCallback"
            android.os.IInterface r0 = r1.queryLocalInterface(r0)
            boolean r4 = r0 instanceof com.google.android.gms.internal.zzcrx
            if (r4 == 0) goto L_0x002e
            com.google.android.gms.internal.zzcrx r0 = (com.google.android.gms.internal.zzcrx) r0
            goto L_0x0033
        L_0x002e:
            com.google.android.gms.internal.zzcrz r0 = new com.google.android.gms.internal.zzcrz
            r0.<init>(r1)
        L_0x0033:
            r7 = r0
        L_0x0034:
            if (r2 != 0) goto L_0x0038
        L_0x0036:
            r13 = r3
            goto L_0x004c
        L_0x0038:
            java.lang.String r0 = "com.google.android.gms.nearby.internal.connection.IConnectionLifecycleListener"
            android.os.IInterface r0 = r2.queryLocalInterface(r0)
            boolean r1 = r0 instanceof com.google.android.gms.internal.zzcsd
            if (r1 == 0) goto L_0x0046
            r3 = r0
            com.google.android.gms.internal.zzcsd r3 = (com.google.android.gms.internal.zzcsd) r3
            goto L_0x0036
        L_0x0046:
            com.google.android.gms.internal.zzcsf r3 = new com.google.android.gms.internal.zzcsf
            r3.<init>(r2)
            goto L_0x0036
        L_0x004c:
            r5 = r14
            r8 = r17
            r9 = r18
            r10 = r19
            r12 = r21
            r5.<init>((com.google.android.gms.internal.zzcsw) r6, (com.google.android.gms.internal.zzcrx) r7, (java.lang.String) r8, (java.lang.String) r9, (long) r10, (com.google.android.gms.nearby.connection.AdvertisingOptions) r12, (com.google.android.gms.internal.zzcsd) r13)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcuk.<init>(android.os.IBinder, android.os.IBinder, java.lang.String, java.lang.String, long, com.google.android.gms.nearby.connection.AdvertisingOptions, android.os.IBinder):void");
    }

    private zzcuk(zzcsw zzcsw, zzcrx zzcrx, String str, String str2, long j, AdvertisingOptions advertisingOptions, zzcsd zzcsd) {
        this.zzkac = zzcsw;
        this.zzkad = zzcrx;
        this.name = str;
        this.zzjwh = str2;
        this.durationMillis = j;
        this.zzkae = advertisingOptions;
        this.zzjzz = zzcsd;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzcuk) {
            zzcuk zzcuk = (zzcuk) obj;
            return zzbg.equal(this.zzkac, zzcuk.zzkac) && zzbg.equal(this.zzkad, zzcuk.zzkad) && zzbg.equal(this.name, zzcuk.name) && zzbg.equal(this.zzjwh, zzcuk.zzjwh) && zzbg.equal(Long.valueOf(this.durationMillis), Long.valueOf(zzcuk.durationMillis)) && zzbg.equal(this.zzkae, zzcuk.zzkae) && zzbg.equal(this.zzjzz, zzcuk.zzjzz);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzkac, this.zzkad, this.name, this.zzjwh, Long.valueOf(this.durationMillis), this.zzkae, this.zzjzz});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzcsw zzcsw = this.zzkac;
        IBinder iBinder = null;
        zzbgo.zza(parcel, 1, zzcsw == null ? null : zzcsw.asBinder(), false);
        zzcrx zzcrx = this.zzkad;
        zzbgo.zza(parcel, 2, zzcrx == null ? null : zzcrx.asBinder(), false);
        zzbgo.zza(parcel, 3, this.name, false);
        zzbgo.zza(parcel, 4, this.zzjwh, false);
        zzbgo.zza(parcel, 5, this.durationMillis);
        zzbgo.zza(parcel, 6, (Parcelable) this.zzkae, i, false);
        zzcsd zzcsd = this.zzjzz;
        if (zzcsd != null) {
            iBinder = zzcsd.asBinder();
        }
        zzbgo.zza(parcel, 7, iBinder, false);
        zzbgo.zzai(parcel, zze);
    }
}
