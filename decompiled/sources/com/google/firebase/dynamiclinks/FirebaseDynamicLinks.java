package com.google.firebase.dynamiclinks;

import android.content.Intent;
import android.net.Uri;
import com.google.android.gms.internal.zzepn;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.dynamiclinks.DynamicLink;
import java.lang.ref.WeakReference;

public abstract class FirebaseDynamicLinks {
    private static WeakReference<FirebaseDynamicLinks> zznrz;

    public static synchronized FirebaseDynamicLinks getInstance() {
        FirebaseDynamicLinks firebaseDynamicLinks;
        synchronized (FirebaseDynamicLinks.class) {
            WeakReference<FirebaseDynamicLinks> weakReference = zznrz;
            firebaseDynamicLinks = weakReference == null ? null : (FirebaseDynamicLinks) weakReference.get();
            if (firebaseDynamicLinks == null) {
                firebaseDynamicLinks = new zzepn(FirebaseApp.getInstance().getApplicationContext());
                zznrz = new WeakReference<>(firebaseDynamicLinks);
            }
        }
        return firebaseDynamicLinks;
    }

    public abstract DynamicLink.Builder createDynamicLink();

    public abstract Task<PendingDynamicLinkData> getDynamicLink(Intent intent);

    public abstract Task<PendingDynamicLinkData> getDynamicLink(Uri uri);
}
