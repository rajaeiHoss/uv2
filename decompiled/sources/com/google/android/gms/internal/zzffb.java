package com.google.android.gms.internal;

import java.io.IOException;

public final class zzffb extends zzflm<zzffb> {
    private static zzfln<zzffk, zzffb> zzpjy = zzfln.zza(11, zzffb.class, 735401858);
    private static final zzffb[] zzpjz = new zzffb[0];
    private int zzpka = 0;
    private int zzpkb = 0;
    private int zzpkc = 0;
    private int zzpkd = 0;
    private int zzpke = 0;
    private int zzpkf = 0;

    public zzffb() {
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzai */
    public final zzffb zza(zzflj zzflj) throws IOException {
        int i;
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                i = zzflj.getPosition();
                this.zzpka = zzkr(zzflj.zzcym());
            } else if (zzcxx == 16) {
                i = zzflj.getPosition();
                this.zzpkb = zzks(zzflj.zzcym());
            } else if (zzcxx == 24) {
                i = zzflj.getPosition();
                this.zzpkc = zzkt(zzflj.zzcym());
            } else if (zzcxx == 32) {
                i = zzflj.getPosition();
                this.zzpkd = zzku(zzflj.zzcym());
            } else if (zzcxx == 40) {
                i = zzflj.getPosition();
                this.zzpke = zzkv(zzflj.zzcym());
            } else if (zzcxx == 48) {
                i = zzflj.getPosition();
                try {
                    this.zzpkf = zzkw(zzflj.zzcym());
                } catch (IllegalArgumentException unused) {
                    zzflj.zzmw(i);
                    zza(zzflj, zzcxx);
                }
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public static int zzkr(int i) {
        if (i == 0 || i == 1 || i == 2) {
            return i;
        }
        StringBuilder sb = new StringBuilder(46);
        sb.append(i);
        sb.append(" is not a valid enum HeadPhoneState");
        throw new IllegalArgumentException(sb.toString());
    }

    public static int zzks(int i) {
        if (i == 0 || i == 1 || i == 2) {
            return i;
        }
        StringBuilder sb = new StringBuilder(50);
        sb.append(i);
        sb.append(" is not a valid enum BluetoothA2DPState");
        throw new IllegalArgumentException(sb.toString());
    }

    public static int zzkt(int i) {
        if (i == 0 || i == 1 || i == 2) {
            return i;
        }
        StringBuilder sb = new StringBuilder(49);
        sb.append(i);
        sb.append(" is not a valid enum BluetoothSCOState");
        throw new IllegalArgumentException(sb.toString());
    }

    public static int zzku(int i) {
        if (i == 0 || i == 1 || i == 2) {
            return i;
        }
        StringBuilder sb = new StringBuilder(47);
        sb.append(i);
        sb.append(" is not a valid enum MicrophoneState");
        throw new IllegalArgumentException(sb.toString());
    }

    public static int zzkv(int i) {
        if (i == 0 || i == 1 || i == 2) {
            return i;
        }
        StringBuilder sb = new StringBuilder(42);
        sb.append(i);
        sb.append(" is not a valid enum MusicState");
        throw new IllegalArgumentException(sb.toString());
    }

    public static int zzkw(int i) {
        if (i == 0 || i == 1 || i == 2) {
            return i;
        }
        StringBuilder sb = new StringBuilder(49);
        sb.append(i);
        sb.append(" is not a valid enum SpeakerPhoneState");
        throw new IllegalArgumentException(sb.toString());
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzffb)) {
            return false;
        }
        zzffb zzffb = (zzffb) obj;
        if (this.zzpka == zzffb.zzpka && this.zzpkb == zzffb.zzpkb && this.zzpkc == zzffb.zzpkc && this.zzpkd == zzffb.zzpkd && this.zzpke == zzffb.zzpke && this.zzpkf == zzffb.zzpkf) {
            return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzffb.zzpvl == null || zzffb.zzpvl.isEmpty() : this.zzpvl.equals(zzffb.zzpvl);
        }
        return false;
    }

    public final int hashCode() {
        return ((((((((((((((getClass().getName().hashCode() + 527) * 31) + this.zzpka) * 31) + this.zzpkb) * 31) + this.zzpkc) * 31) + this.zzpkd) * 31) + this.zzpke) * 31) + this.zzpkf) * 31) + ((this.zzpvl == null || this.zzpvl.isEmpty()) ? 0 : this.zzpvl.hashCode());
    }

    public final void zza(zzflk zzflk) throws IOException {
        int i = this.zzpka;
        if (i != 0) {
            zzflk.zzad(1, i);
        }
        int i2 = this.zzpkb;
        if (i2 != 0) {
            zzflk.zzad(2, i2);
        }
        int i3 = this.zzpkc;
        if (i3 != 0) {
            zzflk.zzad(3, i3);
        }
        int i4 = this.zzpkd;
        if (i4 != 0) {
            zzflk.zzad(4, i4);
        }
        int i5 = this.zzpke;
        if (i5 != 0) {
            zzflk.zzad(5, i5);
        }
        int i6 = this.zzpkf;
        if (i6 != 0) {
            zzflk.zzad(6, i6);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        int i = this.zzpka;
        if (i != 0) {
            zzq += zzflk.zzag(1, i);
        }
        int i2 = this.zzpkb;
        if (i2 != 0) {
            zzq += zzflk.zzag(2, i2);
        }
        int i3 = this.zzpkc;
        if (i3 != 0) {
            zzq += zzflk.zzag(3, i3);
        }
        int i4 = this.zzpkd;
        if (i4 != 0) {
            zzq += zzflk.zzag(4, i4);
        }
        int i5 = this.zzpke;
        if (i5 != 0) {
            zzq += zzflk.zzag(5, i5);
        }
        int i6 = this.zzpkf;
        return i6 != 0 ? zzq + zzflk.zzag(6, i6) : zzq;
    }
}
