package com.google.android.gms.internal;

import com.google.android.gms.internal.zzfhu;
import java.io.IOException;
import java.util.Objects;

public final class zzdwn extends zzfhu<zzdwn, zzdwn.zza> implements zzfje {
    private static volatile zzfjl<zzdwn> zzbbm;
    /* access modifiers changed from: private */
    public static final zzdwn zzmja;
    private String zzmid = "";
    private String zzmiw = "";
    private int zzmix;
    private boolean zzmiy;
    private String zzmiz = "";

    public static final class zza extends zzfhu.zza<zzdwn, zzdwn.zza> implements zzfje {
        private zza() {
            super(zzdwn.zzmja);
        }

        /* synthetic */ zza(zzdwo zzdwo) {
            this();
        }

        public final zza zzch(boolean z) {
            zzczv();
            ((zzdwn) this.zzppl).zzcg(true);
            return this;
        }

        public final zza zzgq(int i) {
            zzczv();
            ((zzdwn) this.zzppl).zzgp(0);
            return this;
        }

        public final zza zzos(String str) {
            zzczv();
            ((zzdwn) this.zzppl).zzoq(str);
            return this;
        }

        public final zza zzot(String str) {
            zzczv();
            ((zzdwn) this.zzppl).zzoo(str);
            return this;
        }

        public final zza zzou(String str) {
            zzczv();
            ((zzdwn) this.zzppl).zzor(str);
            return this;
        }
    }

    static {
        zzdwn zzdwn = new zzdwn();
        zzmja = zzdwn;
        zzdwn.zza(zzfhu.zzg.zzppw, (Object) null, (Object) null);
        zzdwn.zzpph.zzbkr();
    }

    private zzdwn() {
    }

    public static zza zzbrg() {
        return (zza) ((zzfhu.zza) zzmja.zza(zzfhu.zzg.zzppy, (Object) null, (Object) null));
    }

    public static zzdwn zzbrh() {
        return zzmja;
    }

    /* access modifiers changed from: private */
    public final void zzcg(boolean z) {
        this.zzmiy = z;
    }

    /* access modifiers changed from: private */
    public final void zzgp(int i) {
        this.zzmix = i;
    }

    /* access modifiers changed from: private */
    public final void zzoo(String str) {
        Objects.requireNonNull(str);
        this.zzmid = str;
    }

    /* access modifiers changed from: private */
    public final void zzoq(String str) {
        Objects.requireNonNull(str);
        this.zzmiw = str;
    }

    /* access modifiers changed from: private */
    public final void zzor(String str) {
        Objects.requireNonNull(str);
        this.zzmiz = str;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        boolean z = false;
        switch (zzdwo.zzbbk[i - 1]) {
            case 1:
                return new zzdwn();
            case 2:
                return zzmja;
            case 3:
                return null;
            case 4:
                return new zza((zzdwo) null);
            case 5:
                zzfhu.zzh zzh = (zzfhu.zzh) obj;
                zzdwn zzdwn = (zzdwn) obj2;
                this.zzmiw = zzh.zza(!this.zzmiw.isEmpty(), this.zzmiw, !zzdwn.zzmiw.isEmpty(), zzdwn.zzmiw);
                this.zzmid = zzh.zza(!this.zzmid.isEmpty(), this.zzmid, !zzdwn.zzmid.isEmpty(), zzdwn.zzmid);
                int i2 = this.zzmix;
                boolean z2 = i2 != 0;
                int i3 = zzdwn.zzmix;
                if (i3 != 0) {
                    z = true;
                }
                this.zzmix = zzh.zza(z2, i2, z, i3);
                boolean z3 = this.zzmiy;
                boolean z4 = zzdwn.zzmiy;
                this.zzmiy = zzh.zza(z3, z3, z4, z4);
                this.zzmiz = zzh.zza(!this.zzmiz.isEmpty(), this.zzmiz, true ^ zzdwn.zzmiz.isEmpty(), zzdwn.zzmiz);
                return this;
            case 6:
                zzfhb zzfhb = (zzfhb) obj;
                Objects.requireNonNull((zzfhm) obj2);
                while (!z) {
                    try {
                        int zzcxx = zzfhb.zzcxx();
                        if (zzcxx != 0) {
                            if (zzcxx == 10) {
                                this.zzmiw = zzfhb.zzcye();
                            } else if (zzcxx == 18) {
                                this.zzmid = zzfhb.zzcye();
                            } else if (zzcxx == 24) {
                                this.zzmix = zzfhb.zzcyg();
                            } else if (zzcxx == 32) {
                                this.zzmiy = zzfhb.zzcyd();
                            } else if (zzcxx == 42) {
                                this.zzmiz = zzfhb.zzcye();
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
                    synchronized (zzdwn.class) {
                        if (zzbbm == null) {
                            zzbbm = new zzfhu.zzb(zzmja);
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
        return zzmja;
    }

    public final void zza(zzfhg zzfhg) throws IOException {
        if (!this.zzmiw.isEmpty()) {
            zzfhg.zzp(1, this.zzmiw);
        }
        if (!this.zzmid.isEmpty()) {
            zzfhg.zzp(2, this.zzmid);
        }
        int i = this.zzmix;
        if (i != 0) {
            zzfhg.zzae(3, i);
        }
        boolean z = this.zzmiy;
        if (z) {
            zzfhg.zzl(4, z);
        }
        if (!this.zzmiz.isEmpty()) {
            zzfhg.zzp(5, this.zzmiz);
        }
        this.zzpph.zza(zzfhg);
    }

    public final String zzbqu() {
        return this.zzmid;
    }

    public final String zzbrc() {
        return this.zzmiw;
    }

    public final int zzbrd() {
        return this.zzmix;
    }

    public final boolean zzbre() {
        return this.zzmiy;
    }

    public final String zzbrf() {
        return this.zzmiz;
    }

    public final int zzhs() {
        int i = this.zzppi;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!this.zzmiw.isEmpty()) {
            i2 = 0 + zzfhg.zzq(1, this.zzmiw);
        }
        if (!this.zzmid.isEmpty()) {
            i2 += zzfhg.zzq(2, this.zzmid);
        }
        int i3 = this.zzmix;
        if (i3 != 0) {
            i2 += zzfhg.zzah(3, i3);
        }
        boolean z = this.zzmiy;
        if (z) {
            i2 += zzfhg.zzm(4, z);
        }
        if (!this.zzmiz.isEmpty()) {
            i2 += zzfhg.zzq(5, this.zzmiz);
        }
        int zzhs = i2 + this.zzpph.zzhs();
        this.zzppi = zzhs;
        return zzhs;
    }
}
