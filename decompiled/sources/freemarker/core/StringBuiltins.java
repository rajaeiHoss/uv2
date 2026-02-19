package freemarker.core;

import freemarker.template.SimpleNumber;
import freemarker.template.SimpleScalar;
import freemarker.template.SimpleSequence;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateScalarModel;
import freemarker.template.Version;
import freemarker.template.utility.StringUtil;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.StringTokenizer;

class StringBuiltins {
    private StringBuiltins() {
    }

    static abstract class StringBuiltIn extends BuiltIn {
        /* access modifiers changed from: package-private */
        public abstract TemplateModel calculateResult(String str, Environment environment) throws TemplateException;

        StringBuiltIn() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel _eval(Environment environment) throws TemplateException {
            return calculateResult(this.target.evalAndCoerceToString(environment), environment);
        }
    }

    static class capitalizeBI extends StringBuiltIn {
        capitalizeBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(String str, Environment environment) {
            return new SimpleScalar(StringUtil.capitalize(str));
        }
    }

    static class chop_linebreakBI extends StringBuiltIn {
        chop_linebreakBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(String str, Environment environment) {
            return new SimpleScalar(StringUtil.chomp(str));
        }
    }

    static class j_stringBI extends StringBuiltIn {
        j_stringBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(String str, Environment environment) {
            return new SimpleScalar(StringUtil.javaStringEnc(str));
        }
    }

    static class js_stringBI extends StringBuiltIn {
        js_stringBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(String str, Environment environment) {
            return new SimpleScalar(StringUtil.javaScriptStringEnc(str));
        }
    }

    static class json_stringBI extends StringBuiltIn {
        json_stringBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(String str, Environment environment) {
            return new SimpleScalar(StringUtil.jsonStringEnc(str));
        }
    }

    static class cap_firstBI extends StringBuiltIn {
        cap_firstBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(String str, Environment environment) {
            int length = str.length();
            int i = 0;
            while (i < length && Character.isWhitespace(str.charAt(i))) {
                i++;
            }
            if (i < length) {
                StringBuffer stringBuffer = new StringBuffer(str);
                stringBuffer.setCharAt(i, Character.toUpperCase(str.charAt(i)));
                str = stringBuffer.toString();
            }
            return new SimpleScalar(str);
        }
    }

    static class uncap_firstBI extends StringBuiltIn {
        uncap_firstBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(String str, Environment environment) {
            int length = str.length();
            int i = 0;
            while (i < length && Character.isWhitespace(str.charAt(i))) {
                i++;
            }
            if (i < length) {
                StringBuffer stringBuffer = new StringBuffer(str);
                stringBuffer.setCharAt(i, Character.toLowerCase(str.charAt(i)));
                str = stringBuffer.toString();
            }
            return new SimpleScalar(str);
        }
    }

    static class upper_caseBI extends StringBuiltIn {
        upper_caseBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(String str, Environment environment) {
            return new SimpleScalar(str.toUpperCase(environment.getLocale()));
        }
    }

    static class lower_caseBI extends StringBuiltIn {
        lower_caseBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(String str, Environment environment) {
            return new SimpleScalar(str.toLowerCase(environment.getLocale()));
        }
    }

    static class word_listBI extends StringBuiltIn {
        word_listBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(String str, Environment environment) {
            SimpleSequence simpleSequence = new SimpleSequence();
            StringTokenizer stringTokenizer = new StringTokenizer(str);
            while (stringTokenizer.hasMoreTokens()) {
                simpleSequence.add((Object) stringTokenizer.nextToken());
            }
            return simpleSequence;
        }
    }

    static class evalBI extends StringBuiltIn {
        evalBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(String str, Environment environment) throws TemplateException {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("(");
            stringBuffer.append(str);
            stringBuffer.append(")");
            FMParserTokenManager fMParserTokenManager = new FMParserTokenManager(new SimpleCharStream((Reader) new StringReader(stringBuffer.toString()), -1000000000, 1, str.length() + 2));
            fMParserTokenManager.incompatibleImprovements = environment.getConfiguration().getIncompatibleImprovements().intValue();
            fMParserTokenManager.SwitchTo(2);
            FMParser fMParser = new FMParser(fMParserTokenManager);
            fMParser.setTemplate(getTemplate());
            try {
                try {
                    return fMParser.Expression().eval(environment);
                } catch (TemplateException e) {
                    throw new _MiscTemplateException((Expression) this, new Object[]{"Failed to \"?", this.key, "\" string with this error:\n\n", "---begin-message---\n", new _DelayedGetMessageWithoutStackTop(e), "\n---end-message---", "\n\nThe failing expression:"});
                }
            } catch (TokenMgrError e2) {
                throw e2.toParseException(getTemplate());
            } catch (ParseException e3) {
                throw new _MiscTemplateException((Expression) this, new Object[]{"Failed to \"?", this.key, "\" string with this error:\n\n", "---begin-message---\n", new _DelayedGetMessage(e3), "\n---end-message---", "\n\nThe failing expression:"});
            }
        }
    }

    static class numberBI extends StringBuiltIn {
        numberBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(String str, Environment environment) throws TemplateException {
            try {
                return new SimpleNumber(environment.getArithmeticEngine().toNumber(str));
            } catch (NumberFormatException unused) {
                throw NonNumericalException.newMalformedNumberException(this, str, environment);
            }
        }
    }

    static class substringBI extends StringBuiltIn {
        substringBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(final String str, Environment environment) throws TemplateException {
            return new TemplateMethodModelEx() {
                public Object exec(List list) throws TemplateModelException {
                    int size = list.size();
                    substringBI.this.checkMethodArgCount(size, 1, 2);
                    int intValue = substringBI.this.getNumberMethodArg(list, 0).intValue();
                    if (size > 1) {
                        return new SimpleScalar(str.substring(intValue, substringBI.this.getNumberMethodArg(list, 1).intValue()));
                    }
                    return new SimpleScalar(str.substring(intValue));
                }
            };
        }
    }

    static class lengthBI extends StringBuiltIn {
        lengthBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(String str, Environment environment) throws TemplateException {
            return new SimpleNumber(str.length());
        }
    }

    static class trimBI extends StringBuiltIn {
        trimBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(String str, Environment environment) {
            return new SimpleScalar(str.trim());
        }
    }

    static class htmlBI extends StringBuiltIn implements ICIChainMember {
        private static final int MIN_ICE = Version.intValueFor(2, 3, 20);
        private final BIBeforeICE2d3d20 prevICEObj = new BIBeforeICE2d3d20();

        htmlBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(String str, Environment environment) {
            return new SimpleScalar(StringUtil.XHTMLEnc(str));
        }

        static class BIBeforeICE2d3d20 extends StringBuiltIn {
            BIBeforeICE2d3d20() {
            }

            /* access modifiers changed from: package-private */
            public TemplateModel calculateResult(String str, Environment environment) {
                return new SimpleScalar(StringUtil.HTMLEnc(str));
            }
        }

        public int getMinimumICIVersion() {
            return MIN_ICE;
        }

        public Object getPreviousICIChainMember() {
            return this.prevICEObj;
        }
    }

    static class xmlBI extends StringBuiltIn {
        xmlBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(String str, Environment environment) {
            return new SimpleScalar(StringUtil.XMLEnc(str));
        }
    }

    static class xhtmlBI extends StringBuiltIn {
        xhtmlBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(String str, Environment environment) {
            return new SimpleScalar(StringUtil.XHTMLEnc(str));
        }
    }

    static class rtfBI extends StringBuiltIn {
        rtfBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(String str, Environment environment) {
            return new SimpleScalar(StringUtil.RTFEnc(str));
        }
    }

    static class urlBI extends StringBuiltIn {
        urlBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(String str, Environment environment) {
            return new urlBIResult(str, environment);
        }

        static class urlBIResult implements TemplateScalarModel, TemplateMethodModel {
            private String cachedResult;
            private final Environment env;
            private final String target;

            private urlBIResult(String str, Environment environment) {
                this.target = str;
                this.env = environment;
            }

            public String getAsString() throws TemplateModelException {
                if (this.cachedResult == null) {
                    String effectiveURLEscapingCharset = this.env.getEffectiveURLEscapingCharset();
                    if (effectiveURLEscapingCharset != null) {
                        try {
                            this.cachedResult = StringUtil.URLEnc(this.target, effectiveURLEscapingCharset);
                        } catch (UnsupportedEncodingException e) {
                            throw new _TemplateModelException((Throwable) e, "Failed to execute URL encoding.");
                        }
                    } else {
                        throw new _TemplateModelException("To do URL encoding, the framework that encloses FreeMarker must specify the output encoding or the URL encoding charset, so ask the programmers to fix it. Or, as a last chance, you can set the url_encoding_charset setting in the template, e.g. <#setting url_escaping_charset='ISO-8859-1'>, or give the charset explicitly to the buit-in, e.g. foo?url('ISO-8859-1').");
                    }
                }
                return this.cachedResult;
            }

            public Object exec(List list) throws TemplateModelException {
                if (list.size() == 1) {
                    try {
                        return new SimpleScalar(StringUtil.URLEnc(this.target, (String) list.get(0)));
                    } catch (UnsupportedEncodingException e) {
                        throw new _TemplateModelException((Throwable) e, "Failed to execute URL encoding.");
                    }
                } else {
                    throw new _TemplateModelException("The \"url\" built-in needs exactly 1 parameter, the charset.");
                }
            }
        }
    }

    static class starts_withBI extends StringBuiltIn {
        starts_withBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(String str, Environment environment) throws TemplateException {
            return new BIMethod(str);
        }

        private class BIMethod implements TemplateMethodModelEx {
            private String s;

            private BIMethod(String str) {
                this.s = str;
            }

            public Object exec(List list) throws TemplateModelException {
                starts_withBI.this.checkMethodArgCount(list, 1);
                return this.s.startsWith(starts_withBI.this.getStringMethodArg(list, 0)) ? TemplateBooleanModel.TRUE : TemplateBooleanModel.FALSE;
            }
        }
    }

    static class ends_withBI extends StringBuiltIn {
        ends_withBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(String str, Environment environment) throws TemplateException {
            return new BIMethod(str);
        }

        private class BIMethod implements TemplateMethodModelEx {
            private String s;

            private BIMethod(String str) {
                this.s = str;
            }

            public Object exec(List list) throws TemplateModelException {
                ends_withBI.this.checkMethodArgCount(list, 1);
                return this.s.endsWith(ends_withBI.this.getStringMethodArg(list, 0)) ? TemplateBooleanModel.TRUE : TemplateBooleanModel.FALSE;
            }
        }
    }

    static class replaceBI extends StringBuiltIn {
        replaceBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(String str, Environment environment) throws TemplateException {
            return new BIMethod(str);
        }

        private class BIMethod implements TemplateMethodModel {
            private String s;

            private BIMethod(String str) {
                this.s = str;
            }

            public Object exec(List list) throws TemplateModelException {
                boolean z;
                int size = list.size();
                replaceBI.this.checkMethodArgCount(size, 2, 3);
                boolean z2 = false;
                String str = (String) list.get(0);
                String str2 = (String) list.get(1);
                if (size > 2) {
                    String str3 = (String) list.get(2);
                    boolean z3 = str3.indexOf(105) >= 0;
                    if (str3.indexOf(102) >= 0) {
                        z2 = true;
                    }
                    if (str3.indexOf(114) < 0) {
                        z = z2;
                        z2 = z3;
                    } else {
                        throw new _TemplateModelException("The regular expression classes are not available.");
                    }
                } else {
                    z = false;
                }
                return new SimpleScalar(StringUtil.replace(this.s, str, str2, z2, z));
            }
        }
    }

    static class splitBI extends StringBuiltIn {
        splitBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(String str, Environment environment) throws TemplateException {
            return new BIMethod(str);
        }

        private class BIMethod implements TemplateMethodModel {
            private String s;

            private BIMethod(String str) {
                this.s = str;
            }

            public Object exec(List list) throws TemplateModelException {
                int size = list.size();
                boolean z = true;
                splitBI.this.checkMethodArgCount(size, 1, 2);
                String str = (String) list.get(0);
                String str2 = size == 2 ? (String) list.get(1) : "";
                if (str2.indexOf(105) < 0) {
                    z = false;
                }
                if (str2.indexOf(114) < 0) {
                    return new StringArraySequence(StringUtil.split(this.s, str, z));
                }
                throw new _TemplateModelException("Regular expression classes not available");
            }
        }
    }

    static class padBI extends StringBuiltIn {
        /* access modifiers changed from: private */
        public final boolean leftPadder;

        public padBI(boolean z) {
            this.leftPadder = z;
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(String str, Environment environment) throws TemplateException {
            return new BIMethod(str);
        }

        private class BIMethod implements TemplateMethodModelEx {
            private final String s;

            private BIMethod(String str) {
                this.s = str;
            }

            public Object exec(List list) throws TemplateModelException {
                int size = list.size();
                padBI.this.checkMethodArgCount(size, 1, 2);
                int intValue = padBI.this.getNumberMethodArg(list, 0).intValue();
                if (size > 1) {
                    String stringMethodArg = padBI.this.getStringMethodArg(list, 1);
                    try {
                        return new SimpleScalar(padBI.this.leftPadder ? StringUtil.leftPad(this.s, intValue, stringMethodArg) : StringUtil.rightPad(this.s, intValue, stringMethodArg));
                    } catch (IllegalArgumentException e) {
                        if (stringMethodArg.length() == 0) {
                            throw new _TemplateModelException(new Object[]{"?", padBI.this.key, "(...) argument #2 can't be a 0-length string."});
                        }
                        throw new _TemplateModelException((Throwable) e, new Object[]{"?", padBI.this.key, "(...) failed: ", e});
                    }
                } else {
                    return new SimpleScalar(padBI.this.leftPadder ? StringUtil.leftPad(this.s, intValue) : StringUtil.rightPad(this.s, intValue));
                }
            }
        }
    }

    static class containsBI extends BuiltIn {
        containsBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel _eval(Environment environment) throws TemplateException {
            return new BIMethod(this.target.evalAndCoerceToString(environment, "For sequences/collections (lists and such) use \"?seq_contains\" instead."));
        }

        private class BIMethod implements TemplateMethodModelEx {
            private final String s;

            private BIMethod(String str) {
                this.s = str;
            }

            public Object exec(List list) throws TemplateModelException {
                containsBI.this.checkMethodArgCount(list, 1);
                return this.s.indexOf(containsBI.this.getStringMethodArg(list, 0)) != -1 ? TemplateBooleanModel.TRUE : TemplateBooleanModel.FALSE;
            }
        }
    }

    static class index_ofBI extends BuiltIn {
        /* access modifiers changed from: private */
        public final boolean findLast;

        public index_ofBI(boolean z) {
            this.findLast = z;
        }

        /* access modifiers changed from: package-private */
        public TemplateModel _eval(Environment environment) throws TemplateException {
            return new BIMethod(this.target.evalAndCoerceToString(environment, "For sequences/collections (lists and such) use \"?seq_index_of\" instead."));
        }

        private class BIMethod implements TemplateMethodModelEx {
            private final String s;

            private BIMethod(String str) {
                this.s = str;
            }

            public Object exec(List list) throws TemplateModelException {
                int size = list.size();
                index_ofBI.this.checkMethodArgCount(size, 1, 2);
                String stringMethodArg = index_ofBI.this.getStringMethodArg(list, 0);
                if (size > 1) {
                    int intValue = index_ofBI.this.getNumberMethodArg(list, 1).intValue();
                    return new SimpleNumber(index_ofBI.this.findLast ? this.s.lastIndexOf(stringMethodArg, intValue) : this.s.indexOf(stringMethodArg, intValue));
                }
                return new SimpleNumber(index_ofBI.this.findLast ? this.s.lastIndexOf(stringMethodArg) : this.s.indexOf(stringMethodArg));
            }
        }
    }
}
