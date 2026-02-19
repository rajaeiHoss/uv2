package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.GestureDetectorCompat;
import com.google.android.gms.R;
import com.google.android.gms.internal.zzdnp;
import com.google.android.gms.internal.zzdny;
import com.google.android.gms.internal.zzdog;

public final class zza extends ViewGroup {
    private View targetView;
    private final int[] zzfcc = new int[2];
    private final Rect zzfcd = new Rect();
    private final Rect zzfce = new Rect();
    /* access modifiers changed from: private */
    public final OuterHighlightDrawable zzfcf;
    private final InnerZoneDrawable zzfcg;
    private zzi zzfch;
    private View zzfci;
    /* access modifiers changed from: private */
    public Animator zzfcj;
    private final zzj zzfck;
    private final GestureDetectorCompat zzfcl;
    private GestureDetectorCompat zzfcm;
    /* access modifiers changed from: private */
    public zzh zzfcn;
    private boolean zzfco;

    public zza(Context context) {
        super(context);
        setId(R.id.cast_featurehighlight_view);
        setWillNotDraw(false);
        InnerZoneDrawable innerZoneDrawable = new InnerZoneDrawable(context);
        this.zzfcg = innerZoneDrawable;
        innerZoneDrawable.setCallback(this);
        OuterHighlightDrawable outerHighlightDrawable = new OuterHighlightDrawable(context);
        this.zzfcf = outerHighlightDrawable;
        outerHighlightDrawable.setCallback(this);
        this.zzfck = new zzj(this);
        GestureDetectorCompat gestureDetectorCompat = new GestureDetectorCompat(context, new zzb(this));
        this.zzfcl = gestureDetectorCompat;
        gestureDetectorCompat.setIsLongpressEnabled(false);
        setVisibility(8);
    }

    private final void zza(Animator animator) {
        Animator animator2 = this.zzfcj;
        if (animator2 != null) {
            animator2.cancel();
        }
        this.zzfcj = animator;
        animator.start();
    }

    /* access modifiers changed from: private */
    public final Animator zzafe() {
        InnerZoneDrawable innerZoneDrawable = this.zzfcg;
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator duration = ObjectAnimator.ofFloat(innerZoneDrawable, "scale", new float[]{1.0f, 1.1f}).setDuration(500);
        ObjectAnimator duration2 = ObjectAnimator.ofFloat(innerZoneDrawable, "scale", new float[]{1.1f, 1.0f}).setDuration(500);
        ObjectAnimator duration3 = ObjectAnimator.ofPropertyValuesHolder(innerZoneDrawable, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("pulseScale", new float[]{1.1f, 2.0f}), PropertyValuesHolder.ofFloat("pulseAlpha", new float[]{1.0f, 0.0f})}).setDuration(500);
        animatorSet.play(duration);
        animatorSet.play(duration2).with(duration3).after(duration);
        animatorSet.setInterpolator(zzdny.zzbmk());
        animatorSet.setStartDelay(500);
        zzdnp.zza(animatorSet, -1, (Runnable) null);
        return animatorSet;
    }

    /* access modifiers changed from: private */
    public final boolean zzc(float f, float f2) {
        return this.zzfce.contains(Math.round(f), Math.round(f2));
    }

    /* access modifiers changed from: protected */
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams;
    }

    /* access modifiers changed from: protected */
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-2, -2);
    }

    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new ViewGroup.MarginLayoutParams(layoutParams);
    }

    /* access modifiers changed from: protected */
    public final void onDraw(Canvas canvas) {
        canvas.save();
        this.zzfcf.draw(canvas);
        this.zzfcg.draw(canvas);
        View view = this.targetView;
        if (view != null) {
            if (view.getParent() != null) {
                Bitmap createBitmap = Bitmap.createBitmap(this.targetView.getWidth(), this.targetView.getHeight(), Bitmap.Config.ARGB_8888);
                this.targetView.draw(new Canvas(createBitmap));
                int color = this.zzfcf.getColor();
                int red = Color.red(color);
                int green = Color.green(color);
                int blue = Color.blue(color);
                for (int i = 0; i < createBitmap.getHeight(); i++) {
                    for (int i2 = 0; i2 < createBitmap.getWidth(); i2++) {
                        int pixel = createBitmap.getPixel(i2, i);
                        if (Color.alpha(pixel) != 0) {
                            createBitmap.setPixel(i2, i, Color.argb(Color.alpha(pixel), red, green, blue));
                        }
                    }
                }
                canvas.drawBitmap(createBitmap, (float) this.zzfcd.left, (float) this.zzfcd.top, (Paint) null);
            }
            canvas.restore();
            return;
        }
        throw new IllegalStateException("Neither target view nor drawable was set");
    }

    /* access modifiers changed from: protected */
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View view = this.targetView;
        if (view != null) {
            if (view.getParent() != null) {
                int[] iArr = this.zzfcc;
                View view2 = this.targetView;
                getLocationInWindow(iArr);
                int i5 = iArr[0];
                int i6 = iArr[1];
                view2.getLocationInWindow(iArr);
                iArr[0] = iArr[0] - i5;
                iArr[1] = iArr[1] - i6;
            }
            Rect rect = this.zzfcd;
            int[] iArr2 = this.zzfcc;
            rect.set(iArr2[0], iArr2[1], iArr2[0] + this.targetView.getWidth(), this.zzfcc[1] + this.targetView.getHeight());
            this.zzfce.set(i, i2, i3, i4);
            this.zzfcf.setBounds(this.zzfce);
            this.zzfcg.setBounds(this.zzfce);
            this.zzfck.zza(this.zzfcd, this.zzfce);
            return;
        }
        throw new IllegalStateException("Target view must be set before layout");
    }

    /* access modifiers changed from: protected */
    public final void onMeasure(int i, int i2) {
        setMeasuredDimension(resolveSize(View.MeasureSpec.getSize(i), i), resolveSize(View.MeasureSpec.getSize(i2), i2));
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.zzfco = this.zzfcd.contains((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        if (this.zzfco) {
            GestureDetectorCompat gestureDetectorCompat = this.zzfcm;
            if (gestureDetectorCompat != null) {
                gestureDetectorCompat.onTouchEvent(motionEvent);
                if (actionMasked == 1) {
                    motionEvent = MotionEvent.obtain(motionEvent);
                    motionEvent.setAction(3);
                }
            }
            if (this.targetView.getParent() != null) {
                this.targetView.onTouchEvent(motionEvent);
            }
        } else {
            this.zzfcl.onTouchEvent(motionEvent);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public final boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.zzfcf || drawable == this.zzfcg || drawable == null;
    }

    public final void zza(View view, View view2, boolean z, zzh zzh) {
        this.targetView = (View) zzdog.checkNotNull(view);
        this.zzfci = null;
        this.zzfcn = (zzh) zzdog.checkNotNull(zzh);
        GestureDetectorCompat gestureDetectorCompat = new GestureDetectorCompat(getContext(), new zzc(this, view, true, zzh));
        this.zzfcm = gestureDetectorCompat;
        gestureDetectorCompat.setIsLongpressEnabled(false);
        setVisibility(4);
    }

    public final void zza(zzi zzi) {
        this.zzfch = (zzi) zzdog.checkNotNull(zzi);
        addView(zzi.asView(), 0);
    }

    public final void zzafa() {
        if (this.targetView != null) {
            setVisibility(0);
            ObjectAnimator duration = ObjectAnimator.ofFloat(this.zzfch.asView(), "alpha", new float[]{0.0f, 1.0f}).setDuration(350);
            duration.setInterpolator(zzdny.zzbmi());
            Animator zze = this.zzfcf.zze(this.zzfcd.exactCenterX() - this.zzfcf.getCenterX(), this.zzfcd.exactCenterY() - this.zzfcf.getCenterY());
            ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this.zzfcg, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("scale", new float[]{0.0f, 1.0f}), PropertyValuesHolder.ofInt("alpha", new int[]{0, 255})});
            ofPropertyValuesHolder.setInterpolator(zzdny.zzbmi());
            Animator duration2 = ofPropertyValuesHolder.setDuration(350);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(new Animator[]{duration, zze, duration2});
            animatorSet.addListener(new zze(this));
            zza((Animator) animatorSet);
            return;
        }
        throw new IllegalStateException("Target view must be set before animation");
    }

    /* access modifiers changed from: package-private */
    public final View zzafb() {
        return this.zzfch.asView();
    }

    /* access modifiers changed from: package-private */
    public final OuterHighlightDrawable zzafc() {
        return this.zzfcf;
    }

    /* access modifiers changed from: package-private */
    public final InnerZoneDrawable zzafd() {
        return this.zzfcg;
    }

    public final void zzbg(int i) {
        this.zzfcf.setColor(i);
    }

    public final void zze(Runnable runnable) {
        addOnLayoutChangeListener(new zzd(this, (Runnable) null));
    }

    public final void zzf(Runnable runnable) {
        ObjectAnimator duration = ObjectAnimator.ofFloat(this.zzfch.asView(), "alpha", new float[]{0.0f}).setDuration(200);
        duration.setInterpolator(zzdny.zzbmj());
        float exactCenterX = this.zzfcd.exactCenterX() - this.zzfcf.getCenterX();
        float exactCenterY = this.zzfcd.exactCenterY() - this.zzfcf.getCenterY();
        OuterHighlightDrawable outerHighlightDrawable = this.zzfcf;
        PropertyValuesHolder ofFloat = PropertyValuesHolder.ofFloat("scale", new float[]{0.0f});
        PropertyValuesHolder ofInt = PropertyValuesHolder.ofInt("alpha", new int[]{0});
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(outerHighlightDrawable, new PropertyValuesHolder[]{ofFloat, PropertyValuesHolder.ofFloat("translationX", new float[]{0.0f, exactCenterX}), PropertyValuesHolder.ofFloat("translationY", new float[]{0.0f, exactCenterY}), ofInt});
        ofPropertyValuesHolder.setInterpolator(zzdny.zzbmj());
        Animator duration2 = ofPropertyValuesHolder.setDuration(200);
        Animator zzaff = this.zzfcg.zzaff();
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{duration, duration2, zzaff});
        animatorSet.addListener(new zzg(this, runnable));
        zza((Animator) animatorSet);
    }

    public final void zzg(Runnable runnable) {
        ObjectAnimator duration = ObjectAnimator.ofFloat(this.zzfch.asView(), "alpha", new float[]{0.0f}).setDuration(200);
        duration.setInterpolator(zzdny.zzbmj());
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this.zzfcf, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("scale", new float[]{1.125f}), PropertyValuesHolder.ofInt("alpha", new int[]{0})});
        ofPropertyValuesHolder.setInterpolator(zzdny.zzbmj());
        Animator duration2 = ofPropertyValuesHolder.setDuration(200);
        Animator zzaff = this.zzfcg.zzaff();
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{duration, duration2, zzaff});
        animatorSet.addListener(new zzf(this, runnable));
        zza((Animator) animatorSet);
    }
}
