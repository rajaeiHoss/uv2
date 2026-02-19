package com.google.firebase.database;

import com.google.android.gms.internal.zzegu;
import com.google.android.gms.internal.zzegx;
import com.google.android.gms.internal.zzejo;
import com.google.android.gms.internal.zzene;
import com.google.android.gms.internal.zzenn;
import com.google.android.gms.internal.zzenq;
import com.google.android.gms.internal.zzent;
import com.google.android.gms.internal.zzepa;
import com.google.android.gms.internal.zzepd;
import com.google.android.gms.internal.zzepf;
import com.google.android.gms.internal.zzepg;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import java.util.Map;

public class OnDisconnect {
    /* access modifiers changed from: private */
    public zzegx zzmwt;
    /* access modifiers changed from: private */
    public zzegu zzmxa;

    OnDisconnect(zzegx zzegx, zzegu zzegu) {
        this.zzmwt = zzegx;
        this.zzmxa = zzegu;
    }

    private final Task<Void> zza(DatabaseReference.CompletionListener completionListener) {
        zzepa<Task<Void>, DatabaseReference.CompletionListener> zzb = zzepd.zzb(completionListener);
        this.zzmwt.zzp(new zzo(this, zzb));
        return zzb.getFirst();
    }

    private final Task<Void> zza(Map<String, Object> map, DatabaseReference.CompletionListener completionListener) {
        Map<zzegu, zzenn> zzb = zzepf.zzb(this.zzmxa, map);
        zzepa<Task<Void>, DatabaseReference.CompletionListener> zzb2 = zzepd.zzb(completionListener);
        this.zzmwt.zzp(new zzn(this, zzb, zzb2, map));
        return zzb2.getFirst();
    }

    private final Task<Void> zzb(Object obj, zzenn zzenn, DatabaseReference.CompletionListener completionListener) {
        zzepf.zzao(this.zzmxa);
        zzejo.zza(this.zzmxa, obj);
        Object zzca = zzepg.zzca(obj);
        zzepf.zzbz(zzca);
        zzenn zza = zzenq.zza(zzca, zzenn);
        zzepa<Task<Void>, DatabaseReference.CompletionListener> zzb = zzepd.zzb(completionListener);
        this.zzmwt.zzp(new zzm(this, zza, zzb));
        return zzb.getFirst();
    }

    public Task<Void> cancel() {
        return zza((DatabaseReference.CompletionListener) null);
    }

    public void cancel(DatabaseReference.CompletionListener completionListener) {
        zza(completionListener);
    }

    public Task<Void> removeValue() {
        return setValue((Object) null);
    }

    public void removeValue(DatabaseReference.CompletionListener completionListener) {
        setValue((Object) null, completionListener);
    }

    public Task<Void> setValue(Object obj) {
        return zzb(obj, zzene.zzcco(), (DatabaseReference.CompletionListener) null);
    }

    public Task<Void> setValue(Object obj, double d) {
        return zzb(obj, zzent.zzc(this.zzmxa, Double.valueOf(d)), (DatabaseReference.CompletionListener) null);
    }

    public Task<Void> setValue(Object obj, String str) {
        return zzb(obj, zzent.zzc(this.zzmxa, str), (DatabaseReference.CompletionListener) null);
    }

    public void setValue(Object obj, double d, DatabaseReference.CompletionListener completionListener) {
        zzb(obj, zzent.zzc(this.zzmxa, Double.valueOf(d)), completionListener);
    }

    public void setValue(Object obj, DatabaseReference.CompletionListener completionListener) {
        zzb(obj, zzene.zzcco(), completionListener);
    }

    public void setValue(Object obj, String str, DatabaseReference.CompletionListener completionListener) {
        zzb(obj, zzent.zzc(this.zzmxa, str), completionListener);
    }

    public void setValue(Object obj, Map map, DatabaseReference.CompletionListener completionListener) {
        zzb(obj, zzent.zzc(this.zzmxa, map), completionListener);
    }

    public Task<Void> updateChildren(Map<String, Object> map) {
        return zza(map, (DatabaseReference.CompletionListener) null);
    }

    public void updateChildren(Map<String, Object> map, DatabaseReference.CompletionListener completionListener) {
        zza(map, completionListener);
    }
}
