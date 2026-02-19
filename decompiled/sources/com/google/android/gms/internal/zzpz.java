package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.internal.zzbm;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.jivesoftware.smackx.GroupChatInvitation;
import org.json.JSONException;
import org.json.JSONObject;

@zzabh
public class zzpz implements zzpv {
    private final Context mContext;
    private final Object mLock = new Object();
    private final zzala zzapq;
    private zzagt zzaqg;
    private final zzaan zzarm;
    private String zzarn;
    private final zzpw zzbyu;
    private final JSONObject zzbyx;
    private final zzpx zzbyy;
    private final zzcv zzbyz;
    boolean zzbza;
    private WeakReference<View> zzbzb = null;

    public zzpz(Context context, zzpw zzpw, zzaan zzaan, zzcv zzcv, JSONObject jSONObject, zzpx zzpx, zzala zzala, String str) {
        this.mContext = context;
        this.zzbyu = zzpw;
        this.zzarm = zzaan;
        this.zzbyz = zzcv;
        this.zzbyx = jSONObject;
        this.zzbyy = zzpx;
        this.zzapq = zzala;
        this.zzarn = str;
    }

    private final JSONObject zza(Map<String, WeakReference<View>> map, View view) {
        JSONObject jSONObject;
        JSONObject jSONObject2 = new JSONObject();
        if (map == null || view == null) {
            return jSONObject2;
        }
        int[] zzm = zzm(view);
        synchronized (map) {
            for (Map.Entry next : map.entrySet()) {
                View view2 = (View) ((WeakReference) next.getValue()).get();
                if (view2 != null) {
                    int[] zzm2 = zzm(view2);
                    JSONObject jSONObject3 = new JSONObject();
                    JSONObject jSONObject4 = new JSONObject();
                    try {
                        jSONObject4.put("width", zzt(view2.getMeasuredWidth()));
                        jSONObject4.put("height", zzt(view2.getMeasuredHeight()));
                        jSONObject4.put(GroupChatInvitation.ELEMENT_NAME, zzt(zzm2[0] - zzm[0]));
                        jSONObject4.put("y", zzt(zzm2[1] - zzm[1]));
                        jSONObject4.put("relative_to", "ad_view");
                        jSONObject3.put("frame", jSONObject4);
                        Rect rect = new Rect();
                        if (view2.getLocalVisibleRect(rect)) {
                            jSONObject = zzb(rect);
                        } else {
                            JSONObject jSONObject5 = new JSONObject();
                            jSONObject5.put("width", 0);
                            jSONObject5.put("height", 0);
                            jSONObject5.put(GroupChatInvitation.ELEMENT_NAME, zzt(zzm2[0] - zzm[0]));
                            jSONObject5.put("y", zzt(zzm2[1] - zzm[1]));
                            jSONObject5.put("relative_to", "ad_view");
                            jSONObject = jSONObject5;
                        }
                        jSONObject3.put("visible_bounds", jSONObject);
                        if (view2 instanceof TextView) {
                            TextView textView = (TextView) view2;
                            jSONObject3.put("text_color", textView.getCurrentTextColor());
                            jSONObject3.put("font_size", (double) textView.getTextSize());
                            jSONObject3.put("text", textView.getText());
                        }
                        jSONObject2.put((String) next.getKey(), jSONObject3);
                    } catch (JSONException unused) {
                        zzahw.zzcz("Unable to get asset views information");
                    }
                }
            }
        }
        return jSONObject2;
    }

    private final void zza(View view, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, JSONObject jSONObject4, String str, JSONObject jSONObject5, JSONObject jSONObject6) {
        zzbq.zzgn("performClick must be called on the main UI thread.");
        try {
            JSONObject jSONObject7 = new JSONObject();
            jSONObject7.put("ad", this.zzbyx);
            if (jSONObject2 != null) {
                jSONObject7.put("asset_view_signal", jSONObject2);
            }
            if (jSONObject != null) {
                jSONObject7.put("ad_view_signal", jSONObject);
            }
            if (jSONObject5 != null) {
                jSONObject7.put("click_signal", jSONObject5);
            }
            if (jSONObject3 != null) {
                jSONObject7.put("scroll_view_signal", jSONObject3);
            }
            if (jSONObject4 != null) {
                jSONObject7.put("lock_screen_signal", jSONObject4);
            }
            JSONObject jSONObject8 = new JSONObject();
            jSONObject8.put("asset_id", str);
            jSONObject8.put("template", this.zzbyy.zzke());
            zzbt.zzen();
            jSONObject8.put("is_privileged_process", zzaip.zzrk());
            boolean z = true;
            jSONObject8.put("has_custom_click_handler", this.zzbyu.zzs(this.zzbyy.getCustomTemplateId()) != null);
            if (this.zzbyu.zzs(this.zzbyy.getCustomTemplateId()) == null) {
                z = false;
            }
            jSONObject7.put("has_custom_click_handler", z);
            try {
                JSONObject optJSONObject = this.zzbyx.optJSONObject("tracking_urls_and_actions");
                if (optJSONObject == null) {
                    optJSONObject = new JSONObject();
                }
                jSONObject8.put("click_signals", this.zzbyz.zzae().zza(this.mContext, optJSONObject.optString("click_string"), view));
            } catch (Exception e) {
                zzahw.zzb("Exception obtaining click signals", e);
            }
            jSONObject7.put("click", jSONObject8);
            if (jSONObject6 != null) {
                jSONObject7.put("provided_signals", jSONObject6);
            }
            jSONObject7.put("ads_id", this.zzarn);
            zzalg.zza(this.zzarm.zzi(jSONObject7), "NativeAdEngineImpl.performClick");
        } catch (JSONException e2) {
            zzahw.zzb("Unable to create click JSON.", e2);
        }
    }

    private final boolean zza(JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, JSONObject jSONObject4, JSONObject jSONObject5) {
        zzbq.zzgn("recordImpression must be called on the main UI thread.");
        if (this.zzbza) {
            return true;
        }
        this.zzbza = true;
        try {
            JSONObject jSONObject6 = new JSONObject();
            jSONObject6.put("ad", this.zzbyx);
            jSONObject6.put("ads_id", this.zzarn);
            if (jSONObject2 != null) {
                jSONObject6.put("asset_view_signal", jSONObject2);
            }
            if (jSONObject != null) {
                jSONObject6.put("ad_view_signal", jSONObject);
            }
            if (jSONObject3 != null) {
                jSONObject6.put("scroll_view_signal", jSONObject3);
            }
            if (jSONObject4 != null) {
                jSONObject6.put("lock_screen_signal", jSONObject4);
            }
            if (jSONObject5 != null) {
                jSONObject6.put("provided_signals", jSONObject5);
            }
            zzalg.zza(this.zzarm.zzj(jSONObject6), "NativeAdEngineImpl.recordImpression");
            this.zzbyu.zza((zzpv) this);
            this.zzbyu.zzcb();
            return true;
        } catch (JSONException e) {
            zzahw.zzb("Unable to create impression JSON.", e);
            return false;
        }
    }

    private final boolean zzar(String str) {
        JSONObject jSONObject = this.zzbyx;
        JSONObject optJSONObject = jSONObject == null ? null : jSONObject.optJSONObject("allow_pub_event_reporting");
        if (optJSONObject == null) {
            return false;
        }
        return optJSONObject.optBoolean(str, false);
    }

    private final JSONObject zzb(Rect rect) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("width", zzt(rect.right - rect.left));
        jSONObject.put("height", zzt(rect.bottom - rect.top));
        jSONObject.put(GroupChatInvitation.ELEMENT_NAME, zzt(rect.left));
        jSONObject.put("y", zzt(rect.top));
        jSONObject.put("relative_to", "self");
        return jSONObject;
    }

    private static boolean zzl(View view) {
        return view.isShown() && view.getGlobalVisibleRect(new Rect(), (Point) null);
    }

    private static int[] zzm(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return iArr;
    }

    private final JSONObject zzn(View view) {
        JSONObject jSONObject;
        JSONObject jSONObject2 = new JSONObject();
        if (view == null) {
            return jSONObject2;
        }
        try {
            int[] zzm = zzm(view);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("width", zzt(view.getMeasuredWidth()));
            jSONObject3.put("height", zzt(view.getMeasuredHeight()));
            jSONObject3.put(GroupChatInvitation.ELEMENT_NAME, zzt(zzm[0]));
            jSONObject3.put("y", zzt(zzm[1]));
            jSONObject3.put("relative_to", "window");
            jSONObject2.put("frame", jSONObject3);
            Rect rect = new Rect();
            if (view.getGlobalVisibleRect(rect)) {
                jSONObject = zzb(rect);
            } else {
                jSONObject = new JSONObject();
                jSONObject.put("width", 0);
                jSONObject.put("height", 0);
                jSONObject.put(GroupChatInvitation.ELEMENT_NAME, zzt(zzm[0]));
                jSONObject.put("y", zzt(zzm[1]));
                jSONObject.put("relative_to", "window");
            }
            jSONObject2.put("visible_bounds", jSONObject);
        } catch (Exception unused) {
            zzahw.zzcz("Unable to get native ad view bounding box");
        }
        return jSONObject2;
    }

    private static JSONObject zzo(View view) {
        JSONObject jSONObject = new JSONObject();
        if (view == null) {
            return jSONObject;
        }
        try {
            zzbt.zzel();
            jSONObject.put("contained_in_scroll_view", zzaij.zzw(view) != -1);
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    private final JSONObject zzp(View view) {
        JSONObject jSONObject = new JSONObject();
        if (view == null) {
            return jSONObject;
        }
        try {
            zzbt.zzel();
            jSONObject.put("can_show_on_lock_screen", zzaij.zzv(view));
            zzbt.zzel();
            jSONObject.put("is_keyguard_locked", zzaij.zzar(this.mContext));
        } catch (JSONException unused) {
            zzahw.zzcz("Unable to get lock screen information");
        }
        return jSONObject;
    }

    private final int zzt(int i) {
        zzlc.zzij();
        return zzako.zzb(this.mContext, i);
    }

    public final Context getContext() {
        return this.mContext;
    }

    public final void performClick(Bundle bundle) {
        if (bundle == null) {
            zzahw.zzby("Click data is null. No click is reported.");
        } else if (!zzar("click_reporting")) {
            zzahw.e("The ad slot cannot handle external click events. You must be whitelisted to be able to report your click events.");
        } else {
            zza((View) null, (JSONObject) null, (JSONObject) null, (JSONObject) null, (JSONObject) null, bundle.getBundle("click_signal").getString("asset_id"), (JSONObject) null, zzbt.zzel().zza(bundle, (JSONObject) null));
        }
    }

    public final boolean recordImpression(Bundle bundle) {
        if (!zzar("impression_reporting")) {
            zzahw.e("The ad slot cannot handle external impression events. You must be whitelisted to whitelisted to be able to report your impression events.");
            return false;
        }
        return zza((JSONObject) null, (JSONObject) null, (JSONObject) null, (JSONObject) null, zzbt.zzel().zza(bundle, (JSONObject) null));
    }

    public final void reportTouchEvent(Bundle bundle) {
        if (bundle == null) {
            zzahw.zzby("Touch event data is null. No touch event is reported.");
        } else if (!zzar("touch_reporting")) {
            zzahw.e("The ad slot cannot handle external touch events. You must be whitelisted to be able to report your touch events.");
        } else {
            int i = bundle.getInt("duration_ms");
            this.zzbyz.zzae().zza((int) bundle.getFloat(GroupChatInvitation.ELEMENT_NAME), (int) bundle.getFloat("y"), i);
        }
    }

    public View zza(View.OnClickListener onClickListener, boolean z) {
        zzph zzkf = this.zzbyy.zzkf();
        if (zzkf == null) {
            return null;
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        if (!z) {
            int zzjz = zzkf.zzjz();
            if (zzjz != 0) {
                if (zzjz == 2) {
                    layoutParams.addRule(12);
                } else if (zzjz != 3) {
                    layoutParams.addRule(10);
                } else {
                    layoutParams.addRule(12);
                }
                layoutParams.addRule(11);
            } else {
                layoutParams.addRule(10);
            }
            layoutParams.addRule(9);
        }
        zzpi zzpi = new zzpi(this.mContext, zzkf, layoutParams);
        zzpi.setOnClickListener(onClickListener);
        zzpi.setContentDescription((CharSequence) zzlc.zzio().zzd(zzoi.zzbsr));
        return zzpi;
    }

    public final void zza(View view, zzpt zzpt) {
        if (!zzb(view, zzpt)) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            ((FrameLayout) view).removeAllViews();
            zzpx zzpx = this.zzbyy;
            if (zzpx instanceof zzpy) {
                zzpy zzpy = (zzpy) zzpx;
                if (zzpy.getImages() != null && zzpy.getImages().size() > 0) {
                    Object obj = zzpy.getImages().get(0);
                    zzqs zzk = obj instanceof IBinder ? zzqt.zzk((IBinder) obj) : null;
                    if (zzk != null) {
                        try {
                            IObjectWrapper zzkb = zzk.zzkb();
                            if (zzkb != null) {
                                ImageView imageView = new ImageView(this.mContext);
                                imageView.setImageDrawable((Drawable) zzn.zzy(zzkb));
                                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                ((FrameLayout) view).addView(imageView, layoutParams);
                            }
                        } catch (RemoteException unused) {
                            zzahw.zzcz("Could not get drawable from image");
                        }
                    }
                }
            }
        }
    }

    public final void zza(View view, String str, Bundle bundle, Map<String, WeakReference<View>> map, View view2) {
        JSONObject jSONObject;
        JSONObject zza = zza(map, view2);
        JSONObject zzn = zzn(view2);
        JSONObject zzo = zzo(view2);
        JSONObject zzp = zzp(view2);
        JSONObject jSONObject2 = null;
        try {
            JSONObject zza2 = zzbt.zzel().zza(bundle, (JSONObject) null);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("click_point", zza2);
            jSONObject3.put("asset_id", str);
            jSONObject2 = jSONObject3;
        } catch (Exception e) {
            zzahw.zzb("Error occurred while grabbing click signals.", e);
        }
        jSONObject = jSONObject2;
        zza(view, zzn, zza, zzo, zzp, str, jSONObject, (JSONObject) null);
    }

    public void zza(View view, Map<String, WeakReference<View>> map) {
        zza(zzn(view), zza(map, view), zzo(view), zzp(view), (JSONObject) null);
    }

    public void zza(View view, Map<String, WeakReference<View>> map, Bundle bundle, View view2) {
        String str;
        zzbq.zzgn("performClick must be called on the main UI thread.");
        if (map != null) {
            synchronized (map) {
                for (Map.Entry next : map.entrySet()) {
                    if (view.equals((View) ((WeakReference) next.getValue()).get())) {
                        zza(view, (String) next.getKey(), bundle, map, view2);
                        return;
                    }
                }
            }
        }
        if ("6".equals(this.zzbyy.zzke())) {
            str = "3099";
        } else if ("2".equals(this.zzbyy.zzke())) {
            str = "2099";
        } else if ("1".equals(this.zzbyy.zzke())) {
            zza(view, "1099", bundle, map, view2);
            return;
        } else {
            return;
        }
        zza(view, str, bundle, map, view2);
    }

    public void zza(View view, Map<String, WeakReference<View>> map, Map<String, WeakReference<View>> map2, View.OnTouchListener onTouchListener, View.OnClickListener onClickListener) {
        if (((Boolean) zzlc.zzio().zzd(zzoi.zzbsp)).booleanValue()) {
            view.setOnTouchListener(onTouchListener);
            view.setClickable(true);
            view.setOnClickListener(onClickListener);
            if (map != null) {
                synchronized (map) {
                    for (Map.Entry<String, WeakReference<View>> value : map.entrySet()) {
                        View view2 = (View) ((WeakReference) value.getValue()).get();
                        if (view2 != null) {
                            view2.setOnTouchListener(onTouchListener);
                            view2.setClickable(true);
                            view2.setOnClickListener(onClickListener);
                        }
                    }
                }
            }
            if (map2 != null) {
                synchronized (map2) {
                    for (Map.Entry<String, WeakReference<View>> value2 : map2.entrySet()) {
                        View view3 = (View) ((WeakReference) value2.getValue()).get();
                        if (view3 != null) {
                            view3.setOnTouchListener(onTouchListener);
                        }
                    }
                }
            }
        }
    }

    public void zzb(View view, Map<String, WeakReference<View>> map) {
        if (!((Boolean) zzlc.zzio().zzd(zzoi.zzbso)).booleanValue()) {
            view.setOnTouchListener((View.OnTouchListener) null);
            view.setClickable(false);
            view.setOnClickListener((View.OnClickListener) null);
            if (map != null) {
                synchronized (map) {
                    for (Map.Entry<String, WeakReference<View>> value : map.entrySet()) {
                        View view2 = (View) ((WeakReference) value.getValue()).get();
                        if (view2 != null) {
                            view2.setOnTouchListener((View.OnTouchListener) null);
                            view2.setClickable(false);
                            view2.setOnClickListener((View.OnClickListener) null);
                        }
                    }
                }
            }
        }
    }

    public final boolean zzb(View view, zzpt zzpt) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2, 17);
        View zzkg = this.zzbyy.zzkg();
        if (zzkg == null) {
            return false;
        }
        ViewParent parent = zzkg.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(zzkg);
        }
        FrameLayout frameLayout = (FrameLayout) view;
        frameLayout.removeAllViews();
        frameLayout.addView(zzkg, layoutParams);
        this.zzbyu.zza(zzpt);
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x005d, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzc(android.view.View r4, java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r5) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            boolean r1 = r3.zzbza     // Catch:{ all -> 0x005e }
            if (r1 == 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x005e }
            return
        L_0x0009:
            boolean r1 = zzl(r4)     // Catch:{ all -> 0x005e }
            if (r1 == 0) goto L_0x0014
            r3.zza((android.view.View) r4, (java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>>) r5)     // Catch:{ all -> 0x005e }
            monitor-exit(r0)     // Catch:{ all -> 0x005e }
            return
        L_0x0014:
            com.google.android.gms.internal.zzny<java.lang.Boolean> r1 = com.google.android.gms.internal.zzoi.zzbsw     // Catch:{ all -> 0x005e }
            com.google.android.gms.internal.zzog r2 = com.google.android.gms.internal.zzlc.zzio()     // Catch:{ all -> 0x005e }
            java.lang.Object r1 = r2.zzd(r1)     // Catch:{ all -> 0x005e }
            java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch:{ all -> 0x005e }
            boolean r1 = r1.booleanValue()     // Catch:{ all -> 0x005e }
            if (r1 == 0) goto L_0x005c
            if (r5 == 0) goto L_0x005c
            monitor-enter(r5)     // Catch:{ all -> 0x005e }
            java.util.Set r1 = r5.entrySet()     // Catch:{ all -> 0x0059 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0059 }
        L_0x0031:
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x0059 }
            if (r2 == 0) goto L_0x0057
            java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x0059 }
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch:{ all -> 0x0059 }
            java.lang.Object r2 = r2.getValue()     // Catch:{ all -> 0x0059 }
            java.lang.ref.WeakReference r2 = (java.lang.ref.WeakReference) r2     // Catch:{ all -> 0x0059 }
            java.lang.Object r2 = r2.get()     // Catch:{ all -> 0x0059 }
            android.view.View r2 = (android.view.View) r2     // Catch:{ all -> 0x0059 }
            if (r2 == 0) goto L_0x0031
            boolean r2 = zzl(r2)     // Catch:{ all -> 0x0059 }
            if (r2 == 0) goto L_0x0031
            r3.zza((android.view.View) r4, (java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>>) r5)     // Catch:{ all -> 0x0059 }
            monitor-exit(r5)     // Catch:{ all -> 0x0059 }
            monitor-exit(r0)     // Catch:{ all -> 0x005e }
            return
        L_0x0057:
            monitor-exit(r5)     // Catch:{ all -> 0x0059 }
            goto L_0x005c
        L_0x0059:
            r4 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x0059 }
            throw r4     // Catch:{ all -> 0x005e }
        L_0x005c:
            monitor-exit(r0)     // Catch:{ all -> 0x005e }
            return
        L_0x005e:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x005e }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzpz.zzc(android.view.View, java.util.Map):void");
    }

    public final void zzd(MotionEvent motionEvent) {
        this.zzbyz.zza(motionEvent);
    }

    public final void zzh(Map<String, WeakReference<View>> map) {
        if (this.zzbyy.zzkg() == null) {
            return;
        }
        if ("2".equals(this.zzbyy.zzke())) {
            zzbt.zzep().zzqe().zza(this.zzbyu.getAdUnitId(), this.zzbyy.zzke(), map.containsKey(NativeAppInstallAd.ASSET_MEDIA_VIDEO));
        } else if ("1".equals(this.zzbyy.zzke())) {
            zzbt.zzep().zzqe().zza(this.zzbyu.getAdUnitId(), this.zzbyy.zzke(), map.containsKey(NativeContentAd.ASSET_MEDIA_VIDEO));
        }
    }

    public final void zzi(View view) {
        zzcv zzcv;
        zzcr zzae;
        if (((Boolean) zzlc.zzio().zzd(zzoi.zzbrm)).booleanValue() && (zzcv = this.zzbyz) != null && (zzae = zzcv.zzae()) != null) {
            zzae.zzb(view);
        }
    }

    public final void zzk(View view) {
        this.zzbzb = new WeakReference<>(view);
    }

    public boolean zzkm() {
        zzph zzkf = this.zzbyy.zzkf();
        return zzkf != null && zzkf.zzka();
    }

    public boolean zzkn() {
        JSONObject jSONObject = this.zzbyx;
        return jSONObject != null && jSONObject.optBoolean("allow_pub_owned_ad_view", false);
    }

    public zzaof zzkq() throws zzaop {
        JSONObject jSONObject = this.zzbyx;
        if (jSONObject == null || jSONObject.optJSONObject("overlay") == null) {
            return null;
        }
        zzaol zzem = zzbt.zzem();
        Context context = this.mContext;
        zzko zzf = zzko.zzf(context);
        zzaof zza = zzem.zza(context, zzaqa.zzc(zzf), zzf.zzbia, false, false, this.zzbyz, this.zzapq, (zzov) null, (zzbm) null, (zzv) null, zziu.zzhp());
        if (zza != null) {
            zza.getView().setVisibility(8);
            new zzqb(zza).zza(this.zzarm);
        }
        return zza;
    }

    public void zzkr() {
        this.zzarm.zzmd();
    }

    public void zzks() {
        this.zzbyu.zzcu();
    }

    public final View zzkt() {
        WeakReference<View> weakReference = this.zzbzb;
        if (weakReference != null) {
            return (View) weakReference.get();
        }
        return null;
    }

    public final zzagt zzku() {
        if (!zzbt.zzfh().zzs(this.mContext)) {
            return null;
        }
        if (this.zzaqg == null) {
            this.zzaqg = new zzagt(this.mContext, this.zzbyu.getAdUnitId());
        }
        return this.zzaqg;
    }
}
