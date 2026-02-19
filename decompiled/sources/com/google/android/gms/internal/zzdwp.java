package com.google.android.gms.internal;

import com.google.android.gms.internal.zzdwg;
import com.google.android.gms.internal.zzfhu;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public final class zzdwp extends zzfhu<zzdwp, zzdwp.zza> implements zzfje {
    private static volatile zzfjl<zzdwp> zzbbm;
    /* access modifiers changed from: private */
    public static final zzdwp zzmje;
    private int zzmjb;
    private int zzmjc;
    private zzfid<zzdwp.zzb> zzmjd = zzczs();

    public static final class zza extends zzfhu.zza<zzdwp, zzdwp.zza> implements zzfje {
        private zza() {
            super(zzdwp.zzmje);
        }

        /* synthetic */ zza(zzdwq zzdwq) {
            this();
        }
    }

    public static final class zzb extends zzfhu<zzdwp.zzb, zzdwp.zzb.zza> implements zzfje {
        private static volatile zzfjl<zzdwp.zzb> zzbbm;
        /* access modifiers changed from: private */
        public static final zzdwp.zzb zzmji;
        private int zzmiu;
        private zzdwg zzmjf;
        private int zzmjg;
        private int zzmjh;

        public static final class zza extends zzfhu.zza<zzdwp.zzb, zzdwp.zzb.zza> implements zzfje {
            private zza() {
                super(zzdwp.zzb.zzmji);
            }

            /* synthetic */ zza(zzdwq zzdwq) {
                this();
            }
        }

        static {
            zzdwp.zzb zzb = new zzdwp.zzb();
            zzmji = zzb;
            zzb.zza(zzfhu.zzg.zzppw, (Object) null, (Object) null);
            zzb.zzpph.zzbkr();
        }

        private zzb() {
        }

        public static zzdwp.zzb zzbrs() {
            return zzmji;
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            zzdwg.zza zza2;
            boolean z = true;
            boolean z2 = false;
            switch (zzdwq.zzbbk[i - 1]) {
                case 1:
                    return new zzdwp.zzb();
                case 2:
                    return zzmji;
                case 3:
                    return null;
                case 4:
                    return new zza((zzdwq) null);
                case 5:
                    zzfhu.zzh zzh = (zzfhu.zzh) obj;
                    zzdwp.zzb zzb = (zzdwp.zzb) obj2;
                    this.zzmjf = (zzdwg) zzh.zza(this.zzmjf, zzb.zzmjf);
                    int i2 = this.zzmjg;
                    boolean z3 = i2 != 0;
                    int i3 = zzb.zzmjg;
                    this.zzmjg = zzh.zza(z3, i2, i3 != 0, i3);
                    int i4 = this.zzmjh;
                    boolean z4 = i4 != 0;
                    int i5 = zzb.zzmjh;
                    this.zzmjh = zzh.zza(z4, i4, i5 != 0, i5);
                    int i6 = this.zzmiu;
                    boolean z5 = i6 != 0;
                    int i7 = zzb.zzmiu;
                    if (i7 == 0) {
                        z = false;
                    }
                    this.zzmiu = zzh.zza(z5, i6, z, i7);
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
                                    zzdwg zzdwg = this.zzmjf;
                                    if (zzdwg != null) {
                                        zzfhu.zza zza3 = (zzfhu.zza) zzdwg.zza(zzfhu.zzg.zzppy, (Object) null, (Object) null);
                                        zza3.zza(zzdwg);
                                        zza2 = (zzdwg.zza) zza3;
                                    } else {
                                        zza2 = null;
                                    }
                                    zzdwg zzdwg2 = (zzdwg) zzfhb.zza(zzdwg.zzbqy(), zzfhm);
                                    this.zzmjf = zzdwg2;
                                    if (zza2 != null) {
                                        zza2.zza(zzdwg2);
                                        this.zzmjf = (zzdwg) zza2.zzczw();
                                    }
                                } else if (zzcxx == 16) {
                                    this.zzmjg = zzfhb.zzcyh();
                                } else if (zzcxx == 24) {
                                    this.zzmjh = zzfhb.zzcyg();
                                } else if (zzcxx == 32) {
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
                        synchronized (zzdwp.zzb.class) {
                            if (zzbbm == null) {
                                zzbbm = new zzfhu.zzb(zzmji);
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
            return zzmji;
        }

        public final void zza(zzfhg zzfhg) throws IOException {
            zzdwg zzdwg = this.zzmjf;
            if (zzdwg != null) {
                if (zzdwg == null) {
                    zzdwg = zzdwg.zzbqy();
                }
                zzfhg.zza(1, (zzfjc) zzdwg);
            }
            if (this.zzmjg != zzdwj.UNKNOWN_STATUS.zzhu()) {
                zzfhg.zzad(2, this.zzmjg);
            }
            int i = this.zzmjh;
            if (i != 0) {
                zzfhg.zzae(3, i);
            }
            if (this.zzmiu != zzdxb.UNKNOWN_PREFIX.zzhu()) {
                zzfhg.zzad(4, this.zzmiu);
            }
            this.zzpph.zza(zzfhg);
        }

        public final boolean zzbrn() {
            return this.zzmjf != null;
        }

        public final zzdwg zzbro() {
            zzdwg zzdwg = this.zzmjf;
            return zzdwg == null ? zzdwg.zzbqy() : zzdwg;
        }

        public final zzdwj zzbrp() {
            zzdwj zzgo = zzdwj.zzgo(this.zzmjg);
            return zzgo == null ? zzdwj.UNRECOGNIZED : zzgo;
        }

        public final int zzbrq() {
            return this.zzmjh;
        }

        public final zzdxb zzbrr() {
            zzdxb zzgx = zzdxb.zzgx(this.zzmiu);
            return zzgx == null ? zzdxb.UNRECOGNIZED : zzgx;
        }

        public final int zzhs() {
            int i = this.zzppi;
            if (i != -1) {
                return i;
            }
            zzdwg zzdwg = this.zzmjf;
            int i2 = 0;
            if (zzdwg != null) {
                if (zzdwg == null) {
                    zzdwg = zzdwg.zzbqy();
                }
                i2 = 0 + zzfhg.zzc(1, (zzfjc) zzdwg);
            }
            if (this.zzmjg != zzdwj.UNKNOWN_STATUS.zzhu()) {
                i2 += zzfhg.zzaj(2, this.zzmjg);
            }
            int i3 = this.zzmjh;
            if (i3 != 0) {
                i2 += zzfhg.zzah(3, i3);
            }
            if (this.zzmiu != zzdxb.UNKNOWN_PREFIX.zzhu()) {
                i2 += zzfhg.zzaj(4, this.zzmiu);
            }
            int zzhs = i2 + this.zzpph.zzhs();
            this.zzppi = zzhs;
            return zzhs;
        }
    }

    static {
        zzdwp zzdwp = new zzdwp();
        zzmje = zzdwp;
        zzdwp.zza(zzfhu.zzg.zzppw, (Object) null, (Object) null);
        zzdwp.zzpph.zzbkr();
    }

    private zzdwp() {
    }

    public static zzdwp zzai(byte[] bArr) throws zzfie {
        return (zzdwp) zzfhu.zza(zzmje, bArr);
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        boolean z = true;
        boolean z2 = false;
        switch (zzdwq.zzbbk[i - 1]) {
            case 1:
                return new zzdwp();
            case 2:
                return zzmje;
            case 3:
                this.zzmjd.zzbkr();
                return null;
            case 4:
                return new zza((zzdwq) null);
            case 5:
                zzfhu.zzh zzh = (zzfhu.zzh) obj;
                zzdwp zzdwp = (zzdwp) obj2;
                int i2 = this.zzmjc;
                boolean z3 = i2 != 0;
                int i3 = zzdwp.zzmjc;
                if (i3 == 0) {
                    z = false;
                }
                this.zzmjc = zzh.zza(z3, i2, z, i3);
                this.zzmjd = zzh.zza(this.zzmjd, zzdwp.zzmjd);
                if (zzh == zzfhu.zzf.zzppq) {
                    this.zzmjb |= zzdwp.zzmjb;
                }
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
                                this.zzmjc = zzfhb.zzcyg();
                            } else if (zzcxx == 18) {
                                if (!this.zzmjd.zzcxk()) {
                                    zzfid<com.google.android.gms.internal.zzdwp.zzb> zzfid = this.zzmjd;
                                    int size = zzfid.size();
                                    this.zzmjd = zzfid.zzmo(size == 0 ? 10 : size << 1);
                                }
                                this.zzmjd.add((com.google.android.gms.internal.zzdwp.zzb) zzfhb.zza(com.google.android.gms.internal.zzdwp.zzb.zzbrs(), zzfhm));
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
                    synchronized (zzdwp.class) {
                        if (zzbbm == null) {
                            zzbbm = new zzfhu.zzb(zzmje);
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
        return zzmje;
    }

    public final void zza(zzfhg zzfhg) throws IOException {
        int i = this.zzmjc;
        if (i != 0) {
            zzfhg.zzae(1, i);
        }
        for (int i2 = 0; i2 < this.zzmjd.size(); i2++) {
            zzfhg.zza(2, (zzfjc) this.zzmjd.get(i2));
        }
        this.zzpph.zza(zzfhg);
    }

    public final int zzbrj() {
        return this.zzmjc;
    }

    public final List<zzdwp.zzb> zzbrk() {
        return this.zzmjd;
    }

    public final int zzbrl() {
        return this.zzmjd.size();
    }

    public final int zzhs() {
        int i = this.zzppi;
        if (i != -1) {
            return i;
        }
        int i2 = this.zzmjc;
        int zzah = i2 != 0 ? zzfhg.zzah(1, i2) + 0 : 0;
        for (int i3 = 0; i3 < this.zzmjd.size(); i3++) {
            zzah += zzfhg.zzc(2, (zzfjc) this.zzmjd.get(i3));
        }
        int zzhs = zzah + this.zzpph.zzhs();
        this.zzppi = zzhs;
        return zzhs;
    }
}
