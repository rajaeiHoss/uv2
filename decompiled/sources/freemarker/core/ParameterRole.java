package freemarker.core;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.streamax.client.CommonUtilities;

final class ParameterRole {
    static final ParameterRole ARGUMENT_NAME = new ParameterRole("argument name");
    static final ParameterRole ARGUMENT_VALUE = new ParameterRole("argument value");
    static final ParameterRole ASSIGNMENT_SOURCE = new ParameterRole("assignment source");
    static final ParameterRole ASSIGNMENT_TARGET = new ParameterRole("assignment target");
    static final ParameterRole AST_NODE_SUBTYPE = new ParameterRole("AST-node subtype");
    static final ParameterRole CALLEE = new ParameterRole("callee");
    static final ParameterRole CATCH_ALL_PARAMETER_NAME = new ParameterRole("catch-all parameter name");
    static final ParameterRole CONDITION = new ParameterRole("condition");
    static final ParameterRole CONTENT = new ParameterRole(FirebaseAnalytics.Param.CONTENT);
    static final ParameterRole EMBEDDED_TEMPLATE = new ParameterRole("embedded template");
    static final ParameterRole ENCLOSED_OPERAND = new ParameterRole("enclosed operand");
    static final ParameterRole ENCODING_PARAMETER = new ParameterRole("\"encoding\" parameter");
    static final ParameterRole ERROR_HANDLER = new ParameterRole("error handler");
    static final ParameterRole EXPRESSION_TEMPLATE = new ParameterRole("expression template");
    static final ParameterRole ITEM_KEY = new ParameterRole("item key");
    static final ParameterRole ITEM_VALUE = new ParameterRole("item value");
    static final ParameterRole LEFT_HAND_OPERAND = new ParameterRole("left-hand operand");
    static final ParameterRole LIST_SOURCE = new ParameterRole("list source");
    static final ParameterRole MAXIMUM_DECIMALS = new ParameterRole("maximum decimals");
    static final ParameterRole MESSAGE = new ParameterRole(CommonUtilities.EXTRA_MESSAGE);
    static final ParameterRole MINIMUM_DECIMALS = new ParameterRole("minimum decimals");
    static final ParameterRole NAMESPACE = new ParameterRole("namespace");
    static final ParameterRole NODE = new ParameterRole("node");
    static final ParameterRole PARAMETER_DEFAULT = new ParameterRole("parameter default");
    static final ParameterRole PARAMETER_NAME = new ParameterRole("parameter name");
    static final ParameterRole PARSE_PARAMETER = new ParameterRole("\"parse\" parameter");
    static final ParameterRole PASSED_VALUE = new ParameterRole("passed value");
    static final ParameterRole PLACEHOLDER_VARIABLE = new ParameterRole("placeholder variable");
    static final ParameterRole RIGHT_HAND_OPERAND = new ParameterRole("right-hand operand");
    static final ParameterRole TARGET_LOOP_VARIABLE = new ParameterRole("target loop variable");
    static final ParameterRole TEMPLATE_NAME = new ParameterRole("template name");
    static final ParameterRole UNKNOWN = new ParameterRole("[unknown role]");
    static final ParameterRole VALUE = new ParameterRole(FirebaseAnalytics.Param.VALUE);
    static final ParameterRole VARIABLE_SCOPE = new ParameterRole("variable scope");
    private final String name;

    private ParameterRole(String str) {
        this.name = str;
    }

    static ParameterRole forBinaryOperatorOperand(int i) {
        if (i == 0) {
            return LEFT_HAND_OPERAND;
        }
        if (i == 1) {
            return RIGHT_HAND_OPERAND;
        }
        throw new IndexOutOfBoundsException();
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.name;
    }
}
