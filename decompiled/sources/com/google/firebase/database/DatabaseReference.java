package com.google.firebase.database;

import com.google.android.gms.internal.zzegi;
import com.google.android.gms.internal.zzegp;
import com.google.android.gms.internal.zzegu;
import com.google.android.gms.internal.zzegx;
import com.google.android.gms.internal.zzeib;
import com.google.android.gms.internal.zzejo;
import com.google.android.gms.internal.zzemq;
import com.google.android.gms.internal.zzenn;
import com.google.android.gms.internal.zzenq;
import com.google.android.gms.internal.zzent;
import com.google.android.gms.internal.zzepa;
import com.google.android.gms.internal.zzepc;
import com.google.android.gms.internal.zzepd;
import com.google.android.gms.internal.zzepf;
import com.google.android.gms.internal.zzepg;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.Transaction;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Objects;

public class DatabaseReference extends Query {
    private static zzegp zzmwg;

    public interface CompletionListener {
        void onComplete(DatabaseError databaseError, DatabaseReference databaseReference);
    }

    DatabaseReference(zzegx zzegx, zzegu zzegu) {
        super(zzegx, zzegu);
    }

    public static void goOffline() {
        zzeib.zzd(zzbvc());
    }

    public static void goOnline() {
        zzeib.zze(zzbvc());
    }

    private final Task<Void> zza(zzenn zzenn, CompletionListener completionListener) {
        zzepf.zzao(this.zzmxa);
        zzepa<Task<Void>, CompletionListener> zzb = zzepd.zzb(completionListener);
        this.zzmwt.zzp(new zzd(this, zzenn, zzb));
        return zzb.getFirst();
    }

    private final Task<Void> zza(Object obj, zzenn zzenn, CompletionListener completionListener) {
        zzepf.zzao(this.zzmxa);
        zzejo.zza(this.zzmxa, obj);
        Object zzca = zzepg.zzca(obj);
        zzepf.zzbz(zzca);
        zzenn zza = zzenq.zza(zzca, zzenn);
        zzepa<Task<Void>, CompletionListener> zzb = zzepd.zzb(completionListener);
        this.zzmwt.zzp(new zzc(this, zza, zzb));
        return zzb.getFirst();
    }

    private final Task<Void> zza(Map<String, Object> map, CompletionListener completionListener) {
        Objects.requireNonNull(map, "Can't pass null for argument 'update' in updateChildren()");
        Map<String, Object> zzap = zzepg.zzap(map);
        zzegi zzan = zzegi.zzan(zzepf.zzb(this.zzmxa, zzap));
        zzepa<Task<Void>, CompletionListener> zzb = zzepd.zzb(completionListener);
        this.zzmwt.zzp(new zze(this, zzan, zzb, zzap));
        return zzb.getFirst();
    }

    private static synchronized zzegp zzbvc() {
        zzegp zzegp;
        synchronized (DatabaseReference.class) {
            if (zzmwg == null) {
                zzmwg = new zzegp();
            }
            zzegp = zzmwg;
        }
        return zzegp;
    }

    public DatabaseReference child(String str) {
        Objects.requireNonNull(str, "Can't pass null for argument 'pathString' in child()");
        if (this.zzmxa.isEmpty()) {
            zzepf.zzqo(str);
        } else {
            zzepf.zzqn(str);
        }
        return new DatabaseReference(this.zzmwt, this.zzmxa.zzh(new zzegu(str)));
    }

    public boolean equals(Object obj) {
        return (obj instanceof DatabaseReference) && toString().equals(obj.toString());
    }

    public FirebaseDatabase getDatabase() {
        return this.zzmwt.getDatabase();
    }

    public String getKey() {
        if (this.zzmxa.isEmpty()) {
            return null;
        }
        return this.zzmxa.zzbyt().asString();
    }

    public DatabaseReference getParent() {
        zzegu zzbys = this.zzmxa.zzbys();
        if (zzbys != null) {
            return new DatabaseReference(this.zzmwt, zzbys);
        }
        return null;
    }

    public DatabaseReference getRoot() {
        return new DatabaseReference(this.zzmwt, new zzegu(""));
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public OnDisconnect onDisconnect() {
        zzepf.zzao(this.zzmxa);
        return new OnDisconnect(this.zzmwt, this.zzmxa);
    }

    public DatabaseReference push() {
        return new DatabaseReference(this.zzmwt, this.zzmxa.zza(zzemq.zzqf(zzepc.zzca(this.zzmwt.zzbyw()))));
    }

    public Task<Void> removeValue() {
        return setValue((Object) null);
    }

    public void removeValue(CompletionListener completionListener) {
        setValue((Object) null, completionListener);
    }

    public void runTransaction(Transaction.Handler handler) {
        runTransaction(handler, true);
    }

    public void runTransaction(Transaction.Handler handler, boolean z) {
        Objects.requireNonNull(handler, "Can't pass null for argument 'handler' in runTransaction()");
        zzepf.zzao(this.zzmxa);
        this.zzmwt.zzp(new zzf(this, handler, z));
    }

    public Task<Void> setPriority(Object obj) {
        return zza(zzent.zzc(this.zzmxa, obj), (CompletionListener) null);
    }

    public void setPriority(Object obj, CompletionListener completionListener) {
        zza(zzent.zzc(this.zzmxa, obj), completionListener);
    }

    public Task<Void> setValue(Object obj) {
        return zza(obj, zzent.zzc(this.zzmxa, (Object) null), (CompletionListener) null);
    }

    public Task<Void> setValue(Object obj, Object obj2) {
        return zza(obj, zzent.zzc(this.zzmxa, obj2), (CompletionListener) null);
    }

    public void setValue(Object obj, CompletionListener completionListener) {
        zza(obj, zzent.zzc(this.zzmxa, (Object) null), completionListener);
    }

    public void setValue(Object obj, Object obj2, CompletionListener completionListener) {
        zza(obj, zzent.zzc(this.zzmxa, obj2), completionListener);
    }

    public String toString() {
        DatabaseReference parent = getParent();
        if (parent == null) {
            return this.zzmwt.toString();
        }
        try {
            String databaseReference = parent.toString();
            String replace = URLEncoder.encode(getKey(), "UTF-8").replace("+", "%20");
            StringBuilder sb = new StringBuilder(String.valueOf(databaseReference).length() + 1 + String.valueOf(replace).length());
            sb.append(databaseReference);
            sb.append("/");
            sb.append(replace);
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            String valueOf = String.valueOf(getKey());
            throw new DatabaseException(valueOf.length() != 0 ? "Failed to URLEncode key: ".concat(valueOf) : new String("Failed to URLEncode key: "), e);
        }
    }

    public Task<Void> updateChildren(Map<String, Object> map) {
        return zza(map, (CompletionListener) null);
    }

    public void updateChildren(Map<String, Object> map, CompletionListener completionListener) {
        zza(map, completionListener);
    }
}
