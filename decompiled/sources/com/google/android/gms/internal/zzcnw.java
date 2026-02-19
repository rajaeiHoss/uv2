package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcnw extends zzflm<zzcnw> {
    public Integer zzjtp = null;
    public String zzjtq = null;
    public Boolean zzjtr = null;
    public String[] zzjts = zzflv.EMPTY_STRING_ARRAY;

    public zzcnw() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzad */
    public final zzcnw zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
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
                            this.zzjtp = Integer.valueOf(zzcym);
                            break;
                        default:
                            StringBuilder sb = new StringBuilder(41);
                            sb.append(zzcym);
                            sb.append(" is not a valid enum MatchType");
                            throw new IllegalArgumentException(sb.toString());
                    }
                } catch (IllegalArgumentException unused) {
                    zzflj.zzmw(zzflj.getPosition());
                    zza(zzflj, zzcxx);
                }
            } else if (zzcxx == 18) {
                this.zzjtq = zzflj.readString();
            } else if (zzcxx == 24) {
                this.zzjtr = Boolean.valueOf(zzflj.zzcyd());
            } else if (zzcxx == 34) {
                int zzb = zzflv.zzb(zzflj, 34);
                String[] strArr = this.zzjts;
                int length = strArr == null ? 0 : strArr.length;
                int i = zzb + length;
                String[] strArr2 = new String[i];
                if (length != 0) {
                    System.arraycopy(strArr, 0, strArr2, 0, length);
                }
                while (length < i - 1) {
                    strArr2[length] = zzflj.readString();
                    zzflj.zzcxx();
                    length++;
                }
                strArr2[length] = zzflj.readString();
                this.zzjts = strArr2;
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcnw)) {
            return false;
        }
        zzcnw zzcnw = (zzcnw) obj;
        Integer num = this.zzjtp;
        if (num == null) {
            if (zzcnw.zzjtp != null) {
                return false;
            }
        } else if (!num.equals(zzcnw.zzjtp)) {
            return false;
        }
        String str = this.zzjtq;
        if (str == null) {
            if (zzcnw.zzjtq != null) {
                return false;
            }
        } else if (!str.equals(zzcnw.zzjtq)) {
            return false;
        }
        Boolean bool = this.zzjtr;
        if (bool == null) {
            if (zzcnw.zzjtr != null) {
                return false;
            }
        } else if (!bool.equals(zzcnw.zzjtr)) {
            return false;
        }
        if (!zzflq.equals((Object[]) this.zzjts, (Object[]) zzcnw.zzjts)) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzcnw.zzpvl == null || zzcnw.zzpvl.isEmpty() : this.zzpvl.equals(zzcnw.zzpvl);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        Integer num = this.zzjtp;
        int i = 0;
        int intValue = (hashCode + (num == null ? 0 : num.intValue())) * 31;
        String str = this.zzjtq;
        int hashCode2 = (intValue + (str == null ? 0 : str.hashCode())) * 31;
        Boolean bool = this.zzjtr;
        int hashCode3 = (((hashCode2 + (bool == null ? 0 : bool.hashCode())) * 31) + zzflq.hashCode((Object[]) this.zzjts)) * 31;
        if (this.zzpvl != null && !this.zzpvl.isEmpty()) {
            i = this.zzpvl.hashCode();
        }
        return hashCode3 + i;
    }

    public final void zza(zzflk zzflk) throws IOException {
        Integer num = this.zzjtp;
        if (num != null) {
            zzflk.zzad(1, num.intValue());
        }
        String str = this.zzjtq;
        if (str != null) {
            zzflk.zzp(2, str);
        }
        Boolean bool = this.zzjtr;
        if (bool != null) {
            zzflk.zzl(3, bool.booleanValue());
        }
        String[] strArr = this.zzjts;
        if (strArr != null && strArr.length > 0) {
            int i = 0;
            while (true) {
                String[] strArr2 = this.zzjts;
                if (i >= strArr2.length) {
                    break;
                }
                String str2 = strArr2[i];
                if (str2 != null) {
                    zzflk.zzp(4, str2);
                }
                i++;
            }
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        Integer num = this.zzjtp;
        if (num != null) {
            zzq += zzflk.zzag(1, num.intValue());
        }
        String str = this.zzjtq;
        if (str != null) {
            zzq += zzflk.zzq(2, str);
        }
        Boolean bool = this.zzjtr;
        if (bool != null) {
            bool.booleanValue();
            zzq += zzflk.zzlw(3) + 1;
        }
        String[] strArr = this.zzjts;
        if (strArr == null || strArr.length <= 0) {
            return zzq;
        }
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            String[] strArr2 = this.zzjts;
            if (i >= strArr2.length) {
                return zzq + i2 + (i3 * 1);
            }
            String str2 = strArr2[i];
            if (str2 != null) {
                i3++;
                i2 += zzflk.zztx(str2);
            }
            i++;
        }
    }
}
