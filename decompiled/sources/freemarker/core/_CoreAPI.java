package freemarker.core;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.Set;

public class _CoreAPI {
    public static final String STACK_SECTION_SEPARATOR = "----------";

    private _CoreAPI() {
    }

    public static Set getSupportedBuiltInNames() {
        return Collections.unmodifiableSet(BuiltIn.builtins.keySet());
    }

    public static String instructionStackItemToString(TemplateElement templateElement) {
        return Environment.instructionStackItemToString(templateElement);
    }

    public static TemplateElement[] getInstructionStackSnapshot(Environment environment) {
        return environment.getInstructionStackSnapshot();
    }

    public static void outputInstructionStack(TemplateElement[] templateElementArr, PrintWriter printWriter) {
        Environment.outputInstructionStack(templateElementArr, printWriter);
    }
}
