package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

final class zzehy implements Comparable<zzehy> {
    /* access modifiers changed from: private */
    public int retryCount;
    /* access modifiers changed from: private */
    public zzegu zzmxa;
    /* access modifiers changed from: private */
    public Transaction.Handler zzngm;
    /* access modifiers changed from: private */
    public ValueEventListener zzngn;
    /* access modifiers changed from: private */
    public int zzngo;
    private long zzngp;
    /* access modifiers changed from: private */
    public boolean zzngq;
    /* access modifiers changed from: private */
    public DatabaseError zzngr;
    /* access modifiers changed from: private */
    public long zzngs;
    /* access modifiers changed from: private */
    public zzenn zzngt;
    /* access modifiers changed from: private */
    public zzenn zzngu;
    /* access modifiers changed from: private */
    public zzenn zzngv;

    private zzehy(zzegu zzegu, Transaction.Handler handler, ValueEventListener valueEventListener, int i, boolean z, long j) {
        this.zzmxa = zzegu;
        this.zzngm = handler;
        this.zzngn = valueEventListener;
        this.zzngo = i;
        this.retryCount = 0;
        this.zzngq = z;
        this.zzngp = j;
        this.zzngr = null;
        this.zzngt = null;
        this.zzngu = null;
        this.zzngv = null;
    }

    /* synthetic */ zzehy(zzegu zzegu, Transaction.Handler handler, ValueEventListener valueEventListener, int i, boolean z, long j, zzegy zzegy) {
        this(zzegu, handler, valueEventListener, i, z, j);
    }

    static /* synthetic */ int zzd(zzehy zzehy) {
        int i = zzehy.retryCount;
        zzehy.retryCount = i + 1;
        return i;
    }

    @Override
    public int compareTo(zzehy other) {
        long j = this.zzngp;
        long j2 = other.zzngp;
        if (j < j2) {
            return -1;
        }
        return j == j2 ? 0 : 1;
    }
}
