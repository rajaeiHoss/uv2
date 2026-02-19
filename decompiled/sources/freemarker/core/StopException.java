package freemarker.core;

import freemarker.template.TemplateException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class StopException extends TemplateException {
    StopException(Environment environment) {
        super(environment);
    }

    StopException(Environment environment, String str) {
        super(str, environment);
    }

    public void printStackTrace(PrintWriter printWriter) {
        synchronized (printWriter) {
            String message = getMessage();
            printWriter.print("Encountered stop instruction");
            if (message == null || message.equals("")) {
                printWriter.println();
            } else {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("\nCause given: ");
                stringBuffer.append(message);
                printWriter.println(stringBuffer.toString());
            }
            super.printStackTrace(printWriter);
        }
    }

    public void printStackTrace(PrintStream printStream) {
        synchronized (printStream) {
            String message = getMessage();
            printStream.print("Encountered stop instruction");
            if (message == null || message.equals("")) {
                printStream.println();
            } else {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("\nCause given: ");
                stringBuffer.append(message);
                printStream.println(stringBuffer.toString());
            }
            super.printStackTrace(printStream);
        }
    }
}
