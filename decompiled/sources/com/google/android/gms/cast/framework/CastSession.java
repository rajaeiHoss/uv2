package com.google.android.gms.cast.framework;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbae;
import com.google.android.gms.internal.zzbag;
import com.google.android.gms.internal.zzbbi;
import com.google.android.gms.internal.zzbei;
import com.google.android.gms.internal.zzbej;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CastSession extends Session {
    /* access modifiers changed from: private */
    public static final zzbei zzeui = new zzbei("CastSession");
    private final Context zzbky;
    private final CastOptions zzezz;
    /* access modifiers changed from: private */
    public final Set<Cast.Listener> zzfak = new HashSet();
    /* access modifiers changed from: private */
    public final zzl zzfal;
    /* access modifiers changed from: private */
    public final Cast.CastApi zzfam;
    private final zzbag zzfan;
    /* access modifiers changed from: private */
    public final zzbbi zzfao;
    /* access modifiers changed from: private */
    public GoogleApiClient zzfap;
    /* access modifiers changed from: private */
    public RemoteMediaClient zzfaq;
    private CastDevice zzfar;
    /* access modifiers changed from: private */
    public Cast.ApplicationConnectionResult zzfas;

    class zza implements ResultCallback<Cast.ApplicationConnectionResult> {
        private String zzfat;

        zza(String str) {
            this.zzfat = str;
        }

        public final void onResult(Cast.ApplicationConnectionResult applicationConnectionResult) {
            Cast.ApplicationConnectionResult unused = CastSession.this.zzfas = applicationConnectionResult;
            try {
                if (applicationConnectionResult.getStatus().isSuccess()) {
                    CastSession.zzeui.zzb("%s() -> success result", this.zzfat);
                    RemoteMediaClient unused2 = CastSession.this.zzfaq = new RemoteMediaClient(new zzbej((String) null, com.google.android.gms.common.util.zzi.zzanq()), CastSession.this.zzfam);
                    try {
                        CastSession.this.zzfaq.zzc(CastSession.this.zzfap);
                        CastSession.this.zzfaq.zzafl();
                        CastSession.this.zzfaq.requestStatus();
                        CastSession.this.zzfao.zza(CastSession.this.zzfaq, CastSession.this.getCastDevice());
                    } catch (IOException e) {
                        CastSession.zzeui.zza(e, "Exception when setting GoogleApiClient.", new Object[0]);
                        RemoteMediaClient unused3 = CastSession.this.zzfaq = null;
                    }
                    CastSession.this.zzfal.zza(applicationConnectionResult.getApplicationMetadata(), applicationConnectionResult.getApplicationStatus(), applicationConnectionResult.getSessionId(), applicationConnectionResult.getWasLaunched());
                    return;
                }
                CastSession.zzeui.zzb("%s() -> failure result", this.zzfat);
                CastSession.this.zzfal.zzbf(applicationConnectionResult.getStatus().getStatusCode());
            } catch (RemoteException e2) {
                CastSession.zzeui.zzb(e2, "Unable to call %s on %s.", "methods", zzl.class.getSimpleName());
            }
        }
    }

    class zzb extends zzi {
        private zzb() {
        }

        public final void zza(String str, LaunchOptions launchOptions) {
            if (CastSession.this.zzfap != null) {
                CastSession.this.zzfam.launchApplication(CastSession.this.zzfap, str, launchOptions).setResultCallback(new zza("launchApplication"));
            }
        }

        public final int zzadx() {
            return 12211278;
        }

        public final void zzbe(int i) {
            CastSession.this.zzbe(i);
        }

        public final void zzfp(String str) {
            if (CastSession.this.zzfap != null) {
                CastSession.this.zzfam.stopApplication(CastSession.this.zzfap, str);
            }
        }

        public final void zzq(String str, String str2) {
            if (CastSession.this.zzfap != null) {
                CastSession.this.zzfam.joinApplication(CastSession.this.zzfap, str, str2).setResultCallback(new zza("joinApplication"));
            }
        }
    }

    class zzc extends Cast.Listener {
        private zzc() {
        }

        public final void onActiveInputStateChanged(int i) {
            for (Cast.Listener onActiveInputStateChanged : new HashSet<>(CastSession.this.zzfak)) {
                onActiveInputStateChanged.onActiveInputStateChanged(i);
            }
        }

        public final void onApplicationDisconnected(int i) {
            CastSession.this.zzbe(i);
            CastSession.this.notifySessionEnded(i);
            for (Cast.Listener onApplicationDisconnected : new HashSet<>(CastSession.this.zzfak)) {
                onApplicationDisconnected.onApplicationDisconnected(i);
            }
        }

        public final void onApplicationMetadataChanged(ApplicationMetadata applicationMetadata) {
            for (Cast.Listener onApplicationMetadataChanged : new HashSet<>(CastSession.this.zzfak)) {
                onApplicationMetadataChanged.onApplicationMetadataChanged(applicationMetadata);
            }
        }

        public final void onApplicationStatusChanged() {
            for (Cast.Listener onApplicationStatusChanged : new HashSet<>(CastSession.this.zzfak)) {
                onApplicationStatusChanged.onApplicationStatusChanged();
            }
        }

        public final void onStandbyStateChanged(int i) {
            for (Cast.Listener onStandbyStateChanged : new HashSet<>(CastSession.this.zzfak)) {
                onStandbyStateChanged.onStandbyStateChanged(i);
            }
        }

        public final void onVolumeChanged() {
            for (Cast.Listener onVolumeChanged : new HashSet<>(CastSession.this.zzfak)) {
                onVolumeChanged.onVolumeChanged();
            }
        }
    }

    class zzd implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
        private zzd() {
        }

        public final void onConnected(Bundle bundle) {
            try {
                if (CastSession.this.zzfaq != null) {
                    try {
                        CastSession.this.zzfaq.zzafl();
                        CastSession.this.zzfaq.requestStatus();
                    } catch (IOException e) {
                        CastSession.zzeui.zza(e, "Exception when setting GoogleApiClient.", new Object[0]);
                        RemoteMediaClient unused = CastSession.this.zzfaq = null;
                    }
                }
                CastSession.this.zzfal.onConnected(bundle);
            } catch (RemoteException e2) {
                CastSession.zzeui.zzb(e2, "Unable to call %s on %s.", "onConnected", zzl.class.getSimpleName());
            }
        }

        public final void onConnectionFailed(ConnectionResult connectionResult) {
            try {
                CastSession.this.zzfal.onConnectionFailed(connectionResult);
            } catch (RemoteException e) {
                CastSession.zzeui.zzb(e, "Unable to call %s on %s.", "onConnectionFailed", zzl.class.getSimpleName());
            }
        }

        public final void onConnectionSuspended(int i) {
            try {
                CastSession.this.zzfal.onConnectionSuspended(i);
            } catch (RemoteException e) {
                CastSession.zzeui.zzb(e, "Unable to call %s on %s.", "onConnectionSuspended", zzl.class.getSimpleName());
            }
        }
    }

    public CastSession(Context context, String str, String str2, CastOptions castOptions, Cast.CastApi castApi, zzbag zzbag, zzbbi zzbbi) {
        super(context, str, str2);
        this.zzbky = context.getApplicationContext();
        this.zzezz = castOptions;
        this.zzfam = castApi;
        this.zzfan = zzbag;
        this.zzfao = zzbbi;
        this.zzfal = zzbae.zza(context, castOptions, zzaej(), (zzh) new zzb());
    }

    /* access modifiers changed from: private */
    public final void zzbe(int i) {
        this.zzfao.zzbh(i);
        GoogleApiClient googleApiClient = this.zzfap;
        if (googleApiClient != null) {
            googleApiClient.disconnect();
            this.zzfap = null;
        }
        this.zzfar = null;
        RemoteMediaClient remoteMediaClient = this.zzfaq;
        if (remoteMediaClient != null) {
            remoteMediaClient.zzc((GoogleApiClient) null);
            this.zzfaq = null;
        }
        this.zzfas = null;
    }

    private final void zzg(Bundle bundle) {
        CastDevice fromBundle = CastDevice.getFromBundle(bundle);
        this.zzfar = fromBundle;
        if (fromBundle != null) {
            GoogleApiClient googleApiClient = this.zzfap;
            if (googleApiClient != null) {
                googleApiClient.disconnect();
                this.zzfap = null;
            }
            boolean z = true;
            zzeui.zzb("Acquiring a connection to Google Play Services for %s", this.zzfar);
            zzd zzd2 = new zzd();
            Context context = this.zzbky;
            CastDevice castDevice = this.zzfar;
            CastOptions castOptions = this.zzezz;
            zzc zzc2 = new zzc();
            Bundle bundle2 = new Bundle();
            if (castOptions == null || castOptions.getCastMediaOptions() == null || castOptions.getCastMediaOptions().getNotificationOptions() == null) {
                z = false;
            }
            bundle2.putBoolean("com.google.android.gms.cast.EXTRA_CAST_FRAMEWORK_NOTIFICATION_ENABLED", z);
            GoogleApiClient build = new GoogleApiClient.Builder(context).addApi(Cast.API, new Cast.CastOptions.Builder(castDevice, zzc2).zzf(bundle2).build()).addConnectionCallbacks(zzd2).addOnConnectionFailedListener(zzd2).build();
            this.zzfap = build;
            build.connect();
        } else if (isResuming()) {
            notifyFailedToResumeSession(8);
        } else {
            notifyFailedToStartSession(8);
        }
    }

    public void addCastListener(Cast.Listener listener) {
        zzbq.zzgn("Must be called from the main thread.");
        if (listener != null) {
            this.zzfak.add(listener);
        }
    }

    /* access modifiers changed from: protected */
    public void end(boolean z) {
        try {
            this.zzfal.zzb(z, 0);
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "disconnectFromDevice", zzl.class.getSimpleName());
        }
        notifySessionEnded(0);
    }

    public int getActiveInputState() throws IllegalStateException {
        zzbq.zzgn("Must be called from the main thread.");
        GoogleApiClient googleApiClient = this.zzfap;
        if (googleApiClient != null) {
            return this.zzfam.getActiveInputState(googleApiClient);
        }
        return -1;
    }

    public Cast.ApplicationConnectionResult getApplicationConnectionResult() {
        zzbq.zzgn("Must be called from the main thread.");
        return this.zzfas;
    }

    public ApplicationMetadata getApplicationMetadata() throws IllegalStateException {
        zzbq.zzgn("Must be called from the main thread.");
        GoogleApiClient googleApiClient = this.zzfap;
        if (googleApiClient != null) {
            return this.zzfam.getApplicationMetadata(googleApiClient);
        }
        return null;
    }

    public String getApplicationStatus() throws IllegalStateException {
        zzbq.zzgn("Must be called from the main thread.");
        GoogleApiClient googleApiClient = this.zzfap;
        if (googleApiClient != null) {
            return this.zzfam.getApplicationStatus(googleApiClient);
        }
        return null;
    }

    public CastDevice getCastDevice() {
        zzbq.zzgn("Must be called from the main thread.");
        return this.zzfar;
    }

    public RemoteMediaClient getRemoteMediaClient() {
        zzbq.zzgn("Must be called from the main thread.");
        return this.zzfaq;
    }

    public long getSessionRemainingTimeMs() {
        zzbq.zzgn("Must be called from the main thread.");
        RemoteMediaClient remoteMediaClient = this.zzfaq;
        if (remoteMediaClient == null) {
            return 0;
        }
        return remoteMediaClient.getStreamDuration() - this.zzfaq.getApproximateStreamPosition();
    }

    public int getStandbyState() throws IllegalStateException {
        zzbq.zzgn("Must be called from the main thread.");
        GoogleApiClient googleApiClient = this.zzfap;
        if (googleApiClient != null) {
            return this.zzfam.getStandbyState(googleApiClient);
        }
        return -1;
    }

    public double getVolume() throws IllegalStateException {
        zzbq.zzgn("Must be called from the main thread.");
        GoogleApiClient googleApiClient = this.zzfap;
        return googleApiClient != null ? this.zzfam.getVolume(googleApiClient) : FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    public boolean isMute() throws IllegalStateException {
        zzbq.zzgn("Must be called from the main thread.");
        GoogleApiClient googleApiClient = this.zzfap;
        if (googleApiClient != null) {
            return this.zzfam.isMute(googleApiClient);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onResuming(Bundle bundle) {
        this.zzfar = CastDevice.getFromBundle(bundle);
    }

    /* access modifiers changed from: protected */
    public void onStarting(Bundle bundle) {
        this.zzfar = CastDevice.getFromBundle(bundle);
    }

    public void removeCastListener(Cast.Listener listener) {
        zzbq.zzgn("Must be called from the main thread.");
        if (listener != null) {
            this.zzfak.remove(listener);
        }
    }

    public void removeMessageReceivedCallbacks(String str) throws IOException, IllegalArgumentException {
        zzbq.zzgn("Must be called from the main thread.");
        GoogleApiClient googleApiClient = this.zzfap;
        if (googleApiClient != null) {
            this.zzfam.removeMessageReceivedCallbacks(googleApiClient, str);
        }
    }

    public void requestStatus() throws IOException, IllegalStateException {
        zzbq.zzgn("Must be called from the main thread.");
        GoogleApiClient googleApiClient = this.zzfap;
        if (googleApiClient != null) {
            this.zzfam.requestStatus(googleApiClient);
        }
    }

    /* access modifiers changed from: protected */
    public void resume(Bundle bundle) {
        zzg(bundle);
    }

    public PendingResult<Status> sendMessage(String str, String str2) {
        zzbq.zzgn("Must be called from the main thread.");
        GoogleApiClient googleApiClient = this.zzfap;
        if (googleApiClient != null) {
            return this.zzfam.sendMessage(googleApiClient, str, str2);
        }
        return null;
    }

    public void setMessageReceivedCallbacks(String str, Cast.MessageReceivedCallback messageReceivedCallback) throws IOException, IllegalStateException {
        zzbq.zzgn("Must be called from the main thread.");
        GoogleApiClient googleApiClient = this.zzfap;
        if (googleApiClient != null) {
            this.zzfam.setMessageReceivedCallbacks(googleApiClient, str, messageReceivedCallback);
        }
    }

    public void setMute(boolean z) throws IOException, IllegalStateException {
        zzbq.zzgn("Must be called from the main thread.");
        GoogleApiClient googleApiClient = this.zzfap;
        if (googleApiClient != null) {
            this.zzfam.setMute(googleApiClient, z);
        }
    }

    public void setVolume(double d) throws IOException {
        zzbq.zzgn("Must be called from the main thread.");
        GoogleApiClient googleApiClient = this.zzfap;
        if (googleApiClient != null) {
            this.zzfam.setVolume(googleApiClient, d);
        }
    }

    /* access modifiers changed from: protected */
    public void start(Bundle bundle) {
        zzg(bundle);
    }

    public final zzbbi zzaed() {
        return this.zzfao;
    }
}
