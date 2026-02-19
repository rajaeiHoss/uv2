package org.kobjects.io;

import java.io.IOException;
import java.io.InputStream;

public class BoundInputStream extends InputStream {
    InputStream is;
    int remaining;

    public BoundInputStream(InputStream inputStream, int i) {
        this.is = inputStream;
        this.remaining = i;
    }

    public int available() throws IOException {
        int available = this.is.available();
        int i = this.remaining;
        return available < i ? available : i;
    }

    public int read() throws IOException {
        int i = this.remaining;
        if (i <= 0) {
            return -1;
        }
        this.remaining = i - 1;
        return this.is.read();
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.remaining;
        if (i2 > i3) {
            i2 = i3;
        }
        int read = this.is.read(bArr, i, i2);
        if (read > 0) {
            this.remaining -= read;
        }
        return read;
    }

    public void close() {
        try {
            this.is.close();
        } catch (IOException unused) {
        }
    }
}
