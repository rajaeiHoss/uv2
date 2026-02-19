package com.google.android.gms.internal;

import java.util.Arrays;
import java.util.List;

public final class zzdco extends zzdcr {
    private final String mName;
    private final List<String> zzhfd;
    private zzdbb zzkxx = null;
    private final List<zzdkb> zzkzy;

    public zzdco(zzdbb zzdbb, String str, List<String> list, List<zzdkb> list2) {
        this.mName = str;
        this.zzhfd = list;
        this.zzkzy = list2;
    }

    public final String getName() {
        return this.mName;
    }

    public final String toString() {
        String str = this.mName;
        String obj = this.zzhfd.toString();
        String obj2 = this.zzkzy.toString();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 26 + String.valueOf(obj).length() + String.valueOf(obj2).length());
        sb.append(str);
        sb.append("\n\tparams: ");
        sb.append(obj);
        sb.append("\n\t: statements: ");
        sb.append(obj2);
        return sb.toString();
    }

    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        String str;
        zzdjq<?> zzdjq;
        try {
            zzdbb zzbjd = this.zzkxx.zzbjd();
            for (int i = 0; i < this.zzhfd.size(); i++) {
                if (zzdjqArr.length > i) {
                    str = this.zzhfd.get(i);
                    zzdjq = zzdjqArr[i];
                } else {
                    str = this.zzhfd.get(i);
                    zzdjq = zzdjw.zzlcz;
                }
                zzbjd.zza(str, zzdjq);
            }
            zzbjd.zza("arguments", new zzdjx(Arrays.asList(zzdjqArr)));
            for (zzdkb zza : this.zzkzy) {
                zzdjq zza2 = zzdke.zza(zzbjd, zza);
                if ((zza2 instanceof zzdjw) && ((zzdjw) zza2).zzbkq()) {
                    return (zzdjq) ((zzdjw) zza2).value();
                }
            }
        } catch (RuntimeException e) {
            String str2 = this.mName;
            String message = e.getMessage();
            StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 33 + String.valueOf(message).length());
            sb.append("Internal error - Function call: ");
            sb.append(str2);
            sb.append("\n");
            sb.append(message);
            zzdal.e(sb.toString());
        }
        return zzdjw.zzlcz;
    }

    public final void zza(zzdbb zzdbb) {
        this.zzkxx = zzdbb;
    }
}
