package com.google.firebase.database

interface ChildEventListener {
    fun onCancelled(error: DatabaseError)

    fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?)

    fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?)

    fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?)

    fun onChildRemoved(snapshot: DataSnapshot)
}
