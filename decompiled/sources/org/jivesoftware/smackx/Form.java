package org.jivesoftware.smackx;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smackx.packet.DataForm;

public class Form {
    public static final String TYPE_CANCEL = "cancel";
    public static final String TYPE_FORM = "form";
    public static final String TYPE_RESULT = "result";
    public static final String TYPE_SUBMIT = "submit";
    private DataForm dataForm;

    public static Form getFormFrom(Packet packet) {
        PacketExtension extension = packet.getExtension(GroupChatInvitation.ELEMENT_NAME, "jabber:x:data");
        if (extension == null) {
            return null;
        }
        DataForm dataForm2 = (DataForm) extension;
        if (dataForm2.getReportedData() == null) {
            return new Form(dataForm2);
        }
        return null;
    }

    public Form(DataForm dataForm2) {
        this.dataForm = dataForm2;
    }

    public Form(String str) {
        this.dataForm = new DataForm(str);
    }

    public void addField(FormField formField) {
        this.dataForm.addField(formField);
    }

    public void setAnswer(String str, String str2) {
        FormField field = getField(str);
        if (field == null) {
            throw new IllegalArgumentException("Field not found for the specified variable name.");
        } else if (FormField.TYPE_TEXT_MULTI.equals(field.getType()) || FormField.TYPE_TEXT_PRIVATE.equals(field.getType()) || FormField.TYPE_TEXT_SINGLE.equals(field.getType()) || FormField.TYPE_JID_SINGLE.equals(field.getType()) || FormField.TYPE_HIDDEN.equals(field.getType())) {
            setAnswer(field, (Object) str2);
        } else {
            throw new IllegalArgumentException("This field is not of type String.");
        }
    }

    public void setAnswer(String str, int i) {
        FormField field = getField(str);
        if (field == null) {
            throw new IllegalArgumentException("Field not found for the specified variable name.");
        } else if (FormField.TYPE_TEXT_MULTI.equals(field.getType()) || FormField.TYPE_TEXT_PRIVATE.equals(field.getType()) || FormField.TYPE_TEXT_SINGLE.equals(field.getType())) {
            setAnswer(field, (Object) Integer.valueOf(i));
        } else {
            throw new IllegalArgumentException("This field is not of type int.");
        }
    }

    public void setAnswer(String str, long j) {
        FormField field = getField(str);
        if (field == null) {
            throw new IllegalArgumentException("Field not found for the specified variable name.");
        } else if (FormField.TYPE_TEXT_MULTI.equals(field.getType()) || FormField.TYPE_TEXT_PRIVATE.equals(field.getType()) || FormField.TYPE_TEXT_SINGLE.equals(field.getType())) {
            setAnswer(field, (Object) Long.valueOf(j));
        } else {
            throw new IllegalArgumentException("This field is not of type long.");
        }
    }

    public void setAnswer(String str, float f) {
        FormField field = getField(str);
        if (field == null) {
            throw new IllegalArgumentException("Field not found for the specified variable name.");
        } else if (FormField.TYPE_TEXT_MULTI.equals(field.getType()) || FormField.TYPE_TEXT_PRIVATE.equals(field.getType()) || FormField.TYPE_TEXT_SINGLE.equals(field.getType())) {
            setAnswer(field, (Object) Float.valueOf(f));
        } else {
            throw new IllegalArgumentException("This field is not of type float.");
        }
    }

    public void setAnswer(String str, double d) {
        FormField field = getField(str);
        if (field == null) {
            throw new IllegalArgumentException("Field not found for the specified variable name.");
        } else if (FormField.TYPE_TEXT_MULTI.equals(field.getType()) || FormField.TYPE_TEXT_PRIVATE.equals(field.getType()) || FormField.TYPE_TEXT_SINGLE.equals(field.getType())) {
            setAnswer(field, (Object) Double.valueOf(d));
        } else {
            throw new IllegalArgumentException("This field is not of type double.");
        }
    }

    public void setAnswer(String str, boolean z) {
        FormField field = getField(str);
        if (field == null) {
            throw new IllegalArgumentException("Field not found for the specified variable name.");
        } else if (FormField.TYPE_BOOLEAN.equals(field.getType())) {
            setAnswer(field, z ? "1" : "0");
        } else {
            throw new IllegalArgumentException("This field is not of type boolean.");
        }
    }

    private void setAnswer(FormField formField, Object obj) {
        if (isSubmitType()) {
            formField.resetValues();
            formField.addValue(obj.toString());
            return;
        }
        throw new IllegalStateException("Cannot set an answer if the form is not of type \"submit\"");
    }

    public void setAnswer(String str, List<String> list) {
        if (isSubmitType()) {
            FormField field = getField(str);
            if (field == null) {
                throw new IllegalArgumentException("Couldn't find a field for the specified variable.");
            } else if (FormField.TYPE_JID_MULTI.equals(field.getType()) || FormField.TYPE_LIST_MULTI.equals(field.getType()) || FormField.TYPE_LIST_SINGLE.equals(field.getType()) || FormField.TYPE_HIDDEN.equals(field.getType())) {
                field.resetValues();
                field.addValues(list);
            } else {
                throw new IllegalArgumentException("This field only accept list of values.");
            }
        } else {
            throw new IllegalStateException("Cannot set an answer if the form is not of type \"submit\"");
        }
    }

    public void setDefaultAnswer(String str) {
        if (isSubmitType()) {
            FormField field = getField(str);
            if (field != null) {
                field.resetValues();
                Iterator<String> values = field.getValues();
                while (values.hasNext()) {
                    field.addValue(values.next());
                }
                return;
            }
            throw new IllegalArgumentException("Couldn't find a field for the specified variable.");
        }
        throw new IllegalStateException("Cannot set an answer if the form is not of type \"submit\"");
    }

    public Iterator<FormField> getFields() {
        return this.dataForm.getFields();
    }

    public FormField getField(String str) {
        if (str == null || str.equals("")) {
            throw new IllegalArgumentException("Variable must not be null or blank.");
        }
        Iterator<FormField> fields = getFields();
        while (fields.hasNext()) {
            FormField next = fields.next();
            if (str.equals(next.getVariable())) {
                return next;
            }
        }
        return null;
    }

    public String getInstructions() {
        StringBuilder sb = new StringBuilder();
        Iterator<String> instructions = this.dataForm.getInstructions();
        while (instructions.hasNext()) {
            sb.append(instructions.next());
            if (instructions.hasNext()) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public String getTitle() {
        return this.dataForm.getTitle();
    }

    public String getType() {
        return this.dataForm.getType();
    }

    public void setInstructions(String str) {
        ArrayList arrayList = new ArrayList();
        StringTokenizer stringTokenizer = new StringTokenizer(str, "\n");
        while (stringTokenizer.hasMoreTokens()) {
            arrayList.add(stringTokenizer.nextToken());
        }
        this.dataForm.setInstructions(arrayList);
    }

    public void setTitle(String str) {
        this.dataForm.setTitle(str);
    }

    public DataForm getDataFormToSend() {
        if (!isSubmitType()) {
            return this.dataForm;
        }
        DataForm dataForm2 = new DataForm(getType());
        Iterator<FormField> fields = getFields();
        while (fields.hasNext()) {
            FormField next = fields.next();
            if (next.getValues().hasNext()) {
                dataForm2.addField(next);
            }
        }
        return dataForm2;
    }

    private boolean isFormType() {
        return TYPE_FORM.equals(this.dataForm.getType());
    }

    private boolean isSubmitType() {
        return TYPE_SUBMIT.equals(this.dataForm.getType());
    }

    public Form createAnswerForm() {
        if (isFormType()) {
            Form form = new Form(TYPE_SUBMIT);
            Iterator<FormField> fields = getFields();
            while (fields.hasNext()) {
                FormField next = fields.next();
                if (next.getVariable() != null) {
                    FormField formField = new FormField(next.getVariable());
                    formField.setType(next.getType());
                    form.addField(formField);
                    if (FormField.TYPE_HIDDEN.equals(next.getType())) {
                        ArrayList arrayList = new ArrayList();
                        Iterator<String> values = next.getValues();
                        while (values.hasNext()) {
                            arrayList.add(values.next());
                        }
                        form.setAnswer(next.getVariable(), (List<String>) arrayList);
                    }
                }
            }
            return form;
        }
        throw new IllegalStateException("Only forms of type \"form\" could be answered");
    }
}
