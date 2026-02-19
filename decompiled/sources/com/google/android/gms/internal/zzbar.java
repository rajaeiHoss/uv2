package com.google.android.gms.internal;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.gms.R;
import com.google.android.gms.cast.framework.IntroductoryOverlay;

public final class zzbar extends RelativeLayout implements IntroductoryOverlay {
    private Activity mActivity;
    private IntroductoryOverlay.OnOverlayDismissedListener zzfba;
    private final boolean zzfbp;
    private boolean zzfbr;
    private int zzfbu;
    private final zzbau zzfbv;

    public zzbar(IntroductoryOverlay.Builder builder) {
        this(builder, (AttributeSet) null, R.attr.castIntroOverlayStyle);
    }

    private zzbar(IntroductoryOverlay.Builder builder, AttributeSet attributeSet, int i) {
        super(builder.getActivity(), (AttributeSet) null, i);
        this.mActivity = builder.getActivity();
        this.zzfbp = builder.zzaep();
        this.zzfba = builder.zzaen();
        TypedArray obtainStyledAttributes = this.mActivity.getTheme().obtainStyledAttributes((AttributeSet) null, R.styleable.CastIntroOverlay, i, R.style.CastIntroOverlay);
        if (builder.zzaem() != null) {
            Rect rect = new Rect();
            builder.zzaem().getGlobalVisibleRect(rect);
            zzbau zzbau = new zzbau((zzbas) null);
            this.zzfbv = zzbau;
            zzbau.x = rect.centerX();
            zzbau.y = rect.centerY();
            PorterDuffXfermode porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY);
            Paint paint = new Paint();
            paint.setColor(-1);
            paint.setAlpha(0);
            paint.setXfermode(porterDuffXfermode);
            paint.setAntiAlias(true);
            zzbau.zzfby = paint;
            zzbau.zzfbz = builder.zzaes();
            if (zzbau.zzfbz == 0.0f) {
                zzbau.zzfbz = obtainStyledAttributes.getDimension(R.styleable.CastIntroOverlay_castFocusRadius, 0.0f);
            }
        } else {
            this.zzfbv = null;
        }
        LayoutInflater.from(this.mActivity).inflate(R.layout.cast_intro_overlay, this);
        int zzaeo = builder.zzaeo();
        this.zzfbu = zzaeo;
        if (zzaeo == 0) {
            this.zzfbu = obtainStyledAttributes.getColor(R.styleable.CastIntroOverlay_castBackgroundColor, Color.argb(0, 0, 0, 0));
        }
        TextView textView = (TextView) findViewById(R.id.textTitle);
        if (!TextUtils.isEmpty(builder.zzaeq())) {
            textView.setText(builder.zzaeq());
            int resourceId = obtainStyledAttributes.getResourceId(R.styleable.CastIntroOverlay_castTitleTextAppearance, 0);
            if (resourceId != 0) {
                textView.setTextAppearance(this.mActivity, resourceId);
            }
        }
        String zzaer = builder.zzaer();
        zzaer = TextUtils.isEmpty(zzaer) ? obtainStyledAttributes.getString(R.styleable.CastIntroOverlay_castButtonText) : zzaer;
        int color = obtainStyledAttributes.getColor(R.styleable.CastIntroOverlay_castButtonBackgroundColor, Color.argb(0, 0, 0, 0));
        Button button = (Button) findViewById(R.id.button);
        button.setText(zzaer);
        button.getBackground().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
        int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.CastIntroOverlay_castButtonTextAppearance, 0);
        if (resourceId2 != 0) {
            button.setTextAppearance(this.mActivity, resourceId2);
        }
        button.setOnClickListener(new zzbas(this));
        obtainStyledAttributes.recycle();
        setFitsSystemWindows(true);
    }

    /* access modifiers changed from: private */
    public final void zzaez() {
        IntroductoryOverlay.zza.zzbw(this.mActivity);
        IntroductoryOverlay.OnOverlayDismissedListener onOverlayDismissedListener = this.zzfba;
        if (onOverlayDismissedListener != null) {
            onOverlayDismissedListener.onOverlayDismissed();
            this.zzfba = null;
        }
        remove();
    }

    /* access modifiers changed from: protected */
    public final void dispatchDraw(Canvas canvas) {
        Bitmap createBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas2 = new Canvas(createBitmap);
        canvas2.drawColor(this.zzfbu);
        zzbau zzbau = this.zzfbv;
        if (zzbau != null) {
            canvas2.drawCircle((float) zzbau.x, (float) this.zzfbv.y, this.zzfbv.zzfbz, this.zzfbv.zzfby);
        }
        canvas.drawBitmap(createBitmap, 0.0f, 0.0f, (Paint) null);
        createBitmap.recycle();
        super.dispatchDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public final void onDetachedFromWindow() {
        if (this.mActivity != null) {
            this.mActivity = null;
        }
        super.onDetachedFromWindow();
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    public final void remove() {
        Activity activity = this.mActivity;
        if (activity != null) {
            ((ViewGroup) activity.getWindow().getDecorView()).removeView(this);
            this.mActivity = null;
        }
        this.zzfba = null;
    }

    public final void show() {
        Activity activity = this.mActivity;
        if (activity == null || zzban.zzbz(activity)) {
            return;
        }
        if (this.zzfbp && IntroductoryOverlay.zza.zzbx(this.mActivity)) {
            this.mActivity = null;
            this.zzfba = null;
        } else if (!this.zzfbr) {
            this.zzfbr = true;
            ((ViewGroup) this.mActivity.getWindow().getDecorView()).addView(this);
        }
    }
}
