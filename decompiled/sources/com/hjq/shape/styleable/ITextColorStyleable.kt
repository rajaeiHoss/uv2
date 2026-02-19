package com.hjq.shape.styleable

interface ITextColorStyleable {
    fun getTextCenterColorStyleable(): Int

    fun getTextCheckedColorStyleable(): Int = 0

    fun getTextColorStyleable(): Int

    fun getTextDisabledColorStyleable(): Int

    fun getTextEndColorStyleable(): Int

    fun getTextFocusedColorStyleable(): Int

    fun getTextGradientOrientationStyleable(): Int

    fun getTextPressedColorStyleable(): Int

    fun getTextSelectedColorStyleable(): Int

    fun getTextStartColorStyleable(): Int

    object CC {
        @JvmStatic
        fun `$default$getTextCheckedColorStyleable`(iTextColorStyleable: ITextColorStyleable): Int {
            return 0
        }
    }
}
