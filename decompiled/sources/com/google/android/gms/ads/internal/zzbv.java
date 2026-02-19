package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ViewSwitcher;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzajc;
import com.google.android.gms.internal.zzakn;
import com.google.android.gms.internal.zzaof;
import java.util.ArrayList;

public final class zzbv extends ViewSwitcher {
    private final zzajc zzavj;
    private final zzakn zzavk;
    private boolean zzavl = true;

    public zzbv(Context context, String str, String str2, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener onScrollChangedListener) {
        super(context);
        zzajc zzajc = new zzajc(context);
        this.zzavj = zzajc;
        zzajc.setAdUnitId(str);
        zzajc.zzcq(str2);
        if (context instanceof Activity) {
            this.zzavk = new zzakn((Activity) context, this, onGlobalLayoutListener, onScrollChangedListener);
        } else {
            this.zzavk = new zzakn((Activity) null, this, onGlobalLayoutListener, onScrollChangedListener);
        }
        this.zzavk.zzrv();
    }

    /* access modifiers changed from: protected */
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        zzakn zzakn = this.zzavk;
        if (zzakn != null) {
            zzakn.onAttachedToWindow();
        }
    }

    /* access modifiers changed from: protected */
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        zzakn zzakn = this.zzavk;
        if (zzakn != null) {
            zzakn.onDetachedFromWindow();
        }
    }

    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.zzavl) {
            return false;
        }
        this.zzavj.zze(motionEvent);
        return false;
    }

    public final void removeAllViews() {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (childAt != null && (childAt instanceof zzaof)) {
                arrayList.add((zzaof) childAt);
            }
        }
        super.removeAllViews();
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            ((zzaof) obj).destroy();
        }
    }

    public final zzajc zzfr() {
        return this.zzavj;
    }

    public final void zzfs() {
        zzahw.v("Disable position monitoring on adFrame.");
        zzakn zzakn = this.zzavk;
        if (zzakn != null) {
            zzakn.zzrw();
        }
    }

    public final void zzft() {
        zzahw.v("Enable debug gesture detector on adFrame.");
        this.zzavl = true;
    }

    public final void zzfu() {
        zzahw.v("Disable debug gesture detector on adFrame.");
        this.zzavl = false;
    }
}
