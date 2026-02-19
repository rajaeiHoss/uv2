package freemarker.template;

import java.util.List;

public class TemplateModelListSequence implements TemplateSequenceModel {
    private List list;

    public TemplateModelListSequence(List list2) {
        this.list = list2;
    }

    public TemplateModel get(int i) {
        return (TemplateModel) this.list.get(i);
    }

    public int size() {
        return this.list.size();
    }

    public Object getWrappedObject() {
        return this.list;
    }
}
