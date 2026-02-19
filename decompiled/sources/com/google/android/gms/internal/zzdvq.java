package com.google.android.gms.internal;

import com.google.android.gms.internal.zzdvs;
import com.google.android.gms.internal.zzfhu;
import java.io.IOException;
import java.util.Objects;

public final class zzdvq extends zzfhu<zzdvq, zzdvq.zza> implements zzfje {
    private static volatile zzfjl<zzdvq> zzbbm;
    /* access modifiers changed from: private */
    public static final zzdvq zzmhb;
    private int zzmfs;
    private zzfgs zzmga = zzfgs.zzpnw;
    private zzdvs zzmha;

    public static final class zza extends zzfhu.zza<zzdvq, zzdvq.zza> implements zzfje {
        private zza() {
            super(zzdvq.zzmhb);
        }

        /* synthetic */ zza(zzdvr zzdvr) {
            this();
        }

        public final zza zzb(zzdvs zzdvs) {
            zzczv();
            ((zzdvq) this.zzppl).zza(zzdvs);
            return this;
        }

        public final zza zzgi(int i) {
            zzczv();
            ((zzdvq) this.zzppl).setVersion(0);
            return this;
        }

        public final zza zzy(zzfgs zzfgs) {
            zzczv();
            ((zzdvq) this.zzppl).zzk(zzfgs);
            return this;
        }
    }

    static {
        zzdvq zzdvq = new zzdvq();
        zzmhb = zzdvq;
        zzdvq.zza(zzfhu.zzg.zzppw, (Object) null, (Object) null);
        zzdvq.zzpph.zzbkr();
    }

    private zzdvq() {
    }

    /* access modifiers changed from: private */
    public final void setVersion(int i) {
        this.zzmfs = i;
    }

    /* access modifiers changed from: private */
    public final void zza(zzdvs zzdvs) {
        Objects.requireNonNull(zzdvs);
        this.zzmha = zzdvs;
    }

    public static zza zzbpy() {
        return (zza) ((zzfhu.zza) zzmhb.zza(zzfhu.zzg.zzppy, (Object) null, (Object) null));
    }

    /* access modifiers changed from: private */
    public final void zzk(zzfgs zzfgs) {
        Objects.requireNonNull(zzfgs);
        this.zzmga = zzfgs;
    }

    public static zzdvq zzx(zzfgs zzfgs) throws zzfie {
        return (zzdvq) zzfhu.zza(zzmhb, zzfgs);
    }

    public final int getVersion() {
        return this.zzmfs;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        zzdvs.zza zza2;
        boolean z = true;
        boolean z2 = false;
        switch (zzdvr.zzbbk[i - 1]) {
            case 1:
                return new zzdvq();
            case 2:
                return zzmhb;
            case 3:
                return null;
            case 4:
                return new zza((zzdvr) null);
            case 5:
                zzfhu.zzh zzh = (zzfhu.zzh) obj;
                zzdvq zzdvq = (zzdvq) obj2;
                int i2 = this.zzmfs;
                boolean z3 = i2 != 0;
                int i3 = zzdvq.zzmfs;
                this.zzmfs = zzh.zza(z3, i2, i3 != 0, i3);
                this.zzmha = (zzdvs) zzh.zza(this.zzmha, zzdvq.zzmha);
                boolean z4 = this.zzmga != zzfgs.zzpnw;
                zzfgs zzfgs = this.zzmga;
                if (zzdvq.zzmga == zzfgs.zzpnw) {
                    z = false;
                }
                this.zzmga = zzh.zza(z4, zzfgs, z, zzdvq.zzmga);
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
                                zzdvs zzdvs = this.zzmha;
                                if (zzdvs != null) {
                                    zzfhu.zza zza3 = (zzfhu.zza) zzdvs.zza(zzfhu.zzg.zzppy, (Object) null, (Object) null);
                                    zza3.zza(zzdvs);
                                    zza2 = (zzdvs.zza) zza3;
                                } else {
                                    zza2 = null;
                                }
                                zzdvs zzdvs2 = (zzdvs) zzfhb.zza(zzdvs.zzbqd(), zzfhm);
                                this.zzmha = zzdvs2;
                                if (zza2 != null) {
                                    zza2.zza(zzdvs2);
                                    this.zzmha = (zzdvs) zza2.zzczw();
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
                    synchronized (zzdvq.class) {
                        if (zzbbm == null) {
                            zzbbm = new zzfhu.zzb(zzmhb);
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
        return zzmhb;
    }

    public final void zza(zzfhg zzfhg) throws IOException {
        int i = this.zzmfs;
        if (i != 0) {
            zzfhg.zzae(1, i);
        }
        zzdvs zzdvs = this.zzmha;
        if (zzdvs != null) {
            if (zzdvs == null) {
                zzdvs = zzdvs.zzbqd();
            }
            zzfhg.zza(2, (zzfjc) zzdvs);
        }
        if (!this.zzmga.isEmpty()) {
            zzfhg.zza(3, this.zzmga);
        }
        this.zzpph.zza(zzfhg);
    }

    public final zzfgs zzbot() {
        return this.zzmga;
    }

    public final zzdvs zzbpx() {
        zzdvs zzdvs = this.zzmha;
        return zzdvs == null ? zzdvs.zzbqd() : zzdvs;
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
        zzdvs zzdvs = this.zzmha;
        if (zzdvs != null) {
            if (zzdvs == null) {
                zzdvs = zzdvs.zzbqd();
            }
            i3 += zzfhg.zzc(2, (zzfjc) zzdvs);
        }
        if (!this.zzmga.isEmpty()) {
            i3 += zzfhg.zzc(3, this.zzmga);
        }
        int zzhs = i3 + this.zzpph.zzhs();
        this.zzppi = zzhs;
        return zzhs;
    }
}
