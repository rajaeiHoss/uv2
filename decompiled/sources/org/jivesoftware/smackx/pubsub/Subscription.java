package org.jivesoftware.smackx.pubsub;

public class Subscription extends NodeExtension {
    protected boolean configRequired;
    protected String id;
    protected String jid;
    protected State state;

    public enum State {
        subscribed,
        unconfigured,
        pending,
        none
    }

    public Subscription(String str) {
        this(str, (String) null, (String) null, (State) null);
    }

    public Subscription(String str, String str2) {
        this(str, str2, (String) null, (State) null);
    }

    public Subscription(String str, String str2, String str3, State state2) {
        super(PubSubElementType.SUBSCRIPTION, str2);
        this.configRequired = false;
        this.jid = str;
        this.id = str3;
        this.state = state2;
    }

    public Subscription(String str, String str2, String str3, State state2, boolean z) {
        super(PubSubElementType.SUBSCRIPTION, str2);
        this.configRequired = false;
        this.jid = str;
        this.id = str3;
        this.state = state2;
        this.configRequired = z;
    }

    public String getJid() {
        return this.jid;
    }

    public String getId() {
        return this.id;
    }

    public State getState() {
        return this.state;
    }

    public boolean isConfigRequired() {
        return this.configRequired;
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder("<subscription");
        appendAttribute(sb, "jid", this.jid);
        if (getNode() != null) {
            appendAttribute(sb, "node", getNode());
        }
        String str = this.id;
        if (str != null) {
            appendAttribute(sb, "subid", str);
        }
        State state2 = this.state;
        if (state2 != null) {
            appendAttribute(sb, "subscription", state2.toString());
        }
        sb.append("/>");
        return sb.toString();
    }

    private void appendAttribute(StringBuilder sb, String str, String str2) {
        sb.append(" ");
        sb.append(str);
        sb.append("='");
        sb.append(str2);
        sb.append("'");
    }
}
