package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

public final class zzepf {
    private static final Pattern zznrl = Pattern.compile("[\\[\\]\\.#$]");
    private static final Pattern zznrm = Pattern.compile("[\\[\\]\\.#\\$\\/\\u0000-\\u001F\\u007F]");

    public static void zzao(zzegu zzegu) throws DatabaseException {
        zzemq zzbyq = zzegu.zzbyq();
        if (!(zzbyq == null || !zzbyq.asString().startsWith("."))) {
            String valueOf = String.valueOf(zzegu.toString());
            throw new DatabaseException(valueOf.length() != 0 ? "Invalid write location: ".concat(valueOf) : new String("Invalid write location: "));
        }
    }

    public static Map<zzegu, zzenn> zzb(zzegu zzegu, Map<String, Object> map) throws DatabaseException {
        TreeMap<zzegu, zzenn> treeMap = new TreeMap<>();
        for (Map.Entry<String, Object> next : map.entrySet()) {
            zzegu zzegu2 = new zzegu(next.getKey());
            Object value = next.getValue();
            zzejo.zza(zzegu.zzh(zzegu2), value);
            String asString = !zzegu2.isEmpty() ? zzegu2.zzbyt().asString() : "";
            if (asString.equals(".sv") || asString.equals(".value")) {
                String valueOf = String.valueOf(zzegu2);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 40 + String.valueOf(asString).length());
                sb.append("Path '");
                sb.append(valueOf);
                sb.append("' contains disallowed child name: ");
                sb.append(asString);
                throw new DatabaseException(sb.toString());
            }
            zzenn zzc = asString.equals(".priority") ? zzent.zzc(zzegu2, value) : zzenq.zza(value, zzene.zzcco());
            zzbz(value);
            treeMap.put(zzegu2, zzc);
        }
        zzegu zzegu3 = null;
        for (zzegu zzegu4 : treeMap.keySet()) {
            zzepd.zzcw(zzegu3 == null || zzegu3.compareTo(zzegu4) < 0);
            if (zzegu3 == null || !zzegu3.zzi(zzegu4)) {
                zzegu3 = zzegu4;
            } else {
                String valueOf2 = String.valueOf(zzegu3);
                String valueOf3 = String.valueOf(zzegu4);
                StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 42 + String.valueOf(valueOf3).length());
                sb2.append("Path '");
                sb2.append(valueOf2);
                sb2.append("' is an ancestor of '");
                sb2.append(valueOf3);
                sb2.append("' in an update.");
                throw new DatabaseException(sb2.toString());
            }
        }
        return treeMap;
    }

    public static void zzbz(Object obj) {
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (!map.containsKey(".sv")) {
                for (Map.Entry<?, ?> entry : ((Map<?, ?>) map).entrySet()) {
                    String str = (String) entry.getKey();
                    if (str != null && str.length() > 0 && (str.equals(".value") || str.equals(".priority") || (!str.startsWith(".") && !zznrm.matcher(str).find()))) {
                        zzbz(entry.getValue());
                    } else {
                        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 68);
                        sb.append("Invalid key: ");
                        sb.append(str);
                        sb.append(". Keys must not contain '/', '.', '#', '$', '[', or ']'");
                        throw new DatabaseException(sb.toString());
                    }
                }
            }
        } else if (obj instanceof List) {
            for (Object zzbz : (List) obj) {
                zzbz(zzbz);
            }
        } else if ((obj instanceof Double) || (obj instanceof Float)) {
            double doubleValue = ((Double) obj).doubleValue();
            if (Double.isInfinite(doubleValue) || Double.isNaN(doubleValue)) {
                throw new DatabaseException("Invalid value: Value cannot be NaN, Inf or -Inf.");
            }
        }
    }

    public static void zzqn(String str) throws DatabaseException {
        if (!(!zznrl.matcher(str).find())) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 101);
            sb.append("Invalid Firebase Database path: ");
            sb.append(str);
            sb.append(". Firebase Database paths must not contain '.', '#', '$', '[', or ']'");
            throw new DatabaseException(sb.toString());
        }
    }

    public static void zzqo(String str) throws DatabaseException {
        int i;
        if (str.startsWith(".info")) {
            i = 5;
        } else if (str.startsWith("/.info")) {
            i = 6;
        } else {
            zzqn(str);
            return;
        }
        zzqn(str.substring(i));
    }

    public static void zzqp(String str) throws DatabaseException {
        if (str != null) {
            if (!(str.equals(".info") || !zznrm.matcher(str).find())) {
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 68);
                sb.append("Invalid key: ");
                sb.append(str);
                sb.append(". Keys must not contain '/', '.', '#', '$', '[', or ']'");
                throw new DatabaseException(sb.toString());
            }
        }
    }
}
