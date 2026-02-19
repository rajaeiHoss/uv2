package com.google.android.gms.internal;

import com.google.android.gms.internal.zzfhu;
import java.io.IOException;
import java.util.Objects;

public final class zzdwl extends zzfhu<zzdwl, zzdwl.zza> implements zzfje {
    private static volatile zzfjl<zzdwl> zzbbm;
    /* access modifiers changed from: private */
    public static final zzdwl zzmiv;
    private String zzmid = "";
    private zzfgs zzmie = zzfgs.zzpnw;
    private int zzmiu;

    public static final class zza extends zzfhu.zza<zzdwl, zzdwl.zza> implements zzfje {
        private zza() {
            super(zzdwl.zzmiv);
        }

        /* synthetic */ zza(zzdwm zzdwm) {
            this();
        }
    }

    static {
        zzdwl zzdwl = new zzdwl();
        zzmiv = zzdwl;
        zzdwl.zza(zzfhu.zzg.zzppw, (Object) null, (Object) null);
        zzdwl.zzpph.zzbkr();
    }

    private zzdwl() {
    }

    public static zzdwl zzbra() {
        return zzmiv;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        boolean z = true;
        boolean z2 = false;
        switch (zzdwm.zzbbk[i - 1]) {
            case 1:
                return new zzdwl();
            case 2:
                return zzmiv;
            case 3:
                return null;
            case 4:
                return new zza((zzdwm) null);
            case 5:
                zzfhu.zzh zzh = (zzfhu.zzh) obj;
                zzdwl zzdwl = (zzdwl) obj2;
                this.zzmid = zzh.zza(!this.zzmid.isEmpty(), this.zzmid, !zzdwl.zzmid.isEmpty(), zzdwl.zzmid);
                this.zzmie = zzh.zza(this.zzmie != zzfgs.zzpnw, this.zzmie, zzdwl.zzmie != zzfgs.zzpnw, zzdwl.zzmie);
                int i2 = this.zzmiu;
                boolean z3 = i2 != 0;
                int i3 = zzdwl.zzmiu;
                if (i3 == 0) {
                    z = false;
                }
                this.zzmiu = zzh.zza(z3, i2, z, i3);
                return this;
            case 6:
                zzfhb zzfhb = (zzfhb) obj;
                Objects.requireNonNull((zzfhm) obj2);
                while (!z2) {
                    try {
                        int zzcxx = zzfhb.zzcxx();
                        if (zzcxx != 0) {
                            if (zzcxx == 10) {
                                this.zzmid = zzfhb.zzcye();
                            } else if (zzcxx == 18) {
                                this.zzmie = zzfhb.zzcyf();
                            } else if (zzcxx == 24) {
                                this.zzmiu = zzfhb.zzcyh();
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
                    synchronized (zzdwl.class) {
                        if (zzbbm == null) {
                            zzbbm = new zzfhu.zzb(zzmiv);
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
        return zzmiv;
    }

    public final void zza(zzfhg zzfhg) throws IOException {
        if (!this.zzmid.isEmpty()) {
            zzfhg.zzp(1, this.zzmid);
        }
        if (!this.zzmie.isEmpty()) {
            zzfhg.zza(2, this.zzmie);
        }
        if (this.zzmiu != zzdxb.UNKNOWN_PREFIX.zzhu()) {
            zzfhg.zzad(3, this.zzmiu);
        }
        this.zzpph.zza(zzfhg);
    }

    public final String zzbqu() {
        return this.zzmid;
    }

    public final zzfgs zzbqv() {
        return this.zzmie;
    }

    public final int zzhs() {
        int i = this.zzppi;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!this.zzmid.isEmpty()) {
            i2 = 0 + zzfhg.zzq(1, this.zzmid);
        }
        if (!this.zzmie.isEmpty()) {
            i2 += zzfhg.zzc(2, this.zzmie);
        }
        if (this.zzmiu != zzdxb.UNKNOWN_PREFIX.zzhu()) {
            i2 += zzfhg.zzaj(3, this.zzmiu);
        }
        int zzhs = i2 + this.zzpph.zzhs();
        this.zzppi = zzhs;
        return zzhs;
    }
}
