package com.google.android.gms.internal;

public final class zzekc extends zzejy {
    private final zzenn zznke;

    public zzekc(zzeka zzeka, zzegu zzegu, zzenn zzenn) {
        super(zzejz.Overwrite, zzeka, zzegu);
        this.zznke = zzenn;
    }

    public final String toString() {
        return String.format("Overwrite { path=%s, source=%s, snapshot=%s }", new Object[]{this.zzmxa, this.zznjq, this.zznke});
    }

    public final zzenn zzbzx() {
        return this.zznke;
    }

    public final zzejy zzc(zzemq zzemq) {
        return this.zzmxa.isEmpty() ? new zzekc(this.zznjq, zzegu.zzbyn(), this.zznke.zzm(zzemq)) : new zzekc(this.zznjq, this.zzmxa.zzbyr(), this.zznke);
    }
}
