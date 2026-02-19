package com.google.android.gms.nearby.messages.internal;

import android.os.Parcelable;

public final class zzcf implements Parcelable.Creator<zzce> {
    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v4, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ zzce createFromParcel(android.os.Parcel r12) {
        /*
            r11 = this;
            int r0 = com.google.android.gms.internal.zzbgm.zzd(r12)
            r1 = 0
            r2 = 0
            r5 = r2
            r6 = r5
            r7 = r6
            r8 = r7
            r10 = r8
            r4 = 0
            r9 = 0
        L_0x000d:
            int r1 = r12.dataPosition()
            if (r1 >= r0) goto L_0x004f
            int r1 = r12.readInt()
            r2 = 65535(0xffff, float:9.1834E-41)
            r2 = r2 & r1
            switch(r2) {
                case 1: goto L_0x004a;
                case 2: goto L_0x0040;
                case 3: goto L_0x003b;
                case 4: goto L_0x0036;
                case 5: goto L_0x0031;
                case 6: goto L_0x002c;
                case 7: goto L_0x0022;
                default: goto L_0x001e;
            }
        L_0x001e:
            com.google.android.gms.internal.zzbgm.zzb(r12, r1)
            goto L_0x000d
        L_0x0022:
            android.os.Parcelable$Creator<com.google.android.gms.nearby.messages.internal.ClientAppContext> r2 = com.google.android.gms.nearby.messages.internal.ClientAppContext.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r12, (int) r1, r2)
            r10 = r1
            com.google.android.gms.nearby.messages.internal.ClientAppContext r10 = (com.google.android.gms.nearby.messages.internal.ClientAppContext) r10
            goto L_0x000d
        L_0x002c:
            boolean r9 = com.google.android.gms.internal.zzbgm.zzc(r12, r1)
            goto L_0x000d
        L_0x0031:
            java.lang.String r8 = com.google.android.gms.internal.zzbgm.zzq(r12, r1)
            goto L_0x000d
        L_0x0036:
            java.lang.String r7 = com.google.android.gms.internal.zzbgm.zzq(r12, r1)
            goto L_0x000d
        L_0x003b:
            android.os.IBinder r6 = com.google.android.gms.internal.zzbgm.zzr(r12, r1)
            goto L_0x000d
        L_0x0040:
            android.os.Parcelable$Creator<com.google.android.gms.nearby.messages.internal.zzaf> r2 = com.google.android.gms.nearby.messages.internal.zzaf.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r12, (int) r1, r2)
            r5 = r1
            com.google.android.gms.nearby.messages.internal.zzaf r5 = (com.google.android.gms.nearby.messages.internal.zzaf) r5
            goto L_0x000d
        L_0x004a:
            int r4 = com.google.android.gms.internal.zzbgm.zzg(r12, r1)
            goto L_0x000d
        L_0x004f:
            com.google.android.gms.internal.zzbgm.zzaf(r12, r0)
            com.google.android.gms.nearby.messages.internal.zzce r12 = new com.google.android.gms.nearby.messages.internal.zzce
            r3 = r12
            r3.<init>(r4, r5, r6, r7, r8, r9, r10)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.nearby.messages.internal.zzcf.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ zzce[] newArray(int i) {
        return new zzce[i];
    }
}
