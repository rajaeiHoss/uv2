package com.google.android.gms.internal;

import com.google.android.gms.internal.zzfhu;
import java.io.IOException;
import java.util.Objects;

public final class zzdwe extends zzfhu<zzdwe, zzdwe.zza> implements zzfje {
    private static volatile zzfjl<zzdwe> zzbbm;
    /* access modifiers changed from: private */
    public static final zzdwe zzmic;
    private int zzmia;
    private int zzmib;

    public static final class zza extends zzfhu.zza<zzdwe, zzdwe.zza> implements zzfje {
        private zza() {
            super(zzdwe.zzmic);
        }

        /* synthetic */ zza(zzdwf zzdwf) {
            this();
        }
    }

    static {
        zzdwe zzdwe = new zzdwe();
        zzmic = zzdwe;
        zzdwe.zza(zzfhu.zzg.zzppw, (Object) null, (Object) null);
        zzdwe.zzpph.zzbkr();
    }

    private zzdwe() {
    }

    public static zzdwe zzbqs() {
        return zzmic;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        boolean z = true;
        boolean z2 = false;
        switch (zzdwf.zzbbk[i - 1]) {
            case 1:
                return new zzdwe();
            case 2:
                return zzmic;
            case 3:
                return null;
            case 4:
                return new zza((zzdwf) null);
            case 5:
                zzfhu.zzh zzh = (zzfhu.zzh) obj;
                zzdwe zzdwe = (zzdwe) obj2;
                int i2 = this.zzmia;
                boolean z3 = i2 != 0;
                int i3 = zzdwe.zzmia;
                this.zzmia = zzh.zza(z3, i2, i3 != 0, i3);
                int i4 = this.zzmib;
                boolean z4 = i4 != 0;
                int i5 = zzdwe.zzmib;
                if (i5 == 0) {
                    z = false;
                }
                this.zzmib = zzh.zza(z4, i4, z, i5);
                return this;
            case 6:
                zzfhb zzfhb = (zzfhb) obj;
                Objects.requireNonNull((zzfhm) obj2);
                while (!z2) {
                    try {
                        int zzcxx = zzfhb.zzcxx();
                        if (zzcxx != 0) {
                            if (zzcxx == 8) {
                                this.zzmia = zzfhb.zzcyh();
                            } else if (zzcxx == 16) {
                                this.zzmib = zzfhb.zzcyg();
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
                    synchronized (zzdwe.class) {
                        if (zzbbm == null) {
                            zzbbm = new zzfhu.zzb(zzmic);
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
        return zzmic;
    }

    public final void zza(zzfhg zzfhg) throws IOException {
        if (this.zzmia != zzdvy.UNKNOWN_HASH.zzhu()) {
            zzfhg.zzad(1, this.zzmia);
        }
        int i = this.zzmib;
        if (i != 0) {
            zzfhg.zzae(2, i);
        }
        this.zzpph.zza(zzfhg);
    }

    public final zzdvy zzbqq() {
        zzdvy zzgl = zzdvy.zzgl(this.zzmia);
        return zzgl == null ? zzdvy.UNRECOGNIZED : zzgl;
    }

    public final int zzbqr() {
        return this.zzmib;
    }

    public final int zzhs() {
        int i = this.zzppi;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (this.zzmia != zzdvy.UNKNOWN_HASH.zzhu()) {
            i2 = 0 + zzfhg.zzaj(1, this.zzmia);
        }
        int i3 = this.zzmib;
        if (i3 != 0) {
            i2 += zzfhg.zzah(2, i3);
        }
        int zzhs = i2 + this.zzpph.zzhs();
        this.zzppi = zzhs;
        return zzhs;
    }
}
