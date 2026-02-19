package org.jivesoftware.smackx.commands;

import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.PacketIDFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smackx.Form;
import org.jivesoftware.smackx.commands.AdHocCommand;
import org.jivesoftware.smackx.packet.AdHocCommandData;

public class RemoteCommand extends AdHocCommand {
    private Connection connection;
    private String jid;
    private long packetReplyTimeout = ((long) SmackConfiguration.getPacketReplyTimeout());
    private String sessionID;

    protected RemoteCommand(Connection connection2, String str, String str2) {
        this.connection = connection2;
        this.jid = str2;
        setNode(str);
    }

    public void cancel() throws XMPPException {
        executeAction(AdHocCommand.Action.cancel, this.packetReplyTimeout);
    }

    public void complete(Form form) throws XMPPException {
        executeAction(AdHocCommand.Action.complete, form, this.packetReplyTimeout);
    }

    public void execute() throws XMPPException {
        executeAction(AdHocCommand.Action.execute, this.packetReplyTimeout);
    }

    public void execute(Form form) throws XMPPException {
        executeAction(AdHocCommand.Action.execute, form, this.packetReplyTimeout);
    }

    public void next(Form form) throws XMPPException {
        executeAction(AdHocCommand.Action.next, form, this.packetReplyTimeout);
    }

    public void prev() throws XMPPException {
        executeAction(AdHocCommand.Action.prev, this.packetReplyTimeout);
    }

    private void executeAction(AdHocCommand.Action action, long j) throws XMPPException {
        executeAction(action, (Form) null, j);
    }

    private void executeAction(AdHocCommand.Action action, Form form, long j) throws XMPPException {
        AdHocCommandData adHocCommandData = new AdHocCommandData();
        adHocCommandData.setType(IQ.Type.SET);
        adHocCommandData.setTo(getOwnerJID());
        adHocCommandData.setNode(getNode());
        adHocCommandData.setSessionID(this.sessionID);
        adHocCommandData.setAction(action);
        if (form != null) {
            adHocCommandData.setForm(form.getDataFormToSend());
        }
        PacketCollector createPacketCollector = this.connection.createPacketCollector(new PacketIDFilter(adHocCommandData.getPacketID()));
        this.connection.sendPacket(adHocCommandData);
        Packet nextResult = createPacketCollector.nextResult(j);
        createPacketCollector.cancel();
        if (nextResult == null) {
            throw new XMPPException("No response from server on status set.");
        } else if (nextResult.getError() == null) {
            AdHocCommandData adHocCommandData2 = (AdHocCommandData) nextResult;
            this.sessionID = adHocCommandData2.getSessionID();
            super.setData(adHocCommandData2);
        } else {
            throw new XMPPException(nextResult.getError());
        }
    }

    public String getOwnerJID() {
        return this.jid;
    }

    public long getPacketReplyTimeout() {
        return this.packetReplyTimeout;
    }

    public void setPacketReplyTimeout(long j) {
        this.packetReplyTimeout = j;
    }
}
