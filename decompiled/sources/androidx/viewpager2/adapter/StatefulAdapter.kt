package androidx.viewpager2.adapter

import android.os.Parcelable

interface StatefulAdapter {
    fun restoreState(savedState: Parcelable)

    fun saveState(): Parcelable
}
