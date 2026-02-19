package com.google.android.gms.nearby.messages.internal;

import android.app.PendingIntent;
import android.content.Intent;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzcvb;
import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.nearby.messages.MessageListener;
import com.google.android.gms.nearby.messages.Messages;
import com.google.android.gms.nearby.messages.MessagesOptions;
import com.google.android.gms.nearby.messages.PublishOptions;
import com.google.android.gms.nearby.messages.StatusCallback;
import com.google.android.gms.nearby.messages.SubscribeOptions;

public final class zzbi implements Messages {
    public static final Api.zzf<zzah> zzegu = new Api.zzf<>();
    public static final Api.zza<zzah, MessagesOptions> zzegv = new zzbj();
    public static final zzbi zzkdv = new zzbi();

    private zzbi() {
    }

    public final PendingResult<Status> getPermissionStatus(GoogleApiClient googleApiClient) {
        return googleApiClient.zze(new zzbr(this, googleApiClient));
    }

    public final void handleIntent(Intent intent, MessageListener messageListener) {
        zzcvb.zza(intent, messageListener);
    }

    public final PendingResult<Status> publish(GoogleApiClient googleApiClient, Message message) {
        return publish(googleApiClient, message, PublishOptions.DEFAULT);
    }

    public final PendingResult<Status> publish(GoogleApiClient googleApiClient, Message message, PublishOptions publishOptions) {
        zzbq.checkNotNull(message);
        zzbq.checkNotNull(publishOptions);
        zzbt zzbt = null;
        zzci zzt = publishOptions.getCallback() == null ? null : googleApiClient.zzt(publishOptions.getCallback());
        if (zzt != null) {
            zzbt = new zzbt(zzt);
        }
        return googleApiClient.zze(new zzbl(this, googleApiClient, message, zzbt, publishOptions));
    }

    public final PendingResult<Status> registerStatusCallback(GoogleApiClient googleApiClient, StatusCallback statusCallback) {
        zzbq.checkNotNull(statusCallback);
        return googleApiClient.zze(new zzbs(this, googleApiClient, googleApiClient.zzt(statusCallback)));
    }

    public final PendingResult<Status> subscribe(GoogleApiClient googleApiClient, PendingIntent pendingIntent) {
        return subscribe(googleApiClient, pendingIntent, SubscribeOptions.DEFAULT);
    }

    public final PendingResult<Status> subscribe(GoogleApiClient googleApiClient, PendingIntent pendingIntent, SubscribeOptions subscribeOptions) {
        zzbq.checkNotNull(pendingIntent);
        zzbq.checkNotNull(subscribeOptions);
        zzbw zzbw = null;
        zzci zzt = subscribeOptions.getCallback() == null ? null : googleApiClient.zzt(subscribeOptions.getCallback());
        if (zzt != null) {
            zzbw = new zzbw(zzt);
        }
        return googleApiClient.zze(new zzbo(this, googleApiClient, pendingIntent, zzbw, subscribeOptions));
    }

    public final PendingResult<Status> subscribe(GoogleApiClient googleApiClient, MessageListener messageListener) {
        return subscribe(googleApiClient, messageListener, SubscribeOptions.DEFAULT);
    }

    public final PendingResult<Status> subscribe(GoogleApiClient googleApiClient, MessageListener messageListener, SubscribeOptions subscribeOptions) {
        zzbq.checkNotNull(messageListener);
        zzbq.checkNotNull(subscribeOptions);
        zzbq.checkArgument(subscribeOptions.getStrategy().zzbdu() == 0, "Strategy.setBackgroundScanMode() is only supported by background subscribe (the version which takes a PendingIntent).");
        zzci zzt = googleApiClient.zzt(messageListener);
        zzbw zzbw = null;
        zzci zzt2 = subscribeOptions.getCallback() == null ? null : googleApiClient.zzt(subscribeOptions.getCallback());
        if (zzt2 != null) {
            zzbw = new zzbw(zzt2);
        }
        return googleApiClient.zze(new zzbn(this, googleApiClient, zzt, zzbw, subscribeOptions));
    }

    public final PendingResult<Status> unpublish(GoogleApiClient googleApiClient, Message message) {
        zzbq.checkNotNull(message);
        return googleApiClient.zze(new zzbm(this, googleApiClient, message));
    }

    public final PendingResult<Status> unregisterStatusCallback(GoogleApiClient googleApiClient, StatusCallback statusCallback) {
        zzbq.checkNotNull(statusCallback);
        return googleApiClient.zze(new zzbk(this, googleApiClient, googleApiClient.zzt(statusCallback)));
    }

    public final PendingResult<Status> unsubscribe(GoogleApiClient googleApiClient, PendingIntent pendingIntent) {
        zzbq.checkNotNull(pendingIntent);
        return googleApiClient.zze(new com.google.android.gms.nearby.messages.internal.zzbq(this, googleApiClient, pendingIntent));
    }

    public final PendingResult<Status> unsubscribe(GoogleApiClient googleApiClient, MessageListener messageListener) {
        zzbq.checkNotNull(messageListener);
        return googleApiClient.zze(new zzbp(this, googleApiClient, googleApiClient.zzt(messageListener)));
    }
}
