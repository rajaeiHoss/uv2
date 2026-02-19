package com.google.android.gms.internal;

import java.io.IOException;

public final class zzjt extends zzflm<zzjt> {
    private static volatile zzjt[] zzbfr;
    private String zzbes = null;
    private Integer zzbet = null;
    private zzju zzbev = null;

    public zzjt() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public static zzjt[] zzhw() {
        if (zzbfr == null) {
            synchronized (zzflq.zzpvt) {
                if (zzbfr == null) {
                    zzbfr = new zzjt[0];
                }
            }
        }
        return zzbfr;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzt */
    public final zzjt zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 10) {
                this.zzbes = zzflj.readString();
            } else if (zzcxx == 16) {
                int position = zzflj.getPosition();
                try {
                    this.zzbet = Integer.valueOf(zzjc.zzd(zzflj.zzcym()));
                } catch (IllegalArgumentException unused) {
                    zzflj.zzmw(position);
                    zza(zzflj, zzcxx);
                }
            } else if (zzcxx == 26) {
                if (this.zzbev == null) {
                    this.zzbev = new zzju();
                }
                zzflj.zza(this.zzbev);
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        String str = this.zzbes;
        if (str != null) {
            zzflk.zzp(1, str);
        }
        Integer num = this.zzbet;
        if (num != null) {
            zzflk.zzad(2, num.intValue());
        }
        zzju zzju = this.zzbev;
        if (zzju != null) {
            zzflk.zza(3, (zzfls) zzju);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        String str = this.zzbes;
        if (str != null) {
            zzq += zzflk.zzq(1, str);
        }
        Integer num = this.zzbet;
        if (num != null) {
            zzq += zzflk.zzag(2, num.intValue());
        }
        zzju zzju = this.zzbev;
        return zzju != null ? zzq + zzflk.zzb(3, (zzfls) zzju) : zzq;
    }
}
