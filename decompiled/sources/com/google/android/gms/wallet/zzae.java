package com.google.android.gms.wallet;

import android.os.Parcelable;

public final class zzae implements Parcelable.Creator<PaymentDataRequest> {
    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v4, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v5, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v6, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ PaymentDataRequest createFromParcel(android.os.Parcel r15) {
        /*
            r14 = this;
            int r0 = com.google.android.gms.internal.zzbgm.zzd(r15)
            r1 = 0
            r2 = 0
            r3 = 1
            r7 = r2
            r9 = r7
            r10 = r9
            r11 = r10
            r12 = r11
            r5 = 0
            r6 = 0
            r8 = 0
            r13 = 1
        L_0x0010:
            int r1 = r15.dataPosition()
            if (r1 >= r0) goto L_0x0066
            int r1 = r15.readInt()
            r2 = 65535(0xffff, float:9.1834E-41)
            r2 = r2 & r1
            switch(r2) {
                case 1: goto L_0x0061;
                case 2: goto L_0x005c;
                case 3: goto L_0x0052;
                case 4: goto L_0x004d;
                case 5: goto L_0x0043;
                case 6: goto L_0x003e;
                case 7: goto L_0x0034;
                case 8: goto L_0x002a;
                case 9: goto L_0x0025;
                default: goto L_0x0021;
            }
        L_0x0021:
            com.google.android.gms.internal.zzbgm.zzb(r15, r1)
            goto L_0x0010
        L_0x0025:
            boolean r13 = com.google.android.gms.internal.zzbgm.zzc(r15, r1)
            goto L_0x0010
        L_0x002a:
            android.os.Parcelable$Creator<com.google.android.gms.wallet.TransactionInfo> r2 = com.google.android.gms.wallet.TransactionInfo.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r15, (int) r1, r2)
            r12 = r1
            com.google.android.gms.wallet.TransactionInfo r12 = (com.google.android.gms.wallet.TransactionInfo) r12
            goto L_0x0010
        L_0x0034:
            android.os.Parcelable$Creator<com.google.android.gms.wallet.PaymentMethodTokenizationParameters> r2 = com.google.android.gms.wallet.PaymentMethodTokenizationParameters.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r15, (int) r1, r2)
            r11 = r1
            com.google.android.gms.wallet.PaymentMethodTokenizationParameters r11 = (com.google.android.gms.wallet.PaymentMethodTokenizationParameters) r11
            goto L_0x0010
        L_0x003e:
            java.util.ArrayList r10 = com.google.android.gms.internal.zzbgm.zzab(r15, r1)
            goto L_0x0010
        L_0x0043:
            android.os.Parcelable$Creator<com.google.android.gms.wallet.ShippingAddressRequirements> r2 = com.google.android.gms.wallet.ShippingAddressRequirements.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r15, (int) r1, r2)
            r9 = r1
            com.google.android.gms.wallet.ShippingAddressRequirements r9 = (com.google.android.gms.wallet.ShippingAddressRequirements) r9
            goto L_0x0010
        L_0x004d:
            boolean r8 = com.google.android.gms.internal.zzbgm.zzc(r15, r1)
            goto L_0x0010
        L_0x0052:
            android.os.Parcelable$Creator<com.google.android.gms.wallet.CardRequirements> r2 = com.google.android.gms.wallet.CardRequirements.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r15, (int) r1, r2)
            r7 = r1
            com.google.android.gms.wallet.CardRequirements r7 = (com.google.android.gms.wallet.CardRequirements) r7
            goto L_0x0010
        L_0x005c:
            boolean r6 = com.google.android.gms.internal.zzbgm.zzc(r15, r1)
            goto L_0x0010
        L_0x0061:
            boolean r5 = com.google.android.gms.internal.zzbgm.zzc(r15, r1)
            goto L_0x0010
        L_0x0066:
            com.google.android.gms.internal.zzbgm.zzaf(r15, r0)
            com.google.android.gms.wallet.PaymentDataRequest r15 = new com.google.android.gms.wallet.PaymentDataRequest
            r4 = r15
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13)
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.wallet.zzae.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ PaymentDataRequest[] newArray(int i) {
        return new PaymentDataRequest[i];
    }
}
