package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzf;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class zzdhz extends zzdcr {
    private static final String ID = zzbh.UNIVERSAL_ANALYTICS.toString();
    private static final List<String> zzkvc = Arrays.asList(new String[]{ProductAction.ACTION_DETAIL, ProductAction.ACTION_CHECKOUT, "checkout_option", "click", ProductAction.ACTION_ADD, "remove", ProductAction.ACTION_PURCHASE, ProductAction.ACTION_REFUND});
    private static final Pattern zzkvd = Pattern.compile("dimension(\\d+)");
    private static final Pattern zzkve = Pattern.compile("metric(\\d+)");
    private static final Set<String> zzlaq = zzf.zza("", "0", "false");
    private static final Map<String, String> zzlar = zzf.zza("transactionId", "&ti", "transactionAffiliation", "&ta", "transactionTax", "&tt", "transactionShipping", "&ts", "transactionTotal", "&tr", "transactionCurrency", "&cu");
    private static final Map<String, String> zzlas = zzf.zza("name", "&in", "sku", "&ic", "category", "&iv", FirebaseAnalytics.Param.PRICE, "&ip", FirebaseAnalytics.Param.QUANTITY, "&iq", FirebaseAnalytics.Param.CURRENCY, "&cu");
    private final zzdaz zzkxv;
    private final zzdck zzlat;
    private Map<String, Object> zzlau;

    public zzdhz(Context context, zzdaz zzdaz) {
        this(new zzdck(context), zzdaz);
    }

    private zzdhz(zzdck zzdck, zzdaz zzdaz) {
        this.zzkxv = zzdaz;
        this.zzlat = zzdck;
    }

    private final void zza(Tracker tracker, zzdjq<?> zzdjq, zzdjq<?> zzdjq2, zzdjq<?> zzdjq3) {
        String str = (String) this.zzlau.get("transactionId");
        if (str == null) {
            zzdal.e("Cannot find transactionId in data layer.");
            return;
        }
        ArrayList arrayList = new ArrayList();
        try {
            Map<String, String> zzi = zzi(zzdjq);
            zzi.put("&t", "transaction");
            for (Map.Entry next : (zzdjq2 == zzdjw.zzlcz ? zzlar : zzh(zzdjq2)).entrySet()) {
                String str2 = (String) this.zzlau.get(next.getKey());
                if (str2 != null) {
                    zzi.put((String) next.getValue(), str2);
                }
            }
            arrayList.add(zzi);
            List<Map<String, Object>> zzmx = zzmx("transactionProducts");
            if (zzmx != null) {
                for (Map next2 : zzmx) {
                    if (next2.get("name") == null) {
                        zzdal.e("Unable to send transaction item hit due to missing 'name' field.");
                        return;
                    }
                    Map<String, String> zzi2 = zzi(zzdjq);
                    zzi2.put("&t", "item");
                    zzi2.put("&ti", str);
                    for (Map.Entry next3 : (zzdjq3 == zzdjw.zzlcz ? zzlas : zzh(zzdjq3)).entrySet()) {
                        Object obj = next2.get(next3.getKey());
                        if (obj != null) {
                            zzi2.put((String) next3.getValue(), obj.toString());
                        }
                    }
                    arrayList.add(zzi2);
                }
            }
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            int i = 0;
            while (i < size) {
                Object obj2 = arrayList2.get(i);
                i++;
                tracker.send((Map) obj2);
            }
        } catch (IllegalArgumentException e) {
            zzdal.zzb("Unable to send transaction", e);
        }
    }

    private static Product zzag(Map<String, Object> map) {
        String str;
        String valueOf;
        String str2;
        Product product = new Product();
        Object obj = map.get("id");
        if (obj != null) {
            product.setId(String.valueOf(obj));
        }
        Object obj2 = map.get("name");
        if (obj2 != null) {
            product.setName(String.valueOf(obj2));
        }
        Object obj3 = map.get("brand");
        if (obj3 != null) {
            product.setBrand(String.valueOf(obj3));
        }
        Object obj4 = map.get("category");
        if (obj4 != null) {
            product.setCategory(String.valueOf(obj4));
        }
        Object obj5 = map.get("variant");
        if (obj5 != null) {
            product.setVariant(String.valueOf(obj5));
        }
        Object obj6 = map.get(FirebaseAnalytics.Param.COUPON);
        if (obj6 != null) {
            product.setCouponCode(String.valueOf(obj6));
        }
        Object obj7 = map.get("position");
        if (obj7 != null) {
            product.setPosition(zzar(obj7).intValue());
        }
        Object obj8 = map.get(FirebaseAnalytics.Param.PRICE);
        if (obj8 != null) {
            product.setPrice(zzaq(obj8).doubleValue());
        }
        Object obj9 = map.get(FirebaseAnalytics.Param.QUANTITY);
        if (obj9 != null) {
            product.setQuantity(zzar(obj9).intValue());
        }
        for (String next : map.keySet()) {
            Matcher matcher = zzkvd.matcher(next);
            if (matcher.matches()) {
                try {
                    product.setCustomDimension(Integer.parseInt(matcher.group(1)), String.valueOf(map.get(next)));
                } catch (NumberFormatException unused) {
                    str = "illegal number in custom dimension value: ";
                    valueOf = String.valueOf(next);
                    if (valueOf.length() == 0) {
                        str2 = new String(str);
                        zzdal.zzcz(str2);
                    }
                    str2 = str.concat(valueOf);
                    zzdal.zzcz(str2);
                }
            } else {
                Matcher matcher2 = zzkve.matcher(next);
                if (matcher2.matches()) {
                    try {
                        product.setCustomMetric(Integer.parseInt(matcher2.group(1)), zzar(map.get(next)).intValue());
                    } catch (NumberFormatException unused2) {
                        str = "illegal number in custom metric value: ";
                        valueOf = String.valueOf(next);
                        if (valueOf.length() == 0) {
                            str2 = new String(str);
                            zzdal.zzcz(str2);
                        }
                        str2 = str.concat(valueOf);
                        zzdal.zzcz(str2);
                    }
                }
            }
        }
        return product;
    }

    private static Double zzaq(Object obj) {
        if (obj instanceof String) {
            try {
                return Double.valueOf((String) obj);
            } catch (NumberFormatException e) {
                String valueOf = String.valueOf(e.getMessage());
                throw new RuntimeException(valueOf.length() != 0 ? "Cannot convert the object to Double: ".concat(valueOf) : new String("Cannot convert the object to Double: "));
            }
        } else if (obj instanceof Integer) {
            return Double.valueOf(((Integer) obj).doubleValue());
        } else {
            if (obj instanceof Double) {
                return (Double) obj;
            }
            String valueOf2 = String.valueOf(obj.toString());
            throw new RuntimeException(valueOf2.length() != 0 ? "Cannot convert the object to Double: ".concat(valueOf2) : new String("Cannot convert the object to Double: "));
        }
    }

    private static Integer zzar(Object obj) {
        if (obj instanceof String) {
            try {
                return Integer.valueOf((String) obj);
            } catch (NumberFormatException e) {
                String valueOf = String.valueOf(e.getMessage());
                throw new RuntimeException(valueOf.length() != 0 ? "Cannot convert the object to Integer: ".concat(valueOf) : new String("Cannot convert the object to Integer: "));
            }
        } else if (obj instanceof Double) {
            return Integer.valueOf(((Double) obj).intValue());
        } else {
            if (obj instanceof Integer) {
                return (Integer) obj;
            }
            String valueOf2 = String.valueOf(obj.toString());
            throw new RuntimeException(valueOf2.length() != 0 ? "Cannot convert the object to Integer: ".concat(valueOf2) : new String("Cannot convert the object to Integer: "));
        }
    }

    private static Map<String, String> zzh(zzdjq<?> zzdjq) {
        zzbq.checkNotNull(zzdjq);
        zzbq.checkArgument(zzdjq instanceof zzdka);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Object zzj = zzdke.zzj(zzdke.zzk(zzdjq));
        zzbq.checkState(zzj instanceof Map);
        for (Map.Entry<?, ?> entry : ((Map<?, ?>) zzj).entrySet()) {
            linkedHashMap.put(entry.getKey().toString(), entry.getValue().toString());
        }
        return linkedHashMap;
    }

    private static Map<String, String> zzi(zzdjq<?> zzdjq) {
        Map<String, String> zzh = zzh(zzdjq);
        String str = zzh.get("&aip");
        if (str != null && zzlaq.contains(str.toLowerCase())) {
            zzh.remove("&aip");
        }
        return zzh;
    }

    private final List<Map<String, Object>> zzmx(String str) {
        Object obj = this.zzlau.get(str);
        if (obj == null) {
            return null;
        }
        if (obj instanceof List) {
            List<Map<String, Object>> list = (List) obj;
            for (Map<String, Object> map : list) {
                if (!(map instanceof Map)) {
                    throw new IllegalArgumentException("Each element of transactionProducts should be of type Map.");
                }
            }
            return list;
        }
        throw new IllegalArgumentException("transactionProducts should be of type List.");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x0233, code lost:
        r5 = (java.util.Map) r7.get(r4);
        r0 = (java.util.List) r5.get("products");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x0242, code lost:
        if (r0 == null) goto L_0x0279;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x0244, code lost:
        r7 = r0.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x024c, code lost:
        if (r7.hasNext() == false) goto L_0x0279;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:?, code lost:
        r6.addProduct(zzag((java.util.Map) r7.next()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x025c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:?, code lost:
        r0 = java.lang.String.valueOf(r0.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x0269, code lost:
        if (r0.length() != 0) goto L_0x026b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x026b, code lost:
        r0 = "Failed to extract a product from event data. ".concat(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x0270, code lost:
        r0 = new java.lang.String("Failed to extract a product from event data. ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x0275, code lost:
        com.google.android.gms.internal.zzdal.e(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x027d, code lost:
        if (r5.containsKey("actionField") == false) goto L_0x031e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x027f, code lost:
        r0 = (java.util.Map) r5.get("actionField");
        r2 = new com.google.android.gms.analytics.ecommerce.ProductAction(r4);
        r3 = r0.get("id");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x028e, code lost:
        if (r3 == null) goto L_0x0297;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x0290, code lost:
        r2.setTransactionId(java.lang.String.valueOf(r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x0297, code lost:
        r3 = r0.get(com.google.firebase.analytics.FirebaseAnalytics.Param.AFFILIATION);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x029d, code lost:
        if (r3 == null) goto L_0x02a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x029f, code lost:
        r2.setTransactionAffiliation(java.lang.String.valueOf(r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x02a6, code lost:
        r3 = r0.get(com.google.firebase.analytics.FirebaseAnalytics.Param.COUPON);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x02ac, code lost:
        if (r3 == null) goto L_0x02b5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x02ae, code lost:
        r2.setTransactionCouponCode(java.lang.String.valueOf(r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x02b5, code lost:
        r3 = r0.get("list");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x02b9, code lost:
        if (r3 == null) goto L_0x02c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x02bb, code lost:
        r2.setProductActionList(java.lang.String.valueOf(r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x02c2, code lost:
        r3 = r0.get("option");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x02c8, code lost:
        if (r3 == null) goto L_0x02d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x02ca, code lost:
        r2.setCheckoutOptions(java.lang.String.valueOf(r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x02d1, code lost:
        r3 = r0.get("revenue");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x02d7, code lost:
        if (r3 == null) goto L_0x02e4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x02d9, code lost:
        r2.setTransactionRevenue(zzaq(r3).doubleValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x02e4, code lost:
        r3 = r0.get(com.google.firebase.analytics.FirebaseAnalytics.Param.TAX);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:0x02ea, code lost:
        if (r3 == null) goto L_0x02f7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x02ec, code lost:
        r2.setTransactionTax(zzaq(r3).doubleValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x02f7, code lost:
        r3 = r0.get(com.google.firebase.analytics.FirebaseAnalytics.Param.SHIPPING);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x02fd, code lost:
        if (r3 == null) goto L_0x030a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x02ff, code lost:
        r2.setTransactionShipping(zzaq(r3).doubleValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x030a, code lost:
        r0 = r0.get("step");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:185:0x0310, code lost:
        if (r0 == null) goto L_0x0323;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x0312, code lost:
        r2.setCheckoutStep(zzar(r0).intValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:187:0x031e, code lost:
        r2 = new com.google.android.gms.analytics.ecommerce.ProductAction(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:0x0323, code lost:
        r6.setProductAction(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x0327, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:?, code lost:
        r0 = java.lang.String.valueOf(r0.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x0336, code lost:
        if (r0.length() != 0) goto L_0x0338;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x0338, code lost:
        r0 = "Failed to extract a product action from event data. ".concat(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x033d, code lost:
        r0 = new java.lang.String("Failed to extract a product action from event data. ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x0342, code lost:
        com.google.android.gms.internal.zzdal.e(r0);
     */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x0188 A[SYNTHETIC, Splitter:B:101:0x0188] */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x021b A[Catch:{ RuntimeException -> 0x0140, all -> 0x036e }] */
    /* JADX WARNING: Removed duplicated region for block: B:198:0x034a A[Catch:{ RuntimeException -> 0x0140, all -> 0x036e }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00a5 A[Catch:{ RuntimeException -> 0x0140, all -> 0x036e }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00b4 A[Catch:{ RuntimeException -> 0x0140, all -> 0x036e }] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00d4 A[Catch:{ RuntimeException -> 0x0140, all -> 0x036e }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzdjq<?> zza(com.google.android.gms.internal.zzdbb r19, com.google.android.gms.internal.zzdjq<?>... r20) {
        /*
            r18 = this;
            r1 = r18
            r0 = r20
            java.lang.String r2 = "actionField"
            java.lang.String r3 = "promoView"
            java.lang.String r4 = "&cu"
            java.lang.String r5 = "promoClick"
            r6 = 1
            com.google.android.gms.common.internal.zzbq.checkArgument(r6)
            int r7 = r0.length
            r8 = 0
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r8)
            if (r7 <= 0) goto L_0x001a
            r7 = 1
            goto L_0x001b
        L_0x001a:
            r7 = 0
        L_0x001b:
            com.google.android.gms.common.internal.zzbq.checkArgument(r7)
            com.google.android.gms.internal.zzdaz r10 = r1.zzkxv     // Catch:{ all -> 0x036e }
            com.google.android.gms.internal.zzczu r10 = r10.zzbjc()     // Catch:{ all -> 0x036e }
            android.os.Bundle r10 = r10.zzbip()     // Catch:{ all -> 0x036e }
            java.util.Map r10 = com.google.android.gms.internal.zzdke.zzae(r10)     // Catch:{ all -> 0x036e }
            r1.zzlau = r10     // Catch:{ all -> 0x036e }
            r10 = r0[r8]     // Catch:{ all -> 0x036e }
            int r11 = r0.length     // Catch:{ all -> 0x036e }
            if (r11 <= r6) goto L_0x0036
            r11 = r0[r6]     // Catch:{ all -> 0x036e }
            goto L_0x003f
        L_0x0036:
            com.google.android.gms.internal.zzdjt r11 = new com.google.android.gms.internal.zzdjt     // Catch:{ all -> 0x036e }
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r6)     // Catch:{ all -> 0x036e }
            r11.<init>(r12)     // Catch:{ all -> 0x036e }
        L_0x003f:
            int r12 = r0.length     // Catch:{ all -> 0x036e }
            r13 = 2
            if (r12 <= r13) goto L_0x0046
            r12 = r0[r13]     // Catch:{ all -> 0x036e }
            goto L_0x004b
        L_0x0046:
            com.google.android.gms.internal.zzdjt r12 = new com.google.android.gms.internal.zzdjt     // Catch:{ all -> 0x036e }
            r12.<init>(r9)     // Catch:{ all -> 0x036e }
        L_0x004b:
            int r13 = r0.length     // Catch:{ all -> 0x036e }
            r14 = 3
            if (r13 <= r14) goto L_0x0052
            r13 = r0[r14]     // Catch:{ all -> 0x036e }
            goto L_0x0054
        L_0x0052:
            com.google.android.gms.internal.zzdjw r13 = com.google.android.gms.internal.zzdjw.zzlcz     // Catch:{ all -> 0x036e }
        L_0x0054:
            int r14 = r0.length     // Catch:{ all -> 0x036e }
            r15 = 4
            if (r14 <= r15) goto L_0x005b
            r14 = r0[r15]     // Catch:{ all -> 0x036e }
            goto L_0x005d
        L_0x005b:
            com.google.android.gms.internal.zzdjw r14 = com.google.android.gms.internal.zzdjw.zzlcz     // Catch:{ all -> 0x036e }
        L_0x005d:
            int r15 = r0.length     // Catch:{ all -> 0x036e }
            r6 = 5
            if (r15 <= r6) goto L_0x0064
            r6 = r0[r6]     // Catch:{ all -> 0x036e }
            goto L_0x0069
        L_0x0064:
            com.google.android.gms.internal.zzdjt r6 = new com.google.android.gms.internal.zzdjt     // Catch:{ all -> 0x036e }
            r6.<init>(r9)     // Catch:{ all -> 0x036e }
        L_0x0069:
            int r15 = r0.length     // Catch:{ all -> 0x036e }
            r8 = 6
            if (r15 <= r8) goto L_0x0070
            r8 = r0[r8]     // Catch:{ all -> 0x036e }
            goto L_0x0075
        L_0x0070:
            com.google.android.gms.internal.zzdjt r8 = new com.google.android.gms.internal.zzdjt     // Catch:{ all -> 0x036e }
            r8.<init>(r9)     // Catch:{ all -> 0x036e }
        L_0x0075:
            int r15 = r0.length     // Catch:{ all -> 0x036e }
            r7 = 7
            if (r15 <= r7) goto L_0x007c
            r7 = r0[r7]     // Catch:{ all -> 0x036e }
            goto L_0x007e
        L_0x007c:
            com.google.android.gms.internal.zzdjw r7 = com.google.android.gms.internal.zzdjw.zzlcz     // Catch:{ all -> 0x036e }
        L_0x007e:
            int r15 = r0.length     // Catch:{ all -> 0x036e }
            r17 = r12
            r12 = 8
            if (r15 <= r12) goto L_0x0088
            r0 = r0[r12]     // Catch:{ all -> 0x036e }
            goto L_0x008d
        L_0x0088:
            com.google.android.gms.internal.zzdjt r0 = new com.google.android.gms.internal.zzdjt     // Catch:{ all -> 0x036e }
            r0.<init>(r9)     // Catch:{ all -> 0x036e }
        L_0x008d:
            boolean r9 = r10 instanceof com.google.android.gms.internal.zzdka     // Catch:{ all -> 0x036e }
            com.google.android.gms.common.internal.zzbq.checkArgument(r9)     // Catch:{ all -> 0x036e }
            com.google.android.gms.internal.zzdjw r9 = com.google.android.gms.internal.zzdjw.zzlcz     // Catch:{ all -> 0x036e }
            if (r13 == r9) goto L_0x009d
            boolean r9 = r13 instanceof com.google.android.gms.internal.zzdka     // Catch:{ all -> 0x036e }
            if (r9 == 0) goto L_0x009b
            goto L_0x009d
        L_0x009b:
            r9 = 0
            goto L_0x009e
        L_0x009d:
            r9 = 1
        L_0x009e:
            com.google.android.gms.common.internal.zzbq.checkArgument(r9)     // Catch:{ all -> 0x036e }
            com.google.android.gms.internal.zzdjw r9 = com.google.android.gms.internal.zzdjw.zzlcz     // Catch:{ all -> 0x036e }
            if (r14 == r9) goto L_0x00ac
            boolean r9 = r14 instanceof com.google.android.gms.internal.zzdka     // Catch:{ all -> 0x036e }
            if (r9 == 0) goto L_0x00aa
            goto L_0x00ac
        L_0x00aa:
            r9 = 0
            goto L_0x00ad
        L_0x00ac:
            r9 = 1
        L_0x00ad:
            com.google.android.gms.common.internal.zzbq.checkArgument(r9)     // Catch:{ all -> 0x036e }
            com.google.android.gms.internal.zzdjw r9 = com.google.android.gms.internal.zzdjw.zzlcz     // Catch:{ all -> 0x036e }
            if (r7 == r9) goto L_0x00bb
            boolean r9 = r7 instanceof com.google.android.gms.internal.zzdka     // Catch:{ all -> 0x036e }
            if (r9 == 0) goto L_0x00b9
            goto L_0x00bb
        L_0x00b9:
            r9 = 0
            goto L_0x00bc
        L_0x00bb:
            r9 = 1
        L_0x00bc:
            com.google.android.gms.common.internal.zzbq.checkArgument(r9)     // Catch:{ all -> 0x036e }
            com.google.android.gms.internal.zzdck r9 = r1.zzlat     // Catch:{ all -> 0x036e }
            java.lang.String r12 = "_GTM_DEFAULT_TRACKER_"
            com.google.android.gms.analytics.Tracker r9 = r9.zzmf(r12)     // Catch:{ all -> 0x036e }
            boolean r0 = com.google.android.gms.internal.zzdcq.zza(r0)     // Catch:{ all -> 0x036e }
            r9.enableAdvertisingIdCollection(r0)     // Catch:{ all -> 0x036e }
            boolean r0 = com.google.android.gms.internal.zzdcq.zza(r6)     // Catch:{ all -> 0x036e }
            if (r0 == 0) goto L_0x034a
            com.google.android.gms.analytics.HitBuilders$ScreenViewBuilder r6 = new com.google.android.gms.analytics.HitBuilders$ScreenViewBuilder     // Catch:{ all -> 0x036e }
            r6.<init>()     // Catch:{ all -> 0x036e }
            java.util.Map r0 = zzi(r10)     // Catch:{ all -> 0x036e }
            r6.setAll(r0)     // Catch:{ all -> 0x036e }
            boolean r8 = com.google.android.gms.internal.zzdcq.zza(r8)     // Catch:{ all -> 0x036e }
            if (r8 == 0) goto L_0x00ef
            java.util.Map<java.lang.String, java.lang.Object> r7 = r1.zzlau     // Catch:{ all -> 0x036e }
            java.lang.String r8 = "ecommerce"
            java.lang.Object r7 = r7.get(r8)     // Catch:{ all -> 0x036e }
            goto L_0x00f7
        L_0x00ef:
            com.google.android.gms.internal.zzdjq r7 = com.google.android.gms.internal.zzdke.zzk(r7)     // Catch:{ all -> 0x036e }
            java.lang.Object r7 = com.google.android.gms.internal.zzdke.zzj(r7)     // Catch:{ all -> 0x036e }
        L_0x00f7:
            boolean r8 = r7 instanceof java.util.Map     // Catch:{ all -> 0x036e }
            if (r8 == 0) goto L_0x0345
            java.util.Map r7 = (java.util.Map) r7     // Catch:{ all -> 0x036e }
            java.lang.Object r0 = r0.get(r4)     // Catch:{ all -> 0x036e }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x036e }
            if (r0 != 0) goto L_0x010d
            java.lang.String r0 = "currencyCode"
            java.lang.Object r0 = r7.get(r0)     // Catch:{ all -> 0x036e }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x036e }
        L_0x010d:
            if (r0 == 0) goto L_0x0112
            r6.set(r4, r0)     // Catch:{ all -> 0x036e }
        L_0x0112:
            java.lang.String r0 = "impressions"
            java.lang.Object r0 = r7.get(r0)     // Catch:{ all -> 0x036e }
            boolean r4 = r0 instanceof java.util.List     // Catch:{ all -> 0x036e }
            java.lang.String r8 = "Failed to extract a product from event data. "
            java.lang.String r10 = "list"
            if (r4 == 0) goto L_0x015d
            java.util.List r0 = (java.util.List) r0     // Catch:{ all -> 0x036e }
            java.util.Iterator r4 = r0.iterator()     // Catch:{ all -> 0x036e }
        L_0x0126:
            boolean r0 = r4.hasNext()     // Catch:{ all -> 0x036e }
            if (r0 == 0) goto L_0x015d
            java.lang.Object r0 = r4.next()     // Catch:{ all -> 0x036e }
            java.util.Map r0 = (java.util.Map) r0     // Catch:{ all -> 0x036e }
            com.google.android.gms.analytics.ecommerce.Product r11 = zzag(r0)     // Catch:{ RuntimeException -> 0x0140 }
            java.lang.Object r0 = r0.get(r10)     // Catch:{ RuntimeException -> 0x0140 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ RuntimeException -> 0x0140 }
            r6.addImpression(r11, r0)     // Catch:{ RuntimeException -> 0x0140 }
            goto L_0x0126
        L_0x0140:
            r0 = move-exception
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x036e }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x036e }
            int r11 = r0.length()     // Catch:{ all -> 0x036e }
            if (r11 == 0) goto L_0x0154
            java.lang.String r0 = r8.concat(r0)     // Catch:{ all -> 0x036e }
            goto L_0x0159
        L_0x0154:
            java.lang.String r0 = new java.lang.String     // Catch:{ all -> 0x036e }
            r0.<init>(r8)     // Catch:{ all -> 0x036e }
        L_0x0159:
            com.google.android.gms.internal.zzdal.e(r0)     // Catch:{ all -> 0x036e }
            goto L_0x0126
        L_0x015d:
            boolean r0 = r7.containsKey(r5)     // Catch:{ all -> 0x036e }
            java.lang.String r4 = "promotions"
            if (r0 == 0) goto L_0x0172
            java.lang.Object r0 = r7.get(r5)     // Catch:{ all -> 0x036e }
            java.util.Map r0 = (java.util.Map) r0     // Catch:{ all -> 0x036e }
            java.lang.Object r0 = r0.get(r4)     // Catch:{ all -> 0x036e }
        L_0x016f:
            java.util.List r0 = (java.util.List) r0     // Catch:{ all -> 0x036e }
            goto L_0x0184
        L_0x0172:
            boolean r0 = r7.containsKey(r3)     // Catch:{ all -> 0x036e }
            if (r0 == 0) goto L_0x0183
            java.lang.Object r0 = r7.get(r3)     // Catch:{ all -> 0x036e }
            java.util.Map r0 = (java.util.Map) r0     // Catch:{ all -> 0x036e }
            java.lang.Object r0 = r0.get(r4)     // Catch:{ all -> 0x036e }
            goto L_0x016f
        L_0x0183:
            r0 = 0
        L_0x0184:
            java.lang.String r3 = "id"
            if (r0 == 0) goto L_0x0217
            java.util.Iterator r4 = r0.iterator()     // Catch:{ all -> 0x036e }
        L_0x018c:
            boolean r0 = r4.hasNext()     // Catch:{ all -> 0x036e }
            if (r0 == 0) goto L_0x0202
            java.lang.Object r0 = r4.next()     // Catch:{ all -> 0x036e }
            java.util.Map r0 = (java.util.Map) r0     // Catch:{ all -> 0x036e }
            com.google.android.gms.analytics.ecommerce.Promotion r11 = new com.google.android.gms.analytics.ecommerce.Promotion     // Catch:{ RuntimeException -> 0x01e3 }
            r11.<init>()     // Catch:{ RuntimeException -> 0x01e3 }
            java.lang.Object r12 = r0.get(r3)     // Catch:{ RuntimeException -> 0x01e3 }
            java.lang.String r12 = (java.lang.String) r12     // Catch:{ RuntimeException -> 0x01e3 }
            if (r12 == 0) goto L_0x01ac
            java.lang.String r12 = java.lang.String.valueOf(r12)     // Catch:{ RuntimeException -> 0x01e3 }
            r11.setId(r12)     // Catch:{ RuntimeException -> 0x01e3 }
        L_0x01ac:
            java.lang.String r12 = "name"
            java.lang.Object r12 = r0.get(r12)     // Catch:{ RuntimeException -> 0x01e3 }
            java.lang.String r12 = (java.lang.String) r12     // Catch:{ RuntimeException -> 0x01e3 }
            if (r12 == 0) goto L_0x01bd
            java.lang.String r12 = java.lang.String.valueOf(r12)     // Catch:{ RuntimeException -> 0x01e3 }
            r11.setName(r12)     // Catch:{ RuntimeException -> 0x01e3 }
        L_0x01bd:
            java.lang.String r12 = "creative"
            java.lang.Object r12 = r0.get(r12)     // Catch:{ RuntimeException -> 0x01e3 }
            java.lang.String r12 = (java.lang.String) r12     // Catch:{ RuntimeException -> 0x01e3 }
            if (r12 == 0) goto L_0x01ce
            java.lang.String r12 = java.lang.String.valueOf(r12)     // Catch:{ RuntimeException -> 0x01e3 }
            r11.setCreative(r12)     // Catch:{ RuntimeException -> 0x01e3 }
        L_0x01ce:
            java.lang.String r12 = "position"
            java.lang.Object r0 = r0.get(r12)     // Catch:{ RuntimeException -> 0x01e3 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ RuntimeException -> 0x01e3 }
            if (r0 == 0) goto L_0x01df
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ RuntimeException -> 0x01e3 }
            r11.setPosition(r0)     // Catch:{ RuntimeException -> 0x01e3 }
        L_0x01df:
            r6.addPromotion(r11)     // Catch:{ RuntimeException -> 0x01e3 }
            goto L_0x018c
        L_0x01e3:
            r0 = move-exception
            java.lang.String r11 = "Failed to extract a promotion from event data. "
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x036e }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x036e }
            int r12 = r0.length()     // Catch:{ all -> 0x036e }
            if (r12 == 0) goto L_0x01f9
            java.lang.String r0 = r11.concat(r0)     // Catch:{ all -> 0x036e }
            goto L_0x01fe
        L_0x01f9:
            java.lang.String r0 = new java.lang.String     // Catch:{ all -> 0x036e }
            r0.<init>(r11)     // Catch:{ all -> 0x036e }
        L_0x01fe:
            com.google.android.gms.internal.zzdal.e(r0)     // Catch:{ all -> 0x036e }
            goto L_0x018c
        L_0x0202:
            boolean r0 = r7.containsKey(r5)     // Catch:{ all -> 0x036e }
            java.lang.String r4 = "&promoa"
            if (r0 == 0) goto L_0x0212
            java.lang.String r0 = "click"
            r6.set(r4, r0)     // Catch:{ all -> 0x036e }
            r16 = 0
            goto L_0x0219
        L_0x0212:
            java.lang.String r0 = "view"
            r6.set(r4, r0)     // Catch:{ all -> 0x036e }
        L_0x0217:
            r16 = 1
        L_0x0219:
            if (r16 == 0) goto L_0x0345
            java.util.List<java.lang.String> r0 = zzkvc     // Catch:{ all -> 0x036e }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x036e }
        L_0x0221:
            boolean r4 = r0.hasNext()     // Catch:{ all -> 0x036e }
            if (r4 == 0) goto L_0x0345
            java.lang.Object r4 = r0.next()     // Catch:{ all -> 0x036e }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ all -> 0x036e }
            boolean r5 = r7.containsKey(r4)     // Catch:{ all -> 0x036e }
            if (r5 == 0) goto L_0x0221
            java.lang.Object r0 = r7.get(r4)     // Catch:{ all -> 0x036e }
            r5 = r0
            java.util.Map r5 = (java.util.Map) r5     // Catch:{ all -> 0x036e }
            java.lang.String r0 = "products"
            java.lang.Object r0 = r5.get(r0)     // Catch:{ all -> 0x036e }
            java.util.List r0 = (java.util.List) r0     // Catch:{ all -> 0x036e }
            if (r0 == 0) goto L_0x0279
            java.util.Iterator r7 = r0.iterator()     // Catch:{ all -> 0x036e }
        L_0x0248:
            boolean r0 = r7.hasNext()     // Catch:{ all -> 0x036e }
            if (r0 == 0) goto L_0x0279
            java.lang.Object r0 = r7.next()     // Catch:{ all -> 0x036e }
            java.util.Map r0 = (java.util.Map) r0     // Catch:{ all -> 0x036e }
            com.google.android.gms.analytics.ecommerce.Product r0 = zzag(r0)     // Catch:{ RuntimeException -> 0x025c }
            r6.addProduct(r0)     // Catch:{ RuntimeException -> 0x025c }
            goto L_0x0248
        L_0x025c:
            r0 = move-exception
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x036e }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x036e }
            int r11 = r0.length()     // Catch:{ all -> 0x036e }
            if (r11 == 0) goto L_0x0270
            java.lang.String r0 = r8.concat(r0)     // Catch:{ all -> 0x036e }
            goto L_0x0275
        L_0x0270:
            java.lang.String r0 = new java.lang.String     // Catch:{ all -> 0x036e }
            r0.<init>(r8)     // Catch:{ all -> 0x036e }
        L_0x0275:
            com.google.android.gms.internal.zzdal.e(r0)     // Catch:{ all -> 0x036e }
            goto L_0x0248
        L_0x0279:
            boolean r0 = r5.containsKey(r2)     // Catch:{ RuntimeException -> 0x0327 }
            if (r0 == 0) goto L_0x031e
            java.lang.Object r0 = r5.get(r2)     // Catch:{ RuntimeException -> 0x0327 }
            java.util.Map r0 = (java.util.Map) r0     // Catch:{ RuntimeException -> 0x0327 }
            com.google.android.gms.analytics.ecommerce.ProductAction r2 = new com.google.android.gms.analytics.ecommerce.ProductAction     // Catch:{ RuntimeException -> 0x0327 }
            r2.<init>(r4)     // Catch:{ RuntimeException -> 0x0327 }
            java.lang.Object r3 = r0.get(r3)     // Catch:{ RuntimeException -> 0x0327 }
            if (r3 == 0) goto L_0x0297
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ RuntimeException -> 0x0327 }
            r2.setTransactionId(r3)     // Catch:{ RuntimeException -> 0x0327 }
        L_0x0297:
            java.lang.String r3 = "affiliation"
            java.lang.Object r3 = r0.get(r3)     // Catch:{ RuntimeException -> 0x0327 }
            if (r3 == 0) goto L_0x02a6
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ RuntimeException -> 0x0327 }
            r2.setTransactionAffiliation(r3)     // Catch:{ RuntimeException -> 0x0327 }
        L_0x02a6:
            java.lang.String r3 = "coupon"
            java.lang.Object r3 = r0.get(r3)     // Catch:{ RuntimeException -> 0x0327 }
            if (r3 == 0) goto L_0x02b5
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ RuntimeException -> 0x0327 }
            r2.setTransactionCouponCode(r3)     // Catch:{ RuntimeException -> 0x0327 }
        L_0x02b5:
            java.lang.Object r3 = r0.get(r10)     // Catch:{ RuntimeException -> 0x0327 }
            if (r3 == 0) goto L_0x02c2
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ RuntimeException -> 0x0327 }
            r2.setProductActionList(r3)     // Catch:{ RuntimeException -> 0x0327 }
        L_0x02c2:
            java.lang.String r3 = "option"
            java.lang.Object r3 = r0.get(r3)     // Catch:{ RuntimeException -> 0x0327 }
            if (r3 == 0) goto L_0x02d1
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ RuntimeException -> 0x0327 }
            r2.setCheckoutOptions(r3)     // Catch:{ RuntimeException -> 0x0327 }
        L_0x02d1:
            java.lang.String r3 = "revenue"
            java.lang.Object r3 = r0.get(r3)     // Catch:{ RuntimeException -> 0x0327 }
            if (r3 == 0) goto L_0x02e4
            java.lang.Double r3 = zzaq(r3)     // Catch:{ RuntimeException -> 0x0327 }
            double r3 = r3.doubleValue()     // Catch:{ RuntimeException -> 0x0327 }
            r2.setTransactionRevenue(r3)     // Catch:{ RuntimeException -> 0x0327 }
        L_0x02e4:
            java.lang.String r3 = "tax"
            java.lang.Object r3 = r0.get(r3)     // Catch:{ RuntimeException -> 0x0327 }
            if (r3 == 0) goto L_0x02f7
            java.lang.Double r3 = zzaq(r3)     // Catch:{ RuntimeException -> 0x0327 }
            double r3 = r3.doubleValue()     // Catch:{ RuntimeException -> 0x0327 }
            r2.setTransactionTax(r3)     // Catch:{ RuntimeException -> 0x0327 }
        L_0x02f7:
            java.lang.String r3 = "shipping"
            java.lang.Object r3 = r0.get(r3)     // Catch:{ RuntimeException -> 0x0327 }
            if (r3 == 0) goto L_0x030a
            java.lang.Double r3 = zzaq(r3)     // Catch:{ RuntimeException -> 0x0327 }
            double r3 = r3.doubleValue()     // Catch:{ RuntimeException -> 0x0327 }
            r2.setTransactionShipping(r3)     // Catch:{ RuntimeException -> 0x0327 }
        L_0x030a:
            java.lang.String r3 = "step"
            java.lang.Object r0 = r0.get(r3)     // Catch:{ RuntimeException -> 0x0327 }
            if (r0 == 0) goto L_0x0323
            java.lang.Integer r0 = zzar(r0)     // Catch:{ RuntimeException -> 0x0327 }
            int r0 = r0.intValue()     // Catch:{ RuntimeException -> 0x0327 }
            r2.setCheckoutStep(r0)     // Catch:{ RuntimeException -> 0x0327 }
            goto L_0x0323
        L_0x031e:
            com.google.android.gms.analytics.ecommerce.ProductAction r2 = new com.google.android.gms.analytics.ecommerce.ProductAction     // Catch:{ RuntimeException -> 0x0327 }
            r2.<init>(r4)     // Catch:{ RuntimeException -> 0x0327 }
        L_0x0323:
            r6.setProductAction(r2)     // Catch:{ RuntimeException -> 0x0327 }
            goto L_0x0345
        L_0x0327:
            r0 = move-exception
            java.lang.String r2 = "Failed to extract a product action from event data. "
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x036e }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x036e }
            int r3 = r0.length()     // Catch:{ all -> 0x036e }
            if (r3 == 0) goto L_0x033d
            java.lang.String r0 = r2.concat(r0)     // Catch:{ all -> 0x036e }
            goto L_0x0342
        L_0x033d:
            java.lang.String r0 = new java.lang.String     // Catch:{ all -> 0x036e }
            r0.<init>(r2)     // Catch:{ all -> 0x036e }
        L_0x0342:
            com.google.android.gms.internal.zzdal.e(r0)     // Catch:{ all -> 0x036e }
        L_0x0345:
            java.util.Map r0 = r6.build()     // Catch:{ all -> 0x036e }
            goto L_0x0354
        L_0x034a:
            boolean r0 = com.google.android.gms.internal.zzdcq.zza(r11)     // Catch:{ all -> 0x036e }
            if (r0 == 0) goto L_0x0359
            java.util.Map r0 = zzi(r10)     // Catch:{ all -> 0x036e }
        L_0x0354:
            r9.send(r0)     // Catch:{ all -> 0x036e }
        L_0x0357:
            r2 = 0
            goto L_0x0369
        L_0x0359:
            boolean r0 = com.google.android.gms.internal.zzdcq.zza(r17)     // Catch:{ all -> 0x036e }
            if (r0 == 0) goto L_0x0363
            r1.zza(r9, r10, r13, r14)     // Catch:{ all -> 0x036e }
            goto L_0x0357
        L_0x0363:
            java.lang.String r0 = "Ignoring unknown tag."
            com.google.android.gms.internal.zzdal.zzcz(r0)     // Catch:{ all -> 0x036e }
            goto L_0x0357
        L_0x0369:
            r1.zzlau = r2
            com.google.android.gms.internal.zzdjw r0 = com.google.android.gms.internal.zzdjw.zzlcz
            return r0
        L_0x036e:
            r0 = move-exception
            r2 = 0
            r1.zzlau = r2
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzdhz.zza(com.google.android.gms.internal.zzdbb, com.google.android.gms.internal.zzdjq[]):com.google.android.gms.internal.zzdjq");
    }
}
