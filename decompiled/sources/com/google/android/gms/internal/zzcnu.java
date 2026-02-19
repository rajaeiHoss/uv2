package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcnu extends zzflm<zzcnu> {
    public Integer zzjth = null;
    public Boolean zzjti = null;
    public String zzjtj = null;
    public String zzjtk = null;
    public String zzjtl = null;

    public zzcnu() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzac */
    public final zzcnu zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                try {
                    int zzcym = zzflj.zzcym();
                    if (!(zzcym == 0 || zzcym == 1 || zzcym == 2 || zzcym == 3)) {
                        if (zzcym != 4) {
                            StringBuilder sb = new StringBuilder(46);
                            sb.append(zzcym);
                            sb.append(" is not a valid enum ComparisonType");
                            throw new IllegalArgumentException(sb.toString());
                        }
                    }
                    this.zzjth = Integer.valueOf(zzcym);
                } catch (IllegalArgumentException unused) {
                    zzflj.zzmw(zzflj.getPosition());
                    zza(zzflj, zzcxx);
                }
            } else if (zzcxx == 16) {
                this.zzjti = Boolean.valueOf(zzflj.zzcyd());
            } else if (zzcxx == 26) {
                this.zzjtj = zzflj.readString();
            } else if (zzcxx == 34) {
                this.zzjtk = zzflj.readString();
            } else if (zzcxx == 42) {
                this.zzjtl = zzflj.readString();
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcnu)) {
            return false;
        }
        zzcnu zzcnu = (zzcnu) obj;
        Integer num = this.zzjth;
        if (num == null) {
            if (zzcnu.zzjth != null) {
                return false;
            }
        } else if (!num.equals(zzcnu.zzjth)) {
            return false;
        }
        Boolean bool = this.zzjti;
        if (bool == null) {
            if (zzcnu.zzjti != null) {
                return false;
            }
        } else if (!bool.equals(zzcnu.zzjti)) {
            return false;
        }
        String str = this.zzjtj;
        if (str == null) {
            if (zzcnu.zzjtj != null) {
                return false;
            }
        } else if (!str.equals(zzcnu.zzjtj)) {
            return false;
        }
        String str2 = this.zzjtk;
        if (str2 == null) {
            if (zzcnu.zzjtk != null) {
                return false;
            }
        } else if (!str2.equals(zzcnu.zzjtk)) {
            return false;
        }
        String str3 = this.zzjtl;
        if (str3 == null) {
            if (zzcnu.zzjtl != null) {
                return false;
            }
        } else if (!str3.equals(zzcnu.zzjtl)) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzcnu.zzpvl == null || zzcnu.zzpvl.isEmpty() : this.zzpvl.equals(zzcnu.zzpvl);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        Integer num = this.zzjth;
        int i = 0;
        int intValue = (hashCode + (num == null ? 0 : num.intValue())) * 31;
        Boolean bool = this.zzjti;
        int hashCode2 = (intValue + (bool == null ? 0 : bool.hashCode())) * 31;
        String str = this.zzjtj;
        int hashCode3 = (hashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.zzjtk;
        int hashCode4 = (hashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.zzjtl;
        int hashCode5 = (hashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
        if (this.zzpvl != null && !this.zzpvl.isEmpty()) {
            i = this.zzpvl.hashCode();
        }
        return hashCode5 + i;
    }

    public final void zza(zzflk zzflk) throws IOException {
        Integer num = this.zzjth;
        if (num != null) {
            zzflk.zzad(1, num.intValue());
        }
        Boolean bool = this.zzjti;
        if (bool != null) {
            zzflk.zzl(2, bool.booleanValue());
        }
        String str = this.zzjtj;
        if (str != null) {
            zzflk.zzp(3, str);
        }
        String str2 = this.zzjtk;
        if (str2 != null) {
            zzflk.zzp(4, str2);
        }
        String str3 = this.zzjtl;
        if (str3 != null) {
            zzflk.zzp(5, str3);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        Integer num = this.zzjth;
        if (num != null) {
            zzq += zzflk.zzag(1, num.intValue());
        }
        Boolean bool = this.zzjti;
        if (bool != null) {
            bool.booleanValue();
            zzq += zzflk.zzlw(2) + 1;
        }
        String str = this.zzjtj;
        if (str != null) {
            zzq += zzflk.zzq(3, str);
        }
        String str2 = this.zzjtk;
        if (str2 != null) {
            zzq += zzflk.zzq(4, str2);
        }
        String str3 = this.zzjtl;
        return str3 != null ? zzq + zzflk.zzq(5, str3) : zzq;
    }
}
