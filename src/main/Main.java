package main;

import back_end.instruction.Instruction;
import front_end.AST.Node;
import antlr.BasicLexer;
import antlr.BasicParser;
import main.error_handler.SyntaxVisitor;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by andikoh on 08/11/2016.
 */
public class Main {
    public static void main(String args[]) throws IOException {
        assert args.length == 1 : "Must have a pathname in order to compile";

        Path p = Paths.get(args[0]);
        String fileName = p.getFileName().toString();
        if(fileName.indexOf(".") > 0) {
            fileName = fileName.substring(0, fileName.lastIndexOf("."));
        }


        ANTLRInputStream input = new ANTLRFileStream(args[0]);
        BasicLexer lexer = new BasicLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        BasicParser parser = new BasicParser(tokens);
        ParseTree tree = parser.program();

        if(parser.getNumberOfSyntaxErrors() > 0) {
            System.exit(100);
        }

        //syntax checking
        SyntaxVisitor syntaxVisitor = new SyntaxVisitor();
        syntaxVisitor.visit(tree);

        //semantic checking
        Visitor semanticVisit = new Visitor();
        Node program = semanticVisit.visit(tree);
        semanticVisit.checkUndefinedFunc();

        //code generation
        CodeGen codeGen = new CodeGen();
        codeGen.writeFile(fileName);
    }
}
