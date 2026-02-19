package com.google.android.gms.internal;

import com.google.android.gms.internal.zzfhu;
import java.io.IOException;
import java.util.Objects;

public final class zzdwv extends zzfhu<zzdwv, zzdwv.zza> implements zzfje {
    private static volatile zzfjl<zzdwv> zzbbm;
    /* access modifiers changed from: private */
    public static final zzdwv zzmjp;
    private String zzmjo = "";

    public static final class zza extends zzfhu.zza<zzdwv, zzdwv.zza> implements zzfje {
        private zza() {
            super(zzdwv.zzmjp);
        }

        /* synthetic */ zza(zzdww zzdww) {
            this();
        }
    }

    static {
        zzdwv zzdwv = new zzdwv();
        zzmjp = zzdwv;
        zzdwv.zza(zzfhu.zzg.zzppw, (Object) null, (Object) null);
        zzdwv.zzpph.zzbkr();
    }

    private zzdwv() {
    }

    public static zzdwv zzak(zzfgs zzfgs) throws zzfie {
        return (zzdwv) zzfhu.zza(zzmjp, zzfgs);
    }

    public static zzdwv zzbsd() {
        return zzmjp;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzdww.zzbbk[i - 1]) {
            case 1:
                return new zzdwv();
            case 2:
                return zzmjp;
            case 3:
                return null;
            case 4:
                return new zza((zzdww) null);
            case 5:
                zzdwv zzdwv = (zzdwv) obj2;
                this.zzmjo = ((zzfhu.zzh) obj).zza(!this.zzmjo.isEmpty(), this.zzmjo, true ^ zzdwv.zzmjo.isEmpty(), zzdwv.zzmjo);
                return this;
            case 6:
                zzfhb zzfhb = (zzfhb) obj;
                Objects.requireNonNull((zzfhm) obj2);
                boolean z = false;
                while (!z) {
                    try {
                        int zzcxx = zzfhb.zzcxx();
                        if (zzcxx != 0) {
                            if (zzcxx == 10) {
                                this.zzmjo = zzfhb.zzcye();
                            } else if (!zza(zzcxx, zzfhb)) {
                            }
                        }
                        z = true;
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
                    synchronized (zzdwv.class) {
                        if (zzbbm == null) {
                            zzbbm = new zzfhu.zzb(zzmjp);
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
        return zzmjp;
    }

    public final void zza(zzfhg zzfhg) throws IOException {
        if (!this.zzmjo.isEmpty()) {
            zzfhg.zzp(1, this.zzmjo);
        }
        this.zzpph.zza(zzfhg);
    }

    public final String zzbsc() {
        return this.zzmjo;
    }

    public final int zzhs() {
        int i = this.zzppi;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!this.zzmjo.isEmpty()) {
            i2 = 0 + zzfhg.zzq(1, this.zzmjo);
        }
        int zzhs = i2 + this.zzpph.zzhs();
        this.zzppi = zzhs;
        return zzhs;
    }
}
