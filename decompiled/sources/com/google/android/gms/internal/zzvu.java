package com.google.android.gms.internal;

final class zzvu implements Runnable {
    private /* synthetic */ zzvs zzcja;
    private /* synthetic */ zzvt zzcjb;

    zzvu(zzvt zzvt, zzvs zzvs) {
        this.zzcjb = zzvt;
        this.zzcja = zzvs;
    }

    public final void run() {
        synchronized (this.zzcjb.mLock) {
            if (this.zzcjb.zzciy == -2) {
                zzvt zzvt = this.zzcjb;
                zzwi unused = zzvt.zzcix = zzvt.zzmk();
                if (this.zzcjb.zzcix == null) {
                    this.zzcjb.zzv(4);
                } else if (!this.zzcjb.zzml() || this.zzcjb.zzw(1)) {
                    this.zzcja.zza((zzvx) this.zzcjb);
                    this.zzcjb.zza(this.zzcja);
                } else {
                    String zzf = this.zzcjb.zzcip;
                    StringBuilder sb = new StringBuilder(String.valueOf(zzf).length() + 56);
                    sb.append("Ignoring adapter ");
                    sb.append(zzf);
                    sb.append(" as delayed impression is not supported");
                    zzahw.zzcz(sb.toString());
                    this.zzcjb.zzv(2);
                }
            }
        }
    }
}
