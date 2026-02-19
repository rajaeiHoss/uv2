package freemarker.template.utility;

import java.io.PrintStream;
import java.io.PrintWriter;

public class UndeclaredThrowableException extends RuntimeException {
    private final Throwable t;

    public UndeclaredThrowableException(Throwable th) {
        this.t = th;
    }

    public void printStackTrace() {
        printStackTrace(System.err);
    }

    public void printStackTrace(PrintStream printStream) {
        synchronized (printStream) {
            printStream.print("Undeclared throwable:");
            this.t.printStackTrace(printStream);
        }
    }

    public void printStackTrace(PrintWriter printWriter) {
        synchronized (printWriter) {
            printWriter.print("Undeclared throwable:");
            this.t.printStackTrace(printWriter);
        }
    }

    public Throwable getUndeclaredThrowable() {
        return this.t;
    }

    public Throwable getCause() {
        return this.t;
    }
}
