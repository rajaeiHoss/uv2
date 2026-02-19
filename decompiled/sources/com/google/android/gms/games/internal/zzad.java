package com.google.android.gms.games.internal;

import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import com.google.android.gms.common.util.zzs;
import java.lang.ref.WeakReference;

final class zzad extends zzaa implements View.OnAttachStateChangeListener, ViewTreeObserver.OnGlobalLayoutListener {
    private boolean zzhww = false;
    private WeakReference<View> zziag;

    protected zzad(GamesClientImpl gamesClientImpl, int i) {
        super(gamesClientImpl, i);
    }

    private final void zzab(View view) {
        Display display;
        int i = -1;
        if (zzs.zzant() && (display = view.getDisplay()) != null) {
            i = display.getDisplayId();
        }
        IBinder windowToken = view.getWindowToken();
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        int width = view.getWidth();
        int height = view.getHeight();
        this.zziad.zziaf = i;
        this.zziad.zziae = windowToken;
        this.zziad.left = iArr[0];
        this.zziad.top = iArr[1];
        this.zziad.right = iArr[0] + width;
        this.zziad.bottom = iArr[1] + height;
        if (this.zzhww) {
            zzaux();
        }
    }

    public final void onGlobalLayout() {
        View view;
        WeakReference<View> weakReference = this.zziag;
        if (weakReference != null && (view = (View) weakReference.get()) != null) {
            zzab(view);
        }
    }

    public final void onViewAttachedToWindow(View view) {
        zzab(view);
    }

    public final void onViewDetachedFromWindow(View view) {
        this.zziac.zzaur();
        view.removeOnAttachStateChangeListener(this);
    }

    public final void zzaa(View view) {
        this.zziac.zzaur();
        WeakReference<View> weakReference = this.zziag;
        if (weakReference != null) {
            View view2 = (View) weakReference.get();
            Context context = this.zziac.getContext();
            if (view2 == null && (context instanceof Activity)) {
                view2 = ((Activity) context).getWindow().getDecorView();
            }
            if (view2 != null) {
                view2.removeOnAttachStateChangeListener(this);
                ViewTreeObserver viewTreeObserver = view2.getViewTreeObserver();
                if (zzs.zzans()) {
                    viewTreeObserver.removeOnGlobalLayoutListener(this);
                } else {
                    viewTreeObserver.removeGlobalOnLayoutListener(this);
                }
            }
        }
        this.zziag = null;
        Context context2 = this.zziac.getContext();
        if (view == null && (context2 instanceof Activity)) {
            Activity activity = (Activity) context2;
            view = activity.findViewById(16908290);
            if (view == null) {
                view = activity.getWindow().getDecorView();
            }
            zzf.zzv("PopupManager", "You have not specified a View to use as content view for popups. Falling back to the Activity content view. Note that this may not work as expected in multi-screen environments");
        }
        if (view != null) {
            zzab(view);
            this.zziag = new WeakReference<>(view);
            view.addOnAttachStateChangeListener(this);
            view.getViewTreeObserver().addOnGlobalLayoutListener(this);
            return;
        }
        zzf.zzw("PopupManager", "No content view usable to display popups. Popups will not be displayed in response to this client's calls. Use setViewForPopups() to set your content view.");
    }

    public final void zzaux() {
        boolean z;
        if (this.zziad.zziae != null) {
            super.zzaux();
            z = false;
        } else {
            z = true;
        }
        this.zzhww = z;
    }

    /* access modifiers changed from: protected */
    public final void zzdv(int i) {
        this.zziad = new zzac(i, (IBinder) null);
    }
}
