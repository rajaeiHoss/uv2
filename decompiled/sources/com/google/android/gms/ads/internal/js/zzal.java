package com.google.android.gms.ads.internal.js;

import com.google.android.gms.ads.internal.gmsg.zzt;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzahw;
import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

@zzabh
public final class zzal implements zzak {
    private final zzaj zzcgj;
    private final HashSet<AbstractMap.SimpleEntry<String, zzt<? super zzaj>>> zzcgk = new HashSet<>();

    public zzal(zzaj zzaj) {
        this.zzcgj = zzaj;
    }

    public final void zza(String str, zzt<? super zzaj> zzt) {
        this.zzcgj.zza(str, zzt);
        this.zzcgk.add(new AbstractMap.SimpleEntry(str, zzt));
    }

    public final void zza(String str, Map<String, ?> map) {
        this.zzcgj.zza(str, map);
    }

    public final void zza(String str, JSONObject jSONObject) {
        this.zzcgj.zza(str, jSONObject);
    }

    public final void zzb(String str, zzt<? super zzaj> zzt) {
        this.zzcgj.zzb(str, zzt);
        this.zzcgk.remove(new AbstractMap.SimpleEntry(str, zzt));
    }

    public final void zzb(String str, JSONObject jSONObject) {
        this.zzcgj.zzb(str, jSONObject);
    }

    public final void zzme() {
        Iterator<AbstractMap.SimpleEntry<String, zzt<? super zzaj>>> it = this.zzcgk.iterator();
        while (it.hasNext()) {
            AbstractMap.SimpleEntry next = it.next();
            String valueOf = String.valueOf(((zzt) next.getValue()).toString());
            zzahw.v(valueOf.length() != 0 ? "Unregistering eventhandler: ".concat(valueOf) : new String("Unregistering eventhandler: "));
            this.zzcgj.zzb((String) next.getKey(), (zzt) next.getValue());
        }
        this.zzcgk.clear();
    }
}
