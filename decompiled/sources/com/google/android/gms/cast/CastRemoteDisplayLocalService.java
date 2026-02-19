package com.google.android.gms.cast;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.text.TextUtils;
import android.view.Display;
import androidx.core.app.NotificationCompat;
import androidx.mediarouter.media.MediaRouter;
import com.google.android.gms.R;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.internal.zzbei;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class CastRemoteDisplayLocalService extends Service {
    /* access modifiers changed from: private */
    public static final zzbei zzeus = new zzbei("CastRemoteDisplayLocalService");
    private static final int zzeut = R.id.cast_notification_id;
    /* access modifiers changed from: private */
    public static final Object zzeuu = new Object();
    /* access modifiers changed from: private */
    public static AtomicBoolean zzeuv = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public static CastRemoteDisplayLocalService zzevi;
    private Handler mHandler;
    private Notification mNotification;
    /* access modifiers changed from: private */
    public Display zzdmh;
    private String zzetb;
    /* access modifiers changed from: private */
    public WeakReference<Callbacks> zzeuw;
    private zzb zzeux;
    private NotificationSettings zzeuy;
    private boolean zzeuz;
    private PendingIntent zzeva;
    /* access modifiers changed from: private */
    public CastDevice zzevb;
    /* access modifiers changed from: private */
    public Context zzevc;
    /* access modifiers changed from: private */
    public ServiceConnection zzevd;
    private MediaRouter zzeve;
    /* access modifiers changed from: private */
    public boolean zzevf = false;
    private CastRemoteDisplayClient zzevg;
    private final MediaRouter.Callback zzevh = new zzu(this);
    private final IBinder zzevj = new zza();

    public interface Callbacks {
        void onRemoteDisplaySessionEnded(CastRemoteDisplayLocalService castRemoteDisplayLocalService);

        void onRemoteDisplaySessionError(Status status);

        void onRemoteDisplaySessionStarted(CastRemoteDisplayLocalService castRemoteDisplayLocalService);

        void onServiceCreated(CastRemoteDisplayLocalService castRemoteDisplayLocalService);
    }

    public static final class NotificationSettings {
        /* access modifiers changed from: private */
        public Notification mNotification;
        /* access modifiers changed from: private */
        public PendingIntent zzevr;
        /* access modifiers changed from: private */
        public String zzevs;
        /* access modifiers changed from: private */
        public String zzevt;

        public static final class Builder {
            private NotificationSettings zzevu = new NotificationSettings((zzu) null);

            public final NotificationSettings build() {
                if (this.zzevu.mNotification != null) {
                    if (!TextUtils.isEmpty(this.zzevu.zzevs)) {
                        throw new IllegalArgumentException("notificationTitle requires using the default notification");
                    } else if (!TextUtils.isEmpty(this.zzevu.zzevt)) {
                        throw new IllegalArgumentException("notificationText requires using the default notification");
                    } else if (this.zzevu.zzevr != null) {
                        throw new IllegalArgumentException("notificationPendingIntent requires using the default notification");
                    }
                } else if (TextUtils.isEmpty(this.zzevu.zzevs) && TextUtils.isEmpty(this.zzevu.zzevt) && this.zzevu.zzevr == null) {
                    throw new IllegalArgumentException("At least an argument must be provided");
                }
                return this.zzevu;
            }

            public final Builder setNotification(Notification notification) {
                Notification unused = this.zzevu.mNotification = notification;
                return this;
            }

            public final Builder setNotificationPendingIntent(PendingIntent pendingIntent) {
                PendingIntent unused = this.zzevu.zzevr = pendingIntent;
                return this;
            }

            public final Builder setNotificationText(String str) {
                String unused = this.zzevu.zzevt = str;
                return this;
            }

            public final Builder setNotificationTitle(String str) {
                String unused = this.zzevu.zzevs = str;
                return this;
            }
        }

        private NotificationSettings() {
        }

        private NotificationSettings(NotificationSettings notificationSettings) {
            this.mNotification = notificationSettings.mNotification;
            this.zzevr = notificationSettings.zzevr;
            this.zzevs = notificationSettings.zzevs;
            this.zzevt = notificationSettings.zzevt;
        }

        /* synthetic */ NotificationSettings(NotificationSettings notificationSettings, zzu zzu) {
            this(notificationSettings);
        }

        /* synthetic */ NotificationSettings(zzu zzu) {
            this();
        }
    }

    public static class Options {
        private int zzeuf = 2;

        public int getConfigPreset() {
            return this.zzeuf;
        }

        public void setConfigPreset(int i) {
            this.zzeuf = i;
        }
    }

    class zza extends Binder {
        zza() {
        }
    }

    static final class zzb extends BroadcastReceiver {
        private zzb() {
        }

        /* synthetic */ zzb(zzu zzu) {
            this();
        }

        public final void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.google.android.gms.cast.remote_display.ACTION_NOTIFICATION_DISCONNECT")) {
                CastRemoteDisplayLocalService.stopService();
            } else if (intent.getAction().equals("com.google.android.gms.cast.remote_display.ACTION_SESSION_ENDED")) {
                CastRemoteDisplayLocalService.zzax(false);
            }
        }
    }

    public static CastRemoteDisplayLocalService getInstance() {
        CastRemoteDisplayLocalService castRemoteDisplayLocalService;
        synchronized (zzeuu) {
            castRemoteDisplayLocalService = zzevi;
        }
        return castRemoteDisplayLocalService;
    }

    protected static void setDebugEnabled() {
        zzeus.zzbh(true);
    }

    public static void startService(Context context, Class<? extends CastRemoteDisplayLocalService> cls, String str, CastDevice castDevice, NotificationSettings notificationSettings, Callbacks callbacks) {
        startServiceWithOptions(context, cls, str, castDevice, new Options(), notificationSettings, callbacks);
    }

    public static void startServiceWithOptions(Context context, Class<? extends CastRemoteDisplayLocalService> cls, String str, CastDevice castDevice, Options options, NotificationSettings notificationSettings, Callbacks callbacks) {
        zzbei zzbei = zzeus;
        zzbei.zzb("Starting Service", new Object[0]);
        synchronized (zzeuu) {
            if (zzevi != null) {
                zzbei.zzf("An existing service had not been stopped before starting one", new Object[0]);
                zzax(true);
            }
        }
        try {
            ServiceInfo serviceInfo = context.getPackageManager().getServiceInfo(new ComponentName(context, cls), 128);
            if (serviceInfo != null) {
                if (serviceInfo.exported) {
                    throw new IllegalStateException("The service must not be exported, verify the manifest configuration");
                }
            }
            zzbq.checkNotNull(context, "activityContext is required.");
            zzbq.checkNotNull(cls, "serviceClass is required.");
            zzbq.checkNotNull(str, "applicationId is required.");
            zzbq.checkNotNull(castDevice, "device is required.");
            zzbq.checkNotNull(options, "options is required.");
            zzbq.checkNotNull(notificationSettings, "notificationSettings is required.");
            zzbq.checkNotNull(callbacks, "callbacks is required.");
            if (notificationSettings.mNotification == null && notificationSettings.zzevr == null) {
                throw new IllegalArgumentException("notificationSettings: Either the notification or the notificationPendingIntent must be provided");
            } else if (zzeuv.getAndSet(true)) {
                zzbei.zzc("Service is already being started, startService has been called twice", new Object[0]);
            } else {
                Intent intent = new Intent(context, cls);
                context.startService(intent);
                context.bindService(intent, new zzw(str, castDevice, options, notificationSettings, context, callbacks), 64);
            }
        } catch (PackageManager.NameNotFoundException unused) {
            throw new IllegalStateException("Service not found, did you forget to configure it in the manifest?");
        }
    }

    public static void stopService() {
        zzax(false);
    }

    /* access modifiers changed from: private */
    public final void zza(Display display) {
        this.zzdmh = display;
        if (this.zzeuz) {
            Notification zzay = zzay(true);
            this.mNotification = zzay;
            startForeground(zzeut, zzay);
        }
        if (this.zzeuw.get() != null) {
            ((Callbacks) this.zzeuw.get()).onRemoteDisplaySessionStarted(this);
        }
        onCreatePresentation(this.zzdmh);
    }

    /* access modifiers changed from: private */
    public final void zza(NotificationSettings notificationSettings) {
        zzbq.zzgn("updateNotificationSettingsInternal must be called on the main thread");
        if (this.zzeuy != null) {
            if (!this.zzeuz) {
                zzbq.checkNotNull(notificationSettings.mNotification, "notification is required.");
                Notification zzb2 = notificationSettings.mNotification;
                this.mNotification = zzb2;
                Notification unused = this.zzeuy.mNotification = zzb2;
            } else if (notificationSettings.mNotification == null) {
                if (notificationSettings.zzevr != null) {
                    PendingIntent unused2 = this.zzeuy.zzevr = notificationSettings.zzevr;
                }
                if (!TextUtils.isEmpty(notificationSettings.zzevs)) {
                    String unused3 = this.zzeuy.zzevs = notificationSettings.zzevs;
                }
                if (!TextUtils.isEmpty(notificationSettings.zzevt)) {
                    String unused4 = this.zzeuy.zzevt = notificationSettings.zzevt;
                }
                this.mNotification = zzay(true);
            } else {
                throw new IllegalStateException("Current mode is default notification, notification attribute must not be provided");
            }
            startForeground(zzeut, this.mNotification);
            return;
        }
        throw new IllegalStateException("No current notification settings to update");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0020, code lost:
        r3.zzeuw = new java.lang.ref.WeakReference<>(r10);
        r3.zzetb = r4;
        r3.zzevb = r5;
        r3.zzevc = r8;
        r3.zzevd = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0031, code lost:
        if (r3.zzeve != null) goto L_0x003d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0033, code lost:
        r3.zzeve = androidx.mediarouter.media.MediaRouter.getInstance(getApplicationContext());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003d, code lost:
        r4 = new androidx.mediarouter.media.MediaRouteSelector.Builder().addControlCategory(com.google.android.gms.cast.CastMediaControlIntent.categoryForCast(r3.zzetb)).build();
        zzeb("addMediaRouterCallback");
        r3.zzeve.addCallback(r4, r3.zzevh, 4);
        r3.mNotification = com.google.android.gms.cast.CastRemoteDisplayLocalService.NotificationSettings.zzb(r7);
        r3.zzeux = new com.google.android.gms.cast.CastRemoteDisplayLocalService.zzb((com.google.android.gms.cast.zzu) null);
        r4 = new android.content.IntentFilter();
        r4.addAction("com.google.android.gms.cast.remote_display.ACTION_NOTIFICATION_DISCONNECT");
        r4.addAction("com.google.android.gms.cast.remote_display.ACTION_SESSION_ENDED");
        registerReceiver(r3.zzeux, r4);
        r4 = new com.google.android.gms.cast.CastRemoteDisplayLocalService.NotificationSettings(r7, (com.google.android.gms.cast.zzu) null);
        r3.zzeuy = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x008b, code lost:
        if (com.google.android.gms.cast.CastRemoteDisplayLocalService.NotificationSettings.zzb(r4) != null) goto L_0x0094;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x008d, code lost:
        r3.zzeuz = true;
        r4 = zzay(false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0094, code lost:
        r3.zzeuz = false;
        r4 = com.google.android.gms.cast.CastRemoteDisplayLocalService.NotificationSettings.zzb(r3.zzeuy);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x009c, code lost:
        r3.mNotification = r4;
        startForeground(zzeut, r3.mNotification);
        zzeb("startRemoteDisplay");
        r4 = new android.content.Intent("com.google.android.gms.cast.remote_display.ACTION_SESSION_ENDED");
        r4.setPackage(r3.zzevc.getPackageName());
        r3.zzevg.startRemoteDisplay(r5, r3.zzetb, r6.getConfigPreset(), android.app.PendingIntent.getBroadcast(r3, 0, r4, 0)).addOnCompleteListener(new com.google.android.gms.cast.zzz(r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x00d8, code lost:
        if (r3.zzeuw.get() == null) goto L_0x00e5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x00da, code lost:
        ((com.google.android.gms.cast.CastRemoteDisplayLocalService.Callbacks) r3.zzeuw.get()).onServiceCreated(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00e5, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zza(java.lang.String r4, com.google.android.gms.cast.CastDevice r5, com.google.android.gms.cast.CastRemoteDisplayLocalService.Options r6, com.google.android.gms.cast.CastRemoteDisplayLocalService.NotificationSettings r7, android.content.Context r8, android.content.ServiceConnection r9, com.google.android.gms.cast.CastRemoteDisplayLocalService.Callbacks r10) {
        /*
            r3 = this;
            java.lang.String r0 = "startRemoteDisplaySession"
            r3.zzeb(r0)
            java.lang.String r0 = "Starting the Cast Remote Display must be done on the main thread"
            com.google.android.gms.common.internal.zzbq.zzgn(r0)
            java.lang.Object r0 = zzeuu
            monitor-enter(r0)
            com.google.android.gms.cast.CastRemoteDisplayLocalService r1 = zzevi     // Catch:{ all -> 0x00e6 }
            r2 = 0
            if (r1 == 0) goto L_0x001d
            com.google.android.gms.internal.zzbei r4 = zzeus     // Catch:{ all -> 0x00e6 }
            java.lang.String r5 = "An existing service had not been stopped before starting one"
            java.lang.Object[] r6 = new java.lang.Object[r2]     // Catch:{ all -> 0x00e6 }
            r4.zzf(r5, r6)     // Catch:{ all -> 0x00e6 }
            monitor-exit(r0)     // Catch:{ all -> 0x00e6 }
            return r2
        L_0x001d:
            zzevi = r3     // Catch:{ all -> 0x00e6 }
            monitor-exit(r0)     // Catch:{ all -> 0x00e6 }
            java.lang.ref.WeakReference r0 = new java.lang.ref.WeakReference
            r0.<init>(r10)
            r3.zzeuw = r0
            r3.zzetb = r4
            r3.zzevb = r5
            r3.zzevc = r8
            r3.zzevd = r9
            androidx.mediarouter.media.MediaRouter r4 = r3.zzeve
            if (r4 != 0) goto L_0x003d
            android.content.Context r4 = r3.getApplicationContext()
            androidx.mediarouter.media.MediaRouter r4 = androidx.mediarouter.media.MediaRouter.getInstance(r4)
            r3.zzeve = r4
        L_0x003d:
            androidx.mediarouter.media.MediaRouteSelector$Builder r4 = new androidx.mediarouter.media.MediaRouteSelector$Builder
            r4.<init>()
            java.lang.String r8 = r3.zzetb
            java.lang.String r8 = com.google.android.gms.cast.CastMediaControlIntent.categoryForCast((java.lang.String) r8)
            androidx.mediarouter.media.MediaRouteSelector$Builder r4 = r4.addControlCategory(r8)
            androidx.mediarouter.media.MediaRouteSelector r4 = r4.build()
            java.lang.String r8 = "addMediaRouterCallback"
            r3.zzeb(r8)
            androidx.mediarouter.media.MediaRouter r8 = r3.zzeve
            androidx.mediarouter.media.MediaRouter$Callback r9 = r3.zzevh
            r10 = 4
            r8.addCallback(r4, r9, r10)
            android.app.Notification r4 = r7.mNotification
            r3.mNotification = r4
            com.google.android.gms.cast.CastRemoteDisplayLocalService$zzb r4 = new com.google.android.gms.cast.CastRemoteDisplayLocalService$zzb
            r8 = 0
            r4.<init>(r8)
            r3.zzeux = r4
            android.content.IntentFilter r4 = new android.content.IntentFilter
            r4.<init>()
            java.lang.String r9 = "com.google.android.gms.cast.remote_display.ACTION_NOTIFICATION_DISCONNECT"
            r4.addAction(r9)
            java.lang.String r9 = "com.google.android.gms.cast.remote_display.ACTION_SESSION_ENDED"
            r4.addAction(r9)
            com.google.android.gms.cast.CastRemoteDisplayLocalService$zzb r9 = r3.zzeux
            r3.registerReceiver(r9, r4)
            com.google.android.gms.cast.CastRemoteDisplayLocalService$NotificationSettings r4 = new com.google.android.gms.cast.CastRemoteDisplayLocalService$NotificationSettings
            r4.<init>(r7, r8)
            r3.zzeuy = r4
            android.app.Notification r4 = r4.mNotification
            r7 = 1
            if (r4 != 0) goto L_0x0094
            r3.zzeuz = r7
            android.app.Notification r4 = r3.zzay(r2)
            goto L_0x009c
        L_0x0094:
            r3.zzeuz = r2
            com.google.android.gms.cast.CastRemoteDisplayLocalService$NotificationSettings r4 = r3.zzeuy
            android.app.Notification r4 = r4.mNotification
        L_0x009c:
            r3.mNotification = r4
            int r4 = zzeut
            android.app.Notification r8 = r3.mNotification
            r3.startForeground(r4, r8)
            java.lang.String r4 = "startRemoteDisplay"
            r3.zzeb(r4)
            android.content.Intent r4 = new android.content.Intent
            java.lang.String r8 = "com.google.android.gms.cast.remote_display.ACTION_SESSION_ENDED"
            r4.<init>(r8)
            android.content.Context r8 = r3.zzevc
            java.lang.String r8 = r8.getPackageName()
            r4.setPackage(r8)
            android.app.PendingIntent r4 = android.app.PendingIntent.getBroadcast(r3, r2, r4, r2)
            com.google.android.gms.cast.CastRemoteDisplayClient r8 = r3.zzevg
            java.lang.String r9 = r3.zzetb
            int r6 = r6.getConfigPreset()
            com.google.android.gms.tasks.Task r4 = r8.startRemoteDisplay(r5, r9, r6, r4)
            com.google.android.gms.cast.zzz r5 = new com.google.android.gms.cast.zzz
            r5.<init>(r3)
            r4.addOnCompleteListener(r5)
            java.lang.ref.WeakReference<com.google.android.gms.cast.CastRemoteDisplayLocalService$Callbacks> r4 = r3.zzeuw
            java.lang.Object r4 = r4.get()
            if (r4 == 0) goto L_0x00e5
            java.lang.ref.WeakReference<com.google.android.gms.cast.CastRemoteDisplayLocalService$Callbacks> r4 = r3.zzeuw
            java.lang.Object r4 = r4.get()
            com.google.android.gms.cast.CastRemoteDisplayLocalService$Callbacks r4 = (com.google.android.gms.cast.CastRemoteDisplayLocalService.Callbacks) r4
            r4.onServiceCreated(r3)
        L_0x00e5:
            return r7
        L_0x00e6:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00e6 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.CastRemoteDisplayLocalService.zza(java.lang.String, com.google.android.gms.cast.CastDevice, com.google.android.gms.cast.CastRemoteDisplayLocalService$Options, com.google.android.gms.cast.CastRemoteDisplayLocalService$NotificationSettings, android.content.Context, android.content.ServiceConnection, com.google.android.gms.cast.CastRemoteDisplayLocalService$Callbacks):boolean");
    }

    /* access modifiers changed from: private */
    public final void zzadp() {
        if (this.zzeuw.get() != null) {
            ((Callbacks) this.zzeuw.get()).onRemoteDisplaySessionError(new Status(CastStatusCodes.ERROR_SERVICE_CREATION_FAILED));
        }
        stopService();
    }

    /* access modifiers changed from: private */
    public final void zzaw(boolean z) {
        ServiceConnection serviceConnection;
        zzeb("Stopping Service");
        zzbq.zzgn("stopServiceInstanceInternal must be called on the main thread");
        if (!z && this.zzeve != null) {
            zzeb("Setting default route");
            MediaRouter mediaRouter = this.zzeve;
            mediaRouter.selectRoute(mediaRouter.getDefaultRoute());
        }
        if (this.zzeux != null) {
            zzeb("Unregistering notification receiver");
            unregisterReceiver(this.zzeux);
        }
        zzeb("stopRemoteDisplaySession");
        zzeb("stopRemoteDisplay");
        this.zzevg.stopRemoteDisplay().addOnCompleteListener(new zzaa(this));
        if (this.zzeuw.get() != null) {
            ((Callbacks) this.zzeuw.get()).onRemoteDisplaySessionEnded(this);
        }
        onDismissPresentation();
        zzeb("Stopping the remote display Service");
        stopForeground(true);
        stopSelf();
        if (this.zzeve != null) {
            zzbq.zzgn("CastRemoteDisplayLocalService calls must be done on the main thread");
            zzeb("removeMediaRouterCallback");
            this.zzeve.removeCallback(this.zzevh);
        }
        Context context = this.zzevc;
        if (!(context == null || (serviceConnection = this.zzevd) == null)) {
            try {
                context.unbindService(serviceConnection);
            } catch (IllegalArgumentException unused) {
                zzeb("No need to unbind service, already unbound");
            }
            this.zzevd = null;
            this.zzevc = null;
        }
        this.zzetb = null;
        this.mNotification = null;
        this.zzdmh = null;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        if (r3.mHandler == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002f, code lost:
        if (android.os.Looper.myLooper() == android.os.Looper.getMainLooper()) goto L_0x003c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0031, code lost:
        r3.mHandler.post(new com.google.android.gms.cast.zzx(r3, r4));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003b, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003c, code lost:
        r3.zzaw(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void zzax(boolean r4) {
        /*
            com.google.android.gms.internal.zzbei r0 = zzeus
            java.lang.String r1 = "Stopping Service"
            r2 = 0
            java.lang.Object[] r3 = new java.lang.Object[r2]
            r0.zzb(r1, r3)
            java.util.concurrent.atomic.AtomicBoolean r1 = zzeuv
            r1.set(r2)
            java.lang.Object r1 = zzeuu
            monitor-enter(r1)
            com.google.android.gms.cast.CastRemoteDisplayLocalService r3 = zzevi     // Catch:{ all -> 0x0040 }
            if (r3 != 0) goto L_0x001f
            java.lang.String r4 = "Service is already being stopped"
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0040 }
            r0.zzc(r4, r2)     // Catch:{ all -> 0x0040 }
            monitor-exit(r1)     // Catch:{ all -> 0x0040 }
            return
        L_0x001f:
            r0 = 0
            zzevi = r0     // Catch:{ all -> 0x0040 }
            monitor-exit(r1)     // Catch:{ all -> 0x0040 }
            android.os.Handler r0 = r3.mHandler
            if (r0 == 0) goto L_0x003f
            android.os.Looper r0 = android.os.Looper.myLooper()
            android.os.Looper r1 = android.os.Looper.getMainLooper()
            if (r0 == r1) goto L_0x003c
            android.os.Handler r0 = r3.mHandler
            com.google.android.gms.cast.zzx r1 = new com.google.android.gms.cast.zzx
            r1.<init>(r3, r4)
            r0.post(r1)
            return
        L_0x003c:
            r3.zzaw(r4)
        L_0x003f:
            return
        L_0x0040:
            r4 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0040 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.CastRemoteDisplayLocalService.zzax(boolean):void");
    }

    private final Notification zzay(boolean z) {
        int i;
        int i2;
        zzeb("createDefaultNotification");
        String zzc = this.zzeuy.zzevs;
        String zzd = this.zzeuy.zzevt;
        if (z) {
            i = R.string.cast_notification_connected_message;
            i2 = R.drawable.cast_ic_notification_on;
        } else {
            i = R.string.cast_notification_connecting_message;
            i2 = R.drawable.cast_ic_notification_connecting;
        }
        if (TextUtils.isEmpty(zzc)) {
            zzc = (String) getPackageManager().getApplicationLabel(getApplicationInfo());
        }
        if (TextUtils.isEmpty(zzd)) {
            zzd = getString(i, new Object[]{this.zzevb.getFriendlyName()});
        }
        NotificationCompat.Builder ongoing = new NotificationCompat.Builder((Context) this, "cast_remote_display_local_service").setContentTitle(zzc).setContentText(zzd).setContentIntent(this.zzeuy.zzevr).setSmallIcon(i2).setOngoing(true);
        String string = getString(R.string.cast_notification_disconnect);
        if (this.zzeva == null) {
            Intent intent = new Intent("com.google.android.gms.cast.remote_display.ACTION_NOTIFICATION_DISCONNECT");
            intent.setPackage(this.zzevc.getPackageName());
            this.zzeva = PendingIntent.getBroadcast(this, 0, intent, 134217728);
        }
        return ongoing.addAction(17301560, string, this.zzeva).build();
    }

    /* access modifiers changed from: private */
    public final void zzeb(String str) {
        zzeus.zzb("[Instance: %s] %s", this, str);
    }

    /* access modifiers changed from: private */
    public final void zzee(String str) {
        zzeus.zzc("[Instance: %s] %s", this, str);
    }

    /* access modifiers changed from: protected */
    public Display getDisplay() {
        return this.zzdmh;
    }

    public IBinder onBind(Intent intent) {
        zzeb("onBind");
        return this.zzevj;
    }

    public void onCreate() {
        zzeb("onCreate");
        super.onCreate();
        Handler handler = new Handler(getMainLooper());
        this.mHandler = handler;
        handler.postDelayed(new zzv(this), 100);
        if (this.zzevg == null) {
            this.zzevg = CastRemoteDisplay.getClient(this);
        }
        if (zzs.isAtLeastO()) {
            NotificationChannel notificationChannel = new NotificationChannel("cast_remote_display_local_service", getString(R.string.cast_notification_default_channel_name), 2);
            notificationChannel.setShowBadge(false);
            ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel);
        }
    }

    public abstract void onCreatePresentation(Display display);

    public abstract void onDismissPresentation();

    public int onStartCommand(Intent intent, int i, int i2) {
        zzeb("onStartCommand");
        this.zzevf = true;
        return 2;
    }

    public void updateNotificationSettings(NotificationSettings notificationSettings) {
        zzbq.checkNotNull(notificationSettings, "notificationSettings is required.");
        zzbq.checkNotNull(this.mHandler, "Service is not ready yet.");
        this.mHandler.post(new zzy(this, notificationSettings));
    }
}
