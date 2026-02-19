package com.google.android.gms.internal;

import java.util.Collections;
import java.util.List;

public final class zzeel {
    private final List<List<String>> zzmzh;
    private final List<String> zzmzi;

    public zzeel(List<List<String>> list, List<String> list2) {
        if (list.size() == list2.size() - 1) {
            this.zzmzh = list;
            this.zzmzi = list2;
            return;
        }
        throw new IllegalArgumentException("Number of posts need to be n-1 for n hashes in CompoundHash");
    }

    public final List<List<String>> zzbwi() {
        return Collections.unmodifiableList(this.zzmzh);
    }

    public final List<String> zzbwj() {
        return Collections.unmodifiableList(this.zzmzi);
    }
}
