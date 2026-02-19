package com.google.android.gms.internal;

import java.io.IOException;

public final class zzjd extends zzflm<zzjd> {
    public Integer zzbcv = null;
    private Integer zzbcw = null;
    private zzjf zzbcx = null;
    public zzjg zzbcy = null;
    private zzje[] zzbcz = zzje.zzhv();
    private zzjh zzbda = null;
    private zzjq zzbdb = null;
    private zzjp zzbdc = null;
    private zzjm zzbdd = null;
    private zzjn zzbde = null;
    private zzjw[] zzbdf = zzjw.zzhy();

    public zzjd() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* renamed from: zzg */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzjd zza(com.google.android.gms.internal.zzflj r7) throws java.io.IOException {
        /*
            r6 = this;
        L_0x0000:
            int r0 = r7.zzcxx()
            r1 = 0
            switch(r0) {
                case 0: goto L_0x0136;
                case 56: goto L_0x0101;
                case 64: goto L_0x00ed;
                case 74: goto L_0x00db;
                case 82: goto L_0x00cd;
                case 90: goto L_0x0092;
                case 98: goto L_0x0084;
                case 106: goto L_0x0076;
                case 114: goto L_0x0067;
                case 122: goto L_0x0058;
                case 130: goto L_0x0049;
                case 138: goto L_0x000f;
                default: goto L_0x0008;
            }
        L_0x0008:
            boolean r0 = super.zza(r7, r0)
            if (r0 != 0) goto L_0x0000
            return r6
        L_0x000f:
            r0 = 138(0x8a, float:1.93E-43)
            int r0 = com.google.android.gms.internal.zzflv.zzb(r7, r0)
            com.google.android.gms.internal.zzjw[] r2 = r6.zzbdf
            if (r2 != 0) goto L_0x001b
            r3 = 0
            goto L_0x001c
        L_0x001b:
            int r3 = r2.length
        L_0x001c:
            int r0 = r0 + r3
            com.google.android.gms.internal.zzjw[] r4 = new com.google.android.gms.internal.zzjw[r0]
            if (r3 == 0) goto L_0x0024
            java.lang.System.arraycopy(r2, r1, r4, r1, r3)
        L_0x0024:
            int r1 = r0 + -1
            if (r3 >= r1) goto L_0x003a
            com.google.android.gms.internal.zzjw r1 = new com.google.android.gms.internal.zzjw
            r1.<init>()
            r4[r3] = r1
            r1 = r4[r3]
            r7.zza(r1)
            r7.zzcxx()
            int r3 = r3 + 1
            goto L_0x0024
        L_0x003a:
            com.google.android.gms.internal.zzjw r0 = new com.google.android.gms.internal.zzjw
            r0.<init>()
            r4[r3] = r0
            r0 = r4[r3]
            r7.zza(r0)
            r6.zzbdf = r4
            goto L_0x0000
        L_0x0049:
            com.google.android.gms.internal.zzjn r0 = r6.zzbde
            if (r0 != 0) goto L_0x0054
            com.google.android.gms.internal.zzjn r0 = new com.google.android.gms.internal.zzjn
            r0.<init>()
            r6.zzbde = r0
        L_0x0054:
            com.google.android.gms.internal.zzjn r0 = r6.zzbde
            goto L_0x00e8
        L_0x0058:
            com.google.android.gms.internal.zzjm r0 = r6.zzbdd
            if (r0 != 0) goto L_0x0063
            com.google.android.gms.internal.zzjm r0 = new com.google.android.gms.internal.zzjm
            r0.<init>()
            r6.zzbdd = r0
        L_0x0063:
            com.google.android.gms.internal.zzjm r0 = r6.zzbdd
            goto L_0x00e8
        L_0x0067:
            com.google.android.gms.internal.zzjp r0 = r6.zzbdc
            if (r0 != 0) goto L_0x0072
            com.google.android.gms.internal.zzjp r0 = new com.google.android.gms.internal.zzjp
            r0.<init>()
            r6.zzbdc = r0
        L_0x0072:
            com.google.android.gms.internal.zzjp r0 = r6.zzbdc
            goto L_0x00e8
        L_0x0076:
            com.google.android.gms.internal.zzjq r0 = r6.zzbdb
            if (r0 != 0) goto L_0x0081
            com.google.android.gms.internal.zzjq r0 = new com.google.android.gms.internal.zzjq
            r0.<init>()
            r6.zzbdb = r0
        L_0x0081:
            com.google.android.gms.internal.zzjq r0 = r6.zzbdb
            goto L_0x00e8
        L_0x0084:
            com.google.android.gms.internal.zzjh r0 = r6.zzbda
            if (r0 != 0) goto L_0x008f
            com.google.android.gms.internal.zzjh r0 = new com.google.android.gms.internal.zzjh
            r0.<init>()
            r6.zzbda = r0
        L_0x008f:
            com.google.android.gms.internal.zzjh r0 = r6.zzbda
            goto L_0x00e8
        L_0x0092:
            r0 = 90
            int r0 = com.google.android.gms.internal.zzflv.zzb(r7, r0)
            com.google.android.gms.internal.zzje[] r2 = r6.zzbcz
            if (r2 != 0) goto L_0x009e
            r3 = 0
            goto L_0x009f
        L_0x009e:
            int r3 = r2.length
        L_0x009f:
            int r0 = r0 + r3
            com.google.android.gms.internal.zzje[] r4 = new com.google.android.gms.internal.zzje[r0]
            if (r3 == 0) goto L_0x00a7
            java.lang.System.arraycopy(r2, r1, r4, r1, r3)
        L_0x00a7:
            int r1 = r0 + -1
            if (r3 >= r1) goto L_0x00bd
            com.google.android.gms.internal.zzje r1 = new com.google.android.gms.internal.zzje
            r1.<init>()
            r4[r3] = r1
            r1 = r4[r3]
            r7.zza(r1)
            r7.zzcxx()
            int r3 = r3 + 1
            goto L_0x00a7
        L_0x00bd:
            com.google.android.gms.internal.zzje r0 = new com.google.android.gms.internal.zzje
            r0.<init>()
            r4[r3] = r0
            r0 = r4[r3]
            r7.zza(r0)
            r6.zzbcz = r4
            goto L_0x0000
        L_0x00cd:
            com.google.android.gms.internal.zzjg r0 = r6.zzbcy
            if (r0 != 0) goto L_0x00d8
            com.google.android.gms.internal.zzjg r0 = new com.google.android.gms.internal.zzjg
            r0.<init>()
            r6.zzbcy = r0
        L_0x00d8:
            com.google.android.gms.internal.zzjg r0 = r6.zzbcy
            goto L_0x00e8
        L_0x00db:
            com.google.android.gms.internal.zzjf r0 = r6.zzbcx
            if (r0 != 0) goto L_0x00e6
            com.google.android.gms.internal.zzjf r0 = new com.google.android.gms.internal.zzjf
            r0.<init>()
            r6.zzbcx = r0
        L_0x00e6:
            com.google.android.gms.internal.zzjf r0 = r6.zzbcx
        L_0x00e8:
            r7.zza(r0)
            goto L_0x0000
        L_0x00ed:
            int r1 = r7.getPosition()
            int r2 = r7.zzcym()     // Catch:{ IllegalArgumentException -> 0x012e }
            int r2 = com.google.android.gms.internal.zzjc.zzd(r2)     // Catch:{ IllegalArgumentException -> 0x012e }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ IllegalArgumentException -> 0x012e }
            r6.zzbcw = r2     // Catch:{ IllegalArgumentException -> 0x012e }
            goto L_0x0000
        L_0x0101:
            int r1 = r7.getPosition()
            int r2 = r7.zzcym()     // Catch:{ IllegalArgumentException -> 0x012e }
            switch(r2) {
                case 0: goto L_0x010f;
                case 1: goto L_0x010f;
                case 2: goto L_0x010f;
                case 3: goto L_0x010f;
                case 4: goto L_0x010f;
                case 5: goto L_0x010f;
                case 6: goto L_0x010f;
                case 7: goto L_0x010f;
                case 8: goto L_0x010f;
                case 9: goto L_0x010f;
                default: goto L_0x010c;
            }     // Catch:{ IllegalArgumentException -> 0x012e }
        L_0x010c:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x012e }
            goto L_0x0117
        L_0x010f:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ IllegalArgumentException -> 0x012e }
            r6.zzbcv = r2     // Catch:{ IllegalArgumentException -> 0x012e }
            goto L_0x0000
        L_0x0117:
            r4 = 43
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x012e }
            r5.<init>(r4)     // Catch:{ IllegalArgumentException -> 0x012e }
            r5.append(r2)     // Catch:{ IllegalArgumentException -> 0x012e }
            java.lang.String r2 = " is not a valid enum AdInitiater"
            r5.append(r2)     // Catch:{ IllegalArgumentException -> 0x012e }
            java.lang.String r2 = r5.toString()     // Catch:{ IllegalArgumentException -> 0x012e }
            r3.<init>(r2)     // Catch:{ IllegalArgumentException -> 0x012e }
            throw r3     // Catch:{ IllegalArgumentException -> 0x012e }
        L_0x012e:
            r7.zzmw(r1)
            r6.zza(r7, r0)
            goto L_0x0000
        L_0x0136:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzjd.zza(com.google.android.gms.internal.zzflj):com.google.android.gms.internal.zzjd");
    }

    public final void zza(zzflk zzflk) throws IOException {
        Integer num = this.zzbcv;
        if (num != null) {
            zzflk.zzad(7, num.intValue());
        }
        Integer num2 = this.zzbcw;
        if (num2 != null) {
            zzflk.zzad(8, num2.intValue());
        }
        zzjf zzjf = this.zzbcx;
        if (zzjf != null) {
            zzflk.zza(9, (zzfls) zzjf);
        }
        zzjg zzjg = this.zzbcy;
        if (zzjg != null) {
            zzflk.zza(10, (zzfls) zzjg);
        }
        zzje[] zzjeArr = this.zzbcz;
        int i = 0;
        if (zzjeArr != null && zzjeArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzje[] zzjeArr2 = this.zzbcz;
                if (i2 >= zzjeArr2.length) {
                    break;
                }
                zzje zzje = zzjeArr2[i2];
                if (zzje != null) {
                    zzflk.zza(11, (zzfls) zzje);
                }
                i2++;
            }
        }
        zzjh zzjh = this.zzbda;
        if (zzjh != null) {
            zzflk.zza(12, (zzfls) zzjh);
        }
        zzjq zzjq = this.zzbdb;
        if (zzjq != null) {
            zzflk.zza(13, (zzfls) zzjq);
        }
        zzjp zzjp = this.zzbdc;
        if (zzjp != null) {
            zzflk.zza(14, (zzfls) zzjp);
        }
        zzjm zzjm = this.zzbdd;
        if (zzjm != null) {
            zzflk.zza(15, (zzfls) zzjm);
        }
        zzjn zzjn = this.zzbde;
        if (zzjn != null) {
            zzflk.zza(16, (zzfls) zzjn);
        }
        zzjw[] zzjwArr = this.zzbdf;
        if (zzjwArr != null && zzjwArr.length > 0) {
            while (true) {
                zzjw[] zzjwArr2 = this.zzbdf;
                if (i >= zzjwArr2.length) {
                    break;
                }
                zzjw zzjw = zzjwArr2[i];
                if (zzjw != null) {
                    zzflk.zza(17, (zzfls) zzjw);
                }
                i++;
            }
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        Integer num = this.zzbcv;
        if (num != null) {
            zzq += zzflk.zzag(7, num.intValue());
        }
        Integer num2 = this.zzbcw;
        if (num2 != null) {
            zzq += zzflk.zzag(8, num2.intValue());
        }
        zzjf zzjf = this.zzbcx;
        if (zzjf != null) {
            zzq += zzflk.zzb(9, (zzfls) zzjf);
        }
        zzjg zzjg = this.zzbcy;
        if (zzjg != null) {
            zzq += zzflk.zzb(10, (zzfls) zzjg);
        }
        zzje[] zzjeArr = this.zzbcz;
        int i = 0;
        if (zzjeArr != null && zzjeArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzje[] zzjeArr2 = this.zzbcz;
                if (i2 >= zzjeArr2.length) {
                    break;
                }
                zzje zzje = zzjeArr2[i2];
                if (zzje != null) {
                    zzq += zzflk.zzb(11, (zzfls) zzje);
                }
                i2++;
            }
        }
        zzjh zzjh = this.zzbda;
        if (zzjh != null) {
            zzq += zzflk.zzb(12, (zzfls) zzjh);
        }
        zzjq zzjq = this.zzbdb;
        if (zzjq != null) {
            zzq += zzflk.zzb(13, (zzfls) zzjq);
        }
        zzjp zzjp = this.zzbdc;
        if (zzjp != null) {
            zzq += zzflk.zzb(14, (zzfls) zzjp);
        }
        zzjm zzjm = this.zzbdd;
        if (zzjm != null) {
            zzq += zzflk.zzb(15, (zzfls) zzjm);
        }
        zzjn zzjn = this.zzbde;
        if (zzjn != null) {
            zzq += zzflk.zzb(16, (zzfls) zzjn);
        }
        zzjw[] zzjwArr = this.zzbdf;
        if (zzjwArr != null && zzjwArr.length > 0) {
            while (true) {
                zzjw[] zzjwArr2 = this.zzbdf;
                if (i >= zzjwArr2.length) {
                    break;
                }
                zzjw zzjw = zzjwArr2[i];
                if (zzjw != null) {
                    zzq += zzflk.zzb(17, (zzfls) zzjw);
                }
                i++;
            }
        }
        return zzq;
    }
}
