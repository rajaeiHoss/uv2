package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.zzbq;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public final class zzdke {
    public static zzdjq zza(zzdbb zzdbb, zzdjq zzdjq) {
        zzbq.checkNotNull(zzdjq);
        if (!zzl(zzdjq) && !(zzdjq instanceof zzdjv) && !(zzdjq instanceof zzdjx) && !(zzdjq instanceof zzdka)) {
            if (zzdjq instanceof zzdkb) {
                zzdjq = zza(zzdbb, (zzdkb) zzdjq);
            } else {
                throw new UnsupportedOperationException("Attempting to evaluate unknown type");
            }
        }
        if (zzdjq == null) {
            throw new IllegalArgumentException("AbstractType evaluated to Java null");
        } else if (!(zzdjq instanceof zzdkb)) {
            return zzdjq;
        } else {
            throw new IllegalArgumentException("AbstractType evaluated to illegal type Statement.");
        }
    }

    public static zzdjq zza(zzdbb zzdbb, zzdkb zzdkb) {
        String zzbks = zzdkb.zzbks();
        List<zzdjq<?>> zzbkt = zzdkb.zzbkt();
        zzdjq<?> zzmu = zzdbb.zzmu(zzbks);
        if (zzmu == null) {
            StringBuilder sb = new StringBuilder(String.valueOf(zzbks).length() + 28);
            sb.append("Function '");
            sb.append(zzbks);
            sb.append("' is not supported");
            throw new UnsupportedOperationException(sb.toString());
        } else if (zzmu instanceof zzdjv) {
            return ((zzdcp) ((zzdjv) zzmu).value()).zzb(zzdbb, (zzdjq[]) zzbkt.toArray(new zzdjq[zzbkt.size()]));
        } else {
            StringBuilder sb2 = new StringBuilder(String.valueOf(zzbks).length() + 29);
            sb2.append("Function '");
            sb2.append(zzbks);
            sb2.append("' is not a function");
            throw new UnsupportedOperationException(sb2.toString());
        }
    }

    public static zzdjw zza(zzdbb zzdbb, List<zzdjq<?>> list) {
        for (zzdjq next : list) {
            zzbq.checkArgument(next instanceof zzdkb);
            zzdjq zza = zza(zzdbb, next);
            if (zzm(zza)) {
                return (zzdjw) zza;
            }
        }
        return zzdjw.zzlcz;
    }

    public static Map<String, Object> zzae(Bundle bundle) {
        HashMap hashMap = new HashMap();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj instanceof Bundle) {
                obj = zzae((Bundle) obj);
            } else if (obj instanceof List) {
                obj = zzao((List) obj);
            }
            hashMap.put(str, obj);
        }
        return hashMap;
    }

    public static Bundle zzah(Map<String, zzdjq<?>> map) {
        if (map == null) {
            return null;
        }
        Bundle bundle = new Bundle(map.size());
        for (Map.Entry next : map.entrySet()) {
            if (next.getValue() instanceof zzdkc) {
                bundle.putString((String) next.getKey(), (String) ((zzdkc) next.getValue()).value());
            } else if (next.getValue() instanceof zzdjt) {
                bundle.putBoolean((String) next.getKey(), ((Boolean) ((zzdjt) next.getValue()).value()).booleanValue());
            } else if (next.getValue() instanceof zzdju) {
                bundle.putDouble((String) next.getKey(), ((Double) ((zzdju) next.getValue()).value()).doubleValue());
            } else if (next.getValue() instanceof zzdka) {
                bundle.putBundle((String) next.getKey(), zzah((Map) ((zzdka) next.getValue()).value()));
            } else {
                throw new IllegalArgumentException(String.format("Invalid param type for key '%s'. Only boolean, double and string types and maps of thereof are supported.", new Object[]{next.getKey()}));
            }
        }
        return bundle;
    }

    private static List<Object> zzao(List<Object> list) {
        ArrayList arrayList = new ArrayList();
        for (Object next : list) {
            if (next instanceof Bundle) {
                next = zzae((Bundle) next);
            } else if (next instanceof List) {
                next = zzao((List) next);
            }
            arrayList.add(next);
        }
        return arrayList;
    }

    public static zzdjq<?> zzau(Object obj) {
        if (obj == null) {
            return zzdjw.zzlcy;
        }
        if (obj instanceof zzdjq) {
            return (zzdjq) obj;
        }
        if (obj instanceof Boolean) {
            return new zzdjt((Boolean) obj);
        }
        if (obj instanceof Short) {
            return new zzdju(Double.valueOf(((Short) obj).doubleValue()));
        }
        if (obj instanceof Integer) {
            return new zzdju(Double.valueOf(((Integer) obj).doubleValue()));
        }
        if (obj instanceof Long) {
            return new zzdju(Double.valueOf(((Long) obj).doubleValue()));
        }
        if (obj instanceof Float) {
            return new zzdju(Double.valueOf(((Float) obj).doubleValue()));
        }
        if (obj instanceof Double) {
            return new zzdju((Double) obj);
        }
        if (obj instanceof Byte) {
            return new zzdkc(obj.toString());
        }
        if (obj instanceof Character) {
            return new zzdkc(obj.toString());
        }
        if (obj instanceof String) {
            return new zzdkc((String) obj);
        }
        if (obj instanceof List) {
            ArrayList arrayList = new ArrayList();
            for (Object zzau : (List) obj) {
                arrayList.add(zzau(zzau));
            }
            return new zzdjx(arrayList);
        } else if (obj instanceof Map) {
            HashMap hashMap = new HashMap();
            for (Map.Entry<?, ?> entry : ((Map<?, ?>) obj).entrySet()) {
                zzbq.checkArgument(entry.getKey() instanceof String);
                hashMap.put((String) entry.getKey(), zzau(entry.getValue()));
            }
            return new zzdka(hashMap);
        } else if (obj instanceof Bundle) {
            HashMap hashMap2 = new HashMap();
            Bundle bundle = (Bundle) obj;
            for (String str : bundle.keySet()) {
                hashMap2.put(str, zzau(bundle.get(str)));
            }
            return new zzdka(hashMap2);
        } else {
            String valueOf = String.valueOf(obj.getClass());
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 20);
            sb.append("Type not supported: ");
            sb.append(valueOf);
            throw new UnsupportedOperationException(sb.toString());
        }
    }

    public static Object zzj(zzdjq<?> zzdjq) {
        String sb;
        if (zzdjq == null || zzdjq == zzdjw.zzlcy) {
            return null;
        }
        if (zzdjq instanceof zzdjt) {
            return (Boolean) ((zzdjt) zzdjq).value();
        }
        if (zzdjq instanceof zzdju) {
            zzdju zzdju = (zzdju) zzdjq;
            double doubleValue = ((Double) zzdju.value()).doubleValue();
            return (Double.isInfinite(doubleValue) || doubleValue - Math.floor(doubleValue) >= 1.0E-5d) ? ((Double) zzdju.value()).toString() : Integer.valueOf((int) doubleValue);
        } else if (zzdjq instanceof zzdkc) {
            return (String) ((zzdkc) zzdjq).value();
        } else {
            if (zzdjq instanceof zzdjx) {
                ArrayList arrayList = new ArrayList();
                for (zzdjq<?> zzdjq2 : (List<zzdjq<?>>) ((zzdjx) zzdjq).value()) {
                    Object zzj = zzj(zzdjq2);
                    if (zzj == null) {
                        sb = String.format("Failure to convert a list element to object: %s (%s)", new Object[]{zzdjq2, zzdjq2.getClass().getCanonicalName()});
                    } else {
                        arrayList.add(zzj);
                    }
                }
                return arrayList;
            } else if (zzdjq instanceof zzdka) {
                HashMap hashMap = new HashMap();
                for (Map.Entry<String, zzdjq<?>> entry : ((Map<String, zzdjq<?>>) ((zzdka) zzdjq).value()).entrySet()) {
                    Object zzj2 = zzj(entry.getValue());
                    if (zzj2 == null) {
                        sb = String.format("Failure to convert a map's value to object: %s (%s)", new Object[]{entry.getValue(), ((zzdjq) entry.getValue()).getClass().getCanonicalName()});
                    } else {
                        hashMap.put(entry.getKey(), zzj2);
                    }
                }
                return hashMap;
            } else {
                String valueOf = String.valueOf(zzdjq.getClass());
                StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 49);
                sb2.append("Converting to Object from unknown abstract type: ");
                sb2.append(valueOf);
                sb = sb2.toString();
            }
            zzdal.e(sb);
            return null;
        }
    }

    public static zzdjq zzk(zzdjq<?> zzdjq) {
        if (!(zzdjq instanceof zzdka)) {
            return zzdjq;
        }
        HashSet<String> hashSet = new HashSet<>();
        Map<String, zzdjq<?>> map = (Map<String, zzdjq<?>>) ((zzdka) zzdjq).value();
        for (Map.Entry<String, zzdjq<?>> entry : map.entrySet()) {
            if (entry.getValue() == zzdjw.zzlcz) {
                hashSet.add(entry.getKey());
            }
        }
        for (String remove : hashSet) {
            map.remove(remove);
        }
        return zzdjq;
    }

    public static boolean zzl(zzdjq zzdjq) {
        return (zzdjq instanceof zzdjt) || (zzdjq instanceof zzdju) || (zzdjq instanceof zzdkc) || zzdjq == zzdjw.zzlcy || zzdjq == zzdjw.zzlcz;
    }

    public static boolean zzm(zzdjq zzdjq) {
        if (zzdjq == zzdjw.zzlcx || zzdjq == zzdjw.zzlcw) {
            return true;
        }
        return (zzdjq instanceof zzdjw) && ((zzdjw) zzdjq).zzbkq();
    }
}
