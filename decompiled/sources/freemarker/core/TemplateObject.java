package freemarker.core;

import freemarker.template.Template;

public abstract class TemplateObject {
    static final int RUNTIME_EVAL_LINE_DISPLACEMENT = -1000000000;
    int beginColumn;
    int beginLine;
    int endColumn;
    int endLine;
    private Template template;

    public abstract String getCanonicalForm();

    /* access modifiers changed from: package-private */
    public abstract String getNodeTypeSymbol();

    /* access modifiers changed from: package-private */
    public abstract int getParameterCount();

    /* access modifiers changed from: package-private */
    public abstract ParameterRole getParameterRole(int i);

    /* access modifiers changed from: package-private */
    public abstract Object getParameterValue(int i);

    /* access modifiers changed from: package-private */
    public final void setLocation(Template template2, Token token, Token token2) throws ParseException {
        setLocation(template2, token.beginColumn, token.beginLine, token2.endColumn, token2.endLine);
    }

    /* access modifiers changed from: package-private */
    public final void setLocation(Template template2, Token token, TemplateObject templateObject) throws ParseException {
        setLocation(template2, token.beginColumn, token.beginLine, templateObject.endColumn, templateObject.endLine);
    }

    /* access modifiers changed from: package-private */
    public final void setLocation(Template template2, TemplateObject templateObject, Token token) throws ParseException {
        setLocation(template2, templateObject.beginColumn, templateObject.beginLine, token.endColumn, token.endLine);
    }

    /* access modifiers changed from: package-private */
    public final void setLocation(Template template2, TemplateObject templateObject, TemplateObject templateObject2) throws ParseException {
        setLocation(template2, templateObject.beginColumn, templateObject.beginLine, templateObject2.endColumn, templateObject2.endLine);
    }

    /* access modifiers changed from: package-private */
    public void setLocation(Template template2, int i, int i2, int i3, int i4) throws ParseException {
        this.template = template2;
        this.beginColumn = i;
        this.beginLine = i2;
        this.endColumn = i3;
        this.endLine = i4;
    }

    public final int getBeginColumn() {
        return this.beginColumn;
    }

    public final int getBeginLine() {
        return this.beginLine;
    }

    public final int getEndColumn() {
        return this.endColumn;
    }

    public final int getEndLine() {
        return this.endLine;
    }

    public String getStartLocation() {
        return MessageUtil.formatLocationForEvaluationError(this.template, this.beginLine, this.beginColumn);
    }

    public String getStartLocationQuoted() {
        return getStartLocation();
    }

    public String getEndLocation() {
        return MessageUtil.formatLocationForEvaluationError(this.template, this.endLine, this.endColumn);
    }

    public String getEndLocationQuoted() {
        return getEndLocation();
    }

    public final String getSource() {
        Template template2 = this.template;
        String source = template2 != null ? template2.getSource(this.beginColumn, this.beginLine, this.endColumn, this.endLine) : null;
        return source != null ? source : getCanonicalForm();
    }

    public String toString() {
        String str;
        try {
            str = getSource();
        } catch (Exception unused) {
            str = null;
        }
        return str != null ? str : getCanonicalForm();
    }

    public boolean contains(int i, int i2) {
        int i3;
        int i4 = this.beginLine;
        if (i2 < i4 || i2 > (i3 = this.endLine)) {
            return false;
        }
        if (i2 == i4 && i < this.beginColumn) {
            return false;
        }
        if (i2 != i3 || i <= this.endColumn) {
            return true;
        }
        return false;
    }

    public Template getTemplate() {
        return this.template;
    }

    /* access modifiers changed from: package-private */
    public TemplateObject copyLocationFrom(TemplateObject templateObject) {
        this.template = templateObject.template;
        this.beginColumn = templateObject.beginColumn;
        this.beginLine = templateObject.beginLine;
        this.endColumn = templateObject.endColumn;
        this.endLine = templateObject.endLine;
        return this;
    }
}
