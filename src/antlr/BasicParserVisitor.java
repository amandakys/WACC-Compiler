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
	 * Visit a parse tree produced by {@link BasicParser#character}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharacter(@NotNull BasicParser.CharacterContext ctx);
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
	 * Visit a parse tree produced by {@link BasicParser#intsign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntsign(@NotNull BasicParser.IntsignContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(@NotNull BasicParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#assignrhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignrhs(@NotNull BasicParser.AssignrhsContext ctx);
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
	 * Visit a parse tree produced by {@link BasicParser#arrayelem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayelem(@NotNull BasicParser.ArrayelemContext ctx);
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
	 * Visit a parse tree produced by {@link BasicParser#pairtype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPairtype(@NotNull BasicParser.PairtypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#binop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinop(@NotNull BasicParser.BinopContext ctx);
}