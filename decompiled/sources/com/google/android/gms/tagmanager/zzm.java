package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.Uri;
import com.google.android.gms.internal.zzbh;
import com.google.android.gms.internal.zzbi;
import com.google.android.gms.internal.zzbt;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class zzm extends zzgi {
    private static final String ID;
    private static final String URL = zzbi.URL.toString();
    private static final String zzkmw = zzbi.ADDITIONAL_PARAMS.toString();
    private static final String zzkmx = zzbi.UNREPEATABLE.toString();
    private static String zzkmy;
    private static final Set<String> zzkmz = new HashSet();
    private final Context mContext;
    private final zza zzkna;

    public interface zza {
        zzby zzbfi();
    }

    static {
        String id = com.google.android.gms.internal.zzbh.ARBITRARY_PIXEL.toString();
        ID = id;
        StringBuilder sb = new StringBuilder(String.valueOf(id).length() + 17);
        sb.append("gtm_");
        sb.append(id);
        sb.append("_unrepeatable");
        zzkmy = sb.toString();
    }

    public zzm(Context context) {
        this(context, new zzn(context));
    }

    private zzm(Context context, zza zza2) {
        super(ID, URL);
        this.zzkna = zza2;
        this.mContext = context;
    }

    private final synchronized boolean zzlg(String str) {
        Set<String> set = zzkmz;
        if (set.contains(str)) {
            return true;
        }
        if (!this.mContext.getSharedPreferences(zzkmy, 0).contains(str)) {
            return false;
        }
        set.add(str);
        return true;
    }

    public final void zzz(Map<String, zzbt> map) {
        String str = zzkmx;
        String zzd = map.get(str) != null ? zzgk.zzd(map.get(str)) : null;
        if (zzd == null || !zzlg(zzd)) {
            Uri.Builder buildUpon = Uri.parse(zzgk.zzd(map.get(URL))).buildUpon();
            zzbt zzbt = map.get(zzkmw);
            if (zzbt != null) {
                Object zzi = zzgk.zzi(zzbt);
                if (!(zzi instanceof List)) {
                    String valueOf = String.valueOf(buildUpon.build().toString());
                    zzdj.e(valueOf.length() != 0 ? "ArbitraryPixel: additional params not a list: not sending partial hit: ".concat(valueOf) : new String("ArbitraryPixel: additional params not a list: not sending partial hit: "));
                    return;
                }
                for (Object next : (List) zzi) {
                    if (!(next instanceof Map)) {
                        String valueOf2 = String.valueOf(buildUpon.build().toString());
                        zzdj.e(valueOf2.length() != 0 ? "ArbitraryPixel: additional params contains non-map: not sending partial hit: ".concat(valueOf2) : new String("ArbitraryPixel: additional params contains non-map: not sending partial hit: "));
                        return;
                    }
                    for (Object entryObj : ((Map) next).entrySet()) {
                        Map.Entry entry = (Map.Entry) entryObj;
                        buildUpon.appendQueryParameter(entry.getKey().toString(), entry.getValue().toString());
                    }
                }
            }
            String uri = buildUpon.build().toString();
            this.zzkna.zzbfi().zzlr(uri);
            String valueOf3 = String.valueOf(uri);
            zzdj.v(valueOf3.length() != 0 ? "ArbitraryPixel: url = ".concat(valueOf3) : new String("ArbitraryPixel: url = "));
            if (zzd != null) {
                synchronized (zzm.class) {
                    zzkmz.add(zzd);
                    zzfu.zze(this.mContext, zzkmy, zzd, "true");
                }
            }
        }
    }
}
