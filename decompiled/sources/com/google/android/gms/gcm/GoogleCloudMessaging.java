package com.google.android.gms.gcm;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import androidx.collection.ArrayMap;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.iid.InstanceID;
import com.google.android.gms.iid.zzaa;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class GoogleCloudMessaging {
    public static final String ERROR_MAIN_THREAD = "MAIN_THREAD";
    public static final String ERROR_SERVICE_NOT_AVAILABLE = "SERVICE_NOT_AVAILABLE";
    public static final String INSTANCE_ID_SCOPE = "GCM";
    @Deprecated
    public static final String MESSAGE_TYPE_DELETED = "deleted_messages";
    @Deprecated
    public static final String MESSAGE_TYPE_MESSAGE = "gcm";
    @Deprecated
    public static final String MESSAGE_TYPE_SEND_ERROR = "send_error";
    @Deprecated
    public static final String MESSAGE_TYPE_SEND_EVENT = "send_event";
    private static final String SERVICE_DATA_ERROR = "error";
    private static GoogleCloudMessaging zzika;
    private static final AtomicInteger zzikd = new AtomicInteger(1);
    /* access modifiers changed from: private */
    public Context zzaiq;
    private PendingIntent zzikb;
    private final Map<String, Handler> zzikc = Collections.synchronizedMap(new ArrayMap());
    /* access modifiers changed from: private */
    public final BlockingQueue<Intent> zzike = new LinkedBlockingQueue();
    private Messenger zzikf = new Messenger(new zzc(this, Looper.getMainLooper()));

    public static synchronized GoogleCloudMessaging getInstance(Context context) {
        GoogleCloudMessaging googleCloudMessaging;
        synchronized (GoogleCloudMessaging.class) {
            if (zzika == null) {
                GoogleCloudMessaging googleCloudMessaging2 = new GoogleCloudMessaging();
                zzika = googleCloudMessaging2;
                googleCloudMessaging2.zzaiq = context.getApplicationContext();
            }
            googleCloudMessaging = zzika;
        }
        return googleCloudMessaging;
    }

    @Deprecated
    private final Intent zza(Bundle bundle, boolean z) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        } else if (zzdn(this.zzaiq) >= 0) {
            Intent intent = new Intent(z ? "com.google.iid.TOKEN_REQUEST" : "com.google.android.c2dm.intent.REGISTER");
            intent.setPackage(zzaa.zzdr(this.zzaiq));
            zzg(intent);
            int andIncrement = zzikd.getAndIncrement();
            StringBuilder sb = new StringBuilder(21);
            sb.append("google.rpc");
            sb.append(andIncrement);
            intent.putExtra("google.message_id", sb.toString());
            intent.putExtras(bundle);
            intent.putExtra("google.messenger", this.zzikf);
            Context context = this.zzaiq;
            if (z) {
                context.sendBroadcast(intent);
            } else {
                context.startService(intent);
            }
            try {
                return this.zzike.poll(NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                throw new IOException(e.getMessage());
            }
        } else {
            throw new IOException("Google Play Services missing");
        }
    }

    @Deprecated
    private final synchronized String zza(boolean z, String... strArr) throws IOException {
        String zzdr = zzaa.zzdr(this.zzaiq);
        if (zzdr != null) {
            String zzd = zzd(strArr);
            Bundle bundle = new Bundle();
            if (zzdr.contains(".gsf")) {
                bundle.putString("legacy.sender", zzd);
                return InstanceID.getInstance(this.zzaiq).getToken(zzd, INSTANCE_ID_SCOPE, bundle);
            }
            bundle.putString("sender", zzd);
            Intent zza = zza(bundle, z);
            if (zza != null) {
                String stringExtra = zza.getStringExtra("registration_id");
                if (stringExtra != null) {
                    return stringExtra;
                }
                String stringExtra2 = zza.getStringExtra(SERVICE_DATA_ERROR);
                if (stringExtra2 != null) {
                    throw new IOException(stringExtra2);
                }
                throw new IOException("SERVICE_NOT_AVAILABLE");
            }
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
        throw new IOException("SERVICE_NOT_AVAILABLE");
    }

    private final synchronized void zzawh() {
        PendingIntent pendingIntent = this.zzikb;
        if (pendingIntent != null) {
            pendingIntent.cancel();
            this.zzikb = null;
        }
    }

    private static String zzd(String... strArr) {
        if (strArr == null || strArr.length == 0) {
            throw new IllegalArgumentException("No senderIds");
        }
        StringBuilder sb = new StringBuilder(strArr[0]);
        for (int i = 1; i < strArr.length; i++) {
            sb.append(',');
            sb.append(strArr[i]);
        }
        return sb.toString();
    }

    public static int zzdn(Context context) {
        String zzdr = zzaa.zzdr(context);
        if (zzdr == null) {
            return -1;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(zzdr, 0);
            if (packageInfo != null) {
                return packageInfo.versionCode;
            }
            return -1;
        } catch (PackageManager.NameNotFoundException unused) {
            return -1;
        }
    }

    /* access modifiers changed from: private */
    public final boolean zzf(Intent intent) {
        Handler remove;
        String stringExtra = intent.getStringExtra("In-Reply-To");
        if (stringExtra == null && intent.hasExtra(SERVICE_DATA_ERROR)) {
            stringExtra = intent.getStringExtra("google.message_id");
        }
        if (stringExtra == null || (remove = this.zzikc.remove(stringExtra)) == null) {
            return false;
        }
        Message obtain = Message.obtain();
        obtain.obj = intent;
        return remove.sendMessage(obtain);
    }

    private final synchronized void zzg(Intent intent) {
        if (this.zzikb == null) {
            Intent intent2 = new Intent();
            intent2.setPackage("com.google.example.invalidpackage");
            this.zzikb = PendingIntent.getBroadcast(this.zzaiq, 0, intent2, 0);
        }
        intent.putExtra("app", this.zzikb);
    }

    public void close() {
        zzika = null;
        zza.zzijk = null;
        zzawh();
    }

    public String getMessageType(Intent intent) {
        if (!"com.google.android.c2dm.intent.RECEIVE".equals(intent.getAction())) {
            return null;
        }
        String stringExtra = intent.getStringExtra("message_type");
        return stringExtra != null ? stringExtra : "gcm";
    }

    @Deprecated
    public synchronized String register(String... strArr) throws IOException {
        return zza(zzaa.zzdq(this.zzaiq), strArr);
    }

    public void send(String str, String str2, long j, Bundle bundle) throws IOException {
        if (str != null) {
            String zzdr = zzaa.zzdr(this.zzaiq);
            if (zzdr != null) {
                Intent intent = new Intent("com.google.android.gcm.intent.SEND");
                if (bundle != null) {
                    intent.putExtras(bundle);
                }
                zzg(intent);
                intent.setPackage(zzdr);
                intent.putExtra("google.to", str);
                intent.putExtra("google.message_id", str2);
                intent.putExtra("google.ttl", Long.toString(j));
                int indexOf = str.indexOf(64);
                String substring = indexOf > 0 ? str.substring(0, indexOf) : str;
                InstanceID.getInstance(this.zzaiq);
                intent.putExtra("google.from", InstanceID.zzawr().zzf("", substring, INSTANCE_ID_SCOPE));
                if (zzdr.contains(".gsf")) {
                    Bundle bundle2 = new Bundle();
                    for (String str3 : bundle.keySet()) {
                        Object obj = bundle.get(str3);
                        if (obj instanceof String) {
                            String valueOf = String.valueOf(str3);
                            bundle2.putString(valueOf.length() != 0 ? "gcm.".concat(valueOf) : new String("gcm."), (String) obj);
                        }
                    }
                    bundle2.putString("google.to", str);
                    bundle2.putString("google.message_id", str2);
                    InstanceID.getInstance(this.zzaiq).zzb(INSTANCE_ID_SCOPE, "upstream", bundle2);
                    return;
                }
                this.zzaiq.sendOrderedBroadcast(intent, "com.google.android.gtalkservice.permission.GTALK_SERVICE");
                return;
            }
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
        throw new IllegalArgumentException("Missing 'to'");
    }

    public void send(String str, String str2, Bundle bundle) throws IOException {
        send(str, str2, -1, bundle);
    }

    @Deprecated
    public synchronized void unregister() throws IOException {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            InstanceID.getInstance(this.zzaiq).deleteInstanceID();
        } else {
            throw new IOException("MAIN_THREAD");
        }
    }
}
