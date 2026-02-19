package com.google.android.gms.fido.fido2.api.common;

import com.google.android.gms.internal.zzbgl;

public abstract class AuthenticatorResponse extends zzbgl {
    public abstract byte[] getClientDataJSON();

    public abstract byte[] serializeToBytes();
}
