package com.google.android.gms.internal;

import com.google.android.gms.internal.zzfhu;
import java.io.IOException;
import java.util.Objects;

public final class zzdwg extends zzfhu<zzdwg, zzdwg.zza> implements zzfje {
    private static volatile zzfjl<zzdwg> zzbbm;
    /* access modifiers changed from: private */
    public static final zzdwg zzmig;
    private String zzmid = "";
    private zzfgs zzmie = zzfgs.zzpnw;
    private int zzmif;

    public static final class zza extends zzfhu.zza<zzdwg, zzdwg.zza> implements zzfje {
        private zza() {
            super(zzdwg.zzmig);
        }

        /* synthetic */ zza(zzdwh zzdwh) {
            this();
        }

        public final zza zzai(zzfgs zzfgs) {
            zzczv();
            ((zzdwg) this.zzppl).zzah(zzfgs);
            return this;
        }

        public final zza zzb(zzb zzb) {
            zzczv();
            ((zzdwg) this.zzppl).zza(zzb);
            return this;
        }

        public final zza zzop(String str) {
            zzczv();
            ((zzdwg) this.zzppl).zzoo(str);
            return this;
        }
    }

    public enum zzb implements zzfia {
        UNKNOWN_KEYMATERIAL(0),
        SYMMETRIC(1),
        ASYMMETRIC_PRIVATE(2),
        ASYMMETRIC_PUBLIC(3),
        REMOTE(4),
        UNRECOGNIZED(-1);
        
        private static final zzfib<zzb> zzbcn = new zzdwi();
        private final int value;

        private zzb(int i) {
            this.value = i;
        }

        public static zzb zzgn(int i) {
            if (i == 0) {
                return UNKNOWN_KEYMATERIAL;
            }
            if (i == 1) {
                return SYMMETRIC;
            }
            if (i == 2) {
                return ASYMMETRIC_PRIVATE;
            }
            if (i == 3) {
                return ASYMMETRIC_PUBLIC;
            }
            if (i != 4) {
                return null;
            }
            return REMOTE;
        }

        public final int zzhu() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
    }

    static {
        zzdwg zzdwg = new zzdwg();
        zzmig = zzdwg;
        zzdwg.zza(zzfhu.zzg.zzppw, (Object) null, (Object) null);
        zzdwg.zzpph.zzbkr();
    }

    private zzdwg() {
    }

    /* access modifiers changed from: private */
    public final void zza(zzb zzb2) {
        Objects.requireNonNull(zzb2);
        this.zzmif = zzb2.zzhu();
    }

    /* access modifiers changed from: private */
    public final void zzah(zzfgs zzfgs) {
        Objects.requireNonNull(zzfgs);
        this.zzmie = zzfgs;
    }

    public static zza zzbqx() {
        return (zza) ((zzfhu.zza) zzmig.zza(zzfhu.zzg.zzppy, (Object) null, (Object) null));
    }

    public static zzdwg zzbqy() {
        return zzmig;
    }

    /* access modifiers changed from: private */
    public final void zzoo(String str) {
        Objects.requireNonNull(str);
        this.zzmid = str;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        boolean z = true;
        boolean z2 = false;
        switch (zzdwh.zzbbk[i - 1]) {
            case 1:
                return new zzdwg();
            case 2:
                return zzmig;
            case 3:
                return null;
            case 4:
                return new zza((zzdwh) null);
            case 5:
                zzfhu.zzh zzh = (zzfhu.zzh) obj;
                zzdwg zzdwg = (zzdwg) obj2;
                this.zzmid = zzh.zza(!this.zzmid.isEmpty(), this.zzmid, !zzdwg.zzmid.isEmpty(), zzdwg.zzmid);
                this.zzmie = zzh.zza(this.zzmie != zzfgs.zzpnw, this.zzmie, zzdwg.zzmie != zzfgs.zzpnw, zzdwg.zzmie);
                int i2 = this.zzmif;
                boolean z3 = i2 != 0;
                int i3 = zzdwg.zzmif;
                if (i3 == 0) {
                    z = false;
                }
                this.zzmif = zzh.zza(z3, i2, z, i3);
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
                                this.zzmif = zzfhb.zzcyh();
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
                    synchronized (zzdwg.class) {
                        if (zzbbm == null) {
                            zzbbm = new zzfhu.zzb(zzmig);
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
        return zzmig;
    }

    public final void zza(zzfhg zzfhg) throws IOException {
        if (!this.zzmid.isEmpty()) {
            zzfhg.zzp(1, this.zzmid);
        }
        if (!this.zzmie.isEmpty()) {
            zzfhg.zza(2, this.zzmie);
        }
        if (this.zzmif != zzb.UNKNOWN_KEYMATERIAL.zzhu()) {
            zzfhg.zzad(3, this.zzmif);
        }
        this.zzpph.zza(zzfhg);
    }

    public final String zzbqu() {
        return this.zzmid;
    }

    public final zzfgs zzbqv() {
        return this.zzmie;
    }

    public final zzb zzbqw() {
        zzb zzgn = zzb.zzgn(this.zzmif);
        return zzgn == null ? zzb.UNRECOGNIZED : zzgn;
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
        if (this.zzmif != zzb.UNKNOWN_KEYMATERIAL.zzhu()) {
            i2 += zzfhg.zzaj(3, this.zzmif);
        }
        int zzhs = i2 + this.zzpph.zzhs();
        this.zzppi = zzhs;
        return zzhs;
    }
}
