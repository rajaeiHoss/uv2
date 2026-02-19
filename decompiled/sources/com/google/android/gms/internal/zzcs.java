package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class zzcs implements zzcr {
    protected MotionEvent zzagw;
    protected LinkedList<MotionEvent> zzagx = new LinkedList<>();
    protected long zzagy = 0;
    protected long zzagz = 0;
    protected long zzaha = 0;
    protected long zzahb = 0;
    protected long zzahc = 0;
    protected long zzahd = 0;
    protected long zzahe = 0;
    protected double zzahf;
    private double zzahg;
    private double zzahh;
    protected float zzahi;
    protected float zzahj;
    protected float zzahk;
    protected float zzahl;
    private boolean zzahm = false;
    protected boolean zzahn = false;
    protected DisplayMetrics zzaho;

    protected zzcs(Context context) {
        try {
            if (((Boolean) zzlc.zzio().zzd(zzoi.zzbrr)).booleanValue()) {
                zzbx.zzz();
            } else {
                zzdq.zzar();
            }
            this.zzaho = context.getResources().getDisplayMetrics();
        } catch (Throwable unused) {
        }
    }

    private final String zza(Context context, String str, boolean z, View view, Activity activity, byte[] bArr) {
        zzba zzba;
        int i;
        if (z) {
            try {
                zzba = zza(context, view, activity);
                this.zzahm = true;
            } catch (UnsupportedEncodingException | GeneralSecurityException unused) {
                i = 7;
                return Integer.toString(i);
            } catch (Throwable unused2) {
                i = 3;
                return Integer.toString(i);
            }
        } else {
            zzba = zza(context, (zzax) null);
        }
        if (zzba != null) {
            if (zzba.zzhs() != 0) {
                try {
                    return zzbx.zza(zzba, str);
                } catch (UnsupportedEncodingException | GeneralSecurityException unused3) {
                    return Integer.toString(7);
                }
            }
        }
        return Integer.toString(5);
    }

    /* access modifiers changed from: protected */
    public abstract long zza(StackTraceElement[] stackTraceElementArr) throws zzdj;

    /* access modifiers changed from: protected */
    public abstract zzba zza(Context context, View view, Activity activity) throws UnsupportedEncodingException, GeneralSecurityException;

    /* access modifiers changed from: protected */
    public abstract zzba zza(Context context, zzax zzax);

    public final String zza(Context context) {
        if (zzds.zzas()) {
            if (((Boolean) zzlc.zzio().zzd(zzoi.zzbrt)).booleanValue()) {
                throw new IllegalStateException("The caller must not be called from the UI thread.");
            }
        }
        return zza(context, (String) null, false, (View) null, (Activity) null, (byte[]) null);
    }

    public final String zza(Context context, String str, View view) {
        return zza(context, str, view, (Activity) null);
    }

    public final String zza(Context context, String str, View view, Activity activity) {
        return zza(context, str, true, view, activity, (byte[]) null);
    }

    public final void zza(int i, int i2, int i3) {
        MotionEvent motionEvent;
        MotionEvent motionEvent2 = this.zzagw;
        if (motionEvent2 != null) {
            motionEvent2.recycle();
        }
        DisplayMetrics displayMetrics = this.zzaho;
        if (displayMetrics != null) {
            motionEvent = MotionEvent.obtain(0, (long) i3, 1, ((float) i) * displayMetrics.density, this.zzaho.density * ((float) i2), 0.0f, 0.0f, 0, 0.0f, 0.0f, 0, 0);
        } else {
            motionEvent = null;
        }
        this.zzagw = motionEvent;
        this.zzahn = false;
    }

    public final void zza(MotionEvent motionEvent) {
        boolean z = false;
        if (this.zzahm) {
            this.zzahb = 0;
            this.zzaha = 0;
            this.zzagz = 0;
            this.zzagy = 0;
            this.zzahc = 0;
            this.zzahe = 0;
            this.zzahd = 0;
            Iterator it = this.zzagx.iterator();
            while (it.hasNext()) {
                ((MotionEvent) it.next()).recycle();
            }
            this.zzagx.clear();
            this.zzagw = null;
            this.zzahm = false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.zzahf = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            this.zzahg = (double) motionEvent.getRawX();
            this.zzahh = (double) motionEvent.getRawY();
        } else if (action == 1 || action == 2) {
            double rawX = (double) motionEvent.getRawX();
            double rawY = (double) motionEvent.getRawY();
            double d = rawX - this.zzahg;
            double d2 = rawY - this.zzahh;
            this.zzahf += Math.sqrt((d * d) + (d2 * d2));
            this.zzahg = rawX;
            this.zzahh = rawY;
        }
        int action2 = motionEvent.getAction();
        if (action2 == 0) {
            if (((Boolean) zzlc.zzio().zzd(zzoi.zzbro)).booleanValue()) {
                this.zzahi = motionEvent.getX();
                this.zzahj = motionEvent.getY();
                this.zzahk = motionEvent.getRawX();
                this.zzahl = motionEvent.getRawY();
            }
            this.zzagy++;
        } else if (action2 == 1) {
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            this.zzagw = obtain;
            this.zzagx.add(obtain);
            if (this.zzagx.size() > 6) {
                this.zzagx.remove().recycle();
            }
            this.zzaha++;
            try {
                this.zzahc = zza(new Throwable().getStackTrace());
            } catch (zzdj unused2) {
                this.zzahc = 0;
            }
        } else if (action2 == 2) {
            this.zzagz += (long) (motionEvent.getHistorySize() + 1);
            try {
                zzdr zzb = zzb(motionEvent);
                if ((zzb == null || zzb.zzfl == null || zzb.zzajp == null) ? false : true) {
                    this.zzahd += zzb.zzfl.longValue() + zzb.zzajp.longValue();
                }
                if (!(this.zzaho == null || zzb == null || zzb.zzfj == null || zzb.zzajq == null)) {
                    z = true;
                }
                if (z) {
                    this.zzahe += zzb.zzfj.longValue() + zzb.zzajq.longValue();
                }
            } catch (zzdj unused) {
            }
        } else if (action2 == 3) {
            this.zzahb++;
        }
        this.zzahn = true;
    }

    /* access modifiers changed from: protected */
    public abstract zzdr zzb(MotionEvent motionEvent) throws zzdj;

    public void zzb(View view) {
    }
}
