package com.google.android.gms.tasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

final class zzr<TResult> implements Continuation<Void, List<TResult>> {
    private final Collection<? extends Task<TResult>> zzles;

    zzr(Collection<? extends Task<TResult>> collection) {
        this.zzles = collection;
    }

    public final List<TResult> then(Task<Void> task) throws Exception {
        if (this.zzles.size() == 0) {
            return Collections.emptyList();
        }
        ArrayList<TResult> arrayList = new ArrayList<>();
        for (Task<TResult> result : this.zzles) {
            arrayList.add(result.getResult());
        }
        return arrayList;
    }
}
