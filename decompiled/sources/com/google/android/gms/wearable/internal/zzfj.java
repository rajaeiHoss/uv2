package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import java.util.List;

public final class zzfj implements NodeApi.GetConnectedNodesResult {
    private final Status mStatus;
    private final List<Node> zzlva;

    public zzfj(Status status, List<Node> list) {
        this.mStatus = status;
        this.zzlva = list;
    }

    public final List<Node> getNodes() {
        return this.zzlva;
    }

    public final Status getStatus() {
        return this.mStatus;
    }
}
