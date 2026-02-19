package org.jivesoftware.smack.packet;

public class PrivacyItem {
    private boolean allow;
    private boolean filterIQ = false;
    private boolean filterMessage = false;
    private boolean filterPresence_in = false;
    private boolean filterPresence_out = false;
    private int order;
    private PrivacyRule rule;

    public enum Type {
        group,
        jid,
        subscription
    }

    public PrivacyItem(String str, boolean z, int i) {
        setRule(PrivacyRule.fromString(str));
        setAllow(z);
        setOrder(i);
    }

    public boolean isAllow() {
        return this.allow;
    }

    private void setAllow(boolean z) {
        this.allow = z;
    }

    public boolean isFilterIQ() {
        return this.filterIQ;
    }

    public void setFilterIQ(boolean z) {
        this.filterIQ = z;
    }

    public boolean isFilterMessage() {
        return this.filterMessage;
    }

    public void setFilterMessage(boolean z) {
        this.filterMessage = z;
    }

    public boolean isFilterPresence_in() {
        return this.filterPresence_in;
    }

    public void setFilterPresence_in(boolean z) {
        this.filterPresence_in = z;
    }

    public boolean isFilterPresence_out() {
        return this.filterPresence_out;
    }

    public void setFilterPresence_out(boolean z) {
        this.filterPresence_out = z;
    }

    public int getOrder() {
        return this.order;
    }

    private void setOrder(int i) {
        this.order = i;
    }

    public void setValue(String str) {
        if (getRule() != null || str != null) {
            getRule().setValue(str);
        }
    }

    public Type getType() {
        if (getRule() == null) {
            return null;
        }
        return getRule().getType();
    }

    public String getValue() {
        if (getRule() == null) {
            return null;
        }
        return getRule().getValue();
    }

    public boolean isFilterEverything() {
        return !isFilterIQ() && !isFilterMessage() && !isFilterPresence_in() && !isFilterPresence_out();
    }

    private PrivacyRule getRule() {
        return this.rule;
    }

    private void setRule(PrivacyRule privacyRule) {
        this.rule = privacyRule;
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<item");
        if (isAllow()) {
            sb.append(" action=\"allow\"");
        } else {
            sb.append(" action=\"deny\"");
        }
        sb.append(" order=\"");
        sb.append(getOrder());
        sb.append("\"");
        if (getType() != null) {
            sb.append(" type=\"");
            sb.append(getType());
            sb.append("\"");
        }
        if (getValue() != null) {
            sb.append(" value=\"");
            sb.append(getValue());
            sb.append("\"");
        }
        if (isFilterEverything()) {
            sb.append("/>");
        } else {
            sb.append(">");
            if (isFilterIQ()) {
                sb.append("<iq/>");
            }
            if (isFilterMessage()) {
                sb.append("<message/>");
            }
            if (isFilterPresence_in()) {
                sb.append("<presence-in/>");
            }
            if (isFilterPresence_out()) {
                sb.append("<presence-out/>");
            }
            sb.append("</item>");
        }
        return sb.toString();
    }

    public static class PrivacyRule {
        public static final String SUBSCRIPTION_BOTH = "both";
        public static final String SUBSCRIPTION_FROM = "from";
        public static final String SUBSCRIPTION_NONE = "none";
        public static final String SUBSCRIPTION_TO = "to";
        private Type type;
        private String value;

        protected static PrivacyRule fromString(String str) {
            if (str == null) {
                return null;
            }
            PrivacyRule privacyRule = new PrivacyRule();
            privacyRule.setType(Type.valueOf(str.toLowerCase()));
            return privacyRule;
        }

        public Type getType() {
            return this.type;
        }

        private void setType(Type type2) {
            this.type = type2;
        }

        public String getValue() {
            return this.value;
        }

        /* access modifiers changed from: protected */
        public void setValue(String str) {
            if (isSuscription()) {
                setSuscriptionValue(str);
            } else {
                this.value = str;
            }
        }

        private void setSuscriptionValue(String str) {
            String str2 = SUBSCRIPTION_BOTH;
            if (!str2.equalsIgnoreCase(str)) {
                if ("to".equalsIgnoreCase(str)) {
                    str2 = "to";
                } else {
                    str2 = SUBSCRIPTION_FROM.equalsIgnoreCase(str) ? SUBSCRIPTION_FROM : SUBSCRIPTION_NONE.equalsIgnoreCase(str) ? SUBSCRIPTION_NONE : null;
                }
            }
            this.value = str2;
        }

        public boolean isSuscription() {
            return getType() == Type.subscription;
        }
    }
}
