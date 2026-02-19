package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import java.util.Arrays;

public final class zzcop extends zzbgl {
    public static final Parcelable.Creator<zzcop> CREATOR = new zzcoq();
    private zzcst zzjxd;
    private zzcsa zzjxe;
    private String zzjxf;
    private byte[] zzjxg;
    private zzcsq zzjxh;

    private zzcop() {
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzcop(android.os.IBinder r9, android.os.IBinder r10, java.lang.String r11, byte[] r12, android.os.IBinder r13) {
        /*
            r8 = this;
            r0 = 0
            if (r9 != 0) goto L_0x0005
            r3 = r0
            goto L_0x0018
        L_0x0005:
            java.lang.String r1 = "com.google.android.gms.nearby.internal.connection.IResultListener"
            android.os.IInterface r1 = r9.queryLocalInterface(r1)
            boolean r2 = r1 instanceof com.google.android.gms.internal.zzcst
            if (r2 == 0) goto L_0x0012
            com.google.android.gms.internal.zzcst r1 = (com.google.android.gms.internal.zzcst) r1
            goto L_0x0017
        L_0x0012:
            com.google.android.gms.internal.zzcsv r1 = new com.google.android.gms.internal.zzcsv
            r1.<init>(r9)
        L_0x0017:
            r3 = r1
        L_0x0018:
            if (r10 != 0) goto L_0x001c
            r4 = r0
            goto L_0x002f
        L_0x001c:
            java.lang.String r9 = "com.google.android.gms.nearby.internal.connection.IConnectionEventListener"
            android.os.IInterface r9 = r10.queryLocalInterface(r9)
            boolean r1 = r9 instanceof com.google.android.gms.internal.zzcsa
            if (r1 == 0) goto L_0x0029
            com.google.android.gms.internal.zzcsa r9 = (com.google.android.gms.internal.zzcsa) r9
            goto L_0x002e
        L_0x0029:
            com.google.android.gms.internal.zzcsc r9 = new com.google.android.gms.internal.zzcsc
            r9.<init>(r10)
        L_0x002e:
            r4 = r9
        L_0x002f:
            if (r13 != 0) goto L_0x0033
        L_0x0031:
            r7 = r0
            goto L_0x0047
        L_0x0033:
            java.lang.String r9 = "com.google.android.gms.nearby.internal.connection.IPayloadListener"
            android.os.IInterface r9 = r13.queryLocalInterface(r9)
            boolean r10 = r9 instanceof com.google.android.gms.internal.zzcsq
            if (r10 == 0) goto L_0x0041
            r0 = r9
            com.google.android.gms.internal.zzcsq r0 = (com.google.android.gms.internal.zzcsq) r0
            goto L_0x0031
        L_0x0041:
            com.google.android.gms.internal.zzcss r0 = new com.google.android.gms.internal.zzcss
            r0.<init>(r13)
            goto L_0x0031
        L_0x0047:
            r2 = r8
            r5 = r11
            r6 = r12
            r2.<init>((com.google.android.gms.internal.zzcst) r3, (com.google.android.gms.internal.zzcsa) r4, (java.lang.String) r5, (byte[]) r6, (com.google.android.gms.internal.zzcsq) r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcop.<init>(android.os.IBinder, android.os.IBinder, java.lang.String, byte[], android.os.IBinder):void");
    }

    private zzcop(zzcst zzcst, zzcsa zzcsa, String str, byte[] bArr, zzcsq zzcsq) {
        this.zzjxd = zzcst;
        this.zzjxe = zzcsa;
        this.zzjxf = str;
        this.zzjxg = bArr;
        this.zzjxh = zzcsq;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzcop) {
            zzcop zzcop = (zzcop) obj;
            return zzbg.equal(this.zzjxd, zzcop.zzjxd) && zzbg.equal(this.zzjxe, zzcop.zzjxe) && zzbg.equal(this.zzjxf, zzcop.zzjxf) && Arrays.equals(this.zzjxg, zzcop.zzjxg) && zzbg.equal(this.zzjxh, zzcop.zzjxh);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzjxd, this.zzjxe, this.zzjxf, Integer.valueOf(Arrays.hashCode(this.zzjxg)), this.zzjxh});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzcst zzcst = this.zzjxd;
        IBinder iBinder = null;
        zzbgo.zza(parcel, 1, zzcst == null ? null : zzcst.asBinder(), false);
        zzcsa zzcsa = this.zzjxe;
        zzbgo.zza(parcel, 2, zzcsa == null ? null : zzcsa.asBinder(), false);
        zzbgo.zza(parcel, 3, this.zzjxf, false);
        zzbgo.zza(parcel, 4, this.zzjxg, false);
        zzcsq zzcsq = this.zzjxh;
        if (zzcsq != null) {
            iBinder = zzcsq.asBinder();
        }
        zzbgo.zza(parcel, 5, iBinder, false);
        zzbgo.zzai(parcel, zze);
    }
}
