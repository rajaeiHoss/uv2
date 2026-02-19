package org.jivesoftware.smack;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.PacketIDFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Registration;
import org.jivesoftware.smack.util.StringUtils;

public class AccountManager {
    private boolean accountCreationSupported = false;
    private Connection connection;
    private Registration info = null;

    public AccountManager(Connection connection2) {
        this.connection = connection2;
    }

    /* access modifiers changed from: package-private */
    public void setSupportsAccountCreation(boolean z) {
        this.accountCreationSupported = z;
    }

    public boolean supportsAccountCreation() {
        boolean z = true;
        if (this.accountCreationSupported) {
            return true;
        }
        try {
            if (this.info == null) {
                getRegistrationInfo();
                if (this.info.getType() == IQ.Type.ERROR) {
                    z = false;
                }
                this.accountCreationSupported = z;
            }
            return this.accountCreationSupported;
        } catch (XMPPException unused) {
            return false;
        }
    }

    public Collection<String> getAccountAttributes() {
        try {
            if (this.info == null) {
                getRegistrationInfo();
            }
            List<String> requiredFields = this.info.getRequiredFields();
            if (requiredFields.size() > 0) {
                return Collections.unmodifiableSet(new HashSet(requiredFields));
            }
        } catch (XMPPException e) {
            e.printStackTrace();
        }
        return Collections.emptySet();
    }

    public String getAccountAttribute(String str) {
        try {
            if (this.info == null) {
                getRegistrationInfo();
            }
            return this.info.getAttributes().get(str);
        } catch (XMPPException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getAccountInstructions() {
        try {
            if (this.info == null) {
                getRegistrationInfo();
            }
            return this.info.getInstructions();
        } catch (XMPPException unused) {
            return null;
        }
    }

    public void createAccount(String str, String str2) throws XMPPException {
        if (supportsAccountCreation()) {
            HashMap hashMap = new HashMap();
            for (String put : getAccountAttributes()) {
                hashMap.put(put, "");
            }
            createAccount(str, str2, hashMap);
            return;
        }
        throw new XMPPException("Server does not support account creation.");
    }

    public void createAccount(String str, String str2, Map<String, String> map) throws XMPPException {
        if (supportsAccountCreation()) {
            Registration registration = new Registration();
            registration.setType(IQ.Type.SET);
            registration.setTo(this.connection.getServiceName());
            for (String next : map.keySet()) {
                registration.addAttribute(next, map.get(next));
            }
            registration.setUsername(str);
            registration.setPassword(str2);
            PacketCollector createPacketCollector = this.connection.createPacketCollector(new AndFilter(new PacketIDFilter(registration.getPacketID()), new PacketTypeFilter(IQ.class)));
            this.connection.sendPacket(registration);
            IQ iq = (IQ) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
            createPacketCollector.cancel();
            if (iq == null) {
                throw new XMPPException("No response from server.");
            } else if (iq.getType() == IQ.Type.ERROR) {
                throw new XMPPException(iq.getError());
            }
        } else {
            throw new XMPPException("Server does not support account creation.");
        }
    }

    public void changePassword(String str) throws XMPPException {
        Registration registration = new Registration();
        registration.setType(IQ.Type.SET);
        registration.setTo(this.connection.getServiceName());
        registration.setUsername(StringUtils.parseName(this.connection.getUser()));
        registration.setPassword(str);
        PacketCollector createPacketCollector = this.connection.createPacketCollector(new AndFilter(new PacketIDFilter(registration.getPacketID()), new PacketTypeFilter(IQ.class)));
        this.connection.sendPacket(registration);
        IQ iq = (IQ) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
        createPacketCollector.cancel();
        if (iq == null) {
            throw new XMPPException("No response from server.");
        } else if (iq.getType() == IQ.Type.ERROR) {
            throw new XMPPException(iq.getError());
        }
    }

    public void deleteAccount() throws XMPPException {
        if (this.connection.isAuthenticated()) {
            Registration registration = new Registration();
            registration.setType(IQ.Type.SET);
            registration.setTo(this.connection.getServiceName());
            registration.setRemove(true);
            PacketCollector createPacketCollector = this.connection.createPacketCollector(new AndFilter(new PacketIDFilter(registration.getPacketID()), new PacketTypeFilter(IQ.class)));
            this.connection.sendPacket(registration);
            IQ iq = (IQ) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
            createPacketCollector.cancel();
            if (iq == null) {
                throw new XMPPException("No response from server.");
            } else if (iq.getType() == IQ.Type.ERROR) {
                throw new XMPPException(iq.getError());
            }
        } else {
            throw new IllegalStateException("Must be logged in to delete a account.");
        }
    }

    private synchronized void getRegistrationInfo() throws XMPPException {
        Registration registration = new Registration();
        registration.setTo(this.connection.getServiceName());
        PacketCollector createPacketCollector = this.connection.createPacketCollector(new AndFilter(new PacketIDFilter(registration.getPacketID()), new PacketTypeFilter(IQ.class)));
        this.connection.sendPacket(registration);
        IQ iq = (IQ) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
        createPacketCollector.cancel();
        if (iq == null) {
            throw new XMPPException("No response from server.");
        } else if (iq.getType() != IQ.Type.ERROR) {
            this.info = (Registration) iq;
        } else {
            throw new XMPPException(iq.getError());
        }
    }
}
