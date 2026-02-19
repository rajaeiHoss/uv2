package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.google.android.gms.R;
import com.google.android.gms.internal.zzdny;

class InnerZoneDrawable extends Drawable {
    private float centerX;
    private float centerY;
    private final Paint zzfby;
    private float zzfbz;
    private final Rect zzfcd = new Rect();
    private final Paint zzfcy;
    private final int zzfcz;
    private final int zzfda;
    private float zzfdb = 1.0f;
    private float zzfdc;
    private float zzfdd;

    public InnerZoneDrawable(Context context) {
        Paint paint = new Paint();
        this.zzfby = paint;
        Paint paint2 = new Paint();
        this.zzfcy = paint2;
        Resources resources = context.getResources();
        this.zzfcz = resources.getDimensionPixelSize(R.dimen.cast_libraries_material_featurehighlight_inner_radius);
        this.zzfda = resources.getInteger(R.integer.cast_libraries_material_featurehighlight_pulse_base_alpha);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint2.setAntiAlias(true);
        paint2.setStyle(Paint.Style.FILL);
        paint.setColor(-1);
        paint2.setColor(-1);
        invalidateSelf();
    }

    public void draw(Canvas canvas) {
        float f = this.zzfdd;
        if (f > 0.0f) {
            float f2 = this.zzfbz * this.zzfdc;
            this.zzfcy.setAlpha((int) (((float) this.zzfda) * f));
            canvas.drawCircle(this.centerX, this.centerY, f2, this.zzfcy);
        }
        canvas.drawCircle(this.centerX, this.centerY, this.zzfbz * this.zzfdb, this.zzfby);
    }

    public int getOpacity() {
        return -3;
    }

    public void setAlpha(int i) {
        this.zzfby.setAlpha(i);
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.zzfby.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public void setPulseAlpha(float f) {
        this.zzfdd = f;
        invalidateSelf();
    }

    public void setPulseScale(float f) {
        this.zzfdc = f;
        invalidateSelf();
    }

    public void setScale(float f) {
        this.zzfdb = f;
        invalidateSelf();
    }

    public final Animator zzaff() {
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("scale", new float[]{0.0f}), PropertyValuesHolder.ofInt("alpha", new int[]{0}), PropertyValuesHolder.ofFloat("pulseScale", new float[]{0.0f}), PropertyValuesHolder.ofFloat("pulseAlpha", new float[]{0.0f})});
        ofPropertyValuesHolder.setInterpolator(zzdny.zzbmj());
        return ofPropertyValuesHolder.setDuration(200);
    }

    public final void zzc(Rect rect) {
        this.zzfcd.set(rect);
        this.centerX = this.zzfcd.exactCenterX();
        this.centerY = this.zzfcd.exactCenterY();
        this.zzfbz = Math.max((float) this.zzfcz, Math.max(((float) this.zzfcd.width()) / 2.0f, ((float) this.zzfcd.height()) / 2.0f));
        invalidateSelf();
    }
}
