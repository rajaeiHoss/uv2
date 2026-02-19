package org.jivesoftware.smackx.search;

import com.google.android.gms.actions.SearchIntents;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.PacketIDFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.jivesoftware.smackx.Form;
import org.jivesoftware.smackx.FormField;
import org.jivesoftware.smackx.GroupChatInvitation;
import org.jivesoftware.smackx.ReportedData;
import org.jivesoftware.smackx.packet.DataForm;
import org.jivesoftware.smackx.packet.Nick;
import org.xmlpull.v1.XmlPullParser;

public class UserSearch extends IQ {
    public String getChildElementXML() {
        return "<query xmlns=\"jabber:iq:search\">" + getExtensionsXML() + "</query>";
    }

    public Form getSearchForm(Connection connection, String str) throws XMPPException {
        UserSearch userSearch = new UserSearch();
        userSearch.setType(IQ.Type.GET);
        userSearch.setTo(str);
        PacketCollector createPacketCollector = connection.createPacketCollector(new PacketIDFilter(userSearch.getPacketID()));
        connection.sendPacket(userSearch);
        IQ iq = (IQ) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
        createPacketCollector.cancel();
        if (iq == null) {
            throw new XMPPException("No response from server on status set.");
        } else if (iq.getError() == null) {
            return Form.getFormFrom(iq);
        } else {
            throw new XMPPException(iq.getError());
        }
    }

    public ReportedData sendSearchForm(Connection connection, Form form, String str) throws XMPPException {
        UserSearch userSearch = new UserSearch();
        userSearch.setType(IQ.Type.SET);
        userSearch.setTo(str);
        userSearch.addExtension(form.getDataFormToSend());
        PacketCollector createPacketCollector = connection.createPacketCollector(new PacketIDFilter(userSearch.getPacketID()));
        connection.sendPacket(userSearch);
        IQ iq = (IQ) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
        createPacketCollector.cancel();
        if (iq == null) {
            throw new XMPPException("No response from server on status set.");
        } else if (iq.getError() != null) {
            return sendSimpleSearchForm(connection, form, str);
        } else {
            return ReportedData.getReportedDataFrom(iq);
        }
    }

    public ReportedData sendSimpleSearchForm(Connection connection, Form form, String str) throws XMPPException {
        SimpleUserSearch simpleUserSearch = new SimpleUserSearch();
        simpleUserSearch.setForm(form);
        simpleUserSearch.setType(IQ.Type.SET);
        simpleUserSearch.setTo(str);
        PacketCollector createPacketCollector = connection.createPacketCollector(new PacketIDFilter(simpleUserSearch.getPacketID()));
        connection.sendPacket(simpleUserSearch);
        IQ iq = (IQ) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
        createPacketCollector.cancel();
        if (iq == null) {
            throw new XMPPException("No response from server on status set.");
        } else if (iq.getError() != null) {
            throw new XMPPException(iq.getError());
        } else if (iq instanceof SimpleUserSearch) {
            return ((SimpleUserSearch) iq).getReportedData();
        } else {
            return null;
        }
    }

    public static class Provider implements IQProvider {
        public IQ parseIQ(XmlPullParser xmlPullParser) throws Exception {
            SimpleUserSearch simpleUserSearch = new SimpleUserSearch();
            UserSearch userSearch = null;
            boolean z = false;
            while (!z) {
                int next = xmlPullParser.next();
                if (next == 2 && xmlPullParser.getName().equals("instructions")) {
                    UserSearch.buildDataForm(simpleUserSearch, xmlPullParser.nextText(), xmlPullParser);
                    return simpleUserSearch;
                } else if (next == 2 && xmlPullParser.getName().equals("item")) {
                    simpleUserSearch.parseItems(xmlPullParser);
                    return simpleUserSearch;
                } else if (next == 2 && xmlPullParser.getNamespace().equals("jabber:x:data")) {
                    userSearch = new UserSearch();
                    userSearch.addExtension(PacketParserUtils.parsePacketExtension(xmlPullParser.getName(), xmlPullParser.getNamespace(), xmlPullParser));
                } else if (next == 3 && xmlPullParser.getName().equals(SearchIntents.EXTRA_QUERY)) {
                    z = true;
                }
            }
            return userSearch != null ? userSearch : simpleUserSearch;
        }
    }

    /* access modifiers changed from: private */
    public static void buildDataForm(SimpleUserSearch simpleUserSearch, String str, XmlPullParser xmlPullParser) throws Exception {
        DataForm dataForm = new DataForm(Form.TYPE_FORM);
        dataForm.setTitle("User Search");
        dataForm.addInstruction(str);
        boolean z = false;
        while (!z) {
            int next = xmlPullParser.next();
            if (next != 2 || xmlPullParser.getNamespace().equals("jabber:x:data")) {
                if (next == 3) {
                    if (!xmlPullParser.getName().equals(SearchIntents.EXTRA_QUERY)) {
                    }
                } else if (next == 2 && xmlPullParser.getNamespace().equals("jabber:x:data")) {
                    simpleUserSearch.addExtension(PacketParserUtils.parsePacketExtension(xmlPullParser.getName(), xmlPullParser.getNamespace(), xmlPullParser));
                }
                z = true;
            } else {
                String name = xmlPullParser.getName();
                FormField formField = new FormField(name);
                if (name.equals("first")) {
                    formField.setLabel("First Name");
                } else if (name.equals("last")) {
                    formField.setLabel("Last Name");
                } else if (name.equals("email")) {
                    formField.setLabel("Email Address");
                } else if (name.equals(Nick.ELEMENT_NAME)) {
                    formField.setLabel("Nickname");
                }
                formField.setType(FormField.TYPE_TEXT_SINGLE);
                dataForm.addField(formField);
            }
        }
        if (simpleUserSearch.getExtension(GroupChatInvitation.ELEMENT_NAME, "jabber:x:data") == null) {
            simpleUserSearch.addExtension(dataForm);
        }
    }
}
