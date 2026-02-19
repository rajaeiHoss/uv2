package com.google.android.gms.internal;

import com.google.android.gms.internal.zzdvk;
import com.google.android.gms.internal.zzdvu;
import com.google.android.gms.internal.zzfhu;
import java.io.IOException;
import java.util.Objects;

public final class zzdvo extends zzfhu<zzdvo, zzdvo.zza> implements zzfje {
    private static volatile zzfjl<zzdvo> zzbbm;
    /* access modifiers changed from: private */
    public static final zzdvo zzmgz;
    private zzdvu zzmgw;
    private zzdvk zzmgx;
    private int zzmgy;

    public static final class zza extends zzfhu.zza<zzdvo, zzdvo.zza> implements zzfje {
        private zza() {
            super(zzdvo.zzmgz);
        }

        /* synthetic */ zza(zzdvp zzdvp) {
            this();
        }
    }

    static {
        zzdvo zzdvo = new zzdvo();
        zzmgz = zzdvo;
        zzdvo.zza(zzfhu.zzg.zzppw, (Object) null, (Object) null);
        zzdvo.zzpph.zzbkr();
    }

    private zzdvo() {
    }

    public static zzdvo zzbpv() {
        return zzmgz;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        zzdvu.zza zza2;
        zzdvk.zza zza3;
        boolean z = true;
        boolean z2 = false;
        switch (zzdvp.zzbbk[i - 1]) {
            case 1:
                return new zzdvo();
            case 2:
                return zzmgz;
            case 3:
                return null;
            case 4:
                return new zza((zzdvp) null);
            case 5:
                zzfhu.zzh zzh = (zzfhu.zzh) obj;
                zzdvo zzdvo = (zzdvo) obj2;
                this.zzmgw = (zzdvu) zzh.zza(this.zzmgw, zzdvo.zzmgw);
                this.zzmgx = (zzdvk) zzh.zza(this.zzmgx, zzdvo.zzmgx);
                int i2 = this.zzmgy;
                boolean z3 = i2 != 0;
                int i3 = zzdvo.zzmgy;
                if (i3 == 0) {
                    z = false;
                }
                this.zzmgy = zzh.zza(z3, i2, z, i3);
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
                                zzdvu zzdvu = this.zzmgw;
                                if (zzdvu != null) {
                                    zzfhu.zza zza4 = (zzfhu.zza) zzdvu.zza(zzfhu.zzg.zzppy, (Object) null, (Object) null);
                                    zza4.zza(zzdvu);
                                    zza2 = (zzdvu.zza) zza4;
                                } else {
                                    zza2 = null;
                                }
                                zzdvu zzdvu2 = (zzdvu) zzfhb.zza(zzdvu.zzbqi(), zzfhm);
                                this.zzmgw = zzdvu2;
                                if (zza2 != null) {
                                    zza2.zza(zzdvu2);
                                    this.zzmgw = (zzdvu) zza2.zzczw();
                                }
                            } else if (zzcxx == 18) {
                                zzdvk zzdvk = this.zzmgx;
                                if (zzdvk != null) {
                                    zzfhu.zza zza5 = (zzfhu.zza) zzdvk.zza(zzfhu.zzg.zzppy, (Object) null, (Object) null);
                                    zza5.zza(zzdvk);
                                    zza3 = (zzdvk.zza) zza5;
                                } else {
                                    zza3 = null;
                                }
                                zzdvk zzdvk2 = (zzdvk) zzfhb.zza(zzdvk.zzbpo(), zzfhm);
                                this.zzmgx = zzdvk2;
                                if (zza3 != null) {
                                    zza3.zza(zzdvk2);
                                    this.zzmgx = (zzdvk) zza3.zzczw();
                                }
                            } else if (zzcxx == 24) {
                                this.zzmgy = zzfhb.zzcyh();
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
                    synchronized (zzdvo.class) {
                        if (zzbbm == null) {
                            zzbbm = new zzfhu.zzb(zzmgz);
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
        return zzmgz;
    }

    public final void zza(zzfhg zzfhg) throws IOException {
        zzdvu zzdvu = this.zzmgw;
        if (zzdvu != null) {
            if (zzdvu == null) {
                zzdvu = zzdvu.zzbqi();
            }
            zzfhg.zza(1, (zzfjc) zzdvu);
        }
        zzdvk zzdvk = this.zzmgx;
        if (zzdvk != null) {
            if (zzdvk == null) {
                zzdvk = zzdvk.zzbpo();
            }
            zzfhg.zza(2, (zzfjc) zzdvk);
        }
        if (this.zzmgy != zzdvi.UNKNOWN_FORMAT.zzhu()) {
            zzfhg.zzad(3, this.zzmgy);
        }
        this.zzpph.zza(zzfhg);
    }

    public final zzdvu zzbps() {
        zzdvu zzdvu = this.zzmgw;
        return zzdvu == null ? zzdvu.zzbqi() : zzdvu;
    }

    public final zzdvk zzbpt() {
        zzdvk zzdvk = this.zzmgx;
        return zzdvk == null ? zzdvk.zzbpo() : zzdvk;
    }

    public final zzdvi zzbpu() {
        zzdvi zzgh = zzdvi.zzgh(this.zzmgy);
        return zzgh == null ? zzdvi.UNRECOGNIZED : zzgh;
    }

    public final int zzhs() {
        int i = this.zzppi;
        if (i != -1) {
            return i;
        }
        zzdvu zzdvu = this.zzmgw;
        int i2 = 0;
        if (zzdvu != null) {
            if (zzdvu == null) {
                zzdvu = zzdvu.zzbqi();
            }
            i2 = 0 + zzfhg.zzc(1, (zzfjc) zzdvu);
        }
        zzdvk zzdvk = this.zzmgx;
        if (zzdvk != null) {
            if (zzdvk == null) {
                zzdvk = zzdvk.zzbpo();
            }
            i2 += zzfhg.zzc(2, (zzfjc) zzdvk);
        }
        if (this.zzmgy != zzdvi.UNKNOWN_FORMAT.zzhu()) {
            i2 += zzfhg.zzaj(3, this.zzmgy);
        }
        int zzhs = i2 + this.zzpph.zzhs();
        this.zzppi = zzhs;
        return zzhs;
    }
}
