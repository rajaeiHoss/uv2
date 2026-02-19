package com.google.android.gms.awareness.fence;

import com.google.android.gms.common.api.Response;

public class FenceQueryResponse extends Response<FenceQueryResult> {
    public FenceStateMap getFenceStateMap() {
        return ((FenceQueryResult) getResult()).getFenceStateMap();
    }
}
