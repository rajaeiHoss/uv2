package com.amo.demo.wheelview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Message;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import com.zycs.UView.R;
import java.util.LinkedList;
import java.util.List;

public class WheelView extends View {
    private static final int ADDITIONAL_ITEMS_SPACE = 10;
    private static final int ADDITIONAL_ITEM_HEIGHT = 15;
    private static final int DEF_VISIBLE_ITEMS = 5;
    private static final int ITEMS_TEXT_COLOR = -16777216;
    private static final int LABEL_OFFSET = 8;
    private static final int MIN_DELTA_FOR_SCROLLING = 1;
    private static final int PADDING = 10;
    private static final int SCROLLING_DURATION = 400;
    private static final int[] SHADOWS_COLORS = {-15658735, 11184810, 11184810};
    private static final int VALUE_TEXT_COLOR = -268435456;
    private final int ITEM_OFFSET = (this.TEXT_SIZE / 5);
    private final int MESSAGE_JUSTIFY = 1;
    private final int MESSAGE_SCROLL = 0;
    public int TEXT_SIZE;
    /* access modifiers changed from: private */
    public WheelAdapter adapter = null;
    /* access modifiers changed from: private */
    public Handler animationHandler = new Handler() {
        public void handleMessage(Message message) {
            WheelView.this.scroller.computeScrollOffset();
            int currY = WheelView.this.scroller.getCurrY();
            int access$500 = WheelView.this.lastScrollY - currY;
            int unused = WheelView.this.lastScrollY = currY;
            if (access$500 != 0) {
                WheelView.this.doScroll(access$500);
            }
            if (Math.abs(currY - WheelView.this.scroller.getFinalY()) < 1) {
                WheelView.this.scroller.getFinalY();
                WheelView.this.scroller.forceFinished(true);
            }
            if (!WheelView.this.scroller.isFinished()) {
                WheelView.this.animationHandler.sendEmptyMessage(message.what);
            } else if (message.what == 0) {
                WheelView.this.justify();
            } else {
                WheelView.this.finishScrolling();
            }
        }
    };
    private GradientDrawable bottomShadow;
    private Drawable centerDrawable;
    private List<OnWheelChangedListener> changingListeners = new LinkedList();
    /* access modifiers changed from: private */
    public int currentItem = 0;
    private GestureDetector gestureDetector;
    private GestureDetector.SimpleOnGestureListener gestureListener = new GestureDetector.SimpleOnGestureListener() {
        public boolean onDown(MotionEvent motionEvent) {
            if (!WheelView.this.isScrollingPerformed) {
                return false;
            }
            WheelView.this.scroller.forceFinished(true);
            WheelView.this.clearMessages();
            return true;
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            WheelView.this.startScrolling();
            WheelView.this.doScroll((int) (-f2));
            return true;
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            int i;
            WheelView wheelView = WheelView.this;
            int unused = wheelView.lastScrollY = (wheelView.currentItem * WheelView.this.getItemHeight()) + WheelView.this.scrollingOffset;
            if (WheelView.this.isCyclic) {
                i = Integer.MAX_VALUE;
            } else {
                i = WheelView.this.adapter.getItemsCount() * WheelView.this.getItemHeight();
            }
            WheelView.this.scroller.fling(0, WheelView.this.lastScrollY, 0, ((int) (-f2)) / 2, 0, 0, WheelView.this.isCyclic ? -i : 0, i);
            WheelView.this.setNextMessage(0);
            return true;
        }
    };
    boolean isCyclic = false;
    /* access modifiers changed from: private */
    public boolean isScrollingPerformed;
    private int itemHeight = 0;
    private StaticLayout itemsLayout;
    private TextPaint itemsPaint;
    private int itemsWidth = 0;
    private String label;
    private StaticLayout labelLayout;
    private int labelWidth = 0;
    /* access modifiers changed from: private */
    public int lastScrollY;
    /* access modifiers changed from: private */
    public Scroller scroller;
    private List<OnWheelScrollListener> scrollingListeners = new LinkedList();
    /* access modifiers changed from: private */
    public int scrollingOffset;
    private GradientDrawable topShadow;
    private StaticLayout valueLayout;
    private TextPaint valuePaint;
    private int visibleItems = 5;

    public WheelView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initData(context);
    }

    public WheelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initData(context);
    }

    public WheelView(Context context) {
        super(context);
        initData(context);
    }

    private void initData(Context context) {
        GestureDetector gestureDetector2 = new GestureDetector(context, this.gestureListener);
        this.gestureDetector = gestureDetector2;
        gestureDetector2.setIsLongpressEnabled(false);
        this.scroller = new Scroller(context);
    }

    public WheelAdapter getAdapter() {
        return this.adapter;
    }

    public void setAdapter(WheelAdapter wheelAdapter) {
        this.adapter = wheelAdapter;
        invalidateLayouts();
        invalidate();
    }

    public void setInterpolator(Interpolator interpolator) {
        this.scroller.forceFinished(true);
        this.scroller = new Scroller(getContext(), interpolator);
    }

    public int getVisibleItems() {
        return this.visibleItems;
    }

    public void setVisibleItems(int i) {
        this.visibleItems = i;
        invalidate();
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String str) {
        String str2 = this.label;
        if (str2 == null || !str2.equals(str)) {
            this.label = str;
            this.labelLayout = null;
            invalidate();
        }
    }

    public void addChangingListener(OnWheelChangedListener onWheelChangedListener) {
        this.changingListeners.add(onWheelChangedListener);
    }

    public void removeChangingListener(OnWheelChangedListener onWheelChangedListener) {
        this.changingListeners.remove(onWheelChangedListener);
    }

    /* access modifiers changed from: protected */
    public void notifyChangingListeners(int i, int i2) {
        for (OnWheelChangedListener onChanged : this.changingListeners) {
            onChanged.onChanged(this, i, i2);
        }
    }

    public void addScrollingListener(OnWheelScrollListener onWheelScrollListener) {
        this.scrollingListeners.add(onWheelScrollListener);
    }

    public void removeScrollingListener(OnWheelScrollListener onWheelScrollListener) {
        this.scrollingListeners.remove(onWheelScrollListener);
    }

    /* access modifiers changed from: protected */
    public void notifyScrollingListenersAboutStart() {
        for (OnWheelScrollListener onScrollingStarted : this.scrollingListeners) {
            onScrollingStarted.onScrollingStarted(this);
        }
    }

    /* access modifiers changed from: protected */
    public void notifyScrollingListenersAboutEnd() {
        for (OnWheelScrollListener onScrollingFinished : this.scrollingListeners) {
            onScrollingFinished.onScrollingFinished(this);
        }
    }

    public int getCurrentItem() {
        return this.currentItem;
    }

    public void setCurrentItem(int i, boolean z) {
        WheelAdapter wheelAdapter = this.adapter;
        if (wheelAdapter != null && wheelAdapter.getItemsCount() != 0) {
            if (i < 0 || i >= this.adapter.getItemsCount()) {
                if (this.isCyclic) {
                    while (i < 0) {
                        i += this.adapter.getItemsCount();
                    }
                    i %= this.adapter.getItemsCount();
                } else {
                    return;
                }
            }
            int i2 = this.currentItem;
            if (i == i2) {
                return;
            }
            if (z) {
                scroll(i - i2, SCROLLING_DURATION);
                return;
            }
            invalidateLayouts();
            int i3 = this.currentItem;
            this.currentItem = i;
            notifyChangingListeners(i3, i);
            invalidate();
        }
    }

    public void setCurrentItem(int i) {
        setCurrentItem(i, false);
    }

    public boolean isCyclic() {
        return this.isCyclic;
    }

    public void setCyclic(boolean z) {
        this.isCyclic = z;
        invalidate();
        invalidateLayouts();
    }

    private void invalidateLayouts() {
        this.itemsLayout = null;
        this.valueLayout = null;
        this.scrollingOffset = 0;
    }

    private void initResourcesIfNecessary() {
        if (this.itemsPaint == null) {
            TextPaint textPaint = new TextPaint(33);
            this.itemsPaint = textPaint;
            textPaint.setTextSize((float) this.TEXT_SIZE);
        }
        if (this.valuePaint == null) {
            TextPaint textPaint2 = new TextPaint(37);
            this.valuePaint = textPaint2;
            textPaint2.setTextSize((float) this.TEXT_SIZE);
            this.valuePaint.setShadowLayer(0.1f, 0.0f, 0.1f, -4144960);
        }
        if (this.centerDrawable == null) {
            this.centerDrawable = getContext().getResources().getDrawable(R.drawable.wheel_val);
        }
        if (this.topShadow == null) {
            this.topShadow = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, SHADOWS_COLORS);
        }
        if (this.bottomShadow == null) {
            this.bottomShadow = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, SHADOWS_COLORS);
        }
        setBackgroundResource(R.drawable.wheel_bg);
    }

    private int getDesiredHeight(Layout layout) {
        if (layout == null) {
            return 0;
        }
        return Math.max(((getItemHeight() * this.visibleItems) - (this.ITEM_OFFSET * 2)) - 15, getSuggestedMinimumHeight());
    }

    private String getTextItem(int i) {
        WheelAdapter wheelAdapter = this.adapter;
        if (wheelAdapter == null || wheelAdapter.getItemsCount() == 0) {
            return null;
        }
        int itemsCount = this.adapter.getItemsCount();
        if ((i < 0 || i >= itemsCount) && !this.isCyclic) {
            return null;
        }
        while (i < 0) {
            i += itemsCount;
        }
        return this.adapter.getItem(i % itemsCount);
    }

    private String buildText(boolean z) {
        String textItem;
        StringBuilder sb = new StringBuilder();
        int i = (this.visibleItems / 2) + 1;
        int i2 = this.currentItem - i;
        while (true) {
            int i3 = this.currentItem;
            if (i2 > i3 + i) {
                return sb.toString();
            }
            if ((z || i2 != i3) && (textItem = getTextItem(i2)) != null) {
                sb.append(textItem);
            }
            if (i2 < this.currentItem + i) {
                sb.append("\n");
            }
            i2++;
        }
    }

    private int getMaxTextLength() {
        WheelAdapter adapter2 = getAdapter();
        if (adapter2 == null) {
            return 0;
        }
        int maximumLength = adapter2.getMaximumLength();
        if (maximumLength > 0) {
            return maximumLength;
        }
        String str = null;
        for (int max = Math.max(this.currentItem - (this.visibleItems / 2), 0); max < Math.min(this.currentItem + this.visibleItems, adapter2.getItemsCount()); max++) {
            String item = adapter2.getItem(max);
            if (item != null && (str == null || str.length() < item.length())) {
                str = item;
            }
        }
        if (str != null) {
            return str.length();
        }
        return 0;
    }

    /* access modifiers changed from: private */
    public int getItemHeight() {
        int i = this.itemHeight;
        if (i != 0) {
            return i;
        }
        StaticLayout staticLayout = this.itemsLayout;
        if (staticLayout == null || staticLayout.getLineCount() <= 2) {
            return getHeight() / this.visibleItems;
        }
        int lineTop = this.itemsLayout.getLineTop(2) - this.itemsLayout.getLineTop(1);
        this.itemHeight = lineTop;
        return lineTop;
    }

    private int calculateLayoutWidth(int i, int i2) {
        initResourcesIfNecessary();
        int maxTextLength = getMaxTextLength();
        if (maxTextLength > 0) {
            this.itemsWidth = (int) (((float) maxTextLength) * ((float) Math.ceil((double) Layout.getDesiredWidth("0", this.itemsPaint))));
        } else {
            this.itemsWidth = 0;
        }
        this.itemsWidth += 10;
        this.labelWidth = 0;
        String str = this.label;
        if (str != null && str.length() > 0) {
            this.labelWidth = (int) Math.ceil((double) Layout.getDesiredWidth(this.label, this.valuePaint));
        }
        boolean z = true;
        if (i2 != 1073741824) {
            int i3 = this.itemsWidth;
            int i4 = this.labelWidth;
            int i5 = i3 + i4 + 20;
            if (i4 > 0) {
                i5 += 8;
            }
            int max = Math.max(i5, getSuggestedMinimumWidth());
            if (i2 != Integer.MIN_VALUE || i >= max) {
                i = max;
                z = false;
            }
        }
        if (z) {
            int i6 = (i - 8) - 20;
            if (i6 <= 0) {
                this.labelWidth = 0;
                this.itemsWidth = 0;
            }
            int i7 = this.labelWidth;
            if (i7 > 0) {
                int i8 = this.itemsWidth;
                int i9 = (int) ((((double) i8) * ((double) i6)) / ((double) (i8 + i7)));
                this.itemsWidth = i9;
                this.labelWidth = i6 - i9;
            } else {
                this.itemsWidth = i6 + 8;
            }
        }
        int i10 = this.itemsWidth;
        if (i10 > 0) {
            createLayouts(i10, this.labelWidth);
        }
        return i;
    }

    private void createLayouts(int i, int i2) {
        StaticLayout staticLayout;
        StaticLayout staticLayout2 = this.itemsLayout;
        if (staticLayout2 == null || staticLayout2.getWidth() > i) {
            this.itemsLayout = new StaticLayout(buildText(this.isScrollingPerformed), this.itemsPaint, i, i2 > 0 ? Layout.Alignment.ALIGN_OPPOSITE : Layout.Alignment.ALIGN_CENTER, 1.0f, 15.0f, false);
        } else {
            this.itemsLayout.increaseWidthTo(i);
        }
        String str = null;
        if (!this.isScrollingPerformed && ((staticLayout = this.valueLayout) == null || staticLayout.getWidth() > i)) {
            if (getAdapter() != null) {
                str = getAdapter().getItem(this.currentItem);
            }
            if (str == null) {
                str = "";
            }
            this.valueLayout = new StaticLayout(str, this.valuePaint, i, i2 > 0 ? Layout.Alignment.ALIGN_OPPOSITE : Layout.Alignment.ALIGN_CENTER, 1.0f, 15.0f, false);
        } else if (this.isScrollingPerformed) {
            this.valueLayout = null;
        } else {
            this.valueLayout.increaseWidthTo(i);
        }
        if (i2 > 0) {
            StaticLayout staticLayout3 = this.labelLayout;
            if (staticLayout3 == null || staticLayout3.getWidth() > i2) {
                this.labelLayout = new StaticLayout(this.label, this.valuePaint, i2, Layout.Alignment.ALIGN_NORMAL, 1.0f, 15.0f, false);
            } else {
                this.labelLayout.increaseWidthTo(i2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int calculateLayoutWidth = calculateLayoutWidth(size, mode);
        if (mode2 != 1073741824) {
            int desiredHeight = getDesiredHeight(this.itemsLayout);
            size2 = mode2 == Integer.MIN_VALUE ? Math.min(desiredHeight, size2) : desiredHeight;
        }
        setMeasuredDimension(calculateLayoutWidth, size2);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.itemsLayout == null) {
            int i = this.itemsWidth;
            if (i == 0) {
                calculateLayoutWidth(getWidth(), BasicMeasure.EXACTLY);
            } else {
                createLayouts(i, this.labelWidth);
            }
        }
        if (this.itemsWidth > 0) {
            canvas.save();
            canvas.translate(10.0f, (float) (-this.ITEM_OFFSET));
            drawItems(canvas);
            drawValue(canvas);
            canvas.restore();
        }
        drawCenterRect(canvas);
        drawShadows(canvas);
    }

    private void drawShadows(Canvas canvas) {
        this.topShadow.setBounds(0, 0, getWidth(), getHeight() / this.visibleItems);
        this.topShadow.draw(canvas);
        this.bottomShadow.setBounds(0, getHeight() - (getHeight() / this.visibleItems), getWidth(), getHeight());
        this.bottomShadow.draw(canvas);
    }

    private void drawValue(Canvas canvas) {
        this.valuePaint.setColor(VALUE_TEXT_COLOR);
        this.valuePaint.drawableState = getDrawableState();
        Rect rect = new Rect();
        this.itemsLayout.getLineBounds(this.visibleItems / 2, rect);
        if (this.labelLayout != null) {
            canvas.save();
            canvas.translate((float) (this.itemsLayout.getWidth() + 8), (float) rect.top);
            this.labelLayout.draw(canvas);
            canvas.restore();
        }
        if (this.valueLayout != null) {
            canvas.save();
            canvas.translate(0.0f, (float) (rect.top + this.scrollingOffset));
            this.valueLayout.draw(canvas);
            canvas.restore();
        }
    }

    private void drawItems(Canvas canvas) {
        canvas.save();
        canvas.translate(0.0f, (float) ((-this.itemsLayout.getLineTop(1)) + this.scrollingOffset));
        this.itemsPaint.setColor(-16777216);
        this.itemsPaint.drawableState = getDrawableState();
        this.itemsLayout.draw(canvas);
        canvas.restore();
    }

    private void drawCenterRect(Canvas canvas) {
        int height = getHeight() / 2;
        int itemHeight2 = getItemHeight() / 2;
        this.centerDrawable.setBounds(0, height - itemHeight2, getWidth(), height + itemHeight2);
        this.centerDrawable.draw(canvas);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (getAdapter() != null && !this.gestureDetector.onTouchEvent(motionEvent) && motionEvent.getAction() == 1) {
            justify();
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void doScroll(int i) {
        int i2 = this.scrollingOffset + i;
        this.scrollingOffset = i2;
        int itemHeight2 = i2 / getItemHeight();
        int i3 = this.currentItem - itemHeight2;
        if (this.isCyclic && this.adapter.getItemsCount() > 0) {
            while (i3 < 0) {
                i3 += this.adapter.getItemsCount();
            }
            i3 %= this.adapter.getItemsCount();
        } else if (!this.isScrollingPerformed) {
            i3 = Math.min(Math.max(i3, 0), this.adapter.getItemsCount() - 1);
        } else if (i3 < 0) {
            itemHeight2 = this.currentItem;
            i3 = 0;
        } else if (i3 >= this.adapter.getItemsCount()) {
            itemHeight2 = (this.currentItem - this.adapter.getItemsCount()) + 1;
            i3 = this.adapter.getItemsCount() - 1;
        }
        int i4 = this.scrollingOffset;
        if (i3 != this.currentItem) {
            setCurrentItem(i3, false);
        } else {
            invalidate();
        }
        int itemHeight3 = i4 - (itemHeight2 * getItemHeight());
        this.scrollingOffset = itemHeight3;
        if (itemHeight3 > getHeight()) {
            this.scrollingOffset = (this.scrollingOffset % getHeight()) + getHeight();
        }
    }

    /* access modifiers changed from: private */
    public void setNextMessage(int i) {
        clearMessages();
        this.animationHandler.sendEmptyMessage(i);
    }

    /* access modifiers changed from: private */
    public void clearMessages() {
        this.animationHandler.removeMessages(0);
        this.animationHandler.removeMessages(1);
    }

    /* access modifiers changed from: private */
    public void justify() {
        if (this.adapter != null) {
            boolean z = false;
            this.lastScrollY = 0;
            int i = this.scrollingOffset;
            int itemHeight2 = getItemHeight();
            if (i <= 0 ? this.currentItem > 0 : this.currentItem < this.adapter.getItemsCount()) {
                z = true;
            }
            if ((this.isCyclic || z) && Math.abs((float) i) > ((float) itemHeight2) / 2.0f) {
                i = i < 0 ? i + itemHeight2 + 1 : i - (itemHeight2 + 1);
            }
            int i2 = i;
            if (Math.abs(i2) > 1) {
                this.scroller.startScroll(0, 0, 0, i2, SCROLLING_DURATION);
                setNextMessage(1);
                return;
            }
            finishScrolling();
        }
    }

    /* access modifiers changed from: private */
    public void startScrolling() {
        if (!this.isScrollingPerformed) {
            this.isScrollingPerformed = true;
            notifyScrollingListenersAboutStart();
        }
    }

    /* access modifiers changed from: package-private */
    public void finishScrolling() {
        if (this.isScrollingPerformed) {
            notifyScrollingListenersAboutEnd();
            this.isScrollingPerformed = false;
        }
        invalidateLayouts();
        invalidate();
    }

    public void scroll(int i, int i2) {
        this.scroller.forceFinished(true);
        this.lastScrollY = this.scrollingOffset;
        int itemHeight2 = i * getItemHeight();
        Scroller scroller2 = this.scroller;
        int i3 = this.lastScrollY;
        scroller2.startScroll(0, i3, 0, itemHeight2 - i3, i2);
        setNextMessage(0);
        startScrolling();
    }
}
