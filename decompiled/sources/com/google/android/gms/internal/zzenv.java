package com.google.android.gms.internal;

public final class zzenv extends zzeni<zzenv> {
    private final String value;

    public zzenv(String str, zzenn zzenn) {
        super(zzenn);
        this.value = str;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzenv)) {
            return false;
        }
        zzenv zzenv = (zzenv) obj;
        return this.value.equals(zzenv.value) && this.zznob.equals(zzenv.zznob);
    }

    public final Object getValue() {
        return this.value;
    }

    public final int hashCode() {
        return this.value.hashCode() + this.zznob.hashCode();
    }

    /* access modifiers changed from: protected */
    public final int zza(zzenv zzenv) {
        return this.value.compareTo(zzenv.value);
    }

    public final String zza(zzenp zzenp) {
        String zzb;
        String str;
        StringBuilder sb;
        int i = zzenw.zznox[zzenp.ordinal()];
        if (i == 1) {
            zzb = zzb(zzenp);
            str = this.value;
            sb = new StringBuilder(String.valueOf(zzb).length() + 7 + String.valueOf(str).length());
        } else if (i == 2) {
            zzb = zzb(zzenp);
            str = zzepd.zzql(this.value);
            sb = new StringBuilder(String.valueOf(zzb).length() + 7 + String.valueOf(str).length());
        } else {
            String valueOf = String.valueOf(zzenp);
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 38);
            sb2.append("Invalid hash version for string node: ");
            sb2.append(valueOf);
            throw new IllegalArgumentException(sb2.toString());
        }
        sb.append(zzb);
        sb.append("string:");
        sb.append(str);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public final zzenk zzcbv() {
        return zzenk.String;
    }

    public final /* synthetic */ zzenn zzf(zzenn zzenn) {
        return new zzenv(this.value, zzenn);
    }
}
