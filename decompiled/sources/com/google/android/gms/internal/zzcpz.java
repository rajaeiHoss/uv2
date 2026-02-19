package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzcq;
import com.google.android.gms.common.api.internal.zzdo;
import com.google.android.gms.nearby.connection.AdvertisingOptions;
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;
import com.google.android.gms.nearby.connection.ConnectionsClient;
import com.google.android.gms.nearby.connection.DiscoveryOptions;
import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback;
import com.google.android.gms.nearby.connection.Payload;
import com.google.android.gms.nearby.connection.PayloadCallback;
import com.google.android.gms.tasks.Task;
import java.util.List;

public final class zzcpz extends ConnectionsClient {
    private static final Api<Api.ApiOptions.NoOptions> CONNECTIONS_API;
    private static final Api.zzf<zzcov> zzegu;
    private static final Api.zza<zzcov, Api.ApiOptions.NoOptions> zzegv;
    private final zzcon zzjye = zzcon.zzbdd();

    static {
        Api.zzf<zzcov> zzf = new Api.zzf<>();
        zzegu = zzf;
        zzcqi zzcqi = new zzcqi();
        zzegv = zzcqi;
        CONNECTIONS_API = new Api<>("Nearby.CONNECTIONS_API", zzcqi, zzf);
    }

    public zzcpz(Activity activity) {
        super(activity, CONNECTIONS_API, GoogleApi.zza.zzfsr);
    }

    public zzcpz(Context context) {
        super(context, CONNECTIONS_API, GoogleApi.zza.zzfsr);
    }

    private final Task<Void> zza(zzcqs zzcqs) {
        return zzb(new zzcqr(this, zzcqs));
    }

    private final Task<Void> zza(zzcqv zzcqv) {
        return zzb(new zzcqj(this, zzcqv));
    }

    /* access modifiers changed from: private */
    public final void zzkv(String str) {
        zzci<String> zza = this.zzjye.zza((GoogleApi) this, str, "connection");
        this.zzjye.zzb((GoogleApi) this, (zzcq) new zzcqp(this, zza), (zzdo) new zzcqq(this, zza.zzakx()));
    }

    /* access modifiers changed from: private */
    public final void zzkw(String str) {
        zzcon zzcon = this.zzjye;
        zzcon.zzb(this, zzcon.zzb((GoogleApi) this, str, "connection"));
    }

    public final Task<Void> acceptConnection(String str, PayloadCallback payloadCallback) {
        return zza((zzcqs) new zzcqb(str, zza(payloadCallback, PayloadCallback.class.getName())));
    }

    public final Task<Void> cancelPayload(long j) {
        return zza((zzcqs) new zzcqf(j));
    }

    public final void disconnectFromEndpoint(String str) {
        zza((zzcqv) new zzcqg(str));
        zzkw(str);
    }

    public final Task<Void> rejectConnection(String str) {
        return zza((zzcqs) new zzcqc(str));
    }

    public final Task<Void> requestConnection(String str, String str2, ConnectionLifecycleCallback connectionLifecycleCallback) {
        zzci zza = zza(new zzcqt(this, connectionLifecycleCallback), ConnectionLifecycleCallback.class.getName());
        zzkv(str2);
        return zza((zzcqs) new zzcqa(str, str2, zza)).addOnFailureListener(new zzcqo(this, str2));
    }

    public final Task<Void> sendPayload(String str, Payload payload) {
        return zza((zzcqs) new zzcqd(str, payload));
    }

    public final Task<Void> sendPayload(List<String> list, Payload payload) {
        return zza((zzcqs) new zzcqe(list, payload));
    }

    public final Task<Void> startAdvertising(String str, String str2, ConnectionLifecycleCallback connectionLifecycleCallback, AdvertisingOptions advertisingOptions) {
        zzci zza = zza(new zzcqt(this, connectionLifecycleCallback), ConnectionLifecycleCallback.class.getName());
        zzci zza2 = this.zzjye.zza((GoogleApi) this, new Object(), "advertising");
        return this.zzjye.zzb((GoogleApi) this, (zzcq) new zzcqk(this, zza2, str, str2, zza, advertisingOptions), (zzdo) new zzcql(this, zza2.zzakx()));
    }

    public final Task<Void> startDiscovery(String str, EndpointDiscoveryCallback endpointDiscoveryCallback, DiscoveryOptions discoveryOptions) {
        zzci zza = this.zzjye.zza((GoogleApi) this, endpointDiscoveryCallback, "discovery");
        return this.zzjye.zzb((GoogleApi) this, (zzcq) new zzcqm(this, zza, str, zza, discoveryOptions), (zzdo) new zzcqn(this, zza.zzakx()));
    }

    public final void stopAdvertising() {
        this.zzjye.zza((GoogleApi) this, "advertising");
    }

    public final void stopAllEndpoints() {
        stopAdvertising();
        stopDiscovery();
        zza(zzcqh.zzjyk);
        this.zzjye.zza((GoogleApi) this, "connection");
    }

    public final void stopDiscovery() {
        this.zzjye.zza((GoogleApi) this, "discovery");
    }
}
