package com.google.android.gms.analytics;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzaql;
import com.google.android.gms.internal.zzaqm;
import com.google.android.gms.internal.zzaqn;
import com.google.android.gms.internal.zzaqo;
import com.google.android.gms.internal.zzaqp;
import com.google.android.gms.internal.zzaqq;
import com.google.android.gms.internal.zzaqr;
import com.google.android.gms.internal.zzaqs;
import com.google.android.gms.internal.zzaqt;
import com.google.android.gms.internal.zzaqu;
import com.google.android.gms.internal.zzaqv;
import com.google.android.gms.internal.zzaqw;
import com.google.android.gms.internal.zzaqx;
import com.google.android.gms.internal.zzarh;
import com.google.android.gms.internal.zzarj;
import com.google.android.gms.internal.zzark;
import com.google.android.gms.internal.zzarn;
import com.google.android.gms.internal.zzasy;
import com.google.android.gms.internal.zzatt;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jivesoftware.smackx.GroupChatInvitation;
import org.jivesoftware.smackx.packet.MultipleAddresses;

public final class zzb extends zzarh implements zzo {
    private static DecimalFormat zzdua;
    private final zzark zzdtw;
    private final String zzdub;
    private final Uri zzduc;

    public zzb(zzark zzark, String str) {
        this(zzark, str, true, false);
    }

    private zzb(zzark zzark, String str, boolean z, boolean z2) {
        super(zzark);
        zzbq.zzgv(str);
        this.zzdtw = zzark;
        this.zzdub = str;
        this.zzduc = zzdl(str);
    }

    private static void zza(Map<String, String> map, String str, double d) {
        if (d != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            map.put(str, zzb(d));
        }
    }

    private static void zza(Map<String, String> map, String str, int i, int i2) {
        if (i > 0 && i2 > 0) {
            StringBuilder sb = new StringBuilder(23);
            sb.append(i);
            sb.append(GroupChatInvitation.ELEMENT_NAME);
            sb.append(i2);
            map.put(str, sb.toString());
        }
    }

    private static void zza(Map<String, String> map, String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            map.put(str, str2);
        }
    }

    private static void zza(Map<String, String> map, String str, boolean z) {
        if (z) {
            map.put(str, "1");
        }
    }

    private static String zzb(double d) {
        if (zzdua == null) {
            zzdua = new DecimalFormat("0.######");
        }
        return zzdua.format(d);
    }

    private static Map<String, String> zzc(zzg zzg) {
        HashMap hashMap = new HashMap();
        zzaqp zzaqp = (zzaqp) zzg.zza(zzaqp.class);
        if (zzaqp != null) {
            for (Map.Entry next : zzaqp.zzwy().entrySet()) {
                Object value = next.getValue();
                String str = null;
                if (value != null) {
                    if (value instanceof String) {
                        String str2 = (String) value;
                        if (!TextUtils.isEmpty(str2)) {
                            str = str2;
                        }
                    } else if (value instanceof Double) {
                        Double d = (Double) value;
                        if (d.doubleValue() != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                            str = zzb(d.doubleValue());
                        }
                    } else if (!(value instanceof Boolean)) {
                        str = String.valueOf(value);
                    } else if (value != Boolean.FALSE) {
                        str = "1";
                    }
                }
                if (str != null) {
                    hashMap.put((String) next.getKey(), str);
                }
            }
        }
        zzaqu zzaqu = (zzaqu) zzg.zza(zzaqu.class);
        if (zzaqu != null) {
            zza((Map<String, String>) hashMap, "t", zzaqu.zzxd());
            zza((Map<String, String>) hashMap, "cid", zzaqu.zzxe());
            zza((Map<String, String>) hashMap, "uid", zzaqu.getUserId());
            zza((Map<String, String>) hashMap, "sc", zzaqu.zzxh());
            zza((Map<String, String>) hashMap, "sf", zzaqu.zzxj());
            zza((Map<String, String>) hashMap, "ni", zzaqu.zzxi());
            zza((Map<String, String>) hashMap, "adid", zzaqu.zzxf());
            zza((Map<String, String>) hashMap, "ate", zzaqu.zzxg());
        }
        zzaqv zzaqv = (zzaqv) zzg.zza(zzaqv.class);
        if (zzaqv != null) {
            zza((Map<String, String>) hashMap, "cd", zzaqv.zzxk());
            zza((Map<String, String>) hashMap, "a", (double) zzaqv.zzxl());
            zza((Map<String, String>) hashMap, "dr", zzaqv.zzxm());
        }
        zzaqs zzaqs = (zzaqs) zzg.zza(zzaqs.class);
        if (zzaqs != null) {
            zza((Map<String, String>) hashMap, "ec", zzaqs.getCategory());
            zza((Map<String, String>) hashMap, "ea", zzaqs.getAction());
            zza((Map<String, String>) hashMap, "el", zzaqs.getLabel());
            zza((Map<String, String>) hashMap, "ev", (double) zzaqs.getValue());
        }
        zzaqm zzaqm = (zzaqm) zzg.zza(zzaqm.class);
        if (zzaqm != null) {
            zza((Map<String, String>) hashMap, "cn", zzaqm.getName());
            zza((Map<String, String>) hashMap, "cs", zzaqm.getSource());
            zza((Map<String, String>) hashMap, "cm", zzaqm.zzwq());
            zza((Map<String, String>) hashMap, "ck", zzaqm.zzwr());
            zza((Map<String, String>) hashMap, MultipleAddresses.CC, zzaqm.getContent());
            zza((Map<String, String>) hashMap, "ci", zzaqm.getId());
            zza((Map<String, String>) hashMap, "anid", zzaqm.zzws());
            zza((Map<String, String>) hashMap, "gclid", zzaqm.zzwt());
            zza((Map<String, String>) hashMap, "dclid", zzaqm.zzwu());
            zza((Map<String, String>) hashMap, FirebaseAnalytics.Param.ACLID, zzaqm.zzwv());
        }
        zzaqt zzaqt = (zzaqt) zzg.zza(zzaqt.class);
        if (zzaqt != null) {
            zza((Map<String, String>) hashMap, "exd", zzaqt.zzdxh);
            zza((Map<String, String>) hashMap, "exf", zzaqt.zzdxi);
        }
        zzaqw zzaqw = (zzaqw) zzg.zza(zzaqw.class);
        if (zzaqw != null) {
            zza((Map<String, String>) hashMap, "sn", zzaqw.zzdxw);
            zza((Map<String, String>) hashMap, "sa", zzaqw.zzdxe);
            zza((Map<String, String>) hashMap, "st", zzaqw.zzdxx);
        }
        zzaqx zzaqx = (zzaqx) zzg.zza(zzaqx.class);
        if (zzaqx != null) {
            zza((Map<String, String>) hashMap, "utv", zzaqx.zzdxy);
            zza((Map<String, String>) hashMap, "utt", (double) zzaqx.zzdxz);
            zza((Map<String, String>) hashMap, "utc", zzaqx.mCategory);
            zza((Map<String, String>) hashMap, "utl", zzaqx.zzdxf);
        }
        zzaqn zzaqn = (zzaqn) zzg.zza(zzaqn.class);
        if (zzaqn != null) {
            for (Map.Entry next2 : zzaqn.zzww().entrySet()) {
                String zzaj = zzd.zzaj(((Integer) next2.getKey()).intValue());
                if (!TextUtils.isEmpty(zzaj)) {
                    hashMap.put(zzaj, (String) next2.getValue());
                }
            }
        }
        zzaqo zzaqo = (zzaqo) zzg.zza(zzaqo.class);
        if (zzaqo != null) {
            for (Map.Entry next3 : zzaqo.zzwx().entrySet()) {
                String zzal = zzd.zzal(((Integer) next3.getKey()).intValue());
                if (!TextUtils.isEmpty(zzal)) {
                    hashMap.put(zzal, zzb(((Double) next3.getValue()).doubleValue()));
                }
            }
        }
        zzaqr zzaqr = (zzaqr) zzg.zza(zzaqr.class);
        if (zzaqr != null) {
            ProductAction zzwz = zzaqr.zzwz();
            if (zzwz != null) {
                for (Map.Entry next4 : zzwz.build().entrySet()) {
                    hashMap.put(((String) next4.getKey()).startsWith("&") ? ((String) next4.getKey()).substring(1) : (String) next4.getKey(), (String) next4.getValue());
                }
            }
            int i = 1;
            for (Promotion zzdx : zzaqr.zzxc()) {
                hashMap.putAll(zzdx.zzdx(zzd.zzap(i)));
                i++;
            }
            int i2 = 1;
            for (Product zzdx2 : zzaqr.zzxa()) {
                hashMap.putAll(zzdx2.zzdx(zzd.zzan(i2)));
                i2++;
            }
            int i3 = 1;
            for (Map.Entry next5 : zzaqr.zzxb().entrySet()) {
                String zzas = zzd.zzas(i3);
                int i4 = 1;
                for (Product product : (List<Product>) next5.getValue()) {
                    String valueOf = String.valueOf(zzas);
                    String valueOf2 = String.valueOf(zzd.zzaq(i4));
                    hashMap.putAll(product.zzdx(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf)));
                    i4++;
                }
                if (!TextUtils.isEmpty((CharSequence) next5.getKey())) {
                    String valueOf3 = String.valueOf(zzas);
                    hashMap.put("nm".length() != 0 ? valueOf3.concat("nm") : new String(valueOf3), (String) next5.getKey());
                }
                i3++;
            }
        }
        zzaqq zzaqq = (zzaqq) zzg.zza(zzaqq.class);
        if (zzaqq != null) {
            zza((Map<String, String>) hashMap, "ul", zzaqq.getLanguage());
            zza((Map<String, String>) hashMap, "sd", (double) zzaqq.zzdxb);
            zza(hashMap, "sr", zzaqq.zzcly, zzaqq.zzclz);
            zza(hashMap, "vp", zzaqq.zzdxc, zzaqq.zzdxd);
        }
        zzaql zzaql = (zzaql) zzg.zza(zzaql.class);
        if (zzaql != null) {
            zza((Map<String, String>) hashMap, "an", zzaql.zzwn());
            zza((Map<String, String>) hashMap, "aid", zzaql.getAppId());
            zza((Map<String, String>) hashMap, "aiid", zzaql.zzwp());
            zza((Map<String, String>) hashMap, "av", zzaql.zzwo());
        }
        return hashMap;
    }

    static Uri zzdl(String str) {
        zzbq.zzgv(str);
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("uri");
        builder.authority("google-analytics.com");
        builder.path(str);
        return builder.build();
    }

    public final void zzb(zzg zzg) {
        zzbq.checkNotNull(zzg);
        zzbq.checkArgument(zzg.zzwb(), "Can't deliver not submitted measurement");
        zzbq.zzgw("deliver should be called on worker thread");
        zzg zzvx = zzg.zzvx();
        zzaqu zzaqu = (zzaqu) zzvx.zzb(zzaqu.class);
        if (TextUtils.isEmpty(zzaqu.zzxd())) {
            zzxy().zzf(zzc(zzvx), "Ignoring measurement without type");
        } else if (TextUtils.isEmpty(zzaqu.zzxe())) {
            zzxy().zzf(zzc(zzvx), "Ignoring measurement without client id");
        } else if (!this.zzdtw.zzyn().getAppOptOut()) {
            double zzxj = zzaqu.zzxj();
            if (zzatt.zza(zzxj, zzaqu.zzxe())) {
                zzb("Sampling enabled. Hit sampled out. sampling rate", Double.valueOf(zzxj));
                return;
            }
            Map<String, String> zzc = zzc(zzvx);
            zzc.put("v", "1");
            zzc.put("_v", zzarj.zzdyr);
            zzc.put("tid", this.zzdub);
            if (this.zzdtw.zzyn().isDryRunEnabled()) {
                StringBuilder sb = new StringBuilder();
                for (Map.Entry next : zzc.entrySet()) {
                    if (sb.length() != 0) {
                        sb.append(", ");
                    }
                    sb.append((String) next.getKey());
                    sb.append("=");
                    sb.append((String) next.getValue());
                }
                zzc("Dry run is enabled. GoogleAnalytics would have sent", sb.toString());
                return;
            }
            HashMap hashMap = new HashMap();
            zzatt.zzb((Map<String, String>) hashMap, "uid", zzaqu.getUserId());
            zzaql zzaql = (zzaql) zzg.zza(zzaql.class);
            if (zzaql != null) {
                zzatt.zzb((Map<String, String>) hashMap, "an", zzaql.zzwn());
                zzatt.zzb((Map<String, String>) hashMap, "aid", zzaql.getAppId());
                zzatt.zzb((Map<String, String>) hashMap, "av", zzaql.zzwo());
                zzatt.zzb((Map<String, String>) hashMap, "aiid", zzaql.zzwp());
            }
            zzc.put("_s", String.valueOf(zzyc().zza(new zzarn(0, zzaqu.zzxe(), this.zzdub, !TextUtils.isEmpty(zzaqu.zzxf()), 0, hashMap))));
            zzyc().zza(new zzasy(zzxy(), zzc, zzg.zzvz(), true));
        }
    }

    public final Uri zzvu() {
        return this.zzduc;
    }
}
