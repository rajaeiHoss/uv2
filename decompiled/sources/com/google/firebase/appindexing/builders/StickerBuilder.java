package com.google.firebase.appindexing.builders;

public final class StickerBuilder extends IndexableBuilder<StickerBuilder> {
    StickerBuilder() {
        super("Sticker");
    }

    public final StickerBuilder setIsPartOf(StickerPackBuilder stickerPackBuilder) {
        return (StickerBuilder) put("isPartOf", new StickerPackBuilder[]{stickerPackBuilder});
    }
}
