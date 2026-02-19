package com.google.android.gms.internal;

import java.io.IOException;

public final class zzffy extends zzflm<zzffy> {
    private String zzgog = "";

    public zzffy() {
        this.zzpnr = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzffy)) {
            return false;
        }
        zzffy zzffy = (zzffy) obj;
        String str = this.zzgog;
        if (str == null) {
            if (zzffy.zzgog != null) {
                return false;
            }
        } else if (!str.equals(zzffy.zzgog)) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzffy.zzpvl == null || zzffy.zzpvl.isEmpty() : this.zzpvl.equals(zzffy.zzpvl);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        String str = this.zzgog;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        if (this.zzpvl != null && !this.zzpvl.isEmpty()) {
            i = this.zzpvl.hashCode();
        }
        return hashCode2 + i;
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 10) {
                this.zzgog = zzflj.readString();
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        String str = this.zzgog;
        if (str != null && !str.equals("")) {
            zzflk.zzp(1, this.zzgog);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        String str = this.zzgog;
        return (str == null || str.equals("")) ? zzq : zzq + zzflk.zzq(1, this.zzgog);
    }
}
