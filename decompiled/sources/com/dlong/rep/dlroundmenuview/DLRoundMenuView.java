package com.dlong.rep.dlroundmenuview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.dlong.rep.dlroundmenuview.Interface.OnMenuClickListener;
import com.dlong.rep.dlroundmenuview.Interface.OnMenuListener;
import com.dlong.rep.dlroundmenuview.Interface.OnMenuLongClickListener;
import com.dlong.rep.dlroundmenuview.Interface.OnMenuTouchListener;
import com.dlong.rep.dlroundmenuview.utils.DLMathUtils;
import com.dlong.rep.dlroundmenuview.utils.DrawableUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\u0018\u0000 `2\u00020\u0001:\u0001`B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u0010.\u001a\u00020/J\u0010\u00100\u001a\u00020/2\u0006\u00101\u001a\u000202H\u0015J\u0018\u00103\u001a\u00020/2\u0006\u00104\u001a\u00020\t2\u0006\u00105\u001a\u00020\tH\u0014J\u0010\u00106\u001a\u00020\u00172\u0006\u00107\u001a\u000208H\u0016J\u0010\u00109\u001a\u00020/2\b\u0010:\u001a\u0004\u0018\u00010;J\u000e\u0010<\u001a\u00020/2\u0006\u0010=\u001a\u00020\tJ\u000e\u0010>\u001a\u00020/2\u0006\u0010?\u001a\u00020\u0010J\u000e\u0010@\u001a\u00020/2\u0006\u0010=\u001a\u00020\tJ\u000e\u0010A\u001a\u00020/2\u0006\u0010=\u001a\u00020\tJ\u000e\u0010B\u001a\u00020/2\u0006\u0010C\u001a\u00020\u0010J\u000e\u0010D\u001a\u00020/2\u0006\u0010E\u001a\u00020\u0017J\u000e\u0010F\u001a\u00020/2\u0006\u0010G\u001a\u00020\u0017J\u000e\u0010H\u001a\u00020/2\u0006\u0010I\u001a\u00020\tJ\u0010\u0010J\u001a\u00020/2\b\u0010K\u001a\u0004\u0018\u00010\u001cJ\u001f\u0010L\u001a\u00020/2\u0017\u0010M\u001a\u0013\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020/0N¢\u0006\u0002\bOJ\u0010\u0010P\u001a\u00020/2\b\u0010Q\u001a\u0004\u0018\u00010 J\u0010\u0010R\u001a\u00020/2\b\u0010S\u001a\u0004\u0018\u00010+J\u000e\u0010T\u001a\u00020/2\u0006\u0010U\u001a\u00020\u0010J\u000e\u0010V\u001a\u00020/2\u0006\u0010W\u001a\u00020\u0010J\u0018\u0010X\u001a\u00020/2\u0006\u0010Y\u001a\u00020\t2\b\u0010:\u001a\u0004\u0018\u00010;J\u000e\u0010Z\u001a\u00020/2\u0006\u0010=\u001a\u00020\tJ\u000e\u0010[\u001a\u00020/2\u0006\u0010\\\u001a\u00020\tJ\u000e\u0010]\u001a\u00020/2\u0006\u0010=\u001a\u00020\tJ\u000e\u0010^\u001a\u00020/2\u0006\u0010=\u001a\u00020\tJ\u000e\u0010_\u001a\u00020/2\u0006\u0010C\u001a\u00020\u0010R\u000e\u0010\u000b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0017X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000R\u0016\u0010#\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0$X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010+X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000¨\u0006a"}, d2 = {"Lcom/dlong/rep/dlroundmenuview/DLRoundMenuView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "DL_DEFAULT_LONG_CLICK_TIME", "mCoreMenuDrawable", "Landroid/graphics/Bitmap;", "mCoreMenuNormalBackgroundColor", "mCoreMenuRoundRadius", "", "mCoreMenuSelectedBackgroundColor", "mCoreMenuStrokeColor", "mCoreMenuStrokeSize", "mCoreX", "mCoreY", "mHasCoreMenu", "", "mIsDrawLineToCenter", "mLongTimer", "Ljava/util/Timer;", "mMenuClickListener", "Lcom/dlong/rep/dlroundmenuview/Interface/OnMenuClickListener;", "mMenuListener", "Lcom/dlong/rep/dlroundmenuview/Interface/OnMenuListener;", "mMenuLongClickListener", "Lcom/dlong/rep/dlroundmenuview/Interface/OnMenuLongClickListener;", "mRoundMenuDeviationDegree", "mRoundMenuDistance", "mRoundMenuDrawableList", "Ljava/util/ArrayList;", "mRoundMenuNormalBackgroundColor", "mRoundMenuNumber", "mRoundMenuSelectedBackgroundColor", "mRoundMenuStrokeColor", "mRoundMenuStrokeSize", "mTouchListener", "Lcom/dlong/rep/dlroundmenuview/Interface/OnMenuTouchListener;", "mTouchState", "onClickState", "cancelTimer", "", "onDraw", "canvas", "Landroid/graphics/Canvas;", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "onTouchEvent", "event", "Landroid/view/MotionEvent;", "setCoreMenuDrawable", "drawable", "Landroid/graphics/drawable/Drawable;", "setCoreMenuNormalBackgroundColor", "color", "setCoreMenuRoundRadius", "radius", "setCoreMenuSelectedBackgroundColor", "setCoreMenuStrokeColor", "setCoreMenuStrokeSize", "size", "setHasCoreMenu", "hasCoreMenu", "setIsDrawLineToCenter", "is", "setLongClickTime", "millisecond", "setOnMenuClickListener", "onMenuClickListener", "setOnMenuListener", "listener", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "setOnMenuLongClickListener", "onMenuLongClickListener", "setOnMenuTouchListener", "onMenuTouchListener", "setRoundMenuDeviationDegree", "degree", "setRoundMenuDistance", "distance", "setRoundMenuDrawable", "index", "setRoundMenuNormalBackgroundColor", "setRoundMenuNumber", "number", "setRoundMenuSelectedBackgroundColor", "setRoundMenuStrokeColor", "setRoundMenuStrokeSize", "Companion", "dlroundmenuview_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: DLRoundMenuView.kt */
public final class DLRoundMenuView extends View {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int DL_TOUCH_CENTER = -1;
    public static final int DL_TOUCH_OUTSIDE = -2;
    private int DL_DEFAULT_LONG_CLICK_TIME;
    private HashMap _$_findViewCache;
    private Bitmap mCoreMenuDrawable;
    private int mCoreMenuNormalBackgroundColor;
    private float mCoreMenuRoundRadius;
    private int mCoreMenuSelectedBackgroundColor;
    private int mCoreMenuStrokeColor;
    private float mCoreMenuStrokeSize;
    private float mCoreX;
    private float mCoreY;
    private boolean mHasCoreMenu;
    private boolean mIsDrawLineToCenter;
    private Timer mLongTimer;
    private OnMenuClickListener mMenuClickListener;
    /* access modifiers changed from: private */
    public OnMenuListener mMenuListener;
    private OnMenuLongClickListener mMenuLongClickListener;
    private float mRoundMenuDeviationDegree;
    private float mRoundMenuDistance;
    private final ArrayList<Bitmap> mRoundMenuDrawableList;
    private int mRoundMenuNormalBackgroundColor;
    private int mRoundMenuNumber;
    private int mRoundMenuSelectedBackgroundColor;
    private int mRoundMenuStrokeColor;
    private float mRoundMenuStrokeSize;
    /* access modifiers changed from: private */
    public OnMenuTouchListener mTouchListener;
    /* access modifiers changed from: private */
    public int mTouchState;
    private int onClickState;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DLRoundMenuView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        float f;
        Context context2 = context;
        Intrinsics.checkParameterIsNotNull(context2, "context");
        this.mTouchState = -2;
        this.DL_DEFAULT_LONG_CLICK_TIME = 400;
        this.mRoundMenuDrawableList = new ArrayList<>();
        this.onClickState = -2;
        Resources resources = getResources();
        boolean z = resources.getBoolean(R.bool.default_has_core_menu);
        int color = ContextCompat.getColor(context2, R.color.default_core_menu_normal_background_color);
        int color2 = ContextCompat.getColor(context2, R.color.default_core_menu_stroke_color);
        float dimension = resources.getDimension(R.dimen.default_core_menu_stroke_size);
        int color3 = ContextCompat.getColor(context2, R.color.default_core_menu_selected_background_color);
        Drawable drawable = ContextCompat.getDrawable(context2, R.drawable.default_core_menu_drawable);
        float dimension2 = resources.getDimension(R.dimen.default_core_menu_round_radius);
        int integer = resources.getInteger(R.integer.default_round_menu_number);
        int integer2 = resources.getInteger(R.integer.default_round_menu_deviation_degree);
        Drawable drawable2 = ContextCompat.getDrawable(context2, R.drawable.default_round_menu_drawable);
        boolean z2 = resources.getBoolean(R.bool.default_is_draw_line_to_center);
        int color4 = ContextCompat.getColor(context2, R.color.default_round_menu_normal_background_color);
        int color5 = ContextCompat.getColor(context2, R.color.default_round_menu_selected_background_color);
        Drawable drawable3 = drawable;
        int color6 = ContextCompat.getColor(context2, R.color.default_round_menu_stroke_color);
        Drawable drawable4 = drawable2;
        float dimension3 = resources.getDimension(R.dimen.default_round_menu_stroke_size);
        int i2 = color6;
        try {
            f = resources.getFraction(R.fraction.default_round_menu_distance, 1, 1);
        } catch (Exception unused) {
            f = 0.7f;
        }
        TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, R.styleable.DLRoundMenuView);
        Intrinsics.checkExpressionValueIsNotNull(obtainStyledAttributes, "context.obtainStyledAttr…tyleable.DLRoundMenuView)");
        this.mHasCoreMenu = obtainStyledAttributes.getBoolean(R.styleable.DLRoundMenuView_RMHasCoreMenu, z);
        this.mCoreMenuNormalBackgroundColor = obtainStyledAttributes.getColor(R.styleable.DLRoundMenuView_RMCoreMenuNormalBackgroundColor, color);
        this.mCoreMenuStrokeColor = obtainStyledAttributes.getColor(R.styleable.DLRoundMenuView_RMCoreMenuStrokeColor, color2);
        this.mCoreMenuStrokeSize = obtainStyledAttributes.getDimension(R.styleable.DLRoundMenuView_RMCoreMenuStrokeSize, dimension);
        this.mCoreMenuSelectedBackgroundColor = obtainStyledAttributes.getColor(R.styleable.DLRoundMenuView_RMCoreMenuSelectedBackgroundColor, color3);
        this.mCoreMenuRoundRadius = obtainStyledAttributes.getDimension(R.styleable.DLRoundMenuView_RMCoreMenuRoundRadius, dimension2);
        this.mRoundMenuNumber = obtainStyledAttributes.getInteger(R.styleable.DLRoundMenuView_RMRoundMenuNumber, integer);
        this.mRoundMenuDeviationDegree = (float) obtainStyledAttributes.getInteger(R.styleable.DLRoundMenuView_RMRoundMenuDeviationDegree, integer2);
        this.mIsDrawLineToCenter = obtainStyledAttributes.getBoolean(R.styleable.DLRoundMenuView_RMIsDrawLineToCenter, z2);
        this.mRoundMenuNormalBackgroundColor = obtainStyledAttributes.getColor(R.styleable.DLRoundMenuView_RMRoundMenuNormalBackgroundColor, color4);
        this.mRoundMenuSelectedBackgroundColor = obtainStyledAttributes.getColor(R.styleable.DLRoundMenuView_RMRoundMenuSelectedBackgroundColor, color5);
        this.mRoundMenuStrokeColor = obtainStyledAttributes.getColor(R.styleable.DLRoundMenuView_RMRoundMenuStrokeColor, i2);
        this.mRoundMenuStrokeSize = obtainStyledAttributes.getDimension(R.styleable.DLRoundMenuView_RMRoundMenuStrokeSize, dimension3);
        this.mRoundMenuDistance = obtainStyledAttributes.getFraction(R.styleable.DLRoundMenuView_RMRoundMenuDistance, 1, 1, f);
        Drawable drawable5 = obtainStyledAttributes.getDrawable(R.styleable.DLRoundMenuView_RMCoreMenuDrawable);
        Drawable drawable6 = drawable5 != null ? drawable5 : drawable3;
        Bitmap bitmap = null;
        this.mCoreMenuDrawable = drawable6 != null ? DrawableUtils.drawableToBitmap(drawable6) : null;
        Drawable drawable7 = obtainStyledAttributes.getDrawable(R.styleable.DLRoundMenuView_RMRoundMenuDrawable);
        Drawable drawable8 = drawable7 != null ? drawable7 : drawable4;
        bitmap = drawable8 != null ? DrawableUtils.drawableToBitmap(drawable8) : bitmap;
        int i3 = this.mRoundMenuNumber;
        for (int i4 = 0; i4 < i3; i4++) {
            this.mRoundMenuDrawableList.add(i4, bitmap);
        }
        obtainStyledAttributes.recycle();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DLRoundMenuView(Context context) {
        this(context, (AttributeSet) null, 0);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DLRoundMenuView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/dlong/rep/dlroundmenuview/DLRoundMenuView$Companion;", "", "()V", "DL_TOUCH_CENTER", "", "DL_TOUCH_OUTSIDE", "dlroundmenuview_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: DLRoundMenuView.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(View.MeasureSpec.getSize(i) - 2, View.MeasureSpec.getSize(i2) - 2);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Canvas canvas2 = canvas;
        Intrinsics.checkParameterIsNotNull(canvas2, "canvas");
        float f = (float) 2;
        this.mCoreX = ((float) getWidth()) / f;
        this.mCoreY = ((float) getHeight()) / f;
        float f2 = this.mRoundMenuStrokeSize;
        RectF rectF = new RectF(f2, f2, ((float) getWidth()) - this.mRoundMenuStrokeSize, ((float) getHeight()) - this.mRoundMenuStrokeSize);
        int i = this.mRoundMenuNumber;
        if (i > 0) {
            float f3 = ((float) 360) / ((float) i);
            float f4 = (float) 90;
            float f5 = (this.mRoundMenuDeviationDegree - (f3 / f)) - f4;
            int i2 = 0;
            while (i2 < i) {
                Paint paint = new Paint();
                paint.setAntiAlias(true);
                paint.setColor(this.onClickState == i2 ? this.mRoundMenuSelectedBackgroundColor : this.mRoundMenuNormalBackgroundColor);
                float f6 = ((float) i2) * f3;
                RectF rectF2 = rectF;
                float f7 = f5 + f6;
                float f8 = f3;
                int i3 = i2;
                canvas.drawArc(rectF2, f7, f8, true, paint);
                Paint paint2 = new Paint();
                paint2.setAntiAlias(true);
                paint2.setStrokeWidth(this.mRoundMenuStrokeSize);
                paint2.setStyle(Paint.Style.STROKE);
                paint2.setColor(this.mRoundMenuStrokeColor);
                canvas.drawArc(rectF2, f7, f8, this.mIsDrawLineToCenter, paint2);
                Bitmap bitmap = this.mRoundMenuDrawableList.get(i3);
                if (bitmap != null) {
                    Matrix matrix = new Matrix();
                    matrix.postTranslate(((this.mCoreX + (((float) (getWidth() / 2)) * this.mRoundMenuDistance)) - this.mRoundMenuStrokeSize) - ((float) (bitmap.getWidth() / 2)), this.mCoreY - (((float) bitmap.getHeight()) / f));
                    matrix.postRotate((this.mRoundMenuDeviationDegree - f4) + f6, this.mCoreX, this.mCoreY);
                    canvas2.drawBitmap(bitmap, matrix, (Paint) null);
                }
                i2 = i3 + 1;
            }
        }
        if (this.mHasCoreMenu) {
            float f9 = this.mCoreX;
            float f10 = this.mCoreMenuRoundRadius;
            float f11 = this.mCoreY;
            RectF rectF3 = new RectF(f9 - f10, f11 - f10, f9 + f10, f11 + f10);
            Paint paint3 = new Paint();
            paint3.setAntiAlias(true);
            paint3.setStrokeWidth(this.mCoreMenuStrokeSize);
            paint3.setColor(this.onClickState == -1 ? this.mCoreMenuSelectedBackgroundColor : this.mCoreMenuNormalBackgroundColor);
            RectF rectF4 = rectF3;
            canvas.drawArc(rectF4, 0.0f, 360.0f, true, paint3);
            Paint paint4 = new Paint();
            paint4.setAntiAlias(true);
            paint4.setStrokeWidth(this.mCoreMenuStrokeSize);
            paint4.setStyle(Paint.Style.STROKE);
            paint4.setColor(this.mCoreMenuStrokeColor);
            canvas.drawArc(rectF4, 0.0f, 360.0f, true, paint4);
            Bitmap bitmap2 = this.mCoreMenuDrawable;
            if (bitmap2 != null) {
                if (bitmap2 == null) {
                    Intrinsics.throwNpe();
                }
                float f12 = this.mCoreX;
                Bitmap bitmap3 = this.mCoreMenuDrawable;
                if (bitmap3 == null) {
                    Intrinsics.throwNpe();
                }
                float width = f12 - (((float) bitmap3.getWidth()) / f);
                float f13 = this.mCoreY;
                Bitmap bitmap4 = this.mCoreMenuDrawable;
                if (bitmap4 == null) {
                    Intrinsics.throwNpe();
                }
                canvas2.drawBitmap(bitmap2, width, f13 - (((float) bitmap4.getHeight()) / f), (Paint) null);
            }
        }
    }

    public final void cancelTimer() {
        Timer timer = this.mLongTimer;
        if (timer != null) {
            if (timer != null) {
                timer.cancel();
            }
            this.mLongTimer = null;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        Intrinsics.checkParameterIsNotNull(motionEvent, "event");
        int action = motionEvent.getAction();
        if (action == 0) {
            DLRoundMenuView$onTouchEvent$task$1 dLRoundMenuView$onTouchEvent$task$1 = new DLRoundMenuView$onTouchEvent$task$1(this, motionEvent);
            cancelTimer();
            Timer timer = new Timer();
            this.mLongTimer = timer;
            timer.schedule(dLRoundMenuView$onTouchEvent$task$1, (long) this.DL_DEFAULT_LONG_CLICK_TIME, 15);
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            double distanceFromTwoSpot = DLMathUtils.getDistanceFromTwoSpot(this.mCoreX, this.mCoreY, x, y);
            if (distanceFromTwoSpot <= ((double) this.mCoreMenuRoundRadius)) {
                this.onClickState = -1;
                this.mTouchState = -1;
            } else if (distanceFromTwoSpot <= ((double) (getWidth() / 2))) {
                float f = ((float) 360) / ((float) this.mRoundMenuNumber);
                double d = (double) 360;
                int rotationBetweenLines = (int) (((((DLMathUtils.getRotationBetweenLines(this.mCoreX, this.mCoreY, x, y) + d) + ((double) (f / ((float) 2)))) - ((double) ((int) this.mRoundMenuDeviationDegree))) % d) / ((double) f));
                this.onClickState = rotationBetweenLines;
                if (rotationBetweenLines >= this.mRoundMenuNumber) {
                    this.onClickState = 0;
                }
                this.mTouchState = this.onClickState;
            } else {
                this.onClickState = -2;
            }
            OnMenuClickListener onMenuClickListener = this.mMenuClickListener;
            if (onMenuClickListener != null) {
                onMenuClickListener.OnMenuClick(this.onClickState);
            }
            OnMenuListener onMenuListener = this.mMenuListener;
            if (onMenuListener != null) {
                onMenuListener.OnMenuClick(this.onClickState);
            }
            invalidate();
        } else if (action == 1) {
            cancelTimer();
            this.onClickState = -2;
            invalidate();
        } else if (action == 3 || action == 4) {
            cancelTimer();
            this.onClickState = -2;
            invalidate();
        }
        return true;
    }

    public final void setOnMenuClickListener(OnMenuClickListener onMenuClickListener) {
        this.mMenuClickListener = onMenuClickListener;
    }

    public final void setOnMenuLongClickListener(OnMenuLongClickListener onMenuLongClickListener) {
        this.mMenuLongClickListener = onMenuLongClickListener;
    }

    public final void setOnMenuTouchListener(OnMenuTouchListener onMenuTouchListener) {
        this.mTouchListener = onMenuTouchListener;
    }

    public final void setOnMenuListener(Function1<? super OnMenuListener, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "listener");
        OnMenuListener onMenuListener = new OnMenuListener();
        function1.invoke(onMenuListener);
        this.mMenuListener = onMenuListener;
    }

    public final void setLongClickTime(int i) {
        this.DL_DEFAULT_LONG_CLICK_TIME = i;
        invalidate();
    }

    public final void setHasCoreMenu(boolean z) {
        this.mHasCoreMenu = z;
        invalidate();
    }

    public final void setCoreMenuNormalBackgroundColor(int i) {
        this.mCoreMenuNormalBackgroundColor = i;
        invalidate();
    }

    public final void setCoreMenuSelectedBackgroundColor(int i) {
        this.mCoreMenuSelectedBackgroundColor = i;
        invalidate();
    }

    public final void setCoreMenuStrokeColor(int i) {
        this.mCoreMenuStrokeColor = i;
        invalidate();
    }

    public final void setCoreMenuStrokeSize(float f) {
        this.mCoreMenuStrokeSize = f;
        invalidate();
    }

    public final void setCoreMenuRoundRadius(float f) {
        this.mCoreMenuRoundRadius = f;
        invalidate();
    }

    public final void setCoreMenuDrawable(Drawable drawable) {
        this.mCoreMenuDrawable = DrawableUtils.drawableToBitmap(drawable);
        invalidate();
    }

    public final void setRoundMenuNumber(int i) {
        this.mRoundMenuNumber = i;
        invalidate();
    }

    public final void setRoundMenuDeviationDegree(float f) {
        this.mRoundMenuDeviationDegree = f;
        invalidate();
    }

    public final void setRoundMenuDrawable(int i, Drawable drawable) {
        if (i >= 0 && i <= this.mRoundMenuNumber) {
            this.mRoundMenuDrawableList.set(i, DrawableUtils.drawableToBitmap(drawable));
            invalidate();
        }
    }

    public final void setRoundMenuNormalBackgroundColor(int i) {
        this.mRoundMenuNormalBackgroundColor = i;
        invalidate();
    }

    public final void setRoundMenuSelectedBackgroundColor(int i) {
        this.mRoundMenuSelectedBackgroundColor = i;
        invalidate();
    }

    public final void setRoundMenuStrokeColor(int i) {
        this.mRoundMenuStrokeColor = i;
        invalidate();
    }

    public final void setRoundMenuStrokeSize(float f) {
        this.mRoundMenuStrokeSize = f;
        invalidate();
    }

    public final void setRoundMenuDistance(float f) {
        if (f <= ((float) 1)) {
            this.mRoundMenuDistance = f;
            invalidate();
        }
    }

    public final void setIsDrawLineToCenter(boolean z) {
        this.mIsDrawLineToCenter = z;
        invalidate();
    }
}
