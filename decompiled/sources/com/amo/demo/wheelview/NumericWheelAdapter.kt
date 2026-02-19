package com.amo.demo.wheelview

import java.util.Locale

class NumericWheelAdapter : WheelAdapter {
    private var format: String?
    private val maxValue: Int
    private val minValue: Int

    constructor() : this(DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE)

    constructor(minValue: Int, maxValue: Int) : this(minValue, maxValue, null)

    constructor(minValue: Int, maxValue: Int, format: String?) {
        this.minValue = minValue
        this.maxValue = maxValue
        this.format = format
    }

    override fun getItem(i: Int): String? {
        if (i < 0 || i >= getItemsCount()) {
            return null
        }
        val value = minValue + i
        val valueFormat = format
        if (valueFormat == null) {
            return Integer.toString(value)
        }
        return String.format(Locale.getDefault(), valueFormat, value)
    }

    override fun getItemsCount(): Int {
        return (maxValue - minValue) + 1
    }

    override fun getMaximumLength(): Int {
        val length = Integer.toString(maxOf(kotlin.math.abs(maxValue), kotlin.math.abs(minValue))).length
        return if (minValue < 0) {
            length + 1
        } else {
            length
        }
    }

    companion object {
        const val DEFAULT_MAX_VALUE: Int = 9
        private const val DEFAULT_MIN_VALUE: Int = 0
    }
}
