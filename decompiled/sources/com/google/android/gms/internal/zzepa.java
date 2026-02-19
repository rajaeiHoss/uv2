package com.google.android.gms.internal;

public final class zzepa<T, U> {
    private final T first;
    private final U second;

    public zzepa(T t, U u) {
        this.first = t;
        this.second = u;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        zzepa<?, ?> other = (zzepa<?, ?>) obj;
        T t = this.first;
        Object otherFirst = other.first;
        if (t == null ? otherFirst != null : !t.equals(otherFirst)) {
            return false;
        }
        U u = this.second;
        Object otherSecond = other.second;
        return u == null ? otherSecond == null : u.equals(otherSecond);
    }

    public final T getFirst() {
        return this.first;
    }

    public final int hashCode() {
        T t = this.first;
        int i = 0;
        int hashCode = (t != null ? t.hashCode() : 0) * 31;
        U u = this.second;
        if (u != null) {
            i = u.hashCode();
        }
        return hashCode + i;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.first);
        String valueOf2 = String.valueOf(this.second);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 7 + String.valueOf(valueOf2).length());
        sb.append("Pair(");
        sb.append(valueOf);
        sb.append(",");
        sb.append(valueOf2);
        sb.append(")");
        return sb.toString();
    }

    public final U zzcdp() {
        return this.second;
    }
}
