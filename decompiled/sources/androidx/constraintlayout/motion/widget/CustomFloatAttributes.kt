package androidx.constraintlayout.motion.widget

interface CustomFloatAttributes {
    fun get(attribute: String): Float

    fun getListOfAttributes(): Array<String>

    fun set(attribute: String, value: Float)
}
