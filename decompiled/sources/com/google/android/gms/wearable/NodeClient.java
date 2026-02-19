package com.google.android.gms.wearable;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wearable.Wearable;
import java.util.List;

public abstract class NodeClient extends GoogleApi<Wearable.WearableOptions> {
    public NodeClient(Activity activity, GoogleApi.zza zza) {
        super(activity, Wearable.API, null, zza);
    }

    public NodeClient(Context context, GoogleApi.zza zza) {
        super(context, Wearable.API, null, zza);
    }

    public abstract Task<List<Node>> getConnectedNodes();

    public abstract Task<Node> getLocalNode();
}
