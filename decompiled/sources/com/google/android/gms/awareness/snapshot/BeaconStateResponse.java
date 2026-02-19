package com.google.android.gms.awareness.snapshot;

import com.google.android.gms.awareness.state.BeaconState;
import com.google.android.gms.common.api.Response;

public class BeaconStateResponse extends Response<BeaconStateResult> {
    public BeaconState getBeaconState() {
        return ((BeaconStateResult) getResult()).getBeaconState();
    }
}
