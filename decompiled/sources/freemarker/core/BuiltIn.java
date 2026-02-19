package freemarker.core;

import com.google.android.gms.plus.PlusShare;
import freemarker.core.DateBuiltins;
import freemarker.core.ExistenceBuiltins;
import freemarker.core.Expression;
import freemarker.core.HashBuiltins;
import freemarker.core.MiscellaneousBuiltins;
import freemarker.core.NodeBuiltins;
import freemarker.core.NumericalBuiltins;
import freemarker.core.SequenceBuiltins;
import freemarker.core.StringBuiltins;
import freemarker.log.Logger;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNumberModel;
import freemarker.template.TemplateScalarModel;
import java.util.HashMap;
import java.util.List;
import org.jivesoftware.smackx.packet.CapsExtension;

abstract class BuiltIn extends Expression implements Cloneable {
    static final HashMap builtins;
    private static final Logger logger = Logger.getLogger("freemarker.runtime");
    protected String key;
    protected Expression target;

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 2;
    }

    /* access modifiers changed from: package-private */
    public boolean isLiteral() {
        return false;
    }

    BuiltIn() {
    }

    static {
        HashMap hashMap = new HashMap();
        builtins = hashMap;
        hashMap.put("abs", new NumericalBuiltins.absBI());
        hashMap.put("ancestors", new NodeBuiltins.ancestorsBI());
        hashMap.put("byte", new NumericalBuiltins.byteBI());
        hashMap.put(CapsExtension.NODE_NAME, new MiscellaneousBuiltins.cBI());
        hashMap.put("cap_first", new StringBuiltins.cap_firstBI());
        hashMap.put("capitalize", new StringBuiltins.capitalizeBI());
        hashMap.put("ceiling", new NumericalBuiltins.ceilingBI());
        hashMap.put("children", new NodeBuiltins.childrenBI());
        hashMap.put("chop_linebreak", new StringBuiltins.chop_linebreakBI());
        hashMap.put("contains", new StringBuiltins.containsBI());
        hashMap.put("date", new MiscellaneousBuiltins.dateBI(2));
        hashMap.put("datetime", new MiscellaneousBuiltins.dateBI(3));
        hashMap.put("default", new ExistenceBuiltins.defaultBI());
        hashMap.put("double", new NumericalBuiltins.doubleBI());
        hashMap.put("ends_with", new StringBuiltins.ends_withBI());
        hashMap.put("eval", new StringBuiltins.evalBI());
        hashMap.put("exists", new ExistenceBuiltins.existsBI());
        hashMap.put("first", new SequenceBuiltins.firstBI());
        hashMap.put("float", new NumericalBuiltins.floatBI());
        hashMap.put("floor", new NumericalBuiltins.floorBI());
        hashMap.put("chunk", new SequenceBuiltins.chunkBI());
        hashMap.put("has_content", new ExistenceBuiltins.has_contentBI());
        hashMap.put("html", new StringBuiltins.htmlBI());
        hashMap.put("if_exists", new ExistenceBuiltins.if_existsBI());
        hashMap.put("index_of", new StringBuiltins.index_ofBI(false));
        hashMap.put("int", new NumericalBuiltins.intBI());
        hashMap.put("interpret", new Interpret());
        hashMap.put("is_boolean", new MiscellaneousBuiltins.is_booleanBI());
        hashMap.put("is_collection", new MiscellaneousBuiltins.is_collectionBI());
        hashMap.put("is_date", new MiscellaneousBuiltins.is_dateBI());
        hashMap.put("is_directive", new MiscellaneousBuiltins.is_directiveBI());
        hashMap.put("is_enumerable", new MiscellaneousBuiltins.is_enumerableBI());
        hashMap.put("is_hash_ex", new MiscellaneousBuiltins.is_hash_exBI());
        hashMap.put("is_hash", new MiscellaneousBuiltins.is_hashBI());
        hashMap.put("is_infinite", new NumericalBuiltins.is_infiniteBI());
        hashMap.put("is_indexable", new MiscellaneousBuiltins.is_indexableBI());
        hashMap.put("is_macro", new MiscellaneousBuiltins.is_macroBI());
        hashMap.put("is_method", new MiscellaneousBuiltins.is_methodBI());
        hashMap.put("is_nan", new NumericalBuiltins.is_nanBI());
        hashMap.put("is_node", new MiscellaneousBuiltins.is_nodeBI());
        hashMap.put("is_number", new MiscellaneousBuiltins.is_numberBI());
        hashMap.put("is_sequence", new MiscellaneousBuiltins.is_sequenceBI());
        hashMap.put("is_string", new MiscellaneousBuiltins.is_stringBI());
        hashMap.put("is_transform", new MiscellaneousBuiltins.is_transformBI());
        hashMap.put("iso_utc", new DateBuiltins.iso_tz_BI(true, 6, true));
        hashMap.put("iso_utc_nz", new DateBuiltins.iso_tz_BI(false, 6, true));
        hashMap.put("iso_utc_ms", new DateBuiltins.iso_tz_BI(true, 7, true));
        hashMap.put("iso_utc_ms_nz", new DateBuiltins.iso_tz_BI(false, 7, true));
        hashMap.put("iso_utc_m", new DateBuiltins.iso_tz_BI(true, 5, true));
        hashMap.put("iso_utc_m_nz", new DateBuiltins.iso_tz_BI(false, 5, true));
        hashMap.put("iso_utc_h", new DateBuiltins.iso_tz_BI(true, 4, true));
        hashMap.put("iso_utc_h_nz", new DateBuiltins.iso_tz_BI(false, 4, true));
        hashMap.put("iso_local", new DateBuiltins.iso_tz_BI(true, 6, false));
        hashMap.put("iso_local_nz", new DateBuiltins.iso_tz_BI(false, 6, false));
        hashMap.put("iso_local_ms", new DateBuiltins.iso_tz_BI(true, 7, false));
        hashMap.put("iso_local_ms_nz", new DateBuiltins.iso_tz_BI(false, 7, false));
        hashMap.put("iso_local_m", new DateBuiltins.iso_tz_BI(true, 5, false));
        hashMap.put("iso_local_m_nz", new DateBuiltins.iso_tz_BI(false, 5, false));
        hashMap.put("iso_local_h", new DateBuiltins.iso_tz_BI(true, 4, false));
        hashMap.put("iso_local_h_nz", new DateBuiltins.iso_tz_BI(false, 4, false));
        hashMap.put("iso", new DateBuiltins.iso_BI(true, 6));
        hashMap.put("iso_nz", new DateBuiltins.iso_BI(false, 6));
        hashMap.put("iso_ms", new DateBuiltins.iso_BI(true, 7));
        hashMap.put("iso_ms_nz", new DateBuiltins.iso_BI(false, 7));
        hashMap.put("iso_m", new DateBuiltins.iso_BI(true, 5));
        hashMap.put("iso_m_nz", new DateBuiltins.iso_BI(false, 5));
        hashMap.put("iso_h", new DateBuiltins.iso_BI(true, 4));
        hashMap.put("iso_h_nz", new DateBuiltins.iso_BI(false, 4));
        hashMap.put("j_string", new StringBuiltins.j_stringBI());
        hashMap.put("join", new SequenceBuiltins.joinBI());
        hashMap.put("js_string", new StringBuiltins.js_stringBI());
        hashMap.put("json_string", new StringBuiltins.json_stringBI());
        hashMap.put("keys", new HashBuiltins.keysBI());
        hashMap.put("last_index_of", new StringBuiltins.index_ofBI(true));
        hashMap.put("last", new SequenceBuiltins.lastBI());
        hashMap.put("left_pad", new StringBuiltins.padBI(true));
        hashMap.put("length", new StringBuiltins.lengthBI());
        hashMap.put("long", new NumericalBuiltins.longBI());
        hashMap.put("lower_case", new StringBuiltins.lower_caseBI());
        hashMap.put("namespace", new MiscellaneousBuiltins.namespaceBI());
        hashMap.put("new", new NewBI());
        hashMap.put("node_name", new NodeBuiltins.node_nameBI());
        hashMap.put("node_namespace", new NodeBuiltins.node_namespaceBI());
        hashMap.put("node_type", new NodeBuiltins.node_typeBI());
        hashMap.put("number", new StringBuiltins.numberBI());
        hashMap.put("number_to_date", new NumericalBuiltins.number_to_dateBI(2));
        hashMap.put("number_to_time", new NumericalBuiltins.number_to_dateBI(1));
        hashMap.put("number_to_datetime", new NumericalBuiltins.number_to_dateBI(3));
        hashMap.put("parent", new NodeBuiltins.parentBI());
        hashMap.put("replace", new StringBuiltins.replaceBI());
        hashMap.put("reverse", new SequenceBuiltins.reverseBI());
        hashMap.put("right_pad", new StringBuiltins.padBI(false));
        hashMap.put("root", new NodeBuiltins.rootBI());
        hashMap.put("round", new NumericalBuiltins.roundBI());
        hashMap.put("rtf", new StringBuiltins.rtfBI());
        hashMap.put("seq_contains", new SequenceBuiltins.seq_containsBI());
        hashMap.put("seq_index_of", new SequenceBuiltins.seq_index_ofBI(1));
        hashMap.put("seq_last_index_of", new SequenceBuiltins.seq_index_ofBI(-1));
        hashMap.put("short", new NumericalBuiltins.shortBI());
        hashMap.put("size", new MiscellaneousBuiltins.sizeBI());
        hashMap.put("sort_by", new SequenceBuiltins.sort_byBI());
        hashMap.put("sort", new SequenceBuiltins.sortBI());
        hashMap.put("split", new StringBuiltins.splitBI());
        hashMap.put("starts_with", new StringBuiltins.starts_withBI());
        hashMap.put("string", new MiscellaneousBuiltins.stringBI());
        hashMap.put("substring", new StringBuiltins.substringBI());
        hashMap.put("time", new MiscellaneousBuiltins.dateBI(1));
        hashMap.put("trim", new StringBuiltins.trimBI());
        hashMap.put("uncap_first", new StringBuiltins.uncap_firstBI());
        hashMap.put("upper_case", new StringBuiltins.upper_caseBI());
        hashMap.put(PlusShare.KEY_CALL_TO_ACTION_URL, new StringBuiltins.urlBI());
        hashMap.put("values", new HashBuiltins.valuesBI());
        hashMap.put("web_safe", hashMap.get("html"));
        hashMap.put("word_list", new StringBuiltins.word_listBI());
        hashMap.put("xhtml", new StringBuiltins.xhtmlBI());
        hashMap.put("xml", new StringBuiltins.xmlBI());
        try {
            Class.forName("java.util.regex.Pattern");
            hashMap.put("matches", instantiateBI("freemarker.core._RegexBuiltins$matchesBI"));
            hashMap.put("groups", instantiateBI("freemarker.core._RegexBuiltins$groupsBI"));
            hashMap.put("replace", instantiateBI("freemarker.core._RegexBuiltins$replace_reBI"));
            hashMap.put("split", instantiateBI("freemarker.core._RegexBuiltins$split_reBI"));
        } catch (Exception e) {
            logger.debug("Regular expression built-ins won't be avilable", e);
        }
    }

    private static Object instantiateBI(String str) throws Exception {
        return Class.forName(str).newInstance();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: freemarker.core.BuiltIn} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static freemarker.core.BuiltIn newBuiltIn(int r4, freemarker.core.Expression r5, java.lang.String r6) throws freemarker.core.ParseException {
        /*
            java.util.HashMap r0 = builtins
            java.lang.Object r1 = r0.get(r6)
            freemarker.core.BuiltIn r1 = (freemarker.core.BuiltIn) r1
            if (r1 != 0) goto L_0x0092
            java.lang.StringBuffer r4 = new java.lang.StringBuffer
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.String r2 = "Unknown built-in: "
            r1.append(r2)
            java.lang.String r6 = freemarker.template.utility.StringUtil.jQuote((java.lang.String) r6)
            r1.append(r6)
            java.lang.String r6 = ". "
            r1.append(r6)
            java.lang.String r6 = "Help (latest version): http://freemarker.org/docs/ref_builtins.html; "
            r1.append(r6)
            java.lang.String r6 = "you're using FreeMarker "
            r1.append(r6)
            freemarker.template.Version r6 = freemarker.template.Configuration.getVersion()
            r1.append(r6)
            java.lang.String r6 = ".\n"
            r1.append(r6)
            java.lang.String r6 = "The alphabetical list of built-ins:"
            r1.append(r6)
            java.lang.String r6 = r1.toString()
            r4.<init>(r6)
            java.util.ArrayList r6 = new java.util.ArrayList
            java.util.Set r1 = r0.keySet()
            int r1 = r1.size()
            r6.<init>(r1)
            java.util.Set r0 = r0.keySet()
            r6.addAll(r0)
            java.util.Collections.sort(r6)
            java.util.Iterator r6 = r6.iterator()
            r0 = 0
            r1 = 0
        L_0x0061:
            boolean r2 = r6.hasNext()
            if (r2 == 0) goto L_0x0088
            java.lang.Object r2 = r6.next()
            java.lang.String r2 = (java.lang.String) r2
            char r3 = r2.charAt(r0)
            if (r3 == r1) goto L_0x0079
            r1 = 10
            r4.append(r1)
            r1 = r3
        L_0x0079:
            r4.append(r2)
            boolean r2 = r6.hasNext()
            if (r2 == 0) goto L_0x0061
            java.lang.String r2 = ", "
            r4.append(r2)
            goto L_0x0061
        L_0x0088:
            freemarker.core.ParseException r6 = new freemarker.core.ParseException
            java.lang.String r4 = r4.toString()
            r6.<init>(r4, r5)
            throw r6
        L_0x0092:
            boolean r0 = r1 instanceof freemarker.core.ICIChainMember
            if (r0 == 0) goto L_0x00a7
            r0 = r1
            freemarker.core.ICIChainMember r0 = (freemarker.core.ICIChainMember) r0
            int r2 = r0.getMinimumICIVersion()
            if (r4 >= r2) goto L_0x00a7
            java.lang.Object r0 = r0.getPreviousICIChainMember()
            r1 = r0
            freemarker.core.BuiltIn r1 = (freemarker.core.BuiltIn) r1
            goto L_0x0092
        L_0x00a7:
            java.lang.Object r4 = r1.clone()     // Catch:{ CloneNotSupportedException -> 0x00b2 }
            freemarker.core.BuiltIn r4 = (freemarker.core.BuiltIn) r4     // Catch:{ CloneNotSupportedException -> 0x00b2 }
            r4.target = r5
            r4.key = r6
            return r4
        L_0x00b2:
            java.lang.InternalError r4 = new java.lang.InternalError
            r4.<init>()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.core.BuiltIn.newBuiltIn(int, freemarker.core.Expression, java.lang.String):freemarker.core.BuiltIn");
    }

    public String getCanonicalForm() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.target.getCanonicalForm());
        stringBuffer.append(getNodeTypeSymbol());
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("?");
        stringBuffer.append(this.key);
        return stringBuffer.toString();
    }

    /* access modifiers changed from: protected */
    public final void checkMethodArgCount(List list, int i) throws TemplateModelException {
        checkMethodArgCount(list.size(), i);
    }

    /* access modifiers changed from: protected */
    public final void checkMethodArgCount(int i, int i2) throws TemplateModelException {
        if (i != i2) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("?");
            stringBuffer.append(this.key);
            throw MessageUtil.newArgCntError(stringBuffer.toString(), i, i2);
        }
    }

    /* access modifiers changed from: protected */
    public final void checkMethodArgCount(List list, int i, int i2) throws TemplateModelException {
        checkMethodArgCount(list.size(), i, i2);
    }

    /* access modifiers changed from: protected */
    public final void checkMethodArgCount(int i, int i2, int i3) throws TemplateModelException {
        if (i < i2 || i > i3) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("?");
            stringBuffer.append(this.key);
            throw MessageUtil.newArgCntError(stringBuffer.toString(), i, i2, i3);
        }
    }

    /* access modifiers changed from: protected */
    public final String getOptStringMethodArg(List list, int i) throws TemplateModelException {
        if (list.size() > i) {
            return getStringMethodArg(list, i);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public final String getStringMethodArg(List list, int i) throws TemplateModelException {
        TemplateModel templateModel = (TemplateModel) list.get(i);
        if (templateModel instanceof TemplateScalarModel) {
            return EvalUtil.modelToString((TemplateScalarModel) templateModel, (Expression) null, (Environment) null);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("?");
        stringBuffer.append(this.key);
        throw MessageUtil.newMethodArgMustBeStringException(stringBuffer.toString(), i, templateModel);
    }

    /* access modifiers changed from: protected */
    public final Number getNumberMethodArg(List list, int i) throws TemplateModelException {
        TemplateModel templateModel = (TemplateModel) list.get(i);
        if (templateModel instanceof TemplateNumberModel) {
            return EvalUtil.modelToNumber((TemplateNumberModel) templateModel, (Expression) null);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("?");
        stringBuffer.append(this.key);
        throw MessageUtil.newMethodArgMustBeNumberException(stringBuffer.toString(), i, templateModel);
    }

    /* access modifiers changed from: protected */
    public final Expression deepCloneWithIdentifierReplaced_inner(String str, Expression expression, Expression.ReplacemenetState replacemenetState) {
        try {
            BuiltIn builtIn = (BuiltIn) clone();
            builtIn.target = this.target.deepCloneWithIdentifierReplaced(str, expression, replacemenetState);
            return builtIn;
        } catch (CloneNotSupportedException e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Internal error: ");
            stringBuffer.append(e);
            throw new RuntimeException(stringBuffer.toString());
        }
    }

    /* access modifiers changed from: package-private */
    public Object getParameterValue(int i) {
        if (i == 0) {
            return this.target;
        }
        if (i == 1) {
            return this.key;
        }
        throw new IndexOutOfBoundsException();
    }

    /* access modifiers changed from: package-private */
    public ParameterRole getParameterRole(int i) {
        if (i == 0) {
            return ParameterRole.LEFT_HAND_OPERAND;
        }
        if (i == 1) {
            return ParameterRole.RIGHT_HAND_OPERAND;
        }
        throw new IndexOutOfBoundsException();
    }
}
