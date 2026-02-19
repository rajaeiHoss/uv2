package com.google.android.gms.analytics;

import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.internal.zzatc;
import com.google.android.gms.internal.zzatt;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HitBuilders {

    @Deprecated
    public static class AppViewBuilder extends HitBuilder<AppViewBuilder> {
        public AppViewBuilder() {
            set("&t", "screenview");
        }
    }

    public static class EventBuilder extends HitBuilder<EventBuilder> {
        public EventBuilder() {
            set("&t", "event");
        }

        public EventBuilder(String str, String str2) {
            this();
            setCategory(str);
            setAction(str2);
        }

        public EventBuilder setAction(String str) {
            set("&ea", str);
            return this;
        }

        public EventBuilder setCategory(String str) {
            set("&ec", str);
            return this;
        }

        public EventBuilder setLabel(String str) {
            set("&el", str);
            return this;
        }

        public EventBuilder setValue(long j) {
            set("&ev", Long.toString(j));
            return this;
        }
    }

    public static class ExceptionBuilder extends HitBuilder<ExceptionBuilder> {
        public ExceptionBuilder() {
            set("&t", "exception");
        }

        public ExceptionBuilder setDescription(String str) {
            set("&exd", str);
            return this;
        }

        public ExceptionBuilder setFatal(boolean z) {
            set("&exf", zzatt.zzao(z));
            return this;
        }
    }

    public static class HitBuilder<T extends HitBuilder> {
        private Map<String, String> zzduq = new HashMap();
        private ProductAction zzdur;
        private Map<String, List<Product>> zzdus = new HashMap();
        private List<Promotion> zzdut = new ArrayList();
        private List<Product> zzduu = new ArrayList();

        protected HitBuilder() {
        }

        private final T zzh(String str, String str2) {
            if (str2 != null) {
                this.zzduq.put(str, str2);
            }
            return self();
        }

        @SuppressWarnings("unchecked")
        private T self() {
            return (T) this;
        }

        public T addImpression(Product product, String str) {
            if (product == null) {
                zzatc.zzcz("product should be non-null");
                return self();
            }
            if (str == null) {
                str = "";
            }
            if (!this.zzdus.containsKey(str)) {
                this.zzdus.put(str, new ArrayList());
            }
            this.zzdus.get(str).add(product);
            return self();
        }

        public T addProduct(Product product) {
            if (product == null) {
                zzatc.zzcz("product should be non-null");
                return self();
            }
            this.zzduu.add(product);
            return self();
        }

        public T addPromotion(Promotion promotion) {
            if (promotion == null) {
                zzatc.zzcz("promotion should be non-null");
                return self();
            }
            this.zzdut.add(promotion);
            return self();
        }

        public Map<String, String> build() {
            HashMap hashMap = new HashMap(this.zzduq);
            ProductAction productAction = this.zzdur;
            if (productAction != null) {
                hashMap.putAll(productAction.build());
            }
            int i = 1;
            for (Promotion zzdx : this.zzdut) {
                hashMap.putAll(zzdx.zzdx(zzd.zzao(i)));
                i++;
            }
            int i2 = 1;
            for (Product zzdx2 : this.zzduu) {
                hashMap.putAll(zzdx2.zzdx(zzd.zzam(i2)));
                i2++;
            }
            int i3 = 1;
            for (Map.Entry next : this.zzdus.entrySet()) {
                String zzar = zzd.zzar(i3);
                int i4 = 1;
                for (Product product : (List<Product>) next.getValue()) {
                    String valueOf = String.valueOf(zzar);
                    String valueOf2 = String.valueOf(zzd.zzaq(i4));
                    hashMap.putAll(product.zzdx(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf)));
                    i4++;
                }
                if (!TextUtils.isEmpty((CharSequence) next.getKey())) {
                    String valueOf3 = String.valueOf(zzar);
                    hashMap.put("nm".length() != 0 ? valueOf3.concat("nm") : new String(valueOf3), (String) next.getKey());
                }
                i3++;
            }
            return hashMap;
        }

        /* access modifiers changed from: protected */
        public String get(String str) {
            return this.zzduq.get(str);
        }

        public final T set(String str, String str2) {
            if (str != null) {
                this.zzduq.put(str, str2);
            } else {
                zzatc.zzcz("HitBuilder.set() called with a null paramName.");
            }
            return self();
        }

        public final T setAll(Map<String, String> map) {
            if (map == null) {
                return self();
            }
            this.zzduq.putAll(new HashMap(map));
            return self();
        }

        public T setCampaignParamsFromUrl(String str) {
            String zzep = zzatt.zzep(str);
            if (TextUtils.isEmpty(zzep)) {
                return self();
            }
            Map<String, String> zzen = zzatt.zzen(zzep);
            zzh("&cc", zzen.get("utm_content"));
            zzh("&cm", zzen.get("utm_medium"));
            zzh("&cn", zzen.get("utm_campaign"));
            zzh("&cs", zzen.get("utm_source"));
            zzh("&ck", zzen.get("utm_term"));
            zzh("&ci", zzen.get("utm_id"));
            zzh("&anid", zzen.get("anid"));
            zzh("&gclid", zzen.get("gclid"));
            zzh("&dclid", zzen.get("dclid"));
            zzh("&aclid", zzen.get(FirebaseAnalytics.Param.ACLID));
            zzh("&gmob_t", zzen.get("gmob_t"));
            return self();
        }

        public T setCustomDimension(int i, String str) {
            set(zzd.zzai(i), str);
            return self();
        }

        public T setCustomMetric(int i, float f) {
            set(zzd.zzak(i), Float.toString(f));
            return self();
        }

        /* access modifiers changed from: protected */
        public T setHitType(String str) {
            set("&t", str);
            return self();
        }

        public T setNewSession() {
            set("&sc", "start");
            return self();
        }

        public T setNonInteraction(boolean z) {
            set("&ni", zzatt.zzao(z));
            return self();
        }

        public T setProductAction(ProductAction productAction) {
            this.zzdur = productAction;
            return self();
        }

        public T setPromotionAction(String str) {
            this.zzduq.put("&promoa", str);
            return self();
        }
    }

    @Deprecated
    public static class ItemBuilder extends HitBuilder<ItemBuilder> {
        public ItemBuilder() {
            set("&t", "item");
        }

        public ItemBuilder setCategory(String str) {
            set("&iv", str);
            return this;
        }

        public ItemBuilder setCurrencyCode(String str) {
            set("&cu", str);
            return this;
        }

        public ItemBuilder setName(String str) {
            set("&in", str);
            return this;
        }

        public ItemBuilder setPrice(double d) {
            set("&ip", Double.toString(d));
            return this;
        }

        public ItemBuilder setQuantity(long j) {
            set("&iq", Long.toString(j));
            return this;
        }

        public ItemBuilder setSku(String str) {
            set("&ic", str);
            return this;
        }

        public ItemBuilder setTransactionId(String str) {
            set("&ti", str);
            return this;
        }
    }

    public static class ScreenViewBuilder extends HitBuilder<ScreenViewBuilder> {
        public ScreenViewBuilder() {
            set("&t", "screenview");
        }
    }

    public static class SocialBuilder extends HitBuilder<SocialBuilder> {
        public SocialBuilder() {
            set("&t", NotificationCompat.CATEGORY_SOCIAL);
        }

        public SocialBuilder setAction(String str) {
            set("&sa", str);
            return this;
        }

        public SocialBuilder setNetwork(String str) {
            set("&sn", str);
            return this;
        }

        public SocialBuilder setTarget(String str) {
            set("&st", str);
            return this;
        }
    }

    public static class TimingBuilder extends HitBuilder<TimingBuilder> {
        public TimingBuilder() {
            set("&t", "timing");
        }

        public TimingBuilder(String str, String str2, long j) {
            this();
            setVariable(str2);
            setValue(j);
            setCategory(str);
        }

        public TimingBuilder setCategory(String str) {
            set("&utc", str);
            return this;
        }

        public TimingBuilder setLabel(String str) {
            set("&utl", str);
            return this;
        }

        public TimingBuilder setValue(long j) {
            set("&utt", Long.toString(j));
            return this;
        }

        public TimingBuilder setVariable(String str) {
            set("&utv", str);
            return this;
        }
    }

    @Deprecated
    public static class TransactionBuilder extends HitBuilder<TransactionBuilder> {
        public TransactionBuilder() {
            set("&t", "transaction");
        }

        public TransactionBuilder setAffiliation(String str) {
            set("&ta", str);
            return this;
        }

        public TransactionBuilder setCurrencyCode(String str) {
            set("&cu", str);
            return this;
        }

        public TransactionBuilder setRevenue(double d) {
            set("&tr", Double.toString(d));
            return this;
        }

        public TransactionBuilder setShipping(double d) {
            set("&ts", Double.toString(d));
            return this;
        }

        public TransactionBuilder setTax(double d) {
            set("&tt", Double.toString(d));
            return this;
        }

        public TransactionBuilder setTransactionId(String str) {
            set("&ti", str);
            return this;
        }
    }
}
