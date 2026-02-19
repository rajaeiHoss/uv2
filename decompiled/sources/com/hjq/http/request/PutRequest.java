package com.hjq.http.request;

import androidx.lifecycle.LifecycleOwner;
import com.hjq.http.model.HttpMethod;

public final class PutRequest extends BodyRequest<PutRequest> {
    public PutRequest(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    public String getRequestMethod() {
        return HttpMethod.PUT.toString();
    }
}
