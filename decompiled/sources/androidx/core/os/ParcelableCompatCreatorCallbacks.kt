package androidx.core.os

import android.os.Parcel

@java.lang.Deprecated
interface ParcelableCompatCreatorCallbacks<T> {
    fun createFromParcel(parcel: Parcel, loader: ClassLoader?): T

    fun newArray(size: Int): Array<T?>
}
