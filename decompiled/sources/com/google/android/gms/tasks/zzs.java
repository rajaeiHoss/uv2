package com.google.android.gms.tasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

final class zzs implements Continuation<Void, List<Task<?>>> {
    private /* synthetic */ Collection<? extends Task<?>> zzles;

    zzs(Collection<? extends Task<?>> collection) {
        this.zzles = collection;
    }

    public final List<Task<?>> then(Task<Void> task) throws Exception {
        ArrayList<Task<?>> arrayList = new ArrayList<>();
        arrayList.addAll(this.zzles);
        return arrayList;
    }
}
