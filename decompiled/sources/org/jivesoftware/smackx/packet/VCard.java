package org.jivesoftware.smackx.packet;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import kotlin.text.Typography;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.PacketIDFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smackx.EntityCapsManager;

public class VCard extends IQ {
    private String avatar;
    /* access modifiers changed from: private */
    public String emailHome;
    /* access modifiers changed from: private */
    public String emailWork;
    /* access modifiers changed from: private */
    public String firstName;
    /* access modifiers changed from: private */
    public Map<String, String> homeAddr = new HashMap();
    /* access modifiers changed from: private */
    public Map<String, String> homePhones = new HashMap();
    /* access modifiers changed from: private */
    public String lastName;
    /* access modifiers changed from: private */
    public String middleName;
    /* access modifiers changed from: private */
    public String organization;
    /* access modifiers changed from: private */
    public String organizationUnit;
    /* access modifiers changed from: private */
    public Map<String, String> otherSimpleFields = new HashMap();
    /* access modifiers changed from: private */
    public Map<String, String> otherUnescapableFields = new HashMap();
    /* access modifiers changed from: private */
    public Map<String, String> workAddr = new HashMap();
    /* access modifiers changed from: private */
    public Map<String, String> workPhones = new HashMap();

    private interface ContentBuilder {
        void addTagContent();
    }

    public String getField(String str) {
        return this.otherSimpleFields.get(str);
    }

    public void setField(String str, String str2) {
        setField(str, str2, false);
    }

    public void setField(String str, String str2, boolean z) {
        if (!z) {
            this.otherSimpleFields.put(str, str2);
        } else {
            this.otherUnescapableFields.put(str, str2);
        }
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String str) {
        this.firstName = str;
        updateFN();
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String str) {
        this.lastName = str;
        updateFN();
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public void setMiddleName(String str) {
        this.middleName = str;
        updateFN();
    }

    public String getNickName() {
        return this.otherSimpleFields.get("NICKNAME");
    }

    public void setNickName(String str) {
        this.otherSimpleFields.put("NICKNAME", str);
    }

    public String getEmailHome() {
        return this.emailHome;
    }

    public void setEmailHome(String str) {
        this.emailHome = str;
    }

    public String getEmailWork() {
        return this.emailWork;
    }

    public void setEmailWork(String str) {
        this.emailWork = str;
    }

    public String getJabberId() {
        return this.otherSimpleFields.get("JABBERID");
    }

    public void setJabberId(String str) {
        this.otherSimpleFields.put("JABBERID", str);
    }

    public String getOrganization() {
        return this.organization;
    }

    public void setOrganization(String str) {
        this.organization = str;
    }

    public String getOrganizationUnit() {
        return this.organizationUnit;
    }

    public void setOrganizationUnit(String str) {
        this.organizationUnit = str;
    }

    public String getAddressFieldHome(String str) {
        return this.homeAddr.get(str);
    }

    public void setAddressFieldHome(String str, String str2) {
        this.homeAddr.put(str, str2);
    }

    public String getAddressFieldWork(String str) {
        return this.workAddr.get(str);
    }

    public void setAddressFieldWork(String str, String str2) {
        this.workAddr.put(str, str2);
    }

    public void setPhoneHome(String str, String str2) {
        this.homePhones.put(str, str2);
    }

    public String getPhoneHome(String str) {
        return this.homePhones.get(str);
    }

    public void setPhoneWork(String str, String str2) {
        this.workPhones.put(str, str2);
    }

    public String getPhoneWork(String str) {
        return this.workPhones.get(str);
    }

    public void setAvatar(URL url) {
        byte[] bArr = new byte[0];
        try {
            bArr = getBytes(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setAvatar(bArr);
    }

    public void setAvatar(byte[] bArr) {
        if (bArr == null) {
            this.otherUnescapableFields.remove("PHOTO");
            return;
        }
        String encodeBase64 = StringUtils.encodeBase64(bArr);
        this.avatar = encodeBase64;
        setField("PHOTO", "<TYPE>image/jpeg</TYPE><BINVAL>" + encodeBase64 + "</BINVAL>", true);
    }

    public void setAvatar(byte[] bArr, String str) {
        if (bArr == null) {
            this.otherUnescapableFields.remove("PHOTO");
            return;
        }
        String encodeBase64 = StringUtils.encodeBase64(bArr);
        this.avatar = encodeBase64;
        setField("PHOTO", "<TYPE>" + str + "</TYPE><BINVAL>" + encodeBase64 + "</BINVAL>", true);
    }

    public void setEncodedImage(String str) {
        this.avatar = str;
    }

    public byte[] getAvatar() {
        String str = this.avatar;
        if (str == null) {
            return null;
        }
        return StringUtils.decodeBase64(str);
    }

    public static byte[] getBytes(URL url) throws IOException {
        File file = new File(url.getPath());
        if (file.exists()) {
            return getFileBytes(file);
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x002a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] getFileBytes(java.io.File r4) throws java.io.IOException {
        /*
            r0 = 0
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch:{ all -> 0x0027 }
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ all -> 0x0027 }
            r2.<init>(r4)     // Catch:{ all -> 0x0027 }
            r1.<init>(r2)     // Catch:{ all -> 0x0027 }
            long r2 = r4.length()     // Catch:{ all -> 0x0024 }
            int r4 = (int) r2     // Catch:{ all -> 0x0024 }
            byte[] r0 = new byte[r4]     // Catch:{ all -> 0x0024 }
            int r2 = r1.read(r0)     // Catch:{ all -> 0x0024 }
            if (r2 != r4) goto L_0x001c
            r1.close()
            return r0
        L_0x001c:
            java.io.IOException r4 = new java.io.IOException     // Catch:{ all -> 0x0024 }
            java.lang.String r0 = "Entire file not read"
            r4.<init>(r0)     // Catch:{ all -> 0x0024 }
            throw r4     // Catch:{ all -> 0x0024 }
        L_0x0024:
            r4 = move-exception
            r0 = r1
            goto L_0x0028
        L_0x0027:
            r4 = move-exception
        L_0x0028:
            if (r0 == 0) goto L_0x002d
            r0.close()
        L_0x002d:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smackx.packet.VCard.getFileBytes(java.io.File):byte[]");
    }

    public String getAvatarHash() {
        byte[] avatar2 = getAvatar();
        if (avatar2 == null) {
            return null;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance(EntityCapsManager.HASH_METHOD_CAPS);
            instance.update(avatar2);
            return StringUtils.encodeHex(instance.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void updateFN() {
        StringBuilder sb = new StringBuilder();
        String str = this.firstName;
        if (str != null) {
            sb.append(StringUtils.escapeForXML(str));
            sb.append(' ');
        }
        String str2 = this.middleName;
        if (str2 != null) {
            sb.append(StringUtils.escapeForXML(str2));
            sb.append(' ');
        }
        String str3 = this.lastName;
        if (str3 != null) {
            sb.append(StringUtils.escapeForXML(str3));
        }
        setField("FN", sb.toString());
    }

    public void save(Connection connection) throws XMPPException {
        checkAuthenticated(connection, true);
        setType(IQ.Type.SET);
        setFrom(connection.getUser());
        PacketCollector createPacketCollector = connection.createPacketCollector(new PacketIDFilter(getPacketID()));
        connection.sendPacket(this);
        Packet nextResult = createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
        createPacketCollector.cancel();
        if (nextResult == null) {
            throw new XMPPException("No response from server on status set.");
        } else if (nextResult.getError() != null) {
            throw new XMPPException(nextResult.getError());
        }
    }

    public void load(Connection connection) throws XMPPException {
        checkAuthenticated(connection, true);
        setFrom(connection.getUser());
        doLoad(connection, connection.getUser());
    }

    public void load(Connection connection, String str) throws XMPPException {
        checkAuthenticated(connection, false);
        setTo(str);
        doLoad(connection, str);
    }

    private void doLoad(Connection connection, String str) throws XMPPException {
        VCard vCard;
        setType(IQ.Type.GET);
        PacketCollector createPacketCollector = connection.createPacketCollector(new PacketIDFilter(getPacketID()));
        connection.sendPacket(this);
        VCard vCard2 = null;
        try {
            vCard = (VCard) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
            if (vCard != null) {
                try {
                    if (vCard.getError() == null) {
                        copyFieldsFrom(vCard);
                    }
                    throw new XMPPException(vCard.getError());
                } catch (ClassCastException unused) {
                    vCard2 = vCard;
                    PrintStream printStream = System.out;
                    printStream.println("No VCard for " + str);
                    vCard = vCard2;
                    copyFieldsFrom(vCard);
                }
            } else {
                throw new XMPPException("Timeout getting VCard information", new XMPPError(XMPPError.Condition.request_timeout, "Timeout getting VCard information"));
            }
        } catch (ClassCastException unused2) {
            PrintStream printStream2 = System.out;
            printStream2.println("No VCard for " + str);
            vCard = vCard2;
            copyFieldsFrom(vCard);
        }
    }

    public String getChildElementXML() {
        StringBuilder sb = new StringBuilder();
        new VCardWriter(sb).write();
        return sb.toString();
    }

    private void copyFieldsFrom(VCard vCard) {
        Class<VCard> cls = VCard.class;
        if (vCard == null) {
            vCard = new VCard();
        }
        for (Field field : cls.getDeclaredFields()) {
            if (field.getDeclaringClass() == cls && !Modifier.isFinal(field.getModifiers())) {
                try {
                    field.setAccessible(true);
                    field.set(this, field.get(vCard));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("This cannot happen:" + field, e);
                }
            }
        }
    }

    private void checkAuthenticated(Connection connection, boolean z) {
        if (connection == null) {
            throw new IllegalArgumentException("No connection was provided");
        } else if (!connection.isAuthenticated()) {
            throw new IllegalArgumentException("Connection is not authenticated");
        } else if (z && connection.isAnonymous()) {
            throw new IllegalArgumentException("Connection cannot be anonymous");
        }
    }

    /* access modifiers changed from: private */
    public boolean hasContent() {
        return hasNameField() || hasOrganizationFields() || this.emailHome != null || this.emailWork != null || this.otherSimpleFields.size() > 0 || this.otherUnescapableFields.size() > 0 || this.homeAddr.size() > 0 || this.homePhones.size() > 0 || this.workAddr.size() > 0 || this.workPhones.size() > 0;
    }

    /* access modifiers changed from: private */
    public boolean hasNameField() {
        return (this.firstName == null && this.lastName == null && this.middleName == null) ? false : true;
    }

    /* access modifiers changed from: private */
    public boolean hasOrganizationFields() {
        return (this.organization == null && this.organizationUnit == null) ? false : true;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        VCard vCard = (VCard) obj;
        String str = this.emailHome;
        if (str == null ? vCard.emailHome != null : !str.equals(vCard.emailHome)) {
            return false;
        }
        String str2 = this.emailWork;
        if (str2 == null ? vCard.emailWork != null : !str2.equals(vCard.emailWork)) {
            return false;
        }
        String str3 = this.firstName;
        if (str3 == null ? vCard.firstName != null : !str3.equals(vCard.firstName)) {
            return false;
        }
        if (!this.homeAddr.equals(vCard.homeAddr) || !this.homePhones.equals(vCard.homePhones)) {
            return false;
        }
        String str4 = this.lastName;
        if (str4 == null ? vCard.lastName != null : !str4.equals(vCard.lastName)) {
            return false;
        }
        String str5 = this.middleName;
        if (str5 == null ? vCard.middleName != null : !str5.equals(vCard.middleName)) {
            return false;
        }
        String str6 = this.organization;
        if (str6 == null ? vCard.organization != null : !str6.equals(vCard.organization)) {
            return false;
        }
        String str7 = this.organizationUnit;
        if (str7 == null ? vCard.organizationUnit != null : !str7.equals(vCard.organizationUnit)) {
            return false;
        }
        if (this.otherSimpleFields.equals(vCard.otherSimpleFields) && this.workAddr.equals(vCard.workAddr)) {
            return this.workPhones.equals(vCard.workPhones);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = ((((((this.homePhones.hashCode() * 29) + this.workPhones.hashCode()) * 29) + this.homeAddr.hashCode()) * 29) + this.workAddr.hashCode()) * 29;
        String str = this.firstName;
        int i = 0;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 29;
        String str2 = this.lastName;
        int hashCode3 = (hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 29;
        String str3 = this.middleName;
        int hashCode4 = (hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 29;
        String str4 = this.emailHome;
        int hashCode5 = (hashCode4 + (str4 != null ? str4.hashCode() : 0)) * 29;
        String str5 = this.emailWork;
        int hashCode6 = (hashCode5 + (str5 != null ? str5.hashCode() : 0)) * 29;
        String str6 = this.organization;
        int hashCode7 = (hashCode6 + (str6 != null ? str6.hashCode() : 0)) * 29;
        String str7 = this.organizationUnit;
        if (str7 != null) {
            i = str7.hashCode();
        }
        return ((hashCode7 + i) * 29) + this.otherSimpleFields.hashCode();
    }

    public String toString() {
        return getChildElementXML();
    }

    private class VCardWriter {
        /* access modifiers changed from: private */
        public final StringBuilder sb;

        VCardWriter(StringBuilder sb2) {
            this.sb = sb2;
        }

        public void write() {
            appendTag("vCard", "xmlns", "vcard-temp", VCard.this.hasContent(), new ContentBuilder() {
                public void addTagContent() {
                    VCardWriter.this.buildActualContent();
                }
            });
        }

        /* access modifiers changed from: private */
        public void buildActualContent() {
            if (VCard.this.hasNameField()) {
                appendN();
            }
            appendOrganization();
            appendGenericFields();
            appendEmail(VCard.this.emailWork, "WORK");
            appendEmail(VCard.this.emailHome, "HOME");
            appendPhones(VCard.this.workPhones, "WORK");
            appendPhones(VCard.this.homePhones, "HOME");
            appendAddress(VCard.this.workAddr, "WORK");
            appendAddress(VCard.this.homeAddr, "HOME");
        }

        private void appendEmail(final String str, final String str2) {
            if (str != null) {
                appendTag("EMAIL", true, new ContentBuilder() {
                    public void addTagContent() {
                        VCardWriter.this.appendEmptyTag(str2);
                        VCardWriter.this.appendEmptyTag("INTERNET");
                        VCardWriter.this.appendEmptyTag("PREF");
                        VCardWriter.this.appendTag("USERID", StringUtils.escapeForXML(str));
                    }
                });
            }
        }

        private void appendPhones(Map<String, String> map, final String str) {
            for (final Map.Entry next : map.entrySet()) {
                appendTag("TEL", true, new ContentBuilder() {
                    public void addTagContent() {
                        VCardWriter.this.appendEmptyTag(next.getKey());
                        VCardWriter.this.appendEmptyTag(str);
                        VCardWriter.this.appendTag("NUMBER", StringUtils.escapeForXML((String) next.getValue()));
                    }
                });
            }
        }

        private void appendAddress(final Map<String, String> map, final String str) {
            if (map.size() > 0) {
                appendTag("ADR", true, new ContentBuilder() {
                    public void addTagContent() {
                        VCardWriter.this.appendEmptyTag(str);
                        for (Map.Entry entry : map.entrySet()) {
                            VCardWriter.this.appendTag((String) entry.getKey(), StringUtils.escapeForXML((String) entry.getValue()));
                        }
                    }
                });
            }
        }

        /* access modifiers changed from: private */
        public void appendEmptyTag(Object obj) {
            StringBuilder sb2 = this.sb;
            sb2.append(Typography.less);
            sb2.append(obj);
            sb2.append("/>");
        }

        private void appendGenericFields() {
            for (Map.Entry entry : VCard.this.otherSimpleFields.entrySet()) {
                appendTag(entry.getKey().toString(), StringUtils.escapeForXML((String) entry.getValue()));
            }
            for (Map.Entry entry2 : VCard.this.otherUnescapableFields.entrySet()) {
                appendTag(entry2.getKey().toString(), (String) entry2.getValue());
            }
        }

        private void appendOrganization() {
            if (VCard.this.hasOrganizationFields()) {
                appendTag("ORG", true, new ContentBuilder() {
                    public void addTagContent() {
                        VCardWriter vCardWriter = VCardWriter.this;
                        vCardWriter.appendTag("ORGNAME", StringUtils.escapeForXML(VCard.this.organization));
                        VCardWriter vCardWriter2 = VCardWriter.this;
                        vCardWriter2.appendTag("ORGUNIT", StringUtils.escapeForXML(VCard.this.organizationUnit));
                    }
                });
            }
        }

        private void appendN() {
            appendTag("", true, new ContentBuilder() {
                public void addTagContent() {
                    VCardWriter vCardWriter = VCardWriter.this;
                    vCardWriter.appendTag("FAMILY", StringUtils.escapeForXML(VCard.this.lastName));
                    VCardWriter vCardWriter2 = VCardWriter.this;
                    vCardWriter2.appendTag("GIVEN", StringUtils.escapeForXML(VCard.this.firstName));
                    VCardWriter vCardWriter3 = VCardWriter.this;
                    vCardWriter3.appendTag("MIDDLE", StringUtils.escapeForXML(VCard.this.middleName));
                }
            });
        }

        private void appendTag(String str, String str2, String str3, boolean z, ContentBuilder contentBuilder) {
            StringBuilder sb2 = this.sb;
            sb2.append(Typography.less);
            sb2.append(str);
            if (str2 != null) {
                StringBuilder sb3 = this.sb;
                sb3.append(' ');
                sb3.append(str2);
                sb3.append('=');
                sb3.append('\'');
                sb3.append(str3);
                sb3.append('\'');
            }
            if (z) {
                this.sb.append(Typography.greater);
                contentBuilder.addTagContent();
                StringBuilder sb4 = this.sb;
                sb4.append("</");
                sb4.append(str);
                sb4.append(">\n");
                return;
            }
            this.sb.append("/>\n");
        }

        private void appendTag(String str, boolean z, ContentBuilder contentBuilder) {
            appendTag(str, (String) null, (String) null, z, contentBuilder);
        }

        /* access modifiers changed from: private */
        public void appendTag(String str, final String str2) {
            if (str2 != null) {
                appendTag(str, true, new ContentBuilder() {
                    public void addTagContent() {
                        VCardWriter.this.sb.append(str2.trim());
                    }
                });
            }
        }
    }
}
