package com.google.android.gms.internal;

import java.io.IOException;

public final class zzffp extends zzflm<zzffp> {
    private String zzpma = "";
    private String zzpmb = "";
    private String zzpmc = "";

    public zzffp() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzffp)) {
            return false;
        }
        zzffp zzffp = (zzffp) obj;
        String str = this.zzpma;
        if (str == null) {
            if (zzffp.zzpma != null) {
                return false;
            }
        } else if (!str.equals(zzffp.zzpma)) {
            return false;
        }
        String str2 = this.zzpmb;
        if (str2 == null) {
            if (zzffp.zzpmb != null) {
                return false;
            }
        } else if (!str2.equals(zzffp.zzpmb)) {
            return false;
        }
        String str3 = this.zzpmc;
        if (str3 == null) {
            if (zzffp.zzpmc != null) {
                return false;
            }
        } else if (!str3.equals(zzffp.zzpmc)) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzffp.zzpvl == null || zzffp.zzpvl.isEmpty() : this.zzpvl.equals(zzffp.zzpvl);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        String str = this.zzpma;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.zzpmb;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.zzpmc;
        int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        if (this.zzpvl != null && !this.zzpvl.isEmpty()) {
            i = this.zzpvl.hashCode();
        }
        return hashCode4 + i;
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 10) {
                this.zzpma = zzflj.readString();
            } else if (zzcxx == 18) {
                this.zzpmb = zzflj.readString();
            } else if (zzcxx == 26) {
                this.zzpmc = zzflj.readString();
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        String str = this.zzpma;
        if (str != null && !str.equals("")) {
            zzflk.zzp(1, this.zzpma);
        }
        String str2 = this.zzpmb;
        if (str2 != null && !str2.equals("")) {
            zzflk.zzp(2, this.zzpmb);
        }
        String str3 = this.zzpmc;
        if (str3 != null && !str3.equals("")) {
            zzflk.zzp(3, this.zzpmc);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        String str = this.zzpma;
        if (str != null && !str.equals("")) {
            zzq += zzflk.zzq(1, this.zzpma);
        }
        String str2 = this.zzpmb;
        if (str2 != null && !str2.equals("")) {
            zzq += zzflk.zzq(2, this.zzpmb);
        }
        String str3 = this.zzpmc;
        return (str3 == null || str3.equals("")) ? zzq : zzq + zzflk.zzq(3, this.zzpmc);
    }
}
