package com.google.android.gms.internal;

import com.google.android.gms.internal.zzdwz;
import com.google.android.gms.internal.zzfhu;
import java.io.IOException;
import java.util.Objects;

public final class zzdwx extends zzfhu<zzdwx, zzdwx.zza> implements zzfje {
    private static volatile zzfjl<zzdwx> zzbbm;
    /* access modifiers changed from: private */
    public static final zzdwx zzmjr;
    private int zzmfs;
    private zzdwz zzmjq;

    public static final class zza extends zzfhu.zza<zzdwx, zzdwx.zza> implements zzfje {
        private zza() {
            super(zzdwx.zzmjr);
        }

        /* synthetic */ zza(zzdwy zzdwy) {
            this();
        }

        public final zza zzb(zzdwz zzdwz) {
            zzczv();
            ((zzdwx) this.zzppl).zza(zzdwz);
            return this;
        }

        public final zza zzgw(int i) {
            zzczv();
            ((zzdwx) this.zzppl).setVersion(0);
            return this;
        }
    }

    static {
        zzdwx zzdwx = new zzdwx();
        zzmjr = zzdwx;
        zzdwx.zza(zzfhu.zzg.zzppw, (Object) null, (Object) null);
        zzdwx.zzpph.zzbkr();
    }

    private zzdwx() {
    }

    /* access modifiers changed from: private */
    public final void setVersion(int i) {
        this.zzmfs = i;
    }

    /* access modifiers changed from: private */
    public final void zza(zzdwz zzdwz) {
        Objects.requireNonNull(zzdwz);
        this.zzmjq = zzdwz;
    }

    public static zzdwx zzal(zzfgs zzfgs) throws zzfie {
        return (zzdwx) zzfhu.zza(zzmjr, zzfgs);
    }

    public static zza zzbsg() {
        return (zza) ((zzfhu.zza) zzmjr.zza(zzfhu.zzg.zzppy, (Object) null, (Object) null));
    }

    public final int getVersion() {
        return this.zzmfs;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        zzdwz.zza zza2;
        boolean z = true;
        boolean z2 = false;
        switch (zzdwy.zzbbk[i - 1]) {
            case 1:
                return new zzdwx();
            case 2:
                return zzmjr;
            case 3:
                return null;
            case 4:
                return new zza((zzdwy) null);
            case 5:
                zzfhu.zzh zzh = (zzfhu.zzh) obj;
                zzdwx zzdwx = (zzdwx) obj2;
                int i2 = this.zzmfs;
                boolean z3 = i2 != 0;
                int i3 = zzdwx.zzmfs;
                if (i3 == 0) {
                    z = false;
                }
                this.zzmfs = zzh.zza(z3, i2, z, i3);
                this.zzmjq = (zzdwz) zzh.zza(this.zzmjq, zzdwx.zzmjq);
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
                                zzdwz zzdwz = this.zzmjq;
                                if (zzdwz != null) {
                                    zzfhu.zza zza3 = (zzfhu.zza) zzdwz.zza(zzfhu.zzg.zzppy, (Object) null, (Object) null);
                                    zza3.zza(zzdwz);
                                    zza2 = (zzdwz.zza) zza3;
                                } else {
                                    zza2 = null;
                                }
                                zzdwz zzdwz2 = (zzdwz) zzfhb.zza(zzdwz.zzbsk(), zzfhm);
                                this.zzmjq = zzdwz2;
                                if (zza2 != null) {
                                    zza2.zza(zzdwz2);
                                    this.zzmjq = (zzdwz) zza2.zzczw();
                                }
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
                    synchronized (zzdwx.class) {
                        if (zzbbm == null) {
                            zzbbm = new zzfhu.zzb(zzmjr);
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
        return zzmjr;
    }

    public final void zza(zzfhg zzfhg) throws IOException {
        int i = this.zzmfs;
        if (i != 0) {
            zzfhg.zzae(1, i);
        }
        zzdwz zzdwz = this.zzmjq;
        if (zzdwz != null) {
            if (zzdwz == null) {
                zzdwz = zzdwz.zzbsk();
            }
            zzfhg.zza(2, (zzfjc) zzdwz);
        }
        this.zzpph.zza(zzfhg);
    }

    public final zzdwz zzbsf() {
        zzdwz zzdwz = this.zzmjq;
        return zzdwz == null ? zzdwz.zzbsk() : zzdwz;
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
        zzdwz zzdwz = this.zzmjq;
        if (zzdwz != null) {
            if (zzdwz == null) {
                zzdwz = zzdwz.zzbsk();
            }
            i3 += zzfhg.zzc(2, (zzfjc) zzdwz);
        }
        int zzhs = i3 + this.zzpph.zzhs();
        this.zzppi = zzhs;
        return zzhs;
    }
}
