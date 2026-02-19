package com.google.firebase.internal;

import android.content.Context;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public final class zzb {
    private static final AtomicReference<zzb> zzmmz = new AtomicReference<>();

    private zzb(Context context) {
    }

    public static zzb zzclx() {
        return zzmmz.get();
    }

    public static Set<String> zzcly() {
        return Collections.emptySet();
    }

    public static zzb zzfb(Context context) {
        AtomicReference<zzb> atomicReference = zzmmz;
        atomicReference.compareAndSet(null, new zzb(context));
        return atomicReference.get();
    }

    public static void zzg(FirebaseApp firebaseApp) {
    }

    public static FirebaseOptions zzrw(String str) {
        return null;
    }
}
