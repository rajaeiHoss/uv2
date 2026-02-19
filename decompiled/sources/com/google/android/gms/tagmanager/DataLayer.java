package com.google.android.gms.tagmanager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataLayer {
    public static final String EVENT_KEY = "event";
    public static final Object OBJECT_NOT_PRESENT = new Object();
    private static String[] zzkoj = "gtm.lifetime".split("\\.");
    private static final Pattern zzkok = Pattern.compile("(\\d+)\\s*([smhd]?)");
    private final ConcurrentHashMap<zzb, Integer> zzkol;
    private final Map<String, Object> zzkom;
    private final ReentrantLock zzkon;
    private final LinkedList<Map<String, Object>> zzkoo;
    private final zzc zzkop;
    /* access modifiers changed from: private */
    public final CountDownLatch zzkoq;

    static final class zza {
        public final Object mValue;
        public final String zzbkr;

        zza(String str, Object obj) {
            this.zzbkr = str;
            this.mValue = obj;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            return this.zzbkr.equals(zza.zzbkr) && this.mValue.equals(zza.mValue);
        }

        public final int hashCode() {
            return Arrays.hashCode(new Integer[]{Integer.valueOf(this.zzbkr.hashCode()), Integer.valueOf(this.mValue.hashCode())});
        }

        public final String toString() {
            String str = this.zzbkr;
            String obj = this.mValue.toString();
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 13 + String.valueOf(obj).length());
            sb.append("Key: ");
            sb.append(str);
            sb.append(" value: ");
            sb.append(obj);
            return sb.toString();
        }
    }

    interface zzb {
        void zzy(Map<String, Object> map);
    }

    interface zzc {
        void zza(zzaq zzaq);

        void zza(List<zza> list, long j);

        void zzlo(String str);
    }

    DataLayer() {
        this(new zzao());
    }

    DataLayer(zzc zzc2) {
        this.zzkop = zzc2;
        this.zzkol = new ConcurrentHashMap<>();
        this.zzkom = new HashMap();
        this.zzkon = new ReentrantLock();
        this.zzkoo = new LinkedList<>();
        this.zzkoq = new CountDownLatch(1);
        zzc2.zza(new zzap(this));
    }

    public static List<Object> listOf(Object... objArr) {
        ArrayList arrayList = new ArrayList();
        for (Object add : objArr) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public static Map<String, Object> mapOf(Object... objArr) {
        if (objArr.length % 2 == 0) {
            HashMap hashMap = new HashMap();
            int i = 0;
            while (i < objArr.length) {
                if (objArr[i] instanceof String) {
                    hashMap.put(objArr[i], objArr[i + 1]);
                    i += 2;
                } else {
                    String valueOf = String.valueOf(objArr[i]);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 21);
                    sb.append("key is not a string: ");
                    sb.append(valueOf);
                    throw new IllegalArgumentException(sb.toString());
                }
            }
            return hashMap;
        }
        throw new IllegalArgumentException("expected even number of key-value pairs");
    }

    private final void zza(List<Object> list, List<Object> list2) {
        while (list2.size() < list.size()) {
            list2.add((Object) null);
        }
        for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            if (obj instanceof List) {
                if (!(list2.get(i) instanceof List)) {
                    list2.set(i, new ArrayList());
                }
                zza((List<Object>) (List) obj, (List<Object>) (List) list2.get(i));
            } else if (obj instanceof Map) {
                if (!(list2.get(i) instanceof Map)) {
                    list2.set(i, new HashMap());
                }
                zzd((Map) obj, (Map) list2.get(i));
            } else if (obj != OBJECT_NOT_PRESENT) {
                list2.set(i, obj);
            }
        }
    }

    private final void zza(Map<String, Object> map, String str, Collection<zza> collection) {
        for (Map.Entry next : map.entrySet()) {
            String str2 = str.length() == 0 ? "" : ".";
            String str3 = (String) next.getKey();
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + str2.length() + String.valueOf(str3).length());
            sb.append(str);
            sb.append(str2);
            sb.append(str3);
            String sb2 = sb.toString();
            if (next.getValue() instanceof Map) {
                zza((Map) next.getValue(), sb2, collection);
            } else if (!sb2.equals("gtm.lifetime")) {
                collection.add(new zza(sb2, next.getValue()));
            }
        }
    }

    /* access modifiers changed from: private */
    public final void zzaa(Map<String, Object> map) {
        this.zzkon.lock();
        try {
            this.zzkoo.offer(map);
            if (this.zzkon.getHoldCount() == 1) {
                int i = 0;
                while (true) {
                    Map poll = this.zzkoo.poll();
                    if (poll == null) {
                        break;
                    }
                    synchronized (this.zzkom) {
                        for (Object key : poll.keySet()) {
                            String str = (String) key;
                            zzd(zzn(str, poll.get(str)), this.zzkom);
                        }
                    }
                    for (zzb zzy : this.zzkol.keySet()) {
                        zzy.zzy(poll);
                    }
                    i++;
                    if (i > 500) {
                        this.zzkoo.clear();
                        throw new RuntimeException("Seems like an infinite loop of pushing to the data layer");
                    }
                }
            }
            Object zzab = zzab(map);
            Long zzln = zzab == null ? null : zzln(zzab.toString());
            if (zzln != null) {
                ArrayList arrayList = new ArrayList();
                zza(map, "", arrayList);
                this.zzkop.zza(arrayList, zzln.longValue());
            }
            this.zzkon.unlock();
        } catch (Throwable th) {
            this.zzkon.unlock();
            throw th;
        }
    }

    private static Object zzab(Map<String, Object> map) {
        String[] strArr = zzkoj;
        int length = strArr.length;
        int i = 0;
        Object obj = map;
        while (i < length) {
            String str = strArr[i];
            if (!(obj instanceof Map)) {
                return null;
            }
            i++;
            obj = ((Map) obj).get(str);
        }
        return obj;
    }

    private final void zzd(Map<String, Object> map, Map<String, Object> map2) {
        for (String next : map.keySet()) {
            Object obj = map.get(next);
            if (obj instanceof List) {
                if (!(map2.get(next) instanceof List)) {
                    map2.put(next, new ArrayList());
                }
                zza((List<Object>) (List) obj, (List<Object>) (List) map2.get(next));
            } else if (obj instanceof Map) {
                if (!(map2.get(next) instanceof Map)) {
                    map2.put(next, new HashMap());
                }
                zzd((Map) obj, (Map) map2.get(next));
            } else {
                map2.put(next, obj);
            }
        }
    }

    private static Long zzln(String str) {
        long j;
        long j2;
        long j3;
        Matcher matcher = zzkok.matcher(str);
        if (!matcher.matches()) {
            String valueOf = String.valueOf(str);
            zzdj.zzcy(valueOf.length() != 0 ? "unknown _lifetime: ".concat(valueOf) : new String("unknown _lifetime: "));
            return null;
        }
        try {
            j = Long.parseLong(matcher.group(1));
        } catch (NumberFormatException unused) {
            String valueOf2 = String.valueOf(str);
            zzdj.zzcz(valueOf2.length() != 0 ? "illegal number in _lifetime value: ".concat(valueOf2) : new String("illegal number in _lifetime value: "));
            j = 0;
        }
        if (j <= 0) {
            String valueOf3 = String.valueOf(str);
            zzdj.zzcy(valueOf3.length() != 0 ? "non-positive _lifetime: ".concat(valueOf3) : new String("non-positive _lifetime: "));
            return null;
        }
        String group = matcher.group(2);
        if (group.length() == 0) {
            return Long.valueOf(j);
        }
        char charAt = group.charAt(0);
        if (charAt == 'd') {
            j2 = j * 1000 * 60 * 60 * 24;
        } else if (charAt == 'h') {
            j2 = j * 1000 * 60 * 60;
        } else if (charAt == 'm') {
            j2 = j * 1000 * 60;
        } else if (charAt == 's') {
            j2 = j * 1000;
        } else {
            String valueOf4 = String.valueOf(str);
            zzdj.zzcz(valueOf4.length() != 0 ? "unknown units in _lifetime: ".concat(valueOf4) : new String("unknown units in _lifetime: "));
            return null;
        }
        return Long.valueOf(j2);
    }

    static Map<String, Object> zzn(String str, Object obj) {
        HashMap hashMap = new HashMap();
        String[] split = str.toString().split("\\.");
        int i = 0;
        HashMap hashMap2 = hashMap;
        while (i < split.length - 1) {
            HashMap hashMap3 = new HashMap();
            hashMap2.put(split[i], hashMap3);
            i++;
            hashMap2 = hashMap3;
        }
        hashMap2.put(split[split.length - 1], obj);
        return hashMap;
    }

    public Object get(String str) {
        synchronized (this.zzkom) {
            Object obj = this.zzkom;
            for (String str2 : str.split("\\.")) {
                if (!(obj instanceof Map)) {
                    return null;
                }
                obj = ((Map) obj).get(str2);
                if (obj == null) {
                    return null;
                }
            }
            return obj;
        }
    }

    public void push(String str, Object obj) {
        push(zzn(str, obj));
    }

    public void push(Map<String, Object> map) {
        try {
            this.zzkoq.await();
        } catch (InterruptedException unused) {
            zzdj.zzcz("DataLayer.push: unexpected InterruptedException");
        }
        zzaa(map);
    }

    public void pushEvent(String str, Map<String, Object> map) {
        HashMap hashMap = new HashMap(map);
        hashMap.put("event", str);
        push(hashMap);
    }

    public String toString() {
        String sb;
        synchronized (this.zzkom) {
            StringBuilder sb2 = new StringBuilder();
            for (Map.Entry next : this.zzkom.entrySet()) {
                sb2.append(String.format("{\n\tKey: %s\n\tValue: %s\n}\n", new Object[]{next.getKey(), next.getValue()}));
            }
            sb = sb2.toString();
        }
        return sb;
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzb zzb2) {
        this.zzkol.put(zzb2, 0);
    }

    /* access modifiers changed from: package-private */
    public final void zzlm(String str) {
        push(str, (Object) null);
        this.zzkop.zzlo(str);
    }
}
