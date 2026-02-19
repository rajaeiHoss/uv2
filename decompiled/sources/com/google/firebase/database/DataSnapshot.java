package com.google.firebase.database;

import com.google.android.gms.internal.zzegu;
import com.google.android.gms.internal.zzeng;
import com.google.android.gms.internal.zzepf;
import com.google.android.gms.internal.zzepg;

public class DataSnapshot {
    private final zzeng zzmvy;
    /* access modifiers changed from: private */
    public final DatabaseReference zzmvz;

    DataSnapshot(DatabaseReference databaseReference, zzeng zzeng) {
        this.zzmvy = zzeng;
        this.zzmvz = databaseReference;
    }

    public DataSnapshot child(String str) {
        return new DataSnapshot(this.zzmvz.child(str), zzeng.zzj(this.zzmvy.zzbve().zzan(new zzegu(str))));
    }

    public boolean exists() {
        return !this.zzmvy.zzbve().isEmpty();
    }

    public Iterable<DataSnapshot> getChildren() {
        return new zza(this, this.zzmvy.iterator());
    }

    public long getChildrenCount() {
        return (long) this.zzmvy.zzbve().getChildCount();
    }

    public String getKey() {
        return this.zzmvz.getKey();
    }

    public Object getPriority() {
        Object value = this.zzmvy.zzbve().zzcce().getValue();
        return value instanceof Long ? Double.valueOf((double) ((Long) value).longValue()) : value;
    }

    public DatabaseReference getRef() {
        return this.zzmvz;
    }

    public Object getValue() {
        return this.zzmvy.zzbve().getValue();
    }

    public <T> T getValue(GenericTypeIndicator<T> genericTypeIndicator) {
        return zzepg.zza(this.zzmvy.zzbve().getValue(), genericTypeIndicator);
    }

    public <T> T getValue(Class<T> cls) {
        return zzepg.zza(this.zzmvy.zzbve().getValue(), cls);
    }

    public Object getValue(boolean z) {
        return this.zzmvy.zzbve().getValue(z);
    }

    public boolean hasChild(String str) {
        if (this.zzmvz.getParent() == null) {
            zzepf.zzqo(str);
        } else {
            zzepf.zzqn(str);
        }
        return !this.zzmvy.zzbve().zzan(new zzegu(str)).isEmpty();
    }

    public boolean hasChildren() {
        return this.zzmvy.zzbve().getChildCount() > 0;
    }

    public String toString() {
        String key = this.zzmvz.getKey();
        String valueOf = String.valueOf(this.zzmvy.zzbve().getValue(true));
        StringBuilder sb = new StringBuilder(String.valueOf(key).length() + 33 + String.valueOf(valueOf).length());
        sb.append("DataSnapshot { key = ");
        sb.append(key);
        sb.append(", value = ");
        sb.append(valueOf);
        sb.append(" }");
        return sb.toString();
    }
}
