package freemarker.core;

import freemarker.cache.MruCacheStorage;
import freemarker.core.StringBuiltins;
import freemarker.log.Logger;
import freemarker.template.ObjectWrapper;
import freemarker.template.SimpleScalar;
import freemarker.template.SimpleSequence;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateModelIterator;
import freemarker.template.TemplateScalarModel;
import freemarker.template.TemplateSequenceModel;
import freemarker.template.utility.StringUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class _RegexBuiltins {
    private static final int MAX_FLAG_WARNINGS_LOGGED = 25;
    /* access modifiers changed from: private */
    public static final long RE_FLAG_CASE_INSENSITIVE = intFlagToLong(2);
    private static final long RE_FLAG_COMMENTS = intFlagToLong(4);
    private static final long RE_FLAG_DOTALL = intFlagToLong(32);
    private static final long RE_FLAG_FIRST_ONLY = 8589934592L;
    private static final long RE_FLAG_MULTILINE = intFlagToLong(8);
    private static final long RE_FLAG_REGEXP = 4294967296L;
    private static int flagWarningsCnt;
    private static final Object flagWarningsCntSync = new Object();
    private static volatile boolean flagWarningsEnabled;
    private static final Logger logger;
    static final MruCacheStorage patternCache = new MruCacheStorage(50, 150);

    private static long intFlagToLong(int i) {
        return ((long) i) & 65535;
    }

    private _RegexBuiltins() {
    }

    static {
        Logger logger2 = Logger.getLogger("freemarker.runtime");
        logger = logger2;
        flagWarningsEnabled = logger2.isWarnEnabled();
    }

    static Pattern getPattern(String str, int i) throws TemplateModelException {
        Pattern pattern;
        PatternCacheKey patternCacheKey = new PatternCacheKey(str, i);
        MruCacheStorage mruCacheStorage = patternCache;
        synchronized (mruCacheStorage) {
            pattern = (Pattern) mruCacheStorage.get(patternCacheKey);
        }
        if (pattern != null) {
            return pattern;
        }
        try {
            Pattern compile = Pattern.compile(str, i);
            synchronized (mruCacheStorage) {
                mruCacheStorage.put(patternCacheKey, compile);
            }
            return compile;
        } catch (PatternSyntaxException e) {
            throw new _TemplateModelException((Throwable) e, new Object[]{"Malformed regular expression: ", new _DelayedGetMessage(e)});
        }
    }

    private static class PatternCacheKey {
        private final int flags;
        private final int hashCode;
        private final String patternString;

        public PatternCacheKey(String str, int i) {
            this.patternString = str;
            this.flags = i;
            this.hashCode = str.hashCode() + (i * 31);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof PatternCacheKey)) {
                return false;
            }
            PatternCacheKey patternCacheKey = (PatternCacheKey) obj;
            if (patternCacheKey.flags != this.flags || !patternCacheKey.patternString.equals(this.patternString)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return this.hashCode;
        }
    }

    /* access modifiers changed from: private */
    public static long parseFlagString(String str) {
        long j;
        long j2 = 0;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == 'c') {
                j = RE_FLAG_COMMENTS;
            } else if (charAt == 'f') {
                j = RE_FLAG_FIRST_ONLY;
            } else if (charAt == 'i') {
                j = RE_FLAG_CASE_INSENSITIVE;
            } else if (charAt == 'm') {
                j = RE_FLAG_MULTILINE;
            } else if (charAt == 'r') {
                j = RE_FLAG_REGEXP;
            } else if (charAt != 's') {
                if (flagWarningsEnabled) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Unrecognized regular expression flag: ");
                    stringBuffer.append(StringUtil.jQuote(String.valueOf(charAt)));
                    stringBuffer.append(".");
                    logFlagWarning(stringBuffer.toString());
                }
            } else {
                j = RE_FLAG_DOTALL;
            }
            j2 |= j;
        }
        return j2;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0013, code lost:
        r0 = new java.lang.StringBuffer();
        r0.append(r4);
        r0.append(" This will be an error in FreeMarker 2.4!");
        r4 = r0.toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0026, code lost:
        if ((r1 + 1) != 25) goto L_0x0039;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0028, code lost:
        r0 = new java.lang.StringBuffer();
        r0.append(r4);
        r0.append(" [Will not log more regular expression flag problems until restart!]");
        r4 = r0.toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0039, code lost:
        logger.warn(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void logFlagWarning(java.lang.String r4) {
        /*
            boolean r0 = flagWarningsEnabled
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            java.lang.Object r0 = flagWarningsCntSync
            monitor-enter(r0)
            int r1 = flagWarningsCnt     // Catch:{ all -> 0x0044 }
            r2 = 25
            if (r1 >= r2) goto L_0x003f
            int r3 = r1 + 1
            flagWarningsCnt = r3     // Catch:{ all -> 0x0044 }
            monitor-exit(r0)     // Catch:{ all -> 0x0044 }
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            r0.append(r4)
            java.lang.String r4 = " This will be an error in FreeMarker 2.4!"
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            int r1 = r1 + 1
            if (r1 != r2) goto L_0x0039
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            r0.append(r4)
            java.lang.String r4 = " [Will not log more regular expression flag problems until restart!]"
            r0.append(r4)
            java.lang.String r4 = r0.toString()
        L_0x0039:
            freemarker.log.Logger r0 = logger
            r0.warn(r4)
            return
        L_0x003f:
            r4 = 0
            flagWarningsEnabled = r4     // Catch:{ all -> 0x0044 }
            monitor-exit(r0)     // Catch:{ all -> 0x0044 }
            return
        L_0x0044:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0044 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.core._RegexBuiltins.logFlagWarning(java.lang.String):void");
    }

    public static class matchesBI extends StringBuiltins.StringBuiltIn {
        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(String str, Environment environment) throws TemplateModelException {
            return new MatcherBuilder(str);
        }

        class MatcherBuilder implements TemplateMethodModel {
            String matchString;

            MatcherBuilder(String str) throws TemplateModelException {
                this.matchString = str;
            }

            public Object exec(List list) throws TemplateModelException {
                int size = list.size();
                matchesBI.this.checkMethodArgCount(size, 1, 2);
                String str = (String) list.get(0);
                long access$000 = size > 1 ? _RegexBuiltins.parseFlagString((String) list.get(1)) : 0;
                if ((_RegexBuiltins.RE_FLAG_FIRST_ONLY & access$000) != 0) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("?");
                    stringBuffer.append(matchesBI.this.key);
                    stringBuffer.append(" doesn't support the \"f\" flag.");
                    _RegexBuiltins.logFlagWarning(stringBuffer.toString());
                }
                return new RegexMatchModel(_RegexBuiltins.getPattern(str, (int) access$000).matcher(this.matchString), this.matchString);
            }
        }
    }

    public static class groupsBI extends BuiltIn {
        /* access modifiers changed from: package-private */
        public TemplateModel _eval(Environment environment) throws TemplateException {
            TemplateModel eval = this.target.eval(environment);
            assertNonNull(eval, environment);
            if (eval instanceof RegexMatchModel) {
                return ((RegexMatchModel) eval).getGroups();
            }
            if (eval instanceof RegexMatchModel.Match) {
                return ((RegexMatchModel.Match) eval).subs;
            }
            throw new UnexpectedTypeException(this.target, eval, "regular expression matcher", environment);
        }
    }

    public static class replace_reBI extends StringBuiltins.StringBuiltIn {
        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(String str, Environment environment) throws TemplateModelException {
            return new ReplaceMethod(str);
        }

        class ReplaceMethod implements TemplateMethodModel {
            private String s;

            ReplaceMethod(String str) {
                this.s = str;
            }

            public Object exec(List list) throws TemplateModelException {
                String str;
                int size = list.size();
                replace_reBI.this.checkMethodArgCount(size, 2, 3);
                boolean z = false;
                String str2 = (String) list.get(0);
                String str3 = (String) list.get(1);
                long access$000 = size > 2 ? _RegexBuiltins.parseFlagString((String) list.get(2)) : 0;
                if ((_RegexBuiltins.RE_FLAG_REGEXP & access$000) == 0) {
                    _RegexBuiltins.checkNonRegexpFlags("replace", access$000);
                    String str4 = this.s;
                    boolean z2 = (_RegexBuiltins.RE_FLAG_CASE_INSENSITIVE & access$000) != 0;
                    if ((access$000 & _RegexBuiltins.RE_FLAG_FIRST_ONLY) != 0) {
                        z = true;
                    }
                    str = StringUtil.replace(str4, str2, str3, z2, z);
                } else {
                    Matcher matcher = _RegexBuiltins.getPattern(str2, (int) access$000).matcher(this.s);
                    str = (access$000 & _RegexBuiltins.RE_FLAG_FIRST_ONLY) != 0 ? matcher.replaceFirst(str3) : matcher.replaceAll(str3);
                }
                return new SimpleScalar(str);
            }
        }
    }

    public static class split_reBI extends StringBuiltins.StringBuiltIn {
        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(String str, Environment environment) throws TemplateModelException {
            return new SplitMethod(str);
        }

        class SplitMethod implements TemplateMethodModel {
            private String s;

            SplitMethod(String str) {
                this.s = str;
            }

            public Object exec(List list) throws TemplateModelException {
                String[] strArr;
                int size = list.size();
                boolean z = true;
                split_reBI.this.checkMethodArgCount(size, 1, 2);
                String str = (String) list.get(0);
                long access$000 = size > 1 ? _RegexBuiltins.parseFlagString((String) list.get(1)) : 0;
                if ((_RegexBuiltins.RE_FLAG_REGEXP & access$000) == 0) {
                    _RegexBuiltins.checkNonRegexpFlags("split", access$000);
                    String str2 = this.s;
                    if ((access$000 & _RegexBuiltins.RE_FLAG_CASE_INSENSITIVE) == 0) {
                        z = false;
                    }
                    strArr = StringUtil.split(str2, str, z);
                } else {
                    strArr = _RegexBuiltins.getPattern(str, (int) access$000).split(this.s);
                }
                return ObjectWrapper.DEFAULT_WRAPPER.wrap(strArr);
            }
        }
    }

    static class RegexMatchModel implements TemplateBooleanModel, TemplateCollectionModel, TemplateSequenceModel {
        private ArrayList data;
        TemplateSequenceModel groups;
        final String input;
        final Matcher matcher;
        final boolean matches;

        RegexMatchModel(Matcher matcher2, String str) {
            this.matcher = matcher2;
            this.input = str;
            this.matches = matcher2.matches();
        }

        public boolean getAsBoolean() {
            return this.matches;
        }

        public TemplateModel get(int i) throws TemplateModelException {
            if (this.data == null) {
                initSequence();
            }
            return (TemplateModel) this.data.get(i);
        }

        public int size() throws TemplateModelException {
            if (this.data == null) {
                initSequence();
            }
            return this.data.size();
        }

        private void initSequence() throws TemplateModelException {
            this.data = new ArrayList();
            TemplateModelIterator it = iterator();
            while (it.hasNext()) {
                this.data.add(it.next());
            }
        }

        public TemplateModel getGroups() {
            if (this.groups == null) {
                this.groups = new TemplateSequenceModel() {
                    public int size() throws TemplateModelException {
                        try {
                            return RegexMatchModel.this.matcher.groupCount() + 1;
                        } catch (Exception e) {
                            throw new _TemplateModelException((Throwable) e);
                        }
                    }

                    public TemplateModel get(int i) throws TemplateModelException {
                        try {
                            return new SimpleScalar(RegexMatchModel.this.matcher.group(i));
                        } catch (Exception e) {
                            throw new _TemplateModelException((Throwable) e);
                        }
                    }
                };
            }
            return this.groups;
        }

        public TemplateModelIterator iterator() {
            this.matcher.reset();
            return new TemplateModelIterator() {
                boolean hasFindInfo;

                {
                    this.hasFindInfo = RegexMatchModel.this.matcher.find();
                }

                public boolean hasNext() {
                    return this.hasFindInfo;
                }

                public TemplateModel next() throws TemplateModelException {
                    if (hasNext()) {
                        Match match = new Match();
                        this.hasFindInfo = RegexMatchModel.this.matcher.find();
                        return match;
                    }
                    throw new _TemplateModelException("No more matches");
                }
            };
        }

        class Match implements TemplateScalarModel {
            String match;
            SimpleSequence subs = new SimpleSequence();

            Match() {
                this.match = RegexMatchModel.this.input.substring(RegexMatchModel.this.matcher.start(), RegexMatchModel.this.matcher.end());
                for (int i = 0; i < RegexMatchModel.this.matcher.groupCount() + 1; i++) {
                    this.subs.add((Object) RegexMatchModel.this.matcher.group(i));
                }
            }

            public String getAsString() {
                return this.match;
            }
        }
    }

    /* access modifiers changed from: private */
    public static void checkNonRegexpFlags(String str, long j) {
        if (flagWarningsEnabled) {
            if ((RE_FLAG_MULTILINE & j) != 0) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("?");
                stringBuffer.append(str);
                stringBuffer.append(" doesn't support the \"m\" flag ");
                stringBuffer.append("without the \"r\" flag.");
                logFlagWarning(stringBuffer.toString());
            }
            if ((RE_FLAG_DOTALL & j) != 0) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("?");
                stringBuffer2.append(str);
                stringBuffer2.append(" doesn't support the \"s\" flag ");
                stringBuffer2.append("without the \"r\" flag.");
                logFlagWarning(stringBuffer2.toString());
            }
            if ((j & RE_FLAG_COMMENTS) != 0) {
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("?");
                stringBuffer3.append(str);
                stringBuffer3.append(" doesn't support the \"c\" flag ");
                stringBuffer3.append("without the \"r\" flag.");
                logFlagWarning(stringBuffer3.toString());
            }
        }
    }
}
