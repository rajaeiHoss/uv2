package com.google.android.gms.nearby.messages.internal;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzck;
import com.google.android.gms.common.api.internal.zzcm;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzcvb;
import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.nearby.messages.MessageListener;
import com.google.android.gms.nearby.messages.MessagesClient;
import com.google.android.gms.nearby.messages.MessagesOptions;
import com.google.android.gms.nearby.messages.PublishOptions;
import com.google.android.gms.nearby.messages.StatusCallback;
import com.google.android.gms.nearby.messages.SubscribeOptions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzak extends MessagesClient {
    private static final Api<MessagesOptions> MESSAGES_API;
    private static final Api.zzf<zzah> zzegu;
    private static final Api.zza<zzah, MessagesOptions> zzegv;
    private final int zzkcv;

    static {
        Api.zzf<zzah> zzf = new Api.zzf<>();
        zzegu = zzf;
        zzau zzau = new zzau();
        zzegv = zzau;
        MESSAGES_API = new Api<>("Nearby.MESSAGES_API", zzau, zzf);
    }

    public zzak(Activity activity, MessagesOptions messagesOptions) {
        super(activity, MESSAGES_API, messagesOptions, GoogleApi.zza.zzfsr);
        this.zzkcv = 1;
        activity.getApplication().registerActivityLifecycleCallbacks(new zzbc(activity, this, (zzau) null));
    }

    public zzak(Context context, MessagesOptions messagesOptions) {
        super(context, MESSAGES_API, messagesOptions, GoogleApi.zza.zzfsr);
        this.zzkcv = zzah.zzee(context);
    }

    private final <T> Task<Void> zza(zzci<T> zzci, zzbd zzbd, zzbd zzbd2) {
        return zza(new zzaz(this, zzci, zzbd), new zzba(this, zzci.zzakx(), zzbd2));
    }

    private final Task<Void> zza(zzbd zzbd) {
        return zzb(new zzbb(this, zzbd));
    }

    private final <T> Task<Void> zzah(T t) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        zza((zzck<?>) zzcm.zzb(t, t.getClass().getName())).addOnCompleteListener(new zzay(this, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    private final <T> zzci<T> zzai(T t) {
        if (t == null) {
            return null;
        }
        return zza(t, t.getClass().getName());
    }

    /* access modifiers changed from: private */
    public final <T> zzci<zzn<Status>> zzd(TaskCompletionSource<T> taskCompletionSource) {
        return zza(new zzax(this, taskCompletionSource), Status.class.getName());
    }

    /* access modifiers changed from: private */
    public final void zzet(int i) {
        zza(new zzat(1));
    }

    public final void handleIntent(Intent intent, MessageListener messageListener) {
        zzcvb.zza(intent, messageListener);
    }

    public final Task<Void> publish(Message message) {
        return publish(message, PublishOptions.DEFAULT);
    }

    public final Task<Void> publish(Message message, PublishOptions publishOptions) {
        zzbq.checkNotNull(message);
        zzbq.checkNotNull(publishOptions);
        zzci zzai = zzai(message);
        return zza(zzai, (zzbd) new zzal(this, message, new zzav(this, zzai(publishOptions.getCallback()), zzai), publishOptions), (zzbd) new zzam(message));
    }

    public final Task<Void> registerStatusCallback(StatusCallback statusCallback) {
        zzbq.checkNotNull(statusCallback);
        zzci zzai = zzai(statusCallback);
        return zza(zzai, (zzbd) new zzar(zzai), (zzbd) new zzas(zzai));
    }

    public final Task<Void> subscribe(PendingIntent pendingIntent) {
        return subscribe(pendingIntent, SubscribeOptions.DEFAULT);
    }

    public final Task<Void> subscribe(PendingIntent pendingIntent, SubscribeOptions subscribeOptions) {
        zzbq.checkNotNull(pendingIntent);
        zzbq.checkNotNull(subscribeOptions);
        zzci zzai = zzai(subscribeOptions.getCallback());
        return zza(new zzap(this, pendingIntent, zzai == null ? null : new zzbg(zzai), subscribeOptions));
    }

    public final Task<Void> subscribe(MessageListener messageListener) {
        return subscribe(messageListener, SubscribeOptions.DEFAULT);
    }

    public final Task<Void> subscribe(MessageListener messageListener, SubscribeOptions subscribeOptions) {
        zzbq.checkNotNull(messageListener);
        zzbq.checkNotNull(subscribeOptions);
        zzbq.checkArgument(subscribeOptions.getStrategy().zzbdu() == 0, "Strategy.setBackgroundScanMode() is only supported by background subscribe (the version which takes a PendingIntent).");
        zzci zzai = zzai(messageListener);
        return zza(zzai, (zzbd) new zzan(this, zzai, new zzaw(this, zzai(subscribeOptions.getCallback()), zzai), subscribeOptions), (zzbd) new zzao(zzai));
    }

    public final Task<Void> unpublish(Message message) {
        zzbq.checkNotNull(message);
        return zzah(message);
    }

    public final Task<Void> unregisterStatusCallback(StatusCallback statusCallback) {
        zzbq.checkNotNull(statusCallback);
        return zzah(statusCallback);
    }

    public final Task<Void> unsubscribe(PendingIntent pendingIntent) {
        zzbq.checkNotNull(pendingIntent);
        return zza(new zzaq(pendingIntent));
    }

    public final Task<Void> unsubscribe(MessageListener messageListener) {
        zzbq.checkNotNull(messageListener);
        return zzah(messageListener);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(PendingIntent pendingIntent, zzbg zzbg, SubscribeOptions subscribeOptions, zzah zzah, zzci zzci) throws RemoteException {
        zzah.zza((zzci<zzn<Status>>) zzci, pendingIntent, (zzab) zzbg, subscribeOptions, this.zzkcv);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(zzci zzci, zzbg zzbg, SubscribeOptions subscribeOptions, zzah zzah, zzci zzci2) throws RemoteException {
        zzah.zza(zzci2, zzci, zzbg, subscribeOptions, (byte[]) null, this.zzkcv);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(Message message, zzbe zzbe, PublishOptions publishOptions, zzah zzah, zzci zzci) throws RemoteException {
        zzah.zza((zzci<zzn<Status>>) zzci, zzaf.zza(message), (zzv) zzbe, publishOptions, this.zzkcv);
    }
}
