package freemarker.ext.dom;

import freemarker.template.TemplateScalarModel;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Comment;

class CharacterDataNodeModel extends NodeModel implements TemplateScalarModel {
    public boolean isEmpty() {
        return true;
    }

    public CharacterDataNodeModel(CharacterData characterData) {
        super(characterData);
    }

    public String getAsString() {
        return ((CharacterData) this.node).getData();
    }

    public String getNodeName() {
        return this.node instanceof Comment ? "@comment" : "@text";
    }
}
