package com.google.android.gms.internal;

import androidx.collection.SimpleArrayMap;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzabh
public final class zzaaq implements zzaae<zzpo> {
    private final boolean zzcqf;

    public zzaaq(boolean z) {
        this.zzcqf = z;
    }

    private static <K, V> SimpleArrayMap<K, V> zza(SimpleArrayMap<K, Future<V>> simpleArrayMap) throws InterruptedException, ExecutionException {
        SimpleArrayMap<K, V> simpleArrayMap2 = new SimpleArrayMap<>();
        for (int i = 0; i < simpleArrayMap.size(); i++) {
            simpleArrayMap2.put(simpleArrayMap.keyAt(i), simpleArrayMap.valueAt(i).get());
        }
        return simpleArrayMap2;
    }

    public final zzpo zza(zzzy zzzy, JSONObject jSONObject) throws JSONException, InterruptedException, ExecutionException {
        SimpleArrayMap simpleArrayMap = new SimpleArrayMap();
        SimpleArrayMap simpleArrayMap2 = new SimpleArrayMap();
        zzalt<zzph> zzg = zzzy.zzg(jSONObject);
        zzalt<zzaof> zzc = zzzy.zzc(jSONObject, "video");
        JSONArray jSONArray = jSONObject.getJSONArray("custom_assets");
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            String string = jSONObject2.getString(AppMeasurement.Param.TYPE);
            if ("string".equals(string)) {
                simpleArrayMap2.put(jSONObject2.getString("name"), jSONObject2.getString("string_value"));
            } else if ("image".equals(string)) {
                simpleArrayMap.put(jSONObject2.getString("name"), zzzy.zza(jSONObject2, "image_value", this.zzcqf));
            } else {
                String valueOf = String.valueOf(string);
                zzahw.zzcz(valueOf.length() != 0 ? "Unknown custom asset type: ".concat(valueOf) : new String("Unknown custom asset type: "));
            }
        }
        zzaof zzb = zzzy.zzb(zzc);
        return new zzpo(jSONObject.getString("custom_template_id"), zza(simpleArrayMap), simpleArrayMap2, (zzph) zzg.get(), zzb != null ? zzb.zzth() : null, zzb != null ? zzb.getView() : null);
    }
}
