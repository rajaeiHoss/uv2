package com.hjq.http.request;

import androidx.lifecycle.LifecycleOwner;
import com.hjq.http.model.HttpMethod;

public final class TraceRequest extends UrlRequest<TraceRequest> {
    public TraceRequest(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    public String getRequestMethod() {
        return HttpMethod.TRACE.toString();
    }
}
