package com.google.zxing.oned.rss.expanded.decoders;

final class BlockParsedResult {
    private final DecodedInformation decodedInformation;
    private final boolean finished;

    BlockParsedResult() {
        this.finished = true;
        this.decodedInformation = null;
    }

    BlockParsedResult(DecodedInformation decodedInformation2, boolean z) {
        this.finished = z;
        this.decodedInformation = decodedInformation2;
    }

    BlockParsedResult(boolean z) {
        this.finished = z;
        this.decodedInformation = null;
    }

    /* access modifiers changed from: package-private */
    public DecodedInformation getDecodedInformation() {
        return this.decodedInformation;
    }

    /* access modifiers changed from: package-private */
    public boolean isFinished() {
        return this.finished;
    }
}
