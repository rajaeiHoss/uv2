package com.google.android.gms.internal;

@zzabh
final class zzuv {
    private static final zzus zzceu = zzus.zzln();
    private static final float zzcev = ((Float) zzlc.zzio().zzd(zzoi.zzbqd)).floatValue();
    private static final long zzcew = ((Long) zzlc.zzio().zzd(zzoi.zzbqb)).longValue();
    private static final float zzcex = ((Float) zzlc.zzio().zzd(zzoi.zzbqe)).floatValue();
    private static final long zzcey = ((Long) zzlc.zzio().zzd(zzoi.zzbqc)).longValue();

    private static int zzb(long j, int i) {
        return (int) ((j >>> ((i % 16) * 4)) & 15);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0053 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:19:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean zzlx() {
        /*
            com.google.android.gms.internal.zzus r0 = zzceu
            int r1 = r0.zzlu()
            int r2 = r0.zzlv()
            int r3 = r0.zzlt()
            int r0 = r0.zzls()
            int r3 = r3 + r0
            r0 = 1
            r4 = 2147483647(0x7fffffff, float:NaN)
            r5 = 0
            r7 = 0
            r8 = 16
            if (r1 >= r8) goto L_0x0029
            long r9 = zzcey
            int r11 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
            if (r11 == 0) goto L_0x0029
            int r9 = zzb(r9, r1)
            goto L_0x0038
        L_0x0029:
            float r9 = zzcex
            int r10 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r10 == 0) goto L_0x0035
            float r10 = (float) r1
            float r9 = r9 * r10
            int r9 = (int) r9
            int r9 = r9 + r0
            goto L_0x0038
        L_0x0035:
            r9 = 2147483647(0x7fffffff, float:NaN)
        L_0x0038:
            if (r2 > r9) goto L_0x0054
            if (r1 >= r8) goto L_0x0047
            long r8 = zzcew
            int r2 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1))
            if (r2 == 0) goto L_0x0047
            int r4 = zzb(r8, r1)
            goto L_0x0051
        L_0x0047:
            float r2 = zzcev
            int r5 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r5 == 0) goto L_0x0051
            float r1 = (float) r1
            float r2 = r2 * r1
            int r4 = (int) r2
        L_0x0051:
            if (r3 > r4) goto L_0x0054
            return r0
        L_0x0054:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzuv.zzlx():boolean");
    }
}
