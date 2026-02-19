package com.google.android.gms.internal;

import java.io.IOException;

public final class zzffl extends zzflm<zzffl> {
    private static volatile zzffl[] zzplb;
    public int type = 0;
    public zzffl[] zzplc = zzcxi();
    public zzfgb zzpld = null;
    public zzffq zzple = null;
    private zzffu zzplf = null;
    public zzffe zzplg = null;
    private zzffx zzplh = null;
    private zzffv zzpli = null;
    private zzfft zzplj = null;
    public zzfff zzplk = null;
    public zzffg zzpll = null;
    private zzffr zzplm = null;
    private zzffy zzpln = null;
    private zzfgd zzplo = null;
    public zzfgc zzplp = null;
    private zzffo zzplq = null;
    private zzffs zzplr = null;
    private zzffw zzpls = null;
    public zzffz zzplt = null;
    public zzfgb zzplu = null;
    private zzfge zzplv = null;
    private int[] zzplw = zzflv.zzpvy;

    public zzffl() {
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzap */
    public final zzffl zza(zzflj zzflj) throws IOException {
        zzfls zzfls;
        while (true) {
            int zzcxx = zzflj.zzcxx();
            switch (zzcxx) {
                case 0:
                    return this;
                case 8:
                    try {
                        int zzcym = zzflj.zzcym();
                        switch (zzcym) {
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                            case 9:
                            case 10:
                            case 11:
                            case 12:
                            case 13:
                            case 14:
                            case 15:
                            case 16:
                            case 17:
                            case 18:
                            case 19:
                            case 20:
                            case 21:
                                this.type = zzcym;
                                continue;
                            default:
                                StringBuilder sb = new StringBuilder(36);
                                sb.append(zzcym);
                                sb.append(" is not a valid enum Type");
                                throw new IllegalArgumentException(sb.toString());
                        }
                    } catch (IllegalArgumentException unused) {
                        zzflj.zzmw(zzflj.getPosition());
                        zza(zzflj, zzcxx);
                        continue;
                    }
                case 18:
                    int zzb = zzflv.zzb(zzflj, 18);
                    zzffl[] zzfflArr = this.zzplc;
                    int length = zzfflArr == null ? 0 : zzfflArr.length;
                    int i = zzb + length;
                    zzffl[] zzfflArr2 = new zzffl[i];
                    if (length != 0) {
                        System.arraycopy(zzfflArr, 0, zzfflArr2, 0, length);
                    }
                    while (length < i - 1) {
                        zzfflArr2[length] = new zzffl();
                        zzflj.zza(zzfflArr2[length]);
                        zzflj.zzcxx();
                        length++;
                    }
                    zzfflArr2[length] = new zzffl();
                    zzflj.zza(zzfflArr2[length]);
                    this.zzplc = zzfflArr2;
                    continue;
                case 26:
                    if (this.zzpld == null) {
                        this.zzpld = new zzfgb();
                    }
                    zzfls = this.zzpld;
                    break;
                case 34:
                    if (this.zzple == null) {
                        this.zzple = new zzffq();
                    }
                    zzfls = this.zzple;
                    break;
                case 42:
                    if (this.zzplf == null) {
                        this.zzplf = new zzffu();
                    }
                    zzfls = this.zzplf;
                    break;
                case 50:
                    if (this.zzplg == null) {
                        this.zzplg = new zzffe();
                    }
                    zzfls = this.zzplg;
                    break;
                case 58:
                    if (this.zzplh == null) {
                        this.zzplh = new zzffx();
                    }
                    zzfls = this.zzplh;
                    break;
                case 66:
                    if (this.zzpli == null) {
                        this.zzpli = new zzffv();
                    }
                    zzfls = this.zzpli;
                    break;
                case 74:
                    if (this.zzplj == null) {
                        this.zzplj = new zzfft();
                    }
                    zzfls = this.zzplj;
                    break;
                case 82:
                    if (this.zzplk == null) {
                        this.zzplk = new zzfff();
                    }
                    zzfls = this.zzplk;
                    break;
                case 90:
                    if (this.zzpll == null) {
                        this.zzpll = new zzffg();
                    }
                    zzfls = this.zzpll;
                    break;
                case 98:
                    if (this.zzplm == null) {
                        this.zzplm = new zzffr();
                    }
                    zzfls = this.zzplm;
                    break;
                case 106:
                    if (this.zzpln == null) {
                        this.zzpln = new zzffy();
                    }
                    zzfls = this.zzpln;
                    break;
                case 114:
                    if (this.zzplo == null) {
                        this.zzplo = new zzfgd();
                    }
                    zzfls = this.zzplo;
                    break;
                case 122:
                    if (this.zzplp == null) {
                        this.zzplp = new zzfgc();
                    }
                    zzfls = this.zzplp;
                    break;
                case 130:
                    if (this.zzplq == null) {
                        this.zzplq = new zzffo();
                    }
                    zzfls = this.zzplq;
                    break;
                case 138:
                    if (this.zzplr == null) {
                        this.zzplr = new zzffs();
                    }
                    zzfls = this.zzplr;
                    break;
                case 146:
                    if (this.zzpls == null) {
                        this.zzpls = new zzffw();
                    }
                    zzfls = this.zzpls;
                    break;
                case 154:
                    if (this.zzplt == null) {
                        this.zzplt = new zzffz();
                    }
                    zzfls = this.zzplt;
                    break;
                case 162:
                    if (this.zzplu == null) {
                        this.zzplu = new zzfgb();
                    }
                    zzfls = this.zzplu;
                    break;
                case 170:
                    if (this.zzplv == null) {
                        this.zzplv = new zzfge();
                    }
                    zzfls = this.zzplv;
                    break;
                case 176:
                    int zzb2 = zzflv.zzb(zzflj, 176);
                    int[] iArr = new int[zzb2];
                    int i2 = 0;
                    for (int i3 = 0; i3 < zzb2; i3++) {
                        if (i3 != 0) {
                            zzflj.zzcxx();
                        }
                        int position = zzflj.getPosition();
                        try {
                            iArr[i2] = zzffj.zzkz(zzflj.zzcym());
                            i2++;
                        } catch (IllegalArgumentException unused2) {
                            zzflj.zzmw(position);
                            zza(zzflj, zzcxx);
                        }
                    }
                    if (i2 != 0) {
                        int[] iArr2 = this.zzplw;
                        int length2 = iArr2 == null ? 0 : iArr2.length;
                        if (length2 != 0 || i2 != zzb2) {
                            int[] iArr3 = new int[(length2 + i2)];
                            if (length2 != 0) {
                                System.arraycopy(iArr2, 0, iArr3, 0, length2);
                            }
                            System.arraycopy(iArr, 0, iArr3, length2, i2);
                            this.zzplw = iArr3;
                            continue;
                        } else {
                            this.zzplw = iArr;
                            continue;
                        }
                    } else {
                        continue;
                    }
                case 178:
                    int zzli = zzflj.zzli(zzflj.zzcym());
                    int position2 = zzflj.getPosition();
                    int i4 = 0;
                    while (zzflj.zzcyo() > 0) {
                        try {
                            zzffj.zzkz(zzflj.zzcym());
                            i4++;
                        } catch (IllegalArgumentException unused3) {
                        }
                    }
                    if (i4 != 0) {
                        zzflj.zzmw(position2);
                        int[] iArr4 = this.zzplw;
                        int length3 = iArr4 == null ? 0 : iArr4.length;
                        int[] iArr5 = new int[(i4 + length3)];
                        if (length3 != 0) {
                            System.arraycopy(iArr4, 0, iArr5, 0, length3);
                        }
                        while (zzflj.zzcyo() > 0) {
                            int position3 = zzflj.getPosition();
                            try {
                                iArr5[length3] = zzffj.zzkz(zzflj.zzcym());
                                length3++;
                            } catch (IllegalArgumentException unused4) {
                                zzflj.zzmw(position3);
                                zza(zzflj, 176);
                            }
                        }
                        this.zzplw = iArr5;
                    }
                    zzflj.zzlj(zzli);
                    continue;
                default:
                    if (!super.zza(zzflj, zzcxx)) {
                        return this;
                    }
                    continue;
            }
            zzflj.zza(zzfls);
        }
    }

    private static zzffl[] zzcxi() {
        if (zzplb == null) {
            synchronized (zzflq.zzpvt) {
                if (zzplb == null) {
                    zzplb = new zzffl[0];
                }
            }
        }
        return zzplb;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzffl)) {
            return false;
        }
        zzffl zzffl = (zzffl) obj;
        if (this.type != zzffl.type || !zzflq.equals((Object[]) this.zzplc, (Object[]) zzffl.zzplc)) {
            return false;
        }
        zzfgb zzfgb = this.zzpld;
        if (zzfgb == null) {
            if (zzffl.zzpld != null) {
                return false;
            }
        } else if (!zzfgb.equals(zzffl.zzpld)) {
            return false;
        }
        zzffq zzffq = this.zzple;
        if (zzffq == null) {
            if (zzffl.zzple != null) {
                return false;
            }
        } else if (!zzffq.equals(zzffl.zzple)) {
            return false;
        }
        zzffu zzffu = this.zzplf;
        if (zzffu == null) {
            if (zzffl.zzplf != null) {
                return false;
            }
        } else if (!zzffu.equals(zzffl.zzplf)) {
            return false;
        }
        zzffe zzffe = this.zzplg;
        if (zzffe == null) {
            if (zzffl.zzplg != null) {
                return false;
            }
        } else if (!zzffe.equals(zzffl.zzplg)) {
            return false;
        }
        zzffx zzffx = this.zzplh;
        if (zzffx == null) {
            if (zzffl.zzplh != null) {
                return false;
            }
        } else if (!zzffx.equals(zzffl.zzplh)) {
            return false;
        }
        zzffv zzffv = this.zzpli;
        if (zzffv == null) {
            if (zzffl.zzpli != null) {
                return false;
            }
        } else if (!zzffv.equals(zzffl.zzpli)) {
            return false;
        }
        zzfft zzfft = this.zzplj;
        if (zzfft == null) {
            if (zzffl.zzplj != null) {
                return false;
            }
        } else if (!zzfft.equals(zzffl.zzplj)) {
            return false;
        }
        zzfff zzfff = this.zzplk;
        if (zzfff == null) {
            if (zzffl.zzplk != null) {
                return false;
            }
        } else if (!zzfff.equals(zzffl.zzplk)) {
            return false;
        }
        zzffg zzffg = this.zzpll;
        if (zzffg == null) {
            if (zzffl.zzpll != null) {
                return false;
            }
        } else if (!zzffg.equals(zzffl.zzpll)) {
            return false;
        }
        zzffr zzffr = this.zzplm;
        if (zzffr == null) {
            if (zzffl.zzplm != null) {
                return false;
            }
        } else if (!zzffr.equals(zzffl.zzplm)) {
            return false;
        }
        zzffy zzffy = this.zzpln;
        if (zzffy == null) {
            if (zzffl.zzpln != null) {
                return false;
            }
        } else if (!zzffy.equals(zzffl.zzpln)) {
            return false;
        }
        zzfgd zzfgd = this.zzplo;
        if (zzfgd == null) {
            if (zzffl.zzplo != null) {
                return false;
            }
        } else if (!zzfgd.equals(zzffl.zzplo)) {
            return false;
        }
        zzfgc zzfgc = this.zzplp;
        if (zzfgc == null) {
            if (zzffl.zzplp != null) {
                return false;
            }
        } else if (!zzfgc.equals(zzffl.zzplp)) {
            return false;
        }
        zzffo zzffo = this.zzplq;
        if (zzffo == null) {
            if (zzffl.zzplq != null) {
                return false;
            }
        } else if (!zzffo.equals(zzffl.zzplq)) {
            return false;
        }
        zzffs zzffs = this.zzplr;
        if (zzffs == null) {
            if (zzffl.zzplr != null) {
                return false;
            }
        } else if (!zzffs.equals(zzffl.zzplr)) {
            return false;
        }
        zzffw zzffw = this.zzpls;
        if (zzffw == null) {
            if (zzffl.zzpls != null) {
                return false;
            }
        } else if (!zzffw.equals(zzffl.zzpls)) {
            return false;
        }
        zzffz zzffz = this.zzplt;
        if (zzffz == null) {
            if (zzffl.zzplt != null) {
                return false;
            }
        } else if (!zzffz.equals(zzffl.zzplt)) {
            return false;
        }
        zzfgb zzfgb2 = this.zzplu;
        if (zzfgb2 == null) {
            if (zzffl.zzplu != null) {
                return false;
            }
        } else if (!zzfgb2.equals(zzffl.zzplu)) {
            return false;
        }
        zzfge zzfge = this.zzplv;
        if (zzfge == null) {
            if (zzffl.zzplv != null) {
                return false;
            }
        } else if (!zzfge.equals(zzffl.zzplv)) {
            return false;
        }
        if (!zzflq.equals(this.zzplw, zzffl.zzplw)) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzffl.zzpvl == null || zzffl.zzpvl.isEmpty() : this.zzpvl.equals(zzffl.zzpvl);
    }

    public final int hashCode() {
        int hashCode = ((((getClass().getName().hashCode() + 527) * 31) + this.type) * 31) + zzflq.hashCode((Object[]) this.zzplc);
        zzfgb zzfgb = this.zzpld;
        int i = 0;
        int hashCode2 = (hashCode * 31) + (zzfgb == null ? 0 : zzfgb.hashCode());
        zzffq zzffq = this.zzple;
        int hashCode3 = (hashCode2 * 31) + (zzffq == null ? 0 : zzffq.hashCode());
        zzffu zzffu = this.zzplf;
        int hashCode4 = (hashCode3 * 31) + (zzffu == null ? 0 : zzffu.hashCode());
        zzffe zzffe = this.zzplg;
        int hashCode5 = (hashCode4 * 31) + (zzffe == null ? 0 : zzffe.hashCode());
        zzffx zzffx = this.zzplh;
        int hashCode6 = (hashCode5 * 31) + (zzffx == null ? 0 : zzffx.hashCode());
        zzffv zzffv = this.zzpli;
        int hashCode7 = (hashCode6 * 31) + (zzffv == null ? 0 : zzffv.hashCode());
        zzfft zzfft = this.zzplj;
        int hashCode8 = (hashCode7 * 31) + (zzfft == null ? 0 : zzfft.hashCode());
        zzfff zzfff = this.zzplk;
        int hashCode9 = (hashCode8 * 31) + (zzfff == null ? 0 : zzfff.hashCode());
        zzffg zzffg = this.zzpll;
        int hashCode10 = (hashCode9 * 31) + (zzffg == null ? 0 : zzffg.hashCode());
        zzffr zzffr = this.zzplm;
        int hashCode11 = (hashCode10 * 31) + (zzffr == null ? 0 : zzffr.hashCode());
        zzffy zzffy = this.zzpln;
        int hashCode12 = (hashCode11 * 31) + (zzffy == null ? 0 : zzffy.hashCode());
        zzfgd zzfgd = this.zzplo;
        int hashCode13 = (hashCode12 * 31) + (zzfgd == null ? 0 : zzfgd.hashCode());
        zzfgc zzfgc = this.zzplp;
        int hashCode14 = (hashCode13 * 31) + (zzfgc == null ? 0 : zzfgc.hashCode());
        zzffo zzffo = this.zzplq;
        int hashCode15 = (hashCode14 * 31) + (zzffo == null ? 0 : zzffo.hashCode());
        zzffs zzffs = this.zzplr;
        int hashCode16 = (hashCode15 * 31) + (zzffs == null ? 0 : zzffs.hashCode());
        zzffw zzffw = this.zzpls;
        int hashCode17 = (hashCode16 * 31) + (zzffw == null ? 0 : zzffw.hashCode());
        zzffz zzffz = this.zzplt;
        int hashCode18 = (hashCode17 * 31) + (zzffz == null ? 0 : zzffz.hashCode());
        zzfgb zzfgb2 = this.zzplu;
        int hashCode19 = (hashCode18 * 31) + (zzfgb2 == null ? 0 : zzfgb2.hashCode());
        zzfge zzfge = this.zzplv;
        int hashCode20 = ((((hashCode19 * 31) + (zzfge == null ? 0 : zzfge.hashCode())) * 31) + zzflq.hashCode(this.zzplw)) * 31;
        if (this.zzpvl != null && !this.zzpvl.isEmpty()) {
            i = this.zzpvl.hashCode();
        }
        return hashCode20 + i;
    }

    public final void zza(zzflk zzflk) throws IOException {
        int i = this.type;
        if (i != 0) {
            zzflk.zzad(1, i);
        }
        zzffl[] zzfflArr = this.zzplc;
        int i2 = 0;
        if (zzfflArr != null && zzfflArr.length > 0) {
            int i3 = 0;
            while (true) {
                zzffl[] zzfflArr2 = this.zzplc;
                if (i3 >= zzfflArr2.length) {
                    break;
                }
                zzffl zzffl = zzfflArr2[i3];
                if (zzffl != null) {
                    zzflk.zza(2, (zzfls) zzffl);
                }
                i3++;
            }
        }
        zzfgb zzfgb = this.zzpld;
        if (zzfgb != null) {
            zzflk.zza(3, (zzfls) zzfgb);
        }
        zzffq zzffq = this.zzple;
        if (zzffq != null) {
            zzflk.zza(4, (zzfls) zzffq);
        }
        zzffu zzffu = this.zzplf;
        if (zzffu != null) {
            zzflk.zza(5, (zzfls) zzffu);
        }
        zzffe zzffe = this.zzplg;
        if (zzffe != null) {
            zzflk.zza(6, (zzfls) zzffe);
        }
        zzffx zzffx = this.zzplh;
        if (zzffx != null) {
            zzflk.zza(7, (zzfls) zzffx);
        }
        zzffv zzffv = this.zzpli;
        if (zzffv != null) {
            zzflk.zza(8, (zzfls) zzffv);
        }
        zzfft zzfft = this.zzplj;
        if (zzfft != null) {
            zzflk.zza(9, (zzfls) zzfft);
        }
        zzfff zzfff = this.zzplk;
        if (zzfff != null) {
            zzflk.zza(10, (zzfls) zzfff);
        }
        zzffg zzffg = this.zzpll;
        if (zzffg != null) {
            zzflk.zza(11, (zzfls) zzffg);
        }
        zzffr zzffr = this.zzplm;
        if (zzffr != null) {
            zzflk.zza(12, (zzfls) zzffr);
        }
        zzffy zzffy = this.zzpln;
        if (zzffy != null) {
            zzflk.zza(13, (zzfls) zzffy);
        }
        zzfgd zzfgd = this.zzplo;
        if (zzfgd != null) {
            zzflk.zza(14, (zzfls) zzfgd);
        }
        zzfgc zzfgc = this.zzplp;
        if (zzfgc != null) {
            zzflk.zza(15, (zzfls) zzfgc);
        }
        zzffo zzffo = this.zzplq;
        if (zzffo != null) {
            zzflk.zza(16, (zzfls) zzffo);
        }
        zzffs zzffs = this.zzplr;
        if (zzffs != null) {
            zzflk.zza(17, (zzfls) zzffs);
        }
        zzffw zzffw = this.zzpls;
        if (zzffw != null) {
            zzflk.zza(18, (zzfls) zzffw);
        }
        zzffz zzffz = this.zzplt;
        if (zzffz != null) {
            zzflk.zza(19, (zzfls) zzffz);
        }
        zzfgb zzfgb2 = this.zzplu;
        if (zzfgb2 != null) {
            zzflk.zza(20, (zzfls) zzfgb2);
        }
        zzfge zzfge = this.zzplv;
        if (zzfge != null) {
            zzflk.zza(21, (zzfls) zzfge);
        }
        int[] iArr = this.zzplw;
        if (iArr != null && iArr.length > 0) {
            while (true) {
                int[] iArr2 = this.zzplw;
                if (i2 >= iArr2.length) {
                    break;
                }
                zzflk.zzad(22, iArr2[i2]);
                i2++;
            }
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        int i = this.type;
        if (i != 0) {
            zzq += zzflk.zzag(1, i);
        }
        zzffl[] zzfflArr = this.zzplc;
        int i2 = 0;
        if (zzfflArr != null && zzfflArr.length > 0) {
            int i3 = 0;
            while (true) {
                zzffl[] zzfflArr2 = this.zzplc;
                if (i3 >= zzfflArr2.length) {
                    break;
                }
                zzffl zzffl = zzfflArr2[i3];
                if (zzffl != null) {
                    zzq += zzflk.zzb(2, (zzfls) zzffl);
                }
                i3++;
            }
        }
        zzfgb zzfgb = this.zzpld;
        if (zzfgb != null) {
            zzq += zzflk.zzb(3, (zzfls) zzfgb);
        }
        zzffq zzffq = this.zzple;
        if (zzffq != null) {
            zzq += zzflk.zzb(4, (zzfls) zzffq);
        }
        zzffu zzffu = this.zzplf;
        if (zzffu != null) {
            zzq += zzflk.zzb(5, (zzfls) zzffu);
        }
        zzffe zzffe = this.zzplg;
        if (zzffe != null) {
            zzq += zzflk.zzb(6, (zzfls) zzffe);
        }
        zzffx zzffx = this.zzplh;
        if (zzffx != null) {
            zzq += zzflk.zzb(7, (zzfls) zzffx);
        }
        zzffv zzffv = this.zzpli;
        if (zzffv != null) {
            zzq += zzflk.zzb(8, (zzfls) zzffv);
        }
        zzfft zzfft = this.zzplj;
        if (zzfft != null) {
            zzq += zzflk.zzb(9, (zzfls) zzfft);
        }
        zzfff zzfff = this.zzplk;
        if (zzfff != null) {
            zzq += zzflk.zzb(10, (zzfls) zzfff);
        }
        zzffg zzffg = this.zzpll;
        if (zzffg != null) {
            zzq += zzflk.zzb(11, (zzfls) zzffg);
        }
        zzffr zzffr = this.zzplm;
        if (zzffr != null) {
            zzq += zzflk.zzb(12, (zzfls) zzffr);
        }
        zzffy zzffy = this.zzpln;
        if (zzffy != null) {
            zzq += zzflk.zzb(13, (zzfls) zzffy);
        }
        zzfgd zzfgd = this.zzplo;
        if (zzfgd != null) {
            zzq += zzflk.zzb(14, (zzfls) zzfgd);
        }
        zzfgc zzfgc = this.zzplp;
        if (zzfgc != null) {
            zzq += zzflk.zzb(15, (zzfls) zzfgc);
        }
        zzffo zzffo = this.zzplq;
        if (zzffo != null) {
            zzq += zzflk.zzb(16, (zzfls) zzffo);
        }
        zzffs zzffs = this.zzplr;
        if (zzffs != null) {
            zzq += zzflk.zzb(17, (zzfls) zzffs);
        }
        zzffw zzffw = this.zzpls;
        if (zzffw != null) {
            zzq += zzflk.zzb(18, (zzfls) zzffw);
        }
        zzffz zzffz = this.zzplt;
        if (zzffz != null) {
            zzq += zzflk.zzb(19, (zzfls) zzffz);
        }
        zzfgb zzfgb2 = this.zzplu;
        if (zzfgb2 != null) {
            zzq += zzflk.zzb(20, (zzfls) zzfgb2);
        }
        zzfge zzfge = this.zzplv;
        if (zzfge != null) {
            zzq += zzflk.zzb(21, (zzfls) zzfge);
        }
        int[] iArr = this.zzplw;
        if (iArr == null || iArr.length <= 0) {
            return zzq;
        }
        int i4 = 0;
        while (true) {
            int[] iArr2 = this.zzplw;
            if (i2 >= iArr2.length) {
                return zzq + i4 + (iArr2.length * 2);
            }
            i4 += zzflk.zzlx(iArr2[i2]);
            i2++;
        }
    }
}
