package com.google.firebase.appindexing.builders;

public final class StickerPackBuilder extends IndexableBuilder<StickerPackBuilder> {
    StickerPackBuilder() {
        super("StickerPack");
    }

    public final StickerPackBuilder setHasSticker(StickerBuilder... stickerBuilderArr) {
        return (StickerPackBuilder) put("hasSticker", stickerBuilderArr);
    }
}
