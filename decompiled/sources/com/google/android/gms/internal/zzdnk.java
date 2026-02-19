package com.google.android.gms.internal;

import java.io.IOException;

public final class zzdnk extends zzflm<zzdnk> {
    private static volatile zzdnk[] zzlwp;
    public int type = 1;
    public zzdnl zzlwq = null;

    public zzdnk() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzae */
    public final zzdnk zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                try {
                    int zzcym = zzflj.zzcym();
                    switch (zzcym) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                        case 10:
                        case 11:
                        case 12:
                        case 13:
                        case 14:
                        case 15:
                            this.type = zzcym;
                            break;
                        default:
                            StringBuilder sb = new StringBuilder(36);
                            sb.append(zzcym);
                            sb.append(" is not a valid enum Type");
                            throw new IllegalArgumentException(sb.toString());
                    }
                } catch (IllegalArgumentException unused) {
                    zzflj.zzmw(zzflj.getPosition());
                    zza(zzflj, zzcxx);
                }
            } else if (zzcxx == 18) {
                if (this.zzlwq == null) {
                    this.zzlwq = new zzdnl();
                }
                zzflj.zza(this.zzlwq);
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public static zzdnk[] zzbmc() {
        if (zzlwp == null) {
            synchronized (zzflq.zzpvt) {
                if (zzlwp == null) {
                    zzlwp = new zzdnk[0];
                }
            }
        }
        return zzlwp;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzdnk)) {
            return false;
        }
        zzdnk zzdnk = (zzdnk) obj;
        if (this.type != zzdnk.type) {
            return false;
        }
        zzdnl zzdnl = this.zzlwq;
        if (zzdnl == null) {
            if (zzdnk.zzlwq != null) {
                return false;
            }
        } else if (!zzdnl.equals(zzdnk.zzlwq)) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzdnk.zzpvl == null || zzdnk.zzpvl.isEmpty() : this.zzpvl.equals(zzdnk.zzpvl);
    }

    public final int hashCode() {
        int hashCode = ((getClass().getName().hashCode() + 527) * 31) + this.type;
        zzdnl zzdnl = this.zzlwq;
        int i = 0;
        int hashCode2 = ((hashCode * 31) + (zzdnl == null ? 0 : zzdnl.hashCode())) * 31;
        if (this.zzpvl != null && !this.zzpvl.isEmpty()) {
            i = this.zzpvl.hashCode();
        }
        return hashCode2 + i;
    }

    public final void zza(zzflk zzflk) throws IOException {
        zzflk.zzad(1, this.type);
        zzdnl zzdnl = this.zzlwq;
        if (zzdnl != null) {
            zzflk.zza(2, (zzfls) zzdnl);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq() + zzflk.zzag(1, this.type);
        zzdnl zzdnl = this.zzlwq;
        return zzdnl != null ? zzq + zzflk.zzb(2, (zzfls) zzdnl) : zzq;
    }
}
