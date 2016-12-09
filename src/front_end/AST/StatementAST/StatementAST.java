package front_end.AST.StatementAST;

import front_end.AST.AssignmentAST.AssignmentAST;
import front_end.AST.ExpressionAST.BinOpAST;
import front_end.AST.ExpressionAST.IdentAST;
import front_end.AST.Node;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public abstract class StatementAST extends Node {
    protected static Integer labelCount = 0;
    public StatementAST(ParserRuleContext ctx) {
        super(ctx);
    }

    public boolean determineLoopInvariance(List<String> idents) {
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

    public void findLoopInvariants(List<String> idents) {}

    public void extractLoopInvariants(List<String> idents) {
        if (determineLoopInvariance(idents)) {
            translate();
        }
    }

    public void extractLoopDependents(List<String> idents) {
        if (!determineLoopInvariance(idents)) {
            translate();
        }
    }

}
