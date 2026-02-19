package com.google.firebase.appindexing.builders;

public final class MusicRecordingBuilder extends IndexableBuilder<MusicRecordingBuilder> {
    MusicRecordingBuilder() {
        super("MusicRecording");
    }

    public final MusicRecordingBuilder setByArtist(MusicGroupBuilder musicGroupBuilder) {
        return (MusicRecordingBuilder) put("byArtist", new MusicGroupBuilder[]{musicGroupBuilder});
    }

    public final MusicRecordingBuilder setDuration(int i) {
        return (MusicRecordingBuilder) put("duration", (long) i);
    }

    public final MusicRecordingBuilder setInAlbum(MusicAlbumBuilder musicAlbumBuilder) {
        return (MusicRecordingBuilder) put("inAlbum", new MusicAlbumBuilder[]{musicAlbumBuilder});
    }

    public final MusicRecordingBuilder setInPlaylist(MusicPlaylistBuilder... musicPlaylistBuilderArr) {
        return (MusicRecordingBuilder) put("inPlaylist", musicPlaylistBuilderArr);
    }
}
