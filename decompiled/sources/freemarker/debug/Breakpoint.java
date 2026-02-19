package freemarker.debug;

import java.io.Serializable;

public class Breakpoint implements Serializable, Comparable {
    private static final long serialVersionUID = 1;
    private final int line;
    private final String templateName;

    public Breakpoint(String str, int i) {
        this.templateName = str;
        this.line = i;
    }

    public int getLine() {
        return this.line;
    }

    public String getTemplateName() {
        return this.templateName;
    }

    public int hashCode() {
        return this.templateName.hashCode() + (this.line * 31);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Breakpoint)) {
            return false;
        }
        Breakpoint breakpoint = (Breakpoint) obj;
        if (!breakpoint.templateName.equals(this.templateName) || breakpoint.line != this.line) {
            return false;
        }
        return true;
    }

    public int compareTo(Object obj) {
        Breakpoint breakpoint = (Breakpoint) obj;
        int compareTo = this.templateName.compareTo(breakpoint.templateName);
        return compareTo == 0 ? this.line - breakpoint.line : compareTo;
    }

    public String getLocationString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.templateName);
        stringBuffer.append(":");
        stringBuffer.append(this.line);
        return stringBuffer.toString();
    }
}
