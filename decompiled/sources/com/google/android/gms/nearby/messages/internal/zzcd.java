package com.google.android.gms.nearby.messages.internal;

import android.os.Parcelable;

public final class zzcd implements Parcelable.Creator<SubscribeRequest> {
    /* JADX WARNING: type inference failed for: r2v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v4, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v5, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v6, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ SubscribeRequest createFromParcel(android.os.Parcel r23) {
        /*
            r22 = this;
            r0 = r23
            int r1 = com.google.android.gms.internal.zzbgm.zzd(r23)
            r2 = 0
            r3 = 0
            r6 = r3
            r7 = r6
            r8 = r7
            r9 = r8
            r10 = r9
            r12 = r10
            r13 = r12
            r14 = r13
            r16 = r14
            r18 = r16
            r5 = 0
            r11 = 0
            r15 = 0
            r17 = 0
            r19 = 0
            r20 = 0
            r21 = 0
        L_0x001f:
            int r2 = r23.dataPosition()
            if (r2 >= r1) goto L_0x009e
            int r2 = r23.readInt()
            r3 = 65535(0xffff, float:9.1834E-41)
            r3 = r3 & r2
            switch(r3) {
                case 1: goto L_0x0099;
                case 2: goto L_0x0094;
                case 3: goto L_0x008a;
                case 4: goto L_0x0085;
                case 5: goto L_0x007b;
                case 6: goto L_0x0071;
                case 7: goto L_0x006c;
                case 8: goto L_0x0067;
                case 9: goto L_0x0062;
                case 10: goto L_0x005d;
                case 11: goto L_0x0058;
                case 12: goto L_0x0053;
                case 13: goto L_0x004e;
                case 14: goto L_0x0043;
                case 15: goto L_0x003e;
                case 16: goto L_0x0039;
                case 17: goto L_0x0034;
                default: goto L_0x0030;
            }
        L_0x0030:
            com.google.android.gms.internal.zzbgm.zzb(r0, r2)
            goto L_0x001f
        L_0x0034:
            int r21 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            goto L_0x001f
        L_0x0039:
            int r20 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            goto L_0x001f
        L_0x003e:
            boolean r19 = com.google.android.gms.internal.zzbgm.zzc(r0, r2)
            goto L_0x001f
        L_0x0043:
            android.os.Parcelable$Creator<com.google.android.gms.nearby.messages.internal.ClientAppContext> r3 = com.google.android.gms.nearby.messages.internal.ClientAppContext.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r18 = r2
            com.google.android.gms.nearby.messages.internal.ClientAppContext r18 = (com.google.android.gms.nearby.messages.internal.ClientAppContext) r18
            goto L_0x001f
        L_0x004e:
            boolean r17 = com.google.android.gms.internal.zzbgm.zzc(r0, r2)
            goto L_0x001f
        L_0x0053:
            android.os.IBinder r16 = com.google.android.gms.internal.zzbgm.zzr(r0, r2)
            goto L_0x001f
        L_0x0058:
            boolean r15 = com.google.android.gms.internal.zzbgm.zzc(r0, r2)
            goto L_0x001f
        L_0x005d:
            byte[] r14 = com.google.android.gms.internal.zzbgm.zzt(r0, r2)
            goto L_0x001f
        L_0x0062:
            java.lang.String r13 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x001f
        L_0x0067:
            java.lang.String r12 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x001f
        L_0x006c:
            int r11 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            goto L_0x001f
        L_0x0071:
            android.os.Parcelable$Creator r3 = android.app.PendingIntent.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r10 = r2
            android.app.PendingIntent r10 = (android.app.PendingIntent) r10
            goto L_0x001f
        L_0x007b:
            android.os.Parcelable$Creator<com.google.android.gms.nearby.messages.MessageFilter> r3 = com.google.android.gms.nearby.messages.MessageFilter.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r9 = r2
            com.google.android.gms.nearby.messages.MessageFilter r9 = (com.google.android.gms.nearby.messages.MessageFilter) r9
            goto L_0x001f
        L_0x0085:
            android.os.IBinder r8 = com.google.android.gms.internal.zzbgm.zzr(r0, r2)
            goto L_0x001f
        L_0x008a:
            android.os.Parcelable$Creator<com.google.android.gms.nearby.messages.Strategy> r3 = com.google.android.gms.nearby.messages.Strategy.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r7 = r2
            com.google.android.gms.nearby.messages.Strategy r7 = (com.google.android.gms.nearby.messages.Strategy) r7
            goto L_0x001f
        L_0x0094:
            android.os.IBinder r6 = com.google.android.gms.internal.zzbgm.zzr(r0, r2)
            goto L_0x001f
        L_0x0099:
            int r5 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            goto L_0x001f
        L_0x009e:
            com.google.android.gms.internal.zzbgm.zzaf(r0, r1)
            com.google.android.gms.nearby.messages.internal.SubscribeRequest r0 = new com.google.android.gms.nearby.messages.internal.SubscribeRequest
            r4 = r0
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.nearby.messages.internal.zzcd.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ SubscribeRequest[] newArray(int i) {
        return new SubscribeRequest[i];
    }
}
