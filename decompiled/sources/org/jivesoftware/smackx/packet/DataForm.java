package org.jivesoftware.smackx.packet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smackx.FormField;
import org.jivesoftware.smackx.GroupChatInvitation;

public class DataForm implements PacketExtension {
    private final List<FormField> fields = new ArrayList();
    private List<String> instructions = new ArrayList();
    private final List<Item> items = new ArrayList();
    private ReportedData reportedData;
    private String title;
    private String type;

    public String getElementName() {
        return GroupChatInvitation.ELEMENT_NAME;
    }

    public String getNamespace() {
        return "jabber:x:data";
    }

    public DataForm(String str) {
        this.type = str;
    }

    public String getType() {
        return this.type;
    }

    public String getTitle() {
        return this.title;
    }

    public Iterator<String> getInstructions() {
        Iterator<String> it;
        synchronized (this.instructions) {
            it = Collections.unmodifiableList(new ArrayList(this.instructions)).iterator();
        }
        return it;
    }

    public ReportedData getReportedData() {
        return this.reportedData;
    }

    public Iterator<Item> getItems() {
        Iterator<Item> it;
        synchronized (this.items) {
            it = Collections.unmodifiableList(new ArrayList(this.items)).iterator();
        }
        return it;
    }

    public Iterator<FormField> getFields() {
        Iterator<FormField> it;
        synchronized (this.fields) {
            it = Collections.unmodifiableList(new ArrayList(this.fields)).iterator();
        }
        return it;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setInstructions(List<String> list) {
        this.instructions = list;
    }

    public void setReportedData(ReportedData reportedData2) {
        this.reportedData = reportedData2;
    }

    public void addField(FormField formField) {
        synchronized (this.fields) {
            this.fields.add(formField);
        }
    }

    public void addInstruction(String str) {
        synchronized (this.instructions) {
            this.instructions.add(str);
        }
    }

    public void addItem(Item item) {
        synchronized (this.items) {
            this.items.add(item);
        }
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(getElementName());
        sb.append(" xmlns=\"");
        sb.append(getNamespace());
        sb.append("\" type=\"" + getType() + "\">");
        if (getTitle() != null) {
            sb.append("<title>");
            sb.append(getTitle());
            sb.append("</title>");
        }
        Iterator<String> instructions2 = getInstructions();
        while (instructions2.hasNext()) {
            sb.append("<instructions>");
            sb.append(instructions2.next());
            sb.append("</instructions>");
        }
        if (getReportedData() != null) {
            sb.append(getReportedData().toXML());
        }
        Iterator<Item> items2 = getItems();
        while (items2.hasNext()) {
            sb.append(items2.next().toXML());
        }
        Iterator<FormField> fields2 = getFields();
        while (fields2.hasNext()) {
            sb.append(fields2.next().toXML());
        }
        sb.append("</");
        sb.append(getElementName());
        sb.append(">");
        return sb.toString();
    }

    public static class ReportedData {
        private List<FormField> fields = new ArrayList();

        public ReportedData(List<FormField> list) {
            this.fields = list;
        }

        public Iterator<FormField> getFields() {
            return Collections.unmodifiableList(new ArrayList(this.fields)).iterator();
        }

        public String toXML() {
            StringBuilder sb = new StringBuilder();
            sb.append("<reported>");
            Iterator<FormField> fields2 = getFields();
            while (fields2.hasNext()) {
                sb.append(fields2.next().toXML());
            }
            sb.append("</reported>");
            return sb.toString();
        }
    }

    public static class Item {
        private List<FormField> fields = new ArrayList();

        public Item(List<FormField> list) {
            this.fields = list;
        }

        public Iterator<FormField> getFields() {
            return Collections.unmodifiableList(new ArrayList(this.fields)).iterator();
        }

        public String toXML() {
            StringBuilder sb = new StringBuilder();
            sb.append("<item>");
            Iterator<FormField> fields2 = getFields();
            while (fields2.hasNext()) {
                sb.append(fields2.next().toXML());
            }
            sb.append("</item>");
            return sb.toString();
        }
    }
}
