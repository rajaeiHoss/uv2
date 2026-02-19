package com.google.android.gms.internal;

import android.os.Parcelable;

public final class zzazv implements Parcelable.Creator<zzazd> {
    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v4, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v5, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v6, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v7, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v8, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v9, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v10, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v11, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v12, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v13, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final zzazd createFromParcel(android.os.Parcel r15) {
        /*
            r14 = this;
            int r0 = com.google.android.gms.internal.zzbgm.zzd(r15)
            r1 = 0
            r3 = r1
            r4 = r3
            r5 = r4
            r6 = r5
            r7 = r6
            r8 = r7
            r9 = r8
            r10 = r9
            r11 = r10
            r12 = r11
            r13 = r12
        L_0x0010:
            int r1 = r15.dataPosition()
            if (r1 >= r0) goto L_0x0094
            int r1 = r15.readInt()
            r2 = 65535(0xffff, float:9.1834E-41)
            r2 = r2 & r1
            switch(r2) {
                case 2: goto L_0x0089;
                case 3: goto L_0x007f;
                case 4: goto L_0x0075;
                case 5: goto L_0x006b;
                case 6: goto L_0x0061;
                case 7: goto L_0x0057;
                case 8: goto L_0x004d;
                case 9: goto L_0x0043;
                case 10: goto L_0x0039;
                case 11: goto L_0x002f;
                case 12: goto L_0x0025;
                default: goto L_0x0021;
            }
        L_0x0021:
            com.google.android.gms.internal.zzbgm.zzb(r15, r1)
            goto L_0x0010
        L_0x0025:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzbjp> r2 = com.google.android.gms.internal.zzbjp.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r15, (int) r1, r2)
            r13 = r1
            com.google.android.gms.internal.zzbjp r13 = (com.google.android.gms.internal.zzbjp) r13
            goto L_0x0010
        L_0x002f:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzazz> r2 = com.google.android.gms.internal.zzazz.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r15, (int) r1, r2)
            r12 = r1
            com.google.android.gms.internal.zzazz r12 = (com.google.android.gms.internal.zzazz) r12
            goto L_0x0010
        L_0x0039:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzbac> r2 = com.google.android.gms.internal.zzbac.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r15, (int) r1, r2)
            r11 = r1
            com.google.android.gms.internal.zzbac r11 = (com.google.android.gms.internal.zzbac) r11
            goto L_0x0010
        L_0x0043:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzazb> r2 = com.google.android.gms.internal.zzazb.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r15, (int) r1, r2)
            r10 = r1
            com.google.android.gms.internal.zzazb r10 = (com.google.android.gms.internal.zzazb) r10
            goto L_0x0010
        L_0x004d:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzayz> r2 = com.google.android.gms.internal.zzayz.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r15, (int) r1, r2)
            r9 = r1
            com.google.android.gms.internal.zzayz r9 = (com.google.android.gms.internal.zzayz) r9
            goto L_0x0010
        L_0x0057:
            android.os.Parcelable$Creator<com.google.android.gms.common.data.DataHolder> r2 = com.google.android.gms.common.data.DataHolder.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r15, (int) r1, r2)
            r8 = r1
            com.google.android.gms.common.data.DataHolder r8 = (com.google.android.gms.common.data.DataHolder) r8
            goto L_0x0010
        L_0x0061:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzayu> r2 = com.google.android.gms.internal.zzayu.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r15, (int) r1, r2)
            r7 = r1
            com.google.android.gms.internal.zzayu r7 = (com.google.android.gms.internal.zzayu) r7
            goto L_0x0010
        L_0x006b:
            android.os.Parcelable$Creator r2 = android.location.Location.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r15, (int) r1, r2)
            r6 = r1
            android.location.Location r6 = (android.location.Location) r6
            goto L_0x0010
        L_0x0075:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzays> r2 = com.google.android.gms.internal.zzays.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r15, (int) r1, r2)
            r5 = r1
            com.google.android.gms.internal.zzays r5 = (com.google.android.gms.internal.zzays) r5
            goto L_0x0010
        L_0x007f:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzayo> r2 = com.google.android.gms.internal.zzayo.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r15, (int) r1, r2)
            r4 = r1
            com.google.android.gms.internal.zzayo r4 = (com.google.android.gms.internal.zzayo) r4
            goto L_0x0010
        L_0x0089:
            android.os.Parcelable$Creator<com.google.android.gms.location.ActivityRecognitionResult> r2 = com.google.android.gms.location.ActivityRecognitionResult.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r15, (int) r1, r2)
            r3 = r1
            com.google.android.gms.location.ActivityRecognitionResult r3 = (com.google.android.gms.location.ActivityRecognitionResult) r3
            goto L_0x0010
        L_0x0094:
            com.google.android.gms.internal.zzbgm.zzaf(r15, r0)
            com.google.android.gms.internal.zzazd r15 = new com.google.android.gms.internal.zzazd
            r2 = r15
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzazv.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final zzazd[] newArray(int i) {
        return new zzazd[i];
    }
}
