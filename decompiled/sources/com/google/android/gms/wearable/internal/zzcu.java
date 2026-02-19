package com.google.android.gms.wearable.internal;

import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataClient;
import java.io.InputStream;

final class zzcu extends DataClient.GetFdForAssetResponse implements Releasable {
    private final DataApi.GetFdForAssetResult zzlud;

    zzcu(DataApi.GetFdForAssetResult getFdForAssetResult) {
        this.zzlud = getFdForAssetResult;
    }

    public final ParcelFileDescriptor getFdForAsset() {
        return this.zzlud.getFd();
    }

    public final InputStream getInputStream() {
        return this.zzlud.getInputStream();
    }

    public final void release() {
        this.zzlud.release();
    }
}
