package com.google.android.gms.internal;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Objects;

final class zzdyt extends WeakReference<Throwable> {
    private final int zzmmk;

    public zzdyt(Throwable th, ReferenceQueue<Throwable> referenceQueue) {
        super(th, (ReferenceQueue) null);
        Objects.requireNonNull(th, "The referent cannot be null");
        this.zzmmk = System.identityHashCode(th);
    }

    public final boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            if (this == obj) {
                return true;
            }
            zzdyt zzdyt = (zzdyt) obj;
            return this.zzmmk == zzdyt.zzmmk && get() == zzdyt.get();
        }
        return false;
    }

    public final int hashCode() {
        return this.zzmmk;
    }
}
