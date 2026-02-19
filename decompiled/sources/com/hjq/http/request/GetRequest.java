package com.hjq.http.request;

import androidx.lifecycle.LifecycleOwner;
import com.hjq.http.model.HttpMethod;

public final class GetRequest extends UrlRequest<GetRequest> {
    public GetRequest(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    public String getRequestMethod() {
        return HttpMethod.GET.toString();
    }
}
