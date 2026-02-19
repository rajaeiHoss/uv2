package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndexApi;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzm;
import com.google.android.gms.common.api.internal.zzn;
import java.util.List;

public final class zzauz implements AppIndexApi, zzaur {
    private static final String TAG = "zzauz";

    @Deprecated
    static final class zza implements AppIndexApi.ActionResult {
        private zzauz zzegp;
        private PendingResult<Status> zzegq;
        private Action zzegr;

        zza(zzauz zzauz, PendingResult<Status> pendingResult, Action action) {
            this.zzegp = zzauz;
            this.zzegq = pendingResult;
            this.zzegr = action;
        }

        public final PendingResult<Status> end(GoogleApiClient googleApiClient) {
            String packageName = googleApiClient.getContext().getPackageName();
            zzauo zza = zzauy.zza(this.zzegr, System.currentTimeMillis(), packageName, 2);
            return this.zzegp.zza(googleApiClient, zza);
        }

        public final PendingResult<Status> getPendingResult() {
            return this.zzegq;
        }
    }

    static abstract class zzb<T extends Result> extends zzm<T, zzaux> {
        public zzb(GoogleApiClient googleApiClient) {
            super((Api<?>) zzatv.zzefd, googleApiClient);
        }

        /* access modifiers changed from: protected */
        public final void zza(zzaux zzaux) throws RemoteException {
            zza((zzaus) zzaux.zzalw());
        }

        /* access modifiers changed from: protected */
        public abstract void zza(zzaus zzaus) throws RemoteException;
    }

    public static abstract class zzc<T extends Result> extends zzb<Status> {
        public zzc(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        /* access modifiers changed from: protected */
        public final Status zzb(Status status) {
            return status;
        }
    }

    public static final class zzd extends zzauw<Status> {
        public zzd(zzn<Status> zzn) {
            super(zzn);
        }

        public final void zza(Status status) {
            this.zzegn.setResult(status);
        }
    }

    public static Intent zza(String str, Uri uri) {
        zzb(str, uri);
        if (zzj(uri)) {
            return new Intent("android.intent.action.VIEW", uri);
        }
        if (zzk(uri)) {
            return new Intent("android.intent.action.VIEW", zzi(uri));
        }
        String valueOf = String.valueOf(uri);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 70);
        sb.append("appIndexingUri is neither an HTTP(S) URL nor an \"android-app://\" URL: ");
        sb.append(valueOf);
        throw new RuntimeException(sb.toString());
    }

    private final PendingResult<Status> zza(GoogleApiClient googleApiClient, Action action, int i) {
        return zza(googleApiClient, zzauy.zza(action, System.currentTimeMillis(), googleApiClient.getContext().getPackageName(), i));
    }

    private static void zzb(String str, Uri uri) {
        if (zzj(uri)) {
            if (uri.getHost().isEmpty()) {
                String valueOf = String.valueOf(uri);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 98);
                sb.append("AppIndex: The web URL must have a host (follow the format http(s)://<host>/<path>). Provided URI: ");
                sb.append(valueOf);
                throw new IllegalArgumentException(sb.toString());
            }
        } else if (!zzk(uri)) {
            String valueOf2 = String.valueOf(uri);
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 176);
            sb2.append("AppIndex: The URI scheme must either be 'http(s)' or 'android-app'. If the latter, it must follow the format 'android-app://<package_name>/<scheme>/<host_path>'. Provided URI: ");
            sb2.append(valueOf2);
            throw new IllegalArgumentException(sb2.toString());
        } else if (str == null || str.equals(uri.getHost())) {
            List<String> pathSegments = uri.getPathSegments();
            if (pathSegments.isEmpty() || pathSegments.get(0).isEmpty()) {
                String valueOf3 = String.valueOf(uri);
                StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf3).length() + 128);
                sb3.append("AppIndex: The app URI scheme must exist and follow the format android-app://<package_name>/<scheme>/<host_path>). Provided URI: ");
                sb3.append(valueOf3);
                throw new IllegalArgumentException(sb3.toString());
            }
        } else {
            String valueOf4 = String.valueOf(uri);
            StringBuilder sb4 = new StringBuilder(String.valueOf(valueOf4).length() + 150);
            sb4.append("AppIndex: The android-app URI host must match the package name and follow the format android-app://<package_name>/<scheme>/<host_path>. Provided URI: ");
            sb4.append(valueOf4);
            throw new IllegalArgumentException(sb4.toString());
        }
    }

    private static Uri zzi(Uri uri) {
        List<String> pathSegments = uri.getPathSegments();
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(pathSegments.get(0));
        if (pathSegments.size() > 1) {
            builder.authority(pathSegments.get(1));
            for (int i = 2; i < pathSegments.size(); i++) {
                builder.appendPath(pathSegments.get(i));
            }
        } else {
            String str = TAG;
            String valueOf = String.valueOf(uri);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 88);
            sb.append("The app URI must have the format: android-app://<package_name>/<scheme>/<path>. But got ");
            sb.append(valueOf);
            Log.e(str, sb.toString());
        }
        builder.encodedQuery(uri.getEncodedQuery());
        builder.encodedFragment(uri.getEncodedFragment());
        return builder.build();
    }

    private static boolean zzj(Uri uri) {
        String scheme = uri.getScheme();
        return "http".equals(scheme) || "https".equals(scheme);
    }

    private static boolean zzk(Uri uri) {
        return "android-app".equals(uri.getScheme());
    }

    public final AppIndexApi.ActionResult action(GoogleApiClient googleApiClient, Action action) {
        return new zza(this, zza(googleApiClient, action, 1), action);
    }

    public final PendingResult<Status> end(GoogleApiClient googleApiClient, Action action) {
        return zza(googleApiClient, action, 2);
    }

    public final PendingResult<Status> start(GoogleApiClient googleApiClient, Action action) {
        return zza(googleApiClient, action, 1);
    }

    public final PendingResult<Status> view(GoogleApiClient googleApiClient, Activity activity, Intent intent, String str, Uri uri, List<AppIndexApi.AppIndexingLink> list) {
        String packageName = googleApiClient.getContext().getPackageName();
        if (list != null) {
            for (AppIndexApi.AppIndexingLink appIndexingLink : list) {
                zzb((String) null, appIndexingLink.appIndexingUrl);
            }
        }
        zzauo[] zzauoArr = {new zzauo(packageName, intent, str, uri, (String) null, list, 1)};
        GoogleApiClient googleApiClient2 = googleApiClient;
        return zza(googleApiClient, zzauoArr);
    }

    public final PendingResult<Status> view(GoogleApiClient googleApiClient, Activity activity, Uri uri, String str, Uri uri2, List<AppIndexApi.AppIndexingLink> list) {
        String packageName = googleApiClient.getContext().getPackageName();
        zzb(packageName, uri);
        return view(googleApiClient, activity, zza(packageName, uri), str, uri2, list);
    }

    public final PendingResult<Status> viewEnd(GoogleApiClient googleApiClient, Activity activity, Intent intent) {
        return zza(googleApiClient, new zzaup().zza(zzauo.zza(googleApiClient.getContext().getPackageName(), intent)).zzv(System.currentTimeMillis()).zzax(0).zzay(2).zzabr());
    }

    public final PendingResult<Status> viewEnd(GoogleApiClient googleApiClient, Activity activity, Uri uri) {
        return viewEnd(googleApiClient, activity, zza(googleApiClient.getContext().getPackageName(), uri));
    }

    public final PendingResult<Status> zza(GoogleApiClient googleApiClient, zzauo... zzauoArr) {
        return googleApiClient.zzd(new zzava(this, googleApiClient, zzauoArr));
    }
}
