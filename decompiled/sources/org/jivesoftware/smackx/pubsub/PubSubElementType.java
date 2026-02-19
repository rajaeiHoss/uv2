package org.jivesoftware.smackx.pubsub;

import org.jivesoftware.smackx.pubsub.packet.PubSubNamespace;

public enum PubSubElementType {
    CREATE("create", PubSubNamespace.BASIC),
    DELETE("delete", PubSubNamespace.OWNER),
    DELETE_EVENT("delete", PubSubNamespace.EVENT),
    CONFIGURE("configure", PubSubNamespace.BASIC),
    CONFIGURE_OWNER("configure", PubSubNamespace.OWNER),
    CONFIGURATION("configuration", PubSubNamespace.EVENT),
    OPTIONS("options", PubSubNamespace.BASIC),
    DEFAULT("default", PubSubNamespace.OWNER),
    ITEMS("items", PubSubNamespace.BASIC),
    PUBLISH("publish", PubSubNamespace.BASIC),
    PUBLISH_OPTIONS("publish-options", PubSubNamespace.BASIC),
    PURGE_OWNER("purge", PubSubNamespace.OWNER),
    PURGE_EVENT("purge", PubSubNamespace.EVENT),
    RETRACT("retract", PubSubNamespace.BASIC),
    AFFILIATIONS("affiliations", PubSubNamespace.BASIC),
    SUBSCRIBE("subscribe", PubSubNamespace.BASIC),
    SUBSCRIPTION("subscription", PubSubNamespace.BASIC),
    SUBSCRIPTIONS("subscriptions", PubSubNamespace.BASIC),
    SUBSCRIPTIONS_OWNER("subscriptions", PubSubNamespace.OWNER),
    UNSUBSCRIBE("unsubscribe", PubSubNamespace.BASIC);
    
    private String eName;
    private PubSubNamespace nSpace;

    private PubSubElementType(String str, PubSubNamespace pubSubNamespace) {
        this.eName = str;
        this.nSpace = pubSubNamespace;
    }

    public PubSubNamespace getNamespace() {
        return this.nSpace;
    }

    public String getElementName() {
        return this.eName;
    }

    public static PubSubElementType valueOfFromElemName(String str, String str2) {
        String str3;
        int lastIndexOf = str2.lastIndexOf(35);
        if (lastIndexOf == -1) {
            str3 = null;
        } else {
            str3 = str2.substring(lastIndexOf + 1);
        }
        if (str3 == null) {
            return valueOf(str.toUpperCase().replace('-', '_'));
        }
        return valueOf((str + '_' + str3).toUpperCase());
    }
}
