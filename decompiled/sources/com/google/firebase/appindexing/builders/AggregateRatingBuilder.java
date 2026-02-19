package com.google.firebase.appindexing.builders;

public final class AggregateRatingBuilder extends IndexableBuilder<AggregateRatingBuilder> {
    AggregateRatingBuilder() {
        super("AggregateRating");
    }

    public final AggregateRatingBuilder setRatingCount(long j) {
        return (AggregateRatingBuilder) put("ratingCount", j);
    }

    public final AggregateRatingBuilder setRatingValue(String str) {
        return (AggregateRatingBuilder) put("ratingValue", str);
    }
}
