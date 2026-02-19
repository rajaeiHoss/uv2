package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.common.internal.zzc;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@zzabh
public final class zzanb extends FrameLayout implements zzamy {
    private final zzann zzdlg;
    private final FrameLayout zzdlh;
    private final zzov zzdli;
    private final zzanp zzdlj;
    private final long zzdlk;
    private zzamz zzdll;
    private boolean zzdlm;
    private boolean zzdln;
    private boolean zzdlo;
    private boolean zzdlp;
    private long zzdlq;
    private long zzdlr;
    private String zzdls;
    private Bitmap zzdlt;
    private ImageView zzdlu;
    private boolean zzdlv;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzanb(Context context, zzann zzann, int i, boolean z, zzov zzov, zzanm zzanm) {
        super(context);
        Context context2 = context;
        zzov zzov2 = zzov;
        zzann zzann2 = zzann;
        this.zzdlg = zzann2;
        this.zzdli = zzov2;
        FrameLayout frameLayout = new FrameLayout(context);
        this.zzdlh = frameLayout;
        addView(frameLayout, new FrameLayout.LayoutParams(-1, -1));
        zzc.zzv(zzann.zzbo());
        zzamz zza = zzann.zzbo().zzaol.zza(context, zzann2, i, z, zzov, zzanm);
        this.zzdll = zza;
        if (zza != null) {
            frameLayout.addView(zza, new FrameLayout.LayoutParams(-1, -1, 17));
            if (((Boolean) zzlc.zzio().zzd(zzoi.zzbmd)).booleanValue()) {
                zzsx();
            }
        }
        this.zzdlu = new ImageView(context);
        this.zzdlk = ((Long) zzlc.zzio().zzd(zzoi.zzbmh)).longValue();
        boolean booleanValue = ((Boolean) zzlc.zzio().zzd(zzoi.zzbmf)).booleanValue();
        this.zzdlp = booleanValue;
        if (zzov2 != null) {
            zzov2.zzf("spinner_used", booleanValue ? "1" : "0");
        }
        this.zzdlj = new zzanp(this);
        zzamz zzamz = this.zzdll;
        if (zzamz != null) {
            zzamz.zza(this);
        }
        if (this.zzdll == null) {
            zzg("AdVideoUnderlay Error", "Allocating player failed.");
        }
    }

    public static void zza(zzann zzann) {
        HashMap hashMap = new HashMap();
        hashMap.put("event", "no_video_view");
        zzann.zza("onVideoEvent", hashMap);
    }

    public static void zza(zzann zzann, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("event", "decoderProps");
        hashMap.put("error", str);
        zzann.zza("onVideoEvent", hashMap);
    }

    public static void zza(zzann zzann, Map<String, List<Map<String, Object>>> map) {
        HashMap hashMap = new HashMap();
        hashMap.put("event", "decoderProps");
        hashMap.put("mimeTypes", map);
        zzann.zza("onVideoEvent", hashMap);
    }

    /* access modifiers changed from: private */
    public final void zza(String str, String... strArr) {
        HashMap hashMap = new HashMap();
        hashMap.put("event", str);
        String str2 = null;
        for (String str3 : strArr) {
            if (str2 == null) {
                str2 = str3;
            } else {
                hashMap.put(str2, str3);
                str2 = null;
            }
        }
        this.zzdlg.zza("onVideoEvent", hashMap);
    }

    private final boolean zzsz() {
        return this.zzdlu.getParent() != null;
    }

    private final void zzta() {
        if (this.zzdlg.zztj() != null && this.zzdln && !this.zzdlo) {
            this.zzdlg.zztj().getWindow().clearFlags(128);
            this.zzdln = false;
        }
    }

    public final void destroy() {
        this.zzdlj.pause();
        zzamz zzamz = this.zzdll;
        if (zzamz != null) {
            zzamz.stop();
        }
        zzta();
    }

    public final void onPaused() {
        zza("pause", new String[0]);
        zzta();
        this.zzdlm = false;
    }

    public final void pause() {
        zzamz zzamz = this.zzdll;
        if (zzamz != null) {
            zzamz.pause();
        }
    }

    public final void play() {
        zzamz zzamz = this.zzdll;
        if (zzamz != null) {
            zzamz.play();
        }
    }

    public final void seekTo(int i) {
        zzamz zzamz = this.zzdll;
        if (zzamz != null) {
            zzamz.seekTo(i);
        }
    }

    public final void zza(float f, float f2) {
        zzamz zzamz = this.zzdll;
        if (zzamz != null) {
            zzamz.zza(f, f2);
        }
    }

    public final void zzb(float f) {
        zzamz zzamz = this.zzdll;
        if (zzamz != null) {
            zzamz.zzdlf.zzb(f);
            zzamz.zzsn();
        }
    }

    public final void zzd(int i, int i2, int i3, int i4) {
        if (i3 != 0 && i4 != 0) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i3, i4);
            layoutParams.setMargins(i, i2, 0, 0);
            this.zzdlh.setLayoutParams(layoutParams);
            requestLayout();
        }
    }

    public final void zzda(String str) {
        this.zzdls = str;
    }

    public final void zzf(int i, int i2) {
        if (this.zzdlp) {
            int max = Math.max(i / ((Integer) zzlc.zzio().zzd(zzoi.zzbmg)).intValue(), 1);
            int max2 = Math.max(i2 / ((Integer) zzlc.zzio().zzd(zzoi.zzbmg)).intValue(), 1);
            Bitmap bitmap = this.zzdlt;
            if (bitmap == null || bitmap.getWidth() != max || this.zzdlt.getHeight() != max2) {
                this.zzdlt = Bitmap.createBitmap(max, max2, Bitmap.Config.ARGB_8888);
                this.zzdlv = false;
            }
        }
    }

    public final void zzf(MotionEvent motionEvent) {
        zzamz zzamz = this.zzdll;
        if (zzamz != null) {
            zzamz.dispatchTouchEvent(motionEvent);
        }
    }

    public final void zzg(String str, String str2) {
        zza("error", "what", str, "extra", str2);
    }

    public final void zzso() {
        this.zzdlj.resume();
        zzaij.zzdfn.post(new zzanc(this));
    }

    public final void zzsp() {
        zzamz zzamz = this.zzdll;
        if (zzamz != null && this.zzdlr == 0) {
            zza("canplaythrough", "duration", String.valueOf(((float) zzamz.getDuration()) / 1000.0f), "videoWidth", String.valueOf(this.zzdll.getVideoWidth()), "videoHeight", String.valueOf(this.zzdll.getVideoHeight()));
        }
    }

    public final void zzsq() {
        if (this.zzdlg.zztj() != null && !this.zzdln) {
            boolean z = (this.zzdlg.zztj().getWindow().getAttributes().flags & 128) != 0;
            this.zzdlo = z;
            if (!z) {
                this.zzdlg.zztj().getWindow().addFlags(128);
                this.zzdln = true;
            }
        }
        this.zzdlm = true;
    }

    public final void zzsr() {
        zza("ended", new String[0]);
        zzta();
    }

    public final void zzss() {
        if (this.zzdlv && this.zzdlt != null && !zzsz()) {
            this.zzdlu.setImageBitmap(this.zzdlt);
            this.zzdlu.invalidate();
            this.zzdlh.addView(this.zzdlu, new FrameLayout.LayoutParams(-1, -1));
            this.zzdlh.bringChildToFront(this.zzdlu);
        }
        this.zzdlj.pause();
        this.zzdlr = this.zzdlq;
        zzaij.zzdfn.post(new zzand(this));
    }

    public final void zzst() {
        if (this.zzdlm && zzsz()) {
            this.zzdlh.removeView(this.zzdlu);
        }
        if (this.zzdlt != null) {
            long elapsedRealtime = zzbt.zzes().elapsedRealtime();
            if (this.zzdll.getBitmap(this.zzdlt) != null) {
                this.zzdlv = true;
            }
            long elapsedRealtime2 = zzbt.zzes().elapsedRealtime() - elapsedRealtime;
            if (zzahw.zzqk()) {
                StringBuilder sb = new StringBuilder(46);
                sb.append("Spinner frame grab took ");
                sb.append(elapsedRealtime2);
                sb.append("ms");
                zzahw.v(sb.toString());
            }
            if (elapsedRealtime2 > this.zzdlk) {
                zzahw.zzcz("Spinner frame grab crossed jank threshold! Suspending spinner.");
                this.zzdlp = false;
                this.zzdlt = null;
                zzov zzov = this.zzdli;
                if (zzov != null) {
                    zzov.zzf("spinner_jank", Long.toString(elapsedRealtime2));
                }
            }
        }
    }

    public final void zzsu() {
        if (this.zzdll != null) {
            if (!TextUtils.isEmpty(this.zzdls)) {
                this.zzdll.setVideoPath(this.zzdls);
            } else {
                zza("no_src", new String[0]);
            }
        }
    }

    public final void zzsv() {
        zzamz zzamz = this.zzdll;
        if (zzamz != null) {
            zzamz.zzdlf.setMuted(true);
            zzamz.zzsn();
        }
    }

    public final void zzsw() {
        zzamz zzamz = this.zzdll;
        if (zzamz != null) {
            zzamz.zzdlf.setMuted(false);
            zzamz.zzsn();
        }
    }

    public final void zzsx() {
        zzamz zzamz = this.zzdll;
        if (zzamz != null) {
            TextView textView = new TextView(zzamz.getContext());
            String valueOf = String.valueOf(this.zzdll.zzsj());
            textView.setText(valueOf.length() != 0 ? "AdMob - ".concat(valueOf) : new String("AdMob - "));
            textView.setTextColor(SupportMenu.CATEGORY_MASK);
            textView.setBackgroundColor(InputDeviceCompat.SOURCE_ANY);
            this.zzdlh.addView(textView, new FrameLayout.LayoutParams(-2, -2, 17));
            this.zzdlh.bringChildToFront(textView);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzsy() {
        zzamz zzamz = this.zzdll;
        if (zzamz != null) {
            long currentPosition = (long) zzamz.getCurrentPosition();
            if (this.zzdlq != currentPosition && currentPosition > 0) {
                zza("timeupdate", "time", String.valueOf(((float) currentPosition) / 1000.0f));
                this.zzdlq = currentPosition;
            }
        }
    }
}
