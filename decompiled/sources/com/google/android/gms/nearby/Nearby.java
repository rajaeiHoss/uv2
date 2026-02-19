package com.google.android.gms.nearby;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzcoh;
import com.google.android.gms.internal.zzcoj;
import com.google.android.gms.internal.zzcpz;
import com.google.android.gms.internal.zzcqw;
import com.google.android.gms.nearby.connection.Connections;
import com.google.android.gms.nearby.connection.ConnectionsClient;
import com.google.android.gms.nearby.messages.Messages;
import com.google.android.gms.nearby.messages.MessagesClient;
import com.google.android.gms.nearby.messages.MessagesOptions;
import com.google.android.gms.nearby.messages.internal.zzak;
import com.google.android.gms.nearby.messages.internal.zzbi;
import com.google.android.gms.nearby.messages.internal.zzby;
import com.google.android.gms.nearby.messages.zzd;

public final class Nearby {
    @Deprecated
    public static final Api<Api.ApiOptions.NoOptions> CONNECTIONS_API = new Api<>("Nearby.CONNECTIONS_API", zzcqw.zzegv, zzcqw.zzegu);
    @Deprecated
    public static final Connections Connections = new zzcqw();
    @Deprecated
    public static final Api<MessagesOptions> MESSAGES_API = new Api<>("Nearby.MESSAGES_API", zzbi.zzegv, zzbi.zzegu);
    @Deprecated
    public static final Messages Messages = zzbi.zzkdv;
    private static zzd zzjvs = new zzby();
    private static Api<Api.ApiOptions.NoOptions> zzjvt = new Api<>("Nearby.BOOTSTRAP_API", zzcoj.zzegv, zzcoj.zzegu);
    private static zzcoh zzjvu = new zzcoj();

    private Nearby() {
    }

    public static final ConnectionsClient getConnectionsClient(Activity activity) {
        zzbq.checkNotNull(activity, "Activity must not be null");
        return new zzcpz(activity);
    }

    public static final ConnectionsClient getConnectionsClient(Context context) {
        zzbq.checkNotNull(context, "Context must not be null");
        return new zzcpz(context);
    }

    public static final MessagesClient getMessagesClient(Activity activity) {
        zzbq.checkNotNull(activity, "Activity must not be null");
        return new zzak(activity, (MessagesOptions) null);
    }

    public static final MessagesClient getMessagesClient(Activity activity, MessagesOptions messagesOptions) {
        zzbq.checkNotNull(activity, "Activity must not be null");
        zzbq.checkNotNull(messagesOptions, "Options must not be null");
        return new zzak(activity, messagesOptions);
    }

    public static final MessagesClient getMessagesClient(Context context) {
        zzbq.checkNotNull(context, "Context must not be null");
        return new zzak(context, (MessagesOptions) null);
    }

    public static final MessagesClient getMessagesClient(Context context, MessagesOptions messagesOptions) {
        zzbq.checkNotNull(context, "Context must not be null");
        zzbq.checkNotNull(messagesOptions, "Options must not be null");
        return new zzak(context, messagesOptions);
    }
}
