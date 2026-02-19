package com.google.android.gms.awareness;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.zzaze;
import com.google.android.gms.internal.zzbjv;
import com.google.android.gms.internal.zzblg;

public final class Awareness {
    @Deprecated
    public static final Api<AwarenessOptions> API;
    @Deprecated
    public static final FenceApi FenceApi = new zzbjv();
    @Deprecated
    public static final SnapshotApi SnapshotApi = new zzaze();
    private static Api.zzf<zzblg> zzegu = new Api.zzf<>();
    private static final Api.zza<zzblg, AwarenessOptions> zzegv;

    static {
        zza zza = new zza();
        zzegv = zza;
        API = new Api<>("ContextManager.API", zza, zzegu);
    }

    private Awareness() {
    }

    public static FenceClient getFenceClient(Activity activity) {
        return new FenceClient(activity, (AwarenessOptions) null);
    }

    public static FenceClient getFenceClient(Context context) {
        return new FenceClient(context, (AwarenessOptions) null);
    }

    public static SnapshotClient getSnapshotClient(Activity activity) {
        return new SnapshotClient(activity, (AwarenessOptions) null);
    }

    public static SnapshotClient getSnapshotClient(Context context) {
        return new SnapshotClient(context, (AwarenessOptions) null);
    }
}
