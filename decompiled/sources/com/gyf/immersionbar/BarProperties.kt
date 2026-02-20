package com.gyf.immersionbar

class BarProperties {
    private var actionBarHeight: Int = 0
    private var hasNavigationBar: Boolean = false
    private var landscapeLeft: Boolean = false
    private var landscapeRight: Boolean = false
    private var navigationBarHeight: Int = 0
    private var navigationBarWidth: Int = 0
    private var notchHeight: Int = 0
    private var notchScreen: Boolean = false
    private var portrait: Boolean = false
    private var statusBarHeight: Int = 0

    fun isPortrait(): Boolean {
        return portrait
    }

    fun setPortrait(value: Boolean) {
        portrait = value
    }

    fun isLandscapeLeft(): Boolean {
        return landscapeLeft
    }

    fun setLandscapeLeft(value: Boolean) {
        landscapeLeft = value
    }

    fun isLandscapeRight(): Boolean {
        return landscapeRight
    }

    fun setLandscapeRight(value: Boolean) {
        landscapeRight = value
    }

    fun isNotchScreen(): Boolean {
        return notchScreen
    }

    fun setNotchScreen(value: Boolean) {
        notchScreen = value
    }

    fun hasNavigationBar(): Boolean {
        return hasNavigationBar
    }

    fun setNavigationBar(value: Boolean) {
        hasNavigationBar = value
    }

    fun getStatusBarHeight(): Int {
        return statusBarHeight
    }

    fun setStatusBarHeight(value: Int) {
        statusBarHeight = value
    }

    fun getNavigationBarHeight(): Int {
        return navigationBarHeight
    }

    fun setNavigationBarHeight(value: Int) {
        navigationBarHeight = value
    }

    fun getNavigationBarWidth(): Int {
        return navigationBarWidth
    }

    fun setNavigationBarWidth(value: Int) {
        navigationBarWidth = value
    }

    fun getNotchHeight(): Int {
        return notchHeight
    }

    fun setNotchHeight(value: Int) {
        notchHeight = value
    }

    fun getActionBarHeight(): Int {
        return actionBarHeight
    }

    fun setActionBarHeight(value: Int) {
        actionBarHeight = value
    }
}
