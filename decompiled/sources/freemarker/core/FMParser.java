package freemarker.core;

import com.google.android.gms.drive.DriveFile;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateHashModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateModelIterator;
import freemarker.template.TemplateScalarModel;
import freemarker.template.utility.DeepUnwrap;
import freemarker.template.utility.StringUtil;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

public class FMParser implements FMParserConstants {
    private static int[] jj_la1_0;
    private static int[] jj_la1_1;
    private static int[] jj_la1_2;
    private static int[] jj_la1_3;
    private static int[] jj_la1_4;
    private int contentNesting;
    private LinkedList escapes;
    private boolean inFunction;
    private boolean inMacro;
    private int incompatibleImprovements;
    private final JJCalls[] jj_2_rtns;
    private int jj_endpos;
    private Vector jj_expentries;
    private int[] jj_expentry;
    private int jj_gc;
    private int jj_gen;
    SimpleCharStream jj_input_stream;
    private int jj_kind;
    private int jj_la;
    private final int[] jj_la1;
    private Token jj_lastpos;
    private int[] jj_lasttokens;
    private final LookaheadSuccess jj_ls;
    public Token jj_nt;
    private int jj_ntk;
    private boolean jj_rescan;
    private Token jj_scanpos;
    private boolean jj_semLA;
    public boolean lookingAhead;
    private int loopNesting;
    private boolean stripText;
    private boolean stripWhitespace;
    private int switchNesting;
    private Template template;
    public Token token;
    public FMParserTokenManager token_source;

    public final void disable_tracing() {
    }

    public final void enable_tracing() {
    }

    public static FMParser createExpressionParser(String str) {
        FMParserTokenManager fMParserTokenManager = new FMParserTokenManager(new SimpleCharStream((Reader) new StringReader(str), 1, 1, str.length()));
        fMParserTokenManager.SwitchTo(2);
        FMParser fMParser = new FMParser(fMParserTokenManager);
        fMParserTokenManager.setParser(fMParser);
        return fMParser;
    }

    public FMParser(Template template2, Reader reader, boolean z, boolean z2) {
        this(reader);
        setTemplate(template2);
        this.token_source.setParser(this);
        this.token_source.strictEscapeSyntax = z;
        this.stripWhitespace = z2;
    }

    public FMParser(Template template2, Reader reader, boolean z, boolean z2, int i) {
        this(template2, reader, z, z2, i, Configuration.PARSED_DEFAULT_INCOMPATIBLE_ENHANCEMENTS);
    }

    public FMParser(Template template2, Reader reader, boolean z, boolean z2, int i, int i2) {
        this(template2, reader, z, z2);
        if (i == 0) {
            this.token_source.autodetectTagSyntax = true;
        } else if (i == 1) {
            this.token_source.squBracTagSyntax = false;
        } else if (i == 2) {
            this.token_source.squBracTagSyntax = true;
        } else {
            throw new IllegalArgumentException("Illegal argument for tagSyntax");
        }
        this.token_source.incompatibleImprovements = i2;
        this.incompatibleImprovements = i2;
    }

    public FMParser(String str) {
        this((Template) null, new StringReader(str), true, true);
    }

    /* access modifiers changed from: package-private */
    public void setTemplate(Template template2) {
        this.template = template2;
    }

    /* access modifiers changed from: package-private */
    public Template getTemplate() {
        return this.template;
    }

    public int _getLastTagSyntax() {
        return this.token_source.squBracTagSyntax ? 2 : 1;
    }

    private void notStringLiteral(Expression expression, String str) throws ParseException {
        if (expression instanceof StringLiteral) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Found string literal: ");
            stringBuffer.append(expression);
            stringBuffer.append(". Expecting: ");
            stringBuffer.append(str);
            throw new ParseException(stringBuffer.toString(), expression);
        }
    }

    private void notNumberLiteral(Expression expression, String str) throws ParseException {
        if (expression instanceof NumberLiteral) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Found number literal: ");
            stringBuffer.append(expression.getCanonicalForm());
            stringBuffer.append(". Expecting ");
            stringBuffer.append(str);
            throw new ParseException(stringBuffer.toString(), expression);
        }
    }

    private void notBooleanLiteral(Expression expression, String str) throws ParseException {
        if (expression instanceof BooleanLiteral) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Found: ");
            stringBuffer.append(expression.getCanonicalForm());
            stringBuffer.append(". Expecting ");
            stringBuffer.append(str);
            throw new ParseException(stringBuffer.toString(), expression);
        }
    }

    private void notHashLiteral(Expression expression, String str) throws ParseException {
        if (expression instanceof HashLiteral) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Found hash literal: ");
            stringBuffer.append(expression.getCanonicalForm());
            stringBuffer.append(". Expecting ");
            stringBuffer.append(str);
            throw new ParseException(stringBuffer.toString(), expression);
        }
    }

    private void notListLiteral(Expression expression, String str) throws ParseException {
        if (expression instanceof ListLiteral) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Found list literal: ");
            stringBuffer.append(expression.getCanonicalForm());
            stringBuffer.append(". Expecting ");
            stringBuffer.append(str);
            throw new ParseException(stringBuffer.toString(), expression);
        }
    }

    private void numberLiteralOnly(Expression expression) throws ParseException {
        notStringLiteral(expression, "number");
        notListLiteral(expression, "number");
        notHashLiteral(expression, "number");
        notBooleanLiteral(expression, "number");
    }

    private void stringLiteralOnly(Expression expression) throws ParseException {
        notNumberLiteral(expression, "string");
        notListLiteral(expression, "string");
        notHashLiteral(expression, "string");
        notBooleanLiteral(expression, "string");
    }

    private void booleanLiteralOnly(Expression expression) throws ParseException {
        notStringLiteral(expression, "boolean (true/false)");
        notListLiteral(expression, "boolean (true/false)");
        notHashLiteral(expression, "boolean (true/false)");
        notNumberLiteral(expression, "boolean (true/false)");
    }

    private Expression escapedExpression(Expression expression) {
        return !this.escapes.isEmpty() ? ((EscapeBlock) this.escapes.getFirst()).doEscape(expression) : expression;
    }

    private boolean getBoolean(Expression expression) throws ParseException {
        try {
            TemplateModel eval = expression.eval((Environment) null);
            if (eval instanceof TemplateBooleanModel) {
                try {
                    return ((TemplateBooleanModel) eval).getAsBoolean();
                } catch (TemplateModelException unused) {
                }
            }
            if (eval instanceof TemplateScalarModel) {
                try {
                    return StringUtil.getYesNo(((TemplateScalarModel) eval).getAsString());
                } catch (Exception e) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(e.getMessage());
                    stringBuffer.append("\nExpecting boolean (true/false), found: ");
                    stringBuffer.append(expression.getCanonicalForm());
                    throw new ParseException(stringBuffer.toString(), expression);
                }
            } else {
                throw new ParseException("Expecting boolean (true/false) parameter", expression);
            }
        } catch (Exception e2) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(e2.getMessage());
            stringBuffer2.append("\nCould not evaluate expression: ");
            stringBuffer2.append(expression.getCanonicalForm());
            throw new ParseException(stringBuffer2.toString(), (TemplateObject) expression, (Throwable) e2);
        }
    }

    public final Expression Expression() throws ParseException {
        return OrExpression();
    }

    public final Expression PrimaryExpression() throws ParseException {
        Expression expression;
        int i = this.jj_ntk;
        if (i == -1) {
            i = jj_ntk();
        }
        if (i == 111) {
            expression = ListLiteral();
        } else if (i == 113) {
            expression = Parenthesis();
        } else if (i == 115) {
            expression = HashLiteral();
        } else if (i != 120) {
            switch (i) {
                case 81:
                case 82:
                    expression = StringLiteral(true);
                    break;
                case 83:
                case 84:
                    expression = BooleanLiteral();
                    break;
                case 85:
                case 86:
                    expression = NumberLiteral();
                    break;
                case 87:
                    expression = BuiltinVariable();
                    break;
                default:
                    this.jj_la1[0] = this.jj_gen;
                    jj_consume_token(-1);
                    throw new ParseException();
            }
        } else {
            expression = Identifier();
        }
        while (jj_2_1(Integer.MAX_VALUE)) {
            expression = AddSubExpression(expression);
        }
        return expression;
    }

    public final Expression Parenthesis() throws ParseException {
        Token jj_consume_token = jj_consume_token(113);
        Expression Expression = Expression();
        Token jj_consume_token2 = jj_consume_token(114);
        ParentheticalExpression parentheticalExpression = new ParentheticalExpression(Expression);
        parentheticalExpression.setLocation(this.template, jj_consume_token, jj_consume_token2);
        return parentheticalExpression;
    }

    public final Expression UnaryExpression() throws ParseException {
        int i = this.jj_ntk;
        if (i == -1) {
            i = jj_ntk();
        }
        if (i == 98 || i == 99) {
            return UnaryPlusMinusExpression();
        }
        if (i == 107) {
            return NotExpression();
        }
        if (!(i == 111 || i == 113 || i == 115 || i == 120)) {
            switch (i) {
                case 81:
                case 82:
                case 83:
                case 84:
                case 85:
                case 86:
                case 87:
                    break;
                default:
                    this.jj_la1[1] = this.jj_gen;
                    jj_consume_token(-1);
                    throw new ParseException();
            }
        }
        return PrimaryExpression();
    }

    public final Expression NotExpression() throws ParseException {
        int i;
        ArrayList arrayList = new ArrayList();
        do {
            arrayList.add(jj_consume_token(107));
            i = this.jj_ntk;
            if (i == -1) {
                i = jj_ntk();
                continue;
            }
        } while (i == 107);
        this.jj_la1[2] = this.jj_gen;
        Expression PrimaryExpression = PrimaryExpression();
        int i2 = 0;
        NotExpression notExpression = null;
        while (i2 < arrayList.size()) {
            notExpression = new NotExpression(PrimaryExpression);
            notExpression.setLocation(this.template, (Token) arrayList.get((arrayList.size() - i2) - 1), (TemplateObject) PrimaryExpression);
            i2++;
            PrimaryExpression = notExpression;
        }
        return notExpression;
    }

    public final Expression UnaryPlusMinusExpression() throws ParseException {
        boolean z;
        Token token2;
        int i = this.jj_ntk;
        if (i == -1) {
            i = jj_ntk();
        }
        if (i == 98) {
            token2 = jj_consume_token(98);
            z = false;
        } else if (i == 99) {
            token2 = jj_consume_token(99);
            z = true;
        } else {
            this.jj_la1[3] = this.jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
        }
        Expression PrimaryExpression = PrimaryExpression();
        UnaryPlusMinusExpression unaryPlusMinusExpression = new UnaryPlusMinusExpression(PrimaryExpression, z);
        unaryPlusMinusExpression.setLocation(this.template, token2, (TemplateObject) PrimaryExpression);
        return unaryPlusMinusExpression;
    }

    public final Expression AdditiveExpression() throws ParseException {
        boolean z;
        Expression expression;
        Expression MultiplicativeExpression = MultiplicativeExpression();
        while (jj_2_2(Integer.MAX_VALUE)) {
            int i = this.jj_ntk;
            if (i == -1) {
                i = jj_ntk();
            }
            if (i == 98) {
                jj_consume_token(98);
                z = true;
            } else if (i == 99) {
                jj_consume_token(99);
                z = false;
            } else {
                this.jj_la1[4] = this.jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
            }
            Expression MultiplicativeExpression2 = MultiplicativeExpression();
            if (z) {
                expression = new AddConcatExpression(MultiplicativeExpression, MultiplicativeExpression2);
            } else {
                numberLiteralOnly(MultiplicativeExpression);
                numberLiteralOnly(MultiplicativeExpression2);
                expression = new ArithmeticExpression(MultiplicativeExpression, MultiplicativeExpression2, 0);
            }
            expression.setLocation(this.template, (TemplateObject) MultiplicativeExpression, (TemplateObject) MultiplicativeExpression2);
            MultiplicativeExpression = expression;
        }
        return MultiplicativeExpression;
    }

    public final Expression MultiplicativeExpression() throws ParseException {
        int i;
        Expression UnaryExpression = UnaryExpression();
        while (jj_2_3(Integer.MAX_VALUE)) {
            int i2 = this.jj_ntk;
            if (i2 == -1) {
                i2 = jj_ntk();
            }
            if (i2 == 100) {
                jj_consume_token(100);
                i = 1;
            } else if (i2 == 103) {
                jj_consume_token(103);
                i = 2;
            } else if (i2 == 104) {
                jj_consume_token(104);
                i = 3;
            } else {
                this.jj_la1[5] = this.jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
            }
            Expression UnaryExpression2 = UnaryExpression();
            numberLiteralOnly(UnaryExpression);
            numberLiteralOnly(UnaryExpression2);
            ArithmeticExpression arithmeticExpression = new ArithmeticExpression(UnaryExpression, UnaryExpression2, i);
            arithmeticExpression.setLocation(this.template, (TemplateObject) UnaryExpression, (TemplateObject) UnaryExpression2);
            UnaryExpression = arithmeticExpression;
        }
        return UnaryExpression;
    }

    public final Expression EqualityExpression() throws ParseException {
        Token token2;
        Expression RelationalExpression = RelationalExpression();
        if (!jj_2_4(Integer.MAX_VALUE)) {
            return RelationalExpression;
        }
        int i = this.jj_ntk;
        if (i == -1) {
            i = jj_ntk();
        }
        switch (i) {
            case 91:
                token2 = jj_consume_token(91);
                break;
            case 92:
                token2 = jj_consume_token(92);
                break;
            case 93:
                token2 = jj_consume_token(93);
                break;
            default:
                this.jj_la1[6] = this.jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
        }
        Expression RelationalExpression2 = RelationalExpression();
        notHashLiteral(RelationalExpression, "scalar");
        notHashLiteral(RelationalExpression2, "scalar");
        notListLiteral(RelationalExpression, "scalar");
        notListLiteral(RelationalExpression2, "scalar");
        ComparisonExpression comparisonExpression = new ComparisonExpression(RelationalExpression, RelationalExpression2, token2.image);
        comparisonExpression.setLocation(this.template, (TemplateObject) RelationalExpression, (TemplateObject) RelationalExpression2);
        return comparisonExpression;
    }

    public final Expression RelationalExpression() throws ParseException {
        Token token2;
        Expression RangeExpression = RangeExpression();
        if (!jj_2_5(Integer.MAX_VALUE)) {
            return RangeExpression;
        }
        int i = this.jj_ntk;
        if (i == -1) {
            i = jj_ntk();
        }
        if (i == 126) {
            token2 = jj_consume_token(126);
        } else if (i != 127) {
            switch (i) {
                case 94:
                    token2 = jj_consume_token(94);
                    break;
                case 95:
                    token2 = jj_consume_token(95);
                    break;
                case 96:
                    token2 = jj_consume_token(96);
                    break;
                case 97:
                    token2 = jj_consume_token(97);
                    break;
                default:
                    this.jj_la1[7] = this.jj_gen;
                    jj_consume_token(-1);
                    throw new ParseException();
            }
        } else {
            token2 = jj_consume_token(127);
        }
        Expression RangeExpression2 = RangeExpression();
        notHashLiteral(RangeExpression, "scalar");
        notHashLiteral(RangeExpression2, "scalar");
        notListLiteral(RangeExpression, "scalar");
        notListLiteral(RangeExpression2, "scalar");
        notStringLiteral(RangeExpression, "number");
        notStringLiteral(RangeExpression2, "number");
        ComparisonExpression comparisonExpression = new ComparisonExpression(RangeExpression, RangeExpression2, token2.image);
        comparisonExpression.setLocation(this.template, (TemplateObject) RangeExpression, (TemplateObject) RangeExpression2);
        return comparisonExpression;
    }

    public final Expression RangeExpression() throws ParseException {
        Expression AdditiveExpression = AdditiveExpression();
        if (!jj_2_7(Integer.MAX_VALUE)) {
            return AdditiveExpression;
        }
        jj_consume_token(88);
        Expression AdditiveExpression2 = jj_2_6(Integer.MAX_VALUE) ? AdditiveExpression() : null;
        numberLiteralOnly(AdditiveExpression);
        if (AdditiveExpression2 != null) {
            numberLiteralOnly(AdditiveExpression2);
        }
        Range range = new Range(AdditiveExpression, AdditiveExpression2);
        if (AdditiveExpression2 != null) {
            range.setLocation(this.template, (TemplateObject) AdditiveExpression, (TemplateObject) AdditiveExpression2);
        } else {
            range.setLocation(this.template, (TemplateObject) AdditiveExpression, (TemplateObject) AdditiveExpression);
        }
        return range;
    }

    public final Expression AndExpression() throws ParseException {
        Expression EqualityExpression = EqualityExpression();
        while (jj_2_8(Integer.MAX_VALUE)) {
            jj_consume_token(105);
            Expression EqualityExpression2 = EqualityExpression();
            booleanLiteralOnly(EqualityExpression);
            booleanLiteralOnly(EqualityExpression2);
            AndExpression andExpression = new AndExpression(EqualityExpression, EqualityExpression2);
            andExpression.setLocation(this.template, (TemplateObject) EqualityExpression, (TemplateObject) EqualityExpression2);
            EqualityExpression = andExpression;
        }
        return EqualityExpression;
    }

    public final Expression OrExpression() throws ParseException {
        Expression AndExpression = AndExpression();
        while (jj_2_9(Integer.MAX_VALUE)) {
            jj_consume_token(106);
            Expression AndExpression2 = AndExpression();
            booleanLiteralOnly(AndExpression);
            booleanLiteralOnly(AndExpression2);
            OrExpression orExpression = new OrExpression(AndExpression, AndExpression2);
            orExpression.setLocation(this.template, (TemplateObject) AndExpression, (TemplateObject) AndExpression2);
            AndExpression = orExpression;
        }
        return AndExpression;
    }

    public final ListLiteral ListLiteral() throws ParseException {
        new ArrayList();
        Token jj_consume_token = jj_consume_token(111);
        ArrayList PositionalArgs = PositionalArgs();
        Token jj_consume_token2 = jj_consume_token(112);
        ListLiteral listLiteral = new ListLiteral(PositionalArgs);
        listLiteral.setLocation(this.template, jj_consume_token, jj_consume_token2);
        return listLiteral;
    }

    public final Expression NumberLiteral() throws ParseException {
        Token token2;
        int i = this.jj_ntk;
        if (i == -1) {
            i = jj_ntk();
        }
        if (i == 85) {
            token2 = jj_consume_token(85);
        } else if (i == 86) {
            token2 = jj_consume_token(86);
        } else {
            this.jj_la1[8] = this.jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
        }
        NumberLiteral numberLiteral = new NumberLiteral(this.template.getArithmeticEngine().toNumber(token2.image));
        numberLiteral.setLocation(this.template, token2, token2);
        return numberLiteral;
    }

    public final Identifier Identifier() throws ParseException {
        Token jj_consume_token = jj_consume_token(120);
        Identifier identifier = new Identifier(jj_consume_token.image);
        identifier.setLocation(this.template, jj_consume_token, jj_consume_token);
        return identifier;
    }

    public final Expression IdentifierOrStringLiteral() throws ParseException {
        int i = this.jj_ntk;
        if (i == -1) {
            i = jj_ntk();
        }
        if (i == 81 || i == 82) {
            return StringLiteral(false);
        }
        if (i == 120) {
            return Identifier();
        }
        this.jj_la1[9] = this.jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
    }

    public final BuiltinVariable BuiltinVariable() throws ParseException {
        Token jj_consume_token = jj_consume_token(87);
        Token jj_consume_token2 = jj_consume_token(120);
        try {
            BuiltinVariable builtinVariable = new BuiltinVariable(jj_consume_token2.image);
            builtinVariable.setLocation(this.template, jj_consume_token, jj_consume_token2);
            return builtinVariable;
        } catch (ParseException e) {
            e.lineNumber = jj_consume_token.beginLine;
            e.columnNumber = jj_consume_token.beginColumn;
            throw e;
        }
    }

    public final Expression AddSubExpression(Expression expression) throws ParseException {
        int i = this.jj_ntk;
        if (i == -1) {
            i = jj_ntk();
        }
        if (i == 87) {
            return DotVariable(expression);
        }
        if (i != 107) {
            if (i == 111) {
                return DynamicKey(expression);
            }
            if (i == 113) {
                return MethodArgs(expression);
            }
            if (i != 129) {
                if (i == 89) {
                    return BuiltIn(expression);
                }
                if (i == 90) {
                    return Exists(expression);
                }
                this.jj_la1[10] = this.jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
            }
        }
        return DefaultTo(expression);
    }

    public final Expression DefaultTo(Expression expression) throws ParseException {
        Token token2;
        int i = this.jj_ntk;
        if (i == -1) {
            i = jj_ntk();
        }
        Expression expression2 = null;
        if (i == 107) {
            token2 = jj_consume_token(107);
            if (jj_2_10(Integer.MAX_VALUE)) {
                expression2 = Expression();
            }
        } else if (i == 129) {
            token2 = jj_consume_token(129);
        } else {
            this.jj_la1[11] = this.jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
        }
        DefaultToExpression defaultToExpression = new DefaultToExpression(expression, expression2);
        if (expression2 == null) {
            defaultToExpression.setLocation(this.template, (TemplateObject) expression, token2);
        } else {
            defaultToExpression.setLocation(this.template, (TemplateObject) expression, (TemplateObject) expression2);
        }
        return defaultToExpression;
    }

    public final Expression Exists(Expression expression) throws ParseException {
        Token jj_consume_token = jj_consume_token(90);
        ExistsExpression existsExpression = new ExistsExpression(expression);
        existsExpression.setLocation(this.template, (TemplateObject) expression, jj_consume_token);
        return existsExpression;
    }

    public final Expression BuiltIn(Expression expression) throws ParseException {
        jj_consume_token(89);
        Token jj_consume_token = jj_consume_token(120);
        try {
            BuiltIn newBuiltIn = BuiltIn.newBuiltIn(this.incompatibleImprovements, expression, jj_consume_token.image);
            newBuiltIn.setLocation(this.template, (TemplateObject) expression, jj_consume_token);
            return newBuiltIn;
        } catch (ParseException e) {
            e.lineNumber = jj_consume_token.beginLine;
            e.columnNumber = jj_consume_token.beginColumn;
            throw e;
        }
    }

    public final Expression DotVariable(Expression expression) throws ParseException {
        Token token2;
        jj_consume_token(87);
        int i = this.jj_ntk;
        if (i == -1) {
            i = jj_ntk();
        }
        if (!(i == 83 || i == 84)) {
            if (i == 100) {
                token2 = jj_consume_token(100);
            } else if (i != 101) {
                switch (i) {
                    case 94:
                    case 95:
                    case 96:
                    case 97:
                        break;
                    default:
                        switch (i) {
                            case 117:
                            case 118:
                            case 119:
                                break;
                            case 120:
                                token2 = jj_consume_token(120);
                                break;
                            default:
                                this.jj_la1[13] = this.jj_gen;
                                jj_consume_token(-1);
                                throw new ParseException();
                        }
                }
            } else {
                token2 = jj_consume_token(101);
            }
            notListLiteral(expression, "hash");
            notStringLiteral(expression, "hash");
            notBooleanLiteral(expression, "hash");
            Dot dot = new Dot(expression, token2.image);
            dot.setLocation(this.template, (TemplateObject) expression, token2);
            return dot;
        }
        int i2 = this.jj_ntk;
        if (i2 == -1) {
            i2 = jj_ntk();
        }
        if (i2 == 83) {
            token2 = jj_consume_token(83);
        } else if (i2 != 84) {
            switch (i2) {
                case 94:
                    token2 = jj_consume_token(94);
                    break;
                case 95:
                    token2 = jj_consume_token(95);
                    break;
                case 96:
                    token2 = jj_consume_token(96);
                    break;
                case 97:
                    token2 = jj_consume_token(97);
                    break;
                default:
                    switch (i2) {
                        case 117:
                            token2 = jj_consume_token(117);
                            break;
                        case 118:
                            token2 = jj_consume_token(118);
                            break;
                        case 119:
                            token2 = jj_consume_token(119);
                            break;
                        default:
                            this.jj_la1[12] = this.jj_gen;
                            jj_consume_token(-1);
                            throw new ParseException();
                    }
            }
        } else {
            token2 = jj_consume_token(84);
        }
        if (!Character.isLetter(token2.image.charAt(0))) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(token2.image);
            stringBuffer.append(" is not a valid identifier.");
            throw new ParseException(stringBuffer.toString(), this.template, token2);
        }
        notListLiteral(expression, "hash");
        notStringLiteral(expression, "hash");
        notBooleanLiteral(expression, "hash");
        Dot dot2 = new Dot(expression, token2.image);
        dot2.setLocation(this.template, (TemplateObject) expression, token2);
        return dot2;
    }

    public final Expression DynamicKey(Expression expression) throws ParseException {
        jj_consume_token(111);
        Expression Expression = Expression();
        Token jj_consume_token = jj_consume_token(112);
        notBooleanLiteral(expression, "list or hash");
        notNumberLiteral(expression, "list or hash");
        DynamicKeyName dynamicKeyName = new DynamicKeyName(expression, Expression);
        dynamicKeyName.setLocation(this.template, (TemplateObject) expression, jj_consume_token);
        return dynamicKeyName;
    }

    public final MethodCall MethodArgs(Expression expression) throws ParseException {
        new ArrayList();
        jj_consume_token(113);
        ArrayList PositionalArgs = PositionalArgs();
        Token jj_consume_token = jj_consume_token(114);
        PositionalArgs.trimToSize();
        MethodCall methodCall = new MethodCall(expression, PositionalArgs);
        methodCall.setLocation(this.template, (TemplateObject) expression, jj_consume_token);
        return methodCall;
    }

    public final StringLiteral StringLiteral(boolean z) throws ParseException {
        boolean z2;
        Token token2;
        String str;
        int i = this.jj_ntk;
        if (i == -1) {
            i = jj_ntk();
        }
        if (i == 81) {
            token2 = jj_consume_token(81);
            z2 = false;
        } else if (i == 82) {
            token2 = jj_consume_token(82);
            z2 = true;
        } else {
            this.jj_la1[14] = this.jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
        }
        String str2 = token2.image;
        String substring = str2.substring(1, str2.length() - 1);
        if (z2) {
            str = substring.substring(1);
        } else {
            try {
                str = StringUtil.FTLStringLiteralDec(substring);
            } catch (ParseException e) {
                e.lineNumber = token2.beginLine;
                e.columnNumber = token2.beginColumn;
                throw e;
            }
        }
        StringLiteral stringLiteral = new StringLiteral(str);
        stringLiteral.setLocation(this.template, token2, token2);
        if (z && !z2 && (token2.image.indexOf("${") >= 0 || token2.image.indexOf("#{") >= 0)) {
            stringLiteral.checkInterpolation();
        }
        return stringLiteral;
    }

    public final Expression BooleanLiteral() throws ParseException {
        BooleanLiteral booleanLiteral;
        Token token2;
        int i = this.jj_ntk;
        if (i == -1) {
            i = jj_ntk();
        }
        if (i == 83) {
            token2 = jj_consume_token(83);
            booleanLiteral = new BooleanLiteral(false);
        } else if (i == 84) {
            token2 = jj_consume_token(84);
            booleanLiteral = new BooleanLiteral(true);
        } else {
            this.jj_la1[15] = this.jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
        }
        booleanLiteral.setLocation(this.template, token2, token2);
        return booleanLiteral;
    }

    public final HashLiteral HashLiteral() throws ParseException {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Token jj_consume_token = jj_consume_token(115);
        int i = this.jj_ntk;
        if (i == -1) {
            i = jj_ntk();
        }
        if (!(i == 98 || i == 99 || i == 107 || i == 111 || i == 113 || i == 115 || i == 120)) {
            switch (i) {
                case 81:
                case 82:
                case 83:
                case 84:
                case 85:
                case 86:
                case 87:
                    break;
                default:
                    this.jj_la1[19] = this.jj_gen;
                    break;
            }
        }
        Expression Expression = Expression();
        int i2 = this.jj_ntk;
        if (i2 == -1) {
            i2 = jj_ntk();
        }
        if (i2 == 108) {
            jj_consume_token(108);
        } else if (i2 == 110) {
            jj_consume_token(110);
        } else {
            this.jj_la1[16] = this.jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
        }
        Expression Expression2 = Expression();
        stringLiteralOnly(Expression);
        arrayList.add(Expression);
        arrayList2.add(Expression2);
        while (true) {
            int i3 = this.jj_ntk;
            if (i3 == -1) {
                i3 = jj_ntk();
            }
            if (i3 != 108) {
                this.jj_la1[17] = this.jj_gen;
            } else {
                jj_consume_token(108);
                Expression Expression3 = Expression();
                int i4 = this.jj_ntk;
                if (i4 == -1) {
                    i4 = jj_ntk();
                }
                if (i4 == 108) {
                    jj_consume_token(108);
                } else if (i4 == 110) {
                    jj_consume_token(110);
                } else {
                    this.jj_la1[18] = this.jj_gen;
                    jj_consume_token(-1);
                    throw new ParseException();
                }
                Expression Expression4 = Expression();
                stringLiteralOnly(Expression3);
                arrayList.add(Expression3);
                arrayList2.add(Expression4);
            }
        }
        Token jj_consume_token2 = jj_consume_token(116);
        HashLiteral hashLiteral = new HashLiteral(arrayList, arrayList2);
        hashLiteral.setLocation(this.template, jj_consume_token, jj_consume_token2);
        return hashLiteral;
    }

    public final DollarVariable StringOutput() throws ParseException {
        Token jj_consume_token = jj_consume_token(71);
        Expression Expression = Expression();
        notHashLiteral(Expression, "string or something automatically convertible to string (number, date or boolean)");
        notListLiteral(Expression, "string or something automatically convertible to string (number, date or boolean)");
        Token jj_consume_token2 = jj_consume_token(116);
        DollarVariable dollarVariable = new DollarVariable(Expression, escapedExpression(Expression));
        dollarVariable.setLocation(this.template, jj_consume_token, jj_consume_token2);
        return dollarVariable;
    }

    public final NumericalOutput NumericalOutput() throws ParseException {
        Token token2;
        NumericalOutput numericalOutput;
        Token jj_consume_token = jj_consume_token(72);
        Expression Expression = Expression();
        numberLiteralOnly(Expression);
        int i = this.jj_ntk;
        if (i == -1) {
            i = jj_ntk();
        }
        if (i != 109) {
            this.jj_la1[20] = this.jj_gen;
            token2 = null;
        } else {
            jj_consume_token(109);
            token2 = jj_consume_token(120);
        }
        Token jj_consume_token2 = jj_consume_token(116);
        if (token2 != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(token2.image, "mM", true);
            int i2 = -1;
            int i3 = -1;
            while (true) {
                char c = '-';
                while (stringTokenizer.hasMoreTokens()) {
                    String nextToken = stringTokenizer.nextToken();
                    if (c != '-') {
                        if (c != 'M') {
                            if (c != 'm') {
                                throw new ParseException("Invalid formatting string", this.template, token2);
                            } else if (i3 == -1) {
                                try {
                                    i3 = Integer.parseInt(nextToken);
                                } catch (ParseException unused) {
                                    StringBuffer stringBuffer = new StringBuffer();
                                    stringBuffer.append("Invalid format specifier ");
                                    stringBuffer.append(token2.image);
                                    throw new ParseException(stringBuffer.toString(), this.template, token2);
                                } catch (NumberFormatException unused2) {
                                    StringBuffer stringBuffer2 = new StringBuffer();
                                    stringBuffer2.append("Invalid number in the format specifier ");
                                    stringBuffer2.append(token2.image);
                                    throw new ParseException(stringBuffer2.toString(), this.template, token2);
                                }
                            } else {
                                throw new ParseException("Invalid formatting string", this.template, token2);
                            }
                        } else if (i2 == -1) {
                            i2 = Integer.parseInt(nextToken);
                        } else {
                            throw new ParseException("Invalid formatting string", this.template, token2);
                        }
                    } else if (nextToken.equals("m")) {
                        c = 'm';
                    } else if (nextToken.equals("M")) {
                        c = 'M';
                    } else {
                        throw new ParseException();
                    }
                }
                if (i2 == -1) {
                    if (i3 != -1) {
                        i2 = i3;
                    } else {
                        throw new ParseException("Invalid format specification, at least one of m and M must be specified!", this.template, token2);
                    }
                } else if (i3 == -1) {
                    i3 = 0;
                }
                if (i3 > i2) {
                    throw new ParseException("Invalid format specification, min cannot be greater than max!", this.template, token2);
                } else if (i3 > 50 || i2 > 50) {
                    throw new ParseException("Cannot specify more than 50 fraction digits", this.template, token2);
                } else {
                    numericalOutput = new NumericalOutput(Expression, i3, i2);
                }
            }
        } else {
            numericalOutput = new NumericalOutput(Expression);
        }
        numericalOutput.setLocation(this.template, jj_consume_token, jj_consume_token2);
        return numericalOutput;
    }

    public final TemplateElement If() throws ParseException {
        Token jj_consume_token = jj_consume_token(8);
        Expression Expression = Expression();
        jj_consume_token(124);
        TemplateElement OptionalBlock = OptionalBlock();
        ConditionalBlock conditionalBlock = new ConditionalBlock(Expression, OptionalBlock, 0);
        conditionalBlock.setLocation(this.template, jj_consume_token, (TemplateObject) OptionalBlock);
        IfBlock ifBlock = new IfBlock(conditionalBlock);
        while (true) {
            int i = this.jj_ntk;
            if (i == -1) {
                i = jj_ntk();
            }
            if (i != 9) {
                break;
            }
            Token jj_consume_token2 = jj_consume_token(9);
            Expression Expression2 = Expression();
            LooseDirectiveEnd();
            TemplateElement OptionalBlock2 = OptionalBlock();
            ConditionalBlock conditionalBlock2 = new ConditionalBlock(Expression2, OptionalBlock2, 2);
            conditionalBlock2.setLocation(this.template, jj_consume_token2, (TemplateObject) OptionalBlock2);
            ifBlock.addBlock(conditionalBlock2);
        }
        this.jj_la1[21] = this.jj_gen;
        int i2 = this.jj_ntk;
        if (i2 == -1) {
            i2 = jj_ntk();
        }
        if (i2 != 44) {
            this.jj_la1[22] = this.jj_gen;
        } else {
            Token jj_consume_token3 = jj_consume_token(44);
            TemplateElement OptionalBlock3 = OptionalBlock();
            ConditionalBlock conditionalBlock3 = new ConditionalBlock((Expression) null, OptionalBlock3, 1);
            conditionalBlock3.setLocation(this.template, jj_consume_token3, (TemplateObject) OptionalBlock3);
            ifBlock.addBlock(conditionalBlock3);
        }
        ifBlock.setLocation(this.template, jj_consume_token, jj_consume_token(31));
        return ifBlock;
    }

    public final AttemptBlock Attempt() throws ParseException {
        Token token2;
        Token jj_consume_token = jj_consume_token(6);
        TemplateElement OptionalBlock = OptionalBlock();
        RecoveryBlock Recover = Recover();
        int i = this.jj_ntk;
        if (i == -1) {
            i = jj_ntk();
        }
        if (i == 33) {
            token2 = jj_consume_token(33);
        } else if (i == 34) {
            token2 = jj_consume_token(34);
        } else {
            this.jj_la1[23] = this.jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
        }
        AttemptBlock attemptBlock = new AttemptBlock(OptionalBlock, Recover);
        attemptBlock.setLocation(this.template, jj_consume_token, token2);
        return attemptBlock;
    }

    public final RecoveryBlock Recover() throws ParseException {
        Token jj_consume_token = jj_consume_token(7);
        TemplateElement OptionalBlock = OptionalBlock();
        RecoveryBlock recoveryBlock = new RecoveryBlock(OptionalBlock);
        recoveryBlock.setLocation(this.template, jj_consume_token, (TemplateObject) OptionalBlock);
        return recoveryBlock;
    }

    public final IteratorBlock List() throws ParseException {
        Token jj_consume_token = jj_consume_token(10);
        this.loopNesting++;
        Expression Expression = Expression();
        jj_consume_token(118);
        Token jj_consume_token2 = jj_consume_token(120);
        jj_consume_token(124);
        TemplateElement OptionalBlock = OptionalBlock();
        Token jj_consume_token3 = jj_consume_token(32);
        this.loopNesting--;
        IteratorBlock iteratorBlock = new IteratorBlock(Expression, jj_consume_token2.image, OptionalBlock, false);
        iteratorBlock.setLocation(this.template, jj_consume_token, jj_consume_token3);
        return iteratorBlock;
    }

    public final IteratorBlock ForEach() throws ParseException {
        Token jj_consume_token = jj_consume_token(11);
        this.loopNesting++;
        Token jj_consume_token2 = jj_consume_token(120);
        jj_consume_token(117);
        Expression Expression = Expression();
        jj_consume_token(124);
        TemplateElement OptionalBlock = OptionalBlock();
        Token jj_consume_token3 = jj_consume_token(35);
        this.loopNesting--;
        IteratorBlock iteratorBlock = new IteratorBlock(Expression, jj_consume_token2.image, OptionalBlock, true);
        iteratorBlock.setLocation(this.template, jj_consume_token, jj_consume_token3);
        return iteratorBlock;
    }

    public final VisitNode Visit() throws ParseException {
        Expression expression;
        Token jj_consume_token = jj_consume_token(22);
        Expression Expression = Expression();
        int i = this.jj_ntk;
        if (i == -1) {
            i = jj_ntk();
        }
        if (i != 119) {
            this.jj_la1[24] = this.jj_gen;
            expression = null;
        } else {
            jj_consume_token(119);
            expression = Expression();
        }
        Token LooseDirectiveEnd = LooseDirectiveEnd();
        VisitNode visitNode = new VisitNode(Expression, expression);
        visitNode.setLocation(this.template, jj_consume_token, LooseDirectiveEnd);
        return visitNode;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: freemarker.core.Token} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: freemarker.core.Token} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: freemarker.core.Token} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: freemarker.core.Expression} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: freemarker.core.Expression} */
    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r3v6 */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final freemarker.core.RecurseNode Recurse() throws freemarker.core.ParseException {
        /*
            r7 = this;
            int r0 = r7.jj_ntk
            r1 = -1
            if (r0 != r1) goto L_0x0009
            int r0 = r7.jj_ntk()
        L_0x0009:
            r2 = 56
            r3 = 0
            if (r0 == r2) goto L_0x0080
            r2 = 57
            if (r0 != r2) goto L_0x006f
            freemarker.core.Token r0 = r7.jj_consume_token(r2)
            int r2 = r7.jj_ntk
            if (r2 != r1) goto L_0x001e
            int r2 = r7.jj_ntk()
        L_0x001e:
            r4 = 98
            if (r2 == r4) goto L_0x0047
            r4 = 99
            if (r2 == r4) goto L_0x0047
            r4 = 107(0x6b, float:1.5E-43)
            if (r2 == r4) goto L_0x0047
            r4 = 111(0x6f, float:1.56E-43)
            if (r2 == r4) goto L_0x0047
            r4 = 113(0x71, float:1.58E-43)
            if (r2 == r4) goto L_0x0047
            r4 = 115(0x73, float:1.61E-43)
            if (r2 == r4) goto L_0x0047
            r4 = 120(0x78, float:1.68E-43)
            if (r2 == r4) goto L_0x0047
            switch(r2) {
                case 81: goto L_0x0047;
                case 82: goto L_0x0047;
                case 83: goto L_0x0047;
                case 84: goto L_0x0047;
                case 85: goto L_0x0047;
                case 86: goto L_0x0047;
                case 87: goto L_0x0047;
                default: goto L_0x003d;
            }
        L_0x003d:
            int[] r2 = r7.jj_la1
            r4 = 25
            int r5 = r7.jj_gen
            r2[r4] = r5
            r2 = r3
            goto L_0x004b
        L_0x0047:
            freemarker.core.Expression r2 = r7.Expression()
        L_0x004b:
            int r4 = r7.jj_ntk
            if (r4 != r1) goto L_0x0053
            int r4 = r7.jj_ntk()
        L_0x0053:
            r1 = 119(0x77, float:1.67E-43)
            if (r4 == r1) goto L_0x0060
            int[] r1 = r7.jj_la1
            r4 = 26
            int r5 = r7.jj_gen
            r1[r4] = r5
            goto L_0x0067
        L_0x0060:
            r7.jj_consume_token(r1)
            freemarker.core.Expression r3 = r7.Expression()
        L_0x0067:
            freemarker.core.Token r1 = r7.LooseDirectiveEnd()
            r6 = r3
            r3 = r1
            r1 = r6
            goto L_0x0086
        L_0x006f:
            int[] r0 = r7.jj_la1
            r2 = 27
            int r3 = r7.jj_gen
            r0[r2] = r3
            r7.jj_consume_token(r1)
            freemarker.core.ParseException r0 = new freemarker.core.ParseException
            r0.<init>()
            throw r0
        L_0x0080:
            freemarker.core.Token r0 = r7.jj_consume_token(r2)
            r1 = r3
            r2 = r1
        L_0x0086:
            if (r3 != 0) goto L_0x0089
            r3 = r0
        L_0x0089:
            freemarker.core.RecurseNode r4 = new freemarker.core.RecurseNode
            r4.<init>(r2, r1)
            freemarker.template.Template r1 = r7.template
            r4.setLocation((freemarker.template.Template) r1, (freemarker.core.Token) r0, (freemarker.core.Token) r3)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.core.FMParser.Recurse():freemarker.core.RecurseNode");
    }

    public final FallbackInstruction FallBack() throws ParseException {
        Token jj_consume_token = jj_consume_token(58);
        if (this.inMacro) {
            FallbackInstruction fallbackInstruction = new FallbackInstruction();
            fallbackInstruction.setLocation(this.template, jj_consume_token, jj_consume_token);
            return fallbackInstruction;
        }
        throw new ParseException("Cannot fall back outside a macro.", this.template, jj_consume_token);
    }

    public final BreakInstruction Break() throws ParseException {
        Token jj_consume_token = jj_consume_token(45);
        if (this.loopNesting >= 1 || this.switchNesting >= 1) {
            BreakInstruction breakInstruction = new BreakInstruction();
            breakInstruction.setLocation(this.template, jj_consume_token, jj_consume_token);
            return breakInstruction;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(jj_consume_token.image);
        stringBuffer.append(" occurred outside a loop or a switch block.");
        throw new ParseException(stringBuffer.toString(), this.template, jj_consume_token);
    }

    public final ReturnInstruction Return() throws ParseException {
        Token token2;
        Expression expression;
        Token token3;
        int i = this.jj_ntk;
        if (i == -1) {
            i = jj_ntk();
        }
        if (i == 24) {
            token3 = jj_consume_token(24);
            expression = Expression();
            token2 = LooseDirectiveEnd();
        } else if (i == 46) {
            token3 = jj_consume_token(46);
            expression = null;
            token2 = token3;
        } else {
            this.jj_la1[28] = this.jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
        }
        if (this.inMacro) {
            if (expression != null) {
                throw new ParseException("A macro cannot return a value", this.template, token3);
            }
        } else if (this.inFunction) {
            if (expression == null) {
                throw new ParseException("A function must return a value", this.template, token3);
            }
        } else if (expression == null) {
            throw new ParseException("A return instruction can only occur inside a macro or function", this.template, token3);
        }
        ReturnInstruction returnInstruction = new ReturnInstruction(expression);
        returnInstruction.setLocation(this.template, token3, token2);
        return returnInstruction;
    }

    public final StopInstruction Stop() throws ParseException {
        Expression expression;
        Token token2;
        int i = this.jj_ntk;
        if (i == -1) {
            i = jj_ntk();
        }
        if (i == 23) {
            token2 = jj_consume_token(23);
            expression = Expression();
            LooseDirectiveEnd();
        } else if (i == 47) {
            token2 = jj_consume_token(47);
            expression = null;
        } else {
            this.jj_la1[29] = this.jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
        }
        StopInstruction stopInstruction = new StopInstruction(expression);
        stopInstruction.setLocation(this.template, token2, token2);
        return stopInstruction;
    }

    public final TemplateElement Nested() throws ParseException {
        BodyInstruction bodyInstruction;
        Token token2;
        int i = this.jj_ntk;
        if (i == -1) {
            i = jj_ntk();
        }
        if (i == 54) {
            token2 = jj_consume_token(54);
            bodyInstruction = new BodyInstruction((List) null);
            bodyInstruction.setLocation(this.template, token2, token2);
        } else if (i == 55) {
            token2 = jj_consume_token(55);
            ArrayList PositionalArgs = PositionalArgs();
            Token LooseDirectiveEnd = LooseDirectiveEnd();
            bodyInstruction = new BodyInstruction(PositionalArgs);
            bodyInstruction.setLocation(this.template, token2, LooseDirectiveEnd);
        } else {
            this.jj_la1[30] = this.jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
        }
        if (this.inMacro) {
            return bodyInstruction;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Cannot use a ");
        stringBuffer.append(token2.image);
        stringBuffer.append(" instruction outside a macro.");
        throw new ParseException(stringBuffer.toString(), this.template, token2);
    }

    public final TemplateElement Flush() throws ParseException {
        Token jj_consume_token = jj_consume_token(48);
        FlushInstruction flushInstruction = new FlushInstruction();
        flushInstruction.setLocation(this.template, jj_consume_token, jj_consume_token);
        return flushInstruction;
    }

    public final TemplateElement Trim() throws ParseException {
        TrimInstruction trimInstruction;
        Token token2;
        int i = this.jj_ntk;
        if (i == -1) {
            i = jj_ntk();
        }
        switch (i) {
            case 49:
                token2 = jj_consume_token(49);
                trimInstruction = new TrimInstruction(true, true);
                break;
            case 50:
                token2 = jj_consume_token(50);
                trimInstruction = new TrimInstruction(true, false);
                break;
            case 51:
                token2 = jj_consume_token(51);
                trimInstruction = new TrimInstruction(false, true);
                break;
            case 52:
                token2 = jj_consume_token(52);
                trimInstruction = new TrimInstruction(false, false);
                break;
            default:
                this.jj_la1[31] = this.jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
        }
        trimInstruction.setLocation(this.template, token2, token2);
        return trimInstruction;
    }

    public final TemplateElement Assign() throws ParseException {
        int i;
        Token token2;
        Token token3;
        ArrayList arrayList = new ArrayList();
        int i2 = this.jj_ntk;
        if (i2 == -1) {
            i2 = jj_ntk();
        }
        switch (i2) {
            case 14:
                token2 = jj_consume_token(14);
                i = 1;
                break;
            case 15:
                token2 = jj_consume_token(15);
                i = 3;
                break;
            case 16:
                token2 = jj_consume_token(16);
                if (this.inMacro || this.inFunction) {
                    i = 2;
                    break;
                } else {
                    throw new ParseException("Local variable assigned outside a macro.", this.template, token2);
                }
                break;
            default:
                this.jj_la1[32] = this.jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
        }
        Expression IdentifierOrStringLiteral = IdentifierOrStringLiteral();
        String asString = IdentifierOrStringLiteral instanceof StringLiteral ? ((StringLiteral) IdentifierOrStringLiteral).getAsString() : IdentifierOrStringLiteral.toString();
        int i3 = this.jj_ntk;
        if (i3 == -1) {
            i3 = jj_ntk();
        }
        Expression expression = null;
        if (i3 == 91) {
            jj_consume_token(91);
            Expression Expression = Expression();
            Assignment assignment = new Assignment(asString, Expression, i);
            assignment.setLocation(this.template, (TemplateObject) IdentifierOrStringLiteral, (TemplateObject) Expression);
            arrayList.add(assignment);
            while (jj_2_11(Integer.MAX_VALUE)) {
                int i4 = this.jj_ntk;
                if (i4 == -1) {
                    i4 = jj_ntk();
                }
                if (i4 != 108) {
                    this.jj_la1[33] = this.jj_gen;
                } else {
                    jj_consume_token(108);
                }
                Expression IdentifierOrStringLiteral2 = IdentifierOrStringLiteral();
                String asString2 = IdentifierOrStringLiteral2 instanceof StringLiteral ? ((StringLiteral) IdentifierOrStringLiteral2).getAsString() : IdentifierOrStringLiteral2.toString();
                jj_consume_token(91);
                Expression Expression2 = Expression();
                Assignment assignment2 = new Assignment(asString2, Expression2, i);
                assignment2.setLocation(this.template, (TemplateObject) IdentifierOrStringLiteral2, (TemplateObject) Expression2);
                arrayList.add(assignment2);
            }
            int i5 = this.jj_ntk;
            if (i5 == -1) {
                i5 = jj_ntk();
            }
            if (i5 != 117) {
                this.jj_la1[34] = this.jj_gen;
            } else {
                Token jj_consume_token = jj_consume_token(117);
                expression = Expression();
                if (i != 1) {
                    throw new ParseException("Cannot assign to namespace here.", this.template, jj_consume_token);
                }
            }
            Token LooseDirectiveEnd = LooseDirectiveEnd();
            AssignmentInstruction assignmentInstruction = new AssignmentInstruction(i);
            for (int i6 = 0; i6 < arrayList.size(); i6++) {
                assignmentInstruction.addAssignment((Assignment) arrayList.get(i6));
            }
            assignmentInstruction.setNamespaceExp(expression);
            assignmentInstruction.setLocation(this.template, token2, LooseDirectiveEnd);
            return assignmentInstruction;
        } else if (i3 == 117 || i3 == 124) {
            int i7 = this.jj_ntk;
            if (i7 == -1) {
                i7 = jj_ntk();
            }
            if (i7 != 117) {
                this.jj_la1[35] = this.jj_gen;
            } else {
                Token jj_consume_token2 = jj_consume_token(117);
                expression = Expression();
                if (i != 1) {
                    throw new ParseException("Cannot assign to namespace here.", this.template, jj_consume_token2);
                }
            }
            jj_consume_token(124);
            TemplateElement OptionalBlock = OptionalBlock();
            int i8 = this.jj_ntk;
            if (i8 == -1) {
                i8 = jj_ntk();
            }
            switch (i8) {
                case 36:
                    token3 = jj_consume_token(36);
                    if (i != 2) {
                        throw new ParseException("Mismatched assignment tags.", this.template, token3);
                    }
                    break;
                case 37:
                    token3 = jj_consume_token(37);
                    if (i != 3) {
                        throw new ParseException("Mismatched assignment tags", this.template, token3);
                    }
                    break;
                case 38:
                    token3 = jj_consume_token(38);
                    if (i != 1) {
                        throw new ParseException("Mismatched assignment tags.", this.template, token3);
                    }
                    break;
                default:
                    this.jj_la1[36] = this.jj_gen;
                    jj_consume_token(-1);
                    throw new ParseException();
            }
            BlockAssignment blockAssignment = new BlockAssignment(OptionalBlock, asString, i, expression);
            blockAssignment.setLocation(this.template, token2, token3);
            return blockAssignment;
        } else {
            this.jj_la1[37] = this.jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
        }
    }

    public final Include Include() throws ParseException {
        Token jj_consume_token = jj_consume_token(17);
        Expression Expression = Expression();
        int i = this.jj_ntk;
        if (i == -1) {
            i = jj_ntk();
        }
        if (i != 109) {
            this.jj_la1[38] = this.jj_gen;
        } else {
            jj_consume_token(109);
        }
        Expression expression = null;
        Expression expression2 = null;
        while (true) {
            int i2 = this.jj_ntk;
            if (i2 == -1) {
                i2 = jj_ntk();
            }
            if (i2 != 120) {
                this.jj_la1[39] = this.jj_gen;
                Token LooseDirectiveEnd = LooseDirectiveEnd();
                Include include = new Include(this.template, Expression, expression, expression2);
                include.setLocation(this.template, jj_consume_token, LooseDirectiveEnd);
                return include;
            }
            Token jj_consume_token2 = jj_consume_token(120);
            jj_consume_token(91);
            Expression Expression2 = Expression();
            String str = jj_consume_token2.image;
            if (str.equalsIgnoreCase("parse")) {
                expression2 = Expression2;
            } else if (str.equalsIgnoreCase("encoding")) {
                expression = Expression2;
            } else {
                throw new ParseException("Expecting parse= or encoding= to be specified.", this.template, jj_consume_token2);
            }
        }
    }

    public final LibraryLoad Import() throws ParseException {
        Token jj_consume_token = jj_consume_token(18);
        Expression Expression = Expression();
        jj_consume_token(118);
        Token jj_consume_token2 = jj_consume_token(120);
        Token LooseDirectiveEnd = LooseDirectiveEnd();
        LibraryLoad libraryLoad = new LibraryLoad(this.template, Expression, jj_consume_token2.image);
        libraryLoad.setLocation(this.template, jj_consume_token, LooseDirectiveEnd);
        this.template.addImport(libraryLoad);
        return libraryLoad;
    }

    public final Macro Macro() throws ParseException {
        boolean z;
        Token token2;
        Token token3;
        Expression expression;
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        ArrayList arrayList2 = new ArrayList();
        int i = this.jj_ntk;
        int i2 = -1;
        if (i == -1) {
            i = jj_ntk();
        }
        int i3 = 40;
        boolean z2 = false;
        if (i == 19) {
            token2 = jj_consume_token(19);
            z = true;
        } else if (i == 20) {
            token2 = jj_consume_token(20);
            z = false;
        } else {
            this.jj_la1[40] = this.jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
        }
        if (this.inMacro || this.inFunction) {
            throw new ParseException("Macros cannot be nested.", this.template, token2);
        }
        if (z) {
            this.inFunction = true;
        } else {
            this.inMacro = true;
        }
        Expression IdentifierOrStringLiteral = IdentifierOrStringLiteral();
        String asString = IdentifierOrStringLiteral instanceof StringLiteral ? ((StringLiteral) IdentifierOrStringLiteral).getAsString() : IdentifierOrStringLiteral.toString();
        int i4 = this.jj_ntk;
        if (i4 == -1) {
            i4 = jj_ntk();
        }
        if (i4 != 113) {
            this.jj_la1[41] = this.jj_gen;
        } else {
            jj_consume_token(113);
        }
        String str = null;
        boolean z3 = false;
        boolean z4 = false;
        while (true) {
            int i5 = this.jj_ntk;
            if (i5 == i2) {
                i5 = jj_ntk();
            }
            if (i5 != 120) {
                this.jj_la1[42] = this.jj_gen;
                int i6 = this.jj_ntk;
                if (i6 == i2) {
                    i6 = jj_ntk();
                }
                if (i6 != 114) {
                    this.jj_la1[46] = this.jj_gen;
                } else {
                    jj_consume_token(114);
                }
                jj_consume_token(124);
                TemplateElement OptionalBlock = OptionalBlock();
                int i7 = this.jj_ntk;
                if (i7 == i2) {
                    i7 = jj_ntk();
                }
                if (i7 == 39) {
                    token3 = jj_consume_token(39);
                    if (!z) {
                        throw new ParseException("Expected macro end tag here.", this.template, token2);
                    }
                } else if (i7 == i3) {
                    token3 = jj_consume_token(i3);
                    if (z) {
                        throw new ParseException("Expected function end tag here.", this.template, token2);
                    }
                } else {
                    this.jj_la1[47] = this.jj_gen;
                    jj_consume_token(i2);
                    throw new ParseException();
                }
                this.inFunction = z2;
                this.inMacro = z2;
                Macro macro = new Macro(asString, arrayList, hashMap, OptionalBlock);
                macro.setCatchAll(str);
                macro.isFunction = z;
                macro.setLocation(this.template, token2, token3);
                this.template.addMacro(macro);
                return macro;
            }
            Token jj_consume_token = jj_consume_token(120);
            int i8 = this.jj_ntk;
            if (i8 == i2) {
                i8 = jj_ntk();
            }
            if (i8 != 102) {
                this.jj_la1[43] = this.jj_gen;
            } else {
                jj_consume_token(102);
                z3 = true;
            }
            int i9 = this.jj_ntk;
            if (i9 == i2) {
                i9 = jj_ntk();
            }
            if (i9 != 91) {
                this.jj_la1[44] = this.jj_gen;
                expression = null;
            } else {
                jj_consume_token(91);
                expression = Expression();
                arrayList2.add(jj_consume_token.image);
                z4 = true;
            }
            int i10 = this.jj_ntk;
            if (i10 == i2) {
                i10 = jj_ntk();
            }
            if (i10 != 108) {
                this.jj_la1[45] = this.jj_gen;
            } else {
                jj_consume_token(108);
            }
            if (str == null) {
                if (!z3) {
                    arrayList.add(jj_consume_token.image);
                    if (!z4 || expression != null) {
                        hashMap.put(jj_consume_token.image, expression);
                    } else {
                        throw new ParseException("In a macro declaration, parameters without a default value must all occur before the parameters with default values.", this.template, jj_consume_token);
                    }
                } else if (expression == null) {
                    str = jj_consume_token.image;
                } else {
                    throw new ParseException("\"Catch-all\" macro parameter may not have a default value.", this.template, jj_consume_token);
                }
                i2 = -1;
                i3 = 40;
                z2 = false;
            } else {
                throw new ParseException("There may only be one \"catch-all\" parameter in a macro declaration, and it must be the last parameter.", this.template, jj_consume_token);
            }
        }
    }

    public final CompressedBlock Compress() throws ParseException {
        Token jj_consume_token = jj_consume_token(27);
        TemplateElement OptionalBlock = OptionalBlock();
        Token jj_consume_token2 = jj_consume_token(41);
        CompressedBlock compressedBlock = new CompressedBlock(OptionalBlock);
        compressedBlock.setLocation(this.template, jj_consume_token, jj_consume_token2);
        return compressedBlock;
    }

    public final TemplateElement UnifiedMacroTransform() throws ParseException {
        String str;
        ArrayList arrayList;
        HashMap hashMap;
        ArrayList arrayList2;
        Token token2;
        Token jj_consume_token = jj_consume_token(63);
        Expression Expression = Expression();
        TemplateElement templateElement = null;
        if ((Expression instanceof Identifier) || ((Expression instanceof Dot) && ((Dot) Expression).onlyHasIdentifiers())) {
            str = Expression.getCanonicalForm();
        } else {
            str = null;
        }
        int i = this.jj_ntk;
        if (i == -1) {
            i = jj_ntk();
        }
        if (i != 128) {
            this.jj_la1[48] = this.jj_gen;
        } else {
            jj_consume_token(128);
        }
        if (jj_2_12(Integer.MAX_VALUE)) {
            hashMap = NamedArgs();
            arrayList = null;
        } else {
            arrayList = PositionalArgs();
            hashMap = null;
        }
        int i2 = this.jj_ntk;
        if (i2 == -1) {
            i2 = jj_ntk();
        }
        if (i2 != 109) {
            this.jj_la1[54] = this.jj_gen;
            arrayList2 = null;
        } else {
            jj_consume_token(109);
            arrayList2 = new ArrayList();
            int i3 = this.jj_ntk;
            if (i3 == -1) {
                i3 = jj_ntk();
            }
            if (i3 == 120 || i3 == 128) {
                int i4 = this.jj_ntk;
                if (i4 == -1) {
                    i4 = jj_ntk();
                }
                if (i4 != 128) {
                    this.jj_la1[49] = this.jj_gen;
                } else {
                    jj_consume_token(128);
                }
                arrayList2.add(jj_consume_token(120).image);
                while (true) {
                    int i5 = this.jj_ntk;
                    if (i5 == -1) {
                        i5 = jj_ntk();
                    }
                    if (i5 != 108 && i5 != 128) {
                        break;
                    }
                    int i6 = this.jj_ntk;
                    if (i6 == -1) {
                        i6 = jj_ntk();
                    }
                    if (i6 != 128) {
                        this.jj_la1[51] = this.jj_gen;
                    } else {
                        jj_consume_token(128);
                    }
                    jj_consume_token(108);
                    int i7 = this.jj_ntk;
                    if (i7 == -1) {
                        i7 = jj_ntk();
                    }
                    if (i7 != 128) {
                        this.jj_la1[52] = this.jj_gen;
                    } else {
                        jj_consume_token(128);
                    }
                    arrayList2.add(jj_consume_token(120).image);
                }
                this.jj_la1[50] = this.jj_gen;
            } else {
                this.jj_la1[53] = this.jj_gen;
            }
        }
        int i8 = this.jj_ntk;
        if (i8 == -1) {
            i8 = jj_ntk();
        }
        if (i8 == 124) {
            jj_consume_token(124);
            templateElement = OptionalBlock();
            Token jj_consume_token2 = jj_consume_token(64);
            String substring = jj_consume_token2.image.substring(3);
            String trim = substring.substring(0, substring.length() - 1).trim();
            if (trim.length() <= 0 || trim.equals(str)) {
                token2 = jj_consume_token2;
            } else if (str == null) {
                throw new ParseException("Expecting </@>", this.template, jj_consume_token2);
            } else {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Expecting </@> or </@");
                stringBuffer.append(str);
                stringBuffer.append(">");
                throw new ParseException(stringBuffer.toString(), this.template, jj_consume_token2);
            }
        } else if (i8 == 125) {
            token2 = jj_consume_token(125);
        } else {
            this.jj_la1[55] = this.jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
        }
        UnifiedCall unifiedCall = arrayList != null ? new UnifiedCall(Expression, (List) arrayList, templateElement, (List) arrayList2) : new UnifiedCall(Expression, (Map) hashMap, templateElement, (List) arrayList2);
        unifiedCall.setLocation(this.template, jj_consume_token, token2);
        return unifiedCall;
    }

    public final TemplateElement Call() throws ParseException {
        HashMap hashMap;
        ArrayList arrayList;
        UnifiedCall unifiedCall;
        Token jj_consume_token = jj_consume_token(25);
        String str = jj_consume_token(120).image;
        if (jj_2_14(Integer.MAX_VALUE)) {
            hashMap = NamedArgs();
            arrayList = null;
        } else {
            if (jj_2_13(Integer.MAX_VALUE)) {
                jj_consume_token(113);
            }
            arrayList = PositionalArgs();
            int i = this.jj_ntk;
            if (i == -1) {
                i = jj_ntk();
            }
            if (i != 114) {
                this.jj_la1[56] = this.jj_gen;
            } else {
                jj_consume_token(114);
            }
            hashMap = null;
        }
        Token LooseDirectiveEnd = LooseDirectiveEnd();
        if (arrayList != null) {
            unifiedCall = new UnifiedCall((Expression) new Identifier(str), (List) arrayList, (TemplateElement) null, (List) null);
        } else {
            unifiedCall = new UnifiedCall((Expression) new Identifier(str), (Map) hashMap, (TemplateElement) null, (List) null);
        }
        unifiedCall.legacySyntax = true;
        unifiedCall.setLocation(this.template, jj_consume_token, LooseDirectiveEnd);
        return unifiedCall;
    }

    public final HashMap NamedArgs() throws ParseException {
        int i;
        HashMap hashMap = new HashMap();
        do {
            Token jj_consume_token = jj_consume_token(120);
            jj_consume_token(91);
            this.token_source.SwitchTo(4);
            this.token_source.inInvocation = true;
            hashMap.put(jj_consume_token.image, Expression());
            i = this.jj_ntk;
            if (i == -1) {
                i = jj_ntk();
                continue;
            }
        } while (i == 120);
        this.jj_la1[57] = this.jj_gen;
        this.token_source.inInvocation = false;
        return hashMap;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0062, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.ArrayList PositionalArgs() throws freemarker.core.ParseException {
        /*
            r12 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            int r1 = r12.jj_ntk
            r2 = -1
            if (r1 != r2) goto L_0x000e
            int r1 = r12.jj_ntk()
        L_0x000e:
            r3 = 120(0x78, float:1.68E-43)
            r4 = 115(0x73, float:1.61E-43)
            r5 = 113(0x71, float:1.58E-43)
            r6 = 111(0x6f, float:1.56E-43)
            r7 = 107(0x6b, float:1.5E-43)
            r8 = 99
            r9 = 98
            if (r1 == r9) goto L_0x0036
            if (r1 == r8) goto L_0x0036
            if (r1 == r7) goto L_0x0036
            if (r1 == r6) goto L_0x0036
            if (r1 == r5) goto L_0x0036
            if (r1 == r4) goto L_0x0036
            if (r1 == r3) goto L_0x0036
            switch(r1) {
                case 81: goto L_0x0036;
                case 82: goto L_0x0036;
                case 83: goto L_0x0036;
                case 84: goto L_0x0036;
                case 85: goto L_0x0036;
                case 86: goto L_0x0036;
                case 87: goto L_0x0036;
                default: goto L_0x002d;
            }
        L_0x002d:
            int[] r1 = r12.jj_la1
            r2 = 60
            int r3 = r12.jj_gen
            r1[r2] = r3
            goto L_0x0062
        L_0x0036:
            freemarker.core.Expression r1 = r12.Expression()
            r0.add(r1)
        L_0x003d:
            int r1 = r12.jj_ntk
            if (r1 != r2) goto L_0x0045
            int r1 = r12.jj_ntk()
        L_0x0045:
            r10 = 108(0x6c, float:1.51E-43)
            if (r1 == r9) goto L_0x0063
            if (r1 == r8) goto L_0x0063
            if (r1 == r7) goto L_0x0063
            if (r1 == r10) goto L_0x0063
            if (r1 == r6) goto L_0x0063
            if (r1 == r5) goto L_0x0063
            if (r1 == r4) goto L_0x0063
            if (r1 == r3) goto L_0x0063
            switch(r1) {
                case 81: goto L_0x0063;
                case 82: goto L_0x0063;
                case 83: goto L_0x0063;
                case 84: goto L_0x0063;
                case 85: goto L_0x0063;
                case 86: goto L_0x0063;
                case 87: goto L_0x0063;
                default: goto L_0x005a;
            }
        L_0x005a:
            int[] r1 = r12.jj_la1
            r2 = 58
            int r3 = r12.jj_gen
            r1[r2] = r3
        L_0x0062:
            return r0
        L_0x0063:
            int r1 = r12.jj_ntk
            if (r1 != r2) goto L_0x006b
            int r1 = r12.jj_ntk()
        L_0x006b:
            if (r1 == r10) goto L_0x0076
            int[] r1 = r12.jj_la1
            r10 = 59
            int r11 = r12.jj_gen
            r1[r10] = r11
            goto L_0x0079
        L_0x0076:
            r12.jj_consume_token(r10)
        L_0x0079:
            freemarker.core.Expression r1 = r12.Expression()
            r0.add(r1)
            goto L_0x003d
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.core.FMParser.PositionalArgs():java.util.ArrayList");
    }

    public final Comment Comment() throws ParseException {
        Token token2;
        StringBuffer stringBuffer = new StringBuffer();
        int i = this.jj_ntk;
        if (i == -1) {
            i = jj_ntk();
        }
        if (i == 28) {
            token2 = jj_consume_token(28);
        } else if (i == 29) {
            token2 = jj_consume_token(29);
        } else {
            this.jj_la1[61] = this.jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
        }
        Token UnparsedContent = UnparsedContent(stringBuffer);
        Comment comment = new Comment(stringBuffer.toString());
        comment.setLocation(this.template, token2, UnparsedContent);
        return comment;
    }

    public final TextBlock NoParse() throws ParseException {
        StringBuffer stringBuffer = new StringBuffer();
        Token jj_consume_token = jj_consume_token(30);
        Token UnparsedContent = UnparsedContent(stringBuffer);
        TextBlock textBlock = new TextBlock(stringBuffer.toString(), true);
        textBlock.setLocation(this.template, jj_consume_token, UnparsedContent);
        return textBlock;
    }

    public final TransformBlock Transform() throws ParseException {
        Token token2;
        Token jj_consume_token = jj_consume_token(21);
        Expression Expression = Expression();
        int i = this.jj_ntk;
        if (i == -1) {
            i = jj_ntk();
        }
        if (i != 109) {
            this.jj_la1[62] = this.jj_gen;
        } else {
            jj_consume_token(109);
        }
        TemplateElement templateElement = null;
        HashMap hashMap = null;
        while (true) {
            int i2 = this.jj_ntk;
            if (i2 == -1) {
                i2 = jj_ntk();
            }
            if (i2 != 120) {
                break;
            }
            Token jj_consume_token2 = jj_consume_token(120);
            jj_consume_token(91);
            Expression Expression2 = Expression();
            if (hashMap == null) {
                hashMap = new HashMap();
            }
            hashMap.put(jj_consume_token2.image, Expression2);
        }
        this.jj_la1[63] = this.jj_gen;
        int i3 = this.jj_ntk;
        if (i3 == -1) {
            i3 = jj_ntk();
        }
        if (i3 == 124) {
            jj_consume_token(124);
            templateElement = OptionalBlock();
            token2 = jj_consume_token(42);
        } else if (i3 == 125) {
            token2 = jj_consume_token(125);
        } else {
            this.jj_la1[64] = this.jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
        }
        TransformBlock transformBlock = new TransformBlock(Expression, hashMap, templateElement);
        transformBlock.setLocation(this.template, jj_consume_token, token2);
        return transformBlock;
    }

    public final SwitchBlock Switch() throws ParseException {
        Token jj_consume_token = jj_consume_token(12);
        Expression Expression = Expression();
        jj_consume_token(124);
        this.switchNesting++;
        SwitchBlock switchBlock = new SwitchBlock(Expression);
        boolean z = false;
        while (jj_2_15(2)) {
            Case Case = Case();
            if (Case.condition == null) {
                if (!z) {
                    z = true;
                } else {
                    throw new ParseException("You can only have one default case in a switch statement", this.template, jj_consume_token);
                }
            }
            switchBlock.addCase(Case);
        }
        int i = this.jj_ntk;
        if (i == -1) {
            i = jj_ntk();
        }
        if (i != 68) {
            this.jj_la1[65] = this.jj_gen;
        } else {
            jj_consume_token(68);
        }
        Token jj_consume_token2 = jj_consume_token(43);
        this.switchNesting--;
        switchBlock.setLocation(this.template, jj_consume_token, jj_consume_token2);
        return switchBlock;
    }

    public final Case Case() throws ParseException {
        Expression expression;
        Token token2;
        int i = this.jj_ntk;
        if (i == -1) {
            i = jj_ntk();
        }
        if (i != 68) {
            this.jj_la1[66] = this.jj_gen;
        } else {
            jj_consume_token(68);
        }
        int i2 = this.jj_ntk;
        if (i2 == -1) {
            i2 = jj_ntk();
        }
        if (i2 == 13) {
            token2 = jj_consume_token(13);
            expression = Expression();
            jj_consume_token(124);
        } else if (i2 == 53) {
            token2 = jj_consume_token(53);
            expression = null;
        } else {
            this.jj_la1[67] = this.jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
        }
        TemplateElement OptionalBlock = OptionalBlock();
        Case caseR = new Case(expression, OptionalBlock);
        caseR.setLocation(this.template, token2, (TemplateObject) OptionalBlock);
        return caseR;
    }

    public final EscapeBlock Escape() throws ParseException {
        Token jj_consume_token = jj_consume_token(59);
        Token jj_consume_token2 = jj_consume_token(120);
        jj_consume_token(118);
        Expression Expression = Expression();
        jj_consume_token(124);
        EscapeBlock escapeBlock = new EscapeBlock(jj_consume_token2.image, Expression, escapedExpression(Expression));
        this.escapes.addFirst(escapeBlock);
        escapeBlock.setContent(OptionalBlock());
        this.escapes.removeFirst();
        escapeBlock.setLocation(this.template, jj_consume_token, jj_consume_token(60));
        return escapeBlock;
    }

    public final NoEscapeBlock NoEscape() throws ParseException {
        Token jj_consume_token = jj_consume_token(61);
        if (!this.escapes.isEmpty()) {
            Object removeFirst = this.escapes.removeFirst();
            TemplateElement OptionalBlock = OptionalBlock();
            Token jj_consume_token2 = jj_consume_token(62);
            this.escapes.addFirst(removeFirst);
            NoEscapeBlock noEscapeBlock = new NoEscapeBlock(OptionalBlock);
            noEscapeBlock.setLocation(this.template, jj_consume_token, jj_consume_token2);
            return noEscapeBlock;
        }
        throw new ParseException("noescape with no matching escape encountered.", this.template, jj_consume_token);
    }

    public final Token LooseDirectiveEnd() throws ParseException {
        int i = this.jj_ntk;
        if (i == -1) {
            i = jj_ntk();
        }
        if (i == 124) {
            return jj_consume_token(124);
        }
        if (i == 125) {
            return jj_consume_token(125);
        }
        this.jj_la1[68] = this.jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
    }

    public final PropertySetting Setting() throws ParseException {
        Token jj_consume_token = jj_consume_token(26);
        Token jj_consume_token2 = jj_consume_token(120);
        jj_consume_token(91);
        Expression Expression = Expression();
        Token LooseDirectiveEnd = LooseDirectiveEnd();
        PropertySetting propertySetting = new PropertySetting(jj_consume_token2.image, Expression);
        propertySetting.setLocation(this.template, jj_consume_token, LooseDirectiveEnd);
        return propertySetting;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:57:?, code lost:
        return Return();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:?, code lost:
        return Stop();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final freemarker.core.TemplateElement FreemarkerDirective() throws freemarker.core.ParseException {
        /*
            r4 = this;
            int r0 = r4.jj_ntk
            r1 = -1
            if (r0 != r1) goto L_0x0009
            int r0 = r4.jj_ntk()
        L_0x0009:
            r2 = 6
            if (r0 == r2) goto L_0x00bf
            r2 = 8
            if (r0 == r2) goto L_0x00ba
            r2 = 61
            if (r0 == r2) goto L_0x00b5
            r2 = 63
            if (r0 == r2) goto L_0x00b0
            switch(r0) {
                case 10: goto L_0x00ab;
                case 11: goto L_0x00a6;
                case 12: goto L_0x00a1;
                default: goto L_0x001b;
            }
        L_0x001b:
            switch(r0) {
                case 14: goto L_0x009c;
                case 15: goto L_0x009c;
                case 16: goto L_0x009c;
                case 17: goto L_0x0097;
                case 18: goto L_0x0092;
                case 19: goto L_0x008d;
                case 20: goto L_0x008d;
                case 21: goto L_0x0088;
                case 22: goto L_0x0083;
                case 23: goto L_0x007e;
                case 24: goto L_0x0079;
                case 25: goto L_0x0074;
                case 26: goto L_0x006f;
                case 27: goto L_0x006a;
                case 28: goto L_0x0065;
                case 29: goto L_0x0065;
                case 30: goto L_0x005f;
                default: goto L_0x001e;
            }
        L_0x001e:
            switch(r0) {
                case 45: goto L_0x0059;
                case 46: goto L_0x0079;
                case 47: goto L_0x007e;
                case 48: goto L_0x0053;
                case 49: goto L_0x004d;
                case 50: goto L_0x004d;
                case 51: goto L_0x004d;
                case 52: goto L_0x004d;
                default: goto L_0x0021;
            }
        L_0x0021:
            switch(r0) {
                case 54: goto L_0x0047;
                case 55: goto L_0x0047;
                case 56: goto L_0x0041;
                case 57: goto L_0x0041;
                case 58: goto L_0x003b;
                case 59: goto L_0x0035;
                default: goto L_0x0024;
            }
        L_0x0024:
            int[] r0 = r4.jj_la1
            r2 = 69
            int r3 = r4.jj_gen
            r0[r2] = r3
            r4.jj_consume_token(r1)
            freemarker.core.ParseException r0 = new freemarker.core.ParseException
            r0.<init>()
            throw r0
        L_0x0035:
            freemarker.core.EscapeBlock r0 = r4.Escape()
            goto L_0x00c3
        L_0x003b:
            freemarker.core.FallbackInstruction r0 = r4.FallBack()
            goto L_0x00c3
        L_0x0041:
            freemarker.core.RecurseNode r0 = r4.Recurse()
            goto L_0x00c3
        L_0x0047:
            freemarker.core.TemplateElement r0 = r4.Nested()
            goto L_0x00c3
        L_0x004d:
            freemarker.core.TemplateElement r0 = r4.Trim()
            goto L_0x00c3
        L_0x0053:
            freemarker.core.TemplateElement r0 = r4.Flush()
            goto L_0x00c3
        L_0x0059:
            freemarker.core.BreakInstruction r0 = r4.Break()
            goto L_0x00c3
        L_0x005f:
            freemarker.core.TextBlock r0 = r4.NoParse()
            goto L_0x00c3
        L_0x0065:
            freemarker.core.Comment r0 = r4.Comment()
            goto L_0x00c3
        L_0x006a:
            freemarker.core.CompressedBlock r0 = r4.Compress()
            goto L_0x00c3
        L_0x006f:
            freemarker.core.PropertySetting r0 = r4.Setting()
            goto L_0x00c3
        L_0x0074:
            freemarker.core.TemplateElement r0 = r4.Call()
            goto L_0x00c3
        L_0x0079:
            freemarker.core.ReturnInstruction r0 = r4.Return()
            goto L_0x00c3
        L_0x007e:
            freemarker.core.StopInstruction r0 = r4.Stop()
            goto L_0x00c3
        L_0x0083:
            freemarker.core.VisitNode r0 = r4.Visit()
            goto L_0x00c3
        L_0x0088:
            freemarker.core.TransformBlock r0 = r4.Transform()
            goto L_0x00c3
        L_0x008d:
            freemarker.core.Macro r0 = r4.Macro()
            goto L_0x00c3
        L_0x0092:
            freemarker.core.LibraryLoad r0 = r4.Import()
            goto L_0x00c3
        L_0x0097:
            freemarker.core.Include r0 = r4.Include()
            goto L_0x00c3
        L_0x009c:
            freemarker.core.TemplateElement r0 = r4.Assign()
            goto L_0x00c3
        L_0x00a1:
            freemarker.core.SwitchBlock r0 = r4.Switch()
            goto L_0x00c3
        L_0x00a6:
            freemarker.core.IteratorBlock r0 = r4.ForEach()
            goto L_0x00c3
        L_0x00ab:
            freemarker.core.IteratorBlock r0 = r4.List()
            goto L_0x00c3
        L_0x00b0:
            freemarker.core.TemplateElement r0 = r4.UnifiedMacroTransform()
            goto L_0x00c3
        L_0x00b5:
            freemarker.core.NoEscapeBlock r0 = r4.NoEscape()
            goto L_0x00c3
        L_0x00ba:
            freemarker.core.TemplateElement r0 = r4.If()
            goto L_0x00c3
        L_0x00bf:
            freemarker.core.AttemptBlock r0 = r4.Attempt()
        L_0x00c3:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.core.FMParser.FreemarkerDirective():freemarker.core.TemplateElement");
    }

    public final TextBlock PCData() throws ParseException {
        StringBuffer stringBuffer = new StringBuffer();
        Token token2 = null;
        Token token3 = null;
        Token token4 = null;
        do {
            int i = this.jj_ntk;
            if (i == -1) {
                i = jj_ntk();
            }
            switch (i) {
                case 68:
                    token4 = token2;
                    token2 = jj_consume_token(68);
                    break;
                case 69:
                    token2 = jj_consume_token(69);
                    break;
                case 70:
                    token2 = jj_consume_token(70);
                    break;
                default:
                    this.jj_la1[70] = this.jj_gen;
                    jj_consume_token(-1);
                    throw new ParseException();
            }
            stringBuffer.append(token2.image);
            if (token3 == null) {
                token3 = token2;
            }
            if (token4 != null) {
                token4.next = null;
            }
        } while (jj_2_16(Integer.MAX_VALUE));
        if (this.stripText && this.contentNesting == 1) {
            return TextBlock.EMPTY_BLOCK;
        }
        TextBlock textBlock = new TextBlock(stringBuffer.toString(), false);
        textBlock.setLocation(this.template, token3, token2);
        return textBlock;
    }

    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP_START, MTH_ENTER_BLOCK] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final freemarker.core.Token UnparsedContent(java.lang.StringBuffer r5) throws freemarker.core.ParseException {
        /*
            r4 = this;
        L_0x0000:
            int r0 = r4.jj_ntk
            r1 = -1
            if (r0 != r1) goto L_0x0009
            int r0 = r4.jj_ntk()
        L_0x0009:
            switch(r0) {
                case 130: goto L_0x0032;
                case 131: goto L_0x002b;
                case 132: goto L_0x0024;
                case 133: goto L_0x001d;
                default: goto L_0x000c;
            }
        L_0x000c:
            int[] r5 = r4.jj_la1
            r0 = 71
            int r2 = r4.jj_gen
            r5[r0] = r2
            r4.jj_consume_token(r1)
            freemarker.core.ParseException r5 = new freemarker.core.ParseException
            r5.<init>()
            throw r5
        L_0x001d:
            r0 = 133(0x85, float:1.86E-43)
            freemarker.core.Token r0 = r4.jj_consume_token(r0)
            goto L_0x0038
        L_0x0024:
            r0 = 132(0x84, float:1.85E-43)
            freemarker.core.Token r0 = r4.jj_consume_token(r0)
            goto L_0x0038
        L_0x002b:
            r0 = 131(0x83, float:1.84E-43)
            freemarker.core.Token r0 = r4.jj_consume_token(r0)
            goto L_0x0038
        L_0x0032:
            r0 = 130(0x82, float:1.82E-43)
            freemarker.core.Token r0 = r4.jj_consume_token(r0)
        L_0x0038:
            java.lang.String r2 = r0.image
            r5.append(r2)
            int r2 = r4.jj_ntk
            if (r2 != r1) goto L_0x0045
            int r2 = r4.jj_ntk()
        L_0x0045:
            switch(r2) {
                case 130: goto L_0x0000;
                case 131: goto L_0x0000;
                case 132: goto L_0x0000;
                case 133: goto L_0x0000;
                default: goto L_0x0048;
            }
        L_0x0048:
            int[] r1 = r4.jj_la1
            r2 = 72
            int r3 = r4.jj_gen
            r1[r2] = r3
            int r1 = r5.length()
            java.lang.String r2 = r0.image
            int r2 = r2.length()
            int r1 = r1 - r2
            r5.setLength(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.core.FMParser.UnparsedContent(java.lang.StringBuffer):freemarker.core.Token");
    }

    public final TemplateElement Content() throws ParseException {
        TemplateElement templateElement;
        MixedContent mixedContent = new MixedContent();
        this.contentNesting++;
        TemplateElement templateElement2 = null;
        while (true) {
            int i = this.jj_ntk;
            if (i == -1) {
                i = jj_ntk();
            }
            if (!(i == 6 || i == 8 || i == 61 || i == 63)) {
                switch (i) {
                    case 10:
                    case 11:
                    case 12:
                        break;
                    default:
                        switch (i) {
                            case 14:
                            case 15:
                            case 16:
                            case 17:
                            case 18:
                            case 19:
                            case 20:
                            case 21:
                            case 22:
                            case 23:
                            case 24:
                            case 25:
                            case 26:
                            case 27:
                            case 28:
                            case 29:
                            case 30:
                                break;
                            default:
                                switch (i) {
                                    case 45:
                                    case 46:
                                    case 47:
                                    case 48:
                                    case 49:
                                    case 50:
                                    case 51:
                                    case 52:
                                        break;
                                    default:
                                        switch (i) {
                                            case 54:
                                            case 55:
                                            case 56:
                                            case 57:
                                            case 58:
                                            case 59:
                                                break;
                                            default:
                                                switch (i) {
                                                    case 68:
                                                    case 69:
                                                    case 70:
                                                        templateElement = PCData();
                                                        break;
                                                    case 71:
                                                        templateElement = StringOutput();
                                                        break;
                                                    case 72:
                                                        templateElement = NumericalOutput();
                                                        break;
                                                    default:
                                                        this.jj_la1[73] = this.jj_gen;
                                                        jj_consume_token(-1);
                                                        throw new ParseException();
                                                }
                                        }
                                }
                        }
                }
            }
            templateElement = FreemarkerDirective();
            if (templateElement2 == null) {
                templateElement2 = templateElement;
            }
            mixedContent.addElement(templateElement);
            int i2 = this.jj_ntk;
            if (i2 == -1) {
                i2 = jj_ntk();
            }
            if (!(i2 == 6 || i2 == 8 || i2 == 61 || i2 == 63)) {
                switch (i2) {
                    case 10:
                    case 11:
                    case 12:
                        continue;
                    default:
                        switch (i2) {
                            case 14:
                            case 15:
                            case 16:
                            case 17:
                            case 18:
                            case 19:
                            case 20:
                            case 21:
                            case 22:
                            case 23:
                            case 24:
                            case 25:
                            case 26:
                            case 27:
                            case 28:
                            case 29:
                            case 30:
                                continue;
                            default:
                                switch (i2) {
                                    case 45:
                                    case 46:
                                    case 47:
                                    case 48:
                                    case 49:
                                    case 50:
                                    case 51:
                                    case 52:
                                        continue;
                                    default:
                                        switch (i2) {
                                            case 54:
                                            case 55:
                                            case 56:
                                            case 57:
                                            case 58:
                                            case 59:
                                                continue;
                                            default:
                                                switch (i2) {
                                                    case 68:
                                                    case 69:
                                                    case 70:
                                                    case 71:
                                                    case 72:
                                                        break;
                                                    default:
                                                        this.jj_la1[74] = this.jj_gen;
                                                        this.contentNesting--;
                                                        mixedContent.setLocation(this.template, (TemplateObject) templateElement2, (TemplateObject) templateElement);
                                                        return mixedContent;
                                                }
                                        }
                                }
                        }
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:1:0x0006 A[LOOP_START, PHI: r1 
      PHI: (r1v1 freemarker.core.TemplateElement) = (r1v0 freemarker.core.TemplateElement), (r1v3 freemarker.core.TemplateElement) binds: [B:0:0x0000, B:15:0x003f] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final freemarker.core.TemplateElement FreeMarkerText() throws freemarker.core.ParseException {
        /*
            r6 = this;
            freemarker.core.MixedContent r0 = new freemarker.core.MixedContent
            r0.<init>()
            r1 = 0
        L_0x0006:
            int r2 = r6.jj_ntk
            r3 = -1
            if (r2 != r3) goto L_0x000f
            int r2 = r6.jj_ntk()
        L_0x000f:
            switch(r2) {
                case 68: goto L_0x002d;
                case 69: goto L_0x002d;
                case 70: goto L_0x002d;
                case 71: goto L_0x0028;
                case 72: goto L_0x0023;
                default: goto L_0x0012;
            }
        L_0x0012:
            int[] r0 = r6.jj_la1
            r1 = 75
            int r2 = r6.jj_gen
            r0[r1] = r2
            r6.jj_consume_token(r3)
            freemarker.core.ParseException r0 = new freemarker.core.ParseException
            r0.<init>()
            throw r0
        L_0x0023:
            freemarker.core.NumericalOutput r2 = r6.NumericalOutput()
            goto L_0x0031
        L_0x0028:
            freemarker.core.DollarVariable r2 = r6.StringOutput()
            goto L_0x0031
        L_0x002d:
            freemarker.core.TextBlock r2 = r6.PCData()
        L_0x0031:
            if (r1 != 0) goto L_0x0034
            r1 = r2
        L_0x0034:
            r0.addElement(r2)
            int r4 = r6.jj_ntk
            if (r4 != r3) goto L_0x003f
            int r4 = r6.jj_ntk()
        L_0x003f:
            switch(r4) {
                case 68: goto L_0x0006;
                case 69: goto L_0x0006;
                case 70: goto L_0x0006;
                case 71: goto L_0x0006;
                case 72: goto L_0x0006;
                default: goto L_0x0042;
            }
        L_0x0042:
            int[] r3 = r6.jj_la1
            r4 = 76
            int r5 = r6.jj_gen
            r3[r4] = r5
            freemarker.template.Template r3 = r6.template
            r0.setLocation((freemarker.template.Template) r3, (freemarker.core.TemplateObject) r1, (freemarker.core.TemplateObject) r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.core.FMParser.FreeMarkerText():freemarker.core.TemplateElement");
    }

    public final TemplateElement OptionalBlock() throws ParseException {
        TextBlock textBlock = TextBlock.EMPTY_BLOCK;
        int i = this.jj_ntk;
        if (i == -1) {
            i = jj_ntk();
        }
        if (!(i == 6 || i == 8 || i == 61 || i == 63)) {
            switch (i) {
                case 10:
                case 11:
                case 12:
                    break;
                default:
                    switch (i) {
                        case 14:
                        case 15:
                        case 16:
                        case 17:
                        case 18:
                        case 19:
                        case 20:
                        case 21:
                        case 22:
                        case 23:
                        case 24:
                        case 25:
                        case 26:
                        case 27:
                        case 28:
                        case 29:
                        case 30:
                            break;
                        default:
                            switch (i) {
                                case 45:
                                case 46:
                                case 47:
                                case 48:
                                case 49:
                                case 50:
                                case 51:
                                case 52:
                                    break;
                                default:
                                    switch (i) {
                                        case 54:
                                        case 55:
                                        case 56:
                                        case 57:
                                        case 58:
                                        case 59:
                                            break;
                                        default:
                                            switch (i) {
                                                case 68:
                                                case 69:
                                                case 70:
                                                case 71:
                                                case 72:
                                                    break;
                                                default:
                                                    this.jj_la1[77] = this.jj_gen;
                                                    return textBlock;
                                            }
                                    }
                            }
                    }
            }
        }
        return Content();
    }

    public final void HeaderElement() throws ParseException {
        int i = this.jj_ntk;
        if (i == -1) {
            i = jj_ntk();
        }
        if (i != 68) {
            this.jj_la1[78] = this.jj_gen;
        } else {
            jj_consume_token(68);
        }
        int i2 = this.jj_ntk;
        if (i2 == -1) {
            i2 = jj_ntk();
        }
        if (i2 == 65) {
            jj_consume_token(65);
            while (true) {
                int i3 = this.jj_ntk;
                if (i3 == -1) {
                    i3 = jj_ntk();
                }
                if (i3 != 120) {
                    this.jj_la1[79] = this.jj_gen;
                    LooseDirectiveEnd();
                    return;
                }
                Token jj_consume_token = jj_consume_token(120);
                jj_consume_token(91);
                Expression Expression = Expression();
                String str = jj_consume_token.image;
                String str2 = null;
                try {
                    TemplateModel eval = Expression.eval((Environment) null);
                    if (eval instanceof TemplateScalarModel) {
                        try {
                            str2 = ((TemplateScalarModel) Expression).getAsString();
                        } catch (TemplateModelException unused) {
                        }
                    }
                    if (this.template != null) {
                        if (str.equalsIgnoreCase("encoding")) {
                            if (str2 != null) {
                                String encoding = this.template.getEncoding();
                                if (encoding != null && !encoding.equals(str2)) {
                                    throw new Template.WrongEncodingException(str2);
                                }
                            } else {
                                throw new ParseException("Expecting an encoding string.", Expression);
                            }
                        } else if (str.equalsIgnoreCase("STRIP_WHITESPACE")) {
                            this.stripWhitespace = getBoolean(Expression);
                        } else if (str.equalsIgnoreCase("STRIP_TEXT")) {
                            this.stripText = getBoolean(Expression);
                        } else if (str.equalsIgnoreCase("STRICT_SYNTAX")) {
                            this.token_source.strictEscapeSyntax = getBoolean(Expression);
                        } else if (str.equalsIgnoreCase("ns_prefixes")) {
                            if (eval instanceof TemplateHashModelEx) {
                                TemplateHashModelEx templateHashModelEx = (TemplateHashModelEx) eval;
                                try {
                                    TemplateModelIterator it = templateHashModelEx.keys().iterator();
                                    while (it.hasNext()) {
                                        String asString = ((TemplateScalarModel) it.next()).getAsString();
                                        TemplateModel templateModel = templateHashModelEx.get(asString);
                                        if (templateModel instanceof TemplateScalarModel) {
                                            this.template.addPrefixNSMapping(asString, ((TemplateScalarModel) templateModel).getAsString());
                                        } else {
                                            throw new ParseException("Non-string value in prefix to namespace hash.", Expression);
                                        }
                                    }
                                    continue;
                                } catch (IllegalArgumentException e) {
                                    throw new ParseException(e.getMessage(), Expression);
                                } catch (TemplateModelException unused2) {
                                    continue;
                                }
                            } else {
                                throw new ParseException("Expecting a hash of prefixes to namespace URI's.", Expression);
                            }
                        } else if (!str.equalsIgnoreCase("attributes")) {
                            StringBuffer stringBuffer = new StringBuffer();
                            stringBuffer.append("Unknown FTL header parameter: ");
                            stringBuffer.append(jj_consume_token.image);
                            throw new ParseException(stringBuffer.toString(), this.template, jj_consume_token);
                        } else if (eval instanceof TemplateHashModelEx) {
                            TemplateHashModelEx templateHashModelEx2 = (TemplateHashModelEx) eval;
                            TemplateModelIterator it2 = templateHashModelEx2.keys().iterator();
                            while (it2.hasNext()) {
                                String asString2 = ((TemplateScalarModel) it2.next()).getAsString();
                                this.template.setCustomAttribute(asString2, DeepUnwrap.unwrap(templateHashModelEx2.get(asString2)));
                            }
                        } else {
                            throw new ParseException("Expecting a hash of attribute names to values.", Expression);
                        }
                    }
                } catch (Exception e2) {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("Could not evaluate expression: ");
                    stringBuffer2.append(Expression.getCanonicalForm());
                    stringBuffer2.append(" ");
                    stringBuffer2.append("\nUnderlying cause: ");
                    stringBuffer2.append(e2.getMessage());
                    throw new ParseException(stringBuffer2.toString(), (TemplateObject) Expression, (Throwable) e2);
                }
            }
        } else if (i2 == 66) {
            jj_consume_token(66);
        } else {
            this.jj_la1[80] = this.jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
        }
    }

    public final Map ParamList() throws ParseException {
        int i;
        HashMap hashMap = new HashMap();
        do {
            Identifier Identifier = Identifier();
            jj_consume_token(91);
            hashMap.put(Identifier.toString(), Expression());
            int i2 = this.jj_ntk;
            if (i2 == -1) {
                i2 = jj_ntk();
            }
            if (i2 != 108) {
                this.jj_la1[81] = this.jj_gen;
            } else {
                jj_consume_token(108);
            }
            i = this.jj_ntk;
            if (i == -1) {
                i = jj_ntk();
            }
        } while (i == 120);
        this.jj_la1[82] = this.jj_gen;
        return hashMap;
    }

    public final TemplateElement Root() throws ParseException {
        if (jj_2_17(Integer.MAX_VALUE)) {
            HeaderElement();
        }
        TemplateElement OptionalBlock = OptionalBlock();
        jj_consume_token(0);
        OptionalBlock.setParentRecursively((TemplateElement) null);
        return OptionalBlock.postParseCleanup(this.stripWhitespace);
    }

    private final boolean jj_2_1(int i) {
        this.jj_la = i;
        Token token2 = this.token;
        this.jj_scanpos = token2;
        this.jj_lastpos = token2;
        try {
            return true ^ jj_3_1();
        } catch (LookaheadSuccess unused) {
            return true;
        } finally {
            jj_save(0, i);
        }
    }

    private final boolean jj_2_2(int i) {
        this.jj_la = i;
        Token token2 = this.token;
        this.jj_scanpos = token2;
        this.jj_lastpos = token2;
        try {
            return !jj_3_2();
        } catch (LookaheadSuccess unused) {
            return true;
        } finally {
            jj_save(1, i);
        }
    }

    private final boolean jj_2_3(int i) {
        this.jj_la = i;
        Token token2 = this.token;
        this.jj_scanpos = token2;
        this.jj_lastpos = token2;
        try {
            return true ^ jj_3_3();
        } catch (LookaheadSuccess unused) {
            return true;
        } finally {
            jj_save(2, i);
        }
    }

    private final boolean jj_2_4(int i) {
        this.jj_la = i;
        Token token2 = this.token;
        this.jj_scanpos = token2;
        this.jj_lastpos = token2;
        try {
            return true ^ jj_3_4();
        } catch (LookaheadSuccess unused) {
            return true;
        } finally {
            jj_save(3, i);
        }
    }

    private final boolean jj_2_5(int i) {
        this.jj_la = i;
        Token token2 = this.token;
        this.jj_scanpos = token2;
        this.jj_lastpos = token2;
        try {
            return true ^ jj_3_5();
        } catch (LookaheadSuccess unused) {
            return true;
        } finally {
            jj_save(4, i);
        }
    }

    private final boolean jj_2_6(int i) {
        this.jj_la = i;
        Token token2 = this.token;
        this.jj_scanpos = token2;
        this.jj_lastpos = token2;
        try {
            return true ^ jj_3_6();
        } catch (LookaheadSuccess unused) {
            return true;
        } finally {
            jj_save(5, i);
        }
    }

    private final boolean jj_2_7(int i) {
        this.jj_la = i;
        Token token2 = this.token;
        this.jj_scanpos = token2;
        this.jj_lastpos = token2;
        try {
            return true ^ jj_3_7();
        } catch (LookaheadSuccess unused) {
            return true;
        } finally {
            jj_save(6, i);
        }
    }

    private final boolean jj_2_8(int i) {
        this.jj_la = i;
        Token token2 = this.token;
        this.jj_scanpos = token2;
        this.jj_lastpos = token2;
        try {
            return true ^ jj_3_8();
        } catch (LookaheadSuccess unused) {
            return true;
        } finally {
            jj_save(7, i);
        }
    }

    private final boolean jj_2_9(int i) {
        this.jj_la = i;
        Token token2 = this.token;
        this.jj_scanpos = token2;
        this.jj_lastpos = token2;
        try {
            return true ^ jj_3_9();
        } catch (LookaheadSuccess unused) {
            return true;
        } finally {
            jj_save(8, i);
        }
    }

    private final boolean jj_2_10(int i) {
        this.jj_la = i;
        Token token2 = this.token;
        this.jj_scanpos = token2;
        this.jj_lastpos = token2;
        try {
            return true ^ jj_3_10();
        } catch (LookaheadSuccess unused) {
            return true;
        } finally {
            jj_save(9, i);
        }
    }

    private final boolean jj_2_11(int i) {
        this.jj_la = i;
        Token token2 = this.token;
        this.jj_scanpos = token2;
        this.jj_lastpos = token2;
        try {
            return true ^ jj_3_11();
        } catch (LookaheadSuccess unused) {
            return true;
        } finally {
            jj_save(10, i);
        }
    }

    private final boolean jj_2_12(int i) {
        this.jj_la = i;
        Token token2 = this.token;
        this.jj_scanpos = token2;
        this.jj_lastpos = token2;
        try {
            return true ^ jj_3_12();
        } catch (LookaheadSuccess unused) {
            return true;
        } finally {
            jj_save(11, i);
        }
    }

    private final boolean jj_2_13(int i) {
        this.jj_la = i;
        Token token2 = this.token;
        this.jj_scanpos = token2;
        this.jj_lastpos = token2;
        try {
            return true ^ jj_3_13();
        } catch (LookaheadSuccess unused) {
            return true;
        } finally {
            jj_save(12, i);
        }
    }

    private final boolean jj_2_14(int i) {
        this.jj_la = i;
        Token token2 = this.token;
        this.jj_scanpos = token2;
        this.jj_lastpos = token2;
        try {
            return true ^ jj_3_14();
        } catch (LookaheadSuccess unused) {
            return true;
        } finally {
            jj_save(13, i);
        }
    }

    private final boolean jj_2_15(int i) {
        this.jj_la = i;
        Token token2 = this.token;
        this.jj_scanpos = token2;
        this.jj_lastpos = token2;
        try {
            return true ^ jj_3_15();
        } catch (LookaheadSuccess unused) {
            return true;
        } finally {
            jj_save(14, i);
        }
    }

    private final boolean jj_2_16(int i) {
        this.jj_la = i;
        Token token2 = this.token;
        this.jj_scanpos = token2;
        this.jj_lastpos = token2;
        try {
            return true ^ jj_3_16();
        } catch (LookaheadSuccess unused) {
            return true;
        } finally {
            jj_save(15, i);
        }
    }

    private final boolean jj_2_17(int i) {
        this.jj_la = i;
        Token token2 = this.token;
        this.jj_scanpos = token2;
        this.jj_lastpos = token2;
        try {
            return true ^ jj_3_17();
        } catch (LookaheadSuccess unused) {
            return true;
        } finally {
            jj_save(16, i);
        }
    }

    private final boolean jj_3R_92() {
        Token token2 = this.jj_scanpos;
        if (!jj_scan_token(20)) {
            return false;
        }
        this.jj_scanpos = token2;
        return jj_3R_122();
    }

    private final boolean jj_3R_169() {
        if (jj_scan_token(87)) {
            return true;
        }
        Token token2 = this.jj_scanpos;
        if (!jj_scan_token(120)) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_scan_token(100)) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_scan_token(101)) {
            return false;
        }
        this.jj_scanpos = token2;
        if (jj_3R_176()) {
            return true;
        }
        return false;
    }

    private final boolean jj_3R_88() {
        return jj_scan_token(11);
    }

    private final boolean jj_3_2() {
        Token token2 = this.jj_scanpos;
        if (!jj_scan_token(98)) {
            return false;
        }
        this.jj_scanpos = token2;
        return jj_scan_token(99);
    }

    private final boolean jj_3R_84() {
        return jj_scan_token(99);
    }

    private final boolean jj_3R_83() {
        return jj_scan_token(98);
    }

    private final boolean jj_3R_107() {
        return jj_scan_token(59);
    }

    private final boolean jj_3R_51() {
        Token token2 = this.jj_scanpos;
        if (jj_3R_83()) {
            this.jj_scanpos = token2;
            if (jj_3R_84()) {
                return true;
            }
        }
        if (jj_3R_50()) {
            return true;
        }
        return false;
    }

    private final boolean jj_3R_44() {
        Token token2;
        if (jj_3R_50()) {
            return true;
        }
        do {
            token2 = this.jj_scanpos;
        } while (!jj_3R_51());
        this.jj_scanpos = token2;
        return false;
    }

    private final boolean jj_3R_172() {
        if (!jj_scan_token(89) && !jj_scan_token(120)) {
            return false;
        }
        return true;
    }

    private final boolean jj_3R_91() {
        return jj_scan_token(18);
    }

    private final boolean jj_3R_27() {
        return jj_scan_token(53);
    }

    private final boolean jj_3R_87() {
        return jj_scan_token(10);
    }

    private final boolean jj_3R_26() {
        if (!jj_scan_token(13) && !jj_3R_23()) {
            return false;
        }
        return true;
    }

    private final boolean jj_3R_24() {
        Token token2 = this.jj_scanpos;
        if (jj_scan_token(68)) {
            this.jj_scanpos = token2;
        }
        Token token3 = this.jj_scanpos;
        if (jj_3R_26()) {
            this.jj_scanpos = token3;
            if (jj_3R_27()) {
                return true;
            }
        }
        if (jj_3R_28()) {
            return true;
        }
        return false;
    }

    private final boolean jj_3R_136() {
        return jj_scan_token(99);
    }

    private final boolean jj_3R_174() {
        return jj_scan_token(90);
    }

    private final boolean jj_3R_133() {
        Token token2 = this.jj_scanpos;
        if (jj_scan_token(98)) {
            this.jj_scanpos = token2;
            if (jj_3R_136()) {
                return true;
            }
        }
        if (jj_3R_135()) {
            return true;
        }
        return false;
    }

    private final boolean jj_3_10() {
        return jj_3R_23();
    }

    private final boolean jj_3R_178() {
        return jj_3R_23();
    }

    private final boolean jj_3R_90() {
        return jj_scan_token(17);
    }

    private final boolean jj_3R_137() {
        return jj_scan_token(107);
    }

    private final boolean jj_3R_177() {
        if (jj_scan_token(107)) {
            return true;
        }
        Token token2 = this.jj_scanpos;
        if (!jj_3R_178()) {
            return false;
        }
        this.jj_scanpos = token2;
        return false;
    }

    private final boolean jj_3_15() {
        return jj_3R_24();
    }

    private final boolean jj_3R_134() {
        Token token2;
        if (jj_3R_137()) {
            return true;
        }
        do {
            token2 = this.jj_scanpos;
        } while (!jj_3R_137());
        this.jj_scanpos = token2;
        if (jj_3R_135()) {
            return true;
        }
        return false;
    }

    private final boolean jj_3R_173() {
        Token token2 = this.jj_scanpos;
        if (!jj_scan_token(129)) {
            return false;
        }
        this.jj_scanpos = token2;
        return jj_3R_177();
    }

    private final boolean jj_3R_112() {
        return jj_scan_token(6);
    }

    private final boolean jj_3R_99() {
        return jj_scan_token(12);
    }

    private final boolean jj_3R_166() {
        return jj_3R_174();
    }

    private final boolean jj_3R_165() {
        return jj_3R_173();
    }

    private final boolean jj_3R_164() {
        return jj_3R_172();
    }

    private final boolean jj_3R_163() {
        return jj_3R_171();
    }

    private final boolean jj_3R_162() {
        return jj_3R_170();
    }

    private final boolean jj_3R_161() {
        return jj_3R_169();
    }

    private final boolean jj_3R_155() {
        Token token2 = this.jj_scanpos;
        if (!jj_3R_161()) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_3R_162()) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_3R_163()) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_3R_164()) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_3R_165()) {
            return false;
        }
        this.jj_scanpos = token2;
        return jj_3R_166();
    }

    private final boolean jj_3R_115() {
        return jj_3R_135();
    }

    private final boolean jj_3R_114() {
        return jj_3R_134();
    }

    private final boolean jj_3R_113() {
        return jj_3R_133();
    }

    private final boolean jj_3R_98() {
        return jj_scan_token(21);
    }

    private final boolean jj_3R_81() {
        Token token2 = this.jj_scanpos;
        if (!jj_3R_113()) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_3R_114()) {
            return false;
        }
        this.jj_scanpos = token2;
        return jj_3R_115();
    }

    private final boolean jj_3_11() {
        Token token2 = this.jj_scanpos;
        if (jj_scan_token(108)) {
            this.jj_scanpos = token2;
        }
        Token token3 = this.jj_scanpos;
        if (jj_scan_token(120)) {
            this.jj_scanpos = token3;
            if (jj_scan_token(81)) {
                return true;
            }
        }
        if (jj_scan_token(91)) {
            return true;
        }
        return false;
    }

    private final boolean jj_3R_31() {
        return jj_3R_34();
    }

    private final boolean jj_3R_86() {
        return jj_scan_token(8);
    }

    private final boolean jj_3R_28() {
        Token token2 = this.jj_scanpos;
        if (!jj_3R_31()) {
            return false;
        }
        this.jj_scanpos = token2;
        return false;
    }

    private final boolean jj_3R_154() {
        if (!jj_scan_token(87) && !jj_scan_token(120)) {
            return false;
        }
        return true;
    }

    private final boolean jj_3R_97() {
        return jj_scan_token(30);
    }

    private final boolean jj_3R_153() {
        if (!jj_scan_token(113) && !jj_3R_23() && !jj_scan_token(114)) {
            return false;
        }
        return true;
    }

    private final boolean jj_3_1() {
        Token token2 = this.jj_scanpos;
        if (!jj_scan_token(87)) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_scan_token(111)) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_scan_token(113)) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_scan_token(89)) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_scan_token(107)) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_scan_token(129)) {
            return false;
        }
        this.jj_scanpos = token2;
        return jj_scan_token(90);
    }

    private final boolean jj_3R_96() {
        Token token2 = this.jj_scanpos;
        if (!jj_scan_token(28)) {
            return false;
        }
        this.jj_scanpos = token2;
        return jj_scan_token(29);
    }

    private final boolean jj_3R_121() {
        return jj_scan_token(16);
    }

    private final boolean jj_3R_146() {
        return jj_3R_155();
    }

    private final boolean jj_3R_120() {
        return jj_scan_token(15);
    }

    private final boolean jj_3R_152() {
        return jj_scan_token(120);
    }

    private final boolean jj_3R_145() {
        return jj_3R_154();
    }

    private final boolean jj_3R_119() {
        return jj_scan_token(14);
    }

    private final boolean jj_3R_144() {
        return jj_3R_153();
    }

    private final boolean jj_3R_89() {
        Token token2 = this.jj_scanpos;
        if (!jj_3R_119()) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_3R_120()) {
            return false;
        }
        this.jj_scanpos = token2;
        return jj_3R_121();
    }

    private final boolean jj_3R_143() {
        return jj_3R_152();
    }

    private final boolean jj_3R_175() {
        Token token2 = this.jj_scanpos;
        if (jj_scan_token(108)) {
            this.jj_scanpos = token2;
        }
        return jj_3R_23();
    }

    private final boolean jj_3R_142() {
        return jj_3R_151();
    }

    private final boolean jj_3R_141() {
        return jj_3R_150();
    }

    private final boolean jj_3R_140() {
        return jj_3R_149();
    }

    private final boolean jj_3R_168() {
        Token token2;
        if (jj_3R_23()) {
            return true;
        }
        do {
            token2 = this.jj_scanpos;
        } while (!jj_3R_175());
        this.jj_scanpos = token2;
        return false;
    }

    private final boolean jj_3R_139() {
        return jj_3R_148();
    }

    private final boolean jj_3R_138() {
        return jj_3R_147();
    }

    private final boolean jj_3R_160() {
        Token token2 = this.jj_scanpos;
        if (!jj_3R_168()) {
            return false;
        }
        this.jj_scanpos = token2;
        return false;
    }

    private final boolean jj_3R_135() {
        Token token2;
        Token token3 = this.jj_scanpos;
        if (jj_3R_138()) {
            this.jj_scanpos = token3;
            if (jj_3R_139()) {
                this.jj_scanpos = token3;
                if (jj_3R_140()) {
                    this.jj_scanpos = token3;
                    if (jj_3R_141()) {
                        this.jj_scanpos = token3;
                        if (jj_3R_142()) {
                            this.jj_scanpos = token3;
                            if (jj_3R_143()) {
                                this.jj_scanpos = token3;
                                if (jj_3R_144()) {
                                    this.jj_scanpos = token3;
                                    if (jj_3R_145()) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        do {
            token2 = this.jj_scanpos;
        } while (!jj_3R_146());
        this.jj_scanpos = token2;
        return false;
    }

    private final boolean jj_3R_147() {
        Token token2 = this.jj_scanpos;
        if (!jj_scan_token(85)) {
            return false;
        }
        this.jj_scanpos = token2;
        return jj_scan_token(86);
    }

    private final boolean jj_3R_43() {
        return jj_3R_49();
    }

    private final boolean jj_3R_129() {
        return jj_scan_token(52);
    }

    private final boolean jj_3R_42() {
        return jj_3R_48();
    }

    private final boolean jj_3R_128() {
        return jj_scan_token(51);
    }

    private final boolean jj_3R_41() {
        return jj_3R_47();
    }

    private final boolean jj_3R_127() {
        return jj_scan_token(50);
    }

    private final boolean jj_3R_40() {
        return jj_3R_46();
    }

    private final boolean jj_3R_126() {
        return jj_scan_token(49);
    }

    private final boolean jj_3R_105() {
        Token token2 = this.jj_scanpos;
        if (!jj_3R_126()) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_3R_127()) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_3R_128()) {
            return false;
        }
        this.jj_scanpos = token2;
        return jj_3R_129();
    }

    private final boolean jj_3R_37() {
        Token token2 = this.jj_scanpos;
        if (!jj_3R_40()) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_3R_41()) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_3R_42()) {
            return false;
        }
        this.jj_scanpos = token2;
        return jj_3R_43();
    }

    private final boolean jj_3R_151() {
        if (!jj_scan_token(111) && !jj_3R_160() && !jj_scan_token(112)) {
            return false;
        }
        return true;
    }

    private final boolean jj_3R_23() {
        return jj_3R_25();
    }

    private final boolean jj_3R_34() {
        Token token2;
        if (jj_3R_37()) {
            return true;
        }
        do {
            token2 = this.jj_scanpos;
        } while (!jj_3R_37());
        this.jj_scanpos = token2;
        return false;
    }

    private final boolean jj_3_9() {
        return jj_scan_token(106);
    }

    private final boolean jj_3R_48() {
        return jj_scan_token(72);
    }

    private final boolean jj_3R_104() {
        return jj_scan_token(48);
    }

    private final boolean jj_3_13() {
        return jj_scan_token(113);
    }

    private final boolean jj_3R_30() {
        if (!jj_scan_token(106) && !jj_3R_29()) {
            return false;
        }
        return true;
    }

    private final boolean jj_3R_25() {
        Token token2;
        if (jj_3R_29()) {
            return true;
        }
        do {
            token2 = this.jj_scanpos;
        } while (!jj_3R_30());
        this.jj_scanpos = token2;
        return false;
    }

    private final boolean jj_3_14() {
        if (!jj_scan_token(120) && !jj_scan_token(91)) {
            return false;
        }
        return true;
    }

    private final boolean jj_3R_47() {
        return jj_scan_token(71);
    }

    private final boolean jj_3_8() {
        return jj_scan_token(105);
    }

    private final boolean jj_3R_131() {
        return jj_scan_token(55);
    }

    private final boolean jj_3R_130() {
        return jj_scan_token(54);
    }

    private final boolean jj_3R_95() {
        return jj_scan_token(25);
    }

    private final boolean jj_3R_33() {
        if (!jj_scan_token(105) && !jj_3R_32()) {
            return false;
        }
        return true;
    }

    private final boolean jj_3_16() {
        Token token2 = this.jj_scanpos;
        if (!jj_scan_token(68)) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_scan_token(69)) {
            return false;
        }
        this.jj_scanpos = token2;
        return jj_scan_token(70);
    }

    private final boolean jj_3R_106() {
        Token token2 = this.jj_scanpos;
        if (!jj_3R_130()) {
            return false;
        }
        this.jj_scanpos = token2;
        return jj_3R_131();
    }

    private final boolean jj_3R_29() {
        Token token2;
        if (jj_3R_32()) {
            return true;
        }
        do {
            token2 = this.jj_scanpos;
        } while (!jj_3R_33());
        this.jj_scanpos = token2;
        return false;
    }

    private final boolean jj_3R_85() {
        return jj_scan_token(68);
    }

    private final boolean jj_3R_167() {
        if (jj_scan_token(108) || jj_3R_23()) {
            return true;
        }
        Token token2 = this.jj_scanpos;
        if (jj_scan_token(108)) {
            this.jj_scanpos = token2;
            if (jj_scan_token(110)) {
                return true;
            }
        }
        if (jj_3R_23()) {
            return true;
        }
        return false;
    }

    private final boolean jj_3R_53() {
        Token token2 = this.jj_scanpos;
        if (!jj_3R_85()) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_scan_token(69)) {
            return false;
        }
        this.jj_scanpos = token2;
        return jj_scan_token(70);
    }

    private final boolean jj_3R_46() {
        Token token2;
        if (jj_3R_53()) {
            return true;
        }
        do {
            token2 = this.jj_scanpos;
        } while (!jj_3R_53());
        this.jj_scanpos = token2;
        return false;
    }

    private final boolean jj_3R_125() {
        return jj_scan_token(23);
    }

    private final boolean jj_3_6() {
        return jj_3R_23();
    }

    private final boolean jj_3R_103() {
        Token token2 = this.jj_scanpos;
        if (!jj_scan_token(47)) {
            return false;
        }
        this.jj_scanpos = token2;
        return jj_3R_125();
    }

    private final boolean jj_3R_156() {
        Token token2;
        if (jj_3R_23()) {
            return true;
        }
        Token token3 = this.jj_scanpos;
        if (jj_scan_token(108)) {
            this.jj_scanpos = token3;
            if (jj_scan_token(110)) {
                return true;
            }
        }
        if (jj_3R_23()) {
            return true;
        }
        do {
            token2 = this.jj_scanpos;
        } while (!jj_3R_167());
        this.jj_scanpos = token2;
        return false;
    }

    private final boolean jj_3_7() {
        return jj_scan_token(88);
    }

    private final boolean jj_3R_148() {
        if (jj_scan_token(115)) {
            return true;
        }
        Token token2 = this.jj_scanpos;
        if (jj_3R_156()) {
            this.jj_scanpos = token2;
        }
        if (jj_scan_token(116)) {
            return true;
        }
        return false;
    }

    private final boolean jj_3R_52() {
        return jj_3R_44();
    }

    private final boolean jj_3R_80() {
        return jj_3R_112();
    }

    private final boolean jj_3R_79() {
        return jj_3R_111();
    }

    private final boolean jj_3R_45() {
        if (jj_scan_token(88)) {
            return true;
        }
        Token token2 = this.jj_scanpos;
        if (!jj_3R_52()) {
            return false;
        }
        this.jj_scanpos = token2;
        return false;
    }

    private final boolean jj_3R_78() {
        return jj_3R_110();
    }

    private final boolean jj_3_12() {
        if (!jj_scan_token(120) && !jj_scan_token(91)) {
            return false;
        }
        return true;
    }

    private final boolean jj_3R_77() {
        return jj_3R_109();
    }

    private final boolean jj_3R_38() {
        if (jj_3R_44()) {
            return true;
        }
        Token token2 = this.jj_scanpos;
        if (!jj_3R_45()) {
            return false;
        }
        this.jj_scanpos = token2;
        return false;
    }

    private final boolean jj_3R_76() {
        return jj_3R_108();
    }

    private final boolean jj_3R_159() {
        return jj_scan_token(84);
    }

    private final boolean jj_3R_75() {
        return jj_3R_107();
    }

    private final boolean jj_3R_158() {
        return jj_scan_token(83);
    }

    private final boolean jj_3R_74() {
        return jj_3R_106();
    }

    private final boolean jj_3R_73() {
        return jj_3R_105();
    }

    private final boolean jj_3R_124() {
        return jj_scan_token(24);
    }

    private final boolean jj_3R_72() {
        return jj_3R_104();
    }

    private final boolean jj_3R_150() {
        Token token2 = this.jj_scanpos;
        if (!jj_3R_158()) {
            return false;
        }
        this.jj_scanpos = token2;
        return jj_3R_159();
    }

    private final boolean jj_3R_123() {
        return jj_scan_token(46);
    }

    private final boolean jj_3R_71() {
        return jj_3R_103();
    }

    private final boolean jj_3R_70() {
        return jj_3R_102();
    }

    private final boolean jj_3R_102() {
        Token token2 = this.jj_scanpos;
        if (!jj_3R_123()) {
            return false;
        }
        this.jj_scanpos = token2;
        return jj_3R_124();
    }

    private final boolean jj_3R_69() {
        return jj_3R_101();
    }

    private final boolean jj_3R_68() {
        return jj_3R_100();
    }

    private final boolean jj_3R_67() {
        return jj_3R_99();
    }

    private final boolean jj_3R_94() {
        return jj_scan_token(63);
    }

    private final boolean jj_3R_66() {
        return jj_3R_98();
    }

    private final boolean jj_3R_65() {
        return jj_3R_97();
    }

    private final boolean jj_3_5() {
        Token token2 = this.jj_scanpos;
        if (!jj_scan_token(127)) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_scan_token(97)) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_scan_token(126)) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_scan_token(96)) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_scan_token(95)) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_scan_token(95)) {
            return false;
        }
        this.jj_scanpos = token2;
        return jj_scan_token(94);
    }

    private final boolean jj_3R_64() {
        return jj_3R_96();
    }

    private final boolean jj_3R_63() {
        return jj_3R_95();
    }

    private final boolean jj_3R_62() {
        return jj_3R_94();
    }

    private final boolean jj_3R_61() {
        return jj_3R_93();
    }

    private final boolean jj_3R_60() {
        return jj_3R_92();
    }

    private final boolean jj_3R_39() {
        Token token2 = this.jj_scanpos;
        if (jj_scan_token(127)) {
            this.jj_scanpos = token2;
            if (jj_scan_token(97)) {
                this.jj_scanpos = token2;
                if (jj_scan_token(126)) {
                    this.jj_scanpos = token2;
                    if (jj_scan_token(96)) {
                        this.jj_scanpos = token2;
                        if (jj_scan_token(95)) {
                            this.jj_scanpos = token2;
                            if (jj_scan_token(94)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        if (jj_3R_38()) {
            return true;
        }
        return false;
    }

    private final boolean jj_3R_59() {
        return jj_3R_91();
    }

    private final boolean jj_3R_101() {
        return jj_scan_token(45);
    }

    private final boolean jj_3R_58() {
        return jj_3R_90();
    }

    private final boolean jj_3R_35() {
        if (jj_3R_38()) {
            return true;
        }
        Token token2 = this.jj_scanpos;
        if (!jj_3R_39()) {
            return false;
        }
        this.jj_scanpos = token2;
        return false;
    }

    private final boolean jj_3R_57() {
        return jj_3R_89();
    }

    private final boolean jj_3R_157() {
        return jj_scan_token(82);
    }

    private final boolean jj_3R_93() {
        return jj_scan_token(27);
    }

    private final boolean jj_3R_56() {
        return jj_3R_88();
    }

    private final boolean jj_3R_55() {
        return jj_3R_87();
    }

    private final boolean jj_3R_149() {
        Token token2 = this.jj_scanpos;
        if (!jj_scan_token(81)) {
            return false;
        }
        this.jj_scanpos = token2;
        return jj_3R_157();
    }

    private final boolean jj_3R_54() {
        return jj_3R_86();
    }

    private final boolean jj_3R_49() {
        Token token2 = this.jj_scanpos;
        if (!jj_3R_54()) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_3R_55()) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_3R_56()) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_3R_57()) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_3R_58()) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_3R_59()) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_3R_60()) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_3R_61()) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_3R_62()) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_3R_63()) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_3R_64()) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_3R_65()) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_3R_66()) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_3R_67()) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_3R_68()) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_3R_69()) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_3R_70()) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_3R_71()) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_3R_72()) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_3R_73()) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_3R_74()) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_3R_75()) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_3R_76()) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_3R_77()) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_3R_78()) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_3R_79()) {
            return false;
        }
        this.jj_scanpos = token2;
        return jj_3R_80();
    }

    private final boolean jj_3_4() {
        Token token2 = this.jj_scanpos;
        if (!jj_scan_token(93)) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_scan_token(91)) {
            return false;
        }
        this.jj_scanpos = token2;
        return jj_scan_token(92);
    }

    private final boolean jj_3R_111() {
        return jj_scan_token(58);
    }

    private final boolean jj_3R_171() {
        if (!jj_scan_token(113) && !jj_3R_160() && !jj_scan_token(114)) {
            return false;
        }
        return true;
    }

    private final boolean jj_3R_36() {
        Token token2 = this.jj_scanpos;
        if (jj_scan_token(93)) {
            this.jj_scanpos = token2;
            if (jj_scan_token(91)) {
                this.jj_scanpos = token2;
                if (jj_scan_token(92)) {
                    return true;
                }
            }
        }
        if (jj_3R_35()) {
            return true;
        }
        return false;
    }

    private final boolean jj_3R_100() {
        return jj_scan_token(26);
    }

    private final boolean jj_3R_32() {
        if (jj_3R_35()) {
            return true;
        }
        Token token2 = this.jj_scanpos;
        if (!jj_3R_36()) {
            return false;
        }
        this.jj_scanpos = token2;
        return false;
    }

    private final boolean jj_3R_132() {
        return jj_scan_token(57);
    }

    private final boolean jj_3_17() {
        Token token2 = this.jj_scanpos;
        if (jj_scan_token(68)) {
            this.jj_scanpos = token2;
        }
        Token token3 = this.jj_scanpos;
        if (!jj_scan_token(66)) {
            return false;
        }
        this.jj_scanpos = token3;
        return jj_scan_token(65);
    }

    private final boolean jj_3R_170() {
        if (!jj_scan_token(111) && !jj_3R_23() && !jj_scan_token(112)) {
            return false;
        }
        return true;
    }

    private final boolean jj_3R_110() {
        Token token2 = this.jj_scanpos;
        if (!jj_scan_token(56)) {
            return false;
        }
        this.jj_scanpos = token2;
        return jj_3R_132();
    }

    private final boolean jj_3_3() {
        Token token2 = this.jj_scanpos;
        if (!jj_scan_token(100)) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_scan_token(103)) {
            return false;
        }
        this.jj_scanpos = token2;
        return jj_scan_token(104);
    }

    private final boolean jj_3R_118() {
        return jj_scan_token(104);
    }

    private final boolean jj_3R_117() {
        return jj_scan_token(103);
    }

    private final boolean jj_3R_116() {
        return jj_scan_token(100);
    }

    private final boolean jj_3R_82() {
        Token token2 = this.jj_scanpos;
        if (jj_3R_116()) {
            this.jj_scanpos = token2;
            if (jj_3R_117()) {
                this.jj_scanpos = token2;
                if (jj_3R_118()) {
                    return true;
                }
            }
        }
        if (jj_3R_81()) {
            return true;
        }
        return false;
    }

    private final boolean jj_3R_50() {
        Token token2;
        if (jj_3R_81()) {
            return true;
        }
        do {
            token2 = this.jj_scanpos;
        } while (!jj_3R_82());
        this.jj_scanpos = token2;
        return false;
    }

    private final boolean jj_3R_109() {
        return jj_scan_token(22);
    }

    private final boolean jj_3R_108() {
        return jj_scan_token(61);
    }

    private final boolean jj_3R_122() {
        return jj_scan_token(19);
    }

    private final boolean jj_3R_176() {
        Token token2 = this.jj_scanpos;
        if (!jj_scan_token(94)) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_scan_token(95)) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_scan_token(96)) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_scan_token(97)) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_scan_token(83)) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_scan_token(84)) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_scan_token(117)) {
            return false;
        }
        this.jj_scanpos = token2;
        if (!jj_scan_token(118)) {
            return false;
        }
        this.jj_scanpos = token2;
        return jj_scan_token(119);
    }

    static {
        jj_la1_0();
        jj_la1_1();
        jj_la1_2();
        jj_la1_3();
        jj_la1_4();
    }

    private static void jj_la1_0() {
        jj_la1_0 = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 512, 0, 0, 0, 0, 0, 0, 16777216, 8388608, 0, 0, 114688, 0, 0, 0, 0, 0, 0, 0, 1572864, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, DriveFile.MODE_READ_WRITE, 0, 0, 0, 0, 0, 8192, 0, 2147474752, 0, 0, 0, 2147474752, 2147474752, 0, 0, 2147474752, 0, 0, 0, 0, 0};
    }

    private static void jj_la1_1() {
        jj_la1_1 = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4096, 6, 0, 0, 0, 50331648, 16384, 32768, 12582912, 1966080, 0, 0, 0, 0, 112, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 384, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2097152, 0, -1344282624, 0, 0, 0, -1344282624, -1344282624, 0, 0, -1344282624, 0, 0, 0, 0, 0};
    }

    private static void jj_la1_2() {
        jj_la1_2 = new int[]{16646144, 16646144, 0, 0, 0, 0, 939524096, -1073741824, 6291456, 393216, 109051904, 0, -1072168960, -1072168960, 393216, 1572864, 0, 0, 0, 16646144, 0, 0, 0, 0, 0, 16646144, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 134217728, 0, 0, 0, 0, 0, 0, 134217728, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16646144, 0, 16646144, 0, 0, 0, 0, 16, 16, 0, 0, 0, 112, 0, 0, 496, 496, 496, 496, 496, 16, 0, 6, 0, 0};
    }

    private static void jj_la1_3() {
        jj_la1_3 = new int[]{17465344, 17467404, 2048, 12, 12, 400, 0, -1073741821, 0, 16777216, 165888, 2048, 14680067, 31457331, 0, 0, 20480, 4096, 20480, 17467404, 8192, 0, 0, 0, 8388608, 17467404, 8388608, 0, 0, 0, 0, 0, 0, 4096, 2097152, 2097152, 0, 270532608, 8192, 16777216, 0, 131072, 16777216, 64, 0, 4096, 262144, 0, 0, 0, 4096, 0, 0, 16777216, 8192, DriveFile.MODE_READ_WRITE, 262144, 16777216, 17471500, 4096, 17467404, 0, 8192, 16777216, DriveFile.MODE_READ_WRITE, 0, 0, 0, DriveFile.MODE_READ_WRITE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16777216, 0, 4096, 16777216};
    }

    private static void jj_la1_4() {
        jj_la1_4 = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 60, 60, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    }

    public FMParser(InputStream inputStream) {
        this.escapes = new LinkedList();
        int i = 0;
        this.lookingAhead = false;
        this.jj_la1 = new int[83];
        this.jj_2_rtns = new JJCalls[17];
        this.jj_rescan = false;
        this.jj_gc = 0;
        this.jj_ls = new LookaheadSuccess();
        this.jj_expentries = new Vector();
        this.jj_kind = -1;
        this.jj_lasttokens = new int[100];
        this.jj_input_stream = new SimpleCharStream(inputStream, 1, 1);
        this.token_source = new FMParserTokenManager(this.jj_input_stream);
        this.token = new Token();
        this.jj_ntk = -1;
        this.jj_gen = 0;
        for (int i2 = 0; i2 < 83; i2++) {
            this.jj_la1[i2] = -1;
        }
        while (true) {
            JJCalls[] jJCallsArr = this.jj_2_rtns;
            if (i < jJCallsArr.length) {
                jJCallsArr[i] = new JJCalls();
                i++;
            } else {
                return;
            }
        }
    }

    public void ReInit(InputStream inputStream) {
        this.jj_input_stream.ReInit(inputStream, 1, 1);
        this.token_source.ReInit(this.jj_input_stream);
        this.token = new Token();
        this.jj_ntk = -1;
        int i = 0;
        this.jj_gen = 0;
        for (int i2 = 0; i2 < 83; i2++) {
            this.jj_la1[i2] = -1;
        }
        while (true) {
            JJCalls[] jJCallsArr = this.jj_2_rtns;
            if (i < jJCallsArr.length) {
                jJCallsArr[i] = new JJCalls();
                i++;
            } else {
                return;
            }
        }
    }

    public FMParser(Reader reader) {
        this.escapes = new LinkedList();
        int i = 0;
        this.lookingAhead = false;
        this.jj_la1 = new int[83];
        this.jj_2_rtns = new JJCalls[17];
        this.jj_rescan = false;
        this.jj_gc = 0;
        this.jj_ls = new LookaheadSuccess();
        this.jj_expentries = new Vector();
        this.jj_kind = -1;
        this.jj_lasttokens = new int[100];
        this.jj_input_stream = new SimpleCharStream(reader, 1, 1);
        this.token_source = new FMParserTokenManager(this.jj_input_stream);
        this.token = new Token();
        this.jj_ntk = -1;
        this.jj_gen = 0;
        for (int i2 = 0; i2 < 83; i2++) {
            this.jj_la1[i2] = -1;
        }
        while (true) {
            JJCalls[] jJCallsArr = this.jj_2_rtns;
            if (i < jJCallsArr.length) {
                jJCallsArr[i] = new JJCalls();
                i++;
            } else {
                return;
            }
        }
    }

    public void ReInit(Reader reader) {
        this.jj_input_stream.ReInit(reader, 1, 1);
        this.token_source.ReInit(this.jj_input_stream);
        this.token = new Token();
        this.jj_ntk = -1;
        int i = 0;
        this.jj_gen = 0;
        for (int i2 = 0; i2 < 83; i2++) {
            this.jj_la1[i2] = -1;
        }
        while (true) {
            JJCalls[] jJCallsArr = this.jj_2_rtns;
            if (i < jJCallsArr.length) {
                jJCallsArr[i] = new JJCalls();
                i++;
            } else {
                return;
            }
        }
    }

    public FMParser(FMParserTokenManager fMParserTokenManager) {
        this.escapes = new LinkedList();
        int i = 0;
        this.lookingAhead = false;
        this.jj_la1 = new int[83];
        this.jj_2_rtns = new JJCalls[17];
        this.jj_rescan = false;
        this.jj_gc = 0;
        this.jj_ls = new LookaheadSuccess();
        this.jj_expentries = new Vector();
        this.jj_kind = -1;
        this.jj_lasttokens = new int[100];
        this.token_source = fMParserTokenManager;
        this.token = new Token();
        this.jj_ntk = -1;
        this.jj_gen = 0;
        for (int i2 = 0; i2 < 83; i2++) {
            this.jj_la1[i2] = -1;
        }
        while (true) {
            JJCalls[] jJCallsArr = this.jj_2_rtns;
            if (i < jJCallsArr.length) {
                jJCallsArr[i] = new JJCalls();
                i++;
            } else {
                return;
            }
        }
    }

    public void ReInit(FMParserTokenManager fMParserTokenManager) {
        this.token_source = fMParserTokenManager;
        this.token = new Token();
        this.jj_ntk = -1;
        int i = 0;
        this.jj_gen = 0;
        for (int i2 = 0; i2 < 83; i2++) {
            this.jj_la1[i2] = -1;
        }
        while (true) {
            JJCalls[] jJCallsArr = this.jj_2_rtns;
            if (i < jJCallsArr.length) {
                jJCallsArr[i] = new JJCalls();
                i++;
            } else {
                return;
            }
        }
    }

    private final Token jj_consume_token(int i) throws ParseException {
        Token token2 = this.token;
        if (token2.next != null) {
            this.token = this.token.next;
        } else {
            Token token3 = this.token;
            Token nextToken = this.token_source.getNextToken();
            token3.next = nextToken;
            this.token = nextToken;
        }
        this.jj_ntk = -1;
        if (this.token.kind == i) {
            this.jj_gen++;
            int i2 = this.jj_gc + 1;
            this.jj_gc = i2;
            if (i2 > 100) {
                int i3 = 0;
                this.jj_gc = 0;
                while (true) {
                    JJCalls[] jJCallsArr = this.jj_2_rtns;
                    if (i3 >= jJCallsArr.length) {
                        break;
                    }
                    for (JJCalls jJCalls = jJCallsArr[i3]; jJCalls != null; jJCalls = jJCalls.next) {
                        if (jJCalls.gen < this.jj_gen) {
                            jJCalls.first = null;
                        }
                    }
                    i3++;
                }
            }
            return this.token;
        }
        this.token = token2;
        this.jj_kind = i;
        throw generateParseException();
    }

    private static final class LookaheadSuccess extends Error {
        private LookaheadSuccess() {
        }
    }

    private final boolean jj_scan_token(int i) {
        Token token2 = this.jj_scanpos;
        if (token2 == this.jj_lastpos) {
            this.jj_la--;
            if (token2.next == null) {
                Token token3 = this.jj_scanpos;
                Token nextToken = this.token_source.getNextToken();
                token3.next = nextToken;
                this.jj_scanpos = nextToken;
                this.jj_lastpos = nextToken;
            } else {
                Token token4 = this.jj_scanpos.next;
                this.jj_scanpos = token4;
                this.jj_lastpos = token4;
            }
        } else {
            this.jj_scanpos = token2.next;
        }
        if (this.jj_rescan) {
            Token token5 = this.token;
            int i2 = 0;
            while (token5 != null && token5 != this.jj_scanpos) {
                i2++;
                token5 = token5.next;
            }
            if (token5 != null) {
                jj_add_error_token(i, i2);
            }
        }
        if (this.jj_scanpos.kind != i) {
            return true;
        }
        if (this.jj_la != 0 || this.jj_scanpos != this.jj_lastpos) {
            return false;
        }
        throw this.jj_ls;
    }

    public final Token getNextToken() {
        if (this.token.next != null) {
            this.token = this.token.next;
        } else {
            Token token2 = this.token;
            Token nextToken = this.token_source.getNextToken();
            token2.next = nextToken;
            this.token = nextToken;
        }
        this.jj_ntk = -1;
        this.jj_gen++;
        return this.token;
    }

    public final Token getToken(int i) {
        Token token2;
        Token token3 = this.lookingAhead ? this.jj_scanpos : this.token;
        for (int i2 = 0; i2 < i; i2++) {
            if (token3.next != null) {
                token2 = token3.next;
            } else {
                Token nextToken = this.token_source.getNextToken();
                token3.next = nextToken;
                token2 = nextToken;
            }
        }
        return token3;
    }

    private final int jj_ntk() {
        Token token2 = this.token.next;
        this.jj_nt = token2;
        if (token2 == null) {
            Token token3 = this.token;
            Token nextToken = this.token_source.getNextToken();
            token3.next = nextToken;
            int i = nextToken.kind;
            this.jj_ntk = i;
            return i;
        }
        int i2 = token2.kind;
        this.jj_ntk = i2;
        return i2;
    }

    private void jj_add_error_token(int i, int i2) {
        if (i2 < 100) {
            int i3 = this.jj_endpos;
            if (i2 == i3 + 1) {
                int[] iArr = this.jj_lasttokens;
                this.jj_endpos = i3 + 1;
                iArr[i3] = i;
            } else if (i3 != 0) {
                this.jj_expentry = new int[i3];
                for (int i4 = 0; i4 < this.jj_endpos; i4++) {
                    this.jj_expentry[i4] = this.jj_lasttokens[i4];
                }
                Enumeration elements = this.jj_expentries.elements();
                boolean z = false;
                while (elements.hasMoreElements()) {
                    int[] iArr2 = (int[]) elements.nextElement();
                    if (iArr2.length == this.jj_expentry.length) {
                        int i5 = 0;
                        while (true) {
                            int[] iArr3 = this.jj_expentry;
                            if (i5 >= iArr3.length) {
                                z = true;
                                break;
                            } else if (iArr2[i5] != iArr3[i5]) {
                                z = false;
                                break;
                            } else {
                                i5++;
                            }
                        }
                        if (z) {
                            break;
                        }
                    }
                }
                if (!z) {
                    this.jj_expentries.addElement(this.jj_expentry);
                }
                if (i2 != 0) {
                    int[] iArr4 = this.jj_lasttokens;
                    this.jj_endpos = i2;
                    iArr4[i2 - 1] = i;
                }
            }
        }
    }

    public ParseException generateParseException() {
        this.jj_expentries.removeAllElements();
        boolean[] zArr = new boolean[134];
        for (int i = 0; i < 134; i++) {
            zArr[i] = false;
        }
        int i2 = this.jj_kind;
        if (i2 >= 0) {
            zArr[i2] = true;
            this.jj_kind = -1;
        }
        for (int i3 = 0; i3 < 83; i3++) {
            if (this.jj_la1[i3] == this.jj_gen) {
                for (int i4 = 0; i4 < 32; i4++) {
                    int i5 = 1 << i4;
                    if ((jj_la1_0[i3] & i5) != 0) {
                        zArr[i4] = true;
                    }
                    if ((jj_la1_1[i3] & i5) != 0) {
                        zArr[i4 + 32] = true;
                    }
                    if ((jj_la1_2[i3] & i5) != 0) {
                        zArr[i4 + 64] = true;
                    }
                    if ((jj_la1_3[i3] & i5) != 0) {
                        zArr[i4 + 96] = true;
                    }
                    if ((jj_la1_4[i3] & i5) != 0) {
                        zArr[i4 + 128] = true;
                    }
                }
            }
        }
        for (int i6 = 0; i6 < 134; i6++) {
            if (zArr[i6]) {
                int[] iArr = new int[1];
                this.jj_expentry = iArr;
                iArr[0] = i6;
                this.jj_expentries.addElement(iArr);
            }
        }
        this.jj_endpos = 0;
        jj_rescan_token();
        jj_add_error_token(0, 0);
        int[][] iArr2 = new int[this.jj_expentries.size()][];
        for (int i7 = 0; i7 < this.jj_expentries.size(); i7++) {
            iArr2[i7] = (int[]) this.jj_expentries.elementAt(i7);
        }
        return new ParseException(this.token, iArr2, FMParserConstants.tokenImage);
    }

    private final void jj_rescan_token() {
        this.jj_rescan = true;
        for (int i = 0; i < 17; i++) {
            JJCalls jJCalls = this.jj_2_rtns[i];
            do {
                if (jJCalls.gen > this.jj_gen) {
                    this.jj_la = jJCalls.arg;
                    Token token2 = jJCalls.first;
                    this.jj_scanpos = token2;
                    this.jj_lastpos = token2;
                    switch (i) {
                        case 0:
                            jj_3_1();
                            break;
                        case 1:
                            jj_3_2();
                            break;
                        case 2:
                            jj_3_3();
                            break;
                        case 3:
                            jj_3_4();
                            break;
                        case 4:
                            jj_3_5();
                            break;
                        case 5:
                            jj_3_6();
                            break;
                        case 6:
                            jj_3_7();
                            break;
                        case 7:
                            jj_3_8();
                            break;
                        case 8:
                            jj_3_9();
                            break;
                        case 9:
                            jj_3_10();
                            break;
                        case 10:
                            jj_3_11();
                            break;
                        case 11:
                            jj_3_12();
                            break;
                        case 12:
                            jj_3_13();
                            break;
                        case 13:
                            jj_3_14();
                            break;
                        case 14:
                            jj_3_15();
                            break;
                        case 15:
                            jj_3_16();
                            break;
                        case 16:
                            jj_3_17();
                            break;
                    }
                }
                jJCalls = jJCalls.next;
            } while (jJCalls != null);
        }
        this.jj_rescan = false;
    }

    private final void jj_save(int i, int i2) {
        JJCalls jJCalls = this.jj_2_rtns[i];
        while (true) {
            if (jJCalls.gen <= this.jj_gen) {
                break;
            } else if (jJCalls.next == null) {
                JJCalls jJCalls2 = new JJCalls();
                jJCalls.next = jJCalls2;
                jJCalls = jJCalls2;
                break;
            } else {
                jJCalls = jJCalls.next;
            }
        }
        jJCalls.gen = (this.jj_gen + i2) - this.jj_la;
        jJCalls.first = this.token;
        jJCalls.arg = i2;
    }

    static final class JJCalls {
        int arg;
        Token first;
        int gen;
        JJCalls next;

        JJCalls() {
        }
    }
}
