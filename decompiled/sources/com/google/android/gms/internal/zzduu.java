package com.google.android.gms.internal;

import com.google.android.gms.internal.zzfhu;
import java.io.IOException;
import java.util.Objects;

public final class zzduu extends zzfhu<zzduu, zzduu.zza> implements zzfje {
    private static volatile zzfjl<zzduu> zzbbm;
    /* access modifiers changed from: private */
    public static final zzduu zzmgf;
    private int zzmge;

    public static final class zza extends zzfhu.zza<zzduu, zzduu.zza> implements zzfje {
        private zza() {
            super(zzduu.zzmgf);
        }

        /* synthetic */ zza(zzduv zzduv) {
            this();
        }
    }

    static {
        zzduu zzduu = new zzduu();
        zzmgf = zzduu;
        zzduu.zza(zzfhu.zzg.zzppw, (Object) null, (Object) null);
        zzduu.zzpph.zzbkr();
    }

    private zzduu() {
    }

    public static zzduu zzbpa() {
        return zzmgf;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        boolean z = true;
        boolean z2 = false;
        switch (zzduv.zzbbk[i - 1]) {
            case 1:
                return new zzduu();
            case 2:
                return zzmgf;
            case 3:
                return null;
            case 4:
                return new zza((zzduv) null);
            case 5:
                zzfhu.zzh zzh = (zzfhu.zzh) obj;
                zzduu zzduu = (zzduu) obj2;
                int i2 = this.zzmge;
                boolean z3 = i2 != 0;
                int i3 = zzduu.zzmge;
                if (i3 == 0) {
                    z = false;
                }
                this.zzmge = zzh.zza(z3, i2, z, i3);
                return this;
            case 6:
                zzfhb zzfhb = (zzfhb) obj;
                Objects.requireNonNull((zzfhm) obj2);
                while (!z2) {
                    try {
                        int zzcxx = zzfhb.zzcxx();
                        if (zzcxx != 0) {
                            if (zzcxx == 8) {
                                this.zzmge = zzfhb.zzcyg();
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
                    synchronized (zzduu.class) {
                        if (zzbbm == null) {
                            zzbbm = new zzfhu.zzb(zzmgf);
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
        return zzmgf;
    }

    public final void zza(zzfhg zzfhg) throws IOException {
        int i = this.zzmge;
        if (i != 0) {
            zzfhg.zzae(1, i);
        }
        this.zzpph.zza(zzfhg);
    }

    public final int zzboz() {
        return this.zzmge;
    }

    public final int zzhs() {
        int i = this.zzppi;
        if (i != -1) {
            return i;
        }
        int i2 = this.zzmge;
        int i3 = 0;
        if (i2 != 0) {
            i3 = 0 + zzfhg.zzah(1, i2);
        }
        int zzhs = i3 + this.zzpph.zzhs();
        this.zzppi = zzhs;
        return zzhs;
    }
}
