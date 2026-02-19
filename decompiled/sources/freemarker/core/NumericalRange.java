package freemarker.core;

import freemarker.template.SimpleNumber;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateSequenceModel;
import java.io.Serializable;

class NumericalRange implements TemplateSequenceModel, Serializable {
    private boolean descending;
    private int lower;
    private boolean norhs;
    private int upper;

    public NumericalRange(int i) {
        this.norhs = true;
        this.lower = i;
    }

    public NumericalRange(int i, int i2) {
        this.lower = Math.min(i, i2);
        this.upper = Math.max(i, i2);
        this.descending = i != this.lower;
    }

    public TemplateModel get(int i) throws TemplateModelException {
        int i2 = this.descending ? this.upper - i : this.lower + i;
        if ((!this.norhs || i2 <= this.upper) && i2 >= this.lower) {
            return new SimpleNumber(i2);
        }
        throw new _TemplateModelException(new Object[]{"Range item index ", new Integer(i), " is out of bounds."});
    }

    public int size() {
        return (this.upper + 1) - this.lower;
    }

    /* access modifiers changed from: package-private */
    public boolean hasRhs() {
        return !this.norhs;
    }
}
