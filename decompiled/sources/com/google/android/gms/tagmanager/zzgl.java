package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.internal.zzbh;
import com.google.android.gms.internal.zzbi;
import com.google.android.gms.internal.zzbt;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class zzgl extends zzgi {
    private static final String ID = zzbh.UNIVERSAL_ANALYTICS.toString();
    private static final String zzkut = zzbi.ACCOUNT.toString();
    private static final String zzkuu = zzbi.ANALYTICS_PASS_THROUGH.toString();
    private static final String zzkuv = zzbi.ENABLE_ECOMMERCE.toString();
    private static final String zzkuw = zzbi.ECOMMERCE_USE_DATA_LAYER.toString();
    private static final String zzkux = zzbi.ECOMMERCE_MACRO_DATA.toString();
    private static final String zzkuy = zzbi.ANALYTICS_FIELDS.toString();
    private static final String zzkuz = zzbi.TRACK_TRANSACTION.toString();
    private static final String zzkva = zzbi.TRANSACTION_DATALAYER_MAP.toString();
    private static final String zzkvb = zzbi.TRANSACTION_ITEM_DATALAYER_MAP.toString();
    private static final List<String> zzkvc = Arrays.asList(new String[]{ProductAction.ACTION_DETAIL, ProductAction.ACTION_CHECKOUT, "checkout_option", "click", ProductAction.ACTION_ADD, "remove", ProductAction.ACTION_PURCHASE, ProductAction.ACTION_REFUND});
    private static final Pattern zzkvd = Pattern.compile("dimension(\\d+)");
    private static final Pattern zzkve = Pattern.compile("metric(\\d+)");
    private static Map<String, String> zzkvf;
    private static Map<String, String> zzkvg;
    private final DataLayer zzknd;
    private final Set<String> zzkvh;
    private final zzgg zzkvi;

    public zzgl(Context context, DataLayer dataLayer) {
        this(context, dataLayer, new zzgg(context));
    }

    private zzgl(Context context, DataLayer dataLayer, zzgg zzgg) {
        super(ID, new String[0]);
        this.zzknd = dataLayer;
        this.zzkvi = zzgg;
        HashSet hashSet = new HashSet();
        this.zzkvh = hashSet;
        hashSet.add("");
        hashSet.add("0");
        hashSet.add("false");
    }

    private final void zza(Tracker tracker, Map<String, zzbt> map) {
        Map<String, String> map2;
        Map<String, String> map3;
        String zzmk = zzmk("transactionId");
        if (zzmk == null) {
            zzdj.e("Cannot find transactionId in data layer.");
            return;
        }
        ArrayList arrayList = new ArrayList();
        try {
            Map<String, String> zzk = zzk(map.get(zzkuy));
            zzk.put("&t", "transaction");
            zzbt zzbt = map.get(zzkva);
            if (zzbt != null) {
                map2 = zzj(zzbt);
            } else {
                if (zzkvf == null) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("transactionId", "&ti");
                    hashMap.put("transactionAffiliation", "&ta");
                    hashMap.put("transactionTax", "&tt");
                    hashMap.put("transactionShipping", "&ts");
                    hashMap.put("transactionTotal", "&tr");
                    hashMap.put("transactionCurrency", "&cu");
                    zzkvf = hashMap;
                }
                map2 = zzkvf;
            }
            for (Map.Entry next : map2.entrySet()) {
                zzd(zzk, (String) next.getValue(), zzmk((String) next.getKey()));
            }
            arrayList.add(zzk);
            List<Map<String, String>> zzml = zzml("transactionProducts");
            if (zzml != null) {
                for (Map next2 : zzml) {
                    if (next2.get("name") == null) {
                        zzdj.e("Unable to send transaction item hit due to missing 'name' field.");
                        return;
                    }
                    Map<String, String> zzk2 = zzk(map.get(zzkuy));
                    zzk2.put("&t", "item");
                    zzk2.put("&ti", zzmk);
                    zzbt zzbt2 = map.get(zzkvb);
                    if (zzbt2 != null) {
                        map3 = zzj(zzbt2);
                    } else {
                        if (zzkvg == null) {
                            HashMap hashMap2 = new HashMap();
                            hashMap2.put("name", "&in");
                            hashMap2.put("sku", "&ic");
                            hashMap2.put("category", "&iv");
                            hashMap2.put(FirebaseAnalytics.Param.PRICE, "&ip");
                            hashMap2.put(FirebaseAnalytics.Param.QUANTITY, "&iq");
                            hashMap2.put(FirebaseAnalytics.Param.CURRENCY, "&cu");
                            zzkvg = hashMap2;
                        }
                        map3 = zzkvg;
                    }
                    for (Map.Entry next3 : map3.entrySet()) {
                        zzd(zzk2, (String) next3.getValue(), (String) next2.get(next3.getKey()));
                    }
                    arrayList.add(zzk2);
                }
            }
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList2.get(i);
                i++;
                tracker.send((Map) obj);
            }
        } catch (IllegalArgumentException e) {
            zzdj.zzb("Unable to send transaction", e);
        }
    }

    private static Product zzac(Map<String, Object> map) {
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
                        zzdj.zzcz(str2);
                    }
                    str2 = str.concat(valueOf);
                    zzdj.zzcz(str2);
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
                            zzdj.zzcz(str2);
                        }
                        str2 = str.concat(valueOf);
                        zzdj.zzcz(str2);
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

    private static void zzd(Map<String, String> map, String str, String str2) {
        if (str2 != null) {
            map.put(str, str2);
        }
    }

    private static boolean zzh(Map<String, zzbt> map, String str) {
        zzbt zzbt = map.get(str);
        if (zzbt == null) {
            return false;
        }
        return zzgk.zzh(zzbt).booleanValue();
    }

    private static Map<String, String> zzj(zzbt zzbt) {
        Object zzi = zzgk.zzi(zzbt);
        if (!(zzi instanceof Map)) {
            return null;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object entryObj : ((Map) zzi).entrySet()) {
            Map.Entry entry = (Map.Entry) entryObj;
            linkedHashMap.put(entry.getKey().toString(), entry.getValue().toString());
        }
        return linkedHashMap;
    }

    private final Map<String, String> zzk(zzbt zzbt) {
        if (zzbt == null) {
            return new HashMap();
        }
        Map<String, String> zzj = zzj(zzbt);
        if (zzj == null) {
            return new HashMap();
        }
        String str = zzj.get("&aip");
        if (str != null && this.zzkvh.contains(str.toLowerCase())) {
            zzj.remove("&aip");
        }
        return zzj;
    }

    private final String zzmk(String str) {
        Object obj = this.zzknd.get(str);
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    private final List<Map<String, String>> zzml(String str) {
        Object obj = this.zzknd.get(str);
        if (obj == null) {
            return null;
        }
        if (obj instanceof List) {
            List<Map<String, String>> list = (List) obj;
            for (Map<String, String> map : list) {
                if (!(map instanceof Map)) {
                    throw new IllegalArgumentException("Each element of transactionProducts should be of type Map.");
                }
            }
            return list;
        }
        throw new IllegalArgumentException("transactionProducts should be of type List.");
    }

    public final /* bridge */ /* synthetic */ boolean zzbfh() {
        return super.zzbfh();
    }

    public final /* bridge */ /* synthetic */ String zzbgp() {
        return super.zzbgp();
    }

    public final /* bridge */ /* synthetic */ Set zzbgq() {
        return super.zzbgq();
    }

    public final /* bridge */ /* synthetic */ zzbt zzx(Map map) {
        return super.zzx(map);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v15, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v15, resolved type: java.util.List} */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0042, code lost:
        if ((r13 instanceof java.util.Map) != false) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0053, code lost:
        if ((r13 instanceof java.util.Map) != false) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0058, code lost:
        r13 = null;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00e7  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0177  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzz(java.util.Map<java.lang.String, com.google.android.gms.internal.zzbt> r13) {
        /*
            r12 = this;
            java.lang.String r0 = "actionField"
            com.google.android.gms.tagmanager.zzgg r1 = r12.zzkvi
            java.lang.String r2 = "_GTM_DEFAULT_TRACKER_"
            com.google.android.gms.analytics.Tracker r1 = r1.zzmf(r2)
            java.lang.String r2 = "collect_adid"
            boolean r2 = zzh(r13, r2)
            r1.enableAdvertisingIdCollection(r2)
            java.lang.String r2 = zzkuv
            boolean r2 = zzh(r13, r2)
            if (r2 == 0) goto L_0x02a8
            com.google.android.gms.analytics.HitBuilders$ScreenViewBuilder r2 = new com.google.android.gms.analytics.HitBuilders$ScreenViewBuilder
            r2.<init>()
            java.lang.String r3 = zzkuy
            java.lang.Object r3 = r13.get(r3)
            com.google.android.gms.internal.zzbt r3 = (com.google.android.gms.internal.zzbt) r3
            java.util.Map r3 = r12.zzk(r3)
            r2.setAll(r3)
            java.lang.String r4 = zzkuw
            boolean r4 = zzh(r13, r4)
            r5 = 0
            if (r4 == 0) goto L_0x0045
            com.google.android.gms.tagmanager.DataLayer r13 = r12.zzknd
            java.lang.String r4 = "ecommerce"
            java.lang.Object r13 = r13.get(r4)
            boolean r4 = r13 instanceof java.util.Map
            if (r4 == 0) goto L_0x0058
            goto L_0x0055
        L_0x0045:
            java.lang.String r4 = zzkux
            java.lang.Object r13 = r13.get(r4)
            com.google.android.gms.internal.zzbt r13 = (com.google.android.gms.internal.zzbt) r13
            java.lang.Object r13 = com.google.android.gms.tagmanager.zzgk.zzi(r13)
            boolean r4 = r13 instanceof java.util.Map
            if (r4 == 0) goto L_0x0058
        L_0x0055:
            java.util.Map r13 = (java.util.Map) r13
            goto L_0x0059
        L_0x0058:
            r13 = r5
        L_0x0059:
            if (r13 == 0) goto L_0x02a0
            java.lang.String r4 = "&cu"
            java.lang.Object r3 = r3.get(r4)
            java.lang.String r3 = (java.lang.String) r3
            if (r3 != 0) goto L_0x006d
            java.lang.String r3 = "currencyCode"
            java.lang.Object r3 = r13.get(r3)
            java.lang.String r3 = (java.lang.String) r3
        L_0x006d:
            if (r3 == 0) goto L_0x0072
            r2.set(r4, r3)
        L_0x0072:
            java.lang.String r3 = "impressions"
            java.lang.Object r3 = r13.get(r3)
            boolean r4 = r3 instanceof java.util.List
            java.lang.String r6 = "Failed to extract a product from DataLayer. "
            java.lang.String r7 = "list"
            if (r4 == 0) goto L_0x00bd
            java.util.List r3 = (java.util.List) r3
            java.util.Iterator r3 = r3.iterator()
        L_0x0086:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x00bd
            java.lang.Object r4 = r3.next()
            java.util.Map r4 = (java.util.Map) r4
            com.google.android.gms.analytics.ecommerce.Product r8 = zzac(r4)     // Catch:{ RuntimeException -> 0x00a0 }
            java.lang.Object r4 = r4.get(r7)     // Catch:{ RuntimeException -> 0x00a0 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ RuntimeException -> 0x00a0 }
            r2.addImpression(r8, r4)     // Catch:{ RuntimeException -> 0x00a0 }
            goto L_0x0086
        L_0x00a0:
            r4 = move-exception
            java.lang.String r4 = r4.getMessage()
            java.lang.String r4 = java.lang.String.valueOf(r4)
            int r8 = r4.length()
            if (r8 == 0) goto L_0x00b4
            java.lang.String r4 = r6.concat(r4)
            goto L_0x00b9
        L_0x00b4:
            java.lang.String r4 = new java.lang.String
            r4.<init>(r6)
        L_0x00b9:
            com.google.android.gms.tagmanager.zzdj.e(r4)
            goto L_0x0086
        L_0x00bd:
            java.lang.String r3 = "promoClick"
            boolean r4 = r13.containsKey(r3)
            java.lang.String r8 = "promotions"
            if (r4 == 0) goto L_0x00d5
            java.lang.Object r4 = r13.get(r3)
        L_0x00cb:
            java.util.Map r4 = (java.util.Map) r4
            java.lang.Object r4 = r4.get(r8)
            r5 = r4
            java.util.List r5 = (java.util.List) r5
            goto L_0x00e2
        L_0x00d5:
            java.lang.String r4 = "promoView"
            boolean r9 = r13.containsKey(r4)
            if (r9 == 0) goto L_0x00e2
            java.lang.Object r4 = r13.get(r4)
            goto L_0x00cb
        L_0x00e2:
            r4 = 1
            java.lang.String r8 = "id"
            if (r5 == 0) goto L_0x0175
            java.util.Iterator r5 = r5.iterator()
        L_0x00eb:
            boolean r9 = r5.hasNext()
            if (r9 == 0) goto L_0x0161
            java.lang.Object r9 = r5.next()
            java.util.Map r9 = (java.util.Map) r9
            com.google.android.gms.analytics.ecommerce.Promotion r10 = new com.google.android.gms.analytics.ecommerce.Promotion     // Catch:{ RuntimeException -> 0x0142 }
            r10.<init>()     // Catch:{ RuntimeException -> 0x0142 }
            java.lang.Object r11 = r9.get(r8)     // Catch:{ RuntimeException -> 0x0142 }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ RuntimeException -> 0x0142 }
            if (r11 == 0) goto L_0x010b
            java.lang.String r11 = java.lang.String.valueOf(r11)     // Catch:{ RuntimeException -> 0x0142 }
            r10.setId(r11)     // Catch:{ RuntimeException -> 0x0142 }
        L_0x010b:
            java.lang.String r11 = "name"
            java.lang.Object r11 = r9.get(r11)     // Catch:{ RuntimeException -> 0x0142 }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ RuntimeException -> 0x0142 }
            if (r11 == 0) goto L_0x011c
            java.lang.String r11 = java.lang.String.valueOf(r11)     // Catch:{ RuntimeException -> 0x0142 }
            r10.setName(r11)     // Catch:{ RuntimeException -> 0x0142 }
        L_0x011c:
            java.lang.String r11 = "creative"
            java.lang.Object r11 = r9.get(r11)     // Catch:{ RuntimeException -> 0x0142 }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ RuntimeException -> 0x0142 }
            if (r11 == 0) goto L_0x012d
            java.lang.String r11 = java.lang.String.valueOf(r11)     // Catch:{ RuntimeException -> 0x0142 }
            r10.setCreative(r11)     // Catch:{ RuntimeException -> 0x0142 }
        L_0x012d:
            java.lang.String r11 = "position"
            java.lang.Object r9 = r9.get(r11)     // Catch:{ RuntimeException -> 0x0142 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ RuntimeException -> 0x0142 }
            if (r9 == 0) goto L_0x013e
            java.lang.String r9 = java.lang.String.valueOf(r9)     // Catch:{ RuntimeException -> 0x0142 }
            r10.setPosition(r9)     // Catch:{ RuntimeException -> 0x0142 }
        L_0x013e:
            r2.addPromotion(r10)     // Catch:{ RuntimeException -> 0x0142 }
            goto L_0x00eb
        L_0x0142:
            r9 = move-exception
            java.lang.String r10 = "Failed to extract a promotion from DataLayer. "
            java.lang.String r9 = r9.getMessage()
            java.lang.String r9 = java.lang.String.valueOf(r9)
            int r11 = r9.length()
            if (r11 == 0) goto L_0x0158
            java.lang.String r9 = r10.concat(r9)
            goto L_0x015d
        L_0x0158:
            java.lang.String r9 = new java.lang.String
            r9.<init>(r10)
        L_0x015d:
            com.google.android.gms.tagmanager.zzdj.e(r9)
            goto L_0x00eb
        L_0x0161:
            boolean r3 = r13.containsKey(r3)
            java.lang.String r5 = "&promoa"
            if (r3 == 0) goto L_0x0170
            java.lang.String r3 = "click"
            r2.set(r5, r3)
            r4 = 0
            goto L_0x0175
        L_0x0170:
            java.lang.String r3 = "view"
            r2.set(r5, r3)
        L_0x0175:
            if (r4 == 0) goto L_0x02a0
            java.util.List<java.lang.String> r3 = zzkvc
            java.util.Iterator r3 = r3.iterator()
        L_0x017d:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x02a0
            java.lang.Object r4 = r3.next()
            java.lang.String r4 = (java.lang.String) r4
            boolean r5 = r13.containsKey(r4)
            if (r5 == 0) goto L_0x017d
            java.lang.Object r13 = r13.get(r4)
            java.util.Map r13 = (java.util.Map) r13
            java.lang.String r3 = "products"
            java.lang.Object r3 = r13.get(r3)
            java.util.List r3 = (java.util.List) r3
            if (r3 == 0) goto L_0x01d4
            java.util.Iterator r3 = r3.iterator()
        L_0x01a3:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L_0x01d4
            java.lang.Object r5 = r3.next()
            java.util.Map r5 = (java.util.Map) r5
            com.google.android.gms.analytics.ecommerce.Product r5 = zzac(r5)     // Catch:{ RuntimeException -> 0x01b7 }
            r2.addProduct(r5)     // Catch:{ RuntimeException -> 0x01b7 }
            goto L_0x01a3
        L_0x01b7:
            r5 = move-exception
            java.lang.String r5 = r5.getMessage()
            java.lang.String r5 = java.lang.String.valueOf(r5)
            int r9 = r5.length()
            if (r9 == 0) goto L_0x01cb
            java.lang.String r5 = r6.concat(r5)
            goto L_0x01d0
        L_0x01cb:
            java.lang.String r5 = new java.lang.String
            r5.<init>(r6)
        L_0x01d0:
            com.google.android.gms.tagmanager.zzdj.e(r5)
            goto L_0x01a3
        L_0x01d4:
            boolean r3 = r13.containsKey(r0)     // Catch:{ RuntimeException -> 0x0282 }
            if (r3 == 0) goto L_0x0279
            java.lang.Object r13 = r13.get(r0)     // Catch:{ RuntimeException -> 0x0282 }
            java.util.Map r13 = (java.util.Map) r13     // Catch:{ RuntimeException -> 0x0282 }
            com.google.android.gms.analytics.ecommerce.ProductAction r0 = new com.google.android.gms.analytics.ecommerce.ProductAction     // Catch:{ RuntimeException -> 0x0282 }
            r0.<init>(r4)     // Catch:{ RuntimeException -> 0x0282 }
            java.lang.Object r3 = r13.get(r8)     // Catch:{ RuntimeException -> 0x0282 }
            if (r3 == 0) goto L_0x01f2
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ RuntimeException -> 0x0282 }
            r0.setTransactionId(r3)     // Catch:{ RuntimeException -> 0x0282 }
        L_0x01f2:
            java.lang.String r3 = "affiliation"
            java.lang.Object r3 = r13.get(r3)     // Catch:{ RuntimeException -> 0x0282 }
            if (r3 == 0) goto L_0x0201
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ RuntimeException -> 0x0282 }
            r0.setTransactionAffiliation(r3)     // Catch:{ RuntimeException -> 0x0282 }
        L_0x0201:
            java.lang.String r3 = "coupon"
            java.lang.Object r3 = r13.get(r3)     // Catch:{ RuntimeException -> 0x0282 }
            if (r3 == 0) goto L_0x0210
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ RuntimeException -> 0x0282 }
            r0.setTransactionCouponCode(r3)     // Catch:{ RuntimeException -> 0x0282 }
        L_0x0210:
            java.lang.Object r3 = r13.get(r7)     // Catch:{ RuntimeException -> 0x0282 }
            if (r3 == 0) goto L_0x021d
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ RuntimeException -> 0x0282 }
            r0.setProductActionList(r3)     // Catch:{ RuntimeException -> 0x0282 }
        L_0x021d:
            java.lang.String r3 = "option"
            java.lang.Object r3 = r13.get(r3)     // Catch:{ RuntimeException -> 0x0282 }
            if (r3 == 0) goto L_0x022c
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ RuntimeException -> 0x0282 }
            r0.setCheckoutOptions(r3)     // Catch:{ RuntimeException -> 0x0282 }
        L_0x022c:
            java.lang.String r3 = "revenue"
            java.lang.Object r3 = r13.get(r3)     // Catch:{ RuntimeException -> 0x0282 }
            if (r3 == 0) goto L_0x023f
            java.lang.Double r3 = zzaq(r3)     // Catch:{ RuntimeException -> 0x0282 }
            double r3 = r3.doubleValue()     // Catch:{ RuntimeException -> 0x0282 }
            r0.setTransactionRevenue(r3)     // Catch:{ RuntimeException -> 0x0282 }
        L_0x023f:
            java.lang.String r3 = "tax"
            java.lang.Object r3 = r13.get(r3)     // Catch:{ RuntimeException -> 0x0282 }
            if (r3 == 0) goto L_0x0252
            java.lang.Double r3 = zzaq(r3)     // Catch:{ RuntimeException -> 0x0282 }
            double r3 = r3.doubleValue()     // Catch:{ RuntimeException -> 0x0282 }
            r0.setTransactionTax(r3)     // Catch:{ RuntimeException -> 0x0282 }
        L_0x0252:
            java.lang.String r3 = "shipping"
            java.lang.Object r3 = r13.get(r3)     // Catch:{ RuntimeException -> 0x0282 }
            if (r3 == 0) goto L_0x0265
            java.lang.Double r3 = zzaq(r3)     // Catch:{ RuntimeException -> 0x0282 }
            double r3 = r3.doubleValue()     // Catch:{ RuntimeException -> 0x0282 }
            r0.setTransactionShipping(r3)     // Catch:{ RuntimeException -> 0x0282 }
        L_0x0265:
            java.lang.String r3 = "step"
            java.lang.Object r13 = r13.get(r3)     // Catch:{ RuntimeException -> 0x0282 }
            if (r13 == 0) goto L_0x027e
            java.lang.Integer r13 = zzar(r13)     // Catch:{ RuntimeException -> 0x0282 }
            int r13 = r13.intValue()     // Catch:{ RuntimeException -> 0x0282 }
            r0.setCheckoutStep(r13)     // Catch:{ RuntimeException -> 0x0282 }
            goto L_0x027e
        L_0x0279:
            com.google.android.gms.analytics.ecommerce.ProductAction r0 = new com.google.android.gms.analytics.ecommerce.ProductAction     // Catch:{ RuntimeException -> 0x0282 }
            r0.<init>(r4)     // Catch:{ RuntimeException -> 0x0282 }
        L_0x027e:
            r2.setProductAction(r0)     // Catch:{ RuntimeException -> 0x0282 }
            goto L_0x02a0
        L_0x0282:
            r13 = move-exception
            java.lang.String r0 = "Failed to extract a product action from DataLayer. "
            java.lang.String r13 = r13.getMessage()
            java.lang.String r13 = java.lang.String.valueOf(r13)
            int r3 = r13.length()
            if (r3 == 0) goto L_0x0298
            java.lang.String r13 = r0.concat(r13)
            goto L_0x029d
        L_0x0298:
            java.lang.String r13 = new java.lang.String
            r13.<init>(r0)
        L_0x029d:
            com.google.android.gms.tagmanager.zzdj.e(r13)
        L_0x02a0:
            java.util.Map r13 = r2.build()
            r1.send(r13)
            return
        L_0x02a8:
            java.lang.String r0 = zzkuu
            boolean r0 = zzh(r13, r0)
            if (r0 == 0) goto L_0x02c0
            java.lang.String r0 = zzkuy
            java.lang.Object r13 = r13.get(r0)
            com.google.android.gms.internal.zzbt r13 = (com.google.android.gms.internal.zzbt) r13
            java.util.Map r13 = r12.zzk(r13)
            r1.send(r13)
            return
        L_0x02c0:
            java.lang.String r0 = zzkuz
            boolean r0 = zzh(r13, r0)
            if (r0 == 0) goto L_0x02cc
            r12.zza(r1, r13)
            return
        L_0x02cc:
            java.lang.String r13 = "Ignoring unknown tag."
            com.google.android.gms.tagmanager.zzdj.zzcz(r13)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzgl.zzz(java.util.Map):void");
    }
}
