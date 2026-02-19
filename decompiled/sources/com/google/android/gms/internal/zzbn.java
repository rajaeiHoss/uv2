package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbn extends zzflm<zzbn> {
    public zzbt[] zzwq = zzbt.zzx();
    public zzbt[] zzwr = zzbt.zzx();
    public zzbm[] zzws = zzbm.zzt();

    public zzbn() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbn)) {
            return false;
        }
        zzbn zzbn = (zzbn) obj;
        if (zzflq.equals((Object[]) this.zzwq, (Object[]) zzbn.zzwq) && zzflq.equals((Object[]) this.zzwr, (Object[]) zzbn.zzwr) && zzflq.equals((Object[]) this.zzws, (Object[]) zzbn.zzws)) {
            return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzbn.zzpvl == null || zzbn.zzpvl.isEmpty() : this.zzpvl.equals(zzbn.zzpvl);
        }
        return false;
    }

    public final int hashCode() {
        return ((((((((getClass().getName().hashCode() + 527) * 31) + zzflq.hashCode((Object[]) this.zzwq)) * 31) + zzflq.hashCode((Object[]) this.zzwr)) * 31) + zzflq.hashCode((Object[]) this.zzws)) * 31) + ((this.zzpvl == null || this.zzpvl.isEmpty()) ? 0 : this.zzpvl.hashCode());
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 10) {
                int zzb = zzflv.zzb(zzflj, 10);
                zzbt[] zzbtArr = this.zzwq;
                int length = zzbtArr == null ? 0 : zzbtArr.length;
                int i = zzb + length;
                zzbt[] zzbtArr2 = new zzbt[i];
                if (length != 0) {
                    System.arraycopy(zzbtArr, 0, zzbtArr2, 0, length);
                }
                while (length < i - 1) {
                    zzbtArr2[length] = new zzbt();
                    zzflj.zza(zzbtArr2[length]);
                    zzflj.zzcxx();
                    length++;
                }
                zzbtArr2[length] = new zzbt();
                zzflj.zza(zzbtArr2[length]);
                this.zzwq = zzbtArr2;
            } else if (zzcxx == 18) {
                int zzb2 = zzflv.zzb(zzflj, 18);
                zzbt[] zzbtArr3 = this.zzwr;
                int length2 = zzbtArr3 == null ? 0 : zzbtArr3.length;
                int i2 = zzb2 + length2;
                zzbt[] zzbtArr4 = new zzbt[i2];
                if (length2 != 0) {
                    System.arraycopy(zzbtArr3, 0, zzbtArr4, 0, length2);
                }
                while (length2 < i2 - 1) {
                    zzbtArr4[length2] = new zzbt();
                    zzflj.zza(zzbtArr4[length2]);
                    zzflj.zzcxx();
                    length2++;
                }
                zzbtArr4[length2] = new zzbt();
                zzflj.zza(zzbtArr4[length2]);
                this.zzwr = zzbtArr4;
            } else if (zzcxx == 26) {
                int zzb3 = zzflv.zzb(zzflj, 26);
                zzbm[] zzbmArr = this.zzws;
                int length3 = zzbmArr == null ? 0 : zzbmArr.length;
                int i3 = zzb3 + length3;
                zzbm[] zzbmArr2 = new zzbm[i3];
                if (length3 != 0) {
                    System.arraycopy(zzbmArr, 0, zzbmArr2, 0, length3);
                }
                while (length3 < i3 - 1) {
                    zzbmArr2[length3] = new zzbm();
                    zzflj.zza(zzbmArr2[length3]);
                    zzflj.zzcxx();
                    length3++;
                }
                zzbmArr2[length3] = new zzbm();
                zzflj.zza(zzbmArr2[length3]);
                this.zzws = zzbmArr2;
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        zzbt[] zzbtArr = this.zzwq;
        int i = 0;
        if (zzbtArr != null && zzbtArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzbt[] zzbtArr2 = this.zzwq;
                if (i2 >= zzbtArr2.length) {
                    break;
                }
                zzbt zzbt = zzbtArr2[i2];
                if (zzbt != null) {
                    zzflk.zza(1, (zzfls) zzbt);
                }
                i2++;
            }
        }
        zzbt[] zzbtArr3 = this.zzwr;
        if (zzbtArr3 != null && zzbtArr3.length > 0) {
            int i3 = 0;
            while (true) {
                zzbt[] zzbtArr4 = this.zzwr;
                if (i3 >= zzbtArr4.length) {
                    break;
                }
                zzbt zzbt2 = zzbtArr4[i3];
                if (zzbt2 != null) {
                    zzflk.zza(2, (zzfls) zzbt2);
                }
                i3++;
            }
        }
        zzbm[] zzbmArr = this.zzws;
        if (zzbmArr != null && zzbmArr.length > 0) {
            while (true) {
                zzbm[] zzbmArr2 = this.zzws;
                if (i >= zzbmArr2.length) {
                    break;
                }
                zzbm zzbm = zzbmArr2[i];
                if (zzbm != null) {
                    zzflk.zza(3, (zzfls) zzbm);
                }
                i++;
            }
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        zzbt[] zzbtArr = this.zzwq;
        int i = 0;
        if (zzbtArr != null && zzbtArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzbt[] zzbtArr2 = this.zzwq;
                if (i2 >= zzbtArr2.length) {
                    break;
                }
                zzbt zzbt = zzbtArr2[i2];
                if (zzbt != null) {
                    zzq += zzflk.zzb(1, (zzfls) zzbt);
                }
                i2++;
            }
        }
        zzbt[] zzbtArr3 = this.zzwr;
        if (zzbtArr3 != null && zzbtArr3.length > 0) {
            int i3 = 0;
            while (true) {
                zzbt[] zzbtArr4 = this.zzwr;
                if (i3 >= zzbtArr4.length) {
                    break;
                }
                zzbt zzbt2 = zzbtArr4[i3];
                if (zzbt2 != null) {
                    zzq += zzflk.zzb(2, (zzfls) zzbt2);
                }
                i3++;
            }
        }
        zzbm[] zzbmArr = this.zzws;
        if (zzbmArr != null && zzbmArr.length > 0) {
            while (true) {
                zzbm[] zzbmArr2 = this.zzws;
                if (i >= zzbmArr2.length) {
                    break;
                }
                zzbm zzbm = zzbmArr2[i];
                if (zzbm != null) {
                    zzq += zzflk.zzb(3, (zzfls) zzbm);
                }
                i++;
            }
        }
        return zzq;
    }
}
