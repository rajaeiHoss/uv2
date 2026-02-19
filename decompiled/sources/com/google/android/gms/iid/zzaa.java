package com.google.android.gms.iid;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.Process;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.tasks.Tasks;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class zzaa {
    private static final String SERVICE_DATA_ERROR = "error";
    private static int zzino = 0;
    private static zzae<Boolean> zzinr = zzad.zzawy().zzf("gcm_iid_use_messenger_ipc", true);
    private static String zzins = null;
    private static boolean zzint = false;
    private static int zzinu = 0;
    private static int zzinv = 0;
    private static BroadcastReceiver zzinw = null;
    private Context zzaiq;
    private PendingIntent zzikb;
    private Messenger zzikf;
    private Map<String, Object> zzinx = new ArrayMap();
    private Messenger zziny;
    private MessengerCompat zzinz;

    public zzaa(Context context) {
        this.zzaiq = context;
    }

    private static String zza(KeyPair keyPair, String... strArr) {
        try {
            byte[] bytes = TextUtils.join("\n", strArr).getBytes("UTF-8");
            try {
                PrivateKey privateKey = keyPair.getPrivate();
                Signature instance = Signature.getInstance(privateKey instanceof RSAPrivateKey ? "SHA256withRSA" : "SHA256withECDSA");
                instance.initSign(privateKey);
                instance.update(bytes);
                return InstanceID.zzp(instance.sign());
            } catch (GeneralSecurityException e) {
                Log.e("InstanceID/Rpc", "Unable to sign registration request", e);
                return null;
            }
        } catch (UnsupportedEncodingException e) {
            Log.e("InstanceID/Rpc", "Unable to encode string", e);
            return null;
        }
    }

    private static boolean zza(PackageManager packageManager) {
        for (ResolveInfo resolveInfo : packageManager.queryBroadcastReceivers(new Intent("com.google.iid.TOKEN_REQUEST"), 0)) {
            if (zza(packageManager, resolveInfo.activityInfo.packageName, "com.google.iid.TOKEN_REQUEST")) {
                zzint = true;
                return true;
            }
        }
        return false;
    }

    private static boolean zza(PackageManager packageManager, String str, String str2) {
        if (packageManager.checkPermission("com.google.android.c2dm.permission.SEND", str) == 0) {
            return zzb(packageManager, str);
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 56 + String.valueOf(str2).length());
        sb.append("Possible malicious package ");
        sb.append(str);
        sb.append(" declares ");
        sb.append(str2);
        sb.append(" without permission");
        Log.w("InstanceID/Rpc", sb.toString());
        return false;
    }

    private final Bundle zzaa(Bundle bundle) throws IOException {
        Bundle bundle2;
        ConditionVariable conditionVariable = new ConditionVariable();
        String zzawx = zzawx();
        synchronized (getClass()) {
            this.zzinx.put(zzawx, conditionVariable);
        }
        zzf(bundle, zzawx);
        conditionVariable.block(NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS);
        synchronized (getClass()) {
            Object remove = this.zzinx.remove(zzawx);
            if (remove instanceof Bundle) {
                bundle2 = (Bundle) remove;
            } else if (remove instanceof String) {
                throw new IOException((String) remove);
            } else {
                String valueOf = String.valueOf(remove);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 12);
                sb.append("No response ");
                sb.append(valueOf);
                Log.w("InstanceID/Rpc", sb.toString());
                throw new IOException(InstanceID.ERROR_TIMEOUT);
            }
        }
        return bundle2;
    }

    private final void zzae(Object obj) {
        synchronized (getClass()) {
            for (String next : this.zzinx.keySet()) {
                Object obj2 = this.zzinx.get(next);
                this.zzinx.put(next, obj);
                zze(obj2, obj);
            }
        }
    }

    private static synchronized String zzawx() {
        String num;
        synchronized (zzaa.class) {
            int i = zzino;
            zzino = i + 1;
            num = Integer.toString(i);
        }
        return num;
    }

    private static boolean zzb(PackageManager packageManager, String str) {
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 0);
            zzins = applicationInfo.packageName;
            zzinv = applicationInfo.uid;
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static boolean zzdq(Context context) {
        if (zzins != null) {
            zzdr(context);
        }
        return zzint;
    }

    public static String zzdr(Context context) {
        boolean z;
        String str = zzins;
        if (str != null) {
            return str;
        }
        zzinu = Process.myUid();
        PackageManager packageManager = context.getPackageManager();
        if (!zzs.isAtLeastO()) {
            Iterator<ResolveInfo> it = packageManager.queryIntentServices(new Intent("com.google.android.c2dm.intent.REGISTER"), 0).iterator();
            while (true) {
                if (it.hasNext()) {
                    if (zza(packageManager, it.next().serviceInfo.packageName, "com.google.android.c2dm.intent.REGISTER")) {
                        zzint = false;
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
            if (z) {
                return zzins;
            }
        }
        if (zza(packageManager)) {
            return zzins;
        }
        Log.w("InstanceID/Rpc", "Failed to resolve IID implementation package, falling back");
        if (zzb(packageManager, "com.google.android.gms")) {
            zzint = zzs.isAtLeastO();
            return zzins;
        } else if (zzs.zzanx() || !zzb(packageManager, "com.google.android.gsf")) {
            Log.w("InstanceID/Rpc", "Google Play services is missing, unable to get tokens");
            return null;
        } else {
            zzint = false;
            return zzins;
        }
    }

    private static int zzds(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(zzdr(context), 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            return -1;
        }
    }

    private static void zze(Object obj, Object obj2) {
        if (obj instanceof ConditionVariable) {
            ((ConditionVariable) obj).open();
        }
        if (obj instanceof Messenger) {
            Messenger messenger = (Messenger) obj;
            Message obtain = Message.obtain();
            obtain.obj = obj2;
            try {
                messenger.send(obtain);
            } catch (RemoteException e) {
                String valueOf = String.valueOf(e);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 24);
                sb.append("Failed to send response ");
                sb.append(valueOf);
                Log.w("InstanceID/Rpc", sb.toString());
            }
        }
    }

    private final void zzf(Bundle bundle, String str) throws IOException {
        if (this.zzikf == null) {
            zzdr(this.zzaiq);
            this.zzikf = new Messenger(new zzab(this, Looper.getMainLooper()));
        }
        if (zzins != null) {
            Intent intent = new Intent(zzint ? "com.google.iid.TOKEN_REQUEST" : "com.google.android.c2dm.intent.REGISTER");
            intent.setPackage(zzins);
            intent.putExtras(bundle);
            zzi(intent);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 5);
            sb.append("|ID|");
            sb.append(str);
            sb.append("|");
            intent.putExtra("kid", sb.toString());
            StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 5);
            sb2.append("|ID|");
            sb2.append(str);
            sb2.append("|");
            intent.putExtra("X-kid", sb2.toString());
            boolean equals = "com.google.android.gsf".equals(zzins);
            String stringExtra = intent.getStringExtra("useGsf");
            if (stringExtra != null) {
                equals = "1".equals(stringExtra);
            }
            if (Log.isLoggable("InstanceID/Rpc", 3)) {
                String valueOf = String.valueOf(intent.getExtras());
                StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf).length() + 8);
                sb3.append("Sending ");
                sb3.append(valueOf);
                Log.d("InstanceID/Rpc", sb3.toString());
            }
            if (this.zziny != null) {
                intent.putExtra("google.messenger", this.zzikf);
                Message obtain = Message.obtain();
                obtain.obj = intent;
                try {
                    this.zziny.send(obtain);
                    return;
                } catch (RemoteException unused) {
                    if (Log.isLoggable("InstanceID/Rpc", 3)) {
                        Log.d("InstanceID/Rpc", "Messenger failed, fallback to startService");
                    }
                }
            }
            if (equals) {
                synchronized (this) {
                    if (zzinw == null) {
                        zzinw = new zzac(this);
                        if (Log.isLoggable("InstanceID/Rpc", 3)) {
                            Log.d("InstanceID/Rpc", "Registered GSF callback receiver");
                        }
                        IntentFilter intentFilter = new IntentFilter("com.google.android.c2dm.intent.REGISTRATION");
                        intentFilter.addCategory(this.zzaiq.getPackageName());
                        this.zzaiq.registerReceiver(zzinw, intentFilter, "com.google.android.c2dm.permission.SEND", (Handler) null);
                    }
                }
                this.zzaiq.sendBroadcast(intent);
                return;
            }
            intent.putExtra("google.messenger", this.zzikf);
            intent.putExtra("messenger2", "1");
            if (this.zzinz != null) {
                Message obtain2 = Message.obtain();
                obtain2.obj = intent;
                try {
                    this.zzinz.send(obtain2);
                    return;
                } catch (RemoteException unused2) {
                    if (Log.isLoggable("InstanceID/Rpc", 3)) {
                        Log.d("InstanceID/Rpc", "Messenger failed, fallback to startService");
                    }
                }
            }
            if (zzint) {
                this.zzaiq.sendBroadcast(intent);
            } else {
                this.zzaiq.startService(intent);
            }
        } else {
            throw new IOException(InstanceID.ERROR_MISSING_INSTANCEID_SERVICE);
        }
    }

    private final synchronized void zzi(Intent intent) {
        if (this.zzikb == null) {
            Intent intent2 = new Intent();
            intent2.setPackage("com.google.example.invalidpackage");
            this.zzikb = PendingIntent.getBroadcast(this.zzaiq, 0, intent2, 0);
        }
        intent.putExtra("app", this.zzikb);
    }

    private final void zzi(String str, Object obj) {
        synchronized (getClass()) {
            Object obj2 = this.zzinx.get(str);
            this.zzinx.put(str, obj);
            zze(obj2, obj);
        }
    }

    static String zzy(Bundle bundle) throws IOException {
        if (bundle != null) {
            String string = bundle.getString("registration_id");
            if (string == null) {
                string = bundle.getString("unregistered");
            }
            if (string != null) {
                return string;
            }
            String string2 = bundle.getString(SERVICE_DATA_ERROR);
            if (string2 != null) {
                throw new IOException(string2);
            }
            String valueOf = String.valueOf(bundle);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 29);
            sb.append("Unexpected response from GCM ");
            sb.append(valueOf);
            Log.w("InstanceID/Rpc", sb.toString(), new Throwable());
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
        throw new IOException("SERVICE_NOT_AVAILABLE");
    }

    private final Bundle zzz(Bundle bundle) throws IOException {
        Bundle zzaa = zzaa(bundle);
        if (zzaa == null || !zzaa.containsKey("google.messenger")) {
            return zzaa;
        }
        Bundle zzaa2 = zzaa(bundle);
        if (zzaa2 == null || !zzaa2.containsKey("google.messenger")) {
            return zzaa2;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final Bundle zza(Bundle bundle, KeyPair keyPair) throws IOException {
        int zzds = zzds(this.zzaiq);
        bundle.putString("gmsv", Integer.toString(zzds));
        bundle.putString("osv", Integer.toString(Build.VERSION.SDK_INT));
        bundle.putString("app_ver", Integer.toString(InstanceID.zzdo(this.zzaiq)));
        bundle.putString("app_ver_name", InstanceID.zzdp(this.zzaiq));
        bundle.putString("cliv", "iid-12211000");
        bundle.putString("appid", InstanceID.zza(keyPair));
        String zzp = InstanceID.zzp(keyPair.getPublic().getEncoded());
        bundle.putString("pub2", zzp);
        bundle.putString("sig", zza(keyPair, this.zzaiq.getPackageName(), zzp));
        if (zzds < 12000000 || !zzinr.get().booleanValue()) {
            return zzz(bundle);
        }
        try {
            return (Bundle) Tasks.await(new zzm(this.zzaiq).zzj(1, bundle));
        } catch (InterruptedException | ExecutionException e) {
            if (Log.isLoggable("InstanceID/Rpc", 3)) {
                String valueOf = String.valueOf(e);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 22);
                sb.append("Error making request: ");
                sb.append(valueOf);
                Log.d("InstanceID/Rpc", sb.toString());
            }
            if (!(e.getCause() instanceof zzv) || ((zzv) e.getCause()).getErrorCode() != 4) {
                return null;
            }
            return zzz(bundle);
        }
    }

    public final void zzd(Message message) {
        if (message != null) {
            if (message.obj instanceof Intent) {
                Intent intent = (Intent) message.obj;
                intent.setExtrasClassLoader(MessengerCompat.class.getClassLoader());
                if (intent.hasExtra("google.messenger")) {
                    Parcelable parcelableExtra = intent.getParcelableExtra("google.messenger");
                    if (parcelableExtra instanceof MessengerCompat) {
                        this.zzinz = (MessengerCompat) parcelableExtra;
                    }
                    if (parcelableExtra instanceof Messenger) {
                        this.zziny = (Messenger) parcelableExtra;
                    }
                }
                zzj((Intent) message.obj);
                return;
            }
            Log.w("InstanceID/Rpc", "Dropping invalid message");
        }
    }

    public final void zzj(Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            if ("com.google.android.c2dm.intent.REGISTRATION".equals(action) || "com.google.android.gms.iid.InstanceID".equals(action)) {
                String stringExtra = intent.getStringExtra("registration_id");
                if (stringExtra == null) {
                    stringExtra = intent.getStringExtra("unregistered");
                }
                if (stringExtra == null) {
                    String stringExtra2 = intent.getStringExtra(SERVICE_DATA_ERROR);
                    if (stringExtra2 == null) {
                        String valueOf = String.valueOf(intent.getExtras());
                        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 49);
                        sb.append("Unexpected response, no error or registration id ");
                        sb.append(valueOf);
                        Log.w("InstanceID/Rpc", sb.toString());
                        return;
                    }
                    if (Log.isLoggable("InstanceID/Rpc", 3)) {
                        String valueOf2 = String.valueOf(stringExtra2);
                        Log.d("InstanceID/Rpc", valueOf2.length() != 0 ? "Received InstanceID error ".concat(valueOf2) : new String("Received InstanceID error "));
                    }
                    String str = null;
                    if (stringExtra2.startsWith("|")) {
                        String[] split = stringExtra2.split("\\|");
                        if (!"ID".equals(split[1])) {
                            String valueOf3 = String.valueOf(stringExtra2);
                            Log.w("InstanceID/Rpc", valueOf3.length() != 0 ? "Unexpected structured response ".concat(valueOf3) : new String("Unexpected structured response "));
                        }
                        if (split.length > 2) {
                            String str2 = split[2];
                            String str3 = split[3];
                            if (str3.startsWith(":")) {
                                str3 = str3.substring(1);
                            }
                            stringExtra2 = str3;
                            str = str2;
                        } else {
                            stringExtra2 = "UNKNOWN";
                        }
                        intent.putExtra(SERVICE_DATA_ERROR, stringExtra2);
                    }
                    if (str == null) {
                        zzae(stringExtra2);
                    } else {
                        zzi(str, stringExtra2);
                    }
                } else {
                    Matcher matcher = Pattern.compile("\\|ID\\|([^|]+)\\|:?+(.+)").matcher(stringExtra);
                    if (matcher.matches()) {
                        String group = matcher.group(1);
                        String group2 = matcher.group(2);
                        Bundle extras = intent.getExtras();
                        extras.putString("registration_id", group2);
                        zzi(group, extras);
                    } else if (Log.isLoggable("InstanceID/Rpc", 3)) {
                        String valueOf4 = String.valueOf(stringExtra);
                        Log.d("InstanceID/Rpc", valueOf4.length() != 0 ? "Unexpected response string: ".concat(valueOf4) : new String("Unexpected response string: "));
                    }
                }
            } else if (Log.isLoggable("InstanceID/Rpc", 3)) {
                String valueOf5 = String.valueOf(intent.getAction());
                Log.d("InstanceID/Rpc", valueOf5.length() != 0 ? "Unexpected response ".concat(valueOf5) : new String("Unexpected response "));
            }
        } else if (Log.isLoggable("InstanceID/Rpc", 3)) {
            Log.d("InstanceID/Rpc", "Unexpected response: null");
        }
    }
}
