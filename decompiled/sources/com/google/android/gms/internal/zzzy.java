package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbb;
import com.google.android.gms.plus.PlusShare;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzabh
public final class zzzy implements Callable<zzahd> {
    private static long zzcow = 10;
    private final Context mContext;
    private int mErrorCode;
    private final Object mLock = new Object();
    private final zzov zzanh;
    private final zzaan zzarm;
    private final zzcv zzbyz;
    private final zzahe zzcob;
    private final zzajr zzcox;
    /* access modifiers changed from: private */
    public final zzbb zzcoy;
    private boolean zzcoz;
    private List<String> zzcpa;
    private JSONObject zzcpb;
    private String zzcpc;

    public zzzy(Context context, zzbb zzbb, zzajr zzajr, zzcv zzcv, zzahe zzahe, zzov zzov) {
        this.mContext = context;
        this.zzcoy = zzbb;
        this.zzcox = zzajr;
        this.zzcob = zzahe;
        this.zzbyz = zzcv;
        this.zzanh = zzov;
        this.zzarm = zzbb.zzds();
        this.zzcoz = false;
        this.mErrorCode = -2;
        this.zzcpa = null;
        this.zzcpc = null;
    }

    private final zzalt<zzpj> zza(JSONObject jSONObject, boolean z, boolean z2) throws JSONException {
        String string = z ? jSONObject.getString(PlusShare.KEY_CALL_TO_ACTION_URL) : jSONObject.optString(PlusShare.KEY_CALL_TO_ACTION_URL);
        double optDouble = jSONObject.optDouble("scale", 1.0d);
        boolean optBoolean = jSONObject.optBoolean("is_transparent", true);
        if (!TextUtils.isEmpty(string)) {
            return z2 ? zzali.zzh(new zzpj((Drawable) null, Uri.parse(string), optDouble)) : this.zzcox.zza(string, new zzaac(this, z, optDouble, optBoolean, string));
        }
        zzd(0, z);
        return zzali.zzh(null);
    }

    static zzaof zzb(zzalt<zzaof> zzalt) {
        try {
            return (zzaof) zzalt.get((long) ((Integer) zzlc.zzio().zzd(zzoi.zzbsq)).intValue(), TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            zzahw.zzc("InterruptedException occurred while waiting for video to load", e);
            Thread.currentThread().interrupt();
            return null;
        } catch (CancellationException | ExecutionException | TimeoutException e2) {
            zzahw.zzc("Exception occurred while waiting for video to load", e2);
            return null;
        }
    }

    private static Integer zzb(JSONObject jSONObject, String str) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(str);
            return Integer.valueOf(Color.rgb(jSONObject2.getInt("r"), jSONObject2.getInt("g"), jSONObject2.getInt("b")));
        } catch (JSONException unused) {
            return null;
        }
    }

    private final zzahd zzc(zzpx zzpx) {
        int i;
        synchronized (this.mLock) {
            int i2 = this.mErrorCode;
            i = (zzpx == null && i2 == -2) ? 0 : i2;
        }
        return new zzahd(this.zzcob.zzcvm.zzcrv, (zzaof) null, this.zzcob.zzdcw.zzchw, i, this.zzcob.zzdcw.zzchx, this.zzcpa, this.zzcob.zzdcw.orientation, this.zzcob.zzdcw.zzcic, this.zzcob.zzcvm.zzcry, false, (zzvp) null, (zzwi) null, (String) null, (zzvq) null, (zzvs) null, 0, this.zzcob.zzaud, this.zzcob.zzdcw.zzctn, this.zzcob.zzdcn, this.zzcob.zzdco, this.zzcob.zzdcw.zzctt, this.zzcpb, i != -2 ? null : zzpx, (zzagd) null, (List<String>) null, (List<String>) null, this.zzcob.zzdcw.zzcuf, this.zzcob.zzdcw.zzcug, (String) null, this.zzcob.zzdcw.zzchz, this.zzcpc, this.zzcob.zzdcu, this.zzcob.zzdcw.zzaqw, this.zzcob.zzdcv);
    }

    /* access modifiers changed from: private */
    public final void zzc(zzro zzro, String str) {
        try {
            zzry zzs = this.zzcoy.zzs(zzro.getCustomTemplateId());
            if (zzs != null) {
                zzs.zzb(zzro, str);
            }
        } catch (RemoteException e) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 40);
            sb.append("Failed to call onCustomClick for asset ");
            sb.append(str);
            sb.append(".");
            zzahw.zzc(sb.toString(), e);
        }
    }

    private static String[] zzd(JSONObject jSONObject, String str) throws JSONException {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray == null) {
            return null;
        }
        String[] strArr = new String[optJSONArray.length()];
        for (int i = 0; i < optJSONArray.length(); i++) {
            strArr[i] = optJSONArray.getString(i);
        }
        return strArr;
    }

    private static <V> zzalt<List<V>> zzl(List<zzalt<V>> list) {
        zzamd zzamd = new zzamd();
        int size = list.size();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (zzalt<V> zza : list) {
            zza.zza(new zzaad(atomicInteger, size, zzamd, list), zzaid.zzdfi);
        }
        return zzamd;
    }

    /* access modifiers changed from: private */
    public static <V> List<V> zzm(List<zzalt<V>> list) throws ExecutionException, InterruptedException {
        ArrayList arrayList = new ArrayList();
        for (zzalt<V> zzalt : list) {
            Object obj = zzalt.get();
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004a, code lost:
        if (r4.length() != 0) goto L_0x0050;
     */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x007f A[Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0128 A[Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0129 A[Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0171 A[Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x01a0  */
    /* renamed from: zznv */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzahd call() {
        /*
            r13 = this;
            java.lang.String r0 = "custom_template_id"
            r1 = 0
            r2 = 0
            com.google.android.gms.ads.internal.zzbb r3 = r13.zzcoy     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            java.lang.String r12 = r3.getUuid()     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            boolean r3 = r13.zznw()     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            if (r3 != 0) goto L_0x0078
            com.google.android.gms.internal.zzamd r3 = new com.google.android.gms.internal.zzamd     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            r3.<init>()     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            com.google.android.gms.internal.zzzx r3 = new com.google.android.gms.internal.zzzx     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            r3.<init>()     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            com.google.android.gms.internal.zzahe r4 = r13.zzcob     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            com.google.android.gms.internal.zzacj r4 = r4.zzdcw     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            java.lang.String r4 = r4.body     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            r3.<init>(r4)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            com.google.android.gms.internal.zzahe r5 = r13.zzcob     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            com.google.android.gms.internal.zzacj r5 = r5.zzdcw     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            java.lang.String r5 = r5.body     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            r4.<init>(r5)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            int r5 = r4.length()     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            java.lang.String r6 = "ads"
            if (r5 == 0) goto L_0x004c
            org.json.JSONArray r4 = r4.optJSONArray(r6)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            if (r4 == 0) goto L_0x0043
            org.json.JSONObject r4 = r4.optJSONObject(r1)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            goto L_0x0044
        L_0x0043:
            r4 = r2
        L_0x0044:
            if (r4 == 0) goto L_0x004c
            int r4 = r4.length()     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            if (r4 != 0) goto L_0x0050
        L_0x004c:
            r4 = 3
            r13.zzz(r4)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
        L_0x0050:
            com.google.android.gms.internal.zzaan r4 = r13.zzarm     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            com.google.android.gms.internal.zzalt r3 = r4.zzh(r3)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            long r4 = zzcow     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            java.util.concurrent.TimeUnit r7 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            java.lang.Object r3 = r3.get(r4, r7)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            org.json.JSONObject r3 = (org.json.JSONObject) r3     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            java.lang.String r4 = "success"
            boolean r4 = r3.optBoolean(r4, r1)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            if (r4 == 0) goto L_0x0078
            java.lang.String r4 = "json"
            org.json.JSONObject r3 = r3.getJSONObject(r4)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            org.json.JSONArray r3 = r3.optJSONArray(r6)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            org.json.JSONObject r3 = r3.getJSONObject(r1)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            r9 = r3
            goto L_0x0079
        L_0x0078:
            r9 = r2
        L_0x0079:
            boolean r3 = r13.zznw()     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            if (r3 != 0) goto L_0x011d
            if (r9 != 0) goto L_0x0083
            goto L_0x011d
        L_0x0083:
            java.lang.String r3 = "template_id"
            java.lang.String r3 = r9.getString(r3)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            com.google.android.gms.internal.zzahe r4 = r13.zzcob     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            com.google.android.gms.internal.zzacf r4 = r4.zzcvm     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            com.google.android.gms.internal.zzqh r4 = r4.zzauq     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            if (r4 == 0) goto L_0x009a
            com.google.android.gms.internal.zzahe r4 = r13.zzcob     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            com.google.android.gms.internal.zzacf r4 = r4.zzcvm     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            com.google.android.gms.internal.zzqh r4 = r4.zzauq     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            boolean r4 = r4.zzbzj     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            goto L_0x009b
        L_0x009a:
            r4 = 0
        L_0x009b:
            com.google.android.gms.internal.zzahe r5 = r13.zzcob     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            com.google.android.gms.internal.zzacf r5 = r5.zzcvm     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            com.google.android.gms.internal.zzqh r5 = r5.zzauq     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            if (r5 == 0) goto L_0x00ac
            com.google.android.gms.internal.zzahe r5 = r13.zzcob     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            com.google.android.gms.internal.zzacf r5 = r5.zzcvm     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            com.google.android.gms.internal.zzqh r5 = r5.zzauq     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            boolean r5 = r5.zzbzl     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            goto L_0x00ad
        L_0x00ac:
            r5 = 0
        L_0x00ad:
            java.lang.String r6 = "2"
            boolean r6 = r6.equals(r3)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            if (r6 == 0) goto L_0x00bf
            com.google.android.gms.internal.zzaao r0 = new com.google.android.gms.internal.zzaao     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            com.google.android.gms.internal.zzahe r3 = r13.zzcob     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            boolean r3 = r3.zzdcv     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            r0.<init>(r4, r5, r3)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            goto L_0x011e
        L_0x00bf:
            java.lang.String r6 = "1"
            boolean r6 = r6.equals(r3)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            if (r6 == 0) goto L_0x00d1
            com.google.android.gms.internal.zzaap r0 = new com.google.android.gms.internal.zzaap     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            com.google.android.gms.internal.zzahe r3 = r13.zzcob     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            boolean r3 = r3.zzdcv     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            r0.<init>(r4, r5, r3)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            goto L_0x011e
        L_0x00d1:
            java.lang.String r5 = "3"
            boolean r3 = r5.equals(r3)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            if (r3 == 0) goto L_0x011a
            java.lang.String r3 = r9.getString(r0)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            com.google.android.gms.internal.zzamd r5 = new com.google.android.gms.internal.zzamd     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            r5.<init>()     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            android.os.Handler r6 = com.google.android.gms.internal.zzaij.zzdfn     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            com.google.android.gms.internal.zzzz r7 = new com.google.android.gms.internal.zzzz     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            r7.<init>(r13, r5, r3)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            r6.post(r7)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            long r6 = zzcow     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            java.lang.Object r3 = r5.get(r6, r3)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            if (r3 == 0) goto L_0x00fc
            com.google.android.gms.internal.zzaaq r0 = new com.google.android.gms.internal.zzaaq     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            r0.<init>(r4)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            goto L_0x011e
        L_0x00fc:
            java.lang.String r3 = "No handler for custom template: "
            java.lang.String r0 = r9.getString(r0)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            int r4 = r0.length()     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            if (r4 == 0) goto L_0x0111
            java.lang.String r0 = r3.concat(r0)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            goto L_0x0116
        L_0x0111:
            java.lang.String r0 = new java.lang.String     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            r0.<init>(r3)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
        L_0x0116:
            com.google.android.gms.internal.zzahw.e(r0)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            goto L_0x011d
        L_0x011a:
            r13.zzz(r1)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
        L_0x011d:
            r0 = r2
        L_0x011e:
            boolean r3 = r13.zznw()     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            if (r3 != 0) goto L_0x016c
            if (r0 == 0) goto L_0x016c
            if (r9 != 0) goto L_0x0129
            goto L_0x016c
        L_0x0129:
            java.lang.String r3 = "tracking_urls_and_actions"
            org.json.JSONObject r3 = r9.getJSONObject(r3)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            java.lang.String r4 = "impression_tracking_urls"
            java.lang.String[] r4 = zzd((org.json.JSONObject) r3, (java.lang.String) r4)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            if (r4 != 0) goto L_0x0139
            r4 = r2
            goto L_0x013d
        L_0x0139:
            java.util.List r4 = java.util.Arrays.asList(r4)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
        L_0x013d:
            r13.zzcpa = r4     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            java.lang.String r4 = "active_view"
            org.json.JSONObject r3 = r3.optJSONObject(r4)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            r13.zzcpb = r3     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            java.lang.String r3 = "debug_signals"
            java.lang.String r3 = r9.optString(r3)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            r13.zzcpc = r3     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            com.google.android.gms.internal.zzpx r0 = r0.zza(r13, r9)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            com.google.android.gms.internal.zzpz r3 = new com.google.android.gms.internal.zzpz     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            android.content.Context r5 = r13.mContext     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            com.google.android.gms.ads.internal.zzbb r6 = r13.zzcoy     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            com.google.android.gms.internal.zzaan r7 = r13.zzarm     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            com.google.android.gms.internal.zzcv r8 = r13.zzbyz     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            com.google.android.gms.internal.zzahe r4 = r13.zzcob     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            com.google.android.gms.internal.zzacf r4 = r4.zzcvm     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            com.google.android.gms.internal.zzala r11 = r4.zzatz     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            r4 = r3
            r10 = r0
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            r0.zzb(r3)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            goto L_0x016d
        L_0x016c:
            r0 = r2
        L_0x016d:
            boolean r3 = r0 instanceof com.google.android.gms.internal.zzpo     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            if (r3 == 0) goto L_0x0187
            r3 = r0
            com.google.android.gms.internal.zzpo r3 = (com.google.android.gms.internal.zzpo) r3     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            com.google.android.gms.internal.zzzx r4 = new com.google.android.gms.internal.zzzx     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            r4.<init>()     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            com.google.android.gms.internal.zzaaa r5 = new com.google.android.gms.internal.zzaaa     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            r5.<init>(r13, r3)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            r4.zzcov = r5     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            com.google.android.gms.internal.zzaan r3 = r13.zzarm     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            java.lang.String r4 = "/nativeAdCustomClick"
            r3.zza((java.lang.String) r4, r5)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
        L_0x0187:
            com.google.android.gms.internal.zzahd r0 = r13.zzc(r0)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x019b, JSONException -> 0x0194, TimeoutException -> 0x0190, Exception -> 0x018c }
            return r0
        L_0x018c:
            r0 = move-exception
            java.lang.String r3 = "Error occured while doing native ads initialization."
            goto L_0x0197
        L_0x0190:
            r0 = move-exception
            java.lang.String r3 = "Timeout when loading native ad."
            goto L_0x0197
        L_0x0194:
            r0 = move-exception
            java.lang.String r3 = "Malformed native JSON response."
        L_0x0197:
            com.google.android.gms.internal.zzahw.zzc(r3, r0)
            goto L_0x019c
        L_0x019b:
        L_0x019c:
            boolean r0 = r13.zzcoz
            if (r0 != 0) goto L_0x01a3
            r13.zzz(r1)
        L_0x01a3:
            com.google.android.gms.internal.zzahd r0 = r13.zzc(r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzzy.call():com.google.android.gms.internal.zzahd");
    }

    private final boolean zznw() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzcoz;
        }
        return z;
    }

    private final void zzz(int i) {
        synchronized (this.mLock) {
            this.zzcoz = true;
            this.mErrorCode = i;
        }
    }

    public final zzalt<zzpj> zza(JSONObject jSONObject, String str, boolean z, boolean z2) throws JSONException {
        JSONObject jSONObject2 = z ? jSONObject.getJSONObject(str) : jSONObject.optJSONObject(str);
        if (jSONObject2 == null) {
            jSONObject2 = new JSONObject();
        }
        return zza(jSONObject2, z, z2);
    }

    public final List<zzalt<zzpj>> zza(JSONObject jSONObject, String str, boolean z, boolean z2, boolean z3) throws JSONException {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        ArrayList arrayList = new ArrayList();
        if (optJSONArray == null || optJSONArray.length() == 0) {
            zzd(0, false);
            return arrayList;
        }
        int length = z3 ? optJSONArray.length() : 1;
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
            if (jSONObject2 == null) {
                jSONObject2 = new JSONObject();
            }
            arrayList.add(zza(jSONObject2, false, z2));
        }
        return arrayList;
    }

    public final Future<zzpj> zza(JSONObject jSONObject, String str, boolean z) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject(str);
        boolean optBoolean = jSONObject2.optBoolean("require", true);
        if (jSONObject2 == null) {
            jSONObject2 = new JSONObject();
        }
        return zza(jSONObject2, optBoolean, z);
    }

    public final zzalt<zzaof> zzc(JSONObject jSONObject, String str) throws JSONException {
        JSONObject optJSONObject = jSONObject.optJSONObject(str);
        if (optJSONObject == null) {
            return zzali.zzh(null);
        }
        if (TextUtils.isEmpty(optJSONObject.optString("vast_xml"))) {
            zzahw.zzcz("Required field 'vast_xml' is missing");
            return zzali.zzh(null);
        }
        zzaaf zzaaf = new zzaaf(this.mContext, this.zzbyz, this.zzcob, this.zzanh, this.zzcoy);
        zzamd zzamd = new zzamd();
        zzaly.zzdjt.execute(new zzaag(zzaaf, optJSONObject, zzamd));
        return zzamd;
    }

    public final void zzd(int i, boolean z) {
        if (z) {
            zzz(i);
        }
    }

    public final zzalt<zzph> zzg(JSONObject jSONObject) throws JSONException {
        JSONObject optJSONObject = jSONObject.optJSONObject("attribution");
        if (optJSONObject == null) {
            return zzali.zzh(null);
        }
        String optString = optJSONObject.optString("text");
        int optInt = optJSONObject.optInt("text_size", -1);
        Integer zzb = zzb(optJSONObject, "text_color");
        Integer zzb2 = zzb(optJSONObject, "bg_color");
        int optInt2 = optJSONObject.optInt("animation_ms", 1000);
        int optInt3 = optJSONObject.optInt("presentation_ms", 4000);
        int i = (this.zzcob.zzcvm.zzauq == null || this.zzcob.zzcvm.zzauq.versionCode < 2) ? 1 : this.zzcob.zzcvm.zzauq.zzbzm;
        boolean optBoolean = optJSONObject.optBoolean("allow_pub_rendering");
        List arrayList = new ArrayList();
        if (optJSONObject.optJSONArray("images") != null) {
            arrayList = zza(optJSONObject, "images", false, false, true);
        } else {
            arrayList.add(zza(optJSONObject, "image", false, false));
        }
        return zzali.zza(zzl(arrayList), new zzaab(this, optString, zzb2, zzb, optInt, optInt3, optInt2, i, optBoolean), (Executor) zzaid.zzdfi);
    }
}
