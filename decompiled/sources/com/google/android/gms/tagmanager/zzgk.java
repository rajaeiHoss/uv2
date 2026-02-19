package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbt;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class zzgk {
    private static final Object zzkuk = null;
    private static Long zzkul = new Long(0);
    private static Double zzkum = new Double(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
    private static zzgj zzkun = zzgj.zzbi(0);
    private static String zzkuo = new String("");
    private static Boolean zzkup = new Boolean(false);
    private static List<Object> zzkuq = new ArrayList(0);
    private static Map<Object, Object> zzkur = new HashMap();
    private static zzbt zzkus = zzam(zzkuo);

    private static double getDouble(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).doubleValue();
        }
        zzdj.e("getDouble received non-Number");
        return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    private static String zzal(Object obj) {
        return obj == null ? zzkuo : obj.toString();
    }

    public static zzbt zzam(Object obj) {
        String obj2;
        zzbt zzbt = new zzbt();
        if (obj instanceof zzbt) {
            return (zzbt) obj;
        }
        boolean z = false;
        if (obj instanceof String) {
            zzbt.type = 1;
            obj2 = (String) obj;
        } else {
            if (obj instanceof List) {
                zzbt.type = 2;
                List<Object> list = (List) obj;
                ArrayList arrayList = new ArrayList(list.size());
                boolean z2 = false;
                for (Object zzam : list) {
                    zzbt zzam2 = zzam(zzam);
                    zzbt zzbt2 = zzkus;
                    if (zzam2 == zzbt2) {
                        return zzbt2;
                    }
                    z2 = z2 || zzam2.zzyu;
                    arrayList.add(zzam2);
                }
                zzbt.zzyl = (zzbt[]) arrayList.toArray(new zzbt[0]);
                z = z2;
            } else if (obj instanceof Map) {
                zzbt.type = 3;
                Set<Map.Entry> entrySet = ((Map) obj).entrySet();
                ArrayList arrayList2 = new ArrayList(entrySet.size());
                ArrayList arrayList3 = new ArrayList(entrySet.size());
                boolean z3 = false;
                for (Map.Entry entry : entrySet) {
                    zzbt zzam3 = zzam(entry.getKey());
                    zzbt zzam4 = zzam(entry.getValue());
                    zzbt zzbt3 = zzkus;
                    if (zzam3 == zzbt3 || zzam4 == zzbt3) {
                        return zzbt3;
                    }
                    z3 = z3 || zzam3.zzyu || zzam4.zzyu;
                    arrayList2.add(zzam3);
                    arrayList3.add(zzam4);
                }
                zzbt.zzym = (zzbt[]) arrayList2.toArray(new zzbt[0]);
                zzbt.zzyn = (zzbt[]) arrayList3.toArray(new zzbt[0]);
                z = z3;
            } else if (zzan(obj)) {
                zzbt.type = 1;
                obj2 = obj.toString();
            } else if (zzao(obj)) {
                zzbt.type = 6;
                zzbt.zzyq = zzap(obj);
            } else if (obj instanceof Boolean) {
                zzbt.type = 8;
                zzbt.zzyr = ((Boolean) obj).booleanValue();
            } else {
                String valueOf = String.valueOf(obj == null ? "null" : obj.getClass().toString());
                zzdj.e(valueOf.length() != 0 ? "Converting to Value from unknown object type: ".concat(valueOf) : new String("Converting to Value from unknown object type: "));
                return zzkus;
            }
            zzbt.zzyu = z;
            return zzbt;
        }
        zzbt.string = obj2;
        zzbt.zzyu = z;
        return zzbt;
    }

    private static boolean zzan(Object obj) {
        if ((obj instanceof Double) || (obj instanceof Float)) {
            return true;
        }
        return (obj instanceof zzgj) && ((zzgj) obj).zzbid();
    }

    private static boolean zzao(Object obj) {
        if ((obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Integer) || (obj instanceof Long)) {
            return true;
        }
        return (obj instanceof zzgj) && ((zzgj) obj).zzbie();
    }

    private static long zzap(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).longValue();
        }
        zzdj.e("getInt64 received non-Number");
        return 0;
    }

    public static Object zzbif() {
        return null;
    }

    public static Long zzbig() {
        return zzkul;
    }

    public static Double zzbih() {
        return zzkum;
    }

    public static Boolean zzbii() {
        return zzkup;
    }

    public static zzgj zzbij() {
        return zzkun;
    }

    public static String zzbik() {
        return zzkuo;
    }

    public static zzbt zzbil() {
        return zzkus;
    }

    public static String zzd(zzbt zzbt) {
        return zzal(zzi(zzbt));
    }

    public static zzgj zze(zzbt zzbt) {
        Object zzi = zzi(zzbt);
        return zzi instanceof zzgj ? (zzgj) zzi : zzao(zzi) ? zzgj.zzbi(zzap(zzi)) : zzan(zzi) ? zzgj.zzb(Double.valueOf(getDouble(zzi))) : zzmj(zzal(zzi));
    }

    public static Long zzf(zzbt zzbt) {
        long longValue;
        Object zzi = zzi(zzbt);
        if (zzao(zzi)) {
            longValue = zzap(zzi);
        } else {
            zzgj zzmj = zzmj(zzal(zzi));
            if (zzmj == zzkun) {
                return zzkul;
            }
            longValue = zzmj.longValue();
        }
        return Long.valueOf(longValue);
    }

    public static Double zzg(zzbt zzbt) {
        double doubleValue;
        Object zzi = zzi(zzbt);
        if (zzan(zzi)) {
            doubleValue = getDouble(zzi);
        } else {
            zzgj zzmj = zzmj(zzal(zzi));
            if (zzmj == zzkun) {
                return zzkum;
            }
            doubleValue = zzmj.doubleValue();
        }
        return Double.valueOf(doubleValue);
    }

    public static Boolean zzh(zzbt zzbt) {
        Object zzi = zzi(zzbt);
        if (zzi instanceof Boolean) {
            return (Boolean) zzi;
        }
        String zzal = zzal(zzi);
        return "true".equalsIgnoreCase(zzal) ? Boolean.TRUE : "false".equalsIgnoreCase(zzal) ? Boolean.FALSE : zzkup;
    }

    public static Object zzi(zzbt zzbt) {
        String str;
        if (zzbt == null) {
            return null;
        }
        int i = 0;
        switch (zzbt.type) {
            case 1:
                return zzbt.string;
            case 2:
                ArrayList arrayList = new ArrayList(zzbt.zzyl.length);
                zzbt[] zzbtArr = zzbt.zzyl;
                int length = zzbtArr.length;
                while (i < length) {
                    Object zzi = zzi(zzbtArr[i]);
                    if (zzi == null) {
                        return null;
                    }
                    arrayList.add(zzi);
                    i++;
                }
                return arrayList;
            case 3:
                if (zzbt.zzym.length != zzbt.zzyn.length) {
                    String valueOf = String.valueOf(zzbt.toString());
                    zzdj.e(valueOf.length() != 0 ? "Converting an invalid value to object: ".concat(valueOf) : new String("Converting an invalid value to object: "));
                    return null;
                }
                HashMap hashMap = new HashMap(zzbt.zzyn.length);
                while (i < zzbt.zzym.length) {
                    Object zzi2 = zzi(zzbt.zzym[i]);
                    Object zzi3 = zzi(zzbt.zzyn[i]);
                    if (zzi2 == null || zzi3 == null) {
                        return null;
                    }
                    hashMap.put(zzi2, zzi3);
                    i++;
                }
                return hashMap;
            case 4:
                str = "Trying to convert a macro reference to object";
                break;
            case 5:
                str = "Trying to convert a function id to object";
                break;
            case 6:
                return Long.valueOf(zzbt.zzyq);
            case 7:
                StringBuilder sb = new StringBuilder();
                zzbt[] zzbtArr2 = zzbt.zzys;
                int length2 = zzbtArr2.length;
                while (i < length2) {
                    String zzd = zzd(zzbtArr2[i]);
                    if (zzd == zzkuo) {
                        return null;
                    }
                    sb.append(zzd);
                    i++;
                }
                return sb.toString();
            case 8:
                return Boolean.valueOf(zzbt.zzyr);
            default:
                int i2 = zzbt.type;
                StringBuilder sb2 = new StringBuilder(46);
                sb2.append("Failed to convert a value of type: ");
                sb2.append(i2);
                str = sb2.toString();
                break;
        }
        zzdj.e(str);
        return null;
    }

    public static zzbt zzmi(String str) {
        zzbt zzbt = new zzbt();
        zzbt.type = 5;
        zzbt.zzyp = str;
        return zzbt;
    }

    private static zzgj zzmj(String str) {
        try {
            return zzgj.zzmh(str);
        } catch (NumberFormatException unused) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 33);
            sb.append("Failed to convert '");
            sb.append(str);
            sb.append("' to a number.");
            zzdj.e(sb.toString());
            return zzkun;
        }
    }
}
