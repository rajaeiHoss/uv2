package com.google.firebase.appindexing;

import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appindexing.internal.zzf;
import java.lang.ref.WeakReference;

public abstract class FirebaseAppIndex {
    public static final String ACTION_UPDATE_INDEX = "com.google.firebase.appindexing.UPDATE_INDEX";
    public static final String APP_INDEXING_API_TAG = "FirebaseAppIndex";
    public static final String EXTRA_UPDATE_INDEX_REASON = "com.google.firebase.appindexing.extra.REASON";
    public static final int EXTRA_UPDATE_INDEX_REASON_REBUILD = 1;
    public static final int EXTRA_UPDATE_INDEX_REASON_REFRESH = 2;
    private static WeakReference<FirebaseAppIndex> zzmnv;

    public static synchronized FirebaseAppIndex getInstance() {
        FirebaseAppIndex firebaseAppIndex;
        synchronized (FirebaseAppIndex.class) {
            WeakReference<FirebaseAppIndex> weakReference = zzmnv;
            firebaseAppIndex = weakReference == null ? null : weakReference.get();
            if (firebaseAppIndex == null) {
                zzf zzf2 = new zzf(FirebaseApp.getInstance().getApplicationContext());
                zzmnv = new WeakReference<>(zzf2);
                firebaseAppIndex = zzf2;
            }
        }
        return firebaseAppIndex;
    }

    public abstract Task<Void> remove(String... strArr);

    public abstract Task<Void> removeAll();

    public abstract Task<Void> update(Indexable... indexableArr);
}
