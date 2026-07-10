package com.google.android.material.expandable

interface ExpandableTransformationWidget : ExpandableWidget {
    fun getExpandedComponentIdHint(): Int

    fun setExpandedComponentIdHint(expandedComponentIdHint: Int)
}
