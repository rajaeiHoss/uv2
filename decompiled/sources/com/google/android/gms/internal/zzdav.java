package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.tagmanager.zzce;
import com.google.android.gms.tagmanager.zzcn;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class zzdav {
    private final Context mContext;
    private final String zzknc;
    private int zzksv;
    private final zzcn zzkvt;
    /* access modifiers changed from: private */
    public final zzce zzkwc;
    private final zzdjc zzkxo;
    private final zzdbb zzkxp;
    private final zzdka zzkxq;
    private final zzdka zzkxr;
    private final Set<String> zzkxs = new HashSet();
    private zzdgp zzkxt;
    /* access modifiers changed from: private */
    public zzczu zzkxu;
    private final zzdaz zzkxv;

    public zzdav(Context context, String str, zzdjc zzdjc, zzdjk zzdjk, zzcn zzcn, zzce zzce) {
        zzdbb zzdbb = new zzdbb();
        this.zzkxp = zzdbb;
        zzdka zzdka = new zzdka(new HashMap(50));
        this.zzkxq = zzdka;
        zzdka zzdka2 = new zzdka(new HashMap(10));
        this.zzkxr = zzdka2;
        zzdaw zzdaw = new zzdaw(this);
        this.zzkxv = zzdaw;
        zzbq.checkNotNull(zzdjc, "Internal Error: Container resource cannot be null");
        zzbq.checkNotNull(zzdjk, "Internal Error: Runtime resource cannot be null");
        zzbq.zzh(str, "Internal Error: ContainerId cannot be empty");
        zzbq.checkNotNull(zzcn);
        zzbq.checkNotNull(zzce);
        this.mContext = context;
        this.zzknc = str;
        this.zzkxo = zzdjc;
        this.zzkvt = zzcn;
        this.zzkwc = zzce;
        zzdbb.zza("1", new zzdjv(new zzddo()));
        zzdbb.zza("12", new zzdjv(new zzddp()));
        zzdbb.zza("18", new zzdjv(new zzddq()));
        zzdbb.zza("19", new zzdjv(new zzddr()));
        zzdbb.zza("20", new zzdjv(new zzdds()));
        zzdbb.zza("21", new zzdjv(new zzddt()));
        zzdbb.zza("23", new zzdjv(new zzddu()));
        zzdbb.zza("24", new zzdjv(new zzddv()));
        zzdbb.zza("27", new zzdjv(new zzddw()));
        zzdbb.zza("28", new zzdjv(new zzddx()));
        zzdbb.zza("29", new zzdjv(new zzddy()));
        zzdbb.zza("30", new zzdjv(new zzddz()));
        zzdbb.zza("32", new zzdjv(new zzdea()));
        zzdbb.zza("33", new zzdjv(new zzdea()));
        zzdbb.zza("34", new zzdjv(new zzdeb()));
        zzdbb.zza("35", new zzdjv(new zzdeb()));
        zzdbb.zza("39", new zzdjv(new zzdec()));
        zzdbb.zza("40", new zzdjv(new zzded()));
        zzdbb.zza("0", new zzdjv(new zzdfa()));
        zzdbb.zza("10", new zzdjv(new zzdfb()));
        zzdbb.zza("25", new zzdjv(new zzdfc()));
        zzdbb.zza("26", new zzdjv(new zzdfd()));
        zzdbb.zza("37", new zzdjv(new zzdfe()));
        zzdbb.zza("2", new zzdjv(new zzdee()));
        zzdbb.zza("3", new zzdjv(new zzdef()));
        zzdbb.zza("4", new zzdjv(new zzdeg()));
        zzdbb.zza("5", new zzdjv(new zzdeh()));
        zzdbb.zza("6", new zzdjv(new zzdei()));
        zzdbb.zza("7", new zzdjv(new zzdej()));
        zzdbb.zza("8", new zzdjv(new zzdek()));
        zzdbb.zza("9", new zzdjv(new zzdeh()));
        zzdbb.zza("13", new zzdjv(new zzdel()));
        zzdbb.zza("47", new zzdjv(new zzdem()));
        zzdbb.zza("15", new zzdjv(new zzden()));
        zzdbb.zza("48", new zzdjv(new zzdeo(this)));
        zzdep zzdep = new zzdep();
        zzdbb.zza("16", new zzdjv(zzdep));
        zzdbb.zza("17", new zzdjv(zzdep));
        zzdbb.zza("22", new zzdjv(new zzder()));
        zzdbb.zza("45", new zzdjv(new zzdes()));
        zzdbb.zza("46", new zzdjv(new zzdet()));
        zzdbb.zza("36", new zzdjv(new zzdeu()));
        zzdbb.zza("43", new zzdjv(new zzdev()));
        zzdbb.zza("38", new zzdjv(new zzdew()));
        zzdbb.zza("44", new zzdjv(new zzdex()));
        zzdbb.zza("41", new zzdjv(new zzdey()));
        zzdbb.zza("42", new zzdjv(new zzdez()));
        zza(zzbh.CONTAINS, (zzdcp) new zzdhm());
        zza(zzbh.ENDS_WITH, (zzdcp) new zzdhn());
        zza(zzbh.EQUALS, (zzdcp) new zzdho());
        zza(zzbh.GREATER_EQUALS, (zzdcp) new zzdhp());
        zza(zzbh.GREATER_THAN, (zzdcp) new zzdhq());
        zza(zzbh.LESS_EQUALS, (zzdcp) new zzdhr());
        zza(zzbh.LESS_THAN, (zzdcp) new zzdhs());
        zza(zzbh.REGEX, (zzdcp) new zzdhu());
        zza(zzbh.STARTS_WITH, (zzdcp) new zzdhv());
        zzdka.zzc("advertiserId", new zzdjv(new zzdgf(context)));
        zzdka.zzc("advertiserTrackingEnabled", new zzdjv(new zzdgg(context)));
        zzdka.zzc("adwordsClickReferrer", new zzdjv(new zzdgh(context, zzdaw)));
        zzdka.zzc("applicationId", new zzdjv(new zzdgi(context)));
        zzdka.zzc("applicationName", new zzdjv(new zzdgj(context)));
        zzdka.zzc("applicationVersion", new zzdjv(new zzdgk(context)));
        zzdka.zzc("applicationVersionName", new zzdjv(new zzdgl(context)));
        zzdka.zzc("arbitraryPixieMacro", new zzdjv(new zzdgc(1, zzdbb)));
        zzdka.zzc("carrier", new zzdjv(new zzdgm(context)));
        zzdka.zzc("constant", new zzdjv(new zzdeu()));
        zzdka.zzc("containerId", new zzdjv(new zzdgn(new zzdkc(str))));
        zzdka.zzc("containerVersion", new zzdjv(new zzdgn(new zzdkc(zzdjc.getVersion()))));
        zzdka.zzc("customMacro", new zzdjv(new zzdga(new zzday(this, (zzdaw) null))));
        zzdka.zzc("deviceBrand", new zzdjv(new zzdgq()));
        zzdka.zzc("deviceId", new zzdjv(new zzdgr(context)));
        zzdka.zzc("deviceModel", new zzdjv(new zzdgs()));
        zzdka.zzc("deviceName", new zzdjv(new zzdgt()));
        zzdka.zzc("encode", new zzdjv(new zzdgu()));
        zzdka.zzc("encrypt", new zzdjv(new zzdgv()));
        zzdka.zzc("event", new zzdjv(new zzdgo()));
        zzdka.zzc("eventParameters", new zzdjv(new zzdgw(zzdaw)));
        zzdka.zzc("version", new zzdjv(new zzdgx()));
        zzdka.zzc("hashcode", new zzdjv(new zzdgy()));
        zzdka.zzc("installReferrer", new zzdjv(new zzdgz(context)));
        zzdka.zzc("join", new zzdjv(new zzdha()));
        zzdka.zzc("language", new zzdjv(new zzdhb()));
        zzdka.zzc("locale", new zzdjv(new zzdhc()));
        zzdka.zzc("adWordsUniqueId", new zzdjv(new zzdhe(context)));
        zzdka.zzc("osVersion", new zzdjv(new zzdhf()));
        zzdka.zzc("platform", new zzdjv(new zzdhg()));
        zzdka.zzc("random", new zzdjv(new zzdhh()));
        zzdka.zzc("regexGroup", new zzdjv(new zzdhi()));
        zzdka.zzc("resolution", new zzdjv(new zzdhk(context)));
        zzdka.zzc("runtimeVersion", new zzdjv(new zzdhj()));
        zzdka.zzc("sdkVersion", new zzdjv(new zzdhl()));
        this.zzkxt = new zzdgp();
        zzdka.zzc("currentTime", new zzdjv(this.zzkxt));
        zzdka.zzc("userProperty", new zzdjv(new zzdhd(context, zzdaw)));
        zzdka.zzc("arbitraryPixel", new zzdjv(new zzdhy(zzczs.zzeq(context))));
        zzdka.zzc("customTag", new zzdjv(new zzdga(new zzdax(this, (zzdaw) null))));
        zzdka.zzc("universalAnalytics", new zzdjv(new zzdhz(context, (zzdaz) zzdaw)));
        zzdka.zzc("queueRequest", new zzdjv(new zzdhw(zzczs.zzeq(context))));
        zzdka.zzc("sendMeasurement", new zzdjv(new zzdhx(zzcn, zzdaw)));
        zzdka.zzc("arbitraryPixieTag", new zzdjv(new zzdgc(0, zzdbb)));
        zzdka.zzc("suppressPassthrough", new zzdjv(new zzdge(context, zzdaw)));
        zzdka2.zzc("decodeURI", new zzdjv(new zzdfv()));
        zzdka2.zzc("decodeURIComponent", new zzdjv(new zzdfw()));
        zzdka2.zzc("encodeURI", new zzdjv(new zzdfx()));
        zzdka2.zzc("encodeURIComponent", new zzdjv(new zzdfy()));
        zzdka2.zzc("log", new zzdjv(new zzdgd()));
        zzdka2.zzc("isArray", new zzdjv(new zzdfz()));
        zza(zzdjk);
        zzdka zzdka3 = new zzdka(new HashMap(1));
        zzdka3.zzc("mobile", zzdka);
        zzdka3.zzc("common", zzdka2);
        zzdbb.zza("gtmUtils", zzdka3);
        zzdka zzdka4 = new zzdka(new HashMap((Map) zzdka.value()));
        zzdka4.zzbkr();
        zzdka zzdka5 = new zzdka(new HashMap((Map) zzdka2.value()));
        zzdka5.zzbkr();
        if (zzdbb.has("main") && (zzdbb.zzmu("main") instanceof zzdjv)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(zzdka3);
            zzdke.zza(zzdbb, new zzdkb("main", arrayList));
        }
        zzdka.zzc("base", zzdka4);
        zzdka2.zzc("base", zzdka5);
        zzdka3.zzbkr();
        zzdka.zzbkr();
        zzdka2.zzbkr();
    }

    private final zzdjq<?> zza(zzdje zzdje) {
        this.zzkxs.clear();
        try {
            zzdjq<?> zzaf = zzaf(zzae(zzdje.zzbkd()));
            if (zzaf instanceof zzdjt) {
                return zzaf;
            }
            zzczq.zzc("Predicate must return a boolean value", this.mContext);
            return new zzdjt(false);
        } catch (IllegalStateException unused) {
            zzdal.e("Error evaluating predicate.");
            return zzdjw.zzlcy;
        }
    }

    private final zzdjq<?> zza(zzdjh zzdjh, Map<zzdje, zzdjq<?>> map) {
        String valueOf = String.valueOf(zzdjh);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 19);
        sb.append("Evaluating trigger ");
        sb.append(valueOf);
        zzdal.v(sb.toString());
        for (zzdje next : zzdjh.zzbkg()) {
            zzdjq<?> zzdjq = map.get(next);
            if (zzdjq == null) {
                zzdjq = zza(next);
                map.put(next, zzdjq);
            }
            if (zzdjq == zzdjw.zzlcy) {
                return zzdjw.zzlcy;
            }
            if (((Boolean) ((zzdjt) zzdjq).value()).booleanValue()) {
                return new zzdjt(false);
            }
        }
        for (zzdje next2 : zzdjh.zzbkf()) {
            zzdjq<?> zzdjq2 = map.get(next2);
            if (zzdjq2 == null) {
                zzdjq2 = zza(next2);
                map.put(next2, zzdjq2);
            }
            if (zzdjq2 == zzdjw.zzlcy) {
                return zzdjw.zzlcy;
            }
            if (!((Boolean) ((zzdjt) zzdjq2).value()).booleanValue()) {
                return new zzdjt(false);
            }
        }
        return new zzdjt(true);
    }

    private final zzdjq<?> zza(zzdjn zzdjn) {
        switch (zzdjn.getType()) {
            case 1:
                try {
                    return new zzdju(Double.valueOf(Double.parseDouble((String) zzdjn.getValue())));
                } catch (NumberFormatException unused) {
                    return new zzdkc((String) zzdjn.getValue());
                }
            case 2:
                List<zzdjn> list = (List) zzdjn.getValue();
                ArrayList arrayList = new ArrayList(list.size());
                for (zzdjn zza : list) {
                    arrayList.add(zza(zza));
                }
                return new zzdjx(arrayList);
            case 3:
                Map map = (Map) zzdjn.getValue();
                HashMap hashMap = new HashMap(map.size());
                for (Object entryObj : map.entrySet()) {
                    Map.Entry entry = (Map.Entry) entryObj;
                    hashMap.put(zzdcq.zzd(zza((zzdjn) entry.getKey())), zza((zzdjn) entry.getValue()));
                }
                return new zzdka(hashMap);
            case 4:
                zzdjq<?> zzms = zzms((String) zzdjn.getValue());
                return (!(zzms instanceof zzdkc) || zzdjn.zzbkm().isEmpty()) ? zzms : new zzdkc(zzf((String) ((zzdkc) zzms).value(), zzdjn.zzbkm()));
            case 5:
                return new zzdkc((String) zzdjn.getValue());
            case 6:
                return new zzdju(Double.valueOf(((Integer) zzdjn.getValue()).doubleValue()));
            case 7:
                StringBuilder sb = new StringBuilder();
                for (Object item : (List) zzdjn.getValue()) {
                    zzdjn zza2 = (zzdjn) item;
                    sb.append(zzdcq.zzd(zza(zza2)));
                }
                return new zzdkc(sb.toString());
            case 8:
                return new zzdjt((Boolean) zzdjn.getValue());
            default:
                int type = zzdjn.getType();
                StringBuilder sb2 = new StringBuilder(52);
                sb2.append("Attempting to expand unknown Value type ");
                sb2.append(type);
                sb2.append(".");
                throw new IllegalStateException(sb2.toString());
        }
    }

    private final void zza(zzbh zzbh, zzdcp zzdcp) {
        this.zzkxq.zzc(zzdcm.zza(zzbh), new zzdjv(zzdcp));
    }

    private final void zza(zzdjk zzdjk) {
        for (zzdco next : zzdjk.zzbkk()) {
            next.zza(this.zzkxp);
            this.zzkxp.zza(next.getName(), new zzdjv(next));
        }
    }

    private final Map<String, zzdjq<?>> zzae(Map<String, zzdjn> map) {
        HashMap hashMap = new HashMap();
        for (Map.Entry next : map.entrySet()) {
            hashMap.put((String) next.getKey(), zza((zzdjn) next.getValue()));
        }
        return hashMap;
    }

    private final zzdjq zzaf(Map<String, zzdjq<?>> map) {
        zzdkb zzdkb;
        Context context;
        String str;
        if (map == null) {
            context = this.mContext;
            str = "executeFunctionCall: cannot access the function parameters.";
        } else {
            zzdjq zzdjq = map.get(zzbi.FUNCTION.toString());
            if (!(zzdjq instanceof zzdkc)) {
                context = this.mContext;
                str = "No function id in properties";
            } else {
                String str2 = (String) ((zzdkc) zzdjq).value();
                if (this.zzkxp.has(str2)) {
                    HashMap hashMap = new HashMap();
                    for (Map.Entry next : map.entrySet()) {
                        if (((String) next.getKey()).startsWith("vtp_")) {
                            hashMap.put(((String) next.getKey()).substring(4), (zzdjq) next.getValue());
                        }
                    }
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(new zzdka(hashMap));
                    zzdkb = new zzdkb(str2, arrayList);
                } else {
                    String zzmw = zzdcm.zzmw(str2);
                    if (zzmw != null && this.zzkxq.zzni(zzmw)) {
                        zzdkb = zzh(str2, map);
                    } else {
                        StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 30);
                        sb.append("functionId '");
                        sb.append(str2);
                        sb.append("' is not supported");
                        zzczq.zzc(sb.toString(), this.mContext);
                        return zzdjw.zzlcz;
                    }
                }
                if (zzdkb == null) {
                    context = this.mContext;
                    str = "Internal error: failed to convert function to a valid statement";
                } else {
                    String valueOf = String.valueOf(zzdkb.zzbks());
                    zzdal.v(valueOf.length() != 0 ? "Executing: ".concat(valueOf) : new String("Executing: "));
                    zzdjq zza = zzdke.zza(this.zzkxp, zzdkb);
                    if (!(zza instanceof zzdjw)) {
                        return zza;
                    }
                    zzdjw zzdjw = (zzdjw) zza;
                    return zzdjw.zzbkq() ? (zzdjq) zzdjw.value() : zza;
                }
            }
        }
        zzczq.zzc(str, context);
        return zzdjw.zzlcz;
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

    private final String zzf(String str, List<Integer> list) {
        for (Integer intValue : list) {
            int intValue2 = intValue.intValue();
            if (intValue2 != 12) {
                StringBuilder sb = new StringBuilder(39);
                sb.append("Unsupported Value Escaping: ");
                sb.append(intValue2);
                zzdal.e(sb.toString());
            } else {
                str = zzmt(str);
            }
        }
        return str;
    }

    private final zzdkb zzh(String str, Map<String, zzdjq<?>> map) {
        try {
            return zzdcm.zza(str, map, this.zzkxp);
        } catch (RuntimeException e) {
            String message = e.getMessage();
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 30 + String.valueOf(message).length());
            sb.append("Incorrect keys for function ");
            sb.append(str);
            sb.append(". ");
            sb.append(message);
            zzdal.e(sb.toString());
            return null;
        }
    }

    private final zzdjq<?> zzms(String str) {
        this.zzksv++;
        String zzbhp = zzbhp();
        StringBuilder sb = new StringBuilder(String.valueOf(zzbhp).length() + 31 + String.valueOf(str).length());
        sb.append(zzbhp);
        sb.append("Beginning to evaluate variable ");
        sb.append(str);
        zzdal.v(sb.toString());
        if (!this.zzkxs.contains(str)) {
            this.zzkxs.add(str);
            zzdje zznf = this.zzkxo.zznf(str);
            if (zznf != null) {
                zzdjq<?> zzaf = zzaf(zzae(zznf.zzbkd()));
                String zzbhp2 = zzbhp();
                StringBuilder sb2 = new StringBuilder(String.valueOf(zzbhp2).length() + 25 + String.valueOf(str).length());
                sb2.append(zzbhp2);
                sb2.append("Done evaluating variable ");
                sb2.append(str);
                zzdal.v(sb2.toString());
                this.zzksv--;
                this.zzkxs.remove(str);
                return zzaf;
            }
            this.zzksv--;
            this.zzkxs.remove(str);
            String zzbhp3 = zzbhp();
            StringBuilder sb3 = new StringBuilder(String.valueOf(zzbhp3).length() + 36 + String.valueOf(str).length());
            sb3.append(zzbhp3);
            sb3.append("Attempting to resolve unknown macro ");
            sb3.append(str);
            throw new IllegalStateException(sb3.toString());
        }
        this.zzksv--;
        String obj = this.zzkxs.toString();
        StringBuilder sb4 = new StringBuilder(String.valueOf(str).length() + 77 + String.valueOf(obj).length());
        sb4.append("Macro cycle detected.  Current macro reference: ");
        sb4.append(str);
        sb4.append(". Previous macro references: ");
        sb4.append(obj);
        throw new IllegalStateException(sb4.toString());
    }

    private static String zzmt(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            zzdal.zzb("Escape URI: unsupported encoding", e);
            return str;
        }
    }

    public final void dispatch() {
        zzczs.zzeq(this.mContext).dispatch();
    }

    public final void zzb(zzczu zzczu) {
        this.zzkxp.zza("gtm.globals.eventName", new zzdkc(zzczu.zzbio()));
        this.zzkxt.zza(zzczu);
        this.zzkxu = zzczu;
        HashSet<zzdje> hashSet = new HashSet<>();
        HashSet hashSet2 = new HashSet();
        HashMap hashMap = new HashMap();
        for (zzdjh next : this.zzkxo.zzbkb()) {
            String valueOf = null;
            StringBuilder sb = null;
            String str = null;
            if (!next.zzbkh().isEmpty() || !next.zzbki().isEmpty()) {
                zzdjq<?> zza = zza(next, (Map<zzdje, zzdjq<?>>) hashMap);
                if (zza == zzdjw.zzlcy) {
                    String valueOf2 = String.valueOf(next);
                    StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 41);
                    sb2.append("Error encounted while evaluating trigger ");
                    sb2.append(valueOf2);
                    zzczq.zzd(sb2.toString(), this.mContext);
                    if (!next.zzbki().isEmpty()) {
                        valueOf = String.valueOf(next.zzbki());
                        sb = new StringBuilder(String.valueOf(valueOf).length() + 15);
                        str = "Blocking tags: ";
                    }
                } else if (((Boolean) ((zzdjt) zza).value()).booleanValue()) {
                    String valueOf3 = String.valueOf(next);
                    StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf3).length() + 19);
                    sb3.append("Trigger is firing: ");
                    sb3.append(valueOf3);
                    zzdal.v(sb3.toString());
                    if (!next.zzbkh().isEmpty()) {
                        String valueOf4 = String.valueOf(next.zzbkh());
                        StringBuilder sb4 = new StringBuilder(String.valueOf(valueOf4).length() + 34);
                        sb4.append("Adding tags to firing candidates: ");
                        sb4.append(valueOf4);
                        zzdal.v(sb4.toString());
                        hashSet.addAll(next.zzbkh());
                    }
                    if (!next.zzbki().isEmpty()) {
                        valueOf = String.valueOf(next.zzbki());
                        sb = new StringBuilder(String.valueOf(valueOf).length() + 24);
                        str = "Blocking disabled tags: ";
                    }
                }
                if (sb != null) {
                    sb.append(str);
                    sb.append(valueOf);
                    zzdal.v(sb.toString());
                }
                hashSet2.addAll(next.zzbki());
            } else {
                String valueOf5 = String.valueOf(next);
                StringBuilder sb5 = new StringBuilder(String.valueOf(valueOf5).length() + 64);
                sb5.append("Trigger is not being evaluated since it has no associated tags: ");
                sb5.append(valueOf5);
                zzdal.v(sb5.toString());
            }
        }
        hashSet.removeAll(hashSet2);
        boolean z = false;
        for (zzdje zzdje : hashSet) {
            this.zzkxs.clear();
            String valueOf6 = String.valueOf(zzdje);
            StringBuilder sb6 = new StringBuilder(String.valueOf(valueOf6).length() + 21);
            sb6.append("Executing firing tag ");
            sb6.append(valueOf6);
            zzdal.v(sb6.toString());
            try {
                zzaf(zzae(zzdje.zzbkd()));
                zzdjn zzdjn = zzdje.zzbkd().get(zzbi.DISPATCH_ON_FIRE.toString());
                if (zzdjn != null && zzdjn.getType() == 8 && ((Boolean) zzdjn.getValue()).booleanValue()) {
                    try {
                        String valueOf7 = String.valueOf(zzdje);
                        StringBuilder sb7 = new StringBuilder(String.valueOf(valueOf7).length() + 36);
                        sb7.append("Tag configured to dispatch on fire: ");
                        sb7.append(valueOf7);
                        zzdal.v(sb7.toString());
                        z = true;
                    } catch (IllegalStateException e) {
                        z = true;
                        String valueOf8 = String.valueOf(zzdje);
                        StringBuilder sb8 = new StringBuilder(String.valueOf(valueOf8).length() + 19);
                        sb8.append("Error firing tag ");
                        sb8.append(valueOf8);
                        sb8.append(": ");
                        zzczq.zza(sb8.toString(), e, this.mContext);
                    }
                }
            } catch (IllegalStateException e2) {
                String valueOf82 = String.valueOf(zzdje);
                StringBuilder sb82 = new StringBuilder(String.valueOf(valueOf82).length() + 19);
                sb82.append("Error firing tag ");
                sb82.append(valueOf82);
                sb82.append(": ");
                zzczq.zza(sb82.toString(), e2, this.mContext);
            }
        }
        this.zzkxp.remove("gtm.globals.eventName");
        if (zzczu.zzbir()) {
            String zzbio = zzczu.zzbio();
            StringBuilder sb9 = new StringBuilder(String.valueOf(zzbio).length() + 35);
            sb9.append("Log passthrough event ");
            sb9.append(zzbio);
            sb9.append(" to Firebase.");
            zzdal.v(sb9.toString());
            try {
                this.zzkvt.logEventInternalNoInterceptor(zzczu.zzbiq(), zzczu.zzbio(), zzczu.zzbip(), zzczu.currentTimeMillis());
            } catch (RemoteException e3) {
                zzczq.zza("Error calling measurement proxy: ", e3, this.mContext);
            }
        } else {
            String zzbio2 = zzczu.zzbio();
            StringBuilder sb10 = new StringBuilder(String.valueOf(zzbio2).length() + 63);
            sb10.append("Non-passthrough event ");
            sb10.append(zzbio2);
            sb10.append(" doesn't get logged to Firebase directly.");
            zzdal.v(sb10.toString());
        }
        if (z) {
            zzdal.v("Dispatch called for dispatchOnFire tags.");
            dispatch();
        }
    }

    public final zzdjq<?> zzmr(String str) {
        if (!this.zzkxs.contains(str)) {
            this.zzksv = 0;
            return zzms(str);
        }
        String obj = this.zzkxs.toString();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 77 + String.valueOf(obj).length());
        sb.append("Macro cycle detected.  Current macro reference: ");
        sb.append(str);
        sb.append(". Previous macro references: ");
        sb.append(obj);
        throw new IllegalStateException(sb.toString());
    }
}
