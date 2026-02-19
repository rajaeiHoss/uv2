package com.google.android.gms.internal;

public final class zzenl extends zzeni<zzenl> {
    private final long value;

    public zzenl(Long l, zzenn zzenn) {
        super(zzenn);
        this.value = l.longValue();
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzenl)) {
            return false;
        }
        zzenl zzenl = (zzenl) obj;
        return this.value == zzenl.value && this.zznob.equals(zzenl.zznob);
    }

    public final Object getValue() {
        return Long.valueOf(this.value);
    }

    public final int hashCode() {
        long j = this.value;
        return ((int) (j ^ (j >>> 32))) + this.zznob.hashCode();
    }

    /* access modifiers changed from: protected */
    public final int zza(zzenl zzenl) {
        return zzepd.zzi(this.value, zzenl.value);
    }

    public final String zza(zzenp zzenp) {
        String valueOf = String.valueOf(String.valueOf(zzb(zzenp)).concat("number:"));
        String valueOf2 = String.valueOf(zzepd.zzk((double) this.value));
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }

    /* access modifiers changed from: protected */
    public final zzenk zzcbv() {
        return zzenk.Number;
    }

    public final /* synthetic */ zzenn zzf(zzenn zzenn) {
        return new zzenl(Long.valueOf(this.value), zzenn);
    }
}
