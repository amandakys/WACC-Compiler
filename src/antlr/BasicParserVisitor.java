// Generated from ./BasicParser.g4 by ANTLR 4.4
package antlr;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link BasicParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface BasicParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link BasicParser#p1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitP1(@NotNull BasicParser.P1Context ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#p2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitP2(@NotNull BasicParser.P2Context ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#p3}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitP3(@NotNull BasicParser.P3Context ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#p4}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitP4(@NotNull BasicParser.P4Context ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#p5}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitP5(@NotNull BasicParser.P5Context ctx);
	/**
	 * Visit a parse tree produced by the {@code functioncall}
	 * labeled alternative in {@link BasicParser#assignrhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctioncall(@NotNull BasicParser.FunctioncallContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#p6}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitP6(@NotNull BasicParser.P6Context ctx);
	/**
	 * Visit a parse tree produced by the {@code skip}
	 * labeled alternative in {@link BasicParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSkip(@NotNull BasicParser.SkipContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(@NotNull BasicParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#pairelem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPairelem(@NotNull BasicParser.PairelemContext ctx);
	/**
	 * Visit a parse tree produced by the {@code while}
	 * labeled alternative in {@link BasicParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile(@NotNull BasicParser.WhileContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(@NotNull BasicParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#unop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnop(@NotNull BasicParser.UnopContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#boolliter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolliter(@NotNull BasicParser.BoolliterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code println}
	 * labeled alternative in {@link BasicParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintln(@NotNull BasicParser.PrintlnContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#character}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharacter(@NotNull BasicParser.CharacterContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#exprNoBinOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprNoBinOp(@NotNull BasicParser.ExprNoBinOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(@NotNull BasicParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#strliter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStrliter(@NotNull BasicParser.StrliterContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(@NotNull BasicParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#arglist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArglist(@NotNull BasicParser.ArglistContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr}
	 * labeled alternative in {@link BasicParser#assignrhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(@NotNull BasicParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code free}
	 * labeled alternative in {@link BasicParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFree(@NotNull BasicParser.FreeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code if}
	 * labeled alternative in {@link BasicParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf(@NotNull BasicParser.IfContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#binOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinOp(@NotNull BasicParser.BinOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code read}
	 * labeled alternative in {@link BasicParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRead(@NotNull BasicParser.ReadContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#pairelemtype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPairelemtype(@NotNull BasicParser.PairelemtypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(@NotNull BasicParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#assignlhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignlhs(@NotNull BasicParser.AssignlhsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignment}
	 * labeled alternative in {@link BasicParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(@NotNull BasicParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#arrayliter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayliter(@NotNull BasicParser.ArrayliterContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#paramlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamlist(@NotNull BasicParser.ParamlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#intliter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntliter(@NotNull BasicParser.IntliterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arraylit}
	 * labeled alternative in {@link BasicParser#assignrhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArraylit(@NotNull BasicParser.ArraylitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exit}
	 * labeled alternative in {@link BasicParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExit(@NotNull BasicParser.ExitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sequence}
	 * labeled alternative in {@link BasicParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSequence(@NotNull BasicParser.SequenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#arrayelem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayelem(@NotNull BasicParser.ArrayelemContext ctx);
	/**
	 * Visit a parse tree produced by the {@code print}
	 * labeled alternative in {@link BasicParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(@NotNull BasicParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code newpair}
	 * labeled alternative in {@link BasicParser#assignrhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewpair(@NotNull BasicParser.NewpairContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#arraytype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArraytype(@NotNull BasicParser.ArraytypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#charliter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharliter(@NotNull BasicParser.CharliterContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#basetype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBasetype(@NotNull BasicParser.BasetypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code var_decl}
	 * labeled alternative in {@link BasicParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_decl(@NotNull BasicParser.Var_declContext ctx);
	/**
	 * Visit a parse tree produced by the {@code begin}
	 * labeled alternative in {@link BasicParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBegin(@NotNull BasicParser.BeginContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#pairtype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPairtype(@NotNull BasicParser.PairtypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code return}
	 * labeled alternative in {@link BasicParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn(@NotNull BasicParser.ReturnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pairelement}
	 * labeled alternative in {@link BasicParser#assignrhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPairelement(@NotNull BasicParser.PairelementContext ctx);
}