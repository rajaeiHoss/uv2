package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfmd extends zzflm<zzfmd> {
    public String mimeType = null;
    public Integer zzbdh = null;
    public byte[] zzpxg = null;

    public zzfmd() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzbl */
    public final zzfmd zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                try {
                    int zzcya = zzflj.zzcya();
                    if (zzcya != 0) {
                        if (zzcya != 1) {
                            StringBuilder sb = new StringBuilder(36);
                            sb.append(zzcya);
                            sb.append(" is not a valid enum Type");
                            throw new IllegalArgumentException(sb.toString());
                        }
                    }
                    this.zzbdh = Integer.valueOf(zzcya);
                } catch (IllegalArgumentException unused) {
                    zzflj.zzmw(zzflj.getPosition());
                    zza(zzflj, zzcxx);
                }
            } else if (zzcxx == 18) {
                this.mimeType = zzflj.readString();
            } else if (zzcxx == 26) {
                this.zzpxg = zzflj.readBytes();
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        Integer num = this.zzbdh;
        if (num != null) {
            zzflk.zzad(1, num.intValue());
        }
        String str = this.mimeType;
        if (str != null) {
            zzflk.zzp(2, str);
        }
        byte[] bArr = this.zzpxg;
        if (bArr != null) {
            zzflk.zzc(3, bArr);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        Integer num = this.zzbdh;
        if (num != null) {
            zzq += zzflk.zzag(1, num.intValue());
        }
        String str = this.mimeType;
        if (str != null) {
            zzq += zzflk.zzq(2, str);
        }
        byte[] bArr = this.zzpxg;
        return bArr != null ? zzq + zzflk.zzd(3, bArr) : zzq;
    }
}
