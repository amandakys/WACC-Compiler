package front_end.AST.StatementAST;

import back_end.PrintUtility;
import back_end.data_type.register.PreIndex;
import back_end.data_type.register.Register;
import back_end.instruction.Branch;
import back_end.instruction.data_manipulation.ADD;
import back_end.instruction.load_store.LOAD;
import front_end.AST.AssignmentAST.ArrayelemAST;
import front_end.AST.AssignmentAST.AssignlhsAST;
import front_end.AST.AssignmentAST.PairelemAST;
import front_end.AST.Node;
import front_end.AST.ProgramAST;
import front_end.symbol_table.TYPE;
import main.Visitor;
import optimisation.IGNode;
import optimisation.InterferenceGraph;
import org.antlr.v4.runtime.ParserRuleContext;

import static back_end.Utility.*;

public class ReadAST extends StatementAST {
    private AssignlhsAST expression;

    public ReadAST(ParserRuleContext ctx, AssignlhsAST expression) {
        super(ctx);
        this.expression = expression;
    }
    @Override
    public void check() {
        expression.check();
        TYPE t = expression.getType(); //check that expresison is of a type acceptable to read

        TYPE intType = Visitor.ST.lookUpAll("int").getType();

        TYPE charType = Visitor.ST.lookUpAll("char").getType();

        if (!t.equals(intType) && !t.equals(charType)){
            //error
            error("read only takes int or char types");
        }

        identObj = t;

    }

    @Override
    public void translate() {
        Node exprChild = expression.getChild();

        if(exprChild != null) {
            exprChild.translate();
        }

        ProgramAST.nextAddress -= identObj.getSize();

        if(exprChild instanceof PairelemAST || exprChild instanceof ArrayelemAST) {
            addMain(new LOAD(getRegister(), new PreIndex(getRegister())));
        } else {
            addMain(new ADD(getRegister(), Register.SP, Visitor.ST.getAddress(expression.getIdent()).getShiftVal()));
        }

        String functionName = "p_read_" + expression.getType().getTypeName();
        addMain(new Branch("L", functionName));

        String placeholder = "";
        if (expression.getType().getTypeName().equals("char")) {
            placeholder = "\" %c\\0\"";
        } else if (expression.getType().getTypeName().equals("int")) {
            placeholder = "\"%d\\0\"";
        }

        PrintUtility.addToPlaceholders(placeholder);
        PrintUtility.addToEndFunctions(functionName, getRegister());
    }

    @Override
    public void weight() {
        expression.weight();
        size = expression.getSize();
    }

    @Override
    public void IRepresentation() {
        expression.IRepresentation();
        IGNode = expression.getIGNode();
    }

}
