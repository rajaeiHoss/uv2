package com.google.android.gms.internal;

import java.io.IOException;
import org.kxml2.wap.Wbxml;

public final class zzcoe extends zzflm<zzcoe> {
    private static volatile zzcoe[] zzjuo;
    public String zzcm = null;
    public String zzda = null;
    public Long zzfqm = null;
    public String zzina = null;
    public String zzjfk = null;
    public String zzjfl = null;
    public String zzjfn = null;
    public String zzjfs = null;
    public String zzjgi = null;
    public String zzjho = null;
    public Integer zzjup = null;
    public zzcob[] zzjuq = zzcob.zzbcz();
    public zzcog[] zzjur = zzcog.zzbdc();
    public Long zzjus = null;
    public Long zzjut = null;
    public Long zzjuu = null;
    public Long zzjuv = null;
    public Long zzjuw = null;
    public String zzjux = null;
    public String zzjuy = null;
    public Integer zzjuz = null;
    public Long zzjva = null;
    public Long zzjvb = null;
    public String zzjvc = null;
    public Boolean zzjvd = null;
    public Long zzjve = null;
    public Integer zzjvf = null;
    public Boolean zzjvg = null;
    public zzcoa[] zzjvh = zzcoa.zzbcy();
    public Integer zzjvi = null;
    private Integer zzjvj = null;
    private Integer zzjvk = null;
    public String zzjvl = null;
    public Long zzjvm = null;
    public String zzjvn = null;

    public zzcoe() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public static zzcoe[] zzbdb() {
        if (zzjuo == null) {
            synchronized (zzflq.zzpvt) {
                if (zzjuo == null) {
                    zzjuo = new zzcoe[0];
                }
            }
        }
        return zzjuo;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcoe)) {
            return false;
        }
        zzcoe zzcoe = (zzcoe) obj;
        Integer num = this.zzjup;
        if (num == null) {
            if (zzcoe.zzjup != null) {
                return false;
            }
        } else if (!num.equals(zzcoe.zzjup)) {
            return false;
        }
        if (!zzflq.equals((Object[]) this.zzjuq, (Object[]) zzcoe.zzjuq) || !zzflq.equals((Object[]) this.zzjur, (Object[]) zzcoe.zzjur)) {
            return false;
        }
        Long l = this.zzjus;
        if (l == null) {
            if (zzcoe.zzjus != null) {
                return false;
            }
        } else if (!l.equals(zzcoe.zzjus)) {
            return false;
        }
        Long l2 = this.zzjut;
        if (l2 == null) {
            if (zzcoe.zzjut != null) {
                return false;
            }
        } else if (!l2.equals(zzcoe.zzjut)) {
            return false;
        }
        Long l3 = this.zzjuu;
        if (l3 == null) {
            if (zzcoe.zzjuu != null) {
                return false;
            }
        } else if (!l3.equals(zzcoe.zzjuu)) {
            return false;
        }
        Long l4 = this.zzjuv;
        if (l4 == null) {
            if (zzcoe.zzjuv != null) {
                return false;
            }
        } else if (!l4.equals(zzcoe.zzjuv)) {
            return false;
        }
        Long l5 = this.zzjuw;
        if (l5 == null) {
            if (zzcoe.zzjuw != null) {
                return false;
            }
        } else if (!l5.equals(zzcoe.zzjuw)) {
            return false;
        }
        String str = this.zzjux;
        if (str == null) {
            if (zzcoe.zzjux != null) {
                return false;
            }
        } else if (!str.equals(zzcoe.zzjux)) {
            return false;
        }
        String str2 = this.zzda;
        if (str2 == null) {
            if (zzcoe.zzda != null) {
                return false;
            }
        } else if (!str2.equals(zzcoe.zzda)) {
            return false;
        }
        String str3 = this.zzjuy;
        if (str3 == null) {
            if (zzcoe.zzjuy != null) {
                return false;
            }
        } else if (!str3.equals(zzcoe.zzjuy)) {
            return false;
        }
        String str4 = this.zzjho;
        if (str4 == null) {
            if (zzcoe.zzjho != null) {
                return false;
            }
        } else if (!str4.equals(zzcoe.zzjho)) {
            return false;
        }
        Integer num2 = this.zzjuz;
        if (num2 == null) {
            if (zzcoe.zzjuz != null) {
                return false;
            }
        } else if (!num2.equals(zzcoe.zzjuz)) {
            return false;
        }
        String str5 = this.zzjfs;
        if (str5 == null) {
            if (zzcoe.zzjfs != null) {
                return false;
            }
        } else if (!str5.equals(zzcoe.zzjfs)) {
            return false;
        }
        String str6 = this.zzcm;
        if (str6 == null) {
            if (zzcoe.zzcm != null) {
                return false;
            }
        } else if (!str6.equals(zzcoe.zzcm)) {
            return false;
        }
        String str7 = this.zzina;
        if (str7 == null) {
            if (zzcoe.zzina != null) {
                return false;
            }
        } else if (!str7.equals(zzcoe.zzina)) {
            return false;
        }
        Long l6 = this.zzjva;
        if (l6 == null) {
            if (zzcoe.zzjva != null) {
                return false;
            }
        } else if (!l6.equals(zzcoe.zzjva)) {
            return false;
        }
        Long l7 = this.zzjvb;
        if (l7 == null) {
            if (zzcoe.zzjvb != null) {
                return false;
            }
        } else if (!l7.equals(zzcoe.zzjvb)) {
            return false;
        }
        String str8 = this.zzjvc;
        if (str8 == null) {
            if (zzcoe.zzjvc != null) {
                return false;
            }
        } else if (!str8.equals(zzcoe.zzjvc)) {
            return false;
        }
        Boolean bool = this.zzjvd;
        if (bool == null) {
            if (zzcoe.zzjvd != null) {
                return false;
            }
        } else if (!bool.equals(zzcoe.zzjvd)) {
            return false;
        }
        String str9 = this.zzjfk;
        if (str9 == null) {
            if (zzcoe.zzjfk != null) {
                return false;
            }
        } else if (!str9.equals(zzcoe.zzjfk)) {
            return false;
        }
        Long l8 = this.zzjve;
        if (l8 == null) {
            if (zzcoe.zzjve != null) {
                return false;
            }
        } else if (!l8.equals(zzcoe.zzjve)) {
            return false;
        }
        Integer num3 = this.zzjvf;
        if (num3 == null) {
            if (zzcoe.zzjvf != null) {
                return false;
            }
        } else if (!num3.equals(zzcoe.zzjvf)) {
            return false;
        }
        String str10 = this.zzjgi;
        if (str10 == null) {
            if (zzcoe.zzjgi != null) {
                return false;
            }
        } else if (!str10.equals(zzcoe.zzjgi)) {
            return false;
        }
        String str11 = this.zzjfl;
        if (str11 == null) {
            if (zzcoe.zzjfl != null) {
                return false;
            }
        } else if (!str11.equals(zzcoe.zzjfl)) {
            return false;
        }
        Boolean bool2 = this.zzjvg;
        if (bool2 == null) {
            if (zzcoe.zzjvg != null) {
                return false;
            }
        } else if (!bool2.equals(zzcoe.zzjvg)) {
            return false;
        }
        if (!zzflq.equals((Object[]) this.zzjvh, (Object[]) zzcoe.zzjvh)) {
            return false;
        }
        String str12 = this.zzjfn;
        if (str12 == null) {
            if (zzcoe.zzjfn != null) {
                return false;
            }
        } else if (!str12.equals(zzcoe.zzjfn)) {
            return false;
        }
        Integer num4 = this.zzjvi;
        if (num4 == null) {
            if (zzcoe.zzjvi != null) {
                return false;
            }
        } else if (!num4.equals(zzcoe.zzjvi)) {
            return false;
        }
        Integer num5 = this.zzjvj;
        if (num5 == null) {
            if (zzcoe.zzjvj != null) {
                return false;
            }
        } else if (!num5.equals(zzcoe.zzjvj)) {
            return false;
        }
        Integer num6 = this.zzjvk;
        if (num6 == null) {
            if (zzcoe.zzjvk != null) {
                return false;
            }
        } else if (!num6.equals(zzcoe.zzjvk)) {
            return false;
        }
        String str13 = this.zzjvl;
        if (str13 == null) {
            if (zzcoe.zzjvl != null) {
                return false;
            }
        } else if (!str13.equals(zzcoe.zzjvl)) {
            return false;
        }
        Long l9 = this.zzjvm;
        if (l9 == null) {
            if (zzcoe.zzjvm != null) {
                return false;
            }
        } else if (!l9.equals(zzcoe.zzjvm)) {
            return false;
        }
        Long l10 = this.zzfqm;
        if (l10 == null) {
            if (zzcoe.zzfqm != null) {
                return false;
            }
        } else if (!l10.equals(zzcoe.zzfqm)) {
            return false;
        }
        String str14 = this.zzjvn;
        if (str14 == null) {
            if (zzcoe.zzjvn != null) {
                return false;
            }
        } else if (!str14.equals(zzcoe.zzjvn)) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzcoe.zzpvl == null || zzcoe.zzpvl.isEmpty() : this.zzpvl.equals(zzcoe.zzpvl);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        Integer num = this.zzjup;
        int i = 0;
        int hashCode2 = (((((hashCode + (num == null ? 0 : num.hashCode())) * 31) + zzflq.hashCode((Object[]) this.zzjuq)) * 31) + zzflq.hashCode((Object[]) this.zzjur)) * 31;
        Long l = this.zzjus;
        int hashCode3 = (hashCode2 + (l == null ? 0 : l.hashCode())) * 31;
        Long l2 = this.zzjut;
        int hashCode4 = (hashCode3 + (l2 == null ? 0 : l2.hashCode())) * 31;
        Long l3 = this.zzjuu;
        int hashCode5 = (hashCode4 + (l3 == null ? 0 : l3.hashCode())) * 31;
        Long l4 = this.zzjuv;
        int hashCode6 = (hashCode5 + (l4 == null ? 0 : l4.hashCode())) * 31;
        Long l5 = this.zzjuw;
        int hashCode7 = (hashCode6 + (l5 == null ? 0 : l5.hashCode())) * 31;
        String str = this.zzjux;
        int hashCode8 = (hashCode7 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.zzda;
        int hashCode9 = (hashCode8 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.zzjuy;
        int hashCode10 = (hashCode9 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.zzjho;
        int hashCode11 = (hashCode10 + (str4 == null ? 0 : str4.hashCode())) * 31;
        Integer num2 = this.zzjuz;
        int hashCode12 = (hashCode11 + (num2 == null ? 0 : num2.hashCode())) * 31;
        String str5 = this.zzjfs;
        int hashCode13 = (hashCode12 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.zzcm;
        int hashCode14 = (hashCode13 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.zzina;
        int hashCode15 = (hashCode14 + (str7 == null ? 0 : str7.hashCode())) * 31;
        Long l6 = this.zzjva;
        int hashCode16 = (hashCode15 + (l6 == null ? 0 : l6.hashCode())) * 31;
        Long l7 = this.zzjvb;
        int hashCode17 = (hashCode16 + (l7 == null ? 0 : l7.hashCode())) * 31;
        String str8 = this.zzjvc;
        int hashCode18 = (hashCode17 + (str8 == null ? 0 : str8.hashCode())) * 31;
        Boolean bool = this.zzjvd;
        int hashCode19 = (hashCode18 + (bool == null ? 0 : bool.hashCode())) * 31;
        String str9 = this.zzjfk;
        int hashCode20 = (hashCode19 + (str9 == null ? 0 : str9.hashCode())) * 31;
        Long l8 = this.zzjve;
        int hashCode21 = (hashCode20 + (l8 == null ? 0 : l8.hashCode())) * 31;
        Integer num3 = this.zzjvf;
        int hashCode22 = (hashCode21 + (num3 == null ? 0 : num3.hashCode())) * 31;
        String str10 = this.zzjgi;
        int hashCode23 = (hashCode22 + (str10 == null ? 0 : str10.hashCode())) * 31;
        String str11 = this.zzjfl;
        int hashCode24 = (hashCode23 + (str11 == null ? 0 : str11.hashCode())) * 31;
        Boolean bool2 = this.zzjvg;
        int hashCode25 = (((hashCode24 + (bool2 == null ? 0 : bool2.hashCode())) * 31) + zzflq.hashCode((Object[]) this.zzjvh)) * 31;
        String str12 = this.zzjfn;
        int hashCode26 = (hashCode25 + (str12 == null ? 0 : str12.hashCode())) * 31;
        Integer num4 = this.zzjvi;
        int hashCode27 = (hashCode26 + (num4 == null ? 0 : num4.hashCode())) * 31;
        Integer num5 = this.zzjvj;
        int hashCode28 = (hashCode27 + (num5 == null ? 0 : num5.hashCode())) * 31;
        Integer num6 = this.zzjvk;
        int hashCode29 = (hashCode28 + (num6 == null ? 0 : num6.hashCode())) * 31;
        String str13 = this.zzjvl;
        int hashCode30 = (hashCode29 + (str13 == null ? 0 : str13.hashCode())) * 31;
        Long l9 = this.zzjvm;
        int hashCode31 = (hashCode30 + (l9 == null ? 0 : l9.hashCode())) * 31;
        Long l10 = this.zzfqm;
        int hashCode32 = (hashCode31 + (l10 == null ? 0 : l10.hashCode())) * 31;
        String str14 = this.zzjvn;
        int hashCode33 = (hashCode32 + (str14 == null ? 0 : str14.hashCode())) * 31;
        if (this.zzpvl != null && !this.zzpvl.isEmpty()) {
            i = this.zzpvl.hashCode();
        }
        return hashCode33 + i;
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            switch (zzcxx) {
                case 0:
                    return this;
                case 8:
                    this.zzjup = Integer.valueOf(zzflj.zzcym());
                    break;
                case 18:
                    int zzb = zzflv.zzb(zzflj, 18);
                    zzcob[] zzcobArr = this.zzjuq;
                    int length = zzcobArr == null ? 0 : zzcobArr.length;
                    int i = zzb + length;
                    zzcob[] zzcobArr2 = new zzcob[i];
                    if (length != 0) {
                        System.arraycopy(zzcobArr, 0, zzcobArr2, 0, length);
                    }
                    while (length < i - 1) {
                        zzcobArr2[length] = new zzcob();
                        zzflj.zza(zzcobArr2[length]);
                        zzflj.zzcxx();
                        length++;
                    }
                    zzcobArr2[length] = new zzcob();
                    zzflj.zza(zzcobArr2[length]);
                    this.zzjuq = zzcobArr2;
                    break;
                case 26:
                    int zzb2 = zzflv.zzb(zzflj, 26);
                    zzcog[] zzcogArr = this.zzjur;
                    int length2 = zzcogArr == null ? 0 : zzcogArr.length;
                    int i2 = zzb2 + length2;
                    zzcog[] zzcogArr2 = new zzcog[i2];
                    if (length2 != 0) {
                        System.arraycopy(zzcogArr, 0, zzcogArr2, 0, length2);
                    }
                    while (length2 < i2 - 1) {
                        zzcogArr2[length2] = new zzcog();
                        zzflj.zza(zzcogArr2[length2]);
                        zzflj.zzcxx();
                        length2++;
                    }
                    zzcogArr2[length2] = new zzcog();
                    zzflj.zza(zzcogArr2[length2]);
                    this.zzjur = zzcogArr2;
                    break;
                case 32:
                    this.zzjus = Long.valueOf(zzflj.zzcyr());
                    break;
                case 40:
                    this.zzjut = Long.valueOf(zzflj.zzcyr());
                    break;
                case 48:
                    this.zzjuu = Long.valueOf(zzflj.zzcyr());
                    break;
                case 56:
                    this.zzjuw = Long.valueOf(zzflj.zzcyr());
                    break;
                case 66:
                    this.zzjux = zzflj.readString();
                    break;
                case 74:
                    this.zzda = zzflj.readString();
                    break;
                case 82:
                    this.zzjuy = zzflj.readString();
                    break;
                case 90:
                    this.zzjho = zzflj.readString();
                    break;
                case 96:
                    this.zzjuz = Integer.valueOf(zzflj.zzcym());
                    break;
                case 106:
                    this.zzjfs = zzflj.readString();
                    break;
                case 114:
                    this.zzcm = zzflj.readString();
                    break;
                case 130:
                    this.zzina = zzflj.readString();
                    break;
                case 136:
                    this.zzjva = Long.valueOf(zzflj.zzcyr());
                    break;
                case 144:
                    this.zzjvb = Long.valueOf(zzflj.zzcyr());
                    break;
                case 154:
                    this.zzjvc = zzflj.readString();
                    break;
                case 160:
                    this.zzjvd = Boolean.valueOf(zzflj.zzcyd());
                    break;
                case 170:
                    this.zzjfk = zzflj.readString();
                    break;
                case 176:
                    this.zzjve = Long.valueOf(zzflj.zzcyr());
                    break;
                case 184:
                    this.zzjvf = Integer.valueOf(zzflj.zzcym());
                    break;
                case Wbxml.EXT_2 /*194*/:
                    this.zzjgi = zzflj.readString();
                    break;
                case 202:
                    this.zzjfl = zzflj.readString();
                    break;
                case 208:
                    this.zzjuv = Long.valueOf(zzflj.zzcyr());
                    break;
                case 224:
                    this.zzjvg = Boolean.valueOf(zzflj.zzcyd());
                    break;
                case 234:
                    int zzb3 = zzflv.zzb(zzflj, 234);
                    zzcoa[] zzcoaArr = this.zzjvh;
                    int length3 = zzcoaArr == null ? 0 : zzcoaArr.length;
                    int i3 = zzb3 + length3;
                    zzcoa[] zzcoaArr2 = new zzcoa[i3];
                    if (length3 != 0) {
                        System.arraycopy(zzcoaArr, 0, zzcoaArr2, 0, length3);
                    }
                    while (length3 < i3 - 1) {
                        zzcoaArr2[length3] = new zzcoa();
                        zzflj.zza(zzcoaArr2[length3]);
                        zzflj.zzcxx();
                        length3++;
                    }
                    zzcoaArr2[length3] = new zzcoa();
                    zzflj.zza(zzcoaArr2[length3]);
                    this.zzjvh = zzcoaArr2;
                    break;
                case 242:
                    this.zzjfn = zzflj.readString();
                    break;
                case 248:
                    this.zzjvi = Integer.valueOf(zzflj.zzcym());
                    break;
                case 256:
                    this.zzjvj = Integer.valueOf(zzflj.zzcym());
                    break;
                case 264:
                    this.zzjvk = Integer.valueOf(zzflj.zzcym());
                    break;
                case 274:
                    this.zzjvl = zzflj.readString();
                    break;
                case 280:
                    this.zzjvm = Long.valueOf(zzflj.zzcyr());
                    break;
                case 288:
                    this.zzfqm = Long.valueOf(zzflj.zzcyr());
                    break;
                case 298:
                    this.zzjvn = zzflj.readString();
                    break;
                default:
                    if (super.zza(zzflj, zzcxx)) {
                        break;
                    } else {
                        return this;
                    }
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        Integer num = this.zzjup;
        if (num != null) {
            zzflk.zzad(1, num.intValue());
        }
        zzcob[] zzcobArr = this.zzjuq;
        int i = 0;
        if (zzcobArr != null && zzcobArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzcob[] zzcobArr2 = this.zzjuq;
                if (i2 >= zzcobArr2.length) {
                    break;
                }
                zzcob zzcob = zzcobArr2[i2];
                if (zzcob != null) {
                    zzflk.zza(2, (zzfls) zzcob);
                }
                i2++;
            }
        }
        zzcog[] zzcogArr = this.zzjur;
        if (zzcogArr != null && zzcogArr.length > 0) {
            int i3 = 0;
            while (true) {
                zzcog[] zzcogArr2 = this.zzjur;
                if (i3 >= zzcogArr2.length) {
                    break;
                }
                zzcog zzcog = zzcogArr2[i3];
                if (zzcog != null) {
                    zzflk.zza(3, (zzfls) zzcog);
                }
                i3++;
            }
        }
        Long l = this.zzjus;
        if (l != null) {
            zzflk.zzf(4, l.longValue());
        }
        Long l2 = this.zzjut;
        if (l2 != null) {
            zzflk.zzf(5, l2.longValue());
        }
        Long l3 = this.zzjuu;
        if (l3 != null) {
            zzflk.zzf(6, l3.longValue());
        }
        Long l4 = this.zzjuw;
        if (l4 != null) {
            zzflk.zzf(7, l4.longValue());
        }
        String str = this.zzjux;
        if (str != null) {
            zzflk.zzp(8, str);
        }
        String str2 = this.zzda;
        if (str2 != null) {
            zzflk.zzp(9, str2);
        }
        String str3 = this.zzjuy;
        if (str3 != null) {
            zzflk.zzp(10, str3);
        }
        String str4 = this.zzjho;
        if (str4 != null) {
            zzflk.zzp(11, str4);
        }
        Integer num2 = this.zzjuz;
        if (num2 != null) {
            zzflk.zzad(12, num2.intValue());
        }
        String str5 = this.zzjfs;
        if (str5 != null) {
            zzflk.zzp(13, str5);
        }
        String str6 = this.zzcm;
        if (str6 != null) {
            zzflk.zzp(14, str6);
        }
        String str7 = this.zzina;
        if (str7 != null) {
            zzflk.zzp(16, str7);
        }
        Long l5 = this.zzjva;
        if (l5 != null) {
            zzflk.zzf(17, l5.longValue());
        }
        Long l6 = this.zzjvb;
        if (l6 != null) {
            zzflk.zzf(18, l6.longValue());
        }
        String str8 = this.zzjvc;
        if (str8 != null) {
            zzflk.zzp(19, str8);
        }
        Boolean bool = this.zzjvd;
        if (bool != null) {
            zzflk.zzl(20, bool.booleanValue());
        }
        String str9 = this.zzjfk;
        if (str9 != null) {
            zzflk.zzp(21, str9);
        }
        Long l7 = this.zzjve;
        if (l7 != null) {
            zzflk.zzf(22, l7.longValue());
        }
        Integer num3 = this.zzjvf;
        if (num3 != null) {
            zzflk.zzad(23, num3.intValue());
        }
        String str10 = this.zzjgi;
        if (str10 != null) {
            zzflk.zzp(24, str10);
        }
        String str11 = this.zzjfl;
        if (str11 != null) {
            zzflk.zzp(25, str11);
        }
        Long l8 = this.zzjuv;
        if (l8 != null) {
            zzflk.zzf(26, l8.longValue());
        }
        Boolean bool2 = this.zzjvg;
        if (bool2 != null) {
            zzflk.zzl(28, bool2.booleanValue());
        }
        zzcoa[] zzcoaArr = this.zzjvh;
        if (zzcoaArr != null && zzcoaArr.length > 0) {
            while (true) {
                zzcoa[] zzcoaArr2 = this.zzjvh;
                if (i >= zzcoaArr2.length) {
                    break;
                }
                zzcoa zzcoa = zzcoaArr2[i];
                if (zzcoa != null) {
                    zzflk.zza(29, (zzfls) zzcoa);
                }
                i++;
            }
        }
        String str12 = this.zzjfn;
        if (str12 != null) {
            zzflk.zzp(30, str12);
        }
        Integer num4 = this.zzjvi;
        if (num4 != null) {
            zzflk.zzad(31, num4.intValue());
        }
        Integer num5 = this.zzjvj;
        if (num5 != null) {
            zzflk.zzad(32, num5.intValue());
        }
        Integer num6 = this.zzjvk;
        if (num6 != null) {
            zzflk.zzad(33, num6.intValue());
        }
        String str13 = this.zzjvl;
        if (str13 != null) {
            zzflk.zzp(34, str13);
        }
        Long l9 = this.zzjvm;
        if (l9 != null) {
            zzflk.zzf(35, l9.longValue());
        }
        Long l10 = this.zzfqm;
        if (l10 != null) {
            zzflk.zzf(36, l10.longValue());
        }
        String str14 = this.zzjvn;
        if (str14 != null) {
            zzflk.zzp(37, str14);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        Integer num = this.zzjup;
        if (num != null) {
            zzq += zzflk.zzag(1, num.intValue());
        }
        zzcob[] zzcobArr = this.zzjuq;
        int i = 0;
        if (zzcobArr != null && zzcobArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzcob[] zzcobArr2 = this.zzjuq;
                if (i2 >= zzcobArr2.length) {
                    break;
                }
                zzcob zzcob = zzcobArr2[i2];
                if (zzcob != null) {
                    zzq += zzflk.zzb(2, (zzfls) zzcob);
                }
                i2++;
            }
        }
        zzcog[] zzcogArr = this.zzjur;
        if (zzcogArr != null && zzcogArr.length > 0) {
            int i3 = 0;
            while (true) {
                zzcog[] zzcogArr2 = this.zzjur;
                if (i3 >= zzcogArr2.length) {
                    break;
                }
                zzcog zzcog = zzcogArr2[i3];
                if (zzcog != null) {
                    zzq += zzflk.zzb(3, (zzfls) zzcog);
                }
                i3++;
            }
        }
        Long l = this.zzjus;
        if (l != null) {
            zzq += zzflk.zzc(4, l.longValue());
        }
        Long l2 = this.zzjut;
        if (l2 != null) {
            zzq += zzflk.zzc(5, l2.longValue());
        }
        Long l3 = this.zzjuu;
        if (l3 != null) {
            zzq += zzflk.zzc(6, l3.longValue());
        }
        Long l4 = this.zzjuw;
        if (l4 != null) {
            zzq += zzflk.zzc(7, l4.longValue());
        }
        String str = this.zzjux;
        if (str != null) {
            zzq += zzflk.zzq(8, str);
        }
        String str2 = this.zzda;
        if (str2 != null) {
            zzq += zzflk.zzq(9, str2);
        }
        String str3 = this.zzjuy;
        if (str3 != null) {
            zzq += zzflk.zzq(10, str3);
        }
        String str4 = this.zzjho;
        if (str4 != null) {
            zzq += zzflk.zzq(11, str4);
        }
        Integer num2 = this.zzjuz;
        if (num2 != null) {
            zzq += zzflk.zzag(12, num2.intValue());
        }
        String str5 = this.zzjfs;
        if (str5 != null) {
            zzq += zzflk.zzq(13, str5);
        }
        String str6 = this.zzcm;
        if (str6 != null) {
            zzq += zzflk.zzq(14, str6);
        }
        String str7 = this.zzina;
        if (str7 != null) {
            zzq += zzflk.zzq(16, str7);
        }
        Long l5 = this.zzjva;
        if (l5 != null) {
            zzq += zzflk.zzc(17, l5.longValue());
        }
        Long l6 = this.zzjvb;
        if (l6 != null) {
            zzq += zzflk.zzc(18, l6.longValue());
        }
        String str8 = this.zzjvc;
        if (str8 != null) {
            zzq += zzflk.zzq(19, str8);
        }
        Boolean bool = this.zzjvd;
        if (bool != null) {
            bool.booleanValue();
            zzq += zzflk.zzlw(20) + 1;
        }
        String str9 = this.zzjfk;
        if (str9 != null) {
            zzq += zzflk.zzq(21, str9);
        }
        Long l7 = this.zzjve;
        if (l7 != null) {
            zzq += zzflk.zzc(22, l7.longValue());
        }
        Integer num3 = this.zzjvf;
        if (num3 != null) {
            zzq += zzflk.zzag(23, num3.intValue());
        }
        String str10 = this.zzjgi;
        if (str10 != null) {
            zzq += zzflk.zzq(24, str10);
        }
        String str11 = this.zzjfl;
        if (str11 != null) {
            zzq += zzflk.zzq(25, str11);
        }
        Long l8 = this.zzjuv;
        if (l8 != null) {
            zzq += zzflk.zzc(26, l8.longValue());
        }
        Boolean bool2 = this.zzjvg;
        if (bool2 != null) {
            bool2.booleanValue();
            zzq += zzflk.zzlw(28) + 1;
        }
        zzcoa[] zzcoaArr = this.zzjvh;
        if (zzcoaArr != null && zzcoaArr.length > 0) {
            while (true) {
                zzcoa[] zzcoaArr2 = this.zzjvh;
                if (i >= zzcoaArr2.length) {
                    break;
                }
                zzcoa zzcoa = zzcoaArr2[i];
                if (zzcoa != null) {
                    zzq += zzflk.zzb(29, (zzfls) zzcoa);
                }
                i++;
            }
        }
        String str12 = this.zzjfn;
        if (str12 != null) {
            zzq += zzflk.zzq(30, str12);
        }
        Integer num4 = this.zzjvi;
        if (num4 != null) {
            zzq += zzflk.zzag(31, num4.intValue());
        }
        Integer num5 = this.zzjvj;
        if (num5 != null) {
            zzq += zzflk.zzag(32, num5.intValue());
        }
        Integer num6 = this.zzjvk;
        if (num6 != null) {
            zzq += zzflk.zzag(33, num6.intValue());
        }
        String str13 = this.zzjvl;
        if (str13 != null) {
            zzq += zzflk.zzq(34, str13);
        }
        Long l9 = this.zzjvm;
        if (l9 != null) {
            zzq += zzflk.zzc(35, l9.longValue());
        }
        Long l10 = this.zzfqm;
        if (l10 != null) {
            zzq += zzflk.zzc(36, l10.longValue());
        }
        String str14 = this.zzjvn;
        return str14 != null ? zzq + zzflk.zzq(37, str14) : zzq;
    }
}
