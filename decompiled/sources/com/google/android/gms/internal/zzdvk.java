package com.google.android.gms.internal;

import com.google.android.gms.internal.zzdwl;
import com.google.android.gms.internal.zzfhu;
import java.io.IOException;
import java.util.Objects;

public final class zzdvk extends zzfhu<zzdvk, zzdvk.zza> implements zzfje {
    private static volatile zzfjl<zzdvk> zzbbm;
    /* access modifiers changed from: private */
    public static final zzdvk zzmgt;
    private zzdwl zzmgs;

    public static final class zza extends zzfhu.zza<zzdvk, zzdvk.zza> implements zzfje {
        private zza() {
            super(zzdvk.zzmgt);
        }

        /* synthetic */ zza(zzdvl zzdvl) {
            this();
        }
    }

    static {
        zzdvk zzdvk = new zzdvk();
        zzmgt = zzdvk;
        zzdvk.zza(zzfhu.zzg.zzppw, (Object) null, (Object) null);
        zzdvk.zzpph.zzbkr();
    }

    private zzdvk() {
    }

    public static zzdvk zzbpo() {
        return zzmgt;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        zzdwl.zza zza2;
        switch (zzdvl.zzbbk[i - 1]) {
            case 1:
                return new zzdvk();
            case 2:
                return zzmgt;
            case 3:
                return null;
            case 4:
                return new zza((zzdvl) null);
            case 5:
                this.zzmgs = (zzdwl) ((zzfhu.zzh) obj).zza(this.zzmgs, ((zzdvk) obj2).zzmgs);
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
                            if (zzcxx == 18) {
                                zzdwl zzdwl = this.zzmgs;
                                if (zzdwl != null) {
                                    zzfhu.zza zza3 = (zzfhu.zza) zzdwl.zza(zzfhu.zzg.zzppy, (Object) null, (Object) null);
                                    zza3.zza(zzdwl);
                                    zza2 = (zzdwl.zza) zza3;
                                } else {
                                    zza2 = null;
                                }
                                zzdwl zzdwl2 = (zzdwl) zzfhb.zza(zzdwl.zzbra(), zzfhm);
                                this.zzmgs = zzdwl2;
                                if (zza2 != null) {
                                    zza2.zza(zzdwl2);
                                    this.zzmgs = (zzdwl) zza2.zzczw();
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
                    synchronized (zzdvk.class) {
                        if (zzbbm == null) {
                            zzbbm = new zzfhu.zzb(zzmgt);
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
        return zzmgt;
    }

    public final void zza(zzfhg zzfhg) throws IOException {
        zzdwl zzdwl = this.zzmgs;
        if (zzdwl != null) {
            if (zzdwl == null) {
                zzdwl = zzdwl.zzbra();
            }
            zzfhg.zza(2, (zzfjc) zzdwl);
        }
        this.zzpph.zza(zzfhg);
    }

    public final zzdwl zzbpn() {
        zzdwl zzdwl = this.zzmgs;
        return zzdwl == null ? zzdwl.zzbra() : zzdwl;
    }

    public final int zzhs() {
        int i = this.zzppi;
        if (i != -1) {
            return i;
        }
        zzdwl zzdwl = this.zzmgs;
        int i2 = 0;
        if (zzdwl != null) {
            if (zzdwl == null) {
                zzdwl = zzdwl.zzbra();
            }
            i2 = 0 + zzfhg.zzc(2, (zzfjc) zzdwl);
        }
        int zzhs = i2 + this.zzpph.zzhs();
        this.zzppi = zzhs;
        return zzhs;
    }
}
