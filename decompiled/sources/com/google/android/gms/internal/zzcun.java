package com.google.android.gms.internal;

import android.os.Parcelable;

public final class zzcun implements Parcelable.Creator<zzcum> {
    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final zzcum createFromParcel(android.os.Parcel r13) {
        /*
            r12 = this;
            int r0 = com.google.android.gms.internal.zzbgm.zzd(r13)
            r1 = 0
            r2 = 0
            r5 = r1
            r6 = r5
            r7 = r6
            r10 = r7
            r11 = r10
            r8 = r2
        L_0x000d:
            int r1 = r13.dataPosition()
            if (r1 >= r0) goto L_0x0045
            int r1 = r13.readInt()
            r2 = 65535(0xffff, float:9.1834E-41)
            r2 = r2 & r1
            switch(r2) {
                case 1: goto L_0x0040;
                case 2: goto L_0x003b;
                case 3: goto L_0x0036;
                case 4: goto L_0x0031;
                case 5: goto L_0x0027;
                case 6: goto L_0x0022;
                default: goto L_0x001e;
            }
        L_0x001e:
            com.google.android.gms.internal.zzbgm.zzb(r13, r1)
            goto L_0x000d
        L_0x0022:
            android.os.IBinder r11 = com.google.android.gms.internal.zzbgm.zzr(r13, r1)
            goto L_0x000d
        L_0x0027:
            android.os.Parcelable$Creator<com.google.android.gms.nearby.connection.DiscoveryOptions> r2 = com.google.android.gms.nearby.connection.DiscoveryOptions.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r13, (int) r1, r2)
            r10 = r1
            com.google.android.gms.nearby.connection.DiscoveryOptions r10 = (com.google.android.gms.nearby.connection.DiscoveryOptions) r10
            goto L_0x000d
        L_0x0031:
            long r8 = com.google.android.gms.internal.zzbgm.zzi(r13, r1)
            goto L_0x000d
        L_0x0036:
            java.lang.String r7 = com.google.android.gms.internal.zzbgm.zzq(r13, r1)
            goto L_0x000d
        L_0x003b:
            android.os.IBinder r6 = com.google.android.gms.internal.zzbgm.zzr(r13, r1)
            goto L_0x000d
        L_0x0040:
            android.os.IBinder r5 = com.google.android.gms.internal.zzbgm.zzr(r13, r1)
            goto L_0x000d
        L_0x0045:
            com.google.android.gms.internal.zzbgm.zzaf(r13, r0)
            com.google.android.gms.internal.zzcum r13 = new com.google.android.gms.internal.zzcum
            r4 = r13
            r4.<init>((android.os.IBinder) r5, (android.os.IBinder) r6, (java.lang.String) r7, (long) r8, (com.google.android.gms.nearby.connection.DiscoveryOptions) r10, (android.os.IBinder) r11)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcun.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final zzcum[] newArray(int i) {
        return new zzcum[i];
    }
}
