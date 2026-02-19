package com.google.android.gms.internal;

public final class zzejk {
    private final long zznit;

    public zzejk(long j) {
        this.zznit = j;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.zznit == ((zzejk) obj).zznit;
    }

    public final int hashCode() {
        long j = this.zznit;
        return (int) (j ^ (j >>> 32));
    }

    public final String toString() {
        long j = this.zznit;
        StringBuilder sb = new StringBuilder(35);
        sb.append("Tag{tagNumber=");
        sb.append(j);
        sb.append('}');
        return sb.toString();
    }

    public final long zzbzg() {
        return this.zznit;
    }
}
