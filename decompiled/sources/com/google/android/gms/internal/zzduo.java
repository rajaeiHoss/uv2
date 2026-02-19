package com.google.android.gms.internal;

import com.google.android.gms.internal.zzdus;
import com.google.android.gms.internal.zzdwc;
import com.google.android.gms.internal.zzfhu;
import java.io.IOException;
import java.util.Objects;

public final class zzduo extends zzfhu<zzduo, zzduo.zza> implements zzfje {
    private static volatile zzfjl<zzduo> zzbbm;
    /* access modifiers changed from: private */
    public static final zzduo zzmfy;
    private zzdus zzmfw;
    private zzdwc zzmfx;

    public static final class zza extends zzfhu.zza<zzduo, zzduo.zza> implements zzfje {
        private zza() {
            super(zzduo.zzmfy);
        }

        /* synthetic */ zza(zzdup zzdup) {
            this();
        }
    }

    static {
        zzduo zzduo = new zzduo();
        zzmfy = zzduo;
        zzduo.zza(zzfhu.zzg.zzppw, (Object) null, (Object) null);
        zzduo.zzpph.zzbkr();
    }

    private zzduo() {
    }

    public static zzduo zzj(zzfgs zzfgs) throws zzfie {
        return (zzduo) zzfhu.zza(zzmfy, zzfgs);
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        zzdus.zza zza2;
        zzdwc.zza zza3;
        switch (zzdup.zzbbk[i - 1]) {
            case 1:
                return new zzduo();
            case 2:
                return zzmfy;
            case 3:
                return null;
            case 4:
                return new zza((zzdup) null);
            case 5:
                zzfhu.zzh zzh = (zzfhu.zzh) obj;
                zzduo zzduo = (zzduo) obj2;
                this.zzmfw = (zzdus) zzh.zza(this.zzmfw, zzduo.zzmfw);
                this.zzmfx = (zzdwc) zzh.zza(this.zzmfx, zzduo.zzmfx);
                return this;
            case 6:
                zzfhb zzfhb = (zzfhb) obj;
                zzfhm zzfhm = (zzfhm) obj2;
                Objects.requireNonNull(zzfhm);
                boolean z = false;
                while (!z) {
                    try {
                        int zzcxx = zzfhb.zzcxx();
                        if (zzcxx != 0) {
                            if (zzcxx == 10) {
                                zzdus zzdus = this.zzmfw;
                                if (zzdus != null) {
                                    zzfhu.zza zza4 = (zzfhu.zza) zzdus.zza(zzfhu.zzg.zzppy, (Object) null, (Object) null);
                                    zza4.zza(zzdus);
                                    zza2 = (zzdus.zza) zza4;
                                } else {
                                    zza2 = null;
                                }
                                zzdus zzdus2 = (zzdus) zzfhb.zza(zzdus.zzbox(), zzfhm);
                                this.zzmfw = zzdus2;
                                if (zza2 != null) {
                                    zza2.zza(zzdus2);
                                    this.zzmfw = (zzdus) zza2.zzczw();
                                }
                            } else if (zzcxx == 18) {
                                zzdwc zzdwc = this.zzmfx;
                                if (zzdwc != null) {
                                    zzfhu.zza zza5 = (zzfhu.zza) zzdwc.zza(zzfhu.zzg.zzppy, (Object) null, (Object) null);
                                    zza5.zza(zzdwc);
                                    zza3 = (zzdwc.zza) zza5;
                                } else {
                                    zza3 = null;
                                }
                                zzdwc zzdwc2 = (zzdwc) zzfhb.zza(zzdwc.zzbqo(), zzfhm);
                                this.zzmfx = zzdwc2;
                                if (zza3 != null) {
                                    zza3.zza(zzdwc2);
                                    this.zzmfx = (zzdwc) zza3.zzczw();
                                }
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
                    synchronized (zzduo.class) {
                        if (zzbbm == null) {
                            zzbbm = new zzfhu.zzb(zzmfy);
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
        return zzmfy;
    }

    public final void zza(zzfhg zzfhg) throws IOException {
        zzdus zzdus = this.zzmfw;
        if (zzdus != null) {
            if (zzdus == null) {
                zzdus = zzdus.zzbox();
            }
            zzfhg.zza(1, (zzfjc) zzdus);
        }
        zzdwc zzdwc = this.zzmfx;
        if (zzdwc != null) {
            if (zzdwc == null) {
                zzdwc = zzdwc.zzbqo();
            }
            zzfhg.zza(2, (zzfjc) zzdwc);
        }
        this.zzpph.zza(zzfhg);
    }

    public final zzdus zzbop() {
        zzdus zzdus = this.zzmfw;
        return zzdus == null ? zzdus.zzbox() : zzdus;
    }

    public final zzdwc zzboq() {
        zzdwc zzdwc = this.zzmfx;
        return zzdwc == null ? zzdwc.zzbqo() : zzdwc;
    }

    public final int zzhs() {
        int i = this.zzppi;
        if (i != -1) {
            return i;
        }
        zzdus zzdus = this.zzmfw;
        int i2 = 0;
        if (zzdus != null) {
            if (zzdus == null) {
                zzdus = zzdus.zzbox();
            }
            i2 = 0 + zzfhg.zzc(1, (zzfjc) zzdus);
        }
        zzdwc zzdwc = this.zzmfx;
        if (zzdwc != null) {
            if (zzdwc == null) {
                zzdwc = zzdwc.zzbqo();
            }
            i2 += zzfhg.zzc(2, (zzfjc) zzdwc);
        }
        int zzhs = i2 + this.zzpph.zzhs();
        this.zzppi = zzhs;
        return zzhs;
    }
}
