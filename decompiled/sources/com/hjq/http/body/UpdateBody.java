package com.hjq.http.body;

import com.hjq.http.EasyUtils;
import com.hjq.http.model.ContentType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

public class UpdateBody extends RequestBody {
    private final String mKeyName;
    private final long mLength;
    private final MediaType mMediaType;
    private final Source mSource;

    public UpdateBody(File file) throws FileNotFoundException {
        this(Okio.source(file), ContentType.guessMimeType(file.getName()), file.getName(), file.length());
    }

    public UpdateBody(InputStream inputStream, String str) throws IOException {
        this(Okio.source(inputStream), ContentType.STREAM, str, (long) inputStream.available());
    }

    public UpdateBody(Source source, MediaType mediaType, String str, long j) {
        this.mSource = source;
        this.mMediaType = mediaType;
        this.mKeyName = str;
        this.mLength = j;
    }

    public MediaType contentType() {
        return this.mMediaType;
    }

    public long contentLength() {
        long j = this.mLength;
        if (j == 0) {
            return -1;
        }
        return j;
    }

    public void writeTo(BufferedSink bufferedSink) throws IOException {
        try {
            bufferedSink.writeAll(this.mSource);
        } finally {
            EasyUtils.closeStream(this.mSource);
        }
    }

    public String getKeyName() {
        return this.mKeyName;
    }
}
