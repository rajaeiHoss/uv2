package com.google.android.gms.internal;

import com.google.android.gms.internal.zzdvo;
import com.google.android.gms.internal.zzfhu;
import java.io.IOException;
import java.util.Objects;

public final class zzdvm extends zzfhu<zzdvm, zzdvm.zza> implements zzfje {
    private static volatile zzfjl<zzdvm> zzbbm;
    /* access modifiers changed from: private */
    public static final zzdvm zzmgv;
    private zzdvo zzmgu;

    public static final class zza extends zzfhu.zza<zzdvm, zzdvm.zza> implements zzfje {
        private zza() {
            super(zzdvm.zzmgv);
        }

        /* synthetic */ zza(zzdvn zzdvn) {
            this();
        }
    }

    static {
        zzdvm zzdvm = new zzdvm();
        zzmgv = zzdvm;
        zzdvm.zza(zzfhu.zzg.zzppw, (Object) null, (Object) null);
        zzdvm.zzpph.zzbkr();
    }

    private zzdvm() {
    }

    public static zzdvm zzw(zzfgs zzfgs) throws zzfie {
        return (zzdvm) zzfhu.zza(zzmgv, zzfgs);
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        zzdvo.zza zza2;
        switch (zzdvn.zzbbk[i - 1]) {
            case 1:
                return new zzdvm();
            case 2:
                return zzmgv;
            case 3:
                return null;
            case 4:
                return new zza((zzdvn) null);
            case 5:
                this.zzmgu = (zzdvo) ((zzfhu.zzh) obj).zza(this.zzmgu, ((zzdvm) obj2).zzmgu);
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
                                zzdvo zzdvo = this.zzmgu;
                                if (zzdvo != null) {
                                    zzfhu.zza zza3 = (zzfhu.zza) zzdvo.zza(zzfhu.zzg.zzppy, (Object) null, (Object) null);
                                    zza3.zza(zzdvo);
                                    zza2 = (zzdvo.zza) zza3;
                                } else {
                                    zza2 = null;
                                }
                                zzdvo zzdvo2 = (zzdvo) zzfhb.zza(zzdvo.zzbpv(), zzfhm);
                                this.zzmgu = zzdvo2;
                                if (zza2 != null) {
                                    zza2.zza(zzdvo2);
                                    this.zzmgu = (zzdvo) zza2.zzczw();
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
                    synchronized (zzdvm.class) {
                        if (zzbbm == null) {
                            zzbbm = new zzfhu.zzb(zzmgv);
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
        return zzmgv;
    }

    public final void zza(zzfhg zzfhg) throws IOException {
        zzdvo zzdvo = this.zzmgu;
        if (zzdvo != null) {
            if (zzdvo == null) {
                zzdvo = zzdvo.zzbpv();
            }
            zzfhg.zza(1, (zzfjc) zzdvo);
        }
        this.zzpph.zza(zzfhg);
    }

    public final zzdvo zzbpq() {
        zzdvo zzdvo = this.zzmgu;
        return zzdvo == null ? zzdvo.zzbpv() : zzdvo;
    }

    public final int zzhs() {
        int i = this.zzppi;
        if (i != -1) {
            return i;
        }
        zzdvo zzdvo = this.zzmgu;
        int i2 = 0;
        if (zzdvo != null) {
            if (zzdvo == null) {
                zzdvo = zzdvo.zzbpv();
            }
            i2 = 0 + zzfhg.zzc(1, (zzfjc) zzdvo);
        }
        int zzhs = i2 + this.zzpph.zzhs();
        this.zzppi = zzhs;
        return zzhs;
    }
}
