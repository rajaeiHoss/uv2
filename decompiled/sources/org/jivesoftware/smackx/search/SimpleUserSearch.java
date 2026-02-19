package org.jivesoftware.smackx.search;

import com.google.android.gms.actions.SearchIntents;
import java.util.ArrayList;
import java.util.Iterator;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smackx.Form;
import org.jivesoftware.smackx.FormField;
import org.jivesoftware.smackx.ReportedData;
import org.xmlpull.v1.XmlPullParser;

class SimpleUserSearch extends IQ {
    private ReportedData data;
    private Form form;

    SimpleUserSearch() {
    }

    public void setForm(Form form2) {
        this.form = form2;
    }

    public ReportedData getReportedData() {
        return this.data;
    }

    public String getChildElementXML() {
        return "<query xmlns=\"jabber:iq:search\">" + getItemsToSearch() + "</query>";
    }

    private String getItemsToSearch() {
        StringBuilder sb = new StringBuilder();
        if (this.form == null) {
            this.form = Form.getFormFrom(this);
        }
        Form form2 = this.form;
        if (form2 == null) {
            return "";
        }
        Iterator<FormField> fields = form2.getFields();
        while (fields.hasNext()) {
            FormField next = fields.next();
            String variable = next.getVariable();
            String singleValue = getSingleValue(next);
            if (singleValue.trim().length() > 0) {
                sb.append("<");
                sb.append(variable);
                sb.append(">");
                sb.append(singleValue);
                sb.append("</");
                sb.append(variable);
                sb.append(">");
            }
        }
        return sb.toString();
    }

    private static String getSingleValue(FormField formField) {
        Iterator<String> values = formField.getValues();
        return values.hasNext() ? values.next() : "";
    }

    /* access modifiers changed from: protected */
    public void parseItems(XmlPullParser xmlPullParser) throws Exception {
        ReportedData reportedData = new ReportedData();
        reportedData.addColumn(new ReportedData.Column("JID", "jid", FormField.TYPE_TEXT_SINGLE));
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        while (!z) {
            if (xmlPullParser.getAttributeCount() > 0) {
                String attributeValue = xmlPullParser.getAttributeValue("", "jid");
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(attributeValue);
                arrayList.add(new ReportedData.Field("jid", arrayList2));
            }
            int next = xmlPullParser.next();
            if (next == 2 && xmlPullParser.getName().equals("item")) {
                arrayList = new ArrayList();
            } else if (next == 3 && xmlPullParser.getName().equals("item")) {
                reportedData.addRow(new ReportedData.Row(arrayList));
            } else if (next == 2) {
                String name = xmlPullParser.getName();
                String nextText = xmlPullParser.nextText();
                ArrayList arrayList3 = new ArrayList();
                arrayList3.add(nextText);
                arrayList.add(new ReportedData.Field(name, arrayList3));
                Iterator<ReportedData.Column> columns = reportedData.getColumns();
                boolean z2 = false;
                while (columns.hasNext()) {
                    if (columns.next().getVariable().equals(name)) {
                        z2 = true;
                    }
                }
                if (!z2) {
                    reportedData.addColumn(new ReportedData.Column(name, name, FormField.TYPE_TEXT_SINGLE));
                }
            } else if (next == 3 && xmlPullParser.getName().equals(SearchIntents.EXTRA_QUERY)) {
                z = true;
            }
        }
        this.data = reportedData;
    }
}
