import antlr.BasicLexer;
import antlr.BasicParser;
import main.SyntaxVisitor;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

public class Main {
    public static void main(String args[]) throws IOException {
        assert (args.length == 1): "The program only takes one argument";

        ANTLRInputStream input = new ANTLRFileStream(args[0]);
        BasicLexer lexer = new BasicLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);


        BasicParser parser = new BasicParser(tokens);

        SyntaxVisitor v = new SyntaxVisitor();
	    ParseTree tree = parser.program();

        int errors = parser.getNumberOfSyntaxErrors();
        if (errors > 0) {
            System.exit(100);
        }

        v.visit(tree);

        //v.visit(parser.program());
    }

}
