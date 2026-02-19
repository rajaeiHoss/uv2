package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbd extends zzflm<zzbd> {
    private Long zzgf = null;
    private Integer zzgg = null;
    private Boolean zzgh = null;
    private int[] zzgi = zzflv.zzpvy;
    private Long zzgj = null;

    public zzbd() {
        this.zzpnr = -1;
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                this.zzgf = Long.valueOf(zzflj.zzcyr());
            } else if (zzcxx == 16) {
                this.zzgg = Integer.valueOf(zzflj.zzcym());
            } else if (zzcxx == 24) {
                this.zzgh = Boolean.valueOf(zzflj.zzcyd());
            } else if (zzcxx == 32) {
                int zzb = zzflv.zzb(zzflj, 32);
                int[] iArr = this.zzgi;
                int length = iArr == null ? 0 : iArr.length;
                int i = zzb + length;
                int[] iArr2 = new int[i];
                if (length != 0) {
                    System.arraycopy(iArr, 0, iArr2, 0, length);
                }
                while (length < i - 1) {
                    iArr2[length] = zzflj.zzcym();
                    zzflj.zzcxx();
                    length++;
                }
                iArr2[length] = zzflj.zzcym();
                this.zzgi = iArr2;
            } else if (zzcxx == 34) {
                int zzli = zzflj.zzli(zzflj.zzcym());
                int position = zzflj.getPosition();
                int i2 = 0;
                while (zzflj.zzcyo() > 0) {
                    zzflj.zzcym();
                    i2++;
                }
                zzflj.zzmw(position);
                int[] iArr3 = this.zzgi;
                int length2 = iArr3 == null ? 0 : iArr3.length;
                int i3 = i2 + length2;
                int[] iArr4 = new int[i3];
                if (length2 != 0) {
                    System.arraycopy(iArr3, 0, iArr4, 0, length2);
                }
                while (length2 < i3) {
                    iArr4[length2] = zzflj.zzcym();
                    length2++;
                }
                this.zzgi = iArr4;
                zzflj.zzlj(zzli);
            } else if (zzcxx == 40) {
                this.zzgj = Long.valueOf(zzflj.zzcyr());
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        Long l = this.zzgf;
        if (l != null) {
            zzflk.zzf(1, l.longValue());
        }
        Integer num = this.zzgg;
        if (num != null) {
            zzflk.zzad(2, num.intValue());
        }
        Boolean bool = this.zzgh;
        if (bool != null) {
            zzflk.zzl(3, bool.booleanValue());
        }
        int[] iArr = this.zzgi;
        if (iArr != null && iArr.length > 0) {
            int i = 0;
            while (true) {
                int[] iArr2 = this.zzgi;
                if (i >= iArr2.length) {
                    break;
                }
                zzflk.zzad(4, iArr2[i]);
                i++;
            }
        }
        Long l2 = this.zzgj;
        if (l2 != null) {
            zzflk.zza(5, l2.longValue());
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int[] iArr;
        int zzq = super.zzq();
        Long l = this.zzgf;
        if (l != null) {
            zzq += zzflk.zzc(1, l.longValue());
        }
        Integer num = this.zzgg;
        if (num != null) {
            zzq += zzflk.zzag(2, num.intValue());
        }
        Boolean bool = this.zzgh;
        if (bool != null) {
            bool.booleanValue();
            zzq += zzflk.zzlw(3) + 1;
        }
        int[] iArr2 = this.zzgi;
        if (iArr2 != null && iArr2.length > 0) {
            int i = 0;
            int i2 = 0;
            while (true) {
                iArr = this.zzgi;
                if (i >= iArr.length) {
                    break;
                }
                i2 += zzflk.zzlx(iArr[i]);
                i++;
            }
            zzq = zzq + i2 + (iArr.length * 1);
        }
        Long l2 = this.zzgj;
        if (l2 == null) {
            return zzq;
        }
        return zzq + zzflk.zzlw(5) + zzflk.zzdj(l2.longValue());
    }
}
