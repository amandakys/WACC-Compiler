package front_end.AST;

import antlr.BasicParser;
import back_end.data_type.Expression;
import back_end.data_type.register.Register;
import front_end.AST.ExpressionAST.IdentAST;
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

    protected abstract void check();

    public IDENTIFIER getIdentObj() {
        return identObj;
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

    public void setIGNode(optimisation.IGNode IGNode) {
        this.IGNode = IGNode;
    }

    public void defaultIRep(String name) {
        IGNode = new IGNode(name);
        IGNode.setFrom(index);
        IGNode.setTo(index);
        InterferenceGraph.add(IGNode);
    }

    public void newIGNode(String name) {
        if (InterferenceGraph.findIGNode(name) == null) {
            IGNode p_func = new IGNode(name);
            InterferenceGraph.add(p_func);
        }
    }

    public void print_stringIR() {
        if (InterferenceGraph.findIGNode("print_string_mov") == null) {
            IGNode string_mov = new IGNode("print_string_mov");
            IGNode string_load = new IGNode("print_string_ldr");

            string_mov.addEdge(string_load);
            IGNode.addEdge(string_load);
            IGNode.addEdge(string_mov);

            InterferenceGraph.add(string_load);
            InterferenceGraph.add(string_mov);
        }
    }

    public void setRegister(Register register) {
        IGNode.setRegister(register);
    }

    public void linkToMessage(IGNode... nodes) {
        IGNode message = new IGNode("message");
        InterferenceGraph.add(message);

        for (IGNode n : nodes) {
            n.addEdge(message);
        }
        IGNode.addEdge(message);
    }

    public void linkToString(IGNode... nodes) {
        IGNode message = new IGNode("message");
        IGNode string_mov;
        IGNode string_load;

        //print string is needed to throw runtime error
        if (InterferenceGraph.findIGNode("print_string_mov") == null) {
            string_mov = new IGNode("print_string_mov");
        } else {
            string_mov = InterferenceGraph.findIGNode("print_string_mov");
        }

        if (InterferenceGraph.findIGNode("print_string_ldr") == null) {
            string_load = new IGNode("print_string_ldr");
        } else {
            string_load = InterferenceGraph.findIGNode("print_string_ldr");
        }

        for(IGNode node : nodes) {
            if(!node.equals(IGNode)) {
                node.addEdge(string_load);
                node.addEdge(string_mov);
                node.addEdge(message);
            }
        }

        string_mov.addEdge(string_load);
        message.addEdge(string_load);
        message.addEdge(string_mov);

        IGNode.addEdge(string_load);
        IGNode.addEdge(string_mov);
        IGNode.addEdge(message);

        InterferenceGraph.add(message);
        InterferenceGraph.add(string_load);
        InterferenceGraph.add(string_mov);

    }
}
