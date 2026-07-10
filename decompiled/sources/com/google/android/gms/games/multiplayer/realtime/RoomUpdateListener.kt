package com.google.android.gms.games.multiplayer.realtime

@java.lang.Deprecated
interface RoomUpdateListener {
    fun onJoinedRoom(statusCode: Int, room: Room)

    fun onLeftRoom(statusCode: Int, roomId: String)

    fun onRoomConnected(statusCode: Int, room: Room)

    fun onRoomCreated(statusCode: Int, room: Room)
}
