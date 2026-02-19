package com.google.android.gms.location;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.zzda;
import com.google.android.gms.common.api.internal.zzg;
import com.google.android.gms.common.internal.zzbj;
import com.google.android.gms.tasks.Task;

public class ActivityRecognitionClient extends GoogleApi<Api.ApiOptions.NoOptions> {
    public ActivityRecognitionClient(Activity activity) {
        super(activity, LocationServices.API, null, (zzda) new zzg());
    }

    public ActivityRecognitionClient(Context context) {
        super(context, LocationServices.API, null, (zzda) new zzg());
    }

    public Task<Void> removeActivityTransitionUpdates(PendingIntent pendingIntent) {
        return zzbj.zzb(ActivityRecognition.ActivityRecognitionApi.zza(zzahw(), pendingIntent));
    }

    public Task<Void> removeActivityUpdates(PendingIntent pendingIntent) {
        return zzbj.zzb(ActivityRecognition.ActivityRecognitionApi.removeActivityUpdates(zzahw(), pendingIntent));
    }

    public Task<Void> requestActivityTransitionUpdates(ActivityTransitionRequest activityTransitionRequest, PendingIntent pendingIntent) {
        return zzbj.zzb(ActivityRecognition.ActivityRecognitionApi.zza(zzahw(), activityTransitionRequest, pendingIntent));
    }

    public Task<Void> requestActivityUpdates(long j, PendingIntent pendingIntent) {
        return zzbj.zzb(ActivityRecognition.ActivityRecognitionApi.requestActivityUpdates(zzahw(), j, pendingIntent));
    }
}
