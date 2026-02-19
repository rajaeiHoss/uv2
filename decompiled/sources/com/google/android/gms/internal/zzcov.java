package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.nearby.connection.AdvertisingOptions;
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;
import com.google.android.gms.nearby.connection.Connections;
import com.google.android.gms.nearby.connection.ConnectionsStatusCodes;
import com.google.android.gms.nearby.connection.DiscoveryOptions;
import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback;
import com.google.android.gms.nearby.connection.Payload;
import com.google.android.gms.nearby.connection.PayloadCallback;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

public final class zzcov extends zzab<zzcso> {
    private final long zzjxi = ((long) hashCode());
    private final Set<zzcpi> zzjxj = new HashSet();
    private final Set<zzcox> zzjxk = new HashSet();
    private zzctz zzjxl;

    public zzcov(Context context, Looper looper, zzr zzr, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 54, zzr, connectionCallbacks, onConnectionFailedListener);
    }

    private final void reset() {
        for (zzcpi shutdown : this.zzjxj) {
            shutdown.shutdown();
        }
        for (zzcox shutdown2 : this.zzjxk) {
            shutdown2.shutdown();
        }
        this.zzjxj.clear();
        this.zzjxk.clear();
        zzctz zzctz = this.zzjxl;
        if (zzctz != null) {
            zzctz.shutdown();
            this.zzjxl = null;
        }
    }

    /* access modifiers changed from: private */
    public static Status zzcm(int i) {
        return new Status(i, ConnectionsStatusCodes.getStatusCodeString(i));
    }

    public final void disconnect() {
        if (isConnected()) {
            try {
                ((zzcso) zzalw()).zza(new zzcot());
            } catch (RemoteException e) {
                Log.w("NearbyConnectionsClient", "Failed to notify client disconnect.", e);
            }
        }
        reset();
        super.disconnect();
    }

    public final void disconnectFromEndpoint(String str) throws RemoteException {
        ((zzcso) zzalw()).zza(new zzcrv(str));
    }

    public final void onConnectionSuspended(int i) {
        if (i == 1) {
            reset();
        }
        super.onConnectionSuspended(i);
    }

    public final void stopAdvertising() throws RemoteException {
        ((zzcso) zzalw()).zza(new zzcuo());
    }

    public final void stopAllEndpoints() throws RemoteException {
        ((zzcso) zzalw()).zza(new zzcuq());
    }

    public final void stopDiscovery() throws RemoteException {
        ((zzcso) zzalw()).zza(new zzcus());
    }

    /* access modifiers changed from: protected */
    public final void zza(zzcso zzcso) {
        super.zza(zzcso);
        this.zzjxl = new zzctz();
    }

    public final void zza(zzn<Status> zzn, long j) throws RemoteException {
        ((zzcso) zzalw()).zza(new zzcor(new zzcpw(zzn).asBinder(), j));
    }

    public final void zza(zzn<Status> zzn, String str, zzci<PayloadCallback> zzci) throws RemoteException {
        ((zzcso) zzalw()).zza(new zzcop(new zzcpw(zzn).asBinder(), (IBinder) null, str, (byte[]) null, new zzcpt(zzci).asBinder()));
    }

    public final void zza(zzn<Status> zzn, String str, zzci<EndpointDiscoveryCallback> zzci, DiscoveryOptions discoveryOptions) throws RemoteException {
        zzcpi zzcpi = new zzcpi(zzci);
        this.zzjxj.add(zzcpi);
        ((zzcso) zzalw()).zza(new zzcum(new zzcpw(zzn).asBinder(), (IBinder) null, str, 0, discoveryOptions, zzcpi.asBinder()));
    }

    public final void zza(zzn<Status> zzn, String str, String str2, zzci<ConnectionLifecycleCallback> zzci) throws RemoteException {
        zzcox zzcox = new zzcox(zzci);
        this.zzjxk.add(zzcox);
        ((zzcso) zzalw()).zza(new zzcug(new zzcpw(zzn).asBinder(), (IBinder) null, (IBinder) null, str, str2, (byte[]) null, zzcox.asBinder()));
    }

    public final void zza(zzn<Connections.StartAdvertisingResult> zzn, String str, String str2, zzci<ConnectionLifecycleCallback> zzci, AdvertisingOptions advertisingOptions) throws RemoteException {
        zzcox zzcox = new zzcox(zzci);
        this.zzjxk.add(zzcox);
        zzn<Connections.StartAdvertisingResult> zzn2 = zzn;
        ((zzcso) zzalw()).zza(new zzcuk(new zzcpy(zzn).asBinder(), (IBinder) null, str, str2, 0, advertisingOptions, zzcox.asBinder()));
    }

    public final void zza(zzn<Status> zzn, String[] strArr, Payload payload, boolean z) throws RemoteException {
        try {
            Pair<zzcub, Pair<ParcelFileDescriptor, ParcelFileDescriptor>> zza = zzcud.zza(payload);
            ((zzcso) zzalw()).zza(new zzcui(new zzcpw(zzn).asBinder(), strArr, (zzcub) zza.first, z));
            if (zza.second != null) {
                Pair pair = (Pair) zza.second;
                this.zzjxl.zza(payload.asStream().asInputStream(), (OutputStream) new ParcelFileDescriptor.AutoCloseOutputStream((ParcelFileDescriptor) pair.first), (OutputStream) new ParcelFileDescriptor.AutoCloseOutputStream((ParcelFileDescriptor) pair.second), payload.getId());
            }
        } catch (IOException unused) {
            zzn.setResult(zzcm(ConnectionsStatusCodes.STATUS_PAYLOAD_IO_ERROR));
        }
    }

    /* access modifiers changed from: protected */
    public final Bundle zzabt() {
        Bundle bundle = new Bundle();
        bundle.putLong("clientId", this.zzjxi);
        return bundle;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ zzcso zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionService");
        return queryLocalInterface instanceof zzcso ? (zzcso) queryLocalInterface : new zzcsp(iBinder);
    }

    /* access modifiers changed from: protected */
    public final String zzhm() {
        return "com.google.android.gms.nearby.connection.service.START";
    }

    /* access modifiers changed from: protected */
    public final String zzhn() {
        return "com.google.android.gms.nearby.internal.connection.INearbyConnectionService";
    }

    public final void zzj(zzn<Status> zzn, String str) throws RemoteException {
        ((zzcso) zzalw()).zza(new zzcue(new zzcpw(zzn).asBinder(), str));
    }
}
