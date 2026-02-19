package com.google.android.gms.games;

public class AnnotatedData<T> {
    private final T data;
    private final boolean zzhqt;

    public AnnotatedData(T t, boolean z) {
        this.data = t;
        this.zzhqt = z;
    }

    public T get() {
        return this.data;
    }

    public boolean isStale() {
        return this.zzhqt;
    }
}
