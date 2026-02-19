package com.hjq.base.action

import android.view.View

interface ClickAction : View.OnClickListener {
    fun <V : View> findViewById(id: Int): V

    override fun onClick(view: View) {
        // no-op
    }

    fun setOnClickListener(vararg viewIds: Int) {
        setOnClickListener(this, *viewIds)
    }

    fun setOnClickListener(onClickListener: View.OnClickListener, vararg viewIds: Int) {
        for (viewId in viewIds) {
            findViewById<View>(viewId).setOnClickListener(onClickListener)
        }
    }

    fun setOnClickListener(vararg views: View) {
        setOnClickListener(this, *views)
    }

    fun setOnClickListener(onClickListener: View.OnClickListener, vararg views: View) {
        for (view in views) {
            view.setOnClickListener(onClickListener)
        }
    }

    object CC {
        @JvmStatic
        fun `$default$onClick`(clickAction: ClickAction, view: View) {
            // no-op
        }

        @JvmStatic
        fun `$default$setOnClickListener`(clickAction: ClickAction, vararg viewIds: Int) {
            clickAction.setOnClickListener(clickAction, *viewIds)
        }

        @JvmStatic
        fun `$default$setOnClickListener`(
            clickAction: ClickAction,
            onClickListener: View.OnClickListener,
            vararg viewIds: Int
        ) {
            for (viewId in viewIds) {
                clickAction.findViewById<View>(viewId).setOnClickListener(onClickListener)
            }
        }

        @JvmStatic
        fun `$default$setOnClickListener`(clickAction: ClickAction, vararg views: View) {
            clickAction.setOnClickListener(clickAction, *views)
        }

        @JvmStatic
        fun `$default$setOnClickListener`(
            clickAction: ClickAction,
            onClickListener: View.OnClickListener,
            vararg views: View
        ) {
            for (view in views) {
                view.setOnClickListener(onClickListener)
            }
        }
    }
}
