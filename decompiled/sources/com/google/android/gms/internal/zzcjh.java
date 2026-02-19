package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.measurement.AppMeasurement;
import com.streamax.config.constant.Constants;
import java.util.concurrent.atomic.AtomicReference;

public final class zzcjh extends zzcli {
    private static AtomicReference<String[]> zzjkd = new AtomicReference<>();
    private static AtomicReference<String[]> zzjke = new AtomicReference<>();
    private static AtomicReference<String[]> zzjkf = new AtomicReference<>();

    zzcjh(zzckj zzckj) {
        super(zzckj);
    }

    private static String zza(String str, String[] strArr, String[] strArr2, AtomicReference<String[]> atomicReference) {
        String str2;
        zzbq.checkNotNull(strArr);
        zzbq.checkNotNull(strArr2);
        zzbq.checkNotNull(atomicReference);
        zzbq.checkArgument(strArr.length == strArr2.length);
        for (int i = 0; i < strArr.length; i++) {
            if (zzcno.zzas(str, strArr[i])) {
                synchronized (atomicReference) {
                    String[] strArr3 = atomicReference.get();
                    if (strArr3 == null) {
                        strArr3 = new String[strArr2.length];
                        atomicReference.set(strArr3);
                    }
                    if (strArr3[i] == null) {
                        strArr3[i] = strArr2[i] + "(" + strArr[i] + ")";
                    }
                    str2 = strArr3[i];
                }
                return str2;
            }
        }
        return str;
    }

    private static void zza(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("  ");
        }
    }

    private final void zza(StringBuilder sb, int i, zzcnt zzcnt) {
        String str;
        if (zzcnt != null) {
            zza(sb, i);
            sb.append("filter {\n");
            zza(sb, i, "complement", (Object) zzcnt.zzjtf);
            zza(sb, i, "param_name", (Object) zzjq(zzcnt.zzjtg));
            int i2 = i + 1;
            zzcnw zzcnw = zzcnt.zzjtd;
            if (zzcnw != null) {
                zza(sb, i2);
                sb.append("string_filter");
                sb.append(" {\n");
                if (zzcnw.zzjtp != null) {
                    switch (zzcnw.zzjtp.intValue()) {
                        case 1:
                            str = "REGEXP";
                            break;
                        case 2:
                            str = "BEGINS_WITH";
                            break;
                        case 3:
                            str = "ENDS_WITH";
                            break;
                        case 4:
                            str = "PARTIAL";
                            break;
                        case 5:
                            str = "EXACT";
                            break;
                        case 6:
                            str = "IN_LIST";
                            break;
                        default:
                            str = "UNKNOWN_MATCH_TYPE";
                            break;
                    }
                    zza(sb, i2, "match_type", (Object) str);
                }
                zza(sb, i2, "expression", (Object) zzcnw.zzjtq);
                zza(sb, i2, "case_sensitive", (Object) zzcnw.zzjtr);
                if (zzcnw.zzjts.length > 0) {
                    zza(sb, i2 + 1);
                    sb.append("expression_list {\n");
                    for (String append : zzcnw.zzjts) {
                        zza(sb, i2 + 2);
                        sb.append(append);
                        sb.append("\n");
                    }
                    sb.append("}\n");
                }
                zza(sb, i2);
                sb.append("}\n");
            }
            zza(sb, i2, "number_filter", zzcnt.zzjte);
            zza(sb, i);
            sb.append("}\n");
        }
    }

    private final void zza(StringBuilder sb, int i, String str, zzcnu zzcnu) {
        if (zzcnu != null) {
            zza(sb, i);
            sb.append(str);
            sb.append(" {\n");
            if (zzcnu.zzjth != null) {
                int intValue = zzcnu.zzjth.intValue();
                zza(sb, i, "comparison_type", intValue != 1 ? intValue != 2 ? intValue != 3 ? intValue != 4 ? "UNKNOWN_COMPARISON_TYPE" : "BETWEEN" : "EQUAL" : "GREATER_THAN" : "LESS_THAN");
            }
            zza(sb, i, "match_as_float", (Object) zzcnu.zzjti);
            zza(sb, i, "comparison_value", (Object) zzcnu.zzjtj);
            zza(sb, i, "min_comparison_value", (Object) zzcnu.zzjtk);
            zza(sb, i, "max_comparison_value", (Object) zzcnu.zzjtl);
            zza(sb, i);
            sb.append("}\n");
        }
    }

    private static void zza(StringBuilder sb, int i, String str, zzcof zzcof) {
        if (zzcof != null) {
            int i2 = i + 1;
            zza(sb, i2);
            sb.append(str);
            sb.append(" {\n");
            int i3 = 0;
            if (zzcof.zzjvp != null) {
                zza(sb, i2 + 1);
                sb.append("results: ");
                long[] jArr = zzcof.zzjvp;
                int length = jArr.length;
                int i4 = 0;
                int i5 = 0;
                while (i4 < length) {
                    Long valueOf = Long.valueOf(jArr[i4]);
                    int i6 = i5 + 1;
                    if (i5 != 0) {
                        sb.append(", ");
                    }
                    sb.append(valueOf);
                    i4++;
                    i5 = i6;
                }
                sb.append(10);
            }
            if (zzcof.zzjvo != null) {
                zza(sb, i2 + 1);
                sb.append("status: ");
                long[] jArr2 = zzcof.zzjvo;
                int length2 = jArr2.length;
                int i7 = 0;
                while (i3 < length2) {
                    Long valueOf2 = Long.valueOf(jArr2[i3]);
                    int i8 = i7 + 1;
                    if (i7 != 0) {
                        sb.append(", ");
                    }
                    sb.append(valueOf2);
                    i3++;
                    i7 = i8;
                }
                sb.append(10);
            }
            zza(sb, i2);
            sb.append("}\n");
        }
    }

    private static void zza(StringBuilder sb, int i, String str, Object obj) {
        if (obj != null) {
            zza(sb, i + 1);
            sb.append(str);
            sb.append(": ");
            sb.append(obj);
            sb.append(10);
        }
    }

    private final void zza(StringBuilder sb, int i, zzcoa[] zzcoaArr) {
        if (zzcoaArr != null) {
            for (zzcoa zzcoa : zzcoaArr) {
                if (zzcoa != null) {
                    zza(sb, 2);
                    sb.append("audience_membership {\n");
                    zza(sb, 2, "audience_id", (Object) zzcoa.zzjst);
                    zza(sb, 2, "new_audience", (Object) zzcoa.zzjug);
                    zza(sb, 2, "current_data", zzcoa.zzjue);
                    zza(sb, 2, "previous_data", zzcoa.zzjuf);
                    zza(sb, 2);
                    sb.append("}\n");
                }
            }
        }
    }

    private final void zza(StringBuilder sb, int i, zzcob[] zzcobArr) {
        if (zzcobArr != null) {
            for (zzcob zzcob : zzcobArr) {
                if (zzcob != null) {
                    zza(sb, 2);
                    sb.append("event {\n");
                    zza(sb, 2, "name", (Object) zzjp(zzcob.name));
                    zza(sb, 2, "timestamp_millis", (Object) zzcob.zzjuj);
                    zza(sb, 2, "previous_timestamp_millis", (Object) zzcob.zzjuk);
                    zza(sb, 2, "count", (Object) zzcob.count);
                    zzcoc[] zzcocArr = zzcob.zzjui;
                    if (zzcocArr != null) {
                        for (zzcoc zzcoc : zzcocArr) {
                            if (zzcoc != null) {
                                zza(sb, 3);
                                sb.append("param {\n");
                                zza(sb, 3, "name", (Object) zzjq(zzcoc.name));
                                zza(sb, 3, "string_value", (Object) zzcoc.zzgim);
                                zza(sb, 3, "int_value", (Object) zzcoc.zzjum);
                                zza(sb, 3, "double_value", (Object) zzcoc.zzjsl);
                                zza(sb, 3);
                                sb.append("}\n");
                            }
                        }
                    }
                    zza(sb, 2);
                    sb.append("}\n");
                }
            }
        }
    }

    private final void zza(StringBuilder sb, int i, zzcog[] zzcogArr) {
        if (zzcogArr != null) {
            for (zzcog zzcog : zzcogArr) {
                if (zzcog != null) {
                    zza(sb, 2);
                    sb.append("user_property {\n");
                    zza(sb, 2, "set_timestamp_millis", (Object) zzcog.zzjvr);
                    zza(sb, 2, "name", (Object) zzjr(zzcog.name));
                    zza(sb, 2, "string_value", (Object) zzcog.zzgim);
                    zza(sb, 2, "int_value", (Object) zzcog.zzjum);
                    zza(sb, 2, "double_value", (Object) zzcog.zzjsl);
                    zza(sb, 2);
                    sb.append("}\n");
                }
            }
        }
    }

    private final String zzb(zzciu zzciu) {
        if (zzciu == null) {
            return null;
        }
        return !zzbat() ? zzciu.toString() : zzac(zzciu.zzbao());
    }

    private final boolean zzbat() {
        return this.zzjev.zzayp().zzae(3);
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    /* access modifiers changed from: protected */
    public final String zza(zzcis zzcis) {
        if (zzcis == null) {
            return null;
        }
        if (!zzbat()) {
            return zzcis.toString();
        }
        return "Event{appId='" + zzcis.zzcm + "', name='" + zzjp(zzcis.name) + "', params=" + zzb(zzcis.zzjhr) + Constants.JsonSstringSuffix;
    }

    /* access modifiers changed from: protected */
    public final String zza(zzcns zzcns) {
        if (zzcns == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nevent_filter {\n");
        zza(sb, 0, "filter_id", (Object) zzcns.zzjsx);
        zza(sb, 0, "event_name", (Object) zzjp(zzcns.zzjsy));
        zza(sb, 1, "event_count_filter", zzcns.zzjtb);
        sb.append("  filters {\n");
        for (zzcnt zza : zzcns.zzjsz) {
            zza(sb, 2, zza);
        }
        zza(sb, 1);
        sb.append("}\n}\n");
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public final String zza(zzcnv zzcnv) {
        if (zzcnv == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nproperty_filter {\n");
        zza(sb, 0, "filter_id", (Object) zzcnv.zzjsx);
        zza(sb, 0, "property_name", (Object) zzjr(zzcnv.zzjtn));
        zza(sb, 1, zzcnv.zzjto);
        sb.append("}\n");
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public final String zza(zzcod zzcod) {
        StringBuilder sb = new StringBuilder();
        sb.append("\nbatch {\n");
        if (zzcod.zzjun != null) {
            for (zzcoe zzcoe : zzcod.zzjun) {
                if (!(zzcoe == null || zzcoe == null)) {
                    zza(sb, 1);
                    sb.append("bundle {\n");
                    zza(sb, 1, "protocol_version", (Object) zzcoe.zzjup);
                    zza(sb, 1, "platform", (Object) zzcoe.zzjux);
                    zza(sb, 1, "gmp_version", (Object) zzcoe.zzjva);
                    zza(sb, 1, "uploading_gmp_version", (Object) zzcoe.zzjvb);
                    zza(sb, 1, "config_version", (Object) zzcoe.zzjvm);
                    zza(sb, 1, "gmp_app_id", (Object) zzcoe.zzjfl);
                    zza(sb, 1, "app_id", (Object) zzcoe.zzcm);
                    zza(sb, 1, "app_version", (Object) zzcoe.zzina);
                    zza(sb, 1, "app_version_major", (Object) zzcoe.zzjvi);
                    zza(sb, 1, "firebase_instance_id", (Object) zzcoe.zzjfn);
                    zza(sb, 1, "dev_cert_hash", (Object) zzcoe.zzjve);
                    zza(sb, 1, "app_store", (Object) zzcoe.zzjfs);
                    zza(sb, 1, "upload_timestamp_millis", (Object) zzcoe.zzjus);
                    zza(sb, 1, "start_timestamp_millis", (Object) zzcoe.zzjut);
                    zza(sb, 1, "end_timestamp_millis", (Object) zzcoe.zzjuu);
                    zza(sb, 1, "previous_bundle_start_timestamp_millis", (Object) zzcoe.zzjuv);
                    zza(sb, 1, "previous_bundle_end_timestamp_millis", (Object) zzcoe.zzjuw);
                    zza(sb, 1, "app_instance_id", (Object) zzcoe.zzjfk);
                    zza(sb, 1, "resettable_device_id", (Object) zzcoe.zzjvc);
                    zza(sb, 1, "device_id", (Object) zzcoe.zzjvl);
                    zza(sb, 1, "limited_ad_tracking", (Object) zzcoe.zzjvd);
                    zza(sb, 1, "os_version", (Object) zzcoe.zzda);
                    zza(sb, 1, "device_model", (Object) zzcoe.zzjuy);
                    zza(sb, 1, "user_default_language", (Object) zzcoe.zzjho);
                    zza(sb, 1, "time_zone_offset_minutes", (Object) zzcoe.zzjuz);
                    zza(sb, 1, "bundle_sequential_index", (Object) zzcoe.zzjvf);
                    zza(sb, 1, "service_upload", (Object) zzcoe.zzjvg);
                    zza(sb, 1, "health_monitor", (Object) zzcoe.zzjgi);
                    if (zzcoe.zzfqm.longValue() != 0) {
                        zza(sb, 1, "android_id", (Object) zzcoe.zzfqm);
                    }
                    zza(sb, 1, zzcoe.zzjur);
                    zza(sb, 1, zzcoe.zzjvh);
                    zza(sb, 1, zzcoe.zzjuq);
                    zza(sb, 1);
                    sb.append("}\n");
                }
            }
        }
        sb.append("}\n");
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public final String zzac(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        if (!zzbat()) {
            return bundle.toString();
        }
        StringBuilder sb = new StringBuilder();
        for (String str : bundle.keySet()) {
            sb.append(sb.length() != 0 ? ", " : "Bundle[{");
            sb.append(zzjq(str));
            sb.append("=");
            sb.append(bundle.get(str));
        }
        sb.append("}]");
        return sb.toString();
    }

    public final /* bridge */ /* synthetic */ void zzaxz() {
        super.zzaxz();
    }

    public final /* bridge */ /* synthetic */ void zzaya() {
        super.zzaya();
    }

    public final /* bridge */ /* synthetic */ zzcia zzayb() {
        return super.zzayb();
    }

    public final /* bridge */ /* synthetic */ zzcih zzayc() {
        return super.zzayc();
    }

    public final /* bridge */ /* synthetic */ zzclk zzayd() {
        return super.zzayd();
    }

    public final /* bridge */ /* synthetic */ zzcje zzaye() {
        return super.zzaye();
    }

    public final /* bridge */ /* synthetic */ zzcir zzayf() {
        return super.zzayf();
    }

    public final /* bridge */ /* synthetic */ zzcme zzayg() {
        return super.zzayg();
    }

    public final /* bridge */ /* synthetic */ zzcma zzayh() {
        return super.zzayh();
    }

    public final /* bridge */ /* synthetic */ zzcjf zzayi() {
        return super.zzayi();
    }

    public final /* bridge */ /* synthetic */ zzcil zzayj() {
        return super.zzayj();
    }

    public final /* bridge */ /* synthetic */ zzcjh zzayk() {
        return super.zzayk();
    }

    public final /* bridge */ /* synthetic */ zzcno zzayl() {
        return super.zzayl();
    }

    public final /* bridge */ /* synthetic */ zzckd zzaym() {
        return super.zzaym();
    }

    public final /* bridge */ /* synthetic */ zzcnd zzayn() {
        return super.zzayn();
    }

    public final /* bridge */ /* synthetic */ zzcke zzayo() {
        return super.zzayo();
    }

    public final /* bridge */ /* synthetic */ zzcjj zzayp() {
        return super.zzayp();
    }

    public final /* bridge */ /* synthetic */ zzcju zzayq() {
        return super.zzayq();
    }

    public final /* bridge */ /* synthetic */ zzcik zzayr() {
        return super.zzayr();
    }

    /* access modifiers changed from: protected */
    public final boolean zzazq() {
        return false;
    }

    /* access modifiers changed from: protected */
    public final String zzb(zzcix zzcix) {
        if (zzcix == null) {
            return null;
        }
        if (!zzbat()) {
            return zzcix.toString();
        }
        return "origin=" + zzcix.zzjgm + ",name=" + zzjp(zzcix.name) + ",params=" + zzb(zzcix.zzjhr);
    }

    /* access modifiers changed from: protected */
    public final String zzjp(String str) {
        if (str == null) {
            return null;
        }
        return !zzbat() ? str : zza(str, AppMeasurement.Event.zzjex, AppMeasurement.Event.zzjew, zzjkd);
    }

    /* access modifiers changed from: protected */
    public final String zzjq(String str) {
        if (str == null) {
            return null;
        }
        return !zzbat() ? str : zza(str, AppMeasurement.Param.zzjez, AppMeasurement.Param.zzjey, zzjke);
    }

    /* access modifiers changed from: protected */
    public final String zzjr(String str) {
        if (str == null) {
            return null;
        }
        if (!zzbat()) {
            return str;
        }
        if (!str.startsWith("_exp_")) {
            return zza(str, AppMeasurement.UserProperty.zzjfb, AppMeasurement.UserProperty.zzjfa, zzjkf);
        }
        return "experiment_id" + "(" + str + ")";
    }

    public final /* bridge */ /* synthetic */ void zzwj() {
        super.zzwj();
    }

    public final /* bridge */ /* synthetic */ zze zzxx() {
        return super.zzxx();
    }
}
