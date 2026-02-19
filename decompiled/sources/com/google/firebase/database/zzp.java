package com.google.firebase.database;

final class zzp implements ValueEventListener {
    private /* synthetic */ ValueEventListener zzmxg;
    private /* synthetic */ Query zzmxh;

    zzp(Query query, ValueEventListener valueEventListener) {
        this.zzmxh = query;
        this.zzmxg = valueEventListener;
    }

    public final void onCancelled(DatabaseError databaseError) {
        this.zzmxg.onCancelled(databaseError);
    }

    public final void onDataChange(DataSnapshot dataSnapshot) {
        this.zzmxh.removeEventListener((ValueEventListener) this);
        this.zzmxg.onDataChange(dataSnapshot);
    }
}
