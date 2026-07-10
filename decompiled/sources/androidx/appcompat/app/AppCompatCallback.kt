package androidx.appcompat.app

import androidx.appcompat.view.ActionMode

interface AppCompatCallback {
    fun onSupportActionModeFinished(mode: ActionMode)

    fun onSupportActionModeStarted(mode: ActionMode)

    fun onWindowStartingSupportActionMode(callback: ActionMode.Callback): ActionMode?
}
