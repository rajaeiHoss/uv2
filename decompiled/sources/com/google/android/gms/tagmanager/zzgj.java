package com.google.android.gms.tagmanager;

final class zzgj extends Number implements Comparable<zzgj> {
    private double zzkuh;
    private long zzkui;
    private boolean zzkuj = false;

    private zzgj(double d) {
        this.zzkuh = d;
    }

    private zzgj(long j) {
        this.zzkui = j;
    }

    public static zzgj zzb(Double d) {
        return new zzgj(d.doubleValue());
    }

    public static zzgj zzbi(long j) {
        return new zzgj(j);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0013, code lost:
        return new com.google.android.gms.tagmanager.zzgj(java.lang.Double.parseDouble(r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0023, code lost:
        throw new java.lang.NumberFormatException(java.lang.String.valueOf(r3).concat(" is not a valid TypedNumber"));
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x000a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.tagmanager.zzgj zzmh(java.lang.String r3) throws java.lang.NumberFormatException {
        /*
            com.google.android.gms.tagmanager.zzgj r0 = new com.google.android.gms.tagmanager.zzgj     // Catch:{ NumberFormatException -> 0x000a }
            long r1 = java.lang.Long.parseLong(r3)     // Catch:{ NumberFormatException -> 0x000a }
            r0.<init>((long) r1)     // Catch:{ NumberFormatException -> 0x000a }
            return r0
        L_0x000a:
            com.google.android.gms.tagmanager.zzgj r0 = new com.google.android.gms.tagmanager.zzgj     // Catch:{ NumberFormatException -> 0x0014 }
            double r1 = java.lang.Double.parseDouble(r3)     // Catch:{ NumberFormatException -> 0x0014 }
            r0.<init>((double) r1)     // Catch:{ NumberFormatException -> 0x0014 }
            return r0
        L_0x0014:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.String r3 = java.lang.String.valueOf(r3)
            java.lang.String r1 = " is not a valid TypedNumber"
            java.lang.String r3 = r3.concat(r1)
            r0.<init>(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzgj.zzmh(java.lang.String):com.google.android.gms.tagmanager.zzgj");
    }

    public final byte byteValue() {
        return (byte) ((int) longValue());
    }

    public final double doubleValue() {
        return this.zzkuj ? (double) this.zzkui : this.zzkuh;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof zzgj) && compareTo((zzgj) obj) == 0;
    }

    public final float floatValue() {
        return (float) doubleValue();
    }

    public final int hashCode() {
        return new Long(longValue()).hashCode();
    }

    public final int intValue() {
        return (int) longValue();
    }

    public final long longValue() {
        return this.zzkuj ? this.zzkui : (long) this.zzkuh;
    }

    public final short shortValue() {
        return (short) ((int) longValue());
    }

    public final String toString() {
        return this.zzkuj ? Long.toString(this.zzkui) : Double.toString(this.zzkuh);
    }

    /* renamed from: zza */
    public final int compareTo(zzgj zzgj) {
        return (!this.zzkuj || !zzgj.zzkuj) ? Double.compare(doubleValue(), zzgj.doubleValue()) : new Long(this.zzkui).compareTo(Long.valueOf(zzgj.zzkui));
    }

    public final boolean zzbid() {
        return !this.zzkuj;
    }

    public final boolean zzbie() {
        return this.zzkuj;
    }
}
