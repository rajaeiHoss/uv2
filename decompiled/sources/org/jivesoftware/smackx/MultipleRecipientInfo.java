package org.jivesoftware.smackx;

import java.util.List;
import org.jivesoftware.smackx.packet.MultipleAddresses;

public class MultipleRecipientInfo {
    MultipleAddresses extension;

    MultipleRecipientInfo(MultipleAddresses multipleAddresses) {
        this.extension = multipleAddresses;
    }

    public List<MultipleAddresses.Address> getTOAddresses() {
        return this.extension.getAddressesOfType("to");
    }

    public List<MultipleAddresses.Address> getCCAddresses() {
        return this.extension.getAddressesOfType(MultipleAddresses.CC);
    }

    public String getReplyRoom() {
        List<MultipleAddresses.Address> addressesOfType = this.extension.getAddressesOfType(MultipleAddresses.REPLY_ROOM);
        if (addressesOfType.isEmpty()) {
            return null;
        }
        return ((MultipleAddresses.Address) addressesOfType.get(0)).getJid();
    }

    public boolean shouldNotReply() {
        return !this.extension.getAddressesOfType(MultipleAddresses.NO_REPLY).isEmpty();
    }

    public MultipleAddresses.Address getReplyAddress() {
        List<MultipleAddresses.Address> addressesOfType = this.extension.getAddressesOfType(MultipleAddresses.REPLY_TO);
        if (addressesOfType.isEmpty()) {
            return null;
        }
        return (MultipleAddresses.Address) addressesOfType.get(0);
    }
}
