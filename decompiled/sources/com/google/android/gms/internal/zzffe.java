package com.google.android.gms.internal;

import com.google.android.gms.internal.zzffd;
import java.io.IOException;

public final class zzffe extends zzflm<zzffe> {
    public int zzpkl = 0;
    public long zzpkm = 0;
    public int[] zzpkn = zzflv.zzpvy;

    public zzffe() {
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzal */
    public final zzffe zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                try {
                    int zzcym = zzflj.zzcym();
                    if (!(zzcym == 0 || zzcym == 1 || zzcym == 2)) {
                        if (zzcym != 3) {
                            StringBuilder sb = new StringBuilder(43);
                            sb.append(zzcym);
                            sb.append(" is not a valid enum TriggerType");
                            throw new IllegalArgumentException(sb.toString());
                        }
                    }
                    this.zzpkl = zzcym;
                } catch (IllegalArgumentException unused) {
                    zzflj.zzmw(zzflj.getPosition());
                    zza(zzflj, zzcxx);
                }
            } else if (zzcxx == 16) {
                this.zzpkm = zzflj.zzcyr();
            } else if (zzcxx == 24) {
                int zzb = zzflv.zzb(zzflj, 24);
                int[] iArr = new int[zzb];
                int i = 0;
                for (int i2 = 0; i2 < zzb; i2++) {
                    if (i2 != 0) {
                        zzflj.zzcxx();
                    }
                    int position = zzflj.getPosition();
                    try {
                        iArr[i] = zzffd.zza.zzky(zzflj.zzcym());
                        i++;
                    } catch (IllegalArgumentException unused2) {
                        zzflj.zzmw(position);
                        zza(zzflj, zzcxx);
                    }
                }
                if (i != 0) {
                    int[] iArr2 = this.zzpkn;
                    int length = iArr2 == null ? 0 : iArr2.length;
                    if (length == 0 && i == zzb) {
                        this.zzpkn = iArr;
                    } else {
                        int[] iArr3 = new int[(length + i)];
                        if (length != 0) {
                            System.arraycopy(iArr2, 0, iArr3, 0, length);
                        }
                        System.arraycopy(iArr, 0, iArr3, length, i);
                        this.zzpkn = iArr3;
                    }
                }
            } else if (zzcxx == 26) {
                int zzli = zzflj.zzli(zzflj.zzcym());
                int position2 = zzflj.getPosition();
                int i3 = 0;
                while (zzflj.zzcyo() > 0) {
                    try {
                        zzffd.zza.zzky(zzflj.zzcym());
                        i3++;
                    } catch (IllegalArgumentException unused3) {
                    }
                }
                if (i3 != 0) {
                    zzflj.zzmw(position2);
                    int[] iArr4 = this.zzpkn;
                    int length2 = iArr4 == null ? 0 : iArr4.length;
                    int[] iArr5 = new int[(i3 + length2)];
                    if (length2 != 0) {
                        System.arraycopy(iArr4, 0, iArr5, 0, length2);
                    }
                    while (zzflj.zzcyo() > 0) {
                        int position3 = zzflj.getPosition();
                        try {
                            iArr5[length2] = zzffd.zza.zzky(zzflj.zzcym());
                            length2++;
                        } catch (IllegalArgumentException unused4) {
                            zzflj.zzmw(position3);
                            zza(zzflj, 24);
                        }
                    }
                    this.zzpkn = iArr5;
                }
                zzflj.zzlj(zzli);
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzffe)) {
            return false;
        }
        zzffe zzffe = (zzffe) obj;
        if (this.zzpkl == zzffe.zzpkl && this.zzpkm == zzffe.zzpkm && zzflq.equals(this.zzpkn, zzffe.zzpkn)) {
            return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzffe.zzpvl == null || zzffe.zzpvl.isEmpty() : this.zzpvl.equals(zzffe.zzpvl);
        }
        return false;
    }

    public final int hashCode() {
        long j = this.zzpkm;
        return ((((((((getClass().getName().hashCode() + 527) * 31) + this.zzpkl) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + zzflq.hashCode(this.zzpkn)) * 31) + ((this.zzpvl == null || this.zzpvl.isEmpty()) ? 0 : this.zzpvl.hashCode());
    }

    public final void zza(zzflk zzflk) throws IOException {
        int i = this.zzpkl;
        if (i != 0) {
            zzflk.zzad(1, i);
        }
        long j = this.zzpkm;
        if (j != 0) {
            zzflk.zzf(2, j);
        }
        int[] iArr = this.zzpkn;
        if (iArr != null && iArr.length > 0) {
            int i2 = 0;
            while (true) {
                int[] iArr2 = this.zzpkn;
                if (i2 >= iArr2.length) {
                    break;
                }
                zzflk.zzad(3, iArr2[i2]);
                i2++;
            }
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        int i = this.zzpkl;
        if (i != 0) {
            zzq += zzflk.zzag(1, i);
        }
        long j = this.zzpkm;
        if (j != 0) {
            zzq += zzflk.zzc(2, j);
        }
        int[] iArr = this.zzpkn;
        if (iArr == null || iArr.length <= 0) {
            return zzq;
        }
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int[] iArr2 = this.zzpkn;
            if (i2 >= iArr2.length) {
                return zzq + i3 + (iArr2.length * 1);
            }
            i3 += zzflk.zzlx(iArr2[i2]);
            i2++;
        }
    }
}
