package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbi;
import com.google.android.gms.internal.zzbt;
import com.google.android.gms.internal.zzdkj;
import com.google.android.gms.internal.zzdkl;
import com.google.android.gms.internal.zzdkm;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class zzdb {
    private static Object zzak(Object obj) throws JSONException {
        if (obj instanceof JSONArray) {
            throw new RuntimeException("JSONArrays are not supported");
        } else if (JSONObject.NULL.equals(obj)) {
            throw new RuntimeException("JSON nulls are not supported");
        } else if (!(obj instanceof JSONObject)) {
            return obj;
        } else {
            JSONObject jSONObject = (JSONObject) obj;
            HashMap hashMap = new HashMap();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, zzak(jSONObject.get(next)));
            }
            return hashMap;
        }
    }

    public static zzdkl zzly(String str) throws JSONException {
        zzbt zzam = zzgk.zzam(zzak(new JSONObject(str)));
        zzdkm zzbkw = zzdkl.zzbkw();
        for (int i = 0; i < zzam.zzym.length; i++) {
            zzbkw.zzc(zzdkj.zzbku().zzb(zzbi.INSTANCE_NAME.toString(), zzam.zzym[i]).zzb(zzbi.FUNCTION.toString(), zzgk.zzmi(zzt.zzbfj())).zzb(zzt.zzbfk(), zzam.zzyn[i]).zzbkv());
        }
        return zzbkw.zzbky();
    }
}
