package com.google.android.gms.wallet;

import android.os.Parcelable;

public final class zzac implements Parcelable.Creator<PaymentData> {
    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v4, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v5, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ PaymentData createFromParcel(android.os.Parcel r10) {
        /*
            r9 = this;
            int r0 = com.google.android.gms.internal.zzbgm.zzd(r10)
            r1 = 0
            r3 = r1
            r4 = r3
            r5 = r4
            r6 = r5
            r7 = r6
            r8 = r7
        L_0x000b:
            int r1 = r10.dataPosition()
            if (r1 >= r0) goto L_0x004d
            int r1 = r10.readInt()
            r2 = 65535(0xffff, float:9.1834E-41)
            r2 = r2 & r1
            switch(r2) {
                case 1: goto L_0x0048;
                case 2: goto L_0x003e;
                case 3: goto L_0x0034;
                case 4: goto L_0x002a;
                case 5: goto L_0x0025;
                case 6: goto L_0x0020;
                default: goto L_0x001c;
            }
        L_0x001c:
            com.google.android.gms.internal.zzbgm.zzb(r10, r1)
            goto L_0x000b
        L_0x0020:
            android.os.Bundle r8 = com.google.android.gms.internal.zzbgm.zzs(r10, r1)
            goto L_0x000b
        L_0x0025:
            java.lang.String r7 = com.google.android.gms.internal.zzbgm.zzq(r10, r1)
            goto L_0x000b
        L_0x002a:
            android.os.Parcelable$Creator<com.google.android.gms.wallet.PaymentMethodToken> r2 = com.google.android.gms.wallet.PaymentMethodToken.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r10, (int) r1, r2)
            r6 = r1
            com.google.android.gms.wallet.PaymentMethodToken r6 = (com.google.android.gms.wallet.PaymentMethodToken) r6
            goto L_0x000b
        L_0x0034:
            android.os.Parcelable$Creator<com.google.android.gms.identity.intents.model.UserAddress> r2 = com.google.android.gms.identity.intents.model.UserAddress.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r10, (int) r1, r2)
            r5 = r1
            com.google.android.gms.identity.intents.model.UserAddress r5 = (com.google.android.gms.identity.intents.model.UserAddress) r5
            goto L_0x000b
        L_0x003e:
            android.os.Parcelable$Creator<com.google.android.gms.wallet.CardInfo> r2 = com.google.android.gms.wallet.CardInfo.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r10, (int) r1, r2)
            r4 = r1
            com.google.android.gms.wallet.CardInfo r4 = (com.google.android.gms.wallet.CardInfo) r4
            goto L_0x000b
        L_0x0048:
            java.lang.String r3 = com.google.android.gms.internal.zzbgm.zzq(r10, r1)
            goto L_0x000b
        L_0x004d:
            com.google.android.gms.internal.zzbgm.zzaf(r10, r0)
            com.google.android.gms.wallet.PaymentData r10 = new com.google.android.gms.wallet.PaymentData
            r2 = r10
            r2.<init>(r3, r4, r5, r6, r7, r8)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.wallet.zzac.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ PaymentData[] newArray(int i) {
        return new PaymentData[i];
    }
}
