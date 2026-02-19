package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbo extends zzflm<zzbo> {
    private static volatile zzbo[] zzwt;
    public int key = 0;
    public int value = 0;

    public zzbo() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public static zzbo[] zzu() {
        if (zzwt == null) {
            synchronized (zzflq.zzpvt) {
                if (zzwt == null) {
                    zzwt = new zzbo[0];
                }
            }
        }
        return zzwt;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbo)) {
            return false;
        }
        zzbo zzbo = (zzbo) obj;
        if (this.key == zzbo.key && this.value == zzbo.value) {
            return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzbo.zzpvl == null || zzbo.zzpvl.isEmpty() : this.zzpvl.equals(zzbo.zzpvl);
        }
        return false;
    }

    public final int hashCode() {
        return ((((((getClass().getName().hashCode() + 527) * 31) + this.key) * 31) + this.value) * 31) + ((this.zzpvl == null || this.zzpvl.isEmpty()) ? 0 : this.zzpvl.hashCode());
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                this.key = zzflj.zzcym();
            } else if (zzcxx == 16) {
                this.value = zzflj.zzcym();
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        zzflk.zzad(1, this.key);
        zzflk.zzad(2, this.value);
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        return super.zzq() + zzflk.zzag(1, this.key) + zzflk.zzag(2, this.value);
    }
}
