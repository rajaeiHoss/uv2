package com.google.android.gms.dynamic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.zzu;
import com.google.android.gms.common.zzf;
import com.google.android.gms.dynamic.LifecycleDelegate;
import java.util.LinkedList;

public abstract class zza<T extends LifecycleDelegate> {
    /* access modifiers changed from: private */
    public T zzhcp;
    /* access modifiers changed from: private */
    public Bundle zzhcq;
    /* access modifiers changed from: private */
    public LinkedList<zzi> zzhcr;
    private final zzo<T> zzhcs = new zzb<>(this);

    private final void zza(Bundle bundle, zzi zzi) {
        T t = this.zzhcp;
        if (t != null) {
            zzi.zzb(t);
            return;
        }
        if (this.zzhcr == null) {
            this.zzhcr = new LinkedList<>();
        }
        this.zzhcr.add(zzi);
        if (bundle != null) {
            Bundle bundle2 = this.zzhcq;
            if (bundle2 == null) {
                this.zzhcq = (Bundle) bundle.clone();
            } else {
                bundle2.putAll(bundle);
            }
        }
        zza(this.zzhcs);
    }

    public static void zzb(FrameLayout frameLayout) {
        GoogleApiAvailability instance = GoogleApiAvailability.getInstance();
        Context context = frameLayout.getContext();
        int isGooglePlayServicesAvailable = instance.isGooglePlayServicesAvailable(context);
        String zzh = zzu.zzh(context, isGooglePlayServicesAvailable);
        String zzj = zzu.zzj(context, isGooglePlayServicesAvailable);
        LinearLayout linearLayout = new LinearLayout(frameLayout.getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        frameLayout.addView(linearLayout);
        TextView textView = new TextView(frameLayout.getContext());
        textView.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        textView.setText(zzh);
        linearLayout.addView(textView);
        Intent zza = zzf.zza(context, isGooglePlayServicesAvailable, (String) null);
        if (zza != null) {
            Button button = new Button(context);
            button.setId(16908313);
            button.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
            button.setText(zzj);
            linearLayout.addView(button);
            button.setOnClickListener(new com.google.android.gms.dynamic.zzf(context, zza));
        }
    }

    private final void zzcz(int i) {
        while (!this.zzhcr.isEmpty() && this.zzhcr.getLast().getState() >= i) {
            this.zzhcr.removeLast();
        }
    }

    public final void onCreate(Bundle bundle) {
        zza(bundle, (zzi) new zzd(this, bundle));
    }

    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FrameLayout frameLayout = new FrameLayout(layoutInflater.getContext());
        zza(bundle, (zzi) new zze(this, frameLayout, layoutInflater, viewGroup, bundle));
        if (this.zzhcp == null) {
            zza(frameLayout);
        }
        return frameLayout;
    }

    public final void onDestroy() {
        T t = this.zzhcp;
        if (t != null) {
            t.onDestroy();
        } else {
            zzcz(1);
        }
    }

    public final void onDestroyView() {
        T t = this.zzhcp;
        if (t != null) {
            t.onDestroyView();
        } else {
            zzcz(2);
        }
    }

    public final void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
        zza(bundle2, (zzi) new zzc(this, activity, bundle, bundle2));
    }

    public final void onLowMemory() {
        T t = this.zzhcp;
        if (t != null) {
            t.onLowMemory();
        }
    }

    public final void onPause() {
        T t = this.zzhcp;
        if (t != null) {
            t.onPause();
        } else {
            zzcz(5);
        }
    }

    public final void onResume() {
        zza((Bundle) null, (zzi) new zzh(this));
    }

    public final void onSaveInstanceState(Bundle bundle) {
        T t = this.zzhcp;
        if (t != null) {
            t.onSaveInstanceState(bundle);
            return;
        }
        Bundle bundle2 = this.zzhcq;
        if (bundle2 != null) {
            bundle.putAll(bundle2);
        }
    }

    public final void onStart() {
        zza((Bundle) null, (zzi) new zzg(this));
    }

    public final void onStop() {
        T t = this.zzhcp;
        if (t != null) {
            t.onStop();
        } else {
            zzcz(4);
        }
    }

    /* access modifiers changed from: protected */
    public void zza(FrameLayout frameLayout) {
        GoogleApiAvailability instance = GoogleApiAvailability.getInstance();
        Context context = frameLayout.getContext();
        int isGooglePlayServicesAvailable = instance.isGooglePlayServicesAvailable(context);
        String zzh = zzu.zzh(context, isGooglePlayServicesAvailable);
        String zzj = zzu.zzj(context, isGooglePlayServicesAvailable);
        LinearLayout linearLayout = new LinearLayout(frameLayout.getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        frameLayout.addView(linearLayout);
        TextView textView = new TextView(frameLayout.getContext());
        textView.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        textView.setText(zzh);
        linearLayout.addView(textView);
        Intent zza = zzf.zza(context, isGooglePlayServicesAvailable, (String) null);
        if (zza != null) {
            Button button = new Button(context);
            button.setId(16908313);
            button.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
            button.setText(zzj);
            linearLayout.addView(button);
            button.setOnClickListener(new com.google.android.gms.dynamic.zzf(context, zza));
        }
    }

    /* access modifiers changed from: protected */
    public abstract void zza(zzo<T> zzo);

    public final T zzarg() {
        return this.zzhcp;
    }
}
