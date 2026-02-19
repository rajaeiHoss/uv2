package com.google.android.gms.internal;

import java.io.IOException;

public final class zzflz extends zzflm<zzflz> {
    private byte[] body = null;
    private zzfma zzpwv = null;
    public zzfly[] zzpww = zzfly.zzdcs();
    private byte[] zzpwx = null;
    private Integer zzpwy = null;

    public zzflz() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 10) {
                if (this.zzpwv == null) {
                    this.zzpwv = new zzfma();
                }
                zzflj.zza(this.zzpwv);
            } else if (zzcxx == 18) {
                int zzb = zzflv.zzb(zzflj, 18);
                zzfly[] zzflyArr = this.zzpww;
                int length = zzflyArr == null ? 0 : zzflyArr.length;
                int i = zzb + length;
                zzfly[] zzflyArr2 = new zzfly[i];
                if (length != 0) {
                    System.arraycopy(zzflyArr, 0, zzflyArr2, 0, length);
                }
                while (length < i - 1) {
                    zzflyArr2[length] = new zzfly();
                    zzflj.zza(zzflyArr2[length]);
                    zzflj.zzcxx();
                    length++;
                }
                zzflyArr2[length] = new zzfly();
                zzflj.zza(zzflyArr2[length]);
                this.zzpww = zzflyArr2;
            } else if (zzcxx == 26) {
                this.body = zzflj.readBytes();
            } else if (zzcxx == 34) {
                this.zzpwx = zzflj.readBytes();
            } else if (zzcxx == 40) {
                this.zzpwy = Integer.valueOf(zzflj.zzcya());
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        zzfma zzfma = this.zzpwv;
        if (zzfma != null) {
            zzflk.zza(1, (zzfls) zzfma);
        }
        zzfly[] zzflyArr = this.zzpww;
        if (zzflyArr != null && zzflyArr.length > 0) {
            int i = 0;
            while (true) {
                zzfly[] zzflyArr2 = this.zzpww;
                if (i >= zzflyArr2.length) {
                    break;
                }
                zzfly zzfly = zzflyArr2[i];
                if (zzfly != null) {
                    zzflk.zza(2, (zzfls) zzfly);
                }
                i++;
            }
        }
        byte[] bArr = this.body;
        if (bArr != null) {
            zzflk.zzc(3, bArr);
        }
        byte[] bArr2 = this.zzpwx;
        if (bArr2 != null) {
            zzflk.zzc(4, bArr2);
        }
        Integer num = this.zzpwy;
        if (num != null) {
            zzflk.zzad(5, num.intValue());
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        zzfma zzfma = this.zzpwv;
        if (zzfma != null) {
            zzq += zzflk.zzb(1, (zzfls) zzfma);
        }
        zzfly[] zzflyArr = this.zzpww;
        if (zzflyArr != null && zzflyArr.length > 0) {
            int i = 0;
            while (true) {
                zzfly[] zzflyArr2 = this.zzpww;
                if (i >= zzflyArr2.length) {
                    break;
                }
                zzfly zzfly = zzflyArr2[i];
                if (zzfly != null) {
                    zzq += zzflk.zzb(2, (zzfls) zzfly);
                }
                i++;
            }
        }
        byte[] bArr = this.body;
        if (bArr != null) {
            zzq += zzflk.zzd(3, bArr);
        }
        byte[] bArr2 = this.zzpwx;
        if (bArr2 != null) {
            zzq += zzflk.zzd(4, bArr2);
        }
        Integer num = this.zzpwy;
        return num != null ? zzq + zzflk.zzag(5, num.intValue()) : zzq;
    }
}
