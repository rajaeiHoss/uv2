package com.google.android.gms.gcm;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.common.util.zzz;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

public abstract class GcmTaskService extends Service {
    public static final String SERVICE_ACTION_EXECUTE_TASK = "com.google.android.gms.gcm.ACTION_TASK_READY";
    public static final String SERVICE_ACTION_INITIALIZE = "com.google.android.gms.gcm.SERVICE_ACTION_INITIALIZE";
    public static final String SERVICE_PERMISSION = "com.google.android.gms.permission.BIND_NETWORK_TASK_SERVICE";
    /* access modifiers changed from: private */
    public ComponentName componentName;
    /* access modifiers changed from: private */
    public final Object lock = new Object();
    private ExecutorService zzair;
    /* access modifiers changed from: private */
    public int zzijt;
    private Messenger zziju;
    /* access modifiers changed from: private */
    public GcmNetworkManager zzijv;

    class zza extends Handler {
        zza(Looper looper) {
            super(looper);
        }

        public final void handleMessage(Message message) {
            Messenger messenger;
            if (!zzz.zzb(GcmTaskService.this, message.sendingUid, "com.google.android.gms")) {
                Log.e("GcmTaskService", "unable to verify presence of Google Play Services");
                return;
            }
            int i = message.what;
            if (i == 1) {
                Bundle data = message.getData();
                if (!data.isEmpty() && (messenger = message.replyTo) != null) {
                    String string = data.getString("tag");
                    ArrayList parcelableArrayList = data.getParcelableArrayList("triggered_uris");
                    if (!GcmTaskService.this.zzig(string)) {
                        GcmTaskService.this.zza(new zzb(string, messenger, data.getBundle("extras"), (List<Uri>) parcelableArrayList));
                    }
                }
            } else if (i != 2) {
                if (i != 4) {
                    String valueOf = String.valueOf(message);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 31);
                    sb.append("Unrecognized message received: ");
                    sb.append(valueOf);
                    Log.e("GcmTaskService", sb.toString());
                    return;
                }
                GcmTaskService.this.onInitializeTasks();
            } else if (Log.isLoggable("GcmTaskService", 3)) {
                String valueOf2 = String.valueOf(message);
                StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 45);
                sb2.append("ignoring unimplemented stop message for now: ");
                sb2.append(valueOf2);
                Log.d("GcmTaskService", sb2.toString());
            }
        }
    }

    class zzb implements Runnable {
        private final Bundle mExtras;
        private final Messenger mMessenger;
        private final String mTag;
        private final List<Uri> zzijy;
        private final zzd zzijz;

        zzb(String str, IBinder iBinder, Bundle bundle, List<Uri> list) {
            zzd zzd;
            this.mTag = str;
            if (iBinder == null) {
                zzd = null;
            } else {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.gcm.INetworkTaskCallback");
                zzd = queryLocalInterface instanceof zzd ? (zzd) queryLocalInterface : new zze(iBinder);
            }
            this.zzijz = zzd;
            this.mExtras = bundle;
            this.zzijy = list;
            this.mMessenger = null;
        }

        zzb(String str, Messenger messenger, Bundle bundle, List<Uri> list) {
            this.mTag = str;
            this.mMessenger = messenger;
            this.mExtras = bundle;
            this.zzijy = list;
            this.zzijz = null;
        }

        private final boolean zzawg() {
            return this.mMessenger != null;
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x005a, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void zzea(int r6) {
            /*
                r5 = this;
                com.google.android.gms.gcm.GcmTaskService r0 = com.google.android.gms.gcm.GcmTaskService.this
                java.lang.Object r0 = r0.lock
                monitor-enter(r0)
                com.google.android.gms.gcm.GcmTaskService r1 = com.google.android.gms.gcm.GcmTaskService.this     // Catch:{ RemoteException -> 0x00cc }
                com.google.android.gms.gcm.GcmNetworkManager r1 = r1.zzijv     // Catch:{ RemoteException -> 0x00cc }
                java.lang.String r2 = r5.mTag     // Catch:{ RemoteException -> 0x00cc }
                com.google.android.gms.gcm.GcmTaskService r3 = com.google.android.gms.gcm.GcmTaskService.this     // Catch:{ RemoteException -> 0x00cc }
                android.content.ComponentName r3 = r3.componentName     // Catch:{ RemoteException -> 0x00cc }
                java.lang.String r3 = r3.getClassName()     // Catch:{ RemoteException -> 0x00cc }
                boolean r1 = r1.zzac(r2, r3)     // Catch:{ RemoteException -> 0x00cc }
                if (r1 == 0) goto L_0x005b
                com.google.android.gms.gcm.GcmTaskService r6 = com.google.android.gms.gcm.GcmTaskService.this     // Catch:{ all -> 0x015f }
                com.google.android.gms.gcm.GcmNetworkManager r6 = r6.zzijv     // Catch:{ all -> 0x015f }
                java.lang.String r1 = r5.mTag     // Catch:{ all -> 0x015f }
                com.google.android.gms.gcm.GcmTaskService r2 = com.google.android.gms.gcm.GcmTaskService.this     // Catch:{ all -> 0x015f }
                android.content.ComponentName r2 = r2.componentName     // Catch:{ all -> 0x015f }
                java.lang.String r2 = r2.getClassName()     // Catch:{ all -> 0x015f }
                r6.zzab(r1, r2)     // Catch:{ all -> 0x015f }
                boolean r6 = r5.zzawg()     // Catch:{ all -> 0x015f }
                if (r6 != 0) goto L_0x0059
                com.google.android.gms.gcm.GcmTaskService r6 = com.google.android.gms.gcm.GcmTaskService.this     // Catch:{ all -> 0x015f }
                com.google.android.gms.gcm.GcmNetworkManager r6 = r6.zzijv     // Catch:{ all -> 0x015f }
                com.google.android.gms.gcm.GcmTaskService r1 = com.google.android.gms.gcm.GcmTaskService.this     // Catch:{ all -> 0x015f }
                android.content.ComponentName r1 = r1.componentName     // Catch:{ all -> 0x015f }
                java.lang.String r1 = r1.getClassName()     // Catch:{ all -> 0x015f }
                boolean r6 = r6.zzif(r1)     // Catch:{ all -> 0x015f }
                if (r6 != 0) goto L_0x0059
                com.google.android.gms.gcm.GcmTaskService r6 = com.google.android.gms.gcm.GcmTaskService.this     // Catch:{ all -> 0x015f }
                int r1 = r6.zzijt     // Catch:{ all -> 0x015f }
                r6.stopSelf(r1)     // Catch:{ all -> 0x015f }
            L_0x0059:
                monitor-exit(r0)     // Catch:{ all -> 0x015f }
                return
            L_0x005b:
                boolean r1 = r5.zzawg()     // Catch:{ RemoteException -> 0x00cc }
                if (r1 == 0) goto L_0x008a
                android.os.Messenger r1 = r5.mMessenger     // Catch:{ RemoteException -> 0x00cc }
                android.os.Message r2 = android.os.Message.obtain()     // Catch:{ RemoteException -> 0x00cc }
                r3 = 3
                r2.what = r3     // Catch:{ RemoteException -> 0x00cc }
                r2.arg1 = r6     // Catch:{ RemoteException -> 0x00cc }
                android.os.Bundle r6 = new android.os.Bundle     // Catch:{ RemoteException -> 0x00cc }
                r6.<init>()     // Catch:{ RemoteException -> 0x00cc }
                java.lang.String r3 = "component"
                com.google.android.gms.gcm.GcmTaskService r4 = com.google.android.gms.gcm.GcmTaskService.this     // Catch:{ RemoteException -> 0x00cc }
                android.content.ComponentName r4 = r4.componentName     // Catch:{ RemoteException -> 0x00cc }
                r6.putParcelable(r3, r4)     // Catch:{ RemoteException -> 0x00cc }
                java.lang.String r3 = "tag"
                java.lang.String r4 = r5.mTag     // Catch:{ RemoteException -> 0x00cc }
                r6.putString(r3, r4)     // Catch:{ RemoteException -> 0x00cc }
                r2.setData(r6)     // Catch:{ RemoteException -> 0x00cc }
                r1.send(r2)     // Catch:{ RemoteException -> 0x00cc }
                goto L_0x008f
            L_0x008a:
                com.google.android.gms.gcm.zzd r1 = r5.zzijz     // Catch:{ RemoteException -> 0x00cc }
                r1.zzeb(r6)     // Catch:{ RemoteException -> 0x00cc }
            L_0x008f:
                com.google.android.gms.gcm.GcmTaskService r6 = com.google.android.gms.gcm.GcmTaskService.this     // Catch:{ all -> 0x015f }
                com.google.android.gms.gcm.GcmNetworkManager r6 = r6.zzijv     // Catch:{ all -> 0x015f }
                java.lang.String r1 = r5.mTag     // Catch:{ all -> 0x015f }
                com.google.android.gms.gcm.GcmTaskService r2 = com.google.android.gms.gcm.GcmTaskService.this     // Catch:{ all -> 0x015f }
                android.content.ComponentName r2 = r2.componentName     // Catch:{ all -> 0x015f }
                java.lang.String r2 = r2.getClassName()     // Catch:{ all -> 0x015f }
                r6.zzab(r1, r2)     // Catch:{ all -> 0x015f }
                boolean r6 = r5.zzawg()     // Catch:{ all -> 0x015f }
                if (r6 != 0) goto L_0x0122
                com.google.android.gms.gcm.GcmTaskService r6 = com.google.android.gms.gcm.GcmTaskService.this     // Catch:{ all -> 0x015f }
                com.google.android.gms.gcm.GcmNetworkManager r6 = r6.zzijv     // Catch:{ all -> 0x015f }
                com.google.android.gms.gcm.GcmTaskService r1 = com.google.android.gms.gcm.GcmTaskService.this     // Catch:{ all -> 0x015f }
                android.content.ComponentName r1 = r1.componentName     // Catch:{ all -> 0x015f }
                java.lang.String r1 = r1.getClassName()     // Catch:{ all -> 0x015f }
                boolean r6 = r6.zzif(r1)     // Catch:{ all -> 0x015f }
                if (r6 != 0) goto L_0x0122
                com.google.android.gms.gcm.GcmTaskService r6 = com.google.android.gms.gcm.GcmTaskService.this     // Catch:{ all -> 0x015f }
                int r1 = r6.zzijt     // Catch:{ all -> 0x015f }
            L_0x00c6:
                r6.stopSelf(r1)     // Catch:{ all -> 0x015f }
                goto L_0x0122
            L_0x00ca:
                r6 = move-exception
                goto L_0x0124
            L_0x00cc:
                java.lang.String r6 = "GcmTaskService"
                java.lang.String r1 = "Error reporting result of operation to scheduler for "
                java.lang.String r2 = r5.mTag     // Catch:{ all -> 0x00ca }
                java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x00ca }
                int r3 = r2.length()     // Catch:{ all -> 0x00ca }
                if (r3 == 0) goto L_0x00e1
                java.lang.String r1 = r1.concat(r2)     // Catch:{ all -> 0x00ca }
                goto L_0x00e7
            L_0x00e1:
                java.lang.String r2 = new java.lang.String     // Catch:{ all -> 0x00ca }
                r2.<init>(r1)     // Catch:{ all -> 0x00ca }
                r1 = r2
            L_0x00e7:
                android.util.Log.e(r6, r1)     // Catch:{ all -> 0x00ca }
                com.google.android.gms.gcm.GcmTaskService r6 = com.google.android.gms.gcm.GcmTaskService.this     // Catch:{ all -> 0x015f }
                com.google.android.gms.gcm.GcmNetworkManager r6 = r6.zzijv     // Catch:{ all -> 0x015f }
                java.lang.String r1 = r5.mTag     // Catch:{ all -> 0x015f }
                com.google.android.gms.gcm.GcmTaskService r2 = com.google.android.gms.gcm.GcmTaskService.this     // Catch:{ all -> 0x015f }
                android.content.ComponentName r2 = r2.componentName     // Catch:{ all -> 0x015f }
                java.lang.String r2 = r2.getClassName()     // Catch:{ all -> 0x015f }
                r6.zzab(r1, r2)     // Catch:{ all -> 0x015f }
                boolean r6 = r5.zzawg()     // Catch:{ all -> 0x015f }
                if (r6 != 0) goto L_0x0122
                com.google.android.gms.gcm.GcmTaskService r6 = com.google.android.gms.gcm.GcmTaskService.this     // Catch:{ all -> 0x015f }
                com.google.android.gms.gcm.GcmNetworkManager r6 = r6.zzijv     // Catch:{ all -> 0x015f }
                com.google.android.gms.gcm.GcmTaskService r1 = com.google.android.gms.gcm.GcmTaskService.this     // Catch:{ all -> 0x015f }
                android.content.ComponentName r1 = r1.componentName     // Catch:{ all -> 0x015f }
                java.lang.String r1 = r1.getClassName()     // Catch:{ all -> 0x015f }
                boolean r6 = r6.zzif(r1)     // Catch:{ all -> 0x015f }
                if (r6 != 0) goto L_0x0122
                com.google.android.gms.gcm.GcmTaskService r6 = com.google.android.gms.gcm.GcmTaskService.this     // Catch:{ all -> 0x015f }
                int r1 = r6.zzijt     // Catch:{ all -> 0x015f }
                goto L_0x00c6
            L_0x0122:
                monitor-exit(r0)     // Catch:{ all -> 0x015f }
                return
            L_0x0124:
                com.google.android.gms.gcm.GcmTaskService r1 = com.google.android.gms.gcm.GcmTaskService.this     // Catch:{ all -> 0x015f }
                com.google.android.gms.gcm.GcmNetworkManager r1 = r1.zzijv     // Catch:{ all -> 0x015f }
                java.lang.String r2 = r5.mTag     // Catch:{ all -> 0x015f }
                com.google.android.gms.gcm.GcmTaskService r3 = com.google.android.gms.gcm.GcmTaskService.this     // Catch:{ all -> 0x015f }
                android.content.ComponentName r3 = r3.componentName     // Catch:{ all -> 0x015f }
                java.lang.String r3 = r3.getClassName()     // Catch:{ all -> 0x015f }
                r1.zzab(r2, r3)     // Catch:{ all -> 0x015f }
                boolean r1 = r5.zzawg()     // Catch:{ all -> 0x015f }
                if (r1 != 0) goto L_0x015e
                com.google.android.gms.gcm.GcmTaskService r1 = com.google.android.gms.gcm.GcmTaskService.this     // Catch:{ all -> 0x015f }
                com.google.android.gms.gcm.GcmNetworkManager r1 = r1.zzijv     // Catch:{ all -> 0x015f }
                com.google.android.gms.gcm.GcmTaskService r2 = com.google.android.gms.gcm.GcmTaskService.this     // Catch:{ all -> 0x015f }
                android.content.ComponentName r2 = r2.componentName     // Catch:{ all -> 0x015f }
                java.lang.String r2 = r2.getClassName()     // Catch:{ all -> 0x015f }
                boolean r1 = r1.zzif(r2)     // Catch:{ all -> 0x015f }
                if (r1 != 0) goto L_0x015e
                com.google.android.gms.gcm.GcmTaskService r1 = com.google.android.gms.gcm.GcmTaskService.this     // Catch:{ all -> 0x015f }
                int r2 = r1.zzijt     // Catch:{ all -> 0x015f }
                r1.stopSelf(r2)     // Catch:{ all -> 0x015f }
            L_0x015e:
                throw r6     // Catch:{ all -> 0x015f }
            L_0x015f:
                r6 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x015f }
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.gcm.GcmTaskService.zzb.zzea(int):void");
        }

        public final void run() {
            zzea(GcmTaskService.this.onRunTask(new TaskParams(this.mTag, this.mExtras, this.zzijy)));
        }
    }

    /* access modifiers changed from: private */
    public final void zza(zzb zzb2) {
        try {
            this.zzair.execute(zzb2);
        } catch (RejectedExecutionException e) {
            Log.e("GcmTaskService", "Executor is shutdown. onDestroy was called but main looper had an unprocessed start task message. The task will be retried with backoff delay.", e);
            zzb2.zzea(1);
        }
    }

    private final void zzdz(int i) {
        synchronized (this.lock) {
            this.zzijt = i;
            if (!this.zzijv.zzif(this.componentName.getClassName())) {
                stopSelf(this.zzijt);
            }
        }
    }

    /* access modifiers changed from: private */
    public final boolean zzig(String str) {
        boolean z;
        synchronized (this.lock) {
            z = !this.zzijv.zzaa(str, this.componentName.getClassName());
            if (z) {
                String packageName = getPackageName();
                StringBuilder sb = new StringBuilder(String.valueOf(packageName).length() + 44 + String.valueOf(str).length());
                sb.append(packageName);
                sb.append(" ");
                sb.append(str);
                sb.append(": Task already running, won't start another");
                Log.w("GcmTaskService", sb.toString());
            }
        }
        return z;
    }

    public IBinder onBind(Intent intent) {
        if (intent == null || !zzs.zzanx() || !SERVICE_ACTION_EXECUTE_TASK.equals(intent.getAction())) {
            return null;
        }
        return this.zziju.getBinder();
    }

    public void onCreate() {
        super.onCreate();
        this.zzijv = GcmNetworkManager.getInstance(this);
        this.zzair = Executors.newFixedThreadPool(2);
        this.zziju = new Messenger(new zza(Looper.getMainLooper()));
        this.componentName = new ComponentName(this, getClass());
    }

    public void onDestroy() {
        super.onDestroy();
        List<Runnable> shutdownNow = this.zzair.shutdownNow();
        if (!shutdownNow.isEmpty()) {
            int size = shutdownNow.size();
            StringBuilder sb = new StringBuilder(79);
            sb.append("Shutting down, but not all tasks are finished executing. Remaining: ");
            sb.append(size);
            Log.e("GcmTaskService", sb.toString());
        }
    }

    public void onInitializeTasks() {
    }

    public abstract int onRunTask(TaskParams taskParams);

    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent == null) {
            zzdz(i2);
            return 2;
        }
        try {
            intent.setExtrasClassLoader(PendingCallback.class.getClassLoader());
            String action = intent.getAction();
            if (SERVICE_ACTION_EXECUTE_TASK.equals(action)) {
                String stringExtra = intent.getStringExtra("tag");
                Parcelable parcelableExtra = intent.getParcelableExtra("callback");
                Bundle bundleExtra = intent.getBundleExtra("extras");
                ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra("triggered_uris");
                if (!(parcelableExtra instanceof PendingCallback)) {
                    String packageName = getPackageName();
                    StringBuilder sb = new StringBuilder(String.valueOf(packageName).length() + 47 + String.valueOf(stringExtra).length());
                    sb.append(packageName);
                    sb.append(" ");
                    sb.append(stringExtra);
                    sb.append(": Could not process request, invalid callback.");
                    Log.e("GcmTaskService", sb.toString());
                    return 2;
                } else if (zzig(stringExtra)) {
                    zzdz(i2);
                    return 2;
                } else {
                    zza(new zzb(stringExtra, ((PendingCallback) parcelableExtra).zzgfp, bundleExtra, (List<Uri>) parcelableArrayListExtra));
                }
            } else if (SERVICE_ACTION_INITIALIZE.equals(action)) {
                onInitializeTasks();
            } else {
                StringBuilder sb2 = new StringBuilder(String.valueOf(action).length() + 37);
                sb2.append("Unknown action received ");
                sb2.append(action);
                sb2.append(", terminating");
                Log.e("GcmTaskService", sb2.toString());
            }
            zzdz(i2);
            return 2;
        } finally {
            zzdz(i2);
        }
    }
}
