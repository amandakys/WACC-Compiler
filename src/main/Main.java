package main;

import antlr.BasicLexer;
import antlr.BasicParser;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
<<<<<<< HEAD
import org.antlr.v4.runtime.tree.ParseTree;
=======
>>>>>>> semanticsV2

import java.io.IOException;

/**
 * Created by andikoh on 08/11/2016.
 */
public class Main {
    public static void main(String args[]) throws IOException {
        ANTLRInputStream input = new ANTLRFileStream(args[0]);
        BasicLexer lexer = new BasicLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        BasicParser parser = new BasicParser(tokens);
<<<<<<< HEAD
        ParseTree tree = parser.program();

        int errors = parser.getNumberOfSyntaxErrors();
        if (errors > 0) {
            System.exit(100);
        }

        //syntax checking
        SyntaxVisitor syntaxVisitor = new SyntaxVisitor();
        syntaxVisitor.visit(tree);

        //semantic checking
        Visitor semanticVisit = new Visitor();
        semanticVisit.visit(tree);
=======
        parser.removeErrorListeners();
        //parser.addErrorListener(new SyntaxError());
        Visitor v = new Visitor();
        v.visit(parser.program());
>>>>>>> semanticsV2
    }
}
