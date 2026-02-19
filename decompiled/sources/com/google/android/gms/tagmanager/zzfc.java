package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzbi;
import com.google.android.gms.internal.zzbt;
import com.google.android.gms.internal.zzdkh;
import com.google.android.gms.internal.zzdkj;
import com.google.android.gms.internal.zzdkl;
import com.google.android.gms.internal.zzdkn;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

final class zzfc {
    private static final zzea<zzbt> zzksk = new zzea<>(zzgk.zzbil(), true);
    private final DataLayer zzknd;
    private final zzdkl zzksl;
    private final zzbo zzksm;
    private final Map<String, zzbr> zzksn = new HashMap();
    private final Map<String, zzbr> zzkso;
    private final Map<String, zzbr> zzksp;
    private final zzp<zzdkj, zzea<zzbt>> zzksq;
    private final zzp<String, zzfi> zzksr;
    private final Set<zzdkn> zzkss;
    private final Map<String, zzfj> zzkst;
    private volatile String zzksu;
    private int zzksv;

    public zzfc(Context context, zzdkl zzdkl, DataLayer dataLayer, zzan zzan, zzan zzan2, zzbo zzbo) {
        Objects.requireNonNull(zzdkl, "resource cannot be null");
        this.zzksl = zzdkl;
        HashSet<zzdkn> hashSet = new HashSet<>(zzdkl.zzbkb());
        this.zzkss = hashSet;
        this.zzknd = dataLayer;
        this.zzksm = zzbo;
        zzfd zzfd = new zzfd(this);
        new zzq();
        this.zzksq = zzq.zza(1048576, zzfd);
        zzfe zzfe = new zzfe(this);
        new zzq();
        this.zzksr = zzq.zza(1048576, zzfe);
        zzb(new zzm(context));
        zzb(new zzam(zzan2));
        zzb(new zzaz(dataLayer));
        zzb(new zzgl(context, dataLayer));
        this.zzkso = new HashMap();
        zzc(new zzak());
        zzc(new zzbl());
        zzc(new zzbm());
        zzc(new com.google.android.gms.tagmanager.zzbt());
        zzc(new zzbu());
        zzc(new zzdf());
        zzc(new zzdg());
        zzc(new zzem());
        zzc(new zzfz());
        this.zzksp = new HashMap();
        zza((zzbr) new zze(context));
        zza((zzbr) new zzf(context));
        zza((zzbr) new zzh(context));
        zza((zzbr) new zzi(context));
        zza((zzbr) new zzj(context));
        zza((zzbr) new zzk(context));
        zza((zzbr) new zzl(context));
        zza((zzbr) new zzt());
        zza((zzbr) new zzaj(zzdkl.getVersion()));
        zza((zzbr) new zzam(zzan));
        zza((zzbr) new zzas(dataLayer));
        zza((zzbr) new zzbc(context));
        zza((zzbr) new zzbd());
        zza((zzbr) new zzbk());
        zza((zzbr) new zzbp(this));
        zza((zzbr) new zzbv());
        zza((zzbr) new zzbw());
        zza((zzbr) new zzcw(context));
        zza((zzbr) new zzcy());
        zza((zzbr) new zzde());
        zza((zzbr) new zzdl());
        zza((zzbr) new zzdn(context));
        zza((zzbr) new zzeb());
        zza((zzbr) new zzef());
        zza((zzbr) new zzej());
        zza((zzbr) new zzel());
        zza((zzbr) new zzen(context));
        zza((zzbr) new zzfk());
        zza((zzbr) new zzfl());
        zza((zzbr) new zzgf());
        zza((zzbr) new zzgm());
        this.zzkst = new HashMap();
        for (zzdkn zzdkn : hashSet) {
            for (int i = 0; i < zzdkn.zzbkz().size(); i++) {
                zzdkj zzdkj = zzdkn.zzbkz().get(i);
                zzfj zzg = zzg(this.zzkst, zza(zzdkj));
                zzg.zza(zzdkn);
                zzg.zza(zzdkn, zzdkj);
                zzg.zza(zzdkn, "Unknown");
            }
            for (int i2 = 0; i2 < zzdkn.zzbla().size(); i2++) {
                zzdkj zzdkj2 = zzdkn.zzbla().get(i2);
                zzfj zzg2 = zzg(this.zzkst, zza(zzdkj2));
                zzg2.zza(zzdkn);
                zzg2.zzb(zzdkn, zzdkj2);
                zzg2.zzb(zzdkn, "Unknown");
            }
        }
        for (Map.Entry next : this.zzksl.zzbkx().entrySet()) {
            for (Object item : (List) next.getValue()) {
                zzdkj zzdkj3 = (zzdkj) item;
                if (!zzgk.zzh(zzdkj3.zzbkd().get(zzbi.NOT_DEFAULT_MACRO.toString())).booleanValue()) {
                    zzg(this.zzkst, (String) next.getKey()).zzb(zzdkj3);
                }
            }
        }
    }

    private final zzea<zzbt> zza(zzbt zzbt, Set<String> set, zzgn zzgn) {
        if (!zzbt.zzyu) {
            return new zzea<>(zzbt, true);
        }
        int i = zzbt.type;
        if (i == 2) {
            zzbt zzl = zzdkh.zzl(zzbt);
            zzl.zzyl = new zzbt[zzbt.zzyl.length];
            for (int i2 = 0; i2 < zzbt.zzyl.length; i2++) {
                zzea<zzbt> zza = zza(zzbt.zzyl[i2], set, zzgn.zzfa(i2));
                zzea<zzbt> zzea = zzksk;
                if (zza == zzea) {
                    return zzea;
                }
                zzl.zzyl[i2] = zza.getObject();
            }
            return new zzea<>(zzl, false);
        } else if (i == 3) {
            zzbt zzl2 = zzdkh.zzl(zzbt);
            if (zzbt.zzym.length != zzbt.zzyn.length) {
                String valueOf = String.valueOf(zzbt.toString());
                zzdj.e(valueOf.length() != 0 ? "Invalid serving value: ".concat(valueOf) : new String("Invalid serving value: "));
                return zzksk;
            }
            zzl2.zzym = new zzbt[zzbt.zzym.length];
            zzl2.zzyn = new zzbt[zzbt.zzym.length];
            for (int i3 = 0; i3 < zzbt.zzym.length; i3++) {
                zzea<zzbt> zza2 = zza(zzbt.zzym[i3], set, zzgn.zzfb(i3));
                zzea<zzbt> zza3 = zza(zzbt.zzyn[i3], set, zzgn.zzfc(i3));
                zzea<zzbt> zzea2 = zzksk;
                if (zza2 == zzea2 || zza3 == zzea2) {
                    return zzea2;
                }
                zzl2.zzym[i3] = zza2.getObject();
                zzl2.zzyn[i3] = zza3.getObject();
            }
            return new zzea<>(zzl2, false);
        } else if (i != 4) {
            if (i != 7) {
                int i4 = zzbt.type;
                StringBuilder sb = new StringBuilder(25);
                sb.append("Unknown type: ");
                sb.append(i4);
                zzdj.e(sb.toString());
                return zzksk;
            }
            zzbt zzl3 = zzdkh.zzl(zzbt);
            zzl3.zzys = new zzbt[zzbt.zzys.length];
            for (int i5 = 0; i5 < zzbt.zzys.length; i5++) {
                zzea<zzbt> zza4 = zza(zzbt.zzys[i5], set, zzgn.zzfd(i5));
                zzea<zzbt> zzea3 = zzksk;
                if (zza4 == zzea3) {
                    return zzea3;
                }
                zzl3.zzys[i5] = zza4.getObject();
            }
            return new zzea<>(zzl3, false);
        } else if (set.contains(zzbt.zzyo)) {
            String str = zzbt.zzyo;
            String obj = set.toString();
            StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 79 + String.valueOf(obj).length());
            sb2.append("Macro cycle detected.  Current macro reference: ");
            sb2.append(str);
            sb2.append(".  Previous macro references: ");
            sb2.append(obj);
            sb2.append(".");
            zzdj.e(sb2.toString());
            return zzksk;
        } else {
            set.add(zzbt.zzyo);
            zzea<zzbt> zza5 = zzgo.zza(zza(zzbt.zzyo, set, zzgn.zzbhc()), zzbt.zzyt);
            set.remove(zzbt.zzyo);
            return zza5;
        }
    }

    private final zzea<Boolean> zza(zzdkj zzdkj, Set<String> set, zzeo zzeo) {
        zzea<zzbt> zza = zza(this.zzkso, zzdkj, set, zzeo);
        Boolean zzh = zzgk.zzh(zza.getObject());
        zzeo.zza(zzgk.zzam(zzh));
        return new zzea<>(zzh, zza.zzbhd());
    }

    private final zzea<Boolean> zza(zzdkn zzdkn, Set<String> set, zzer zzer) {
        boolean z = true;
        Iterator<zzdkj> it = zzdkn.zzbkg().iterator();
        while (true) {
            while (true) {
                if (it.hasNext()) {
                    zzea<Boolean> zza = zza(it.next(), set, zzer.zzbgv());
                    if (zza.getObject().booleanValue()) {
                        zzer.zzc(zzgk.zzam(false));
                        return new zzea<>(false, zza.zzbhd());
                    } else {
                        z = z && zza.zzbhd();
                    }
                } else {
                    for (zzdkj zza2 : zzdkn.zzbkf()) {
                        zzea<Boolean> zza3 = zza(zza2, set, zzer.zzbgw());
                        if (!zza3.getObject().booleanValue()) {
                            zzer.zzc(zzgk.zzam(false));
                            return new zzea<>(false, zza3.zzbhd());
                        }
                        z = z && zza3.zzbhd();
                    }
                    zzer.zzc(zzgk.zzam(true));
                    return new zzea<>(true, z);
                }
            }
        }
    }

    private final zzea<zzbt> zza(String str, Set<String> set, zzdm zzdm) {
        zzdkj zzdkj;
        this.zzksv++;
        zzfi zzfi = this.zzksr.get(str);
        if (zzfi != null) {
            this.zzksm.zzbgo();
            zza(zzfi.zzbhr(), set);
            this.zzksv--;
            return zzfi.zzbhq();
        }
        zzfj zzfj = this.zzkst.get(str);
        if (zzfj == null) {
            String zzbhp = zzbhp();
            StringBuilder sb = new StringBuilder(String.valueOf(zzbhp).length() + 15 + String.valueOf(str).length());
            sb.append(zzbhp);
            sb.append("Invalid macro: ");
            sb.append(str);
            zzdj.e(sb.toString());
            this.zzksv--;
            return zzksk;
        }
        zzea<Set<zzdkj>> zza = zza(str, zzfj.zzbhs(), zzfj.zzbht(), zzfj.zzbhu(), zzfj.zzbhw(), zzfj.zzbhv(), set, zzdm.zzbgd());
        if (zza.getObject().isEmpty()) {
            zzdkj = zzfj.zzbhx();
        } else {
            if (zza.getObject().size() > 1) {
                String zzbhp2 = zzbhp();
                StringBuilder sb2 = new StringBuilder(String.valueOf(zzbhp2).length() + 37 + String.valueOf(str).length());
                sb2.append(zzbhp2);
                sb2.append("Multiple macros active for macroName ");
                sb2.append(str);
                zzdj.zzcz(sb2.toString());
            }
            zzdkj = (zzdkj) zza.getObject().iterator().next();
        }
        if (zzdkj == null) {
            this.zzksv--;
            return zzksk;
        }
        zzea<zzbt> zza2 = zza(this.zzksp, zzdkj, set, zzdm.zzbgu());
        boolean z = zza.zzbhd() && zza2.zzbhd();
        zzea<zzbt> zzea = zzksk;
        if (zza2 != zzea) {
            zzea = new zzea<>(zza2.getObject(), z);
        }
        zzbt zzbhr = zzdkj.zzbhr();
        if (zzea.zzbhd()) {
            this.zzksr.zzf(str, new zzfi(zzea, zzbhr));
        }
        zza(zzbhr, set);
        this.zzksv--;
        return zzea;
    }

    private final zzea<Set<zzdkj>> zza(String str, Set<zzdkn> set, Map<zzdkn, List<zzdkj>> map, Map<zzdkn, List<String>> map2, Map<zzdkn, List<zzdkj>> map3, Map<zzdkn, List<String>> map4, Set<String> set2, zzfb zzfb) {
        return zza(set, set2, (zzfh) new zzff(this, map, map2, map3, map4), zzfb);
    }

    private final zzea<zzbt> zza(Map<String, zzbr> map, zzdkj zzdkj, Set<String> set, zzeo zzeo) {
        String sb;
        zzbt zzbt = zzdkj.zzbkd().get(zzbi.FUNCTION.toString());
        if (zzbt == null) {
            sb = "No function id in properties";
        } else {
            String str = zzbt.zzyp;
            zzbr zzbr = map.get(str);
            if (zzbr == null) {
                sb = String.valueOf(str).concat(" has no backing implementation.");
            } else {
                zzea<zzbt> zzea = this.zzksq.get(zzdkj);
                if (zzea != null) {
                    this.zzksm.zzbgo();
                    return zzea;
                }
                HashMap hashMap = new HashMap();
                boolean z = true;
                boolean z2 = true;
                for (Map.Entry next : zzdkj.zzbkd().entrySet()) {
                    zzea<zzbt> zza = zza((zzbt) next.getValue(), set, zzeo.zzlz((String) next.getKey()).zzb((zzbt) next.getValue()));
                    zzea<zzbt> zzea2 = zzksk;
                    if (zza == zzea2) {
                        return zzea2;
                    }
                    if (zza.zzbhd()) {
                        zzdkj.zza((String) next.getKey(), zza.getObject());
                    } else {
                        z2 = false;
                    }
                    hashMap.put((String) next.getKey(), zza.getObject());
                }
                if (!zzbr.zzd(hashMap.keySet())) {
                    String valueOf = String.valueOf(zzbr.zzbgq());
                    String valueOf2 = String.valueOf(hashMap.keySet());
                    StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 43 + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length());
                    sb2.append("Incorrect keys for function ");
                    sb2.append(str);
                    sb2.append(" required ");
                    sb2.append(valueOf);
                    sb2.append(" had ");
                    sb2.append(valueOf2);
                    sb = sb2.toString();
                } else {
                    if (!z2 || !zzbr.zzbfh()) {
                        z = false;
                    }
                    zzea<zzbt> zzea3 = new zzea<>(zzbr.zzx(hashMap), z);
                    if (z) {
                        this.zzksq.zzf(zzdkj, zzea3);
                    }
                    zzeo.zza(zzea3.getObject());
                    return zzea3;
                }
            }
        }
        zzdj.e(sb);
        return zzksk;
    }

    private final zzea<Set<zzdkj>> zza(Set<zzdkn> set, Set<String> set2, zzfh zzfh, zzfb zzfb) {
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        Iterator<zzdkn> it = set.iterator();
        while (true) {
            boolean z = true;
            while (true) {
                if (it.hasNext()) {
                    zzdkn next = it.next();
                    zzer zzbhb = zzfb.zzbhb();
                    zzea<Boolean> zza = zza(next, set2, zzbhb);
                    if (zza.getObject().booleanValue()) {
                        zzfh.zza(next, hashSet, hashSet2, zzbhb);
                    }
                    if (!z || !zza.zzbhd()) {
                        z = false;
                    }
                } else {
                    hashSet.removeAll(hashSet2);
                    zzfb.zze(hashSet);
                    return new zzea<>(hashSet, z);
                }
            }
        }
    }

    private static String zza(zzdkj zzdkj) {
        return zzgk.zzd(zzdkj.zzbkd().get(zzbi.INSTANCE_NAME.toString()));
    }

    private final void zza(zzbt zzbt, Set<String> set) {
        zzea<zzbt> zza;
        if (zzbt != null && (zza = zza(zzbt, set, (zzgn) new zzdy())) != zzksk) {
            Object zzi = zzgk.zzi(zza.getObject());
            if (zzi instanceof Map) {
                this.zzknd.push((Map) zzi);
            } else if (zzi instanceof List) {
                for (Object next : (List) zzi) {
                    if (next instanceof Map) {
                        this.zzknd.push((Map) next);
                    } else {
                        zzdj.zzcz("pushAfterEvaluate: value not a Map");
                    }
                }
            } else {
                zzdj.zzcz("pushAfterEvaluate: value not a Map or List");
            }
        }
    }

    private final void zza(zzbr zzbr) {
        zza(this.zzksp, zzbr);
    }

    private static void zza(Map<String, zzbr> map, zzbr zzbr) {
        if (map.containsKey(zzbr.zzbgp())) {
            String valueOf = String.valueOf(zzbr.zzbgp());
            throw new IllegalArgumentException(valueOf.length() != 0 ? "Duplicate function type name: ".concat(valueOf) : new String("Duplicate function type name: "));
        } else {
            map.put(zzbr.zzbgp(), zzbr);
        }
    }

    private final void zzb(zzbr zzbr) {
        zza(this.zzksn, zzbr);
    }

    private final String zzbhp() {
        if (this.zzksv <= 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toString(this.zzksv));
        for (int i = 2; i < this.zzksv; i++) {
            sb.append(' ');
        }
        sb.append(": ");
        return sb.toString();
    }

    private final void zzc(zzbr zzbr) {
        zza(this.zzkso, zzbr);
    }

    private static zzfj zzg(Map<String, zzfj> map, String str) {
        zzfj zzfj = map.get(str);
        if (zzfj != null) {
            return zzfj;
        }
        zzfj zzfj2 = new zzfj();
        map.put(str, zzfj2);
        return zzfj2;
    }

    private final synchronized void zzmd(String str) {
        this.zzksu = str;
    }

    public final synchronized void zzan(List<com.google.android.gms.internal.zzbr> list) {
        for (com.google.android.gms.internal.zzbr next : list) {
            if (next.name != null) {
                if (next.name.startsWith("gaExperiment:")) {
                    zzbq.zza(this.zzknd, next);
                }
            }
            String valueOf = String.valueOf(next);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 22);
            sb.append("Ignored supplemental: ");
            sb.append(valueOf);
            zzdj.v(sb.toString());
        }
    }

    /* access modifiers changed from: package-private */
    public final synchronized String zzbho() {
        return this.zzksu;
    }

    public final synchronized void zzlj(String str) {
        zzmd(str);
        zzar zzbgn = this.zzksm.zzlt(str).zzbgn();
        for (zzdkj zza : zza(this.zzkss, (Set<String>) new HashSet(), (zzfh) new zzfg(this), zzbgn.zzbgd()).getObject()) {
            zza(this.zzksn, zza, (Set<String>) new HashSet(), zzbgn.zzbgc());
        }
        zzmd((String) null);
    }

    public final zzea<zzbt> zzmc(String str) {
        this.zzksv = 0;
        return zza(str, (Set<String>) new HashSet(), this.zzksm.zzls(str).zzbgm());
    }
}
