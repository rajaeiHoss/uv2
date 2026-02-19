package com.google.android.gms.internal;

import java.lang.Comparable;
import java.util.Comparator;

public final class zzeek<A extends Comparable<A>> implements Comparator<A> {
    private static zzeek zzmzg = new zzeek();

    private zzeek() {
    }

    public static <T extends Comparable<T>> zzeek<T> zze(Class<T> cls) {
        return zzmzg;
    }

    public final int compare(A a, A a2) {
        return a.compareTo(a2);
    }
}
