package com.google.android.gms.internal;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.graphics.Rect;
import android.os.PowerManager;
import android.os.Process;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.common.util.zzs;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@zzabh
public final class zzhm extends Thread {
    private final Object mLock;
    private boolean mStarted = false;
    private final int zzayc;
    private final int zzaye;
    private boolean zzazd = false;
    private final zzhh zzaze;
    private final zzabf zzazf;
    private final int zzazg;
    private final int zzazh;
    private final int zzazi;
    private final int zzazj;
    private final int zzazk;
    private final int zzazl;
    private final String zzazm;
    private final boolean zzazn;
    private boolean zzbm = false;

    public zzhm(zzhh zzhh, zzabf zzabf) {
        this.zzaze = zzhh;
        this.zzazf = zzabf;
        this.mLock = new Object();
        this.zzayc = ((Integer) zzlc.zzio().zzd(zzoi.zzbni)).intValue();
        this.zzazh = ((Integer) zzlc.zzio().zzd(zzoi.zzbnj)).intValue();
        this.zzaye = ((Integer) zzlc.zzio().zzd(zzoi.zzbnk)).intValue();
        this.zzazi = ((Integer) zzlc.zzio().zzd(zzoi.zzbnl)).intValue();
        this.zzazj = ((Integer) zzlc.zzio().zzd(zzoi.zzbno)).intValue();
        this.zzazk = ((Integer) zzlc.zzio().zzd(zzoi.zzbnq)).intValue();
        this.zzazl = ((Integer) zzlc.zzio().zzd(zzoi.zzbnr)).intValue();
        this.zzazg = ((Integer) zzlc.zzio().zzd(zzoi.zzbnm)).intValue();
        this.zzazm = (String) zzlc.zzio().zzd(zzoi.zzbnt);
        this.zzazn = ((Boolean) zzlc.zzio().zzd(zzoi.zzbnv)).booleanValue();
        setName("ContentFetchTask");
    }

    private final zzhq zza(View view, zzhg zzhg) {
        boolean z;
        if (view == null) {
            return new zzhq(this, 0, 0);
        }
        Context context = zzbt.zzeo().getContext();
        if (context != null) {
            String str = (String) view.getTag(context.getResources().getIdentifier((String) zzlc.zzio().zzd(zzoi.zzbns), "id", context.getPackageName()));
            if (!TextUtils.isEmpty(this.zzazm) && str != null && str.equals(this.zzazm)) {
                return new zzhq(this, 0, 0);
            }
        }
        boolean globalVisibleRect = view.getGlobalVisibleRect(new Rect());
        if ((view instanceof TextView) && !(view instanceof EditText)) {
            CharSequence text = ((TextView) view).getText();
            if (TextUtils.isEmpty(text)) {
                return new zzhq(this, 0, 0);
            }
            zzhg.zzb(text.toString(), globalVisibleRect, view.getX(), view.getY(), (float) view.getWidth(), (float) view.getHeight());
            return new zzhq(this, 1, 0);
        } else if ((view instanceof WebView) && !(view instanceof zzaof)) {
            zzhg.zzgt();
            WebView webView = (WebView) view;
            if (!zzs.zzanv()) {
                z = false;
            } else {
                zzhg.zzgt();
                webView.post(new zzho(this, zzhg, webView, globalVisibleRect));
                z = true;
            }
            return z ? new zzhq(this, 0, 1) : new zzhq(this, 0, 0);
        } else if (!(view instanceof ViewGroup)) {
            return new zzhq(this, 0, 0);
        } else {
            ViewGroup viewGroup = (ViewGroup) view;
            int i = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < viewGroup.getChildCount(); i3++) {
                zzhq zza = zza(viewGroup.getChildAt(i3), zzhg);
                i += zza.zzazv;
                i2 += zza.zzazw;
            }
            return new zzhq(this, i, i2);
        }
    }

    private static boolean zzgy() {
        try {
            Context context = zzbt.zzeo().getContext();
            if (context == null) {
                return false;
            }
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
            if (activityManager == null) {
                return false;
            }
            if (keyguardManager == null) {
                return false;
            }
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses == null) {
                return false;
            }
            for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
                if (Process.myPid() == next.pid) {
                    if (next.importance != 100 || keyguardManager.inKeyguardRestrictedInputMode()) {
                        return false;
                    }
                    PowerManager powerManager = (PowerManager) context.getSystemService("power");
                    return powerManager == null ? false : powerManager.isScreenOn();
                }
            }
            return false;
        } catch (Throwable th) {
            zzbt.zzep().zza(th, "ContentFetchTask.isInForeground");
            return false;
        }
    }

    private final void zzha() {
        synchronized (this.mLock) {
            this.zzazd = true;
            StringBuilder sb = new StringBuilder(42);
            sb.append("ContentFetchThread: paused, mPause = ");
            sb.append(true);
            zzahw.zzby(sb.toString());
        }
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeEndlessLoop(RegionMaker.java:368)
        	at jadx.core.dex.visitors.regions.RegionMaker.processLoop(RegionMaker.java:172)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    public final void run() {
        /*
            r4 = this;
        L_0x0000:
            boolean r0 = zzgy()     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
            if (r0 == 0) goto L_0x0059
            com.google.android.gms.internal.zzhi r0 = com.google.android.gms.ads.internal.zzbt.zzeo()     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
            android.app.Activity r0 = r0.getActivity()     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
            if (r0 != 0) goto L_0x0019
            java.lang.String r0 = "ContentFetchThread: no activity. Sleeping."
            com.google.android.gms.internal.zzahw.zzby(r0)     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
        L_0x0015:
            r4.zzha()     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
            goto L_0x005f
        L_0x0019:
            if (r0 == 0) goto L_0x005f
            r1 = 0
            android.view.Window r2 = r0.getWindow()     // Catch:{ Exception -> 0x003d, InterruptedException -> 0x0076 }
            if (r2 == 0) goto L_0x004c
            android.view.Window r2 = r0.getWindow()     // Catch:{ Exception -> 0x003d, InterruptedException -> 0x0076 }
            android.view.View r2 = r2.getDecorView()     // Catch:{ Exception -> 0x003d, InterruptedException -> 0x0076 }
            if (r2 == 0) goto L_0x004c
            android.view.Window r0 = r0.getWindow()     // Catch:{ Exception -> 0x003d, InterruptedException -> 0x0076 }
            android.view.View r0 = r0.getDecorView()     // Catch:{ Exception -> 0x003d, InterruptedException -> 0x0076 }
            r2 = 16908290(0x1020002, float:2.3877235E-38)
            android.view.View r0 = r0.findViewById(r2)     // Catch:{ Exception -> 0x003d, InterruptedException -> 0x0076 }
            r1 = r0
            goto L_0x004c
        L_0x003d:
            r0 = move-exception
            com.google.android.gms.internal.zzahi r2 = com.google.android.gms.ads.internal.zzbt.zzep()     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
            java.lang.String r3 = "ContentFetchTask.extractContent"
            r2.zza(r0, r3)     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
            java.lang.String r0 = "Failed getting root view of activity. Content not extracted."
            com.google.android.gms.internal.zzahw.zzby(r0)     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
        L_0x004c:
            if (r1 == 0) goto L_0x005f
            if (r1 == 0) goto L_0x005f
            com.google.android.gms.internal.zzhn r0 = new com.google.android.gms.internal.zzhn     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
            r0.<init>(r4, r1)     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
            r1.post(r0)     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
            goto L_0x005f
        L_0x0059:
            java.lang.String r0 = "ContentFetchTask: sleeping"
            com.google.android.gms.internal.zzahw.zzby(r0)     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
            goto L_0x0015
        L_0x005f:
            int r0 = r4.zzazg     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
            int r0 = r0 * 1000
            long r0 = (long) r0     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
            java.lang.Thread.sleep(r0)     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
            goto L_0x007c
        L_0x0068:
            r0 = move-exception
            java.lang.String r1 = "Error in ContentFetchTask"
            com.google.android.gms.internal.zzahw.zzb(r1, r0)
            com.google.android.gms.internal.zzabf r1 = r4.zzazf
            java.lang.String r2 = "ContentFetchTask.run"
            r1.zza(r0, r2)
            goto L_0x007c
        L_0x0076:
            r0 = move-exception
            java.lang.String r1 = "Error in ContentFetchTask"
            com.google.android.gms.internal.zzahw.zzb(r1, r0)
        L_0x007c:
            java.lang.Object r0 = r4.mLock
            monitor-enter(r0)
        L_0x007f:
            boolean r1 = r4.zzazd     // Catch:{ all -> 0x0091 }
            if (r1 == 0) goto L_0x008e
            java.lang.String r1 = "ContentFetchTask: waiting"
            com.google.android.gms.internal.zzahw.zzby(r1)     // Catch:{ InterruptedException -> 0x007f }
            java.lang.Object r1 = r4.mLock     // Catch:{ InterruptedException -> 0x007f }
            r1.wait()     // Catch:{ InterruptedException -> 0x007f }
            goto L_0x007f
        L_0x008e:
            monitor-exit(r0)     // Catch:{ all -> 0x0091 }
            goto L_0x0000
        L_0x0091:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0091 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzhm.run():void");
    }

    public final void wakeup() {
        synchronized (this.mLock) {
            this.zzazd = false;
            this.mLock.notifyAll();
            zzahw.zzby("ContentFetchThread: wakeup");
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzhg zzhg, WebView webView, String str, boolean z) {
        zzhg.zzgs();
        try {
            if (!TextUtils.isEmpty(str)) {
                String optString = new JSONObject(str).optString("text");
                if (this.zzazn || TextUtils.isEmpty(webView.getTitle())) {
                    zzhg.zza(optString, z, webView.getX(), webView.getY(), (float) webView.getWidth(), (float) webView.getHeight());
                } else {
                    String title = webView.getTitle();
                    StringBuilder sb = new StringBuilder(String.valueOf(title).length() + 1 + String.valueOf(optString).length());
                    sb.append(title);
                    sb.append("\n");
                    sb.append(optString);
                    zzhg.zza(sb.toString(), z, webView.getX(), webView.getY(), (float) webView.getWidth(), (float) webView.getHeight());
                }
            }
            if (zzhg.zzgn()) {
                this.zzaze.zzb(zzhg);
            }
        } catch (JSONException unused) {
            zzahw.zzby("Json string may be malformed.");
        } catch (Throwable th) {
            zzahw.zza("Failed to get webview content.", th);
            this.zzazf.zza(th, "ContentFetchTask.processWebViewContent");
        }
    }

    public final void zzgx() {
        synchronized (this.mLock) {
            if (this.mStarted) {
                zzahw.zzby("Content hash thread already started, quiting...");
                return;
            }
            this.mStarted = true;
            start();
        }
    }

    public final zzhg zzgz() {
        return this.zzaze.zzgw();
    }

    public final boolean zzhb() {
        return this.zzazd;
    }

    /* access modifiers changed from: package-private */
    public final void zzj(View view) {
        try {
            zzhg zzhg = new zzhg(this.zzayc, this.zzazh, this.zzaye, this.zzazi, this.zzazj, this.zzazk, this.zzazl);
            zzhq zza = zza(view, zzhg);
            zzhg.zzgu();
            if (zza.zzazv != 0 || zza.zzazw != 0) {
                if (zza.zzazw != 0 || zzhg.zzgv() != 0) {
                    if (zza.zzazw != 0 || !this.zzaze.zza(zzhg)) {
                        this.zzaze.zzc(zzhg);
                    }
                }
            }
        } catch (Exception e) {
            zzahw.zzb("Exception in fetchContentOnUIThread", e);
            this.zzazf.zza(e, "ContentFetchTask.fetchContent");
        }
    }
}
