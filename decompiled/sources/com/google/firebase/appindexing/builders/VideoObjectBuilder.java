package com.google.firebase.appindexing.builders;

import java.util.Date;

public final class VideoObjectBuilder extends IndexableBuilder<VideoObjectBuilder> {
    VideoObjectBuilder() {
        super("VideoObject");
    }

    public final VideoObjectBuilder setAuthor(PersonBuilder personBuilder) {
        return (VideoObjectBuilder) put("author", new PersonBuilder[]{personBuilder});
    }

    public final VideoObjectBuilder setDuration(long j) {
        return (VideoObjectBuilder) put("duration", j);
    }

    public final VideoObjectBuilder setDurationWatched(long j) {
        return (VideoObjectBuilder) put("durationWatched", j);
    }

    public final VideoObjectBuilder setLocationCreated(PlaceBuilder placeBuilder) {
        return (VideoObjectBuilder) put("locationCreated", new PlaceBuilder[]{placeBuilder});
    }

    public final VideoObjectBuilder setSeriesName(String str) {
        return (VideoObjectBuilder) put("seriesName", str);
    }

    public final VideoObjectBuilder setUploadDate(Date date) {
        return (VideoObjectBuilder) put("uploadDate", date.getTime());
    }
}
