package com.google.android.gms.internal;

import java.io.IOException;

public final class zzjh extends zzflm<zzjh> {
    private Integer zzbdq = null;
    private zzju zzbdr = null;
    private zzju zzbds = null;
    private zzju zzbdt = null;
    private zzju[] zzbdu = zzju.zzhx();
    private Integer zzbdv = null;

    public zzjh() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        zzju zzju;
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx != 8) {
                if (zzcxx == 18) {
                    if (this.zzbdr == null) {
                        this.zzbdr = new zzju();
                    }
                    zzju = this.zzbdr;
                } else if (zzcxx == 26) {
                    if (this.zzbds == null) {
                        this.zzbds = new zzju();
                    }
                    zzju = this.zzbds;
                } else if (zzcxx == 34) {
                    if (this.zzbdt == null) {
                        this.zzbdt = new zzju();
                    }
                    zzju = this.zzbdt;
                } else {
                    if (zzcxx == 42) {
                        int zzb = zzflv.zzb(zzflj, 42);
                        zzju[] zzjuArr = this.zzbdu;
                        int length = zzjuArr == null ? 0 : zzjuArr.length;
                        int i = zzb + length;
                        zzju[] zzjuArr2 = new zzju[i];
                        if (length != 0) {
                            System.arraycopy(zzjuArr, 0, zzjuArr2, 0, length);
                        }
                        while (length < i - 1) {
                            zzjuArr2[length] = new zzju();
                            zzflj.zza(zzjuArr2[length]);
                            zzflj.zzcxx();
                            length++;
                        }
                        zzjuArr2[length] = new zzju();
                        zzflj.zza(zzjuArr2[length]);
                        this.zzbdu = zzjuArr2;
                        continue;
                    } else if (zzcxx == 48) {
                        this.zzbdv = Integer.valueOf(zzflj.zzcym());
                        continue;
                    } else if (!super.zza(zzflj, zzcxx)) {
                        return this;
                    } else {
                        continue;
                    }
                }
                zzflj.zza(zzju);
            } else {
                this.zzbdq = Integer.valueOf(zzflj.zzcym());
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        Integer num = this.zzbdq;
        if (num != null) {
            zzflk.zzad(1, num.intValue());
        }
        zzju zzju = this.zzbdr;
        if (zzju != null) {
            zzflk.zza(2, (zzfls) zzju);
        }
        zzju zzju2 = this.zzbds;
        if (zzju2 != null) {
            zzflk.zza(3, (zzfls) zzju2);
        }
        zzju zzju3 = this.zzbdt;
        if (zzju3 != null) {
            zzflk.zza(4, (zzfls) zzju3);
        }
        zzju[] zzjuArr = this.zzbdu;
        if (zzjuArr != null && zzjuArr.length > 0) {
            int i = 0;
            while (true) {
                zzju[] zzjuArr2 = this.zzbdu;
                if (i >= zzjuArr2.length) {
                    break;
                }
                zzju zzju4 = zzjuArr2[i];
                if (zzju4 != null) {
                    zzflk.zza(5, (zzfls) zzju4);
                }
                i++;
            }
        }
        Integer num2 = this.zzbdv;
        if (num2 != null) {
            zzflk.zzad(6, num2.intValue());
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        Integer num = this.zzbdq;
        if (num != null) {
            zzq += zzflk.zzag(1, num.intValue());
        }
        zzju zzju = this.zzbdr;
        if (zzju != null) {
            zzq += zzflk.zzb(2, (zzfls) zzju);
        }
        zzju zzju2 = this.zzbds;
        if (zzju2 != null) {
            zzq += zzflk.zzb(3, (zzfls) zzju2);
        }
        zzju zzju3 = this.zzbdt;
        if (zzju3 != null) {
            zzq += zzflk.zzb(4, (zzfls) zzju3);
        }
        zzju[] zzjuArr = this.zzbdu;
        if (zzjuArr != null && zzjuArr.length > 0) {
            int i = 0;
            while (true) {
                zzju[] zzjuArr2 = this.zzbdu;
                if (i >= zzjuArr2.length) {
                    break;
                }
                zzju zzju4 = zzjuArr2[i];
                if (zzju4 != null) {
                    zzq += zzflk.zzb(5, (zzfls) zzju4);
                }
                i++;
            }
        }
        Integer num2 = this.zzbdv;
        return num2 != null ? zzq + zzflk.zzag(6, num2.intValue()) : zzq;
    }
}
