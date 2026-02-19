package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.wearable.Channel;
import java.io.IOException;
import java.io.InputStream;

final class zzbg implements Channel.GetInputStreamResult {
    private final Status mStatus;
    private final InputStream zzltm;

    zzbg(Status status, InputStream inputStream) {
        this.mStatus = (Status) zzbq.checkNotNull(status);
        this.zzltm = inputStream;
    }

    public final InputStream getInputStream() {
        return this.zzltm;
    }

    public final Status getStatus() {
        return this.mStatus;
    }

    public final void release() {
        InputStream inputStream = this.zzltm;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
        }
    }
}
