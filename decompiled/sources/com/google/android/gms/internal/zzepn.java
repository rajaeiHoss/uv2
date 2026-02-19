package com.google.android.gms.internal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.google.firebase.dynamiclinks.ShortDynamicLink;

public final class zzepn extends FirebaseDynamicLinks {
    private final GoogleApi<Api.ApiOptions.NoOptions> zzgba;

    public zzepn(Context context) {
        this((GoogleApi<Api.ApiOptions.NoOptions>) new zzepk(context));
    }

    private zzepn(GoogleApi<Api.ApiOptions.NoOptions> googleApi) {
        this.zzgba = googleApi;
    }

    public static void zzag(Bundle bundle) {
        Uri uri = (Uri) bundle.getParcelable("dynamicLink");
        if (TextUtils.isEmpty(bundle.getString("domain")) && uri == null) {
            throw new IllegalArgumentException("FDL domain is missing. Set with setDynamicLinkDomain().");
        }
    }

    private final void zzcdx() {
        try {
            AppMeasurement.getInstance(this.zzgba.getApplicationContext());
        } catch (NoClassDefFoundError unused) {
            Log.w("FDL", "FDL logging failed. Add a dependency for Firebase Analytics to your app to enable logging of Dynamic Link events.");
        }
    }

    public final DynamicLink.Builder createDynamicLink() {
        return new DynamicLink.Builder(this);
    }

    public final Task<PendingDynamicLinkData> getDynamicLink(Intent intent) {
        zzcdx();
        GoogleApi<Api.ApiOptions.NoOptions> googleApi = this.zzgba;
        Task<PendingDynamicLinkData> task = googleApi.zzb(new zzeps(googleApi.getApplicationContext(), intent.getDataString()));
        zzepi zzepiData = (zzepi) zzbgq.zza(intent, "com.google.firebase.dynamiclinks.DYNAMIC_LINK_DATA", com.google.android.gms.internal.zzepi.CREATOR);
        PendingDynamicLinkData pendingDynamicLinkData = zzepiData != null ? new PendingDynamicLinkData(zzepiData) : null;
        return pendingDynamicLinkData != null ? Tasks.forResult(pendingDynamicLinkData) : task;
    }

    public final Task<PendingDynamicLinkData> getDynamicLink(Uri uri) {
        zzcdx();
        GoogleApi<Api.ApiOptions.NoOptions> googleApi = this.zzgba;
        return googleApi.zzb(new zzeps(googleApi.getApplicationContext(), uri.toString()));
    }

    public final Task<ShortDynamicLink> zzaf(Bundle bundle) {
        zzag(bundle);
        return this.zzgba.zzb(new zzepq(bundle));
    }
}
