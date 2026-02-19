package com.google.android.gms.internal;

import android.os.Parcelable;

public final class zzbdy implements Parcelable.Creator<zzbdx> {
    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final zzbdx createFromParcel(android.os.Parcel r13) {
        /*
            r12 = this;
            int r0 = com.google.android.gms.internal.zzbgm.zzd(r13)
            r1 = 0
            r2 = 0
            r4 = 0
            r6 = r2
            r10 = r4
            r8 = 0
            r9 = 0
            r11 = 0
        L_0x000d:
            int r1 = r13.dataPosition()
            if (r1 >= r0) goto L_0x004c
            int r1 = r13.readInt()
            r2 = 65535(0xffff, float:9.1834E-41)
            r2 = r2 & r1
            r3 = 2
            if (r2 == r3) goto L_0x0047
            r3 = 3
            if (r2 == r3) goto L_0x0042
            r3 = 4
            if (r2 == r3) goto L_0x003d
            r3 = 5
            if (r2 == r3) goto L_0x0033
            r3 = 6
            if (r2 == r3) goto L_0x002e
            com.google.android.gms.internal.zzbgm.zzb(r13, r1)
            goto L_0x000d
        L_0x002e:
            int r11 = com.google.android.gms.internal.zzbgm.zzg(r13, r1)
            goto L_0x000d
        L_0x0033:
            android.os.Parcelable$Creator<com.google.android.gms.cast.ApplicationMetadata> r2 = com.google.android.gms.cast.ApplicationMetadata.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r13, (int) r1, r2)
            r10 = r1
            com.google.android.gms.cast.ApplicationMetadata r10 = (com.google.android.gms.cast.ApplicationMetadata) r10
            goto L_0x000d
        L_0x003d:
            int r9 = com.google.android.gms.internal.zzbgm.zzg(r13, r1)
            goto L_0x000d
        L_0x0042:
            boolean r8 = com.google.android.gms.internal.zzbgm.zzc(r13, r1)
            goto L_0x000d
        L_0x0047:
            double r6 = com.google.android.gms.internal.zzbgm.zzn(r13, r1)
            goto L_0x000d
        L_0x004c:
            com.google.android.gms.internal.zzbgm.zzaf(r13, r0)
            com.google.android.gms.internal.zzbdx r13 = new com.google.android.gms.internal.zzbdx
            r5 = r13
            r5.<init>(r6, r8, r9, r10, r11)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzbdy.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final zzbdx[] newArray(int i) {
        return new zzbdx[i];
    }
}
