package freemarker.template;

import java.io.Serializable;

public final class SimpleScalar implements TemplateScalarModel, Serializable {
    private String value;

    public SimpleScalar(String str) {
        this.value = str;
    }

    public String getAsString() {
        String str = this.value;
        return str == null ? "" : str;
    }

    public String toString() {
        return this.value;
    }
}
