package org.jivesoftware.smackx;

import org.jivesoftware.smack.util.StringUtils;

public class XHTMLText {
    private StringBuilder text = new StringBuilder(30);

    private String closeBodyTag() {
        return "</body>";
    }

    public XHTMLText(String str, String str2) {
        appendOpenBodyTag(str, str2);
    }

    public void appendOpenAnchorTag(String str, String str2) {
        StringBuilder sb = new StringBuilder("<a");
        if (str != null) {
            sb.append(" href=\"");
            sb.append(str);
            sb.append("\"");
        }
        if (str2 != null) {
            sb.append(" style=\"");
            sb.append(str2);
            sb.append("\"");
        }
        sb.append(">");
        this.text.append(sb.toString());
    }

    public void appendCloseAnchorTag() {
        this.text.append("</a>");
    }

    public void appendOpenBlockQuoteTag(String str) {
        StringBuilder sb = new StringBuilder("<blockquote");
        if (str != null) {
            sb.append(" style=\"");
            sb.append(str);
            sb.append("\"");
        }
        sb.append(">");
        this.text.append(sb.toString());
    }

    public void appendCloseBlockQuoteTag() {
        this.text.append("</blockquote>");
    }

    private void appendOpenBodyTag(String str, String str2) {
        StringBuilder sb = new StringBuilder("<body");
        if (str != null) {
            sb.append(" style=\"");
            sb.append(str);
            sb.append("\"");
        }
        if (str2 != null) {
            sb.append(" xml:lang=\"");
            sb.append(str2);
            sb.append("\"");
        }
        sb.append(">");
        this.text.append(sb.toString());
    }

    public void appendBrTag() {
        this.text.append("<br>");
    }

    public void appendOpenCiteTag() {
        this.text.append("<cite>");
    }

    public void appendOpenCodeTag() {
        this.text.append("<code>");
    }

    public void appendCloseCodeTag() {
        this.text.append("</code>");
    }

    public void appendOpenEmTag() {
        this.text.append("<em>");
    }

    public void appendCloseEmTag() {
        this.text.append("</em>");
    }

    public void appendOpenHeaderTag(int i, String str) {
        if (i <= 3 && i >= 1) {
            StringBuilder sb = new StringBuilder("<h");
            sb.append(i);
            if (str != null) {
                sb.append(" style=\"");
                sb.append(str);
                sb.append("\"");
            }
            sb.append(">");
            this.text.append(sb.toString());
        }
    }

    public void appendCloseHeaderTag(int i) {
        if (i <= 3 && i >= 1) {
            this.text.append("</h" + i + ">");
        }
    }

    public void appendImageTag(String str, String str2, String str3, String str4, String str5) {
        StringBuilder sb = new StringBuilder("<img");
        if (str != null) {
            sb.append(" align=\"");
            sb.append(str);
            sb.append("\"");
        }
        if (str2 != null) {
            sb.append(" alt=\"");
            sb.append(str2);
            sb.append("\"");
        }
        if (str3 != null) {
            sb.append(" height=\"");
            sb.append(str3);
            sb.append("\"");
        }
        if (str4 != null) {
            sb.append(" src=\"");
            sb.append(str4);
            sb.append("\"");
        }
        if (str5 != null) {
            sb.append(" width=\"");
            sb.append(str5);
            sb.append("\"");
        }
        sb.append(">");
        this.text.append(sb.toString());
    }

    public void appendLineItemTag(String str) {
        StringBuilder sb = new StringBuilder("<li");
        if (str != null) {
            sb.append(" style=\"");
            sb.append(str);
            sb.append("\"");
        }
        sb.append(">");
        this.text.append(sb.toString());
    }

    public void appendOpenOrderedListTag(String str) {
        StringBuilder sb = new StringBuilder("<ol");
        if (str != null) {
            sb.append(" style=\"");
            sb.append(str);
            sb.append("\"");
        }
        sb.append(">");
        this.text.append(sb.toString());
    }

    public void appendCloseOrderedListTag() {
        this.text.append("</ol>");
    }

    public void appendOpenUnorderedListTag(String str) {
        StringBuilder sb = new StringBuilder("<ul");
        if (str != null) {
            sb.append(" style=\"");
            sb.append(str);
            sb.append("\"");
        }
        sb.append(">");
        this.text.append(sb.toString());
    }

    public void appendCloseUnorderedListTag() {
        this.text.append("</ul>");
    }

    public void appendOpenParagraphTag(String str) {
        StringBuilder sb = new StringBuilder("<p");
        if (str != null) {
            sb.append(" style=\"");
            sb.append(str);
            sb.append("\"");
        }
        sb.append(">");
        this.text.append(sb.toString());
    }

    public void appendCloseParagraphTag() {
        this.text.append("</p>");
    }

    public void appendOpenInlinedQuoteTag(String str) {
        StringBuilder sb = new StringBuilder("<q");
        if (str != null) {
            sb.append(" style=\"");
            sb.append(str);
            sb.append("\"");
        }
        sb.append(">");
        this.text.append(sb.toString());
    }

    public void appendCloseInlinedQuoteTag() {
        this.text.append("</q>");
    }

    public void appendOpenSpanTag(String str) {
        StringBuilder sb = new StringBuilder("<span");
        if (str != null) {
            sb.append(" style=\"");
            sb.append(str);
            sb.append("\"");
        }
        sb.append(">");
        this.text.append(sb.toString());
    }

    public void appendCloseSpanTag() {
        this.text.append("</span>");
    }

    public void appendOpenStrongTag() {
        this.text.append("<strong>");
    }

    public void appendCloseStrongTag() {
        this.text.append("</strong>");
    }

    public void append(String str) {
        this.text.append(StringUtils.escapeForXML(str));
    }

    public String toString() {
        return this.text.toString().concat(closeBodyTag());
    }
}
