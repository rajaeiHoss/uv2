package com.google.android.gms.internal;

import com.google.android.gms.internal.zzdvo;
import com.google.android.gms.internal.zzfhu;
import java.io.IOException;
import java.util.Objects;

public final class zzdvs extends zzfhu<zzdvs, zzdvs.zza> implements zzfje {
    private static volatile zzfjl<zzdvs> zzbbm;
    /* access modifiers changed from: private */
    public static final zzdvs zzmhe;
    private int zzmfs;
    private zzdvo zzmgu;
    private zzfgs zzmhc = zzfgs.zzpnw;
    private zzfgs zzmhd = zzfgs.zzpnw;

    public static final class zza extends zzfhu.zza<zzdvs, zzdvs.zza> implements zzfje {
        private zza() {
            super(zzdvs.zzmhe);
        }

        /* synthetic */ zza(zzdvt zzdvt) {
            this();
        }

        public final zza zzac(zzfgs zzfgs) {
            zzczv();
            ((zzdvs) this.zzppl).zzz(zzfgs);
            return this;
        }

        public final zza zzad(zzfgs zzfgs) {
            zzczv();
            ((zzdvs) this.zzppl).zzaa(zzfgs);
            return this;
        }

        public final zza zzc(zzdvo zzdvo) {
            zzczv();
            ((zzdvs) this.zzppl).zzb(zzdvo);
            return this;
        }

        public final zza zzgj(int i) {
            zzczv();
            ((zzdvs) this.zzppl).setVersion(0);
            return this;
        }
    }

    static {
        zzdvs zzdvs = new zzdvs();
        zzmhe = zzdvs;
        zzdvs.zza(zzfhu.zzg.zzppw, (Object) null, (Object) null);
        zzdvs.zzpph.zzbkr();
    }

    private zzdvs() {
    }

    /* access modifiers changed from: private */
    public final void setVersion(int i) {
        this.zzmfs = i;
    }

    /* access modifiers changed from: private */
    public final void zzaa(zzfgs zzfgs) {
        Objects.requireNonNull(zzfgs);
        this.zzmhd = zzfgs;
    }

    public static zzdvs zzab(zzfgs zzfgs) throws zzfie {
        return (zzdvs) zzfhu.zza(zzmhe, zzfgs);
    }

    /* access modifiers changed from: private */
    public final void zzb(zzdvo zzdvo) {
        Objects.requireNonNull(zzdvo);
        this.zzmgu = zzdvo;
    }

    public static zza zzbqc() {
        return (zza) ((zzfhu.zza) zzmhe.zza(zzfhu.zzg.zzppy, (Object) null, (Object) null));
    }

    public static zzdvs zzbqd() {
        return zzmhe;
    }

    /* access modifiers changed from: private */
    public final void zzz(zzfgs zzfgs) {
        Objects.requireNonNull(zzfgs);
        this.zzmhc = zzfgs;
    }

    public final int getVersion() {
        return this.zzmfs;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        zzdvo.zza zza2;
        boolean z = true;
        boolean z2 = false;
        switch (zzdvt.zzbbk[i - 1]) {
            case 1:
                return new zzdvs();
            case 2:
                return zzmhe;
            case 3:
                return null;
            case 4:
                return new zza((zzdvt) null);
            case 5:
                zzfhu.zzh zzh = (zzfhu.zzh) obj;
                zzdvs zzdvs = (zzdvs) obj2;
                int i2 = this.zzmfs;
                boolean z3 = i2 != 0;
                int i3 = zzdvs.zzmfs;
                this.zzmfs = zzh.zza(z3, i2, i3 != 0, i3);
                this.zzmgu = (zzdvo) zzh.zza(this.zzmgu, zzdvs.zzmgu);
                this.zzmhc = zzh.zza(this.zzmhc != zzfgs.zzpnw, this.zzmhc, zzdvs.zzmhc != zzfgs.zzpnw, zzdvs.zzmhc);
                boolean z4 = this.zzmhd != zzfgs.zzpnw;
                zzfgs zzfgs = this.zzmhd;
                if (zzdvs.zzmhd == zzfgs.zzpnw) {
                    z = false;
                }
                this.zzmhd = zzh.zza(z4, zzfgs, z, zzdvs.zzmhd);
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
                                zzdvo zzdvo = this.zzmgu;
                                if (zzdvo != null) {
                                    zzfhu.zza zza3 = (zzfhu.zza) zzdvo.zza(zzfhu.zzg.zzppy, (Object) null, (Object) null);
                                    zza3.zza(zzdvo);
                                    zza2 = (zzdvo.zza) zza3;
                                } else {
                                    zza2 = null;
                                }
                                zzdvo zzdvo2 = (zzdvo) zzfhb.zza(zzdvo.zzbpv(), zzfhm);
                                this.zzmgu = zzdvo2;
                                if (zza2 != null) {
                                    zza2.zza(zzdvo2);
                                    this.zzmgu = (zzdvo) zza2.zzczw();
                                }
                            } else if (zzcxx == 26) {
                                this.zzmhc = zzfhb.zzcyf();
                            } else if (zzcxx == 34) {
                                this.zzmhd = zzfhb.zzcyf();
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
                    synchronized (zzdvs.class) {
                        if (zzbbm == null) {
                            zzbbm = new zzfhu.zzb(zzmhe);
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
        return zzmhe;
    }

    public final void zza(zzfhg zzfhg) throws IOException {
        int i = this.zzmfs;
        if (i != 0) {
            zzfhg.zzae(1, i);
        }
        zzdvo zzdvo = this.zzmgu;
        if (zzdvo != null) {
            if (zzdvo == null) {
                zzdvo = zzdvo.zzbpv();
            }
            zzfhg.zza(2, (zzfjc) zzdvo);
        }
        if (!this.zzmhc.isEmpty()) {
            zzfhg.zza(3, this.zzmhc);
        }
        if (!this.zzmhd.isEmpty()) {
            zzfhg.zza(4, this.zzmhd);
        }
        this.zzpph.zza(zzfhg);
    }

    public final zzdvo zzbpq() {
        zzdvo zzdvo = this.zzmgu;
        return zzdvo == null ? zzdvo.zzbpv() : zzdvo;
    }

    public final zzfgs zzbqa() {
        return this.zzmhc;
    }

    public final zzfgs zzbqb() {
        return this.zzmhd;
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
        zzdvo zzdvo = this.zzmgu;
        if (zzdvo != null) {
            if (zzdvo == null) {
                zzdvo = zzdvo.zzbpv();
            }
            i3 += zzfhg.zzc(2, (zzfjc) zzdvo);
        }
        if (!this.zzmhc.isEmpty()) {
            i3 += zzfhg.zzc(3, this.zzmhc);
        }
        if (!this.zzmhd.isEmpty()) {
            i3 += zzfhg.zzc(4, this.zzmhd);
        }
        int zzhs = i3 + this.zzpph.zzhs();
        this.zzppi = zzhs;
        return zzhs;
    }
}
