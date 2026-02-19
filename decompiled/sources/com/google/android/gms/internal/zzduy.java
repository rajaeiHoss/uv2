package com.google.android.gms.internal;

import com.google.android.gms.internal.zzdva;
import com.google.android.gms.internal.zzfhu;
import java.io.IOException;
import java.util.Objects;

public final class zzduy extends zzfhu<zzduy, zzduy.zza> implements zzfje {
    private static volatile zzfjl<zzduy> zzbbm;
    /* access modifiers changed from: private */
    public static final zzduy zzmgi;
    private int zzmgc;
    private zzdva zzmgg;

    public static final class zza extends zzfhu.zza<zzduy, zzduy.zza> implements zzfje {
        private zza() {
            super(zzduy.zzmgi);
        }

        /* synthetic */ zza(zzduz zzduz) {
            this();
        }
    }

    static {
        zzduy zzduy = new zzduy();
        zzmgi = zzduy;
        zzduy.zza(zzfhu.zzg.zzppw, (Object) null, (Object) null);
        zzduy.zzpph.zzbkr();
    }

    private zzduy() {
    }

    public static zzduy zzq(zzfgs zzfgs) throws zzfie {
        return (zzduy) zzfhu.zza(zzmgi, zzfgs);
    }

    public final int getKeySize() {
        return this.zzmgc;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        zzdva.zza zza2;
        boolean z = true;
        boolean z2 = false;
        switch (zzduz.zzbbk[i - 1]) {
            case 1:
                return new zzduy();
            case 2:
                return zzmgi;
            case 3:
                return null;
            case 4:
                return new zza((zzduz) null);
            case 5:
                zzfhu.zzh zzh = (zzfhu.zzh) obj;
                zzduy zzduy = (zzduy) obj2;
                this.zzmgg = (zzdva) zzh.zza(this.zzmgg, zzduy.zzmgg);
                int i2 = this.zzmgc;
                boolean z3 = i2 != 0;
                int i3 = zzduy.zzmgc;
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
                    synchronized (zzduy.class) {
                        if (zzbbm == null) {
                            zzbbm = new zzfhu.zzb(zzmgi);
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
        return zzmgi;
    }

    public final void zza(zzfhg zzfhg) throws IOException {
        zzdva zzdva = this.zzmgg;
        if (zzdva != null) {
            if (zzdva == null) {
                zzdva = zzdva.zzbpg();
            }
            zzfhg.zza(1, (zzfjc) zzdva);
        }
        int i = this.zzmgc;
        if (i != 0) {
            zzfhg.zzae(2, i);
        }
        this.zzpph.zza(zzfhg);
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
        zzdva zzdva = this.zzmgg;
        int i2 = 0;
        if (zzdva != null) {
            if (zzdva == null) {
                zzdva = zzdva.zzbpg();
            }
            i2 = 0 + zzfhg.zzc(1, (zzfjc) zzdva);
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
