package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import java.util.Arrays;

public final class zzcug extends zzbgl {
    public static final Parcelable.Creator<zzcug> CREATOR = new zzcuh();
    private String name;
    private zzcst zzjxd;
    private zzcsa zzjxe;
    private String zzjxf;
    private byte[] zzjxg;
    private zzcsg zzjzy;
    private zzcsd zzjzz;

    private zzcug() {
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzcug(android.os.IBinder r15, android.os.IBinder r16, android.os.IBinder r17, java.lang.String r18, java.lang.String r19, byte[] r20, android.os.IBinder r21) {
        /*
            r14 = this;
            r0 = r15
            r1 = r16
            r2 = r17
            r3 = r21
            r4 = 0
            if (r0 != 0) goto L_0x000c
            r7 = r4
            goto L_0x001f
        L_0x000c:
            java.lang.String r5 = "com.google.android.gms.nearby.internal.connection.IResultListener"
            android.os.IInterface r5 = r15.queryLocalInterface(r5)
            boolean r6 = r5 instanceof com.google.android.gms.internal.zzcst
            if (r6 == 0) goto L_0x0019
            com.google.android.gms.internal.zzcst r5 = (com.google.android.gms.internal.zzcst) r5
            goto L_0x001e
        L_0x0019:
            com.google.android.gms.internal.zzcsv r5 = new com.google.android.gms.internal.zzcsv
            r5.<init>(r15)
        L_0x001e:
            r7 = r5
        L_0x001f:
            if (r1 != 0) goto L_0x0023
            r8 = r4
            goto L_0x0036
        L_0x0023:
            java.lang.String r0 = "com.google.android.gms.nearby.internal.connection.IConnectionEventListener"
            android.os.IInterface r0 = r1.queryLocalInterface(r0)
            boolean r5 = r0 instanceof com.google.android.gms.internal.zzcsa
            if (r5 == 0) goto L_0x0030
            com.google.android.gms.internal.zzcsa r0 = (com.google.android.gms.internal.zzcsa) r0
            goto L_0x0035
        L_0x0030:
            com.google.android.gms.internal.zzcsc r0 = new com.google.android.gms.internal.zzcsc
            r0.<init>(r1)
        L_0x0035:
            r8 = r0
        L_0x0036:
            if (r2 != 0) goto L_0x003a
            r9 = r4
            goto L_0x004d
        L_0x003a:
            java.lang.String r0 = "com.google.android.gms.nearby.internal.connection.IConnectionResponseListener"
            android.os.IInterface r0 = r2.queryLocalInterface(r0)
            boolean r1 = r0 instanceof com.google.android.gms.internal.zzcsg
            if (r1 == 0) goto L_0x0047
            com.google.android.gms.internal.zzcsg r0 = (com.google.android.gms.internal.zzcsg) r0
            goto L_0x004c
        L_0x0047:
            com.google.android.gms.internal.zzcsi r0 = new com.google.android.gms.internal.zzcsi
            r0.<init>(r2)
        L_0x004c:
            r9 = r0
        L_0x004d:
            if (r3 != 0) goto L_0x0051
        L_0x004f:
            r13 = r4
            goto L_0x0065
        L_0x0051:
            java.lang.String r0 = "com.google.android.gms.nearby.internal.connection.IConnectionLifecycleListener"
            android.os.IInterface r0 = r3.queryLocalInterface(r0)
            boolean r1 = r0 instanceof com.google.android.gms.internal.zzcsd
            if (r1 == 0) goto L_0x005f
            r4 = r0
            com.google.android.gms.internal.zzcsd r4 = (com.google.android.gms.internal.zzcsd) r4
            goto L_0x004f
        L_0x005f:
            com.google.android.gms.internal.zzcsf r4 = new com.google.android.gms.internal.zzcsf
            r4.<init>(r3)
            goto L_0x004f
        L_0x0065:
            r6 = r14
            r10 = r18
            r11 = r19
            r12 = r20
            r6.<init>((com.google.android.gms.internal.zzcst) r7, (com.google.android.gms.internal.zzcsa) r8, (com.google.android.gms.internal.zzcsg) r9, (java.lang.String) r10, (java.lang.String) r11, (byte[]) r12, (com.google.android.gms.internal.zzcsd) r13)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcug.<init>(android.os.IBinder, android.os.IBinder, android.os.IBinder, java.lang.String, java.lang.String, byte[], android.os.IBinder):void");
    }

    private zzcug(zzcst zzcst, zzcsa zzcsa, zzcsg zzcsg, String str, String str2, byte[] bArr, zzcsd zzcsd) {
        this.zzjxd = zzcst;
        this.zzjxe = zzcsa;
        this.zzjzy = zzcsg;
        this.name = str;
        this.zzjxf = str2;
        this.zzjxg = bArr;
        this.zzjzz = zzcsd;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzcug) {
            zzcug zzcug = (zzcug) obj;
            return zzbg.equal(this.zzjxd, zzcug.zzjxd) && zzbg.equal(this.zzjxe, zzcug.zzjxe) && zzbg.equal(this.zzjzy, zzcug.zzjzy) && zzbg.equal(this.name, zzcug.name) && zzbg.equal(this.zzjxf, zzcug.zzjxf) && Arrays.equals(this.zzjxg, zzcug.zzjxg) && zzbg.equal(this.zzjzz, zzcug.zzjzz);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzjxd, this.zzjxe, this.zzjzy, this.name, this.zzjxf, Integer.valueOf(Arrays.hashCode(this.zzjxg)), this.zzjzz});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzcst zzcst = this.zzjxd;
        IBinder iBinder = null;
        zzbgo.zza(parcel, 1, zzcst == null ? null : zzcst.asBinder(), false);
        zzcsa zzcsa = this.zzjxe;
        zzbgo.zza(parcel, 2, zzcsa == null ? null : zzcsa.asBinder(), false);
        zzcsg zzcsg = this.zzjzy;
        zzbgo.zza(parcel, 3, zzcsg == null ? null : zzcsg.asBinder(), false);
        zzbgo.zza(parcel, 4, this.name, false);
        zzbgo.zza(parcel, 5, this.zzjxf, false);
        zzbgo.zza(parcel, 6, this.zzjxg, false);
        zzcsd zzcsd = this.zzjzz;
        if (zzcsd != null) {
            iBinder = zzcsd.asBinder();
        }
        zzbgo.zza(parcel, 7, iBinder, false);
        zzbgo.zzai(parcel, zze);
    }
}
