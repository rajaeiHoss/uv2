package com.google.android.gms.internal;

import java.io.IOException;

public final class zzjj extends zzflm<zzjj> {
    private Integer zzbee = null;
    private zzjv zzbef = null;
    private String zzbeg = null;
    private String zzbeh = null;

    public zzjj() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzl */
    public final zzjj zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 40) {
                try {
                    int zzcym = zzflj.zzcym();
                    if (!(zzcym == 0 || zzcym == 1)) {
                        if (zzcym != 2) {
                            StringBuilder sb = new StringBuilder(40);
                            sb.append(zzcym);
                            sb.append(" is not a valid enum Platform");
                            throw new IllegalArgumentException(sb.toString());
                        }
                    }
                    this.zzbee = Integer.valueOf(zzcym);
                } catch (IllegalArgumentException unused) {
                    zzflj.zzmw(zzflj.getPosition());
                    zza(zzflj, zzcxx);
                }
            } else if (zzcxx == 50) {
                if (this.zzbef == null) {
                    this.zzbef = new zzjv();
                }
                zzflj.zza(this.zzbef);
            } else if (zzcxx == 58) {
                this.zzbeg = zzflj.readString();
            } else if (zzcxx == 66) {
                this.zzbeh = zzflj.readString();
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        Integer num = this.zzbee;
        if (num != null) {
            zzflk.zzad(5, num.intValue());
        }
        zzjv zzjv = this.zzbef;
        if (zzjv != null) {
            zzflk.zza(6, (zzfls) zzjv);
        }
        String str = this.zzbeg;
        if (str != null) {
            zzflk.zzp(7, str);
        }
        String str2 = this.zzbeh;
        if (str2 != null) {
            zzflk.zzp(8, str2);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        Integer num = this.zzbee;
        if (num != null) {
            zzq += zzflk.zzag(5, num.intValue());
        }
        zzjv zzjv = this.zzbef;
        if (zzjv != null) {
            zzq += zzflk.zzb(6, (zzfls) zzjv);
        }
        String str = this.zzbeg;
        if (str != null) {
            zzq += zzflk.zzq(7, str);
        }
        String str2 = this.zzbeh;
        return str2 != null ? zzq + zzflk.zzq(8, str2) : zzq;
    }
}
