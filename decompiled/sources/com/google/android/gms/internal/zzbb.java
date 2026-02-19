package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbb extends zzflm<zzbb> {
    private static volatile zzbb[] zzfi;
    public Long zzdm = null;
    public Long zzdn = null;
    public Long zzfj = null;
    public Long zzfk = null;
    public Long zzfl = null;
    public Long zzfm = null;
    public Integer zzfn;
    public Long zzfo = null;
    public Long zzfp = null;
    public Long zzfq = null;
    public Integer zzfr;
    public Long zzfs = null;
    public Long zzft = null;
    public Long zzfu = null;
    public Long zzfv = null;
    public Long zzfw = null;
    public Long zzfx = null;
    public Long zzfy = null;
    public Long zzfz = null;
    private Long zzga = null;
    private Long zzgb = null;

    public zzbb() {
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* renamed from: zzc */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzbb zza(com.google.android.gms.internal.zzflj r4) throws java.io.IOException {
        /*
            r3 = this;
        L_0x0000:
            int r0 = r4.zzcxx()
            switch(r0) {
                case 0: goto L_0x0118;
                case 8: goto L_0x010c;
                case 16: goto L_0x0100;
                case 24: goto L_0x00f4;
                case 32: goto L_0x00e8;
                case 40: goto L_0x00dc;
                case 48: goto L_0x00d0;
                case 56: goto L_0x00b4;
                case 64: goto L_0x00a8;
                case 72: goto L_0x009c;
                case 80: goto L_0x0090;
                case 88: goto L_0x007c;
                case 96: goto L_0x0071;
                case 104: goto L_0x0066;
                case 112: goto L_0x005b;
                case 120: goto L_0x0050;
                case 128: goto L_0x0045;
                case 136: goto L_0x003a;
                case 144: goto L_0x002f;
                case 152: goto L_0x0024;
                case 160: goto L_0x0019;
                case 168: goto L_0x000e;
                default: goto L_0x0007;
            }
        L_0x0007:
            boolean r0 = super.zza(r4, r0)
            if (r0 != 0) goto L_0x0000
            return r3
        L_0x000e:
            long r0 = r4.zzcyr()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r3.zzgb = r0
            goto L_0x0000
        L_0x0019:
            long r0 = r4.zzcyr()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r3.zzga = r0
            goto L_0x0000
        L_0x0024:
            long r0 = r4.zzcyr()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r3.zzfz = r0
            goto L_0x0000
        L_0x002f:
            long r0 = r4.zzcyr()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r3.zzfy = r0
            goto L_0x0000
        L_0x003a:
            long r0 = r4.zzcyr()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r3.zzfx = r0
            goto L_0x0000
        L_0x0045:
            long r0 = r4.zzcyr()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r3.zzfw = r0
            goto L_0x0000
        L_0x0050:
            long r0 = r4.zzcyr()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r3.zzfv = r0
            goto L_0x0000
        L_0x005b:
            long r0 = r4.zzcyr()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r3.zzfu = r0
            goto L_0x0000
        L_0x0066:
            long r0 = r4.zzcyr()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r3.zzft = r0
            goto L_0x0000
        L_0x0071:
            long r0 = r4.zzcyr()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r3.zzfs = r0
            goto L_0x0000
        L_0x007c:
            int r1 = r4.getPosition()
            int r2 = r4.zzcym()     // Catch:{ IllegalArgumentException -> 0x00c8 }
            int r2 = com.google.android.gms.internal.zzaz.zzd(r2)     // Catch:{ IllegalArgumentException -> 0x00c8 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ IllegalArgumentException -> 0x00c8 }
            r3.zzfr = r2     // Catch:{ IllegalArgumentException -> 0x00c8 }
            goto L_0x0000
        L_0x0090:
            long r0 = r4.zzcyr()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r3.zzfq = r0
            goto L_0x0000
        L_0x009c:
            long r0 = r4.zzcyr()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r3.zzfp = r0
            goto L_0x0000
        L_0x00a8:
            long r0 = r4.zzcyr()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r3.zzfo = r0
            goto L_0x0000
        L_0x00b4:
            int r1 = r4.getPosition()
            int r2 = r4.zzcym()     // Catch:{ IllegalArgumentException -> 0x00c8 }
            int r2 = com.google.android.gms.internal.zzaz.zzd(r2)     // Catch:{ IllegalArgumentException -> 0x00c8 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ IllegalArgumentException -> 0x00c8 }
            r3.zzfn = r2     // Catch:{ IllegalArgumentException -> 0x00c8 }
            goto L_0x0000
        L_0x00c8:
            r4.zzmw(r1)
            r3.zza(r4, r0)
            goto L_0x0000
        L_0x00d0:
            long r0 = r4.zzcyr()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r3.zzfm = r0
            goto L_0x0000
        L_0x00dc:
            long r0 = r4.zzcyr()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r3.zzfl = r0
            goto L_0x0000
        L_0x00e8:
            long r0 = r4.zzcyr()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r3.zzfk = r0
            goto L_0x0000
        L_0x00f4:
            long r0 = r4.zzcyr()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r3.zzfj = r0
            goto L_0x0000
        L_0x0100:
            long r0 = r4.zzcyr()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r3.zzdn = r0
            goto L_0x0000
        L_0x010c:
            long r0 = r4.zzcyr()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r3.zzdm = r0
            goto L_0x0000
        L_0x0118:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzbb.zza(com.google.android.gms.internal.zzflj):com.google.android.gms.internal.zzbb");
    }

    public static zzbb[] zzr() {
        if (zzfi == null) {
            synchronized (zzflq.zzpvt) {
                if (zzfi == null) {
                    zzfi = new zzbb[0];
                }
            }
        }
        return zzfi;
    }

    public final void zza(zzflk zzflk) throws IOException {
        Long l = this.zzdm;
        if (l != null) {
            zzflk.zzf(1, l.longValue());
        }
        Long l2 = this.zzdn;
        if (l2 != null) {
            zzflk.zzf(2, l2.longValue());
        }
        Long l3 = this.zzfj;
        if (l3 != null) {
            zzflk.zzf(3, l3.longValue());
        }
        Long l4 = this.zzfk;
        if (l4 != null) {
            zzflk.zzf(4, l4.longValue());
        }
        Long l5 = this.zzfl;
        if (l5 != null) {
            zzflk.zzf(5, l5.longValue());
        }
        Long l6 = this.zzfm;
        if (l6 != null) {
            zzflk.zzf(6, l6.longValue());
        }
        Integer num = this.zzfn;
        if (num != null) {
            zzflk.zzad(7, num.intValue());
        }
        Long l7 = this.zzfo;
        if (l7 != null) {
            zzflk.zzf(8, l7.longValue());
        }
        Long l8 = this.zzfp;
        if (l8 != null) {
            zzflk.zzf(9, l8.longValue());
        }
        Long l9 = this.zzfq;
        if (l9 != null) {
            zzflk.zzf(10, l9.longValue());
        }
        Integer num2 = this.zzfr;
        if (num2 != null) {
            zzflk.zzad(11, num2.intValue());
        }
        Long l10 = this.zzfs;
        if (l10 != null) {
            zzflk.zzf(12, l10.longValue());
        }
        Long l11 = this.zzft;
        if (l11 != null) {
            zzflk.zzf(13, l11.longValue());
        }
        Long l12 = this.zzfu;
        if (l12 != null) {
            zzflk.zzf(14, l12.longValue());
        }
        Long l13 = this.zzfv;
        if (l13 != null) {
            zzflk.zzf(15, l13.longValue());
        }
        Long l14 = this.zzfw;
        if (l14 != null) {
            zzflk.zzf(16, l14.longValue());
        }
        Long l15 = this.zzfx;
        if (l15 != null) {
            zzflk.zzf(17, l15.longValue());
        }
        Long l16 = this.zzfy;
        if (l16 != null) {
            zzflk.zzf(18, l16.longValue());
        }
        Long l17 = this.zzfz;
        if (l17 != null) {
            zzflk.zzf(19, l17.longValue());
        }
        Long l18 = this.zzga;
        if (l18 != null) {
            zzflk.zzf(20, l18.longValue());
        }
        Long l19 = this.zzgb;
        if (l19 != null) {
            zzflk.zzf(21, l19.longValue());
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        Long l = this.zzdm;
        if (l != null) {
            zzq += zzflk.zzc(1, l.longValue());
        }
        Long l2 = this.zzdn;
        if (l2 != null) {
            zzq += zzflk.zzc(2, l2.longValue());
        }
        Long l3 = this.zzfj;
        if (l3 != null) {
            zzq += zzflk.zzc(3, l3.longValue());
        }
        Long l4 = this.zzfk;
        if (l4 != null) {
            zzq += zzflk.zzc(4, l4.longValue());
        }
        Long l5 = this.zzfl;
        if (l5 != null) {
            zzq += zzflk.zzc(5, l5.longValue());
        }
        Long l6 = this.zzfm;
        if (l6 != null) {
            zzq += zzflk.zzc(6, l6.longValue());
        }
        Integer num = this.zzfn;
        if (num != null) {
            zzq += zzflk.zzag(7, num.intValue());
        }
        Long l7 = this.zzfo;
        if (l7 != null) {
            zzq += zzflk.zzc(8, l7.longValue());
        }
        Long l8 = this.zzfp;
        if (l8 != null) {
            zzq += zzflk.zzc(9, l8.longValue());
        }
        Long l9 = this.zzfq;
        if (l9 != null) {
            zzq += zzflk.zzc(10, l9.longValue());
        }
        Integer num2 = this.zzfr;
        if (num2 != null) {
            zzq += zzflk.zzag(11, num2.intValue());
        }
        Long l10 = this.zzfs;
        if (l10 != null) {
            zzq += zzflk.zzc(12, l10.longValue());
        }
        Long l11 = this.zzft;
        if (l11 != null) {
            zzq += zzflk.zzc(13, l11.longValue());
        }
        Long l12 = this.zzfu;
        if (l12 != null) {
            zzq += zzflk.zzc(14, l12.longValue());
        }
        Long l13 = this.zzfv;
        if (l13 != null) {
            zzq += zzflk.zzc(15, l13.longValue());
        }
        Long l14 = this.zzfw;
        if (l14 != null) {
            zzq += zzflk.zzc(16, l14.longValue());
        }
        Long l15 = this.zzfx;
        if (l15 != null) {
            zzq += zzflk.zzc(17, l15.longValue());
        }
        Long l16 = this.zzfy;
        if (l16 != null) {
            zzq += zzflk.zzc(18, l16.longValue());
        }
        Long l17 = this.zzfz;
        if (l17 != null) {
            zzq += zzflk.zzc(19, l17.longValue());
        }
        Long l18 = this.zzga;
        if (l18 != null) {
            zzq += zzflk.zzc(20, l18.longValue());
        }
        Long l19 = this.zzgb;
        return l19 != null ? zzq + zzflk.zzc(21, l19.longValue()) : zzq;
    }
}
