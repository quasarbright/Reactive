// Generated from /home/mdelmonaco/Documents/GitHub/Reactive/src/grammar/Reactive.g4 by ANTLR 4.8
package grammar;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ReactiveParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ReactiveVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ReactiveParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(ReactiveParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link ReactiveParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(ReactiveParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link ReactiveParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(ReactiveParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ReactiveParser#exprProgram}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprProgram(ReactiveParser.ExprProgramContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SDChild}
	 * labeled alternative in {@link ReactiveParser#sumDiff}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSDChild(ReactiveParser.SDChildContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Plus}
	 * labeled alternative in {@link ReactiveParser#sumDiff}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlus(ReactiveParser.PlusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Minus}
	 * labeled alternative in {@link ReactiveParser#sumDiff}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMinus(ReactiveParser.MinusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Divide}
	 * labeled alternative in {@link ReactiveParser#prodDiv}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivide(ReactiveParser.DivideContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PDChild}
	 * labeled alternative in {@link ReactiveParser#prodDiv}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPDChild(ReactiveParser.PDChildContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Times}
	 * labeled alternative in {@link ReactiveParser#prodDiv}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTimes(ReactiveParser.TimesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UMinus}
	 * labeled alternative in {@link ReactiveParser#uminus}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUMinus(ReactiveParser.UMinusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UMChild}
	 * labeled alternative in {@link ReactiveParser#uminus}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUMChild(ReactiveParser.UMChildContext ctx);
	/**
	 * Visit a parse tree produced by {@link ReactiveParser#parenExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpr(ReactiveParser.ParenExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Double}
	 * labeled alternative in {@link ReactiveParser#atomic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDouble(ReactiveParser.DoubleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Integer}
	 * labeled alternative in {@link ReactiveParser#atomic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteger(ReactiveParser.IntegerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Identifier}
	 * labeled alternative in {@link ReactiveParser#atomic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(ReactiveParser.IdentifierContext ctx);
}