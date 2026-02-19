package com.google.android.gms.internal;

import com.google.android.gms.internal.zzfhu;
import java.io.IOException;
import java.util.Objects;

public final class zzdvg extends zzfhu<zzdvg, zzdvg.zza> implements zzfje {
    private static volatile zzfjl<zzdvg> zzbbm;
    /* access modifiers changed from: private */
    public static final zzdvg zzmgm;
    private int zzmfs;
    private zzfgs zzmga = zzfgs.zzpnw;

    public static final class zza extends zzfhu.zza<zzdvg, zzdvg.zza> implements zzfje {
        private zza() {
            super(zzdvg.zzmgm);
        }

        /* synthetic */ zza(zzdvh zzdvh) {
            this();
        }

        public final zza zzgg(int i) {
            zzczv();
            ((zzdvg) this.zzppl).setVersion(0);
            return this;
        }

        public final zza zzv(zzfgs zzfgs) {
            zzczv();
            ((zzdvg) this.zzppl).zzk(zzfgs);
            return this;
        }
    }

    static {
        zzdvg zzdvg = new zzdvg();
        zzmgm = zzdvg;
        zzdvg.zza(zzfhu.zzg.zzppw, (Object) null, (Object) null);
        zzdvg.zzpph.zzbkr();
    }

    private zzdvg() {
    }

    /* access modifiers changed from: private */
    public final void setVersion(int i) {
        this.zzmfs = i;
    }

    public static zza zzbpl() {
        return (zza) ((zzfhu.zza) zzmgm.zza(zzfhu.zzg.zzppy, (Object) null, (Object) null));
    }

    /* access modifiers changed from: private */
    public final void zzk(zzfgs zzfgs) {
        Objects.requireNonNull(zzfgs);
        this.zzmga = zzfgs;
    }

    public static zzdvg zzu(zzfgs zzfgs) throws zzfie {
        return (zzdvg) zzfhu.zza(zzmgm, zzfgs);
    }

    public final int getVersion() {
        return this.zzmfs;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        boolean z = true;
        boolean z2 = false;
        switch (zzdvh.zzbbk[i - 1]) {
            case 1:
                return new zzdvg();
            case 2:
                return zzmgm;
            case 3:
                return null;
            case 4:
                return new zza((zzdvh) null);
            case 5:
                zzfhu.zzh zzh = (zzfhu.zzh) obj;
                zzdvg zzdvg = (zzdvg) obj2;
                int i2 = this.zzmfs;
                boolean z3 = i2 != 0;
                int i3 = zzdvg.zzmfs;
                this.zzmfs = zzh.zza(z3, i2, i3 != 0, i3);
                boolean z4 = this.zzmga != zzfgs.zzpnw;
                zzfgs zzfgs = this.zzmga;
                if (zzdvg.zzmga == zzfgs.zzpnw) {
                    z = false;
                }
                this.zzmga = zzh.zza(z4, zzfgs, z, zzdvg.zzmga);
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
                            } else if (zzcxx == 18) {
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
                    synchronized (zzdvg.class) {
                        if (zzbbm == null) {
                            zzbbm = new zzfhu.zzb(zzmgm);
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
        return zzmgm;
    }

    public final void zza(zzfhg zzfhg) throws IOException {
        int i = this.zzmfs;
        if (i != 0) {
            zzfhg.zzae(1, i);
        }
        if (!this.zzmga.isEmpty()) {
            zzfhg.zza(2, this.zzmga);
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
            i3 += zzfhg.zzc(2, this.zzmga);
        }
        int zzhs = i3 + this.zzpph.zzhs();
        this.zzppi = zzhs;
        return zzhs;
    }
}
