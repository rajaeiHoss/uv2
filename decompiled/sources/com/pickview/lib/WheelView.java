package com.pickview.lib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.pickview.adapter.WheelAdapter;
import com.pickview.listener.OnItemSelectedListener;
import com.zycs.UView.R;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class WheelView extends View {
    private static final float CENTERCONTENTOFFSET = 6.0f;
    private static final String GETPICKERVIEWTEXT = "getPickerViewText";
    private static final float SCALECONTENT = 0.8f;
    private static final int VELOCITYFLING = 5;
    static final float lineSpacingMultiplier = 1.4f;
    WheelAdapter adapter;
    float centerY;
    int change;
    Context context;
    boolean customTextSize;
    int dividerColor;
    private int drawCenterContentStart;
    private int drawOutContentStart;
    float firstLineY;
    private GestureDetector gestureDetector;
    int halfCircumference;
    Handler handler;
    int initPosition;
    boolean isLoop;
    float itemHeight;
    int itemsVisible;
    private String label;
    ScheduledExecutorService mExecutor;
    private ScheduledFuture<?> mFuture;
    private int mGravity;
    private int mOffset;
    int maxTextHeight;
    int maxTextWidth;
    int measuredHeight;
    int measuredWidth;
    OnItemSelectedListener onItemSelectedListener;
    Paint paintCenterText;
    Paint paintIndicator;
    Paint paintOuterText;
    int preCurrentIndex;
    private float previousY;
    int radius;
    float secondLineY;
    private int selectedItem;
    long startTime;
    int textColorCenter;
    int textColorOut;
    int textSize;
    int totalScrollY;
    int widthMeasureSpec;

    public enum ACTION {
        CLICK,
        FLING,
        DAGGLE
    }

    public WheelView(Context context2) {
        this(context2, (AttributeSet) null);
    }

    public WheelView(Context context2, AttributeSet attributeSet) {
        super(context2, attributeSet);
        this.mExecutor = Executors.newSingleThreadScheduledExecutor();
        this.itemsVisible = 11;
        this.mOffset = 0;
        this.previousY = 0.0f;
        this.startTime = 0;
        this.mGravity = 17;
        this.drawCenterContentStart = 0;
        this.drawOutContentStart = 0;
        this.textColorOut = getResources().getColor(R.color.pickerview_wheelview_textcolor_out);
        this.textColorCenter = getResources().getColor(R.color.pickerview_wheelview_textcolor_center);
        this.dividerColor = getResources().getColor(R.color.pickerview_wheelview_textcolor_divider);
        this.textSize = getResources().getDimensionPixelSize(R.dimen.pickerview_textsize);
        this.customTextSize = getResources().getBoolean(R.bool.pickerview_customTextSize);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, R.styleable.wheelview, 0, 0);
            this.mGravity = obtainStyledAttributes.getInt(1, 17);
            this.textColorOut = obtainStyledAttributes.getColor(3, this.textColorOut);
            this.textColorCenter = obtainStyledAttributes.getColor(2, this.textColorCenter);
            this.dividerColor = obtainStyledAttributes.getColor(0, this.dividerColor);
            this.textSize = obtainStyledAttributes.getDimensionPixelOffset(4, this.textSize);
        }
        initLoopView(context2);
    }

    private void initLoopView(Context context2) {
        this.context = context2;
        this.handler = new MessageHandler(this);
        GestureDetector gestureDetector2 = new GestureDetector(context2, new LoopViewGestureListener(this));
        this.gestureDetector = gestureDetector2;
        gestureDetector2.setIsLongpressEnabled(false);
        this.isLoop = true;
        this.totalScrollY = 0;
        this.initPosition = -1;
        initPaints();
    }

    private void initPaints() {
        Paint paint = new Paint();
        this.paintOuterText = paint;
        paint.setColor(this.textColorOut);
        this.paintOuterText.setAntiAlias(true);
        this.paintOuterText.setTypeface(Typeface.MONOSPACE);
        this.paintOuterText.setTextSize((float) this.textSize);
        Paint paint2 = new Paint();
        this.paintCenterText = paint2;
        paint2.setColor(this.textColorCenter);
        this.paintCenterText.setAntiAlias(true);
        this.paintCenterText.setTextScaleX(1.1f);
        this.paintCenterText.setTypeface(Typeface.MONOSPACE);
        this.paintCenterText.setTextSize((float) this.textSize);
        Paint paint3 = new Paint();
        this.paintIndicator = paint3;
        paint3.setColor(this.dividerColor);
        this.paintIndicator.setAntiAlias(true);
        if (Build.VERSION.SDK_INT >= 11) {
            setLayerType(1, (Paint) null);
        }
    }

    private void remeasure() {
        if (this.adapter != null) {
            measureTextWidthHeight();
            int i = (int) (this.itemHeight * ((float) (this.itemsVisible - 1)));
            this.halfCircumference = i;
            this.measuredHeight = (int) (((double) (i * 2)) / 3.141592653589793d);
            this.radius = (int) (((double) i) / 3.141592653589793d);
            this.measuredWidth = View.MeasureSpec.getSize(this.widthMeasureSpec);
            int i2 = this.measuredHeight;
            float f = this.itemHeight;
            this.firstLineY = (((float) i2) - f) / 2.0f;
            this.secondLineY = (((float) i2) + f) / 2.0f;
            this.centerY = (((float) (i2 + this.maxTextHeight)) / 2.0f) - CENTERCONTENTOFFSET;
            if (this.initPosition == -1) {
                if (this.isLoop) {
                    this.initPosition = (this.adapter.getItemsCount() + 1) / 2;
                } else {
                    this.initPosition = 0;
                }
            }
            this.preCurrentIndex = this.initPosition;
        }
    }

    private void measureTextWidthHeight() {
        Rect rect = new Rect();
        for (int i = 0; i < this.adapter.getItemsCount(); i++) {
            String contentText = getContentText(this.adapter.getItem(i));
            this.paintCenterText.getTextBounds(contentText, 0, contentText.length(), rect);
            int width = rect.width();
            if (width > this.maxTextWidth) {
                this.maxTextWidth = width;
            }
            this.paintCenterText.getTextBounds("星期", 0, 2, rect);
            int height = rect.height();
            if (height > this.maxTextHeight) {
                this.maxTextHeight = height;
            }
        }
        this.itemHeight = ((float) this.maxTextHeight) * lineSpacingMultiplier;
    }

    /* access modifiers changed from: package-private */
    public void smoothScroll(ACTION action) {
        cancelFuture();
        if (action == ACTION.FLING || action == ACTION.DAGGLE) {
            float f = this.itemHeight;
            int i = (int) (((((float) this.totalScrollY) % f) + f) % f);
            this.mOffset = i;
            if (((float) i) > f / 2.0f) {
                this.mOffset = (int) (f - ((float) i));
            } else {
                this.mOffset = -i;
            }
        }
        this.mFuture = this.mExecutor.scheduleWithFixedDelay(new SmoothScrollTimerTask(this, this.mOffset), 0, 10, TimeUnit.MILLISECONDS);
    }

    /* access modifiers changed from: protected */
    public final void scrollBy(float f) {
        cancelFuture();
        this.mFuture = this.mExecutor.scheduleWithFixedDelay(new InertiaTimerTask(this, f), 0, 5, TimeUnit.MILLISECONDS);
    }

    public void cancelFuture() {
        ScheduledFuture<?> scheduledFuture = this.mFuture;
        if (scheduledFuture != null && !scheduledFuture.isCancelled()) {
            this.mFuture.cancel(true);
            this.mFuture = null;
        }
    }

    public final void setCyclic(boolean z) {
        this.isLoop = z;
    }

    public final void setTextSize(float f) {
        if (f > 0.0f && !this.customTextSize) {
            int i = (int) (this.context.getResources().getDisplayMetrics().density * f);
            this.textSize = i;
            this.paintOuterText.setTextSize((float) i);
            this.paintCenterText.setTextSize((float) this.textSize);
        }
    }

    public final void setCurrentItem(int i) {
        this.initPosition = i;
        this.totalScrollY = 0;
        invalidate();
    }

    public final void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener2) {
        this.onItemSelectedListener = onItemSelectedListener2;
    }

    public final void setAdapter(WheelAdapter wheelAdapter) {
        this.adapter = wheelAdapter;
        remeasure();
        invalidate();
    }

    public final WheelAdapter getAdapter() {
        return this.adapter;
    }

    public final int getCurrentItem() {
        return this.selectedItem;
    }

    /* access modifiers changed from: protected */
    public final void onItemSelected() {
        if (this.onItemSelectedListener != null) {
            postDelayed(new OnItemSelectedRunnable(this), 200);
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int i;
        Canvas canvas2 = canvas;
        WheelAdapter wheelAdapter = this.adapter;
        if (wheelAdapter != null) {
            Object[] objArr = new Object[this.itemsVisible];
            int i2 = (int) (((float) this.totalScrollY) / this.itemHeight);
            this.change = i2;
            try {
                this.preCurrentIndex = this.initPosition + (i2 % wheelAdapter.getItemsCount());
            } catch (ArithmeticException unused) {
                System.out.println("出错了！adapter.getItemsCount() == 0，联动数据不匹配");
            }
            if (!this.isLoop) {
                if (this.preCurrentIndex < 0) {
                    this.preCurrentIndex = 0;
                }
                if (this.preCurrentIndex > this.adapter.getItemsCount() - 1) {
                    this.preCurrentIndex = this.adapter.getItemsCount() - 1;
                }
            } else {
                if (this.preCurrentIndex < 0) {
                    this.preCurrentIndex = this.adapter.getItemsCount() + this.preCurrentIndex;
                }
                if (this.preCurrentIndex > this.adapter.getItemsCount() - 1) {
                    this.preCurrentIndex -= this.adapter.getItemsCount();
                }
            }
            int i3 = (int) (((float) this.totalScrollY) % this.itemHeight);
            int i4 = 0;
            while (true) {
                int i5 = this.itemsVisible;
                if (i4 >= i5) {
                    break;
                }
                int i6 = this.preCurrentIndex - ((i5 / 2) - i4);
                if (this.isLoop) {
                    objArr[i4] = this.adapter.getItem(getLoopMappingIndex(i6));
                } else if (i6 < 0) {
                    objArr[i4] = "";
                } else if (i6 > this.adapter.getItemsCount() - 1) {
                    objArr[i4] = "";
                } else {
                    objArr[i4] = this.adapter.getItem(i6);
                }
                i4++;
            }
            float f = this.firstLineY;
            canvas.drawLine(0.0f, f, (float) this.measuredWidth, f, this.paintIndicator);
            float f2 = this.secondLineY;
            canvas.drawLine(0.0f, f2, (float) this.measuredWidth, f2, this.paintIndicator);
            String str = this.label;
            if (str != null) {
                canvas2.drawText(this.label, ((float) (this.measuredWidth - getTextWidth(this.paintCenterText, str))) - CENTERCONTENTOFFSET, this.centerY, this.paintCenterText);
            }
            int i7 = 0;
            while (i7 < this.itemsVisible) {
                canvas.save();
                float f3 = ((float) this.maxTextHeight) * lineSpacingMultiplier;
                double d = (((double) ((((float) i7) * f3) - ((float) i3))) * 3.141592653589793d) / ((double) this.halfCircumference);
                float f4 = (float) (90.0d - ((d / 3.141592653589793d) * 180.0d));
                if (f4 >= 90.0f || f4 <= -90.0f) {
                    i = i3;
                    canvas.restore();
                } else {
                    String contentText = getContentText(objArr[i7]);
                    measuredCenterContentStart(contentText);
                    measuredOutContentStart(contentText);
                    i = i3;
                    float cos = (float) ((((double) this.radius) - (Math.cos(d) * ((double) this.radius))) - ((Math.sin(d) * ((double) this.maxTextHeight)) / 2.0d));
                    canvas2.translate(0.0f, cos);
                    canvas2.scale(1.0f, (float) Math.sin(d));
                    float f5 = this.firstLineY;
                    if (cos > f5 || ((float) this.maxTextHeight) + cos < f5) {
                        float f6 = this.secondLineY;
                        if (cos <= f6 && ((float) this.maxTextHeight) + cos >= f6) {
                            canvas.save();
                            canvas2.clipRect(0.0f, 0.0f, (float) this.measuredWidth, this.secondLineY - cos);
                            canvas2.scale(1.0f, ((float) Math.sin(d)) * 1.0f);
                            canvas2.drawText(contentText, (float) this.drawCenterContentStart, ((float) this.maxTextHeight) - CENTERCONTENTOFFSET, this.paintCenterText);
                            canvas.restore();
                            canvas.save();
                            canvas2.clipRect(0.0f, this.secondLineY - cos, (float) this.measuredWidth, (float) ((int) f3));
                            canvas2.scale(1.0f, ((float) Math.sin(d)) * SCALECONTENT);
                            canvas2.drawText(contentText, (float) this.drawOutContentStart, (float) this.maxTextHeight, this.paintOuterText);
                            canvas.restore();
                        } else if (cos < f5 || ((float) this.maxTextHeight) + cos > f6) {
                            canvas.save();
                            canvas2.clipRect(0, 0, this.measuredWidth, (int) f3);
                            canvas2.scale(1.0f, ((float) Math.sin(d)) * SCALECONTENT);
                            canvas2.drawText(contentText, (float) this.drawOutContentStart, (float) this.maxTextHeight, this.paintOuterText);
                            canvas.restore();
                            canvas.restore();
                        } else {
                            canvas2.clipRect(0, 0, this.measuredWidth, (int) f3);
                            canvas2.drawText(contentText, (float) this.drawCenterContentStart, ((float) this.maxTextHeight) - CENTERCONTENTOFFSET, this.paintCenterText);
                            int indexOf = this.adapter.indexOf(objArr[i7]);
                            if (indexOf != -1) {
                                this.selectedItem = indexOf;
                            }
                        }
                    } else {
                        canvas.save();
                        canvas2.clipRect(0.0f, 0.0f, (float) this.measuredWidth, this.firstLineY - cos);
                        canvas2.scale(1.0f, ((float) Math.sin(d)) * SCALECONTENT);
                        canvas2.drawText(contentText, (float) this.drawOutContentStart, (float) this.maxTextHeight, this.paintOuterText);
                        canvas.restore();
                        canvas.save();
                        canvas2.clipRect(0.0f, this.firstLineY - cos, (float) this.measuredWidth, (float) ((int) f3));
                        canvas2.scale(1.0f, ((float) Math.sin(d)) * 1.0f);
                        canvas2.drawText(contentText, (float) this.drawCenterContentStart, ((float) this.maxTextHeight) - CENTERCONTENTOFFSET, this.paintCenterText);
                        canvas.restore();
                    }
                    canvas.restore();
                }
                i7++;
                i3 = i;
            }
        }
    }

    private int getLoopMappingIndex(int i) {
        if (i < 0) {
            return getLoopMappingIndex(i + this.adapter.getItemsCount());
        }
        return i > this.adapter.getItemsCount() + -1 ? getLoopMappingIndex(i - this.adapter.getItemsCount()) : i;
    }

    private String getContentText(Object obj) {
        try {
            return obj.getClass().getMethod(GETPICKERVIEWTEXT, new Class[0]).invoke(obj, new Object[0]).toString();
        } catch (Exception unused) {
            return obj.toString();
        }
    }

    private void measuredCenterContentStart(String str) {
        Rect rect = new Rect();
        this.paintCenterText.getTextBounds(str, 0, str.length(), rect);
        int i = this.mGravity;
        if (i == 3) {
            this.drawCenterContentStart = getPaddingLeft() + 0;
        } else if (i == 5) {
            this.drawCenterContentStart = this.measuredWidth - rect.width();
        } else if (i == 17) {
            this.drawCenterContentStart = (int) (((double) (this.measuredWidth - rect.width())) * 0.5d);
        }
    }

    private void measuredOutContentStart(String str) {
        Rect rect = new Rect();
        this.paintOuterText.getTextBounds(str, 0, str.length(), rect);
        int i = this.mGravity;
        if (i == 3) {
            this.drawOutContentStart = getPaddingLeft() + 0;
        } else if (i == 5) {
            this.drawOutContentStart = this.measuredWidth - rect.width();
        } else if (i == 17) {
            this.drawOutContentStart = (int) (((double) (this.measuredWidth - rect.width())) * 0.5d);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        this.widthMeasureSpec = i;
        remeasure();
        setMeasuredDimension(this.measuredWidth, this.measuredHeight);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean onTouchEvent = this.gestureDetector.onTouchEvent(motionEvent);
        int action = motionEvent.getAction();
        if (action == 0) {
            this.startTime = System.currentTimeMillis();
            cancelFuture();
            this.previousY = motionEvent.getRawY();
        } else if (action == 2) {
            float rawY = this.previousY - motionEvent.getRawY();
            this.previousY = motionEvent.getRawY();
            this.totalScrollY = (int) (((float) this.totalScrollY) + rawY);
            if (!this.isLoop) {
                float f = ((float) (-this.initPosition)) * this.itemHeight;
                float f2 = this.itemHeight;
                float itemsCount = ((float) ((this.adapter.getItemsCount() - 1) - this.initPosition)) * f2;
                int i = this.totalScrollY;
                if (((double) i) - (((double) f2) * 0.3d) < ((double) f)) {
                    f = ((float) i) - rawY;
                } else if (((double) i) + (((double) f2) * 0.3d) > ((double) itemsCount)) {
                    itemsCount = ((float) i) - rawY;
                }
                if (((float) i) < f) {
                    this.totalScrollY = (int) f;
                } else if (((float) i) > itemsCount) {
                    this.totalScrollY = (int) itemsCount;
                }
            }
        } else if (!onTouchEvent) {
            float y = motionEvent.getY();
            int i2 = this.radius;
            float f3 = this.itemHeight;
            this.mOffset = (int) ((((float) (((int) (((Math.acos((double) ((((float) i2) - y) / ((float) i2))) * ((double) this.radius)) + ((double) (f3 / 2.0f))) / ((double) f3))) - (this.itemsVisible / 2))) * f3) - (((((float) this.totalScrollY) % f3) + f3) % f3));
            if (System.currentTimeMillis() - this.startTime > 120) {
                smoothScroll(ACTION.DAGGLE);
            } else {
                smoothScroll(ACTION.CLICK);
            }
        }
        invalidate();
        return true;
    }

    public int getItemsCount() {
        WheelAdapter wheelAdapter = this.adapter;
        if (wheelAdapter != null) {
            return wheelAdapter.getItemsCount();
        }
        return 0;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public void setGravity(int i) {
        this.mGravity = i;
    }

    public int getTextWidth(Paint paint, String str) {
        if (str == null || str.length() <= 0) {
            return 0;
        }
        int length = str.length();
        float[] fArr = new float[length];
        paint.getTextWidths(str, fArr);
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            i += (int) Math.ceil((double) fArr[i2]);
        }
        return i;
    }
}
