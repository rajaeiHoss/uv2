package com.google.android.gms.cast.framework.media.widget

import android.widget.ImageView
import com.google.android.gms.cast.framework.media.uicontroller.UIMediaController

interface ControlButtonsContainer {
    @Throws(IndexOutOfBoundsException::class)
    fun getButtonImageViewAt(index: Int): ImageView

    val buttonSlotCount: Int

    @Throws(IndexOutOfBoundsException::class)
    fun getButtonTypeAt(index: Int): Int

    fun getUIMediaController(): UIMediaController
}
