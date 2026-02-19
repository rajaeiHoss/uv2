package freemarker.core;

import freemarker.template.SimpleScalar;
import freemarker.template.SimpleSequence;
import freemarker.template.TemplateException;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNodeModel;
import freemarker.template.utility.StringUtil;
import java.util.List;

class NodeBuiltins {
    private NodeBuiltins() {
    }

    private static abstract class NodeBuiltIn extends BuiltIn {
        /* access modifiers changed from: package-private */
        public abstract TemplateModel calculateResult(TemplateNodeModel templateNodeModel, Environment environment) throws TemplateModelException;

        private NodeBuiltIn() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel _eval(Environment environment) throws TemplateException {
            TemplateModel eval = this.target.eval(environment);
            if (eval instanceof TemplateNodeModel) {
                return calculateResult((TemplateNodeModel) eval, environment);
            }
            throw new UnexpectedTypeException(this.target, eval, "node", environment);
        }
    }

    static class ancestorsBI extends NodeBuiltIn {
        ancestorsBI() {
            super();
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(TemplateNodeModel templateNodeModel, Environment environment) throws TemplateModelException {
            AncestorSequence ancestorSequence = new AncestorSequence(environment);
            for (TemplateNodeModel parentNode = templateNodeModel.getParentNode(); parentNode != null; parentNode = parentNode.getParentNode()) {
                ancestorSequence.add((Object) parentNode);
            }
            return ancestorSequence;
        }
    }

    static class childrenBI extends NodeBuiltIn {
        childrenBI() {
            super();
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(TemplateNodeModel templateNodeModel, Environment environment) throws TemplateModelException {
            return templateNodeModel.getChildNodes();
        }
    }

    static class node_nameBI extends NodeBuiltIn {
        node_nameBI() {
            super();
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(TemplateNodeModel templateNodeModel, Environment environment) throws TemplateModelException {
            return new SimpleScalar(templateNodeModel.getNodeName());
        }
    }

    static class node_typeBI extends NodeBuiltIn {
        node_typeBI() {
            super();
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(TemplateNodeModel templateNodeModel, Environment environment) throws TemplateModelException {
            return new SimpleScalar(templateNodeModel.getNodeType());
        }
    }

    static class parentBI extends NodeBuiltIn {
        parentBI() {
            super();
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(TemplateNodeModel templateNodeModel, Environment environment) throws TemplateModelException {
            return templateNodeModel.getParentNode();
        }
    }

    static class rootBI extends NodeBuiltIn {
        rootBI() {
            super();
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(TemplateNodeModel templateNodeModel, Environment environment) throws TemplateModelException {
            TemplateNodeModel parentNode = templateNodeModel.getParentNode();
            while (true) {
                TemplateNodeModel templateNodeModel2 = parentNode;
                TemplateNodeModel templateNodeModel3 = templateNodeModel;
                templateNodeModel = templateNodeModel2;
                if (templateNodeModel == null) {
                    return templateNodeModel3;
                }
                parentNode = templateNodeModel.getParentNode();
            }
        }
    }

    static class node_namespaceBI extends NodeBuiltIn {
        node_namespaceBI() {
            super();
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(TemplateNodeModel templateNodeModel, Environment environment) throws TemplateModelException {
            String nodeNamespace = templateNodeModel.getNodeNamespace();
            if (nodeNamespace == null) {
                return null;
            }
            return new SimpleScalar(nodeNamespace);
        }
    }

    static class AncestorSequence extends SimpleSequence implements TemplateMethodModel {
        private Environment env;

        AncestorSequence(Environment environment) {
            this.env = environment;
        }

        public Object exec(List list) throws TemplateModelException {
            if (list == null || list.isEmpty()) {
                return this;
            }
            AncestorSequence ancestorSequence = new AncestorSequence(this.env);
            for (int i = 0; i < size(); i++) {
                TemplateNodeModel templateNodeModel = (TemplateNodeModel) get(i);
                String nodeName = templateNodeModel.getNodeName();
                String nodeNamespace = templateNodeModel.getNodeNamespace();
                if (nodeNamespace != null) {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= list.size()) {
                            break;
                        } else if (StringUtil.matchesName((String) list.get(i2), nodeName, nodeNamespace, this.env)) {
                            ancestorSequence.add((Object) templateNodeModel);
                            break;
                        } else {
                            i2++;
                        }
                    }
                } else if (list.contains(nodeName)) {
                    ancestorSequence.add((Object) templateNodeModel);
                }
            }
            return ancestorSequence;
        }
    }
}
