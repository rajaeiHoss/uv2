package org.jivesoftware.smackx.workgroup.packet;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

public class AgentInfo extends IQ {
    public static final String ELEMENT_NAME = "agent-info";
    public static final String NAMESPACE = "http://jivesoftware.com/protocol/workgroup";
    private String jid;
    private String name;

    public String getJid() {
        return this.jid;
    }

    public void setJid(String str) {
        this.jid = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getChildElementXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(ELEMENT_NAME);
        sb.append(" xmlns=\"");
        sb.append("http://jivesoftware.com/protocol/workgroup");
        sb.append("\">");
        if (this.jid != null) {
            sb.append("<jid>");
            sb.append(getJid());
            sb.append("</jid>");
        }
        if (this.name != null) {
            sb.append("<name>");
            sb.append(getName());
            sb.append("</name>");
        }
        sb.append("</");
        sb.append(ELEMENT_NAME);
        sb.append("> ");
        return sb.toString();
    }

    public static class Provider implements IQProvider {
        public IQ parseIQ(XmlPullParser xmlPullParser) throws Exception {
            AgentInfo agentInfo = new AgentInfo();
            boolean z = false;
            while (!z) {
                int next = xmlPullParser.next();
                if (next == 2) {
                    if (xmlPullParser.getName().equals("jid")) {
                        agentInfo.setJid(xmlPullParser.nextText());
                    } else if (xmlPullParser.getName().equals("name")) {
                        agentInfo.setName(xmlPullParser.nextText());
                    }
                } else if (next == 3 && xmlPullParser.getName().equals(AgentInfo.ELEMENT_NAME)) {
                    z = true;
                }
            }
            return agentInfo;
        }
    }
}
