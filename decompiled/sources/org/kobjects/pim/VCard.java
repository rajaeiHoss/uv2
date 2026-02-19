package org.kobjects.pim;

public class VCard extends PimItem {
    public String getType() {
        return "vcard";
    }

    public VCard() {
    }

    public VCard(VCard vCard) {
        super(vCard);
    }

    public int getArraySize(String str) {
        if (str.equals("n")) {
            return 5;
        }
        return str.equals("adr") ? 6 : -1;
    }
}
