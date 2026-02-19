package com.google.android.gms.fido.fido2.api.common;

import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgq;

public abstract class RequestOptions extends zzbgl {
    public abstract byte[] getChallenge();

    public abstract Integer getRequestId();

    public abstract Double getTimeoutSeconds();

    public abstract TokenBindingIdValue getTokenBindingIdValue();

    public byte[] serializeToBytes() {
        return zzbgq.zza(this);
    }
}
