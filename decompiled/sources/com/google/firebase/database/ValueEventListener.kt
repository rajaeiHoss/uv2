package com.google.firebase.database

interface ValueEventListener {
    fun onCancelled(error: DatabaseError)

    fun onDataChange(snapshot: DataSnapshot)
}
