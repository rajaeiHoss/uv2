package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfay extends zzflm<zzfay> {
    private static volatile zzfay[] zzotk;
    public int resourceId = 0;
    public String zzkal = "";
    public long zzotl = 0;

    public zzfay() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public static zzfay[] zzcns() {
        if (zzotk == null) {
            synchronized (zzflq.zzpvt) {
                if (zzotk == null) {
                    zzotk = new zzfay[0];
                }
            }
        }
        return zzotk;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfay)) {
            return false;
        }
        zzfay zzfay = (zzfay) obj;
        if (this.resourceId != zzfay.resourceId || this.zzotl != zzfay.zzotl) {
            return false;
        }
        String str = this.zzkal;
        if (str == null) {
            if (zzfay.zzkal != null) {
                return false;
            }
        } else if (!str.equals(zzfay.zzkal)) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzfay.zzpvl == null || zzfay.zzpvl.isEmpty() : this.zzpvl.equals(zzfay.zzpvl);
    }

    public final int hashCode() {
        long j = this.zzotl;
        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + this.resourceId) * 31) + ((int) (j ^ (j >>> 32)))) * 31;
        String str = this.zzkal;
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
            if (zzcxx == 8) {
                this.resourceId = zzflj.zzcym();
            } else if (zzcxx == 17) {
                this.zzotl = zzflj.zzcyt();
            } else if (zzcxx == 26) {
                this.zzkal = zzflj.readString();
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        int i = this.resourceId;
        if (i != 0) {
            zzflk.zzad(1, i);
        }
        long j = this.zzotl;
        if (j != 0) {
            zzflk.zzb(2, j);
        }
        String str = this.zzkal;
        if (str != null && !str.equals("")) {
            zzflk.zzp(3, this.zzkal);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        int i = this.resourceId;
        if (i != 0) {
            zzq += zzflk.zzag(1, i);
        }
        if (this.zzotl != 0) {
            zzq += zzflk.zzlw(2) + 8;
        }
        String str = this.zzkal;
        return (str == null || str.equals("")) ? zzq : zzq + zzflk.zzq(3, this.zzkal);
    }
}
