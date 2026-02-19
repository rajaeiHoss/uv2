package org.jivesoftware.smack;

import org.apache.harmony.javax.security.auth.callback.Callback;
import org.apache.harmony.javax.security.auth.callback.CallbackHandler;
import org.apache.harmony.javax.security.auth.callback.PasswordCallback;
import org.jivesoftware.smack.filter.PacketIDFilter;
import org.jivesoftware.smack.packet.Authentication;
import org.jivesoftware.smack.packet.IQ;

class NonSASLAuthentication implements UserAuthentication {
    private Connection connection;

    public NonSASLAuthentication(Connection connection2) {
        this.connection = connection2;
    }

    public String authenticate(String str, String str2, CallbackHandler callbackHandler) throws XMPPException {
        PasswordCallback passwordCallback = new PasswordCallback("Password: ", false);
        try {
            callbackHandler.handle(new Callback[]{passwordCallback});
            return authenticate(str, String.valueOf(passwordCallback.getPassword()), str2);
        } catch (Exception e) {
            throw new XMPPException("Unable to determine password.", (Throwable) e);
        }
    }

    public String authenticate(String str, String str2, String str3) throws XMPPException {
        Authentication authentication = new Authentication();
        authentication.setType(IQ.Type.GET);
        authentication.setUsername(str);
        PacketCollector createPacketCollector = this.connection.createPacketCollector(new PacketIDFilter(authentication.getPacketID()));
        this.connection.sendPacket(authentication);
        IQ iq = (IQ) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
        if (iq == null) {
            throw new XMPPException("No response from the server.");
        } else if (iq.getType() != IQ.Type.ERROR) {
            Authentication authentication2 = (Authentication) iq;
            createPacketCollector.cancel();
            Authentication authentication3 = new Authentication();
            authentication3.setUsername(str);
            if (authentication2.getDigest() != null) {
                authentication3.setDigest(this.connection.getConnectionID(), str2);
            } else if (authentication2.getPassword() != null) {
                authentication3.setPassword(str2);
            } else {
                throw new XMPPException("Server does not support compatible authentication mechanism.");
            }
            authentication3.setResource(str3);
            PacketCollector createPacketCollector2 = this.connection.createPacketCollector(new PacketIDFilter(authentication3.getPacketID()));
            this.connection.sendPacket(authentication3);
            IQ iq2 = (IQ) createPacketCollector2.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
            if (iq2 == null) {
                throw new XMPPException("Authentication failed.");
            } else if (iq2.getType() != IQ.Type.ERROR) {
                createPacketCollector2.cancel();
                return iq2.getTo();
            } else {
                throw new XMPPException(iq2.getError());
            }
        } else {
            throw new XMPPException(iq.getError());
        }
    }

    public String authenticateAnonymously() throws XMPPException {
        Authentication authentication = new Authentication();
        PacketCollector createPacketCollector = this.connection.createPacketCollector(new PacketIDFilter(authentication.getPacketID()));
        this.connection.sendPacket(authentication);
        IQ iq = (IQ) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
        if (iq == null) {
            throw new XMPPException("Anonymous login failed.");
        } else if (iq.getType() != IQ.Type.ERROR) {
            createPacketCollector.cancel();
            if (iq.getTo() != null) {
                return iq.getTo();
            }
            return this.connection.getServiceName() + "/" + ((Authentication) iq).getResource();
        } else {
            throw new XMPPException(iq.getError());
        }
    }
}
