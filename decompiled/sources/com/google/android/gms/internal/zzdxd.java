package com.google.android.gms.internal;

import com.google.android.gms.internal.zzfhu;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public final class zzdxd extends zzfhu<zzdxd, zzdxd.zza> implements zzfje {
    private static volatile zzfjl<zzdxd> zzbbm;
    /* access modifiers changed from: private */
    public static final zzdxd zzmke;
    private int zzmjb;
    private String zzmkc = "";
    private zzfid<zzdwn> zzmkd = zzczs();

    public static final class zza extends zzfhu.zza<zzdxd, zzdxd.zza> implements zzfje {
        private zza() {
            super(zzdxd.zzmke);
        }

        /* synthetic */ zza(zzdxe zzdxe) {
            this();
        }

        public final zza zzb(zzdwn zzdwn) {
            zzczv();
            ((zzdxd) this.zzppl).zza(zzdwn);
            return this;
        }

        public final zza zzox(String str) {
            zzczv();
            ((zzdxd) this.zzppl).zzow(str);
            return this;
        }
    }

    static {
        zzdxd zzdxd = new zzdxd();
        zzmke = zzdxd;
        zzdxd.zza(zzfhu.zzg.zzppw, (Object) null, (Object) null);
        zzdxd.zzpph.zzbkr();
    }

    private zzdxd() {
    }

    /* access modifiers changed from: private */
    public final void zza(zzdwn zzdwn) {
        Objects.requireNonNull(zzdwn);
        if (!this.zzmkd.zzcxk()) {
            zzfid<zzdwn> zzfid = this.zzmkd;
            int size = zzfid.size();
            this.zzmkd = zzfid.zzmo(size == 0 ? 10 : size << 1);
        }
        this.zzmkd.add(zzdwn);
    }

    public static zza zzbsn() {
        return (zza) ((zzfhu.zza) zzmke.zza(zzfhu.zzg.zzppy, (Object) null, (Object) null));
    }

    /* access modifiers changed from: private */
    public final void zzow(String str) {
        Objects.requireNonNull(str);
        this.zzmkc = str;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzdxe.zzbbk[i - 1]) {
            case 1:
                return new zzdxd();
            case 2:
                return zzmke;
            case 3:
                this.zzmkd.zzbkr();
                return null;
            case 4:
                return new zza((zzdxe) null);
            case 5:
                zzfhu.zzh zzh = (zzfhu.zzh) obj;
                zzdxd zzdxd = (zzdxd) obj2;
                this.zzmkc = zzh.zza(!this.zzmkc.isEmpty(), this.zzmkc, true ^ zzdxd.zzmkc.isEmpty(), zzdxd.zzmkc);
                this.zzmkd = zzh.zza(this.zzmkd, zzdxd.zzmkd);
                if (zzh == zzfhu.zzf.zzppq) {
                    this.zzmjb |= zzdxd.zzmjb;
                }
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
                            int i2 = 10;
                            if (zzcxx == 10) {
                                this.zzmkc = zzfhb.zzcye();
                            } else if (zzcxx == 18) {
                                if (!this.zzmkd.zzcxk()) {
                                    zzfid<zzdwn> zzfid = this.zzmkd;
                                    int size = zzfid.size();
                                    if (size != 0) {
                                        i2 = size << 1;
                                    }
                                    this.zzmkd = zzfid.zzmo(i2);
                                }
                                this.zzmkd.add((zzdwn) zzfhb.zza(zzdwn.zzbrh(), zzfhm));
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
                    synchronized (zzdxd.class) {
                        if (zzbbm == null) {
                            zzbbm = new zzfhu.zzb(zzmke);
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
        return zzmke;
    }

    public final void zza(zzfhg zzfhg) throws IOException {
        if (!this.zzmkc.isEmpty()) {
            zzfhg.zzp(1, this.zzmkc);
        }
        for (int i = 0; i < this.zzmkd.size(); i++) {
            zzfhg.zza(2, (zzfjc) this.zzmkd.get(i));
        }
        this.zzpph.zza(zzfhg);
    }

    public final List<zzdwn> zzbsm() {
        return this.zzmkd;
    }

    public final int zzhs() {
        int i = this.zzppi;
        if (i != -1) {
            return i;
        }
        int zzq = !this.zzmkc.isEmpty() ? zzfhg.zzq(1, this.zzmkc) + 0 : 0;
        for (int i2 = 0; i2 < this.zzmkd.size(); i2++) {
            zzq += zzfhg.zzc(2, (zzfjc) this.zzmkd.get(i2));
        }
        int zzhs = zzq + this.zzpph.zzhs();
        this.zzppi = zzhs;
        return zzhs;
    }
}
