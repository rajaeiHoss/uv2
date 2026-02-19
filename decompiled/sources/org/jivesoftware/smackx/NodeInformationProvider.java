package org.jivesoftware.smackx;

import java.util.List;
import org.jivesoftware.smackx.packet.DiscoverInfo;
import org.jivesoftware.smackx.packet.DiscoverItems;

public interface NodeInformationProvider {
    List<String> getNodeFeatures();

    List<DiscoverInfo.Identity> getNodeIdentities();

    List<DiscoverItems.Item> getNodeItems();
}
