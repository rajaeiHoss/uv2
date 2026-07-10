package androidx.constraintlayout.solver.state

import androidx.constraintlayout.solver.widgets.ConstraintWidget

interface Reference {
    fun apply()

    fun getConstraintWidget(): ConstraintWidget?

    fun getKey(): Any?

    fun setConstraintWidget(constraintWidget: ConstraintWidget?)

    fun setKey(key: Any?)
}
