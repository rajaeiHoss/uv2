package com.google.android.gms.common;

import com.google.android.gms.common.api.internal.zzh;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.util.Map;

final class zze implements Continuation<Map<zzh<?>, String>, Void> {
    zze(GoogleApiAvailability googleApiAvailability) {
    }

    public final Void then(Task<Map<zzh<?>, String>> task) throws Exception {
        task.getResult();
        return null;
    }
}
