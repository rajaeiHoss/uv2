package com.hjq.http.body;

import com.hjq.http.model.ContentType;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

public class StringBody extends RequestBody {
    private final byte[] mBytes;
    private final String mText;

    public StringBody() {
        this("");
    }

    public StringBody(String str) {
        this.mText = str;
        this.mBytes = str.getBytes();
    }

    public MediaType contentType() {
        return ContentType.TEXT;
    }

    public long contentLength() {
        return (long) this.mBytes.length;
    }

    public void writeTo(BufferedSink bufferedSink) throws IOException {
        byte[] bArr = this.mBytes;
        bufferedSink.write(bArr, 0, bArr.length);
    }

    public String toString() {
        return this.mText;
    }
}
