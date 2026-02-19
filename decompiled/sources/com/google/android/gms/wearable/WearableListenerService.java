package com.google.android.gms.wearable;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.CapabilityApi;
import com.google.android.gms.wearable.ChannelApi;
import com.google.android.gms.wearable.ChannelClient;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.internal.zzah;
import com.google.android.gms.wearable.internal.zzas;
import com.google.android.gms.wearable.internal.zzaw;
import com.google.android.gms.wearable.internal.zzen;
import com.google.android.gms.wearable.internal.zzfe;
import com.google.android.gms.wearable.internal.zzfo;
import com.google.android.gms.wearable.internal.zzi;
import java.util.List;

public class WearableListenerService extends Service implements CapabilityApi.CapabilityListener, ChannelApi.ChannelListener, DataApi.DataListener, MessageApi.MessageListener {
    public static final String BIND_LISTENER_INTENT_ACTION = "com.google.android.gms.wearable.BIND_LISTENER";
    private IBinder zzgfp;
    /* access modifiers changed from: private */
    public ComponentName zzlrg;
    /* access modifiers changed from: private */
    public zzc zzlrh;
    /* access modifiers changed from: private */
    public Intent zzlri;
    private Looper zzlrj;
    /* access modifiers changed from: private */
    public final Object zzlrk = new Object();
    /* access modifiers changed from: private */
    public boolean zzlrl;
    /* access modifiers changed from: private */
    public zzas zzlrm = new zzas(new zza());

    class zza extends ChannelClient.ChannelCallback {
        private zza() {
        }

        public final void onChannelClosed(ChannelClient.Channel channel, int i, int i2) {
            WearableListenerService.this.onChannelClosed(channel, i, i2);
        }

        public final void onChannelOpened(ChannelClient.Channel channel) {
            WearableListenerService.this.onChannelOpened(channel);
        }

        public final void onInputClosed(ChannelClient.Channel channel, int i, int i2) {
            WearableListenerService.this.onInputClosed(channel, i, i2);
        }

        public final void onOutputClosed(ChannelClient.Channel channel, int i, int i2) {
            WearableListenerService.this.onOutputClosed(channel, i, i2);
        }
    }

    class zzb implements ServiceConnection {
        private zzb() {
        }

        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        }

        public final void onServiceDisconnected(ComponentName componentName) {
        }
    }

    final class zzc extends Handler {
        private boolean started;
        private final zzb zzlro = new zzb();

        zzc(Looper looper) {
            super(looper);
        }

        private final synchronized void zzblx() {
            if (!this.started) {
                if (Log.isLoggable("WearableLS", 2)) {
                    String valueOf = String.valueOf(WearableListenerService.this.zzlrg);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 13);
                    sb.append("bindService: ");
                    sb.append(valueOf);
                    Log.v("WearableLS", sb.toString());
                }
                WearableListenerService wearableListenerService = WearableListenerService.this;
                wearableListenerService.bindService(wearableListenerService.zzlri, this.zzlro, 1);
                this.started = true;
            }
        }

        private final synchronized void zzod(String str) {
            if (this.started) {
                if (Log.isLoggable("WearableLS", 2)) {
                    String valueOf = String.valueOf(WearableListenerService.this.zzlrg);
                    StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 17 + String.valueOf(valueOf).length());
                    sb.append("unbindService: ");
                    sb.append(str);
                    sb.append(", ");
                    sb.append(valueOf);
                    Log.v("WearableLS", sb.toString());
                }
                try {
                    WearableListenerService.this.unbindService(this.zzlro);
                } catch (RuntimeException e) {
                    Log.e("WearableLS", "Exception when unbinding from local service", e);
                }
                this.started = false;
            }
        }

        public final void dispatchMessage(Message message) {
            zzblx();
            try {
                super.dispatchMessage(message);
            } finally {
                if (!hasMessages(0)) {
                    zzod("dispatch");
                }
            }
        }

        /* access modifiers changed from: package-private */
        public final void quit() {
            getLooper().quit();
            zzod("quit");
        }
    }

    final class zzd extends zzen {
        private volatile int zzlrp;

        private zzd() {
            this.zzlrp = -1;
        }

        /* access modifiers changed from: package-private */
        public final WearableListenerService zzbly() {
            return WearableListenerService.this;
        }

        /* access modifiers changed from: package-private */
        public final zzas zzblz() {
            return WearableListenerService.this.zzlrm;
        }

        /* JADX WARNING: Removed duplicated region for block: B:15:0x0072 A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:16:0x0073  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private final boolean zza(java.lang.Runnable r6, java.lang.String r7, java.lang.Object r8) {
            /*
                r5 = this;
                java.lang.String r0 = "WearableLS"
                r1 = 3
                boolean r0 = android.util.Log.isLoggable(r0, r1)
                r2 = 0
                r3 = 1
                if (r0 == 0) goto L_0x0029
                java.lang.String r0 = "WearableLS"
                java.lang.String r4 = "%s: %s %s"
                java.lang.Object[] r1 = new java.lang.Object[r1]
                r1[r2] = r7
                com.google.android.gms.wearable.WearableListenerService r7 = com.google.android.gms.wearable.WearableListenerService.this
                android.content.ComponentName r7 = r7.zzlrg
                java.lang.String r7 = r7.toString()
                r1[r3] = r7
                r7 = 2
                r1[r7] = r8
                java.lang.String r7 = java.lang.String.format(r4, r1)
                android.util.Log.d(r0, r7)
            L_0x0029:
                int r7 = android.os.Binder.getCallingUid()
                int r8 = r5.zzlrp
                if (r7 != r8) goto L_0x0033
            L_0x0031:
                r7 = 1
                goto L_0x0070
            L_0x0033:
                com.google.android.gms.wearable.WearableListenerService r8 = com.google.android.gms.wearable.WearableListenerService.this
                com.google.android.gms.wearable.internal.zzhp r8 = com.google.android.gms.wearable.internal.zzhp.zzeu(r8)
                java.lang.String r0 = "com.google.android.wearable.app.cn"
                boolean r8 = r8.zzof(r0)
                if (r8 == 0) goto L_0x004c
                com.google.android.gms.wearable.WearableListenerService r8 = com.google.android.gms.wearable.WearableListenerService.this
                java.lang.String r0 = "com.google.android.wearable.app.cn"
                boolean r8 = com.google.android.gms.common.util.zzz.zzb(r8, r7, r0)
                if (r8 == 0) goto L_0x004c
                goto L_0x0054
            L_0x004c:
                com.google.android.gms.wearable.WearableListenerService r8 = com.google.android.gms.wearable.WearableListenerService.this
                boolean r8 = com.google.android.gms.common.util.zzz.zze(r8, r7)
                if (r8 == 0) goto L_0x0057
            L_0x0054:
                r5.zzlrp = r7
                goto L_0x0031
            L_0x0057:
                java.lang.String r8 = "WearableLS"
                r0 = 57
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>(r0)
                java.lang.String r0 = "Caller is not GooglePlayServices; caller UID: "
                r1.append(r0)
                r1.append(r7)
                java.lang.String r7 = r1.toString()
                android.util.Log.e(r8, r7)
                r7 = 0
            L_0x0070:
                if (r7 != 0) goto L_0x0073
                return r2
            L_0x0073:
                com.google.android.gms.wearable.WearableListenerService r7 = com.google.android.gms.wearable.WearableListenerService.this
                java.lang.Object r7 = r7.zzlrk
                monitor-enter(r7)
                com.google.android.gms.wearable.WearableListenerService r8 = com.google.android.gms.wearable.WearableListenerService.this     // Catch:{ all -> 0x008f }
                boolean r8 = r8.zzlrl     // Catch:{ all -> 0x008f }
                if (r8 == 0) goto L_0x0084
                monitor-exit(r7)     // Catch:{ all -> 0x008f }
                return r2
            L_0x0084:
                com.google.android.gms.wearable.WearableListenerService r8 = com.google.android.gms.wearable.WearableListenerService.this     // Catch:{ all -> 0x008f }
                com.google.android.gms.wearable.WearableListenerService$zzc r8 = r8.zzlrh     // Catch:{ all -> 0x008f }
                r8.post(r6)     // Catch:{ all -> 0x008f }
                monitor-exit(r7)     // Catch:{ all -> 0x008f }
                return r3
            L_0x008f:
                r6 = move-exception
                monitor-exit(r7)     // Catch:{ all -> 0x008f }
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.wearable.WearableListenerService.zzd.zza(java.lang.Runnable, java.lang.String, java.lang.Object):boolean");
        }

        public final void onConnectedNodes(List<zzfo> list) {
            zza(new zzp(this, list), "onConnectedNodes", list);
        }

        public final void zza(zzah zzah) {
            zza(new zzq(this, zzah), "onConnectedCapabilityChanged", zzah);
        }

        public final void zza(zzaw zzaw) {
            zza(new zzt(this, zzaw), "onChannelEvent", zzaw);
        }

        public final void zza(zzfe zzfe) {
            zza(new zzm(this, zzfe), "onMessageReceived", zzfe);
        }

        public final void zza(zzfo zzfo) {
            zza(new zzn(this, zzfo), "onPeerConnected", zzfo);
        }

        public final void zza(zzi zzi) {
            zza(new zzs(this, zzi), "onEntityUpdate", zzi);
        }

        public final void zza(com.google.android.gms.wearable.internal.zzl zzl) {
            zza(new zzr(this, zzl), "onNotificationReceived", zzl);
        }

        public final void zzb(zzfo zzfo) {
            zza(new zzo(this, zzfo), "onPeerDisconnected", zzfo);
        }

        public final void zzbo(DataHolder dataHolder) {
            zzl zzl = new zzl(this, dataHolder);
            try {
                String valueOf = String.valueOf(dataHolder);
                int count = dataHolder.getCount();
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 18);
                sb.append(valueOf);
                sb.append(", rows=");
                sb.append(count);
                if (zza(zzl, "onDataItemChanged", sb.toString())) {
                }
            } finally {
                dataHolder.close();
            }
        }
    }

    public Looper getLooper() {
        if (this.zzlrj == null) {
            HandlerThread handlerThread = new HandlerThread("WearableListenerService");
            handlerThread.start();
            this.zzlrj = handlerThread.getLooper();
        }
        return this.zzlrj;
    }

    public final IBinder onBind(Intent intent) {
        if (BIND_LISTENER_INTENT_ACTION.equals(intent.getAction())) {
            return this.zzgfp;
        }
        return null;
    }

    public void onCapabilityChanged(CapabilityInfo capabilityInfo) {
    }

    public void onChannelClosed(Channel channel, int i, int i2) {
    }

    public void onChannelClosed(ChannelClient.Channel channel, int i, int i2) {
    }

    public void onChannelOpened(Channel channel) {
    }

    public void onChannelOpened(ChannelClient.Channel channel) {
    }

    public void onConnectedNodes(List<Node> list) {
    }

    public void onCreate() {
        super.onCreate();
        this.zzlrg = new ComponentName(this, getClass().getName());
        if (Log.isLoggable("WearableLS", 3)) {
            String valueOf = String.valueOf(this.zzlrg);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 10);
            sb.append("onCreate: ");
            sb.append(valueOf);
            Log.d("WearableLS", sb.toString());
        }
        this.zzlrh = new zzc(getLooper());
        Intent intent = new Intent(BIND_LISTENER_INTENT_ACTION);
        this.zzlri = intent;
        intent.setComponent(this.zzlrg);
        this.zzgfp = new zzd();
    }

    public void onDataChanged(DataEventBuffer dataEventBuffer) {
    }

    public void onDestroy() {
        if (Log.isLoggable("WearableLS", 3)) {
            String valueOf = String.valueOf(this.zzlrg);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 11);
            sb.append("onDestroy: ");
            sb.append(valueOf);
            Log.d("WearableLS", sb.toString());
        }
        synchronized (this.zzlrk) {
            this.zzlrl = true;
            zzc zzc2 = this.zzlrh;
            if (zzc2 != null) {
                zzc2.quit();
            } else {
                String valueOf2 = String.valueOf(this.zzlrg);
                StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 111);
                sb2.append("onDestroy: mServiceHandler not set, did you override onCreate() but forget to call super.onCreate()? component=");
                sb2.append(valueOf2);
                throw new IllegalStateException(sb2.toString());
            }
        }
        super.onDestroy();
    }

    public void onEntityUpdate(com.google.android.gms.wearable.zzb zzb2) {
    }

    public void onInputClosed(Channel channel, int i, int i2) {
    }

    public void onInputClosed(ChannelClient.Channel channel, int i, int i2) {
    }

    public void onMessageReceived(MessageEvent messageEvent) {
    }

    public void onNotificationReceived(com.google.android.gms.wearable.zzd zzd2) {
    }

    public void onOutputClosed(Channel channel, int i, int i2) {
    }

    public void onOutputClosed(ChannelClient.Channel channel, int i, int i2) {
    }

    public void onPeerConnected(Node node) {
    }

    public void onPeerDisconnected(Node node) {
    }
}
