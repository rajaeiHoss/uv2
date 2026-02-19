package com.google.android.gms.nearby.messages;

import android.os.Parcelable;

public final class zza implements Parcelable.Creator<Message> {
    /* JADX WARNING: type inference failed for: r1v3, types: [java.lang.Object[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ Message createFromParcel(android.os.Parcel r14) {
        /*
            r13 = this;
            int r0 = com.google.android.gms.internal.zzbgm.zzd(r14)
            r1 = 0
            r2 = 0
            r3 = 0
            r7 = r1
            r8 = r7
            r9 = r8
            r10 = r9
            r11 = r3
            r6 = 0
        L_0x000e:
            int r1 = r14.dataPosition()
            if (r1 >= r0) goto L_0x0056
            int r1 = r14.readInt()
            r2 = 65535(0xffff, float:9.1834E-41)
            r2 = r2 & r1
            r3 = 1
            if (r2 == r3) goto L_0x0051
            r3 = 2
            if (r2 == r3) goto L_0x004c
            r3 = 3
            if (r2 == r3) goto L_0x0047
            r3 = 4
            if (r2 == r3) goto L_0x003d
            r3 = 5
            if (r2 == r3) goto L_0x0038
            r3 = 1000(0x3e8, float:1.401E-42)
            if (r2 == r3) goto L_0x0033
            com.google.android.gms.internal.zzbgm.zzb(r14, r1)
            goto L_0x000e
        L_0x0033:
            int r6 = com.google.android.gms.internal.zzbgm.zzg(r14, r1)
            goto L_0x000e
        L_0x0038:
            long r11 = com.google.android.gms.internal.zzbgm.zzi(r14, r1)
            goto L_0x000e
        L_0x003d:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzcux> r2 = com.google.android.gms.internal.zzcux.CREATOR
            java.lang.Object[] r1 = com.google.android.gms.internal.zzbgm.zzb(r14, r1, r2)
            r10 = r1
            com.google.android.gms.internal.zzcux[] r10 = (com.google.android.gms.internal.zzcux[]) r10
            goto L_0x000e
        L_0x0047:
            java.lang.String r8 = com.google.android.gms.internal.zzbgm.zzq(r14, r1)
            goto L_0x000e
        L_0x004c:
            java.lang.String r9 = com.google.android.gms.internal.zzbgm.zzq(r14, r1)
            goto L_0x000e
        L_0x0051:
            byte[] r7 = com.google.android.gms.internal.zzbgm.zzt(r14, r1)
            goto L_0x000e
        L_0x0056:
            com.google.android.gms.internal.zzbgm.zzaf(r14, r0)
            com.google.android.gms.nearby.messages.Message r14 = new com.google.android.gms.nearby.messages.Message
            r5 = r14
            r5.<init>(r6, r7, r8, r9, r10, r11)
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.nearby.messages.zza.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Message[] newArray(int i) {
        return new Message[i];
    }
}
