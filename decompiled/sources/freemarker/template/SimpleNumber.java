package freemarker.template;

import java.io.Serializable;

public final class SimpleNumber implements TemplateNumberModel, Serializable {
    private Number value;

    public SimpleNumber(Number number) {
        this.value = number;
    }

    public SimpleNumber(byte b) {
        this.value = new Byte(b);
    }

    public SimpleNumber(short s) {
        this.value = new Short(s);
    }

    public SimpleNumber(int i) {
        this.value = new Integer(i);
    }

    public SimpleNumber(long j) {
        this.value = new Long(j);
    }

    public SimpleNumber(float f) {
        this.value = new Float(f);
    }

    public SimpleNumber(double d) {
        this.value = new Double(d);
    }

    public Number getAsNumber() {
        return this.value;
    }

    public String toString() {
        return this.value.toString();
    }
}
