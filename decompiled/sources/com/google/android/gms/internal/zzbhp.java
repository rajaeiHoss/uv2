package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzc;
import com.google.android.gms.common.util.zzq;
import com.google.android.gms.common.util.zzr;
import com.streamax.config.constant.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class zzbhp {
    protected static <O, I> I zza(zzbhq<I, O> zzbhq, Object obj) {
        return zzbhq.zzgix != null ? zzbhq.convertBack((O) obj) : (I) obj;
    }

    private static void zza(StringBuilder sb, zzbhq zzbhq, Object obj) {
        String str;
        if (zzbhq.zzgio == 11) {
            str = ((zzbhp) zzbhq.zzgiu.cast(obj)).toString();
        } else if (zzbhq.zzgio == 7) {
            str = "\"";
            sb.append(str);
            sb.append(zzq.zzha((String) obj));
        } else {
            sb.append(obj);
            return;
        }
        sb.append(str);
    }

    private static void zza(StringBuilder sb, zzbhq zzbhq, ArrayList<Object> arrayList) {
        sb.append("[");
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(",");
            }
            Object obj = arrayList.get(i);
            if (obj != null) {
                zza(sb, zzbhq, obj);
            }
        }
        sb.append("]");
    }

    public String toString() {
        Map<String, zzbhq<?, ?>> zzabz = zzabz();
        StringBuilder sb = new StringBuilder(100);
        for (String next : zzabz.keySet()) {
            zzbhq zzbhq = zzabz.get(next);
            if (zza(zzbhq)) {
                Object zza = zza(zzbhq, zzb(zzbhq));
                sb.append(sb.length() == 0 ? "{" : ",");
                sb.append("\"");
                sb.append(next);
                sb.append("\":");
                if (zza == null) {
                    sb.append("null");
                } else {
                    switch (zzbhq.zzgiq) {
                        case 8:
                            sb.append("\"");
                            sb.append(zzc.zzj((byte[]) zza));
                            sb.append("\"");
                            continue;
                        case 9:
                            sb.append("\"");
                            sb.append(zzc.zzk((byte[]) zza));
                            sb.append("\"");
                            continue;
                        case 10:
                            zzr.zza(sb, (HashMap) zza);
                            continue;
                        default:
                            if (!zzbhq.zzgip) {
                                zza(sb, zzbhq, zza);
                            } else {
                                zza(sb, zzbhq, (ArrayList<Object>) (ArrayList) zza);
                            }
                            continue;
                    }
                }
            }
        }
        sb.append(sb.length() > 0 ? Constants.JsonSstringSuffix : "{}");
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public boolean zza(zzbhq zzbhq) {
        if (zzbhq.zzgiq != 11) {
            return zzgy(zzbhq.zzgis);
        }
        if (zzbhq.zzgir) {
            throw new UnsupportedOperationException("Concrete type arrays not supported");
        }
        throw new UnsupportedOperationException("Concrete types not supported");
    }

    public abstract Map<String, zzbhq<?, ?>> zzabz();

    /* access modifiers changed from: protected */
    public Object zzb(zzbhq zzbhq) {
        String str = zzbhq.zzgis;
        if (zzbhq.zzgiu == null) {
            return zzgx(zzbhq.zzgis);
        }
        zzgx(zzbhq.zzgis);
        zzbq.zza(true, "Concrete field shouldn't be value object: %s", zzbhq.zzgis);
        boolean z = zzbhq.zzgir;
        try {
            char upperCase = Character.toUpperCase(str.charAt(0));
            String substring = str.substring(1);
            StringBuilder sb = new StringBuilder(String.valueOf(substring).length() + 4);
            sb.append("get");
            sb.append(upperCase);
            sb.append(substring);
            return getClass().getMethod(sb.toString(), new Class[0]).invoke(this, new Object[0]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: protected */
    public abstract Object zzgx(String str);

    /* access modifiers changed from: protected */
    public abstract boolean zzgy(String str);
}
