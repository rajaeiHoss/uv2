package com.kenai.jbosh;

import java.lang.Comparable;

abstract class AbstractAttr<T extends Comparable> implements Comparable {
    private final T value;

    protected AbstractAttr(T t) {
        this.value = t;
    }

    public final T getValue() {
        return this.value;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof AbstractAttr)) {
            return this.value.equals(((AbstractAttr) obj).value);
        }
        return false;
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    public String toString() {
        return this.value.toString();
    }

    public int compareTo(Object obj) {
        if (obj == null) {
            return 1;
        }
        return this.value.compareTo(obj);
    }
}
