package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.drive.DriveApi;

final /* synthetic */ class zzbpe implements zzbo {
    static final zzbo zzgui = new zzbpe();

    private zzbpe() {
    }

    public final Object zzb(Result result) {
        return ((DriveApi.MetadataBufferResult) result).getMetadataBuffer();
    }
}
