package com.google.android.gms.internal;

import java.io.IOException;

public final class zzflw extends zzflm<zzflw> {
    public String url = null;
    public Integer zzbdh = null;
    private Integer zzpwf = null;
    public String zzpwg = null;
    private String zzpwh = null;
    public zzflx zzpwi = null;
    public zzfme[] zzpwj = zzfme.zzdct();
    public String zzpwk = null;
    public zzfmd zzpwl = null;
    private Boolean zzpwm = null;
    private String[] zzpwn = zzflv.EMPTY_STRING_ARRAY;
    private String zzpwo = null;
    private Boolean zzpwp = null;
    private Boolean zzpwq = null;
    private byte[] zzpwr = null;
    public zzfmf zzpws = null;

    public zzflw() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* renamed from: zzbk */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzflw zza(com.google.android.gms.internal.zzflj r7) throws java.io.IOException {
        /*
            r6 = this;
        L_0x0000:
            int r0 = r7.zzcxx()
            r1 = 0
            switch(r0) {
                case 0: goto L_0x0165;
                case 10: goto L_0x015d;
                case 18: goto L_0x0155;
                case 26: goto L_0x014d;
                case 34: goto L_0x0112;
                case 40: goto L_0x0106;
                case 50: goto L_0x00d7;
                case 58: goto L_0x00cf;
                case 64: goto L_0x00c3;
                case 72: goto L_0x00b7;
                case 80: goto L_0x0082;
                case 88: goto L_0x004a;
                case 98: goto L_0x0039;
                case 106: goto L_0x0032;
                case 114: goto L_0x0024;
                case 122: goto L_0x001d;
                case 138: goto L_0x000f;
                default: goto L_0x0008;
            }
        L_0x0008:
            boolean r0 = super.zza(r7, r0)
            if (r0 != 0) goto L_0x0000
            return r6
        L_0x000f:
            com.google.android.gms.internal.zzfmf r0 = r6.zzpws
            if (r0 != 0) goto L_0x001a
            com.google.android.gms.internal.zzfmf r0 = new com.google.android.gms.internal.zzfmf
            r0.<init>()
            r6.zzpws = r0
        L_0x001a:
            com.google.android.gms.internal.zzfmf r0 = r6.zzpws
            goto L_0x0046
        L_0x001d:
            byte[] r0 = r7.readBytes()
            r6.zzpwr = r0
            goto L_0x0000
        L_0x0024:
            com.google.android.gms.internal.zzfmd r0 = r6.zzpwl
            if (r0 != 0) goto L_0x002f
            com.google.android.gms.internal.zzfmd r0 = new com.google.android.gms.internal.zzfmd
            r0.<init>()
            r6.zzpwl = r0
        L_0x002f:
            com.google.android.gms.internal.zzfmd r0 = r6.zzpwl
            goto L_0x0046
        L_0x0032:
            java.lang.String r0 = r7.readString()
            r6.zzpwk = r0
            goto L_0x0000
        L_0x0039:
            com.google.android.gms.internal.zzflx r0 = r6.zzpwi
            if (r0 != 0) goto L_0x0044
            com.google.android.gms.internal.zzflx r0 = new com.google.android.gms.internal.zzflx
            r0.<init>()
            r6.zzpwi = r0
        L_0x0044:
            com.google.android.gms.internal.zzflx r0 = r6.zzpwi
        L_0x0046:
            r7.zza(r0)
            goto L_0x0000
        L_0x004a:
            int r1 = r7.getPosition()
            int r2 = r7.zzcya()     // Catch:{ IllegalArgumentException -> 0x00af }
            if (r2 == 0) goto L_0x007a
            r3 = 1
            if (r2 == r3) goto L_0x007a
            r3 = 2
            if (r2 == r3) goto L_0x007a
            r3 = 3
            if (r2 == r3) goto L_0x007a
            r3 = 4
            if (r2 != r3) goto L_0x0061
            goto L_0x007a
        L_0x0061:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x00af }
            r4 = 39
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x00af }
            r5.<init>(r4)     // Catch:{ IllegalArgumentException -> 0x00af }
            r5.append(r2)     // Catch:{ IllegalArgumentException -> 0x00af }
            java.lang.String r2 = " is not a valid enum Verdict"
            r5.append(r2)     // Catch:{ IllegalArgumentException -> 0x00af }
            java.lang.String r2 = r5.toString()     // Catch:{ IllegalArgumentException -> 0x00af }
            r3.<init>(r2)     // Catch:{ IllegalArgumentException -> 0x00af }
            throw r3     // Catch:{ IllegalArgumentException -> 0x00af }
        L_0x007a:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ IllegalArgumentException -> 0x00af }
            r6.zzpwf = r2     // Catch:{ IllegalArgumentException -> 0x00af }
            goto L_0x0000
        L_0x0082:
            int r1 = r7.getPosition()
            int r2 = r7.zzcya()     // Catch:{ IllegalArgumentException -> 0x00af }
            switch(r2) {
                case 0: goto L_0x0090;
                case 1: goto L_0x0090;
                case 2: goto L_0x0090;
                case 3: goto L_0x0090;
                case 4: goto L_0x0090;
                case 5: goto L_0x0090;
                case 6: goto L_0x0090;
                case 7: goto L_0x0090;
                case 8: goto L_0x0090;
                case 9: goto L_0x0090;
                default: goto L_0x008d;
            }     // Catch:{ IllegalArgumentException -> 0x00af }
        L_0x008d:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x00af }
            goto L_0x0098
        L_0x0090:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ IllegalArgumentException -> 0x00af }
            r6.zzbdh = r2     // Catch:{ IllegalArgumentException -> 0x00af }
            goto L_0x0000
        L_0x0098:
            r4 = 42
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x00af }
            r5.<init>(r4)     // Catch:{ IllegalArgumentException -> 0x00af }
            r5.append(r2)     // Catch:{ IllegalArgumentException -> 0x00af }
            java.lang.String r2 = " is not a valid enum ReportType"
            r5.append(r2)     // Catch:{ IllegalArgumentException -> 0x00af }
            java.lang.String r2 = r5.toString()     // Catch:{ IllegalArgumentException -> 0x00af }
            r3.<init>(r2)     // Catch:{ IllegalArgumentException -> 0x00af }
            throw r3     // Catch:{ IllegalArgumentException -> 0x00af }
        L_0x00af:
            r7.zzmw(r1)
            r6.zza(r7, r0)
            goto L_0x0000
        L_0x00b7:
            boolean r0 = r7.zzcyd()
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            r6.zzpwq = r0
            goto L_0x0000
        L_0x00c3:
            boolean r0 = r7.zzcyd()
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            r6.zzpwp = r0
            goto L_0x0000
        L_0x00cf:
            java.lang.String r0 = r7.readString()
            r6.zzpwo = r0
            goto L_0x0000
        L_0x00d7:
            r0 = 50
            int r0 = com.google.android.gms.internal.zzflv.zzb(r7, r0)
            java.lang.String[] r2 = r6.zzpwn
            if (r2 != 0) goto L_0x00e3
            r3 = 0
            goto L_0x00e4
        L_0x00e3:
            int r3 = r2.length
        L_0x00e4:
            int r0 = r0 + r3
            java.lang.String[] r4 = new java.lang.String[r0]
            if (r3 == 0) goto L_0x00ec
            java.lang.System.arraycopy(r2, r1, r4, r1, r3)
        L_0x00ec:
            int r1 = r0 + -1
            if (r3 >= r1) goto L_0x00fc
            java.lang.String r1 = r7.readString()
            r4[r3] = r1
            r7.zzcxx()
            int r3 = r3 + 1
            goto L_0x00ec
        L_0x00fc:
            java.lang.String r0 = r7.readString()
            r4[r3] = r0
            r6.zzpwn = r4
            goto L_0x0000
        L_0x0106:
            boolean r0 = r7.zzcyd()
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            r6.zzpwm = r0
            goto L_0x0000
        L_0x0112:
            r0 = 34
            int r0 = com.google.android.gms.internal.zzflv.zzb(r7, r0)
            com.google.android.gms.internal.zzfme[] r2 = r6.zzpwj
            if (r2 != 0) goto L_0x011e
            r3 = 0
            goto L_0x011f
        L_0x011e:
            int r3 = r2.length
        L_0x011f:
            int r0 = r0 + r3
            com.google.android.gms.internal.zzfme[] r4 = new com.google.android.gms.internal.zzfme[r0]
            if (r3 == 0) goto L_0x0127
            java.lang.System.arraycopy(r2, r1, r4, r1, r3)
        L_0x0127:
            int r1 = r0 + -1
            if (r3 >= r1) goto L_0x013d
            com.google.android.gms.internal.zzfme r1 = new com.google.android.gms.internal.zzfme
            r1.<init>()
            r4[r3] = r1
            r1 = r4[r3]
            r7.zza(r1)
            r7.zzcxx()
            int r3 = r3 + 1
            goto L_0x0127
        L_0x013d:
            com.google.android.gms.internal.zzfme r0 = new com.google.android.gms.internal.zzfme
            r0.<init>()
            r4[r3] = r0
            r0 = r4[r3]
            r7.zza(r0)
            r6.zzpwj = r4
            goto L_0x0000
        L_0x014d:
            java.lang.String r0 = r7.readString()
            r6.zzpwh = r0
            goto L_0x0000
        L_0x0155:
            java.lang.String r0 = r7.readString()
            r6.zzpwg = r0
            goto L_0x0000
        L_0x015d:
            java.lang.String r0 = r7.readString()
            r6.url = r0
            goto L_0x0000
        L_0x0165:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzflw.zza(com.google.android.gms.internal.zzflj):com.google.android.gms.internal.zzflw");
    }

    public final void zza(zzflk zzflk) throws IOException {
        String str = this.url;
        if (str != null) {
            zzflk.zzp(1, str);
        }
        String str2 = this.zzpwg;
        if (str2 != null) {
            zzflk.zzp(2, str2);
        }
        String str3 = this.zzpwh;
        if (str3 != null) {
            zzflk.zzp(3, str3);
        }
        zzfme[] zzfmeArr = this.zzpwj;
        int i = 0;
        if (zzfmeArr != null && zzfmeArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzfme[] zzfmeArr2 = this.zzpwj;
                if (i2 >= zzfmeArr2.length) {
                    break;
                }
                zzfme zzfme = zzfmeArr2[i2];
                if (zzfme != null) {
                    zzflk.zza(4, (zzfls) zzfme);
                }
                i2++;
            }
        }
        Boolean bool = this.zzpwm;
        if (bool != null) {
            zzflk.zzl(5, bool.booleanValue());
        }
        String[] strArr = this.zzpwn;
        if (strArr != null && strArr.length > 0) {
            while (true) {
                String[] strArr2 = this.zzpwn;
                if (i >= strArr2.length) {
                    break;
                }
                String str4 = strArr2[i];
                if (str4 != null) {
                    zzflk.zzp(6, str4);
                }
                i++;
            }
        }
        String str5 = this.zzpwo;
        if (str5 != null) {
            zzflk.zzp(7, str5);
        }
        Boolean bool2 = this.zzpwp;
        if (bool2 != null) {
            zzflk.zzl(8, bool2.booleanValue());
        }
        Boolean bool3 = this.zzpwq;
        if (bool3 != null) {
            zzflk.zzl(9, bool3.booleanValue());
        }
        Integer num = this.zzbdh;
        if (num != null) {
            zzflk.zzad(10, num.intValue());
        }
        Integer num2 = this.zzpwf;
        if (num2 != null) {
            zzflk.zzad(11, num2.intValue());
        }
        zzflx zzflx = this.zzpwi;
        if (zzflx != null) {
            zzflk.zza(12, (zzfls) zzflx);
        }
        String str6 = this.zzpwk;
        if (str6 != null) {
            zzflk.zzp(13, str6);
        }
        zzfmd zzfmd = this.zzpwl;
        if (zzfmd != null) {
            zzflk.zza(14, (zzfls) zzfmd);
        }
        byte[] bArr = this.zzpwr;
        if (bArr != null) {
            zzflk.zzc(15, bArr);
        }
        zzfmf zzfmf = this.zzpws;
        if (zzfmf != null) {
            zzflk.zza(17, (zzfls) zzfmf);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        String str = this.url;
        if (str != null) {
            zzq += zzflk.zzq(1, str);
        }
        String str2 = this.zzpwg;
        if (str2 != null) {
            zzq += zzflk.zzq(2, str2);
        }
        String str3 = this.zzpwh;
        if (str3 != null) {
            zzq += zzflk.zzq(3, str3);
        }
        zzfme[] zzfmeArr = this.zzpwj;
        int i = 0;
        if (zzfmeArr != null && zzfmeArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzfme[] zzfmeArr2 = this.zzpwj;
                if (i2 >= zzfmeArr2.length) {
                    break;
                }
                zzfme zzfme = zzfmeArr2[i2];
                if (zzfme != null) {
                    zzq += zzflk.zzb(4, (zzfls) zzfme);
                }
                i2++;
            }
        }
        Boolean bool = this.zzpwm;
        if (bool != null) {
            bool.booleanValue();
            zzq += zzflk.zzlw(5) + 1;
        }
        String[] strArr = this.zzpwn;
        if (strArr != null && strArr.length > 0) {
            int i3 = 0;
            int i4 = 0;
            while (true) {
                String[] strArr2 = this.zzpwn;
                if (i >= strArr2.length) {
                    break;
                }
                String str4 = strArr2[i];
                if (str4 != null) {
                    i4++;
                    i3 += zzflk.zztx(str4);
                }
                i++;
            }
            zzq = zzq + i3 + (i4 * 1);
        }
        String str5 = this.zzpwo;
        if (str5 != null) {
            zzq += zzflk.zzq(7, str5);
        }
        Boolean bool2 = this.zzpwp;
        if (bool2 != null) {
            bool2.booleanValue();
            zzq += zzflk.zzlw(8) + 1;
        }
        Boolean bool3 = this.zzpwq;
        if (bool3 != null) {
            bool3.booleanValue();
            zzq += zzflk.zzlw(9) + 1;
        }
        Integer num = this.zzbdh;
        if (num != null) {
            zzq += zzflk.zzag(10, num.intValue());
        }
        Integer num2 = this.zzpwf;
        if (num2 != null) {
            zzq += zzflk.zzag(11, num2.intValue());
        }
        zzflx zzflx = this.zzpwi;
        if (zzflx != null) {
            zzq += zzflk.zzb(12, (zzfls) zzflx);
        }
        String str6 = this.zzpwk;
        if (str6 != null) {
            zzq += zzflk.zzq(13, str6);
        }
        zzfmd zzfmd = this.zzpwl;
        if (zzfmd != null) {
            zzq += zzflk.zzb(14, (zzfls) zzfmd);
        }
        byte[] bArr = this.zzpwr;
        if (bArr != null) {
            zzq += zzflk.zzd(15, bArr);
        }
        zzfmf zzfmf = this.zzpws;
        return zzfmf != null ? zzq + zzflk.zzb(17, (zzfls) zzfmf) : zzq;
    }
}
