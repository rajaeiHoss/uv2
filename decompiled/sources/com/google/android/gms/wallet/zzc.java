package com.google.android.gms.wallet;

import android.os.Parcelable;

public final class zzc implements Parcelable.Creator<CardInfo> {
    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ CardInfo createFromParcel(android.os.Parcel r10) {
        /*
            r9 = this;
            int r0 = com.google.android.gms.internal.zzbgm.zzd(r10)
            r1 = 0
            r2 = 0
            r4 = r1
            r5 = r4
            r6 = r5
            r8 = r6
            r7 = 0
        L_0x000b:
            int r1 = r10.dataPosition()
            if (r1 >= r0) goto L_0x004a
            int r1 = r10.readInt()
            r2 = 65535(0xffff, float:9.1834E-41)
            r2 = r2 & r1
            r3 = 1
            if (r2 == r3) goto L_0x0045
            r3 = 2
            if (r2 == r3) goto L_0x0040
            r3 = 3
            if (r2 == r3) goto L_0x003b
            r3 = 4
            if (r2 == r3) goto L_0x0036
            r3 = 5
            if (r2 == r3) goto L_0x002c
            com.google.android.gms.internal.zzbgm.zzb(r10, r1)
            goto L_0x000b
        L_0x002c:
            android.os.Parcelable$Creator<com.google.android.gms.identity.intents.model.UserAddress> r2 = com.google.android.gms.identity.intents.model.UserAddress.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r10, (int) r1, r2)
            r8 = r1
            com.google.android.gms.identity.intents.model.UserAddress r8 = (com.google.android.gms.identity.intents.model.UserAddress) r8
            goto L_0x000b
        L_0x0036:
            int r7 = com.google.android.gms.internal.zzbgm.zzg(r10, r1)
            goto L_0x000b
        L_0x003b:
            java.lang.String r6 = com.google.android.gms.internal.zzbgm.zzq(r10, r1)
            goto L_0x000b
        L_0x0040:
            java.lang.String r5 = com.google.android.gms.internal.zzbgm.zzq(r10, r1)
            goto L_0x000b
        L_0x0045:
            java.lang.String r4 = com.google.android.gms.internal.zzbgm.zzq(r10, r1)
            goto L_0x000b
        L_0x004a:
            com.google.android.gms.internal.zzbgm.zzaf(r10, r0)
            com.google.android.gms.wallet.CardInfo r10 = new com.google.android.gms.wallet.CardInfo
            r3 = r10
            r3.<init>(r4, r5, r6, r7, r8)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.wallet.zzc.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ CardInfo[] newArray(int i) {
        return new CardInfo[i];
    }
}
