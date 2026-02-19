package freemarker.core;

import freemarker.template.utility.StringUtil;

public final class Comment extends TemplateElement {
    private final String text;

    /* access modifiers changed from: package-private */
    public void accept(Environment environment) {
    }

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return "#--...--";
    }

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 1;
    }

    Comment(String str) {
        this.text = str;
    }

    /* access modifiers changed from: protected */
    public String dump(boolean z) {
        if (z) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("<#--");
            stringBuffer.append(this.text);
            stringBuffer.append("-->");
            return stringBuffer.toString();
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("comment ");
        stringBuffer2.append(StringUtil.jQuote(this.text.trim()));
        return stringBuffer2.toString();
    }

    /* access modifiers changed from: package-private */
    public Object getParameterValue(int i) {
        if (i == 0) {
            return this.text;
        }
        throw new IndexOutOfBoundsException();
    }

    /* access modifiers changed from: package-private */
    public ParameterRole getParameterRole(int i) {
        if (i == 0) {
            return ParameterRole.CONTENT;
        }
        throw new IndexOutOfBoundsException();
    }

    public String getText() {
        return this.text;
    }
}
