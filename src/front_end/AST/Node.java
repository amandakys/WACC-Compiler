package front_end.AST;

import back_end.PrintUtility;
import back_end.data_type.register.Register;
import front_end.symbol_table.IDENTIFIER;
import front_end.symbol_table.TYPE;
import main.Visitor;
import optimisation.IGNode;
import optimisation.InterferenceGraph;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class Node {
    //associate with the type of the obj in the sumbol table
    protected IDENTIFIER identObj;
    //ctx is passed for commenting
    protected ParserRuleContext ctx;
    //to make sure that the node has already been checked
    protected boolean isChecked;
    //size of the current tree
    protected int size;
    //index relative to the curent tree
    protected int index;
    protected IGNode IGNode;

    public Node(ParserRuleContext ctx) {
        this.ctx = ctx;
    }

    public void checkNode() {
        if(!isChecked) {
            check();
            isChecked = true;
        }
    }

    //use to ensure the node has no semantic a
    protected abstract void check();

    public IDENTIFIER getIdentObj() {
        return identObj;
    }

    //checkType makes sure the current node has the same type as the node that is passed as a parameter
    public void checkType(Node node) {
        if (getType() == null) {
            //this value cannot be assigned to
            error("trying to assign to unassignable value");
        }

        if (!Compare.types(this.getType(), node.getType())) {
            error("types do not match\nexpected: " + this.getType() + "\nactual: " + node.getType());
        }
    }

    public TYPE getType() {
        return identObj.getType();
    }

    //print out the error message if comes across any semantics message
    protected void error(String message) {
        Visitor.error(ctx, message);
    }

    //check if the variable name is already in scope
    protected void checkIfInScope(String name) {
        IDENTIFIER N = Visitor.ST.lookUpAll(name);
        if(N != null) {
            error(name + " has already been declared");
        }
    }

    //translate node to target language - aiding CodeGen
    public abstract void translate();

    public abstract void weight();

    public int getSize() {
        return size;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public abstract void IRepresentation();

    public Register getRegister() {
        return IGNode.getRegister();
    }

    public optimisation.IGNode getIGNode() {
        return IGNode;
    }

    public void setIGNode(optimisation.IGNode IGNode) {
        this.IGNode = IGNode;
    }

    public void defaultIRep(String name) {
        IGNode = new IGNode(name);
        IGNode.setFrom(index);
        IGNode.setTo(index);
        Visitor.ST.add(IGNode);
    }

    public void addPrintFunc(String name) {
        if(Visitor.ST.findIGNode(name) == null) {
            IGNode p_func = new IGNode(name);
            IGNode.addEdge(p_func);
            Visitor.ST.add(p_func);
        }
    }

    public void print_stringIR() {
        if(Visitor.ST.findIGNode("print_string_mov") == null) {
            IGNode string_mov = new IGNode("print_string_mov");
            IGNode string_load = new IGNode("print_string_ldr");

            string_mov.addEdge(string_load);
            IGNode.addEdge(string_load);
            IGNode.addEdge(string_mov);

            Visitor.ST.add(string_load);
            Visitor.ST.add(string_mov);
        }
    }
}
