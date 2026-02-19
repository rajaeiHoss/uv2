package freemarker.core;

import freemarker.template.utility.StringUtil;
import java.io.IOException;

public final class TextBlock extends TemplateElement {
    static final TextBlock EMPTY_BLOCK;
    private static final char[] EMPTY_CHAR_ARRAY;
    private char[] text;
    private final boolean unparsed;

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return "#text";
    }

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 1;
    }

    static {
        char[] cArr = new char[0];
        EMPTY_CHAR_ARRAY = cArr;
        EMPTY_BLOCK = new TextBlock(cArr, false);
    }

    public TextBlock(String str) {
        this(str, false);
    }

    public TextBlock(String str, boolean z) {
        this(str.toCharArray(), z);
    }

    private TextBlock(char[] cArr, boolean z) {
        this.text = cArr;
        this.unparsed = z;
    }

    public void accept(Environment environment) throws IOException {
        environment.getOut().write(this.text);
    }

    /* access modifiers changed from: protected */
    public String dump(boolean z) {
        if (z) {
            String str = new String(this.text);
            if (!this.unparsed) {
                return str;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("<#noparse>");
            stringBuffer.append(str);
            stringBuffer.append("</#noparse>");
            return stringBuffer.toString();
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("text ");
        stringBuffer2.append(StringUtil.jQuote(new String(this.text)));
        return stringBuffer2.toString();
    }

    /* access modifiers changed from: package-private */
    public Object getParameterValue(int i) {
        if (i == 0) {
            return new String(this.text);
        }
        throw new IndexOutOfBoundsException();
    }

    /* access modifiers changed from: package-private */
    public ParameterRole getParameterRole(int i) {
        if (i == 0) {
            return ParameterRole.CONTENT;
        }
        throw new IndexOutOfBoundsException();
    }

    /* access modifiers changed from: package-private */
    public TemplateElement postParseCleanup(boolean z) {
        if (this.text.length == 0) {
            return this;
        }
        boolean deliberateLeftTrim = deliberateLeftTrim();
        boolean deliberateRightTrim = deliberateRightTrim();
        if (!z || this.text.length == 0 || (this.parent.parent == null && previousSibling() == null)) {
            return this;
        }
        int trailingCharsToStrip = !deliberateLeftTrim ? trailingCharsToStrip() : 0;
        int openingCharsToStrip = !deliberateRightTrim ? openingCharsToStrip() : 0;
        if (openingCharsToStrip == 0 && trailingCharsToStrip == 0) {
            return this;
        }
        char[] cArr = this.text;
        this.text = substring(cArr, openingCharsToStrip, cArr.length - trailingCharsToStrip);
        if (openingCharsToStrip > 0) {
            this.beginLine++;
            this.beginColumn = 1;
        }
        if (trailingCharsToStrip > 0) {
            this.endColumn = 0;
        }
        return this;
    }

    private boolean deliberateLeftTrim() {
        TemplateElement nextTerminalNode = nextTerminalNode();
        boolean z = false;
        while (nextTerminalNode != null && nextTerminalNode.beginLine == this.endLine) {
            if (nextTerminalNode instanceof TrimInstruction) {
                TrimInstruction trimInstruction = (TrimInstruction) nextTerminalNode;
                if (!trimInstruction.left && !trimInstruction.right) {
                    z = true;
                }
                if (trimInstruction.left) {
                    int lastNewLineIndex = lastNewLineIndex();
                    if (lastNewLineIndex >= 0 || this.beginColumn == 1) {
                        int i = lastNewLineIndex + 1;
                        char[] substring = substring(this.text, 0, i);
                        char[] substring2 = substring(this.text, i);
                        if (trim(substring2).length == 0) {
                            this.text = substring;
                            this.endColumn = 0;
                        } else {
                            int i2 = 0;
                            while (Character.isWhitespace(substring2[i2])) {
                                i2++;
                            }
                            this.text = concat(substring, substring(substring2, i2));
                        }
                    }
                    z = true;
                }
            }
            nextTerminalNode = nextTerminalNode.nextTerminalNode();
        }
        return z;
    }

    private boolean deliberateRightTrim() {
        TemplateElement prevTerminalNode = prevTerminalNode();
        boolean z = false;
        while (prevTerminalNode != null && prevTerminalNode.endLine == this.beginLine) {
            if (prevTerminalNode instanceof TrimInstruction) {
                TrimInstruction trimInstruction = (TrimInstruction) prevTerminalNode;
                if (!trimInstruction.left && !trimInstruction.right) {
                    z = true;
                }
                if (!trimInstruction.right) {
                    continue;
                } else {
                    int firstNewLineIndex = firstNewLineIndex() + 1;
                    if (firstNewLineIndex == 0) {
                        return false;
                    }
                    char[] cArr = this.text;
                    if (cArr.length > firstNewLineIndex && cArr[firstNewLineIndex - 1] == 13 && cArr[firstNewLineIndex] == 10) {
                        firstNewLineIndex++;
                    }
                    char[] substring = substring(cArr, firstNewLineIndex);
                    char[] substring2 = substring(this.text, 0, firstNewLineIndex);
                    if (trim(substring2).length == 0) {
                        this.text = substring;
                        this.beginLine++;
                        this.beginColumn = 1;
                    } else {
                        int length = substring2.length - 1;
                        while (Character.isWhitespace(this.text[length])) {
                            length--;
                        }
                        char[] substring3 = substring(this.text, 0, length + 1);
                        if (trim(substring).length == 0) {
                            TemplateElement nextTerminalNode = nextTerminalNode();
                            boolean z2 = true;
                            while (true) {
                                if (nextTerminalNode == null || nextTerminalNode.beginLine != this.endLine) {
                                    break;
                                }
                                if (nextTerminalNode.heedsOpeningWhitespace()) {
                                    z2 = false;
                                }
                                if ((nextTerminalNode instanceof TrimInstruction) && ((TrimInstruction) nextTerminalNode).left) {
                                    z2 = true;
                                    break;
                                }
                                nextTerminalNode = nextTerminalNode.nextTerminalNode();
                            }
                            if (z2) {
                                substring = EMPTY_CHAR_ARRAY;
                            }
                        }
                        this.text = concat(substring3, substring);
                    }
                    z = true;
                }
            }
            prevTerminalNode = prevTerminalNode.prevTerminalNode();
        }
        return z;
    }

    private int firstNewLineIndex() {
        String str = new String(this.text);
        int indexOf = str.indexOf(10);
        int indexOf2 = str.indexOf(13);
        int i = indexOf >= 0 ? indexOf : indexOf2;
        return (indexOf < 0 || indexOf2 < 0) ? i : Math.min(indexOf, indexOf2);
    }

    private int lastNewLineIndex() {
        String str = new String(this.text);
        return Math.max(str.lastIndexOf(13), str.lastIndexOf(10));
    }

    private int openingCharsToStrip() {
        int firstNewLineIndex = firstNewLineIndex();
        if (firstNewLineIndex == -1 && this.beginColumn != 1) {
            return 0;
        }
        int i = firstNewLineIndex + 1;
        char[] cArr = this.text;
        if (cArr.length > i && i > 0 && cArr[i - 1] == 13 && cArr[i] == 10) {
            i++;
        }
        if (new String(this.text).substring(0, i).trim().length() > 0) {
            return 0;
        }
        TemplateElement prevTerminalNode = prevTerminalNode();
        while (prevTerminalNode != null && prevTerminalNode.endLine == this.beginLine) {
            if (prevTerminalNode.heedsOpeningWhitespace()) {
                return 0;
            }
            prevTerminalNode = prevTerminalNode.prevTerminalNode();
        }
        return i;
    }

    private int trailingCharsToStrip() {
        String str = new String(this.text);
        int lastNewLineIndex = lastNewLineIndex();
        if (lastNewLineIndex == -1 && this.beginColumn != 1) {
            return 0;
        }
        String substring = str.substring(lastNewLineIndex + 1);
        if (substring.trim().length() > 0) {
            return 0;
        }
        TemplateElement nextTerminalNode = nextTerminalNode();
        while (nextTerminalNode != null && nextTerminalNode.beginLine == this.endLine) {
            if (nextTerminalNode.heedsTrailingWhitespace()) {
                return 0;
            }
            nextTerminalNode = nextTerminalNode.nextTerminalNode();
        }
        return substring.length();
    }

    /* access modifiers changed from: package-private */
    public boolean heedsTrailingWhitespace() {
        if (isIgnorable()) {
            return false;
        }
        int i = 0;
        while (true) {
            char[] cArr = this.text;
            if (i >= cArr.length) {
                return true;
            }
            char c = cArr[i];
            if (c == 10 || c == 13) {
                return false;
            }
            if (!Character.isWhitespace(c)) {
                return true;
            }
            i++;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean heedsOpeningWhitespace() {
        if (isIgnorable()) {
            return false;
        }
        for (int length = this.text.length - 1; length >= 0; length--) {
            char c = this.text[length];
            if (c == 10 || c == 13) {
                return false;
            }
            if (!Character.isWhitespace(c)) {
                return true;
            }
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean isIgnorable() {
        char[] cArr = this.text;
        if (cArr == null || cArr.length == 0) {
            return true;
        }
        if (!isWhitespace()) {
            return false;
        }
        boolean z = getParent().getParent() == null;
        TemplateElement previousSibling = previousSibling();
        TemplateElement nextSibling = nextSibling();
        if (((previousSibling != null || !z) && !nonOutputtingType(previousSibling)) || ((nextSibling != null || !z) && !nonOutputtingType(nextSibling))) {
            return false;
        }
        return true;
    }

    private boolean nonOutputtingType(TemplateElement templateElement) {
        return (templateElement instanceof Macro) || (templateElement instanceof Assignment) || (templateElement instanceof AssignmentInstruction) || (templateElement instanceof PropertySetting) || (templateElement instanceof LibraryLoad) || (templateElement instanceof Comment);
    }

    private static char[] substring(char[] cArr, int i, int i2) {
        int i3 = i2 - i;
        char[] cArr2 = new char[i3];
        System.arraycopy(cArr, i, cArr2, 0, i3);
        return cArr2;
    }

    private static char[] substring(char[] cArr, int i) {
        return substring(cArr, i, cArr.length);
    }

    private static char[] trim(char[] cArr) {
        if (cArr.length == 0) {
            return cArr;
        }
        return new String(cArr).trim().toCharArray();
    }

    private static char[] concat(char[] cArr, char[] cArr2) {
        char[] cArr3 = new char[(cArr.length + cArr2.length)];
        System.arraycopy(cArr, 0, cArr3, 0, cArr.length);
        System.arraycopy(cArr2, 0, cArr3, cArr.length, cArr2.length);
        return cArr3;
    }

    /* access modifiers changed from: package-private */
    public boolean isWhitespace() {
        char[] cArr = this.text;
        return cArr == null || trim(cArr).length == 0;
    }
}
