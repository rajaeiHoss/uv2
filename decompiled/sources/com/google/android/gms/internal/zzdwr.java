package com.google.android.gms.internal;

import com.google.android.gms.internal.zzfhu;
import java.io.IOException;
import java.util.Objects;

public final class zzdwr extends zzfhu<zzdwr, zzdwr.zza> implements zzfje {
    private static volatile zzfjl<zzdwr> zzbbm;
    /* access modifiers changed from: private */
    public static final zzdwr zzmjk;
    private int zzmjb;
    private int zzmjc;
    private zzfid<zzdwr.zzb> zzmjj = zzczs();

    public static final class zza extends zzfhu.zza<zzdwr, zzdwr.zza> implements zzfje {
        private zza() {
            super(zzdwr.zzmjk);
        }

        /* synthetic */ zza(zzdws zzdws) {
            this();
        }

        public final zza zzb(zzdwr.zzb zzb) {
            zzczv();
            ((zzdwr) this.zzppl).zza(zzb);
            return this;
        }

        public final zza zzgs(int i) {
            zzczv();
            ((zzdwr) this.zzppl).zzgr(i);
            return this;
        }
    }

    public static final class zzb extends zzfhu<zzdwr.zzb, zzdwr.zzb.zza> implements zzfje {
        private static volatile zzfjl<zzdwr.zzb> zzbbm;
        /* access modifiers changed from: private */
        public static final zzdwr.zzb zzmjl;
        private String zzmid = "";
        private int zzmiu;
        private int zzmjg;
        private int zzmjh;

        public static final class zza extends zzfhu.zza<zzdwr.zzb, zzdwr.zzb.zza> implements zzfje {
            private zza() {
                super(zzdwr.zzb.zzmjl);
            }

            /* synthetic */ zza(zzdws zzdws) {
                this();
            }

            public final zza zzb(zzdwj zzdwj) {
                zzczv();
                ((zzdwr.zzb) this.zzppl).zza(zzdwj);
                return this;
            }

            public final zza zzb(zzdxb zzdxb) {
                zzczv();
                ((zzdwr.zzb) this.zzppl).zza(zzdxb);
                return this;
            }

            public final zza zzgu(int i) {
                zzczv();
                ((zzdwr.zzb) this.zzppl).zzgt(i);
                return this;
            }

            public final zza zzov(String str) {
                zzczv();
                ((zzdwr.zzb) this.zzppl).zzoo(str);
                return this;
            }
        }

        static {
            zzdwr.zzb zzb = new zzdwr.zzb();
            zzmjl = zzb;
            zzb.zza(zzfhu.zzg.zzppw, (Object) null, (Object) null);
            zzb.zzpph.zzbkr();
        }

        private zzb() {
        }

        /* access modifiers changed from: private */
        public final void zza(zzdwj zzdwj) {
            Objects.requireNonNull(zzdwj);
            this.zzmjg = zzdwj.zzhu();
        }

        /* access modifiers changed from: private */
        public final void zza(zzdxb zzdxb) {
            Objects.requireNonNull(zzdxb);
            this.zzmiu = zzdxb.zzhu();
        }

        public static zza zzbrw() {
            return (zza) ((zzfhu.zza) zzmjl.zza(zzfhu.zzg.zzppy, (Object) null, (Object) null));
        }

        public static zzdwr.zzb zzbrx() {
            return zzmjl;
        }

        /* access modifiers changed from: private */
        public final void zzgt(int i) {
            this.zzmjh = i;
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
            switch (zzdws.zzbbk[i - 1]) {
                case 1:
                    return new zzdwr.zzb();
                case 2:
                    return zzmjl;
                case 3:
                    return null;
                case 4:
                    return new zza((zzdws) null);
                case 5:
                    zzfhu.zzh zzh = (zzfhu.zzh) obj;
                    zzdwr.zzb zzb = (zzdwr.zzb) obj2;
                    this.zzmid = zzh.zza(!this.zzmid.isEmpty(), this.zzmid, !zzb.zzmid.isEmpty(), zzb.zzmid);
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
                    Objects.requireNonNull((zzfhm) obj2);
                    while (!z2) {
                        try {
                            int zzcxx = zzfhb.zzcxx();
                            if (zzcxx != 0) {
                                if (zzcxx == 10) {
                                    this.zzmid = zzfhb.zzcye();
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
                        synchronized (zzdwr.zzb.class) {
                            if (zzbbm == null) {
                                zzbbm = new zzfhu.zzb(zzmjl);
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
            return zzmjl;
        }

        public final void zza(zzfhg zzfhg) throws IOException {
            if (!this.zzmid.isEmpty()) {
                zzfhg.zzp(1, this.zzmid);
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

        public final int zzhs() {
            int i = this.zzppi;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.zzmid.isEmpty()) {
                i2 = 0 + zzfhg.zzq(1, this.zzmid);
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
        zzdwr zzdwr = new zzdwr();
        zzmjk = zzdwr;
        zzdwr.zza(zzfhu.zzg.zzppw, (Object) null, (Object) null);
        zzdwr.zzpph.zzbkr();
    }

    private zzdwr() {
    }

    /* access modifiers changed from: private */
    public final void zza(zzdwr.zzb zzb2) {
        Objects.requireNonNull(zzb2);
        if (!this.zzmjj.zzcxk()) {
            zzfid<zzdwr.zzb> zzfid = this.zzmjj;
            int size = zzfid.size();
            this.zzmjj = zzfid.zzmo(size == 0 ? 10 : size << 1);
        }
        this.zzmjj.add(zzb2);
    }

    public static zza zzbru() {
        return (zza) ((zzfhu.zza) zzmjk.zza(zzfhu.zzg.zzppy, (Object) null, (Object) null));
    }

    /* access modifiers changed from: private */
    public final void zzgr(int i) {
        this.zzmjc = i;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        boolean z = true;
        boolean z2 = false;
        switch (zzdws.zzbbk[i - 1]) {
            case 1:
                return new zzdwr();
            case 2:
                return zzmjk;
            case 3:
                this.zzmjj.zzbkr();
                return null;
            case 4:
                return new zza((zzdws) null);
            case 5:
                zzfhu.zzh zzh = (zzfhu.zzh) obj;
                zzdwr zzdwr = (zzdwr) obj2;
                int i2 = this.zzmjc;
                boolean z3 = i2 != 0;
                int i3 = zzdwr.zzmjc;
                if (i3 == 0) {
                    z = false;
                }
                this.zzmjc = zzh.zza(z3, i2, z, i3);
                this.zzmjj = zzh.zza(this.zzmjj, zzdwr.zzmjj);
                if (zzh == zzfhu.zzf.zzppq) {
                    this.zzmjb |= zzdwr.zzmjb;
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
                                if (!this.zzmjj.zzcxk()) {
                                    zzfid<zzdwr.zzb> zzfid = this.zzmjj;
                                    int size = zzfid.size();
                                    this.zzmjj = zzfid.zzmo(size == 0 ? 10 : size << 1);
                                }
                                this.zzmjj.add((zzdwr.zzb) zzfhb.zza(com.google.android.gms.internal.zzdwr.zzb.zzbrx(), zzfhm));
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
                    synchronized (zzdwr.class) {
                        if (zzbbm == null) {
                            zzbbm = new zzfhu.zzb(zzmjk);
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
        return zzmjk;
    }

    public final void zza(zzfhg zzfhg) throws IOException {
        int i = this.zzmjc;
        if (i != 0) {
            zzfhg.zzae(1, i);
        }
        for (int i2 = 0; i2 < this.zzmjj.size(); i2++) {
            zzfhg.zza(2, (zzfjc) this.zzmjj.get(i2));
        }
        this.zzpph.zza(zzfhg);
    }

    public final int zzhs() {
        int i = this.zzppi;
        if (i != -1) {
            return i;
        }
        int i2 = this.zzmjc;
        int zzah = i2 != 0 ? zzfhg.zzah(1, i2) + 0 : 0;
        for (int i3 = 0; i3 < this.zzmjj.size(); i3++) {
            zzah += zzfhg.zzc(2, (zzfjc) this.zzmjj.get(i3));
        }
        int zzhs = zzah + this.zzpph.zzhs();
        this.zzppi = zzhs;
        return zzhs;
    }
}
