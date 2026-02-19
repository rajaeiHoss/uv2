package com.google.android.gms.wearable.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzj;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.common.zzf;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.CapabilityApi;
import com.google.android.gms.wearable.ChannelApi;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.PutDataRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public final class zzhg extends zzab<zzep> {
    private final ExecutorService zzimc;
    private final zzer<Object> zzlvl;
    private final zzer<Object> zzlvm;
    private final zzer<ChannelApi.ChannelListener> zzlvn;
    private final zzer<DataApi.DataListener> zzlvo;
    private final zzer<MessageApi.MessageListener> zzlvp;
    private final zzer<Object> zzlvq;
    private final zzer<Object> zzlvr;
    private final zzer<CapabilityApi.CapabilityListener> zzlvs;
    private final zzhp zzlvt;

    public zzhg(Context context, Looper looper, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, zzr zzr) {
        this(context, looper, connectionCallbacks, onConnectionFailedListener, zzr, Executors.newCachedThreadPool(), zzhp.zzeu(context));
    }

    private zzhg(Context context, Looper looper, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, zzr zzr, ExecutorService executorService, zzhp zzhp) {
        super(context, looper, 14, zzr, connectionCallbacks, onConnectionFailedListener);
        this.zzlvl = new zzer<>();
        this.zzlvm = new zzer<>();
        this.zzlvn = new zzer<>();
        this.zzlvo = new zzer<>();
        this.zzlvp = new zzer<>();
        this.zzlvq = new zzer<>();
        this.zzlvr = new zzer<>();
        this.zzlvs = new zzer<>();
        this.zzimc = (ExecutorService) zzbq.checkNotNull(executorService);
        this.zzlvt = zzhp;
    }

    /* access modifiers changed from: protected */
    public final void zza(int i, IBinder iBinder, Bundle bundle, int i2) {
        if (Log.isLoggable("WearableClient", 2)) {
            StringBuilder sb = new StringBuilder(41);
            sb.append("onPostInitHandler: statusCode ");
            sb.append(i);
            Log.v("WearableClient", sb.toString());
        }
        if (i == 0) {
            this.zzlvl.zzbt(iBinder);
            this.zzlvm.zzbt(iBinder);
            this.zzlvn.zzbt(iBinder);
            this.zzlvo.zzbt(iBinder);
            this.zzlvp.zzbt(iBinder);
            this.zzlvq.zzbt(iBinder);
            this.zzlvr.zzbt(iBinder);
            this.zzlvs.zzbt(iBinder);
        }
        super.zza(i, iBinder, bundle, i2);
    }

    public final void zza(zzn<DataApi.GetFdForAssetResult> zzn, Asset asset) throws RemoteException {
        ((zzep) zzalw()).zza((zzek) new zzgx(zzn), asset);
    }

    public final void zza(zzn<Status> zzn, CapabilityApi.CapabilityListener capabilityListener) throws RemoteException {
        this.zzlvs.zza(this, zzn, capabilityListener);
    }

    public final void zza(zzn<Status> zzn, CapabilityApi.CapabilityListener capabilityListener, zzci<CapabilityApi.CapabilityListener> zzci, IntentFilter[] intentFilterArr) throws RemoteException {
        this.zzlvs.zza(this, zzn, capabilityListener, zzhk.zzd(zzci, intentFilterArr));
    }

    public final void zza(zzn<Status> zzn, ChannelApi.ChannelListener channelListener, zzci<ChannelApi.ChannelListener> zzci, String str, IntentFilter[] intentFilterArr) throws RemoteException {
        if (str == null) {
            this.zzlvn.zza(this, zzn, channelListener, zzhk.zzc(zzci, intentFilterArr));
            return;
        }
        this.zzlvn.zza(this, zzn, new zzgc(str, channelListener), zzhk.zza(zzci, str, intentFilterArr));
    }

    public final void zza(zzn<Status> zzn, ChannelApi.ChannelListener channelListener, String str) throws RemoteException {
        if (str == null) {
            this.zzlvn.zza(this, zzn, channelListener);
            return;
        }
        this.zzlvn.zza(this, zzn, new zzgc(str, channelListener));
    }

    public final void zza(zzn<Status> zzn, DataApi.DataListener dataListener) throws RemoteException {
        this.zzlvo.zza(this, zzn, dataListener);
    }

    public final void zza(zzn<Status> zzn, DataApi.DataListener dataListener, zzci<DataApi.DataListener> zzci, IntentFilter[] intentFilterArr) throws RemoteException {
        this.zzlvo.zza(this, zzn, dataListener, zzhk.zza(zzci, intentFilterArr));
    }

    public final void zza(zzn<Status> zzn, MessageApi.MessageListener messageListener) throws RemoteException {
        this.zzlvp.zza(this, zzn, messageListener);
    }

    public final void zza(zzn<Status> zzn, MessageApi.MessageListener messageListener, zzci<MessageApi.MessageListener> zzci, IntentFilter[] intentFilterArr) throws RemoteException {
        this.zzlvp.zza(this, zzn, messageListener, zzhk.zzb(zzci, intentFilterArr));
    }

    public final void zza(zzn<DataApi.DataItemResult> zzn, PutDataRequest putDataRequest) throws RemoteException {
        zzn<DataApi.DataItemResult> zzn2 = zzn;
        for (Map.Entry<String, Asset> value : putDataRequest.getAssets().entrySet()) {
            Asset asset = (Asset) value.getValue();
            if (asset.getData() == null && asset.getDigest() == null && asset.getFd() == null && asset.getUri() == null) {
                String valueOf = String.valueOf(putDataRequest.getUri());
                String valueOf2 = String.valueOf(asset);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 33 + String.valueOf(valueOf2).length());
                sb.append("Put for ");
                sb.append(valueOf);
                sb.append(" contains invalid asset: ");
                sb.append(valueOf2);
                throw new IllegalArgumentException(sb.toString());
            }
        }
        PutDataRequest zzs = PutDataRequest.zzs(putDataRequest.getUri());
        zzs.setData(putDataRequest.getData());
        if (putDataRequest.isUrgent()) {
            zzs.setUrgent();
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry next : putDataRequest.getAssets().entrySet()) {
            Asset asset2 = (Asset) next.getValue();
            if (asset2.getData() != null) {
                try {
                    ParcelFileDescriptor[] createPipe = ParcelFileDescriptor.createPipe();
                    if (Log.isLoggable("WearableClient", 3)) {
                        String valueOf3 = String.valueOf(asset2);
                        String valueOf4 = String.valueOf(createPipe[0]);
                        String valueOf5 = String.valueOf(createPipe[1]);
                        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf3).length() + 61 + String.valueOf(valueOf4).length() + String.valueOf(valueOf5).length());
                        sb2.append("processAssets: replacing data with FD in asset: ");
                        sb2.append(valueOf3);
                        sb2.append(" read:");
                        sb2.append(valueOf4);
                        sb2.append(" write:");
                        sb2.append(valueOf5);
                        Log.d("WearableClient", sb2.toString());
                    }
                    zzs.putAsset((String) next.getKey(), Asset.createFromFd(createPipe[0]));
                    FutureTask futureTask = new FutureTask(new zzhh(this, createPipe[1], asset2.getData()));
                    arrayList.add(futureTask);
                    this.zzimc.submit(futureTask);
                } catch (IOException e) {
                    String valueOf6 = String.valueOf(putDataRequest);
                    StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf6).length() + 60);
                    sb3.append("Unable to create ParcelFileDescriptor for asset in request: ");
                    sb3.append(valueOf6);
                    throw new IllegalStateException(sb3.toString(), e);
                }
            } else if (asset2.getUri() != null) {
                try {
                    zzs.putAsset((String) next.getKey(), Asset.createFromFd(getContext().getContentResolver().openFileDescriptor(asset2.getUri(), "r")));
                } catch (FileNotFoundException unused) {
                    new zzhb(zzn2, arrayList).zza(new zzfu(4005, (zzdd) null));
                    String valueOf7 = String.valueOf(asset2.getUri());
                    StringBuilder sb4 = new StringBuilder(String.valueOf(valueOf7).length() + 28);
                    sb4.append("Couldn't resolve asset URI: ");
                    sb4.append(valueOf7);
                    Log.w("WearableClient", sb4.toString());
                    return;
                }
            } else {
                zzs.putAsset((String) next.getKey(), asset2);
            }
        }
        ((zzep) zzalw()).zza((zzek) new zzhb(zzn2, arrayList), zzs);
    }

    public final void zza(zzn<Status> zzn, String str, Uri uri, long j, long j2) {
        try {
            ExecutorService executorService = this.zzimc;
            zzbq.checkNotNull(zzn);
            zzbq.checkNotNull(str);
            zzbq.checkNotNull(uri);
            zzbq.zzb(j >= 0, "startOffset is negative: %s", Long.valueOf(j));
            zzbq.zzb(j2 >= -1, "invalid length: %s", Long.valueOf(j2));
            executorService.execute(new zzhj(this, uri, zzn, str, j, j2));
        } catch (RuntimeException e) {
            zzn<Status> zzn2 = zzn;
            zzn.zzu(new Status(8));
            throw e;
        }
    }

    public final void zza(zzn<Status> zzn, String str, Uri uri, boolean z) {
        try {
            ExecutorService executorService = this.zzimc;
            zzbq.checkNotNull(zzn);
            zzbq.checkNotNull(str);
            zzbq.checkNotNull(uri);
            executorService.execute(new zzhi(this, uri, zzn, z, str));
        } catch (RuntimeException e) {
            zzn.zzu(new Status(8));
            throw e;
        }
    }

    public final void zza(zzj zzj) {
        if (!zzahn()) {
            try {
                Bundle bundle = getContext().getPackageManager().getApplicationInfo("com.google.android.wearable.app.cn", 128).metaData;
                int i = bundle != null ? bundle.getInt("com.google.android.wearable.api.version", 0) : 0;
                if (i < zzf.GOOGLE_PLAY_SERVICES_VERSION_CODE) {
                    int i2 = zzf.GOOGLE_PLAY_SERVICES_VERSION_CODE;
                    StringBuilder sb = new StringBuilder(80);
                    sb.append("Android Wear out of date. Requires API version ");
                    sb.append(i2);
                    sb.append(" but found ");
                    sb.append(i);
                    Log.w("WearableClient", sb.toString());
                    Context context = getContext();
                    Context context2 = getContext();
                    Intent intent = new Intent("com.google.android.wearable.app.cn.UPDATE_ANDROID_WEAR").setPackage("com.google.android.wearable.app.cn");
                    if (context2.getPackageManager().resolveActivity(intent, 65536) == null) {
                        intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details").buildUpon().appendQueryParameter("id", "com.google.android.wearable.app.cn").build());
                    }
                    zza(zzj, 6, PendingIntent.getActivity(context, 0, intent, 0));
                    return;
                }
            } catch (PackageManager.NameNotFoundException unused) {
                zza(zzj, 16, (PendingIntent) null);
                return;
            }
        }
        super.zza(zzj);
    }

    public final boolean zzahn() {
        return !this.zzlvt.zzof("com.google.android.wearable.app.cn");
    }

    /* access modifiers changed from: protected */
    public final String zzalq() {
        return this.zzlvt.zzof("com.google.android.wearable.app.cn") ? "com.google.android.wearable.app.cn" : "com.google.android.gms";
    }

    /* access modifiers changed from: protected */
    public final zzep zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.wearable.internal.IWearableService");
        return queryLocalInterface instanceof zzep ? (zzep) queryLocalInterface : new zzeq(iBinder);
    }

    /* access modifiers changed from: protected */
    public final String zzhm() {
        return "com.google.android.gms.wearable.BIND";
    }

    /* access modifiers changed from: protected */
    public final String zzhn() {
        return "com.google.android.gms.wearable.internal.IWearableService";
    }
}
