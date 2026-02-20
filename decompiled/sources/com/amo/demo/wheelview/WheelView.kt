package com.amo.demo.wheelview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Handler
import android.os.Message
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.animation.Interpolator
import android.widget.Scroller
import com.zycs.UView.R
import java.util.LinkedList
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.max
import kotlin.math.min

class WheelView : View {
    @JvmField
    var TEXT_SIZE: Int = 0
    private val ITEM_OFFSET: Int = TEXT_SIZE / 5
    private val MESSAGE_JUSTIFY: Int = 1
    private val MESSAGE_SCROLL: Int = 0

    private var adapter: WheelAdapter? = null
    private val animationHandler: Handler = object : Handler() {
        override fun handleMessage(message: Message) {
            scroller.computeScrollOffset()
            val currY = scroller.currY
            val delta = lastScrollY - currY
            lastScrollY = currY
            if (delta != 0) {
                doScroll(delta)
            }
            if (abs((currY - scroller.finalY).toFloat()) < 1) {
                scroller.finalY
                scroller.forceFinished(true)
            }
            if (!scroller.isFinished) {
                sendEmptyMessage(message.what)
            } else if (message.what == MESSAGE_SCROLL) {
                justify()
            } else {
                finishScrolling()
            }
        }
    }
    private var bottomShadow: GradientDrawable? = null
    private var centerDrawable: Drawable? = null
    private val changingListeners: MutableList<OnWheelChangedListener> = LinkedList()
    private var currentItem: Int = 0
    private lateinit var gestureDetector: GestureDetector
    private val gestureListener: GestureDetector.OnGestureListener =
        object : GestureDetector.OnGestureListener {
            override fun onDown(e: MotionEvent): Boolean {
                if (!isScrollingPerformed) {
                    return false
                }
                scroller.forceFinished(true)
                clearMessages()
                return true
            }

            override fun onShowPress(e: MotionEvent) {
            }

            override fun onSingleTapUp(e: MotionEvent): Boolean {
                return false
            }

            override fun onScroll(e1: MotionEvent?, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
                startScrolling()
                doScroll((-distanceY).toInt())
                return true
            }

            override fun onLongPress(e: MotionEvent) {
            }

            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                val maxY: Int
                lastScrollY = (currentItem * getItemHeight()) + scrollingOffset
                if (isCyclic) {
                    maxY = Int.MAX_VALUE
                } else {
                    maxY = adapter!!.getItemsCount() * getItemHeight()
                }
                scroller.fling(
                    0,
                    lastScrollY,
                    0,
                    (-velocityY).toInt() / 2,
                    0,
                    0,
                    if (isCyclic) -maxY else 0,
                    maxY
                )
                setNextMessage(MESSAGE_SCROLL)
                return true
            }
        }
    private var isCyclic: Boolean = false
    private var isScrollingPerformed: Boolean = false
    private var itemHeight: Int = 0
    private var itemsLayout: StaticLayout? = null
    private var itemsPaint: TextPaint? = null
    private var itemsWidth: Int = 0
    private var label: String? = null
    private var labelLayout: StaticLayout? = null
    private var labelWidth: Int = 0
    private var lastScrollY: Int = 0
    private lateinit var scroller: Scroller
    private val scrollingListeners: MutableList<OnWheelScrollListener> = LinkedList()
    private var scrollingOffset: Int = 0
    private var topShadow: GradientDrawable? = null
    private var valueLayout: StaticLayout? = null
    private var valuePaint: TextPaint? = null
    private var visibleItems: Int = DEF_VISIBLE_ITEMS

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initData(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initData(context)
    }

    constructor(context: Context) : super(context) {
        initData(context)
    }

    private fun initData(context: Context) {
        gestureDetector = GestureDetector(context, gestureListener)
        gestureDetector.setIsLongpressEnabled(false)
        scroller = Scroller(context)
    }

    fun getAdapter(): WheelAdapter? {
        return adapter
    }

    fun setAdapter(wheelAdapter: WheelAdapter?) {
        adapter = wheelAdapter
        invalidateLayouts()
        invalidate()
    }

    fun setInterpolator(interpolator: Interpolator?) {
        scroller.forceFinished(true)
        scroller = Scroller(context, interpolator)
    }

    fun getVisibleItems(): Int {
        return visibleItems
    }

    fun setVisibleItems(count: Int) {
        visibleItems = count
        invalidate()
    }

    fun getLabel(): String? {
        return label
    }

    fun setLabel(text: String?) {
        val oldLabel = label
        if (oldLabel == null || oldLabel != text) {
            label = text
            labelLayout = null
            invalidate()
        }
    }

    fun addChangingListener(listener: OnWheelChangedListener) {
        changingListeners.add(listener)
    }

    fun removeChangingListener(listener: OnWheelChangedListener) {
        changingListeners.remove(listener)
    }

    protected fun notifyChangingListeners(oldValue: Int, newValue: Int) {
        for (listener in changingListeners) {
            listener.onChanged(this, oldValue, newValue)
        }
    }

    fun addScrollingListener(listener: OnWheelScrollListener) {
        scrollingListeners.add(listener)
    }

    fun removeScrollingListener(listener: OnWheelScrollListener) {
        scrollingListeners.remove(listener)
    }

    protected fun notifyScrollingListenersAboutStart() {
        for (listener in scrollingListeners) {
            listener.onScrollingStarted(this)
        }
    }

    protected fun notifyScrollingListenersAboutEnd() {
        for (listener in scrollingListeners) {
            listener.onScrollingFinished(this)
        }
    }

    fun getCurrentItem(): Int {
        return currentItem
    }

    fun setCurrentItem(index: Int, animated: Boolean) {
        var nextIndex = index
        val wheelAdapter = adapter
        if (wheelAdapter != null && wheelAdapter.getItemsCount() != 0) {
            if (nextIndex < 0 || nextIndex >= wheelAdapter.getItemsCount()) {
                if (isCyclic) {
                    while (nextIndex < 0) {
                        nextIndex += wheelAdapter.getItemsCount()
                    }
                    nextIndex %= wheelAdapter.getItemsCount()
                } else {
                    return
                }
            }
            val oldIndex = currentItem
            if (nextIndex == oldIndex) {
                return
            }
            if (animated) {
                scroll(nextIndex - oldIndex, SCROLLING_DURATION)
                return
            }
            invalidateLayouts()
            currentItem = nextIndex
            notifyChangingListeners(oldIndex, nextIndex)
            invalidate()
        }
    }

    fun setCurrentItem(index: Int) {
        setCurrentItem(index, false)
    }

    fun isCyclic(): Boolean {
        return isCyclic
    }

    fun setCyclic(cyclic: Boolean) {
        isCyclic = cyclic
        invalidate()
        invalidateLayouts()
    }

    private fun invalidateLayouts() {
        itemsLayout = null
        valueLayout = null
        scrollingOffset = 0
    }

    private fun initResourcesIfNecessary() {
        if (itemsPaint == null) {
            val textPaint = TextPaint(33)
            textPaint.textSize = TEXT_SIZE.toFloat()
            itemsPaint = textPaint
        }
        if (valuePaint == null) {
            val textPaint = TextPaint(37)
            textPaint.textSize = TEXT_SIZE.toFloat()
            textPaint.setShadowLayer(0.1f, 0.0f, 0.1f, -4144960)
            valuePaint = textPaint
        }
        if (centerDrawable == null) {
            centerDrawable = context.resources.getDrawable(R.drawable.wheel_val)
        }
        if (topShadow == null) {
            topShadow = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, SHADOWS_COLORS)
        }
        if (bottomShadow == null) {
            bottomShadow = GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, SHADOWS_COLORS)
        }
        setBackgroundResource(R.drawable.wheel_bg)
    }

    private fun getDesiredHeight(layout: Layout?): Int {
        if (layout == null) {
            return 0
        }
        return max(((getItemHeight() * visibleItems) - (ITEM_OFFSET * 2)) - ADDITIONAL_ITEM_HEIGHT, suggestedMinimumHeight)
    }

    private fun getTextItem(index: Int): String? {
        var itemIndex = index
        val wheelAdapter = adapter
        if (wheelAdapter == null || wheelAdapter.getItemsCount() == 0) {
            return null
        }
        val itemsCount = wheelAdapter.getItemsCount()
        if ((itemIndex < 0 || itemIndex >= itemsCount) && !isCyclic) {
            return null
        }
        while (itemIndex < 0) {
            itemIndex += itemsCount
        }
        return wheelAdapter.getItem(itemIndex % itemsCount)
    }

    private fun buildText(useCurrentValue: Boolean): String {
        val builder = StringBuilder()
        val addItems = (visibleItems / 2) + 1
        var index = currentItem - addItems
        while (index <= currentItem + addItems) {
            if ((useCurrentValue || index != currentItem)) {
                val textItem = getTextItem(index)
                if (textItem != null) {
                    builder.append(textItem)
                }
            }
            if (index < currentItem + addItems) {
                builder.append("\n")
            }
            index++
        }
        return builder.toString()
    }

    private fun getMaxTextLength(): Int {
        val wheelAdapter = getAdapter() ?: return 0
        val maxLength = wheelAdapter.getMaximumLength()
        if (maxLength > 0) {
            return maxLength
        }
        var maxText: String? = null
        val from = max(currentItem - (visibleItems / 2), 0)
        val to = min(currentItem + visibleItems, wheelAdapter.getItemsCount())
        for (index in from until to) {
            val item = wheelAdapter.getItem(index)
            if (item != null && (maxText == null || maxText.length < item.length)) {
                maxText = item
            }
        }
        return maxText?.length ?: 0
    }

    private fun getItemHeight(): Int {
        if (itemHeight != 0) {
            return itemHeight
        }
        val layout = itemsLayout
        if (layout == null || layout.lineCount <= 2) {
            return height / visibleItems
        }
        val lineHeight = layout.getLineTop(2) - layout.getLineTop(1)
        itemHeight = lineHeight
        return lineHeight
    }

    private fun calculateLayoutWidth(widthSize: Int, widthMode: Int): Int {
        initResourcesIfNecessary()

        val maxTextLength = getMaxTextLength()
        itemsWidth = if (maxTextLength > 0) {
            (maxTextLength.toFloat() * ceil(Layout.getDesiredWidth("0", itemsPaint!!).toDouble()).toFloat()).toInt()
        } else {
            0
        }
        itemsWidth += ADDITIONAL_ITEMS_SPACE
        labelWidth = 0
        val labelText = label
        if (!labelText.isNullOrEmpty()) {
            labelWidth = ceil(Layout.getDesiredWidth(labelText, valuePaint!!).toDouble()).toInt()
        }

        var width = widthSize
        var recalculateByAvailableSpace = true
        if (widthMode != MeasureSpec.EXACTLY) {
            val baseWidth = itemsWidth + labelWidth + PADDING * 2 + (if (labelWidth > 0) LABEL_OFFSET else 0)
            val minWidth = max(baseWidth, suggestedMinimumWidth)
            if (widthMode != MeasureSpec.AT_MOST || widthSize >= minWidth) {
                width = minWidth
                recalculateByAvailableSpace = false
            }
        }

        if (recalculateByAvailableSpace) {
            val contentWidth = (width - LABEL_OFFSET) - (PADDING * 2)
            if (contentWidth <= 0) {
                labelWidth = 0
                itemsWidth = 0
            }
            if (labelWidth > 0) {
                val textWidth = itemsWidth
                val textPart = (textWidth.toDouble() * contentWidth.toDouble()) / (textWidth + labelWidth).toDouble()
                itemsWidth = textPart.toInt()
                labelWidth = contentWidth - itemsWidth
            } else {
                itemsWidth = contentWidth + LABEL_OFFSET
            }
        }

        if (itemsWidth > 0) {
            createLayouts(itemsWidth, labelWidth)
        }
        return width
    }

    private fun createLayouts(itemsLayoutWidth: Int, labelLayoutWidth: Int) {
        val align = if (labelLayoutWidth > 0) Layout.Alignment.ALIGN_OPPOSITE else Layout.Alignment.ALIGN_CENTER

        val oldItemsLayout = itemsLayout
        if (oldItemsLayout == null || oldItemsLayout.width > itemsLayoutWidth) {
            itemsLayout = StaticLayout(
                buildText(isScrollingPerformed),
                itemsPaint,
                itemsLayoutWidth,
                align,
                1.0f,
                ADDITIONAL_ITEM_HEIGHT.toFloat(),
                false
            )
        } else {
            oldItemsLayout.increaseWidthTo(itemsLayoutWidth)
        }

        if (!isScrollingPerformed && (valueLayout == null || valueLayout!!.width > itemsLayoutWidth)) {
            var text = ""
            val wheelAdapter = getAdapter()
            if (wheelAdapter != null) {
                text = wheelAdapter.getItem(currentItem) ?: ""
            }
            valueLayout = StaticLayout(
                text,
                valuePaint,
                itemsLayoutWidth,
                align,
                1.0f,
                ADDITIONAL_ITEM_HEIGHT.toFloat(),
                false
            )
        } else if (isScrollingPerformed) {
            valueLayout = null
        } else {
            valueLayout?.increaseWidthTo(itemsLayoutWidth)
        }

        if (labelLayoutWidth > 0) {
            val oldLabelLayout = labelLayout
            if (oldLabelLayout == null || oldLabelLayout.width > labelLayoutWidth) {
                labelLayout = StaticLayout(
                    label ?: "",
                    valuePaint,
                    labelLayoutWidth,
                    Layout.Alignment.ALIGN_NORMAL,
                    1.0f,
                    ADDITIONAL_ITEM_HEIGHT.toFloat(),
                    false
                )
            } else {
                oldLabelLayout.increaseWidthTo(labelLayoutWidth)
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        var heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val width = calculateLayoutWidth(widthSize, widthMode)
        if (heightMode != MeasureSpec.EXACTLY) {
            val desiredHeight = getDesiredHeight(itemsLayout)
            heightSize = if (heightMode == MeasureSpec.AT_MOST) {
                min(desiredHeight, heightSize)
            } else {
                desiredHeight
            }
        }
        setMeasuredDimension(width, heightSize)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (itemsLayout == null) {
            if (itemsWidth == 0) {
                calculateLayoutWidth(width, MeasureSpec.EXACTLY)
            } else {
                createLayouts(itemsWidth, labelWidth)
            }
        }
        if (itemsWidth > 0) {
            canvas.save()
            canvas.translate(PADDING.toFloat(), (-ITEM_OFFSET).toFloat())
            drawItems(canvas)
            drawValue(canvas)
            canvas.restore()
        }
        drawCenterRect(canvas)
        drawShadows(canvas)
    }

    private fun drawShadows(canvas: Canvas) {
        topShadow!!.setBounds(0, 0, width, height / visibleItems)
        topShadow!!.draw(canvas)
        bottomShadow!!.setBounds(0, height - (height / visibleItems), width, height)
        bottomShadow!!.draw(canvas)
    }

    private fun drawValue(canvas: Canvas) {
        valuePaint!!.color = VALUE_TEXT_COLOR
        valuePaint!!.drawableState = drawableState
        val rect = Rect()
        itemsLayout!!.getLineBounds(visibleItems / 2, rect)
        if (labelLayout != null) {
            canvas.save()
            canvas.translate((itemsLayout!!.width + LABEL_OFFSET).toFloat(), rect.top.toFloat())
            labelLayout!!.draw(canvas)
            canvas.restore()
        }
        if (valueLayout != null) {
            canvas.save()
            canvas.translate(0.0f, (rect.top + scrollingOffset).toFloat())
            valueLayout!!.draw(canvas)
            canvas.restore()
        }
    }

    private fun drawItems(canvas: Canvas) {
        canvas.save()
        canvas.translate(0.0f, ((-itemsLayout!!.getLineTop(1)) + scrollingOffset).toFloat())
        itemsPaint!!.color = ITEMS_TEXT_COLOR
        itemsPaint!!.drawableState = drawableState
        itemsLayout!!.draw(canvas)
        canvas.restore()
    }

    private fun drawCenterRect(canvas: Canvas) {
        val center = height / 2
        val halfItemHeight = getItemHeight() / 2
        centerDrawable!!.setBounds(0, center - halfItemHeight, width, center + halfItemHeight)
        centerDrawable!!.draw(canvas)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (getAdapter() != null && !gestureDetector.onTouchEvent(event) && event.action == MotionEvent.ACTION_UP) {
            justify()
        }
        return true
    }

    private fun doScroll(delta: Int) {
        val wheelAdapter = adapter ?: return

        scrollingOffset += delta
        var count = scrollingOffset / getItemHeight()
        var item = currentItem - count
        if (isCyclic && wheelAdapter.getItemsCount() > 0) {
            while (item < 0) {
                item += wheelAdapter.getItemsCount()
            }
            item %= wheelAdapter.getItemsCount()
        } else if (!isScrollingPerformed) {
            item = min(max(item, 0), wheelAdapter.getItemsCount() - 1)
        } else if (item < 0) {
            count = currentItem
            item = 0
        } else if (item >= wheelAdapter.getItemsCount()) {
            count = (currentItem - wheelAdapter.getItemsCount()) + 1
            item = wheelAdapter.getItemsCount() - 1
        }

        val offset = scrollingOffset
        if (item != currentItem) {
            setCurrentItem(item, false)
        } else {
            invalidate()
        }
        scrollingOffset = offset - (count * getItemHeight())
        if (scrollingOffset > height) {
            scrollingOffset = (scrollingOffset % height) + height
        }
    }

    private fun setNextMessage(message: Int) {
        clearMessages()
        animationHandler.sendEmptyMessage(message)
    }

    private fun clearMessages() {
        animationHandler.removeMessages(MESSAGE_SCROLL)
        animationHandler.removeMessages(MESSAGE_JUSTIFY)
    }

    private fun justify() {
        val wheelAdapter = adapter ?: return
        var canMove = false
        lastScrollY = 0
        var offset = scrollingOffset
        val itemHeight = getItemHeight()
        if (if (offset <= 0) currentItem > 0 else currentItem < wheelAdapter.getItemsCount()) {
            canMove = true
        }
        if ((isCyclic || canMove) && abs(offset.toFloat()) > itemHeight.toFloat() / 2.0f) {
            offset = if (offset < 0) {
                offset + itemHeight + 1
            } else {
                offset - (itemHeight + 1)
            }
        }
        if (abs(offset) > MIN_DELTA_FOR_SCROLLING) {
            scroller.startScroll(0, 0, 0, offset, SCROLLING_DURATION)
            setNextMessage(MESSAGE_JUSTIFY)
            return
        }
        finishScrolling()
    }

    private fun startScrolling() {
        if (!isScrollingPerformed) {
            isScrollingPerformed = true
            notifyScrollingListenersAboutStart()
        }
    }

    fun finishScrolling() {
        if (isScrollingPerformed) {
            notifyScrollingListenersAboutEnd()
            isScrollingPerformed = false
        }
        invalidateLayouts()
        invalidate()
    }

    fun scroll(itemsToScroll: Int, duration: Int) {
        scroller.forceFinished(true)
        lastScrollY = scrollingOffset
        val offset = itemsToScroll * getItemHeight()
        val startY = lastScrollY
        scroller.startScroll(0, startY, 0, offset - startY, duration)
        setNextMessage(MESSAGE_SCROLL)
        startScrolling()
    }

    companion object {
        private const val ADDITIONAL_ITEMS_SPACE = 10
        private const val ADDITIONAL_ITEM_HEIGHT = 15
        private const val DEF_VISIBLE_ITEMS = 5
        private const val ITEMS_TEXT_COLOR = -16777216
        private const val LABEL_OFFSET = 8
        private const val MIN_DELTA_FOR_SCROLLING = 1
        private const val PADDING = 10
        private const val SCROLLING_DURATION = 400
        private val SHADOWS_COLORS = intArrayOf(-15658735, 11184810, 11184810)
        private const val VALUE_TEXT_COLOR = -268435456
    }
}
