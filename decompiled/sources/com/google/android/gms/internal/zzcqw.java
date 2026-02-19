package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.nearby.connection.AdvertisingOptions;
import com.google.android.gms.nearby.connection.AppMetadata;
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;
import com.google.android.gms.nearby.connection.Connections;
import com.google.android.gms.nearby.connection.DiscoveryOptions;
import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback;
import com.google.android.gms.nearby.connection.Payload;
import com.google.android.gms.nearby.connection.PayloadCallback;
import java.util.List;

public final class zzcqw implements Connections {
    public static final Api.zzf<zzcov> zzegu = new Api.zzf<>();
    public static final Api.zza<zzcov, Api.ApiOptions.NoOptions> zzegv = new zzcqx();

    public final PendingResult<Status> acceptConnection(GoogleApiClient googleApiClient, String str, PayloadCallback payloadCallback) {
        return googleApiClient.zze(new zzcrp(this, googleApiClient, str, googleApiClient.zzt(payloadCallback)));
    }

    @Deprecated
    public final PendingResult<Status> acceptConnectionRequest(GoogleApiClient googleApiClient, String str, byte[] bArr, Connections.MessageListener messageListener) {
        return googleApiClient.zze(new zzcrg(this, googleApiClient, str, bArr, googleApiClient.zzt(messageListener)));
    }

    public final PendingResult<Status> cancelPayload(GoogleApiClient googleApiClient, long j) {
        return googleApiClient.zze(new zzcra(this, googleApiClient, j));
    }

    public final void disconnectFromEndpoint(GoogleApiClient googleApiClient, String str) {
        googleApiClient.zze(new zzcrb(this, googleApiClient, str));
    }

    public final PendingResult<Status> rejectConnection(GoogleApiClient googleApiClient, String str) {
        return googleApiClient.zze(new zzcrq(this, googleApiClient, str));
    }

    @Deprecated
    public final PendingResult<Status> rejectConnectionRequest(GoogleApiClient googleApiClient, String str) {
        return googleApiClient.zze(new zzcrh(this, googleApiClient, str));
    }

    public final PendingResult<Status> requestConnection(GoogleApiClient googleApiClient, String str, String str2, ConnectionLifecycleCallback connectionLifecycleCallback) {
        return googleApiClient.zze(new zzcro(this, googleApiClient, str, str2, googleApiClient.zzt(connectionLifecycleCallback)));
    }

    @Deprecated
    public final PendingResult<Status> sendConnectionRequest(GoogleApiClient googleApiClient, String str, String str2, byte[] bArr, Connections.ConnectionResponseCallback connectionResponseCallback, Connections.MessageListener messageListener) {
        return googleApiClient.zze(new zzcrf(this, googleApiClient, str, str2, bArr, googleApiClient.zzt(connectionResponseCallback), googleApiClient.zzt(messageListener)));
    }

    public final PendingResult<Status> sendPayload(GoogleApiClient googleApiClient, String str, Payload payload) {
        return googleApiClient.zze(new zzcqy(this, googleApiClient, str, payload));
    }

    public final PendingResult<Status> sendPayload(GoogleApiClient googleApiClient, List<String> list, Payload payload) {
        return googleApiClient.zze(new zzcqz(this, googleApiClient, list, payload));
    }

    @Deprecated
    public final void sendReliableMessage(GoogleApiClient googleApiClient, String str, byte[] bArr) {
        googleApiClient.zze(new zzcri(this, googleApiClient, str, bArr));
    }

    @Deprecated
    public final void sendReliableMessage(GoogleApiClient googleApiClient, List<String> list, byte[] bArr) {
        googleApiClient.zze(new zzcrj(this, googleApiClient, list, bArr));
    }

    @Deprecated
    public final void sendUnreliableMessage(GoogleApiClient googleApiClient, String str, byte[] bArr) {
        sendPayload(googleApiClient, str, Payload.fromBytes(bArr));
    }

    @Deprecated
    public final void sendUnreliableMessage(GoogleApiClient googleApiClient, List<String> list, byte[] bArr) {
        sendPayload(googleApiClient, list, Payload.fromBytes(bArr));
    }

    @Deprecated
    public final PendingResult<Connections.StartAdvertisingResult> startAdvertising(GoogleApiClient googleApiClient, String str, AppMetadata appMetadata, long j, Connections.ConnectionRequestListener connectionRequestListener) {
        return googleApiClient.zze(new zzcrd(this, googleApiClient, str, j, googleApiClient.zzt(connectionRequestListener)));
    }

    public final PendingResult<Connections.StartAdvertisingResult> startAdvertising(GoogleApiClient googleApiClient, String str, String str2, ConnectionLifecycleCallback connectionLifecycleCallback, AdvertisingOptions advertisingOptions) {
        return googleApiClient.zze(new zzcrk(this, googleApiClient, str, str2, googleApiClient.zzt(connectionLifecycleCallback), advertisingOptions));
    }

    @Deprecated
    public final PendingResult<Status> startDiscovery(GoogleApiClient googleApiClient, String str, long j, Connections.EndpointDiscoveryListener endpointDiscoveryListener) {
        return googleApiClient.zze(new zzcre(this, googleApiClient, str, j, googleApiClient.zzt(endpointDiscoveryListener)));
    }

    public final PendingResult<Status> startDiscovery(GoogleApiClient googleApiClient, String str, EndpointDiscoveryCallback endpointDiscoveryCallback, DiscoveryOptions discoveryOptions) {
        return googleApiClient.zze(new zzcrm(this, googleApiClient, str, googleApiClient.zzt(endpointDiscoveryCallback), discoveryOptions));
    }

    public final void stopAdvertising(GoogleApiClient googleApiClient) {
        googleApiClient.zze(new zzcrl(this, googleApiClient));
    }

    public final void stopAllEndpoints(GoogleApiClient googleApiClient) {
        googleApiClient.zze(new zzcrc(this, googleApiClient));
    }

    public final void stopDiscovery(GoogleApiClient googleApiClient) {
        googleApiClient.zze(new zzcrn(this, googleApiClient));
    }

    @Deprecated
    public final void stopDiscovery(GoogleApiClient googleApiClient, String str) {
        stopDiscovery(googleApiClient);
    }
}
