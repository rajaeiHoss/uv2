package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.drive.DriveApi;

final /* synthetic */ class zzbnw implements zzbo {
    static final zzbo zzgui = new zzbnw();

    private zzbnw() {
    }

    public final Object zzb(Result result) {
        return ((DriveApi.DriveIdResult) result).getDriveId();
    }
}
