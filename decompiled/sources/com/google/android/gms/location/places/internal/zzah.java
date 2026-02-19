package com.google.android.gms.location.places.internal;

import android.os.Parcelable;

public final class zzah implements Parcelable.Creator<zzag> {
    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ zzag createFromParcel(android.os.Parcel r11) {
        /*
            r10 = this;
            int r0 = com.google.android.gms.internal.zzbgm.zzd(r11)
            r1 = 0
            r2 = 0
            r3 = 0
            r5 = r1
            r6 = r5
            r7 = r6
            r8 = 0
            r9 = 0
        L_0x000c:
            int r1 = r11.dataPosition()
            if (r1 >= r0) goto L_0x004b
            int r1 = r11.readInt()
            r2 = 65535(0xffff, float:9.1834E-41)
            r2 = r2 & r1
            r3 = 1
            if (r2 == r3) goto L_0x0046
            r3 = 2
            if (r2 == r3) goto L_0x0041
            r3 = 3
            if (r2 == r3) goto L_0x0037
            r3 = 4
            if (r2 == r3) goto L_0x0032
            r3 = 5
            if (r2 == r3) goto L_0x002d
            com.google.android.gms.internal.zzbgm.zzb(r11, r1)
            goto L_0x000c
        L_0x002d:
            int r9 = com.google.android.gms.internal.zzbgm.zzg(r11, r1)
            goto L_0x000c
        L_0x0032:
            float r8 = com.google.android.gms.internal.zzbgm.zzl(r11, r1)
            goto L_0x000c
        L_0x0037:
            android.os.Parcelable$Creator r2 = android.net.Uri.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r11, (int) r1, r2)
            r7 = r1
            android.net.Uri r7 = (android.net.Uri) r7
            goto L_0x000c
        L_0x0041:
            java.lang.String r6 = com.google.android.gms.internal.zzbgm.zzq(r11, r1)
            goto L_0x000c
        L_0x0046:
            java.util.ArrayList r5 = com.google.android.gms.internal.zzbgm.zzab(r11, r1)
            goto L_0x000c
        L_0x004b:
            com.google.android.gms.internal.zzbgm.zzaf(r11, r0)
            com.google.android.gms.location.places.internal.zzag r11 = new com.google.android.gms.location.places.internal.zzag
            r4 = r11
            r4.<init>(r5, r6, r7, r8, r9)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.location.places.internal.zzah.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ zzag[] newArray(int i) {
        return new zzag[i];
    }
}
