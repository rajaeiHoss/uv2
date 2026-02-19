package com.google.android.gms.games.multiplayer.realtime;

import java.util.List;

public abstract class RoomStatusUpdateCallback implements RoomStatusUpdateListener {
    public abstract void onConnectedToRoom(Room room);

    public abstract void onDisconnectedFromRoom(Room room);

    public abstract void onP2PConnected(String str);

    public abstract void onP2PDisconnected(String str);

    public abstract void onPeerDeclined(Room room, List<String> list);

    public abstract void onPeerInvitedToRoom(Room room, List<String> list);

    public abstract void onPeerJoined(Room room, List<String> list);

    public abstract void onPeerLeft(Room room, List<String> list);

    public abstract void onPeersConnected(Room room, List<String> list);

    public abstract void onPeersDisconnected(Room room, List<String> list);

    public abstract void onRoomAutoMatching(Room room);

    public abstract void onRoomConnecting(Room room);
}
