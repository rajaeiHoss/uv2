package com.google.android.gms.internal;

import com.google.android.gms.internal.zzdva;
import com.google.android.gms.internal.zzfhu;
import java.io.IOException;
import java.util.Objects;

public final class zzduw extends zzfhu<zzduw, zzduw.zza> implements zzfje {
    private static volatile zzfjl<zzduw> zzbbm;
    /* access modifiers changed from: private */
    public static final zzduw zzmgh;
    private int zzmfs;
    private zzfgs zzmga = zzfgs.zzpnw;
    private zzdva zzmgg;

    public static final class zza extends zzfhu.zza<zzduw, zzduw.zza> implements zzfje {
        private zza() {
            super(zzduw.zzmgh);
        }

        /* synthetic */ zza(zzdux zzdux) {
            this();
        }

        public final zza zzb(zzdva zzdva) {
            zzczv();
            ((zzduw) this.zzppl).zza(zzdva);
            return this;
        }

        public final zza zzge(int i) {
            zzczv();
            ((zzduw) this.zzppl).setVersion(0);
            return this;
        }

        public final zza zzp(zzfgs zzfgs) {
            zzczv();
            ((zzduw) this.zzppl).zzk(zzfgs);
            return this;
        }
    }

    static {
        zzduw zzduw = new zzduw();
        zzmgh = zzduw;
        zzduw.zza(zzfhu.zzg.zzppw, (Object) null, (Object) null);
        zzduw.zzpph.zzbkr();
    }

    private zzduw() {
    }

    /* access modifiers changed from: private */
    public final void setVersion(int i) {
        this.zzmfs = i;
    }

    /* access modifiers changed from: private */
    public final void zza(zzdva zzdva) {
        Objects.requireNonNull(zzdva);
        this.zzmgg = zzdva;
    }

    public static zza zzbpd() {
        return (zza) ((zzfhu.zza) zzmgh.zza(zzfhu.zzg.zzppy, (Object) null, (Object) null));
    }

    /* access modifiers changed from: private */
    public final void zzk(zzfgs zzfgs) {
        Objects.requireNonNull(zzfgs);
        this.zzmga = zzfgs;
    }

    public static zzduw zzo(zzfgs zzfgs) throws zzfie {
        return (zzduw) zzfhu.zza(zzmgh, zzfgs);
    }

    public final int getVersion() {
        return this.zzmfs;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        zzdva.zza zza2;
        boolean z = true;
        boolean z2 = false;
        switch (zzdux.zzbbk[i - 1]) {
            case 1:
                return new zzduw();
            case 2:
                return zzmgh;
            case 3:
                return null;
            case 4:
                return new zza((zzdux) null);
            case 5:
                zzfhu.zzh zzh = (zzfhu.zzh) obj;
                zzduw zzduw = (zzduw) obj2;
                int i2 = this.zzmfs;
                boolean z3 = i2 != 0;
                int i3 = zzduw.zzmfs;
                this.zzmfs = zzh.zza(z3, i2, i3 != 0, i3);
                this.zzmgg = (zzdva) zzh.zza(this.zzmgg, zzduw.zzmgg);
                boolean z4 = this.zzmga != zzfgs.zzpnw;
                zzfgs zzfgs = this.zzmga;
                if (zzduw.zzmga == zzfgs.zzpnw) {
                    z = false;
                }
                this.zzmga = zzh.zza(z4, zzfgs, z, zzduw.zzmga);
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
                                zzdva zzdva = this.zzmgg;
                                if (zzdva != null) {
                                    zzfhu.zza zza3 = (zzfhu.zza) zzdva.zza(zzfhu.zzg.zzppy, (Object) null, (Object) null);
                                    zza3.zza(zzdva);
                                    zza2 = (zzdva.zza) zza3;
                                } else {
                                    zza2 = null;
                                }
                                zzdva zzdva2 = (zzdva) zzfhb.zza(zzdva.zzbpg(), zzfhm);
                                this.zzmgg = zzdva2;
                                if (zza2 != null) {
                                    zza2.zza(zzdva2);
                                    this.zzmgg = (zzdva) zza2.zzczw();
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
                    synchronized (zzduw.class) {
                        if (zzbbm == null) {
                            zzbbm = new zzfhu.zzb(zzmgh);
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
        return zzmgh;
    }

    public final void zza(zzfhg zzfhg) throws IOException {
        int i = this.zzmfs;
        if (i != 0) {
            zzfhg.zzae(1, i);
        }
        zzdva zzdva = this.zzmgg;
        if (zzdva != null) {
            if (zzdva == null) {
                zzdva = zzdva.zzbpg();
            }
            zzfhg.zza(2, (zzfjc) zzdva);
        }
        if (!this.zzmga.isEmpty()) {
            zzfhg.zza(3, this.zzmga);
        }
        this.zzpph.zza(zzfhg);
    }

    public final zzfgs zzbot() {
        return this.zzmga;
    }

    public final zzdva zzbpc() {
        zzdva zzdva = this.zzmgg;
        return zzdva == null ? zzdva.zzbpg() : zzdva;
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
        zzdva zzdva = this.zzmgg;
        if (zzdva != null) {
            if (zzdva == null) {
                zzdva = zzdva.zzbpg();
            }
            i3 += zzfhg.zzc(2, (zzfjc) zzdva);
        }
        if (!this.zzmga.isEmpty()) {
            i3 += zzfhg.zzc(3, this.zzmga);
        }
        int zzhs = i3 + this.zzpph.zzhs();
        this.zzppi = zzhs;
        return zzhs;
    }
}
