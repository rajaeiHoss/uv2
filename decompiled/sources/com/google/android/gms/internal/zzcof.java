package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcof extends zzflm<zzcof> {
    public long[] zzjvo = zzflv.zzpvz;
    public long[] zzjvp = zzflv.zzpvz;

    public zzcof() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcof)) {
            return false;
        }
        zzcof zzcof = (zzcof) obj;
        if (zzflq.equals(this.zzjvo, zzcof.zzjvo) && zzflq.equals(this.zzjvp, zzcof.zzjvp)) {
            return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzcof.zzpvl == null || zzcof.zzpvl.isEmpty() : this.zzpvl.equals(zzcof.zzpvl);
        }
        return false;
    }

    public final int hashCode() {
        return ((((((getClass().getName().hashCode() + 527) * 31) + zzflq.hashCode(this.zzjvo)) * 31) + zzflq.hashCode(this.zzjvp)) * 31) + ((this.zzpvl == null || this.zzpvl.isEmpty()) ? 0 : this.zzpvl.hashCode());
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        int i;
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx != 8) {
                if (zzcxx == 10) {
                    i = zzflj.zzli(zzflj.zzcym());
                    int position = zzflj.getPosition();
                    int i2 = 0;
                    while (zzflj.zzcyo() > 0) {
                        zzflj.zzcyr();
                        i2++;
                    }
                    zzflj.zzmw(position);
                    long[] jArr = this.zzjvo;
                    int length = jArr == null ? 0 : jArr.length;
                    int i3 = i2 + length;
                    long[] jArr2 = new long[i3];
                    if (length != 0) {
                        System.arraycopy(jArr, 0, jArr2, 0, length);
                    }
                    while (length < i3) {
                        jArr2[length] = zzflj.zzcyr();
                        length++;
                    }
                    this.zzjvo = jArr2;
                    zzflj.zzlj(i);
                } else if (zzcxx == 16) {
                    int zzb = zzflv.zzb(zzflj, 16);
                    long[] jArr3 = this.zzjvp;
                    int length2 = jArr3 == null ? 0 : jArr3.length;
                    int i4 = zzb + length2;
                    long[] jArr4 = new long[i4];
                    if (length2 != 0) {
                        System.arraycopy(jArr3, 0, jArr4, 0, length2);
                    }
                    while (length2 < i4 - 1) {
                        jArr4[length2] = zzflj.zzcyr();
                        zzflj.zzcxx();
                        length2++;
                    }
                    jArr4[length2] = zzflj.zzcyr();
                    this.zzjvp = jArr4;
                } else if (zzcxx == 18) {
                    i = zzflj.zzli(zzflj.zzcym());
                    int position2 = zzflj.getPosition();
                    int i5 = 0;
                    while (zzflj.zzcyo() > 0) {
                        zzflj.zzcyr();
                        i5++;
                    }
                    zzflj.zzmw(position2);
                    long[] jArr5 = this.zzjvp;
                    int length3 = jArr5 == null ? 0 : jArr5.length;
                    int i6 = i5 + length3;
                    long[] jArr6 = new long[i6];
                    if (length3 != 0) {
                        System.arraycopy(jArr5, 0, jArr6, 0, length3);
                    }
                    while (length3 < i6) {
                        jArr6[length3] = zzflj.zzcyr();
                        length3++;
                    }
                    this.zzjvp = jArr6;
                    zzflj.zzlj(i);
                } else if (!super.zza(zzflj, zzcxx)) {
                    return this;
                }
            } else {
                int zzb2 = zzflv.zzb(zzflj, 8);
                long[] jArr7 = this.zzjvo;
                int length4 = jArr7 == null ? 0 : jArr7.length;
                int i7 = zzb2 + length4;
                long[] jArr8 = new long[i7];
                if (length4 != 0) {
                    System.arraycopy(jArr7, 0, jArr8, 0, length4);
                }
                while (length4 < i7 - 1) {
                    jArr8[length4] = zzflj.zzcyr();
                    zzflj.zzcxx();
                    length4++;
                }
                jArr8[length4] = zzflj.zzcyr();
                this.zzjvo = jArr8;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        long[] jArr = this.zzjvo;
        int i = 0;
        if (jArr != null && jArr.length > 0) {
            int i2 = 0;
            while (true) {
                long[] jArr2 = this.zzjvo;
                if (i2 >= jArr2.length) {
                    break;
                }
                zzflk.zza(1, jArr2[i2]);
                i2++;
            }
        }
        long[] jArr3 = this.zzjvp;
        if (jArr3 != null && jArr3.length > 0) {
            while (true) {
                long[] jArr4 = this.zzjvp;
                if (i >= jArr4.length) {
                    break;
                }
                zzflk.zza(2, jArr4[i]);
                i++;
            }
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        long[] jArr;
        int zzq = super.zzq();
        long[] jArr2 = this.zzjvo;
        int i = 0;
        if (jArr2 != null && jArr2.length > 0) {
            int i2 = 0;
            int i3 = 0;
            while (true) {
                jArr = this.zzjvo;
                if (i2 >= jArr.length) {
                    break;
                }
                i3 += zzflk.zzdj(jArr[i2]);
                i2++;
            }
            zzq = zzq + i3 + (jArr.length * 1);
        }
        long[] jArr3 = this.zzjvp;
        if (jArr3 == null || jArr3.length <= 0) {
            return zzq;
        }
        int i4 = 0;
        while (true) {
            long[] jArr4 = this.zzjvp;
            if (i >= jArr4.length) {
                return zzq + i4 + (jArr4.length * 1);
            }
            i4 += zzflk.zzdj(jArr4[i]);
            i++;
        }
    }
}
