package com.google.android.material.expandable

interface ExpandableWidget {
    fun isExpanded(): Boolean

    fun setExpanded(expanded: Boolean): Boolean
}
