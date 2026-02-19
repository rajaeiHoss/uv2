package com.google.android.gms.wallet.wobs;

import android.os.Parcelable;

public final class zzb implements Parcelable.Creator<CommonWalletObject> {
    /* JADX WARNING: type inference failed for: r2v3, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ CommonWalletObject createFromParcel(android.os.Parcel r31) {
        /*
            r30 = this;
            r0 = r31
            int r1 = com.google.android.gms.internal.zzbgm.zzd(r31)
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
            r20 = r2
            r22 = r3
            r25 = r4
            r27 = r5
            r28 = r6
            r29 = r7
            r11 = r9
            r12 = r11
            r13 = r12
            r14 = r13
            r15 = r14
            r16 = r15
            r17 = r16
            r18 = r17
            r21 = r18
            r23 = r21
            r24 = r23
            r19 = 0
            r26 = 0
        L_0x0047:
            int r2 = r31.dataPosition()
            if (r2 >= r1) goto L_0x00cf
            int r2 = r31.readInt()
            r3 = 65535(0xffff, float:9.1834E-41)
            r3 = r3 & r2
            switch(r3) {
                case 2: goto L_0x00c9;
                case 3: goto L_0x00c3;
                case 4: goto L_0x00be;
                case 5: goto L_0x00b9;
                case 6: goto L_0x00b4;
                case 7: goto L_0x00af;
                case 8: goto L_0x00aa;
                case 9: goto L_0x00a5;
                case 10: goto L_0x00a0;
                case 11: goto L_0x0099;
                case 12: goto L_0x008e;
                case 13: goto L_0x0087;
                case 14: goto L_0x0082;
                case 15: goto L_0x007d;
                case 16: goto L_0x0076;
                case 17: goto L_0x0071;
                case 18: goto L_0x006a;
                case 19: goto L_0x0063;
                case 20: goto L_0x005c;
                default: goto L_0x0058;
            }
        L_0x0058:
            com.google.android.gms.internal.zzbgm.zzb(r0, r2)
            goto L_0x0047
        L_0x005c:
            android.os.Parcelable$Creator<com.google.android.gms.wallet.wobs.UriData> r3 = com.google.android.gms.wallet.wobs.UriData.CREATOR
            java.util.ArrayList r29 = com.google.android.gms.internal.zzbgm.zzc(r0, r2, r3)
            goto L_0x0047
        L_0x0063:
            android.os.Parcelable$Creator<com.google.android.gms.wallet.wobs.TextModuleData> r3 = com.google.android.gms.wallet.wobs.TextModuleData.CREATOR
            java.util.ArrayList r28 = com.google.android.gms.internal.zzbgm.zzc(r0, r2, r3)
            goto L_0x0047
        L_0x006a:
            android.os.Parcelable$Creator<com.google.android.gms.wallet.wobs.UriData> r3 = com.google.android.gms.wallet.wobs.UriData.CREATOR
            java.util.ArrayList r27 = com.google.android.gms.internal.zzbgm.zzc(r0, r2, r3)
            goto L_0x0047
        L_0x0071:
            boolean r26 = com.google.android.gms.internal.zzbgm.zzc(r0, r2)
            goto L_0x0047
        L_0x0076:
            android.os.Parcelable$Creator<com.google.android.gms.wallet.wobs.LabelValueRow> r3 = com.google.android.gms.wallet.wobs.LabelValueRow.CREATOR
            java.util.ArrayList r25 = com.google.android.gms.internal.zzbgm.zzc(r0, r2, r3)
            goto L_0x0047
        L_0x007d:
            java.lang.String r24 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0047
        L_0x0082:
            java.lang.String r23 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0047
        L_0x0087:
            android.os.Parcelable$Creator<com.google.android.gms.maps.model.LatLng> r3 = com.google.android.gms.maps.model.LatLng.CREATOR
            java.util.ArrayList r22 = com.google.android.gms.internal.zzbgm.zzc(r0, r2, r3)
            goto L_0x0047
        L_0x008e:
            android.os.Parcelable$Creator<com.google.android.gms.wallet.wobs.TimeInterval> r3 = com.google.android.gms.wallet.wobs.TimeInterval.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r21 = r2
            com.google.android.gms.wallet.wobs.TimeInterval r21 = (com.google.android.gms.wallet.wobs.TimeInterval) r21
            goto L_0x0047
        L_0x0099:
            android.os.Parcelable$Creator<com.google.android.gms.wallet.wobs.WalletObjectMessage> r3 = com.google.android.gms.wallet.wobs.WalletObjectMessage.CREATOR
            java.util.ArrayList r20 = com.google.android.gms.internal.zzbgm.zzc(r0, r2, r3)
            goto L_0x0047
        L_0x00a0:
            int r19 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            goto L_0x0047
        L_0x00a5:
            java.lang.String r18 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0047
        L_0x00aa:
            java.lang.String r17 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0047
        L_0x00af:
            java.lang.String r16 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0047
        L_0x00b4:
            java.lang.String r15 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0047
        L_0x00b9:
            java.lang.String r14 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0047
        L_0x00be:
            java.lang.String r13 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0047
        L_0x00c3:
            java.lang.String r12 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0047
        L_0x00c9:
            java.lang.String r11 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0047
        L_0x00cf:
            com.google.android.gms.internal.zzbgm.zzaf(r0, r1)
            com.google.android.gms.wallet.wobs.CommonWalletObject r0 = new com.google.android.gms.wallet.wobs.CommonWalletObject
            r10 = r0
            r10.<init>(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.wallet.wobs.zzb.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ CommonWalletObject[] newArray(int i) {
        return new CommonWalletObject[i];
    }
}
