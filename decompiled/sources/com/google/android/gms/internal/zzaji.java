package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbt;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

@zzabh
public final class zzaji {
    private final Object mLock = new Object();
    private String zzdgh = "";
    private String zzdgi = "";
    private boolean zzdgj = false;
    private String zzdgk = "";

    private final void zza(Context context, String str, boolean z, boolean z2) {
        if (!(context instanceof Activity)) {
            zzahw.zzcy("Can not create dialog without Activity Context");
        } else {
            zzaij.zzdfn.post(new zzajj(this, context, str, z, z2));
        }
    }

    private final String zzaw(Context context) {
        String str;
        synchronized (this.mLock) {
            if (TextUtils.isEmpty(this.zzdgh)) {
                zzbt.zzel();
                String zzm = zzaij.zzm(context, "debug_signals_id.txt");
                this.zzdgh = zzm;
                if (TextUtils.isEmpty(zzm)) {
                    zzbt.zzel();
                    this.zzdgh = zzaij.zzrc();
                    zzbt.zzel();
                    zzaij.zzf(context, "debug_signals_id.txt", this.zzdgh);
                }
            }
            str = this.zzdgh;
        }
        return str;
    }

    private final Uri zzc(Context context, String str, String str2, String str3) {
        Uri.Builder buildUpon = Uri.parse(str).buildUpon();
        buildUpon.appendQueryParameter("linkedDeviceId", zzaw(context));
        buildUpon.appendQueryParameter("adSlotPath", str2);
        buildUpon.appendQueryParameter("afmaVersion", str3);
        return buildUpon.build();
    }

    private final boolean zzh(Context context, String str, String str2) {
        String zzj = zzj(context, zzc(context, (String) zzlc.zzio().zzd(zzoi.zzbuu), str, str2).toString(), str2);
        if (TextUtils.isEmpty(zzj)) {
            zzahw.zzby("Not linked for in app preview.");
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(zzj.trim());
            String optString = jSONObject.optString("gct");
            this.zzdgk = jSONObject.optString("status");
            synchronized (this.mLock) {
                this.zzdgi = optString;
            }
            return true;
        } catch (JSONException e) {
            zzahw.zzc("Fail to get in app preview response json.", e);
            return false;
        }
    }

    private final boolean zzi(Context context, String str, String str2) {
        String zzj = zzj(context, zzc(context, (String) zzlc.zzio().zzd(zzoi.zzbuv), str, str2).toString(), str2);
        if (TextUtils.isEmpty(zzj)) {
            zzahw.zzby("Not linked for debug signals.");
            return false;
        }
        try {
            boolean equals = "1".equals(new JSONObject(zzj.trim()).optString("debug_mode"));
            synchronized (this.mLock) {
                this.zzdgj = equals;
            }
            return equals;
        } catch (JSONException e) {
            zzahw.zzc("Fail to get debug mode response json.", e);
            return false;
        }
    }

    private static String zzj(Context context, String str, String str2) {
        String str3;
        Throwable e;
        String str4;
        String str5;
        HashMap hashMap = new HashMap();
        hashMap.put("User-Agent", zzbt.zzel().zzl(context, str2));
        zzalt<String> zzb = new zzajr(context).zzb(str, hashMap);
        try {
            return (String) zzb.get((long) ((Integer) zzlc.zzio().zzd(zzoi.zzbux)).intValue(), TimeUnit.MILLISECONDS);
        } catch (TimeoutException e2) {
            e = e2;
            str5 = "Timeout while retriving a response from: ";
            str4 = String.valueOf(str);
            if (str4.length() == 0) {
                str3 = new String(str5);
                zzahw.zzb(str3, e);
                zzb.cancel(true);
                return null;
            }
            str3 = str5.concat(str4);
            zzahw.zzb(str3, e);
            zzb.cancel(true);
            return null;
        } catch (InterruptedException e3) {
            e = e3;
            str5 = "Interrupted while retriving a response from: ";
            str4 = String.valueOf(str);
            if (str4.length() == 0) {
                str3 = new String(str5);
                zzahw.zzb(str3, e);
                zzb.cancel(true);
                return null;
            }
            str3 = str5.concat(str4);
            zzahw.zzb(str3, e);
            zzb.cancel(true);
            return null;
        } catch (Exception e4) {
            String valueOf = String.valueOf(str);
            zzahw.zzb(valueOf.length() != 0 ? "Error retriving a response from: ".concat(valueOf) : new String("Error retriving a response from: "), e4);
            return null;
        }
    }

    private final void zzk(Context context, String str, String str2) {
        zzbt.zzel();
        zzaij.zza(context, zzc(context, (String) zzlc.zzio().zzd(zzoi.zzbut), str, str2));
    }

    public final void zza(Context context, String str, String str2, String str3) {
        boolean zzrr = zzrr();
        if (zzi(context, str, str2)) {
            if (!zzrr && !TextUtils.isEmpty(str3)) {
                zzb(context, str2, str3, str);
            }
            zzahw.zzby("Device is linked for debug signals.");
            zza(context, "The device is successfully linked for troubleshooting.", false, true);
            return;
        }
        zzk(context, str, str2);
    }

    public final void zzb(Context context, String str, String str2, String str3) {
        Uri.Builder buildUpon = zzc(context, (String) zzlc.zzio().zzd(zzoi.zzbuw), str3, str).buildUpon();
        buildUpon.appendQueryParameter("debugData", str2);
        zzbt.zzel();
        zzaij.zze(context, str, buildUpon.build().toString());
    }

    public final void zzg(Context context, String str, String str2) {
        if (!zzh(context, str, str2)) {
            zza(context, "In-app preview failed to load because of a system error. Please try again later.", true, true);
        } else if ("2".equals(this.zzdgk)) {
            zzahw.zzby("Creative is not pushed for this device.");
            zza(context, "There was no creative pushed from DFP to the device.", false, false);
        } else if ("1".equals(this.zzdgk)) {
            zzahw.zzby("The app is not linked for creative preview.");
            zzk(context, str, str2);
        } else if ("0".equals(this.zzdgk)) {
            zzahw.zzby("Device is linked for in app preview.");
            zza(context, "The device is successfully linked for creative preview.", false, true);
        }
    }

    public final String zzrq() {
        String str;
        synchronized (this.mLock) {
            str = this.zzdgi;
        }
        return str;
    }

    public final boolean zzrr() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzdgj;
        }
        return z;
    }
}
