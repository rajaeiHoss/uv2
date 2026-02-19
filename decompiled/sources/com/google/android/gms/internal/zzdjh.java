package com.google.android.gms.internal;

import java.util.Collections;
import java.util.List;

public final class zzdjh {
    private final List<zzdje> zzlcg;
    private final List<zzdje> zzlch;
    private final List<zzdje> zzlci;
    private final List<zzdje> zzlcj;

    zzdjh(List<zzdje> list, List<zzdje> list2, List<zzdje> list3, List<zzdje> list4) {
        this.zzlcg = Collections.unmodifiableList(list);
        this.zzlch = Collections.unmodifiableList(list2);
        this.zzlci = Collections.unmodifiableList(list3);
        this.zzlcj = Collections.unmodifiableList(list4);
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzlcg);
        String valueOf2 = String.valueOf(this.zzlch);
        String valueOf3 = String.valueOf(this.zzlci);
        String valueOf4 = String.valueOf(this.zzlcj);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 71 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length() + String.valueOf(valueOf4).length());
        sb.append("Positive predicates: ");
        sb.append(valueOf);
        sb.append("  Negative predicates: ");
        sb.append(valueOf2);
        sb.append("  Add tags: ");
        sb.append(valueOf3);
        sb.append("  Remove tags: ");
        sb.append(valueOf4);
        return sb.toString();
    }

    public final List<zzdje> zzbkf() {
        return this.zzlcg;
    }

    public final List<zzdje> zzbkg() {
        return this.zzlch;
    }

    public final List<zzdje> zzbkh() {
        return this.zzlci;
    }

    public final List<zzdje> zzbki() {
        return this.zzlcj;
    }
}
