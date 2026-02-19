package com.hjq.widget.layout

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.hjq.widget.R

class CustomViewStub : FrameLayout {
    private var mInflateView: View? = null
    private val mLayoutResource: Int
    private var mListener: OnViewStubListener? = null

    interface OnViewStubListener {
        fun onInflate(customViewStub: CustomViewStub, view: View)

        fun onVisibility(customViewStub: CustomViewStub, visibility: Int)
    }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attrs,
        defStyleAttr,
        0
    )

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomViewStub)
        mLayoutResource = typedArray.getResourceId(R.styleable.CustomViewStub_android_layout, 0)
        typedArray.recycle()
        visibility = View.GONE
    }

    override fun setVisibility(visibility: Int) {
        super.setVisibility(visibility)
        if (mInflateView == null && visibility != View.GONE) {
            val inflate = LayoutInflater.from(context).inflate(mLayoutResource, this, false)
            mInflateView = inflate
            val childLayoutParams = inflate.layoutParams as FrameLayout.LayoutParams
            val parentLayoutParams = layoutParams
            childLayoutParams.width = parentLayoutParams.width
            childLayoutParams.height = parentLayoutParams.height
            if (childLayoutParams.gravity == -1) {
                childLayoutParams.gravity = 17
            }
            inflate.layoutParams = childLayoutParams
            addView(inflate)
            mListener?.onInflate(this, inflate)
        }
        mListener?.onVisibility(this, visibility)
    }

    fun setCustomVisibility(visibility: Int) {
        super.setVisibility(visibility)
    }

    fun getInflateView(): View? {
        return mInflateView
    }

    fun setOnViewStubListener(listener: OnViewStubListener?) {
        mListener = listener
    }
}
