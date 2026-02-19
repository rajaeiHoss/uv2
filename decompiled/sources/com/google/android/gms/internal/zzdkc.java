package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class zzdkc extends zzdjq<String> {
    private static final Map<String, zzdcp> zzlct;
    /* access modifiers changed from: private */
    public final String mValue;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put("charAt", new zzdff());
        hashMap.put("concat", new zzdfg());
        hashMap.put("hasOwnProperty", zzdeq.zzlab);
        hashMap.put("indexOf", new zzdfh());
        hashMap.put("lastIndexOf", new zzdfi());
        hashMap.put("match", new zzdfj());
        hashMap.put("replace", new zzdfk());
        hashMap.put(FirebaseAnalytics.Event.SEARCH, new zzdfl());
        hashMap.put("slice", new zzdfm());
        hashMap.put("split", new zzdfn());
        hashMap.put("substring", new zzdfo());
        hashMap.put("toLocaleLowerCase", new zzdfp());
        hashMap.put("toLocaleUpperCase", new zzdfq());
        hashMap.put("toLowerCase", new zzdfr());
        hashMap.put("toUpperCase", new zzdft());
        hashMap.put("toString", new zzdfs());
        hashMap.put("trim", new zzdfu());
        zzlct = Collections.unmodifiableMap(hashMap);
    }

    public zzdkc(String str) {
        zzbq.checkNotNull(str);
        this.mValue = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzdkc) {
            return this.mValue.equals((String) ((zzdkc) obj).value());
        }
        return false;
    }

    public final String toString() {
        return this.mValue.toString();
    }

    public final String value() {
        return this.mValue;
    }

    public final Iterator<zzdjq<?>> zzbko() {
        return new zzdkd(this);
    }

    public final zzdjq<?> zzfj(int i) {
        return (i < 0 || i >= this.mValue.length()) ? zzdjw.zzlcz : new zzdkc(String.valueOf(this.mValue.charAt(i)));
    }

    public final boolean zznk(String str) {
        return zzlct.containsKey(str);
    }

    public final zzdcp zznl(String str) {
        if (zznk(str)) {
            return zzlct.get(str);
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 51);
        sb.append("Native Method ");
        sb.append(str);
        sb.append(" is not defined for type ListWrapper.");
        throw new IllegalStateException(sb.toString());
    }
}
