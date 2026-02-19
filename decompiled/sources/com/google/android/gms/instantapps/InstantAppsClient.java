package com.google.android.gms.instantapps;

import android.app.Activity;
import android.content.Context;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.internal.zzbj;
import com.google.android.gms.internal.zzcej;
import com.google.android.gms.tasks.Task;

public class InstantAppsClient extends GoogleApi<Api.ApiOptions.NoOptions> {
    private final zzcej zziog = new zzcej();

    public InstantAppsClient(Activity activity) {
        super(activity, InstantApps.API, null, GoogleApi.zza.zzfsr);
    }

    public InstantAppsClient(Context context) {
        super(context, InstantApps.API, null, GoogleApi.zza.zzfsr);
    }

    public Task<ParcelFileDescriptor> getInstantAppData() {
        return zzbj.zza(this.zziog.zzh(zzahw()), zzg.zzgui);
    }

    public final Task<LaunchData> getInstantAppLaunchData(String str) {
        return zzbj.zza(this.zziog.zza(zzahw(), str), zzf.zzgui);
    }
}
