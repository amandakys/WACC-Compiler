package front_end.AST.AssignmentAST;

import back_end.data_type.ImmValue;
import back_end.data_type.register.PreIndex;
import back_end.data_type.register.Register;
import back_end.instruction.Branch;
import back_end.instruction.data_manipulation.ADD;
import back_end.instruction.data_manipulation.MOV;
import back_end.instruction.load_store.LOAD;
import back_end.instruction.load_store.STORE;
import front_end.AST.Compare;
import front_end.AST.FunctionDecl.ArglistAST;
import main.CodeGen;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;
import front_end.symbol_table.FUNCTION;
import front_end.symbol_table.IDENTIFIER;
import front_end.symbol_table.TYPE;

import static back_end.Utility.addFunction;
import static back_end.Utility.addMain;

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
        ImmValue argSize = new ImmValue(arglist.size());
        addMain(new Branch("L", "f_"+funcname));
        if (arglist.size() != 0) {
            addMain((new ADD(Register.SP, Register.SP, argSize)));
        }
        addMain(new MOV(CodeGen.notUsedRegisters.peek(),Register.R0));

    }

    public void setReVisited() {
        isReVisited = true;
    }
    
}