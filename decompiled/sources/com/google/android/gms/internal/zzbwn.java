package com.google.android.gms.internal;

import com.google.android.gms.fitness.data.Field;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class zzbwn {
    public static final Set<String> zzhlz = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{"altitude", "duration", "food_item", "meal_type", "repetitions", "resistance", "resistance_type", "debug_session", "google.android.fitness.SessionV2"})));
    private static final zzbwn zzhmc = new zzbwn();
    private final Map<String, Map<String, zzbwp>> zzhma;
    private final Map<String, zzbwp> zzhmb;

    private zzbwn() {
        HashMap hashMap = new HashMap();
        hashMap.put("latitude", new zzbwp(-90.0d, 90.0d));
        hashMap.put("longitude", new zzbwp(-180.0d, 180.0d));
        hashMap.put("accuracy", new zzbwp(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, 10000.0d));
        hashMap.put("bpm", new zzbwp(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, 1000.0d));
        hashMap.put("altitude", new zzbwp(-100000.0d, 100000.0d));
        hashMap.put("percentage", new zzbwp(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, 100.0d));
        hashMap.put("confidence", new zzbwp(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, 100.0d));
        hashMap.put("duration", new zzbwp(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, 9.223372036854776E18d));
        hashMap.put("height", new zzbwp(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, 3.0d));
        hashMap.put("weight", new zzbwp(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, 1000.0d));
        hashMap.put("speed", new zzbwp(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, 11000.0d));
        this.zzhmb = Collections.unmodifiableMap(hashMap);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("com.google.step_count.delta", zzd("steps", new zzbwp(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, 1.0E-8d)));
        hashMap2.put("com.google.calories.consumed", zzd(Field.NUTRIENT_CALORIES, new zzbwp(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, 1.0E-6d)));
        hashMap2.put("com.google.calories.expended", zzd(Field.NUTRIENT_CALORIES, new zzbwp(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, 5.555555555555555E-10d)));
        hashMap2.put("com.google.distance.delta", zzd("distance", new zzbwp(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, 1.0E-7d)));
        this.zzhma = Collections.unmodifiableMap(hashMap2);
    }

    public static zzbwn zzase() {
        return zzhmc;
    }

    private static <K, V> Map<K, V> zzd(K k, V v) {
        HashMap hashMap = new HashMap();
        hashMap.put(k, v);
        return hashMap;
    }

    /* access modifiers changed from: package-private */
    public final zzbwp zzhv(String str) {
        return this.zzhmb.get(str);
    }

    /* access modifiers changed from: package-private */
    public final zzbwp zzz(String str, String str2) {
        Map map = this.zzhma.get(str);
        if (map != null) {
            return (zzbwp) map.get(str2);
        }
        return null;
    }
}
