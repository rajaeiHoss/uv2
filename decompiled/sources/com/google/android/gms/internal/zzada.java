package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.internal.gmsg.HttpClient;
import com.google.android.gms.ads.internal.gmsg.zzt;
import com.google.android.gms.ads.internal.gmsg.zzy;
import com.google.android.gms.ads.internal.js.zzaa;
import com.google.android.gms.ads.internal.js.zzc;
import com.google.android.gms.ads.internal.js.zzn;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;
import org.json.JSONException;
import org.json.JSONObject;

@zzabh
public final class zzada extends zzahs {
    private static final Object sLock = new Object();
    /* access modifiers changed from: private */
    public static zzn zzcql = null;
    private static long zzcuy = TimeUnit.SECONDS.toMillis(10);
    private static boolean zzcuz = false;
    private static HttpClient zzcva = null;
    /* access modifiers changed from: private */
    public static zzy zzcvb = null;
    private static zzt<Object> zzcvc = null;
    private final Context mContext;
    private final Object zzcoe = new Object();
    /* access modifiers changed from: private */
    public final zzabj zzcre;
    private final zzacg zzcrf;
    private zziz zzcrh;
    /* access modifiers changed from: private */
    public zzaa zzcvd;

    public zzada(Context context, zzacg zzacg, zzabj zzabj, zziz zziz) {
        super(true);
        this.zzcre = zzabj;
        this.mContext = context;
        this.zzcrf = zzacg;
        this.zzcrh = zziz;
        synchronized (sLock) {
            if (!zzcuz) {
                zzcvb = new zzy();
                zzcva = new HttpClient(context.getApplicationContext(), zzacg.zzatz);
                zzcvc = new zzadi();
                zzcql = new zzn(context.getApplicationContext(), zzacg.zzatz, (String) zzlc.zzio().zzd(zzoi.zzblc), new zzadh(), new zzadg());
                zzcuz = true;
            }
        }
    }

    private final JSONObject zza(zzacf zzacf, String str) {
        zzaea zzaea;
        AdvertisingIdClient.Info info;
        Bundle bundle = zzacf.zzcrv.extras.getBundle("sdk_less_server_data");
        if (bundle == null) {
            return null;
        }
        try {
            zzaea = zzbt.zzew().zzo(this.mContext).get();
        } catch (Exception e) {
            zzahw.zzc("Error grabbing device info: ", e);
            zzaea = null;
        }
        Context context = this.mContext;
        zzadl zzadl = new zzadl();
        zzadl.zzcvm = zzacf;
        zzadl.zzcvn = zzaea;
        JSONObject zza = zzads.zza(context, zzadl);
        if (zza == null) {
            return null;
        }
        try {
            info = AdvertisingIdClient.getAdvertisingIdInfo(this.mContext);
        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException | IllegalStateException e2) {
            zzahw.zzc("Cannot get advertising id info", e2);
            info = null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("request_id", str);
        hashMap.put("request_param", zza);
        hashMap.put(DataPacketExtension.ELEMENT_NAME, bundle);
        if (info != null) {
            hashMap.put("adid", info.getId());
            hashMap.put("lat", Integer.valueOf(info.isLimitAdTrackingEnabled() ? 1 : 0));
        }
        try {
            return zzbt.zzel().zzq(hashMap);
        } catch (JSONException unused) {
            return null;
        }
    }

    protected static void zzb(zzc zzc) {
        zzc.zza("/loadAd", zzcvb);
        zzc.zza("/fetchHttpRequest", zzcva);
        zzc.zza("/invalidRequest", zzcvc);
    }

    private final zzacj zzc(zzacf zzacf) {
        zzbt.zzel();
        String zzrc = zzaij.zzrc();
        JSONObject zza = zza(zzacf, zzrc);
        if (zza == null) {
            return new zzacj(0);
        }
        long elapsedRealtime = zzbt.zzes().elapsedRealtime();
        Future<JSONObject> zzas = zzcvb.zzas(zzrc);
        zzako.zzaju.post(new zzadc(this, zza, zzrc));
        try {
            JSONObject jSONObject = zzas.get(zzcuy - (zzbt.zzes().elapsedRealtime() - elapsedRealtime), TimeUnit.MILLISECONDS);
            if (jSONObject == null) {
                return new zzacj(-1);
            }
            zzacj zza2 = zzads.zza(this.mContext, zzacf, jSONObject.toString());
            return (zza2.errorCode == -3 || !TextUtils.isEmpty(zza2.body)) ? zza2 : new zzacj(3);
        } catch (InterruptedException | CancellationException unused) {
            return new zzacj(-1);
        } catch (TimeoutException unused2) {
            return new zzacj(2);
        } catch (ExecutionException unused3) {
            return new zzacj(0);
        }
    }

    protected static void zzc(zzc zzc) {
        zzc.zzb("/loadAd", zzcvb);
        zzc.zzb("/fetchHttpRequest", zzcva);
        zzc.zzb("/invalidRequest", zzcvc);
    }

    public final void onStop() {
        synchronized (this.zzcoe) {
            zzako.zzaju.post(new zzadf(this));
        }
    }

    public final void zzdo() {
        zzahw.zzby("SdkLessAdLoaderBackgroundTask started.");
        String zzz = zzbt.zzfh().zzz(this.mContext);
        zzacf zzacf = new zzacf(this.zzcrf, -1, zzbt.zzfh().zzx(this.mContext), zzbt.zzfh().zzy(this.mContext), zzz);
        zzbt.zzfh().zzg(this.mContext, zzz);
        zzacj zzc = zzc(zzacf);
        long elapsedRealtime = zzbt.zzes().elapsedRealtime();
        zzacf zzacf2 = zzacf;
        zzako.zzaju.post(new zzadb(this, new zzahe(zzacf2, zzc, (zzvq) null, (zzko) null, zzc.errorCode, elapsedRealtime, zzc.zzcts, (JSONObject) null, this.zzcrh)));
    }
}
