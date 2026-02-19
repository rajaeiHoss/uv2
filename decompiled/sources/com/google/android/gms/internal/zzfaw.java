package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfaw extends zzflm<zzfaw> {
    private static volatile zzfaw[] zzotd;
    public String zzkal = "";
    public zzfau[] zzote = zzfau.zzcnq();

    public zzfaw() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public static zzfaw[] zzcnr() {
        if (zzotd == null) {
            synchronized (zzflq.zzpvt) {
                if (zzotd == null) {
                    zzotd = new zzfaw[0];
                }
            }
        }
        return zzotd;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfaw)) {
            return false;
        }
        zzfaw zzfaw = (zzfaw) obj;
        String str = this.zzkal;
        if (str == null) {
            if (zzfaw.zzkal != null) {
                return false;
            }
        } else if (!str.equals(zzfaw.zzkal)) {
            return false;
        }
        if (!zzflq.equals((Object[]) this.zzote, (Object[]) zzfaw.zzote)) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzfaw.zzpvl == null || zzfaw.zzpvl.isEmpty() : this.zzpvl.equals(zzfaw.zzpvl);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        String str = this.zzkal;
        int i = 0;
        int hashCode2 = (((hashCode + (str == null ? 0 : str.hashCode())) * 31) + zzflq.hashCode((Object[]) this.zzote)) * 31;
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
                this.zzkal = zzflj.readString();
            } else if (zzcxx == 18) {
                int zzb = zzflv.zzb(zzflj, 18);
                zzfau[] zzfauArr = this.zzote;
                int length = zzfauArr == null ? 0 : zzfauArr.length;
                int i = zzb + length;
                zzfau[] zzfauArr2 = new zzfau[i];
                if (length != 0) {
                    System.arraycopy(zzfauArr, 0, zzfauArr2, 0, length);
                }
                while (length < i - 1) {
                    zzfauArr2[length] = new zzfau();
                    zzflj.zza(zzfauArr2[length]);
                    zzflj.zzcxx();
                    length++;
                }
                zzfauArr2[length] = new zzfau();
                zzflj.zza(zzfauArr2[length]);
                this.zzote = zzfauArr2;
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
        zzfau[] zzfauArr = this.zzote;
        if (zzfauArr != null && zzfauArr.length > 0) {
            int i = 0;
            while (true) {
                zzfau[] zzfauArr2 = this.zzote;
                if (i >= zzfauArr2.length) {
                    break;
                }
                zzfau zzfau = zzfauArr2[i];
                if (zzfau != null) {
                    zzflk.zza(2, (zzfls) zzfau);
                }
                i++;
            }
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
        zzfau[] zzfauArr = this.zzote;
        if (zzfauArr != null && zzfauArr.length > 0) {
            int i = 0;
            while (true) {
                zzfau[] zzfauArr2 = this.zzote;
                if (i >= zzfauArr2.length) {
                    break;
                }
                zzfau zzfau = zzfauArr2[i];
                if (zzfau != null) {
                    zzq += zzflk.zzb(2, (zzfls) zzfau);
                }
                i++;
            }
        }
        return zzq;
    }
}
