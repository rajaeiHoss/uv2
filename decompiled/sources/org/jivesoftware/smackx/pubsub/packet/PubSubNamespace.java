package org.jivesoftware.smackx.pubsub.packet;

public enum PubSubNamespace {
    BASIC((String) null),
    ERROR("errors"),
    EVENT("event"),
    OWNER("owner");
    
    private String fragment;

    private PubSubNamespace(String str) {
        this.fragment = str;
    }

    public String getXmlns() {
        if (this.fragment == null) {
            return "http://jabber.org/protocol/pubsub";
        }
        return "http://jabber.org/protocol/pubsub" + '#' + this.fragment;
    }

    public String getFragment() {
        return this.fragment;
    }

    public static PubSubNamespace valueOfFromXmlns(String str) {
        if (str.lastIndexOf(35) != -1) {
            return valueOf(str.substring(str.lastIndexOf(35) + 1).toUpperCase());
        }
        return BASIC;
    }
}
