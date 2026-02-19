package org.jivesoftware.smackx.workgroup.agent;

import java.util.Date;
import java.util.Map;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.packet.IQ;

public class Offer {
    private boolean accepted = false;
    private Connection connection;
    private OfferContent content;
    private Date expiresDate;
    private Map metaData;
    private boolean rejected = false;
    private AgentSession session;
    private String sessionID;
    private String userID;
    private String userJID;
    private String workgroupName;

    Offer(Connection connection2, AgentSession agentSession, String str, String str2, String str3, Date date, String str4, Map map, OfferContent offerContent) {
        this.connection = connection2;
        this.session = agentSession;
        this.userID = str;
        this.userJID = str2;
        this.workgroupName = str3;
        this.expiresDate = date;
        this.sessionID = str4;
        this.metaData = map;
        this.content = offerContent;
    }

    public void accept() {
        this.connection.sendPacket(new AcceptPacket(this.session.getWorkgroupJID()));
        this.accepted = true;
    }

    public void reject() {
        this.connection.sendPacket(new RejectPacket(this.session.getWorkgroupJID()));
        this.rejected = true;
    }

    public String getUserID() {
        return this.userID;
    }

    public String getUserJID() {
        return this.userJID;
    }

    public String getWorkgroupName() {
        return this.workgroupName;
    }

    public Date getExpiresDate() {
        return this.expiresDate;
    }

    public String getSessionID() {
        return this.sessionID;
    }

    public Map getMetaData() {
        return this.metaData;
    }

    public OfferContent getContent() {
        return this.content;
    }

    public boolean isAccepted() {
        return this.accepted;
    }

    public boolean isRejected() {
        return this.rejected;
    }

    private class RejectPacket extends IQ {
        RejectPacket(String str) {
            setTo(str);
            setType(IQ.Type.SET);
        }

        public String getChildElementXML() {
            return "<offer-reject id=\"" + Offer.this.getSessionID() + "\" xmlns=\"http://jabber.org/protocol/workgroup" + "\"/>";
        }
    }

    private class AcceptPacket extends IQ {
        AcceptPacket(String str) {
            setTo(str);
            setType(IQ.Type.SET);
        }

        public String getChildElementXML() {
            return "<offer-accept id=\"" + Offer.this.getSessionID() + "\" xmlns=\"http://jabber.org/protocol/workgroup" + "\"/>";
        }
    }
}
