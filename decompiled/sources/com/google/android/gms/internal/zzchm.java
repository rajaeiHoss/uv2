package com.google.android.gms.internal;

import android.os.Parcelable;

public final class zzchm implements Parcelable.Creator<zzchl> {
    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final zzchl createFromParcel(android.os.Parcel r13) {
        /*
            r12 = this;
            int r0 = com.google.android.gms.internal.zzbgm.zzd(r13)
            java.util.List<com.google.android.gms.internal.zzcfs> r1 = com.google.android.gms.internal.zzchl.zzitm
            r2 = 0
            r3 = 0
            r6 = r1
            r5 = r3
            r7 = r5
            r11 = r7
            r8 = 0
            r9 = 0
            r10 = 0
        L_0x000f:
            int r1 = r13.dataPosition()
            if (r1 >= r0) goto L_0x0051
            int r1 = r13.readInt()
            r2 = 65535(0xffff, float:9.1834E-41)
            r2 = r2 & r1
            r3 = 1
            if (r2 == r3) goto L_0x0047
            switch(r2) {
                case 5: goto L_0x0040;
                case 6: goto L_0x003b;
                case 7: goto L_0x0036;
                case 8: goto L_0x0031;
                case 9: goto L_0x002c;
                case 10: goto L_0x0027;
                default: goto L_0x0023;
            }
        L_0x0023:
            com.google.android.gms.internal.zzbgm.zzb(r13, r1)
            goto L_0x000f
        L_0x0027:
            java.lang.String r11 = com.google.android.gms.internal.zzbgm.zzq(r13, r1)
            goto L_0x000f
        L_0x002c:
            boolean r10 = com.google.android.gms.internal.zzbgm.zzc(r13, r1)
            goto L_0x000f
        L_0x0031:
            boolean r9 = com.google.android.gms.internal.zzbgm.zzc(r13, r1)
            goto L_0x000f
        L_0x0036:
            boolean r8 = com.google.android.gms.internal.zzbgm.zzc(r13, r1)
            goto L_0x000f
        L_0x003b:
            java.lang.String r7 = com.google.android.gms.internal.zzbgm.zzq(r13, r1)
            goto L_0x000f
        L_0x0040:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzcfs> r2 = com.google.android.gms.internal.zzcfs.CREATOR
            java.util.ArrayList r6 = com.google.android.gms.internal.zzbgm.zzc(r13, r1, r2)
            goto L_0x000f
        L_0x0047:
            android.os.Parcelable$Creator<com.google.android.gms.location.LocationRequest> r2 = com.google.android.gms.location.LocationRequest.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r13, (int) r1, r2)
            r5 = r1
            com.google.android.gms.location.LocationRequest r5 = (com.google.android.gms.location.LocationRequest) r5
            goto L_0x000f
        L_0x0051:
            com.google.android.gms.internal.zzbgm.zzaf(r13, r0)
            com.google.android.gms.internal.zzchl r13 = new com.google.android.gms.internal.zzchl
            r4 = r13
            r4.<init>(r5, r6, r7, r8, r9, r10, r11)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzchm.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final zzchl[] newArray(int i) {
        return new zzchl[i];
    }
}
