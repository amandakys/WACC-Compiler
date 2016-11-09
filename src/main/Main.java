package main;

import antlr.BasicLexer;
import antlr.BasicParser;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

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
        parser.removeErrorListeners();
        //parser.addErrorListener(new SyntaxError());
        Visitor v = new Visitor();
        v.visit(parser.program());
    }
}
