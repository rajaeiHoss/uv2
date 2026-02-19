package org.jivesoftware.smackx.provider;

import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.plus.PlusShare;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smack.provider.PacketExtensionProvider;
import org.jivesoftware.smackx.FormField;
import org.jivesoftware.smackx.packet.DataForm;
import org.xmlpull.v1.XmlPullParser;

public class DataFormProvider implements PacketExtensionProvider {
    public PacketExtension parseExtension(XmlPullParser xmlPullParser) throws Exception {
        DataForm dataForm = new DataForm(xmlPullParser.getAttributeValue("", AppMeasurement.Param.TYPE));
        boolean z = false;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("instructions")) {
                    dataForm.addInstruction(xmlPullParser.nextText());
                } else if (xmlPullParser.getName().equals(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE)) {
                    dataForm.setTitle(xmlPullParser.nextText());
                } else if (xmlPullParser.getName().equals("field")) {
                    dataForm.addField(parseField(xmlPullParser));
                } else if (xmlPullParser.getName().equals("item")) {
                    dataForm.addItem(parseItem(xmlPullParser));
                } else if (xmlPullParser.getName().equals("reported")) {
                    dataForm.setReportedData(parseReported(xmlPullParser));
                }
            } else if (next == 3 && xmlPullParser.getName().equals(dataForm.getElementName())) {
                z = true;
            }
        }
        return dataForm;
    }

    private FormField parseField(XmlPullParser xmlPullParser) throws Exception {
        FormField formField = new FormField(xmlPullParser.getAttributeValue("", "var"));
        formField.setLabel(xmlPullParser.getAttributeValue("", PlusShare.KEY_CALL_TO_ACTION_LABEL));
        formField.setType(xmlPullParser.getAttributeValue("", AppMeasurement.Param.TYPE));
        boolean z = false;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("desc")) {
                    formField.setDescription(xmlPullParser.nextText());
                } else if (xmlPullParser.getName().equals(FirebaseAnalytics.Param.VALUE)) {
                    formField.addValue(xmlPullParser.nextText());
                } else if (xmlPullParser.getName().equals("required")) {
                    formField.setRequired(true);
                } else if (xmlPullParser.getName().equals("option")) {
                    formField.addOption(parseOption(xmlPullParser));
                }
            } else if (next == 3 && xmlPullParser.getName().equals("field")) {
                z = true;
            }
        }
        return formField;
    }

    private DataForm.Item parseItem(XmlPullParser xmlPullParser) throws Exception {
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("field")) {
                    arrayList.add(parseField(xmlPullParser));
                }
            } else if (next == 3 && xmlPullParser.getName().equals("item")) {
                z = true;
            }
        }
        return new DataForm.Item(arrayList);
    }

    private DataForm.ReportedData parseReported(XmlPullParser xmlPullParser) throws Exception {
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("field")) {
                    arrayList.add(parseField(xmlPullParser));
                }
            } else if (next == 3 && xmlPullParser.getName().equals("reported")) {
                z = true;
            }
        }
        return new DataForm.ReportedData(arrayList);
    }

    private FormField.Option parseOption(XmlPullParser xmlPullParser) throws Exception {
        String attributeValue = xmlPullParser.getAttributeValue("", PlusShare.KEY_CALL_TO_ACTION_LABEL);
        boolean z = false;
        FormField.Option option = null;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals(FirebaseAnalytics.Param.VALUE)) {
                    option = new FormField.Option(attributeValue, xmlPullParser.nextText());
                }
            } else if (next == 3 && xmlPullParser.getName().equals("option")) {
                z = true;
            }
        }
        return option;
    }
}
