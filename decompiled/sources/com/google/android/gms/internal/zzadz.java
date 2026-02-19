package com.google.android.gms.internal;

import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.plus.PlusShare;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

@zzabh
public final class zzadz {
    private final int errorCode;
    private final String type;
    private String url;
    private final String zzcsi;
    private final List<String> zzcxu;
    private final String zzcxv;
    private final String zzcxw;
    private final boolean zzcxx;
    private final String zzcxy;
    private final boolean zzcxz;
    private final JSONObject zzcya;

    public zzadz(int i, Map<String, String> map) {
        this.url = map.get(PlusShare.KEY_CALL_TO_ACTION_URL);
        this.zzcxv = map.get("base_uri");
        this.zzcxw = map.get("post_parameters");
        this.zzcxx = parseBoolean(map.get("drt_include"));
        this.zzcsi = map.get("request_id");
        this.type = map.get(AppMeasurement.Param.TYPE);
        this.zzcxu = zzbp(map.get("errors"));
        this.errorCode = i;
        this.zzcxy = map.get("fetched_ad");
        this.zzcxz = parseBoolean(map.get("render_test_ad_label"));
        this.zzcya = new JSONObject();
    }

    public zzadz(JSONObject jSONObject) {
        this.url = jSONObject.optString(PlusShare.KEY_CALL_TO_ACTION_URL);
        this.zzcxv = jSONObject.optString("base_uri");
        this.zzcxw = jSONObject.optString("post_parameters");
        this.zzcxx = parseBoolean(jSONObject.optString("drt_include"));
        this.zzcsi = jSONObject.optString("request_id");
        this.type = jSONObject.optString(AppMeasurement.Param.TYPE);
        this.zzcxu = zzbp(jSONObject.optString("errors"));
        this.errorCode = jSONObject.optInt("valid", 0) == 1 ? -2 : 1;
        this.zzcxy = jSONObject.optString("fetched_ad");
        this.zzcxz = jSONObject.optBoolean("render_test_ad_label");
        JSONObject optJSONObject = jSONObject.optJSONObject("preprocessor_flags");
        this.zzcya = optJSONObject == null ? new JSONObject() : optJSONObject;
    }

    private static boolean parseBoolean(String str) {
        if (str != null) {
            return str.equals("1") || str.equals("true");
        }
        return false;
    }

    private static List<String> zzbp(String str) {
        if (str == null) {
            return null;
        }
        return Arrays.asList(str.split(","));
    }

    public final int getErrorCode() {
        return this.errorCode;
    }

    public final String getRequestId() {
        return this.zzcsi;
    }

    public final String getType() {
        return this.type;
    }

    public final String getUrl() {
        return this.url;
    }

    public final void setUrl(String str) {
        this.url = str;
    }

    public final List<String> zzog() {
        return this.zzcxu;
    }

    public final String zzoh() {
        return this.zzcxv;
    }

    public final String zzoi() {
        return this.zzcxw;
    }

    public final boolean zzoj() {
        return this.zzcxx;
    }

    public final String zzok() {
        return this.zzcxy;
    }

    public final boolean zzol() {
        return this.zzcxz;
    }
}
