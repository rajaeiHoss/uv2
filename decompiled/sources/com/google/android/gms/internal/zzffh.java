package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

public final class zzffh extends zzflm<zzffh> {
    private static volatile zzffh[] zzpkp;
    public byte[] content = zzflv.zzpwe;
    public String type = "";
    public String zzkal = "";

    public zzffh() {
        this.zzpnr = -1;
    }

    public static zzffh[] zzcxh() {
        if (zzpkp == null) {
            synchronized (zzflq.zzpvt) {
                if (zzpkp == null) {
                    zzpkp = new zzffh[0];
                }
            }
        }
        return zzpkp;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzffh)) {
            return false;
        }
        zzffh zzffh = (zzffh) obj;
        String str = this.zzkal;
        if (str == null) {
            if (zzffh.zzkal != null) {
                return false;
            }
        } else if (!str.equals(zzffh.zzkal)) {
            return false;
        }
        String str2 = this.type;
        if (str2 == null) {
            if (zzffh.type != null) {
                return false;
            }
        } else if (!str2.equals(zzffh.type)) {
            return false;
        }
        if (!Arrays.equals(this.content, zzffh.content)) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzffh.zzpvl == null || zzffh.zzpvl.isEmpty() : this.zzpvl.equals(zzffh.zzpvl);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        String str = this.zzkal;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.type;
        int hashCode3 = (((hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31) + Arrays.hashCode(this.content)) * 31;
        if (this.zzpvl != null && !this.zzpvl.isEmpty()) {
            i = this.zzpvl.hashCode();
        }
        return hashCode3 + i;
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 10) {
                this.zzkal = zzflj.readString();
            } else if (zzcxx == 18) {
                this.type = zzflj.readString();
            } else if (zzcxx == 26) {
                this.content = zzflj.readBytes();
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        String str = this.zzkal;
        if (str != null && !str.equals("")) {
            zzflk.zzp(1, this.zzkal);
        }
        String str2 = this.type;
        if (str2 != null && !str2.equals("")) {
            zzflk.zzp(2, this.type);
        }
        if (!Arrays.equals(this.content, zzflv.zzpwe)) {
            zzflk.zzc(3, this.content);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        String str = this.zzkal;
        if (str != null && !str.equals("")) {
            zzq += zzflk.zzq(1, this.zzkal);
        }
        String str2 = this.type;
        if (str2 != null && !str2.equals("")) {
            zzq += zzflk.zzq(2, this.type);
        }
        return !Arrays.equals(this.content, zzflv.zzpwe) ? zzq + zzflk.zzd(3, this.content) : zzq;
    }
}
