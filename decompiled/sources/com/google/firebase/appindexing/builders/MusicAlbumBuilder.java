package com.google.firebase.appindexing.builders;

public final class MusicAlbumBuilder extends IndexableBuilder<MusicAlbumBuilder> {
    MusicAlbumBuilder() {
        super("MusicAlbum");
    }

    public final MusicAlbumBuilder setByArtist(MusicGroupBuilder musicGroupBuilder) {
        return (MusicAlbumBuilder) put("byArtist", new MusicGroupBuilder[]{musicGroupBuilder});
    }

    public final MusicAlbumBuilder setNumTracks(int i) {
        return (MusicAlbumBuilder) put("numTracks", (long) i);
    }

    public final MusicAlbumBuilder setTrack(MusicRecordingBuilder... musicRecordingBuilderArr) {
        return (MusicAlbumBuilder) put("track", musicRecordingBuilderArr);
    }
}
