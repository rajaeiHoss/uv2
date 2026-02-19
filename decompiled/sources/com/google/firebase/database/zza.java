package com.google.firebase.database;

import java.util.Iterator;

final class zza implements Iterable<DataSnapshot> {
    final /* synthetic */ Iterator zzmwa;
    final /* synthetic */ DataSnapshot zzmwb;

    zza(DataSnapshot dataSnapshot, Iterator it) {
        this.zzmwb = dataSnapshot;
        this.zzmwa = it;
    }

    public final Iterator<DataSnapshot> iterator() {
        return new zzb(this);
    }
}
