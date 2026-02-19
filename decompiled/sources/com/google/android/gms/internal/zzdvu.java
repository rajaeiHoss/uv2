package com.google.android.gms.internal;

import com.google.android.gms.internal.zzfhu;
import java.io.IOException;
import java.util.Objects;

public final class zzdvu extends zzfhu<zzdvu, zzdvu.zza> implements zzfje {
    private static volatile zzfjl<zzdvu> zzbbm;
    /* access modifiers changed from: private */
    public static final zzdvu zzmhi;
    private int zzmhf;
    private int zzmhg;
    private zzfgs zzmhh = zzfgs.zzpnw;

    public static final class zza extends zzfhu.zza<zzdvu, zzdvu.zza> implements zzfje {
        private zza() {
            super(zzdvu.zzmhi);
        }

        /* synthetic */ zza(zzdvv zzdvv) {
            this();
        }
    }

    static {
        zzdvu zzdvu = new zzdvu();
        zzmhi = zzdvu;
        zzdvu.zza(zzfhu.zzg.zzppw, (Object) null, (Object) null);
        zzdvu.zzpph.zzbkr();
    }

    private zzdvu() {
    }

    public static zzdvu zzbqi() {
        return zzmhi;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        boolean z = true;
        boolean z2 = false;
        switch (zzdvv.zzbbk[i - 1]) {
            case 1:
                return new zzdvu();
            case 2:
                return zzmhi;
            case 3:
                return null;
            case 4:
                return new zza((zzdvv) null);
            case 5:
                zzfhu.zzh zzh = (zzfhu.zzh) obj;
                zzdvu zzdvu = (zzdvu) obj2;
                int i2 = this.zzmhf;
                boolean z3 = i2 != 0;
                int i3 = zzdvu.zzmhf;
                this.zzmhf = zzh.zza(z3, i2, i3 != 0, i3);
                int i4 = this.zzmhg;
                boolean z4 = i4 != 0;
                int i5 = zzdvu.zzmhg;
                this.zzmhg = zzh.zza(z4, i4, i5 != 0, i5);
                boolean z5 = this.zzmhh != zzfgs.zzpnw;
                zzfgs zzfgs = this.zzmhh;
                if (zzdvu.zzmhh == zzfgs.zzpnw) {
                    z = false;
                }
                this.zzmhh = zzh.zza(z5, zzfgs, z, zzdvu.zzmhh);
                return this;
            case 6:
                zzfhb zzfhb = (zzfhb) obj;
                Objects.requireNonNull((zzfhm) obj2);
                while (!z2) {
                    try {
                        int zzcxx = zzfhb.zzcxx();
                        if (zzcxx != 0) {
                            if (zzcxx == 8) {
                                this.zzmhf = zzfhb.zzcyh();
                            } else if (zzcxx == 16) {
                                this.zzmhg = zzfhb.zzcyh();
                            } else if (zzcxx == 90) {
                                this.zzmhh = zzfhb.zzcyf();
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
                    synchronized (zzdvu.class) {
                        if (zzbbm == null) {
                            zzbbm = new zzfhu.zzb(zzmhi);
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
        return zzmhi;
    }

    public final void zza(zzfhg zzfhg) throws IOException {
        if (this.zzmhf != zzdvw.UNKNOWN_CURVE.zzhu()) {
            zzfhg.zzad(1, this.zzmhf);
        }
        if (this.zzmhg != zzdvy.UNKNOWN_HASH.zzhu()) {
            zzfhg.zzad(2, this.zzmhg);
        }
        if (!this.zzmhh.isEmpty()) {
            zzfhg.zza(11, this.zzmhh);
        }
        this.zzpph.zza(zzfhg);
    }

    public final zzdvw zzbqf() {
        zzdvw zzgk = zzdvw.zzgk(this.zzmhf);
        return zzgk == null ? zzdvw.UNRECOGNIZED : zzgk;
    }

    public final zzdvy zzbqg() {
        zzdvy zzgl = zzdvy.zzgl(this.zzmhg);
        return zzgl == null ? zzdvy.UNRECOGNIZED : zzgl;
    }

    public final zzfgs zzbqh() {
        return this.zzmhh;
    }

    public final int zzhs() {
        int i = this.zzppi;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (this.zzmhf != zzdvw.UNKNOWN_CURVE.zzhu()) {
            i2 = 0 + zzfhg.zzaj(1, this.zzmhf);
        }
        if (this.zzmhg != zzdvy.UNKNOWN_HASH.zzhu()) {
            i2 += zzfhg.zzaj(2, this.zzmhg);
        }
        if (!this.zzmhh.isEmpty()) {
            i2 += zzfhg.zzc(11, this.zzmhh);
        }
        int zzhs = i2 + this.zzpph.zzhs();
        this.zzppi = zzhs;
        return zzhs;
    }
}
