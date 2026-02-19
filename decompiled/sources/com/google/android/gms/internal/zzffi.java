package com.google.android.gms.internal;

import java.io.IOException;

public final class zzffi extends zzflm<zzffi> {
    private static zzfln<zzffi, zzffi> zzpkq = zzfln.zza(11, zzffi.class, 855033290);
    private static final zzffi[] zzpkr = new zzffi[0];
    public String zzpks = "";
    public zzffm zzpkt = null;
    private int zzpku = 0;
    private int zzpkv = 0;
    private int zzpkw = 0;
    private zzfga zzpkx = null;
    private zzffk zzpky = null;
    private String[] zzpkz = zzflv.EMPTY_STRING_ARRAY;
    private zzffp zzpla = null;

    public zzffi() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzao */
    public final zzffi zza(zzflj zzflj) throws IOException {
        zzfls zzfls;
        int i;
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx != 10) {
                if (zzcxx == 18) {
                    if (this.zzpkt == null) {
                        this.zzpkt = new zzffm();
                    }
                    zzfls = this.zzpkt;
                } else if (zzcxx == 32) {
                    i = zzflj.getPosition();
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
                            this.zzpku = zzcym;
                            break;
                        default:
                            StringBuilder sb = new StringBuilder(45);
                            sb.append(zzcym);
                            sb.append(" is not a valid enum ContextFamily");
                            throw new IllegalArgumentException(sb.toString());
                    }
                    continue;
                } else if (zzcxx == 40) {
                    i = zzflj.getPosition();
                    this.zzpkv = zzffj.zzkz(zzflj.zzcym());
                    continue;
                } else if (zzcxx == 48) {
                    i = zzflj.getPosition();
                    try {
                        int zzcym2 = zzflj.zzcym();
                        if (!(zzcym2 == 0 || zzcym2 == 1 || zzcym2 == 2)) {
                            if (zzcym2 != 3) {
                                StringBuilder sb2 = new StringBuilder(42);
                                sb2.append(zzcym2);
                                sb2.append(" is not a valid enum SignalType");
                                throw new IllegalArgumentException(sb2.toString());
                            }
                        }
                        this.zzpkw = zzcym2;
                    } catch (IllegalArgumentException unused) {
                        zzflj.zzmw(i);
                        zza(zzflj, zzcxx);
                    }
                    continue;
                } else if (zzcxx == 58) {
                    if (this.zzpkx == null) {
                        this.zzpkx = new zzfga();
                    }
                    zzfls = this.zzpkx;
                } else if (zzcxx == 66) {
                    if (this.zzpky == null) {
                        this.zzpky = new zzffk();
                    }
                    zzfls = this.zzpky;
                } else if (zzcxx == 74) {
                    int zzb = zzflv.zzb(zzflj, 74);
                    String[] strArr = this.zzpkz;
                    int length = strArr == null ? 0 : strArr.length;
                    int i2 = zzb + length;
                    String[] strArr2 = new String[i2];
                    if (length != 0) {
                        System.arraycopy(strArr, 0, strArr2, 0, length);
                    }
                    while (length < i2 - 1) {
                        strArr2[length] = zzflj.readString();
                        zzflj.zzcxx();
                        length++;
                    }
                    strArr2[length] = zzflj.readString();
                    this.zzpkz = strArr2;
                    continue;
                } else if (zzcxx == 82) {
                    if (this.zzpla == null) {
                        this.zzpla = new zzffp();
                    }
                    zzfls = this.zzpla;
                } else if (!super.zza(zzflj, zzcxx)) {
                    return this;
                } else {
                    continue;
                }
                zzflj.zza(zzfls);
            } else {
                this.zzpks = zzflj.readString();
            }
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzffi)) {
            return false;
        }
        zzffi zzffi = (zzffi) obj;
        String str = this.zzpks;
        if (str == null) {
            if (zzffi.zzpks != null) {
                return false;
            }
        } else if (!str.equals(zzffi.zzpks)) {
            return false;
        }
        zzffm zzffm = this.zzpkt;
        if (zzffm == null) {
            if (zzffi.zzpkt != null) {
                return false;
            }
        } else if (!zzffm.equals(zzffi.zzpkt)) {
            return false;
        }
        if (this.zzpku != zzffi.zzpku || this.zzpkv != zzffi.zzpkv || this.zzpkw != zzffi.zzpkw) {
            return false;
        }
        zzfga zzfga = this.zzpkx;
        if (zzfga == null) {
            if (zzffi.zzpkx != null) {
                return false;
            }
        } else if (!zzfga.equals(zzffi.zzpkx)) {
            return false;
        }
        zzffk zzffk = this.zzpky;
        if (zzffk == null) {
            if (zzffi.zzpky != null) {
                return false;
            }
        } else if (!zzffk.equals(zzffi.zzpky)) {
            return false;
        }
        if (!zzflq.equals((Object[]) this.zzpkz, (Object[]) zzffi.zzpkz)) {
            return false;
        }
        zzffp zzffp = this.zzpla;
        if (zzffp == null) {
            if (zzffi.zzpla != null) {
                return false;
            }
        } else if (!zzffp.equals(zzffi.zzpla)) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzffi.zzpvl == null || zzffi.zzpvl.isEmpty() : this.zzpvl.equals(zzffi.zzpvl);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        String str = this.zzpks;
        int i = 0;
        int hashCode2 = hashCode + (str == null ? 0 : str.hashCode());
        zzffm zzffm = this.zzpkt;
        int hashCode3 = (((((((hashCode2 * 31) + (zzffm == null ? 0 : zzffm.hashCode())) * 31) + this.zzpku) * 31) + this.zzpkv) * 31) + this.zzpkw;
        zzfga zzfga = this.zzpkx;
        int hashCode4 = (hashCode3 * 31) + (zzfga == null ? 0 : zzfga.hashCode());
        zzffk zzffk = this.zzpky;
        int hashCode5 = (((hashCode4 * 31) + (zzffk == null ? 0 : zzffk.hashCode())) * 31) + zzflq.hashCode((Object[]) this.zzpkz);
        zzffp zzffp = this.zzpla;
        int hashCode6 = ((hashCode5 * 31) + (zzffp == null ? 0 : zzffp.hashCode())) * 31;
        if (this.zzpvl != null && !this.zzpvl.isEmpty()) {
            i = this.zzpvl.hashCode();
        }
        return hashCode6 + i;
    }

    public final void zza(zzflk zzflk) throws IOException {
        String str = this.zzpks;
        if (str != null && !str.equals("")) {
            zzflk.zzp(1, this.zzpks);
        }
        zzffm zzffm = this.zzpkt;
        if (zzffm != null) {
            zzflk.zza(2, (zzfls) zzffm);
        }
        int i = this.zzpku;
        if (i != 0) {
            zzflk.zzad(4, i);
        }
        int i2 = this.zzpkv;
        if (i2 != 0) {
            zzflk.zzad(5, i2);
        }
        int i3 = this.zzpkw;
        if (i3 != 0) {
            zzflk.zzad(6, i3);
        }
        zzfga zzfga = this.zzpkx;
        if (zzfga != null) {
            zzflk.zza(7, (zzfls) zzfga);
        }
        zzffk zzffk = this.zzpky;
        if (zzffk != null) {
            zzflk.zza(8, (zzfls) zzffk);
        }
        String[] strArr = this.zzpkz;
        if (strArr != null && strArr.length > 0) {
            int i4 = 0;
            while (true) {
                String[] strArr2 = this.zzpkz;
                if (i4 >= strArr2.length) {
                    break;
                }
                String str2 = strArr2[i4];
                if (str2 != null) {
                    zzflk.zzp(9, str2);
                }
                i4++;
            }
        }
        zzffp zzffp = this.zzpla;
        if (zzffp != null) {
            zzflk.zza(10, (zzfls) zzffp);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        String str = this.zzpks;
        if (str != null && !str.equals("")) {
            zzq += zzflk.zzq(1, this.zzpks);
        }
        zzffm zzffm = this.zzpkt;
        if (zzffm != null) {
            zzq += zzflk.zzb(2, (zzfls) zzffm);
        }
        int i = this.zzpku;
        if (i != 0) {
            zzq += zzflk.zzag(4, i);
        }
        int i2 = this.zzpkv;
        if (i2 != 0) {
            zzq += zzflk.zzag(5, i2);
        }
        int i3 = this.zzpkw;
        if (i3 != 0) {
            zzq += zzflk.zzag(6, i3);
        }
        zzfga zzfga = this.zzpkx;
        if (zzfga != null) {
            zzq += zzflk.zzb(7, (zzfls) zzfga);
        }
        zzffk zzffk = this.zzpky;
        if (zzffk != null) {
            zzq += zzflk.zzb(8, (zzfls) zzffk);
        }
        String[] strArr = this.zzpkz;
        if (strArr != null && strArr.length > 0) {
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (true) {
                String[] strArr2 = this.zzpkz;
                if (i4 >= strArr2.length) {
                    break;
                }
                String str2 = strArr2[i4];
                if (str2 != null) {
                    i6++;
                    i5 += zzflk.zztx(str2);
                }
                i4++;
            }
            zzq = zzq + i5 + (i6 * 1);
        }
        zzffp zzffp = this.zzpla;
        return zzffp != null ? zzq + zzflk.zzb(10, (zzfls) zzffp) : zzq;
    }
}
