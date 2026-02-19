package com.google.android.gms.internal;

import com.google.android.gms.internal.zzduq;
import com.google.android.gms.internal.zzdwa;
import com.google.android.gms.internal.zzfhu;
import java.io.IOException;
import java.util.Objects;

public final class zzdum extends zzfhu<zzdum, zzdum.zza> implements zzfje {
    private static volatile zzfjl<zzdum> zzbbm;
    /* access modifiers changed from: private */
    public static final zzdum zzmfv;
    private int zzmfs;
    private zzduq zzmft;
    private zzdwa zzmfu;

    public static final class zza extends zzfhu.zza<zzdum, zzdum.zza> implements zzfje {
        private zza() {
            super(zzdum.zzmfv);
        }

        /* synthetic */ zza(zzdun zzdun) {
            this();
        }

        public final zza zzb(zzduq zzduq) {
            zzczv();
            ((zzdum) this.zzppl).zza(zzduq);
            return this;
        }

        public final zza zzb(zzdwa zzdwa) {
            zzczv();
            ((zzdum) this.zzppl).zza(zzdwa);
            return this;
        }

        public final zza zzgc(int i) {
            zzczv();
            ((zzdum) this.zzppl).setVersion(i);
            return this;
        }
    }

    static {
        zzdum zzdum = new zzdum();
        zzmfv = zzdum;
        zzdum.zza(zzfhu.zzg.zzppw, (Object) null, (Object) null);
        zzdum.zzpph.zzbkr();
    }

    private zzdum() {
    }

    /* access modifiers changed from: private */
    public final void setVersion(int i) {
        this.zzmfs = i;
    }

    /* access modifiers changed from: private */
    public final void zza(zzduq zzduq) {
        Objects.requireNonNull(zzduq);
        this.zzmft = zzduq;
    }

    /* access modifiers changed from: private */
    public final void zza(zzdwa zzdwa) {
        Objects.requireNonNull(zzdwa);
        this.zzmfu = zzdwa;
    }

    public static zza zzbon() {
        return (zza) ((zzfhu.zza) zzmfv.zza(zzfhu.zzg.zzppy, (Object) null, (Object) null));
    }

    public static zzdum zzi(zzfgs zzfgs) throws zzfie {
        return (zzdum) zzfhu.zza(zzmfv, zzfgs);
    }

    public final int getVersion() {
        return this.zzmfs;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        zzduq.zza zza2;
        zzdwa.zza zza3;
        boolean z = true;
        boolean z2 = false;
        switch (zzdun.zzbbk[i - 1]) {
            case 1:
                return new zzdum();
            case 2:
                return zzmfv;
            case 3:
                return null;
            case 4:
                return new zza((zzdun) null);
            case 5:
                zzfhu.zzh zzh = (zzfhu.zzh) obj;
                zzdum zzdum = (zzdum) obj2;
                int i2 = this.zzmfs;
                boolean z3 = i2 != 0;
                int i3 = zzdum.zzmfs;
                if (i3 == 0) {
                    z = false;
                }
                this.zzmfs = zzh.zza(z3, i2, z, i3);
                this.zzmft = (zzduq) zzh.zza(this.zzmft, zzdum.zzmft);
                this.zzmfu = (zzdwa) zzh.zza(this.zzmfu, zzdum.zzmfu);
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
                                zzduq zzduq = this.zzmft;
                                if (zzduq != null) {
                                    zzfhu.zza zza4 = (zzfhu.zza) zzduq.zza(zzfhu.zzg.zzppy, (Object) null, (Object) null);
                                    zza4.zza(zzduq);
                                    zza2 = (zzduq.zza) zza4;
                                } else {
                                    zza2 = null;
                                }
                                zzduq zzduq2 = (zzduq) zzfhb.zza(zzduq.zzbov(), zzfhm);
                                this.zzmft = zzduq2;
                                if (zza2 != null) {
                                    zza2.zza(zzduq2);
                                    this.zzmft = (zzduq) zza2.zzczw();
                                }
                            } else if (zzcxx == 26) {
                                zzdwa zzdwa = this.zzmfu;
                                if (zzdwa != null) {
                                    zzfhu.zza zza5 = (zzfhu.zza) zzdwa.zza(zzfhu.zzg.zzppy, (Object) null, (Object) null);
                                    zza5.zza(zzdwa);
                                    zza3 = (zzdwa.zza) zza5;
                                } else {
                                    zza3 = null;
                                }
                                zzdwa zzdwa2 = (zzdwa) zzfhb.zza(zzdwa.zzbqm(), zzfhm);
                                this.zzmfu = zzdwa2;
                                if (zza3 != null) {
                                    zza3.zza(zzdwa2);
                                    this.zzmfu = (zzdwa) zza3.zzczw();
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
                    synchronized (zzdum.class) {
                        if (zzbbm == null) {
                            zzbbm = new zzfhu.zzb(zzmfv);
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
        return zzmfv;
    }

    public final void zza(zzfhg zzfhg) throws IOException {
        int i = this.zzmfs;
        if (i != 0) {
            zzfhg.zzae(1, i);
        }
        zzduq zzduq = this.zzmft;
        if (zzduq != null) {
            if (zzduq == null) {
                zzduq = zzduq.zzbov();
            }
            zzfhg.zza(2, (zzfjc) zzduq);
        }
        zzdwa zzdwa = this.zzmfu;
        if (zzdwa != null) {
            if (zzdwa == null) {
                zzdwa = zzdwa.zzbqm();
            }
            zzfhg.zza(3, (zzfjc) zzdwa);
        }
        this.zzpph.zza(zzfhg);
    }

    public final zzduq zzbol() {
        zzduq zzduq = this.zzmft;
        return zzduq == null ? zzduq.zzbov() : zzduq;
    }

    public final zzdwa zzbom() {
        zzdwa zzdwa = this.zzmfu;
        return zzdwa == null ? zzdwa.zzbqm() : zzdwa;
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
        zzduq zzduq = this.zzmft;
        if (zzduq != null) {
            if (zzduq == null) {
                zzduq = zzduq.zzbov();
            }
            i3 += zzfhg.zzc(2, (zzfjc) zzduq);
        }
        zzdwa zzdwa = this.zzmfu;
        if (zzdwa != null) {
            if (zzdwa == null) {
                zzdwa = zzdwa.zzbqm();
            }
            i3 += zzfhg.zzc(3, (zzfjc) zzdwa);
        }
        int zzhs = i3 + this.zzpph.zzhs();
        this.zzppi = zzhs;
        return zzhs;
    }
}
