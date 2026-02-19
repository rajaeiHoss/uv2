package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbg extends zzflm<zzbg> {
    public byte[] zzgk = null;
    public byte[][] zzgp = zzflv.zzpwd;
    private Integer zzgq;
    public Integer zzgr;

    public zzbg() {
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzd */
    public final zzbg zza(zzflj zzflj) throws IOException {
        int i;
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 10) {
                int zzb = zzflv.zzb(zzflj, 10);
                byte[][] bArr = this.zzgp;
                int length = bArr == null ? 0 : bArr.length;
                int i2 = zzb + length;
                byte[][] bArr2 = new byte[i2][];
                if (length != 0) {
                    System.arraycopy(bArr, 0, bArr2, 0, length);
                }
                while (length < i2 - 1) {
                    bArr2[length] = zzflj.readBytes();
                    zzflj.zzcxx();
                    length++;
                }
                bArr2[length] = zzflj.readBytes();
                this.zzgp = bArr2;
            } else if (zzcxx == 18) {
                this.zzgk = zzflj.readBytes();
            } else if (zzcxx == 24) {
                i = zzflj.getPosition();
                int zzcym = zzflj.zzcym();
                if (!(zzcym == 0 || zzcym == 1)) {
                    if (zzcym != 2) {
                        StringBuilder sb = new StringBuilder(41);
                        sb.append(zzcym);
                        sb.append(" is not a valid enum ProtoName");
                        throw new IllegalArgumentException(sb.toString());
                    }
                }
                this.zzgq = Integer.valueOf(zzcym);
            } else if (zzcxx == 32) {
                i = zzflj.getPosition();
                try {
                    int zzcym2 = zzflj.zzcym();
                    if (!(zzcym2 == 0 || zzcym2 == 1 || zzcym2 == 2)) {
                        if (zzcym2 != 3) {
                            StringBuilder sb2 = new StringBuilder(48);
                            sb2.append(zzcym2);
                            sb2.append(" is not a valid enum EncryptionMethod");
                            throw new IllegalArgumentException(sb2.toString());
                        }
                    }
                    this.zzgr = Integer.valueOf(zzcym2);
                } catch (IllegalArgumentException unused) {
                    zzflj.zzmw(i);
                    zza(zzflj, zzcxx);
                }
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        byte[][] bArr = this.zzgp;
        if (bArr != null && bArr.length > 0) {
            int i = 0;
            while (true) {
                byte[][] bArr2 = this.zzgp;
                if (i >= bArr2.length) {
                    break;
                }
                byte[] bArr3 = bArr2[i];
                if (bArr3 != null) {
                    zzflk.zzc(1, bArr3);
                }
                i++;
            }
        }
        byte[] bArr4 = this.zzgk;
        if (bArr4 != null) {
            zzflk.zzc(2, bArr4);
        }
        Integer num = this.zzgq;
        if (num != null) {
            zzflk.zzad(3, num.intValue());
        }
        Integer num2 = this.zzgr;
        if (num2 != null) {
            zzflk.zzad(4, num2.intValue());
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        byte[][] bArr = this.zzgp;
        if (bArr != null && bArr.length > 0) {
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (true) {
                byte[][] bArr2 = this.zzgp;
                if (i >= bArr2.length) {
                    break;
                }
                byte[] bArr3 = bArr2[i];
                if (bArr3 != null) {
                    i3++;
                    i2 += zzflk.zzbg(bArr3);
                }
                i++;
            }
            zzq = zzq + i2 + (i3 * 1);
        }
        byte[] bArr4 = this.zzgk;
        if (bArr4 != null) {
            zzq += zzflk.zzd(2, bArr4);
        }
        Integer num = this.zzgq;
        if (num != null) {
            zzq += zzflk.zzag(3, num.intValue());
        }
        Integer num2 = this.zzgr;
        return num2 != null ? zzq + zzflk.zzag(4, num2.intValue()) : zzq;
    }
}
