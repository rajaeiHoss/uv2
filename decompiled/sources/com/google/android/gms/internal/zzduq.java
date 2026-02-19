package com.google.android.gms.internal;

import com.google.android.gms.internal.zzduu;
import com.google.android.gms.internal.zzfhu;
import java.io.IOException;
import java.util.Objects;

public final class zzduq extends zzfhu<zzduq, zzduq.zza> implements zzfje {
    private static volatile zzfjl<zzduq> zzbbm;
    /* access modifiers changed from: private */
    public static final zzduq zzmgb;
    private int zzmfs;
    private zzduu zzmfz;
    private zzfgs zzmga = zzfgs.zzpnw;

    public static final class zza extends zzfhu.zza<zzduq, zzduq.zza> implements zzfje {
        private zza() {
            super(zzduq.zzmgb);
        }

        /* synthetic */ zza(zzdur zzdur) {
            this();
        }

        public final zza zzc(zzduu zzduu) {
            zzczv();
            ((zzduq) this.zzppl).zzb(zzduu);
            return this;
        }

        public final zza zzgd(int i) {
            zzczv();
            ((zzduq) this.zzppl).setVersion(0);
            return this;
        }

        public final zza zzm(zzfgs zzfgs) {
            zzczv();
            ((zzduq) this.zzppl).zzk(zzfgs);
            return this;
        }
    }

    static {
        zzduq zzduq = new zzduq();
        zzmgb = zzduq;
        zzduq.zza(zzfhu.zzg.zzppw, (Object) null, (Object) null);
        zzduq.zzpph.zzbkr();
    }

    private zzduq() {
    }

    /* access modifiers changed from: private */
    public final void setVersion(int i) {
        this.zzmfs = i;
    }

    /* access modifiers changed from: private */
    public final void zzb(zzduu zzduu) {
        Objects.requireNonNull(zzduu);
        this.zzmfz = zzduu;
    }

    public static zza zzbou() {
        return (zza) ((zzfhu.zza) zzmgb.zza(zzfhu.zzg.zzppy, (Object) null, (Object) null));
    }

    public static zzduq zzbov() {
        return zzmgb;
    }

    /* access modifiers changed from: private */
    public final void zzk(zzfgs zzfgs) {
        Objects.requireNonNull(zzfgs);
        this.zzmga = zzfgs;
    }

    public static zzduq zzl(zzfgs zzfgs) throws zzfie {
        return (zzduq) zzfhu.zza(zzmgb, zzfgs);
    }

    public final int getVersion() {
        return this.zzmfs;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        zzduu.zza zza2;
        boolean z = true;
        boolean z2 = false;
        switch (zzdur.zzbbk[i - 1]) {
            case 1:
                return new zzduq();
            case 2:
                return zzmgb;
            case 3:
                return null;
            case 4:
                return new zza((zzdur) null);
            case 5:
                zzfhu.zzh zzh = (zzfhu.zzh) obj;
                zzduq zzduq = (zzduq) obj2;
                int i2 = this.zzmfs;
                boolean z3 = i2 != 0;
                int i3 = zzduq.zzmfs;
                this.zzmfs = zzh.zza(z3, i2, i3 != 0, i3);
                this.zzmfz = (zzduu) zzh.zza(this.zzmfz, zzduq.zzmfz);
                boolean z4 = this.zzmga != zzfgs.zzpnw;
                zzfgs zzfgs = this.zzmga;
                if (zzduq.zzmga == zzfgs.zzpnw) {
                    z = false;
                }
                this.zzmga = zzh.zza(z4, zzfgs, z, zzduq.zzmga);
                return this;
            case 6:
                zzfhb zzfhb = (zzfhb) obj;
                zzfhm zzfhm = (zzfhm) obj2;
                Objects.requireNonNull(zzfhm);
                while (!z2) {
                    try {
                        int zzcxx = zzfhb.zzcxx();
                        if (zzcxx != 0) {
                            if (zzcxx == 8) {
                                this.zzmfs = zzfhb.zzcyg();
                            } else if (zzcxx == 18) {
                                zzduu zzduu = this.zzmfz;
                                if (zzduu != null) {
                                    zzfhu.zza zza3 = (zzfhu.zza) zzduu.zza(zzfhu.zzg.zzppy, (Object) null, (Object) null);
                                    zza3.zza(zzduu);
                                    zza2 = (zzduu.zza) zza3;
                                } else {
                                    zza2 = null;
                                }
                                zzduu zzduu2 = (zzduu) zzfhb.zza(zzduu.zzbpa(), zzfhm);
                                this.zzmfz = zzduu2;
                                if (zza2 != null) {
                                    zza2.zza(zzduu2);
                                    this.zzmfz = (zzduu) zza2.zzczw();
                                }
                            } else if (zzcxx == 26) {
                                this.zzmga = zzfhb.zzcyf();
                            } else if (!zza(zzcxx, zzfhb)) {
                            }
                        }
                        z2 = true;
                    } catch (zzfie e) {
                        throw new RuntimeException(e.zzi(this));
                    } catch (IOException e2) {
                        throw new RuntimeException(new zzfie(e2.getMessage()).zzi(this));
                    }
                }
                break;
            case 7:
                break;
            case 8:
                if (zzbbm == null) {
                    synchronized (zzduq.class) {
                        if (zzbbm == null) {
                            zzbbm = new zzfhu.zzb(zzmgb);
                        }
                    }
                }
                return zzbbm;
            case 9:
                return (byte) 1;
            case 10:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
        return zzmgb;
    }

    public final void zza(zzfhg zzfhg) throws IOException {
        int i = this.zzmfs;
        if (i != 0) {
            zzfhg.zzae(1, i);
        }
        zzduu zzduu = this.zzmfz;
        if (zzduu != null) {
            if (zzduu == null) {
                zzduu = zzduu.zzbpa();
            }
            zzfhg.zza(2, (zzfjc) zzduu);
        }
        if (!this.zzmga.isEmpty()) {
            zzfhg.zza(3, this.zzmga);
        }
        this.zzpph.zza(zzfhg);
    }

    public final zzduu zzbos() {
        zzduu zzduu = this.zzmfz;
        return zzduu == null ? zzduu.zzbpa() : zzduu;
    }

    public final zzfgs zzbot() {
        return this.zzmga;
    }

    public final int zzhs() {
        int i = this.zzppi;
        if (i != -1) {
            return i;
        }
        int i2 = this.zzmfs;
        int i3 = 0;
        if (i2 != 0) {
            i3 = 0 + zzfhg.zzah(1, i2);
        }
        zzduu zzduu = this.zzmfz;
        if (zzduu != null) {
            if (zzduu == null) {
                zzduu = zzduu.zzbpa();
            }
            i3 += zzfhg.zzc(2, (zzfjc) zzduu);
        }
        if (!this.zzmga.isEmpty()) {
            i3 += zzfhg.zzc(3, this.zzmga);
        }
        int zzhs = i3 + this.zzpph.zzhs();
        this.zzppi = zzhs;
        return zzhs;
    }
}
