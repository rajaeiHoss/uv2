package com.google.android.gms.internal;

public final class zzemp extends zzeni<zzemp> {
    private final boolean value;

    public zzemp(Boolean bool, zzenn zzenn) {
        super(zzenn);
        this.value = bool.booleanValue();
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzemp)) {
            return false;
        }
        zzemp zzemp = (zzemp) obj;
        return this.value == zzemp.value && this.zznob.equals(zzemp.zznob);
    }

    public final Object getValue() {
        return Boolean.valueOf(this.value);
    }

    public final int hashCode() {
        boolean z = this.value;
        return (z ? 1 : 0) + this.zznob.hashCode();
    }

    /* access modifiers changed from: protected */
    public final int zza(zzemp zzemp) {
        boolean z = this.value;
        if (z == zzemp.value) {
            return 0;
        }
        return z ? 1 : -1;
    }

    public final String zza(zzenp zzenp) {
        String zzb = zzb(zzenp);
        boolean z = this.value;
        StringBuilder sb = new StringBuilder(String.valueOf(zzb).length() + 13);
        sb.append(zzb);
        sb.append("boolean:");
        sb.append(z);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public final zzenk zzcbv() {
        return zzenk.Boolean;
    }

    public final /* synthetic */ zzenn zzf(zzenn zzenn) {
        return new zzemp(Boolean.valueOf(this.value), zzenn);
    }
}
