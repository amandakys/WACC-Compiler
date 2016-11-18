package front_end.AST.AssignmentAST;

import back_end.instruction.Instruction;
import front_end.AST.Compare;
import front_end.AST.FunctionDecl.ArglistAST;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;
import front_end.symbol_table.FUNCTION;
import front_end.symbol_table.IDENTIFIER;
import front_end.symbol_table.TYPE;

import java.util.List;

/**
 * Created by tsd15 on 09/11/16.
 */
public class CallAST extends AssignrhsAST{
    private String funcname;
    private ArglistAST arglist;
    private boolean isDeclared = true;
    private boolean isReVisited = false;
    
    public CallAST(ParserRuleContext ctx, String funcname, ArglistAST arglist) {
        super(ctx);
        this.funcname = funcname;
        this.arglist = arglist;
    }
    @Override
    public void check() {
        IDENTIFIER F = Visitor.ST.lookUpAll(funcname);
        
        if (F == null || !(F instanceof FUNCTION)) {
            if(!isReVisited) {
                Visitor.ST.getEncSymbolTable().add(funcname, null);
                isDeclared = false;
            } else {
                error("Unknown function " + funcname);
            }
        } else if (((FUNCTION)F).getParamList().size() != arglist.size()) {
            error("wrong no. of params");
        } else {
            arglist.check();
            
            for(int i = 0; i < arglist.size(); i++) {
                if (!Compare.types(arglist.getType(i), ((FUNCTION) F).getParamList().get(i))){
                    error("unexpected type in function " + funcname + "\nexpected: " + ((FUNCTION) F).getParamList().get(i)+ "\nactual: " + arglist.getType(i));
                }
            }
            
            identObj = F;
        }
    }
    
    public boolean isDeclared() {
        return isDeclared;
    }
    
    @Override
    public TYPE getType() {
        return ((FUNCTION) identObj).getReturntype();
    }

    @Override
    public void translate() {

    }

    public void setReVisited() {
        isReVisited = true;
    }
    
}