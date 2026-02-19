package com.google.android.gms.ads.internal;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import com.google.android.gms.ads.internal.gmsg.zzt;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzahd;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzaof;
import com.google.android.gms.internal.zzpk;
import com.google.android.gms.internal.zzpm;
import com.google.android.gms.internal.zzqs;
import com.google.android.gms.internal.zzqt;
import com.google.android.gms.internal.zzwr;
import com.google.android.gms.internal.zzwu;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.ByteArrayOutputStream;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzabh
public final class zzar {
    static zzt<zzaof> zza(zzwr zzwr, zzwu zzwu, zzab zzab) {
        return new zzaw(zzwr, zzab, zzwu);
    }

    private static String zza(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (bitmap == null) {
            zzahw.zzcz("Bitmap is null. Returning empty string");
            return "";
        }
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        String valueOf = String.valueOf(Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0));
        return valueOf.length() != 0 ? "data:image/png;Base64,".concat(valueOf) : new String("data:image/png;Base64,");
    }

    private static String zza(zzqs zzqs) {
        if (zzqs == null) {
            zzahw.zzcz("Image is null. Returning empty string");
            return "";
        }
        try {
            Uri uri = zzqs.getUri();
            if (uri != null) {
                return uri.toString();
            }
        } catch (RemoteException unused) {
            zzahw.zzcz("Unable to get image uri. Trying data uri next");
        }
        return zzb(zzqs);
    }

    private static JSONObject zza(Bundle bundle, String str) throws JSONException {
        String str2;
        JSONObject jSONObject = new JSONObject();
        if (bundle != null && !TextUtils.isEmpty(str)) {
            JSONObject jSONObject2 = new JSONObject(str);
            Iterator<String> keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (bundle.containsKey(next)) {
                    String str3 = null;
                    if ("image".equals(jSONObject2.getString(next))) {
                        Object obj = bundle.get(next);
                        if (obj instanceof Bitmap) {
                            str2 = zza((Bitmap) obj);
                            jSONObject.put(next, str2);
                        } else {
                            str3 = "Invalid type. An image type extra should return a bitmap";
                        }
                    } else if (bundle.get(next) instanceof Bitmap) {
                        str3 = "Invalid asset type. Bitmap should be returned only for image type";
                    } else {
                        str2 = String.valueOf(bundle.get(next));
                        jSONObject.put(next, str2);
                    }
                    if (str3 != null) {
                        zzahw.zzcz(str3);
                    }
                }
            }
        }
        return jSONObject;
    }

    static final /* synthetic */ void zza(zzpk zzpk, String str, zzaof zzaof, zzaof zzaof2, boolean z) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("headline", zzpk.getHeadline());
            jSONObject.put("body", zzpk.getBody());
            jSONObject.put("call_to_action", zzpk.getCallToAction());
            jSONObject.put(FirebaseAnalytics.Param.PRICE, zzpk.getPrice());
            jSONObject.put("star_rating", String.valueOf(zzpk.getStarRating()));
            jSONObject.put("store", zzpk.getStore());
            jSONObject.put("icon", zza(zzpk.zzkc()));
            JSONArray jSONArray = new JSONArray();
            List<Object> images = zzpk.getImages();
            if (images != null) {
                for (Object zzd : images) {
                    jSONArray.put(zza(zzd(zzd)));
                }
            }
            jSONObject.put("images", jSONArray);
            jSONObject.put("extras", zza(zzpk.getExtras(), str));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("assets", jSONObject);
            jSONObject2.put("template_id", "2");
            zzaof.zzb("google.afma.nativeExpressAds.loadAssets", jSONObject2);
        } catch (JSONException e) {
            zzahw.zzc("Exception occurred when loading assets", e);
        }
    }

    static final /* synthetic */ void zza(zzpm zzpm, String str, zzaof zzaof, zzaof zzaof2, boolean z) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("headline", zzpm.getHeadline());
            jSONObject.put("body", zzpm.getBody());
            jSONObject.put("call_to_action", zzpm.getCallToAction());
            jSONObject.put("advertiser", zzpm.getAdvertiser());
            jSONObject.put("logo", zza(zzpm.zzkj()));
            JSONArray jSONArray = new JSONArray();
            List<Object> images = zzpm.getImages();
            if (images != null) {
                for (Object zzd : images) {
                    jSONArray.put(zza(zzd(zzd)));
                }
            }
            jSONObject.put("images", jSONArray);
            jSONObject.put("extras", zza(zzpm.getExtras(), str));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("assets", jSONObject);
            jSONObject2.put("template_id", "1");
            zzaof.zzb("google.afma.nativeExpressAds.loadAssets", jSONObject2);
        } catch (JSONException e) {
            zzahw.zzc("Exception occurred when loading assets", e);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v2, resolved type: android.view.View} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v6, resolved type: android.view.View} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x013c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean zza(com.google.android.gms.internal.zzaof r25, com.google.android.gms.internal.zzvw r26, java.util.concurrent.CountDownLatch r27) {
        /*
            r0 = r25
            r1 = r26
            r7 = r27
            r8 = 0
            android.view.View r2 = r25.getView()     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            if (r2 != 0) goto L_0x0014
            java.lang.String r0 = "AdWebView is null"
        L_0x000f:
            com.google.android.gms.internal.zzahw.zzcz(r0)     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            goto L_0x013a
        L_0x0014:
            r3 = 4
            r2.setVisibility(r3)     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            com.google.android.gms.internal.zzvp r2 = r1.zzcje     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            java.util.List<java.lang.String> r2 = r2.zzchr     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            if (r2 == 0) goto L_0x012b
            boolean r3 = r2.isEmpty()     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            if (r3 == 0) goto L_0x0026
            goto L_0x012b
        L_0x0026:
            com.google.android.gms.internal.zzapu r3 = r25.zzua()     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            java.lang.String r4 = "/nativeExpressAssetsLoaded"
            com.google.android.gms.ads.internal.zzau r5 = new com.google.android.gms.ads.internal.zzau     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            r5.<init>(r7)     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            r3.zza((java.lang.String) r4, (com.google.android.gms.ads.internal.gmsg.zzt<? super com.google.android.gms.internal.zzaof>) r5)     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            com.google.android.gms.internal.zzapu r3 = r25.zzua()     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            java.lang.String r4 = "/nativeExpressAssetsLoadingFailed"
            com.google.android.gms.ads.internal.zzav r5 = new com.google.android.gms.ads.internal.zzav     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            r5.<init>(r7)     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            r3.zza((java.lang.String) r4, (com.google.android.gms.ads.internal.gmsg.zzt<? super com.google.android.gms.internal.zzaof>) r5)     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            com.google.android.gms.internal.zzwi r3 = r1.zzcjf     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            com.google.android.gms.internal.zzwr r3 = r3.zzmp()     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            com.google.android.gms.internal.zzwi r4 = r1.zzcjf     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            com.google.android.gms.internal.zzwu r4 = r4.zzmq()     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            java.lang.String r5 = "2"
            boolean r5 = r2.contains(r5)     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            r6 = 0
            if (r5 == 0) goto L_0x00b1
            if (r3 == 0) goto L_0x00b1
            com.google.android.gms.internal.zzpk r2 = new com.google.android.gms.internal.zzpk     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            java.lang.String r10 = r3.getHeadline()     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            java.util.List r11 = r3.getImages()     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            java.lang.String r12 = r3.getBody()     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            com.google.android.gms.internal.zzqs r13 = r3.zzkc()     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            java.lang.String r14 = r3.getCallToAction()     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            double r15 = r3.getStarRating()     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            java.lang.String r17 = r3.getStore()     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            java.lang.String r18 = r3.getPrice()     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            r19 = 0
            android.os.Bundle r20 = r3.getExtras()     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            r21 = 0
            com.google.android.gms.dynamic.IObjectWrapper r4 = r3.zzmx()     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            if (r4 == 0) goto L_0x0094
            com.google.android.gms.dynamic.IObjectWrapper r4 = r3.zzmx()     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            java.lang.Object r4 = com.google.android.gms.dynamic.zzn.zzy(r4)     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            r6 = r4
            android.view.View r6 = (android.view.View) r6     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
        L_0x0094:
            r22 = r6
            com.google.android.gms.dynamic.IObjectWrapper r23 = r3.zzkh()     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            r24 = 0
            r9 = r2
            r9.<init>(r10, r11, r12, r13, r14, r15, r17, r18, r19, r20, r21, r22, r23, r24)     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            com.google.android.gms.internal.zzvp r3 = r1.zzcje     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            java.lang.String r3 = r3.zzchq     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            com.google.android.gms.internal.zzapu r4 = r25.zzua()     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            com.google.android.gms.ads.internal.zzas r5 = new com.google.android.gms.ads.internal.zzas     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            r5.<init>(r2, r3, r0)     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
        L_0x00ad:
            r4.zza((com.google.android.gms.internal.zzapv) r5)     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            goto L_0x0108
        L_0x00b1:
            java.lang.String r3 = "1"
            boolean r2 = r2.contains(r3)     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            if (r2 == 0) goto L_0x0127
            if (r4 == 0) goto L_0x0127
            com.google.android.gms.internal.zzpm r2 = new com.google.android.gms.internal.zzpm     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            java.lang.String r10 = r4.getHeadline()     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            java.util.List r11 = r4.getImages()     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            java.lang.String r12 = r4.getBody()     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            com.google.android.gms.internal.zzqs r13 = r4.zzkj()     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            java.lang.String r14 = r4.getCallToAction()     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            java.lang.String r15 = r4.getAdvertiser()     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            r16 = 0
            android.os.Bundle r17 = r4.getExtras()     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            r18 = 0
            com.google.android.gms.dynamic.IObjectWrapper r3 = r4.zzmx()     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            if (r3 == 0) goto L_0x00ee
            com.google.android.gms.dynamic.IObjectWrapper r3 = r4.zzmx()     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            java.lang.Object r3 = com.google.android.gms.dynamic.zzn.zzy(r3)     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            r6 = r3
            android.view.View r6 = (android.view.View) r6     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
        L_0x00ee:
            r19 = r6
            com.google.android.gms.dynamic.IObjectWrapper r20 = r4.zzkh()     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            r21 = 0
            r9 = r2
            r9.<init>(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21)     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            com.google.android.gms.internal.zzvp r3 = r1.zzcje     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            java.lang.String r3 = r3.zzchq     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            com.google.android.gms.internal.zzapu r4 = r25.zzua()     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            com.google.android.gms.ads.internal.zzat r5 = new com.google.android.gms.ads.internal.zzat     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            r5.<init>(r2, r3, r0)     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            goto L_0x00ad
        L_0x0108:
            com.google.android.gms.internal.zzvp r2 = r1.zzcje     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            java.lang.String r3 = r2.zzcho     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            com.google.android.gms.internal.zzvp r1 = r1.zzcje     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            java.lang.String r2 = r1.zzchp     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            if (r2 == 0) goto L_0x011d
            java.lang.String r4 = "text/html"
            java.lang.String r5 = "UTF-8"
            r6 = 0
            r1 = r25
            r1.loadDataWithBaseURL(r2, r3, r4, r5, r6)     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
            goto L_0x0124
        L_0x011d:
            java.lang.String r1 = "text/html"
            java.lang.String r2 = "UTF-8"
            r0.loadData(r3, r1, r2)     // Catch:{ RemoteException -> 0x0134, RuntimeException -> 0x012f }
        L_0x0124:
            r0 = 1
            r8 = 1
            goto L_0x013a
        L_0x0127:
            java.lang.String r0 = "No matching template id and mapper"
            goto L_0x000f
        L_0x012b:
            java.lang.String r0 = "No template ids present in mediation response"
            goto L_0x000f
        L_0x012f:
            r0 = move-exception
            r27.countDown()
            throw r0
        L_0x0134:
            r0 = move-exception
            java.lang.String r1 = "Unable to invoke load assets"
            com.google.android.gms.internal.zzahw.zzc(r1, r0)
        L_0x013a:
            if (r8 != 0) goto L_0x013f
            r27.countDown()
        L_0x013f:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzar.zza(com.google.android.gms.internal.zzaof, com.google.android.gms.internal.zzvw, java.util.concurrent.CountDownLatch):boolean");
    }

    private static String zzb(zzqs zzqs) {
        String str;
        try {
            IObjectWrapper zzkb = zzqs.zzkb();
            if (zzkb == null) {
                zzahw.zzcz("Drawable is null. Returning empty string");
                return "";
            }
            Drawable drawable = (Drawable) zzn.zzy(zzkb);
            if (drawable instanceof BitmapDrawable) {
                return zza(((BitmapDrawable) drawable).getBitmap());
            }
            str = "Drawable is not an instance of BitmapDrawable. Returning empty string";
            zzahw.zzcz(str);
            return "";
        } catch (RemoteException unused) {
            str = "Unable to get drawable. Returning empty string";
        }
        zzahw.zzcz(str);
        return "";
    }

    /* access modifiers changed from: private */
    public static void zzc(zzaof zzaof) {
        View.OnClickListener onClickListener = zzaof.getOnClickListener();
        if (onClickListener != null) {
            onClickListener.onClick(zzaof.getView());
        }
    }

    private static zzqs zzd(Object obj) {
        if (obj instanceof IBinder) {
            return zzqt.zzk((IBinder) obj);
        }
        return null;
    }

    public static View zze(zzahd zzahd) {
        if (zzahd == null) {
            zzahw.e("AdState is null");
            return null;
        } else if (zzf(zzahd) && zzahd.zzcnm != null) {
            return zzahd.zzcnm.getView();
        } else {
            try {
                IObjectWrapper view = zzahd.zzcjf != null ? zzahd.zzcjf.getView() : null;
                if (view != null) {
                    return (View) zzn.zzy(view);
                }
                zzahw.zzcz("View in mediation adapter is null.");
                return null;
            } catch (RemoteException e) {
                zzahw.zzc("Could not get View from mediation adapter.", e);
                return null;
            }
        }
    }

    public static boolean zzf(zzahd zzahd) {
        return (zzahd == null || !zzahd.zzcto || zzahd.zzcje == null || zzahd.zzcje.zzcho == null) ? false : true;
    }
}
