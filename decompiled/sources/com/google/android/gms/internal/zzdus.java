package com.google.android.gms.internal;

import com.google.android.gms.internal.zzduu;
import com.google.android.gms.internal.zzfhu;
import java.io.IOException;
import java.util.Objects;

public final class zzdus extends zzfhu<zzdus, zzdus.zza> implements zzfje {
    private static volatile zzfjl<zzdus> zzbbm;
    /* access modifiers changed from: private */
    public static final zzdus zzmgd;
    private zzduu zzmfz;
    private int zzmgc;

    public static final class zza extends zzfhu.zza<zzdus, zzdus.zza> implements zzfje {
        private zza() {
            super(zzdus.zzmgd);
        }

        /* synthetic */ zza(zzdut zzdut) {
            this();
        }
    }

    static {
        zzdus zzdus = new zzdus();
        zzmgd = zzdus;
        zzdus.zza(zzfhu.zzg.zzppw, (Object) null, (Object) null);
        zzdus.zzpph.zzbkr();
    }

    private zzdus() {
    }

    public static zzdus zzbox() {
        return zzmgd;
    }

    public static zzdus zzn(zzfgs zzfgs) throws zzfie {
        return (zzdus) zzfhu.zza(zzmgd, zzfgs);
    }

    public final int getKeySize() {
        return this.zzmgc;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        zzduu.zza zza2;
        boolean z = true;
        boolean z2 = false;
        switch (zzdut.zzbbk[i - 1]) {
            case 1:
                return new zzdus();
            case 2:
                return zzmgd;
            case 3:
                return null;
            case 4:
                return new zza((zzdut) null);
            case 5:
                zzfhu.zzh zzh = (zzfhu.zzh) obj;
                zzdus zzdus = (zzdus) obj2;
                this.zzmfz = (zzduu) zzh.zza(this.zzmfz, zzdus.zzmfz);
                int i2 = this.zzmgc;
                boolean z3 = i2 != 0;
                int i3 = zzdus.zzmgc;
                if (i3 == 0) {
                    z = false;
                }
                this.zzmgc = zzh.zza(z3, i2, z, i3);
                return this;
            case 6:
                zzfhb zzfhb = (zzfhb) obj;
                zzfhm zzfhm = (zzfhm) obj2;
                Objects.requireNonNull(zzfhm);
                while (!z2) {
                    try {
                        int zzcxx = zzfhb.zzcxx();
                        if (zzcxx != 0) {
                            if (zzcxx == 10) {
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
                            } else if (zzcxx == 16) {
                                this.zzmgc = zzfhb.zzcyg();
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
                    synchronized (zzdus.class) {
                        if (zzbbm == null) {
                            zzbbm = new zzfhu.zzb(zzmgd);
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
        return zzmgd;
    }

    public final void zza(zzfhg zzfhg) throws IOException {
        zzduu zzduu = this.zzmfz;
        if (zzduu != null) {
            if (zzduu == null) {
                zzduu = zzduu.zzbpa();
            }
            zzfhg.zza(1, (zzfjc) zzduu);
        }
        int i = this.zzmgc;
        if (i != 0) {
            zzfhg.zzae(2, i);
        }
        this.zzpph.zza(zzfhg);
    }

    public final zzduu zzbos() {
        zzduu zzduu = this.zzmfz;
        return zzduu == null ? zzduu.zzbpa() : zzduu;
    }

    public final int zzhs() {
        int i = this.zzppi;
        if (i != -1) {
            return i;
        }
        zzduu zzduu = this.zzmfz;
        int i2 = 0;
        if (zzduu != null) {
            if (zzduu == null) {
                zzduu = zzduu.zzbpa();
            }
            i2 = 0 + zzfhg.zzc(1, (zzfjc) zzduu);
        }
        int i3 = this.zzmgc;
        if (i3 != 0) {
            i2 += zzfhg.zzah(2, i3);
        }
        int zzhs = i2 + this.zzpph.zzhs();
        this.zzppi = zzhs;
        return zzhs;
    }
}
