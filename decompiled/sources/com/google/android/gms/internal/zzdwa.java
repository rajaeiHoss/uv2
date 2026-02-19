package com.google.android.gms.internal;

import com.google.android.gms.internal.zzdwe;
import com.google.android.gms.internal.zzfhu;
import java.io.IOException;
import java.util.Objects;

public final class zzdwa extends zzfhu<zzdwa, zzdwa.zza> implements zzfje {
    private static volatile zzfjl<zzdwa> zzbbm;
    /* access modifiers changed from: private */
    public static final zzdwa zzmhy;
    private int zzmfs;
    private zzfgs zzmga = zzfgs.zzpnw;
    private zzdwe zzmhx;

    public static final class zza extends zzfhu.zza<zzdwa, zzdwa.zza> implements zzfje {
        private zza() {
            super(zzdwa.zzmhy);
        }

        /* synthetic */ zza(zzdwb zzdwb) {
            this();
        }

        public final zza zzaf(zzfgs zzfgs) {
            zzczv();
            ((zzdwa) this.zzppl).zzk(zzfgs);
            return this;
        }

        public final zza zzc(zzdwe zzdwe) {
            zzczv();
            ((zzdwa) this.zzppl).zzb(zzdwe);
            return this;
        }

        public final zza zzgm(int i) {
            zzczv();
            ((zzdwa) this.zzppl).setVersion(0);
            return this;
        }
    }

    static {
        zzdwa zzdwa = new zzdwa();
        zzmhy = zzdwa;
        zzdwa.zza(zzfhu.zzg.zzppw, (Object) null, (Object) null);
        zzdwa.zzpph.zzbkr();
    }

    private zzdwa() {
    }

    /* access modifiers changed from: private */
    public final void setVersion(int i) {
        this.zzmfs = i;
    }

    public static zzdwa zzae(zzfgs zzfgs) throws zzfie {
        return (zzdwa) zzfhu.zza(zzmhy, zzfgs);
    }

    /* access modifiers changed from: private */
    public final void zzb(zzdwe zzdwe) {
        Objects.requireNonNull(zzdwe);
        this.zzmhx = zzdwe;
    }

    public static zza zzbql() {
        return (zza) ((zzfhu.zza) zzmhy.zza(zzfhu.zzg.zzppy, (Object) null, (Object) null));
    }

    public static zzdwa zzbqm() {
        return zzmhy;
    }

    /* access modifiers changed from: private */
    public final void zzk(zzfgs zzfgs) {
        Objects.requireNonNull(zzfgs);
        this.zzmga = zzfgs;
    }

    public final int getVersion() {
        return this.zzmfs;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        zzdwe.zza zza2;
        boolean z = true;
        boolean z2 = false;
        switch (zzdwb.zzbbk[i - 1]) {
            case 1:
                return new zzdwa();
            case 2:
                return zzmhy;
            case 3:
                return null;
            case 4:
                return new zza((zzdwb) null);
            case 5:
                zzfhu.zzh zzh = (zzfhu.zzh) obj;
                zzdwa zzdwa = (zzdwa) obj2;
                int i2 = this.zzmfs;
                boolean z3 = i2 != 0;
                int i3 = zzdwa.zzmfs;
                this.zzmfs = zzh.zza(z3, i2, i3 != 0, i3);
                this.zzmhx = (zzdwe) zzh.zza(this.zzmhx, zzdwa.zzmhx);
                boolean z4 = this.zzmga != zzfgs.zzpnw;
                zzfgs zzfgs = this.zzmga;
                if (zzdwa.zzmga == zzfgs.zzpnw) {
                    z = false;
                }
                this.zzmga = zzh.zza(z4, zzfgs, z, zzdwa.zzmga);
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
                                zzdwe zzdwe = this.zzmhx;
                                if (zzdwe != null) {
                                    zzfhu.zza zza3 = (zzfhu.zza) zzdwe.zza(zzfhu.zzg.zzppy, (Object) null, (Object) null);
                                    zza3.zza(zzdwe);
                                    zza2 = (zzdwe.zza) zza3;
                                } else {
                                    zza2 = null;
                                }
                                zzdwe zzdwe2 = (zzdwe) zzfhb.zza(zzdwe.zzbqs(), zzfhm);
                                this.zzmhx = zzdwe2;
                                if (zza2 != null) {
                                    zza2.zza(zzdwe2);
                                    this.zzmhx = (zzdwe) zza2.zzczw();
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
                    synchronized (zzdwa.class) {
                        if (zzbbm == null) {
                            zzbbm = new zzfhu.zzb(zzmhy);
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
        return zzmhy;
    }

    public final void zza(zzfhg zzfhg) throws IOException {
        int i = this.zzmfs;
        if (i != 0) {
            zzfhg.zzae(1, i);
        }
        zzdwe zzdwe = this.zzmhx;
        if (zzdwe != null) {
            if (zzdwe == null) {
                zzdwe = zzdwe.zzbqs();
            }
            zzfhg.zza(2, (zzfjc) zzdwe);
        }
        if (!this.zzmga.isEmpty()) {
            zzfhg.zza(3, this.zzmga);
        }
        this.zzpph.zza(zzfhg);
    }

    public final zzfgs zzbot() {
        return this.zzmga;
    }

    public final zzdwe zzbqk() {
        zzdwe zzdwe = this.zzmhx;
        return zzdwe == null ? zzdwe.zzbqs() : zzdwe;
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
        zzdwe zzdwe = this.zzmhx;
        if (zzdwe != null) {
            if (zzdwe == null) {
                zzdwe = zzdwe.zzbqs();
            }
            i3 += zzfhg.zzc(2, (zzfjc) zzdwe);
        }
        if (!this.zzmga.isEmpty()) {
            i3 += zzfhg.zzc(3, this.zzmga);
        }
        int zzhs = i3 + this.zzpph.zzhs();
        this.zzppi = zzhs;
        return zzhs;
    }
}
