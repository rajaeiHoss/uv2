package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbh;
import com.google.android.gms.internal.zzbi;
import com.google.android.gms.internal.zzbt;
import java.util.HashMap;
import java.util.Map;

final class zzam extends zzbr {
    private static final String ID = zzbh.FUNCTION_CALL.toString();
    private static final String zzkmw = zzbi.ADDITIONAL_PARAMS.toString();
    private static final String zzkoh = zzbi.FUNCTION_CALL_NAME.toString();
    private final zzan zzkoi;

    public zzam(zzan zzan) {
        super(ID, zzkoh);
        this.zzkoi = zzan;
    }

    public final boolean zzbfh() {
        return false;
    }

    public final zzbt zzx(Map<String, zzbt> map) {
        String sb;
        String zzd = zzgk.zzd(map.get(zzkoh));
        HashMap hashMap = new HashMap();
        zzbt zzbt = map.get(zzkmw);
        if (zzbt != null) {
            Object zzi = zzgk.zzi(zzbt);
            if (!(zzi instanceof Map)) {
                sb = "FunctionCallMacro: expected ADDITIONAL_PARAMS to be a map.";
                zzdj.zzcz(sb);
                return zzgk.zzbil();
            }
            for (Object entryObj : ((Map) zzi).entrySet()) {
                Map.Entry entry = (Map.Entry) entryObj;
                hashMap.put(entry.getKey().toString(), entry.getValue());
            }
        }
        try {
            return zzgk.zzam(this.zzkoi.zze(zzd, hashMap));
        } catch (Exception e) {
            String message = e.getMessage();
            StringBuilder sb2 = new StringBuilder(String.valueOf(zzd).length() + 34 + String.valueOf(message).length());
            sb2.append("Custom macro/tag ");
            sb2.append(zzd);
            sb2.append(" threw exception ");
            sb2.append(message);
            sb = sb2.toString();
            zzdj.zzcz(sb);
            return zzgk.zzbil();
        }
    }
}
