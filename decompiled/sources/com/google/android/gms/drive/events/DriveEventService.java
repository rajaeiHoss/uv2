package com.google.android.gms.drive.events;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzal;
import com.google.android.gms.common.util.zzz;
import com.google.android.gms.internal.zzbrp;
import com.google.android.gms.internal.zzbsf;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class DriveEventService extends Service implements ChangeListener, CompletionListener, zzd, zzi {
    public static final String ACTION_HANDLE_EVENT = "com.google.android.gms.drive.events.HANDLE_EVENT";
    /* access modifiers changed from: private */
    public static final zzal zzgpv = new zzal("DriveEventService", "");
    private final String mName;
    /* access modifiers changed from: private */
    public CountDownLatch zzgsa;
    zza zzgsb;
    boolean zzgsc;
    private int zzgsd;

    final class zza extends Handler {
        zza() {
        }

        /* access modifiers changed from: private */
        public final Message zzaqd() {
            return obtainMessage(2);
        }

        /* access modifiers changed from: private */
        public final Message zzb(zzbsf zzbsf) {
            return obtainMessage(1, zzbsf);
        }

        public final void handleMessage(Message message) {
            DriveEventService.zzgpv.zzb("DriveEventService", "handleMessage message type: %s", Integer.valueOf(message.what));
            int i = message.what;
            if (i == 1) {
                DriveEventService.this.zza((zzbsf) message.obj);
            } else if (i != 2) {
                DriveEventService.zzgpv.zzc("DriveEventService", "Unexpected message type: %s", Integer.valueOf(message.what));
            } else {
                getLooper().quit();
            }
        }
    }

    final class zzb extends zzbrp {
        zzb() {
        }

        public final void zzc(zzbsf zzbsf) throws RemoteException {
            synchronized (DriveEventService.this) {
                DriveEventService.zzgpv.zzb("DriveEventService", "onEvent: %s", zzbsf);
                DriveEventService.this.zzaqb();
                if (DriveEventService.this.zzgsb != null) {
                    DriveEventService.this.zzgsb.sendMessage(DriveEventService.this.zzgsb.zzb(zzbsf));
                } else {
                    DriveEventService.zzgpv.zzw("DriveEventService", "Receiving event before initialize is completed.");
                }
            }
        }
    }

    protected DriveEventService() {
        this(DriveEventService.class.getSimpleName());
    }

    protected DriveEventService(String str) {
        this.zzgsc = false;
        this.zzgsd = -1;
        this.mName = str;
    }

    /* access modifiers changed from: private */
    public final void zza(zzbsf zzbsf) {
        DriveEvent zzaqq = zzbsf.zzaqq();
        zzal zzal = zzgpv;
        zzal.zzb("DriveEventService", "handleEventMessage: %s", zzaqq);
        try {
            int type = zzaqq.getType();
            if (type == 1) {
                onChange((ChangeEvent) zzaqq);
            } else if (type == 2) {
                onCompletion((CompletionEvent) zzaqq);
            } else if (type == 4) {
                zza((com.google.android.gms.drive.events.zzb) zzaqq);
            } else if (type != 7) {
                zzal.zzc("DriveEventService", "Unhandled event: %s", zzaqq);
            } else {
                zzal.zzc("DriveEventService", "Unhandled transfer state event in %s: %s", this.mName, (zzv) zzaqq);
            }
        } catch (Exception e) {
            zzgpv.zzd("DriveEventService", String.format("Error handling event in %s", new Object[]{this.mName}), (Throwable) e);
        }
    }

    /* access modifiers changed from: private */
    public final void zzaqb() throws SecurityException {
        int callingUid = getCallingUid();
        if (callingUid != this.zzgsd) {
            if (zzz.zze(this, callingUid)) {
                this.zzgsd = callingUid;
                return;
            }
            throw new SecurityException("Caller is not GooglePlayServices");
        }
    }

    /* access modifiers changed from: protected */
    public int getCallingUid() {
        return Binder.getCallingUid();
    }

    public final synchronized IBinder onBind(Intent intent) {
        if (!ACTION_HANDLE_EVENT.equals(intent.getAction())) {
            return null;
        }
        if (this.zzgsb == null && !this.zzgsc) {
            this.zzgsc = true;
            CountDownLatch countDownLatch = new CountDownLatch(1);
            this.zzgsa = new CountDownLatch(1);
            new zzh(this, countDownLatch).start();
            try {
                if (!countDownLatch.await(5000, TimeUnit.MILLISECONDS)) {
                    zzgpv.zzw("DriveEventService", "Failed to synchronously initialize event handler.");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException("Unable to start event handler", e);
            }
        }
        return new zzb().asBinder();
    }

    public void onChange(ChangeEvent changeEvent) {
        zzgpv.zzc("DriveEventService", "Unhandled change event in %s: %s", this.mName, changeEvent);
    }

    public void onCompletion(CompletionEvent completionEvent) {
        zzgpv.zzc("DriveEventService", "Unhandled completion event in %s: %s", this.mName, completionEvent);
    }

    public void zza(com.google.android.gms.drive.events.zzb zzb) {
        zzgpv.zzc("DriveEventService", "Unhandled changes available event in %s: %s", this.mName, zzb);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:4|5|6|(1:8)|9|10) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void onDestroy() {
        /*
            r6 = this;
            monitor-enter(r6)
            com.google.android.gms.common.internal.zzal r0 = zzgpv     // Catch:{ all -> 0x0034 }
            java.lang.String r1 = "DriveEventService"
            java.lang.String r2 = "onDestroy"
            r0.zzu(r1, r2)     // Catch:{ all -> 0x0034 }
            com.google.android.gms.drive.events.DriveEventService$zza r1 = r6.zzgsb     // Catch:{ all -> 0x0034 }
            if (r1 == 0) goto L_0x002f
            android.os.Message r1 = r1.zzaqd()     // Catch:{ all -> 0x0034 }
            com.google.android.gms.drive.events.DriveEventService$zza r2 = r6.zzgsb     // Catch:{ all -> 0x0034 }
            r2.sendMessage(r1)     // Catch:{ all -> 0x0034 }
            r1 = 0
            r6.zzgsb = r1     // Catch:{ all -> 0x0034 }
            java.util.concurrent.CountDownLatch r2 = r6.zzgsa     // Catch:{ InterruptedException -> 0x002d }
            r3 = 5000(0x1388, double:2.4703E-320)
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ InterruptedException -> 0x002d }
            boolean r2 = r2.await(r3, r5)     // Catch:{ InterruptedException -> 0x002d }
            if (r2 != 0) goto L_0x002d
            java.lang.String r2 = "DriveEventService"
            java.lang.String r3 = "Failed to synchronously quit event handler. Will quit itself"
            r0.zzv(r2, r3)     // Catch:{ InterruptedException -> 0x002d }
        L_0x002d:
            r6.zzgsa = r1     // Catch:{ all -> 0x0034 }
        L_0x002f:
            super.onDestroy()     // Catch:{ all -> 0x0034 }
            monitor-exit(r6)
            return
        L_0x0034:
            r0 = move-exception
            monitor-exit(r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.drive.events.DriveEventService.onDestroy():void");
    }

    public boolean onUnbind(Intent intent) {
        return true;
    }

    public final void zza(zzb zzb2) {
        zzgpv.zzc("DriveEventService", "Unhandled changes available event in %s: %s", this.mName, zzb2);
    }
}
