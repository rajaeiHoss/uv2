package com.google.android.gms.internal;

import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.common.internal.zzbq;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smackx.workgroup.ext.macros.Macros;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzdic {
    private static zzdjh zza(JSONArray jSONArray, List<zzdje> list, List<zzdje> list2) throws zzdib, JSONException {
        zzdjj zzdjj = new zzdjj();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONArray jSONArray2 = jSONArray.getJSONArray(i);
            int i2 = 1;
            if (jSONArray2.getString(0).equals("if")) {
                while (i2 < jSONArray2.length()) {
                    zzdjj.zzc(list2.get(jSONArray2.getInt(i2)));
                    i2++;
                }
            } else if (jSONArray2.getString(0).equals("unless")) {
                while (i2 < jSONArray2.length()) {
                    zzdjj.zzd(list2.get(jSONArray2.getInt(i2)));
                    i2++;
                }
            } else if (jSONArray2.getString(0).equals(ProductAction.ACTION_ADD)) {
                while (i2 < jSONArray2.length()) {
                    zzdjj.zze(list.get(jSONArray2.getInt(i2)));
                    i2++;
                }
            } else if (jSONArray2.getString(0).equals("block")) {
                while (i2 < jSONArray2.length()) {
                    zzdjj.zzf(list.get(jSONArray2.getInt(i2)));
                    i2++;
                }
            } else {
                String valueOf = String.valueOf(jSONArray2.getString(0));
                zzna(valueOf.length() != 0 ? "Unknown Rule property: ".concat(valueOf) : new String("Unknown Rule property: "));
            }
        }
        return zzdjj.zzbkj();
    }

    public static zzdco zzas(Object obj) throws JSONException {
        JSONArray jSONArray;
        JSONArray jSONArray2;
        String str;
        if (obj instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) obj;
            str = jSONObject.getString("name");
            jSONArray2 = jSONObject.getJSONArray("params");
            jSONArray = jSONObject.getJSONArray("instructions");
        } else if (obj instanceof JSONArray) {
            JSONArray jSONArray3 = (JSONArray) obj;
            zzbq.checkArgument(jSONArray3.length() >= 3);
            str = jSONArray3.getString(1);
            JSONArray jSONArray4 = jSONArray3.getJSONArray(2);
            JSONArray jSONArray5 = new JSONArray();
            for (int i = 1; i < jSONArray4.length(); i++) {
                zzbq.checkArgument(jSONArray4.get(i) instanceof String);
                jSONArray5.put(jSONArray4.get(i));
            }
            JSONArray jSONArray6 = new JSONArray();
            for (int i2 = 3; i2 < jSONArray3.length(); i2++) {
                jSONArray6.put(jSONArray3.get(i2));
            }
            jSONArray = jSONArray6;
            jSONArray2 = jSONArray5;
        } else {
            throw new IllegalArgumentException("invalid JSON in runtime section");
        }
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
            arrayList.add(jSONArray2.getString(i3));
        }
        ArrayList arrayList2 = new ArrayList();
        for (int i4 = 0; i4 < jSONArray.length(); i4++) {
            JSONArray jSONArray7 = jSONArray.getJSONArray(i4);
            if (jSONArray7.length() != 0) {
                arrayList2.add(zzd(jSONArray7));
            }
        }
        return new zzdco((zzdbb) null, str, arrayList, arrayList2);
    }

    private static zzdje zzb(JSONObject jSONObject, List<String> list) throws zzdib, JSONException {
        zzdjg zzdjg = new zzdjg();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            zzdjn zzbkn = zzb(jSONObject.get(next), list).zzbkn();
            if ("push_after_evaluate".equals(next)) {
                zzdjg.zzb(zzbkn);
            } else {
                zzdjg.zza(next, zzbkn);
            }
        }
        return zzdjg.zzbke();
    }

    private static zzdjp zzb(Object obj, List<String> list) throws zzdib, JSONException {
        zzdjp zzdjp = null;
        if (obj instanceof JSONArray) {
            JSONArray jSONArray = (JSONArray) obj;
            String string = jSONArray.getString(0);
            if (string.equals("escape")) {
                zzdjp zzb = zzb(jSONArray.get(1), list);
                for (int i = 2; i < jSONArray.length(); i++) {
                    zzb.zzfg(jSONArray.getInt(i));
                }
                return zzb;
            }
            if (string.equals("list")) {
                ArrayList arrayList = new ArrayList();
                for (int i2 = 1; i2 < jSONArray.length(); i2++) {
                    arrayList.add(zzb(jSONArray.get(i2), list).zzbkn());
                }
                zzdjp = new zzdjp(2, arrayList);
            } else if (string.equals("map")) {
                HashMap hashMap = new HashMap();
                for (int i3 = 1; i3 < jSONArray.length(); i3 += 2) {
                    hashMap.put(zzb(jSONArray.get(i3), list).zzbkn(), zzb(jSONArray.get(i3 + 1), list).zzbkn());
                }
                zzdjp = new zzdjp(3, hashMap);
            } else if (string.equals("macro")) {
                zzdjp zzdjp2 = new zzdjp(4, list.get(jSONArray.getInt(1)));
                zzdjp2.zzcc(true);
                return zzdjp2;
            } else if (string.equals("template")) {
                ArrayList arrayList2 = new ArrayList();
                for (int i4 = 1; i4 < jSONArray.length(); i4++) {
                    arrayList2.add(zzb(jSONArray.get(i4), list).zzbkn());
                }
                zzdjp = new zzdjp(7, arrayList2);
            } else {
                String valueOf = String.valueOf(obj);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 20);
                sb.append("Invalid value type: ");
                sb.append(valueOf);
                zzna(sb.toString());
                return null;
            }
            zzdjp.zzcc(true);
            return zzdjp;
        }
        if (obj instanceof Boolean) {
            zzdjp = new zzdjp(8, obj);
        } else if (obj instanceof Integer) {
            zzdjp = new zzdjp(6, obj);
        } else if (obj instanceof String) {
            zzdjp = new zzdjp(1, obj);
        } else {
            String valueOf2 = String.valueOf(obj);
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 20);
            sb2.append("Invalid value type: ");
            sb2.append(valueOf2);
            zzna(sb2.toString());
            return null;
        }
        return zzdjp;
    }

    private static List<zzdje> zzb(JSONArray jSONArray, List<String> list) throws JSONException, zzdib {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(zzb(jSONArray.getJSONObject(i), list));
        }
        return arrayList;
    }

    private static List<String> zzc(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.getJSONObject(i).getString("instance_name"));
        }
        return arrayList;
    }

    private static zzdkb zzd(JSONArray jSONArray) throws JSONException {
        Object obj;
        zzbq.checkArgument(jSONArray.length() > 0);
        String string = jSONArray.getString(0);
        ArrayList arrayList = new ArrayList();
        for (int i = 1; i < jSONArray.length(); i++) {
            Object obj2 = jSONArray.get(i);
            if (obj2 instanceof JSONArray) {
                JSONArray jSONArray2 = (JSONArray) obj2;
                obj = jSONArray2.length() != 0 ? zzd(jSONArray2) : zzdjw.zzlcy;
            } else {
                obj = obj2 == JSONObject.NULL ? zzdjw.zzlcy : zzdke.zzau(obj2);
            }
            arrayList.add(obj);
        }
        return new zzdkb(string, arrayList);
    }

    static zzdjc zzmy(String str) throws JSONException, zzdib {
        Object obj = new JSONObject(str).get("resource");
        if (obj instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) obj;
            zzdjd zzdjd = new zzdjd();
            zzdjd.zzng(jSONObject.optString("version"));
            List<String> zzc = zzc(jSONObject.getJSONArray(Macros.ELEMENT_NAME));
            List<zzdje> zzb = zzb(jSONObject.getJSONArray("tags"), zzc);
            List<zzdje> zzb2 = zzb(jSONObject.getJSONArray("predicates"), zzc);
            for (zzdje zzb3 : zzb(jSONObject.getJSONArray(Macros.ELEMENT_NAME), zzc)) {
                zzdjd.zzb(zzb3);
            }
            JSONArray jSONArray = jSONObject.getJSONArray("rules");
            for (int i = 0; i < jSONArray.length(); i++) {
                zzdjd.zza(zza(jSONArray.getJSONArray(i), zzb, zzb2));
            }
            return zzdjd.zzbkc();
        }
        throw new zzdib("Resource map not found");
    }

    static zzdjk zzmz(String str) throws JSONException, zzdib {
        JSONObject jSONObject = new JSONObject(str);
        JSONArray optJSONArray = jSONObject.optJSONArray("runtime");
        if (optJSONArray == null) {
            return null;
        }
        zzdjm zzdjm = new zzdjm();
        Object obj = jSONObject.get("resource");
        if (obj instanceof JSONObject) {
            zzdjm.zznh(((JSONObject) obj).optString("version"));
            for (int i = 0; i < optJSONArray.length(); i++) {
                Object obj2 = optJSONArray.get(i);
                if (!(obj2 instanceof JSONArray) || ((JSONArray) obj2).length() != 0) {
                    zzdjm.zza(zzas(obj2));
                }
            }
            return zzdjm.zzbkl();
        }
        throw new zzdib("Resource map not found");
    }

    private static void zzna(String str) throws zzdib {
        zzdal.e(str);
        throw new zzdib(str);
    }
}
