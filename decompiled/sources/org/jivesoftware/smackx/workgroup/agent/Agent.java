package org.jivesoftware.smackx.workgroup.agent;

import java.util.Collection;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.PacketIDFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smackx.workgroup.packet.AgentInfo;
import org.jivesoftware.smackx.workgroup.packet.AgentWorkgroups;

public class Agent {
    private Connection connection;
    private String workgroupJID;

    public static Collection<String> getWorkgroups(String str, String str2, Connection connection2) throws XMPPException {
        AgentWorkgroups agentWorkgroups = new AgentWorkgroups(str2);
        agentWorkgroups.setTo(str);
        PacketCollector createPacketCollector = connection2.createPacketCollector(new PacketIDFilter(agentWorkgroups.getPacketID()));
        connection2.sendPacket(agentWorkgroups);
        AgentWorkgroups agentWorkgroups2 = (AgentWorkgroups) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
        createPacketCollector.cancel();
        if (agentWorkgroups2 == null) {
            throw new XMPPException("No response from server on status set.");
        } else if (agentWorkgroups2.getError() == null) {
            return agentWorkgroups2.getWorkgroups();
        } else {
            throw new XMPPException(agentWorkgroups2.getError());
        }
    }

    Agent(Connection connection2, String str) {
        this.connection = connection2;
        this.workgroupJID = str;
    }

    public String getUser() {
        return this.connection.getUser();
    }

    public String getName() throws XMPPException {
        AgentInfo agentInfo = new AgentInfo();
        agentInfo.setType(IQ.Type.GET);
        agentInfo.setTo(this.workgroupJID);
        agentInfo.setFrom(getUser());
        PacketCollector createPacketCollector = this.connection.createPacketCollector(new PacketIDFilter(agentInfo.getPacketID()));
        this.connection.sendPacket(agentInfo);
        AgentInfo agentInfo2 = (AgentInfo) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
        createPacketCollector.cancel();
        if (agentInfo2 == null) {
            throw new XMPPException("No response from server on status set.");
        } else if (agentInfo2.getError() == null) {
            return agentInfo2.getName();
        } else {
            throw new XMPPException(agentInfo2.getError());
        }
    }

    public void setName(String str) throws XMPPException {
        AgentInfo agentInfo = new AgentInfo();
        agentInfo.setType(IQ.Type.SET);
        agentInfo.setTo(this.workgroupJID);
        agentInfo.setFrom(getUser());
        agentInfo.setName(str);
        PacketCollector createPacketCollector = this.connection.createPacketCollector(new PacketIDFilter(agentInfo.getPacketID()));
        this.connection.sendPacket(agentInfo);
        IQ iq = (IQ) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
        createPacketCollector.cancel();
        if (iq == null) {
            throw new XMPPException("No response from server on status set.");
        } else if (iq.getError() != null) {
            throw new XMPPException(iq.getError());
        }
    }
}
