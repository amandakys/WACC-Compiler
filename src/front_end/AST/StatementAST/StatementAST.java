package front_end.AST.StatementAST;

import front_end.AST.AssignmentAST.AssignmentAST;
import front_end.AST.ExpressionAST.BinOpAST;
import front_end.AST.ExpressionAST.IdentAST;
import front_end.AST.Node;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class StatementAST extends Node {
    protected static Integer labelCount = 0;
    public StatementAST(ParserRuleContext ctx) {
        super(ctx);
    }

    public boolean determineLoopInvariance() {
        return false;
    }
//    public void determineLoopInvariance() {
//        if (this instanceof VarDeclAST) {
//            if (!(((VarDeclAST) this).getRhs() instanceof IdentAST && ((VarDeclAST) this).getRhs() instanceof BinOpAST)) {
//                //this statement is invariant
//                this.translate();
//            }
//        } else if (this instanceof AssignmentAST) {
//            if (!(((AssignmentAST) this).getRhs() instanceof IdentAST && ((AssignmentAST) this).getRhs() instanceof BinOpAST)) {
//                //this statement is invariant
//                this.translate();
//            }
//        }
//    }
//
//    public void extractLoopDependents() {
//        if (!(this instanceof VarDeclAST && this instanceof AssignmentAST)) {
//
//        }
//    }
    public void findLoopInvariants() {}

    public void extractLoopInvariants() {
        if (determineLoopInvariance()) {
            translate();
        }
    }

    public void extractLoopDependents() {
        if (!determineLoopInvariance()) {
            translate();
        }
    }

}
