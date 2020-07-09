// Generated from /home/mdelmonaco/Documents/GitHub/Reactive/src/grammar/Reactive.g4 by ANTLR 4.8
package grammar;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ReactiveParser}.
 */
public interface ReactiveListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ReactiveParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(ReactiveParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReactiveParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(ReactiveParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReactiveParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(ReactiveParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReactiveParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(ReactiveParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReactiveParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(ReactiveParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReactiveParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(ReactiveParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReactiveParser#exprProgram}.
	 * @param ctx the parse tree
	 */
	void enterExprProgram(ReactiveParser.ExprProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReactiveParser#exprProgram}.
	 * @param ctx the parse tree
	 */
	void exitExprProgram(ReactiveParser.ExprProgramContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SDChild}
	 * labeled alternative in {@link ReactiveParser#sumDiff}.
	 * @param ctx the parse tree
	 */
	void enterSDChild(ReactiveParser.SDChildContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SDChild}
	 * labeled alternative in {@link ReactiveParser#sumDiff}.
	 * @param ctx the parse tree
	 */
	void exitSDChild(ReactiveParser.SDChildContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Plus}
	 * labeled alternative in {@link ReactiveParser#sumDiff}.
	 * @param ctx the parse tree
	 */
	void enterPlus(ReactiveParser.PlusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Plus}
	 * labeled alternative in {@link ReactiveParser#sumDiff}.
	 * @param ctx the parse tree
	 */
	void exitPlus(ReactiveParser.PlusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Minus}
	 * labeled alternative in {@link ReactiveParser#sumDiff}.
	 * @param ctx the parse tree
	 */
	void enterMinus(ReactiveParser.MinusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Minus}
	 * labeled alternative in {@link ReactiveParser#sumDiff}.
	 * @param ctx the parse tree
	 */
	void exitMinus(ReactiveParser.MinusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Divide}
	 * labeled alternative in {@link ReactiveParser#prodDiv}.
	 * @param ctx the parse tree
	 */
	void enterDivide(ReactiveParser.DivideContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Divide}
	 * labeled alternative in {@link ReactiveParser#prodDiv}.
	 * @param ctx the parse tree
	 */
	void exitDivide(ReactiveParser.DivideContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PDChild}
	 * labeled alternative in {@link ReactiveParser#prodDiv}.
	 * @param ctx the parse tree
	 */
	void enterPDChild(ReactiveParser.PDChildContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PDChild}
	 * labeled alternative in {@link ReactiveParser#prodDiv}.
	 * @param ctx the parse tree
	 */
	void exitPDChild(ReactiveParser.PDChildContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Times}
	 * labeled alternative in {@link ReactiveParser#prodDiv}.
	 * @param ctx the parse tree
	 */
	void enterTimes(ReactiveParser.TimesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Times}
	 * labeled alternative in {@link ReactiveParser#prodDiv}.
	 * @param ctx the parse tree
	 */
	void exitTimes(ReactiveParser.TimesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UMinus}
	 * labeled alternative in {@link ReactiveParser#uminus}.
	 * @param ctx the parse tree
	 */
	void enterUMinus(ReactiveParser.UMinusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UMinus}
	 * labeled alternative in {@link ReactiveParser#uminus}.
	 * @param ctx the parse tree
	 */
	void exitUMinus(ReactiveParser.UMinusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UMChild}
	 * labeled alternative in {@link ReactiveParser#uminus}.
	 * @param ctx the parse tree
	 */
	void enterUMChild(ReactiveParser.UMChildContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UMChild}
	 * labeled alternative in {@link ReactiveParser#uminus}.
	 * @param ctx the parse tree
	 */
	void exitUMChild(ReactiveParser.UMChildContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReactiveParser#parenExpr}.
	 * @param ctx the parse tree
	 */
	void enterParenExpr(ReactiveParser.ParenExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReactiveParser#parenExpr}.
	 * @param ctx the parse tree
	 */
	void exitParenExpr(ReactiveParser.ParenExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Double}
	 * labeled alternative in {@link ReactiveParser#atomic}.
	 * @param ctx the parse tree
	 */
	void enterDouble(ReactiveParser.DoubleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Double}
	 * labeled alternative in {@link ReactiveParser#atomic}.
	 * @param ctx the parse tree
	 */
	void exitDouble(ReactiveParser.DoubleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Integer}
	 * labeled alternative in {@link ReactiveParser#atomic}.
	 * @param ctx the parse tree
	 */
	void enterInteger(ReactiveParser.IntegerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Integer}
	 * labeled alternative in {@link ReactiveParser#atomic}.
	 * @param ctx the parse tree
	 */
	void exitInteger(ReactiveParser.IntegerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Identifier}
	 * labeled alternative in {@link ReactiveParser#atomic}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(ReactiveParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Identifier}
	 * labeled alternative in {@link ReactiveParser#atomic}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(ReactiveParser.IdentifierContext ctx);
}