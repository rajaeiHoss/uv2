package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfax extends zzflm<zzfax> {
    public zzfat zzotf = null;
    public zzfat zzotg = null;
    public zzfat zzoth = null;
    public zzfav zzoti = null;
    public zzfay[] zzotj = zzfay.zzcns();

    public zzfax() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfax)) {
            return false;
        }
        zzfax zzfax = (zzfax) obj;
        zzfat zzfat = this.zzotf;
        if (zzfat == null) {
            if (zzfax.zzotf != null) {
                return false;
            }
        } else if (!zzfat.equals(zzfax.zzotf)) {
            return false;
        }
        zzfat zzfat2 = this.zzotg;
        if (zzfat2 == null) {
            if (zzfax.zzotg != null) {
                return false;
            }
        } else if (!zzfat2.equals(zzfax.zzotg)) {
            return false;
        }
        zzfat zzfat3 = this.zzoth;
        if (zzfat3 == null) {
            if (zzfax.zzoth != null) {
                return false;
            }
        } else if (!zzfat3.equals(zzfax.zzoth)) {
            return false;
        }
        zzfav zzfav = this.zzoti;
        if (zzfav == null) {
            if (zzfax.zzoti != null) {
                return false;
            }
        } else if (!zzfav.equals(zzfax.zzoti)) {
            return false;
        }
        if (!zzflq.equals((Object[]) this.zzotj, (Object[]) zzfax.zzotj)) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzfax.zzpvl == null || zzfax.zzpvl.isEmpty() : this.zzpvl.equals(zzfax.zzpvl);
    }

    public final int hashCode() {
        zzfat zzfat = this.zzotf;
        int i = 0;
        int hashCode = ((getClass().getName().hashCode() + 527) * 31) + (zzfat == null ? 0 : zzfat.hashCode());
        zzfat zzfat2 = this.zzotg;
        int hashCode2 = (hashCode * 31) + (zzfat2 == null ? 0 : zzfat2.hashCode());
        zzfat zzfat3 = this.zzoth;
        int hashCode3 = (hashCode2 * 31) + (zzfat3 == null ? 0 : zzfat3.hashCode());
        zzfav zzfav = this.zzoti;
        int hashCode4 = ((((hashCode3 * 31) + (zzfav == null ? 0 : zzfav.hashCode())) * 31) + zzflq.hashCode((Object[]) this.zzotj)) * 31;
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
            zzfls zzfls = null;
            if (zzcxx == 10) {
                if (this.zzotf == null) {
                    this.zzotf = new zzfat();
                }
                zzfls = this.zzotf;
            } else if (zzcxx == 18) {
                if (this.zzotg == null) {
                    this.zzotg = new zzfat();
                }
                zzfls = this.zzotg;
            } else if (zzcxx == 26) {
                if (this.zzoth == null) {
                    this.zzoth = new zzfat();
                }
                zzfls = this.zzoth;
            } else if (zzcxx == 34) {
                if (this.zzoti == null) {
                    this.zzoti = new zzfav();
                }
                zzfls = this.zzoti;
            } else if (zzcxx == 42) {
                int zzb = zzflv.zzb(zzflj, 42);
                zzfay[] zzfayArr = this.zzotj;
                int length = zzfayArr == null ? 0 : zzfayArr.length;
                int i = zzb + length;
                zzfay[] zzfayArr2 = new zzfay[i];
                if (length != 0) {
                    System.arraycopy(zzfayArr, 0, zzfayArr2, 0, length);
                }
                while (length < i - 1) {
                    zzfayArr2[length] = new zzfay();
                    zzflj.zza(zzfayArr2[length]);
                    zzflj.zzcxx();
                    length++;
                }
                zzfayArr2[length] = new zzfay();
                zzflj.zza(zzfayArr2[length]);
                this.zzotj = zzfayArr2;
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
            if (zzfls != null) {
                zzflj.zza(zzfls);
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        zzfat zzfat = this.zzotf;
        if (zzfat != null) {
            zzflk.zza(1, (zzfls) zzfat);
        }
        zzfat zzfat2 = this.zzotg;
        if (zzfat2 != null) {
            zzflk.zza(2, (zzfls) zzfat2);
        }
        zzfat zzfat3 = this.zzoth;
        if (zzfat3 != null) {
            zzflk.zza(3, (zzfls) zzfat3);
        }
        zzfav zzfav = this.zzoti;
        if (zzfav != null) {
            zzflk.zza(4, (zzfls) zzfav);
        }
        zzfay[] zzfayArr = this.zzotj;
        if (zzfayArr != null && zzfayArr.length > 0) {
            int i = 0;
            while (true) {
                zzfay[] zzfayArr2 = this.zzotj;
                if (i >= zzfayArr2.length) {
                    break;
                }
                zzfay zzfay = zzfayArr2[i];
                if (zzfay != null) {
                    zzflk.zza(5, (zzfls) zzfay);
                }
                i++;
            }
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        zzfat zzfat = this.zzotf;
        if (zzfat != null) {
            zzq += zzflk.zzb(1, (zzfls) zzfat);
        }
        zzfat zzfat2 = this.zzotg;
        if (zzfat2 != null) {
            zzq += zzflk.zzb(2, (zzfls) zzfat2);
        }
        zzfat zzfat3 = this.zzoth;
        if (zzfat3 != null) {
            zzq += zzflk.zzb(3, (zzfls) zzfat3);
        }
        zzfav zzfav = this.zzoti;
        if (zzfav != null) {
            zzq += zzflk.zzb(4, (zzfls) zzfav);
        }
        zzfay[] zzfayArr = this.zzotj;
        if (zzfayArr != null && zzfayArr.length > 0) {
            int i = 0;
            while (true) {
                zzfay[] zzfayArr2 = this.zzotj;
                if (i >= zzfayArr2.length) {
                    break;
                }
                zzfay zzfay = zzfayArr2[i];
                if (zzfay != null) {
                    zzq += zzflk.zzb(5, (zzfls) zzfay);
                }
                i++;
            }
        }
        return zzq;
    }
}
