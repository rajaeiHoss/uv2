package com.google.android.gms.iid;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;
import androidx.collection.ArrayMap;
import java.io.IOException;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class InstanceID {
    public static final String ERROR_MAIN_THREAD = "MAIN_THREAD";
    public static final String ERROR_MISSING_INSTANCEID_SERVICE = "MISSING_INSTANCEID_SERVICE";
    public static final String ERROR_SERVICE_NOT_AVAILABLE = "SERVICE_NOT_AVAILABLE";
    public static final String ERROR_TIMEOUT = "TIMEOUT";
    private static Map<String, InstanceID> zzimu = new ArrayMap();
    private static zzaf zzimv;
    private static zzaa zzimw;
    private static String zzina;
    private Context mContext;
    private KeyPair zzimx;
    private String zzimy = "";
    private long zzimz;

    private InstanceID(Context context, String str) {
        this.mContext = context.getApplicationContext();
        this.zzimy = str;
    }

    public static InstanceID getInstance(Context context) {
        return getInstance(context, (Bundle) null);
    }

    public static synchronized InstanceID getInstance(Context context, Bundle bundle) {
        InstanceID instanceID;
        synchronized (InstanceID.class) {
            String string = bundle == null ? "" : bundle.getString("subtype");
            if (string == null) {
                string = "";
            }
            Context applicationContext = context.getApplicationContext();
            if (zzimv == null) {
                zzimv = new zzaf(applicationContext);
                zzimw = new zzaa(applicationContext);
            }
            zzina = Integer.toString(zzdo(applicationContext));
            instanceID = zzimu.get(string);
            if (instanceID == null) {
                instanceID = new InstanceID(applicationContext, string);
                zzimu.put(string, instanceID);
            }
        }
        return instanceID;
    }

    static String zza(KeyPair keyPair) {
        try {
            byte[] digest = MessageDigest.getInstance("SHA1").digest(keyPair.getPublic().getEncoded());
            digest[0] = (byte) ((digest[0] & 15) + 112);
            return Base64.encodeToString(digest, 0, 8, 11);
        } catch (NoSuchAlgorithmException unused) {
            Log.w("InstanceID", "Unexpected error, device missing required algorithms");
            return null;
        }
    }

    private final KeyPair zzawp() {
        if (this.zzimx == null) {
            this.zzimx = zzimv.zzii(this.zzimy);
        }
        if (this.zzimx == null) {
            long currentTimeMillis = System.currentTimeMillis();
            this.zzimz = currentTimeMillis;
            this.zzimx = zzimv.zzc(this.zzimy, currentTimeMillis);
        }
        return this.zzimx;
    }

    public static zzaf zzawr() {
        return zzimv;
    }

    static int zzdo(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            String valueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 38);
            sb.append("Never happens: can't find own package ");
            sb.append(valueOf);
            Log.w("InstanceID", sb.toString());
            return 0;
        }
    }

    static String zzdp(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            String valueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 38);
            sb.append("Never happens: can't find own package ");
            sb.append(valueOf);
            Log.w("InstanceID", sb.toString());
            return null;
        }
    }

    static String zzp(byte[] bArr) {
        return Base64.encodeToString(bArr, 11);
    }

    public void deleteInstanceID() throws IOException {
        zza("*", "*", (Bundle) null);
        zzawq();
    }

    public void deleteToken(String str, String str2) throws IOException {
        zza(str, str2, (Bundle) null);
    }

    public long getCreationTime() {
        String str;
        if (this.zzimz == 0 && (str = zzimv.get(this.zzimy, "cre")) != null) {
            this.zzimz = Long.parseLong(str);
        }
        return this.zzimz;
    }

    public String getId() {
        return zza(zzawp());
    }

    public String getSubtype() {
        return this.zzimy;
    }

    public String getToken(String str, String str2) throws IOException {
        return getToken(str, str2, (Bundle) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0057 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0058  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getToken(java.lang.String r9, java.lang.String r10, android.os.Bundle r11) throws java.io.IOException {
        /*
            r8 = this;
            android.os.Looper r0 = android.os.Looper.getMainLooper()
            android.os.Looper r1 = android.os.Looper.myLooper()
            if (r0 == r1) goto L_0x008d
            com.google.android.gms.iid.zzaf r0 = zzimv
            java.lang.String r1 = "appVersion"
            java.lang.String r0 = r0.get(r1)
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x0048
            java.lang.String r3 = zzina
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x001f
            goto L_0x0048
        L_0x001f:
            com.google.android.gms.iid.zzaf r0 = zzimv
            java.lang.String r3 = "lastToken"
            java.lang.String r0 = r0.get(r3)
            if (r0 != 0) goto L_0x002a
            goto L_0x0048
        L_0x002a:
            long r3 = java.lang.Long.parseLong(r0)
            java.lang.Long r0 = java.lang.Long.valueOf(r3)
            long r3 = java.lang.System.currentTimeMillis()
            r5 = 1000(0x3e8, double:4.94E-321)
            long r3 = r3 / r5
            long r5 = r0.longValue()
            long r3 = r3 - r5
            r5 = 604800(0x93a80, double:2.98811E-318)
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 <= 0) goto L_0x0046
            goto L_0x0048
        L_0x0046:
            r0 = 0
            goto L_0x0049
        L_0x0048:
            r0 = 1
        L_0x0049:
            if (r0 == 0) goto L_0x004d
            r0 = 0
            goto L_0x0055
        L_0x004d:
            com.google.android.gms.iid.zzaf r0 = zzimv
            java.lang.String r3 = r8.zzimy
            java.lang.String r0 = r0.zzf(r3, r9, r10)
        L_0x0055:
            if (r0 == 0) goto L_0x0058
            return r0
        L_0x0058:
            if (r11 != 0) goto L_0x005f
            android.os.Bundle r11 = new android.os.Bundle
            r11.<init>()
        L_0x005f:
            java.lang.String r0 = "ttl"
            java.lang.String r0 = r11.getString(r0)
            if (r0 == 0) goto L_0x0068
            r2 = 0
        L_0x0068:
            java.lang.String r0 = "type"
            java.lang.String r0 = r11.getString(r0)
            java.lang.String r3 = "jwt"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0077
            goto L_0x0078
        L_0x0077:
            r1 = r2
        L_0x0078:
            java.lang.String r11 = r8.zzb(r9, r10, r11)
            if (r11 == 0) goto L_0x008c
            if (r1 == 0) goto L_0x008c
            com.google.android.gms.iid.zzaf r2 = zzimv
            java.lang.String r3 = r8.zzimy
            java.lang.String r7 = zzina
            r4 = r9
            r5 = r10
            r6 = r11
            r2.zza(r3, r4, r5, r6, r7)
        L_0x008c:
            return r11
        L_0x008d:
            java.io.IOException r9 = new java.io.IOException
            java.lang.String r10 = "MAIN_THREAD"
            r9.<init>(r10)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.iid.InstanceID.getToken(java.lang.String, java.lang.String, android.os.Bundle):java.lang.String");
    }

    public final void zza(String str, String str2, Bundle bundle) throws IOException {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            zzimv.zzg(this.zzimy, str, str2);
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putString("sender", str);
            if (str2 != null) {
                bundle.putString("scope", str2);
            }
            bundle.putString("subscription", str);
            bundle.putString("delete", "1");
            bundle.putString("X-delete", "1");
            bundle.putString("subtype", "".equals(this.zzimy) ? str : this.zzimy);
            if (!"".equals(this.zzimy)) {
                str = this.zzimy;
            }
            bundle.putString("X-subtype", str);
            zzaa.zzy(zzimw.zza(bundle, zzawp()));
            return;
        }
        throw new IOException("MAIN_THREAD");
    }

    public final void zzawq() {
        this.zzimz = 0;
        zzimv.zzih(String.valueOf(this.zzimy).concat("|"));
        this.zzimx = null;
    }

    public final String zzb(String str, String str2, Bundle bundle) throws IOException {
        if (str2 != null) {
            bundle.putString("scope", str2);
        }
        bundle.putString("sender", str);
        String str3 = "".equals(this.zzimy) ? str : this.zzimy;
        if (!bundle.containsKey("legacy.register")) {
            bundle.putString("subscription", str);
            bundle.putString("subtype", str3);
            bundle.putString("X-subscription", str);
            bundle.putString("X-subtype", str3);
        }
        String zzy = zzaa.zzy(zzimw.zza(bundle, zzawp()));
        if (!"RST".equals(zzy) && !zzy.startsWith("RST|")) {
            return zzy;
        }
        InstanceIDListenerService.zza(this.mContext, zzimv);
        throw new IOException("SERVICE_NOT_AVAILABLE");
    }
}
