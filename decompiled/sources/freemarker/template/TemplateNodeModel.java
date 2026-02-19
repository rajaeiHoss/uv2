package freemarker.template;

public interface TemplateNodeModel extends TemplateModel {
    TemplateSequenceModel getChildNodes() throws TemplateModelException;

    String getNodeName() throws TemplateModelException;

    String getNodeNamespace() throws TemplateModelException;

    String getNodeType() throws TemplateModelException;

    TemplateNodeModel getParentNode() throws TemplateModelException;
}
