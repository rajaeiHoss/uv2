package com.dvr.calendar

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import com.streamax.utils.LogUtils
import java.util.ArrayList
import java.util.Calendar

class CalendarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {
    private val FirstDateOfMonthCalendar: Calendar = Calendar.getInstance()
    private val MainCalendar: Calendar = Calendar.getInstance()
    private val SelectedCalendar: Calendar = Calendar.getInstance()
    private val ToadyCalendar: Calendar = Calendar.getInstance()

    @JvmField
    var btnCurrentMonth: Button? = null

    @JvmField
    var btnNext: Button? = null

    @JvmField
    var btnPrev: Button? = null

    private val days: ArrayList<DateWidgetDayCell> = ArrayList()
    private var iDayCellSize = 110
    private var iFirstDayOfWeek = 1
    private var iMonthViewCurrentMonth = 0
    private var iMonthViewCurrentYear = 0
    private var iTotalWidth = 0

    @JvmField
    var layContent: LinearLayout? = null

    private val mContext: Context = context

    @JvmField
    var mDay: Int = 0

    @JvmField
    var mMonth: Int = 0

    private val mOnDayCellClick = object : DateWidgetDayCell.OnItemClick {
        override fun OnClick(dateWidgetDayCell: DateWidgetDayCell) {
            SelectedCalendar.timeInMillis = dateWidgetDayCell.getDate().timeInMillis
            if (mUpdateCalendar != null) {
                LogUtils.log(javaClass, "SearchDay()")
                mUpdateCalendar?.SearchDay(
                    SelectedCalendar.get(1),
                    SelectedCalendar.get(2) + 1,
                    SelectedCalendar.get(5)
                )
            }
        }
    }

    @JvmField
    var mRecordTimeInfo: RecordTimeInfo = RecordTimeInfo()

    private var mUpdateCalendar: UpdateCalendarInterface? = null

    @JvmField
    var mYear: Int = 0

    inner class RecordTimeInfo {
        @JvmField
        var nDayBits: Int = 0

        @JvmField
        var nMonth: Int = 0

        @JvmField
        var nYear: Int = 0

        fun init() {
            nYear = 0
            nMonth = 0
            nDayBits = 0
        }
    }

    init {
        val i = resources.displayMetrics.widthPixels / 7
        iDayCellSize = i
        iTotalWidth = i * 7
    }

    fun LoadViews() {
        iFirstDayOfWeek = 1
        mYear = SelectedCalendar.get(1)
        mMonth = SelectedCalendar.get(2)
        mDay = SelectedCalendar.get(5)
        addView(generateContentView())
        getCalendarStartDate()
        updateCalendar()
    }

    private fun createLayout(i: Int): LinearLayout {
        val linearLayout = LinearLayout(mContext)
        linearLayout.layoutParams = LayoutParams(-2, -2)
        linearLayout.orientation = i
        return linearLayout
    }

    private fun createButton(str: String, i: Int, i2: Int): Button {
        val button = Button(mContext)
        button.text = str
        button.layoutParams = LayoutParams(i, i2)
        return button
    }

    private fun generateTopButtons(linearLayout: LinearLayout) {
        val createButton = createButton("", (iTotalWidth - 100) - 100, -2)
        btnCurrentMonth = createButton
        createButton.setBackgroundResource(17301509)

        val symbolButton = SymbolButton(mContext, SymbolButton.symbol.arrowLeft)
        symbolButton.layoutParams = LayoutParams(100, -2)
        symbolButton.setBackgroundResource(17301509)

        val symbolButton2 = SymbolButton(mContext, SymbolButton.symbol.arrowRight)
        symbolButton2.layoutParams = LayoutParams(100, -2)
        symbolButton2.setBackgroundResource(17301509)

        symbolButton.setOnClickListener { setPrevViewItem() }

        btnCurrentMonth?.setOnClickListener {
            setTodayViewItem()
            btnCurrentMonth?.text = "${ToadyCalendar.get(1)}/${ToadyCalendar.get(2) + 1}"
            mUpdateCalendar?.UpdateCalendar(ToadyCalendar.get(1), ToadyCalendar.get(2) + 1)
        }

        symbolButton2.setOnClickListener { setNextViewItem() }

        linearLayout.gravity = 1
        linearLayout.addView(symbolButton)
        linearLayout.addView(btnCurrentMonth)
        linearLayout.addView(symbolButton2)
    }

    private fun generateContentView(): View {
        val createLayout = createLayout(1)
        createLayout.setPadding(4, 4, 4, 4)
        val createLayout2 = createLayout(0)
        layContent = createLayout(1)
        generateTopButtons(createLayout2)
        generateCalendar(layContent!!)
        createLayout.addView(createLayout2)
        createLayout.addView(layContent)
        return createLayout
    }

    private fun generateCalendarRow(): View {
        val createLayout = createLayout(0)
        for (i in 0 until 7) {
            val dateWidgetDayCell = DateWidgetDayCell(mContext, iDayCellSize, iDayCellSize)
            dateWidgetDayCell.setItemClick(mOnDayCellClick)
            days.add(dateWidgetDayCell)
            createLayout.addView(dateWidgetDayCell)
        }
        return createLayout
    }

    private fun generateCalendarHeader(): View {
        val createLayout = createLayout(0)
        for (i in 0 until 7) {
            val dateWidgetDayHeader = DateWidgetDayHeader(mContext, iDayCellSize, 20)
            dateWidgetDayHeader.setData(DayStyle.getWeekDay(i, iFirstDayOfWeek))
            createLayout.addView(dateWidgetDayHeader)
        }
        return createLayout
    }

    private fun generateCalendar(linearLayout: LinearLayout) {
        linearLayout.addView(generateCalendarHeader())
        days.clear()
        for (i in 0 until 6) {
            linearLayout.addView(generateCalendarRow())
        }
    }

    private fun getCalendarStartDate(): Calendar {
        ToadyCalendar.timeInMillis = System.currentTimeMillis()
        ToadyCalendar.firstDayOfWeek = iFirstDayOfWeek

        FirstDateOfMonthCalendar.timeInMillis = System.currentTimeMillis()
        FirstDateOfMonthCalendar.firstDayOfWeek = iFirstDayOfWeek

        UpdateStartDateForMonth()
        return FirstDateOfMonthCalendar
    }

    fun GetCurrentMonth(): Int = iMonthViewCurrentMonth + 1

    fun GetCurrentYear(): Int = iMonthViewCurrentYear

    fun updateCalendar() {
        MainCalendar.timeInMillis = FirstDateOfMonthCalendar.timeInMillis
        for (i in 0 until days.size) {
            val year = MainCalendar.get(1)
            val month = MainCalendar.get(2)
            val day = MainCalendar.get(5)
            val weekDay = MainCalendar.get(7)
            val dateWidgetDayCell = days[i]

            val isToday = ToadyCalendar.get(1) == year && ToadyCalendar.get(2) == month && ToadyCalendar.get(5) == day
            var isHoliday = weekDay == 7 || weekDay == 1
            if (month == 0 && day == 1) {
                isHoliday = true
            }

            if (year != mRecordTimeInfo.nYear || month + 1 != mRecordTimeInfo.nMonth) {
                dateWidgetDayCell.SetRecordDay(false)
            } else if ((mRecordTimeInfo.nDayBits and (1 shl (day - 1))) > 0) {
                dateWidgetDayCell.SetRecordDay(true)
            } else {
                dateWidgetDayCell.SetRecordDay(false)
            }

            dateWidgetDayCell.setData(year, month, day, isToday, isHoliday, iMonthViewCurrentMonth)
            MainCalendar.add(5, 1)
        }
        layContent?.invalidate()
    }

    private fun UpdateStartDateForMonth() {
        iMonthViewCurrentMonth = FirstDateOfMonthCalendar.get(2)
        iMonthViewCurrentYear = FirstDateOfMonthCalendar.get(1)
        FirstDateOfMonthCalendar.set(5, 1)
        UpdateCurrentMonthDisplay()

        val offset = when (iFirstDayOfWeek) {
            2 -> {
                val value = FirstDateOfMonthCalendar.get(7) - 2
                if (value >= 0) value else 6
            }

            1 -> {
                val value = FirstDateOfMonthCalendar.get(7) - 1
                if (value >= 0) value else 6
            }

            else -> 0
        }

        FirstDateOfMonthCalendar.add(7, -offset)
    }

    private fun UpdateCurrentMonthDisplay() {
        btnCurrentMonth?.text = "${FirstDateOfMonthCalendar.get(1)}/${FirstDateOfMonthCalendar.get(2) + 1}"
    }

    private fun setPrevViewItem() {
        iMonthViewCurrentMonth -= 1
        if (iMonthViewCurrentMonth == -1) {
            iMonthViewCurrentMonth = 11
            iMonthViewCurrentYear--
        }
        Log.v(TAG, "setPrevViewItem_2__iMonthViewCurrentMonth = $iMonthViewCurrentMonth")

        FirstDateOfMonthCalendar.set(5, 1)
        FirstDateOfMonthCalendar.set(2, iMonthViewCurrentMonth)
        FirstDateOfMonthCalendar.set(1, iMonthViewCurrentYear)

        mRecordTimeInfo.init()
        UpdateStartDateForMonth()
        updateCalendar()
        mUpdateCalendar?.UpdateCalendar(FirstDateOfMonthCalendar.get(1), FirstDateOfMonthCalendar.get(2) + 1)
    }

    private fun setNextViewItem() {
        Log.v(TAG, "setNextViewItem_1_iMonthViewCurrentMonth = $iMonthViewCurrentMonth")
        iMonthViewCurrentMonth += 1
        if (iMonthViewCurrentMonth == 12) {
            iMonthViewCurrentMonth = 0
            iMonthViewCurrentYear++
        }
        Log.v(TAG, "setNextViewItem_2_iMonthViewCurrentMonth = $iMonthViewCurrentMonth")

        FirstDateOfMonthCalendar.set(5, 1)
        FirstDateOfMonthCalendar.set(2, iMonthViewCurrentMonth)
        FirstDateOfMonthCalendar.set(1, iMonthViewCurrentYear)

        mRecordTimeInfo.init()
        UpdateStartDateForMonth()
        updateCalendar()
        mUpdateCalendar?.UpdateCalendar(FirstDateOfMonthCalendar.get(1), FirstDateOfMonthCalendar.get(2) + 1)
    }

    private fun setTodayViewItem() {
        ToadyCalendar.timeInMillis = System.currentTimeMillis()
        ToadyCalendar.firstDayOfWeek = iFirstDayOfWeek

        FirstDateOfMonthCalendar.timeInMillis = ToadyCalendar.timeInMillis
        FirstDateOfMonthCalendar.firstDayOfWeek = iFirstDayOfWeek

        UpdateStartDateForMonth()
        updateCalendar()
    }

    fun SetUpdateCalendarInterface(updateCalendarInterface: UpdateCalendarInterface?) {
        mUpdateCalendar = updateCalendarInterface
    }

    companion object {
        const val SELECT_DATE_REQUEST: Int = 111
        private const val TAG = "CalendarView"
    }
}
