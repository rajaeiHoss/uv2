package com.google.android.gms.internal;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.GetTokenResult;

final class zzedc implements OnSuccessListener<GetTokenResult> {
    private /* synthetic */ zzege zzmxp;

    zzedc(zzeda zzeda, zzege zzege) {
        this.zzmxp = zzege;
    }

    public final void onSuccess(GetTokenResult getTokenResult) {
        this.zzmxp.zzpr(getTokenResult.getToken());
    }
}
