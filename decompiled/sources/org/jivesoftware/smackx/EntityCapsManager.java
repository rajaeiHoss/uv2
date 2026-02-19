package org.jivesoftware.smackx;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.PacketExtensionFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.provider.ProviderManager;
import org.jivesoftware.smack.util.Base64;
import org.jivesoftware.smackx.packet.CapsExtension;
import org.jivesoftware.smackx.packet.DataForm;
import org.jivesoftware.smackx.packet.DiscoverInfo;
import org.jivesoftware.smackx.provider.CapsExtensionProvider;

public class EntityCapsManager {
    public static final String HASH_METHOD = "sha-1";
    public static final String HASH_METHOD_CAPS = "SHA-1";
    private static Map<String, DiscoverInfo> caps = new ConcurrentHashMap();
    private static String entityNode = "http://www.igniterealtime.org/projects/smack/";
    private Set<CapsVerListener> capsVerListeners = new CopyOnWriteArraySet();
    private String currentCapsVersion = null;
    private Map<String, String> userCaps = new ConcurrentHashMap();

    static {
        ProviderManager.getInstance().addExtensionProvider(CapsExtension.NODE_NAME, CapsExtension.XMLNS, new CapsExtensionProvider());
    }

    public static void addDiscoverInfoByNode(String str, DiscoverInfo discoverInfo) {
        cleanupDicsoverInfo(discoverInfo);
        caps.put(str, discoverInfo);
    }

    public void addUserCapsNode(String str, String str2) {
        if (str != null && str2 != null) {
            this.userCaps.put(str, str2);
        }
    }

    public void removeUserCapsNode(String str) {
        this.userCaps.remove(str);
    }

    public String getNodeVersionByUser(String str) {
        return this.userCaps.get(str);
    }

    public DiscoverInfo getDiscoverInfoByUser(String str) {
        String str2 = this.userCaps.get(str);
        if (str2 == null) {
            return null;
        }
        return getDiscoverInfoByNode(str2);
    }

    public String getCapsVersion() {
        return this.currentCapsVersion;
    }

    public String getNode() {
        return entityNode;
    }

    public void setNode(String str) {
        entityNode = str;
    }

    public static DiscoverInfo getDiscoverInfoByNode(String str) {
        return caps.get(str);
    }

    private static void cleanupDicsoverInfo(DiscoverInfo discoverInfo) {
        discoverInfo.setFrom((String) null);
        discoverInfo.setTo((String) null);
        discoverInfo.setPacketID((String) null);
    }

    public void addPacketListener(Connection connection) {
        connection.addPacketListener(new CapsPacketListener(), new AndFilter(new PacketTypeFilter(Presence.class), new PacketExtensionFilter(CapsExtension.NODE_NAME, CapsExtension.XMLNS)));
    }

    public void addCapsVerListener(CapsVerListener capsVerListener) {
        this.capsVerListeners.add(capsVerListener);
        String str = this.currentCapsVersion;
        if (str != null) {
            capsVerListener.capsVerUpdated(str);
        }
    }

    public void removeCapsVerListener(CapsVerListener capsVerListener) {
        this.capsVerListeners.remove(capsVerListener);
    }

    private void notifyCapsVerListeners() {
        for (CapsVerListener capsVerUpdated : this.capsVerListeners) {
            capsVerUpdated.capsVerUpdated(this.currentCapsVersion);
        }
    }

    private static String capsToHash(String str) {
        try {
            return Base64.encodeBytes(MessageDigest.getInstance(HASH_METHOD_CAPS).digest(str.getBytes()));
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }

    private static String formFieldValuesToCaps(Iterator<String> it) {
        TreeSet<String> treeSet = new TreeSet<>();
        while (it.hasNext()) {
            treeSet.add(it.next());
        }
        String str = "";
        for (String str2 : treeSet) {
            str = str + str2 + "<";
        }
        return str;
    }

    /* access modifiers changed from: package-private */
    public void calculateEntityCapsVersion(DiscoverInfo discoverInfo, String str, String str2, List<String> list, DataForm dataForm) {
        String str3 = "" + "client/" + str + "//" + str2 + "<";
        synchronized (list) {
            TreeSet<String> treeSet = new TreeSet<>();
            for (String add : list) {
                treeSet.add(add);
            }
            for (String str4 : treeSet) {
                str3 = str3 + str4 + "<";
            }
        }
        if (dataForm != null) {
            synchronized (dataForm) {
                TreeSet<FormField> treeSet2 = new TreeSet<>(new Comparator<FormField>() {
                    public int compare(FormField formField, FormField formField2) {
                        return formField.getVariable().compareTo(formField2.getVariable());
                    }
                });
                FormField formField = null;
                Iterator<FormField> fields = dataForm.getFields();
                while (fields.hasNext()) {
                    FormField next = fields.next();
                    if (!next.getVariable().equals("FORM_TYPE")) {
                        treeSet2.add(next);
                    } else {
                        formField = next;
                    }
                }
                if (formField != null) {
                    str3 = str3 + formFieldValuesToCaps(formField.getValues());
                }
                for (FormField formField2 : treeSet2) {
                    str3 = (str3 + formField2.getVariable() + "<") + formFieldValuesToCaps(formField2.getValues());
                }
            }
        }
        setCurrentCapsVersion(discoverInfo, capsToHash(str3));
    }

    public void setCurrentCapsVersion(DiscoverInfo discoverInfo, String str) {
        this.currentCapsVersion = str;
        addDiscoverInfoByNode(getNode() + "#" + str, discoverInfo);
        notifyCapsVerListeners();
    }

    class CapsPacketListener implements PacketListener {
        CapsPacketListener() {
        }

        public void processPacket(Packet packet) {
            CapsExtension capsExtension = (CapsExtension) packet.getExtension(CapsExtension.NODE_NAME, CapsExtension.XMLNS);
            EntityCapsManager.this.addUserCapsNode(packet.getFrom(), capsExtension.getNode() + "#" + capsExtension.getVersion());
        }
    }
}
