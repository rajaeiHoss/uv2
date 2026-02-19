package com.hjq.http.request;

import androidx.lifecycle.LifecycleOwner;
import com.hjq.http.model.HttpMethod;

public final class OptionsRequest extends UrlRequest<OptionsRequest> {
    public OptionsRequest(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    public String getRequestMethod() {
        return HttpMethod.OPTIONS.toString();
    }
}
