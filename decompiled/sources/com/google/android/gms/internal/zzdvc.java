package com.google.android.gms.internal;

import com.google.android.gms.internal.zzfhu;
import java.io.IOException;
import java.util.Objects;

public final class zzdvc extends zzfhu<zzdvc, zzdvc.zza> implements zzfje {
    private static volatile zzfjl<zzdvc> zzbbm;
    /* access modifiers changed from: private */
    public static final zzdvc zzmgk;
    private int zzmfs;
    private zzfgs zzmga = zzfgs.zzpnw;

    public static final class zza extends zzfhu.zza<zzdvc, zzdvc.zza> implements zzfje {
        private zza() {
            super(zzdvc.zzmgk);
        }

        /* synthetic */ zza(zzdvd zzdvd) {
            this();
        }

        public final zza zzgf(int i) {
            zzczv();
            ((zzdvc) this.zzppl).setVersion(0);
            return this;
        }

        public final zza zzs(zzfgs zzfgs) {
            zzczv();
            ((zzdvc) this.zzppl).zzk(zzfgs);
            return this;
        }
    }

    static {
        zzdvc zzdvc = new zzdvc();
        zzmgk = zzdvc;
        zzdvc.zza(zzfhu.zzg.zzppw, (Object) null, (Object) null);
        zzdvc.zzpph.zzbkr();
    }

    private zzdvc() {
    }

    /* access modifiers changed from: private */
    public final void setVersion(int i) {
        this.zzmfs = i;
    }

    public static zza zzbpi() {
        return (zza) ((zzfhu.zza) zzmgk.zza(zzfhu.zzg.zzppy, (Object) null, (Object) null));
    }

    /* access modifiers changed from: private */
    public final void zzk(zzfgs zzfgs) {
        Objects.requireNonNull(zzfgs);
        this.zzmga = zzfgs;
    }

    public static zzdvc zzr(zzfgs zzfgs) throws zzfie {
        return (zzdvc) zzfhu.zza(zzmgk, zzfgs);
    }

    public final int getVersion() {
        return this.zzmfs;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        boolean z = true;
        boolean z2 = false;
        switch (zzdvd.zzbbk[i - 1]) {
            case 1:
                return new zzdvc();
            case 2:
                return zzmgk;
            case 3:
                return null;
            case 4:
                return new zza((zzdvd) null);
            case 5:
                zzfhu.zzh zzh = (zzfhu.zzh) obj;
                zzdvc zzdvc = (zzdvc) obj2;
                int i2 = this.zzmfs;
                boolean z3 = i2 != 0;
                int i3 = zzdvc.zzmfs;
                this.zzmfs = zzh.zza(z3, i2, i3 != 0, i3);
                boolean z4 = this.zzmga != zzfgs.zzpnw;
                zzfgs zzfgs = this.zzmga;
                if (zzdvc.zzmga == zzfgs.zzpnw) {
                    z = false;
                }
                this.zzmga = zzh.zza(z4, zzfgs, z, zzdvc.zzmga);
                return this;
            case 6:
                zzfhb zzfhb = (zzfhb) obj;
                Objects.requireNonNull((zzfhm) obj2);
                while (!z2) {
                    try {
                        int zzcxx = zzfhb.zzcxx();
                        if (zzcxx != 0) {
                            if (zzcxx == 8) {
                                this.zzmfs = zzfhb.zzcyg();
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
                    synchronized (zzdvc.class) {
                        if (zzbbm == null) {
                            zzbbm = new zzfhu.zzb(zzmgk);
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
        return zzmgk;
    }

    public final void zza(zzfhg zzfhg) throws IOException {
        int i = this.zzmfs;
        if (i != 0) {
            zzfhg.zzae(1, i);
        }
        if (!this.zzmga.isEmpty()) {
            zzfhg.zza(3, this.zzmga);
        }
        this.zzpph.zza(zzfhg);
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
        if (!this.zzmga.isEmpty()) {
            i3 += zzfhg.zzc(3, this.zzmga);
        }
        int zzhs = i3 + this.zzpph.zzhs();
        this.zzppi = zzhs;
        return zzhs;
    }
}
