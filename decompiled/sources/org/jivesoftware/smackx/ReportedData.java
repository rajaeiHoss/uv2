package org.jivesoftware.smackx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smackx.packet.DataForm;

public class ReportedData {
    private List<Column> columns = new ArrayList();
    private List<Row> rows = new ArrayList();
    private String title = "";

    public static ReportedData getReportedDataFrom(Packet packet) {
        PacketExtension extension = packet.getExtension(GroupChatInvitation.ELEMENT_NAME, "jabber:x:data");
        if (extension == null) {
            return null;
        }
        DataForm dataForm = (DataForm) extension;
        if (dataForm.getReportedData() != null) {
            return new ReportedData(dataForm);
        }
        return null;
    }

    private ReportedData(DataForm dataForm) {
        Iterator<FormField> fields = dataForm.getReportedData().getFields();
        while (fields.hasNext()) {
            FormField next = fields.next();
            this.columns.add(new Column(next.getLabel(), next.getVariable(), next.getType()));
        }
        Iterator<DataForm.Item> items = dataForm.getItems();
        while (items.hasNext()) {
            ArrayList arrayList = new ArrayList(this.columns.size());
            Iterator<FormField> fields2 = items.next().getFields();
            while (fields2.hasNext()) {
                FormField next2 = fields2.next();
                ArrayList arrayList2 = new ArrayList();
                Iterator<String> values = next2.getValues();
                while (values.hasNext()) {
                    arrayList2.add(values.next());
                }
                arrayList.add(new Field(next2.getVariable(), arrayList2));
            }
            this.rows.add(new Row(arrayList));
        }
        this.title = dataForm.getTitle();
    }

    public ReportedData() {
    }

    public void addRow(Row row) {
        this.rows.add(row);
    }

    public void addColumn(Column column) {
        this.columns.add(column);
    }

    public Iterator<Row> getRows() {
        return Collections.unmodifiableList(new ArrayList(this.rows)).iterator();
    }

    public Iterator<Column> getColumns() {
        return Collections.unmodifiableList(new ArrayList(this.columns)).iterator();
    }

    public String getTitle() {
        return this.title;
    }

    public static class Column {
        private String label;
        private String type;
        private String variable;

        public Column(String str, String str2, String str3) {
            this.label = str;
            this.variable = str2;
            this.type = str3;
        }

        public String getLabel() {
            return this.label;
        }

        public String getType() {
            return this.type;
        }

        public String getVariable() {
            return this.variable;
        }
    }

    public static class Row {
        private List<Field> fields = new ArrayList();

        public Row(List<Field> list) {
            this.fields = list;
        }

        public Iterator getValues(String str) {
            Iterator<Field> fields2 = getFields();
            while (fields2.hasNext()) {
                Field next = fields2.next();
                if (str.equalsIgnoreCase(next.getVariable())) {
                    return next.getValues();
                }
            }
            return null;
        }

        private Iterator<Field> getFields() {
            return Collections.unmodifiableList(new ArrayList(this.fields)).iterator();
        }
    }

    public static class Field {
        private List<String> values;
        private String variable;

        public Field(String str, List<String> list) {
            this.variable = str;
            this.values = list;
        }

        public String getVariable() {
            return this.variable;
        }

        public Iterator<String> getValues() {
            return Collections.unmodifiableList(this.values).iterator();
        }
    }
}
