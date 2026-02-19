package com.google.android.gms.ads.internal.overlay;

import android.os.Parcelable;

public final class zzm implements Parcelable.Creator<AdOverlayInfoParcel> {
    /* JADX WARNING: type inference failed for: r2v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v4, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v5, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final AdOverlayInfoParcel createFromParcel(android.os.Parcel r21) {
        /*
            r20 = this;
            r0 = r21
            int r1 = com.google.android.gms.internal.zzbgm.zzd(r21)
            r2 = 0
            r3 = 0
            r5 = r3
            r6 = r5
            r7 = r6
            r8 = r7
            r9 = r8
            r10 = r9
            r12 = r10
            r13 = r12
            r16 = r13
            r17 = r16
            r18 = r17
            r19 = r18
            r11 = 0
            r14 = 0
            r15 = 0
        L_0x001b:
            int r2 = r21.dataPosition()
            if (r2 >= r1) goto L_0x008c
            int r2 = r21.readInt()
            r3 = 65535(0xffff, float:9.1834E-41)
            r3 = r3 & r2
            switch(r3) {
                case 2: goto L_0x0082;
                case 3: goto L_0x007d;
                case 4: goto L_0x0078;
                case 5: goto L_0x0073;
                case 6: goto L_0x006e;
                case 7: goto L_0x0069;
                case 8: goto L_0x0064;
                case 9: goto L_0x005f;
                case 10: goto L_0x005a;
                case 11: goto L_0x0055;
                case 12: goto L_0x0050;
                case 13: goto L_0x004b;
                case 14: goto L_0x0040;
                case 15: goto L_0x002c;
                case 16: goto L_0x003b;
                case 17: goto L_0x0030;
                default: goto L_0x002c;
            }
        L_0x002c:
            com.google.android.gms.internal.zzbgm.zzb(r0, r2)
            goto L_0x001b
        L_0x0030:
            android.os.Parcelable$Creator<com.google.android.gms.ads.internal.zzap> r3 = com.google.android.gms.ads.internal.zzap.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r19 = r2
            com.google.android.gms.ads.internal.zzap r19 = (com.google.android.gms.ads.internal.zzap) r19
            goto L_0x001b
        L_0x003b:
            java.lang.String r18 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x001b
        L_0x0040:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzala> r3 = com.google.android.gms.internal.zzala.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r17 = r2
            com.google.android.gms.internal.zzala r17 = (com.google.android.gms.internal.zzala) r17
            goto L_0x001b
        L_0x004b:
            java.lang.String r16 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x001b
        L_0x0050:
            int r15 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            goto L_0x001b
        L_0x0055:
            int r14 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            goto L_0x001b
        L_0x005a:
            android.os.IBinder r13 = com.google.android.gms.internal.zzbgm.zzr(r0, r2)
            goto L_0x001b
        L_0x005f:
            java.lang.String r12 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x001b
        L_0x0064:
            boolean r11 = com.google.android.gms.internal.zzbgm.zzc(r0, r2)
            goto L_0x001b
        L_0x0069:
            java.lang.String r10 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x001b
        L_0x006e:
            android.os.IBinder r9 = com.google.android.gms.internal.zzbgm.zzr(r0, r2)
            goto L_0x001b
        L_0x0073:
            android.os.IBinder r8 = com.google.android.gms.internal.zzbgm.zzr(r0, r2)
            goto L_0x001b
        L_0x0078:
            android.os.IBinder r7 = com.google.android.gms.internal.zzbgm.zzr(r0, r2)
            goto L_0x001b
        L_0x007d:
            android.os.IBinder r6 = com.google.android.gms.internal.zzbgm.zzr(r0, r2)
            goto L_0x001b
        L_0x0082:
            android.os.Parcelable$Creator<com.google.android.gms.ads.internal.overlay.zzc> r3 = com.google.android.gms.ads.internal.overlay.zzc.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r5 = r2
            com.google.android.gms.ads.internal.overlay.zzc r5 = (com.google.android.gms.ads.internal.overlay.zzc) r5
            goto L_0x001b
        L_0x008c:
            com.google.android.gms.internal.zzbgm.zzaf(r0, r1)
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = new com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel
            r4 = r0
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.overlay.zzm.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final AdOverlayInfoParcel[] newArray(int i) {
        return new AdOverlayInfoParcel[i];
    }
}
