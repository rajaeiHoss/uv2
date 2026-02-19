package com.google.android.gms.internal;

import java.io.IOException;

public final class zzffc extends zzflm<zzffc> {
    private static zzfln<zzffk, zzffc> zzpjy = zzfln.zza(11, zzffc.class, 971494290);
    private static final zzffc[] zzpkg = new zzffc[0];
    private int[] zzpkh = zzflv.zzpvy;

    public zzffc() {
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzaj */
    public final zzffc zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                int zzb = zzflv.zzb(zzflj, 8);
                int[] iArr = new int[zzb];
                int i = 0;
                for (int i2 = 0; i2 < zzb; i2++) {
                    if (i2 != 0) {
                        zzflj.zzcxx();
                    }
                    int position = zzflj.getPosition();
                    try {
                        iArr[i] = zzkx(zzflj.zzcym());
                        i++;
                    } catch (IllegalArgumentException unused) {
                        zzflj.zzmw(position);
                        zza(zzflj, zzcxx);
                    }
                }
                if (i != 0) {
                    int[] iArr2 = this.zzpkh;
                    int length = iArr2 == null ? 0 : iArr2.length;
                    if (length == 0 && i == zzb) {
                        this.zzpkh = iArr;
                    } else {
                        int[] iArr3 = new int[(length + i)];
                        if (length != 0) {
                            System.arraycopy(iArr2, 0, iArr3, 0, length);
                        }
                        System.arraycopy(iArr, 0, iArr3, length, i);
                        this.zzpkh = iArr3;
                    }
                }
            } else if (zzcxx == 10) {
                int zzli = zzflj.zzli(zzflj.zzcym());
                int position2 = zzflj.getPosition();
                int i3 = 0;
                while (zzflj.zzcyo() > 0) {
                    try {
                        zzkx(zzflj.zzcym());
                        i3++;
                    } catch (IllegalArgumentException unused2) {
                    }
                }
                if (i3 != 0) {
                    zzflj.zzmw(position2);
                    int[] iArr4 = this.zzpkh;
                    int length2 = iArr4 == null ? 0 : iArr4.length;
                    int[] iArr5 = new int[(i3 + length2)];
                    if (length2 != 0) {
                        System.arraycopy(iArr4, 0, iArr5, 0, length2);
                    }
                    while (zzflj.zzcyo() > 0) {
                        int position3 = zzflj.getPosition();
                        try {
                            iArr5[length2] = zzkx(zzflj.zzcym());
                            length2++;
                        } catch (IllegalArgumentException unused3) {
                            zzflj.zzmw(position3);
                            zza(zzflj, 8);
                        }
                    }
                    this.zzpkh = iArr5;
                }
                zzflj.zzlj(zzli);
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public static int zzkx(int i) {
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                return i;
            default:
                StringBuilder sb = new StringBuilder(48);
                sb.append(i);
                sb.append(" is not a valid enum DayAttributeType");
                throw new IllegalArgumentException(sb.toString());
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzffc)) {
            return false;
        }
        zzffc zzffc = (zzffc) obj;
        if (!zzflq.equals(this.zzpkh, zzffc.zzpkh)) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzffc.zzpvl == null || zzffc.zzpvl.isEmpty() : this.zzpvl.equals(zzffc.zzpvl);
    }

    public final int hashCode() {
        return ((((getClass().getName().hashCode() + 527) * 31) + zzflq.hashCode(this.zzpkh)) * 31) + ((this.zzpvl == null || this.zzpvl.isEmpty()) ? 0 : this.zzpvl.hashCode());
    }

    public final void zza(zzflk zzflk) throws IOException {
        int[] iArr = this.zzpkh;
        if (iArr != null && iArr.length > 0) {
            int i = 0;
            while (true) {
                int[] iArr2 = this.zzpkh;
                if (i >= iArr2.length) {
                    break;
                }
                zzflk.zzad(1, iArr2[i]);
                i++;
            }
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        int[] iArr = this.zzpkh;
        if (iArr == null || iArr.length <= 0) {
            return zzq;
        }
        int i = 0;
        int i2 = 0;
        while (true) {
            int[] iArr2 = this.zzpkh;
            if (i >= iArr2.length) {
                return zzq + i2 + (iArr2.length * 1);
            }
            i2 += zzflk.zzlx(iArr2[i]);
            i++;
        }
    }
}
