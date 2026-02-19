package com.dvr.calendar

class DayStyle {
    companion object {
        @JvmField
        val iColorBkg: Int = -7829368

        @JvmField
        val iColorBkgFocusDark: Int = -5614336

        @JvmField
        val iColorBkgFocusLight: Int = -8773

        @JvmField
        val iColorBkgHoliday: Int = -5592406

        @JvmField
        val iColorBkgSelectedDark: Int = -14527079

        @JvmField
        val iColorBkgSelectedLight: Int = -4465153

        @JvmField
        val iColorBkgToday: Int = -7816312

        @JvmField
        val iColorFrameHeader: Int = -10066330

        @JvmField
        val iColorFrameHeaderHoliday: Int = -9408400

        @JvmField
        val iColorText: Int = -2236963

        @JvmField
        val iColorTextFocused: Int = -14544640

        @JvmField
        val iColorTextHeader: Int = -3355444

        @JvmField
        val iColorTextHeaderHoliday: Int = -3092272

        @JvmField
        val iColorTextHoliday: Int = -986896

        @JvmField
        val iColorTextSelected: Int = -16772830

        @JvmField
        val iColorTextToday: Int = -16768512

        @JvmField
        val vecStrWeekDayNames: Array<String?> = getWeekDayNames()

        @JvmStatic
        fun getColorBkg(z: Boolean, z2: Boolean): Int = if (z2) iColorBkgToday else if (z) iColorBkgHoliday else iColorBkg

        @JvmStatic
        fun getColorFrameHeader(z: Boolean): Int = if (z) iColorFrameHeaderHoliday else iColorFrameHeader

        @JvmStatic
        fun getColorText(z: Boolean, z2: Boolean): Int = if (z2) iColorTextToday else if (z) iColorTextHoliday else iColorText

        @JvmStatic
        fun getColorTextHeader(z: Boolean): Int = if (z) iColorTextHeaderHoliday else iColorTextHeader

        @JvmStatic
        fun getWeekDay(i: Int, i2: Int): Int {
            val i3 = if (i2 == 2) {
                val week = i + 2
                if (week > 7) 1 else week
            } else {
                -1
            }
            return if (i2 == 1) i + 1 else i3
        }

        private fun getWeekDayNames(): Array<String?> {
            val strArr = arrayOfNulls<String>(10)
            strArr[1] = "Sun"
            strArr[2] = "Mon"
            strArr[3] = "Tue"
            strArr[4] = "Wed"
            strArr[5] = "Thu"
            strArr[6] = "Fri"
            strArr[7] = "Sat"
            return strArr
        }

        @JvmStatic
        fun getWeekDayName(i: Int): String? = vecStrWeekDayNames[i]
    }
}
