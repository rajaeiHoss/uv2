package com.google.firebase.database;

import java.util.Iterator;

final class zzk implements Iterable<MutableData> {
    final /* synthetic */ Iterator zzmwa;
    final /* synthetic */ MutableData zzmwy;

    zzk(MutableData mutableData, Iterator it) {
        this.zzmwy = mutableData;
        this.zzmwa = it;
    }

    public final Iterator<MutableData> iterator() {
        return new zzl(this);
    }
}
