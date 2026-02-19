package com.google.android.gms.internal;

import com.google.android.gms.internal.zzdwv;
import com.google.android.gms.internal.zzfhu;
import java.io.IOException;
import java.util.Objects;

public final class zzdwt extends zzfhu<zzdwt, zzdwt.zza> implements zzfje {
    private static volatile zzfjl<zzdwt> zzbbm;
    /* access modifiers changed from: private */
    public static final zzdwt zzmjn;
    private int zzmfs;
    private zzdwv zzmjm;

    public static final class zza extends zzfhu.zza<zzdwt, zzdwt.zza> implements zzfje {
        private zza() {
            super(zzdwt.zzmjn);
        }

        /* synthetic */ zza(zzdwu zzdwu) {
            this();
        }

        public final zza zzb(zzdwv zzdwv) {
            zzczv();
            ((zzdwt) this.zzppl).zza(zzdwv);
            return this;
        }

        public final zza zzgv(int i) {
            zzczv();
            ((zzdwt) this.zzppl).setVersion(0);
            return this;
        }
    }

    static {
        zzdwt zzdwt = new zzdwt();
        zzmjn = zzdwt;
        zzdwt.zza(zzfhu.zzg.zzppw, (Object) null, (Object) null);
        zzdwt.zzpph.zzbkr();
    }

    private zzdwt() {
    }

    /* access modifiers changed from: private */
    public final void setVersion(int i) {
        this.zzmfs = i;
    }

    /* access modifiers changed from: private */
    public final void zza(zzdwv zzdwv) {
        Objects.requireNonNull(zzdwv);
        this.zzmjm = zzdwv;
    }

    public static zzdwt zzaj(zzfgs zzfgs) throws zzfie {
        return (zzdwt) zzfhu.zza(zzmjn, zzfgs);
    }

    public static zza zzbsa() {
        return (zza) ((zzfhu.zza) zzmjn.zza(zzfhu.zzg.zzppy, (Object) null, (Object) null));
    }

    public final int getVersion() {
        return this.zzmfs;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        zzdwv.zza zza2;
        boolean z = true;
        boolean z2 = false;
        switch (zzdwu.zzbbk[i - 1]) {
            case 1:
                return new zzdwt();
            case 2:
                return zzmjn;
            case 3:
                return null;
            case 4:
                return new zza((zzdwu) null);
            case 5:
                zzfhu.zzh zzh = (zzfhu.zzh) obj;
                zzdwt zzdwt = (zzdwt) obj2;
                int i2 = this.zzmfs;
                boolean z3 = i2 != 0;
                int i3 = zzdwt.zzmfs;
                if (i3 == 0) {
                    z = false;
                }
                this.zzmfs = zzh.zza(z3, i2, z, i3);
                this.zzmjm = (zzdwv) zzh.zza(this.zzmjm, zzdwt.zzmjm);
                return this;
            case 6:
                zzfhb zzfhb = (zzfhb) obj;
                zzfhm zzfhm = (zzfhm) obj2;
                Objects.requireNonNull(zzfhm);
                while (!z2) {
                    try {
                        int zzcxx = zzfhb.zzcxx();
                        if (zzcxx != 0) {
                            if (zzcxx == 8) {
                                this.zzmfs = zzfhb.zzcyg();
                            } else if (zzcxx == 18) {
                                zzdwv zzdwv = this.zzmjm;
                                if (zzdwv != null) {
                                    zzfhu.zza zza3 = (zzfhu.zza) zzdwv.zza(zzfhu.zzg.zzppy, (Object) null, (Object) null);
                                    zza3.zza(zzdwv);
                                    zza2 = (zzdwv.zza) zza3;
                                } else {
                                    zza2 = null;
                                }
                                zzdwv zzdwv2 = (zzdwv) zzfhb.zza(zzdwv.zzbsd(), zzfhm);
                                this.zzmjm = zzdwv2;
                                if (zza2 != null) {
                                    zza2.zza(zzdwv2);
                                    this.zzmjm = (zzdwv) zza2.zzczw();
                                }
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
                    synchronized (zzdwt.class) {
                        if (zzbbm == null) {
                            zzbbm = new zzfhu.zzb(zzmjn);
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
        return zzmjn;
    }

    public final void zza(zzfhg zzfhg) throws IOException {
        int i = this.zzmfs;
        if (i != 0) {
            zzfhg.zzae(1, i);
        }
        zzdwv zzdwv = this.zzmjm;
        if (zzdwv != null) {
            if (zzdwv == null) {
                zzdwv = zzdwv.zzbsd();
            }
            zzfhg.zza(2, (zzfjc) zzdwv);
        }
        this.zzpph.zza(zzfhg);
    }

    public final zzdwv zzbrz() {
        zzdwv zzdwv = this.zzmjm;
        return zzdwv == null ? zzdwv.zzbsd() : zzdwv;
    }

    public final int zzhs() {
        int i = this.zzppi;
        if (i != -1) {
            return i;
        }
        int i2 = this.zzmfs;
        int i3 = 0;
        if (i2 != 0) {
            i3 = 0 + zzfhg.zzah(1, i2);
        }
        zzdwv zzdwv = this.zzmjm;
        if (zzdwv != null) {
            if (zzdwv == null) {
                zzdwv = zzdwv.zzbsd();
            }
            i3 += zzfhg.zzc(2, (zzfjc) zzdwv);
        }
        int zzhs = i3 + this.zzpph.zzhs();
        this.zzppi = zzhs;
        return zzhs;
    }
}
