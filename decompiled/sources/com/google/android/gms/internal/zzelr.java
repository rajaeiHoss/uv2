package com.google.android.gms.internal;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class zzelr {
    public static final zzelr zznmi = new zzelr();
    private zzenf zznme = zzens.zzccy();
    private Integer zznmj;
    private int zznmk;
    private zzenn zznml = null;
    private zzemq zznmm = null;
    private zzenn zznmn = null;
    private zzemq zznmo = null;
    private String zznmp = null;

    public static zzelr zzao(Map<String, Object> map) {
        zzenf zzenf;
        zzelr zzelr = new zzelr();
        zzelr.zznmj = (Integer) map.get("l");
        if (map.containsKey("sp")) {
            zzelr.zznml = zze(zzenq.zza(map.get("sp"), zzene.zzcco()));
            String str = (String) map.get("sn");
            if (str != null) {
                zzelr.zznmm = zzemq.zzqf(str);
            }
        }
        if (map.containsKey("ep")) {
            zzelr.zznmn = zze(zzenq.zza(map.get("ep"), zzene.zzcco()));
            String str2 = (String) map.get("en");
            if (str2 != null) {
                zzelr.zznmo = zzemq.zzqf(str2);
            }
        }
        String str3 = (String) map.get("vf");
        if (str3 != null) {
            zzelr.zznmk = str3.equals("l") ? zzelt.zznmr : zzelt.zznms;
        }
        String str4 = (String) map.get("i");
        if (str4 != null) {
            if (str4.equals(".value")) {
                zzenf = zzenx.zzccz();
            } else if (str4.equals(".key")) {
                zzenf = zzenh.zzccu();
            } else if (!str4.equals(".priority")) {
                zzenf = new zzenr(new zzegu(str4));
            } else {
                throw new IllegalStateException("queryDefinition shouldn't ever be .priority since it's the default");
            }
            zzelr.zznme = zzenf;
        }
        return zzelr;
    }

    private final zzelr zzcbb() {
        zzelr zzelr = new zzelr();
        zzelr.zznmj = this.zznmj;
        zzelr.zznml = this.zznml;
        zzelr.zznmm = this.zznmm;
        zzelr.zznmn = this.zznmn;
        zzelr.zznmo = this.zznmo;
        zzelr.zznmk = this.zznmk;
        zzelr.zznme = this.zznme;
        return zzelr;
    }

    private static zzenn zze(zzenn zzenn) {
        if ((zzenn instanceof zzenv) || (zzenn instanceof zzemp) || (zzenn instanceof zzend) || (zzenn instanceof zzene)) {
            return zzenn;
        }
        if (zzenn instanceof zzenl) {
            return new zzend(Double.valueOf(((Long) zzenn.getValue()).doubleValue()), zzene.zzcco());
        }
        String valueOf = String.valueOf(zzenn.getValue());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 43);
        sb.append("Unexpected value passed to normalizeValue: ");
        sb.append(valueOf);
        throw new IllegalStateException(sb.toString());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        zzelr zzelr = (zzelr) obj;
        Integer num = this.zznmj;
        if (num == null ? zzelr.zznmj != null : !num.equals(zzelr.zznmj)) {
            return false;
        }
        zzenf zzenf = this.zznme;
        if (zzenf == null ? zzelr.zznme != null : !zzenf.equals(zzelr.zznme)) {
            return false;
        }
        zzemq zzemq = this.zznmo;
        if (zzemq == null ? zzelr.zznmo != null : !zzemq.equals(zzelr.zznmo)) {
            return false;
        }
        zzenn zzenn = this.zznmn;
        if (zzenn == null ? zzelr.zznmn != null : !zzenn.equals(zzelr.zznmn)) {
            return false;
        }
        zzemq zzemq2 = this.zznmm;
        if (zzemq2 == null ? zzelr.zznmm != null : !zzemq2.equals(zzelr.zznmm)) {
            return false;
        }
        zzenn zzenn2 = this.zznml;
        if (zzenn2 == null ? zzelr.zznml == null : zzenn2.equals(zzelr.zznml)) {
            return zzcbc() == zzelr.zzcbc();
        }
        return false;
    }

    public final int getLimit() {
        if (zzcay()) {
            return this.zznmj.intValue();
        }
        throw new IllegalArgumentException("Cannot get limit if limit has not been set");
    }

    public final int hashCode() {
        Integer num = this.zznmj;
        int i = 0;
        int intValue = (((num != null ? num.intValue() : 0) * 31) + (zzcbc() ? 1231 : 1237)) * 31;
        zzenn zzenn = this.zznml;
        int hashCode = (intValue + (zzenn != null ? zzenn.hashCode() : 0)) * 31;
        zzemq zzemq = this.zznmm;
        int hashCode2 = (hashCode + (zzemq != null ? zzemq.hashCode() : 0)) * 31;
        zzenn zzenn2 = this.zznmn;
        int hashCode3 = (hashCode2 + (zzenn2 != null ? zzenn2.hashCode() : 0)) * 31;
        zzemq zzemq2 = this.zznmo;
        int hashCode4 = (hashCode3 + (zzemq2 != null ? zzemq2.hashCode() : 0)) * 31;
        zzenf zzenf = this.zznme;
        if (zzenf != null) {
            i = zzenf.hashCode();
        }
        return hashCode4 + i;
    }

    public final boolean isDefault() {
        return zzcbe() && this.zznme.equals(zzens.zzccy());
    }

    public final String toString() {
        return zzcbd().toString();
    }

    public final zzelr zza(zzenf zzenf) {
        zzelr zzcbb = zzcbb();
        zzcbb.zznme = zzenf;
        return zzcbb;
    }

    public final zzelr zza(zzenn zzenn, zzemq zzemq) {
        zzepd.zzcw(!(zzenn instanceof zzenl));
        zzelr zzcbb = zzcbb();
        zzcbb.zznml = zzenn;
        zzcbb.zznmm = zzemq;
        return zzcbb;
    }

    public final zzelr zzb(zzenn zzenn, zzemq zzemq) {
        zzepd.zzcw(!(zzenn instanceof zzenl));
        zzelr zzcbb = zzcbb();
        zzcbb.zznmn = zzenn;
        zzcbb.zznmo = zzemq;
        return zzcbb;
    }

    public final boolean zzcas() {
        return this.zznml != null;
    }

    public final zzenn zzcat() {
        if (zzcas()) {
            return this.zznml;
        }
        throw new IllegalArgumentException("Cannot get index start value if start has not been set");
    }

    public final zzemq zzcau() {
        if (zzcas()) {
            zzemq zzemq = this.zznmm;
            return zzemq != null ? zzemq : zzemq.zzcbw();
        }
        throw new IllegalArgumentException("Cannot get index start name if start has not been set");
    }

    public final boolean zzcav() {
        return this.zznmn != null;
    }

    public final zzenn zzcaw() {
        if (zzcav()) {
            return this.zznmn;
        }
        throw new IllegalArgumentException("Cannot get index end value if start has not been set");
    }

    public final zzemq zzcax() {
        if (zzcav()) {
            zzemq zzemq = this.zznmo;
            return zzemq != null ? zzemq : zzemq.zzcbx();
        }
        throw new IllegalArgumentException("Cannot get index end name if start has not been set");
    }

    public final boolean zzcay() {
        return this.zznmj != null;
    }

    public final boolean zzcaz() {
        return zzcay() && this.zznmk != 0;
    }

    public final zzenf zzcba() {
        return this.zznme;
    }

    public final boolean zzcbc() {
        int i = this.zznmk;
        return i != 0 ? i == zzelt.zznmr : zzcas();
    }

    public final Map<String, Object> zzcbd() {
        HashMap hashMap = new HashMap();
        if (zzcas()) {
            hashMap.put("sp", this.zznml.getValue());
            zzemq zzemq = this.zznmm;
            if (zzemq != null) {
                hashMap.put("sn", zzemq.asString());
            }
        }
        if (zzcav()) {
            hashMap.put("ep", this.zznmn.getValue());
            zzemq zzemq2 = this.zznmo;
            if (zzemq2 != null) {
                hashMap.put("en", zzemq2.asString());
            }
        }
        Integer num = this.zznmj;
        if (num != null) {
            hashMap.put("l", num);
            int i = this.zznmk;
            if (i == 0) {
                i = zzcas() ? zzelt.zznmr : zzelt.zznms;
            }
            int i2 = zzels.zznmq[i - 1];
            if (i2 == 1) {
                hashMap.put("vf", "l");
            } else if (i2 == 2) {
                hashMap.put("vf", "r");
            }
        }
        if (!this.zznme.equals(zzens.zzccy())) {
            hashMap.put("i", this.zznme.zzccq());
        }
        return hashMap;
    }

    public final boolean zzcbe() {
        return !zzcas() && !zzcav() && !zzcay();
    }

    public final String zzcbf() {
        if (this.zznmp == null) {
            try {
                this.zznmp = zzeor.zzbx(zzcbd());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return this.zznmp;
    }

    public final zzemg zzcbg() {
        return zzcbe() ? new zzeme(this.zznme) : zzcay() ? new zzemf(this) : new zzemi(this);
    }

    public final zzelr zzhi(int i) {
        zzelr zzcbb = zzcbb();
        zzcbb.zznmj = Integer.valueOf(i);
        zzcbb.zznmk = zzelt.zznmr;
        return zzcbb;
    }

    public final zzelr zzhj(int i) {
        zzelr zzcbb = zzcbb();
        zzcbb.zznmj = Integer.valueOf(i);
        zzcbb.zznmk = zzelt.zznms;
        return zzcbb;
    }
}
