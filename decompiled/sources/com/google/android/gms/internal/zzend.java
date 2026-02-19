package com.google.android.gms.internal;

public final class zzend extends zzeni<zzend> {
    private final Double zznor;

    public zzend(Double d, zzenn zzenn) {
        super(zzenn);
        this.zznor = d;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzend)) {
            return false;
        }
        zzend zzend = (zzend) obj;
        return this.zznor.equals(zzend.zznor) && this.zznob.equals(zzend.zznob);
    }

    public final Object getValue() {
        return this.zznor;
    }

    public final int hashCode() {
        return this.zznor.hashCode() + this.zznob.hashCode();
    }

    /* access modifiers changed from: protected */
    public final int zza(zzend zzend) {
        return this.zznor.compareTo(zzend.zznor);
    }

    public final String zza(zzenp zzenp) {
        String valueOf = String.valueOf(String.valueOf(zzb(zzenp)).concat("number:"));
        String valueOf2 = String.valueOf(zzepd.zzk(this.zznor.doubleValue()));
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }

    /* access modifiers changed from: protected */
    public final zzenk zzcbv() {
        return zzenk.Number;
    }

    public final /* synthetic */ zzenn zzf(zzenn zzenn) {
        return new zzend(this.zznor, zzenn);
    }
}
