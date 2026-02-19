package com.google.android.gms.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.security.auth.x500.X500Principal;

public final class zzcno extends zzcli {
    private static String[] zzjsm = {"firebase_"};
    private SecureRandom zzjsn;
    private final AtomicLong zzjso = new AtomicLong(0);
    private int zzjsp;

    zzcno(zzckj zzckj) {
        super(zzckj);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zza(java.lang.String r9, java.lang.Object r10, boolean r11) {
        /*
            r8 = this;
            r0 = 0
            if (r11 == 0) goto L_0x0035
            boolean r1 = r10 instanceof android.os.Parcelable[]
            r2 = 1
            if (r1 == 0) goto L_0x000d
            r1 = r10
            android.os.Parcelable[] r1 = (android.os.Parcelable[]) r1
            int r1 = r1.length
            goto L_0x0018
        L_0x000d:
            boolean r1 = r10 instanceof java.util.ArrayList
            if (r1 == 0) goto L_0x0030
            r1 = r10
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            int r1 = r1.size()
        L_0x0018:
            r3 = 1000(0x3e8, float:1.401E-42)
            if (r1 <= r3) goto L_0x0030
            com.google.android.gms.internal.zzcjj r2 = r8.zzayp()
            com.google.android.gms.internal.zzcjl r2 = r2.zzbaw()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.String r3 = "Parameter array is too long; discarded. Value kind, name, array length"
            java.lang.String r4 = "param"
            r2.zzd(r3, r4, r9, r1)
            r2 = 0
        L_0x0030:
            if (r2 != 0) goto L_0x0035
            r9 = 17
            return r9
        L_0x0035:
            boolean r1 = zzkp(r9)
            if (r1 == 0) goto L_0x0048
            r5 = 256(0x100, float:3.59E-43)
            java.lang.String r3 = "param"
            r2 = r8
            r4 = r9
            r6 = r10
            r7 = r11
            boolean r9 = r2.zza((java.lang.String) r3, (java.lang.String) r4, (int) r5, (java.lang.Object) r6, (boolean) r7)
            goto L_0x0054
        L_0x0048:
            r4 = 100
            java.lang.String r2 = "param"
            r1 = r8
            r3 = r9
            r5 = r10
            r6 = r11
            boolean r9 = r1.zza((java.lang.String) r2, (java.lang.String) r3, (int) r4, (java.lang.Object) r5, (boolean) r6)
        L_0x0054:
            if (r9 == 0) goto L_0x0057
            return r0
        L_0x0057:
            r9 = 4
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcno.zza(java.lang.String, java.lang.Object, boolean):int");
    }

    public static zzcoc zza(zzcob zzcob, String str) {
        for (zzcoc zzcoc : zzcob.zzjui) {
            if (zzcoc.name.equals(str)) {
                return zzcoc;
            }
        }
        return null;
    }

    private static Object zza(int i, Object obj, boolean z) {
        if (obj == null) {
            return null;
        }
        if ((obj instanceof Long) || (obj instanceof Double)) {
            return obj;
        }
        if (obj instanceof Integer) {
            return Long.valueOf((long) ((Integer) obj).intValue());
        }
        if (obj instanceof Byte) {
            return Long.valueOf((long) ((Byte) obj).byteValue());
        }
        if (obj instanceof Short) {
            return Long.valueOf((long) ((Short) obj).shortValue());
        }
        if (obj instanceof Boolean) {
            return Long.valueOf(((Boolean) obj).booleanValue() ? 1 : 0);
        } else if (obj instanceof Float) {
            return Double.valueOf(((Float) obj).doubleValue());
        } else {
            if ((obj instanceof String) || (obj instanceof Character) || (obj instanceof CharSequence)) {
                return zza(String.valueOf(obj), i, z);
            }
            return null;
        }
    }

    public static String zza(String str, int i, boolean z) {
        if (str.codePointCount(0, str.length()) <= i) {
            return str;
        }
        if (z) {
            return String.valueOf(str.substring(0, str.offsetByCodePoints(0, i))).concat("...");
        }
        return null;
    }

    public static String zza(String str, String[] strArr, String[] strArr2) {
        zzbq.checkNotNull(strArr);
        zzbq.checkNotNull(strArr2);
        int min = Math.min(strArr.length, strArr2.length);
        for (int i = 0; i < min; i++) {
            if (zzas(str, strArr[i])) {
                return strArr2[i];
            }
        }
        return null;
    }

    private final boolean zza(String str, String str2, int i, Object obj, boolean z) {
        if (obj != null && !(obj instanceof Long) && !(obj instanceof Float) && !(obj instanceof Integer) && !(obj instanceof Byte) && !(obj instanceof Short) && !(obj instanceof Boolean) && !(obj instanceof Double)) {
            if ((obj instanceof String) || (obj instanceof Character) || (obj instanceof CharSequence)) {
                String valueOf = String.valueOf(obj);
                if (valueOf.codePointCount(0, valueOf.length()) > i) {
                    zzayp().zzbaw().zzd("Value is too long; discarded. Value kind, name, value length", str, str2, Integer.valueOf(valueOf.length()));
                    return false;
                }
            } else if ((obj instanceof Bundle) && z) {
                return true;
            } else {
                if ((obj instanceof Parcelable[]) && z) {
                    for (Parcelable parcelable : (Parcelable[]) obj) {
                        if (!(parcelable instanceof Bundle)) {
                            zzayp().zzbaw().zze("All Parcelable[] elements must be of type Bundle. Value type, name", parcelable.getClass(), str2);
                            return false;
                        }
                    }
                    return true;
                } else if (!(obj instanceof ArrayList) || !z) {
                    return false;
                } else {
                    ArrayList arrayList = (ArrayList) obj;
                    int size = arrayList.size();
                    int i2 = 0;
                    while (i2 < size) {
                        Object obj2 = arrayList.get(i2);
                        i2++;
                        if (!(obj2 instanceof Bundle)) {
                            zzayp().zzbaw().zze("All ArrayList elements must be of type Bundle. Value type, name", obj2.getClass(), str2);
                            return false;
                        }
                    }
                    return true;
                }
            }
        }
        return true;
    }

    public static boolean zza(long[] jArr, int i) {
        if (i >= (jArr.length << 6)) {
            return false;
        }
        return ((1 << (i % 64)) & jArr[i / 64]) != 0;
    }

    static byte[] zza(Parcelable parcelable) {
        if (parcelable == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        try {
            parcelable.writeToParcel(obtain, 0);
            return obtain.marshall();
        } finally {
            obtain.recycle();
        }
    }

    public static long[] zza(BitSet bitSet) {
        int length = (bitSet.length() + 63) / 64;
        long[] jArr = new long[length];
        for (int i = 0; i < length; i++) {
            jArr[i] = 0;
            for (int i2 = 0; i2 < 64; i2++) {
                int i3 = (i << 6) + i2;
                if (i3 >= bitSet.length()) {
                    break;
                }
                if (bitSet.get(i3)) {
                    jArr[i] = jArr[i] | (1 << i2);
                }
            }
        }
        return jArr;
    }

    static zzcoc[] zza(zzcoc[] zzcocArr, String str, Object obj) {
        for (zzcoc zzcoc : zzcocArr) {
            if (str.equals(zzcoc.name)) {
                zzcoc.zzjum = null;
                zzcoc.zzgim = null;
                zzcoc.zzjsl = null;
                if (obj instanceof Long) {
                    zzcoc.zzjum = (Long) obj;
                } else if (obj instanceof String) {
                    zzcoc.zzgim = (String) obj;
                } else if (obj instanceof Double) {
                    zzcoc.zzjsl = (Double) obj;
                }
                return zzcocArr;
            }
        }
        zzcoc[] zzcocArr2 = new zzcoc[(zzcocArr.length + 1)];
        System.arraycopy(zzcocArr, 0, zzcocArr2, 0, zzcocArr.length);
        zzcoc zzcoc2 = new zzcoc();
        zzcoc2.name = str;
        if (obj instanceof Long) {
            zzcoc2.zzjum = (Long) obj;
        } else if (obj instanceof String) {
            zzcoc2.zzgim = (String) obj;
        } else if (obj instanceof Double) {
            zzcoc2.zzjsl = (Double) obj;
        }
        zzcocArr2[zzcocArr.length] = zzcoc2;
        return zzcocArr2;
    }

    private final boolean zzac(Context context, String str) {
        X500Principal x500Principal = new X500Principal("CN=Android Debug,O=Android,C=US");
        try {
            PackageInfo packageInfo = zzbih.zzdd(context).getPackageInfo(str, 64);
            if (packageInfo == null || packageInfo.signatures == null || packageInfo.signatures.length <= 0) {
                return true;
            }
            return ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(packageInfo.signatures[0].toByteArray()))).getSubjectX500Principal().equals(x500Principal);
        } catch (CertificateException e) {
            zzayp().zzbau().zzj("Error obtaining certificate", e);
            return true;
        } catch (PackageManager.NameNotFoundException e2) {
            zzayp().zzbau().zzj("Package name not found", e2);
            return true;
        }
    }

    public static Bundle[] zzaf(Object obj) {
        Object[] array;
        if (obj instanceof Bundle) {
            return new Bundle[]{(Bundle) obj};
        }
        if (obj instanceof Parcelable[]) {
            Parcelable[] parcelableArr = (Parcelable[]) obj;
            array = Arrays.copyOf(parcelableArr, parcelableArr.length, Bundle[].class);
        } else if (!(obj instanceof ArrayList)) {
            return null;
        } else {
            ArrayList arrayList = (ArrayList) obj;
            array = arrayList.toArray(new Bundle[arrayList.size()]);
        }
        return (Bundle[]) array;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0037 A[Catch:{ IOException | ClassNotFoundException -> 0x0040 }] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x003c A[Catch:{ IOException | ClassNotFoundException -> 0x0040 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object zzag(java.lang.Object r4) {
        /*
            r0 = 0
            if (r4 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x0032 }
            r1.<init>()     // Catch:{ all -> 0x0032 }
            java.io.ObjectOutputStream r2 = new java.io.ObjectOutputStream     // Catch:{ all -> 0x0032 }
            r2.<init>(r1)     // Catch:{ all -> 0x0032 }
            r2.writeObject(r4)     // Catch:{ all -> 0x002f }
            r2.flush()     // Catch:{ all -> 0x002f }
            java.io.ObjectInputStream r4 = new java.io.ObjectInputStream     // Catch:{ all -> 0x002f }
            java.io.ByteArrayInputStream r3 = new java.io.ByteArrayInputStream     // Catch:{ all -> 0x002f }
            byte[] r1 = r1.toByteArray()     // Catch:{ all -> 0x002f }
            r3.<init>(r1)     // Catch:{ all -> 0x002f }
            r4.<init>(r3)     // Catch:{ all -> 0x002f }
            java.lang.Object r1 = r4.readObject()     // Catch:{ all -> 0x002d }
            r2.close()     // Catch:{ IOException | ClassNotFoundException -> 0x0040 }
            r4.close()     // Catch:{ IOException | ClassNotFoundException -> 0x0040 }
            return r1
        L_0x002d:
            r1 = move-exception
            goto L_0x0035
        L_0x002f:
            r1 = move-exception
            r4 = r0
            goto L_0x0035
        L_0x0032:
            r1 = move-exception
            r4 = r0
            r2 = r4
        L_0x0035:
            if (r2 == 0) goto L_0x003a
            r2.close()     // Catch:{ IOException | ClassNotFoundException -> 0x0040 }
        L_0x003a:
            if (r4 == 0) goto L_0x003f
            r4.close()     // Catch:{ IOException | ClassNotFoundException -> 0x0040 }
        L_0x003f:
            throw r1     // Catch:{ IOException | ClassNotFoundException -> 0x0040 }
        L_0x0040:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcno.zzag(java.lang.Object):java.lang.Object");
    }

    private final boolean zzar(String str, String str2) {
        if (str2 == null) {
            zzayp().zzbau().zzj("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.length() == 0) {
            zzayp().zzbau().zzj("Name is required and can't be empty. Type", str);
            return false;
        } else {
            int codePointAt = str2.codePointAt(0);
            if (Character.isLetter(codePointAt) || codePointAt == 95) {
                int length = str2.length();
                int charCount = Character.charCount(codePointAt);
                while (charCount < length) {
                    int codePointAt2 = str2.codePointAt(charCount);
                    if (codePointAt2 == 95 || Character.isLetterOrDigit(codePointAt2)) {
                        charCount += Character.charCount(codePointAt2);
                    } else {
                        zzayp().zzbau().zze("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                        return false;
                    }
                }
                return true;
            }
            zzayp().zzbau().zze("Name must start with a letter or _ (underscore). Type, name", str, str2);
            return false;
        }
    }

    public static boolean zzas(String str, String str2) {
        if (str == null && str2 == null) {
            return true;
        }
        if (str == null) {
            return false;
        }
        return str.equals(str2);
    }

    public static Object zzb(zzcob zzcob, String str) {
        zzcoc zza = zza(zzcob, str);
        if (zza == null) {
            return null;
        }
        if (zza.zzgim != null) {
            return zza.zzgim;
        }
        if (zza.zzjum != null) {
            return zza.zzjum;
        }
        if (zza.zzjsl != null) {
            return zza.zzjsl;
        }
        return null;
    }

    private static void zzb(Bundle bundle, Object obj) {
        zzbq.checkNotNull(bundle);
        if (obj == null) {
            return;
        }
        if ((obj instanceof String) || (obj instanceof CharSequence)) {
            bundle.putLong("_el", (long) String.valueOf(obj).length());
        }
    }

    private static boolean zzd(Bundle bundle, int i) {
        if (bundle.getLong("_err") != 0) {
            return false;
        }
        bundle.putLong("_err", (long) i);
        return true;
    }

    static boolean zzd(zzcix zzcix, zzcif zzcif) {
        zzbq.checkNotNull(zzcix);
        zzbq.checkNotNull(zzcif);
        return !TextUtils.isEmpty(zzcif.zzjfl);
    }

    static MessageDigest zzeq(String str) {
        int i = 0;
        while (i < 2) {
            try {
                MessageDigest instance = MessageDigest.getInstance(str);
                if (instance != null) {
                    return instance;
                }
                i++;
            } catch (NoSuchAlgorithmException unused) {
            }
        }
        return null;
    }

    static boolean zzkh(String str) {
        zzbq.zzgv(str);
        return str.charAt(0) != '_' || str.equals("_ep");
    }

    private final int zzkl(String str) {
        if (!zzaq("event param", str)) {
            return 3;
        }
        if (!zza("event param", (String[]) null, str)) {
            return 14;
        }
        return !zzb("event param", 40, str) ? 3 : 0;
    }

    private final int zzkm(String str) {
        if (!zzar("event param", str)) {
            return 3;
        }
        if (!zza("event param", (String[]) null, str)) {
            return 14;
        }
        return !zzb("event param", 40, str) ? 3 : 0;
    }

    private static int zzko(String str) {
        if ("_ldl".equals(str)) {
            return 2048;
        }
        return "_id".equals(str) ? 256 : 36;
    }

    public static boolean zzkp(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith("_");
    }

    static boolean zzkr(String str) {
        return str != null && str.matches("(\\+|-)?([0-9]+\\.?[0-9]*|[0-9]*\\.?[0-9]+)") && str.length() <= 310;
    }

    static boolean zzku(String str) {
        zzbq.zzgv(str);
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case 94660:
                if (str.equals("_in")) {
                    c = 0;
                    break;
                }
                break;
            case 95025:
                if (str.equals("_ug")) {
                    c = 1;
                    break;
                }
                break;
            case 95027:
                if (str.equals("_ui")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
                return true;
            default:
                return false;
        }
    }

    public static boolean zzn(Intent intent) {
        String stringExtra = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
        return "android-app://com.google.android.googlequicksearchbox/https/www.google.com".equals(stringExtra) || "https://www.google.com".equals(stringExtra) || "android-app://com.google.appcrawler".equals(stringExtra);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0008, code lost:
        r3 = r1.getServiceInfo(new android.content.ComponentName(r3, r4), 0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean zzp(android.content.Context r3, java.lang.String r4) {
        /*
            r0 = 0
            android.content.pm.PackageManager r1 = r3.getPackageManager()     // Catch:{ NameNotFoundException -> 0x0019 }
            if (r1 != 0) goto L_0x0008
            return r0
        L_0x0008:
            android.content.ComponentName r2 = new android.content.ComponentName     // Catch:{ NameNotFoundException -> 0x0019 }
            r2.<init>(r3, r4)     // Catch:{ NameNotFoundException -> 0x0019 }
            android.content.pm.ServiceInfo r3 = r1.getServiceInfo(r2, r0)     // Catch:{ NameNotFoundException -> 0x0019 }
            if (r3 == 0) goto L_0x0019
            boolean r3 = r3.enabled     // Catch:{ NameNotFoundException -> 0x0019 }
            if (r3 == 0) goto L_0x0019
            r3 = 1
            return r3
        L_0x0019:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcno.zzp(android.content.Context, java.lang.String):boolean");
    }

    static long zzt(byte[] bArr) {
        zzbq.checkNotNull(bArr);
        int i = 0;
        zzbq.checkState(bArr.length > 0);
        long j = 0;
        int length = bArr.length - 1;
        while (length >= 0 && length >= bArr.length - 8) {
            j += (((long) bArr[length]) & 255) << i;
            i += 8;
            length--;
        }
        return j;
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final Bundle zza(String str, Bundle bundle, List<String> list, boolean z, boolean z2) {
        int i;
        if (bundle == null) {
            return null;
        }
        Bundle bundle2 = new Bundle(bundle);
        int i2 = 0;
        for (String str2 : bundle.keySet()) {
            if (list == null || !list.contains(str2)) {
                i = z ? zzkl(str2) : 0;
                if (i == 0) {
                    i = zzkm(str2);
                }
            } else {
                i = 0;
            }
            if (i == 0) {
                int zza = zza(str2, bundle.get(str2), z2);
                if (zza == 0 || "_ev".equals(str2)) {
                    if (zzkh(str2) && (i2 = i2 + 1) > 25) {
                        StringBuilder sb = new StringBuilder(48);
                        sb.append("Event can't contain more than 25 params");
                        zzayp().zzbau().zze(sb.toString(), zzayk().zzjp(str), zzayk().zzac(bundle));
                        zzd(bundle2, 5);
                    }
                } else if (zzd(bundle2, zza)) {
                    bundle2.putString("_ev", zza(str2, 40, true));
                    zzb(bundle2, bundle.get(str2));
                }
            } else if (zzd(bundle2, i)) {
                bundle2.putString("_ev", zza(str2, 40, true));
                if (i == 3) {
                    zzb(bundle2, (Object) str2);
                }
            }
            bundle2.remove(str2);
        }
        return bundle2;
    }

    /* access modifiers changed from: package-private */
    public final zzcix zza(String str, Bundle bundle, String str2, long j, boolean z, boolean z2) {
        Bundle bundle2;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (zzki(str) == 0) {
            if (bundle == null) {
                bundle2 = new Bundle();
            } else {
                bundle2 = new Bundle(bundle);
            }
            Bundle bundle3 = bundle2;
            bundle3.putString("_o", str2);
            String str3 = str;
            return new zzcix(str3, new zzciu(zzad(zza(str3, bundle3, (List<String>) Collections.singletonList("_o"), false, false))), str2, j);
        }
        zzayp().zzbau().zzj("Invalid conditional property event name", zzayk().zzjr(str));
        throw new IllegalArgumentException();
    }

    public final void zza(int i, String str, String str2, int i2) {
        zza((String) null, i, str, str2, i2);
    }

    public final void zza(Bundle bundle, String str, Object obj) {
        if (bundle != null) {
            if (obj instanceof Long) {
                bundle.putLong(str, ((Long) obj).longValue());
            } else if (obj instanceof String) {
                bundle.putString(str, String.valueOf(obj));
            } else if (obj instanceof Double) {
                bundle.putDouble(str, ((Double) obj).doubleValue());
            } else if (str != null) {
                zzayp().zzbax().zze("Not putting event parameter. Invalid value type. name, type", zzayk().zzjq(str), obj != null ? obj.getClass().getSimpleName() : null);
            }
        }
    }

    public final void zza(zzcoc zzcoc, Object obj) {
        zzbq.checkNotNull(obj);
        zzcoc.zzgim = null;
        zzcoc.zzjum = null;
        zzcoc.zzjsl = null;
        if (obj instanceof String) {
            zzcoc.zzgim = (String) obj;
        } else if (obj instanceof Long) {
            zzcoc.zzjum = (Long) obj;
        } else if (obj instanceof Double) {
            zzcoc.zzjsl = (Double) obj;
        } else {
            zzayp().zzbau().zzj("Ignoring invalid (type) event param value", obj);
        }
    }

    public final void zza(zzcog zzcog, Object obj) {
        zzbq.checkNotNull(obj);
        zzcog.zzgim = null;
        zzcog.zzjum = null;
        zzcog.zzjsl = null;
        if (obj instanceof String) {
            zzcog.zzgim = (String) obj;
        } else if (obj instanceof Long) {
            zzcog.zzjum = (Long) obj;
        } else if (obj instanceof Double) {
            zzcog.zzjsl = (Double) obj;
        } else {
            zzayp().zzbau().zzj("Ignoring invalid (type) user attribute value", obj);
        }
    }

    public final void zza(String str, int i, String str2, String str3, int i2) {
        Bundle bundle = new Bundle();
        zzd(bundle, i);
        if (!TextUtils.isEmpty(str2)) {
            bundle.putString(str2, str3);
        }
        if (i == 6 || i == 7 || i == 2) {
            bundle.putLong("_el", (long) i2);
        }
        this.zzjev.zzayd().zzd("auto", "_err", bundle);
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(String str, String[] strArr, String str2) {
        boolean z;
        boolean z2;
        if (str2 == null) {
            zzayp().zzbau().zzj("Name is required and can't be null. Type", str);
            return false;
        }
        zzbq.checkNotNull(str2);
        int i = 0;
        while (true) {
            String[] strArr2 = zzjsm;
            if (i >= strArr2.length) {
                z = false;
                break;
            } else if (str2.startsWith(strArr2[i])) {
                z = true;
                break;
            } else {
                i++;
            }
        }
        if (z) {
            zzayp().zzbau().zze("Name starts with reserved prefix. Type, name", str, str2);
            return false;
        }
        if (strArr != null) {
            zzbq.checkNotNull(strArr);
            int i2 = 0;
            while (true) {
                if (i2 >= strArr.length) {
                    z2 = false;
                    break;
                } else if (zzas(str2, strArr[i2])) {
                    z2 = true;
                    break;
                } else {
                    i2++;
                }
            }
            if (z2) {
                zzayp().zzbau().zze("Name is reserved. Type, name", str, str2);
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public final long zzab(Context context, String str) {
        zzwj();
        zzbq.checkNotNull(context);
        zzbq.zzgv(str);
        PackageManager packageManager = context.getPackageManager();
        MessageDigest zzeq = zzeq("MD5");
        if (zzeq == null) {
            zzayp().zzbau().log("Could not get MD5 instance");
            return -1;
        }
        if (packageManager != null) {
            try {
                if (!zzac(context, str)) {
                    PackageInfo packageInfo = zzbih.zzdd(context).getPackageInfo(getContext().getPackageName(), 64);
                    if (packageInfo.signatures != null && packageInfo.signatures.length > 0) {
                        return zzt(zzeq.digest(packageInfo.signatures[0].toByteArray()));
                    }
                    zzayp().zzbaw().log("Could not get signatures");
                    return -1;
                }
            } catch (PackageManager.NameNotFoundException e) {
                zzayp().zzbau().zzj("Package name not found", e);
            }
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public final Bundle zzad(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                Object zzk = zzk(str, bundle.get(str));
                if (zzk == null) {
                    zzayp().zzbaw().zzj("Param value can't be null", zzayk().zzjq(str));
                } else {
                    zza(bundle2, str, zzk);
                }
            }
        }
        return bundle2;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzaq(String str, String str2) {
        if (str2 == null) {
            zzayp().zzbau().zzj("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.length() == 0) {
            zzayp().zzbau().zzj("Name is required and can't be empty. Type", str);
            return false;
        } else {
            int codePointAt = str2.codePointAt(0);
            if (!Character.isLetter(codePointAt)) {
                zzayp().zzbau().zze("Name must start with a letter. Type, name", str, str2);
                return false;
            }
            int length = str2.length();
            int charCount = Character.charCount(codePointAt);
            while (charCount < length) {
                int codePointAt2 = str2.codePointAt(charCount);
                if (codePointAt2 == 95 || Character.isLetterOrDigit(codePointAt2)) {
                    charCount += Character.charCount(codePointAt2);
                } else {
                    zzayp().zzbau().zze("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                    return false;
                }
            }
            return true;
        }
    }

    public final /* bridge */ /* synthetic */ void zzaxz() {
        super.zzaxz();
    }

    public final /* bridge */ /* synthetic */ void zzaya() {
        super.zzaya();
    }

    public final /* bridge */ /* synthetic */ zzcia zzayb() {
        return super.zzayb();
    }

    public final /* bridge */ /* synthetic */ zzcih zzayc() {
        return super.zzayc();
    }

    public final /* bridge */ /* synthetic */ zzclk zzayd() {
        return super.zzayd();
    }

    public final /* bridge */ /* synthetic */ zzcje zzaye() {
        return super.zzaye();
    }

    public final /* bridge */ /* synthetic */ zzcir zzayf() {
        return super.zzayf();
    }

    public final /* bridge */ /* synthetic */ zzcme zzayg() {
        return super.zzayg();
    }

    public final /* bridge */ /* synthetic */ zzcma zzayh() {
        return super.zzayh();
    }

    public final /* bridge */ /* synthetic */ zzcjf zzayi() {
        return super.zzayi();
    }

    public final /* bridge */ /* synthetic */ zzcil zzayj() {
        return super.zzayj();
    }

    public final /* bridge */ /* synthetic */ zzcjh zzayk() {
        return super.zzayk();
    }

    public final /* bridge */ /* synthetic */ zzcno zzayl() {
        return super.zzayl();
    }

    public final /* bridge */ /* synthetic */ zzckd zzaym() {
        return super.zzaym();
    }

    public final /* bridge */ /* synthetic */ zzcnd zzayn() {
        return super.zzayn();
    }

    public final /* bridge */ /* synthetic */ zzcke zzayo() {
        return super.zzayo();
    }

    public final /* bridge */ /* synthetic */ zzcjj zzayp() {
        return super.zzayp();
    }

    public final /* bridge */ /* synthetic */ zzcju zzayq() {
        return super.zzayq();
    }

    public final /* bridge */ /* synthetic */ zzcik zzayr() {
        return super.zzayr();
    }

    /* access modifiers changed from: protected */
    public final boolean zzazq() {
        return true;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        zzayp().zzbau().log("Failed to load parcelable from buffer");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002c, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        r1.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0030, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001a, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x001c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <T extends android.os.Parcelable> T zzb(byte[] r5, android.os.Parcelable.Creator<T> r6) {
        /*
            r4 = this;
            r0 = 0
            if (r5 != 0) goto L_0x0004
            return r0
        L_0x0004:
            android.os.Parcel r1 = android.os.Parcel.obtain()
            int r2 = r5.length     // Catch:{ zzbgn -> 0x001c }
            r3 = 0
            r1.unmarshall(r5, r3, r2)     // Catch:{ zzbgn -> 0x001c }
            r1.setDataPosition(r3)     // Catch:{ zzbgn -> 0x001c }
            java.lang.Object r5 = r6.createFromParcel(r1)     // Catch:{ zzbgn -> 0x001c }
            android.os.Parcelable r5 = (android.os.Parcelable) r5     // Catch:{ zzbgn -> 0x001c }
            r1.recycle()
            return r5
        L_0x001a:
            r5 = move-exception
            goto L_0x002d
        L_0x001c:
            com.google.android.gms.internal.zzcjj r5 = r4.zzayp()     // Catch:{ all -> 0x001a }
            com.google.android.gms.internal.zzcjl r5 = r5.zzbau()     // Catch:{ all -> 0x001a }
            java.lang.String r6 = "Failed to load parcelable from buffer"
            r5.log(r6)     // Catch:{ all -> 0x001a }
            r1.recycle()
            return r0
        L_0x002d:
            r1.recycle()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcno.zzb(byte[], android.os.Parcelable$Creator):android.os.Parcelable");
    }

    /* access modifiers changed from: package-private */
    public final boolean zzb(String str, int i, String str2) {
        if (str2 == null) {
            zzayp().zzbau().zzj("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.codePointCount(0, str2.length()) <= i) {
            return true;
        } else {
            zzayp().zzbau().zzd("Name is too long. Type, maximum supported length, name", str, Integer.valueOf(i), str2);
            return false;
        }
    }

    public final byte[] zzb(zzcod zzcod) {
        try {
            int zzhs = zzcod.zzhs();
            byte[] bArr = new byte[zzhs];
            zzflk zzp = zzflk.zzp(bArr, 0, zzhs);
            zzcod.zza(zzp);
            zzp.zzcyx();
            return bArr;
        } catch (IOException e) {
            zzayp().zzbau().zzj("Data loss. Failed to serialize batch", e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public final void zzbap() {
        zzwj();
        SecureRandom secureRandom = new SecureRandom();
        long nextLong = secureRandom.nextLong();
        if (nextLong == 0) {
            nextLong = secureRandom.nextLong();
            if (nextLong == 0) {
                zzayp().zzbaw().log("Utils falling back to Random for random id");
            }
        }
        this.zzjso.set(nextLong);
    }

    public final long zzbcq() {
        int i = (this.zzjso.get() > 0 ? 1 : (this.zzjso.get() == 0 ? 0 : -1));
        synchronized (this.zzjso) {
            if (i == 0) {
                long nextLong = new Random(System.nanoTime() ^ zzxx().currentTimeMillis()).nextLong();
                int i2 = this.zzjsp + 1;
                this.zzjsp = i2;
                long j = nextLong + ((long) i2);
                return j;
            }
            this.zzjso.compareAndSet(-1, 1);
            long andIncrement = this.zzjso.getAndIncrement();
            return andIncrement;
        }
    }

    /* access modifiers changed from: package-private */
    public final SecureRandom zzbcr() {
        zzwj();
        if (this.zzjsn == null) {
            this.zzjsn = new SecureRandom();
        }
        return this.zzjsn;
    }

    public final boolean zzeh(String str) {
        zzwj();
        if (zzbih.zzdd(getContext()).checkCallingOrSelfPermission(str) == 0) {
            return true;
        }
        zzayp().zzbaz().zzj("Permission not granted", str);
        return false;
    }

    public final boolean zzf(long j, long j2) {
        return j == 0 || j2 <= 0 || Math.abs(zzxx().currentTimeMillis() - j) > j2;
    }

    public final Object zzk(String str, Object obj) {
        boolean z;
        int i = 256;
        if ("_ev".equals(str)) {
            z = true;
        } else {
            if (!zzkp(str)) {
                i = 100;
            }
            z = false;
        }
        return zza(i, obj, z);
    }

    public final int zzki(String str) {
        if (!zzar("event", str)) {
            return 2;
        }
        if (!zza("event", AppMeasurement.Event.zzjew, str)) {
            return 13;
        }
        return !zzb("event", 40, str) ? 2 : 0;
    }

    public final int zzkj(String str) {
        if (!zzaq("user property", str)) {
            return 6;
        }
        if (!zza("user property", AppMeasurement.UserProperty.zzjfa, str)) {
            return 15;
        }
        return !zzb("user property", 24, str) ? 6 : 0;
    }

    public final int zzkk(String str) {
        if (!zzar("user property", str)) {
            return 6;
        }
        if (!zza("user property", AppMeasurement.UserProperty.zzjfa, str)) {
            return 15;
        }
        return !zzb("user property", 24, str) ? 6 : 0;
    }

    public final boolean zzkn(String str) {
        if (TextUtils.isEmpty(str)) {
            zzayp().zzbau().log("Missing google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI");
            return false;
        }
        zzbq.checkNotNull(str);
        if (str.matches("^1:\\d+:android:[a-f0-9]+$")) {
            return true;
        }
        zzayp().zzbau().zzj("Invalid google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI. provided id", str);
        return false;
    }

    public final boolean zzkq(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return zzayr().zzazu().equals(str);
    }

    /* access modifiers changed from: package-private */
    public final boolean zzks(String str) {
        return "1".equals(zzaym().zzam(str, "measurement.upload.blacklist_internal"));
    }

    /* access modifiers changed from: package-private */
    public final boolean zzkt(String str) {
        return "1".equals(zzaym().zzam(str, "measurement.upload.blacklist_public"));
    }

    public final int zzl(String str, Object obj) {
        if ("_ldl".equals(str)) {
            return zza("user property referrer", str, zzko(str), obj, false) ? 0 : 7;
        }
        return zza("user property", str, zzko(str), obj, false) ? 0 : 7;
    }

    public final Object zzm(String str, Object obj) {
        return zza(zzko(str), obj, "_ldl".equals(str));
    }

    public final Bundle zzp(Uri uri) {
        String str;
        String str2;
        String str3;
        String str4;
        if (uri == null) {
            return null;
        }
        try {
            if (uri.isHierarchical()) {
                str4 = uri.getQueryParameter("utm_campaign");
                str3 = uri.getQueryParameter("utm_source");
                str2 = uri.getQueryParameter("utm_medium");
                str = uri.getQueryParameter("gclid");
            } else {
                str4 = null;
                str3 = null;
                str2 = null;
                str = null;
            }
            if (TextUtils.isEmpty(str4) && TextUtils.isEmpty(str3) && TextUtils.isEmpty(str2) && TextUtils.isEmpty(str)) {
                return null;
            }
            Bundle bundle = new Bundle();
            if (!TextUtils.isEmpty(str4)) {
                bundle.putString(FirebaseAnalytics.Param.CAMPAIGN, str4);
            }
            if (!TextUtils.isEmpty(str3)) {
                bundle.putString(FirebaseAnalytics.Param.SOURCE, str3);
            }
            if (!TextUtils.isEmpty(str2)) {
                bundle.putString(FirebaseAnalytics.Param.MEDIUM, str2);
            }
            if (!TextUtils.isEmpty(str)) {
                bundle.putString("gclid", str);
            }
            String queryParameter = uri.getQueryParameter("utm_term");
            if (!TextUtils.isEmpty(queryParameter)) {
                bundle.putString(FirebaseAnalytics.Param.TERM, queryParameter);
            }
            String queryParameter2 = uri.getQueryParameter("utm_content");
            if (!TextUtils.isEmpty(queryParameter2)) {
                bundle.putString(FirebaseAnalytics.Param.CONTENT, queryParameter2);
            }
            String queryParameter3 = uri.getQueryParameter(FirebaseAnalytics.Param.ACLID);
            if (!TextUtils.isEmpty(queryParameter3)) {
                bundle.putString(FirebaseAnalytics.Param.ACLID, queryParameter3);
            }
            String queryParameter4 = uri.getQueryParameter(FirebaseAnalytics.Param.CP1);
            if (!TextUtils.isEmpty(queryParameter4)) {
                bundle.putString(FirebaseAnalytics.Param.CP1, queryParameter4);
            }
            String queryParameter5 = uri.getQueryParameter("anid");
            if (!TextUtils.isEmpty(queryParameter5)) {
                bundle.putString("anid", queryParameter5);
            }
            return bundle;
        } catch (UnsupportedOperationException e) {
            zzayp().zzbaw().zzj("Install referrer url isn't a hierarchical URI", e);
            return null;
        }
    }

    public final byte[] zzr(byte[] bArr) throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            zzayp().zzbau().zzj("Failed to gzip content", e);
            throw e;
        }
    }

    public final byte[] zzs(byte[] bArr) throws IOException {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr2 = new byte[1024];
            while (true) {
                int read = gZIPInputStream.read(bArr2);
                if (read > 0) {
                    byteArrayOutputStream.write(bArr2, 0, read);
                } else {
                    gZIPInputStream.close();
                    byteArrayInputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (IOException e) {
            zzayp().zzbau().zzj("Failed to ungzip content", e);
            throw e;
        }
    }

    public final /* bridge */ /* synthetic */ void zzwj() {
        super.zzwj();
    }

    public final /* bridge */ /* synthetic */ zze zzxx() {
        return super.zzxx();
    }
}
