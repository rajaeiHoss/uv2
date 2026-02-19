package com.google.firebase.appindexing.builders;

public final class LocalBusinessBuilder extends IndexableBuilder<LocalBusinessBuilder> {
    LocalBusinessBuilder() {
        super("LocalBusiness");
    }

    LocalBusinessBuilder(String str) {
        super(str);
    }

    public final LocalBusinessBuilder setAddress(PostalAddressBuilder postalAddressBuilder) {
        return (LocalBusinessBuilder) put("address", new PostalAddressBuilder[]{postalAddressBuilder});
    }

    public final LocalBusinessBuilder setAggregateRating(AggregateRatingBuilder aggregateRatingBuilder) {
        return (LocalBusinessBuilder) put("aggregateRating", new AggregateRatingBuilder[]{aggregateRatingBuilder});
    }

    public final LocalBusinessBuilder setGeo(GeoShapeBuilder geoShapeBuilder) {
        return (LocalBusinessBuilder) put("geo", new GeoShapeBuilder[]{geoShapeBuilder});
    }

    public final LocalBusinessBuilder setPriceRange(String str) {
        return (LocalBusinessBuilder) put("priceRange", str);
    }

    public final LocalBusinessBuilder setTelephone(String str) {
        return (LocalBusinessBuilder) put("telephone", str);
    }
}
