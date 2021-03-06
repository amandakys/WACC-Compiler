package front_end.AST;

import antlr.BasicParser;
import back_end.data_type.Expression;
import back_end.data_type.register.Register;
import front_end.AST.ExpressionAST.IdentAST;
import front_end.symbol_table.IDENTIFIER;
import front_end.symbol_table.TYPE;
import main.Visitor;
import optimisation.GraphColour;
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

    protected abstract void check();

    public IDENTIFIER getIdentObj() {
        return identObj;
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

    public IGNode getIGNode() {
        return IGNode;
    }

    public void setIGNode(IGNode IGNode) {
        this.IGNode = IGNode;
    }

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

    protected void error(String message) {
        Visitor.error(ctx, message);
    }

    protected void checkIfInScope(String name) {
        IDENTIFIER N = Visitor.ST.lookUpAll(name);
        if(N != null) {
            error(name + " has already been declared");
        }
    }

    public void defaultIRep(String name) {
        IGNode = findIGNode(name);
        IGNode.setFrom(index);
        IGNode.setTo(index);
    }

    public void setRegister(Register register) {
        IGNode.setRegister(register);
    }

    public static void reserveRegForPrint() {
       if(GraphColour.startReg < 1) {
           GraphColour.startReg = 1;
       }
    }

    public static void reserveRegForPrintStr() {
        if(GraphColour.startReg < 3) {
            GraphColour.startReg = 3;
        }
    }

    public IGNode findIGNode(String name) {
        return InterferenceGraph.findIGNode(name, index);
    }
}
