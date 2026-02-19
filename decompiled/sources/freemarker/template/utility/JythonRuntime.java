package freemarker.template.utility;

import freemarker.core.Environment;
import freemarker.template.TemplateTransformModel;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

public class JythonRuntime extends PythonInterpreter implements TemplateTransformModel {
    public Writer getWriter(final Writer writer, Map map) {
        final StringBuffer stringBuffer = new StringBuffer();
        final Environment currentEnvironment = Environment.getCurrentEnvironment();
        return new Writer() {
            public void write(char[] cArr, int i, int i2) {
                stringBuffer.append(cArr, i, i2);
            }

            public void flush() throws IOException {
                interpretBuffer();
                writer.flush();
            }

            public void close() {
                interpretBuffer();
            }

            private void interpretBuffer() {
                synchronized (JythonRuntime.this) {
                    PyObject pyObject = JythonRuntime.this.systemState.stdout;
                    try {
                        JythonRuntime.this.setOut(writer);
                        JythonRuntime.this.set("env", currentEnvironment);
                        JythonRuntime.this.exec(stringBuffer.toString());
                        stringBuffer.setLength(0);
                    } finally {
                        JythonRuntime.this.setOut(pyObject);
                    }
                }
            }
        };
    }
}
