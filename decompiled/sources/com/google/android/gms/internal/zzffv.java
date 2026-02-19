package com.google.android.gms.internal;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.IOException;

public final class zzffv extends zzflm<zzffv> {
    private int zzpkl = 0;
    private long zzpkm = 0;
    private double zzpmq = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    private double zzpmr = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    private int[] zzpms = zzflv.zzpvy;

    public zzffv() {
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzaw */
    public final zzffv zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                try {
                    int zzcym = zzflj.zzcym();
                    switch (zzcym) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                            this.zzpkl = zzcym;
                            break;
                        default:
                            StringBuilder sb = new StringBuilder(43);
                            sb.append(zzcym);
                            sb.append(" is not a valid enum TriggerType");
                            throw new IllegalArgumentException(sb.toString());
                    }
                } catch (IllegalArgumentException unused) {
                    zzflj.zzmw(zzflj.getPosition());
                    zza(zzflj, zzcxx);
                }
            } else if (zzcxx == 17) {
                this.zzpmq = Double.longBitsToDouble(zzflj.zzcyt());
            } else if (zzcxx == 25) {
                this.zzpmr = Double.longBitsToDouble(zzflj.zzcyt());
            } else if (zzcxx == 32) {
                this.zzpkm = zzflj.zzcyr();
            } else if (zzcxx == 40) {
                int zzb = zzflv.zzb(zzflj, 40);
                int[] iArr = new int[zzb];
                int i = 0;
                for (int i2 = 0; i2 < zzb; i2++) {
                    if (i2 != 0) {
                        zzflj.zzcxx();
                    }
                    int position = zzflj.getPosition();
                    try {
                        iArr[i] = zzfgh.zzlc(zzflj.zzcym());
                        i++;
                    } catch (IllegalArgumentException unused2) {
                        zzflj.zzmw(position);
                        zza(zzflj, zzcxx);
                    }
                }
                if (i != 0) {
                    int[] iArr2 = this.zzpms;
                    int length = iArr2 == null ? 0 : iArr2.length;
                    if (length == 0 && i == zzb) {
                        this.zzpms = iArr;
                    } else {
                        int[] iArr3 = new int[(length + i)];
                        if (length != 0) {
                            System.arraycopy(iArr2, 0, iArr3, 0, length);
                        }
                        System.arraycopy(iArr, 0, iArr3, length, i);
                        this.zzpms = iArr3;
                    }
                }
            } else if (zzcxx == 42) {
                int zzli = zzflj.zzli(zzflj.zzcym());
                int position2 = zzflj.getPosition();
                int i3 = 0;
                while (zzflj.zzcyo() > 0) {
                    try {
                        zzfgh.zzlc(zzflj.zzcym());
                        i3++;
                    } catch (IllegalArgumentException unused3) {
                    }
                }
                if (i3 != 0) {
                    zzflj.zzmw(position2);
                    int[] iArr4 = this.zzpms;
                    int length2 = iArr4 == null ? 0 : iArr4.length;
                    int[] iArr5 = new int[(i3 + length2)];
                    if (length2 != 0) {
                        System.arraycopy(iArr4, 0, iArr5, 0, length2);
                    }
                    while (zzflj.zzcyo() > 0) {
                        int position3 = zzflj.getPosition();
                        try {
                            iArr5[length2] = zzfgh.zzlc(zzflj.zzcym());
                            length2++;
                        } catch (IllegalArgumentException unused4) {
                            zzflj.zzmw(position3);
                            zza(zzflj, 40);
                        }
                    }
                    this.zzpms = iArr5;
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
        if (!(obj instanceof zzffv)) {
            return false;
        }
        zzffv zzffv = (zzffv) obj;
        if (this.zzpkl == zzffv.zzpkl && Double.doubleToLongBits(this.zzpmq) == Double.doubleToLongBits(zzffv.zzpmq) && Double.doubleToLongBits(this.zzpmr) == Double.doubleToLongBits(zzffv.zzpmr) && this.zzpkm == zzffv.zzpkm && zzflq.equals(this.zzpms, zzffv.zzpms)) {
            return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzffv.zzpvl == null || zzffv.zzpvl.isEmpty() : this.zzpvl.equals(zzffv.zzpvl);
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = ((getClass().getName().hashCode() + 527) * 31) + this.zzpkl;
        long doubleToLongBits = Double.doubleToLongBits(this.zzpmq);
        long doubleToLongBits2 = Double.doubleToLongBits(this.zzpmr);
        long j = this.zzpkm;
        return (((((((((hashCode * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + zzflq.hashCode(this.zzpms)) * 31) + ((this.zzpvl == null || this.zzpvl.isEmpty()) ? 0 : this.zzpvl.hashCode());
    }

    public final void zza(zzflk zzflk) throws IOException {
        int i = this.zzpkl;
        if (i != 0) {
            zzflk.zzad(1, i);
        }
        if (Double.doubleToLongBits(this.zzpmq) != Double.doubleToLongBits(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)) {
            zzflk.zza(2, this.zzpmq);
        }
        if (Double.doubleToLongBits(this.zzpmr) != Double.doubleToLongBits(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)) {
            zzflk.zza(3, this.zzpmr);
        }
        long j = this.zzpkm;
        if (j != 0) {
            zzflk.zzf(4, j);
        }
        int[] iArr = this.zzpms;
        if (iArr != null && iArr.length > 0) {
            int i2 = 0;
            while (true) {
                int[] iArr2 = this.zzpms;
                if (i2 >= iArr2.length) {
                    break;
                }
                zzflk.zzad(5, iArr2[i2]);
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
        if (Double.doubleToLongBits(this.zzpmq) != Double.doubleToLongBits(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)) {
            zzq += zzflk.zzlw(2) + 8;
        }
        if (Double.doubleToLongBits(this.zzpmr) != Double.doubleToLongBits(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)) {
            zzq += zzflk.zzlw(3) + 8;
        }
        long j = this.zzpkm;
        if (j != 0) {
            zzq += zzflk.zzc(4, j);
        }
        int[] iArr = this.zzpms;
        if (iArr == null || iArr.length <= 0) {
            return zzq;
        }
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int[] iArr2 = this.zzpms;
            if (i2 >= iArr2.length) {
                return zzq + i3 + (iArr2.length * 1);
            }
            i3 += zzflk.zzlx(iArr2[i2]);
            i2++;
        }
    }
}
