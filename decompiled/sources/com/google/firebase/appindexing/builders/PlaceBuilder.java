package com.google.firebase.appindexing.builders;

public final class PlaceBuilder extends IndexableBuilder<PlaceBuilder> {
    PlaceBuilder() {
        super("Place");
    }

    public final PlaceBuilder setGeo(GeoShapeBuilder geoShapeBuilder) {
        return (PlaceBuilder) put("geo", new GeoShapeBuilder[]{geoShapeBuilder});
    }
}
