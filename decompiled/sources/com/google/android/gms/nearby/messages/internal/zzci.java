package com.google.android.gms.nearby.messages.internal;

import android.os.Parcelable;

public final class zzci implements Parcelable.Creator<Update> {
    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v4, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v5, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v6, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ Update createFromParcel(android.os.Parcel r12) {
        /*
            r11 = this;
            int r0 = com.google.android.gms.internal.zzbgm.zzd(r12)
            r1 = 0
            r2 = 0
            r6 = r2
            r7 = r6
            r8 = r7
            r9 = r8
            r10 = r9
            r4 = 0
            r5 = 0
        L_0x000d:
            int r1 = r12.dataPosition()
            if (r1 >= r0) goto L_0x0059
            int r1 = r12.readInt()
            r2 = 65535(0xffff, float:9.1834E-41)
            r2 = r2 & r1
            switch(r2) {
                case 1: goto L_0x0054;
                case 2: goto L_0x004f;
                case 3: goto L_0x0045;
                case 4: goto L_0x003b;
                case 5: goto L_0x0031;
                case 6: goto L_0x0027;
                case 7: goto L_0x0022;
                default: goto L_0x001e;
            }
        L_0x001e:
            com.google.android.gms.internal.zzbgm.zzb(r12, r1)
            goto L_0x000d
        L_0x0022:
            byte[] r10 = com.google.android.gms.internal.zzbgm.zzt(r12, r1)
            goto L_0x000d
        L_0x0027:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzcux> r2 = com.google.android.gms.internal.zzcux.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r12, (int) r1, r2)
            r9 = r1
            com.google.android.gms.internal.zzcux r9 = (com.google.android.gms.internal.zzcux) r9
            goto L_0x000d
        L_0x0031:
            android.os.Parcelable$Creator<com.google.android.gms.nearby.messages.internal.zza> r2 = com.google.android.gms.nearby.messages.internal.zza.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r12, (int) r1, r2)
            r8 = r1
            com.google.android.gms.nearby.messages.internal.zza r8 = (com.google.android.gms.nearby.messages.internal.zza) r8
            goto L_0x000d
        L_0x003b:
            android.os.Parcelable$Creator<com.google.android.gms.nearby.messages.internal.zze> r2 = com.google.android.gms.nearby.messages.internal.zze.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r12, (int) r1, r2)
            r7 = r1
            com.google.android.gms.nearby.messages.internal.zze r7 = (com.google.android.gms.nearby.messages.internal.zze) r7
            goto L_0x000d
        L_0x0045:
            android.os.Parcelable$Creator<com.google.android.gms.nearby.messages.Message> r2 = com.google.android.gms.nearby.messages.Message.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r12, (int) r1, r2)
            r6 = r1
            com.google.android.gms.nearby.messages.Message r6 = (com.google.android.gms.nearby.messages.Message) r6
            goto L_0x000d
        L_0x004f:
            int r5 = com.google.android.gms.internal.zzbgm.zzg(r12, r1)
            goto L_0x000d
        L_0x0054:
            int r4 = com.google.android.gms.internal.zzbgm.zzg(r12, r1)
            goto L_0x000d
        L_0x0059:
            com.google.android.gms.internal.zzbgm.zzaf(r12, r0)
            com.google.android.gms.nearby.messages.internal.Update r12 = new com.google.android.gms.nearby.messages.internal.Update
            r3 = r12
            r3.<init>(r4, r5, r6, r7, r8, r9, r10)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.nearby.messages.internal.zzci.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Update[] newArray(int i) {
        return new Update[i];
    }
}
