package com.google.android.gms.internal;

import com.google.android.gms.internal.zzdwe;
import com.google.android.gms.internal.zzfhu;
import java.io.IOException;
import java.util.Objects;

public final class zzdwc extends zzfhu<zzdwc, zzdwc.zza> implements zzfje {
    private static volatile zzfjl<zzdwc> zzbbm;
    /* access modifiers changed from: private */
    public static final zzdwc zzmhz;
    private int zzmgc;
    private zzdwe zzmhx;

    public static final class zza extends zzfhu.zza<zzdwc, zzdwc.zza> implements zzfje {
        private zza() {
            super(zzdwc.zzmhz);
        }

        /* synthetic */ zza(zzdwd zzdwd) {
            this();
        }
    }

    static {
        zzdwc zzdwc = new zzdwc();
        zzmhz = zzdwc;
        zzdwc.zza(zzfhu.zzg.zzppw, (Object) null, (Object) null);
        zzdwc.zzpph.zzbkr();
    }

    private zzdwc() {
    }

    public static zzdwc zzag(zzfgs zzfgs) throws zzfie {
        return (zzdwc) zzfhu.zza(zzmhz, zzfgs);
    }

    public static zzdwc zzbqo() {
        return zzmhz;
    }

    public final int getKeySize() {
        return this.zzmgc;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        zzdwe.zza zza2;
        boolean z = true;
        boolean z2 = false;
        switch (zzdwd.zzbbk[i - 1]) {
            case 1:
                return new zzdwc();
            case 2:
                return zzmhz;
            case 3:
                return null;
            case 4:
                return new zza((zzdwd) null);
            case 5:
                zzfhu.zzh zzh = (zzfhu.zzh) obj;
                zzdwc zzdwc = (zzdwc) obj2;
                this.zzmhx = (zzdwe) zzh.zza(this.zzmhx, zzdwc.zzmhx);
                int i2 = this.zzmgc;
                boolean z3 = i2 != 0;
                int i3 = zzdwc.zzmgc;
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
                    synchronized (zzdwc.class) {
                        if (zzbbm == null) {
                            zzbbm = new zzfhu.zzb(zzmhz);
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
        return zzmhz;
    }

    public final void zza(zzfhg zzfhg) throws IOException {
        zzdwe zzdwe = this.zzmhx;
        if (zzdwe != null) {
            if (zzdwe == null) {
                zzdwe = zzdwe.zzbqs();
            }
            zzfhg.zza(1, (zzfjc) zzdwe);
        }
        int i = this.zzmgc;
        if (i != 0) {
            zzfhg.zzae(2, i);
        }
        this.zzpph.zza(zzfhg);
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
        zzdwe zzdwe = this.zzmhx;
        int i2 = 0;
        if (zzdwe != null) {
            if (zzdwe == null) {
                zzdwe = zzdwe.zzbqs();
            }
            i2 = 0 + zzfhg.zzc(1, (zzfjc) zzdwe);
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
