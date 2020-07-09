// Generated from /home/mdelmonaco/Documents/GitHub/Reactive/src/grammar/Reactive.g4 by ANTLR 4.8
package grammar;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ReactiveParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		DOUBLE=1, INTEGER=2, IDENTIFIER=3, LPAREN=4, RPAREN=5, PLUS=6, MINUS=7, 
		TIMES=8, DIVIDE=9, EQUALS=10, WS=11;
	public static final int
		RULE_program = 0, RULE_assignment = 1, RULE_expr = 2, RULE_exprProgram = 3, 
		RULE_sumDiff = 4, RULE_prodDiv = 5, RULE_uminus = 6, RULE_parenExpr = 7, 
		RULE_atomic = 8;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "assignment", "expr", "exprProgram", "sumDiff", "prodDiv", 
			"uminus", "parenExpr", "atomic"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, "'('", "')'", "'+'", "'-'", "'*'", "'/'", "'='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "DOUBLE", "INTEGER", "IDENTIFIER", "LPAREN", "RPAREN", "PLUS", 
			"MINUS", "TIMES", "DIVIDE", "EQUALS", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Reactive.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ReactiveParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(ReactiveParser.EOF, 0); }
		public List<AssignmentContext> assignment() {
			return getRuleContexts(AssignmentContext.class);
		}
		public AssignmentContext assignment(int i) {
			return getRuleContext(AssignmentContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactiveListener ) ((ReactiveListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactiveListener ) ((ReactiveListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactiveVisitor ) return ((ReactiveVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(21);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IDENTIFIER) {
				{
				{
				setState(18);
				assignment();
				}
				}
				setState(23);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(24);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(ReactiveParser.IDENTIFIER, 0); }
		public TerminalNode EQUALS() { return getToken(ReactiveParser.EQUALS, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactiveListener ) ((ReactiveListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactiveListener ) ((ReactiveListener)listener).exitAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactiveVisitor ) return ((ReactiveVisitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			match(IDENTIFIER);
			setState(27);
			match(EQUALS);
			setState(28);
			expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public SumDiffContext e;
		public SumDiffContext sumDiff() {
			return getRuleContext(SumDiffContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactiveListener ) ((ReactiveListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactiveListener ) ((ReactiveListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactiveVisitor ) return ((ReactiveVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			((ExprContext)_localctx).e = sumDiff(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprProgramContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode EOF() { return getToken(ReactiveParser.EOF, 0); }
		public ExprProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprProgram; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactiveListener ) ((ReactiveListener)listener).enterExprProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactiveListener ) ((ReactiveListener)listener).exitExprProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactiveVisitor ) return ((ReactiveVisitor<? extends T>)visitor).visitExprProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprProgramContext exprProgram() throws RecognitionException {
		ExprProgramContext _localctx = new ExprProgramContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_exprProgram);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			expr();
			setState(33);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SumDiffContext extends ParserRuleContext {
		public SumDiffContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sumDiff; }
	 
		public SumDiffContext() { }
		public void copyFrom(SumDiffContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SDChildContext extends SumDiffContext {
		public ProdDivContext prodDiv() {
			return getRuleContext(ProdDivContext.class,0);
		}
		public SDChildContext(SumDiffContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactiveListener ) ((ReactiveListener)listener).enterSDChild(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactiveListener ) ((ReactiveListener)listener).exitSDChild(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactiveVisitor ) return ((ReactiveVisitor<? extends T>)visitor).visitSDChild(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PlusContext extends SumDiffContext {
		public SumDiffContext left;
		public ProdDivContext right;
		public TerminalNode PLUS() { return getToken(ReactiveParser.PLUS, 0); }
		public SumDiffContext sumDiff() {
			return getRuleContext(SumDiffContext.class,0);
		}
		public ProdDivContext prodDiv() {
			return getRuleContext(ProdDivContext.class,0);
		}
		public PlusContext(SumDiffContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactiveListener ) ((ReactiveListener)listener).enterPlus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactiveListener ) ((ReactiveListener)listener).exitPlus(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactiveVisitor ) return ((ReactiveVisitor<? extends T>)visitor).visitPlus(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MinusContext extends SumDiffContext {
		public SumDiffContext left;
		public ProdDivContext right;
		public TerminalNode MINUS() { return getToken(ReactiveParser.MINUS, 0); }
		public SumDiffContext sumDiff() {
			return getRuleContext(SumDiffContext.class,0);
		}
		public ProdDivContext prodDiv() {
			return getRuleContext(ProdDivContext.class,0);
		}
		public MinusContext(SumDiffContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactiveListener ) ((ReactiveListener)listener).enterMinus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactiveListener ) ((ReactiveListener)listener).exitMinus(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactiveVisitor ) return ((ReactiveVisitor<? extends T>)visitor).visitMinus(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SumDiffContext sumDiff() throws RecognitionException {
		return sumDiff(0);
	}

	private SumDiffContext sumDiff(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		SumDiffContext _localctx = new SumDiffContext(_ctx, _parentState);
		SumDiffContext _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_sumDiff, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new SDChildContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(36);
			prodDiv(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(46);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(44);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
					case 1:
						{
						_localctx = new PlusContext(new SumDiffContext(_parentctx, _parentState));
						((PlusContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_sumDiff);
						setState(38);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(39);
						match(PLUS);
						setState(40);
						((PlusContext)_localctx).right = prodDiv(0);
						}
						break;
					case 2:
						{
						_localctx = new MinusContext(new SumDiffContext(_parentctx, _parentState));
						((MinusContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_sumDiff);
						setState(41);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(42);
						match(MINUS);
						setState(43);
						((MinusContext)_localctx).right = prodDiv(0);
						}
						break;
					}
					} 
				}
				setState(48);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ProdDivContext extends ParserRuleContext {
		public ProdDivContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prodDiv; }
	 
		public ProdDivContext() { }
		public void copyFrom(ProdDivContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class DivideContext extends ProdDivContext {
		public ProdDivContext left;
		public UminusContext right;
		public TerminalNode DIVIDE() { return getToken(ReactiveParser.DIVIDE, 0); }
		public ProdDivContext prodDiv() {
			return getRuleContext(ProdDivContext.class,0);
		}
		public UminusContext uminus() {
			return getRuleContext(UminusContext.class,0);
		}
		public DivideContext(ProdDivContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactiveListener ) ((ReactiveListener)listener).enterDivide(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactiveListener ) ((ReactiveListener)listener).exitDivide(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactiveVisitor ) return ((ReactiveVisitor<? extends T>)visitor).visitDivide(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PDChildContext extends ProdDivContext {
		public UminusContext uminus() {
			return getRuleContext(UminusContext.class,0);
		}
		public PDChildContext(ProdDivContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactiveListener ) ((ReactiveListener)listener).enterPDChild(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactiveListener ) ((ReactiveListener)listener).exitPDChild(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactiveVisitor ) return ((ReactiveVisitor<? extends T>)visitor).visitPDChild(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TimesContext extends ProdDivContext {
		public ProdDivContext left;
		public UminusContext right;
		public TerminalNode TIMES() { return getToken(ReactiveParser.TIMES, 0); }
		public ProdDivContext prodDiv() {
			return getRuleContext(ProdDivContext.class,0);
		}
		public UminusContext uminus() {
			return getRuleContext(UminusContext.class,0);
		}
		public TimesContext(ProdDivContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactiveListener ) ((ReactiveListener)listener).enterTimes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactiveListener ) ((ReactiveListener)listener).exitTimes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactiveVisitor ) return ((ReactiveVisitor<? extends T>)visitor).visitTimes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProdDivContext prodDiv() throws RecognitionException {
		return prodDiv(0);
	}

	private ProdDivContext prodDiv(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ProdDivContext _localctx = new ProdDivContext(_ctx, _parentState);
		ProdDivContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_prodDiv, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new PDChildContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(50);
			uminus();
			}
			_ctx.stop = _input.LT(-1);
			setState(60);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(58);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
					case 1:
						{
						_localctx = new TimesContext(new ProdDivContext(_parentctx, _parentState));
						((TimesContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_prodDiv);
						setState(52);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(53);
						match(TIMES);
						setState(54);
						((TimesContext)_localctx).right = uminus();
						}
						break;
					case 2:
						{
						_localctx = new DivideContext(new ProdDivContext(_parentctx, _parentState));
						((DivideContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_prodDiv);
						setState(55);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(56);
						match(DIVIDE);
						setState(57);
						((DivideContext)_localctx).right = uminus();
						}
						break;
					}
					} 
				}
				setState(62);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class UminusContext extends ParserRuleContext {
		public UminusContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_uminus; }
	 
		public UminusContext() { }
		public void copyFrom(UminusContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class UMinusContext extends UminusContext {
		public UminusContext child;
		public TerminalNode MINUS() { return getToken(ReactiveParser.MINUS, 0); }
		public UminusContext uminus() {
			return getRuleContext(UminusContext.class,0);
		}
		public UMinusContext(UminusContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactiveListener ) ((ReactiveListener)listener).enterUMinus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactiveListener ) ((ReactiveListener)listener).exitUMinus(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactiveVisitor ) return ((ReactiveVisitor<? extends T>)visitor).visitUMinus(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UMChildContext extends UminusContext {
		public ParenExprContext parenExpr() {
			return getRuleContext(ParenExprContext.class,0);
		}
		public UMChildContext(UminusContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactiveListener ) ((ReactiveListener)listener).enterUMChild(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactiveListener ) ((ReactiveListener)listener).exitUMChild(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactiveVisitor ) return ((ReactiveVisitor<? extends T>)visitor).visitUMChild(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UminusContext uminus() throws RecognitionException {
		UminusContext _localctx = new UminusContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_uminus);
		try {
			setState(66);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MINUS:
				_localctx = new UMinusContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(63);
				match(MINUS);
				setState(64);
				((UMinusContext)_localctx).child = uminus();
				}
				break;
			case DOUBLE:
			case INTEGER:
			case IDENTIFIER:
			case LPAREN:
				_localctx = new UMChildContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(65);
				parenExpr();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParenExprContext extends ParserRuleContext {
		public AtomicContext atomic() {
			return getRuleContext(AtomicContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(ReactiveParser.LPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ReactiveParser.RPAREN, 0); }
		public ParenExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parenExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactiveListener ) ((ReactiveListener)listener).enterParenExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactiveListener ) ((ReactiveListener)listener).exitParenExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactiveVisitor ) return ((ReactiveVisitor<? extends T>)visitor).visitParenExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParenExprContext parenExpr() throws RecognitionException {
		ParenExprContext _localctx = new ParenExprContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_parenExpr);
		try {
			setState(73);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DOUBLE:
			case INTEGER:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(68);
				atomic();
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 2);
				{
				setState(69);
				match(LPAREN);
				setState(70);
				expr();
				setState(71);
				match(RPAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AtomicContext extends ParserRuleContext {
		public AtomicContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atomic; }
	 
		public AtomicContext() { }
		public void copyFrom(AtomicContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class IntegerContext extends AtomicContext {
		public TerminalNode INTEGER() { return getToken(ReactiveParser.INTEGER, 0); }
		public IntegerContext(AtomicContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactiveListener ) ((ReactiveListener)listener).enterInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactiveListener ) ((ReactiveListener)listener).exitInteger(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactiveVisitor ) return ((ReactiveVisitor<? extends T>)visitor).visitInteger(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IdentifierContext extends AtomicContext {
		public TerminalNode IDENTIFIER() { return getToken(ReactiveParser.IDENTIFIER, 0); }
		public IdentifierContext(AtomicContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactiveListener ) ((ReactiveListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactiveListener ) ((ReactiveListener)listener).exitIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactiveVisitor ) return ((ReactiveVisitor<? extends T>)visitor).visitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DoubleContext extends AtomicContext {
		public TerminalNode DOUBLE() { return getToken(ReactiveParser.DOUBLE, 0); }
		public DoubleContext(AtomicContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactiveListener ) ((ReactiveListener)listener).enterDouble(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactiveListener ) ((ReactiveListener)listener).exitDouble(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactiveVisitor ) return ((ReactiveVisitor<? extends T>)visitor).visitDouble(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomicContext atomic() throws RecognitionException {
		AtomicContext _localctx = new AtomicContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_atomic);
		try {
			setState(78);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DOUBLE:
				_localctx = new DoubleContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(75);
				match(DOUBLE);
				}
				break;
			case INTEGER:
				_localctx = new IntegerContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(76);
				match(INTEGER);
				}
				break;
			case IDENTIFIER:
				_localctx = new IdentifierContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(77);
				match(IDENTIFIER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 4:
			return sumDiff_sempred((SumDiffContext)_localctx, predIndex);
		case 5:
			return prodDiv_sempred((ProdDivContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean sumDiff_sempred(SumDiffContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean prodDiv_sempred(ProdDivContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 3);
		case 3:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\rS\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\7\2\26"+
		"\n\2\f\2\16\2\31\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6/\n\6\f\6\16\6\62\13\6\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\7\7=\n\7\f\7\16\7@\13\7\3\b\3\b\3\b\5\bE\n\b"+
		"\3\t\3\t\3\t\3\t\3\t\5\tL\n\t\3\n\3\n\3\n\5\nQ\n\n\3\n\2\4\n\f\13\2\4"+
		"\6\b\n\f\16\20\22\2\2\2R\2\27\3\2\2\2\4\34\3\2\2\2\6 \3\2\2\2\b\"\3\2"+
		"\2\2\n%\3\2\2\2\f\63\3\2\2\2\16D\3\2\2\2\20K\3\2\2\2\22P\3\2\2\2\24\26"+
		"\5\4\3\2\25\24\3\2\2\2\26\31\3\2\2\2\27\25\3\2\2\2\27\30\3\2\2\2\30\32"+
		"\3\2\2\2\31\27\3\2\2\2\32\33\7\2\2\3\33\3\3\2\2\2\34\35\7\5\2\2\35\36"+
		"\7\f\2\2\36\37\5\6\4\2\37\5\3\2\2\2 !\5\n\6\2!\7\3\2\2\2\"#\5\6\4\2#$"+
		"\7\2\2\3$\t\3\2\2\2%&\b\6\1\2&\'\5\f\7\2\'\60\3\2\2\2()\f\5\2\2)*\7\b"+
		"\2\2*/\5\f\7\2+,\f\4\2\2,-\7\t\2\2-/\5\f\7\2.(\3\2\2\2.+\3\2\2\2/\62\3"+
		"\2\2\2\60.\3\2\2\2\60\61\3\2\2\2\61\13\3\2\2\2\62\60\3\2\2\2\63\64\b\7"+
		"\1\2\64\65\5\16\b\2\65>\3\2\2\2\66\67\f\5\2\2\678\7\n\2\28=\5\16\b\29"+
		":\f\4\2\2:;\7\13\2\2;=\5\16\b\2<\66\3\2\2\2<9\3\2\2\2=@\3\2\2\2><\3\2"+
		"\2\2>?\3\2\2\2?\r\3\2\2\2@>\3\2\2\2AB\7\t\2\2BE\5\16\b\2CE\5\20\t\2DA"+
		"\3\2\2\2DC\3\2\2\2E\17\3\2\2\2FL\5\22\n\2GH\7\6\2\2HI\5\6\4\2IJ\7\7\2"+
		"\2JL\3\2\2\2KF\3\2\2\2KG\3\2\2\2L\21\3\2\2\2MQ\7\3\2\2NQ\7\4\2\2OQ\7\5"+
		"\2\2PM\3\2\2\2PN\3\2\2\2PO\3\2\2\2Q\23\3\2\2\2\n\27.\60<>DKP";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}