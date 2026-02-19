package com.google.android.gms.fido.u2f.api.common;

import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.internal.zzbgl;
import org.json.JSONObject;

public abstract class ResponseData extends zzbgl implements ReflectedParcelable {
    public abstract JSONObject toJsonObject();
}
