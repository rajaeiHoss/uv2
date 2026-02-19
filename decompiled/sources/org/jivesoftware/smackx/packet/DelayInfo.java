package org.jivesoftware.smackx.packet;

import java.util.Date;
import org.jivesoftware.smack.packet.Packet;

public class DelayInfo extends DelayInformation {
    DelayInformation wrappedInfo;

    public String getElementName() {
        return "delay";
    }

    public String getNamespace() {
        return "urn:xmpp:delay";
    }

    public DelayInfo(DelayInformation delayInformation) {
        super(delayInformation.getStamp());
        this.wrappedInfo = delayInformation;
    }

    public String getFrom() {
        return this.wrappedInfo.getFrom();
    }

    public String getReason() {
        return this.wrappedInfo.getReason();
    }

    public Date getStamp() {
        return this.wrappedInfo.getStamp();
    }

    public void setFrom(String str) {
        this.wrappedInfo.setFrom(str);
    }

    public void setReason(String str) {
        this.wrappedInfo.setReason(str);
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(getElementName());
        sb.append(" xmlns=\"");
        sb.append(getNamespace());
        sb.append("\"");
        sb.append(" stamp=\"");
        synchronized (Packet.XEP_0082_UTC_FORMAT) {
            sb.append(Packet.XEP_0082_UTC_FORMAT.format(getStamp()));
        }
        sb.append("\"");
        if (getFrom() != null && getFrom().length() > 0) {
            sb.append(" from=\"");
            sb.append(getFrom());
            sb.append("\"");
        }
        sb.append(">");
        if (getReason() != null && getReason().length() > 0) {
            sb.append(getReason());
        }
        sb.append("</");
        sb.append(getElementName());
        sb.append(">");
        return sb.toString();
    }
}
