package org.jivesoftware.smack.util;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ObservableWriter extends Writer {
    List listeners = new ArrayList();
    Writer wrappedWriter = null;

    public ObservableWriter(Writer writer) {
        this.wrappedWriter = writer;
    }

    public void write(char[] cArr, int i, int i2) throws IOException {
        this.wrappedWriter.write(cArr, i, i2);
        notifyListeners(new String(cArr, i, i2));
    }

    public void flush() throws IOException {
        this.wrappedWriter.flush();
    }

    public void close() throws IOException {
        this.wrappedWriter.close();
    }

    public void write(int i) throws IOException {
        this.wrappedWriter.write(i);
    }

    public void write(char[] cArr) throws IOException {
        this.wrappedWriter.write(cArr);
        notifyListeners(new String(cArr));
    }

    public void write(String str) throws IOException {
        this.wrappedWriter.write(str);
        notifyListeners(str);
    }

    public void write(String str, int i, int i2) throws IOException {
        this.wrappedWriter.write(str, i, i2);
        notifyListeners(str.substring(i, i2 + i));
    }

    private void notifyListeners(String str) {
        int size;
        WriterListener[] writerListenerArr;
        synchronized (this.listeners) {
            size = this.listeners.size();
            writerListenerArr = new WriterListener[size];
            this.listeners.toArray(writerListenerArr);
        }
        for (int i = 0; i < size; i++) {
            writerListenerArr[i].write(str);
        }
    }

    public void addWriterListener(WriterListener writerListener) {
        if (writerListener != null) {
            synchronized (this.listeners) {
                if (!this.listeners.contains(writerListener)) {
                    this.listeners.add(writerListener);
                }
            }
        }
    }

    public void removeWriterListener(WriterListener writerListener) {
        synchronized (this.listeners) {
            this.listeners.remove(writerListener);
        }
    }
}
