package com.google.android.gms.awareness.snapshot;

import com.google.android.gms.awareness.state.HeadphoneState;
import com.google.android.gms.common.api.Response;

public class HeadphoneStateResponse extends Response<HeadphoneStateResult> {
    public HeadphoneState getHeadphoneState() {
        return ((HeadphoneStateResult) getResult()).getHeadphoneState();
    }
}
