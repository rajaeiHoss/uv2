package org.jivesoftware.smackx.workgroup.ext.history;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import kotlin.text.Typography;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

public class AgentChatHistory extends IQ {
    public static final String ELEMENT_NAME = "chat-sessions";
    public static final String NAMESPACE = "http://jivesoftware.com/protocol/workgroup";
    private List agentChatSessions = new ArrayList();
    private String agentJID;
    private int maxSessions;
    private long startDate;

    public AgentChatHistory(String str, int i, Date date) {
        this.agentJID = str;
        this.maxSessions = i;
        this.startDate = date.getTime();
    }

    public AgentChatHistory(String str, int i) {
        this.agentJID = str;
        this.maxSessions = i;
        this.startDate = 0;
    }

    public AgentChatHistory() {
    }

    public void addChatSession(AgentChatSession agentChatSession) {
        this.agentChatSessions.add(agentChatSession);
    }

    public Collection getAgentChatSessions() {
        return this.agentChatSessions;
    }

    public String getChildElementXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(ELEMENT_NAME);
        sb.append(" xmlns=");
        sb.append(Typography.quote);
        sb.append("http://jivesoftware.com/protocol/workgroup");
        sb.append(Typography.quote);
        sb.append(" agentJID=\"" + this.agentJID + "\"");
        sb.append(" maxSessions=\"" + this.maxSessions + "\"");
        sb.append(" startDate=\"" + this.startDate + "\"");
        sb.append("></");
        sb.append(ELEMENT_NAME);
        sb.append("> ");
        return sb.toString();
    }

    public static class InternalProvider implements IQProvider {
        public IQ parseIQ(XmlPullParser xmlPullParser) throws Exception {
            if (xmlPullParser.getEventType() == 2) {
                AgentChatHistory agentChatHistory = new AgentChatHistory();
                boolean z = false;
                while (!z) {
                    int next = xmlPullParser.next();
                    if (next == 2 && "chat-session".equals(xmlPullParser.getName())) {
                        agentChatHistory.addChatSession(parseChatSetting(xmlPullParser));
                    } else if (next == 3 && AgentChatHistory.ELEMENT_NAME.equals(xmlPullParser.getName())) {
                        z = true;
                    }
                }
                return agentChatHistory;
            }
            throw new IllegalStateException("Parser not in proper position, or bad XML.");
        }

        private AgentChatSession parseChatSetting(XmlPullParser xmlPullParser) throws Exception {
            boolean z = false;
            long j = 0;
            Date date = null;
            String str = null;
            String str2 = null;
            String str3 = null;
            String str4 = null;
            while (true) {
                long j2 = j;
                while (!z) {
                    int next = xmlPullParser.next();
                    if (next == 2 && "date".equals(xmlPullParser.getName())) {
                        date = new Date(Long.valueOf(xmlPullParser.nextText()).longValue());
                    } else if (next == 2 && "duration".equals(xmlPullParser.getName())) {
                        j = Long.valueOf(xmlPullParser.nextText()).longValue();
                    } else if (next == 2 && "visitorsName".equals(xmlPullParser.getName())) {
                        str = xmlPullParser.nextText();
                    } else if (next == 2 && "visitorsEmail".equals(xmlPullParser.getName())) {
                        str2 = xmlPullParser.nextText();
                    } else if (next == 2 && "sessionID".equals(xmlPullParser.getName())) {
                        str3 = xmlPullParser.nextText();
                    } else if (next == 2 && "question".equals(xmlPullParser.getName())) {
                        str4 = xmlPullParser.nextText();
                    } else if (next == 3 && "chat-session".equals(xmlPullParser.getName())) {
                        z = true;
                    }
                }
                return new AgentChatSession(date, j2, str, str2, str3, str4);
            }
        }
    }
}
