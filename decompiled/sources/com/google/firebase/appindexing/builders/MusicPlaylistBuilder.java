package com.google.firebase.appindexing.builders;

public class MusicPlaylistBuilder extends IndexableBuilder<MusicPlaylistBuilder> {
    MusicPlaylistBuilder() {
        super("MusicPlaylist");
    }

    public MusicPlaylistBuilder setNumTracks(int i) {
        return (MusicPlaylistBuilder) put("numTracks", (long) i);
    }

    public MusicPlaylistBuilder setTrack(MusicRecordingBuilder... musicRecordingBuilderArr) {
        return (MusicPlaylistBuilder) put("track", musicRecordingBuilderArr);
    }
}
