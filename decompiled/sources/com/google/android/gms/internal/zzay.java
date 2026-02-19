package com.google.android.gms.internal;

import java.io.IOException;

public final class zzay extends zzflm<zzay> {
    public String zzcv;
    private String zzcw;
    private String zzcx;
    private String zzcy;
    private String zzcz;

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 10) {
                this.zzcv = zzflj.readString();
            } else if (zzcxx == 18) {
                this.zzcw = zzflj.readString();
            } else if (zzcxx == 26) {
                this.zzcx = zzflj.readString();
            } else if (zzcxx == 34) {
                this.zzcy = zzflj.readString();
            } else if (zzcxx == 42) {
                this.zzcz = zzflj.readString();
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        String str = this.zzcv;
        if (str != null) {
            zzflk.zzp(1, str);
        }
        String str2 = this.zzcw;
        if (str2 != null) {
            zzflk.zzp(2, str2);
        }
        String str3 = this.zzcx;
        if (str3 != null) {
            zzflk.zzp(3, str3);
        }
        String str4 = this.zzcy;
        if (str4 != null) {
            zzflk.zzp(4, str4);
        }
        String str5 = this.zzcz;
        if (str5 != null) {
            zzflk.zzp(5, str5);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        String str = this.zzcv;
        if (str != null) {
            zzq += zzflk.zzq(1, str);
        }
        String str2 = this.zzcw;
        if (str2 != null) {
            zzq += zzflk.zzq(2, str2);
        }
        String str3 = this.zzcx;
        if (str3 != null) {
            zzq += zzflk.zzq(3, str3);
        }
        String str4 = this.zzcy;
        if (str4 != null) {
            zzq += zzflk.zzq(4, str4);
        }
        String str5 = this.zzcz;
        return str5 != null ? zzq + zzflk.zzq(5, str5) : zzq;
    }
}
