package com.google.android.gms.nearby.messages.internal;

import android.os.Parcelable;

public final class zzcc implements Parcelable.Creator<zzcb> {
    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ zzcb createFromParcel(android.os.Parcel r11) {
        /*
            r10 = this;
            int r0 = com.google.android.gms.internal.zzbgm.zzd(r11)
            r1 = 0
            r2 = 0
            r5 = r2
            r6 = r5
            r8 = r6
            r9 = r8
            r4 = 0
            r7 = 0
        L_0x000c:
            int r1 = r11.dataPosition()
            if (r1 >= r0) goto L_0x0044
            int r1 = r11.readInt()
            r2 = 65535(0xffff, float:9.1834E-41)
            r2 = r2 & r1
            switch(r2) {
                case 1: goto L_0x003f;
                case 2: goto L_0x003a;
                case 3: goto L_0x0035;
                case 4: goto L_0x0030;
                case 5: goto L_0x002b;
                case 6: goto L_0x0021;
                default: goto L_0x001d;
            }
        L_0x001d:
            com.google.android.gms.internal.zzbgm.zzb(r11, r1)
            goto L_0x000c
        L_0x0021:
            android.os.Parcelable$Creator<com.google.android.gms.nearby.messages.internal.ClientAppContext> r2 = com.google.android.gms.nearby.messages.internal.ClientAppContext.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r11, (int) r1, r2)
            r9 = r1
            com.google.android.gms.nearby.messages.internal.ClientAppContext r9 = (com.google.android.gms.nearby.messages.internal.ClientAppContext) r9
            goto L_0x000c
        L_0x002b:
            java.lang.String r8 = com.google.android.gms.internal.zzbgm.zzq(r11, r1)
            goto L_0x000c
        L_0x0030:
            boolean r7 = com.google.android.gms.internal.zzbgm.zzc(r11, r1)
            goto L_0x000c
        L_0x0035:
            android.os.IBinder r6 = com.google.android.gms.internal.zzbgm.zzr(r11, r1)
            goto L_0x000c
        L_0x003a:
            android.os.IBinder r5 = com.google.android.gms.internal.zzbgm.zzr(r11, r1)
            goto L_0x000c
        L_0x003f:
            int r4 = com.google.android.gms.internal.zzbgm.zzg(r11, r1)
            goto L_0x000c
        L_0x0044:
            com.google.android.gms.internal.zzbgm.zzaf(r11, r0)
            com.google.android.gms.nearby.messages.internal.zzcb r11 = new com.google.android.gms.nearby.messages.internal.zzcb
            r3 = r11
            r3.<init>(r4, r5, r6, r7, r8, r9)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.nearby.messages.internal.zzcc.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ zzcb[] newArray(int i) {
        return new zzcb[i];
    }
}
