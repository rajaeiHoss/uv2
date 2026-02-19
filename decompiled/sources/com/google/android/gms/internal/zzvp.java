package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzbt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzabh
public final class zzvp {
    public final String zzchb;
    public final String zzchc;
    public final List<String> zzchd;
    public final String zzche;
    public final String zzchf;
    public final List<String> zzchg;
    public final List<String> zzchh;
    public final List<String> zzchi;
    public final List<String> zzchj;
    public final String zzchk;
    public final List<String> zzchl;
    public final List<String> zzchm;
    public final List<String> zzchn;
    public final String zzcho;
    public final String zzchp;
    public final String zzchq;
    public final List<String> zzchr;
    public final String zzchs;
    private String zzcht;
    public final long zzchu;

    public zzvp(String str, String str2, List<String> list, String str3, String str4, List<String> list2, List<String> list3, List<String> list4, String str5, String str6, List<String> list5, List<String> list6, List<String> list7, String str7, String str8, String str9, List<String> list8, String str10, List<String> list9, String str11, long j) {
        this.zzchb = str;
        this.zzchc = null;
        this.zzchd = list;
        this.zzche = null;
        this.zzchf = null;
        this.zzchg = list2;
        this.zzchh = list3;
        this.zzchi = list4;
        this.zzchk = str5;
        this.zzchl = list5;
        this.zzchm = list6;
        this.zzchn = list7;
        this.zzcho = null;
        this.zzchp = null;
        this.zzchq = null;
        this.zzchr = null;
        this.zzchs = null;
        this.zzchj = list9;
        this.zzcht = null;
        this.zzchu = -1;
    }

    public zzvp(JSONObject jSONObject) throws JSONException {
        List<String> list;
        this.zzchc = jSONObject.optString("id");
        JSONArray jSONArray = jSONObject.getJSONArray("adapters");
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.getString(i));
        }
        this.zzchd = Collections.unmodifiableList(arrayList);
        this.zzche = jSONObject.optString("allocation_id", (String) null);
        zzbt.zzfd();
        this.zzchg = zzvy.zza(jSONObject, "clickurl");
        zzbt.zzfd();
        this.zzchh = zzvy.zza(jSONObject, "imp_urls");
        zzbt.zzfd();
        this.zzchj = zzvy.zza(jSONObject, "fill_urls");
        zzbt.zzfd();
        this.zzchl = zzvy.zza(jSONObject, "video_start_urls");
        zzbt.zzfd();
        List<String> zza = zzvy.zza(jSONObject, "video_complete_urls");
        this.zzchn = zza;
        zzbt.zzfd();
        this.zzchm = ((Boolean) zzlc.zzio().zzd(zzoi.zzbos)).booleanValue() ? zzvy.zza(jSONObject, "video_reward_urls") : zza;
        JSONObject optJSONObject = jSONObject.optJSONObject("ad");
        if (optJSONObject != null) {
            zzbt.zzfd();
            list = zzvy.zza(optJSONObject, "manual_impression_urls");
        } else {
            list = null;
        }
        this.zzchi = list;
        this.zzchb = optJSONObject != null ? optJSONObject.toString() : null;
        JSONObject optJSONObject2 = jSONObject.optJSONObject(DataPacketExtension.ELEMENT_NAME);
        this.zzchk = optJSONObject2 != null ? optJSONObject2.toString() : null;
        this.zzchf = optJSONObject2 != null ? optJSONObject2.optString("class_name") : null;
        this.zzcho = jSONObject.optString("html_template", (String) null);
        this.zzchp = jSONObject.optString("ad_base_url", (String) null);
        JSONObject optJSONObject3 = jSONObject.optJSONObject("assets");
        this.zzchq = optJSONObject3 != null ? optJSONObject3.toString() : null;
        zzbt.zzfd();
        this.zzchr = zzvy.zza(jSONObject, "template_ids");
        JSONObject optJSONObject4 = jSONObject.optJSONObject("ad_loader_options");
        this.zzchs = optJSONObject4 != null ? optJSONObject4.toString() : null;
        this.zzcht = jSONObject.optString("response_type", (String) null);
        this.zzchu = jSONObject.optLong("ad_network_timeout_millis", -1);
    }

    public final boolean zzmg() {
        return "banner".equalsIgnoreCase(this.zzcht);
    }

    public final boolean zzmh() {
        return "native".equalsIgnoreCase(this.zzcht);
    }
}
