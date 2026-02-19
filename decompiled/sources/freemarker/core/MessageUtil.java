package freemarker.core;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.utility.StringUtil;
import java.util.ArrayList;
import org.jivesoftware.smack.packet.PrivacyItem;
import org.jivesoftware.smackx.FormField;

class MessageUtil {
    static final String EMBEDDED_MESSAGE_BEGIN = "---begin-message---\n";
    static final String EMBEDDED_MESSAGE_END = "\n---end-message---";
    static final String[] UNKNOWN_DATE_TO_STRING_TIPS;
    static final String[] UNKNOWN_DATE_TYPE_ERROR_TIPS;

    static {
        String[] strArr = {"Use ?time, ?date or ?datetime to tell FreeMarker which parts of the date is used.", "For programmers: Use java.sql.Date/Time/Timestamp instead of java.util.Date in the data-model to avoid this ambiguity."};
        UNKNOWN_DATE_TYPE_ERROR_TIPS = strArr;
        UNKNOWN_DATE_TO_STRING_TIPS = new String[]{"Use ?string(format) to specify which parts to display.", strArr[0], strArr[1]};
    }

    private MessageUtil() {
    }

    static String formatLocationForSimpleParsingError(Template template, int i, int i2) {
        return formatLocation("in", template, i, i2);
    }

    static String formatLocationForSimpleParsingError(String str, int i, int i2) {
        return formatLocation("in", str, i, i2);
    }

    static String formatLocationForDependentParsingError(Template template, int i, int i2) {
        return formatLocation("on", template, i, i2);
    }

    static String formatLocationForDependentParsingError(String str, int i, int i2) {
        return formatLocation("on", str, i, i2);
    }

    static String formatLocationForEvaluationError(Template template, int i, int i2) {
        return formatLocation("at", template, i, i2);
    }

    static String formatLocationForEvaluationError(Macro macro, int i, int i2) {
        Template template = macro.getTemplate();
        return formatLocation("at", template != null ? template.getName() : null, macro.getName(), macro.isFunction(), i, i2);
    }

    static String formatLocationForEvaluationError(String str, int i, int i2) {
        return formatLocation("at", str, i, i2);
    }

    private static String formatLocation(String str, Template template, int i, int i2) {
        return formatLocation(str, template != null ? template.getName() : null, i, i2);
    }

    private static String formatLocation(String str, String str2, int i, int i2) {
        return formatLocation(str, str2, (String) null, false, i, i2);
    }

    private static String formatLocation(String str, String str2, String str3, boolean z, int i, int i2) {
        String str4;
        String str5;
        if (i < 0) {
            i += 1000000001;
            str3 = null;
            str4 = "?eval-ed string";
        } else if (str2 != null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("template ");
            stringBuffer.append(StringUtil.jQuoteNoXSS(str2));
            str4 = stringBuffer.toString();
        } else {
            str4 = "nameless template";
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("in ");
        stringBuffer2.append(str4);
        if (str3 != null) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append(" in ");
            stringBuffer3.append(z ? "function " : "macro ");
            stringBuffer3.append(StringUtil.jQuote(str3));
            str5 = stringBuffer3.toString();
        } else {
            str5 = "";
        }
        stringBuffer2.append(str5);
        stringBuffer2.append(" ");
        stringBuffer2.append(str);
        stringBuffer2.append(" line ");
        stringBuffer2.append(i);
        stringBuffer2.append(", column ");
        stringBuffer2.append(i2);
        return stringBuffer2.toString();
    }

    static String shorten(String str, int i) {
        boolean z;
        if (i < 5) {
            i = 5;
        }
        int indexOf = str.indexOf(10);
        boolean z2 = true;
        if (indexOf != -1) {
            str = str.substring(0, indexOf);
            z = true;
        } else {
            z = false;
        }
        int indexOf2 = str.indexOf(13);
        if (indexOf2 != -1) {
            str = str.substring(0, indexOf2);
            z = true;
        }
        if (str.length() > i) {
            str = str.substring(0, i - 3);
        } else {
            z2 = z;
        }
        if (!z2) {
            return str;
        }
        if (!str.endsWith(".")) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str);
            stringBuffer.append("...");
            return stringBuffer.toString();
        } else if (!str.endsWith("..")) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(str);
            stringBuffer2.append("..");
            return stringBuffer2.toString();
        } else if (str.endsWith("...")) {
            return str;
        } else {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append(str);
            stringBuffer3.append(".");
            return stringBuffer3.toString();
        }
    }

    static StringBuffer appendExpressionAsUntearable(StringBuffer stringBuffer, Expression expression) {
        boolean z = !(expression instanceof NumberLiteral) && !(expression instanceof StringLiteral) && !(expression instanceof BooleanLiteral) && !(expression instanceof ListLiteral) && !(expression instanceof HashLiteral) && !(expression instanceof Identifier) && !(expression instanceof Dot) && !(expression instanceof DynamicKeyName) && !(expression instanceof MethodCall) && !(expression instanceof BuiltIn);
        if (z) {
            stringBuffer.append('(');
        }
        stringBuffer.append(expression.getCanonicalForm());
        if (z) {
            stringBuffer.append(')');
        }
        return stringBuffer;
    }

    static TemplateModelException newArgCntError(String str, int i, int i2) {
        return newArgCntError(str, i, i2, i2);
    }

    static TemplateModelException newArgCntError(String str, int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList(20);
        arrayList.add(str);
        arrayList.add("(");
        if (i3 != 0) {
            arrayList.add("...");
        }
        arrayList.add(") expects ");
        if (i2 == i3) {
            if (i3 == 0) {
                arrayList.add("no");
            } else {
                arrayList.add(new Integer(i3));
            }
        } else if (i3 - i2 == 1) {
            arrayList.add(new Integer(i2));
            arrayList.add(" or ");
            arrayList.add(new Integer(i3));
        } else {
            arrayList.add(new Integer(i2));
            if (i3 != Integer.MAX_VALUE) {
                arrayList.add(" to ");
                arrayList.add(new Integer(i3));
            } else {
                arrayList.add(" or more (unlimited)");
            }
        }
        arrayList.add(" argument");
        if (i3 > 1) {
            arrayList.add("s");
        }
        arrayList.add(" but has received ");
        if (i == 0) {
            arrayList.add(PrivacyItem.PrivacyRule.SUBSCRIPTION_NONE);
        } else {
            arrayList.add(new Integer(i));
        }
        arrayList.add(".");
        return new _TemplateModelException(arrayList.toArray());
    }

    static TemplateModelException newMethodArgMustBeStringException(String str, int i, TemplateModel templateModel) {
        return newMethodArgUnexpectedTypeException(str, i, "string", templateModel);
    }

    static TemplateModelException newMethodArgMustBeNumberException(String str, int i, TemplateModel templateModel) {
        return newMethodArgUnexpectedTypeException(str, i, "number", templateModel);
    }

    static TemplateModelException newMethodArgMustBeBooleanException(String str, int i, TemplateModel templateModel) {
        return newMethodArgUnexpectedTypeException(str, i, FormField.TYPE_BOOLEAN, templateModel);
    }

    static TemplateModelException newMethodArgMustBeExtendedHashException(String str, int i, TemplateModel templateModel) {
        return newMethodArgUnexpectedTypeException(str, i, "extended hash", templateModel);
    }

    static TemplateModelException newMethodArgMustBeSequenceException(String str, int i, TemplateModel templateModel) {
        return newMethodArgUnexpectedTypeException(str, i, "sequence", templateModel);
    }

    static TemplateModelException newMethodArgMustBeSequenceOrCollectionException(String str, int i, TemplateModel templateModel) {
        return newMethodArgUnexpectedTypeException(str, i, "sequence or collection", templateModel);
    }

    static TemplateModelException newMethodArgUnexpectedTypeException(String str, int i, String str2, TemplateModel templateModel) {
        return new _TemplateModelException(new Object[]{str, "(...) expects ", new _DelayedAOrAn(str2), " as argument #", new Integer(i + 1), ", but received ", new _DelayedAOrAn(new _DelayedFTLTypeDescription(templateModel)), "."});
    }

    static TemplateException newInstantiatingClassNotAllowedException(String str, Environment environment) {
        return new _MiscTemplateException(environment, new Object[]{"Instantiating ", str, " is not allowed in the template for security reasons."});
    }

    static String getAOrAn(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return "";
        }
        char c = 0;
        char lowerCase = Character.toLowerCase(str.charAt(0));
        if (lowerCase == 'a' || lowerCase == 'e' || lowerCase == 'i') {
            return "an";
        }
        if (lowerCase == 'h') {
            String lowerCase2 = str.toLowerCase();
            if (lowerCase2.startsWith("has") || lowerCase2.startsWith("hi")) {
                return "a";
            }
            return lowerCase2.startsWith("ht") ? "an" : "a(n)";
        } else if (lowerCase == 'u' || lowerCase == 'o') {
            return "a(n)";
        } else {
            if (str.length() > 1) {
                c = str.charAt(1);
            }
            return (lowerCase != 'x' || c == 'a' || c == 'e' || c == 'i' || c == 'a' || c == 'o' || c == 'u') ? "a" : "an";
        }
    }
}
