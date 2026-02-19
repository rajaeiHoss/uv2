package com.google.android.gms.internal;

import com.google.android.gms.internal.zzfhu;
import java.io.IOException;
import java.util.Objects;

public final class zzdve extends zzfhu<zzdve, zzdve.zza> implements zzfje {
    private static volatile zzfjl<zzdve> zzbbm;
    /* access modifiers changed from: private */
    public static final zzdve zzmgl;
    private int zzmgc;

    public static final class zza extends zzfhu.zza<zzdve, zzdve.zza> implements zzfje {
        private zza() {
            super(zzdve.zzmgl);
        }

        /* synthetic */ zza(zzdvf zzdvf) {
            this();
        }
    }

    static {
        zzdve zzdve = new zzdve();
        zzmgl = zzdve;
        zzdve.zza(zzfhu.zzg.zzppw, (Object) null, (Object) null);
        zzdve.zzpph.zzbkr();
    }

    private zzdve() {
    }

    public static zzdve zzt(zzfgs zzfgs) throws zzfie {
        return (zzdve) zzfhu.zza(zzmgl, zzfgs);
    }

    public final int getKeySize() {
        return this.zzmgc;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        boolean z = true;
        boolean z2 = false;
        switch (zzdvf.zzbbk[i - 1]) {
            case 1:
                return new zzdve();
            case 2:
                return zzmgl;
            case 3:
                return null;
            case 4:
                return new zza((zzdvf) null);
            case 5:
                zzfhu.zzh zzh = (zzfhu.zzh) obj;
                zzdve zzdve = (zzdve) obj2;
                int i2 = this.zzmgc;
                boolean z3 = i2 != 0;
                int i3 = zzdve.zzmgc;
                if (i3 == 0) {
                    z = false;
                }
                this.zzmgc = zzh.zza(z3, i2, z, i3);
                return this;
            case 6:
                zzfhb zzfhb = (zzfhb) obj;
                Objects.requireNonNull((zzfhm) obj2);
                while (!z2) {
                    try {
                        int zzcxx = zzfhb.zzcxx();
                        if (zzcxx != 0) {
                            if (zzcxx == 16) {
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
                    synchronized (zzdve.class) {
                        if (zzbbm == null) {
                            zzbbm = new zzfhu.zzb(zzmgl);
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
        return zzmgl;
    }

    public final void zza(zzfhg zzfhg) throws IOException {
        int i = this.zzmgc;
        if (i != 0) {
            zzfhg.zzae(2, i);
        }
        this.zzpph.zza(zzfhg);
    }

    public final int zzhs() {
        int i = this.zzppi;
        if (i != -1) {
            return i;
        }
        int i2 = this.zzmgc;
        int i3 = 0;
        if (i2 != 0) {
            i3 = 0 + zzfhg.zzah(2, i2);
        }
        int zzhs = i3 + this.zzpph.zzhs();
        this.zzppi = zzhs;
        return zzhs;
    }
}
