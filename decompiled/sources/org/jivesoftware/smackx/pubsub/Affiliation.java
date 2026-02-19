package org.jivesoftware.smackx.pubsub;

import com.google.firebase.analytics.FirebaseAnalytics;
import org.jivesoftware.smack.packet.PacketExtension;

public class Affiliation implements PacketExtension {
    protected String node;
    protected Type type;

    public enum Type {
        member,
        none,
        outcast,
        owner,
        publisher
    }

    public String getElementName() {
        return "subscription";
    }

    public String getNamespace() {
        return null;
    }

    public Affiliation(String str, Type type2) {
        this.node = str;
        this.type = type2;
    }

    public String getNodeId() {
        return this.node;
    }

    public Type getType() {
        return this.type;
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder("<");
        sb.append(getElementName());
        appendAttribute(sb, "node", this.node);
        appendAttribute(sb, FirebaseAnalytics.Param.AFFILIATION, this.type.toString());
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
