package com.google.android.gms.internal;

import android.os.Parcelable;

public final class zzcdw implements Parcelable.Creator<zzcdv> {
    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v4, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final zzcdv createFromParcel(android.os.Parcel r15) {
        /*
            r14 = this;
            int r0 = com.google.android.gms.internal.zzbgm.zzd(r15)
            r1 = 0
            r2 = 0
            r4 = r1
            r5 = r4
            r6 = r5
            r7 = r6
            r8 = r7
            r9 = r8
            r11 = r9
            r12 = r11
            r13 = r12
            r10 = 0
        L_0x0010:
            int r1 = r15.dataPosition()
            if (r1 >= r0) goto L_0x0067
            int r1 = r15.readInt()
            r2 = 65535(0xffff, float:9.1834E-41)
            r2 = r2 & r1
            switch(r2) {
                case 2: goto L_0x0062;
                case 3: goto L_0x005d;
                case 4: goto L_0x0053;
                case 5: goto L_0x004c;
                case 6: goto L_0x0045;
                case 7: goto L_0x0040;
                case 8: goto L_0x003b;
                case 9: goto L_0x0031;
                case 10: goto L_0x0021;
                case 11: goto L_0x002a;
                case 12: goto L_0x0025;
                default: goto L_0x0021;
            }
        L_0x0021:
            com.google.android.gms.internal.zzbgm.zzb(r15, r1)
            goto L_0x0010
        L_0x0025:
            byte[] r13 = com.google.android.gms.internal.zzbgm.zzt(r15, r1)
            goto L_0x0010
        L_0x002a:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzcff> r2 = com.google.android.gms.internal.zzcff.CREATOR
            java.util.ArrayList r9 = com.google.android.gms.internal.zzbgm.zzc(r15, r1, r2)
            goto L_0x0010
        L_0x0031:
            android.os.Parcelable$Creator r2 = android.content.pm.PackageInfo.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r15, (int) r1, r2)
            r12 = r1
            android.content.pm.PackageInfo r12 = (android.content.pm.PackageInfo) r12
            goto L_0x0010
        L_0x003b:
            byte[] r11 = com.google.android.gms.internal.zzbgm.zzt(r15, r1)
            goto L_0x0010
        L_0x0040:
            int r10 = com.google.android.gms.internal.zzbgm.zzg(r15, r1)
            goto L_0x0010
        L_0x0045:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzcdx> r2 = com.google.android.gms.internal.zzcdx.CREATOR
            java.util.ArrayList r8 = com.google.android.gms.internal.zzbgm.zzc(r15, r1, r2)
            goto L_0x0010
        L_0x004c:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzcfd> r2 = com.google.android.gms.internal.zzcfd.CREATOR
            java.util.ArrayList r7 = com.google.android.gms.internal.zzbgm.zzc(r15, r1, r2)
            goto L_0x0010
        L_0x0053:
            android.os.Parcelable$Creator<com.google.android.gms.common.data.BitmapTeleporter> r2 = com.google.android.gms.common.data.BitmapTeleporter.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r15, (int) r1, r2)
            r6 = r1
            com.google.android.gms.common.data.BitmapTeleporter r6 = (com.google.android.gms.common.data.BitmapTeleporter) r6
            goto L_0x0010
        L_0x005d:
            java.lang.String r5 = com.google.android.gms.internal.zzbgm.zzq(r15, r1)
            goto L_0x0010
        L_0x0062:
            java.lang.String r4 = com.google.android.gms.internal.zzbgm.zzq(r15, r1)
            goto L_0x0010
        L_0x0067:
            com.google.android.gms.internal.zzbgm.zzaf(r15, r0)
            com.google.android.gms.internal.zzcdv r15 = new com.google.android.gms.internal.zzcdv
            r3 = r15
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcdw.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final zzcdv[] newArray(int i) {
        return new zzcdv[i];
    }
}
