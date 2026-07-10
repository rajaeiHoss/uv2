package androidx.appcompat.view.menu

import android.widget.ListView

interface ShowableListMenu {
    fun dismiss()

    fun getListView(): ListView

    fun isShowing(): Boolean

    fun show()
}
