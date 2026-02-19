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
import android.util.TypedValue;
import androidx.core.graphics.ColorUtils;
import com.google.android.gms.R;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.internal.zzdny;
import com.google.android.gms.internal.zzdoa;

class OuterHighlightDrawable extends Drawable {
    private float centerX;
    private float centerY;
    private final Paint zzfby;
    private float zzfbz;
    private final Rect zzfcd = new Rect();
    private float zzfdb;
    private final Rect zzfde = new Rect();
    private final int zzfdk;
    private final int zzfdl;
    private final int zzfdm;
    private float zzfdn;
    private float zzfdo;
    private int zzfdp;

    public OuterHighlightDrawable(Context context) {
        int i;
        Paint paint = new Paint();
        this.zzfby = paint;
        this.zzfdb = 1.0f;
        this.zzfdn = 0.0f;
        this.zzfdo = 0.0f;
        this.zzfdp = 244;
        if (zzs.zzanx()) {
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(16843827, typedValue, true);
            i = ColorUtils.setAlphaComponent(typedValue.data, 244);
        } else {
            i = context.getResources().getColor(R.color.cast_libraries_material_featurehighlight_outer_highlight_default_color);
        }
        setColor(i);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        Resources resources = context.getResources();
        this.zzfdk = resources.getDimensionPixelSize(R.dimen.cast_libraries_material_featurehighlight_center_threshold);
        this.zzfdl = resources.getDimensionPixelSize(R.dimen.cast_libraries_material_featurehighlight_center_horizontal_offset);
        this.zzfdm = resources.getDimensionPixelSize(R.dimen.cast_libraries_material_featurehighlight_outer_padding);
    }

    private static float zza(float f, float f2, Rect rect) {
        float f3 = (float) rect.left;
        float f4 = (float) rect.top;
        float f5 = (float) rect.right;
        float f6 = (float) rect.bottom;
        float zza = zzdoa.zza(f, f2, f3, f4);
        float zza2 = zzdoa.zza(f, f2, f5, f4);
        float zza3 = zzdoa.zza(f, f2, f5, f6);
        float zza4 = zzdoa.zza(f, f2, f3, f6);
        if (zza <= zza2 || zza <= zza3 || zza <= zza4) {
            zza = (zza2 <= zza3 || zza2 <= zza4) ? zza3 > zza4 ? zza3 : zza4 : zza2;
        }
        return (float) Math.ceil((double) zza);
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(this.centerX + this.zzfdn, this.centerY + this.zzfdo, this.zzfbz * this.zzfdb, this.zzfby);
    }

    public int getAlpha() {
        return this.zzfby.getAlpha();
    }

    public final float getCenterX() {
        return this.centerX;
    }

    public final float getCenterY() {
        return this.centerY;
    }

    public final int getColor() {
        return this.zzfby.getColor();
    }

    public int getOpacity() {
        return -3;
    }

    public void setAlpha(int i) {
        this.zzfby.setAlpha(i);
        invalidateSelf();
    }

    public final void setColor(int i) {
        this.zzfby.setColor(i);
        this.zzfdp = this.zzfby.getAlpha();
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.zzfby.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public void setScale(float f) {
        this.zzfdb = f;
        invalidateSelf();
    }

    public void setTranslationX(float f) {
        this.zzfdn = f;
        invalidateSelf();
    }

    public void setTranslationY(float f) {
        this.zzfdo = f;
        invalidateSelf();
    }

    public final void zzb(Rect rect, Rect rect2) {
        this.zzfcd.set(rect);
        this.zzfde.set(rect2);
        float exactCenterX = rect.exactCenterX();
        float exactCenterY = rect.exactCenterY();
        Rect bounds = getBounds();
        if (Math.min(exactCenterY - ((float) bounds.top), ((float) bounds.bottom) - exactCenterY) < ((float) this.zzfdk)) {
            this.centerX = exactCenterX;
            this.centerY = exactCenterY;
        } else {
            this.centerX = (exactCenterX > bounds.exactCenterX() ? 1 : (exactCenterX == bounds.exactCenterX() ? 0 : -1)) <= 0 ? rect2.exactCenterX() + ((float) this.zzfdl) : rect2.exactCenterX() - ((float) this.zzfdl);
            this.centerY = rect2.exactCenterY();
        }
        this.zzfbz = ((float) this.zzfdm) + Math.max(zza(this.centerX, this.centerY, rect), zza(this.centerX, this.centerY, rect2));
        invalidateSelf();
    }

    public final boolean zzd(float f, float f2) {
        return zzdoa.zza(f, f2, this.centerX, this.centerY) < this.zzfbz;
    }

    public final Animator zze(float f, float f2) {
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("scale", new float[]{0.0f, 1.0f}), PropertyValuesHolder.ofFloat("translationX", new float[]{f, 0.0f}), PropertyValuesHolder.ofFloat("translationY", new float[]{f2, 0.0f}), PropertyValuesHolder.ofInt("alpha", new int[]{0, this.zzfdp})});
        ofPropertyValuesHolder.setInterpolator(zzdny.zzbmi());
        return ofPropertyValuesHolder.setDuration(350);
    }
}
