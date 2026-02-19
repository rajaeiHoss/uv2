package com.google.android.gms.internal;

import java.io.IOException;

public final class zzffd extends zzflm<zzffd> {
    private static zzfln<zzffk, zzffd> zzpjy = zzfln.zza(11, zzffd.class, 622520458);
    private static final zzffd[] zzpki = new zzffd[0];
    private zza[] zzpkj = zza.zzcxg();

    public static final class zza extends zzflm<zza> {
        private static volatile zza[] zzpkk;
        private int type = 0;
        private int zzirg = 0;

        public zza() {
            this.zzpvl = null;
            this.zzpnr = -1;
        }

        /* access modifiers changed from: private */
        /* renamed from: zzak */
        public final zza zza(zzflj zzflj) throws IOException {
            while (true) {
                int zzcxx = zzflj.zzcxx();
                if (zzcxx == 0) {
                    return this;
                }
                if (zzcxx == 8) {
                    int position = zzflj.getPosition();
                    try {
                        this.type = zzky(zzflj.zzcym());
                    } catch (IllegalArgumentException unused) {
                        zzflj.zzmw(position);
                        zza(zzflj, zzcxx);
                    }
                } else if (zzcxx == 16) {
                    this.zzirg = zzflj.zzcym();
                } else if (!super.zza(zzflj, zzcxx)) {
                    return this;
                }
            }
        }

        public static zza[] zzcxg() {
            if (zzpkk == null) {
                synchronized (zzflq.zzpvt) {
                    if (zzpkk == null) {
                        zzpkk = new zza[0];
                    }
                }
            }
            return zzpkk;
        }

        public static int zzky(int i) {
            if (i != -1000) {
                switch (i) {
                    case 0:
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
                    case 16:
                    case 17:
                        break;
                    default:
                        StringBuilder sb = new StringBuilder(36);
                        sb.append(i);
                        sb.append(" is not a valid enum Type");
                        throw new IllegalArgumentException(sb.toString());
                }
            }
            return i;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            if (this.type == zza.type && this.zzirg == zza.zzirg) {
                return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zza.zzpvl == null || zza.zzpvl.isEmpty() : this.zzpvl.equals(zza.zzpvl);
            }
            return false;
        }

        public final int hashCode() {
            return ((((((getClass().getName().hashCode() + 527) * 31) + this.type) * 31) + this.zzirg) * 31) + ((this.zzpvl == null || this.zzpvl.isEmpty()) ? 0 : this.zzpvl.hashCode());
        }

        public final void zza(zzflk zzflk) throws IOException {
            int i = this.type;
            if (i != 0) {
                zzflk.zzad(1, i);
            }
            int i2 = this.zzirg;
            if (i2 != 0) {
                zzflk.zzad(2, i2);
            }
            super.zza(zzflk);
        }

        /* access modifiers changed from: protected */
        public final int zzq() {
            int zzq = super.zzq();
            int i = this.type;
            if (i != 0) {
                zzq += zzflk.zzag(1, i);
            }
            int i2 = this.zzirg;
            return i2 != 0 ? zzq + zzflk.zzag(2, i2) : zzq;
        }
    }

    public zzffd() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzffd)) {
            return false;
        }
        zzffd zzffd = (zzffd) obj;
        if (!zzflq.equals((Object[]) this.zzpkj, (Object[]) zzffd.zzpkj)) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzffd.zzpvl == null || zzffd.zzpvl.isEmpty() : this.zzpvl.equals(zzffd.zzpvl);
    }

    public final int hashCode() {
        return ((((getClass().getName().hashCode() + 527) * 31) + zzflq.hashCode((Object[]) this.zzpkj)) * 31) + ((this.zzpvl == null || this.zzpvl.isEmpty()) ? 0 : this.zzpvl.hashCode());
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 18) {
                int zzb = zzflv.zzb(zzflj, 18);
                zza[] zzaArr = this.zzpkj;
                int length = zzaArr == null ? 0 : zzaArr.length;
                int i = zzb + length;
                zza[] zzaArr2 = new zza[i];
                if (length != 0) {
                    System.arraycopy(zzaArr, 0, zzaArr2, 0, length);
                }
                while (length < i - 1) {
                    zzaArr2[length] = new zza();
                    zzflj.zza(zzaArr2[length]);
                    zzflj.zzcxx();
                    length++;
                }
                zzaArr2[length] = new zza();
                zzflj.zza(zzaArr2[length]);
                this.zzpkj = zzaArr2;
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        zza[] zzaArr = this.zzpkj;
        if (zzaArr != null && zzaArr.length > 0) {
            int i = 0;
            while (true) {
                zza[] zzaArr2 = this.zzpkj;
                if (i >= zzaArr2.length) {
                    break;
                }
                zza zza2 = zzaArr2[i];
                if (zza2 != null) {
                    zzflk.zza(2, (zzfls) zza2);
                }
                i++;
            }
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        zza[] zzaArr = this.zzpkj;
        if (zzaArr != null && zzaArr.length > 0) {
            int i = 0;
            while (true) {
                zza[] zzaArr2 = this.zzpkj;
                if (i >= zzaArr2.length) {
                    break;
                }
                zza zza2 = zzaArr2[i];
                if (zza2 != null) {
                    zzq += zzflk.zzb(2, (zzfls) zza2);
                }
                i++;
            }
        }
        return zzq;
    }
}
