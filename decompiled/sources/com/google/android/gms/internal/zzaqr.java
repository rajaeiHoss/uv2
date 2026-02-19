package com.google.android.gms.internal;

import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.analytics.zzi;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class zzaqr extends zzi<zzaqr> {
    private ProductAction zzdur;
    private final Map<String, List<Product>> zzdus = new HashMap();
    private final List<Promotion> zzdut = new ArrayList();
    private final List<Product> zzduu = new ArrayList();

    public final String toString() {
        HashMap hashMap = new HashMap();
        if (!this.zzduu.isEmpty()) {
            hashMap.put("products", this.zzduu);
        }
        if (!this.zzdut.isEmpty()) {
            hashMap.put("promotions", this.zzdut);
        }
        if (!this.zzdus.isEmpty()) {
            hashMap.put("impressions", this.zzdus);
        }
        hashMap.put("productAction", this.zzdur);
        return zzl(hashMap);
    }

    public final void zzb(zzaqr zzaqr) {
        zzaqr.zzduu.addAll(this.zzduu);
        zzaqr.zzdut.addAll(this.zzdut);
        for (Map.Entry<String, List<Product>> next : this.zzdus.entrySet()) {
            String str = next.getKey();
            for (Product product : next.getValue()) {
                if (product != null) {
                    String str2 = str == null ? "" : str;
                    if (!zzaqr.zzdus.containsKey(str2)) {
                        zzaqr.zzdus.put(str2, new ArrayList());
                    }
                    zzaqr.zzdus.get(str2).add(product);
                }
            }
        }
        ProductAction productAction = this.zzdur;
        if (productAction != null) {
            zzaqr.zzdur = productAction;
        }
    }

    public final ProductAction zzwz() {
        return this.zzdur;
    }

    public final List<Product> zzxa() {
        return Collections.unmodifiableList(this.zzduu);
    }

    public final Map<String, List<Product>> zzxb() {
        return this.zzdus;
    }

    public final List<Promotion> zzxc() {
        return Collections.unmodifiableList(this.zzdut);
    }
}
