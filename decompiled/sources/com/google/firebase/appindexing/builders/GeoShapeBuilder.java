package com.google.firebase.appindexing.builders;

public final class GeoShapeBuilder extends IndexableBuilder<GeoShapeBuilder> {
    GeoShapeBuilder() {
        super("GeoShape");
    }

    public final GeoShapeBuilder setBox(String str) {
        return (GeoShapeBuilder) put("box", str);
    }
}
