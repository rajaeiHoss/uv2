package com.google.android.gms.internal;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.KeyguardManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.PowerManager;
import android.os.Process;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import com.google.android.gms.ads.AdActivity;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.common.util.zzp;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.measurement.AppMeasurement;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzabh
public final class zzaij {
    public static final Handler zzdfn = new zzahx(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    /* access modifiers changed from: private */
    public String zzddt;
    /* access modifiers changed from: private */
    public boolean zzdfo = true;
    private boolean zzdfp = false;
    private boolean zzdfq = false;
    private Pattern zzdfr;
    private Pattern zzdfs;

    public static Bundle zza(zzhm zzhm) {
        String str;
        String str2;
        String str3;
        if (zzhm == null) {
            return null;
        }
        if (!((Boolean) zzlc.zzio().zzd(zzoi.zzbnn)).booleanValue()) {
            if (!((Boolean) zzlc.zzio().zzd(zzoi.zzbnp)).booleanValue()) {
                return null;
            }
        }
        if (zzbt.zzep().zzqe().zzqp() && zzbt.zzep().zzqe().zzqr()) {
            return null;
        }
        if (zzhm.zzhb()) {
            zzhm.wakeup();
        }
        zzhg zzgz = zzhm.zzgz();
        if (zzgz != null) {
            str3 = zzgz.zzgo();
            str2 = zzgz.zzgp();
            str = zzgz.zzgq();
            if (str3 != null) {
                zzbt.zzep().zzqe().zzcb(str3);
            }
            if (str != null) {
                zzbt.zzep().zzqe().zzcc(str);
            }
        } else {
            str3 = zzbt.zzep().zzqe().zzqq();
            str = zzbt.zzep().zzqe().zzqs();
            str2 = null;
        }
        Bundle bundle = new Bundle(1);
        if (str != null) {
            if (((Boolean) zzlc.zzio().zzd(zzoi.zzbnp)).booleanValue() && !zzbt.zzep().zzqe().zzqr()) {
                bundle.putString("v_fp_vertical", str);
            }
        }
        if (str3 != null) {
            if (((Boolean) zzlc.zzio().zzd(zzoi.zzbnn)).booleanValue() && !zzbt.zzep().zzqe().zzqp()) {
                bundle.putString("fingerprint", str3);
                if (!str3.equals(str2)) {
                    bundle.putString("v_fp", str2);
                }
            }
        }
        if (!bundle.isEmpty()) {
            return bundle;
        }
        return null;
    }

    public static DisplayMetrics zza(WindowManager windowManager) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static PopupWindow zza(View view, int i, int i2, boolean z) {
        return new PopupWindow(view, i, i2, false);
    }

    public static String zza(Context context, View view, zzko zzko) {
        if (!((Boolean) zzlc.zzio().zzd(zzoi.zzbof)).booleanValue()) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("width", zzko.width);
            jSONObject2.put("height", zzko.height);
            jSONObject.put("size", jSONObject2);
            jSONObject.put("activity", zzam(context));
            if (!zzko.zzbib) {
                JSONArray jSONArray = new JSONArray();
                while (view != null) {
                    ViewParent parent = view.getParent();
                    if (parent != null) {
                        int i = -1;
                        if (parent instanceof ViewGroup) {
                            i = ((ViewGroup) parent).indexOfChild(view);
                        }
                        JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.put(AppMeasurement.Param.TYPE, parent.getClass().getName());
                        jSONObject3.put("index_of_child", i);
                        jSONArray.put(jSONObject3);
                    }
                    view = (parent == null || !(parent instanceof View)) ? null : (View) parent;
                }
                if (jSONArray.length() > 0) {
                    jSONObject.put("parents", jSONArray);
                }
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            zzahw.zzc("Fail to get view hierarchy json", e);
            return null;
        }
    }

    public static String zza(InputStreamReader inputStreamReader) throws IOException {
        StringBuilder sb = new StringBuilder(8192);
        char[] cArr = new char[2048];
        while (true) {
            int read = inputStreamReader.read(cArr);
            if (read == -1) {
                return sb.toString();
            }
            sb.append(cArr, 0, read);
        }
    }

    private final JSONArray zza(Collection<?> collection) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (Object zza : collection) {
            zza(jSONArray, (Object) zza);
        }
        return jSONArray;
    }

    public static void zza(Context context, Intent intent) {
        try {
            context.startActivity(intent);
        } catch (Throwable unused) {
            intent.addFlags(DriveFile.MODE_READ_ONLY);
            context.startActivity(intent);
        }
    }

    public static void zza(Context context, Uri uri) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", uri);
            Bundle bundle = new Bundle();
            intent.putExtras(bundle);
            if (((Boolean) zzlc.zzio().zzd(zzoi.zzbuo)).booleanValue()) {
                zzb(context, intent);
            }
            bundle.putString("com.android.browser.application_id", context.getPackageName());
            context.startActivity(intent);
            String uri2 = uri.toString();
            StringBuilder sb = new StringBuilder(String.valueOf(uri2).length() + 26);
            sb.append("Opening ");
            sb.append(uri2);
            sb.append(" in a new browser.");
            zzahw.zzby(sb.toString());
        } catch (ActivityNotFoundException e) {
            zzahw.zzb("No browser is found.", e);
        }
    }

    public static void zza(Context context, String str, List<String> list) {
        for (String zzakd : list) {
            new zzakd(context, str, zzakd).zzns();
        }
    }

    private final void zza(JSONArray jSONArray, Object obj) throws JSONException {
        Object zza;
        if (obj instanceof Bundle) {
            zza = zzd((Bundle) obj);
        } else if (obj instanceof Map) {
            zza = zzq((Map) obj);
        } else if (obj instanceof Collection) {
            zza = zza((Collection<?>) (Collection) obj);
        } else if (obj instanceof Object[]) {
            JSONArray jSONArray2 = new JSONArray();
            for (Object zza2 : (Object[]) obj) {
                zza(jSONArray2, zza2);
            }
            jSONArray.put(jSONArray2);
            return;
        } else {
            jSONArray.put(obj);
            return;
        }
        jSONArray.put(zza);
    }

    private final void zza(JSONObject jSONObject, String str, Object obj) throws JSONException {
        Collection asList;
        Object zza;
        if (obj instanceof Bundle) {
            zza = zzd((Bundle) obj);
        } else if (obj instanceof Map) {
            zza = zzq((Map) obj);
        } else {
            if (obj instanceof Collection) {
                if (str == null) {
                    str = "null";
                }
                asList = (Collection) obj;
            } else if (obj instanceof Object[]) {
                asList = Arrays.asList((Object[]) obj);
            } else {
                jSONObject.put(str, obj);
                return;
            }
            zza = zza((Collection<?>) asList);
        }
        jSONObject.put(str, zza);
    }

    public static boolean zza(Activity activity, Configuration configuration) {
        zzlc.zzij();
        int zza = zzako.zza((Context) activity, configuration.screenHeightDp);
        int zza2 = zzako.zza((Context) activity, configuration.screenWidthDp);
        DisplayMetrics zza3 = zza((WindowManager) activity.getApplicationContext().getSystemService("window"));
        int i = zza3.heightPixels;
        int i2 = zza3.widthPixels;
        int identifier = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int dimensionPixelSize = identifier > 0 ? activity.getResources().getDimensionPixelSize(identifier) : 0;
        int round = ((int) Math.round(((double) activity.getResources().getDisplayMetrics().density) + 0.5d)) * ((Integer) zzlc.zzio().zzd(zzoi.zzbuy)).intValue();
        return zzb(i, zza + dimensionPixelSize, round) && zzb(i2, zza2, round);
    }

    public static boolean zza(ClassLoader classLoader, Class<?> cls, String str) {
        try {
            return cls.isAssignableFrom(Class.forName(str, false, classLoader));
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean zzag(Context context) {
        String str;
        boolean z;
        Intent intent = new Intent();
        intent.setClassName(context, AdActivity.CLASS_NAME);
        ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 65536);
        if (resolveActivity == null || resolveActivity.activityInfo == null) {
            str = "Could not find com.google.android.gms.ads.AdActivity, please make sure it is declared in AndroidManifest.xml.";
        } else {
            if ((resolveActivity.activityInfo.configChanges & 16) == 0) {
                zzahw.zzcz(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", new Object[]{"keyboard"}));
                z = false;
            } else {
                z = true;
            }
            if ((resolveActivity.activityInfo.configChanges & 32) == 0) {
                zzahw.zzcz(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", new Object[]{"keyboardHidden"}));
                z = false;
            }
            if ((resolveActivity.activityInfo.configChanges & 128) == 0) {
                zzahw.zzcz(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", new Object[]{"orientation"}));
                z = false;
            }
            if ((resolveActivity.activityInfo.configChanges & 256) == 0) {
                zzahw.zzcz(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", new Object[]{"screenLayout"}));
                z = false;
            }
            if ((resolveActivity.activityInfo.configChanges & 512) == 0) {
                zzahw.zzcz(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", new Object[]{"uiMode"}));
                z = false;
            }
            if ((resolveActivity.activityInfo.configChanges & 1024) == 0) {
                zzahw.zzcz(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", new Object[]{"screenSize"}));
                z = false;
            }
            if ((resolveActivity.activityInfo.configChanges & 2048) != 0) {
                return z;
            }
            str = String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", new Object[]{"smallestScreenSize"});
        }
        zzahw.zzcz(str);
        return false;
    }

    protected static String zzaj(Context context) {
        try {
            return new WebView(context).getSettings().getUserAgentString();
        } catch (Throwable unused) {
            return zzrb();
        }
    }

    public static AlertDialog.Builder zzak(Context context) {
        return new AlertDialog.Builder(context);
    }

    public static zznu zzal(Context context) {
        return new zznu(context);
    }

    private static String zzam(Context context) {
        List<ActivityManager.RunningTaskInfo> runningTasks;
        ActivityManager.RunningTaskInfo runningTaskInfo;
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (!(activityManager == null || (runningTasks = activityManager.getRunningTasks(1)) == null || runningTasks.isEmpty() || (runningTaskInfo = runningTasks.get(0)) == null || runningTaskInfo.topActivity == null)) {
                return runningTaskInfo.topActivity.getClassName();
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public static boolean zzan(Context context) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
            if (activityManager != null) {
                if (keyguardManager != null) {
                    List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
                    if (runningAppProcesses != null) {
                        Iterator<ActivityManager.RunningAppProcessInfo> it = runningAppProcesses.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            ActivityManager.RunningAppProcessInfo next = it.next();
                            if (Process.myPid() == next.pid) {
                                if (next.importance == 100 && !keyguardManager.inKeyguardRestrictedInputMode()) {
                                    PowerManager powerManager = (PowerManager) context.getSystemService("power");
                                    if (powerManager == null ? false : powerManager.isScreenOn()) {
                                        return true;
                                    }
                                }
                            }
                        }
                    } else {
                        return false;
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    public static Bitmap zzao(Context context) {
        if (!(context instanceof Activity)) {
            return null;
        }
        try {
            if (!((Boolean) zzlc.zzio().zzd(zzoi.zzbsa)).booleanValue()) {
                return zzt(((Activity) context).getWindow().getDecorView());
            }
            Window window = ((Activity) context).getWindow();
            if (window != null) {
                return zzu(window.getDecorView().getRootView());
            }
            return null;
        } catch (RuntimeException e) {
            zzahw.zzb("Fail to capture screen shot", e);
            return null;
        }
    }

    public static int zzap(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (applicationInfo == null) {
            return 0;
        }
        return applicationInfo.targetSdkVersion;
    }

    private static KeyguardManager zzaq(Context context) {
        Object systemService = context.getSystemService("keyguard");
        if (systemService == null || !(systemService instanceof KeyguardManager)) {
            return null;
        }
        return (KeyguardManager) systemService;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000a, code lost:
        r2 = zzaq(r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean zzar(android.content.Context r2) {
        /*
            r0 = 0
            if (r2 == 0) goto L_0x0018
            boolean r1 = com.google.android.gms.common.util.zzs.zzans()
            if (r1 != 0) goto L_0x000a
            goto L_0x0018
        L_0x000a:
            android.app.KeyguardManager r2 = zzaq(r2)
            if (r2 == 0) goto L_0x0018
            boolean r2 = r2.isKeyguardLocked()
            if (r2 == 0) goto L_0x0018
            r2 = 1
            return r2
        L_0x0018:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaij.zzar(android.content.Context):boolean");
    }

    public static boolean zzas(Context context) {
        try {
            context.getClassLoader().loadClass("com.google.android.gms.ads.internal.ClientApi");
            return false;
        } catch (ClassNotFoundException unused) {
            return true;
        } catch (Throwable th) {
            zzahw.zzb("Error loading class.", th);
            zzbt.zzep().zza(th, "AdUtil.isLiteSdk");
            return false;
        }
    }

    public static void zzb(Context context, Intent intent) {
        if (intent != null && zzs.zzanu()) {
            Bundle extras = intent.getExtras() != null ? intent.getExtras() : new Bundle();
            extras.putBinder("android.support.customtabs.extra.SESSION", (IBinder) null);
            extras.putString("com.android.browser.application_id", context.getPackageName());
            intent.putExtras(extras);
        }
    }

    private static boolean zzb(int i, int i2, int i3) {
        return Math.abs(i - i2) <= i3;
    }

    public static void zzc(Runnable runnable) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            runnable.run();
        } else {
            zzaid.zzb(runnable);
        }
    }

    public static String zzci(String str) {
        return Uri.parse(str).buildUpon().query((String) null).build().toString();
    }

    public static int zzcj(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            String valueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 22);
            sb.append("Could not parse value:");
            sb.append(valueOf);
            zzahw.zzcz(sb.toString());
            return 0;
        }
    }

    public static boolean zzck(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.matches("([^\\s]+(\\.(?i)(jpg|png|gif|bmp|webp))$)");
    }

    public static String zzcn(String str) {
        return TextUtils.isEmpty(str) ? "" : str.split(";")[0].trim();
    }

    public static String zzco(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String[] split = str.split(";");
        if (split.length == 1) {
            return "";
        }
        for (int i = 1; i < split.length; i++) {
            if (split[i].trim().startsWith("charset")) {
                String[] split2 = split[i].trim().split("=");
                if (split2.length > 1) {
                    return split2[1].trim();
                }
            }
        }
        return "";
    }

    private final JSONObject zzd(Bundle bundle) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (String str : bundle.keySet()) {
            zza(jSONObject, str, bundle.get(str));
        }
        return jSONObject;
    }

    public static boolean zzd(Context context, String str, String str2) {
        return zzbih.zzdd(context).checkPermission(str2, str) == 0;
    }

    public static void zze(Context context, String str, String str2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str2);
        zza(context, str, (List<String>) arrayList);
    }

    public static Map<String, String> zzf(Uri uri) {
        if (uri == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (String next : zzbt.zzen().zzg(uri)) {
            hashMap.put(next, uri.getQueryParameter(next));
        }
        return hashMap;
    }

    public static void zzf(Context context, String str, String str2) {
        try {
            FileOutputStream openFileOutput = context.openFileOutput(str, 0);
            openFileOutput.write(str2.getBytes("UTF-8"));
            openFileOutput.close();
        } catch (Exception e) {
            zzahw.zzb("Error writing to file in internal storage.", e);
        }
    }

    public static int[] zzf(Activity activity) {
        View findViewById;
        Window window = activity.getWindow();
        if (window == null || (findViewById = window.findViewById(16908290)) == null) {
            return zzre();
        }
        return new int[]{findViewById.getWidth(), findViewById.getHeight()};
    }

    public static String zzm(Context context, String str) {
        try {
            return new String(zzp.zza(context.openFileInput(str), true), "UTF-8");
        } catch (IOException unused) {
            zzahw.zzby("Error reading from internal storage.");
            return "";
        }
    }

    public static Bitmap zzr(View view) {
        view.setDrawingCacheEnabled(true);
        Bitmap createBitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return createBitmap;
    }

    private static String zzrb() {
        StringBuilder sb = new StringBuilder(256);
        sb.append("Mozilla/5.0 (Linux; U; Android");
        if (Build.VERSION.RELEASE != null) {
            sb.append(" ");
            sb.append(Build.VERSION.RELEASE);
        }
        sb.append("; ");
        sb.append(Locale.getDefault());
        if (Build.DEVICE != null) {
            sb.append("; ");
            sb.append(Build.DEVICE);
            if (Build.DISPLAY != null) {
                sb.append(" Build/");
                sb.append(Build.DISPLAY);
            }
        }
        sb.append(") AppleWebKit/533 Version/4.0 Safari/533");
        return sb.toString();
    }

    public static String zzrc() {
        return UUID.randomUUID().toString();
    }

    public static String zzrd() {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        if (str2.startsWith(str)) {
            return str2;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 1 + String.valueOf(str2).length());
        sb.append(str);
        sb.append(" ");
        sb.append(str2);
        return sb.toString();
    }

    private static int[] zzre() {
        return new int[]{0, 0};
    }

    public static Bundle zzrf() {
        Bundle bundle = new Bundle();
        try {
            if (((Boolean) zzlc.zzio().zzd(zzoi.zzbmj)).booleanValue()) {
                Debug.MemoryInfo memoryInfo = new Debug.MemoryInfo();
                Debug.getMemoryInfo(memoryInfo);
                bundle.putParcelable("debug_memory_info", memoryInfo);
            }
            if (((Boolean) zzlc.zzio().zzd(zzoi.zzbmk)).booleanValue()) {
                Runtime runtime = Runtime.getRuntime();
                bundle.putLong("runtime_free_memory", runtime.freeMemory());
                bundle.putLong("runtime_max_memory", runtime.maxMemory());
                bundle.putLong("runtime_total_memory", runtime.totalMemory());
            }
            bundle.putInt("web_view_count", zzbt.zzep().zzqd());
        } catch (Exception e) {
            zzahw.zzc("Unable to gather memory stats", e);
        }
        return bundle;
    }

    public static Bitmap zzs(View view) {
        if (view == null) {
            return null;
        }
        Bitmap zzu = zzu(view);
        return zzu == null ? zzt(view) : zzu;
    }

    private static Bitmap zzt(View view) {
        try {
            int width = view.getWidth();
            int height = view.getHeight();
            if (width != 0) {
                if (height != 0) {
                    Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.RGB_565);
                    Canvas canvas = new Canvas(createBitmap);
                    view.layout(0, 0, width, height);
                    view.draw(canvas);
                    return createBitmap;
                }
            }
            zzahw.zzcz("Width or height of view is zero");
            return null;
        } catch (RuntimeException e) {
            zzahw.zzb("Fail to capture the webview", e);
            return null;
        }
    }

    private static Bitmap zzu(View view) {
        Bitmap bitmap = null;
        try {
            boolean isDrawingCacheEnabled = view.isDrawingCacheEnabled();
            view.setDrawingCacheEnabled(true);
            Bitmap drawingCache = view.getDrawingCache();
            if (drawingCache != null) {
                bitmap = Bitmap.createBitmap(drawingCache);
            }
            view.setDrawingCacheEnabled(isDrawingCacheEnabled);
        } catch (RuntimeException e) {
            zzahw.zzb("Fail to capture the web view", e);
        }
        return bitmap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:8:0x0016 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0017  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean zzv(android.view.View r2) {
        /*
            android.view.View r2 = r2.getRootView()
            r0 = 0
            if (r2 == 0) goto L_0x0012
            android.content.Context r2 = r2.getContext()
            boolean r1 = r2 instanceof android.app.Activity
            if (r1 == 0) goto L_0x0012
            android.app.Activity r2 = (android.app.Activity) r2
            goto L_0x0013
        L_0x0012:
            r2 = r0
        L_0x0013:
            r1 = 0
            if (r2 != 0) goto L_0x0017
            return r1
        L_0x0017:
            android.view.Window r2 = r2.getWindow()
            if (r2 != 0) goto L_0x001e
            goto L_0x0022
        L_0x001e:
            android.view.WindowManager$LayoutParams r0 = r2.getAttributes()
        L_0x0022:
            if (r0 == 0) goto L_0x002d
            int r2 = r0.flags
            r0 = 524288(0x80000, float:7.34684E-40)
            r2 = r2 & r0
            if (r2 == 0) goto L_0x002d
            r2 = 1
            return r2
        L_0x002d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaij.zzv(android.view.View):boolean");
    }

    public static int zzw(View view) {
        if (view == null) {
            return -1;
        }
        ViewParent parent = view.getParent();
        while (parent != null && !(parent instanceof AdapterView)) {
            parent = parent.getParent();
        }
        if (parent == null) {
            return -1;
        }
        return ((AdapterView) parent).getPositionForView(view);
    }

    public final JSONObject zza(Bundle bundle, JSONObject jSONObject) {
        if (bundle != null) {
            try {
                return zzd(bundle);
            } catch (JSONException e) {
                zzahw.zzb("Error converting Bundle to JSON", e);
            }
        }
        return null;
    }

    public final void zza(Context context, String str, WebSettings webSettings) {
        webSettings.setUserAgentString(zzl(context, str));
    }

    public final void zza(Context context, String str, String str2, Bundle bundle, boolean z) {
        if (z) {
            zzbt.zzel();
            bundle.putString("device", zzrd());
            bundle.putString("eids", TextUtils.join(",", zzoi.zzjf()));
        }
        zzlc.zzij();
        zzako.zza(context, str, str2, bundle, z, new zzaim(this, context, str));
    }

    public final void zza(Context context, String str, boolean z, HttpURLConnection httpURLConnection) {
        httpURLConnection.setConnectTimeout(60000);
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setReadTimeout(60000);
        httpURLConnection.setRequestProperty("User-Agent", zzl(context, str));
        httpURLConnection.setUseCaches(false);
    }

    public final void zza(Context context, List<String> list) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (!TextUtils.isEmpty(zzfxd.zzfk(activity))) {
                if (list == null) {
                    zzahw.v("Cannot ping urls: empty list.");
                } else if (!zzpf.zzh(context)) {
                    zzahw.v("Cannot ping url because custom tabs is not supported");
                } else {
                    zzpf zzpf = new zzpf();
                    zzpf.zza((zzpg) new zzaik(this, list, zzpf, context));
                    zzpf.zzd(activity);
                }
            }
        }
    }

    public final boolean zza(View view, Context context) {
        Context applicationContext = context.getApplicationContext();
        return zza(view, applicationContext != null ? (PowerManager) applicationContext.getSystemService("power") : null, zzaq(context));
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0074 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zza(android.view.View r4, android.os.PowerManager r5, android.app.KeyguardManager r6) {
        /*
            r3 = this;
            com.google.android.gms.internal.zzaij r0 = com.google.android.gms.ads.internal.zzbt.zzel()
            boolean r0 = r0.zzdfo
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x002f
            if (r6 != 0) goto L_0x000e
            r6 = 0
            goto L_0x0012
        L_0x000e:
            boolean r6 = r6.inKeyguardRestrictedInputMode()
        L_0x0012:
            if (r6 == 0) goto L_0x002f
            com.google.android.gms.internal.zzny<java.lang.Boolean> r6 = com.google.android.gms.internal.zzoi.zzbqn
            com.google.android.gms.internal.zzog r0 = com.google.android.gms.internal.zzlc.zzio()
            java.lang.Object r6 = r0.zzd(r6)
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            if (r6 == 0) goto L_0x002d
            boolean r6 = zzv(r4)
            if (r6 == 0) goto L_0x002d
            goto L_0x002f
        L_0x002d:
            r6 = 0
            goto L_0x0030
        L_0x002f:
            r6 = 1
        L_0x0030:
            int r0 = r4.getVisibility()
            if (r0 != 0) goto L_0x0075
            boolean r0 = r4.isShown()
            if (r0 == 0) goto L_0x0075
            if (r5 == 0) goto L_0x0047
            boolean r5 = r5.isScreenOn()
            if (r5 == 0) goto L_0x0045
            goto L_0x0047
        L_0x0045:
            r5 = 0
            goto L_0x0048
        L_0x0047:
            r5 = 1
        L_0x0048:
            if (r5 == 0) goto L_0x0075
            if (r6 == 0) goto L_0x0075
            com.google.android.gms.internal.zzny<java.lang.Boolean> r5 = com.google.android.gms.internal.zzoi.zzbql
            com.google.android.gms.internal.zzog r6 = com.google.android.gms.internal.zzlc.zzio()
            java.lang.Object r5 = r6.zzd(r5)
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            if (r5 == 0) goto L_0x0074
            android.graphics.Rect r5 = new android.graphics.Rect
            r5.<init>()
            boolean r5 = r4.getLocalVisibleRect(r5)
            if (r5 != 0) goto L_0x0074
            android.graphics.Rect r5 = new android.graphics.Rect
            r5.<init>()
            boolean r4 = r4.getGlobalVisibleRect(r5)
            if (r4 == 0) goto L_0x0075
        L_0x0074:
            return r1
        L_0x0075:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaij.zza(android.view.View, android.os.PowerManager, android.app.KeyguardManager):boolean");
    }

    public final boolean zzah(Context context) {
        if (this.zzdfp) {
            return false;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        context.getApplicationContext().registerReceiver(new zzaio(this, (zzaik) null), intentFilter);
        this.zzdfp = true;
        return true;
    }

    public final boolean zzai(Context context) {
        if (this.zzdfq) {
            return false;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.google.android.ads.intent.DEBUG_LOGGING_ENABLEMENT_CHANGED");
        context.getApplicationContext().registerReceiver(new zzain(this, (zzaik) null), intentFilter);
        this.zzdfq = true;
        return true;
    }

    public final void zzb(Context context, String str, String str2, Bundle bundle, boolean z) {
        if (((Boolean) zzlc.zzio().zzd(zzoi.zzbqq)).booleanValue()) {
            zza(context, str, str2, bundle, z);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0023, code lost:
        if (((java.lang.String) com.google.android.gms.internal.zzlc.zzio().zzd(com.google.android.gms.internal.zzoi.zzbol)).equals(r3.zzdfr.pattern()) == false) goto L_0x0025;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzcl(java.lang.String r4) {
        /*
            r3 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            r1 = 0
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            monitor-enter(r3)     // Catch:{ PatternSyntaxException -> 0x0046 }
            java.util.regex.Pattern r0 = r3.zzdfr     // Catch:{ all -> 0x0043 }
            if (r0 == 0) goto L_0x0025
            com.google.android.gms.internal.zzny<java.lang.String> r0 = com.google.android.gms.internal.zzoi.zzbol     // Catch:{ all -> 0x0043 }
            com.google.android.gms.internal.zzog r2 = com.google.android.gms.internal.zzlc.zzio()     // Catch:{ all -> 0x0043 }
            java.lang.Object r0 = r2.zzd(r0)     // Catch:{ all -> 0x0043 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x0043 }
            java.util.regex.Pattern r2 = r3.zzdfr     // Catch:{ all -> 0x0043 }
            java.lang.String r2 = r2.pattern()     // Catch:{ all -> 0x0043 }
            boolean r0 = r0.equals(r2)     // Catch:{ all -> 0x0043 }
            if (r0 != 0) goto L_0x0037
        L_0x0025:
            com.google.android.gms.internal.zzny<java.lang.String> r0 = com.google.android.gms.internal.zzoi.zzbol     // Catch:{ all -> 0x0043 }
            com.google.android.gms.internal.zzog r2 = com.google.android.gms.internal.zzlc.zzio()     // Catch:{ all -> 0x0043 }
            java.lang.Object r0 = r2.zzd(r0)     // Catch:{ all -> 0x0043 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x0043 }
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0)     // Catch:{ all -> 0x0043 }
            r3.zzdfr = r0     // Catch:{ all -> 0x0043 }
        L_0x0037:
            java.util.regex.Pattern r0 = r3.zzdfr     // Catch:{ all -> 0x0043 }
            java.util.regex.Matcher r4 = r0.matcher(r4)     // Catch:{ all -> 0x0043 }
            boolean r4 = r4.matches()     // Catch:{ all -> 0x0043 }
            monitor-exit(r3)     // Catch:{ all -> 0x0043 }
            return r4
        L_0x0043:
            r4 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0043 }
            throw r4     // Catch:{ PatternSyntaxException -> 0x0046 }
        L_0x0046:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaij.zzcl(java.lang.String):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0023, code lost:
        if (((java.lang.String) com.google.android.gms.internal.zzlc.zzio().zzd(com.google.android.gms.internal.zzoi.zzbom)).equals(r3.zzdfs.pattern()) == false) goto L_0x0025;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzcm(java.lang.String r4) {
        /*
            r3 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            r1 = 0
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            monitor-enter(r3)     // Catch:{ PatternSyntaxException -> 0x0046 }
            java.util.regex.Pattern r0 = r3.zzdfs     // Catch:{ all -> 0x0043 }
            if (r0 == 0) goto L_0x0025
            com.google.android.gms.internal.zzny<java.lang.String> r0 = com.google.android.gms.internal.zzoi.zzbom     // Catch:{ all -> 0x0043 }
            com.google.android.gms.internal.zzog r2 = com.google.android.gms.internal.zzlc.zzio()     // Catch:{ all -> 0x0043 }
            java.lang.Object r0 = r2.zzd(r0)     // Catch:{ all -> 0x0043 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x0043 }
            java.util.regex.Pattern r2 = r3.zzdfs     // Catch:{ all -> 0x0043 }
            java.lang.String r2 = r2.pattern()     // Catch:{ all -> 0x0043 }
            boolean r0 = r0.equals(r2)     // Catch:{ all -> 0x0043 }
            if (r0 != 0) goto L_0x0037
        L_0x0025:
            com.google.android.gms.internal.zzny<java.lang.String> r0 = com.google.android.gms.internal.zzoi.zzbom     // Catch:{ all -> 0x0043 }
            com.google.android.gms.internal.zzog r2 = com.google.android.gms.internal.zzlc.zzio()     // Catch:{ all -> 0x0043 }
            java.lang.Object r0 = r2.zzd(r0)     // Catch:{ all -> 0x0043 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x0043 }
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0)     // Catch:{ all -> 0x0043 }
            r3.zzdfs = r0     // Catch:{ all -> 0x0043 }
        L_0x0037:
            java.util.regex.Pattern r0 = r3.zzdfs     // Catch:{ all -> 0x0043 }
            java.util.regex.Matcher r4 = r0.matcher(r4)     // Catch:{ all -> 0x0043 }
            boolean r4 = r4.matches()     // Catch:{ all -> 0x0043 }
            monitor-exit(r3)     // Catch:{ all -> 0x0043 }
            return r4
        L_0x0043:
            r4 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0043 }
            throw r4     // Catch:{ PatternSyntaxException -> 0x0046 }
        L_0x0046:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaij.zzcm(java.lang.String):boolean");
    }

    public final int[] zzg(Activity activity) {
        int[] zzf = zzf(activity);
        zzlc.zzij();
        zzlc.zzij();
        return new int[]{zzako.zzb((Context) activity, zzf[0]), zzako.zzb((Context) activity, zzf[1])};
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0009, code lost:
        r0 = r0.findViewById(16908290);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int[] zzh(android.app.Activity r7) {
        /*
            r6 = this;
            android.view.Window r0 = r7.getWindow()
            r1 = 2
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L_0x0021
            r4 = 16908290(0x1020002, float:2.3877235E-38)
            android.view.View r0 = r0.findViewById(r4)
            if (r0 == 0) goto L_0x0021
            int[] r4 = new int[r1]
            int r5 = r0.getTop()
            r4[r3] = r5
            int r0 = r0.getBottom()
            r4[r2] = r0
            goto L_0x0025
        L_0x0021:
            int[] r4 = zzre()
        L_0x0025:
            int[] r0 = new int[r1]
            com.google.android.gms.internal.zzlc.zzij()
            r1 = r4[r3]
            int r1 = com.google.android.gms.internal.zzako.zzb((android.content.Context) r7, (int) r1)
            r0[r3] = r1
            com.google.android.gms.internal.zzlc.zzij()
            r1 = r4[r2]
            int r7 = com.google.android.gms.internal.zzako.zzb((android.content.Context) r7, (int) r1)
            r0[r2] = r7
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaij.zzh(android.app.Activity):int[]");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0039, code lost:
        continue;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x001b */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0043 */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0023 A[Catch:{ Exception -> 0x00b1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0055 A[Catch:{ Exception -> 0x00b1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x005a A[Catch:{ Exception -> 0x00b1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a2 A[Catch:{ Exception -> 0x00b1 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zzl(android.content.Context r5, java.lang.String r6) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.mLock
            monitor-enter(r0)
            java.lang.String r1 = r4.zzddt     // Catch:{ all -> 0x00cb }
            if (r1 == 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x00cb }
            return r1
        L_0x0009:
            if (r6 != 0) goto L_0x0011
            java.lang.String r5 = zzrb()     // Catch:{ all -> 0x00cb }
            monitor-exit(r0)     // Catch:{ all -> 0x00cb }
            return r5
        L_0x0011:
            com.google.android.gms.internal.zzaip r1 = com.google.android.gms.ads.internal.zzbt.zzen()     // Catch:{ Exception -> 0x001b }
            java.lang.String r1 = r1.getDefaultUserAgent(r5)     // Catch:{ Exception -> 0x001b }
            r4.zzddt = r1     // Catch:{ Exception -> 0x001b }
        L_0x001b:
            java.lang.String r1 = r4.zzddt     // Catch:{ all -> 0x00cb }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x00cb }
            if (r1 == 0) goto L_0x0069
            com.google.android.gms.internal.zzlc.zzij()     // Catch:{ all -> 0x00cb }
            boolean r1 = com.google.android.gms.internal.zzako.zzsa()     // Catch:{ all -> 0x00cb }
            if (r1 != 0) goto L_0x0063
            r1 = 0
            r4.zzddt = r1     // Catch:{ all -> 0x00cb }
            android.os.Handler r1 = zzdfn     // Catch:{ all -> 0x00cb }
            com.google.android.gms.internal.zzail r2 = new com.google.android.gms.internal.zzail     // Catch:{ all -> 0x00cb }
            r2.<init>(r4, r5)     // Catch:{ all -> 0x00cb }
            r1.post(r2)     // Catch:{ all -> 0x00cb }
        L_0x0039:
            java.lang.String r1 = r4.zzddt     // Catch:{ all -> 0x00cb }
            if (r1 != 0) goto L_0x0069
            java.lang.Object r1 = r4.mLock     // Catch:{ InterruptedException -> 0x0043 }
            r1.wait()     // Catch:{ InterruptedException -> 0x0043 }
            goto L_0x0039
        L_0x0043:
            java.lang.String r1 = zzrb()     // Catch:{ all -> 0x00cb }
            r4.zzddt = r1     // Catch:{ all -> 0x00cb }
            java.lang.String r2 = "Interrupted, use default user agent: "
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x00cb }
            int r3 = r1.length()     // Catch:{ all -> 0x00cb }
            if (r3 == 0) goto L_0x005a
            java.lang.String r1 = r2.concat(r1)     // Catch:{ all -> 0x00cb }
            goto L_0x005f
        L_0x005a:
            java.lang.String r1 = new java.lang.String     // Catch:{ all -> 0x00cb }
            r1.<init>(r2)     // Catch:{ all -> 0x00cb }
        L_0x005f:
            com.google.android.gms.internal.zzahw.zzcz(r1)     // Catch:{ all -> 0x00cb }
            goto L_0x0039
        L_0x0063:
            java.lang.String r1 = zzaj(r5)     // Catch:{ all -> 0x00cb }
            r4.zzddt = r1     // Catch:{ all -> 0x00cb }
        L_0x0069:
            java.lang.String r1 = r4.zzddt     // Catch:{ all -> 0x00cb }
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x00cb }
            java.lang.String r2 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x00cb }
            int r2 = r2.length()     // Catch:{ all -> 0x00cb }
            int r2 = r2 + 10
            java.lang.String r3 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x00cb }
            int r3 = r3.length()     // Catch:{ all -> 0x00cb }
            int r2 = r2 + r3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00cb }
            r3.<init>(r2)     // Catch:{ all -> 0x00cb }
            r3.append(r1)     // Catch:{ all -> 0x00cb }
            java.lang.String r1 = " (Mobile; "
            r3.append(r1)     // Catch:{ all -> 0x00cb }
            r3.append(r6)     // Catch:{ all -> 0x00cb }
            java.lang.String r6 = r3.toString()     // Catch:{ all -> 0x00cb }
            r4.zzddt = r6     // Catch:{ all -> 0x00cb }
            com.google.android.gms.internal.zzbig r5 = com.google.android.gms.internal.zzbih.zzdd(r5)     // Catch:{ Exception -> 0x00b1 }
            boolean r5 = r5.zzaoe()     // Catch:{ Exception -> 0x00b1 }
            if (r5 == 0) goto L_0x00bb
            java.lang.String r5 = r4.zzddt     // Catch:{ Exception -> 0x00b1 }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ Exception -> 0x00b1 }
            java.lang.String r6 = ";aia"
            java.lang.String r5 = r5.concat(r6)     // Catch:{ Exception -> 0x00b1 }
            r4.zzddt = r5     // Catch:{ Exception -> 0x00b1 }
            goto L_0x00bb
        L_0x00b1:
            r5 = move-exception
            com.google.android.gms.internal.zzahi r6 = com.google.android.gms.ads.internal.zzbt.zzep()     // Catch:{ all -> 0x00cb }
            java.lang.String r1 = "AdUtil.getUserAgent"
            r6.zza(r5, r1)     // Catch:{ all -> 0x00cb }
        L_0x00bb:
            java.lang.String r5 = r4.zzddt     // Catch:{ all -> 0x00cb }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x00cb }
            java.lang.String r6 = ")"
            java.lang.String r5 = r5.concat(r6)     // Catch:{ all -> 0x00cb }
            r4.zzddt = r5     // Catch:{ all -> 0x00cb }
            monitor-exit(r0)     // Catch:{ all -> 0x00cb }
            return r5
        L_0x00cb:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00cb }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaij.zzl(android.content.Context, java.lang.String):java.lang.String");
    }

    public final JSONObject zzq(Map<String, ?> map) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            for (String next : map.keySet()) {
                zza(jSONObject, next, (Object) map.get(next));
            }
            return jSONObject;
        } catch (ClassCastException e) {
            String valueOf = String.valueOf(e.getMessage());
            throw new JSONException(valueOf.length() != 0 ? "Could not convert map to JSON: ".concat(valueOf) : new String("Could not convert map to JSON: "));
        }
    }
}
