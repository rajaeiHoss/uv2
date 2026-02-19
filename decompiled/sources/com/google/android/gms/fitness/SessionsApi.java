package com.google.android.gms.fitness;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.fitness.result.SessionReadResult;
import com.google.android.gms.fitness.result.SessionStopResult;
import com.google.android.gms.internal.zzbgq;

public interface SessionsApi {

    public static class ViewIntentBuilder {
        private final Context zzaiq;
        private String zzhhn;
        private Session zzhhs;
        private boolean zzhht = false;

        public ViewIntentBuilder(Context context) {
            this.zzaiq = context;
        }

        public Intent build() {
            ResolveInfo resolveActivity;
            zzbq.zza(this.zzhhs != null, (Object) "Session must be set");
            Intent intent2 = new Intent(Fitness.ACTION_VIEW);
            intent2.setType(Session.getMimeType(this.zzhhs.getActivity()));
            zzbgq.zza(this.zzhhs, intent2, Session.EXTRA_SESSION);
            if (!this.zzhht) {
                this.zzhhn = this.zzhhs.getAppPackageName();
            }
            if (this.zzhhn == null || (resolveActivity = this.zzaiq.getPackageManager().resolveActivity(intent2, 0)) == null) {
                return intent2;
            }
            Intent intent = new Intent(intent2).setPackage(this.zzhhn);
            intent.setComponent(new ComponentName(this.zzhhn, resolveActivity.activityInfo.name));
            return intent;
        }

        public ViewIntentBuilder setPreferredApplication(String str) {
            this.zzhhn = str;
            this.zzhht = true;
            return this;
        }

        public ViewIntentBuilder setSession(Session session) {
            this.zzhhs = session;
            return this;
        }
    }

    PendingResult<Status> insertSession(GoogleApiClient googleApiClient, SessionInsertRequest sessionInsertRequest);

    PendingResult<SessionReadResult> readSession(GoogleApiClient googleApiClient, SessionReadRequest sessionReadRequest);

    PendingResult<Status> registerForSessions(GoogleApiClient googleApiClient, PendingIntent pendingIntent);

    PendingResult<Status> startSession(GoogleApiClient googleApiClient, Session session);

    PendingResult<SessionStopResult> stopSession(GoogleApiClient googleApiClient, String str);

    PendingResult<Status> unregisterForSessions(GoogleApiClient googleApiClient, PendingIntent pendingIntent);
}
