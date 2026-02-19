package com.google.android.gms.internal;

import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.json.JSONObject;

@zzabh
public final class zzoe {
    private final Collection<zzny<?>> zzbkt = new ArrayList();
    private final Collection<zzny<String>> zzbku = new ArrayList();
    private final Collection<zzny<String>> zzbkv = new ArrayList();

    public final void zza(SharedPreferences.Editor editor, int i, JSONObject jSONObject) {
        for (zzny next : this.zzbkt) {
            if (next.getSource() == 1) {
                next.zza(editor, next.zzb(jSONObject));
            }
        }
    }

    public final void zza(zzny zzny) {
        this.zzbkt.add(zzny);
    }

    public final void zzb(zzny<String> zzny) {
        this.zzbku.add(zzny);
    }

    public final void zzc(zzny<String> zzny) {
        this.zzbkv.add(zzny);
    }

    public final List<String> zzjf() {
        ArrayList arrayList = new ArrayList();
        for (zzny<String> zzd : this.zzbku) {
            String str = (String) zzlc.zzio().zzd(zzd);
            if (str != null) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public final List<String> zzjg() {
        List<String> zzjf = zzjf();
        for (zzny<String> zzd : this.zzbkv) {
            String str = (String) zzlc.zzio().zzd(zzd);
            if (str != null) {
                zzjf.add(str);
            }
        }
        return zzjf;
    }
}
