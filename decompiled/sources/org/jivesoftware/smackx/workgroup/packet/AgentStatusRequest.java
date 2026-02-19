package org.jivesoftware.smackx.workgroup.packet;

import com.google.android.gms.measurement.AppMeasurement;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

public class AgentStatusRequest extends IQ {
    public static final String ELEMENT_NAME = "agent-status-request";
    public static final String NAMESPACE = "http://jabber.org/protocol/workgroup";
    /* access modifiers changed from: private */
    public Set<Item> agents = new HashSet();

    public String getElementName() {
        return ELEMENT_NAME;
    }

    public String getNamespace() {
        return "http://jabber.org/protocol/workgroup";
    }

    public int getAgentCount() {
        return this.agents.size();
    }

    public Set<Item> getAgents() {
        return Collections.unmodifiableSet(this.agents);
    }

    public String getChildElementXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(ELEMENT_NAME);
        sb.append(" xmlns=\"");
        sb.append("http://jabber.org/protocol/workgroup");
        sb.append("\">");
        synchronized (this.agents) {
            for (Item next : this.agents) {
                sb.append("<agent jid=\"");
                sb.append(next.getJID());
                sb.append("\">");
                if (next.getName() != null) {
                    sb.append("<name xmlns=\"http://jivesoftware.com/protocol/workgroup\">");
                    sb.append(next.getName());
                    sb.append("</name>");
                }
                sb.append("</agent>");
            }
        }
        sb.append("</");
        sb.append(getElementName());
        sb.append("> ");
        return sb.toString();
    }

    public static class Item {
        private String jid;
        private String name;
        private String type;

        public Item(String str, String str2, String str3) {
            this.jid = str;
            this.type = str2;
            this.name = str3;
        }

        public String getJID() {
            return this.jid;
        }

        public String getType() {
            return this.type;
        }

        public String getName() {
            return this.name;
        }
    }

    public static class Provider implements IQProvider {
        public IQ parseIQ(XmlPullParser xmlPullParser) throws Exception {
            AgentStatusRequest agentStatusRequest = new AgentStatusRequest();
            if (xmlPullParser.getEventType() == 2) {
                boolean z = false;
                while (!z) {
                    int next = xmlPullParser.next();
                    if (next == 2 && "agent".equals(xmlPullParser.getName())) {
                        agentStatusRequest.agents.add(parseAgent(xmlPullParser));
                    } else if (next == 3 && AgentStatusRequest.ELEMENT_NAME.equals(xmlPullParser.getName())) {
                        z = true;
                    }
                }
                return agentStatusRequest;
            }
            throw new IllegalStateException("Parser not in proper position, or bad XML.");
        }

        private Item parseAgent(XmlPullParser xmlPullParser) throws Exception {
            String attributeValue = xmlPullParser.getAttributeValue("", "jid");
            String attributeValue2 = xmlPullParser.getAttributeValue("", AppMeasurement.Param.TYPE);
            boolean z = false;
            String str = null;
            while (!z) {
                int next = xmlPullParser.next();
                if (next == 2 && "name".equals(xmlPullParser.getName())) {
                    str = xmlPullParser.nextText();
                } else if (next == 3 && "agent".equals(xmlPullParser.getName())) {
                    z = true;
                }
            }
            return new Item(attributeValue, attributeValue2, str);
        }
    }
}
