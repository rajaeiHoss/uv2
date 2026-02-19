package com.google.firebase.appindexing.builders;

import java.util.Date;

public final class PhotographBuilder extends IndexableBuilder<PhotographBuilder> {
    PhotographBuilder() {
        super("Photograph");
    }

    public final PhotographBuilder setDateCreated(Date date) {
        return (PhotographBuilder) put("dateCreated", date.getTime());
    }

    public final PhotographBuilder setLocationCreated(PlaceBuilder placeBuilder) {
        return (PhotographBuilder) put("locationCreated", new PlaceBuilder[]{placeBuilder});
    }
}
