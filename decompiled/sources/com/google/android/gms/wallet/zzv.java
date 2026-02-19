package com.google.android.gms.wallet;

import android.os.Parcelable;

public final class zzv implements Parcelable.Creator<LoyaltyWalletObject> {
    /* JADX WARNING: type inference failed for: r2v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v4, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ LoyaltyWalletObject createFromParcel(android.os.Parcel r34) {
        /*
            r33 = this;
            r0 = r34
            int r1 = com.google.android.gms.internal.zzbgm.zzd(r34)
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            r8 = 0
            r9 = 0
            r22 = r2
            r24 = r3
            r27 = r4
            r29 = r5
            r30 = r6
            r31 = r7
            r11 = r9
            r12 = r11
            r13 = r12
            r14 = r13
            r15 = r14
            r16 = r15
            r17 = r16
            r18 = r17
            r19 = r18
            r20 = r19
            r23 = r20
            r25 = r23
            r26 = r25
            r32 = r26
            r21 = 0
            r28 = 0
        L_0x004d:
            int r2 = r34.dataPosition()
            if (r2 >= r1) goto L_0x00ee
            int r2 = r34.readInt()
            r3 = 65535(0xffff, float:9.1834E-41)
            r3 = r3 & r2
            switch(r3) {
                case 2: goto L_0x00e8;
                case 3: goto L_0x00e2;
                case 4: goto L_0x00dc;
                case 5: goto L_0x00d6;
                case 6: goto L_0x00d0;
                case 7: goto L_0x00ca;
                case 8: goto L_0x00c5;
                case 9: goto L_0x00c0;
                case 10: goto L_0x00bb;
                case 11: goto L_0x00b6;
                case 12: goto L_0x00b1;
                case 13: goto L_0x00aa;
                case 14: goto L_0x009f;
                case 15: goto L_0x0098;
                case 16: goto L_0x0093;
                case 17: goto L_0x008e;
                case 18: goto L_0x0087;
                case 19: goto L_0x0082;
                case 20: goto L_0x007b;
                case 21: goto L_0x0074;
                case 22: goto L_0x006d;
                case 23: goto L_0x0062;
                default: goto L_0x005e;
            }
        L_0x005e:
            com.google.android.gms.internal.zzbgm.zzb(r0, r2)
            goto L_0x004d
        L_0x0062:
            android.os.Parcelable$Creator<com.google.android.gms.wallet.wobs.LoyaltyPoints> r3 = com.google.android.gms.wallet.wobs.LoyaltyPoints.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r32 = r2
            com.google.android.gms.wallet.wobs.LoyaltyPoints r32 = (com.google.android.gms.wallet.wobs.LoyaltyPoints) r32
            goto L_0x004d
        L_0x006d:
            android.os.Parcelable$Creator<com.google.android.gms.wallet.wobs.UriData> r3 = com.google.android.gms.wallet.wobs.UriData.CREATOR
            java.util.ArrayList r31 = com.google.android.gms.internal.zzbgm.zzc(r0, r2, r3)
            goto L_0x004d
        L_0x0074:
            android.os.Parcelable$Creator<com.google.android.gms.wallet.wobs.TextModuleData> r3 = com.google.android.gms.wallet.wobs.TextModuleData.CREATOR
            java.util.ArrayList r30 = com.google.android.gms.internal.zzbgm.zzc(r0, r2, r3)
            goto L_0x004d
        L_0x007b:
            android.os.Parcelable$Creator<com.google.android.gms.wallet.wobs.UriData> r3 = com.google.android.gms.wallet.wobs.UriData.CREATOR
            java.util.ArrayList r29 = com.google.android.gms.internal.zzbgm.zzc(r0, r2, r3)
            goto L_0x004d
        L_0x0082:
            boolean r28 = com.google.android.gms.internal.zzbgm.zzc(r0, r2)
            goto L_0x004d
        L_0x0087:
            android.os.Parcelable$Creator<com.google.android.gms.wallet.wobs.LabelValueRow> r3 = com.google.android.gms.wallet.wobs.LabelValueRow.CREATOR
            java.util.ArrayList r27 = com.google.android.gms.internal.zzbgm.zzc(r0, r2, r3)
            goto L_0x004d
        L_0x008e:
            java.lang.String r26 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x004d
        L_0x0093:
            java.lang.String r25 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x004d
        L_0x0098:
            android.os.Parcelable$Creator<com.google.android.gms.maps.model.LatLng> r3 = com.google.android.gms.maps.model.LatLng.CREATOR
            java.util.ArrayList r24 = com.google.android.gms.internal.zzbgm.zzc(r0, r2, r3)
            goto L_0x004d
        L_0x009f:
            android.os.Parcelable$Creator<com.google.android.gms.wallet.wobs.TimeInterval> r3 = com.google.android.gms.wallet.wobs.TimeInterval.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r23 = r2
            com.google.android.gms.wallet.wobs.TimeInterval r23 = (com.google.android.gms.wallet.wobs.TimeInterval) r23
            goto L_0x004d
        L_0x00aa:
            android.os.Parcelable$Creator<com.google.android.gms.wallet.wobs.WalletObjectMessage> r3 = com.google.android.gms.wallet.wobs.WalletObjectMessage.CREATOR
            java.util.ArrayList r22 = com.google.android.gms.internal.zzbgm.zzc(r0, r2, r3)
            goto L_0x004d
        L_0x00b1:
            int r21 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            goto L_0x004d
        L_0x00b6:
            java.lang.String r20 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x004d
        L_0x00bb:
            java.lang.String r19 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x004d
        L_0x00c0:
            java.lang.String r18 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x004d
        L_0x00c5:
            java.lang.String r17 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x004d
        L_0x00ca:
            java.lang.String r16 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x004d
        L_0x00d0:
            java.lang.String r15 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x004d
        L_0x00d6:
            java.lang.String r14 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x004d
        L_0x00dc:
            java.lang.String r13 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x004d
        L_0x00e2:
            java.lang.String r12 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x004d
        L_0x00e8:
            java.lang.String r11 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x004d
        L_0x00ee:
            com.google.android.gms.internal.zzbgm.zzaf(r0, r1)
            com.google.android.gms.wallet.LoyaltyWalletObject r0 = new com.google.android.gms.wallet.LoyaltyWalletObject
            r10 = r0
            r10.<init>(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.wallet.zzv.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ LoyaltyWalletObject[] newArray(int i) {
        return new LoyaltyWalletObject[i];
    }
}
