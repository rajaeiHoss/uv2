package org.jivesoftware.smackx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.util.StringUtils;

public class FormField {
    public static final String TYPE_BOOLEAN = "boolean";
    public static final String TYPE_FIXED = "fixed";
    public static final String TYPE_HIDDEN = "hidden";
    public static final String TYPE_JID_MULTI = "jid-multi";
    public static final String TYPE_JID_SINGLE = "jid-single";
    public static final String TYPE_LIST_MULTI = "list-multi";
    public static final String TYPE_LIST_SINGLE = "list-single";
    public static final String TYPE_TEXT_MULTI = "text-multi";
    public static final String TYPE_TEXT_PRIVATE = "text-private";
    public static final String TYPE_TEXT_SINGLE = "text-single";
    private String description;
    private String label;
    private final List<Option> options;
    private boolean required;
    private String type;
    private final List<String> values;
    private String variable;

    public FormField(String str) {
        this.required = false;
        this.options = new ArrayList();
        this.values = new ArrayList();
        this.variable = str;
    }

    public FormField() {
        this.required = false;
        this.options = new ArrayList();
        this.values = new ArrayList();
        this.type = TYPE_FIXED;
    }

    public String getDescription() {
        return this.description;
    }

    public String getLabel() {
        return this.label;
    }

    public Iterator<Option> getOptions() {
        Iterator<Option> it;
        synchronized (this.options) {
            it = Collections.unmodifiableList(new ArrayList(this.options)).iterator();
        }
        return it;
    }

    public boolean isRequired() {
        return this.required;
    }

    public String getType() {
        return this.type;
    }

    public Iterator<String> getValues() {
        Iterator<String> it;
        synchronized (this.values) {
            it = Collections.unmodifiableList(new ArrayList(this.values)).iterator();
        }
        return it;
    }

    public String getVariable() {
        return this.variable;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public void setRequired(boolean z) {
        this.required = z;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void addValue(String str) {
        synchronized (this.values) {
            this.values.add(str);
        }
    }

    public void addValues(List<String> list) {
        synchronized (this.values) {
            this.values.addAll(list);
        }
    }

    /* access modifiers changed from: protected */
    public void resetValues() {
        synchronized (this.values) {
            this.values.removeAll(new ArrayList(this.values));
        }
    }

    public void addOption(Option option) {
        synchronized (this.options) {
            this.options.add(option);
        }
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<field");
        if (getLabel() != null) {
            sb.append(" label=\"");
            sb.append(getLabel());
            sb.append("\"");
        }
        if (getVariable() != null) {
            sb.append(" var=\"");
            sb.append(getVariable());
            sb.append("\"");
        }
        if (getType() != null) {
            sb.append(" type=\"");
            sb.append(getType());
            sb.append("\"");
        }
        sb.append(">");
        if (getDescription() != null) {
            sb.append("<desc>");
            sb.append(getDescription());
            sb.append("</desc>");
        }
        if (isRequired()) {
            sb.append("<required/>");
        }
        Iterator<String> values2 = getValues();
        while (values2.hasNext()) {
            sb.append("<value>");
            sb.append(values2.next());
            sb.append("</value>");
        }
        Iterator<Option> options2 = getOptions();
        while (options2.hasNext()) {
            sb.append(options2.next().toXML());
        }
        sb.append("</field>");
        return sb.toString();
    }

    public static class Option {
        private String label;
        private String value;

        public Option(String str) {
            this.value = str;
        }

        public Option(String str, String str2) {
            this.label = str;
            this.value = str2;
        }

        public String getLabel() {
            return this.label;
        }

        public String getValue() {
            return this.value;
        }

        public String toString() {
            return getLabel();
        }

        public String toXML() {
            StringBuilder sb = new StringBuilder();
            sb.append("<option");
            if (getLabel() != null) {
                sb.append(" label=\"");
                sb.append(getLabel());
                sb.append("\"");
            }
            sb.append(">");
            sb.append("<value>");
            sb.append(StringUtils.escapeForXML(getValue()));
            sb.append("</value>");
            sb.append("</option>");
            return sb.toString();
        }
    }
}
