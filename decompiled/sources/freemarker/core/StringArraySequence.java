package freemarker.core;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateScalarModel;
import freemarker.template.TemplateSequenceModel;

public class StringArraySequence implements TemplateSequenceModel {
    private TemplateScalarModel[] array;
    private String[] stringArray;

    public StringArraySequence(String[] strArr) {
        this.stringArray = strArr;
    }

    public TemplateModel get(int i) {
        if (this.array == null) {
            this.array = new TemplateScalarModel[this.stringArray.length];
        }
        TemplateScalarModel templateScalarModel = this.array[i];
        if (templateScalarModel != null) {
            return templateScalarModel;
        }
        SimpleScalar simpleScalar = new SimpleScalar(this.stringArray[i]);
        this.array[i] = simpleScalar;
        return simpleScalar;
    }

    public int size() {
        return this.stringArray.length;
    }
}
