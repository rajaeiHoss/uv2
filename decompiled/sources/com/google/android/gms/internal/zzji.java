package com.google.android.gms.internal;

import java.io.IOException;

public final class zzji extends zzflm<zzji> {
    public String zzbdw = null;
    private zzju zzbdx = null;
    private Integer zzbdy = null;
    public zzjv zzbdz = null;
    private Integer zzbea = null;
    private Integer zzbeb = null;
    private Integer zzbec = null;
    private Integer zzbed = null;

    public zzji() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzk */
    public final zzji zza(zzflj zzflj) throws IOException {
        zzfls zzfls;
        int i;
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx != 10) {
                if (zzcxx == 18) {
                    if (this.zzbdx == null) {
                        this.zzbdx = new zzju();
                    }
                    zzfls = this.zzbdx;
                } else if (zzcxx == 34) {
                    if (this.zzbdz == null) {
                        this.zzbdz = new zzjv();
                    }
                    zzfls = this.zzbdz;
                } else {
                    if (zzcxx == 24) {
                        this.zzbdy = Integer.valueOf(zzflj.zzcym());
                        continue;
                    } else if (zzcxx == 40) {
                        this.zzbea = Integer.valueOf(zzflj.zzcym());
                        continue;
                    } else if (zzcxx == 48) {
                        i = zzflj.getPosition();
                        this.zzbeb = Integer.valueOf(zzjc.zzd(zzflj.zzcym()));
                        continue;
                    } else if (zzcxx == 56) {
                        i = zzflj.getPosition();
                        this.zzbec = Integer.valueOf(zzjc.zzd(zzflj.zzcym()));
                        continue;
                    } else if (zzcxx == 64) {
                        i = zzflj.getPosition();
                        try {
                            this.zzbed = Integer.valueOf(zzjc.zzd(zzflj.zzcym()));
                        } catch (IllegalArgumentException unused) {
                            zzflj.zzmw(i);
                            zza(zzflj, zzcxx);
                        }
                        continue;
                    } else if (!super.zza(zzflj, zzcxx)) {
                        return this;
                    } else {
                        continue;
                    }
                }
                zzflj.zza(zzfls);
            } else {
                this.zzbdw = zzflj.readString();
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        String str = this.zzbdw;
        if (str != null) {
            zzflk.zzp(1, str);
        }
        zzju zzju = this.zzbdx;
        if (zzju != null) {
            zzflk.zza(2, (zzfls) zzju);
        }
        Integer num = this.zzbdy;
        if (num != null) {
            zzflk.zzad(3, num.intValue());
        }
        zzjv zzjv = this.zzbdz;
        if (zzjv != null) {
            zzflk.zza(4, (zzfls) zzjv);
        }
        Integer num2 = this.zzbea;
        if (num2 != null) {
            zzflk.zzad(5, num2.intValue());
        }
        Integer num3 = this.zzbeb;
        if (num3 != null) {
            zzflk.zzad(6, num3.intValue());
        }
        Integer num4 = this.zzbec;
        if (num4 != null) {
            zzflk.zzad(7, num4.intValue());
        }
        Integer num5 = this.zzbed;
        if (num5 != null) {
            zzflk.zzad(8, num5.intValue());
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        String str = this.zzbdw;
        if (str != null) {
            zzq += zzflk.zzq(1, str);
        }
        zzju zzju = this.zzbdx;
        if (zzju != null) {
            zzq += zzflk.zzb(2, (zzfls) zzju);
        }
        Integer num = this.zzbdy;
        if (num != null) {
            zzq += zzflk.zzag(3, num.intValue());
        }
        zzjv zzjv = this.zzbdz;
        if (zzjv != null) {
            zzq += zzflk.zzb(4, (zzfls) zzjv);
        }
        Integer num2 = this.zzbea;
        if (num2 != null) {
            zzq += zzflk.zzag(5, num2.intValue());
        }
        Integer num3 = this.zzbeb;
        if (num3 != null) {
            zzq += zzflk.zzag(6, num3.intValue());
        }
        Integer num4 = this.zzbec;
        if (num4 != null) {
            zzq += zzflk.zzag(7, num4.intValue());
        }
        Integer num5 = this.zzbed;
        return num5 != null ? zzq + zzflk.zzag(8, num5.intValue()) : zzq;
    }
}
