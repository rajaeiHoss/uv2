package com.google.firebase.appindexing.internal;

import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.appindexing.FirebaseAppIndex;
import com.google.firebase.appindexing.FirebaseAppIndexingInvalidArgumentException;
import com.google.firebase.appindexing.Indexable;

public final class zzf extends FirebaseAppIndex {
    private zzm zzmoc;

    public zzf(Context context) {
        this(context, new zzj(context));
    }

    private zzf(Context context, GoogleApi<Api.ApiOptions.NoOptions> googleApi) {
        this.zzmoc = new zzm(googleApi);
    }

    public final Task<Void> remove(String... strArr) {
        return this.zzmoc.zzb(new zzh(this, strArr));
    }

    public final Task<Void> removeAll() {
        return this.zzmoc.zzb(new zzi(this));
    }

    public final Task<Void> update(Indexable... indexableArr) {
        Thing[] thingArr;
        if (indexableArr == null) {
            thingArr = null;
        } else {
            try {
                Thing[] thingArr2 = new Thing[indexableArr.length];
                System.arraycopy(indexableArr, 0, thingArr2, 0, indexableArr.length);
                thingArr = thingArr2;
            } catch (ArrayStoreException unused) {
                return Tasks.forException(new FirebaseAppIndexingInvalidArgumentException("Custom Indexable-objects are not allowed. Please use the 'Indexables'-class for creating the objects."));
            }
        }
        return this.zzmoc.zzb(new zzg(this, thingArr));
    }
}
