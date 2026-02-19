package com.hjq.http.request;

import androidx.lifecycle.LifecycleOwner;
import com.hjq.http.model.HttpMethod;

public final class HeadRequest extends UrlRequest<HeadRequest> {
    public HeadRequest(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    public String getRequestMethod() {
        return HttpMethod.HEAD.toString();
    }
}
