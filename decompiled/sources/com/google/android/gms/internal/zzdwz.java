package com.google.android.gms.internal;

import com.google.android.gms.internal.zzdwl;
import com.google.android.gms.internal.zzfhu;
import java.io.IOException;
import java.util.Objects;

public final class zzdwz extends zzfhu<zzdwz, zzdwz.zza> implements zzfje {
    private static volatile zzfjl<zzdwz> zzbbm;
    /* access modifiers changed from: private */
    public static final zzdwz zzmju;
    private String zzmjs = "";
    private zzdwl zzmjt;

    public static final class zza extends zzfhu.zza<zzdwz, zzdwz.zza> implements zzfje {
        private zza() {
            super(zzdwz.zzmju);
        }

        /* synthetic */ zza(zzdxa zzdxa) {
            this();
        }
    }

    static {
        zzdwz zzdwz = new zzdwz();
        zzmju = zzdwz;
        zzdwz.zza(zzfhu.zzg.zzppw, (Object) null, (Object) null);
        zzdwz.zzpph.zzbkr();
    }

    private zzdwz() {
    }

    public static zzdwz zzam(zzfgs zzfgs) throws zzfie {
        return (zzdwz) zzfhu.zza(zzmju, zzfgs);
    }

    public static zzdwz zzbsk() {
        return zzmju;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        zzdwl.zza zza2;
        switch (zzdxa.zzbbk[i - 1]) {
            case 1:
                return new zzdwz();
            case 2:
                return zzmju;
            case 3:
                return null;
            case 4:
                return new zza((zzdxa) null);
            case 5:
                zzfhu.zzh zzh = (zzfhu.zzh) obj;
                zzdwz zzdwz = (zzdwz) obj2;
                this.zzmjs = zzh.zza(!this.zzmjs.isEmpty(), this.zzmjs, true ^ zzdwz.zzmjs.isEmpty(), zzdwz.zzmjs);
                this.zzmjt = (zzdwl) zzh.zza(this.zzmjt, zzdwz.zzmjt);
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
                                this.zzmjs = zzfhb.zzcye();
                            } else if (zzcxx == 18) {
                                zzdwl zzdwl = this.zzmjt;
                                if (zzdwl != null) {
                                    zzfhu.zza zza3 = (zzfhu.zza) zzdwl.zza(zzfhu.zzg.zzppy, (Object) null, (Object) null);
                                    zza3.zza(zzdwl);
                                    zza2 = (zzdwl.zza) zza3;
                                } else {
                                    zza2 = null;
                                }
                                zzdwl zzdwl2 = (zzdwl) zzfhb.zza(zzdwl.zzbra(), zzfhm);
                                this.zzmjt = zzdwl2;
                                if (zza2 != null) {
                                    zza2.zza(zzdwl2);
                                    this.zzmjt = (zzdwl) zza2.zzczw();
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
                    synchronized (zzdwz.class) {
                        if (zzbbm == null) {
                            zzbbm = new zzfhu.zzb(zzmju);
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
        return zzmju;
    }

    public final void zza(zzfhg zzfhg) throws IOException {
        if (!this.zzmjs.isEmpty()) {
            zzfhg.zzp(1, this.zzmjs);
        }
        zzdwl zzdwl = this.zzmjt;
        if (zzdwl != null) {
            if (zzdwl == null) {
                zzdwl = zzdwl.zzbra();
            }
            zzfhg.zza(2, (zzfjc) zzdwl);
        }
        this.zzpph.zza(zzfhg);
    }

    public final String zzbsi() {
        return this.zzmjs;
    }

    public final zzdwl zzbsj() {
        zzdwl zzdwl = this.zzmjt;
        return zzdwl == null ? zzdwl.zzbra() : zzdwl;
    }

    public final int zzhs() {
        int i = this.zzppi;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!this.zzmjs.isEmpty()) {
            i2 = 0 + zzfhg.zzq(1, this.zzmjs);
        }
        zzdwl zzdwl = this.zzmjt;
        if (zzdwl != null) {
            if (zzdwl == null) {
                zzdwl = zzdwl.zzbra();
            }
            i2 += zzfhg.zzc(2, (zzfjc) zzdwl);
        }
        int zzhs = i2 + this.zzpph.zzhs();
        this.zzppi = zzhs;
        return zzhs;
    }
}
