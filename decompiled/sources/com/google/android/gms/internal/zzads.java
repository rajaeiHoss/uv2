package com.google.android.gms.internal;

import android.location.Location;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;

@zzabh
public final class zzads {
    private static final SimpleDateFormat zzcwi = new SimpleDateFormat("yyyyMMdd", Locale.US);

    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b5 A[Catch:{ JSONException -> 0x0239 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00d5 A[Catch:{ JSONException -> 0x0239 }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00dc A[Catch:{ JSONException -> 0x0239 }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00e2 A[Catch:{ JSONException -> 0x0239 }] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0145 A[Catch:{ JSONException -> 0x0239 }] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x014e A[Catch:{ JSONException -> 0x0239 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.zzacj zza(android.content.Context r50, com.google.android.gms.internal.zzacf r51, java.lang.String r52) {
        /*
            r0 = r51
            java.lang.String r1 = "interstitial_timeout"
            java.lang.String r9 = ""
            r15 = 0
            org.json.JSONObject r10 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0239 }
            r2 = r52
            r10.<init>(r2)     // Catch:{ JSONException -> 0x0239 }
            java.lang.String r2 = "ad_base_url"
            r11 = 0
            java.lang.String r2 = r10.optString(r2, r11)     // Catch:{ JSONException -> 0x0239 }
            java.lang.String r3 = "ad_url"
            java.lang.String r4 = r10.optString(r3, r11)     // Catch:{ JSONException -> 0x0239 }
            java.lang.String r3 = "ad_size"
            java.lang.String r13 = r10.optString(r3, r11)     // Catch:{ JSONException -> 0x0239 }
            java.lang.String r3 = "ad_slot_size"
            java.lang.String r40 = r10.optString(r3, r13)     // Catch:{ JSONException -> 0x0239 }
            r12 = 1
            if (r0 == 0) goto L_0x0031
            int r3 = r0.zzcsb     // Catch:{ JSONException -> 0x0239 }
            if (r3 == 0) goto L_0x0031
            r24 = 1
            goto L_0x0033
        L_0x0031:
            r24 = 0
        L_0x0033:
            java.lang.String r3 = "ad_json"
            java.lang.String r3 = r10.optString(r3, r11)     // Catch:{ JSONException -> 0x0239 }
            if (r3 != 0) goto L_0x0041
            java.lang.String r3 = "ad_html"
            java.lang.String r3 = r10.optString(r3, r11)     // Catch:{ JSONException -> 0x0239 }
        L_0x0041:
            if (r3 != 0) goto L_0x0049
            java.lang.String r3 = "body"
            java.lang.String r3 = r10.optString(r3, r11)     // Catch:{ JSONException -> 0x0239 }
        L_0x0049:
            if (r3 != 0) goto L_0x0057
            java.lang.String r5 = "ads"
            boolean r5 = r10.has(r5)     // Catch:{ JSONException -> 0x0239 }
            if (r5 == 0) goto L_0x0057
            java.lang.String r3 = r10.toString()     // Catch:{ JSONException -> 0x0239 }
        L_0x0057:
            java.lang.String r5 = "debug_dialog"
            java.lang.String r19 = r10.optString(r5, r11)     // Catch:{ JSONException -> 0x0239 }
            java.lang.String r5 = "debug_signals"
            java.lang.String r42 = r10.optString(r5, r11)     // Catch:{ JSONException -> 0x0239 }
            boolean r5 = r10.has(r1)     // Catch:{ JSONException -> 0x0239 }
            r7 = -1
            if (r5 == 0) goto L_0x007a
            double r5 = r10.getDouble(r1)     // Catch:{ JSONException -> 0x0239 }
            r16 = 4652007308841189376(0x408f400000000000, double:1000.0)
            double r5 = r5 * r16
            long r5 = (long) r5     // Catch:{ JSONException -> 0x0239 }
            r16 = r5
            goto L_0x007c
        L_0x007a:
            r16 = r7
        L_0x007c:
            java.lang.String r1 = "orientation"
            java.lang.String r1 = r10.optString(r1, r11)     // Catch:{ JSONException -> 0x0239 }
            java.lang.String r5 = "portrait"
            boolean r5 = r5.equals(r1)     // Catch:{ JSONException -> 0x0239 }
            r14 = -1
            if (r5 == 0) goto L_0x0096
            com.google.android.gms.internal.zzaip r1 = com.google.android.gms.ads.internal.zzbt.zzen()     // Catch:{ JSONException -> 0x0239 }
            int r1 = r1.zzrh()     // Catch:{ JSONException -> 0x0239 }
        L_0x0093:
            r18 = r1
            goto L_0x00a9
        L_0x0096:
            java.lang.String r5 = "landscape"
            boolean r1 = r5.equals(r1)     // Catch:{ JSONException -> 0x0239 }
            if (r1 == 0) goto L_0x00a7
            com.google.android.gms.internal.zzaip r1 = com.google.android.gms.ads.internal.zzbt.zzen()     // Catch:{ JSONException -> 0x0239 }
            int r1 = r1.zzrg()     // Catch:{ JSONException -> 0x0239 }
            goto L_0x0093
        L_0x00a7:
            r18 = -1
        L_0x00a9:
            boolean r1 = android.text.TextUtils.isEmpty(r3)     // Catch:{ JSONException -> 0x0239 }
            if (r1 == 0) goto L_0x00d5
            boolean r1 = android.text.TextUtils.isEmpty(r4)     // Catch:{ JSONException -> 0x0239 }
            if (r1 != 0) goto L_0x00d5
            com.google.android.gms.internal.zzala r1 = r0.zzatz     // Catch:{ JSONException -> 0x0239 }
            java.lang.String r3 = r1.zzcu     // Catch:{ JSONException -> 0x0239 }
            r5 = 0
            r6 = 0
            r20 = 0
            r21 = 0
            r1 = r51
            r2 = r50
            r7 = r20
            r8 = r21
            com.google.android.gms.internal.zzacj r1 = com.google.android.gms.internal.zzadn.zza(r1, r2, r3, r4, r5, r6, r7, r8)     // Catch:{ JSONException -> 0x0239 }
            java.lang.String r2 = r1.zzcno     // Catch:{ JSONException -> 0x0239 }
            java.lang.String r3 = r1.body     // Catch:{ JSONException -> 0x0239 }
            long r4 = r1.zzcts     // Catch:{ JSONException -> 0x0239 }
            r20 = r4
            r4 = r3
            goto L_0x00d9
        L_0x00d5:
            r4 = r3
            r1 = r11
            r20 = -1
        L_0x00d9:
            r3 = r2
            if (r4 != 0) goto L_0x00e2
            com.google.android.gms.internal.zzacj r0 = new com.google.android.gms.internal.zzacj     // Catch:{ JSONException -> 0x0239 }
            r0.<init>(r15)     // Catch:{ JSONException -> 0x0239 }
            return r0
        L_0x00e2:
            java.lang.String r2 = "click_urls"
            org.json.JSONArray r2 = r10.optJSONArray(r2)     // Catch:{ JSONException -> 0x0239 }
            if (r1 != 0) goto L_0x00ec
            r5 = r11
            goto L_0x00ee
        L_0x00ec:
            java.util.List<java.lang.String> r5 = r1.zzchw     // Catch:{ JSONException -> 0x0239 }
        L_0x00ee:
            if (r2 == 0) goto L_0x00f5
            java.util.List r2 = zza((org.json.JSONArray) r2, (java.util.List<java.lang.String>) r5)     // Catch:{ JSONException -> 0x0239 }
            r5 = r2
        L_0x00f5:
            java.lang.String r2 = "impression_urls"
            org.json.JSONArray r2 = r10.optJSONArray(r2)     // Catch:{ JSONException -> 0x0239 }
            if (r1 != 0) goto L_0x00ff
            r6 = r11
            goto L_0x0101
        L_0x00ff:
            java.util.List<java.lang.String> r6 = r1.zzchx     // Catch:{ JSONException -> 0x0239 }
        L_0x0101:
            if (r2 == 0) goto L_0x0108
            java.util.List r2 = zza((org.json.JSONArray) r2, (java.util.List<java.lang.String>) r6)     // Catch:{ JSONException -> 0x0239 }
            r6 = r2
        L_0x0108:
            java.lang.String r2 = "manual_impression_urls"
            org.json.JSONArray r2 = r10.optJSONArray(r2)     // Catch:{ JSONException -> 0x0239 }
            if (r1 != 0) goto L_0x0112
            r7 = r11
            goto L_0x0114
        L_0x0112:
            java.util.List<java.lang.String> r7 = r1.zzctq     // Catch:{ JSONException -> 0x0239 }
        L_0x0114:
            if (r2 == 0) goto L_0x011d
            java.util.List r2 = zza((org.json.JSONArray) r2, (java.util.List<java.lang.String>) r7)     // Catch:{ JSONException -> 0x0239 }
            r22 = r2
            goto L_0x011f
        L_0x011d:
            r22 = r7
        L_0x011f:
            if (r1 == 0) goto L_0x0135
            int r2 = r1.orientation     // Catch:{ JSONException -> 0x0239 }
            if (r2 == r14) goto L_0x0129
            int r2 = r1.orientation     // Catch:{ JSONException -> 0x0239 }
            r18 = r2
        L_0x0129:
            long r7 = r1.zzctn     // Catch:{ JSONException -> 0x0239 }
            r25 = 0
            int r2 = (r7 > r25 ? 1 : (r7 == r25 ? 0 : -1))
            if (r2 <= 0) goto L_0x0135
            long r1 = r1.zzctn     // Catch:{ JSONException -> 0x0239 }
            r7 = r1
            goto L_0x0137
        L_0x0135:
            r7 = r16
        L_0x0137:
            java.lang.String r1 = "active_view"
            java.lang.String r23 = r10.optString(r1)     // Catch:{ JSONException -> 0x0239 }
            java.lang.String r1 = "ad_is_javascript"
            boolean r25 = r10.optBoolean(r1, r15)     // Catch:{ JSONException -> 0x0239 }
            if (r25 == 0) goto L_0x014e
            java.lang.String r1 = "ad_passback_url"
            java.lang.String r1 = r10.optString(r1, r11)     // Catch:{ JSONException -> 0x0239 }
            r26 = r1
            goto L_0x0150
        L_0x014e:
            r26 = r11
        L_0x0150:
            java.lang.String r1 = "mediation"
            boolean r14 = r10.optBoolean(r1, r15)     // Catch:{ JSONException -> 0x0239 }
            java.lang.String r1 = "custom_render_allowed"
            boolean r27 = r10.optBoolean(r1, r15)     // Catch:{ JSONException -> 0x0239 }
            java.lang.String r1 = "content_url_opted_out"
            boolean r28 = r10.optBoolean(r1, r12)     // Catch:{ JSONException -> 0x0239 }
            java.lang.String r1 = "content_vertical_opted_out"
            boolean r43 = r10.optBoolean(r1, r12)     // Catch:{ JSONException -> 0x0239 }
            java.lang.String r1 = "prefetch"
            boolean r29 = r10.optBoolean(r1, r15)     // Catch:{ JSONException -> 0x0239 }
            java.lang.String r1 = "refresh_interval_milliseconds"
            r11 = -1
            long r16 = r10.optLong(r1, r11)     // Catch:{ JSONException -> 0x0239 }
            java.lang.String r1 = "mediation_config_cache_time_milliseconds"
            long r11 = r10.optLong(r1, r11)     // Catch:{ JSONException -> 0x0239 }
            java.lang.String r1 = "gws_query_id"
            java.lang.String r30 = r10.optString(r1, r9)     // Catch:{ JSONException -> 0x0239 }
            java.lang.String r1 = "height"
            java.lang.String r2 = "fluid"
            java.lang.String r2 = r10.optString(r2, r9)     // Catch:{ JSONException -> 0x0239 }
            boolean r31 = r1.equals(r2)     // Catch:{ JSONException -> 0x0239 }
            java.lang.String r1 = "native_express"
            boolean r32 = r10.optBoolean(r1, r15)     // Catch:{ JSONException -> 0x0239 }
            java.lang.String r1 = "video_start_urls"
            org.json.JSONArray r1 = r10.optJSONArray(r1)     // Catch:{ JSONException -> 0x0239 }
            r2 = 0
            java.util.List r33 = zza((org.json.JSONArray) r1, (java.util.List<java.lang.String>) r2)     // Catch:{ JSONException -> 0x0239 }
            java.lang.String r1 = "video_complete_urls"
            org.json.JSONArray r1 = r10.optJSONArray(r1)     // Catch:{ JSONException -> 0x0239 }
            java.util.List r34 = zza((org.json.JSONArray) r1, (java.util.List<java.lang.String>) r2)     // Catch:{ JSONException -> 0x0239 }
            java.lang.String r1 = "rewards"
            org.json.JSONArray r1 = r10.optJSONArray(r1)     // Catch:{ JSONException -> 0x0239 }
            com.google.android.gms.internal.zzagd r35 = com.google.android.gms.internal.zzagd.zza(r1)     // Catch:{ JSONException -> 0x0239 }
            java.lang.String r1 = "use_displayed_impression"
            boolean r36 = r10.optBoolean(r1, r15)     // Catch:{ JSONException -> 0x0239 }
            java.lang.String r1 = "auto_protection_configuration"
            org.json.JSONObject r1 = r10.optJSONObject(r1)     // Catch:{ JSONException -> 0x0239 }
            com.google.android.gms.internal.zzacl r37 = com.google.android.gms.internal.zzacl.zzk(r1)     // Catch:{ JSONException -> 0x0239 }
            java.lang.String r1 = "set_cookie"
            java.lang.String r38 = r10.optString(r1, r9)     // Catch:{ JSONException -> 0x0239 }
            java.lang.String r1 = "remote_ping_urls"
            org.json.JSONArray r1 = r10.optJSONArray(r1)     // Catch:{ JSONException -> 0x0239 }
            r2 = 0
            java.util.List r39 = zza((org.json.JSONArray) r1, (java.util.List<java.lang.String>) r2)     // Catch:{ JSONException -> 0x0239 }
            java.lang.String r1 = "safe_browsing"
            org.json.JSONObject r1 = r10.optJSONObject(r1)     // Catch:{ JSONException -> 0x0239 }
            com.google.android.gms.internal.zzagn r41 = com.google.android.gms.internal.zzagn.zzn(r1)     // Catch:{ JSONException -> 0x0239 }
            java.lang.String r1 = "render_in_browser"
            boolean r2 = r0.zzcia     // Catch:{ JSONException -> 0x0239 }
            boolean r44 = r10.optBoolean(r1, r2)     // Catch:{ JSONException -> 0x0239 }
            java.lang.String r1 = "custom_close_blocked"
            boolean r45 = r10.optBoolean(r1)     // Catch:{ JSONException -> 0x0239 }
            com.google.android.gms.internal.zzacj r47 = new com.google.android.gms.internal.zzacj     // Catch:{ JSONException -> 0x0239 }
            boolean r10 = r0.zzcsd     // Catch:{ JSONException -> 0x0239 }
            boolean r9 = r0.zzcsr     // Catch:{ JSONException -> 0x0239 }
            boolean r2 = r0.zzctd     // Catch:{ JSONException -> 0x0239 }
            r46 = 0
            r1 = r47
            r48 = r2
            r2 = r51
            r0 = r9
            r9 = r14
            r49 = r10
            r10 = r11
            r12 = r22
            r22 = r13
            r13 = r16
            r15 = r18
            r16 = r22
            r17 = r20
            r20 = r25
            r21 = r26
            r22 = r23
            r23 = r27
            r25 = r49
            r26 = r28
            r27 = r29
            r28 = r30
            r29 = r31
            r30 = r32
            r31 = r35
            r32 = r33
            r33 = r34
            r34 = r36
            r35 = r37
            r36 = r0
            r37 = r38
            r38 = r39
            r39 = r44
            r44 = r48
            r1.<init>(r2, r3, r4, r5, r6, r7, r9, r10, r12, r13, r15, r16, r17, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46)     // Catch:{ JSONException -> 0x0239 }
            return r47
        L_0x0239:
            r0 = move-exception
            java.lang.String r1 = "Could not parse the inline ad response: "
            java.lang.String r0 = r0.getMessage()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r2 = r0.length()
            if (r2 == 0) goto L_0x024f
            java.lang.String r0 = r1.concat(r0)
            goto L_0x0254
        L_0x024f:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r1)
        L_0x0254:
            com.google.android.gms.internal.zzahw.zzcz(r0)
            com.google.android.gms.internal.zzacj r0 = new com.google.android.gms.internal.zzacj
            r1 = 0
            r0.<init>(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzads.zza(android.content.Context, com.google.android.gms.internal.zzacf, java.lang.String):com.google.android.gms.internal.zzacj");
    }

    private static List<String> zza(JSONArray jSONArray, List<String> list) throws JSONException {
        if (jSONArray == null) {
            return null;
        }
        if (list == null) {
            list = new LinkedList<>();
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            list.add(jSONArray.getString(i));
        }
        return list;
    }

    /* JADX WARNING: Removed duplicated region for block: B:322:0x0801 A[Catch:{ JSONException -> 0x08e3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:323:0x0803 A[Catch:{ JSONException -> 0x08e3 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.json.JSONObject zza(android.content.Context r23, com.google.android.gms.internal.zzadl r24) {
        /*
            r1 = r24
            java.lang.String r2 = "web_view_count"
            com.google.android.gms.internal.zzacf r3 = r1.zzcvm
            android.location.Location r4 = r1.zzbhd
            com.google.android.gms.internal.zzaea r5 = r1.zzcvn
            android.os.Bundle r6 = r1.zzcsc
            org.json.JSONObject r7 = r1.zzcvo
            java.util.HashMap r9 = new java.util.HashMap     // Catch:{ JSONException -> 0x08e3 }
            r9.<init>()     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r10 = "extra_caps"
            com.google.android.gms.internal.zzny<java.lang.String> r11 = com.google.android.gms.internal.zzoi.zzbsd     // Catch:{ JSONException -> 0x08e3 }
            com.google.android.gms.internal.zzog r12 = com.google.android.gms.internal.zzlc.zzio()     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Object r11 = r12.zzd(r11)     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r10, r11)     // Catch:{ JSONException -> 0x08e3 }
            java.util.List<java.lang.String> r10 = r1.zzcsj     // Catch:{ JSONException -> 0x08e3 }
            int r10 = r10.size()     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r11 = ","
            if (r10 <= 0) goto L_0x0037
            java.lang.String r10 = "eid"
            java.util.List<java.lang.String> r12 = r1.zzcsj     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r12 = android.text.TextUtils.join(r11, r12)     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r10, r12)     // Catch:{ JSONException -> 0x08e3 }
        L_0x0037:
            android.os.Bundle r10 = r3.zzcru     // Catch:{ JSONException -> 0x08e3 }
            if (r10 == 0) goto L_0x0042
            java.lang.String r10 = "ad_pos"
            android.os.Bundle r12 = r3.zzcru     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r10, r12)     // Catch:{ JSONException -> 0x08e3 }
        L_0x0042:
            com.google.android.gms.internal.zzkk r10 = r3.zzcrv     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r12 = com.google.android.gms.internal.zzahr.zzqi()     // Catch:{ JSONException -> 0x08e3 }
            if (r12 == 0) goto L_0x004f
            java.lang.String r13 = "abf"
            r9.put(r13, r12)     // Catch:{ JSONException -> 0x08e3 }
        L_0x004f:
            long r12 = r10.zzbgv     // Catch:{ JSONException -> 0x08e3 }
            r14 = -1
            int r16 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r16 == 0) goto L_0x0069
            java.lang.String r12 = "cust_age"
            java.text.SimpleDateFormat r13 = zzcwi     // Catch:{ JSONException -> 0x08e3 }
            java.util.Date r8 = new java.util.Date     // Catch:{ JSONException -> 0x08e3 }
            long r14 = r10.zzbgv     // Catch:{ JSONException -> 0x08e3 }
            r8.<init>(r14)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r8 = r13.format(r8)     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r12, r8)     // Catch:{ JSONException -> 0x08e3 }
        L_0x0069:
            android.os.Bundle r8 = r10.extras     // Catch:{ JSONException -> 0x08e3 }
            if (r8 == 0) goto L_0x0074
            java.lang.String r8 = "extras"
            android.os.Bundle r12 = r10.extras     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r8, r12)     // Catch:{ JSONException -> 0x08e3 }
        L_0x0074:
            int r8 = r10.zzbgw     // Catch:{ JSONException -> 0x08e3 }
            r12 = -1
            if (r8 == r12) goto L_0x0084
            java.lang.String r8 = "cust_gender"
            int r13 = r10.zzbgw     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r8, r13)     // Catch:{ JSONException -> 0x08e3 }
        L_0x0084:
            java.util.List<java.lang.String> r8 = r10.zzbgx     // Catch:{ JSONException -> 0x08e3 }
            if (r8 == 0) goto L_0x008f
            java.lang.String r8 = "kw"
            java.util.List<java.lang.String> r13 = r10.zzbgx     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r8, r13)     // Catch:{ JSONException -> 0x08e3 }
        L_0x008f:
            int r8 = r10.zzbgz     // Catch:{ JSONException -> 0x08e3 }
            if (r8 == r12) goto L_0x009e
            java.lang.String r8 = "tag_for_child_directed_treatment"
            int r13 = r10.zzbgz     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r8, r13)     // Catch:{ JSONException -> 0x08e3 }
        L_0x009e:
            boolean r8 = r10.zzbgy     // Catch:{ JSONException -> 0x08e3 }
            r13 = 1
            if (r8 == 0) goto L_0x00c4
            com.google.android.gms.internal.zzny<java.lang.Boolean> r8 = com.google.android.gms.internal.zzoi.zzbvu     // Catch:{ JSONException -> 0x08e3 }
            com.google.android.gms.internal.zzog r14 = com.google.android.gms.internal.zzlc.zzio()     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Object r8 = r14.zzd(r8)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ JSONException -> 0x08e3 }
            boolean r8 = r8.booleanValue()     // Catch:{ JSONException -> 0x08e3 }
            if (r8 == 0) goto L_0x00bf
            java.lang.String r8 = "test_request"
            java.lang.Boolean r14 = java.lang.Boolean.valueOf(r13)     // Catch:{ JSONException -> 0x08e3 }
        L_0x00bb:
            r9.put(r8, r14)     // Catch:{ JSONException -> 0x08e3 }
            goto L_0x00c4
        L_0x00bf:
            java.lang.String r8 = "adtest"
            java.lang.String r14 = "on"
            goto L_0x00bb
        L_0x00c4:
            int r8 = r10.versionCode     // Catch:{ JSONException -> 0x08e3 }
            r14 = 2
            if (r8 < r14) goto L_0x00e5
            boolean r8 = r10.zzbha     // Catch:{ JSONException -> 0x08e3 }
            if (r8 == 0) goto L_0x00d6
            java.lang.String r8 = "d_imp_hdr"
            java.lang.Integer r15 = java.lang.Integer.valueOf(r13)     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r8, r15)     // Catch:{ JSONException -> 0x08e3 }
        L_0x00d6:
            java.lang.String r8 = r10.zzbhb     // Catch:{ JSONException -> 0x08e3 }
            boolean r8 = android.text.TextUtils.isEmpty(r8)     // Catch:{ JSONException -> 0x08e3 }
            if (r8 != 0) goto L_0x00e5
            java.lang.String r8 = "ppid"
            java.lang.String r15 = r10.zzbhb     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r8, r15)     // Catch:{ JSONException -> 0x08e3 }
        L_0x00e5:
            int r8 = r10.versionCode     // Catch:{ JSONException -> 0x08e3 }
            r15 = 3
            if (r8 < r15) goto L_0x00f5
            java.lang.String r8 = r10.zzbhe     // Catch:{ JSONException -> 0x08e3 }
            if (r8 == 0) goto L_0x00f5
            java.lang.String r8 = "url"
            java.lang.String r15 = r10.zzbhe     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r8, r15)     // Catch:{ JSONException -> 0x08e3 }
        L_0x00f5:
            int r8 = r10.versionCode     // Catch:{ JSONException -> 0x08e3 }
            r15 = 5
            if (r8 < r15) goto L_0x011b
            android.os.Bundle r8 = r10.zzbhg     // Catch:{ JSONException -> 0x08e3 }
            if (r8 == 0) goto L_0x0105
            java.lang.String r8 = "custom_targeting"
            android.os.Bundle r15 = r10.zzbhg     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r8, r15)     // Catch:{ JSONException -> 0x08e3 }
        L_0x0105:
            java.util.List<java.lang.String> r8 = r10.zzbhh     // Catch:{ JSONException -> 0x08e3 }
            if (r8 == 0) goto L_0x0110
            java.lang.String r8 = "category_exclusions"
            java.util.List<java.lang.String> r15 = r10.zzbhh     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r8, r15)     // Catch:{ JSONException -> 0x08e3 }
        L_0x0110:
            java.lang.String r8 = r10.zzbhi     // Catch:{ JSONException -> 0x08e3 }
            if (r8 == 0) goto L_0x011b
            java.lang.String r8 = "request_agent"
            java.lang.String r15 = r10.zzbhi     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r8, r15)     // Catch:{ JSONException -> 0x08e3 }
        L_0x011b:
            int r8 = r10.versionCode     // Catch:{ JSONException -> 0x08e3 }
            r15 = 6
            if (r8 < r15) goto L_0x012b
            java.lang.String r8 = r10.zzbhj     // Catch:{ JSONException -> 0x08e3 }
            if (r8 == 0) goto L_0x012b
            java.lang.String r8 = "request_pkg"
            java.lang.String r15 = r10.zzbhj     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r8, r15)     // Catch:{ JSONException -> 0x08e3 }
        L_0x012b:
            int r8 = r10.versionCode     // Catch:{ JSONException -> 0x08e3 }
            r15 = 7
            if (r8 < r15) goto L_0x013b
            java.lang.String r8 = "is_designed_for_families"
            boolean r10 = r10.zzbhk     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r10)     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r8, r10)     // Catch:{ JSONException -> 0x08e3 }
        L_0x013b:
            com.google.android.gms.internal.zzko r8 = r3.zzaud     // Catch:{ JSONException -> 0x08e3 }
            com.google.android.gms.internal.zzko[] r8 = r8.zzbic     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r10 = "height"
            java.lang.String r15 = "fluid"
            java.lang.String r14 = "format"
            if (r8 != 0) goto L_0x0158
            com.google.android.gms.internal.zzko r8 = r3.zzaud     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r8 = r8.zzbia     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r14, r8)     // Catch:{ JSONException -> 0x08e3 }
            com.google.android.gms.internal.zzko r8 = r3.zzaud     // Catch:{ JSONException -> 0x08e3 }
            boolean r8 = r8.zzbie     // Catch:{ JSONException -> 0x08e3 }
            if (r8 == 0) goto L_0x018d
            r9.put(r15, r10)     // Catch:{ JSONException -> 0x08e3 }
            goto L_0x018d
        L_0x0158:
            com.google.android.gms.internal.zzko r8 = r3.zzaud     // Catch:{ JSONException -> 0x08e3 }
            com.google.android.gms.internal.zzko[] r8 = r8.zzbic     // Catch:{ JSONException -> 0x08e3 }
            int r13 = r8.length     // Catch:{ JSONException -> 0x08e3 }
            r12 = 0
            r19 = 0
            r20 = 0
        L_0x0162:
            if (r12 >= r13) goto L_0x018d
            r21 = r13
            r13 = r8[r12]     // Catch:{ JSONException -> 0x08e3 }
            r22 = r8
            boolean r8 = r13.zzbie     // Catch:{ JSONException -> 0x08e3 }
            if (r8 != 0) goto L_0x0177
            if (r19 != 0) goto L_0x0177
            java.lang.String r8 = r13.zzbia     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r14, r8)     // Catch:{ JSONException -> 0x08e3 }
            r19 = 1
        L_0x0177:
            boolean r8 = r13.zzbie     // Catch:{ JSONException -> 0x08e3 }
            if (r8 == 0) goto L_0x0182
            if (r20 != 0) goto L_0x0182
            r9.put(r15, r10)     // Catch:{ JSONException -> 0x08e3 }
            r20 = 1
        L_0x0182:
            if (r19 == 0) goto L_0x0186
            if (r20 != 0) goto L_0x018d
        L_0x0186:
            int r12 = r12 + 1
            r13 = r21
            r8 = r22
            goto L_0x0162
        L_0x018d:
            com.google.android.gms.internal.zzko r8 = r3.zzaud     // Catch:{ JSONException -> 0x08e3 }
            int r8 = r8.width     // Catch:{ JSONException -> 0x08e3 }
            r10 = -1
            if (r8 != r10) goto L_0x019b
            java.lang.String r8 = "smart_w"
            java.lang.String r10 = "full"
            r9.put(r8, r10)     // Catch:{ JSONException -> 0x08e3 }
        L_0x019b:
            com.google.android.gms.internal.zzko r8 = r3.zzaud     // Catch:{ JSONException -> 0x08e3 }
            int r8 = r8.height     // Catch:{ JSONException -> 0x08e3 }
            r10 = -2
            if (r8 != r10) goto L_0x01a9
            java.lang.String r8 = "smart_h"
            java.lang.String r12 = "auto"
            r9.put(r8, r12)     // Catch:{ JSONException -> 0x08e3 }
        L_0x01a9:
            com.google.android.gms.internal.zzko r8 = r3.zzaud     // Catch:{ JSONException -> 0x08e3 }
            com.google.android.gms.internal.zzko[] r8 = r8.zzbic     // Catch:{ JSONException -> 0x08e3 }
            if (r8 == 0) goto L_0x021d
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x08e3 }
            r8.<init>()     // Catch:{ JSONException -> 0x08e3 }
            com.google.android.gms.internal.zzko r12 = r3.zzaud     // Catch:{ JSONException -> 0x08e3 }
            com.google.android.gms.internal.zzko[] r12 = r12.zzbic     // Catch:{ JSONException -> 0x08e3 }
            int r13 = r12.length     // Catch:{ JSONException -> 0x08e3 }
            r14 = 0
            r15 = 0
        L_0x01bb:
            java.lang.String r10 = "|"
            if (r15 >= r13) goto L_0x0205
            r20 = r13
            r13 = r12[r15]     // Catch:{ JSONException -> 0x08e3 }
            r21 = r12
            boolean r12 = r13.zzbie     // Catch:{ JSONException -> 0x08e3 }
            if (r12 == 0) goto L_0x01cc
            r14 = 1
            goto L_0x01fe
        L_0x01cc:
            int r12 = r8.length()     // Catch:{ JSONException -> 0x08e3 }
            if (r12 == 0) goto L_0x01d5
            r8.append(r10)     // Catch:{ JSONException -> 0x08e3 }
        L_0x01d5:
            int r10 = r13.width     // Catch:{ JSONException -> 0x08e3 }
            r12 = -1
            if (r10 != r12) goto L_0x01e2
            int r10 = r13.widthPixels     // Catch:{ JSONException -> 0x08e3 }
            float r10 = (float) r10     // Catch:{ JSONException -> 0x08e3 }
            float r12 = r5.zzaxz     // Catch:{ JSONException -> 0x08e3 }
            float r10 = r10 / r12
            int r10 = (int) r10     // Catch:{ JSONException -> 0x08e3 }
            goto L_0x01e4
        L_0x01e2:
            int r10 = r13.width     // Catch:{ JSONException -> 0x08e3 }
        L_0x01e4:
            r8.append(r10)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r10 = "x"
            r8.append(r10)     // Catch:{ JSONException -> 0x08e3 }
            int r10 = r13.height     // Catch:{ JSONException -> 0x08e3 }
            r12 = -2
            if (r10 != r12) goto L_0x01f9
            int r10 = r13.heightPixels     // Catch:{ JSONException -> 0x08e3 }
            float r10 = (float) r10     // Catch:{ JSONException -> 0x08e3 }
            float r12 = r5.zzaxz     // Catch:{ JSONException -> 0x08e3 }
            float r10 = r10 / r12
            int r10 = (int) r10     // Catch:{ JSONException -> 0x08e3 }
            goto L_0x01fb
        L_0x01f9:
            int r10 = r13.height     // Catch:{ JSONException -> 0x08e3 }
        L_0x01fb:
            r8.append(r10)     // Catch:{ JSONException -> 0x08e3 }
        L_0x01fe:
            int r15 = r15 + 1
            r13 = r20
            r12 = r21
            goto L_0x01bb
        L_0x0205:
            if (r14 == 0) goto L_0x0218
            int r12 = r8.length()     // Catch:{ JSONException -> 0x08e3 }
            if (r12 == 0) goto L_0x0212
            r12 = 0
            r8.insert(r12, r10)     // Catch:{ JSONException -> 0x08e3 }
            goto L_0x0213
        L_0x0212:
            r12 = 0
        L_0x0213:
            java.lang.String r10 = "320x50"
            r8.insert(r12, r10)     // Catch:{ JSONException -> 0x08e3 }
        L_0x0218:
            java.lang.String r10 = "sz"
            r9.put(r10, r8)     // Catch:{ JSONException -> 0x08e3 }
        L_0x021d:
            int r8 = r3.zzcsb     // Catch:{ JSONException -> 0x08e3 }
            r10 = 24
            if (r8 == 0) goto L_0x028d
            java.lang.String r8 = "native_version"
            int r12 = r3.zzcsb     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r8, r12)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r8 = "native_templates"
            java.util.List<java.lang.String> r12 = r3.zzauy     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r8, r12)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r8 = "native_image_orientation"
            com.google.android.gms.internal.zzqh r12 = r3.zzauq     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r13 = "any"
            if (r12 != 0) goto L_0x023e
            goto L_0x0250
        L_0x023e:
            int r12 = r12.zzbzk     // Catch:{ JSONException -> 0x08e3 }
            if (r12 == 0) goto L_0x0250
            r14 = 1
            if (r12 == r14) goto L_0x024e
            r13 = 2
            if (r12 == r13) goto L_0x024b
            java.lang.String r13 = "not_set"
            goto L_0x0250
        L_0x024b:
            java.lang.String r13 = "landscape"
            goto L_0x0250
        L_0x024e:
            java.lang.String r13 = "portrait"
        L_0x0250:
            r9.put(r8, r13)     // Catch:{ JSONException -> 0x08e3 }
            java.util.List<java.lang.String> r8 = r3.zzcsk     // Catch:{ JSONException -> 0x08e3 }
            boolean r8 = r8.isEmpty()     // Catch:{ JSONException -> 0x08e3 }
            if (r8 != 0) goto L_0x0262
            java.lang.String r8 = "native_custom_templates"
            java.util.List<java.lang.String> r12 = r3.zzcsk     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r8, r12)     // Catch:{ JSONException -> 0x08e3 }
        L_0x0262:
            int r8 = r3.versionCode     // Catch:{ JSONException -> 0x08e3 }
            if (r8 < r10) goto L_0x0271
            java.lang.String r8 = "max_num_ads"
            int r12 = r3.zzctg     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r8, r12)     // Catch:{ JSONException -> 0x08e3 }
        L_0x0271:
            java.lang.String r8 = r3.zzcte     // Catch:{ JSONException -> 0x08e3 }
            boolean r8 = android.text.TextUtils.isEmpty(r8)     // Catch:{ JSONException -> 0x08e3 }
            if (r8 != 0) goto L_0x028d
            java.lang.String r8 = "native_advanced_settings"
            org.json.JSONArray r12 = new org.json.JSONArray     // Catch:{ JSONException -> 0x0286 }
            java.lang.String r13 = r3.zzcte     // Catch:{ JSONException -> 0x0286 }
            r12.<init>(r13)     // Catch:{ JSONException -> 0x0286 }
            r9.put(r8, r12)     // Catch:{ JSONException -> 0x0286 }
            goto L_0x028d
        L_0x0286:
            r0 = move-exception
            r8 = r0
            java.lang.String r12 = "Problem creating json from native advanced settings"
            com.google.android.gms.internal.zzahw.zzc(r12, r8)     // Catch:{ JSONException -> 0x08e3 }
        L_0x028d:
            java.util.List<java.lang.Integer> r8 = r3.zzauu     // Catch:{ JSONException -> 0x08e3 }
            if (r8 == 0) goto L_0x02cb
            java.util.List<java.lang.Integer> r8 = r3.zzauu     // Catch:{ JSONException -> 0x08e3 }
            int r8 = r8.size()     // Catch:{ JSONException -> 0x08e3 }
            if (r8 <= 0) goto L_0x02cb
            java.util.List<java.lang.Integer> r8 = r3.zzauu     // Catch:{ JSONException -> 0x08e3 }
            java.util.Iterator r8 = r8.iterator()     // Catch:{ JSONException -> 0x08e3 }
        L_0x029f:
            boolean r12 = r8.hasNext()     // Catch:{ JSONException -> 0x08e3 }
            if (r12 == 0) goto L_0x02cb
            java.lang.Object r12 = r8.next()     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Integer r12 = (java.lang.Integer) r12     // Catch:{ JSONException -> 0x08e3 }
            int r13 = r12.intValue()     // Catch:{ JSONException -> 0x08e3 }
            r14 = 2
            if (r13 != r14) goto L_0x02bd
            java.lang.String r12 = "iba"
            r13 = 1
            java.lang.Boolean r14 = java.lang.Boolean.valueOf(r13)     // Catch:{ JSONException -> 0x08e3 }
        L_0x02b9:
            r9.put(r12, r14)     // Catch:{ JSONException -> 0x08e3 }
            goto L_0x029f
        L_0x02bd:
            int r12 = r12.intValue()     // Catch:{ JSONException -> 0x08e3 }
            r13 = 1
            if (r12 != r13) goto L_0x029f
            java.lang.String r12 = "ina"
            java.lang.Boolean r14 = java.lang.Boolean.valueOf(r13)     // Catch:{ JSONException -> 0x08e3 }
            goto L_0x02b9
        L_0x02cb:
            com.google.android.gms.internal.zzko r8 = r3.zzaud     // Catch:{ JSONException -> 0x08e3 }
            boolean r8 = r8.zzbif     // Catch:{ JSONException -> 0x08e3 }
            if (r8 == 0) goto L_0x02db
            java.lang.String r8 = "ene"
            r12 = 1
            java.lang.Boolean r13 = java.lang.Boolean.valueOf(r12)     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r8, r13)     // Catch:{ JSONException -> 0x08e3 }
        L_0x02db:
            com.google.android.gms.internal.zzny<java.lang.Boolean> r8 = com.google.android.gms.internal.zzoi.zzbos     // Catch:{ JSONException -> 0x08e3 }
            com.google.android.gms.internal.zzog r12 = com.google.android.gms.internal.zzlc.zzio()     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Object r8 = r12.zzd(r8)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ JSONException -> 0x08e3 }
            boolean r8 = r8.booleanValue()     // Catch:{ JSONException -> 0x08e3 }
            if (r8 == 0) goto L_0x02f7
            java.lang.String r8 = "xsrve"
            r12 = 1
            java.lang.Boolean r13 = java.lang.Boolean.valueOf(r12)     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r8, r13)     // Catch:{ JSONException -> 0x08e3 }
        L_0x02f7:
            com.google.android.gms.internal.zzms r8 = r3.zzaus     // Catch:{ JSONException -> 0x08e3 }
            if (r8 == 0) goto L_0x0312
            java.lang.String r8 = "is_icon_ad"
            r12 = 1
            java.lang.Boolean r13 = java.lang.Boolean.valueOf(r12)     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r8, r13)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r8 = "icon_ad_expansion_behavior"
            com.google.android.gms.internal.zzms r12 = r3.zzaus     // Catch:{ JSONException -> 0x08e3 }
            int r12 = r12.zzbjh     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r8, r12)     // Catch:{ JSONException -> 0x08e3 }
        L_0x0312:
            java.lang.String r8 = "slotname"
            java.lang.String r12 = r3.zzatx     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r8, r12)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r8 = "pn"
            android.content.pm.ApplicationInfo r12 = r3.applicationInfo     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r12 = r12.packageName     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r8, r12)     // Catch:{ JSONException -> 0x08e3 }
            android.content.pm.PackageInfo r8 = r3.zzcrw     // Catch:{ JSONException -> 0x08e3 }
            if (r8 == 0) goto L_0x0333
            java.lang.String r8 = "vc"
            android.content.pm.PackageInfo r12 = r3.zzcrw     // Catch:{ JSONException -> 0x08e3 }
            int r12 = r12.versionCode     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r8, r12)     // Catch:{ JSONException -> 0x08e3 }
        L_0x0333:
            java.lang.String r8 = "ms"
            java.lang.String r12 = r1.zzcrx     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r8, r12)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r8 = "seq_num"
            java.lang.String r12 = r3.zzcry     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r8, r12)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r8 = "session_id"
            java.lang.String r12 = r3.zzcrz     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r8, r12)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r8 = "js"
            com.google.android.gms.internal.zzala r12 = r3.zzatz     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r12 = r12.zzcu     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r8, r12)     // Catch:{ JSONException -> 0x08e3 }
            com.google.android.gms.internal.zzaek r8 = r1.zzcvj     // Catch:{ JSONException -> 0x08e3 }
            android.os.Bundle r12 = r3.zzcsw     // Catch:{ JSONException -> 0x08e3 }
            android.os.Bundle r13 = r1.zzcvi     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r14 = "am"
            int r15 = r5.zzcyb     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r14, r15)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r14 = "cog"
            boolean r15 = r5.zzcyc     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Integer r15 = zzu(r15)     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r14, r15)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r14 = "coh"
            boolean r15 = r5.zzcyd     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Integer r15 = zzu(r15)     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r14, r15)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r14 = r5.zzcye     // Catch:{ JSONException -> 0x08e3 }
            boolean r14 = android.text.TextUtils.isEmpty(r14)     // Catch:{ JSONException -> 0x08e3 }
            if (r14 != 0) goto L_0x0387
            java.lang.String r14 = "carrier"
            java.lang.String r15 = r5.zzcye     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r14, r15)     // Catch:{ JSONException -> 0x08e3 }
        L_0x0387:
            java.lang.String r14 = "gl"
            java.lang.String r15 = r5.zzcyf     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r14, r15)     // Catch:{ JSONException -> 0x08e3 }
            boolean r14 = r5.zzcyg     // Catch:{ JSONException -> 0x08e3 }
            if (r14 == 0) goto L_0x039c
            java.lang.String r14 = "simulator"
            r15 = 1
            java.lang.Integer r10 = java.lang.Integer.valueOf(r15)     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r14, r10)     // Catch:{ JSONException -> 0x08e3 }
        L_0x039c:
            boolean r10 = r5.zzcyh     // Catch:{ JSONException -> 0x08e3 }
            if (r10 == 0) goto L_0x03ab
            java.lang.String r10 = "is_sidewinder"
            r14 = 1
            java.lang.Integer r15 = java.lang.Integer.valueOf(r14)     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r10, r15)     // Catch:{ JSONException -> 0x08e3 }
            goto L_0x03ac
        L_0x03ab:
            r14 = 1
        L_0x03ac:
            java.lang.String r10 = "ma"
            boolean r15 = r5.zzcyi     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Integer r15 = zzu(r15)     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r10, r15)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r10 = "sp"
            boolean r15 = r5.zzcyj     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Integer r15 = zzu(r15)     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r10, r15)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r10 = "hl"
            java.lang.String r15 = r5.zzcyk     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r10, r15)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r10 = r5.zzcyl     // Catch:{ JSONException -> 0x08e3 }
            boolean r10 = android.text.TextUtils.isEmpty(r10)     // Catch:{ JSONException -> 0x08e3 }
            if (r10 != 0) goto L_0x03d8
            java.lang.String r10 = "mv"
            java.lang.String r15 = r5.zzcyl     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r10, r15)     // Catch:{ JSONException -> 0x08e3 }
        L_0x03d8:
            java.lang.String r10 = "muv"
            int r15 = r5.zzcyn     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r10, r15)     // Catch:{ JSONException -> 0x08e3 }
            int r10 = r5.zzcyo     // Catch:{ JSONException -> 0x08e3 }
            r15 = -2
            if (r10 == r15) goto L_0x03f3
            java.lang.String r10 = "cnt"
            int r15 = r5.zzcyo     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r10, r15)     // Catch:{ JSONException -> 0x08e3 }
        L_0x03f3:
            java.lang.String r10 = "gnt"
            int r15 = r5.zzcyp     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r10, r15)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r10 = "pt"
            int r15 = r5.zzcyq     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r10, r15)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r10 = "rm"
            int r15 = r5.zzcyr     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r10, r15)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r10 = "riv"
            int r15 = r5.zzcys     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r10, r15)     // Catch:{ JSONException -> 0x08e3 }
            android.os.Bundle r10 = new android.os.Bundle     // Catch:{ JSONException -> 0x08e3 }
            r10.<init>()     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r15 = "build_build"
            java.lang.String r14 = r5.zzcyx     // Catch:{ JSONException -> 0x08e3 }
            r10.putString(r15, r14)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r14 = "build_device"
            java.lang.String r15 = r5.zzcyy     // Catch:{ JSONException -> 0x08e3 }
            r10.putString(r14, r15)     // Catch:{ JSONException -> 0x08e3 }
            android.os.Bundle r14 = new android.os.Bundle     // Catch:{ JSONException -> 0x08e3 }
            r14.<init>()     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r15 = "is_charging"
            r19 = r11
            boolean r11 = r5.zzcyu     // Catch:{ JSONException -> 0x08e3 }
            r14.putBoolean(r15, r11)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r11 = "battery_level"
            r15 = r6
            r21 = r7
            double r6 = r5.zzcyt     // Catch:{ JSONException -> 0x08e3 }
            r14.putDouble(r11, r6)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r6 = "battery"
            r10.putBundle(r6, r14)     // Catch:{ JSONException -> 0x08e3 }
            android.os.Bundle r6 = new android.os.Bundle     // Catch:{ JSONException -> 0x08e3 }
            r6.<init>()     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r7 = "active_network_state"
            int r11 = r5.zzcyw     // Catch:{ JSONException -> 0x08e3 }
            r6.putInt(r7, r11)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r7 = "active_network_metered"
            boolean r11 = r5.zzcyv     // Catch:{ JSONException -> 0x08e3 }
            r6.putBoolean(r7, r11)     // Catch:{ JSONException -> 0x08e3 }
            if (r8 == 0) goto L_0x0486
            android.os.Bundle r7 = new android.os.Bundle     // Catch:{ JSONException -> 0x08e3 }
            r7.<init>()     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r11 = "predicted_latency_micros"
            int r14 = r8.zzczh     // Catch:{ JSONException -> 0x08e3 }
            r7.putInt(r11, r14)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r11 = "predicted_down_throughput_bps"
            r22 = r15
            long r14 = r8.zzczi     // Catch:{ JSONException -> 0x08e3 }
            r7.putLong(r11, r14)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r11 = "predicted_up_throughput_bps"
            long r14 = r8.zzczj     // Catch:{ JSONException -> 0x08e3 }
            r7.putLong(r11, r14)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r8 = "predictions"
            r6.putBundle(r8, r7)     // Catch:{ JSONException -> 0x08e3 }
            goto L_0x0488
        L_0x0486:
            r22 = r15
        L_0x0488:
            java.lang.String r7 = "network"
            r10.putBundle(r7, r6)     // Catch:{ JSONException -> 0x08e3 }
            android.os.Bundle r6 = new android.os.Bundle     // Catch:{ JSONException -> 0x08e3 }
            r6.<init>()     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r7 = "is_browser_custom_tabs_capable"
            boolean r8 = r5.zzcyz     // Catch:{ JSONException -> 0x08e3 }
            r6.putBoolean(r7, r8)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r7 = "browser"
            r10.putBundle(r7, r6)     // Catch:{ JSONException -> 0x08e3 }
            if (r12 == 0) goto L_0x0553
            java.lang.String r6 = "android_mem_info"
            android.os.Bundle r7 = new android.os.Bundle     // Catch:{ JSONException -> 0x08e3 }
            r7.<init>()     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r8 = "runtime_free"
            java.lang.String r11 = "runtime_free_memory"
            r14 = -1
            long r17 = r12.getLong(r11, r14)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r11 = java.lang.Long.toString(r17)     // Catch:{ JSONException -> 0x08e3 }
            r7.putString(r8, r11)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r8 = "runtime_max"
            java.lang.String r11 = "runtime_max_memory"
            long r17 = r12.getLong(r11, r14)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r11 = java.lang.Long.toString(r17)     // Catch:{ JSONException -> 0x08e3 }
            r7.putString(r8, r11)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r8 = "runtime_total"
            java.lang.String r11 = "runtime_total_memory"
            long r14 = r12.getLong(r11, r14)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r11 = java.lang.Long.toString(r14)     // Catch:{ JSONException -> 0x08e3 }
            r7.putString(r8, r11)     // Catch:{ JSONException -> 0x08e3 }
            r8 = 0
            int r11 = r12.getInt(r2, r8)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r11 = java.lang.Integer.toString(r11)     // Catch:{ JSONException -> 0x08e3 }
            r7.putString(r2, r11)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r2 = "debug_memory_info"
            android.os.Parcelable r2 = r12.getParcelable(r2)     // Catch:{ JSONException -> 0x08e3 }
            android.os.Debug$MemoryInfo r2 = (android.os.Debug.MemoryInfo) r2     // Catch:{ JSONException -> 0x08e3 }
            if (r2 == 0) goto L_0x054f
            java.lang.String r11 = "debug_info_dalvik_private_dirty"
            int r12 = r2.dalvikPrivateDirty     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r12 = java.lang.Integer.toString(r12)     // Catch:{ JSONException -> 0x08e3 }
            r7.putString(r11, r12)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r11 = "debug_info_dalvik_pss"
            int r12 = r2.dalvikPss     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r12 = java.lang.Integer.toString(r12)     // Catch:{ JSONException -> 0x08e3 }
            r7.putString(r11, r12)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r11 = "debug_info_dalvik_shared_dirty"
            int r12 = r2.dalvikSharedDirty     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r12 = java.lang.Integer.toString(r12)     // Catch:{ JSONException -> 0x08e3 }
            r7.putString(r11, r12)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r11 = "debug_info_native_private_dirty"
            int r12 = r2.nativePrivateDirty     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r12 = java.lang.Integer.toString(r12)     // Catch:{ JSONException -> 0x08e3 }
            r7.putString(r11, r12)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r11 = "debug_info_native_pss"
            int r12 = r2.nativePss     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r12 = java.lang.Integer.toString(r12)     // Catch:{ JSONException -> 0x08e3 }
            r7.putString(r11, r12)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r11 = "debug_info_native_shared_dirty"
            int r12 = r2.nativeSharedDirty     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r12 = java.lang.Integer.toString(r12)     // Catch:{ JSONException -> 0x08e3 }
            r7.putString(r11, r12)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r11 = "debug_info_other_private_dirty"
            int r12 = r2.otherPrivateDirty     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r12 = java.lang.Integer.toString(r12)     // Catch:{ JSONException -> 0x08e3 }
            r7.putString(r11, r12)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r11 = "debug_info_other_pss"
            int r12 = r2.otherPss     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r12 = java.lang.Integer.toString(r12)     // Catch:{ JSONException -> 0x08e3 }
            r7.putString(r11, r12)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r11 = "debug_info_other_shared_dirty"
            int r2 = r2.otherSharedDirty     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r2 = java.lang.Integer.toString(r2)     // Catch:{ JSONException -> 0x08e3 }
            r7.putString(r11, r2)     // Catch:{ JSONException -> 0x08e3 }
        L_0x054f:
            r10.putBundle(r6, r7)     // Catch:{ JSONException -> 0x08e3 }
            goto L_0x0554
        L_0x0553:
            r8 = 0
        L_0x0554:
            android.os.Bundle r2 = new android.os.Bundle     // Catch:{ JSONException -> 0x08e3 }
            r2.<init>()     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r6 = "parental_controls"
            r2.putBundle(r6, r13)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r6 = r5.zzcym     // Catch:{ JSONException -> 0x08e3 }
            boolean r6 = android.text.TextUtils.isEmpty(r6)     // Catch:{ JSONException -> 0x08e3 }
            if (r6 != 0) goto L_0x056d
            java.lang.String r6 = "package_version"
            java.lang.String r7 = r5.zzcym     // Catch:{ JSONException -> 0x08e3 }
            r2.putString(r6, r7)     // Catch:{ JSONException -> 0x08e3 }
        L_0x056d:
            java.lang.String r6 = "play_store"
            r10.putBundle(r6, r2)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r2 = "device"
            r9.put(r2, r10)     // Catch:{ JSONException -> 0x08e3 }
            android.os.Bundle r2 = new android.os.Bundle     // Catch:{ JSONException -> 0x08e3 }
            r2.<init>()     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r6 = "doritos"
            java.lang.String r7 = r1.zzcvk     // Catch:{ JSONException -> 0x08e3 }
            r2.putString(r6, r7)     // Catch:{ JSONException -> 0x08e3 }
            com.google.android.gms.internal.zzny<java.lang.Boolean> r6 = com.google.android.gms.internal.zzoi.zzbpd     // Catch:{ JSONException -> 0x08e3 }
            com.google.android.gms.internal.zzog r7 = com.google.android.gms.internal.zzlc.zzio()     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Object r6 = r7.zzd(r6)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch:{ JSONException -> 0x08e3 }
            boolean r6 = r6.booleanValue()     // Catch:{ JSONException -> 0x08e3 }
            if (r6 == 0) goto L_0x05d1
            com.google.android.gms.ads.identifier.AdvertisingIdClient$Info r6 = r1.zzcvl     // Catch:{ JSONException -> 0x08e3 }
            if (r6 == 0) goto L_0x05a6
            com.google.android.gms.ads.identifier.AdvertisingIdClient$Info r6 = r1.zzcvl     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r6 = r6.getId()     // Catch:{ JSONException -> 0x08e3 }
            com.google.android.gms.ads.identifier.AdvertisingIdClient$Info r7 = r1.zzcvl     // Catch:{ JSONException -> 0x08e3 }
            boolean r12 = r7.isLimitAdTrackingEnabled()     // Catch:{ JSONException -> 0x08e3 }
            goto L_0x05a8
        L_0x05a6:
            r6 = 0
            r12 = 0
        L_0x05a8:
            boolean r7 = android.text.TextUtils.isEmpty(r6)     // Catch:{ JSONException -> 0x08e3 }
            if (r7 != 0) goto L_0x05c0
            java.lang.String r7 = "rdid"
            r2.putString(r7, r6)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r6 = "is_lat"
            r2.putBoolean(r6, r12)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r6 = "idtype"
            java.lang.String r7 = "adid"
        L_0x05bc:
            r2.putString(r6, r7)     // Catch:{ JSONException -> 0x08e3 }
            goto L_0x05d1
        L_0x05c0:
            com.google.android.gms.internal.zzlc.zzij()     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r6 = com.google.android.gms.internal.zzako.zzba(r23)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r7 = "pdid"
            r2.putString(r7, r6)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r6 = "pdidtype"
            java.lang.String r7 = "ssaid"
            goto L_0x05bc
        L_0x05d1:
            java.lang.String r6 = "pii"
            r9.put(r6, r2)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r2 = "platform"
            java.lang.String r6 = android.os.Build.MANUFACTURER     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r2, r6)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r2 = "submodel"
            java.lang.String r6 = android.os.Build.MODEL     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r2, r6)     // Catch:{ JSONException -> 0x08e3 }
            if (r4 == 0) goto L_0x05ea
            zza((java.util.HashMap<java.lang.String, java.lang.Object>) r9, (android.location.Location) r4)     // Catch:{ JSONException -> 0x08e3 }
            goto L_0x05fe
        L_0x05ea:
            com.google.android.gms.internal.zzkk r2 = r3.zzcrv     // Catch:{ JSONException -> 0x08e3 }
            int r2 = r2.versionCode     // Catch:{ JSONException -> 0x08e3 }
            r4 = 2
            if (r2 < r4) goto L_0x05fe
            com.google.android.gms.internal.zzkk r2 = r3.zzcrv     // Catch:{ JSONException -> 0x08e3 }
            android.location.Location r2 = r2.zzbhd     // Catch:{ JSONException -> 0x08e3 }
            if (r2 == 0) goto L_0x05fe
            com.google.android.gms.internal.zzkk r2 = r3.zzcrv     // Catch:{ JSONException -> 0x08e3 }
            android.location.Location r2 = r2.zzbhd     // Catch:{ JSONException -> 0x08e3 }
            zza((java.util.HashMap<java.lang.String, java.lang.Object>) r9, (android.location.Location) r2)     // Catch:{ JSONException -> 0x08e3 }
        L_0x05fe:
            int r2 = r3.versionCode     // Catch:{ JSONException -> 0x08e3 }
            r4 = 2
            if (r2 < r4) goto L_0x060a
            java.lang.String r2 = "quality_signals"
            android.os.Bundle r4 = r3.zzcsa     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r2, r4)     // Catch:{ JSONException -> 0x08e3 }
        L_0x060a:
            int r2 = r3.versionCode     // Catch:{ JSONException -> 0x08e3 }
            r4 = 4
            if (r2 < r4) goto L_0x061e
            boolean r2 = r3.zzcsd     // Catch:{ JSONException -> 0x08e3 }
            if (r2 == 0) goto L_0x061e
            java.lang.String r2 = "forceHttps"
            boolean r4 = r3.zzcsd     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r2, r4)     // Catch:{ JSONException -> 0x08e3 }
        L_0x061e:
            if (r22 == 0) goto L_0x0627
            java.lang.String r2 = "content_info"
            r4 = r22
            r9.put(r2, r4)     // Catch:{ JSONException -> 0x08e3 }
        L_0x0627:
            int r2 = r3.versionCode     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r4 = "sw"
            java.lang.String r6 = "sh"
            java.lang.String r7 = "u_sd"
            r10 = 5
            if (r2 < r10) goto L_0x064e
            float r2 = r3.zzaxz     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Float r2 = java.lang.Float.valueOf(r2)     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r7, r2)     // Catch:{ JSONException -> 0x08e3 }
            int r2 = r3.zzcsf     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r6, r2)     // Catch:{ JSONException -> 0x08e3 }
            int r2 = r3.zzcse     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ JSONException -> 0x08e3 }
        L_0x064a:
            r9.put(r4, r2)     // Catch:{ JSONException -> 0x08e3 }
            goto L_0x0667
        L_0x064e:
            float r2 = r5.zzaxz     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Float r2 = java.lang.Float.valueOf(r2)     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r7, r2)     // Catch:{ JSONException -> 0x08e3 }
            int r2 = r5.zzcsf     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r6, r2)     // Catch:{ JSONException -> 0x08e3 }
            int r2 = r5.zzcse     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ JSONException -> 0x08e3 }
            goto L_0x064a
        L_0x0667:
            int r2 = r3.versionCode     // Catch:{ JSONException -> 0x08e3 }
            r4 = 6
            if (r2 < r4) goto L_0x0693
            java.lang.String r2 = r3.zzcsg     // Catch:{ JSONException -> 0x08e3 }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ JSONException -> 0x08e3 }
            if (r2 != 0) goto L_0x0688
            java.lang.String r2 = "view_hierarchy"
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0681 }
            java.lang.String r5 = r3.zzcsg     // Catch:{ JSONException -> 0x0681 }
            r4.<init>(r5)     // Catch:{ JSONException -> 0x0681 }
            r9.put(r2, r4)     // Catch:{ JSONException -> 0x0681 }
            goto L_0x0688
        L_0x0681:
            r0 = move-exception
            r2 = r0
            java.lang.String r4 = "Problem serializing view hierarchy to JSON"
            com.google.android.gms.internal.zzahw.zzc(r4, r2)     // Catch:{ JSONException -> 0x08e3 }
        L_0x0688:
            java.lang.String r2 = "correlation_id"
            long r4 = r3.zzcsh     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r2, r4)     // Catch:{ JSONException -> 0x08e3 }
        L_0x0693:
            int r2 = r3.versionCode     // Catch:{ JSONException -> 0x08e3 }
            r4 = 7
            if (r2 < r4) goto L_0x069f
            java.lang.String r2 = "request_id"
            java.lang.String r4 = r3.zzcsi     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r2, r4)     // Catch:{ JSONException -> 0x08e3 }
        L_0x069f:
            int r2 = r3.versionCode     // Catch:{ JSONException -> 0x08e3 }
            r4 = 12
            if (r2 < r4) goto L_0x06b4
            java.lang.String r2 = r3.zzcsm     // Catch:{ JSONException -> 0x08e3 }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ JSONException -> 0x08e3 }
            if (r2 != 0) goto L_0x06b4
            java.lang.String r2 = "anchor"
            java.lang.String r4 = r3.zzcsm     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r2, r4)     // Catch:{ JSONException -> 0x08e3 }
        L_0x06b4:
            int r2 = r3.versionCode     // Catch:{ JSONException -> 0x08e3 }
            r4 = 13
            if (r2 < r4) goto L_0x06c5
            java.lang.String r2 = "android_app_volume"
            float r4 = r3.zzcsn     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Float r4 = java.lang.Float.valueOf(r4)     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r2, r4)     // Catch:{ JSONException -> 0x08e3 }
        L_0x06c5:
            int r2 = r3.versionCode     // Catch:{ JSONException -> 0x08e3 }
            r4 = 18
            if (r2 < r4) goto L_0x06d6
            java.lang.String r2 = "android_app_muted"
            boolean r5 = r3.zzcst     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r2, r5)     // Catch:{ JSONException -> 0x08e3 }
        L_0x06d6:
            int r2 = r3.versionCode     // Catch:{ JSONException -> 0x08e3 }
            r5 = 14
            if (r2 < r5) goto L_0x06eb
            int r2 = r3.zzcso     // Catch:{ JSONException -> 0x08e3 }
            if (r2 <= 0) goto L_0x06eb
            java.lang.String r2 = "target_api"
            int r5 = r3.zzcso     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r2, r5)     // Catch:{ JSONException -> 0x08e3 }
        L_0x06eb:
            int r2 = r3.versionCode     // Catch:{ JSONException -> 0x08e3 }
            r5 = 15
            if (r2 < r5) goto L_0x0703
            java.lang.String r2 = "scroll_index"
            int r5 = r3.zzcsp     // Catch:{ JSONException -> 0x08e3 }
            r6 = -1
            if (r5 != r6) goto L_0x06fa
            r12 = -1
            goto L_0x06fc
        L_0x06fa:
            int r12 = r3.zzcsp     // Catch:{ JSONException -> 0x08e3 }
        L_0x06fc:
            java.lang.Integer r5 = java.lang.Integer.valueOf(r12)     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r2, r5)     // Catch:{ JSONException -> 0x08e3 }
        L_0x0703:
            int r2 = r3.versionCode     // Catch:{ JSONException -> 0x08e3 }
            r5 = 16
            if (r2 < r5) goto L_0x0714
            java.lang.String r2 = "_activity_context"
            boolean r5 = r3.zzcsq     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r2, r5)     // Catch:{ JSONException -> 0x08e3 }
        L_0x0714:
            int r2 = r3.versionCode     // Catch:{ JSONException -> 0x08e3 }
            if (r2 < r4) goto L_0x073f
            java.lang.String r2 = r3.zzcsu     // Catch:{ JSONException -> 0x08e3 }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ JSONException -> 0x08e3 }
            if (r2 != 0) goto L_0x0734
            java.lang.String r2 = "app_settings"
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ JSONException -> 0x072d }
            java.lang.String r6 = r3.zzcsu     // Catch:{ JSONException -> 0x072d }
            r5.<init>(r6)     // Catch:{ JSONException -> 0x072d }
            r9.put(r2, r5)     // Catch:{ JSONException -> 0x072d }
            goto L_0x0734
        L_0x072d:
            r0 = move-exception
            r2 = r0
            java.lang.String r5 = "Problem creating json from app settings"
            com.google.android.gms.internal.zzahw.zzc(r5, r2)     // Catch:{ JSONException -> 0x08e3 }
        L_0x0734:
            java.lang.String r2 = "render_in_browser"
            boolean r5 = r3.zzcia     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r2, r5)     // Catch:{ JSONException -> 0x08e3 }
        L_0x073f:
            int r2 = r3.versionCode     // Catch:{ JSONException -> 0x08e3 }
            if (r2 < r4) goto L_0x074e
            java.lang.String r2 = "android_num_video_cache_tasks"
            int r4 = r3.zzcsv     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r2, r4)     // Catch:{ JSONException -> 0x08e3 }
        L_0x074e:
            com.google.android.gms.internal.zzala r2 = r3.zzatz     // Catch:{ JSONException -> 0x08e3 }
            boolean r4 = r3.zzcth     // Catch:{ JSONException -> 0x08e3 }
            boolean r1 = r1.zzcvp     // Catch:{ JSONException -> 0x08e3 }
            boolean r5 = r3.zzctj     // Catch:{ JSONException -> 0x08e3 }
            android.os.Bundle r6 = new android.os.Bundle     // Catch:{ JSONException -> 0x08e3 }
            r6.<init>()     // Catch:{ JSONException -> 0x08e3 }
            android.os.Bundle r7 = new android.os.Bundle     // Catch:{ JSONException -> 0x08e3 }
            r7.<init>()     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r10 = "cl"
            java.lang.String r11 = "190237664"
            r7.putString(r10, r11)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r10 = "rapid_rc"
            java.lang.String r11 = "dev"
            r7.putString(r10, r11)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r10 = "rapid_rollup"
            java.lang.String r11 = "HEAD"
            r7.putString(r10, r11)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r10 = "build_meta"
            r6.putBundle(r10, r7)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r7 = "mf"
            com.google.android.gms.internal.zzny<java.lang.Boolean> r10 = com.google.android.gms.internal.zzoi.zzbsf     // Catch:{ JSONException -> 0x08e3 }
            com.google.android.gms.internal.zzog r11 = com.google.android.gms.internal.zzlc.zzio()     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Object r10 = r11.zzd(r10)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch:{ JSONException -> 0x08e3 }
            boolean r10 = r10.booleanValue()     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r10 = java.lang.Boolean.toString(r10)     // Catch:{ JSONException -> 0x08e3 }
            r6.putString(r7, r10)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r7 = "instant_app"
            r6.putBoolean(r7, r4)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r4 = "lite"
            boolean r2 = r2.zzdjc     // Catch:{ JSONException -> 0x08e3 }
            r6.putBoolean(r4, r2)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r2 = "local_service"
            r6.putBoolean(r2, r1)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r1 = "is_privileged_process"
            r6.putBoolean(r1, r5)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r1 = "sdk_env"
            r9.put(r1, r6)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r1 = "cache_state"
            r2 = r21
            r9.put(r1, r2)     // Catch:{ JSONException -> 0x08e3 }
            int r1 = r3.versionCode     // Catch:{ JSONException -> 0x08e3 }
            r2 = 19
            if (r1 < r2) goto L_0x07c2
            java.lang.String r1 = "gct"
            java.lang.String r2 = r3.zzcsx     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r1, r2)     // Catch:{ JSONException -> 0x08e3 }
        L_0x07c2:
            int r1 = r3.versionCode     // Catch:{ JSONException -> 0x08e3 }
            r2 = 21
            if (r1 < r2) goto L_0x07d3
            boolean r1 = r3.zzcsy     // Catch:{ JSONException -> 0x08e3 }
            if (r1 == 0) goto L_0x07d3
            java.lang.String r1 = "de"
            java.lang.String r2 = "1"
            r9.put(r1, r2)     // Catch:{ JSONException -> 0x08e3 }
        L_0x07d3:
            com.google.android.gms.internal.zzny<java.lang.Boolean> r1 = com.google.android.gms.internal.zzoi.zzbpr     // Catch:{ JSONException -> 0x08e3 }
            com.google.android.gms.internal.zzog r2 = com.google.android.gms.internal.zzlc.zzio()     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Object r1 = r2.zzd(r1)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch:{ JSONException -> 0x08e3 }
            boolean r1 = r1.booleanValue()     // Catch:{ JSONException -> 0x08e3 }
            if (r1 == 0) goto L_0x0817
            com.google.android.gms.internal.zzko r1 = r3.zzaud     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r1 = r1.zzbia     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r2 = "interstitial_mb"
            boolean r2 = r1.equals(r2)     // Catch:{ JSONException -> 0x08e3 }
            if (r2 != 0) goto L_0x07fc
            java.lang.String r2 = "reward_mb"
            boolean r1 = r1.equals(r2)     // Catch:{ JSONException -> 0x08e3 }
            if (r1 == 0) goto L_0x07fa
            goto L_0x07fc
        L_0x07fa:
            r12 = 0
            goto L_0x07fd
        L_0x07fc:
            r12 = 1
        L_0x07fd:
            android.os.Bundle r1 = r3.zzcsz     // Catch:{ JSONException -> 0x08e3 }
            if (r1 == 0) goto L_0x0803
            r13 = 1
            goto L_0x0804
        L_0x0803:
            r13 = 0
        L_0x0804:
            if (r12 == 0) goto L_0x0817
            if (r13 == 0) goto L_0x0817
            android.os.Bundle r2 = new android.os.Bundle     // Catch:{ JSONException -> 0x08e3 }
            r2.<init>()     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r4 = "interstitial_pool"
            r2.putBundle(r4, r1)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r1 = "counters"
            r9.put(r1, r2)     // Catch:{ JSONException -> 0x08e3 }
        L_0x0817:
            java.lang.String r1 = r3.zzcta     // Catch:{ JSONException -> 0x08e3 }
            if (r1 == 0) goto L_0x0822
            java.lang.String r1 = "gmp_app_id"
            java.lang.String r2 = r3.zzcta     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r1, r2)     // Catch:{ JSONException -> 0x08e3 }
        L_0x0822:
            java.lang.String r1 = r3.zzctb     // Catch:{ JSONException -> 0x08e3 }
            if (r1 == 0) goto L_0x0845
            java.lang.String r1 = "TIME_OUT"
            java.lang.String r2 = r3.zzctb     // Catch:{ JSONException -> 0x08e3 }
            boolean r1 = r1.equals(r2)     // Catch:{ JSONException -> 0x08e3 }
            if (r1 == 0) goto L_0x0840
            java.lang.String r1 = "sai_timeout"
            com.google.android.gms.internal.zzny<java.lang.Long> r2 = com.google.android.gms.internal.zzoi.zzboq     // Catch:{ JSONException -> 0x08e3 }
            com.google.android.gms.internal.zzog r4 = com.google.android.gms.internal.zzlc.zzio()     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Object r2 = r4.zzd(r2)     // Catch:{ JSONException -> 0x08e3 }
        L_0x083c:
            r9.put(r1, r2)     // Catch:{ JSONException -> 0x08e3 }
            goto L_0x084a
        L_0x0840:
            java.lang.String r1 = "fbs_aiid"
            java.lang.String r2 = r3.zzctb     // Catch:{ JSONException -> 0x08e3 }
            goto L_0x083c
        L_0x0845:
            java.lang.String r1 = "fbs_aiid"
            java.lang.String r2 = ""
            goto L_0x083c
        L_0x084a:
            java.lang.String r1 = r3.zzctc     // Catch:{ JSONException -> 0x08e3 }
            if (r1 == 0) goto L_0x0855
            java.lang.String r1 = "fbs_aeid"
            java.lang.String r2 = r3.zzctc     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r1, r2)     // Catch:{ JSONException -> 0x08e3 }
        L_0x0855:
            int r1 = r3.versionCode     // Catch:{ JSONException -> 0x08e3 }
            r2 = 24
            if (r1 < r2) goto L_0x0866
            java.lang.String r1 = "disable_ml"
            boolean r2 = r3.zzcti     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ JSONException -> 0x08e3 }
            r9.put(r1, r2)     // Catch:{ JSONException -> 0x08e3 }
        L_0x0866:
            com.google.android.gms.internal.zzny<java.lang.String> r1 = com.google.android.gms.internal.zzoi.zzbml     // Catch:{ JSONException -> 0x08e3 }
            com.google.android.gms.internal.zzog r2 = com.google.android.gms.internal.zzlc.zzio()     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Object r1 = r2.zzd(r1)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ JSONException -> 0x08e3 }
            if (r1 == 0) goto L_0x08ae
            boolean r2 = r1.isEmpty()     // Catch:{ JSONException -> 0x08e3 }
            if (r2 != 0) goto L_0x08ae
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch:{ JSONException -> 0x08e3 }
            com.google.android.gms.internal.zzny<java.lang.Integer> r3 = com.google.android.gms.internal.zzoi.zzbmm     // Catch:{ JSONException -> 0x08e3 }
            com.google.android.gms.internal.zzog r4 = com.google.android.gms.internal.zzlc.zzio()     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Object r3 = r4.zzd(r3)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.Integer r3 = (java.lang.Integer) r3     // Catch:{ JSONException -> 0x08e3 }
            int r3 = r3.intValue()     // Catch:{ JSONException -> 0x08e3 }
            if (r2 < r3) goto L_0x08ae
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ JSONException -> 0x08e3 }
            r2.<init>()     // Catch:{ JSONException -> 0x08e3 }
            r3 = r19
            java.lang.String[] r1 = r1.split(r3)     // Catch:{ JSONException -> 0x08e3 }
            int r3 = r1.length     // Catch:{ JSONException -> 0x08e3 }
            r13 = 0
        L_0x089b:
            if (r13 >= r3) goto L_0x08a9
            r4 = r1[r13]     // Catch:{ JSONException -> 0x08e3 }
            java.util.List r5 = com.google.android.gms.internal.zzakm.zzct(r4)     // Catch:{ JSONException -> 0x08e3 }
            r2.put(r4, r5)     // Catch:{ JSONException -> 0x08e3 }
            int r13 = r13 + 1
            goto L_0x089b
        L_0x08a9:
            java.lang.String r1 = "video_decoders"
            r9.put(r1, r2)     // Catch:{ JSONException -> 0x08e3 }
        L_0x08ae:
            r1 = 2
            boolean r2 = com.google.android.gms.internal.zzahw.zzae(r1)     // Catch:{ JSONException -> 0x08e3 }
            if (r2 == 0) goto L_0x08da
            com.google.android.gms.internal.zzaij r2 = com.google.android.gms.ads.internal.zzbt.zzel()     // Catch:{ JSONException -> 0x08e3 }
            org.json.JSONObject r2 = r2.zzq(r9)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r1 = r2.toString(r1)     // Catch:{ JSONException -> 0x08e3 }
            java.lang.String r2 = "Ad Request JSON: "
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ JSONException -> 0x08e3 }
            int r3 = r1.length()     // Catch:{ JSONException -> 0x08e3 }
            if (r3 == 0) goto L_0x08d2
            java.lang.String r1 = r2.concat(r1)     // Catch:{ JSONException -> 0x08e3 }
            goto L_0x08d7
        L_0x08d2:
            java.lang.String r1 = new java.lang.String     // Catch:{ JSONException -> 0x08e3 }
            r1.<init>(r2)     // Catch:{ JSONException -> 0x08e3 }
        L_0x08d7:
            com.google.android.gms.internal.zzahw.v(r1)     // Catch:{ JSONException -> 0x08e3 }
        L_0x08da:
            com.google.android.gms.internal.zzaij r1 = com.google.android.gms.ads.internal.zzbt.zzel()     // Catch:{ JSONException -> 0x08e3 }
            org.json.JSONObject r1 = r1.zzq(r9)     // Catch:{ JSONException -> 0x08e3 }
            return r1
        L_0x08e3:
            r0 = move-exception
            r1 = r0
            java.lang.String r2 = "Problem serializing ad request to JSON: "
            java.lang.String r1 = r1.getMessage()
            java.lang.String r1 = java.lang.String.valueOf(r1)
            int r3 = r1.length()
            if (r3 == 0) goto L_0x08fa
            java.lang.String r1 = r2.concat(r1)
            goto L_0x08ff
        L_0x08fa:
            java.lang.String r1 = new java.lang.String
            r1.<init>(r2)
        L_0x08ff:
            com.google.android.gms.internal.zzahw.zzcz(r1)
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzads.zza(android.content.Context, com.google.android.gms.internal.zzadl):org.json.JSONObject");
    }

    private static void zza(HashMap<String, Object> hashMap, Location location) {
        HashMap hashMap2 = new HashMap();
        Float valueOf = Float.valueOf(location.getAccuracy() * 1000.0f);
        Long valueOf2 = Long.valueOf(location.getTime() * 1000);
        Long valueOf3 = Long.valueOf((long) (location.getLatitude() * 1.0E7d));
        Long valueOf4 = Long.valueOf((long) (location.getLongitude() * 1.0E7d));
        hashMap2.put("radius", valueOf);
        hashMap2.put("lat", valueOf3);
        hashMap2.put("long", valueOf4);
        hashMap2.put("time", valueOf2);
        hashMap.put("uule", hashMap2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a3  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00b2  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00c4  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00f4  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0101  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0110  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x011b  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x011e  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0130  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x013f  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x014e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.json.JSONObject zzb(com.google.android.gms.internal.zzacj r7) throws org.json.JSONException {
        /*
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.lang.String r1 = r7.zzcno
            if (r1 == 0) goto L_0x0010
            java.lang.String r1 = r7.zzcno
            java.lang.String r2 = "ad_base_url"
            r0.put(r2, r1)
        L_0x0010:
            java.lang.String r1 = r7.zzctr
            if (r1 == 0) goto L_0x001b
            java.lang.String r1 = r7.zzctr
            java.lang.String r2 = "ad_size"
            r0.put(r2, r1)
        L_0x001b:
            boolean r1 = r7.zzbid
            java.lang.String r2 = "native"
            r0.put(r2, r1)
            boolean r1 = r7.zzbid
            if (r1 == 0) goto L_0x002b
            java.lang.String r1 = r7.body
            java.lang.String r2 = "ad_json"
            goto L_0x002f
        L_0x002b:
            java.lang.String r1 = r7.body
            java.lang.String r2 = "ad_html"
        L_0x002f:
            r0.put(r2, r1)
            java.lang.String r1 = r7.zzctt
            if (r1 == 0) goto L_0x003d
            java.lang.String r1 = r7.zzctt
            java.lang.String r2 = "debug_dialog"
            r0.put(r2, r1)
        L_0x003d:
            java.lang.String r1 = r7.zzcuj
            if (r1 == 0) goto L_0x0048
            java.lang.String r1 = r7.zzcuj
            java.lang.String r2 = "debug_signals"
            r0.put(r2, r1)
        L_0x0048:
            long r1 = r7.zzctn
            r3 = -1
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x005e
            long r1 = r7.zzctn
            double r1 = (double) r1
            r5 = 4652007308841189376(0x408f400000000000, double:1000.0)
            double r1 = r1 / r5
            java.lang.String r5 = "interstitial_timeout"
            r0.put(r5, r1)
        L_0x005e:
            int r1 = r7.orientation
            com.google.android.gms.internal.zzaip r2 = com.google.android.gms.ads.internal.zzbt.zzen()
            int r2 = r2.zzrh()
            java.lang.String r5 = "orientation"
            if (r1 != r2) goto L_0x0072
            java.lang.String r1 = "portrait"
        L_0x006e:
            r0.put(r5, r1)
            goto L_0x0081
        L_0x0072:
            int r1 = r7.orientation
            com.google.android.gms.internal.zzaip r2 = com.google.android.gms.ads.internal.zzbt.zzen()
            int r2 = r2.zzrg()
            if (r1 != r2) goto L_0x0081
            java.lang.String r1 = "landscape"
            goto L_0x006e
        L_0x0081:
            java.util.List<java.lang.String> r1 = r7.zzchw
            if (r1 == 0) goto L_0x0090
            java.util.List<java.lang.String> r1 = r7.zzchw
            org.json.JSONArray r1 = zzp(r1)
            java.lang.String r2 = "click_urls"
            r0.put(r2, r1)
        L_0x0090:
            java.util.List<java.lang.String> r1 = r7.zzchx
            if (r1 == 0) goto L_0x009f
            java.util.List<java.lang.String> r1 = r7.zzchx
            org.json.JSONArray r1 = zzp(r1)
            java.lang.String r2 = "impression_urls"
            r0.put(r2, r1)
        L_0x009f:
            java.util.List<java.lang.String> r1 = r7.zzctq
            if (r1 == 0) goto L_0x00ae
            java.util.List<java.lang.String> r1 = r7.zzctq
            org.json.JSONArray r1 = zzp(r1)
            java.lang.String r2 = "manual_impression_urls"
            r0.put(r2, r1)
        L_0x00ae:
            java.lang.String r1 = r7.zzctw
            if (r1 == 0) goto L_0x00b9
            java.lang.String r1 = r7.zzctw
            java.lang.String r2 = "active_view"
            r0.put(r2, r1)
        L_0x00b9:
            boolean r1 = r7.zzctu
            java.lang.String r2 = "ad_is_javascript"
            r0.put(r2, r1)
            java.lang.String r1 = r7.zzctv
            if (r1 == 0) goto L_0x00cb
            java.lang.String r1 = r7.zzctv
            java.lang.String r2 = "ad_passback_url"
            r0.put(r2, r1)
        L_0x00cb:
            boolean r1 = r7.zzcto
            java.lang.String r2 = "mediation"
            r0.put(r2, r1)
            boolean r1 = r7.zzctx
            java.lang.String r2 = "custom_render_allowed"
            r0.put(r2, r1)
            boolean r1 = r7.zzcty
            java.lang.String r2 = "content_url_opted_out"
            r0.put(r2, r1)
            boolean r1 = r7.zzcuk
            java.lang.String r2 = "content_vertical_opted_out"
            r0.put(r2, r1)
            boolean r1 = r7.zzctz
            java.lang.String r2 = "prefetch"
            r0.put(r2, r1)
            long r1 = r7.zzcic
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x00fb
            long r1 = r7.zzcic
            java.lang.String r5 = "refresh_interval_milliseconds"
            r0.put(r5, r1)
        L_0x00fb:
            long r1 = r7.zzctp
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x0108
            long r1 = r7.zzctp
            java.lang.String r3 = "mediation_config_cache_time_milliseconds"
            r0.put(r3, r1)
        L_0x0108:
            java.lang.String r1 = r7.zzbdl
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x0117
            java.lang.String r1 = r7.zzbdl
            java.lang.String r2 = "gws_query_id"
            r0.put(r2, r1)
        L_0x0117:
            boolean r1 = r7.zzbie
            if (r1 == 0) goto L_0x011e
            java.lang.String r1 = "height"
            goto L_0x0120
        L_0x011e:
            java.lang.String r1 = ""
        L_0x0120:
            java.lang.String r2 = "fluid"
            r0.put(r2, r1)
            boolean r1 = r7.zzbif
            java.lang.String r2 = "native_express"
            r0.put(r2, r1)
            java.util.List<java.lang.String> r1 = r7.zzcud
            if (r1 == 0) goto L_0x013b
            java.util.List<java.lang.String> r1 = r7.zzcud
            org.json.JSONArray r1 = zzp(r1)
            java.lang.String r2 = "video_start_urls"
            r0.put(r2, r1)
        L_0x013b:
            java.util.List<java.lang.String> r1 = r7.zzcue
            if (r1 == 0) goto L_0x014a
            java.util.List<java.lang.String> r1 = r7.zzcue
            org.json.JSONArray r1 = zzp(r1)
            java.lang.String r2 = "video_complete_urls"
            r0.put(r2, r1)
        L_0x014a:
            com.google.android.gms.internal.zzagd r1 = r7.zzcuc
            if (r1 == 0) goto L_0x0170
            com.google.android.gms.internal.zzagd r1 = r7.zzcuc
            org.json.JSONObject r2 = new org.json.JSONObject
            r2.<init>()
            java.lang.String r3 = r1.type
            java.lang.String r4 = "rb_type"
            r2.put(r4, r3)
            int r1 = r1.zzdax
            java.lang.String r3 = "rb_amount"
            r2.put(r3, r1)
            org.json.JSONArray r1 = new org.json.JSONArray
            r1.<init>()
            r1.put(r2)
            java.lang.String r2 = "rewards"
            r0.put(r2, r1)
        L_0x0170:
            boolean r1 = r7.zzcuf
            java.lang.String r2 = "use_displayed_impression"
            r0.put(r2, r1)
            com.google.android.gms.internal.zzacl r1 = r7.zzcug
            java.lang.String r2 = "auto_protection_configuration"
            r0.put(r2, r1)
            boolean r7 = r7.zzcia
            java.lang.String r1 = "render_in_browser"
            r0.put(r1, r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzads.zzb(com.google.android.gms.internal.zzacj):org.json.JSONObject");
    }

    private static JSONArray zzp(List<String> list) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (String put : list) {
            jSONArray.put(put);
        }
        return jSONArray;
    }

    private static Integer zzu(boolean z) {
        return Integer.valueOf(z ? 1 : 0);
    }
}
