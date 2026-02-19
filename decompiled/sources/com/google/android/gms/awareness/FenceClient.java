package com.google.android.gms.awareness;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.awareness.fence.FenceQueryRequest;
import com.google.android.gms.awareness.fence.FenceQueryResponse;
import com.google.android.gms.awareness.fence.FenceUpdateRequest;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.zzda;
import com.google.android.gms.common.api.internal.zzg;
import com.google.android.gms.common.internal.zzbj;
import com.google.android.gms.tasks.Task;

public class FenceClient extends GoogleApi<AwarenessOptions> {
    FenceClient(Activity activity, AwarenessOptions awarenessOptions) {
        super(activity, Awareness.API, null, (zzda) new zzg());
    }

    FenceClient(Context context, AwarenessOptions awarenessOptions) {
        super(context, Awareness.API, null, (zzda) new zzg());
    }

    public Task<FenceQueryResponse> queryFences(FenceQueryRequest fenceQueryRequest) {
        return zzbj.zza(Awareness.FenceApi.queryFences(zzahw(), fenceQueryRequest), new FenceQueryResponse());
    }

    public Task<Void> updateFences(FenceUpdateRequest fenceUpdateRequest) {
        return zzbj.zzb(Awareness.FenceApi.updateFences(zzahw(), fenceUpdateRequest));
    }
}
