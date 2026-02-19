package com.google.android.gms.internal;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;

public final class zzbge extends Drawable implements Drawable.Callback {
    private int mAlpha;
    private int mFrom;
    private long zzebf;
    private boolean zzgdt;
    private int zzgdy;
    private int zzgdz;
    private int zzgea;
    private int zzgeb;
    private boolean zzgec;
    private zzbgi zzged;
    private Drawable zzgee;
    private Drawable zzgef;
    private boolean zzgeg;
    private boolean zzgeh;
    private boolean zzgei;
    private int zzgej;

    public zzbge(Drawable drawable, Drawable drawable2) {
        this((zzbgi) null);
        drawable = drawable == null ? zzbgg.zzgek : drawable;
        this.zzgee = drawable;
        drawable.setCallback(this);
        zzbgi zzbgi = this.zzged;
        zzbgi.zzgem = drawable.getChangingConfigurations() | zzbgi.zzgem;
        drawable2 = drawable2 == null ? zzbgg.zzgek : drawable2;
        this.zzgef = drawable2;
        drawable2.setCallback(this);
        zzbgi zzbgi2 = this.zzged;
        zzbgi2.zzgem = drawable2.getChangingConfigurations() | zzbgi2.zzgem;
    }

    zzbge(zzbgi zzbgi) {
        this.zzgdy = 0;
        this.zzgea = 255;
        this.mAlpha = 0;
        this.zzgdt = true;
        this.zzged = new zzbgi(zzbgi);
    }

    private final boolean canConstantState() {
        if (!this.zzgeg) {
            this.zzgeh = (this.zzgee.getConstantState() == null || this.zzgef.getConstantState() == null) ? false : true;
            this.zzgeg = true;
        }
        return this.zzgeh;
    }

    public final void draw(Canvas canvas) {
        int i = this.zzgdy;
        boolean z = false;
        boolean z2 = true;
        if (i == 1) {
            this.zzebf = SystemClock.uptimeMillis();
            this.zzgdy = 2;
        } else if (i == 2 && this.zzebf >= 0) {
            float uptimeMillis = ((float) (SystemClock.uptimeMillis() - this.zzebf)) / ((float) this.zzgeb);
            if (uptimeMillis < 1.0f) {
                z2 = false;
            }
            if (z2) {
                this.zzgdy = 0;
            }
            this.mAlpha = (int) ((((float) this.zzgdz) * Math.min(uptimeMillis, 1.0f)) + 0.0f);
            z = z2;
        } else {
            z = true;
        }
        int i2 = this.mAlpha;
        boolean z3 = this.zzgdt;
        Drawable drawable = this.zzgee;
        Drawable drawable2 = this.zzgef;
        if (z) {
            if (!z3 || i2 == 0) {
                drawable.draw(canvas);
            }
            int i3 = this.zzgea;
            if (i2 == i3) {
                drawable2.setAlpha(i3);
                drawable2.draw(canvas);
                return;
            }
            return;
        }
        if (z3) {
            drawable.setAlpha(this.zzgea - i2);
        }
        drawable.draw(canvas);
        if (z3) {
            drawable.setAlpha(this.zzgea);
        }
        if (i2 > 0) {
            drawable2.setAlpha(i2);
            drawable2.draw(canvas);
            drawable2.setAlpha(this.zzgea);
        }
        invalidateSelf();
    }

    public final int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.zzged.mChangingConfigurations | this.zzged.zzgem;
    }

    public final Drawable.ConstantState getConstantState() {
        if (!canConstantState()) {
            return null;
        }
        this.zzged.mChangingConfigurations = getChangingConfigurations();
        return this.zzged;
    }

    public final int getIntrinsicHeight() {
        return Math.max(this.zzgee.getIntrinsicHeight(), this.zzgef.getIntrinsicHeight());
    }

    public final int getIntrinsicWidth() {
        return Math.max(this.zzgee.getIntrinsicWidth(), this.zzgef.getIntrinsicWidth());
    }

    public final int getOpacity() {
        if (!this.zzgei) {
            this.zzgej = Drawable.resolveOpacity(this.zzgee.getOpacity(), this.zzgef.getOpacity());
            this.zzgei = true;
        }
        return this.zzgej;
    }

    public final void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    public final Drawable mutate() {
        if (!this.zzgec && super.mutate() == this) {
            if (canConstantState()) {
                this.zzgee.mutate();
                this.zzgef.mutate();
                this.zzgec = true;
            } else {
                throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
            }
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public final void onBoundsChange(Rect rect) {
        this.zzgee.setBounds(rect);
        this.zzgef.setBounds(rect);
    }

    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j);
        }
    }

    public final void setAlpha(int i) {
        if (this.mAlpha == this.zzgea) {
            this.mAlpha = i;
        }
        this.zzgea = i;
        invalidateSelf();
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        this.zzgee.setColorFilter(colorFilter);
        this.zzgef.setColorFilter(colorFilter);
    }

    public final void startTransition(int i) {
        this.mFrom = 0;
        this.zzgdz = this.zzgea;
        this.mAlpha = 0;
        this.zzgeb = 250;
        this.zzgdy = 1;
        invalidateSelf();
    }

    public final void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }

    public final Drawable zzaln() {
        return this.zzgef;
    }
}
